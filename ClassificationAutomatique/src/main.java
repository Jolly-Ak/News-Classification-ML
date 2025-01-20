public class main {
    public static void main(String[] args) {
        Categorie sport = new Categorie("sport");
        sport.initLexique("sport.txt");
        System.out.println(sport.getLexique());
    }
}
