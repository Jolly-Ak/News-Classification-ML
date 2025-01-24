import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Categorie {
    private String nom;
    private ArrayList<PaireChaineEntier> lexique;


    public Categorie(String nom) {
        this.nom = nom;
        this.lexique = new ArrayList<PaireChaineEntier>();
    }


    public void initLexique(String nomFichier) {
        // lecture du fichier d'entrée
        //  chaque ligne du fichier contient un mot suivi de : et d'un entier
        //  on crée un objet PaireChaineEntier pour chaque ligne
        //  et on l'ajoute à la liste lexique

        try {
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                int index = ligne.indexOf(":");
                if (index != -1) {
                    String chaine = ligne.substring(0, index);
                    int entier = Integer.parseInt(ligne.substring(index + 1));
                    this.lexique.add(new PaireChaineEntier(chaine, entier));
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }


    public String getNom() {
        return nom;
    }

    //calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        //d.contenue contient le texte de la dépêche
        //on le découpe en mots
        int count = 0;
        ArrayList<String> mots = d.getMots();
        int score = 0;
        for (String mot : mots) {
            for (PaireChaineEntier paire : lexique) {
                count = count + 1;
                if (mot.compareTo(paire.getChaine()) == 0) {
                    score += paire.getEntier();
                }
            }
        }
        return score;

    }
}





