import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Categorie sport = new Categorie("spo");
        sport.initLexique("sport.txt");

        System.out.println(UtilitairePaireChaineEntier.indicePourChaine(sport.getlexic() , "Médailles"));
        System.out.println(UtilitairePaireChaineEntier.moyenne(sport.getlexic()));
    }
}
