package ftn.uns.ac.rs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lokacija {

	@Id
	protected Long id;

	protected String drzava;

	protected String grad;

	protected String ulica;

	protected String broj;

	protected Long idKoordinate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Long getIdKoordinate() {
		return idKoordinate;
	}

	public void setIdKoordinate(Long idKoordinate) {
		this.idKoordinate = idKoordinate;
	}

}
