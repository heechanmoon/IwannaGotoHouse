package Model;

public class MovieDTO {
    private int id;
    private String title;
    private String story;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean equals(Object o){
        if(o instanceof  MovieDTO){
            MovieDTO u = (MovieDTO) o;
            return id == u.id;
        }
        return false;
    }

    public MovieDTO(MovieDTO origin){
        id = origin.id;
        title = origin.title;
        story = origin.story;
        level = origin.level;
    }

    public MovieDTO(){

    }
}
