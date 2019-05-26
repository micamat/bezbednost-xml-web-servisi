package agentski.modul.app.modelDTO;

public class LokacijaDTO {
	
	private Long id;
	private String drzava;
	private String grad;
	private String ulica;
	private String broj;
	private Long koordinateId;
	
	public LokacijaDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getBroj() {
		return broj;
	}
	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Long getKoordinateId() {
		return koordinateId;
	}

	public void setKoordinateId(Long koordinateId) {
		this.koordinateId = koordinateId;
	}
	
	
	
}
