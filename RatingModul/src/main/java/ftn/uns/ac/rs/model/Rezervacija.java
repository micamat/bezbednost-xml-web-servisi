
package ftn.uns.ac.rs.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	
    protected Date datumOd;
    
    protected Date datumDo;
    
    protected int brojSoba;
    
    protected float cena;
    
    @ManyToOne(optional = false)
    protected Smestaj smestaj;
    
    @OneToMany(mappedBy = "rezervacija")
    protected List<Poruka> poruka;
    
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    protected Korisnik korisnik;
    
    @OneToMany(mappedBy = "rezervacija")
    protected List<RezervisaneSobe> rezervisaneSobe;

    public Long getId() {
        return id;
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

	public int getBrojSoba() {
		return brojSoba;
	}

	public void setBrojSoba(int brojSoba) {
		this.brojSoba = brojSoba;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

	public List<Poruka> getPoruka() {
		return poruka;
	}

	public void setPoruka(List<Poruka> poruka) {
		this.poruka = poruka;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<RezervisaneSobe> getRezervisaneSobe() {
		return rezervisaneSobe;
	}

	public void setRezervisaneSobe(List<RezervisaneSobe> rezervisaneSobe) {
		this.rezervisaneSobe = rezervisaneSobe;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
}
