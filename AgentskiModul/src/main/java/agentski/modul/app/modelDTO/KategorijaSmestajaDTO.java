package agentski.modul.app.modelDTO;

public class KategorijaSmestajaDTO {
	private Long id;
	private String naziv;
	private String opis;
	
	public KategorijaSmestajaDTO(){
		
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
}
