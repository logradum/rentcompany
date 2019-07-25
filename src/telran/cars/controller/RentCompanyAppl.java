package telran.cars.controller;
import telran.cars.controller.items.Administrator;
import telran.cars.controller.items.Clerk;
import telran.cars.controller.items.RestoreCompany;
import telran.cars.controller.items.SaveCompany;
import telran.cars.controller.items.Statist;
import telran.cars.controller.items.Technician;
import telran.cars.controller.items.User;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.MenuWithExit;

public class RentCompanyAppl {

	public static void main(String[] args) {

		InputOutput inputOutput = new ConsoleInputOutput();
		
		
		Item[] items = {
				new Administrator(inputOutput),
				new User(inputOutput),
				new Clerk(inputOutput),
				new Technician(inputOutput),
				new Statist(inputOutput),
				new SaveCompany(inputOutput),
				new RestoreCompany(inputOutput),
		};
		Menu menu = new MenuWithExit(inputOutput, items);
		menu.runMenu();
	}
}
