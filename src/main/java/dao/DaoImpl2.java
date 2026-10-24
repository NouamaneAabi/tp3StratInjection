package dao;

import org.springframework.stereotype.Component;

/**
 * Implémentation secondaire : simule une base de données de développement.
 * Bean nommé "dao2" pour la sélection par propriété (Stratégie C).
 */
@Component("dao2")
public class DaoImpl2 implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO] Lecture depuis la base de données (dev)");
        return 150.0;
    }
}
