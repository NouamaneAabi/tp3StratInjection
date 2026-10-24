package metier;

/**
 * Interface du service métier.
 * Contrat pour l'exécution du calcul principal de l'application.
 */
public interface IMetier {
    /**
     * Effectue le calcul métier basé sur la valeur DAO.
     * @return le résultat du calcul
     */
    double calcul();
}
