package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class User extends RentCompanyItem{

	public User(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		//
		return "User";
	}

	@Override
	public void perform() {
		Item[] items = {
				new DisplayModelItem(inputOutput, company),
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
		
	}

}
