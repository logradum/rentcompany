package telran.cars.tests;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import telran.cars.dao.AbstractRentCompany;
import telran.cars.dao.IRentCompany;
import telran.cars.dao.RentCompany;
import telran.cars.domain.Car;
import telran.cars.domain.Driver;
import telran.cars.domain.Model;
import telran.cars.domain.RentRecord;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.State;

public class RentCompanyTest {
	private static final String REG_NUMBER1 = "123";
	private static final String REG_NUMBER2 = "124";
	private static final String REG_NUMBER3 = "125";
	private static final String MODEL1 = "BMW12";
	private static final String MODEL2 = "B4";
	private static final long LICENSE1 = 123;
	private static final long LICENSE2 = 124;
	private static final LocalDate RENT_DATE1 = 
			LocalDate.of(2019, 5, 28);
	private static final int RENT_DAYS1 = 5;
	private static final LocalDate RETURN_DATE = RENT_DATE1.plusDays(RENT_DAYS1);
	private static final LocalDate RETURN_DATE_WRONG = RENT_DATE1.minusDays(1);
	private static final long DELAY_DAYS1 = 2;
	private static final LocalDate RETURN_DATE_DELAY = RETURN_DATE.plusDays(DELAY_DAYS1);
	private static final int GAS_PERCENT = 50;
	private static final int DAMAGES = 50;
	
	IRentCompany company;
	
	Car car1 = new Car(REG_NUMBER1, "red", MODEL1);
	Car car2 = new Car(REG_NUMBER2, "green", MODEL2);
	Car car3 = new Car(REG_NUMBER3, "silver", MODEL1);
	Model model1 = new Model(MODEL1, 55, "Germany", "BMW", 200);
	Model model2 = new Model(MODEL2, 50, "Japan", "Subaru", 190);
	Driver driver1 = new Driver(LICENSE1, "Moshe", 1980, "050-1234567");
	Driver driver2 = new Driver(LICENSE2, "David", 1960, "050-7654321");
	RentRecord recordRent = new RentRecord(LICENSE1, REG_NUMBER1, RENT_DATE1, RENT_DAYS1);

	@Before
	public void setUp() throws Exception {
		Class<?> clazz = IRentCompany.class;
		//Constructor<?> constructor = clazz.getConstructor();
		
		company=RentCompany.restoreFromFile(null);
		company.addModel(model1);
		company.addDriver(driver1);
		company.addCar(car1);
		company.rentCar(LICENSE1, REG_NUMBER1, RENT_DATE1, RENT_DAYS1);
	}

	@Test
	public void testAddModel() {
		assertEquals(CarsReturnCode.MODEL_EXISTS,company.addModel(model1));
		assertEquals(CarsReturnCode.OK,company.addModel(model2));
	}

	@Test
	public void testAddCar() {
		assertEquals(CarsReturnCode.CAR_EXISTS,company.addCar(car1));
		assertEquals(CarsReturnCode.NO_MODEL,company.addCar(car2));
		assertEquals(CarsReturnCode.OK,company.addCar(car3));
	}

	@Test
	public void testAddDriver() {
		assertEquals(CarsReturnCode.DRIVER_EXISTS,company.addDriver(driver1));
		assertEquals(CarsReturnCode.OK,company.addDriver(driver2));
	}

	@Test
	public void testGetModel() {
		assertNull(company.getModel(MODEL2));
		assertEquals(model1,company.getModel(MODEL1));
	}

	@Test
	public void testGetCar() {
		assertNull(company.getCar(REG_NUMBER2));
		assertEquals(car1,company.getCar(REG_NUMBER1));
	}

	@Test
	public void testGetDriver() {
		assertNull(company.getDriver(LICENSE2));
		assertEquals(driver1,company.getDriver(LICENSE1));
	}
	
	@Test
	public void testRentCar() {
		assertEquals(CarsReturnCode.CAR_IN_USE, 
				company.rentCar(LICENSE1, REG_NUMBER1, RENT_DATE1, RENT_DAYS1));
		assertEquals(CarsReturnCode.CAR_NOT_EXISTS, 
				company.rentCar(LICENSE1, REG_NUMBER2, RENT_DATE1, RENT_DAYS1));
		company.addModel(model2);
		company.addCar(car2);
		assertEquals(CarsReturnCode.NO_DRIVER, 
				company.rentCar(LICENSE2, REG_NUMBER2, RENT_DATE1, RENT_DAYS1));
		assertEquals(CarsReturnCode.OK, 
				company.rentCar(LICENSE1, REG_NUMBER2, RENT_DATE1, RENT_DAYS1));
		RentRecord record1 = getRecord(REG_NUMBER1);
		assertEquals(LICENSE1, record1.getLicenseId());
		assertEquals(RENT_DATE1, record1.getRentDate());
		assertEquals(RENT_DAYS1, record1.getRentDays());
		assertTrue(car1.isInUse());
		assertEquals(recordRent, record1);
	}

