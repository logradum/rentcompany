package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.cars.dao.RentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public abstract class RentCompanyItem extends Item{
	protected static IRentCompany company = RentCompany.restoreFromFile(null); // will do static and work only with one object
	//static field one for all menu items
	//should fix all menu items constructors
	
	public RentCompanyItem(InputOutput inputOutput) {
		super(inputOutput);
	}
}
