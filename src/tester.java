public class tester {
    public static void main(String[] args) {
        DataBase db = new DataBase();
//      write to DB
        Movie movie1 = new Movie("titanic", "arnold", 2020);
        Movie movie2 = new Movie("titanic111", "arnold111", 2021);
   
////     ++++++++++++++++++++++++++++++
        // read from db
        db.populateFromFile();
//        Movie[] getTestMovie = db.getMovies();
    }
}
