package dao;

import org.springframework.stereotype.Component;

/**
 * Implémentation fichier : simule la lecture depuis un fichier local.
 * Bean nommé "daoFile" pour la sélection par propriété (Stratégie C).
 */
@Component("daoFile")
public class DaoFile implements IDao {

    @Override
    public double getValue() {
        System.out.println("[DAO] Lecture depuis un fichier local");
        return 180.0;
    }
}
