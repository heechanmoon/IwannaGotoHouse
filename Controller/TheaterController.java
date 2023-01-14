package Controller;

import Model.TheaterDTO;

import java.util.ArrayList;

public class TheaterController {
    private ArrayList<TheaterDTO> list;
    private int nextId;
    public TheaterController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(TheaterDTO theaterDTO){
        theaterDTO.setId(nextId++);
        list.add(theaterDTO);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public ArrayList<TheaterDTO> getList(){
        return list;
    }

    public TheaterDTO selectById(int id){
        for(TheaterDTO b : list){
            if(b.getId() == id){
                return new TheaterDTO(b);
            }
        }
        return null;
    }

    public void update(TheaterDTO theaterDTO){
        list.set(list.indexOf(theaterDTO), theaterDTO);
    }

    public void delete(int number){
        TheaterDTO b = new TheaterDTO();
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
