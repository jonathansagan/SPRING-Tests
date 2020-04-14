package dev.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.entite.Plat;

//Creation du context
@SpringJUnitConfig({ PlatDaoJdbc.class, DataSourceH2TestConfig.class })
@ActiveProfiles({ "Platjdbc", "Service1" })
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class PlatDaoJdbcIntegrationTest {

	// Injection du Bean à tester :
	@Autowired
	PlatDaoJdbc platDaoJdbc;

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = Logger.getLogger(PlatDaoJdbcIntegrationTest.class.getName());

	@Test
	void listerPlatsNonVide() {
		List<Plat> listePlats = platDaoJdbc.listerPlats();
		assertFalse(listePlats.isEmpty());
	}

}
