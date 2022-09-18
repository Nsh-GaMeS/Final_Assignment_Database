/*
 Author: Nathaniel Shafran Avshalom
 Description: This is a movie theater application.
              It has the following functionality: add new movie,show saved movies.
              Movie object has property name(string), director(string), release date(int).
              Database functinality implemented by using file.
              Movie information will be saved to the file - output.txt in the same directory with this application.
              output.txt file will be cerated automaticly on the first Movie addition.
              On start of this application saved Movies information will read from the Database (text file)
              and be  shown in the GUI.
              Additional functinality:
                Shown movies' list can be sorted by name or release date or director.
                Maximum capacity for database in 50 movies - this can be chages in the Consts class in the settings section.
                When reach max capasity - no addtional movie will be saved and error message will be printed out.
                There is a validation for the Release date text field - just integer input will be allowed,
                otherwise save won't be allowed and error message will be printed out.
                Also, valid input for Release date - is an integer bigger than 1888
                
 Date: 2022, 01, 31
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


class Main extends JFrame implements ActionListener{
    private static DataBase db;
    JLabel dbLabel;
    JLabel addingMenuLabel;
    JLabel nameLabel;
    JLabel directorLabel;
    JLabel dateLabel;
    JLabel moviesLabel;
    JLabel sortByLabel;
    
    JButton addButton;
    JButton abcSortButton;
    JButton sortByDateButton;
    JButton sortByDirectorButton;
    JTextField nameTextField;
    JTextField directorTextField;
    JTextField dateTextField;
    static JTextArea  movieTableTextArea;
    
    
    public static void main(String[] args){
        new Main();
    }
    
    // GUI method
    public Main() {
        db = new DataBase();
        // GUI conrols definition
        //JFrame
        setBounds(0, 0, 1600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        //JLabels
        dbLabel = new JLabel("Movie Theater Database");
        dbLabel.setBounds(600, 0, 400, 100);
        dbLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(dbLabel);
        addingMenuLabel = new JLabel("Movie adding menu");
        addingMenuLabel.setBounds(60, 35, 400, 100);
        addingMenuLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(addingMenuLabel);
        nameLabel = new JLabel(" Name ");
        nameLabel.setBounds(10, 75, 400, 100);
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        this.add(nameLabel);
        directorLabel = new JLabel(" Director ");
        directorLabel.setBounds(10, 175, 400, 100);
        directorLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        this.add(directorLabel);
        dateLabel = new JLabel(" Date ");
        dateLabel.setBounds(10, 275, 400, 100);
        dateLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        this.add(dateLabel);
        moviesLabel = new JLabel("All movies");
        moviesLabel.setBounds(450, 35, 500, 100);
        moviesLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(moviesLabel);
        sortByLabel = new JLabel("Sort by");
        sortByLabel.setBounds(1200, 35, 400, 100);
        sortByLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        this.add(sortByLabel);
    
        //JButtons
        addButton = new JButton("Add");
        addButton.setBounds(80, 400, 200, 50);
        addButton.addActionListener(this);
        this.add(addButton);
    
        abcSortButton = new JButton("Alphabetic");
        abcSortButton.setBounds(1200, 100, 200, 50);
        abcSortButton.addActionListener(this);
        this.add(abcSortButton);
    
        sortByDateButton = new JButton("Release Date");
        sortByDateButton.setBounds(1200, 225, 200, 50);
        sortByDateButton.addActionListener(this);
        this.add(sortByDateButton);
    
        sortByDirectorButton = new JButton("Director");
        sortByDirectorButton.setBounds(1200, 350, 200, 50);
        sortByDirectorButton.addActionListener(this);
        this.add(sortByDirectorButton);
    
        // JTextFields
        nameTextField = new JTextField("   Enter name here");
        nameTextField.setBounds(80, 100, 200, 50);
        this.add(nameTextField);
        directorTextField = new JTextField("   Enter the directors name here");
        directorTextField.setBounds(80, 200, 200, 50);
        this.add(directorTextField);
        dateTextField = new JTextField("   Enter release date here");
        dateTextField.setBounds(80, 300, 200, 50);
        this.add(dateTextField);
        // show movie text area
        movieTableTextArea = new JTextArea();
        movieTableTextArea.setBounds(450, 100, 600, 500);
        movieTableTextArea.setLineWrap(true);
        movieTableTextArea.setEditable(false);
        movieTableTextArea.setBorder(new LineBorder(Color.BLACK, 4));
        //get text from db line per movie
        refresh();
        this.add(movieTableTextArea);
        
        setVisible(true);
    }
    
    // method for saving movies
    private void saveMovie (String nameOfMovie, String director, int releaseDate) {
        if (db.getMovies().length >= Consts.numOfMovies) {
            Movie newMovie = new Movie(nameOfMovie, director, releaseDate);
            db.addMovie(newMovie);
            db.saveToFile();
        } else {
            System.out.println(Consts.dbLimitError);
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        add new movie
        if (e.getSource() == addButton){  // if the add button is pressed
            try {
//                  validate not integer input
                    int date = Integer.parseInt(dateTextField.getText().trim());
//                  validate data range
                    if (date>Consts.minMoviesYear) {
//                      save new movie to the DB
                        saveMovie(nameTextField.getText(), directorTextField.getText(), date);
                        movieTableTextArea.selectAll();
                        movieTableTextArea.replaceSelection("");
//                      printout movies list
                        printOutMovies();
                    } else
//                       print our erro rmessage for wornf releasee date value
                        System.out.println(Consts.wrongDateValueError);
                } catch (Exception ex) {
//                  print out error for thw rong release date value type
                    System.out.println(Consts.wrongFromatError + ex);
            }
        }
//      if the "Sort by director" button is pressed
        if (e.getSource() == sortByDirectorButton) {
            Arrays.sort(db.getMovies(), new SortbyDirector());
            printOutMovies();
        }
//      if the "Sort alphabeticly" button is pressed
        if (e.getSource() == abcSortButton){
            Arrays.sort(db.getMovies(), new SortbyAlphabatic());
            printOutMovies();
        }
//      if the "Sort by date" button is pressed
        if (e.getSource() == sortByDateButton){
            Arrays.sort(db.getMovies(), new SortbyDate());
            printOutMovies();
        }
    }
    
    // this method refreshes the array, by reading the file and svaing it to the array
    public static void refresh() {
        db.populateFromFile();
        printOutMovies();
    }
    
    // prints out all the movies in the array
    public static void printOutMovies(){
        Movie[] arr = db.getMovies();
        String strToShow = "";
        for (Movie currentMovie : arr) {
            if (currentMovie != null) {
                String name = currentMovie.getNameOfMovie();
                String director = currentMovie.getDirector();
                int date = currentMovie.getReleaseDate();
                String strAddition = String.format(Consts.outPutStringFormat, name, director, String.valueOf(date));
                strToShow = strToShow + strAddition;
            }
        }
        movieTableTextArea.setText(strToShow);
    }
}







