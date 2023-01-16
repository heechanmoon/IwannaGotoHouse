package View;

import Controller.MovieController;
import Model.MovieDTO;
import Model.UserDTO;
import util.ScannerUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewer {
    private final Scanner SCANNER;
    private final String DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private MovieController movieController;
    private ScoreViewer scoreViewer;
    private TheaterViewer theaterViewer;
    private UserViewer userViewer;
    private UserDTO logIn;

    public MovieViewer(Scanner scanner){
        SCANNER = scanner;
        movieController = new MovieController();
    }

    public void setUserViewer(UserViewer userViewer){
        this.userViewer = userViewer;
    }
    public void setTheaterViewer(TheaterViewer theaterViewer) { this.theaterViewer = theaterViewer; }

    public void setScoreViewer(ScoreViewer scoreViewer){
        this.scoreViewer = scoreViewer;
    }

    public void setLogIn(UserDTO logIn){
        this.logIn = logIn;
    }

    public void showIndex(){
        while (true) {
            if(logIn.getLevel()!=2) {
                String message = "1. 영화 목록 2. 종료";
                int userChoice = ScannerUtil.nextInt(SCANNER, message);
                if (userChoice == 1) {
                    printList();
                } else if (userChoice == 2) {
                    break;
                }
            } else if (logIn.getLevel()==2) {
                String message = "1. 영화 등록 2. 영화 목록 3. 종료";
                int userChoice = ScannerUtil.nextInt(SCANNER, message);
                if (userChoice == 1) {
                    writeMovie();
                } else if (userChoice == 2) {
                    printList();
                } else if (userChoice == 3) {
                    break;
                }
            }
        }
    }

    private void writeMovie() {

        MovieDTO b = new MovieDTO();

        String message;

        message = "영화의 제목을 입력해주세요.";
        b.setTitle(ScannerUtil.nextLine(SCANNER, message));

        message = "영화의 스토리를 입력해주세요.";
        b.setStory(ScannerUtil.nextLine(SCANNER, message));

        message = "영화의 등급을 입력해주세요.(1: 전체관람가 2: 12세이상 3: 15세이상 4: 청소년관람불가)";
        b.setLevel(ScannerUtil.nextInt(SCANNER,message,1,4));

        movieController.insert(b);

        theaterViewer.setMovieList(movieController.getList());
    }

    private void printList() {
        int userChoice = -1;

        if (movieController.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
        } else {
            ArrayList<MovieDTO> movieList = movieController.getList();
            for (MovieDTO b : movieList) {
                System.out.printf("%d. %s\n", b.getId(), b.getTitle());
            }
            String message = "상세보기할 영화의 번호나 뒤로 가실려면 0을 입력해주세요.";
            userChoice = ScannerUtil.nextInt(SCANNER, message);

            MovieDTO b = new MovieDTO();
            b.setId(userChoice);

            while (userChoice != 0 && !movieList.contains(b)) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(SCANNER, message);
                b.setId(userChoice);
            }

            if (userChoice != 0) {
                printOne(userChoice);
            }
        }
    }

    private void printOne(int id) {
        MovieDTO temp = new MovieDTO();
        temp.setId(id);

        ArrayList<MovieDTO> movieList = movieController.getList();

        //BoardDTO b = ArrayUtil.get(array, ArrayUtil.indexOf(array, temp));
        MovieDTO b = movieList.get(movieList.indexOf(temp));

        System.out.printf("제목: %s\n",b.getTitle());
        if(b.getLevel()==1) {
            System.out.println("영화 등급: 전체관람가능");
        } else if (b.getLevel()==2) {
            System.out.println("영화 등급: 12세 이상");
        } else if (b.getLevel()==3) {
            System.out.println("영화 등급: 15세 이상");
        } else {
            System.out.println("영화 등급: 청소년 관람 불가");
        }
        System.out.printf("줄거리: %s\n",b.getStory());
        System.out.println("==================================");

        scoreViewer.setMovieNumber(b);
        scoreViewer.setLogIn(logIn);

        String message;
        int userChoice;

        if(logIn.getLevel()==2){
            message = "1. 영화 정보 수정 2. 영화 삭제 3. 평점 보기 4. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 4);
        } else {
            message = "3. 평점 보기 4. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 4);
        }

        if (userChoice == 1) {
            message = "수정할 제목을 입력해주세요.";
            b.setTitle(ScannerUtil.nextLine(SCANNER, message));

            message = "수정할 줄거리를 입력해주세요.";
            b.setStory(ScannerUtil.nextLine(SCANNER, message));

            movieController.update(b);
            theaterViewer.setMovieList(movieController.getList());
            printOne(id);
        } else if (userChoice == 2) {
            movieController.delete(b.getId());
            theaterViewer.setMovieList(movieController.getList());
        } else if (userChoice == 3){
            scoreViewer.printList(0);
            printOne(id);
        } else {
            printList();
        }
    }
}