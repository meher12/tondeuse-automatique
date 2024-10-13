package com.mowitnow.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrientationTest {

    @Test
    public void testTournerDroite() {
        assertEquals(Orientation.E, Orientation.N.tournerDroite());
        assertEquals(Orientation.S, Orientation.E.tournerDroite());
        assertEquals(Orientation.W, Orientation.S.tournerDroite());
        assertEquals(Orientation.N, Orientation.W.tournerDroite());
    }

    @Test
    public void testTournerGauche() {
        assertEquals(Orientation.W, Orientation.N.tournerGauche());
        assertEquals(Orientation.S, Orientation.W.tournerGauche());
        assertEquals(Orientation.E, Orientation.S.tournerGauche());
        assertEquals(Orientation.N, Orientation.E.tournerGauche());
    }
}
