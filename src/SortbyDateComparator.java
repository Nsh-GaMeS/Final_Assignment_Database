import java.util.Comparator;

// sort by date  comparator class
class SortbyDate implements Comparator<Movie> {
    // Used for sorting in ascending order of Director number
    public int compare(Movie a, Movie b){
        if( a != null && b != null) {
            return b.getReleaseDate() - a.getReleaseDate();
        } else {
            return 1;
        }
    }
}