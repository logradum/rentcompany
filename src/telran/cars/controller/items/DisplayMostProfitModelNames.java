package telran.cars.controller.items;

import java.util.List;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostProfitModelNames extends RentCompanyItem {

	public DisplayMostProfitModelNames(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Display most profit model names";
	}

	@Override
	public void perform() {
		List<String> modelNames = company.getMostProfitModelNames();
		modelNames.stream().forEach(System.out::println);
	}
}
