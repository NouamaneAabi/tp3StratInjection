package metier;

import dao.IDao;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Implémentation du service métier.
 *
 * RÈGLE OCP : Cette classe ne doit JAMAIS être modifiée pour changer
 * la source de données. La sélection de l'implémentation IDao est
 * entièrement déléguée au conteneur Spring via la configuration externe.
 *
 * L'injection de constructeur est utilisée car elle :
 *   - Garantit que la dépendance est toujours présente (non nullable)
 *   - Rend la classe immuable (champ final)
 *   - Facilite les tests unitaires (pas besoin du conteneur Spring)
 */
@Service
public class MetierImpl implements IMetier {

    private final IDao dao;

    /**
     * Injection de constructeur — méthode recommandée par Spring.
     * @param dao l'implémentation IDao choisie par le conteneur
     */
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    /**
     * Trace l'implémentation injectée au démarrage du bean.
     * Utile pour déboguer et vérifier la stratégie de sélection active.
     */
    @PostConstruct
    public void init() {
        System.out.println("[TRACE] Implémentation DAO injectée : "
                + dao.getClass().getSimpleName());
    }

    /**
     * Logique métier : récupère la valeur depuis le DAO et la double.
     */
    @Override
    public double calcul() {
        double valeur = dao.getValue();
        return valeur * 2.0;
    }
}
