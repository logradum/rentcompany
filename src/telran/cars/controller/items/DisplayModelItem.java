package telran.cars.controller.items;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.Model;
import telran.view.InputOutput;

public class DisplayModelItem extends RentCompanyItem {

	

	public DisplayModelItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Get model item";
	}

	@Override
	public void perform() {
		String modelStr = inputOutput.getString("Enter Model name");
		Model model = company.getModel(modelStr);
		inputOutput.display(model);
		System.out.println();
	}

}
