package com.mowitnow.service;

import com.mowitnow.model.Orientation;
import com.mowitnow.model.Position;
import com.mowitnow.model.Tondeuse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Cette classe contient des tests unitaires pour vérifier le bon fonctionnement
 * de la méthode readInstructions de FileReaderService.
 */
public class FileReaderServiceTest {

    /**
     * Teste la lecture d'instructions à partir d'un fichier.
     * @throws IOException Si une erreur de lecture du fichier se produit
     */
    @Test
    public void testReadInstructionsValid() throws IOException {
        FileReaderService fileReaderService = new FileReaderService();
        String filePath = "src/test/resources/instructions_test.txt";
        List<Tondeuse> tondeuses = fileReaderService.readInstructions(filePath);

        assertEquals(2, tondeuses.size());

        // Vérifie que la position de la première tondeuse ne correspond pas à la valeur attendue
        Tondeuse t1 = tondeuses.get(0);
        Position expectedPosition1 = new Position(1,2, Orientation.N);
        assertNotEquals(expectedPosition1, t1.getPosition());

        // Vérifier la deuxième tondeuse
        Tondeuse t2 = tondeuses.get(1);
        Position expectedPosition2 = new Position(4, 3, Orientation.E);
        assertNotEquals(expectedPosition2, t2.getPosition());
    }

    /**
     * Teste la lecture d'instructions invalides à partir d'un fichier.
     */
    @Test
    public void testReadInstructionsInvalid() {
        FileReaderService fileReaderService = new FileReaderService();
        String filePath = "src/test/resources/instructions_invalid.txt";

        // Vérifie qu'une IllegalArgumentException est levée
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fileReaderService.readInstructions(filePath);
        });

        // Vérifie que le message d'erreur contient la chaîne attendue
        String expectedMessage = "Position initiale invalide";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
