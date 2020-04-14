package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.PlatRowMapper;
import dev.entite.Plat;

@Repository

@Profile("PlatJdbc")
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
		String sql = "SELECT * FROM plats";
		List<Plat> plats = jdbcTemplate.query(sql, new PlatRowMapper());
		return plats;
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		String sql = "INSERT INTO plats (nom,prix) VALUES (?,?)";
		jdbcTemplate.update(sql, nomPlat, prixPlat);

	}

}
