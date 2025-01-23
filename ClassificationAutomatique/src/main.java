import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Categorie sport = new Categorie("sport");
        sport.initLexique("sport.txt");

        System.out.println(UtilitairePaireChaineEntier.indicePourChaine(sport.getLexique() , "Médailles"));
        System.out.println(UtilitairePaireChaineEntier.moyenne(sport.getLexique()));
    }
}
