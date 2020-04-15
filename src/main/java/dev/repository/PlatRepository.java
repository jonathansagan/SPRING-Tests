package dev.repository;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Ingredient;
import dev.entite.Plat;

@Profile("jpa")
public interface PlatRepository extends JpaRepository<Plat, Integer> {

	/*
	 * méthode FindAll: pour les différentes méthodes, vérifier qu'elles ne sont pas
	 * déjà existantes dans japRepository! List<Plat> findAll(String nom);
	 */

	// méthode FindAllSortAsc()

	// Céation de la méthode findByPrix():
	List<Plat> findByPrixEnCentimesEuros(Integer prixEnCentimesEuros);

	/*
	 * Céation de la méthode avgPrix() :
	 */
	/*
	 * permet de trouver la moyenne (select avg(..) …) des prix supérieurs à un
	 * montant donné:
	 */
	@Query("select avg(p.prixEnCentimesEuros) from Plat p where p.prixEnCentimesEuros > :prix")
	Integer avgPrix(@Param("prix") Integer prix);

	// Céation de la méthode findByNomWithIngredients():
	@Query("select p.ingredients from Plat p where p.nom = :nom")
	List<Ingredient> findByNomWithIngredients(@Param("nom") String nom);

	// Céation de la méthode changerNom():
	@Modifying
	@Query("update Plat p set p.nom = :newName where p.nom = :oldName")
	void changerNom(@Param("oldName") String oldName, @Param("newName") String newName);

}
