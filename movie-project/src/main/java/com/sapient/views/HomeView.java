package com.sapient.views;

import com.sapient.service.MovieService;
import com.sapient.util.InputUtil;
import com.sapient.util.MenuUtil;

public class HomeView {

	AdminView adminView;
	UserView userView;
	MovieService ms;

	public HomeView() {
		adminView = new AdminView();
		userView = new UserView();
		ms= new MovieService();
	}

	public void firstView() {

		System.out.println("Hello, Welcome to Flix-Me");
		System.out.println("Stream, Enjoy & Review         your Favourite Movies!!");
		int ch;
		boolean adminSet = false;
		while ((ch = mainMenu()) != 0) {

			switch (ch) {
			case 1:
				if(userView.login())
				{
					System.out.println("User Logged In Succefully!");
					userView.userDashboard();
					System.out.println("User Logged Out...");
				}
				else
					System.out.println("User Login Cancelled!");
				break;
			case 2:
				String name = userView.userRegister();
				System.out.println("Hello, " + name
						+ "! Welcome to FlixMe! Your Account has Been Created Succesfully! \nWe have addded $10 Balance to your account as Bonus"+
						"\nPlease login again to contiune enjoying Flixme!");
				break;
			case 3:
				InputUtil.clearSceen();
				System.out.println("Welcome to Admin Module-");
				if (!adminSet) {
					adminView.registerView(true);
					adminSet = true;
					InputUtil.clearSceen();
					System.out.println("Please login again as Admin  to continue...");
				} else if (adminView.login()) {
					adminView.adminDashboard();
					System.out.println("Logged Out Successfully!");
				}
				break;
			case 4:
				MenuUtil.displayMoviesByList(ms.getAllMovies());
				break;
			case 0:
				break;
			default:
				InputUtil.clearSceen();
				System.out.println("Invalid Choice.\nPlease try again");
			}
			
		}
		System.out.println("\n\nExiting App...\nThank You for using FlixME!\n\n");

	}

	public int mainMenu() {
		System.out.println("\nWelcome Page");
		System.out.println("1. Login as User");
		System.out.println("2. Register as User");
		System.out.println("3. Login as Admin");
		System.out.println("4. List All Movies in App");
		System.out.println("0. Exit the App");
		int ch = InputUtil.getIntegerInput("Enter Choice- ", "\n\nInvalid Input.\nPlease Try Again.\n");
		return ch;
	}

}
