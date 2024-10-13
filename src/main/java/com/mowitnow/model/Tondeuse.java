package com.mowitnow.model;

import com.mowitnow.exception.InvalidInstructionException;
import com.mowitnow.exception.PositionOutOfBoundsException;

/**
 * Représente une tondeuse avec sa position sur la grille et ses méthodes de déplacement.
 */
public class Tondeuse {
    private Position position;
    private final Grille pelouse;

    public Tondeuse(Position position, Grille pelouse) {
        if (pelouse == null) {
            throw new IllegalArgumentException("La pelouse ne peut pas être null.");
        }
        this.pelouse = pelouse;
        if (!pelouse.isInside(position.getX(), position.getY())) {
            throw new PositionOutOfBoundsException("Position initiale en dehors de la pelouse : " + position);
        }
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Tourne la tondeuse de 90 degrés à droite.
     */
    public void tournerDroite() {
        position.setOrientation(position.getOrientation().tournerDroite());
    }

    /**
     * Tourne la tondeuse de 90 degrés à gauche.
     */
    public void tournerGauche() {
        position.setOrientation(position.getOrientation().tournerGauche());
    }

    /**
     * Fait avancer la tondeuse d'une case dans la direction de son orientation actuelle.
     * Si le mouvement est possible (dans les limites de la pelouse), la position est mise à jour.
     * Sinon, la tondeuse reste sur place.
     */
    public void avancer() {
        // Récupère les coordonnées actuelles de la tondeuse
        int x = position.getX();
        int y = position.getY();

        // Calcule la nouvelle position en fonction de l'orientation
        switch (position.getOrientation()) {
            case N -> y++;
            case E -> x++;
            case S -> y--;
            case W -> x--;
        }

        // Vérifie si la nouvelle position est à l'intérieur de la pelouse
        if (pelouse.isInside(x, y)) {
            // Si oui, met à jour la position de la tondeuse
            position.setX(x);
            position.setY(y);
        }
        // Si la nouvelle position est en dehors de la pelouse,
        // la tondeuse ne bouge pas et conserve son orientation actuelle
    }

    /**
     * Exécute une série d'instructions pour déplacer la tondeuse.
     * @param instructions Chaîne d'instructions (D, G, A)
     */
    public void executerInstructions(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'D' -> tournerDroite();
                case 'G' -> tournerGauche();
                case 'A' -> avancer();
                default -> throw new InvalidInstructionException("Instruction invalide : " + instruction);
            }
        }
    }

    @Override
    public String toString() {
        return "Tondeuse {position=" + position.toString() + "}";
    }
}
