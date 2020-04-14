package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.entite.Plat;

//Creation du context
@SpringJUnitConfig({ JpaConfig.class, PlatDaoJpa.class, DataSourceH2TestConfig.class })
@ActiveProfiles({ "jpa", "Service1" })
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class PlatDaoJpaIntegrationTest {
	@Autowired
	IPlatDao platDaoJpa;

	private static final Logger LOGGER = Logger.getLogger(PlatDaoJpaIntegrationTest.class.getName());

	@Test
	void listerPlatsNonVide() {
		List<Plat> listePlats = platDaoJpa.listerPlats();
		assertFalse(listePlats.isEmpty());
	}

	@Test
	void testAjouterPlat() {
		Plat ajout = new Plat("bruschetta", 1500);
		platDaoJpa.ajouterPlat(ajout.getNom(), ajout.getPrixEnCentimesEuros());
		assertThat(platDaoJpa.listerPlats()).contains(ajout);
	}
}
