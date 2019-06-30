
package ftn.uns.ac.rs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Korisnik {

	@Id
	protected Long id;

	protected String korisnickoIme;

	public Long getId() {
		return id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String value) {
		this.korisnickoIme = value;
	}

}
