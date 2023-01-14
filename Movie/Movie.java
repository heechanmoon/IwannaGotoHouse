package Movie;

import View.UserViewer;
import View.TheaterViewer;
import View.MovieViewer;
import View.ScoreViewer;
import View.ScreenViewer;
import java.util.Scanner;

public class Movie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserViewer userViewer = new UserViewer(scanner);
        TheaterViewer theaterViewer = new TheaterViewer(scanner);
        MovieViewer movieViewer = new MovieViewer(scanner);
        ScoreViewer scoreViewer = new ScoreViewer(scanner);
        ScreenViewer screenViewer = new ScreenViewer(scanner);

        userViewer.setMovieViewer(movieViewer);
        userViewer.setTheaterViewer(theaterViewer);
        movieViewer.setScoreViewer(scoreViewer);
        movieViewer.setUserViewer(userViewer);
        theaterViewer.setScreenViewer(screenViewer);
        theaterViewer.setUserViewer(userViewer);

        userViewer.showIndex();
    }
}