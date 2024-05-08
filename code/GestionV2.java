import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Set;



public class GestionV2 {


    /**
     * Charge une communauté d'agglomération à partir d'un fichier.
     * Utilise la méthode traiterLigne pour interpréter chaque ligne du fichier.
     *
     * @param cheminFichier Le chemin du fichier à charger.
     * @param communaute L'objet CommAgglomeration dans lequel la communauté est chargée.
     */
    public static void chargerCommunauteDepuisFichier(String cheminFichier, CommAgglomeration communaute) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;

            while ((ligne = br.readLine()) != null) {
                traiterLigne(ligne, communaute);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Traite une ligne du fichier et effectue les actions appropriées dans la communauté d'agglomération.
     *
     * @param ligne La ligne à traiter.
     * @param communaute L'objet CommAgglomeration sur lequel les actions sont effectuées.
     */
    public static void traiterLigne(String ligne, CommAgglomeration communaute) {
        String[] mots = ligne.split("[()]");

        if (mots.length > 1) {
            String typeDeclaration = mots[0];
            String contenuDeclaration = mots[1].trim();

            switch (typeDeclaration) {
                case "ville":
                    communaute.ajouterVille(contenuDeclaration);
                    break;
                case "route":
                    String[] villes = contenuDeclaration.split(",");
                    communaute.ajouterRoute(villes[0].trim(), villes[1].trim());
                    break;
                case "recharge":
                    if (!contenuDeclaration.isEmpty()) {
                        Ville ville = communaute.getVille(contenuDeclaration);
                        if (ville != null) {
                            ville.ajouterZoneRecharge();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
    

    /**
     * Collecte toutes les villes sans zone de recharge.
     *
     * @param communaute La communauté d'agglomération.
     * @return Ensemble des villes sans zone de recharge.
     */
    private static Set<Ville> VillesNonChargees(CommAgglomeration communaute) {
        // Collecter toutes les villes sans zone de recharge
        Set<Ville> VilleSR = communaute.getVilles().stream()
                .filter(ville -> !ville.aZoneRecharge())
                .collect(Collectors.toSet());

        // Collecter toutes les villes voisines des villes avec une zone de recharge
        Set<Ville> VilleAR = communaute.getVilles().stream()
                .filter(Ville::aZoneRecharge)
                .flatMap(ville -> ville.getVoisins().stream())
                .collect(Collectors.toSet());

        // Retirer les villes voisines des villes avec une zone de recharge de l'ensemble VilleSR
        VilleSR.removeAll(VilleAR);

        return VilleSR;
    }


    /**
     * Vérifie si des villes sont non chargées et imprime des messages d'erreur si nécessaire.
     *
     * @param communaute La communauté d'agglomération.
     * @return true si des erreurs sont détectées, sinon false.
     */
    private static boolean estVilleNonChargee(CommAgglomeration communaute) {
        Set<Ville> villesNonChargees = VillesNonChargees(communaute);

        if (communaute.getVilles().stream().noneMatch(Ville::aZoneRecharge)) {
            System.out.println("Aucune ville n'est chargée!");
            return true;       
        }else if (!villesNonChargees.isEmpty()) {
            System.out.println("Contraintes d'accessibilité non respectées par ville(s) :");
            villesNonChargees.forEach(villeNonChargee -> System.out.println("- " + villeNonChargee.getNom()));
            return true;
        } else {
            return false;
        }
    }


    /**
     * Récupère les villes sans zone de recharge.
     *
     * @param communaute La communauté d'agglomération.
     * @return Ensemble des villes sans zone de recharge.
     */    
    private static Set<Ville> getVillesSansZoneRecharge(CommAgglomeration communaute) {
        return communaute.getVilles().stream()
                .filter(ville -> !ville.aZoneRecharge())
                .collect(Collectors.toSet());
    }
    

    /**
     * Applique une solution naïve en ajoutant des zones de recharge aux villes non chargées.
     *
     * @param communaute La communauté d'agglomération.
     */
    private static void SolutionNaif(CommAgglomeration communaute) {
    	if (estVilleNonChargee(communaute)) {
    		System.out.println("Application de la solution naif...");
    		Set<Ville> villesSansZoneRecharge = getVillesSansZoneRecharge(communaute);
    		for (Ville ville : villesSansZoneRecharge) {
    			ville.ajouterZoneRecharge();
    		}
    		communaute.afficherCommunaute();
    		System.out.println("Solution naif appliquée : toutes les villes ont maintenant une zone de recharge.");
    		// Utiliser la méthode score pour calculer et afficher le score après l'application de la solution
    		int score = score(communaute);
    		System.out.println("Score solution naive: " + score);
    		}
    }
    

    /**
     * Résout manuellement en appliquant la solution naïve, puis en permettant à l'utilisateur
     * d'ajouter ou de retirer des zones de recharge.
     *
     * @param communaute La communauté d'agglomération.
     * @param scanner    Scanner pour lire l'entrée utilisateur.
     */
    public static void resoudreManuellement(CommAgglomeration communaute, Scanner scanner) {
        SolutionNaif(communaute);

        // Continuez avec la résolution manuelle
        int choixMenu = 0;

        do {
            System.out.println("\nMenu de résolution manuelle :");
            System.out.println("1) Ajouter une zone de recharge");
            System.out.println("2) Retirer une zone de recharge");
            System.out.println("3) Afficher les villes avec zone de recharge");
            System.out.println("4) Fin de la résolution manuelle");
            System.out.print("Votre choix : ");

            if (scanner.hasNextInt()) {
                choixMenu = scanner.nextInt();
                scanner.nextLine(); // Pour consommer la nouvelle ligne restante

                switch (choixMenu) {
                    case 1:
                        do {
                            System.out.print("Nom de la ville où ajouter une zone de recharge : ");
                            String nomVilleAjout = scanner.nextLine();

                            Ville villeAjout = communaute.getVilleIgnoreCase(nomVilleAjout);

                            if (villeAjout != null) {
                                communaute.ajouterZoneRecharge(villeAjout.getNom());
                                break; // Sortir de la boucle si la ville est trouvée
                            } else {
                                System.out.println("Erreur : la ville spécifiée n'existe pas dans la communauté.");
                                System.out.println("Veuillez saisir une ville valide.");
                            }
                        } while (true); // Répéter jusqu'à ce qu'une ville valide soit saisie
                        break;

                    case 2:
                        do {
                            System.out.print("Nom de la ville où retirer une zone de recharge : ");
                            String nomVilleRetrait = scanner.nextLine();

                            Ville villeRetrait = communaute.getVilleIgnoreCase(nomVilleRetrait);

                            if (villeRetrait != null) {
                                communaute.retirerZoneRecharge(villeRetrait.getNom());
                                break; // Sortir de la boucle si la ville est trouvée
                            } else {
                                System.out.println("Erreur : la ville spécifiée n'existe pas dans la communauté.");
                                System.out.println("Veuillez saisir une ville valide.");
                            }
                        } while (true); // Répéter jusqu'à ce qu'une ville valide soit saisie
                        break;
                    case 3:
                        communaute.afficherVillesAvecZoneRecharge();
                        break;
                    case 4:
                        System.out.println("Fin de la résolution manuelle.");
                        break;
                    default:
                        System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
                }
            } else {
                System.out.println("Veuillez entrer un entier valide entre 1 a 4 !");
                scanner.nextLine(); // Pour consommer la nouvelle ligne restante
            }
        } while (choixMenu != 4);
    }
    
    /**
     * Résout automatiquement en appliquant d'abord la solution naïve, puis l'algorithme optimal.
     *
     * @param communaute La communauté d'agglomération.
     */
    public static void resoudreAutomatiquement(CommAgglomeration communaute) {
    	SolutionNaif(communaute);
    	algorithmeOptimale(communaute);
    }
    

    /**
     * Algorithme optimal pour ajouter ou retirer des zones de recharge de manière stratégique.
     *
     * @param communaute La communauté d'agglomération.
     */
    public static void algorithmeOptimale(CommAgglomeration communaute) {
        // Étape 1: Lancer SolutionNaif
        SolutionNaif(communaute);

        // Étape 2: Lancer algorithmeOptimale et ajouter des zones de recharges dans toutes les villes
        for (Ville ville : communaute.getVilles()) {
            communaute.ajouterZoneRecharge(ville.getNom());
        }

        // Étape 3: Classer les villes selon leur nombre de voisins croissant dans un set VilleC
        Set<Ville> villesCroissantes = communaute.getVilles().stream()
                .sorted(Comparator.comparingInt(ville -> ville.getVoisins().size()))
                .collect(Collectors.toSet());

        // Étape 4: Supprimer une zone de recharge aux villes en parcourant VilleC
        int iterationsMax = 4;
        int scoreCourant = score(communaute);

        for (int i = 0; i < iterationsMax; i++) {
            for (Ville ville : villesCroissantes) {
                if (ville.aZoneRecharge()) {
                    communaute.retirerZoneRecharge(ville.getNom());
                    int nouveauScore = score(communaute);

                    if (nouveauScore < scoreCourant) {
                        scoreCourant = nouveauScore;
                    } else {
                        // Si le score ne s'améliore pas, rétablir la zone de recharge retirée
                        communaute.ajouterZoneRecharge(ville.getNom());
                    }
                }
            }
        }
        System.out.println("Score solution inverse optimale: " + scoreCourant);
    }
	

    /**
     * Calcule et retourne le score de la communauté d'agglomération.
     *
     * @param communaute La communauté d'agglomération.
     * @return Le score, représentant le nombre de villes avec une zone de recharge.
     */
    public static int score(CommAgglomeration communaute) {
        return (int) communaute.getVilles().stream().filter(Ville::aZoneRecharge).count();
    }


    /**
     * Sauvegarde la communauté d'agglomération dans un fichier.
     *
     * @param communaute La communauté d'agglomération à sauvegarder.
     */

    public static void sauvegarder(CommAgglomeration communaute) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le chemin du fichier de sauvegarde : ");
        String cheminFichier = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Ville ville : communaute.getVilles()) {
                writer.write("ville(" + ville.getNom() + ").");
                writer.newLine();
            }

            for (Ville ville : communaute.getVilles()) {
                for (Ville voisin : ville.getVoisins()) {
                    writer.write("route(" + ville.getNom() + "," + voisin.getNom() + ").");
                    writer.newLine();
                }
            }

            for (Ville ville : communaute.getVilles()) {
                if (ville.aZoneRecharge()) {
                    writer.write("recharge(" + ville.getNom() + ").");
                    writer.newLine();
                }
            }

            System.out.println("Sauvegarde réussie dans le fichier : " + cheminFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

}
