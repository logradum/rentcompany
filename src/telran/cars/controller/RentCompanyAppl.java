package telran.cars.controller;
import telran.cars.controller.items.Administrator;
import telran.cars.controller.items.Clerk;
import telran.cars.controller.items.Statist;
import telran.cars.controller.items.Technician;
import telran.cars.controller.items.User;
import telran.cars.dao.IRentCompany;
import telran.cars.dao.RentCompany;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class RentCompanyAppl {

	public static void main(String[] args) {

		InputOutput inputOutput = new ConsoleInputOutput();
		IRentCompany persons = RentCompany.restoreFromFile(null);
		Item[] items = {
				new Administrator(inputOutput, persons),
				new User(inputOutput, persons),
				new Clerk(inputOutput, persons),
				new Technician(inputOutput, persons),
				new Statist(inputOutput, persons)
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}
}
