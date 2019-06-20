package ftn.uns.ac.rs.ReservationMicroservice.dto;

import java.util.Date;

public class ReservationDTO {

	private long id;
	private Date DatumOd;
	private Date DatumDo;
	private float cena;
	private long idSobe;
	private long idKorisnika;
	
	public ReservationDTO() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDatumOd() {
		return DatumOd;
	}
	public void setDatumOd(Date datumOd) {
		DatumOd = datumOd;
	}
	public Date getDatumDo() {
		return DatumDo;
	}
	public void setDatumDo(Date datumDo) {
		DatumDo = datumDo;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	public long getIdSobe() {
		return idSobe;
	}
	public void setIdSobe(long idSobe) {
		this.idSobe = idSobe;
	}
	public long getIdKorisnika() {
		return idKorisnika;
	}
	public void setIdKorisnika(long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	
	
}
