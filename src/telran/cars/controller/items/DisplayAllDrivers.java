package telran.cars.controller.items;

import java.util.stream.Stream;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.Driver;
import telran.view.InputOutput;

public class DisplayAllDrivers extends RentCompanyItem {

	public DisplayAllDrivers(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display all drivers";
	}

	@Override
	public void perform() {

		Stream<Driver> drivers = company.getAllDrivers();
		boolean isEmpty = !(drivers.findFirst().isPresent());
		if (isEmpty) {
			System.out.println("Sorry, there is no records");
		} else {
			drivers.forEach(System.out::println);
		}

	}

}
