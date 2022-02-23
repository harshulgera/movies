package com.sapient.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.sapient.entity.Genre;
import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.service.MovieService;
import com.sapient.service.ReviewService;
import com.sapient.service.UserService;
import com.sapient.util.InputUtil;
import com.sapient.util.MenuUtil;

public class AdminView {

	MovieService movieService;
	ReviewService reviewService;

	public AdminView() {
		movieService = new MovieService();
		reviewService= new ReviewService();
	}

	static String username = "admin";
	static String adminPass = "admin";

	void registerView(Boolean firstLogin) {
        
		if(firstLogin)
		{
			System.out.println("Default Username & Password is admin (Case-Sensitive)");
			while (true) {
				String uname = InputUtil.getStringInput("Enter Username-");
				String pass = InputUtil.getStringInput("Enter Password-");

				if (uname.equals(username) && pass.equals(adminPass))
					break;
				else
					System.out.println("Invalid Username or Password. Please try again.");

			}
		}
		

		while (true) {
			String pass = InputUtil.getStringInput("Enter New Password-");
			String cnfPass = InputUtil.getStringInput("Confirm New Password-");
			if (pass.equals(cnfPass)) {
				adminPass = cnfPass;
				System.out.println("Password Reset Successfully");
				break;
			} else {
				System.out.println("Passwords do not match");
				System.out.println("Please Retry\n\n");
			}
		}
	}

	boolean login() {
		while (true) {
			String uname = InputUtil.getStringInput("Enter Username-");
			String pass = InputUtil.getStringInput("Enter Password-");
			if (uname.equals(username) && pass.equals(adminPass))
				return true;
			else
				System.out.println("Ivalid username/password.");

			if (!MenuUtil.retry("Do you want to retry ?"))
				return false;

		}

	}

	void adminDashboard() {
		System.out.println("\n\nHello Admin");
		while (true) {
			int ch = -1;
			while (ch != 0) {
				System.out.println("\n\nWhat would you like to do ?");
				System.out.println("1. Add Movie");
				System.out.println("2. Edit Movie(s)");
				System.out.println("3. Display All Movie(s)");
				System.out.println("4. View Review(s) By Movie");
				System.out.println("5. Edit Reviews(s) By Movie");
				System.out.println("6. Reset Password");
				System.out.println("0. Log Out");
				System.out.println("Enter Choice -");
				ch = InputUtil.getIntegerInput("", "Invalid Input");
				switch (ch) {
				case 1:
					if (addMovieMenu())
						System.out.println("Movie Added Succefully\n");
					break;
				case 2:
					if(editMovie())
					 System.out.println("Movie Edited Succefully\n");
					break;
				case 3:
					displayAllMovies();
					break;
				case 4:
					viewReviewsByMovie(true);
					break;
				case 5:
					if(editReviewByMovie())
						System.out.println("Reviews Edited Succesfully\n");
					break;
				case 6:
					registerView(false);
					break;
				case 0:
					System.out.println("Logging Out...");
					return;
				default:
					InputUtil.clearSceen();
					System.out.println("\n\nInvalid Choice.\nPlease Try Again.\n");
					break;
				}

			}

		}
	}

	boolean addMovieMenu() {

		if (!MenuUtil.retry("Do you want to continue ?"))
			return false;
		InputUtil.clearSceen();
		System.out.println("Enter Movie Details-");
		String title = InputUtil.getStringInput("Enter Movie Name-");
		int year = InputUtil.getIntegerInput("Enter Movie Year", "Invalid Year");
		String str = "";
		List<String> cast = new ArrayList<String>();
		int genreNum = MenuUtil.getGenre();
		Genre genre = MenuUtil.setGenre(genreNum);
		while (!str.equals("exit")) {
			str = InputUtil.getStringInput("Enter Cast name-");
			if (str.length() == 0)
				break;
			else
				cast.add(str);
			System.out.println("Press Enter To Exit");
		}
		boolean conf = MenuUtil.confirm("Are you sure you want to add this movie ?");
		if (!conf)
			return false;
		movieService.addMovieService(title, year, genre, cast);
		return true;

	}

