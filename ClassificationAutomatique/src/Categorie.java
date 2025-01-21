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


    public boolean initLexique(String nomFichier) {
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
            return true; // Indicate success
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Indicate failure
        }
    }

    public ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }


    public ArrayList<PaireChaineEntier> getlexic() {
        return lexique;
    }

    //calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        //d.contenue contient le texte de la dépêche
        //on le découpe en mots
        ArrayList<String> mots = d.getMots();
        int score = 0;
        System.out.println("mots de la depeche : " + mots);
        for (String mot : mots) {
            System.out.println("mot :" + mot+":");
            for (PaireChaineEntier paire : lexique) {
                if (mot.compareTo(paire.getChaine()) == 0) {
                    score += paire.getEntier();
                }
            }
        }
        return score;

    }
}





