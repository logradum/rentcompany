package telran.cars.controller.items;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import telran.cars.dao.IRentCompany;
import telran.cars.dao.RentCompany;
import telran.view.InputOutput;

public class RestoreCompany extends RentCompanyItem {

	public RestoreCompany(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Restore company from file";
	}

	@Override
	public void perform() {
		
		Predicate<String> p = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").asPredicate();
		String companySaveDate = inputOutput.getString("Please enter date of last changes using format 2010-01-01", p);
		p = Pattern.compile("^y|^n|^$ ").asPredicate();
		String answer = inputOutput
				.getString("File name with your last changes Company_"+companySaveDate+". Do you want to continue? (y/n)",p);
		if (answer.contentEquals("y")) {
			System.out.println("Restoring...");
		company = RentCompany.restoreFromFile("Company_" +companySaveDate);
		if (company.equals(null)) {System.out.println("Company is empty");}
		} else {System.out.println("Choose another menu item");}		
	}

}
