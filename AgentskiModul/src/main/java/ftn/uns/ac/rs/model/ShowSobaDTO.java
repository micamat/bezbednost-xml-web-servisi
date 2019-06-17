
package ftn.uns.ac.rs.model;

public class ShowSobaDTO {

    protected Long id;
    protected String naziv;
    protected String opis;
    protected String slika;
    protected String opisTipaSobe;
    protected String nazivTipaSobe;
    protected String nazivSmestaja;

 
    public Long getId() {
        return id;
    }

   
    public void setId(Long value) {
        this.id = value;
    }

  
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String value) {
        this.naziv = value;
    }


    public String getOpis() {
        return opis;
    }

    public void setOpis(String value) {
        this.opis = value;
    }

  
    public String getSlika() {
        return slika;
    }

    public void setSlika(String value) {
        this.slika = value;
    }


	public String getOpisTipaSobe() {
		return opisTipaSobe;
	}


	public void setOpisTipaSobe(String opisTipaSobe) {
		this.opisTipaSobe = opisTipaSobe;
	}


	public String getNazivTipaSobe() {
		return nazivTipaSobe;
	}


	public void setNazivTipaSobe(String nazivTipaSobe) {
		this.nazivTipaSobe = nazivTipaSobe;
	}


	public String getNazivSmestaja() {
		return nazivSmestaja;
	}


	public void setNazivSmestaja(String nazivSmestaja) {
		this.nazivSmestaja = nazivSmestaja;
	}



}
