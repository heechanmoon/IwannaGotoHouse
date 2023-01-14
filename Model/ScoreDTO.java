package Model;

public class ScoreDTO {
    private int id;
    private String userId;
    private int movieId;
    private int score;
    private String review;

    private int scoreNumber;

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean equals(Object o){
        if(o instanceof  ScoreDTO){
            ScoreDTO u = (ScoreDTO) o;
            return id == u.id;
        }
        return false;
    }

    public ScoreDTO(ScoreDTO origin){
        id = origin.id;
        userId = origin.userId;
        movieId = origin.movieId;
        score = origin.score;
        review = origin.review;
    }

    public ScoreDTO(){

    }
}
