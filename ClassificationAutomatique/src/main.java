import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Categorie sport = new Categorie("sport");
        System.out.println(sport.initLexique("sport.txt"));
    }
}
