package telran.cars.dao;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import telran.cars.domain.Car;
import telran.cars.domain.Driver;
import telran.cars.domain.Model;
import telran.cars.domain.RentRecord;
import telran.cars.dto.CarsReturnCode;

public interface IRentCompany extends Serializable {
	
	
	CarsReturnCode addCar(Car car);

	CarsReturnCode addModel(Model model);

	CarsReturnCode addDriver(Driver driver);

	Car getCar(String regNumber);

	Model getModel(String modelName);

	Driver getDriver(long licenseId);
	
	CarsReturnCode rentCar(long licenseId, String regNumber, 
			LocalDate rentDate, int rentDays);
	
	CarsReturnCode returnCar(long licenseId, String regNumber,
			LocalDate returnDate, int gasTankPercent, int damages);
	
	CarsReturnCode removeCar(String regNumber);
	
	List<Car> clear(LocalDate currentDate, int days);
	
	List<Driver> getCarDrivers(String regNumber);
	
	List<Car> getDriverCars(long licenseId);
	
	Stream<Car> getAllCars();
	
	Stream<Driver> getAllDrivers();
	
	Stream<RentRecord> getAllRecords();
	
	List<String> getMostPopularModelNames();
	
	double getModelProfit(String modelName);
	
	List<String> getMostProfitModelNames();
	
	void save(String fileName);
}






