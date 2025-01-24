import java.util.ArrayList;
import java.util.Scanner;

public class figure {
    public static void main(String[] args) {
        String[] categoriesNames = {"sport", "politique", "economie", "culture", "sciences"};
        ArrayList<Categorie> categories = new ArrayList<Categorie>();

        for (String name : categoriesNames) {
            Categorie categorie = new Categorie(name);
            categorie.initLexique("./lexique/" + name + ".txt");
            categories.add(categorie);
        }

        //Figure 2
        Categorie SportCategorie = new Categorie("sport");

        ArrayList<Depeche> depeches = Classification.lectureDepeches("depeches.txt");


        SportCategorie.initLexique("lexique/SPORT.txt");


        for (int i = 0; i < 5; i++) {
            SportCategorie.getLexique().get(i).afficher();

        }


        //Figure 3
        System.out.println("FIGURE 3");

        //demander un mot à l'utilisateur
        Scanner lecteur = new Scanner(System.in);
        System.out.printf("Entrez un mot : ");
        String mot = lecteur.nextLine();
        System.out.println("poids de "+mot+" est " + UtilitairePaireChaineEntier.entierPourChaine(SportCategorie.getLexique(), mot)+" dans la catégorie "+ SportCategorie.getNom());


        //Figure 4
        System.out.println("FIGURE 4");
        depeches.get(400).afficher();
        System.out.println("score de la dépêche pour la catégorie "+SportCategorie.getNom()+" est "+SportCategorie.score(depeches.get(400)));

        //Figure 5
        Depeche depeche = depeches.get(399);
        System.out.println("FIGURE 5");
        System.out.println("La dépêche numéro " + depeche.getId() + " a");
        for (Categorie categorie : categories) {
            System.out.println("un score de " + categorie.score(depeche) + " pour la catégorie " + categorie.getNom());
        }






    }
}
