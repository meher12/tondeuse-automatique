package com.mowitnow.model;

/**
 * Représente la pelouse avec ses dimensions maximales
 */
public class Grille {
    private final int maxX;
    private final int maxY;

    public Grille(int maxX, int maxY) {
        if (maxX < 0 || maxY < 0) {
            throw new IllegalArgumentException("Les dimensions de la grille doivent être non négatives.");
        }
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Vérifie si les coordonnées (x, y) sont à l'intérieur de la grille.
     * @param x
     * @param y
     * @return Vrai si (x, y) est à l'intérieur, sinon faux.
     */
    public boolean isInside(int x, int y) {
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
