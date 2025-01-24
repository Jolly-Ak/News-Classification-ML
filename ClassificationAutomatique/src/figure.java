import java.util.ArrayList;
import java.util.Scanner;

public class figure {
    public static void main(String[] args) {
        Scanner lecteur = new Scanner(System.in);

        Categorie SportCategorie = new Categorie("sport");

        ArrayList<Depeche> depeches = Classification.lectureDepeches("depeches.txt");

        SportCategorie.initLexique("lexique/SPORT.txt");

        //Figure 2
        for (int i = 0; i < 5; i++) {
            SportCategorie.getLexique().get(i).afficher();

        }
        lecteur.nextLine();

        //Figure 3
        System.out.println("\nFIGURE 3");

        //demander un mot à l'utilisateur
        System.out.printf("Entrez un mot : ");
        String mot = lecteur.nextLine();
        System.out.println("poids de "+mot+" est " + UtilitairePaireChaineEntier.entierPourChaine(SportCategorie.getLexique(), mot)+" dans la catégorie "+ SportCategorie.getNom());

        lecteur.nextLine();

        //Figure 4
        System.out.println("FIGURE 4");
        depeches.get(400).afficher();
        System.out.println("score de la dépêche pour la catégorie "+SportCategorie.getNom()+" est "+SportCategorie.score(depeches.get(400)));
        lecteur.nextLine();

        //liste des categories
        System.out.println("création de la liste des categories ");
        String[] categoriesNames = {"sport", "politique", "economie", "culture", "sciences"};
        ArrayList<Categorie> categories = new ArrayList<Categorie>();

        System.out.println("Apelle de la methode initLexique pour chaque catégorie et l'ajouter a la liste categories");
        for (String name : categoriesNames) {
            Categorie categorie = new Categorie(name);
            categorie.initLexique("./lexique/" + name + ".txt");
            categories.add(categorie);
        }
        lecteur.nextLine();

        //Figure 5
        Depeche depeche = depeches.get(399);
        System.out.println("FIGURE 5");
        System.out.println("La dépêche numéro " + depeche.getId() + " a");
        for (Categorie categorie : categories) {
            System.out.println("un score de " + categorie.score(depeche) + " pour la catégorie " + categorie.getNom());
        }
        lecteur.nextLine();

        //Figure calssification








    }
}
