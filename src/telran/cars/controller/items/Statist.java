package telran.cars.controller.items;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class Statist extends RentCompanyItem {

	public Statist(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Statist";
	}

	@Override
	public void perform() {
		Item[] items = {
				new GetModelProfitItem(inputOutput),
				new DisplayMostPopularModelNames(inputOutput),
				new DisplayMostProfitModelNames(inputOutput),
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}

}
