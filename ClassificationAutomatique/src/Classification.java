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


    public static ArrayList<Depeche> lectureDepeches(String nomFichier) {
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
            float  nb_valide_all= 0;
            float nb_depeche_all = 0;

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

                int index = (int) index_categorie.get(depeche.getCategorie().toLowerCase());
                if (depeche.getCategorie().equalsIgnoreCase(categorieMax)) {
                    prediction.set(index, prediction.get(index) + 1);
                    nb_valide_all += 1;
                }
                valide.set(index, valide.get(index) + 1);
                nb_depeche_all += 1;
                // writer.write(categorieMax + ":"  + scoreMax + "\n");
                writer.write(categorieMax + "\n");

            }

            for (int i = 0; i < index_categorie.size(); i++) {
                writer.write(categories.get(i).getNom() + ":" + (prediction.get(i) * 100 / valide.get(i)) + "%\n");

            }
            writer.write("MOYENNE:" + (nb_valide_all * 100 / nb_depeche_all) + "%\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
        /*
            qui initialise un dictionnaire de mots pour la catégorie categorie à partir du vecteur
            depeches. Cette méthode doit parcourir les dépêches et pour chaque mot rencontré, l’ajouter au dictionnaire
            Le score est incrémenté à chaque nouvelle occurrence d'un mot
         */
        ArrayList<PaireChaineEntier> resultat = new ArrayList<>();
        System.out.println("debut initDico");

        for (Depeche depeche : depeches) {
            if (depeche.getCategorie().equalsIgnoreCase(categorie)) {
                ArrayList<String> mots = depeche.getMots();
                for (String mot : mots) {
                    // Cherche si le mot existe déjà dans le résultat
                    int indice = UtilitairePaireChaineEntier.indicePourChaine(resultat, mot);
                    if (indice == -1) {
                        // Si le mot n'existe pas, ajoute-le avec un score initial de 1
                        resultat.add(new PaireChaineEntier(mot, 1));
                    } else {
                        // Si le mot existe, incrémente son score de 1
                        PaireChaineEntier paireExistante = resultat.get(indice);
                        paireExistante.setEntier(paireExistante.getEntier() + 1);
                    }
                }
            }
        }

        return resultat;
    }

    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        /* Décrémente le score des mots de la categorie qui sont présents dans une autre categorie
         */
        for (Depeche depeche : depeches) {
            ArrayList<String> mots = depeche.getMots();
            for (String mot : mots) {
                if (!depeche.getCategorie().equalsIgnoreCase(categorie)) {
                    int indice = UtilitairePaireChaineEntier.indicePourChaine(dictionnaire, mot);
                    if (indice != -1) {
                        PaireChaineEntier paire = dictionnaire.get(indice);
                        paire.setEntier(paire.getEntier() - 1);
                    }
                }
            }
        }
    }

    public static int poidsPourScore(int score) {
        if (score > 20) {
            return 3;
        } else if (score > 5) {
            return 2;
        } else if (score > 0) {
            return 1;
        } else if (score <= 0) {
            return 0;
        }
        return -999;
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {

        /*qui crée pour la catégorie categorie le fichier lexique de nom nomFichier à partir du vecteur de
dépêches depeches. Cette méthode doit construire une ArrayList<PaireChaineEntier> avec
initDico, puis mettre à jour les scores dans ce vecteur avec calculScores et enfin utiliser le vecteur
résultant pour créer un fichier lexique en utilisant la fonction poidsPourScore. Prenez exemple sur la
classe ExempleEcritureFichier pour l’écriture dans un fichier.*/

        ArrayList<PaireChaineEntier> dictionnaire = new ArrayList<>();
        for (int i = 0; i < dictionnaire.size(); i++) {
            System.out.println(dictionnaire.get(i).getChaine());

        }
        dictionnaire = initDico(depeches, categorie);
        System.out.println(dictionnaire);
        calculScores(depeches, categorie, dictionnaire);
        System.out.println("dictionnaire :"+ dictionnaire);

        try {
            FileWriter writer = new FileWriter(nomFichier);
            for (int i = 0; i < dictionnaire.size(); i++) {
                writer.write(dictionnaire.get(i).getChaine() + ":" + poidsPourScore(dictionnaire.get(i).getEntier()) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //print dictionnaire
        for (int i = 0; i < dictionnaire.size(); i++) {
            dictionnaire.get(i).afficher();


        };


    }

    public static void main(String[] args) {

        //Chargement des dépêches en mémoire
        System.out.println("chargement des dépêches");
        ArrayList<Depeche> depeches = lectureDepeches("depeches.txt");
        ArrayList<Depeche> depeches_test = lectureDepeches("ClassificationAutomatique/test.txt");
        ArrayList<Categorie> categories= new ArrayList<Categorie>();

        // PARTIE 1 : chargement des lexiques créés à la main
        String[] categoriesNames = {"sport", "politique", "economie", "culture", "sciences"};

//        float b = UtilitairePaireChaineEntier.moyenne(sport.getlexic());
//        // print culture
//        System.out.println("moyenne de la catégorie culture :"+ b);

       // PARTIE 2

        ArrayList<Categorie> auto_categories= new ArrayList<Categorie>();

        for (String name : categoriesNames) {
            generationLexique(depeches, name, "./auto_lexique/" + name + ".txt");
        }

        for (String name : categoriesNames) {
            Categorie categorie = new Categorie(name);
            categorie.initLexique("./auto_lexique/" + name + ".txt");
            auto_categories.add(categorie);
        }
        System.out.println("Classement des dépêches avec les lexiques automatiques");

        classementDepeches(depeches_test, auto_categories, "auto_output.txt");

    }

}

