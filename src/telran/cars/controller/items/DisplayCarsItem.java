package telran.cars.controller.items;

import java.util.stream.Stream;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.Car;
import telran.view.InputOutput;

public class DisplayCarsItem extends RentCompanyItem {

	public DisplayCarsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display all cars";
	}

	@Override
	public void perform() {

		Stream<Car> cars = company.getAllCars();
		boolean isEmpty = !(cars.findFirst().isPresent());
		if (isEmpty) {
			System.out.println("Sorry, there is no cars in Company.");
		} else {
			cars.forEach(System.out::println);
		}
	}

}
