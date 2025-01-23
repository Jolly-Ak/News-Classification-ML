import java.util.ArrayList;

public class MainKnn {
    public static void main(String[] args) {
        ArrayList<String> cilbe = new ArrayList<String>();
        // add mot in cilbe and voisin

        ArrayList<Depeche> depeches = Classification.lectureDepeches("depeches.txt");
        ArrayList<Depeche> depeches_cible = Classification.lectureDepeches("ClassificationAutomatique/test.txt");
        ArrayList<PaireChaineEntier> voisins = UtilitaireKnn.KnnDico(depeches, depeches_cible.get(0), 5);

        for (int i = 0; i < voisins.size(); i++) {
            voisins.get(i).afficher();

        }




    }
}

