
package ftn.uns.ac.rs.model;

import java.util.Date;


public class ShowCenovnikDTO {

    protected Long id;
    protected String cena;
    protected Date datumOd;
    protected Date datumDo;
    protected String nazivSmestaj;
    protected String nazivTipSobe;
    protected String nazivSobe;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCena() {
		return cena;
	}
	public void setCena(String cena) {
		this.cena = cena;
	}
	public Date getDatumOd() {
		return datumOd;
	}
	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}
	public Date getDatumDo() {
		return datumDo;
	}
	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}
	public String getNazivSmestaj() {
		return nazivSmestaj;
	}
	public void setNazivSmestaj(String nazivSmestaj) {
		this.nazivSmestaj = nazivSmestaj;
	}
	public String getNazivTipSobe() {
		return nazivTipSobe;
	}
	public void setNazivTipSobe(String nazivTipSobe) {
		this.nazivTipSobe = nazivTipSobe;
	}
	public String getNazivSobe() {
		return nazivSobe;
	}
	public void setNazivSobe(String nazivSobe) {
		this.nazivSobe = nazivSobe;
	}

    


}
