public class Main {

    public static void main(String[] args) {
        Directory dir = new Directory("Movie");
        Directory english = new Directory("Avengers Series");
        File file1 = new File("Avenger EndGame", "1KB" );
        File file2 = new File("Captain America", "1.1KB");
        english.add(file1);
        english.add(file2);
        dir.add(english);
        dir.ls("   ");
    }
}


/*  OUTPUT -:

Movie/
  Avengers Series/
     - Avenger EndGame (1KB)
     - Captain America (1.1KB)

*/
