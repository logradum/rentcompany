package telran.cars.controller.items;

import java.util.List;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.Driver;
import telran.view.InputOutput;

public class DisplayCarsByDriverItem extends RentCompanyItem {

	public DisplayCarsByDriverItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display drivers by car";
	}

	@Override
	public void perform() {
		String regNumber = inputOutput.getString("Please enter registration number");
		List<Driver> drivers = company.getCarDrivers(regNumber);
		drivers.stream().forEach(System.out::println);
	}

}
