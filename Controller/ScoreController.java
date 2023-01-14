package Controller;

import Model.ScoreDTO;

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
            if(d.getScoreNumber()==scoreDTO.getScoreNumber()){
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

    public void update(int nextIndex, ScoreDTO commentDTO){
        int updateIndex = -1;
        for(ScoreDTO b : list){
            updateIndex++;
            if(b.getId() == nextIndex){
                break;
            }
        }
        list.set(updateIndex, commentDTO);
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
