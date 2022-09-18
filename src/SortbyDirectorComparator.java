import java.util.Comparator;

// sort by director comparator class
class SortbyDirector implements Comparator<Movie> {
    // Used for sorting in ascending order of Director number
    public int compare(Movie a, Movie b){
        if( a != null && b != null) {
            return a.getDirector().compareTo(b.getDirector());
        } else {
            return 0;
        }
    }
}