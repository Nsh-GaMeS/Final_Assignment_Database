import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import static java.lang.Integer.parseInt;

//this class define database object
// impelement save Movie to file and get Movie from file functinality
// name of the output file defined in the Consts class
public class DataBase{
//  last used cell number
    private int cellUsed = 0;
//  list of Movies in teh datebase
    private Movie[] movies;
    
//    ctor
    public DataBase() {
        this.movies = new Movie[Consts.numOfMovies];
        this.cellUsed++;
    }
    
//    get Movies
    public Movie[] getMovies() {
        return movies;
    }
    
//  save internal list of Movies to file
    public void saveToFile() {
        try {
            FileWriter out = new FileWriter(Consts.dbFileName);
    
            for (Movie movie : movies) {
                if (movie != null) {
                    String rowdb = movie.toString();
                    System.out.println(rowdb);
                    out.write(rowdb + '\n');//write to file
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//     clear internal list of the movies
    private void clean() {
        Arrays.fill(movies, null);
    }
    
//    populate internal Movies list from the file
    public void populateFromFile() {
        clean();
        try {
            
            FileReader in = new FileReader(Consts.dbFileName);
            BufferedReader br = new BufferedReader(in);
            String strFromFile = br.readLine();
            while (strFromFile != null && strFromFile.length() > 0) {
                String name = strFromFile.substring(strFromFile.indexOf(Consts.strName) + 6, strFromFile.indexOf(","));
                String director = strFromFile.substring(strFromFile.indexOf(Consts.strDirector) + 10, strFromFile.lastIndexOf(","));
                String date = strFromFile.substring(strFromFile.indexOf(Consts.strDate) + 6, strFromFile.length());
                Movie parsedMovie = new Movie(name, director, parseInt(date.trim()));
                this.addMovie(parsedMovie);
                strFromFile = br.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
//   add movie object to the internal Movies collection and increase cellUsed
    public void addMovie(Movie movie) {
        if (movie != null) {
            movies[cellUsed] = movie;
            this.cellUsed++;
        }
    }
    
}
