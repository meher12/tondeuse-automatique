package com.mowitnow.model;

/**
 * Représente la position d'une tondeuse sur la grille avec ses coordonnées (x, y) et son orientation.
 */
public class Position {

    private int x;
    private int y;
    private Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    // Getters et Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}
