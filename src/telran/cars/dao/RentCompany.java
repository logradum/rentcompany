package telran.cars.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import telran.cars.domain.Car;
import telran.cars.domain.Driver;
import telran.cars.domain.Model;
import telran.cars.domain.RentRecord;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.State;

public class RentCompany extends AbstractRentCompany implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Car> cars = new HashMap<>();
	private HashMap<Long, Driver> drivers = new HashMap<>();
	private HashMap<String, Model> models = new HashMap<>();
	private HashMap<String, List<RentRecord>> carRecords = new HashMap<>();
	private HashMap<Long, List<RentRecord>> driverRecords = new HashMap<>();
	private TreeMap<LocalDate, List<RentRecord>> returnedRecords = new TreeMap<>();
	private static final String DEFAULT_FILE_NAME = "data";

	
	private RentCompany() {
		
	}
	

	public static RentCompany restoreFromFile() {
		return restoreFromFile(DEFAULT_FILE_NAME);
	}

	public static RentCompany restoreFromFile(String fileName) {
		RentCompany rentCompany;
		
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			rentCompany = (RentCompany) in.readObject();
			System.out.println("Restored successfully");
		} catch (Exception e) {
		
			rentCompany = new RentCompany();
		}
		
		return rentCompany;
	
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		Model model = models.get(car.getModelName());
		if (model == null) {
			return CarsReturnCode.NO_MODEL;
		}
		return cars.putIfAbsent(car.getRegNumber(), car) == null ? CarsReturnCode.OK : CarsReturnCode.CAR_EXISTS;
	}

	@Override
	public CarsReturnCode addModel(Model model) {
		return models.putIfAbsent(model.getModelName(), model) == null ? CarsReturnCode.OK
				: CarsReturnCode.MODEL_EXISTS;
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		return drivers.putIfAbsent(driver.getLicenseId(), driver) == null ? CarsReturnCode.OK
				: CarsReturnCode.DRIVER_EXISTS;
	}

	@Override
	public Car getCar(String regNumber) {
		return cars.get(regNumber);
	}

	@Override
	public Model getModel(String modelName) {
		return models.get(modelName);
	}

	@Override
	public Driver getDriver(long licenseId) {
		return drivers.get(licenseId);
	}

	@Override
	public CarsReturnCode rentCar(long licenseId, String regNumber, LocalDate rentDate, int rentDays) {
		CarsReturnCode code = checkRentCar(licenseId, regNumber);
		if (CarsReturnCode.OK == code) {
			RentRecord record = new RentRecord(licenseId, regNumber, rentDate, rentDays);
			addCarRecords(record);
			addDriverRecords(record);
			setInUse(record);
		}
		return code;
	}

	private void setInUse(RentRecord record) {
		String regNumber = record.getRegNumber();
		Car car = cars.get(regNumber);
		car.setInUse(true);

	}

	private void addDriverRecords(RentRecord record) {
		long licensId = record.getLicenseId();
		List<RentRecord> records = driverRecords.get(licensId);
		if (records == null) {
			records = new ArrayList<>();
			driverRecords.put(licensId, records);
		}
		records.add(record);
	}

	private void addCarRecords(RentRecord record) {
		String regNumber = record.getRegNumber();
		List<RentRecord> records = carRecords.get(regNumber);
		if (records == null) {
			records = new ArrayList<>();
			carRecords.put(regNumber, records);
		}
		records.add(record);

	}

	private CarsReturnCode checkRentCar(long licenseId, String regNumber) {
		Car car = cars.get(regNumber);
		if (car == null || car.isFlRemoved()) {
			return CarsReturnCode.CAR_NOT_EXISTS;
		}
		if (car.isInUse()) {
			return CarsReturnCode.CAR_IN_USE;
		}
		if (!drivers.containsKey(licenseId)) {
			return CarsReturnCode.NO_DRIVER;
		}
		return CarsReturnCode.OK;
	}

	@Override
	public CarsReturnCode returnCar(long licenseId, String regNumber, LocalDate returnDate, int gasTankPercent,
			int damages) {
		RentRecord record = getRentRecord(regNumber, licenseId);
		CarsReturnCode code = checkReturnCar(regNumber, licenseId, returnDate, record);

		if (code == CarsReturnCode.OK) {
			Car car = carSettings(regNumber, damages);
			record.setReturnDate(returnDate);
			record.setDamages(damages);
			record.setGasTankPercent(gasTankPercent);
			Model model = models.get(car.getModelName());
			calcCosts(record, model);
			List<RentRecord> records = returnedRecords.get(returnDate);
			if (records == null) {
				records = new ArrayList<>();
				returnedRecords.put(returnDate, records);
			}
			records.add(record);
		}
		return code;
	}

	private void calcCosts(RentRecord record, Model model) {
		double cost = record.getRentDays() * model.getPriceDay();
		double gasCost = (100 - record.getGasTankPercent()) / 100.0 * model.getGasTank() * gasPrice;
		LocalDate expectedReturnDate = record.getRentDate().plusDays(record.getRentDays());
		long delayDays = ChronoUnit.DAYS.between(expectedReturnDate, record.getReturnDate());
		double fineCost = delayDays * (100 + finePercent) / 100.0 * model.getPriceDay();
		fineCost = fineCost > 0 ? fineCost : 0;
		record.setCost(cost + gasCost + fineCost);
	}

	private Car carSettings(String regNumber, int damages) {
		Car car = cars.get(regNumber);
		car.setInUse(false);
		if (damages > 0 && damages <= 10) {
			car.setState(State.GOOD);
		}
		if (damages > 10) {
			car.setState(State.BAD);
		}
		if (damages > 30) {
			car.setFlRemoved(true);
		}
		return car;
	}

	private RentRecord getRentRecord(String regNumber, long licenseId) {
		List<RentRecord> records = carRecords.get(regNumber);
		return records == null ? null
				: records.stream().filter(r -> r.getReturnDate() == null && r.getLicenseId() == licenseId).findFirst()
						.orElse(null);
	}

	private CarsReturnCode checkReturnCar(String carNumber, long licenseId, LocalDate returnDate,
			RentRecord rentRecord) {
		if (drivers.get(licenseId) == null) {
			return CarsReturnCode.NO_DRIVER;
		}
		if (rentRecord == null) {
			return CarsReturnCode.CAR_NOT_RENTED;
		}
		if (returnDate.isBefore(rentRecord.getRentDate())) {
			return CarsReturnCode.RETURN_DATE_WRONG;
		}
		return CarsReturnCode.OK;
	}

	@Override
	public CarsReturnCode removeCar(String regNumber) {
		Car car = cars.get(regNumber);
		if (car == null) {
			return CarsReturnCode.CAR_NOT_EXISTS;
		}

		return null;
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		Set<String> regNumbers = returnedRecords.headMap(currentDate.minusDays(days)).values().stream()
				.flatMap(l -> l.stream()).filter(r -> cars.get(r.getRegNumber()).isFlRemoved())
				.map(r -> r.getRegNumber()).collect(Collectors.toSet());
		List<Car> res = cars.entrySet().stream().filter(e -> regNumbers.contains(e.getKey())).map(c -> c.getValue())
				.collect(Collectors.toList());
		regNumbers.stream().forEach(r -> {
			cars.remove(r);
			carRecords.remove(r);
			driverRecords.values().stream().forEach(l -> l.removeIf(lr -> regNumbers.contains(lr.getRegNumber())));
			returnedRecords.values().stream().forEach(l -> l.removeIf(lr -> regNumbers.contains(lr.getRegNumber())));

		});

		return res;
	}

	@Override
	public List<Driver> getCarDrivers(String regNumber) {
		return carRecords.containsKey(regNumber)
				? carRecords.get(regNumber).stream().map(r -> r.getLicenseId()).map(l -> drivers.get(l)).distinct()
						.collect(Collectors.toList())
				: null;
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		return driverRecords.containsKey(licenseId)
				? driverRecords.get(licenseId).stream().map(r -> r.getRegNumber()).map(rn -> cars.get(rn)).distinct()
						.collect(Collectors.toList())
				: null;
	}

	@Override
	public Stream<Car> getAllCars() {
		return cars.values().stream();
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		return drivers.values().stream();
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		return carRecords.values().stream().flatMap(l -> l.stream());
	}

	@Override
	public List<String> getMostPopularModelNames() {
		Map<String, Long> modelFrequency = getAllRecords().map(r -> r.getRegNumber()).map(n -> cars.get(n))
				.collect(Collectors.groupingBy(c -> c.getModelName(), Collectors.counting()));

		long max = modelFrequency.values().stream().max(Long::compareTo).orElse(null);
		return modelFrequency.entrySet().stream().filter(m -> m.getValue() == max).map(c -> c.getKey())
				.collect(Collectors.toList());
	}

	@Override
	public double getModelProfit(String modelName) {

		return getAllRecords().filter(r -> cars.get(r.getRegNumber()).getModelName().equals(modelName))
				.mapToDouble(RentRecord::getCost).sum();
	}

	@Override
	public List<String> getMostProfitModelNames() {
		Map<String, Double> modelCosts = getAllRecords().collect(Collectors.groupingBy(
				r -> cars.get(r.getRegNumber()).getModelName(), Collectors.summingDouble(r -> r.getCost())));
		double max = modelCosts.values().stream().max(Double::compare).orElse(0.0);
		return modelCosts.entrySet().stream().filter(e -> e.getValue() == max).map(e -> e.getKey())
				.collect(Collectors.toList());
	}

	@Override
	public void save(String fileName) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
		out.writeObject(this);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))){
//			out.writeObject(new RentCompany().setCars(cars).setDrivers(drivers)
//					.setModels(models).setCarRecords(carRecords)
//					.setDriverRecords(driverRecords).setReturnedRecords(returnedRecords));
//			
//		} catch (FileNotFoundException e) {
//			try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(rentCompanyFileName))){
//				out.writeObject(restoreFromFile());
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public RentCompany setCars(HashMap<String, Car> cars) {
		 this.cars = cars;
		 return this;
	}

	public RentCompany setDrivers(HashMap<Long, Driver> drivers) {
		this.drivers = drivers;
		 return this;
	}

	public RentCompany setModels(HashMap<String, Model> models) {
		this.models = models;
		 return this;
	}

	public RentCompany setCarRecords(HashMap<String, List<RentRecord>> carRecords) {
		this.carRecords = carRecords;
		 return this;
	}

	public RentCompany setDriverRecords(HashMap<Long, List<RentRecord>> driverRecords) {
		this.driverRecords = driverRecords;
		 return this;
	}

	public RentCompany setReturnedRecords(TreeMap<LocalDate, List<RentRecord>> returnedRecords) {
		this.returnedRecords = returnedRecords;
		 return this;
	}
	
}
