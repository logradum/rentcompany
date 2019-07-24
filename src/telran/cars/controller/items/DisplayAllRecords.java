package telran.cars.controller.items;

import java.util.stream.Stream;

import telran.cars.dao.IRentCompany;
import telran.cars.domain.RentRecord;
import telran.view.InputOutput;

public class DisplayAllRecords extends RentCompanyItem {

	public DisplayAllRecords(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display all company records";
	}

	@Override
	public void perform() {
		
		Stream<RentRecord> rentRecords = company.getAllRecords();
		boolean isEmpty = !(rentRecords.findFirst().isPresent());
		if (isEmpty) {
			System.out.println("Sorry, there is no records");
		} else {
			rentRecords.forEach(System.out::println);
		}
		
	}

}
