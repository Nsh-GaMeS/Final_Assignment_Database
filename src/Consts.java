// this class is made for all the constant variable messages etc.
// there are section for application settingd, output strings, error messages
public class Consts {
//      Application settings
        public static String dbFileName = "output.txt";
        public static int numOfMovies = 50;
        public static int minMoviesYear = 1887;
        public static int maxMoviesYear = 2022;

        
//      Format strings
        public static String strName = "Name:";
        public static String strDirector = "Director:";
        public static String strDate = "Date: ";
        public static String str = "%s";
        public static String outPutStringFormat = String.format("%s%s         %s%s         %s%s     \n",
                                                                strName,str, strDirector,str, strDate,str);
        public static String movie2String = String.format("%s %s, %s %s, %s %s",
                                                          strName,str, strDirector,str, strDate,str);
        
//      Error messages
        public static String dbLimitError = "you have reach the db limit....";
        public static String wrongFromatError = "wrong data format for Date field....";
        public static String wrongDateValueError = "wrong data. can be between 1800-2022....";
        
}
