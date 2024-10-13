package com.mowitnow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    /**
     * Test de l'initialisation de la classe Position avec des coordonnées (x, y) et une orientation.
     */
    @Test
    public void testPositionInitialization() {
        Position position = new Position(1, 2, Orientation.N);
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(Orientation.N, position.getOrientation());
    }

    /**
     * Test des setters pour modifier les valeurs d'une Position.
     */
    @Test
    public void testSetters() {
        // Création d'une position avec les valeurs initiales 0, 0 et E
        Position position = new Position(0, 0, Orientation.E);

        // Utilisation des setters pour modifier les valeurs
        position.setX(3);
        position.setY(4);
        position.setOrientation(Orientation.S);

        // Vérification que les modifications ont bien pris effet
        assertEquals(3, position.getX());
        assertEquals(4, position.getY());
        assertEquals(Orientation.S, position.getOrientation());
    }
}
