package telran.cars.controller.items;

import java.util.List;

import telran.cars.dao.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostPopularModelNames extends RentCompanyItem {

	public DisplayMostPopularModelNames(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Display most popular model names";
	}

	@Override
	public void perform() {
		
		List<String> models = company.getMostPopularModelNames();
		models.stream().forEach(System.out::println);
		
	}

}
