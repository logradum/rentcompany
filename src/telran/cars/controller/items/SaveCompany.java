package telran.cars.controller.items;

import java.time.LocalDate;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;

public class SaveCompany extends RentCompanyItem {

	public SaveCompany(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Save recent changes";
	}

	@Override
	public void perform() {
		company.save("Company_"+LocalDate.now().toString());
		System.out.println("saved successfully");
	}

}
