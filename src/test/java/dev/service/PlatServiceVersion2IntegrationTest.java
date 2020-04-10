package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.IPlatDao;
import dev.dao.PlatDaoMemoire;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PlatDaoMemoire.class, PlatServiceVersion2.class, })
@ActiveProfiles({ "PlatMemoire", "Service2" })
public class PlatServiceVersion2IntegrationTest {

	// permet d'injecter le bean à tester
	@Autowired
	private IPlatService platService2;
	private IPlatDao platDaoMemoire;

	// Test lorsque tt est OK
	@Test
	void testAjouterPlat() {
		platService2.ajouterPlat("Paella", 3500);
		assertFalse(platService2.listerPlats().isEmpty());
	}

	// Test lorsque le prix du plat est invalide
	@Test
	void testAjouterPlatPrixInvalide() {
		assertThatThrownBy(() -> {
			platService2.ajouterPlat("Paella", 350);
		}).hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
