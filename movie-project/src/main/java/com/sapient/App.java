package com.sapient;

import com.sapient.views.HomeView;
import com.sapient.views.LoadDB;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        new LoadDB().addData();
//        MovieDao dao = new MovieDaoImpl();
        // MenuUtil.displayMoviesByList(dao.listMovies());
         new HomeView().firstView();
//        new AdminView().editMovie();
    }
}
