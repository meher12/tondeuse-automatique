package com.mowitnow;

import com.mowitnow.exception.InvalidInstructionException;
import com.mowitnow.model.Position;
import com.mowitnow.model.Tondeuse;
import com.mowitnow.service.FileReaderService;
import com.mowitnow.utils.ExitCodes;

import java.io.IOException;
import java.util.List;


public class ApplicationTondeuse {

    public static void main(String[] args) {
        // Vérifier si le chemin du fichier est fourni en argument
        if (args.length != 1) {
            System.err.println("Aucun fichier fourni.");
            System.exit(ExitCodes.NO_FILE);
        }

        String filePath = args[0];
        FileReaderService fileReaderService = new FileReaderService();

        try {
            List<Tondeuse> tondeuses = fileReaderService.readInstructions(filePath);
            afficherResultats(tondeuses);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier d'entrée : " + e.getMessage());
            System.exit(ExitCodes.FILE_ERROR);
        } catch (IllegalArgumentException | InvalidInstructionException e) {
            System.err.println("Erreur dans les données d'entrée : " + e.getMessage());
            System.exit(ExitCodes.DATA_ERROR);
        }
    }

    private static void afficherResultats(List<Tondeuse> tondeuses) {
        System.out.println("Résultats de la simulation des tondeuses :");
        System.out.println("========================================");

        for (int i = 0; i < tondeuses.size(); i++) {
            Tondeuse tondeuse = tondeuses.get(i);
            Position position = tondeuse.getPosition();
            System.out.printf("Tondeuse %d :%n", i + 1);
            System.out.printf("  Position finale : (%d, %d)%n", position.getX(), position.getY());
            System.out.printf("  Orientation     : %s%n", position.getOrientation());
            System.out.println("----------------------------------------");
        }

        System.out.printf("Total des tondeuses traitées : %d%n", tondeuses.size());
    }
}