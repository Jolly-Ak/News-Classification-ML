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
                System.out.println("Chaine créée : " + this.chaine + " : " + this.entier);
    }
    public int getEntier() {
        return entier;
    }
    public String getChaine(){
        return chaine;
    }
    public void setChaine(String chaine){
        this.chaine = chaine;

    }
    public void setEntier(int entier){
        this.entier = entier;
    }
    public void afficher(){
        System.out.println(chaine + " : " + entier);
    }
/*
    public static int entierPourChaine(ArrayList<PaireChaineEntier>
                                               listePaires, String chaine){
        for (PaireChaineEntier paire : listePaires){




    }

*/

}



