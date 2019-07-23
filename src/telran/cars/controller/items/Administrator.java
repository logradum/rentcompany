package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Administrator extends RentCompanyItem{

	public Administrator(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		//Please, enter role (Administrator, Clerk, User, Statist, Technician)
		return "Administrator";
	}

	@Override
	public void perform() {
		Item[] items = {
				new AddModelItem(inputOutput, company),
				new AddCarItem(inputOutput, company),
				new RemoveCarItem(inputOutput, company),
				new ClearItem(inputOutput, company),
				new GetDriverItem(inputOutput, company)
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
