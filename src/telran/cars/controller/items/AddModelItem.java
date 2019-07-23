package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.Model;
import telran.view.InputOutput;

public class AddModelItem extends RentCompanyItem {

	
	public AddModelItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Please, add new Model";
	}

	@Override
	public void perform() {
		String modelName = inputOutput.getString("Enter Model name");
		int gasTank = inputOutput.getInteger("Please enter gas tank volume", 10, 100);
		String modelCompany = inputOutput.getString("Enter Company Name");
		String country = inputOutput.getString("Enter factory country name");
		int priceDay = inputOutput.getInteger("Please enter price per day");
		Model model = new Model(modelName,gasTank,modelCompany,country,priceDay);
		company.addModel(model);
	}

}
