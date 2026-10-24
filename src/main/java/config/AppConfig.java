package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration principale du conteneur Spring.
 *
 * @ComponentScan : scanne les paquets "dao" et "metier" pour découvrir
 *                  automatiquement les beans annotés (@Component, @Service...).
 * @PropertySource : charge le fichier app.properties depuis le classpath,
 *                   ce qui rend disponible la propriété dao.target pour la
 *                   sélection dynamique (Stratégie C).
 */
@Configuration
@ComponentScan(basePackages = {"dao", "metier", "config"})
@PropertySource("classpath:app.properties")
public class AppConfig {
    // La configuration des beans dynamiques est déléguée à DynamicDaoConfig
}
