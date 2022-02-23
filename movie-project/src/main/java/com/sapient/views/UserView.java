package com.sapient.views;

import java.util.List;

import com.sapient.entity.Movie;
import com.sapient.entity.Review;
import com.sapient.entity.User;
import com.sapient.service.MovieService;
import com.sapient.service.ReviewService;
import com.sapient.service.UserService;
import com.sapient.util.InputUtil;
import com.sapient.util.MenuUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserView {

	UserService us;
	User user;
	MovieService ms;
	ReviewService rs;

	public UserView() {
		us = new UserService();
		ms = new MovieService();
		rs = new ReviewService();
	}

	String userRegister() {
		while (true) {

			String username;
			while (true) {
				username = InputUtil.getStringInput("Enter Username-");
				if (us.getUserExists(username))
					System.out.println("Username already exists!\nPlease Try Again\n\n");
				else
					break;
			}

			String name = InputUtil.getStringInput("Enter Name-");
			String pass;
			while (true) {
				pass = InputUtil.getStringInput("Enter New Pasword-");
				String cnfPass = InputUtil.getStringInput("Confirm New Pasword-");
				if (pass.equals(cnfPass))
					break;
				else {
					System.out.println("Passwords do not match");
					System.out.println("Please Retry\n\n");
				}
			}
			String number;
			while (true) {
				number = InputUtil.getStringInput("Number");
				if (number.length() < 7 || number.length() > 10)
					System.out.println("Invalid Phone Number!\nPlease Try Again\n\n");
				else {
					boolean valid = true;
					for (int i = 0; i < number.length(); i++) {
						if (number.charAt(i) < '0' || number.charAt(i) > '9') {
							valid = false;
							System.out.println("Invalid Input!\nPlease Try Again\n\n");
							break;
						}
					}
					if (valid)
						break;
				}
			}

			User user=us.addUserService(name, number, username, pass, 10.0);
			return user.getName();

		}
	}

	public boolean login() {
		while (true) {
			String username = InputUtil.getStringInput("Enter Username-");
			String password = InputUtil.getStringInput("Enter Password-");
			user = us.getUserByUsername(username, password);
			if (user == null) {
				System.out.println("Invalid Username/Password");
				if (!MenuUtil.retry("Do you want to retry ?"))
					return false;
			} else
				return true;
		}

	}

	void userDashboard() {
		System.out.println("\n\nHello " + user.getName() + "!");
		InputUtil.clearSceen();
		while (true) {
			int ch = -1;
			while (ch != 0) {
				user = us.getUserService(user.getUserId());
				System.out.println("What would you like to do ?");
				System.out.println("1. Display All Movie(s)");
				System.out.println("2. Search Movie By Name");
				System.out.println("3. Review a Movie");
				System.out.println("4. View Reviews of a Movie");
				System.out.println("5. Edit Your Reviews");
				System.out.println("6. View Your Flixme Wallet Balance");
				System.out.println("7. Recharge You Flixme Wallet");
				System.out.println("8. Edit Your Details");
				System.out.println("9. Reset Your Password");
				System.out.println("0. Log Out");
				ch = InputUtil.getIntegerInput("Enter Choice-", "Invalid Input");
				switch (ch) {
				case 1:
					System.out.println("Displaying All Movies\n\n");
					displayAllMovies();
					break;
				case 2:
					searchByName();
					break;
				case 3:
					if (reviewMovie())
						System.out.println("Review Added Succefully");
					else
						System.out.println("Review Discarded");
					break;
				case 4:
					viewReviewsByMovie();
					break;
				case 5:
					if (editReview(true))
						System.out.println("Reviews Edited Succesfully\n");
					break;
				case 6:
					System.out.printf("Your Current Wallet Amount- %.2f\n", user.getBalance());
					break;
				case 7:
					System.out.printf("Your Updated Wallet Amount- %.2f\n", addBalance());
					System.out.println("Transaction Completed Succefully");
					break;
				case 8:
					if(editUserDetails())
						System.out.println("Details Updated Successfully");
					else
						System.out.println("Details Change Discarded");
					break;
				case 9:
					if(resetPassword())
						System.out.println("Password Reset Successfully");
					else
						System.out.println("Password Change Discarded");
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

	void displayAllMovies() {
		List<Movie> movies = ms.getAllMovies();
		MenuUtil.displayMoviesByList(movies);
		System.out.println("\n\n");
	}

	void searchByName() {
		while (true) {
			String search = InputUtil.getStringInput("Enter Movie Name To Search-");
			List<Movie> resMovies = ms.searchMovieByName(search);
			if (resMovies.size() == 0)
				System.out.println("Sorry, we don't have that! \nBut your taste in Movies is better than us.");
			else
				displayMoviesBySearch(resMovies);

			if (!MenuUtil.retry("\n\nDo you want to search another Movie?"))
				break;
		}
	}

	void displayMoviesBySearch(List<Movie> movies) {
		MenuUtil.displayMoviesByList(movies);
		System.out.println("\n\n");
	}

	boolean reviewMovie() {

		displayAllMovies();
		while (true) {
			int movieId;
			while (true) {
				try {

					movieId = InputUtil.getIntegerInput("Enter Movie Id", "Invalid Movie Id!");
					Movie movie = ms.getMovieService(movieId);
					if (movie == null)
						throw new NullPointerException();
					if (rs.checkReviewByUserExists(movieId, user.getUserId())) {
						if (!MenuUtil.confirm(
								"You have already reviewed this movie. Continuing will overwrite the previous review."))
							return false;
						return editReview(false);
					}
					String view = InputUtil.getStringInput("Enter Review-");
					Double rating;
					while (true) {
						System.out.println("Rating Must Be Between 1 & 10");
						rating = InputUtil.getDoubleInput("Enter Rating-");
						if (rating < 0 || rating > 10) {
							System.out.println("\nInvalid Rating\nPlease Try Again\n");
							continue;
						} else
							break;
					}
					rs.addReviewService(movieId, user.getUserId(), view, rating);
					return true;
				} catch (Exception e) {
					log.debug(e.getMessage());
					if (!MenuUtil.retry("Movie Not Found\n\nDo you want to try Again?"))
						return false;
				}

			}
		}
	}

	void viewReviewsByMovie() {
		displayAllMovies();
		while (true) {
			int movieId = -1;
			while (movieId == -1) {
				try {
					movieId = InputUtil.getIntegerInput("Enter Movie Id", "Invalid Movie Id!");
					Movie movie = ms.getMovieService(movieId);
					List<Review> reviews = movie.getReview();
					displayReviews(reviews, false);
					return;
				} catch (Exception e) {
					log.debug(e.getMessage());
					System.out.println("Invalid Input!\n");
				}
			}
		}

	}

	void displayReviews(List<Review> reviews, Boolean showMovieDetails) {
		double rating = 0;
		if (reviews.size() == 0) {
			System.out.println("Sorry, No Reviews Found!");
			return;
		}
		System.out.println("-".repeat(80));
		for (Review rvw : reviews) {
			System.out.println(
					"Review Id- " + rvw.getReviewId() + " By- " + us.getUserService(rvw.getUserId()).getUsername());
			if (showMovieDetails) {
				System.out.println("Movie Id- " + rvw.getMovieId() + "\tMovie Name-"
						+ ms.getMovieService(rvw.getMovieId()).getTitle());
			}
			System.out.println("Review- " + rvw.getReview());
			System.out.println("Rating- " + rvw.getRating());
			rating += rvw.getRating();
			System.out.println("-".repeat(80));
		}
		double avg = rating / reviews.size();
		System.out.printf("Total Average Rating- %.2f\n\n", avg);
	}

	boolean editReview(boolean display) {
		List<Review> reviews = user.getReview();
		if (display)
			displayReviews(reviews, display);
		Review rvw;
		while (true) {
			try {
				int reviewId = InputUtil.getIntegerInput("Enter Review Id- ", "Invalid Input");
				rvw = rs.getReviewService(reviewId);
				if (rvw == null)
					throw new NullPointerException();
				else
					break;
			} catch (Exception e) {
				log.debug(e.getMessage());
				System.out.println("Invalid Review Id");
				if (!MenuUtil.retry("Do you want to try again ?"))
					return false;

			}
		}

		System.out.println("Review-\n" + rvw.getReview());
		String view = InputUtil.getStringInput("Press Enter to keep review unchanged.\n New Review-");
		if (view.length() != 0)
			rvw.setReview(view);
		Double rtng;
		while (true) {
			System.out.println("Rating-\n" + rvw.getRating());
			String rating = InputUtil.getStringInput("Press Enter to keep rating unchanged.\n New Rating-");
			if (rating.length() != 0) {
				try {
					rtng = Double.parseDouble(rating);
					rvw.setRating(rtng);
				} catch (Exception e) {
					log.debug(e.getMessage());
					System.out.println("\n\nInvalid Input\nPlease Retry\n");
					continue;
				}
			}
			rs.updateReviewService(rvw);
			return true;
		}

	}

	Double addBalance() {
		while (true) {
			try {
				Double amt = InputUtil
						.getDoubleInput("Add Money to Your FlixMe Wallet \nEnter the amount you want to add-");
				user.setBalance(user.getBalance() + amt);
				us.updateUserService(user);
				return user.getBalance();
			} catch (Exception e) {
				log.debug(e.getMessage());
				System.out.println("Invalid Input");
			}

		}
	}

	boolean editUserDetails() {

			System.out.println("\n\nEdit Your Details-\n");
			System.out.println("\n\nPress Enter To Keep Details Unchanged-\n");

			
			String name = InputUtil.getStringInput(user.getName()+"\nEnter Name-");
			if(name.length()!=0)
				user.setName(name);
			String number;
			while (true) {
				number = InputUtil.getStringInput(user.getNumber()+"\nNumber");
				if(number.length()==0)
					break;
				if (number.length() < 7 || number.length() > 10)
					System.out.println("Invalid Phone Number!\nPlease Try Again\n\n");
				else {
					boolean valid = true;
					for (int i = 0; i < number.length(); i++) {
						if (number.charAt(i) <= '0' || number.charAt(i) >= 9) {
							valid = false;
							System.out.println("Invalid Input!\nPlease Try Again\n\n");
						}
					}
					if (valid)
						break;
				}
			}
			user.setNumber(number);

			if(MenuUtil.confirm("Are you sure you want to save changes ?"))
			{
				us.updateUserService(user);
				return true;
			}
			return false;

		}
	
	
	boolean resetPassword()
	{
		while (true) {
			String pass = InputUtil.getStringInput("Enter New Pasword-");
			String cnfPass = InputUtil.getStringInput("Confirm New Pasword-");
			if (pass.equals(cnfPass)) {
				user.setPassword(pass);
				us.updateUserService(user);
				return true;
			}
			else {
				if(!MenuUtil.retry("Passwords do not match."))
					return false;
			}
		}
	}

}
