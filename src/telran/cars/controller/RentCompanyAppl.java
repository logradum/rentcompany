package telran.cars.controller;
import telran.cars.controller.items.Administrator;
import telran.cars.controller.items.Clerk;
import telran.cars.controller.items.RestoreCompany;
import telran.cars.controller.items.SaveCompany;
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
		IRentCompany company = RentCompany.restoreFromFile(null);
		Item[] items = {
				new Administrator(inputOutput, company),
				new User(inputOutput, company),
				new Clerk(inputOutput, company),
				new Technician(inputOutput, company),
				new Statist(inputOutput, company),
				new SaveCompany(inputOutput, company),
				new RestoreCompany(inputOutput, company),
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}
}
