package dev.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.entite.Plat;

public class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	public void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {

		assertEquals(true, platDaoMemoire.listerPlats().isEmpty());
	}

	@Test
	void testAjouterPlatCasPassants() {
		platDaoMemoire.ajouterPlat("Bruscheta", 1600);
		platDaoMemoire.ajouterPlat("Tapas", 1200);

		assertEquals(false, platDaoMemoire.listerPlats().isEmpty());
	}
}