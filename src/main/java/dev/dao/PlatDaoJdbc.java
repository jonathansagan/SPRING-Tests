package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

@Repository
public class PlatDaoJdbc implements IPlatDao {
	private JdbcTemplate jdbcTemplate;

	public PlatDaoJdbc(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public Integer compter() {
		return this.jdbcTemplate.queryForObject("select count(*) from plat", Integer.class);
	}

	@Override
	public List<Plat> listerPlats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		// TODO Auto-generated method stub

	}

}
