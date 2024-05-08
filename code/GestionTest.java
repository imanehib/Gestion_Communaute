import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;


class GestionTest {
    private GestionV2 gestionV2;
    private GestionCA gestionCA;
    private CommAgglomeration communaute;

    @BeforeEach
    void setUp() {
        gestionCA = new GestionCA();
        communaute = new CommAgglomeration();
        gestionV2 = new GestionV2();

    }

    @Test
    void ajouterVille() {
        gestionCA.ajouterVille("VilleTest");
        assertTrue(gestionCA.getCommunaute().getVille("VilleTest") != null);
    }

    @Test
    void ajouterRoute() {
        gestionCA.ajouterVille("Ville1");
        gestionCA.ajouterVille("Ville2");

        gestionCA.ajouterRoute("Ville1", "Ville2");
        assertTrue(gestionCA.getCommunaute().getVille("Ville1").getVoisins().contains(gestionCA.getCommunaute().getVille("Ville2")));
    }

    @Test
    void ajouterZoneRecharge() {
        gestionCA.ajouterVille("VilleAvecZoneRecharge");
        gestionCA.ajouterZoneRecharge("VilleAvecZoneRecharge");
        assertTrue(gestionCA.getCommunaute().getVille("VilleAvecZoneRecharge").aZoneRecharge());
    }

    @Test
    void retirerZoneRecharge() {
        gestionCA.ajouterVille("VilleSansZoneRecharge");
        gestionCA.ajouterZoneRecharge("VilleSansZoneRecharge");
        gestionCA.retirerZoneRecharge("VilleSansZoneRecharge");
        assertTrue(gestionCA.getCommunaute().getVille("VilleSansZoneRecharge").aZoneRecharge());
    }

    @Test
    void peutRetirerZoneRecharge() {
        gestionCA.ajouterVille("VilleAvecZoneRecharge");
        gestionCA.ajouterVille("VoisinSansZoneRecharge");
        gestionCA.ajouterRoute("VilleAvecZoneRecharge", "VoisinSansZoneRecharge");
        assertFalse(gestionCA.peutRetirerZoneRecharge(gestionCA.getCommunaute().getVille("VilleAvecZoneRecharge"))); // change visibility to package not private
        // The method peutRetirerZoneRecharge(Ville) from the type GestionCA is not visible
    }
    

    @Test
    void traiterLigne() {
        GestionV2 gestionV2 = new GestionV2();
        gestionV2.traiterLigne("ville(NouvelleVille).", communaute);
        assertNotNull(communaute.getVille("NouvelleVille"));
        assertFalse(communaute.getVille("NouvelleVille").aZoneRecharge());
    }
 

    @Test
    void score() {
        GestionV2 gestionV2 = new GestionV2();
        int score = gestionV2.score(communaute);
        assertEquals(0, score);
       
    }

    @Test
    void retirerZoneRechargeSansVoisinAvecZoneRecharge() {
        GestionCA gestionCA = new GestionCA();
        gestionCA.ajouterVille("VilleA");
        gestionCA.ajouterVille("VilleB");
        gestionCA.ajouterRoute("VilleA", "VilleB");
        gestionCA.ajouterZoneRecharge("VilleA");
        gestionCA.ajouterZoneRecharge("VilleB");

        gestionCA.retirerZoneRecharge("VilleA");
        assertFalse(gestionCA.getCommunaute().getVille("VilleA").aZoneRecharge());
        assertTrue(gestionCA.getCommunaute().getVille("VilleB").aZoneRecharge());
    }
    
    @Test
    void ajouterVilleExistante() {
        GestionCA gestionCA = new GestionCA();
        gestionCA.ajouterVille("Y");
        assertTrue(gestionCA.getCommunaute().getVille("Y") != null);

        gestionCA.ajouterVille("X");
        assertNotNull(gestionCA.getCommunaute().getVille("Y"));
        assertNotNull(gestionCA.getCommunaute().getVille("X"));

    }

    @Test
    void retirerZoneRechargeVilleSansZoneRecharge() {
        GestionCA gestionCA = new GestionCA();
        gestionCA.ajouterVille("VilleSansZoneRecharge");
        gestionCA.retirerZoneRecharge("VilleSansZoneRecharge");
        assertFalse(gestionCA.getCommunaute().getVille("VilleSansZoneRecharge").aZoneRecharge());
    }

    @Test
    void ajouterRouteVilleInexistante() {
        GestionCA gestionCA = new GestionCA();
        gestionCA.ajouterVille("VilleA");
        gestionCA.ajouterRoute("VilleA", "VilleInexistante");
        assertFalse(gestionCA.getCommunaute().getVille("VilleA").getVoisins().contains(gestionCA.getCommunaute().getVille("VilleInexistante")));
    }

    @Test
    void ajouterZoneRechargeVilleInexistante() {
        GestionCA gestionCA = new GestionCA();
        gestionCA.ajouterZoneRecharge("B");
        assertNull(gestionCA.getCommunaute().getVille("B"));
    }
    

    @Test
    void resoudreAutomatiquement() {
        communaute.ajouterVille("VilleA");
        communaute.ajouterVille("VilleB");
        communaute.ajouterRoute("VilleA", "VilleB");

        gestionV2.resoudreAutomatiquement(communaute);

        assertTrue(communaute.getVille("VilleA").aZoneRecharge());
        assertFalse(communaute.getVille("VilleB").aZoneRecharge());
    }
    
    @Test
    void algorithmeOptimale() {
        communaute.ajouterVille("VilleX");
        communaute.ajouterVille("VilleY");
        communaute.ajouterVille("VilleZ");
        communaute.ajouterRoute("VilleX", "VilleY");
        communaute.ajouterRoute("VilleY", "VilleZ");

        gestionV2.algorithmeOptimale(communaute);

        assertFalse(communaute.getVille("VilleX").aZoneRecharge());
        assertTrue(communaute.getVille("VilleY").aZoneRecharge());
        assertFalse(communaute.getVille("VilleZ").aZoneRecharge());
    }
    
    @Test
    void resoudreManuellement() {
        communaute.ajouterVille("VilleP");
        communaute.ajouterVille("VilleQ");
        communaute.ajouterRoute("VilleP", "VilleQ");

        Scanner scanner = new Scanner("1\nVilleP\n4\n");

        gestionV2.resoudreManuellement(communaute, scanner);

        assertTrue(communaute.getVille("VilleP").aZoneRecharge());
    }

}
