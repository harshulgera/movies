package com.sapient.util;

import java.util.List;

import com.sapient.entity.Genre;
import com.sapient.entity.Movie;

public class MenuUtil {

    public static boolean retry(String msg) {
        while (true) {
            String cont = InputUtil.getStringInput(msg + "\nEnter y if yes or enter n for no- ");
            if (cont.trim().equalsIgnoreCase("n"))
                return false;
            else if (cont.trim().equalsIgnoreCase("y"))
                return true;
            else
                System.out.println("Invalid Input!\n");
        }

    }

    public static boolean confirm(String msg) {
        while (true) {
            String cont = InputUtil.getStringInput(msg + "\nEnter y if yes or enter n for no- ");
            if (cont.trim().equalsIgnoreCase("n"))
                return false;
            else if (cont.trim().equalsIgnoreCase("y"))
                return true;
            else
                System.out.println("Invalid Input!\n");
        }
    }

    public static Genre setGenre(int num) {
        switch (num) {
            case 1:
                return Genre.Action;
            case 2:
                return Genre.Comedy;
            case 3:
                return Genre.Drama;
            case 4:
                return Genre.Horror;
            case 5:
                return Genre.Thriller;
            default:
                return Genre.Action;
        }
    }

    public static int getGenre() {
        while (true) {
            System.out.println("Choose the genre of Movie-");
            int i = 1;
            for (Genre genre : Genre.values()) {
                System.out.println(i++ + ". " + genre);
            }
            int genreNum = InputUtil.getIntegerInput("Enter Choice From 1 to 5", "Invalid Input");
            if (genreNum > 0 && genreNum < 5)
                return genreNum;
            else
                System.out.println("Invalid Choice\nPlease");
        }
    }

    public static void displayMoviesByList(List<Movie> movies) {
        if (movies.size() == 0) {
            System.out.println("\nNo Movies Found!\n");
            return;
        }
        InputUtil.clearSceen();
        System.out.printf("%-20s %20s %20s %20s %50s\n", "Movie Id", "Movie Title", "Movie Year", "Genre","Cast");
        System.out.println(
                "-".repeat(140));
        for (Movie movie : movies) {
            String allCast="";
            for(String cast: movie.getCast())
            	 allCast+=cast+", ";
            allCast=allCast.substring(0,allCast.length()-2);
            System.out.printf("%-20s %20s %20s %20s %50s\n",
            		movie.getMovieId(), movie.getTitle(), movie.getYear(), movie.getGenre(),allCast);
            System.out.println(
                    "-".repeat(140));

        }
    }
    
    

}
