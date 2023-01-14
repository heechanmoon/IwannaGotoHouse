package Model;

public class TheaterDTO {
    private int id;
    private String name;
    private String address;
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean equals(Object o){
        if(o instanceof  TheaterDTO){
            TheaterDTO u = (TheaterDTO) o;
            return id == u.id;
        }
        return false;
    }

    public TheaterDTO(TheaterDTO origin){
        id = origin.id;
        name = origin.name;
        address = origin.address;
        number = origin.number;
    }

    public TheaterDTO(){

    }
}
