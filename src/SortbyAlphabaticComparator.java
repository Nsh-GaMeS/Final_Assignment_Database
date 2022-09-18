import java.util.Comparator;

// sort alphabaticly  comparator class
class SortbyAlphabatic implements Comparator<Movie> {
    // Used for sorting in ascending order of Director number
    public int compare(Movie a, Movie b){
        if( a != null && b != null) {
            return a.getNameOfMovie().compareTo(b.getNameOfMovie());
        } else {
            return 0;
        }
    }
}