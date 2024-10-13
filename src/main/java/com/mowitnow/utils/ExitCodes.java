package com.mowitnow.utils;

/**
 * Classe utilitaire contenant les constantes des codes de sortie de l'application.
 */
public final class ExitCodes {
    // Code de sortie indiquant que l'application s'est terminée avec succès.
    public static final int SUCCESS = 0;

    // Code de sortie indiquant qu'aucun fichier n'a été fourni en argument.
    public static final int NO_FILE = 1;

    //Code de sortie indiquant une erreur lors de la lecture du fichier.
    public static final int FILE_ERROR = 2;

    //Code de sortie indiquant une erreur dans les données d'entrée.
    public static final int DATA_ERROR = 3;

}
