package ftn.uns.ac.rs.SearchMicroservice.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchDTO {
	private String drzava;
	private String grad;
	private String ulica;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date datumOd;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date datumDo;
	private String brojOsoba;
	private String tip;
	private String kategorija;
	private List<String> usluge;
	
	
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
	public String getBrojOsoba() {
		return brojOsoba;
	}
	public void setBrojOsoba(String brojOsoba) {
		this.brojOsoba = brojOsoba;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getKategorija() {
		return kategorija;
	}
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}
	public List<String> getUsluge() {
		return usluge;
	}
	public void setUsluge(List<String> usluge) {
		this.usluge = usluge;
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
	@Override
	public String toString() {
		return "SearchDTO [drzava=" + drzava + ", grad=" + grad + ", ulica=" + ulica + ", datumOd=" + datumOd
				+ ", datumDo=" + datumDo + ", brojOsoba=" + brojOsoba + ", tip=" + tip + ", kategorija=" + kategorija
				+ ", usluge=" + usluge + "]";
	}
}
