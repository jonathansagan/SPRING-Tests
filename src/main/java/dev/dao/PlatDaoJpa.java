package dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Plat;

@Profile("jpa")
public class PlatDaoJpa implements IPlatDao {

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Plat> listerPlats() {
		TypedQuery<Plat> query = em.createQuery("select p from Plat p", Plat.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		Plat plat = new Plat(nomPlat, prixPlat);
		em.persist(plat);
	}

}
