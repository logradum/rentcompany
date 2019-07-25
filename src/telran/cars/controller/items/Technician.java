package telran.cars.controller.items;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Technician extends RentCompanyItem {

	public Technician(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Techician";
	}

	public void perform() {
		Item[] items = {
				new DisplayCarsItem(inputOutput),
				new DisplayAllDrivers(inputOutput),
				new DisplayAllRecords(inputOutput)
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
