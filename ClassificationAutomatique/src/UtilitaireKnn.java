import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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



    public static ArrayList<PaireChaineEntier> KnnDico(ArrayList<Depeche> depeches, Depeche cible ) {
        // retourne les k voisins les plus proches de la cible

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


        return voisins;
    }

    public static String CategorieMajoritaire(ArrayList<PaireChaineEntier> voisins, int k) {
        // retourn la categorie majoritaire dans les k voisins

        ArrayList<PaireChaineEntier> categories = new ArrayList<PaireChaineEntier>();

        // fait un dico String , int
        Dictionary occ_categorie = new Hashtable();


        // compte le nombre d'occurence de chaque categorie dans les k voisins
        for (int i = 0; i < k; i++) {
            if (occ_categorie.get(voisins.get(i).getChaine()) == null) {
                occ_categorie.put(voisins.get(i).getChaine(), 1);
            } else {
                occ_categorie.put(voisins.get(i).getChaine(), (int) occ_categorie.get(voisins.get(i).getChaine()) + 1);
            }
        }

        // trouve la categorie majoritaire dans occ_categorie
        int max = 0;
        String categorie = "";
        for (int i = 0; i < k; i++) {
            if ((int) occ_categorie.get(voisins.get(i).getChaine()) > max) {
                max = (int) occ_categorie.get(voisins.get(i).getChaine());
                categorie = voisins.get(i).getChaine();
            }
        }
        System.out.println(occ_categorie);
        return categorie;

    }

    public static void classementDepechesKnn(ArrayList<Depeche> depeches,ArrayList<Depeche> cibles,String nomFichier, int k){
//        qui pour chacune des dépêches de depeches, calcule le score pour chaque catégorie de categories et
//        écrit dans le fichier de nom nomFichier, le nom de la catégorie ayant une majoriter de  knn de la cible ainsi que les
//        pourcentages conformément au format attendu pour les fichiers réponses (voir ci-dessus). Prenez exemple sur
//        la classe ExempleEcritureFichier pour l’écriture dans un fichier.
        int x_index = 0;
        Map<String, Integer> index_categorie = new HashMap();
        ArrayList<Integer> valide = new ArrayList<Integer>();
        ArrayList<Integer> prediction = new ArrayList<Integer>();

        Map<String, Integer> ValidationCategories = new HashMap();

        for (int i = 0; i < index_categorie.size(); i++) {
            valide.add(0);
            prediction.add(0);
        }



        try {
            float  nb_valide_all= 0;
            float nb_depeche_all = 0;

            FileWriter writer = new FileWriter(nomFichier);

            for (int i = 0; i < depeches.size(); i++) {
                if (ValidationCategories.get(depeches.get(i).getCategorie()) == null) {
                    ValidationCategories.put(depeches.get(i).getCategorie(), 0);
                    ValidationCategories.put((depeches.get(i).getCategorie()+"validation"), 0);
                }




                Depeche cible = cibles.get(i);
                ArrayList<PaireChaineEntier> voisins = UtilitaireKnn.KnnDico(depeches, cible);
                String categorieMaj = UtilitaireKnn.CategorieMajoritaire(voisins, k);
                writer.write(cible.getId() + ":" + categorieMaj + "\n");


                if (cible.getCategorie().equalsIgnoreCase(categorieMaj)) {
                    writer.write("CORRECT\n");
                    ValidationCategories.put(cible.getCategorie(), (int) ValidationCategories.get(cible.getCategorie()) + 1);
                    ValidationCategories.put(cible.getCategorie() + "validation", (int) ValidationCategories.get(cible.getCategorie() + "validation") + 1);
                    nb_valide_all += 1;

                }
                ValidationCategories.put(cible.getCategorie() + "validation", (int) ValidationCategories.get(cible.getCategorie() + "validation") + 1);
                nb_depeche_all += 1;
                // writer.write(categorieMax + ":"  + scoreMax + "\n");

            }

            for (String categorie : ValidationCategories.keySet()) {
                if (categorie.contains("validation")) {
                    continue;
                }else {
                    writer.write(categorie + ":" + (ValidationCategories.get(categorie) * 100 / ValidationCategories.get(categorie + "validation")) + "%\n");
                }

            }
            writer.write("MOYENNE:" + (nb_valide_all * 100 / nb_depeche_all) + "%\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        ArrayList<String> cilbe = new ArrayList<String>();
        // add mot in cilbe and voisin

        ArrayList<Depeche> depeches = Classification.lectureDepeches("depeches.txt");
        ArrayList<Depeche> cibles = Classification.lectureDepeches("ClassificationAutomatique/test.txt");
        ArrayList<Depeche> depeches_cible = Classification.lectureDepeches("ClassificationAutomatique/test.txt");
        ArrayList<PaireChaineEntier> voisins = UtilitaireKnn.KnnDico(depeches, depeches_cible.get(0));

        for (int i = 0; i < voisins.size(); i++) {
            voisins.get(i).afficher();
        }
//        System.out.println(UtilitaireKnn.CategorieMajoritaire(voisins, 6)+ " est la catégorie majoritaire");

        ArrayList<Categorie> categories = new ArrayList<Categorie>();

        classementDepechesKnn(depeches,cibles,"output_auto_knn.txt", 15);






    }




}
