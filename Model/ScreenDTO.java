package Model;

public class ScreenDTO {
    private int id;
    private int movieId;
    private int theaterId;
    private String screenTime;
    private int screenNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }

    public boolean equals(Object o){
        if(o instanceof  ScreenDTO){
            ScreenDTO u = (ScreenDTO) o;
            return id == u.id;
        }
        return false;
    }

    public ScreenDTO(ScreenDTO origin){
        id = origin.id;
        movieId = origin.movieId;
        theaterId = origin.theaterId;
        screenTime = origin.screenTime;
        screenNumber = origin.screenNumber;
    }

    public ScreenDTO(){

    }
}
