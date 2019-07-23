package telran.cars.controller.items;
import telran.cars.dao.IRentCompany;
import telran.cars.domain.Car;
import telran.view.InputOutput;

public class AddCarItem extends RentCompanyItem {

	

	public AddCarItem(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Please add new car";
	}

	@Override
	public void perform() {
		String regNumber = inputOutput.getString("Please enter regNumber");
		String color = inputOutput.getString("Please enter color");
		String modelName = inputOutput.getString("Please enter ModelName from the list");
		Car car = new Car(regNumber,color,modelName);
		company.addCar(car);
		
	}

}
