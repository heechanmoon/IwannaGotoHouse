package Controller;

import Model.MovieDTO;
import Model.ScoreDTO;
import Model.ScreenDTO;

import java.util.ArrayList;
import java.util.Iterator;

public class ScreenController {
    private ArrayList<ScreenDTO> list;
    private int nextId = 0;
    private int nextIndex;
    public ScreenController() {
        list = new ArrayList<>();
        nextIndex = 1;
    }

    public void insert(ScreenDTO screenDTO){
        for(ScreenDTO d : list){
            if(d.getTheaterId()==screenDTO.getTheaterId()){
                nextId = d.getScreenNumber();
            }
        }
        screenDTO.setScreenNumber(nextId+1);
        screenDTO.setId(nextIndex++);
        list.add(screenDTO);
        nextId = 0;
    }

    public boolean isEmpty(int theaterId){
        boolean isEmpty = true;
        for(ScreenDTO d : list){
            if(d.getTheaterId()==theaterId){
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    public ArrayList<ScreenDTO> getList(){
        return list;
    }

    public ScreenDTO selectById(int theaterId, int id){
        for(ScreenDTO b : list){
            if(b.getTheaterId() == theaterId && b.getScreenNumber() == id){
                return new ScreenDTO(b);
            }
        }
        return null;
    }

    public void update(int nextIndex, ScreenDTO commentDTO){
        int updateIndex = -1;
        for(ScreenDTO b : list){
            updateIndex++;
            if(b.getId() == nextIndex){
                break;
            }
        }
        list.set(updateIndex, commentDTO);
    }

    public void delete(int nextIndex){
        int deleteIndex = -1;
        for(ScreenDTO b : list){
            deleteIndex++;
            if(b.getId() == nextIndex){
                break;
            }
        }
        list.remove(deleteIndex);
    }

    public void deleteMovie(int movieId){
        ScreenDTO b;
        Iterator<ScreenDTO> itr = list.iterator();
        while(itr.hasNext()){
            b = itr.next();
            if(b.getMovieId()==movieId){
                itr.remove();
            }
        }
    }

    public void delete(ScreenDTO commentDTO){
        list.remove(commentDTO);
    }

    public int getCommentNumber(ScreenDTO commentDTO){
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
