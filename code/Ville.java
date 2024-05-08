import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * La classe Ville représente une ville dans une communauté d'agglomération.
 */
public class Ville {
    private final String nom;           // Le nom de la ville
    private boolean aZoneRecharge;      // Indique si la ville a une zone de recharge
    private Set<Ville> voisins; // Liste des voisins de la ville

    /**
     * Constructeur de la classe Ville.
     *
     * @param nom             Le nom de la ville.
     * @param aZoneRecharge   Indique si la ville a une zone de recharge.
     */
    public Ville(String nom, boolean aZoneRecharge) {
    	if (nom == null || nom.isEmpty()) {
            throw new IllegalArgumentException("Le nom de la ville ne peut pas être nul ou vide");
        }
        this.nom = nom;
        this.voisins = new HashSet<>();
        this.aZoneRecharge = aZoneRecharge;
    }

    /**
     * Obtient le nom de la ville.
     *
     * @return Le nom de la ville.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Vérifie si la ville a une zone de recharge.
     *
     * @return true si la ville a une zone de recharge, sinon false.
     */
    public boolean aZoneRecharge() {
        return aZoneRecharge;
    }

    /**
     * Ajoute une zone de recharge à la ville.
     */
    public void ajouterZoneRecharge() {
        aZoneRecharge = true;
    }

    /**
     * Supprime la zone de recharge de la ville.
     */
    public void supprimerZoneRecharge() {
        aZoneRecharge = false;
    }

    /**
     * Ajoute un voisin à la liste des voisins de la ville.
     *
     * @param voisin Le voisin à ajouter.
     */
    public void ajouterVoisin(Ville voisin) {
    	if (voisin == null) {
            throw new IllegalArgumentException("Le voisin ne peut pas être nul");
        }
        voisins.add(voisin);
    }

    /**
     * Obtient la liste des voisins de la ville.
     *
     * @return La liste des voisins de la ville.
     */
    public List<Ville> getVoisins() {
        return new ArrayList<>(voisins);
    }

    /**
     * Obtient une représentation sous forme de chaîne des voisins de la ville.
     *
     * @return Une chaîne représentant les voisins de la ville.
     */
    public String getVoisinsAsString() {
        StringBuilder voisinsStr = new StringBuilder();
        for (Ville voisin : voisins) {
            voisinsStr.append(voisin.getNom()).append(", ");
        }

        // Supprimer la virgule et l'espace à la fin, si la liste n'est pas vide
        if (voisinsStr.length() > 0) {
            voisinsStr.delete(voisinsStr.length() - 2, voisinsStr.length());
        }

        return voisinsStr.toString();
    }

}