
package ftn.uns.ac.rs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Smestaj {

	@Id
    protected Long id;

    protected String naziv;

    protected String opis;

    @Column(columnDefinition = "longblob")
    protected String slika;
    
    protected int kapacitet;
    
    @OneToOne(optional = false)
    protected Lokacija lokacija;
    
    @ManyToOne(optional = false)
    protected TipSmestaja tipSmestaja;

    @ManyToOne(optional = false)
    protected KategorijaSmestaja kategorijaSmestaja;

    @OneToMany(mappedBy = "smestaj")
    protected List<Soba> soba;

    @ManyToOne(optional = false)
    protected Agent agent;

    @OneToMany(mappedBy = "smestaj")
    protected List<Komentar> komentar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public TipSmestaja getTipSmestaja() {
		return tipSmestaja;
	}

	public void setTipSmestaja(TipSmestaja tipSmestaja) {
		this.tipSmestaja = tipSmestaja;
	}

	public KategorijaSmestaja getKategorijaSmestaja() {
		return kategorijaSmestaja;
	}

	public void setKategorijaSmestaja(KategorijaSmestaja kategorijaSmestaja) {
		this.kategorijaSmestaja = kategorijaSmestaja;
	}

	public List<Soba> getSoba() {
		return soba;
	}

	public void setSoba(List<Soba> soba) {
		this.soba = soba;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<Komentar> getKomentar() {
		return komentar;
	}

	public void setKomentar(List<Komentar> komentar) {
		this.komentar = komentar;
	}

}
