package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Clerk extends RentCompanyItem {

	public Clerk(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Clerk";
	}

	@Override
	public void perform() {
		Item[] items = {
				new AddDriverItem(inputOutput, company),
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
