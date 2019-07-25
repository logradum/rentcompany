package telran.cars.controller.items;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.Driver;
import telran.view.InputOutput;

public class AddDriverItem extends RentCompanyItem {

	public AddDriverItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Add Driver";
	}

	@Override
	public void perform() {
		Predicate<String> p = Pattern.compile("[0-9]{5,10}").asPredicate();
		String idString = inputOutput.getString("Please enter license ID", p);
		Long licenseId = Long.parseLong(idString);
		String name = inputOutput.getString("Enter Driver's name");
		//LocalDate birthDayDate = inputOutput.getDate("Enter Drivers birth date", 
		//		LocalDate.parse("1950-01-01"), LocalDate.now().minusYears(20));
		int birthYear = inputOutput.getInteger("Enter Drivers year of birth");
		String phone = inputOutput.getString("Please enter Drivers phone");
		Driver driver = new Driver(licenseId,name,birthYear,phone);
		company.addDriver(driver);

	}

}
