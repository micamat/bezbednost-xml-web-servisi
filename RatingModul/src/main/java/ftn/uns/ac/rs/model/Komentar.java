
package ftn.uns.ac.rs.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Komentar {

	@Id
	protected Long id;

	protected String naslov;

	protected String tekst;

	protected String statusKomentara;

	protected Date datumKreiranja;

	@ManyToOne(optional = false)
	protected Rezervacija rezervacija;

	@ManyToOne(optional = false)
	protected Smestaj smestaj;

	@ManyToOne(optional = false)
	protected Korisnik korisnik;

	public Long getId() {
		return id;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String value) {
		this.naslov = value;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String value) {
		this.tekst = value;
	}

	public String getStatusKomentara() {
		return statusKomentara;
	}

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public Komentar() {
		super();
	}

	public Komentar(Long id, String naslov, String tekst, String statusKomentara, Date datumKreiranja,
			Rezervacija rezervacija, Smestaj smestaj, Korisnik korisnik) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.tekst = tekst;
		this.statusKomentara = statusKomentara;
		this.datumKreiranja = datumKreiranja;
		this.rezervacija = rezervacija;
		this.smestaj = smestaj;
		this.korisnik = korisnik;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public void setStatusKomentara(String statusKomentara) {
		this.statusKomentara = statusKomentara;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

}
