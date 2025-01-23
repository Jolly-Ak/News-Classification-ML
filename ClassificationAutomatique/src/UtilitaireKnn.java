import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class UtilitaireKnn {

    public static int calculerSimilarite(ArrayList<String> cilbe, ArrayList<String> voisin) {
        int similarite = 0;
        for (String mot : cilbe) {
            if (voisin.contains(mot)) {
                similarite++;
            }
        }
        return similarite;
    }



    public static ArrayList<PaireChaineEntier> KnnDico(ArrayList<Depeche> depeches, Depeche cible, int k ) {

        ArrayList<PaireChaineEntier> voisins = new ArrayList<PaireChaineEntier>();

        for (int i = 0; i < depeches.size(); i++) {
            int SimilariterVoisin = calculerSimilarite(depeches.get(i).getMots(), cible.getMots());
            voisins.add(new PaireChaineEntier(depeches.get(i).getCategorie(), SimilariterVoisin));
        }

        //trier voisins par ordre decroissant de voisins.getEntier()
        for (int i = 0; i < voisins.size(); i++) {
            for (int j = i + 1; j < voisins.size(); j++) {
                if (voisins.get(i).getEntier() < voisins.get(j).getEntier()) {
                    PaireChaineEntier temp = voisins.get(i);
                    voisins.set(i, voisins.get(j));
                    voisins.set(j, temp);
                }
            }
        }


        for (int i = k; i < voisins.size(); i++) {
            voisins.remove(i);
        }
        return voisins;
    }

    public static String CategorieMajoritaire(ArrayList<PaireChaineEntier> voisins, int k){
        ArrayList<PaireChaineEntier> categories = new ArrayList<PaireChaineEntier>();
        // fait un dictioanire String , int
        Dictionary occ_categorie = new Hashtable();
        //fait une list ede categories avec les categories des voisins
        for (int i = 0; i < k; i++) {
            if (occ_categorie.get(voisins.get(i).getChaine()) == null) {
                occ_categorie.put(voisins.get(i).getChaine(), 1);
            } else {
                occ_categorie.put(voisins.get(i).getChaine(), (int) occ_categorie.get(voisins.get(i).getChaine()) + 1);
            }
        }

        for (int i = 0; i < k; i++) {
            if (occ_categorie.get(voisins.get(i).getChaine()) == null) {
                occ_categorie.put(voisins.get(i).getChaine(), 1);
            } else {
                occ_categorie.put(voisins.get(i).getChaine(), (int) occ_categorie.get(voisins.get(i).getChaine()) + 1);
            }
        }
        //prend
            //verifier si voisins.get(i).getChaine() est deja dans categories



        }
        int max = 0;
        int indice = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
                indice = i;
            }
        }
        return indice;
    }




}
