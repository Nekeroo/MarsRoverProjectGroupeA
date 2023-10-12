package org.marsrover;

public class Planete {

    private int longueur;

    private int largeur;

    private String name;

    public Planete(int longueur, int largeur, String name) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.name = name;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public String getName() {
        return name;
    }
}
