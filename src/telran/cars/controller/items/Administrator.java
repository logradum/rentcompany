package telran.cars.controller.items;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Administrator extends RentCompanyItem{

	public Administrator(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		//Please, enter role (Administrator, Clerk, User, Statist, Technician)
		return "Administrator";
	}

	@Override
	public void perform() {
		Item[] items = {
				new AddModelItem(inputOutput),
				new AddCarItem(inputOutput),
				new RemoveCarItem(inputOutput),
				new ClearItem(inputOutput),
				new GetDriverItem(inputOutput)
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
