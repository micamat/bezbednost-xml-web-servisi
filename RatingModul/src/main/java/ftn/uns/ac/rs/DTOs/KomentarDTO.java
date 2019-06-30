package ftn.uns.ac.rs.DTOs;

import java.sql.Date;

public class KomentarDTO {

	protected Long id;

	protected String naslov;

	protected String tekst;

	protected String statusKomentara;

	protected Date datumKreiranja;

	protected Long idRezervacije;

	protected Long idSmestaja;

	protected String usernameKorisnika;

	public KomentarDTO() {
		super();
	}

	public KomentarDTO(Long id, String naslov, String tekst, String statusKomentara, Date datumKreiranja,
			Long idRezervacije, Long idSmestaja, String usernameKorisnika) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.tekst = tekst;
		this.statusKomentara = statusKomentara;
		this.datumKreiranja = datumKreiranja;
		this.idRezervacije = idRezervacije;
		this.idSmestaja = idSmestaja;
		this.usernameKorisnika = usernameKorisnika;
	}

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

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getStatusKomentara() {
		return statusKomentara;
	}

	public void setStatusKomentara(String statusKomentara) {
		this.statusKomentara = statusKomentara;
	}

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Long getIdRezervacije() {
		return idRezervacije;
	}

	public void setIdRezervacije(Long idRezervacije) {
		this.idRezervacije = idRezervacije;
	}

	public Long getIdSmestaja() {
		return idSmestaja;
	}

	public void setIdSmestaja(Long idSmestaja) {
		this.idSmestaja = idSmestaja;
	}

	public String getUsernameKorisnika() {
		return usernameKorisnika;
	}

	public void setUsernameKorisnika(String usernameKorisnika) {
		this.usernameKorisnika = usernameKorisnika;
	}

}
