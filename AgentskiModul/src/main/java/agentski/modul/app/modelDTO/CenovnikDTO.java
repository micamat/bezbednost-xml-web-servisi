package agentski.modul.app.modelDTO;

import java.sql.Date;

public class CenovnikDTO {
	protected Long id;
	
	protected String cena;
    
    protected Date datumOd;
    
    protected Date datumDo;
   
    protected Long smestajId;
   
    protected Long tipSobeId;
    
    public CenovnikDTO() {
    	
    }
    
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

	public Long getSmestajId() {
		return smestajId;
	}

	public void setSmestajId(Long smestajId) {
		this.smestajId = smestajId;
	}

	public Long getTipSobeId() {
		return tipSobeId;
	}

	public void setTipSobeId(Long tipSobeId) {
		this.tipSobeId = tipSobeId;
	}
    
}
