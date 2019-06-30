
package ftn.uns.ac.rs.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Poruka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String naslov;

    protected String sadrzaj;

    protected Date datum;

    @ManyToOne(optional = false)
    protected Rezervacija rezervacija;
    
    protected boolean korisnikPosiljalac;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public boolean isKorisnikPosiljalac() {
		return korisnikPosiljalac;
	}

	public void setKorisnikPosiljalac(boolean korisnikPosiljalac) {
		this.korisnikPosiljalac = korisnikPosiljalac;
	}
}
