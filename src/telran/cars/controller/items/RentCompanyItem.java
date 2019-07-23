package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public abstract class RentCompanyItem extends Item{
	IRentCompany company;

	public RentCompanyItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput);
		this.company = company;
	}
}
