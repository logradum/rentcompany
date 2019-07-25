package telran.cars.controller.items;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Clerk extends RentCompanyItem {

	public Clerk(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Clerk";
	}

	@Override
	public void perform() {
		Item[] items = {
				new AddDriverItem(inputOutput),
				new RentCarItem(inputOutput),
				new ReturnCarItem(inputOutput),
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
