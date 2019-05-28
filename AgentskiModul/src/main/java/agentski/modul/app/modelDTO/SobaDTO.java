package agentski.modul.app.modelDTO;

public class SobaDTO {
	protected Long id;
	   
    protected String naziv;
    
    protected String opis;
    
    protected String slika;
    
    protected Long tipSobeId;
    
    protected Long smestajId;

	public SobaDTO() {
		
	}

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

	public Long getTipSobeId() {
		return tipSobeId;
	}

	public void setTipSobeId(Long tipSobeId) {
		this.tipSobeId = tipSobeId;
	}

	public Long getSmestajId() {
		return smestajId;
	}

	public void setSmestajId(Long smestajId) {
		this.smestajId = smestajId;
	}
	
    
}
