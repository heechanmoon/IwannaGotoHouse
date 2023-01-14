package View;

import Controller.ScreenController;
import Model.MovieDTO;
import Model.ScreenDTO;
import Model.TheaterDTO;
import Model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ScreenViewer {
    private final Scanner SCANNER;
    private ScreenController screenController;
    private UserDTO logIn;
    private TheaterDTO theaterNumber;
    private ArrayList<MovieDTO> movieList;

    public ScreenViewer(Scanner scanner){
        SCANNER = scanner;
        screenController = new ScreenController();
    }

    public void setLogIn(UserDTO logIn){
        this.logIn = logIn;
    }
    public void setMovieList(ArrayList<MovieDTO> movieList){this.movieList = movieList; }
    public void setTheaterNumber(TheaterDTO theaterNumber){
        this.theaterNumber = theaterNumber;
    }

    private void writeScreen() {
        ScreenDTO b = new ScreenDTO();

        b.setTheaterId(theaterNumber.getId());

        String message;
        for(MovieDTO d : movieList){
            System.out.printf("%d. %s\n", d.getId(), d.getTitle());
        }
        message = "상영정보를 입력해주세요.\n 영화 : ";
        b.setMovieId(ScannerUtil.nextInt(SCANNER, message));

        message = "상영 시간 : ";
        b.setScreenTime(ScannerUtil.nextLine(SCANNER, message));

        screenController.insert(b);
        printList();
    }

    public void printList() {
        int userChoice;
        ScreenDTO b;

        if (screenController.isEmpty(theaterNumber.getId())) {
            System.out.println("상영정보가 존재하지 않습니다.");
            if(logIn.getLevel()==2) {
                String message = "1.상영정보 추가";
                userChoice = ScannerUtil.nextInt(SCANNER, message);
                if (userChoice == 1) {
                    writeScreen();
                }
            }
        } else {
            screenList();
            if(logIn.getLevel()==2) {
                String message = "1.상영정보 추가  2.상영정보 삭제  3.상영정보 수정";
                userChoice = ScannerUtil.nextInt(SCANNER, message);
                if (userChoice == 1) {
                    writeScreen();
                } else if (userChoice == 2) {
                    message = "삭제할 상영정보의 번호를 입력해주세요.";
                    userChoice = ScannerUtil.nextInt(SCANNER, message);
                    b = screenController.selectById(theaterNumber.getId(), userChoice);
                    if (b != null) {
                        screenController.delete(b.getId());
                    } else {
                        System.out.println("존재하지 않는 상영정보입니다.");
                    }
                } else if (userChoice == 3) {
                    message = "수정할 상영정보의 번호를 입력해주세요.";
                    userChoice = ScannerUtil.nextInt(SCANNER, message);
                    b = screenController.selectById(theaterNumber.getId(), userChoice);
                    if (b != null) {
                        message = "수정할 상영정보를 입력해주세요.\n 영화 : ";
                        b.setMovieId(ScannerUtil.nextInt(SCANNER, message));

                        message = "상영 시간 : ";
                        b.setScreenTime(ScannerUtil.nextLine(SCANNER, message));
                        screenController.update(b.getId(), b);
                    } else {
                        System.out.println("수정할 권한이 없습니다.");
                    }
                }
            }
        }
    }

    public void screenList(){
        String movieName = "";
        System.out.println("평점 목록");
        ArrayList<ScreenDTO> screenList = screenController.getList();

        for (ScreenDTO d : screenList) {
            if(d.getTheaterId()== theaterNumber.getId()) {
                for(MovieDTO m : movieList){
                    if(m.getId()==d.getMovieId()){
                        movieName = m.getTitle();
                    }
                }
                System.out.printf("(%d)%s: - %s\n", d.getScreenNumber(), movieName , d.getScreenTime());
            }
        }

    }

    /*
    private void printOne(int id) {
        CommentDTO temp = new CommentDTO();
        temp.setNumber(id);

        ArrayList<CommentDTO> commentList = commentController.getList();

        //BoardDTO b = ArrayUtil.get(array, ArrayUtil.indexOf(array, temp));
        CommentDTO b = commentList.get(commentList.indexOf(temp));

        System.out.println("게시글 번호: "+b.getNumber()+"번 게시자 이름: "+b.getNickname());
        System.out.printf("제목: %s\n",b.getTitle());
        System.out.printf("내용: %s\n",b.getWrite());
        System.out.println("==================================");

        String message;
        int userChoice;

        if(b.getWriterId() == logIn.getId()){
            message = "1. 수정 2. 삭제 3. 뒤로가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 1, 3);
        } else {
            message = "3. 뒤로가기";
            userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 3);
        }

        if (userChoice == 1) {
            message = "새로운 제목을 입력해주세요.";
            b.setTitle(ScannerUtil.nextLine(SCANNER, message));

            message = "새로운 내용을 입력해주세요.";
            b.setWrite(ScannerUtil.nextLine(SCANNER, message));

            boardController.update(b);

            printOne(id);
        } else if (userChoice == 2) {
            boardController.delete(b.getNumber());
        } else {
            printList();
        }
    }
     */
}