package test;

import config.AppConfig;
import metier.IMetier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests de non-régression OCP.
 *
 * Ces tests vérifient que MetierImpl retourne le bon résultat selon
 * la configuration active dans app.properties.
 * MetierImpl n'est PAS modifié entre les tests — seule la configuration change.
 */
public class OcpSelectionTest {

    private AnnotationConfigApplicationContext context;
    private IMetier metier;

    @Before
    public void setUp() {
        // Démarre le conteneur Spring avec la même config que l'appli
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        metier = context.getBean(IMetier.class);
    }

    @After
    public void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    /**
     * Vérifie que le bean IMetier est correctement instancié par Spring.
     */
    @Test
    public void testBeanMetierExiste() {
        assertNotNull("Le bean IMetier ne doit pas être null", metier);
    }

    /**
     * Vérifie que le résultat est cohérent avec une implémentation IDao.
     *
     * Les valeurs valides sont :
     *   dao=dao     → 200.0
     *   dao=dao2    → 300.0
     *   dao=daoFile → 360.0
     *   dao=daoApi  → 440.0
     *
     * Ce test vérifie que le résultat est l'un des résultats attendus.
     */
    @Test
    public void testResultatCoherent() {
        double resultat = metier.calcul();
        System.out.println("[TEST] Résultat obtenu : " + resultat);

        // Le résultat doit être le double d'une des valeurs DAO connues
        boolean estValide = (resultat == 200.0)
                         || (resultat == 300.0)
                         || (resultat == 360.0)
                         || (resultat == 440.0);

        assertEquals("Le résultat doit être positif", true, resultat > 0);
        assertEquals("Le résultat doit être l'un des résultats attendus", true, estValide);
    }

    /**
     * Test spécifique : si dao.target=dao dans app.properties,
     * le résultat attendu est 200.0 (DaoImpl retourne 100.0 * 2).
     *
     * Ce test servira de référence OCP : changer la propriété sans
     * toucher au code doit faire échouer ce test spécifique si on
     * sélectionne une autre implémentation.
     */
    @Test
    public void testDaoImplParDefaut() {
        // Ce test suppose dao.target=dao dans app.properties
        // Modifiez app.properties pour tester d'autres stratégies
        double resultat = metier.calcul();
        System.out.println("[TEST] Résultat pour validation OCP : " + resultat);

        // Commentez/décommentez selon la valeur de dao.target :
        assertEquals("dao (100.0 * 2 = 200.0)", 200.0, resultat, 0.001);
        // assertEquals("dao2 (150.0 * 2 = 300.0)", 300.0, resultat, 0.001);
        // assertEquals("daoFile (180.0 * 2 = 360.0)", 360.0, resultat, 0.001);
        // assertEquals("daoApi (220.0 * 2 = 440.0)", 440.0, resultat, 0.001);
    }
}
