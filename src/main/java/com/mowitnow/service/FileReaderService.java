package com.mowitnow.service;
import com.mowitnow.exception.InvalidInstructionException;
import com.mowitnow.model.Grille;
import com.mowitnow.model.Orientation;
import com.mowitnow.model.Position;
import com.mowitnow.model.Tondeuse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Service pour lire et parser le fichier d'entrée contenant les instructions pour les tondeuses.
 */

public class FileReaderService {
    /**
     * Lit les instructions depuis un fichier texte et retourne une liste de tondeuses avec leurs positions finales.
     * @param filePath Chemin du fichier d'entrée.
     * @return Liste de tondeuses après exécution des instructions.
     * @throws IOException Si une erreur survient lors de la lecture du fichier.
     */
    public List<Tondeuse> readInstructions(String filePath) throws IOException {

        List<Tondeuse> tondeuses = new ArrayList<>();
        Grille pelouse = null;


        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String ligne;

            // Lire la première ligne pour obtenir la taille de la pelouse
            if ((ligne = reader.readLine()) != null) {
                String[] dimensions = ligne.trim().split("\\s+");
                if (dimensions.length != 2) {
                    throw new IllegalArgumentException("La première ligne doit contenir exactement deux nombres pour la taille de la pelouse.");
                }
                int maxX = Integer.parseInt(dimensions[0]);
                int maxY = Integer.parseInt(dimensions[1]);
                pelouse = new Grille(maxX, maxY);
            } else {
                throw new IllegalArgumentException("Le fichier d'entrée est vide.");
            }

            /* Lire les informations des tondeuses
             Chaque tondeuse exécute intégralement sa série d'instructions avant de passer à la suivante:
              1. Les tondeuses sont traitées une par une dans la boucle while.
              2. Pour chaque tondeuse, on lit sa position initiale, puis ses instructions.
            */
            while ((ligne = reader.readLine()) != null) {
                // Lire la position initiale de la tondeuse
                String[] positionData = ligne.trim().split("\\s+");
                if (positionData.length != 3) {
                    throw new IllegalArgumentException("Position initiale invalide : " + ligne);
                }

                int x = Integer.parseInt(positionData[0]);
                int y = Integer.parseInt(positionData[1]);
                Orientation orientation;
                try {
                    orientation = Orientation.valueOf(positionData[2]);
                } catch (IllegalArgumentException e) {
                    throw new InvalidInstructionException("Orientation invalide : " + positionData[2]);
                }

                Position position = new Position(x, y, orientation);
                Tondeuse tondeuse = new Tondeuse(position, pelouse);

                // Lire la série d'instructions
                String instructions = reader.readLine();
                if (instructions == null || instructions.trim().isEmpty()) {
                    throw new IllegalArgumentException("Instructions manquantes pour la tondeuse à la position : " + position);
                }

                tondeuse.executerInstructions(instructions.trim());

                // Ajouter la tondeuse à la liste
                tondeuses.add(tondeuse);
            }
        }

        return tondeuses;
    }
}
