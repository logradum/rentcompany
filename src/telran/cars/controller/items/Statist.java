package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Statist extends RentCompanyItem {

	public Statist(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Statist";
	}

	@Override
	public void perform() {
		Item[] items = {
				new GetModelProfitItem(inputOutput, company),
				new DisplayMostPopularModelNames(inputOutput, company),
				new DisplayMostProfitModelNames(inputOutput, company),
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
