package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.config.AppConfig;
import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles({ "PlatMemoire", "service2" })
public class PlatServiceVersion2IntegrationTest {

	@Autowired
	PlatServiceVersion2 platService2;
	PlatDaoMemoire platDaoMemoire;

	// Test lorsque tt est OK
	void testAjouterPlatOk() {
		platService2.ajouterPlat("Paella", 3500);
		assertThat(platDaoMemoire.listerPlats()).contains(new Plat("Paella", 3500));
	}

	// Test lorsque le prix du plat est invalide
	void testAjouterPlatPrixInvalide() {
		assertThatThrownBy(() -> {
			platService2.ajouterPlat("Paella", 350);
		}).hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
