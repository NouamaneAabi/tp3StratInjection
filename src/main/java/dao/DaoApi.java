package dao;

import org.springframework.stereotype.Component;

/**
 * Implémentation API REST : simule un appel à une API externe.
 * Bean nommé "daoApi" pour la sélection par propriété (Stratégie C).
 */
@Component("daoApi")
public class DaoApi implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO] Appel à l'API REST externe");
        return 220.0;
    }
}
