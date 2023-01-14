package View;

import Controller.TheaterController;
import Controller.MovieController;
import Model.MovieDTO;
import Model.TheaterDTO;
import Model.UserDTO;
import util.ScannerUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TheaterViewer {
    private final Scanner SCANNER;
    private final String DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private TheaterController theaterController;
    private MovieController movieController;
    private ScreenViewer screenViewer;
    private UserViewer userViewer;
    private UserDTO logIn;

    public TheaterViewer(Scanner scanner){
        SCANNER = scanner;
        theaterController = new TheaterController();
        movieController = new MovieController();
    }

    public void setUserViewer(UserViewer userViewer){
        this.userViewer = userViewer;
    }

    public void setScreenViewer(ScreenViewer screenViewer){
        this.screenViewer = screenViewer;
    }

    public void setLogIn(UserDTO logIn){
        this.logIn = logIn;
    }

    public void showIndex(){
        while (true) {
            if(logIn.getLevel()!=2) {
                String message = "1. 극장 목록 2. 종료";
                int userChoice = ScannerUtil.nextInt(SCANNER, message);
                if (userChoice == 1) {
                    printList();
                } else if (userChoice == 2) {
                    break;
                }
            } else if (logIn.getLevel()==2) {
                String message = "1. 극장 등록 2. 극장 목록 3. 종료";
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

        TheaterDTO b = new TheaterDTO();

        String message;

        message = "극장 이름을 입력해주세요.";
        b.setName(ScannerUtil.nextLine(SCANNER, message));

        message = "극장 위치를 입력해주세요.";
        b.setAddress(ScannerUtil.nextLine(SCANNER, message));

        message = "극장 전화번호를 입력해주세요.";
        b.setNumber(ScannerUtil.nextLine(SCANNER, message));

        theaterController.insert(b);
    }

    private void printList() {
        int userChoice = -1;

        if (theaterController.isEmpty()) {
            System.out.println("등록된 극장이 없습니다.");
        } else {
            ArrayList<TheaterDTO> boardList = theaterController.getList();
            for (TheaterDTO b : boardList) {
                System.out.printf("%d. %s\n", b.getId(), b.getName());
            }
            String message = "상세보기할 극장의 번호나 뒤로 가실려면 0을 입력해주세요.";
            userChoice = ScannerUtil.nextInt(SCANNER, message);

            TheaterDTO b = new TheaterDTO();
            b.setId(userChoice);

            while (userChoice != 0 && !boardList.contains(b)) {
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
        TheaterDTO temp = new TheaterDTO();
        temp.setId(id);

        ArrayList<TheaterDTO> theaterList = theaterController.getList();
        ArrayList<MovieDTO> movieList = movieController.getList();

        //BoardDTO b = ArrayUtil.get(array, ArrayUtil.indexOf(array, temp));
        TheaterDTO b = theaterList.get(theaterList.indexOf(temp));

        System.out.printf("극장 이름: %s\n",b.getName());
        System.out.printf("극장 위치: %s\n",b.getAddress());
        System.out.printf("극장 전화번호: %s\n",b.getNumber());
        System.out.println("==================================");

        screenViewer.setMovieList(movieList);
        screenViewer.setTheaterNumber(b);
        screenViewer.setLogIn(logIn);

        String message;
        int userChoice;

        if(logIn.getLevel()==2){
            message = "1. 극장 정보 수정 2. 극장 정보 삭제 3. 상영정보 보기 4. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 4);
        } else {
            message = "3. 상영정보 보기 4. 뒤로 가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 4);
        }

        if (userChoice == 1) {
            message = "수정할 극장 이름을 입력해주세요.";
            b.setName(ScannerUtil.nextLine(SCANNER, message));

            message = "수정할 극장 위치를 입력해주세요.";
            b.setAddress(ScannerUtil.nextLine(SCANNER, message));

            message = "수정할 극장 전화번호를 입력해주세요.";
            b.setName(ScannerUtil.nextLine(SCANNER, message));

            theaterController.update(b);

            printOne(id);
        } else if (userChoice == 2) {
            theaterController.delete(b.getId());
        } else if (userChoice == 3){
            screenViewer.printList();
        } else {
            printList();
        }
    }
}