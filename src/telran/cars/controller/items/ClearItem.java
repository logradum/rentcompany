package telran.cars.controller.items;

import java.time.LocalDate;
import java.util.List;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.Car;
import telran.view.InputOutput;

public class ClearItem extends RentCompanyItem {

	public ClearItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "ClearItem car list";
	}

	@Override
	public void perform() {
		LocalDate localDate = inputOutput.getDate("Please enter from what date to clear company history");
		int days = inputOutput.getInteger("Please enter number of days to clear company history");
		List<Car> cars = company.clear(localDate, days);
		System.out.println("Company history has been cleared from these cars:" + cars);
	}

}
