package telran.cars.controller.items;
import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;

public class GetModelProfitItem extends RentCompanyItem {


	public GetModelProfitItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Get profit by model";
	}

	@Override
	public void perform() {
		String model = inputOutput.getString("Please, enter model name.");
		Double profit = company.getModelProfit(model);
		System.out.println("Profit of model "+model+" is: "+ profit);
	}

}
