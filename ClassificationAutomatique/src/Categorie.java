<<<<<<< Updated upstream
import java.io.FileInputStream;
import java.io.FileNotFoundException;
=======
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
>>>>>>> Stashed changes
import java.io.IOException;
import java.util.ArrayList;


public class Categorie {

    private String nom; // le nom de la catégorie p.ex : sport, politique,...
    private ArrayList<PaireChaineEntier> lexique; //le lexique de la catégorie

    // constructeur
    public Categorie(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }


    public  ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }


    // initialisation du lexique de la catégorie à partir du contenu d'un fichier texte
    public void initLexique(String nomFichier) {

    }


    import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

    public class PaireChaineEntier {
        private String chaine;
        private int entier;

        public PaireChaineEntier(String chaine, int entier) {
            this.chaine = chaine;
            this.entier = entier;
        }



        public void initLexique (String nomFichier) throws FileNotFoundException {
            ArrayList<PaireChaineEntier> lexique = new ArrayList<>();


            try {
                // lecture du fichier d'entrée
                FileInputStream file = new FileInputStream(nomFichier);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    int index = ligne.indexOf(":");
                    String chaine = ligne.substring(0, index);
                    int entier = Integer.parseInt(ligne.substring(index + 1));
                    ligne = scanner.nextLine();


                    lexique.add(new PaireChaineEntier(chaine, entier));

                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}





//calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        return 0;
    }
    //
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
    public InitMonde() {
        }

        public static ArrayList<PaireChaineEntier>creerMonde() {
            ArrayList<PaireChaineEntier> resultat = new ArrayList();

            try {
                BufferedReader br = new BufferedReader(new FileReader("Monde2019.txt"));

                String ligne;
                try {
                    while((ligne = br.readLine()) != null) {
                        String nom = ligne;
                        ligne = br.readLine();
                        String continent = ligne;
                        ligne = br.readLine();
                        int population = Integer.parseInt(ligne);
                        ligne = br.readLine();
                        int superficie = Integer.parseInt(ligne);
                        resultat.add(new PaireChaineEntier(nom, continent, population, superficie));
                    }
                } catch (Throwable var10) {
                    try {
                        br.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }

                    throw var10;
                }

                br.close();
            } catch (FileNotFoundException var11) {
                FileNotFoundException e = var11;
                e.printStackTrace();
            } catch (IOException var12) {
                IOException e = var12;
                e.printStackTrace();
            }

            return resultat;
        }
    }



}
