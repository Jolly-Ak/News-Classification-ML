import java.util.ArrayList;
import java.util.Arrays;

public class Depeche {

    private String id;
    private String date;
    private String categorie;
    private String contenu;
    private ArrayList<String> mots;


    public Depeche(String id, String date, String categorie, String contenu) {
        this.id = id;
        this.date = date;
        this.categorie = categorie;
        this.contenu = contenu;
        this.mots = decoupeEnMots(contenu);
    }


    private ArrayList<String> decoupeEnMots(String contenu) {
        String chaine = contenu.toLowerCase();
        ArrayList<Character> caracteresASupprimer = new ArrayList<>(Arrays.asList('\n', '-', '\'', '.', ',', '\"', '(', ')', ':', ';', '?', '!', '«', '»', '’', '…', '—', '“', '”', '‘', '’', '–', '°', '€'));
        ArrayList<String> complementASuprimer = new ArrayList<>(Arrays.asList("le", "la", "les", "un", "une", "des", "du", "de", "d'", "l'", "au", "me", "ma", "mes","nos"));


        for (char c : caracteresASupprimer) {
            chaine = chaine.replace(c, ' ');
        }

        for(String c : complementASuprimer){
            chaine = chaine.replace(c, "");
        }

        String[] tabchaine = chaine.split(" ");
        ArrayList<String> resultat = new ArrayList<String>();
        for (int i = 0; i < tabchaine.length; i++) {
            if (!tabchaine[i].equals("")) {
                //ne pas lajouter si c'est une lettre toute seule
                if (tabchaine[i].length() > 1){
                    resultat.add(tabchaine[i]);
                }

            }
        }
        return resultat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public ArrayList<String> getMots() {
        return mots;
    }

    public void setMots(ArrayList<String> mots) {
        this.mots = mots;
    }

    public void afficher() {
        System.out.println("---------------------");
        System.out.println("depêche " + id);
        System.out.println("date:" + date);
        System.out.println("catégorie:" + categorie);
        System.out.println(contenu);
        System.out.println();
        System.out.println("---------------------");
    }

}

