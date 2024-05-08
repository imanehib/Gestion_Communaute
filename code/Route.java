import java.util.Objects;


/**
 * Représente une route reliant deux villes dans une communauté d'agglomération.
 */
public class Route {
    private String villeDepart;
    private String villeArrivee;

    /**
     * Constructeur de la classe Route.
     *
     * @param villeDepart   Nom de la ville de départ.
     * @param villeArrivee  Nom de la ville d'arrivée.
     */
    public Route(String villeDepart, String villeArrivee) {
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
    }


    /**
     * Obtient le nom de la ville de départ.
     *
     * @return Nom de la ville de départ.
     */
    public String getVilleDepart() {
        return villeDepart;
    }


    /**
     * Obtient le nom de la ville d'arrivée.
     *
     * @return Nom de la ville d'arrivée.
     */
    public String getVilleArrivee() {
        return villeArrivee;
    }
    
    /**
     * Obtient le nom de la route, composé des noms de ville de départ et d'arrivée séparés par "--".
     *
     * @return Nom de la route.
     */
    public String getNom() {
        return getVilleDepart() + "--" + getVilleArrivee() ;
    }

    /**
     * Vérifie si cette route est égale à un autre objet.
     *
     * @param obj Objet à comparer.
     * @return true si les routes sont égales, sinon false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route route = (Route) obj;
        return villeDepart.equals(route.villeDepart) && villeArrivee.equals(route.villeArrivee);
    }

    /**
     * Calcule le code de hachage de la route.
     *
     * @return Code de hachage de la route.
     */
    @Override
    public int hashCode() {
        return Objects.hash(villeDepart, villeArrivee);
    }
}

