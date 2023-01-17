package Controller;

import Model.ScoreDTO;
import Model.ScreenDTO;

import java.util.ArrayList;

public class ScoreController {
    private ArrayList<ScoreDTO> list;
    private int nextId = 0;
    private int nextIndex;
    public ScoreController() {
        list = new ArrayList<>();
        nextIndex = 1;
    }

    public void insert(ScoreDTO scoreDTO){
        for(ScoreDTO d : list){
            if(d.getMovieId()==scoreDTO.getMovieId()){
                nextId = d.getScoreNumber();
            }
        }
        scoreDTO.setScoreNumber(nextId+1);
        scoreDTO.setId(nextIndex++);
        list.add(scoreDTO);
        nextId = 0;
    }

    public boolean isEmpty(int movieId){
        boolean isEmpty = true;
        for(ScoreDTO d : list){
            if(d.getMovieId()==movieId){
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    public ArrayList<ScoreDTO> getList(){
        return list;
    }

    public ScoreDTO selectById(int movieNumber, int id){
        for(ScoreDTO b : list){
            if(b.getMovieId() == movieNumber && b.getScoreNumber() == id){
                return new ScoreDTO(b);
            }
        }
        return null;
    }

    public void update(int nextIndex, ScoreDTO scoreDTO){
        int updateIndex = -1;
        for(ScoreDTO b : list){
            updateIndex++;
            if(b.getScoreNumber() == nextIndex){
                break;
            }
        }
        list.set(updateIndex, scoreDTO);
    }

    public void delete(int nextIndex){
        int deleteIndex = -1;
        for(ScoreDTO b : list){
            deleteIndex++;
            if(b.getId() == nextIndex){
                break;
            }
        }
        list.remove(deleteIndex);
    }

    public void delete(ScoreDTO commentDTO){
        list.remove(commentDTO);
    }

    public int getCommentNumber(ScoreDTO commentDTO){
        int number = list.indexOf(commentDTO);
        return number;
    }

    public void nicknameChange(int id, String changeName){
        for(ScoreDTO b : list){
            if(b.getUserId()==id){
                b.setNickName(changeName);
            }
        }
    }

    public double getAverage(int movieId){
        double total = 0.0;
        double sum = 0.0;
        double avg;
        for(ScoreDTO s : list){
            if(s.getMovieId()==movieId){
                total = total+1.0;
                sum = sum + (double)s.getScore();
            }
        }
        if(total!=0) {
            avg = sum / total;
            return avg;
        }else{
            return 0.0;
        }
    }

    /*
    public boolean validateUsername(String username){
        if(username.equalsIgnoreCase("X")){
            return false;
        }
        for(CommentDTO u : list){
            if(username.equalsIgnoreCase(u.getNickname())){
                return false;
            }
        }
        return true;
    }

     */

    /*
    public UserDTO auth(String username, String password){
        for(UserDTO u : list){
            if(username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())){
                return new UserDTO(u);
            }
        }

        return null;
    }
    */
}
