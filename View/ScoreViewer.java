package View;

import Controller.ScoreController;
import Model.MovieDTO;
import Model.ScoreDTO;
import Model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreViewer {
    private final Scanner SCANNER;
    private ScoreController scoreController;
    private UserViewer userViewer;
    private UserDTO logIn;
    private MovieDTO movieNumber;

    public ScoreViewer(Scanner scanner){
        SCANNER = scanner;
        scoreController = new ScoreController();
    }

    public void setLogIn(UserDTO logIn){
        this.logIn = logIn;
    }

    public void setMovieNumber(MovieDTO movieNumber){
        this.movieNumber = movieNumber;
    }

    public void setUserViewer(UserViewer userViewer){ this.userViewer = userViewer; }

    private void writeComment() {
        ScoreDTO b = new ScoreDTO();
        boolean scoreDuplicate = false;

        ArrayList<ScoreDTO> scoreList = scoreController.getList();

        for(ScoreDTO s : scoreList){
            if(s.getUserId()==logIn.getId() && movieNumber.getId()==s.getMovieId()){
                scoreDuplicate = true;
            }
        }

        if(!scoreDuplicate) {

            b.setMovieId(movieNumber.getId());
            b.setUserId(logIn.getId());
            b.setNickName(logIn.getNickname());

            String message;

            message = "평점을 입력해주세요.";
            b.setScore(ScannerUtil.nextInt(SCANNER, message, 1, 10));

            if (logIn.getLevel() == 1) {
                message = "평론을 입력해주세요.";
                b.setReview(ScannerUtil.nextLine(SCANNER, message));
            }

            scoreController.insert(b);
        }else{
            System.out.println("이미 평점을 작성하셨습니다.");
        }
    }

    public void printList() {
        int userChoice;

        if (scoreController.isEmpty(movieNumber.getId())) {
            System.out.println("평점이 존재하지 않습니다.");
            String message = "1.평점 추가\n 뒤로 가려면 아무 키나 누르십시오.";
            userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1 && logIn.getLevel()!=2) {
                writeComment();
                selectMenu();
            }else if(userChoice == 1 && logIn.getLevel()==2){
                System.out.println("관리자는 평점을 추가하거나 수정할수 없습니다.");
                printList();
            }
        }else {
            selectMenu();
        }
    }

    public void selectMenu(){
        String message;
        int menuChoice = 1;
        boolean isEmpty = false;
        ArrayList<ScoreDTO> scoreList = scoreController.getList();

        while(menuChoice<=6 && menuChoice >=1 && !isEmpty) {
            message = "1.전체 평점  2.일반 관람객  3.전문 평론가  4.평점 추가  5.평점 삭제  6.평점 수정\n 뒤로 가려면 아무 숫자나 입력하십시오.";
            menuChoice = ScannerUtil.nextInt(SCANNER, message);

            if (menuChoice >= 1 && menuChoice <= 3) {
                scoreList(menuChoice);
            } else if (menuChoice >= 4 && menuChoice <= 6) {
                scoreMenu(menuChoice);
            }

            isEmpty = true;
            for(ScoreDTO d : scoreList){
                if(d.getMovieId()==movieNumber.getId()){
                    isEmpty = false;
                }
            }
        }
    }

    public void scoreMenu(int userChoice){
        ScoreDTO b;

        String message;
        if (userChoice == 4) {
            if (logIn.getLevel() != 2) {
                writeComment();
            } else {
                System.out.println("관리자는 평점을 추가할 수 없습니다.");
            }
        } else if (userChoice == 5) {
            if(logIn.getLevel() !=2) {
                scoreList(1);
                message = "삭제할 평점의 번호를 입력해주세요.";
                userChoice = ScannerUtil.nextInt(SCANNER, message);
                b = scoreController.selectById(movieNumber.getId(), userChoice);
                if (b != null && b.getUserId() == logIn.getId()) {
                    scoreController.delete(b.getId());
                } else {
                    System.out.println("이미 존재하지 않는 평점의 번호이거나 삭제할 권한이 없습니다.");
                }
            }else{
                System.out.println("관리자는 평점에 대한 수정 및 삭제 권한이 없습니다.");
            }
        } else if (userChoice == 6) {
            if(logIn.getLevel()!=2) {
                scoreList(1);
                message = "수정할 평점의 번호를 입력해주세요.";
                userChoice = ScannerUtil.nextInt(SCANNER, message);
                b = scoreController.selectById(movieNumber.getId(), userChoice);
                if (b != null && b.getUserId() == logIn.getId()) {
                    message = "수정할 평점을 입력해주세요.";
                    b.setScore(ScannerUtil.nextInt(SCANNER, message, 1, 10));

                    if (logIn.getLevel() == 1) {
                        message = "수정할 평론을 입력해주세요.";
                        b.setReview(ScannerUtil.nextLine(SCANNER, message));
                    }
                    scoreController.update(b.getId(), b);
                } else {
                    System.out.println("존재하지 않는 평점의 번호이거나 수정할 권한이 없습니다.");
                }
            }else{
                System.out.println("관리자는 평점에 대한 수정 및 삭제 권한이 없습니다.");
            }
        }
    }

    public void scoreList(int level){
        System.out.println("평점 목록");
        ArrayList<ScoreDTO> scoreList = scoreController.getList();
        if(level==2) {
            for (ScoreDTO d : scoreList) {
                if (d.getMovieId() == movieNumber.getId()) {
                    if(d.getReview()==null) {
                        System.out.printf("(%d)%s: %d\n", d.getScoreNumber(), d.getNickName(), d.getScore());
                    }
                }
            }
        } else if(level==3){
            for (ScoreDTO d : scoreList) {
                if (d.getMovieId() == movieNumber.getId()) {
                    if(d.getReview()!=null) {
                        System.out.printf("(%d)%s: %d - %s\n", d.getScoreNumber(), d.getNickName(), d.getScore(), d.getReview());
                    }
                }
            }
        } else if(level==1){
            for (ScoreDTO d : scoreList) {
                if(d.getMovieId()== movieNumber.getId()) {
                    if(d.getReview()!=null) {
                        System.out.printf("(%d)%s: %d - %s\n", d.getScoreNumber(), d.getNickName(), d.getScore(), d.getReview());
                    }else{
                        System.out.printf("(%d)%s: %d\n", d.getScoreNumber(), d.getNickName(), d.getScore());
                    }
                }
            }
        }
    }

    public void changeNickname(int id, String changeName){
        scoreController.nicknameChange(id, changeName);
    }

    public double getAverage(int movieId){
        double avg;
        avg = scoreController.getAverage(movieId);
        return avg;
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