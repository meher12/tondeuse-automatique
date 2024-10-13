package com.mowitnow.model;

/**
 * Enumération des orientations possibles d'une tondeuse.
 */
public enum Orientation {
    N, E, S, W;

    /**
     * Tourne l'orientation de 90° à droite.
     * @return Nouvelle orientation après rotation à droite.
     */
    public Orientation tournerDroite() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }

    /**
     * Tourne l'orientation de 90° à gauche.
     * @return La Nouvelle orientation après rotation à gauche.
     */
    public Orientation tournerGauche() {
        return switch (this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }
}
