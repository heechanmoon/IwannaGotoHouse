package Controller;

import Model.MovieDTO;

import java.util.ArrayList;

public class MovieController {
    private ArrayList<MovieDTO> list;
    private int nextId;
    public MovieController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(MovieDTO movieDTO){
        movieDTO.setId(nextId++);
        list.add(movieDTO);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public ArrayList<MovieDTO> getList(){
        return list;
    }

    public MovieDTO selectById(int id){
        for(MovieDTO b : list){
            if(b.getId() == id){
                return new MovieDTO(b);
            }
        }
        return null;
    }

    public void update(MovieDTO movieDTO){
        list.set(list.indexOf(movieDTO), movieDTO);
    }

    public void delete(int number){
        MovieDTO b = new MovieDTO();
        b.setId(number);
        list.remove(b);
    }

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
