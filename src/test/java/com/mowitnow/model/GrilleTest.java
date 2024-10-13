package com.mowitnow.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrilleTest {

    /**
     *  Test de l'initialisation de la grille avec des valeurs valides
     */
    @Test
    public void testGrilleInitialization() {
        Grille grille = new Grille(5, 5);

        // Vérification que les dimensions sont bien initialisées à 5x5
        assertEquals(5, grille.getMaxX());
        assertEquals(5, grille.getMaxY());
    }

    /**
     * Test de la méthode isInside() pour vérifier si des coordonnées données
     * sont bien à l'intérieur des limites définies de la grille.
     */
    @Test
    public void testIsInside() {
        Grille grille = new Grille(5, 5);

        assertTrue(grille.isInside(0, 0));
        assertTrue(grille.isInside(5, 5));
        assertFalse(grille.isInside(-1, 0));
        assertFalse(grille.isInside(0, -1));
        assertFalse(grille.isInside(6, 5));
        assertFalse(grille.isInside(5, 6));
    }

    // Test de la gestion d'une initialisation invalide de la grille
    @Test
    public void testInvalidGrilleInitialization() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Grille(-1, 5);
        });
        assertTrue(exception.getMessage().contains("doivent être non négatives"));
    }
}

