package Model;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private int level;
    private int upgrade;

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public int getUpgrade(){ return upgrade; }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean equals(Object o){
        if(o instanceof  UserDTO){
            UserDTO u = (UserDTO) o;
            return id == u.id;
        }
        return false;
    }

    public UserDTO(UserDTO origin){
        id = origin.id;
        username = origin.username;
        password = origin.password;
        nickname = origin.nickname;
        level = origin.level;
        upgrade = origin.upgrade;
    }

    public UserDTO(){
    }

    @Override
    public String toString() {
        return "{"+
                "id: "+id+", "+
                "username: "+username + ", " +
                "password: "+password + ", " +
                "nickname: "+nickname +
                "}";
    }
}
