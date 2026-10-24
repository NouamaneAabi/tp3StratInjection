import config.AppConfig;
import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Point d'entrée de l'application.
 *
 * Démarre le conteneur Spring, récupère le bean IMetier et exécute le calcul.
 * La source de données active est déterminée par dao.target dans app.properties.
 *
 * Pour changer de source : modifier app.properties et relancer — aucun
 * changement de code source requis (OCP respecté).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Démarrage du conteneur Spring ===");

        // Initialise le conteneur avec la configuration Java
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n=== Exécution du service métier ===");

        // Récupère le bean métier via l'interface (découplage total)
        IMetier metier = context.getBean(IMetier.class);

        // Exécute le calcul
        double resultat = metier.calcul();
        System.out.println("\n>>> Résultat final : " + resultat + " <<<");

        // Ferme proprement le conteneur (déclenche les callbacks @PreDestroy)
        context.close();
        System.out.println("\n=== Conteneur Spring arrêté ===");
    }
}
