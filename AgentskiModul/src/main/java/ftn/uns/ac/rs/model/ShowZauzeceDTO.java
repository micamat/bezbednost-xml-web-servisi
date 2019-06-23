
package ftn.uns.ac.rs.model;

import java.util.Date;


public class ShowZauzeceDTO {

    protected Long id;
    protected Date datumOd;
    protected Date datumDo;
    protected String nazivSobe;
    protected String nazivStatusaSobe;
    protected String nazivSmestaja;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getNazivSobe() {
		return nazivSobe;
	}
	public void setNazivSobe(String nazivSobe) {
		this.nazivSobe = nazivSobe;
	}
	public String getNazivStatusaSobe() {
		return nazivStatusaSobe;
	}
	public void setNazivStatusaSobe(String nazivStatusaSobe) {
		this.nazivStatusaSobe = nazivStatusaSobe;
	}
	public String getNazivSmestaja() {
		return nazivSmestaja;
	}
	public void setNazivSmestaja(String nazivSmestaja) {
		this.nazivSmestaja = nazivSmestaja;
	}
	

}
