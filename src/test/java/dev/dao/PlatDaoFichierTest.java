package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

//Creation du context
@SpringJUnitConfig(classes = PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class PlatDaoFichierTest {

	@Autowired
	private IPlatDao dao;

	@Test
	void TestAjouterPlatOk() {
		Plat plat1 = new Plat("Paella", 3500);
		List<Plat> listPlat = new ArrayList<Plat>();
		listPlat.add(plat1);
		dao.ajouterPlat("Paella", 3500);
		assertThat(dao.listerPlats()).isEqualTo(listPlat);
	}

	/**
	 * Permet de tester indépendamment des tests réalisés avant grace a
	 * l'annotation @DirtiesContext
	 * 
	 */
	@Test
	void TestAjouterPlatIndépendant() {
		Plat plat1 = new Plat("Paella", 3500);
		List<Plat> listPlat = new ArrayList<Plat>();
		listPlat.add(plat1);
		dao.ajouterPlat("Paella", 3500);
		assertThat(dao.listerPlats()).isEqualTo(listPlat);
	}
}