	public boolean editMovie() {

		if (!MenuUtil.retry("Do you want to continue ?")) {
			System.out.println("Edit Cancelled");
			return false;
		}

		List<Movie> movies = movieService.getAllMovies();
		MenuUtil.displayMoviesByList(movies);

		Set<Integer> movieSet = movies.stream().map(m -> m.getMovieId()).collect(Collectors.toCollection(HashSet::new));

		while (true) {
			int movieId = InputUtil.getIntegerInput("Enter Movie Id to Edit- ", "Invalid Input");
			if (!movieSet.contains(movieId)) {
				boolean retry = MenuUtil.retry("Do you want to Retry ?");
				if (!retry)
					return false;
				movieId = InputUtil.getIntegerInput("Enter Movie Id to Edit- ", "Invalid Input");
			}
			Movie movie = movieService.getMovieService(movieId);
			System.out.println("Press Enter if you dont want to Edit-");
			System.out.println();
			String title = InputUtil.getStringInput("Movie Name-" + movie.getTitle() + "\n");
			if (title.length() == 0)
				movie.setTitle(movie.getTitle());
			else
				movie.setTitle(title);
			while (true) {
				String year = InputUtil.getStringInput("Movie Year- " + movie.getYear() + "\n");
				if (year.length() == 0) {
					movie.setYear(movie.getYear());
					break;
				} else {
					try {
						movie.setYear(Integer.parseInt(year));
						break;
					} catch (NumberFormatException ex) {
						System.out.println("\nInvalid Input\nPlease Try Again\n\n");
					}
				}
			}
			while (true) {
				System.out.println("Choose the genre of Movie-");
				int i = 1;
				for (Genre gen : Genre.values()) {
					System.out.println(i++ + ". " + gen);
				}
				System.out.println("Enter Choice From 1 to 5. Enter to Continue");
				String genre = InputUtil.getStringInput("Movie Genre- " + movie.getGenre() + "\n");

				if (genre.length() == 0) {
					movie.setGenre(movie.getGenre());
					break;
				} else {
					try {
						int genreNum = Integer.parseInt(genre);
						if (genreNum < 1 || genreNum > 5) {
							System.out.println("\nInvalid Choice.\nPlease Try Again\n");
							continue;
						}
						movie.setGenre(MenuUtil.setGenre(genreNum));
						break;
					} catch (NumberFormatException ex) {
						System.out.println("\nInvalid Genre\nPlease Try Again\n\n");
					}
				}
			}

			List<String> cast = movie.getCast();
			System.out.println("\n\nEdit Cast-");
			System.out.println("Type del to Delete the Cast or 0 to continue to Add New Cast");
			List<String> newCast = new ArrayList<>();
			for (String cst : cast) {
				String inp = InputUtil.getStringInput("Cast Name -" + cst + "\n");
				if (inp.equals("0"))
					break;
				else if (inp.equalsIgnoreCase("del")) {
					continue;
				} else if (inp.length() != 0)
					newCast.add(inp);
				else
					newCast.add(cst);
			}
			cast = newCast;
			System.out.println("\n\nAdd New Cast.");
			String str = "dummy";
			while (str.length() != 0) {
				str = InputUtil.getStringInput("Enter Cast name-");
				if (str.length() != 0)
					cast.add(str);
				System.out.println("Press Enter To Exit");

			}
			
			if(MenuUtil.confirm("Are you sure you want to save changes ?"))
			{
				movieService.updateMovieService(movie);
				return true;
			}
			else
				return false;
					

		}

	}
	
	Set<Integer> displayAllReviews(List<Review> reviews)
	{
		if(reviews.size()==0) {
			System.out.println("No Reviews To Display");
			return null;
		}
		Set<Integer> set= new HashSet<Integer>();
		UserService us= new UserService();
		System.out.println("-".repeat(200));
		for(Review rvs: reviews)
		{
			set.add(rvs.getReviewId());
			System.out.println("Review Id- "+rvs.getReviewId() + "\tAuthor Name- "+us.getUserService(rvs.getUserId()).getName());
			System.out.println("Review- "+rvs.getReview());
			System.out.println("Rating- "+rvs.getRating()+"\n");
			System.out.println("-".repeat(200));
		}
		return set;
		
	}
	
	
	Movie viewReviewsByMovie(Boolean show)
	{
		List<Movie> movies = movieService.getAllMovies();
		MenuUtil.displayMoviesByList(movies);
		int movieId;
		Set<Integer> movieSet = movies.stream().map(m -> m.getMovieId()).collect(Collectors.toCollection(HashSet::new));
		while (true) {
			movieId = InputUtil.getIntegerInput("Enter Movie Id to Display Reviews- ", "Invalid Input");
			if (!movieSet.contains(movieId)) {
				boolean retry = MenuUtil.retry("Invalid Movie Id.\nDo you want to Retry ?");
				if (!retry)
					return null;
				
			}
			else
				break;
		}
			
			Movie movie = movieService.getMovieService(movieId);
			
			if(show)
				displayAllReviews(movie.getReview());
			
			return movie;
		
	}
	
	boolean editReviewByMovie()
	{
		Movie movie= viewReviewsByMovie(false);
		if(movie==null) return false;
		List<Review> reviews=movie.getReview();
		
		if(reviews.size()!=0)
		{
			System.out.println("\n\nAll Reviews For "+ movie.getTitle());
			Set<Integer> reviewIds=displayAllReviews(reviews);
			System.out.println("\n\n Enter Review Ids To Delete && Enter to Exit");
			String str="dummy";
			List<Integer> reviewsToDelete = new ArrayList<Integer>();
			while(str.length()!=0)
			{
				str=InputUtil.getStringInput("Review Id-");
				if(str.length()==0)
					break;
				else
				{
					try
					{
						int n=Integer.parseInt(str);
						if(reviewIds.contains(n))
							reviewsToDelete.add(n);
						else
							System.out.println("Invalid Id");
					}
					catch(Exception e)
					{
						System.out.println("Invalid Input");
					}
				}
			}
			
			for(int reviewId: reviewsToDelete)
				reviewService.deleteReviewService(reviewId);
		}
		else {
			System.out.println("No Reviews To Display");
			return false;
		}
		
		return true;
	}
	
	void displayAllMovies()
	{
		List<Movie> movies = movieService.getAllMovies();
		MenuUtil.displayMoviesByList(movies);
		System.out.println("\n\n");
	}

}
