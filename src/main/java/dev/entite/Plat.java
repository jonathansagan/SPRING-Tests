package dev.entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "plat")
public class Plat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	@Column(name = "prix")
	private Integer prixEnCentimesEuros;

	@ManyToMany(mappedBy = "plats")
	private List<Ingredient> ingredients = new ArrayList<>();

	public Plat() {
	}

	public Plat(String nom, Integer prixEnCentimesEuros) {
		this.nom = nom;
		this.prixEnCentimesEuros = prixEnCentimesEuros;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getPrixEnCentimesEuros() {
		return prixEnCentimesEuros;
	}

	public void setPrixEnCentimesEuros(Integer prixEnCentimesEuros) {
		this.prixEnCentimesEuros = prixEnCentimesEuros;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Plat plat = (Plat) o;
		return nom.equals(plat.nom);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}
}
