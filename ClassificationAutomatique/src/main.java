import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws FileNotFoundException {

        // PARTIE 1 : chargement des dépêches
        System.out.println("chargement des dépêches");
        ArrayList<Depeche> depeches =Classification.lectureDepeches("depeches.txt");
        ArrayList<Depeche> depeches_test = Classification.lectureDepeches("ClassificationAutomatique/test.txt");
        ArrayList<Categorie> categories= new ArrayList<Categorie>();

        // PARTIE 1 : chargement des lexiques créés à la main
        String[] categoriesNames = {"sport", "politique", "economie", "culture", "sciences"};

        //PARTIE 1

        for (String name : categoriesNames) {
            Categorie categorie = new Categorie(name);
            categorie.initLexique("./lexique/" + name + ".txt");
            categories.add(categorie);
        }
        System.out.println("Classement des dépêches avec les lexiques manuels");


    }
}
