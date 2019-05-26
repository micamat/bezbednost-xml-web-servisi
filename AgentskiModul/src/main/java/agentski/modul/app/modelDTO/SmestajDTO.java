package agentski.modul.app.modelDTO;

public class SmestajDTO {
	private Long id;
    private String naziv;
    private String opis;
    private String slika;
    private Long lokacijaId;
    private Long tipSmestajaId;
    private Long kategorijaSmestajaId;
    
    public SmestajDTO() {
    	
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

	public Long getLokacijaId() {
		return lokacijaId;
	}

	public void setLokacijaId(Long lokacijaId) {
		this.lokacijaId = lokacijaId;
	}

	public Long getTipSmestajaId() {
		return tipSmestajaId;
	}

	public void setTipSmestajaId(Long tipSmestajaId) {
		this.tipSmestajaId = tipSmestajaId;
	}

	public Long getKategorijaSmestajaId() {
		return kategorijaSmestajaId;
	}

	public void setKategorijaSmestajaId(Long kategorijaSmestajaId) {
		this.kategorijaSmestajaId = kategorijaSmestajaId;
	}
    
}
