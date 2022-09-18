// this class define Movie object
public class Movie{
// name of the movie field
    private String name;
//  director's name
    private String director;
//  release date
    private int date;
    
    // ctor
    public Movie(String nameOfMovie, String director, int releaseDate) {
        this.name = nameOfMovie;
        this.director = director;
        this.date = releaseDate;
    }
    public Movie() {
        this.name = null;
        this.director = null;
        this.date = Integer.parseInt(null);
    }
    
    //Methods
    public String getNameOfMovie() {
        return name;
    }
    
    public void setNameOfMovie(String nameOfMovie) {
        this.name = nameOfMovie;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public int getReleaseDate() {
        return date;
    }
    
    public void setReleaseDate(int releaseDate) {
        this.date = releaseDate;
    }
    
    @Override
    public String toString() {
        return String.format(Consts.movie2String, name, director, String.valueOf(date));
    }
}
