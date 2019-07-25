package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;

public class RemoveCarItem extends RentCompanyItem {

	public RemoveCarItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Remove car from company";
	}

	@Override
	public void perform() {
		String carNumber = inputOutput.getString("Please enter car number to remove");
		System.out.println(company.removeCar(carNumber));
	}

}
