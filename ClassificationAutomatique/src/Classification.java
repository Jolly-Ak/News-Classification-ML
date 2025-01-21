import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Hashtable;

public class Classification {


    private static ArrayList<Depeche> lectureDepeches(String nomFichier) {
        //creation d'un tableau de dépêches
        ArrayList<Depeche> depeches = new ArrayList<>();
        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String id = ligne.substring(3);
                ligne = scanner.nextLine();
                String date = ligne.substring(3);
                ligne = scanner.nextLine();
                String categorie = ligne.substring(3);
                ligne = scanner.nextLine();
                String lignes = ligne.substring(3);
                while (scanner.hasNextLine() && !ligne.equals("")) {
                    ligne = scanner.nextLine();
                    if (!ligne.equals("")) {
                        lignes = lignes + '\n' + ligne;
                    }
                }
                Depeche uneDepeche = new Depeche(id, date, categorie, lignes);
                depeches.add(uneDepeche);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return depeches;
    }


    public static void classementDepeches(ArrayList<Depeche> depeches,ArrayList<Categorie> categories,String nomFichier){
//        qui pour chacune des dépêches de depeches, calcule le score pour chaque catégorie de categories et
//        écrit dans le fichier de nom nomFichier, le nom de la catégorie ayant le plus grand score ainsi que les
//        pourcentages conformément au format attendu pour les fichiers réponses (voir ci-dessus). Prenez exemple sur
//        la classe ExempleEcritureFichier pour l’écriture dans un fichier.
        ArrayList<Integer> validation = new ArrayList<Integer>();
        ArrayList<Integer> donner = new ArrayList<Integer>();
        //créée un dictionaire str et int
        Dictionary index_categorie = new Hashtable();
        for (int i = 0; i <categories.size() ; i++) {
            index_categorie.put(categories.get(i).getNom(),i);
        }
        ArrayList<Integer> valide = new ArrayList<Integer>();
        ArrayList<Integer> prediction = new ArrayList<Integer>();

        for (int i = 0; i < index_categorie.size(); i++) {
            valide.add(0);
            prediction.add(0);
        }

        System.out.println("dictionnaire :"+ index_categorie);
        System.out.println("validation :"+ validation);
        System.out.println("donner :"+ donner);

        try {
            FileWriter writer = new FileWriter(nomFichier);
            for (int i = 0; i < depeches.size(); i++) {
                Depeche depeche = depeches.get(i);
                writer.write(depeche.getId() + ":");
                int scoreMax = -1;
                String categorieMax = "";

                for (int j = 0; j < categories.size(); j++) {
                    Categorie categorie = categories.get(j);
                    int score = categorie.score(depeche);
                    if (score > scoreMax) {
                        categorieMax = categorie.getNom();
                        scoreMax = score;
                    }
                }

                int index = (int) index_categorie.get(categorieMax);
                if (depeche.getCategorie().toLowerCase().compareTo(categorieMax) == 0) {
                    prediction.set(index, prediction.get(index) + 1);
                }
                valide.set(index, valide.get(index) + 1);
                writer.write(categorieMax + ":"  + scoreMax + "\n");
            }
            for (int i = 0; i < index_categorie.size(); i++) {

                System.out.println("valide :"+ valide.get(i));

                System.out.println("prediction :"+ prediction.get(i));
                writer.write(categories.get(i).getNom() + ":" + (prediction.get(i) * 100 / valide.get(i)) + "%\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
/*qui retourne une ArrayList<PaireChaineEntier> contenant tous les mots présents dans au
moins une dépêche de la catégorie categorie. Attention, même si le mot est présent plusieurs fois, il ne
doit apparaître qu’une fois dans la ArrayList retournée. Dans les entiers, nous stockerons les scores
associés à chaque mot et dans un premier tempvs, nous initialiserons ce score à 0.*/

        ArrayList<PaireChaineEntier> resultat = new ArrayList<>();

        for (int i = 0; i < depeches.size(); i++) {
            if (depeches.get(i).getCategorie().equals(categorie)) {
                ArrayList<String> mots = depeches.get(i).getMots();
                for (int j = 0; j < mots.size(); j++) {
                    if (UtilitairePaireChaineEntier.indicePourChaine(resultat,mots.get(j)) == -1) {
                        resultat.add(new PaireChaineEntier(mots.get(j), 0));
                    }
                }
            }
        }
        return resultat;
    }


    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
    }

    public static int poidsPourScore(int score) {
        if (score < 0) {
            return 0;
        } else if (score < 5) {
            return 1;
        } else if (score < 10) {
            return 2;
        } else if (score < 15) {
            return 3;
        }
        return -1;
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {

    }

    public static void main(String[] args) {

        //Chargement des dépêches en mémoire
        System.out.println("chargement des dépêches");
        ArrayList<Depeche> depeches = lectureDepeches("depeches.txt");

//        for (int i = 0; i < depeches.size(); i++) {
//            depeches.get(i).afficher();
//        }
        depeches.getFirst().afficher();
        Categorie sport = new Categorie("sport");
        sport.initLexique("lexique/sport.txt");
        System.out.println("Lexique de la catégorie sport :"+ sport.getlexic());

        int a = sport.score(depeches.getFirst());
        System.out.println("score de la première dépêche pour la catégorie sport :"+ a);

        ArrayList<Categorie> categories= new ArrayList<Categorie>();
        categories.add(sport);
        Categorie politique = new Categorie("politique");
        politique.initLexique("lexique/politique.txt");
        categories.add(politique);

        Categorie economie = new Categorie("economie");
        economie.initLexique("lexique/economie.txt");
        categories.add(economie);

        Categorie culture = new Categorie("culture");
        culture.initLexique("lexique/culture.txt");

        Categorie science = new Categorie("science");
        science.initLexique("lexique/sciences.txt");


        categories.add(culture);
        classementDepeches(depeches, categories, "output.txt");

        float b = UtilitairePaireChaineEntier.moyenne(sport.getlexic());
        // print culture
        System.out.println("moyenne de la catégorie culture :"+ b);




    }


}

