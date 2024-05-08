import java.util.List;

/**
 * La classe GestionCA gère les opérations sur la communauté d'agglomération.
 */
public class GestionCA {
    private CommAgglomeration communaute;  // La communauté d'agglomération à gérer

    /**
     * Constructeur de la classe GestionCA.
     * Initialise une nouvelle communauté d'agglomération.
     */
    public GestionCA(CommAgglomeration communaute) {
        this.communaute = communaute;
    }
    public GestionCA() {
        this.communaute = new CommAgglomeration();
    }

    /**
     * Ajoute une ville à la communauté.
     *
     * @param nomVille Le nom de la ville à ajouter.
     */
    public void ajouterVille(String nomVille) {
        communaute.ajouterVille(nomVille);
        System.out.println("Ville " + nomVille + " ajoutée avec succès !");
    }

    /**
     * Ajoute une route entre deux villes.
     *
     * @param nomVille1 Le nom de la première ville.
     * @param nomVille2 Le nom de la deuxième ville.
     */
    public void ajouterRoute(String nomVille1, String nomVille2) {
        List<Ville> villesCommunaute = communaute.getVilles();

        // Vérifier si les deux villes existent dans la CommunauteAgglomeration
        boolean ville1Existe = villesCommunaute.stream().anyMatch(ville -> ville.getNom().equals(nomVille1));
        boolean ville2Existe = villesCommunaute.stream().anyMatch(ville -> ville.getNom().equals(nomVille2));

        if (ville1Existe && ville2Existe) {
            try {
                communaute.ajouterRoute(nomVille1, nomVille2);
                System.out.println("Route ajoutée avec succès entre " + nomVille1 + " et " + nomVille2);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        } else {
            System.out.println("Erreur : Les villes spécifiées ne font pas partie de la Communaute d'Agglomeration.");
        }
    }


    /**
     * Obtient la communauté d'agglomération.
     *
     * @return La communauté d'agglomération.
     */
    public CommAgglomeration getCommunaute() {
        return communaute;
    }

    /**
     * Ajoute une zone de recharge à une ville si elle n'en a pas déjà.
     *
     * @param nomVille Le nom de la ville où ajouter la zone de recharge.
     */
    public void ajouterZoneRecharge(String nomVille) {
        List<Ville> villesCommunaute = communaute.getVilles();

        // Vérifier si la ville existe dans la CommunauteAgglomeration
        if (villesCommunaute.stream().anyMatch(ville -> ville.getNom().equals(nomVille))) {
            Ville ville = communaute.getVille(nomVille);

            if (ville != null && !ville.aZoneRecharge()) {
                ville.ajouterZoneRecharge();
                System.out.println("Zone de recharge ajoutée à la ville " + nomVille);
            } else {
                System.out.println("La ville " + nomVille + " a déjà une zone de recharge.");
            }
        } else {
            System.out.println("La ville " + nomVille + " n'existe pas dans la Communaute d'Agglomeration.");
        }
    }


    /**
     * Retire une zone de recharge d'une ville si possible.
     *
     * @param nomVille Le nom de la ville où retirer la zone de recharge.
     */
    public void retirerZoneRecharge(String nomVille) {
        List<Ville> villesCommunaute = communaute.getVilles();

        // Vérifier si la ville existe dans la CommunauteAgglomeration
        if (villesCommunaute.stream().anyMatch(ville -> ville.getNom().equals(nomVille))) {
            Ville ville = communaute.getVille(nomVille);
            
            if (ville != null && ville.aZoneRecharge()) {
                if (peutRetirerZoneRecharge(ville)) {
                    ville.supprimerZoneRecharge();
                    System.out.println("Zone de recharge retirée de la ville " + nomVille);
                } else {
                    System.out.println("Impossible de retirer la zone de recharge de la ville " + nomVille +
                                       ". Au moins une ville ou ses voisins se retrouveraient sans zone de recharge.");
                }
            } else {
                System.out.println("La ville " + nomVille + " n'a pas de zone de recharge à retirer.");
            }
        } else {
            System.out.println("La ville " + nomVille + " n'existe pas dans la Communaute d'Agglomeration.");
        }
    }


    /**
     * Vérifie si la zone de recharge peut être retirée d'une ville.
     *
     * @param ville La ville à vérifier.
     * @return True si la zone de recharge peut être retirée, sinon False.
     */
    private boolean peutRetirerZoneRecharge(Ville ville) {
        if (ville.aZoneRecharge()) {
            // Vérifie si l'un des voisins possède une zone de recharge
            for (Ville voisin : ville.getVoisins()) {
                if (voisin.aZoneRecharge()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Affiche la liste des villes avec une zone de recharge.
     */
    public void afficherVillesAvecZoneRecharge() {
        System.out.println("\nListe des villes avec zone de recharge :");
        for (Ville ville : communaute.getVilles()) {
            if (ville.aZoneRecharge()) {
                System.out.println("- " + ville.getNom());
            }
        }
    }

}