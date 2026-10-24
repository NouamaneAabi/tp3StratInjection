package config;

import dao.DaoApi;
import dao.DaoFile;
import dao.DaoImpl;
import dao.DaoImpl2;
import dao.IDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configuration dynamique du DAO — Stratégie C (Propriétés externes).
 *
 * Ce bean @Primary remplace le @Primary sur DaoImpl et pointe vers
 * l'implémentation choisie via la propriété "dao.target" du fichier
 * app.properties. Changer app.properties suffit — aucun code source
 * n'est modifié ni recompilé.
 *
 * Valeurs acceptées pour dao.target :
 *   dao     → DaoImpl  (BD production, retourne 100.0)
 *   dao2    → DaoImpl2 (BD développement, retourne 150.0)
 *   daoFile → DaoFile  (Fichier local, retourne 180.0)
 *   daoApi  → DaoApi   (API REST externe, retourne 220.0)
 */
@Configuration
public class DynamicDaoConfig {

    @Value("${dao.target}")
    private String daoTarget;

    /**
     * Bean @Primary — résout l'ambiguïté entre les 4 implémentations IDao
     * en créant un bean "vainqueur" basé sur la propriété externe.
     */
    @Bean
    @Primary
    public IDao dynamicDao() {
        System.out.println("[CONFIG] Sélection du DAO via propriété dao.target = " + daoTarget);
        switch (daoTarget) {
            case "dao":     return new DaoImpl();
            case "dao2":    return new DaoImpl2();
            case "daoFile": return new DaoFile();
            case "daoApi":  return new DaoApi();
            default:
                throw new IllegalArgumentException(
                    "Cible DAO inconnue : '" + daoTarget
                    + "'. Valeurs valides : dao, dao2, daoFile, daoApi");
        }
    }
}
