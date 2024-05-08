import java.util.InputMismatchException;

import java.util.Scanner;

/**
 * La classe MainP1 est le point d'entrée du programme pour gérer la communauté d'agglomération.
 */
public class MainP1 {

    /**
     * Fonction principale pour exécuter le programme.
     *
     * @param args Les arguments de la ligne de commande (non utilisés).
     */
	 public static void main(String[] args) {
	        // Utilisation d'un try-with-resources pour fermer automatiquement le scanner à la fin
	        try (Scanner scanner = new Scanner(System.in)) {
	            GestionCA gestionCA = new GestionCA();

	            // Étape 1: Ajout des villes à la communauté
	            try {
	                System.out.print("Entrez le nombre de villes : ");
	                int nombreDeVilles = scanner.nextInt();

	                for (int i = 0; i < nombreDeVilles; i++) {
	                    System.out.print("Nom de la ville " + (i + 1) + " : ");
	                    String nomVille = scanner.next();
	                    gestionCA.ajouterVille(nomVille);
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Erreur : Veuillez entrer un nombre entier pour le nombre de villes.");
	                return; // Arrête le programme en cas d'erreur
	            }

	            // Menu 1: Ajouter une route ou Fin
	            int choixMenu1;
	            do {
	                System.out.println("\nMenu 1 :");
	                System.out.println("1) Ajouter une route");
	                System.out.println("2) Fin");
	                System.out.print("Votre choix : ");

	                try {
	                    choixMenu1 = scanner.nextInt();

	                    switch (choixMenu1) {
	                        case 1:
	                            // Option 1: Ajouter une route
	                            System.out.print("Ville de départ : ");
	                            String villeDepart = scanner.next();
	                            System.out.print("Ville d'arrivée : ");
	                            String villeArrivee = scanner.next();
	                            gestionCA.ajouterRoute(villeDepart, villeArrivee);
	                            break;
	                        case 2:
	                            // Option 2: Fin du premier menu
	                            break;
	                        default:
	                            System.out.println("Choix invalide, veuillez réessayer.");
	                    }
	                } catch (InputMismatchException e) {
	                    System.out.println("Erreur : Veuillez entrer un nombre entier pour votre choix.");
	                    scanner.next(); // Vide le scanner pour éviter une boucle infinie
	                    choixMenu1 = 0; // Réinitialise le choix pour relancer le menu
	                }

	            } while (choixMenu1 != 2);

            // Menu 2: Ajouter une zone de recharge, Retirer une zone de recharge ou Fin
            int choixMenu2;
            do {
                System.out.println("\nMenu 2 :");
                System.out.println("1) Ajouter une zone de recharge");
                System.out.println("2) Retirer une zone de recharge");
                System.out.println("3) Fin");
                System.out.print("Votre choix : ");

                try {
                    choixMenu2 = scanner.nextInt();
                  
                    switch (choixMenu2) {
                        case 1:
                            // Option 1: Ajouter une zone de recharge
                            System.out.print("Ville où ajouter une zone de recharge : ");
                            String villeAjout = scanner.next();
                            gestionCA.ajouterZoneRecharge(villeAjout);
                            break;
                        case 2:
                            // Option 2: Retirer une zone de recharge
                            System.out.print("Ville où retirer une zone de recharge : ");
                            String villeRetrait = scanner.next();
                            gestionCA.retirerZoneRecharge(villeRetrait);
                            break;
                        case 3:
                            // Option 3: Fin du deuxième menu
                            break;
                        default:
                            System.out.println("Choix invalide, veuillez réessayer.");
                    }

                    // Affiche la liste des villes avec zone de recharge après chaque action
                    gestionCA.afficherVillesAvecZoneRecharge();

                } catch (Exception e) {
                    System.out.println("Erreur : Entrée invalide. Veuillez saisir un nombre.");
                    scanner.next(); // Vide le scanner pour éviter une boucle infinie
                    choixMenu2 = 0; // Réinitialise le choix pour relancer le menu
                }

            } while (choixMenu2 != 3);

            System.out.println("Programme terminé. Beslama <3 ");
        }
    }
}
