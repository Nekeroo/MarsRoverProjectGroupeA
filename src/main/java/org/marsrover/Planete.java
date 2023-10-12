package org.marsrover;

public class Planete {

    private int longueur;

    private int largeur;

    public Planete(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }
}
