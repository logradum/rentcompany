package telran.cars.controller.items;

import telran.view.InputOutput;

public class DisplayCarItem extends RentCompanyItem {

	public DisplayCarItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Display car";
	}

	@Override
	public void perform() {
		String regNumber = inputOutput.getString("Please, enter registration number");
		System.out.println(company.getCar(regNumber));

	}

}
