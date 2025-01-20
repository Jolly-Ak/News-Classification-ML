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

    }
    public String getChaine() {
        return chaine;

    }
    public int getEntier() {
        return entier;

    }
    public String setChaine(String chaine){
        return chaine;
    }
    public int setEntier(int entier){
        return entier;
    }
    public void afficher(){
        System.out.println(chaine + " : " + entier);
    }
}



