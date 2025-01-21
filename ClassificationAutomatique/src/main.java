import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Categorie sport = new Categorie("spo");
        sport.initLexique("sport.txt");
        Categorie sciences = new Categorie("SCIENCES");
        sciences.initLexique("sciences.txt");
    }
}
