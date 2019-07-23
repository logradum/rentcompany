package telran.cars.controller.items;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;

public class GetDriverItem extends RentCompanyItem {

	public GetDriverItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display driver";
	}

	@Override
	public void perform() {
		Predicate<String> p = Pattern.compile("[0-9]{5-10}").asPredicate();
		String idString = inputOutput.getString("Please enter license ID", p);
		Long licenseId = Long.parseLong(idString);
		System.out.println(company.getDriver(licenseId));
	}

}
