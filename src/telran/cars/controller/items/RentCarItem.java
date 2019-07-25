package telran.cars.controller.items;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import telran.cars.dao.IRentCompany;
import telran.cars.dto.CarsReturnCode;
import telran.view.InputOutput;

public class RentCarItem extends RentCompanyItem {

	public RentCarItem(InputOutput inputOutput) {
		super(inputOutput);
	}

	@Override
	public String displayedName() {
		return "Rent a car";
	}

	@Override
	public void perform() {
		Predicate<String> p = Pattern.compile("[0-9]{5,10}").asPredicate();
		String idString = inputOutput.getString("Please enter license ID", p);
		Long licenseId = Long.parseLong(idString);
		String regNumber = inputOutput.getString("Please enter registration number");
		LocalDate rentDate = inputOutput.getDate("Please enter a rental start date");
		int rentDays= inputOutput.getInteger("Please enter a number of rental days");
		CarsReturnCode res = company.rentCar(licenseId, regNumber, rentDate, rentDays);
		System.out.println(res);
	}
}
