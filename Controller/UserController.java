package Controller;

import Model.UserDTO;

import java.util.ArrayList;

public class UserController {
    private ArrayList<UserDTO> list;
    private int nextId;
    public UserController() {
        list = new ArrayList<>();
        nextId = 1;

        UserDTO manager = new UserDTO();
        manager.setId(nextId++);
        manager.setUsername("Administrator");
        manager.setNickname("관리자");
        manager.setPassword("1234");
        manager.setLevel(2);

        insertAdmin(manager);
    }

    public void insertAdmin(UserDTO userDTO){
        userDTO.setId(nextId++);
        list.add(userDTO);
    }

    public void insert(UserDTO userDTO){
        userDTO.setId(nextId++);
        userDTO.setLevel(0);
        list.add(userDTO);
    }

    public UserDTO selectById(int id){
        for(UserDTO u : list){
            if(u.getId() == id){
                return new UserDTO(u);
            }
        }
        return null;
    }

    public void update(UserDTO userDTO){
        list.set(list.indexOf(userDTO), userDTO);
    }

    public void delete(int id){
        UserDTO u = new UserDTO();
        u.setId(id);
        list.remove(u);
    }

    public boolean validateUsername(String username){
        if(username.equalsIgnoreCase("X")){
            return false;
        }
        for(UserDTO u : list){
            if(username.equalsIgnoreCase(u.getUsername())){
                return false;
            }
        }
        return true;
    }

    public UserDTO auth(String username, String password){
        for(UserDTO u : list){
            if(username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())){
                return new UserDTO(u);
            }
        }

        return null;
    }

    public ArrayList<UserDTO> getList(int loginId){
        ArrayList<UserDTO> userList = new ArrayList<>();
        for(UserDTO u : list){
            if(u.getId()!=loginId){
                userList.add(u);
            }
        }
        return userList;
    }

    public int change(String username,int changeLevel){
        for(UserDTO u : list){
            if(username.equals(u.getUsername())){
                u.setLevel(changeLevel);
                update(u);
                return 1;
            }
        }
        return 0;
    }
}
