package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;

public class GetAllDriverItems extends RentCompanyItem {

	public GetAllDriverItems(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Get all driver notes";
	}

	@Override
	public void perform() {
		company.getAllDrivers().forEach(inputOutput::display);
	}

}
