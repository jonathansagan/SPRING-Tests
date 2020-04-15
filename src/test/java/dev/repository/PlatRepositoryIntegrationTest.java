package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.entite.Plat;

//Contexte : 
@SpringJUnitConfig({ JpaConfig.class, DataSourceH2TestConfig.class })
@ActiveProfiles({ "jpa" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional

public class PlatRepositoryIntegrationTest {

	@Autowired
	PlatRepository platRepository;

	@Test
	void testFindAll() {
		List<Plat> listePlats = platRepository.findAll();
		assertFalse(listePlats.isEmpty());
	}

	@Test
	void testFindAllSortAsc() {
		Sort sort = Sort.by("prixEnCentimesEuros").ascending();
		assertThat(platRepository.findAll(sort))
				.isSortedAccordingTo((a, b) -> a.getPrixEnCentimesEuros() - b.getPrixEnCentimesEuros());
	}

	@Test
	void testFindAllSortDesc() {
		Sort sort = Sort.by("prixEnCentimesEuros").descending();
		assertThat(platRepository.findAll(sort))
				.isSortedAccordingTo((a, b) -> b.getPrixEnCentimesEuros() - a.getPrixEnCentimesEuros());
	}

	/**
	 * dans (0,2) le 0 veut dire qu'on est sur la première page et le 2 que les
	 * données sont regroupées par 2
	 */
	@Test
	void testFindAllPageable() {
		Sort sort = Sort.by("nom").ascending();
		Pageable p = PageRequest.of(0, 2, sort);
		assertThat(platRepository.findAll(p)).containsExactly(new Plat("Blanquette de veau", 1500),
				new Plat("Couscous", 1600));
	}

	@Test
	void testFindById() {
		assertThat(platRepository.findById(1)).hasValue(new Plat("Magret de canard", 0));
	}

	@Test
	void testGetOne() {
		assertThat(platRepository.getOne(1).getPrixEnCentimesEuros()).isEqualTo(1300);
	}

	@Test
	void testCount() {
		assertThat(platRepository.count()).isEqualTo(6);
	}

	@Test
	void testFindByPrix() {
		assertThat(platRepository.findByPrixEnCentimesEuros(1300).get(0).getNom()).isEqualTo("Magret de canard");
	}

	@Test
	void testAvgPrix() {
		assertThat(platRepository.avgPrix(1600)).isEqualTo(2500);
	}

	@Test
	void testFindByNomWithIngredients() {
		assertThat(platRepository.findByNomWithIngredients("Moules-frites")).hasSize(6);
	}

	@Test
	void testSave() {
		Plat nvPlat = new Plat("paella", 1800);
		platRepository.save(nvPlat);
		assertThat(platRepository.findAll()).contains(nvPlat);
	}

	@Test
	void testChangerNom() {
		platRepository.changerNom("Magret de canard", "Coin Coin");
		assertThat(platRepository.findAll()).contains(new Plat("Coin Coin", 0));
	}

}
