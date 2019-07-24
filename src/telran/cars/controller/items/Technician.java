package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Technician extends RentCompanyItem {

	public Technician(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Techician";
	}

	public void perform() {
		Item[] items = {
				new DisplayCarsItem(inputOutput, company),
				new DisplayAllDrivers(inputOutput, company),
				new DisplayAllRecords(inputOutput, company)
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
