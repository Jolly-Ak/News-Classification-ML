import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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


    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {

        String categorieMax = null;
        for (int i = 0; i < depeches.size(); i++) {
            Depeche depeche = depeches.get(i);


            int scoreMax = 0;
            categorieMax = "";
            for (int j = 0; j < categories.size(); j++) {
                Categorie categorie = categories.get(j);
                int score = categorie.score(depeche);
                if (score > scoreMax) {
                    scoreMax = score;
                    categorieMax = categorie.getNom();
                }
            }
            // count = "nnn 'categoriemax'" with nnn the number of the ligne in the file
            String count = "ligne " + i + " " + categorieMax;

            try {
                FileWriter file = new FileWriter("output.txt");
                file.write(count + "\n");
                file.close();
                System.out.println("votre saisie a été écrite avec succès dans fichier-sortie.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }


        depeche.setCategorie(categorieMax);
    }

    }


    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
/*qui retourne une ArrayList<PaireChaineEntier> contenant tous les mots présents dans au
moins une dépêche de la catégorie categorie. Attention, même si le mot est présent plusieurs fois, il ne
doit apparaître qu’une fois dans la ArrayList retournée. Dans les entiers, nous stockerons les scores
associés à chaque mot et dans un premier temps, nous initialiserons ce score à 0.*/

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


        categories.add(culture);
        classementDepeches(depeches, categories, "output.txt");

        float b = UtilitairePaireChaineEntier.moyenne(sport.getlexic());
        // print culture
        System.out.println("moyenne de la catégorie culture :"+ b);




    }


}

