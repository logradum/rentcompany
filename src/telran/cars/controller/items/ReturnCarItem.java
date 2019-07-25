package telran.cars.controller.items;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import telran.cars.dao.IRentCompany;
import telran.cars.dto.CarsReturnCode;
import telran.view.InputOutput;

public class ReturnCarItem extends RentCompanyItem {

	public ReturnCarItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Return a car";
	}

	@Override
	public void perform() {
		Predicate<String> p = Pattern.compile("[0-9]{5,10}").asPredicate();
		String idString = inputOutput.getString("Please enter license ID", p);
		Long licenseId = Long.parseLong(idString);
		String regNumber = inputOutput.getString("Please enter registration number");
		LocalDate returnDate = inputOutput.getDate("Please enter a rental end date");
		int gasTankPercent = inputOutput.getInteger("Please enter remaining gas in liters", 0, 100);
		int damages = inputOutput.getInteger("Please enter demages in percents from 100 to 0", 0, 100);
		CarsReturnCode res = company.returnCar(licenseId, regNumber, returnDate, gasTankPercent, damages);
		System.out.println(res);
	}

}
