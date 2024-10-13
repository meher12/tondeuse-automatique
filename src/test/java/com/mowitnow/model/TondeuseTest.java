package com.mowitnow.model;


import com.mowitnow.exception.InvalidInstructionException;
import com.mowitnow.exception.PositionOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TondeuseTest {

    /**
     * Test de l'initialisation d'une tondeuse avec une position valide à l'intérieur de la grille.
     */
    @Test
    public void testTondeuseInitializationInsideGrille() {
        Grille grille = new Grille(5, 5);
        Position position = new Position(1, 2, Orientation.N);
        Tondeuse tondeuse = new Tondeuse(position, grille);

        // Vérification que la tondeuse a bien la position initiale spécifiée
        assertEquals(position, tondeuse.getPosition());
    }

    /**
     * Test de l'initialisation d'une tondeuse avec une position invalide en dehors de la grille.
     * Vérifie que l'exception PositionOutOfBoundsException est bien lancée.
     */
    @Test
    public void testTondeuseInitializationOutsideGrille() {
        Grille grille = new Grille(5, 5);
        Position position = new Position(6, 6, Orientation.N);

        // Vérification qu'une exception est lancée pour une position hors des limites
        Exception exception = assertThrows(PositionOutOfBoundsException.class, () -> {
            new Tondeuse(position, grille);
        });

        // Vérification de message d'erreur contient le texte attendu
        assertTrue(exception.getMessage().contains("Position initiale en dehors de la pelouse"));
    }

    /**
     * Test du comportement de la tondeuse lorsqu'elle tourne à droite.
     */
    @Test
    public void testTournerDroite() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(1, 2, Orientation.N), grille);

        // Tourne à droite depuis le Nord devrait être Est
        tondeuse.tournerDroite();
        assertEquals(Orientation.E, tondeuse.getPosition().getOrientation());

        // Tourne à droite depuis l'Est devrait être Sud
        tondeuse.tournerDroite();
        assertEquals(Orientation.S, tondeuse.getPosition().getOrientation());
    }

    /**
     * Test du comportement de la tondeuse lorsqu'elle tourne à gauche.
     */
    @Test
    public void testTournerGauche() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(1, 2, Orientation.N), grille);

        // Tourne à gauche depuis le Nord devrait être Ouest (W)
        tondeuse.tournerGauche();
        assertEquals(Orientation.W, tondeuse.getPosition().getOrientation());

        // Tourne à gauche depuis l'Ouest devrait être Sud
        tondeuse.tournerGauche();
        assertEquals(Orientation.S, tondeuse.getPosition().getOrientation());
    }

    /**
     * Test du déplacement de la tondeuse en direction du Nord, Vérifie que la position Y augmente
     */
    @Test
    public void testAvancerNorth() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(1, 2, Orientation.N), grille);

        // Avance en direction du Nord, la coordonnée Y devrait augmenter de 1
        tondeuse.avancer();
        assertEquals(1, tondeuse.getPosition().getX());
        assertEquals(3, tondeuse.getPosition().getY());
    }

    /**
     * Test du déplacement de la tondeuse en direction de l'Est, Vérifie que la position X augmente.
     */
    @Test
    public void testAvancerEast() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(1, 2, Orientation.E), grille);

        // Avance en direction de l'Est, la coordonnée X devrait augmenter de 1
        tondeuse.avancer();
        assertEquals(2, tondeuse.getPosition().getX());
        assertEquals(2, tondeuse.getPosition().getY());
    }

    /**
     * Test du déplacement de la tondeuse en direction du Sud, Vérifie que la position Y diminue.
     */
    @Test
    public void testAvancerSouth() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(1, 2, Orientation.S), grille);

        // Avance en direction du Sud, la coordonnée Y devrait diminuer de 1
        tondeuse.avancer();
        assertEquals(1, tondeuse.getPosition().getX());
        assertEquals(1, tondeuse.getPosition().getY());
    }

    /**
     * Test du déplacement de la tondeuse en direction de l'Ouest, Vérifie que la position X diminue.
     */
    @Test
    public void testAvancerWest() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(1, 2, Orientation.W), grille);

        // Avance en direction de l'Ouest, la coordonnée X devrait diminuer de 1
        tondeuse.avancer();
        assertEquals(0, tondeuse.getPosition().getX());
        assertEquals(2, tondeuse.getPosition().getY());
    }

    /**
     * Test du comportement de la tondeuse lorsqu'elle tente de sortir de la grille.
     * Vérifie qu'elle ne dépasse pas les limites (5x5).
     */
    @Test
    public void testAvancerOutOfBounds() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(5, 5, Orientation.N), grille);

        // Tente d'avancer hors des limites, la tondeuse ne doit pas bouger
        tondeuse.avancer();
        assertEquals(5, tondeuse.getPosition().getX());
        assertEquals(5, tondeuse.getPosition().getY());
    }

    /**
     * Test d'exécution d'instructions valides pour la tondeuse.
     * Vérifie que les déplacements et rotations sont effectués correctement.
     */
    @Test
    public void testExecuterInstructionsValid() {
        Grille grille = new Grille(5, 5);

        // Première tondeuse, instructions : tourner à gauche et avancer
        Position position1 = new Position(1, 2, Orientation.N);
        Tondeuse tondeuse1 = new Tondeuse(position1, grille);
        tondeuse1.executerInstructions("GAGAGAGAA");
        assertEquals("1 3 N", tondeuse1.getPosition().toString());

        // Deuxième tondeuse, instructions : avancer, tourner à droite
        Position position2 = new Position(3, 3, Orientation.E);
        Tondeuse tondeuse2 = new Tondeuse(position2, grille);
        tondeuse2.executerInstructions("AADAADADDA");
        assertEquals("5 1 E", tondeuse2.getPosition().toString());
    }

    /**
     * Test d'exécution d'instructions invalides.
     * Vérifie que l'exception InvalidInstructionException est lancée pour des instructions inconnues.
     */
    @Test
    public void testExecuterInstructionsInvalid() {
        Grille grille = new Grille(5, 5);
        Tondeuse tondeuse = new Tondeuse(new Position(0, 0, Orientation.N), grille);

        // Utilisation d'instructions invalides pour vérifier qu'une exception est lancée.
        Exception exception = assertThrows(InvalidInstructionException.class, () -> {
            tondeuse.executerInstructions("AGB");
        });

        // Vérifie que le message d'exception correspond à l'instruction invalide
        String expectedMessage = "Instruction invalide : B";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
