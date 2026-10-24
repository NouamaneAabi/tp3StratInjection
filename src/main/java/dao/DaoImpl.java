package dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Implémentation principale : simule une base de données de production.
 * Annotée @Primary pour être le choix par défaut en cas d'ambiguïté (Stratégie A).
 * Le bean est nommé "dao" pour permettre la sélection par nom.
 */
@Component("dao")
public class DaoImpl implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO] Lecture depuis la base de données (prod)");
        return 100.0;
    }
}