	private RentRecord getRecord(String regNumber1) {
		return company.getAllRecords()
				.filter(r -> r.getRegNumber().equals(regNumber1))
				.findFirst().orElse(null);
	}
	
	@Test
	public void testGetAllRecords() {
		company.getAllRecords()
		.forEach(r -> assertEquals(recordRent, r));
	}

	@Test
	public void testGetCarDrivers() {
		company.getCarDrivers(REG_NUMBER1)
		.forEach(d -> assertEquals(driver1, d));
		assertNull(company.getCarDrivers(REG_NUMBER2));
	}

	@Test
	public void testGetDriverCars() {
		company.getDriverCars(LICENSE1).forEach(c -> assertEquals(car1, c));
		assertNull(company.getDriverCars(LICENSE2));
	}
	
	@Test
	public void testReturnCarCode() {
		assertEquals(CarsReturnCode.CAR_NOT_RENTED, 
				company.returnCar(LICENSE1, REG_NUMBER2, RETURN_DATE, 100, 0));
		assertEquals(CarsReturnCode.NO_DRIVER, 
				company.returnCar(LICENSE2, REG_NUMBER1, RETURN_DATE, 100, 0));
		company.addDriver(driver2);
		assertEquals(CarsReturnCode.CAR_NOT_RENTED, 
				company.returnCar(LICENSE2, REG_NUMBER1, RETURN_DATE, 100, 0));
		assertEquals(CarsReturnCode.RETURN_DATE_WRONG, 
				company.returnCar(LICENSE1, REG_NUMBER1, RETURN_DATE_WRONG, 100, 0));
		assertEquals(CarsReturnCode.OK, 
				company.returnCar(LICENSE1, REG_NUMBER1, RETURN_DATE, 100, 0));
		
	}
	
	@Test
	public void returnCarNoDamagesNoAdditionalCost() {
		company.returnCar(LICENSE1, REG_NUMBER1, RETURN_DATE, 100, 0);
		assertFalse(car1.isInUse());
		assertEquals(State.EXCELLENT, car1.getState());
		assertFalse(car1.isFlRemoved());
		recordRent.setReturnDate(RETURN_DATE);
		recordRent.setGasTankPercent(100);
		recordRent.setDamages(0);
		recordRent.setCost(RENT_DAYS1 * model1.getPriceDay());
		RentRecord record = getRecord(REG_NUMBER1);
		assertEquals(recordRent, record);
		assertEquals(recordRent.getReturnDate(), record.getReturnDate());
		assertEquals(recordRent.getDamages(), record.getDamages());
		assertEquals(recordRent.getGasTankPercent(), record.getGasTankPercent());
		assertEquals(recordRent.getCost(), record.getCost(), 0.01);
	}
	
	@Test
	public void returnCarWithDamagesAdditionalCosts() {
		company.returnCar(LICENSE1, REG_NUMBER1, RETURN_DATE_DELAY, GAS_PERCENT, DAMAGES);
		assertFalse(car1.isInUse());
		assertTrue(car1.isFlRemoved());
		recordRent.setReturnDate(RETURN_DATE_DELAY);
		recordRent.setGasTankPercent(GAS_PERCENT);
		recordRent.setDamages(DAMAGES);
		recordRent.setCost(RENT_DAYS1 * model1.getPriceDay() + 
				getAdditionalCost());
		RentRecord record = getRecord(REG_NUMBER1);
		assertEquals(recordRent, record);
		assertEquals(recordRent.getReturnDate(), record.getReturnDate());
		assertEquals(recordRent.getDamages(), record.getDamages());
		assertEquals(recordRent.getGasTankPercent(), record.getGasTankPercent());
		assertEquals(recordRent.getCost(), record.getCost(), 0.01);
	}

	private double getAdditionalCost() {
		int gasPrice = ((AbstractRentCompany)company).getGasPrice();
		int finePerDay = ((AbstractRentCompany)company).getFinePercent();
		int gasTank = model1.getGasTank();
		int priceDay = model1.getPriceDay();
		return (gasTank - GAS_PERCENT * gasTank / 100.) * gasPrice +
				DELAY_DAYS1 * (priceDay  + finePerDay * priceDay / 100.);
	}
}
