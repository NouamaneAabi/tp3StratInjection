package dao;

/**
 * Interface fondamentale (abstraction) pour toutes les sources de données.
 * Respecte le principe de dépendance vers les abstractions (DIP).
 * Toutes les implémentations concrètes doivent respecter ce contrat.
 */
public interface IDao {
    /**
     * Récupère une valeur numérique depuis la source de données.
     * @return la valeur récupérée
     */
    double getValue();
}
