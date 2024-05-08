import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * La classe CommAgglomeration représente une communauté d'agglomération avec
 * ses villes et routes.
 */
public class CommAgglomeration implements Cloneable {
    private GestionCA gestionCA;
    private Map<String, Ville> villes; // Cartographie des villes par leur nom
    private Map<String, Route> routes; // Champ pour stocker les routes

    /**
     * Constructeur de la classe CommAgglomeration.
     * Initialise la cartographie des villes.
     */
    public CommAgglomeration() {
        this.villes = new HashMap<>();
        this.routes = new HashMap<>();
        this.gestionCA = new GestionCA(this);
    }

    public CommAgglomeration(GestionCA gestionCA) {
        this.gestionCA = gestionCA;
        this.villes = new HashMap<>();
    }

    /**
     * Ajoute une ville à la communauté avec une zone de recharge.
     *
     * @param nom Le nom de la ville à ajouter.
     */
    public void ajouterVille(String nom) {
        villes.putIfAbsent(nom, new Ville(nom, false)); // Ajoute une ville avec une zone de recharge
    }

    /**
     * Obtient une instance de la ville à partir de son nom.
     *
     * @param nomVille Le nom de la ville à obtenir.
     * @return L'instance de la ville correspondant au nom, ou null si la ville
     *         n'existe pas.
     */
    public Ville getVille(String nomVille) {
        if (nomVille == null || nomVille.isEmpty()) {
            throw new IllegalArgumentException("Le nom de la ville ne peut pas être nul ou vide");
        }
        return villes.get(nomVille);
    }

    /**
     * Ajoute une route entre deux villes.
     *
     * @param nomVille1 Le nom de la première ville.
     * @param nomVille2 Le nom de la deuxième ville.
     */
    public void ajouterRoute(String nomVille1, String nomVille2) {
        
        Ville ville1 = getVille(nomVille1);
        Ville ville2 = getVille(nomVille2);

        if (ville1 != null && ville2 != null) {
        	Route nouvelleRoute = new Route(ville1.getNom(), ville2.getNom());
        	if (!routes.containsValue(nouvelleRoute)) {
                ville1.ajouterVoisin(ville2);
                ville2.ajouterVoisin(ville1);
                routes.put(nouvelleRoute.getNom(), nouvelleRoute);
            }
        }
    }
    
    /**
     * Retourne la ville correspondant au nom (ignorant la casse) spécifié.
     *
     * @param nomVille Le nom de la ville dont on veut obtenir une correspondance.
     * @return La ville correspondant au nom spécifié (ignorant la casse), ou null
     *         si aucune correspondance n'est trouvée.
     */ 
    public Ville getVilleIgnoreCase(String nomVille) {
        for (Ville ville : villes.values()) {
            if (ville.getNom().equalsIgnoreCase(nomVille)) {
                return ville;
            }
        }
        return null;
    }

    /**
     * Retourne la liste des voisins d'une ville donnée.
     *
     * @param nomVille Le nom de la ville dont on veut obtenir les voisins.
     * @return La liste des voisins de la ville, ou une liste vide si la ville
     *         n'existe pas.
     */
    public List<Ville> getVoisins(String nomVille) {
        Ville ville = villes.get(nomVille);
        return (ville != null) ? ville.getVoisins() : new ArrayList<>();
    }

    /**
     * Obtient la liste des villes dans la communauté.
     *
     * @return La liste des villes dans la communauté.
     */
    public List<Ville> getVilles() {
        return new ArrayList<>(villes.values());
    }

    public void ajouterZoneRecharge(String nomVille) {
        gestionCA.ajouterZoneRecharge(nomVille);
    }

    public void retirerZoneRecharge(String nomVille) {
        gestionCA.retirerZoneRecharge(nomVille);
    }

    public void afficherVillesAvecZoneRecharge() {
        gestionCA.afficherVillesAvecZoneRecharge();
    }

    public void afficherCommunaute() {
        System.out.println("Communauté d'agglomération :");

        for (Ville ville : villes.values()) {
            System.out.println("Ville : " + ville.getNom());
            System.out.println("Voisins : " + ville.getVoisinsAsString());
            System.out.println("Zone de recharge : " + (ville.aZoneRecharge() ? "Oui" : "Non"));
            System.out.println();
        }
    }
}
