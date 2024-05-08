import java.io.File;
import java.util.Scanner;

/**
 * La classe MainP2 est le point d'entrée du programme pour gérer la communauté d'agglomération.
 */

public class MainP2 {

    /**
     * La méthode principale qui est le point d'entrée du programme.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filePath;

        do {
            System.out.print("Entrez le chemin du fichier texte représentant la communauté d'agglomération : ");
            filePath = sc.nextLine();
            if (!isValidFilePath(filePath)) {
                System.out.println("Chemin invalide. Veuillez réessayer.");
            }

        } while (!isValidFilePath(filePath));
        System.out.println("Chemin du fichier valide : " + filePath);

        CommAgglomeration communaute = new CommAgglomeration();

        // Charger la communauté d'agglomération depuis le fichier
        GestionV2.chargerCommunauteDepuisFichier(filePath, communaute);
        // Afficher la communauté après le chargement
        communaute.afficherCommunaute();

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            afficherMenu();

            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne restante

                switch (choix) {
                    case 1:
                        GestionV2.resoudreManuellement(communaute, scanner);
                        break;
                    case 2:
                        GestionV2.resoudreAutomatiquement(communaute);
                        break;
                    case 3:
                        GestionV2.sauvegarder(communaute);
                        break;
                    case 4:
                        System.out.println("Programme terminé.");
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
                }
            } else {
                System.out.println("Veuillez entrer un entier valide.");
                scanner.nextLine(); // Pour consommer la nouvelle ligne restante
            }
        }

        sc.close();
    }

    /**
     * Affiche le menu des options du programme.
     */
    private static void afficherMenu() {
        System.out.println("\nMenu :");
        System.out.println("1) Résoudre manuellement");
        System.out.println("2) Résoudre automatiquement");
        System.out.println("3) Sauvegarder");
        System.out.println("4) Fin");
        System.out.print("Choix : ");
    }


    /**
     * Vérifie si un chemin de fichier est valide.
     *
     * @param filePath Le chemin du fichier à vérifier.
     * @return true si le chemin est valide, false sinon.
     */
    private static boolean isValidFilePath(String filePath) {
        try {
            // Vérifiez si le fichier existe
            File file = new File(filePath);
            return file.exists() && file.isFile();
        } catch (Exception e) {
            return false;
        }
    }

}