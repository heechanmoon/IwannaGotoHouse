package View;

import Controller.UserController;
import Model.UserDTO;
import util.ScannerUtil;

import java.util.Scanner;

public class UserViewer {
    private final Scanner SCANNER;
    private UserController userController;
    private MovieViewer movieViewer;
    private TheaterViewer theaterViewer;

    private UserDTO logIn = null;

    public UserViewer(Scanner scanner){
        SCANNER = scanner;
        userController = new UserController();
    }

    public void setMovieViewer(MovieViewer movieViewer){
        this.movieViewer = movieViewer;
    }

    public void setTheaterViewer(TheaterViewer theaterViewer){
        this.theaterViewer = theaterViewer;
    }

    public void showIndex(){
        String message = "1. 로그인 2. 회원가입 3. 종료";
        while (true){
            int userChoice = ScannerUtil.nextInt(SCANNER,message);
            if(userChoice == 1){
                auth();
                if(logIn != null){
                    movieViewer.setLogIn(logIn);
                    theaterViewer.setLogIn(logIn);
                    showMenu();
                }
            }else if(userChoice==2){
                register();
            } else if (userChoice==3) {
                System.out.println("사용 종료");
                break;
            }
        }
    }

    private void register(){
        String message;
        message = "사용하실 아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER,message);

        while(!userController.validateUsername(username)){
            System.out.println("해당 아이디는 사용하실 수 없습니다.");
            message = "사용하실 아이디를 다시 입력해주세요.(뒤로가기 \"X\")";
            username = ScannerUtil.nextLine(SCANNER,message);

            if(username.equalsIgnoreCase("X")){
                break;
            }
        }

        if(!username.equalsIgnoreCase("X")){
            UserDTO u = new UserDTO();
            u.setUsername(username);

            message = "사용하실 비밀번호를 입력해주세요.";
            u.setPassword(ScannerUtil.nextLine(SCANNER,message));

            message = "사용하실 닉네임을 입력해주세요.";
            u.setNickname(ScannerUtil.nextLine(SCANNER,message));

            userController.insert(u);
        }
    }

    private void auth(){
        String message;
        message = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER,message);

        message = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(SCANNER,message);

        logIn = userController.auth(username, password);

        if(logIn==null){
            System.out.println("아이디/비밀번호를 다시 확인하십시오.");
        }
    }

    private void showMenu(){
        String message = "1. 영화 정보 2. 극장 및 상영정보 3. 회원 정보 관리 4. 로그 아웃";
        while(logIn != null){
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if(userChoice == 1){
                movieViewer.showIndex();
            } else if (userChoice == 2) {
                theaterViewer.showIndex();
            } else if (userChoice == 3) {
                printOne();
            } else if (userChoice == 4) {
                logIn = null;
                System.out.println("로그아웃 되었습니다.");
            }
        }
    }

    private void printOne(){
        System.out.println("회원 번호: " + logIn.getId());
        System.out.println("회원 닉네임: " + logIn.getNickname());


        if(logIn.getLevel()==0) {
            System.out.println("회원 등급: 일반 관람객");
            System.out.println("-------------------------------------------");
            String message = "1. 수정 2. 탈퇴 3. 뒤로가기";
            int userChoice = ScannerUtil.nextInt(SCANNER,message,1,3);
            if(userChoice == 1){
                update();
            }else if(userChoice == 2){
                delete();
            }
        }else if(logIn.getLevel()==1){
            System.out.println("회원 등급: 전문 평론가");
            System.out.println("-------------------------------------------");
            String message = "1. 수정 2. 탈퇴 3. 뒤로가기";
            int userChoice = ScannerUtil.nextInt(SCANNER,message,1,3);
            if(userChoice == 1){
                update();
            }else if(userChoice == 2){
                delete();
            }
        }else{
            System.out.println("회원 등급: 관리자");
            System.out.println("-------------------------------------------");
            String message = "1. 수정 2. 탈퇴 3. 회원등급변경 4. 뒤로가기";
            int userChoice = ScannerUtil.nextInt(SCANNER,message,1,4);
            if(userChoice == 1){
                update();
            }else if(userChoice == 2){
                delete();
            }else if(userChoice == 3){
                change();
            }
        }
    }

    private void update(){
        System.out.println("회원 번호: " + logIn.getId());
        System.out.println("회원 닉네임: " + logIn.getNickname());
        System.out.println("-------------------------------------------");
        String message = "새로운 비밀번호를 입력해주세요.";
        String newPassword = ScannerUtil.nextLine(SCANNER,message);

        message = "새로운 닉네임을 입력해주세요.";
        String newNickname = ScannerUtil.nextLine(SCANNER,message);

        message = "기존 비밀번호를 입력해주세요.";
        String oldPassword = ScannerUtil.nextLine(SCANNER,message);

        if(logIn.getPassword().equals(oldPassword)){
            logIn.setNickname(newNickname);
            logIn.setPassword(newPassword);

            userController.update(logIn);
        }else{
            System.out.println("기존 비밀번호가 아닙니다.");
        }
    }

    private void delete(){
        String message = "정말로 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if(yesNo.equalsIgnoreCase("Y")){
            message = "비밀번호를 입력해주세요.";
            String password = ScannerUtil.nextLine(SCANNER,message);

            if(password.equals(logIn.getPassword())){
                userController.delete(logIn.getId());
                logIn = null;
            }
        }
    }

    private void change(){
        String message = "등급을 변경할 회원의 아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "변경할 등급을 입력해주세요(0: 일반 관람객  1: 전문 평론가  2: 관리자)";
        int changeLevel = ScannerUtil.nextInt(SCANNER,message,0,2);

        int result = userController.change(username,changeLevel);

        if(result == 0){
            System.out.println("해당 회원 아이디가 존재하지않아 변경에 실패했습니다.");
        }else{
            System.out.println("해당 회원의 등급이 변경되었습니다.");
        }
    }
}