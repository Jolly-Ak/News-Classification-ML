import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Categorie {
    private String nom;
    private ArrayList<PaireChaineEntier> lexique;


    public Categorie(String nom){
        this.nom = nom;
        this.lexique = new ArrayList<PaireChaineEntier>();
    }


    public  void  initLexique(String nomFichier)  {


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


                this.lexique.add(new PaireChaineEntier(chaine, entier));

            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PaireChaineEntier> getlexic () {
        return lexique;
    }
    //calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        return 0;
    }
}






