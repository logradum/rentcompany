package telran.cars.controller.items;

import java.util.List;
import java.util.stream.Collectors;
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
		List<Car> carList = cars.collect(Collectors.toList());
		boolean isEmpty = carList.isEmpty();
		if (isEmpty) {
			System.out.println("Sorry, there is no cars in Company.");
		} else {
			inputOutput.displayLine(carList);
		}
	}

}
