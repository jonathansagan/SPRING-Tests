package dev.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.dao.IPlatDao;

public class PlatServiceVersion1Test {
	private IPlatDao dao;
	private IPlatService platService1;

	@BeforeEach
	public void setUp() {
		dao = Mockito.mock(IPlatDao.class);
		platService1 = new PlatServiceVersion1(dao);
	}

	//Test lorsque le nom du plat est invalide
	@Test
	void testAjouterPlatNomInvalide() {
		assertThatThrownBy(() -> {
			platService1.ajouterPlat("AA", 1000);
		}).hasMessage("un plat doit avoir un nom de plus de 3 caractères");
	}
	
	//Test lorsque le prix du plat est invalide
	@Test
	void testAjouterPlatPrixInvalide() {
		assertThatThrownBy(() -> {
			platService1.ajouterPlat("Paella", 100);
		}).hasMessage("le prix d'un plat doit être supérieur à 5 €");
	}

	//Test lorsque tt est OK
	@Test
	void testAjouterPlatAvecDao() {
		platService1.ajouterPlat("Paella", 3500);
		Mockito.verify(dao).ajouterPlat("Paella", 3500);
	}
}