package telran.cars.controller.items;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import telran.cars.dao.IRentCompany;
import telran.cars.domain.Car;
import telran.view.InputOutput;

public class DisplayDriverCarsItem extends RentCompanyItem {

	public DisplayDriverCarsItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display cars used by driver";
	}

	@Override
	public void perform() {
		Predicate<String> p = Pattern.compile("[0-9]{5,10}").asPredicate();
		String idString = inputOutput.getString("Please enter license ID", p);
		Long licenseId = Long.parseLong(idString);
		List<Car> cars = company.getDriverCars(licenseId);
		cars.stream().forEach(System.out::println);
	}

}
