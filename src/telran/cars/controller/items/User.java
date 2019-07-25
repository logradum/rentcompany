package telran.cars.controller.items;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class User extends RentCompanyItem{

	public User(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "User";
	}

	@Override
	public void perform() {
		Item[] items = {
				new DisplayModelItem(inputOutput),
				new DisplayDriverCarsItem(inputOutput),
				new DisplayCarsByDriverItem(inputOutput),
				new DisplayCarItem(inputOutput)
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
		
	}

}
