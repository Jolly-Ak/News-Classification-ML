import java.util.ArrayList;

public class UtilitairePaireChaineEntier {


    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        return 0;

    }

    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        for (int i = 0; i <listePaires.size() ; i++) {
            if (listePaires.get(i).getChaine().equals(chaine)){
                return listePaires.get(i).getEntier();
            }

        }
        return -1;
    }

    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        //qui retourne la chaine associé au plus grand entier de listePaires
        int Suv = 0;
        for (int i = 0; i < listePaires.size(); i++) {
            if (listePaires.get(i).getEntier() > listePaires.get(Suv).getEntier()){
                Suv = i;
            }




        }

        return listePaires.get(Suv).getChaine();
    }


    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        return 0;
    }

}
