import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Categorie sport = new Categorie("sport");
        sport.initLexique("sport.txt");
        System.out.println("Lexique de la catégorie sport :"+ sport.getlexic());
        System.out.println("premier lexique " + sport.getlexic().get(0).getChaine());
    }
}
