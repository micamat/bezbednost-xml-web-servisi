package agentski.modul.app.modelDTO;

import java.sql.Date;

public class ZauzeceDTO {
	protected Long id;
	
    protected Date datumOd;
    
    protected Date datumDo;
    
    protected Long sobaId;
    
    protected Long statusSobeId;

    public ZauzeceDTO() {
    	
    }
    
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

	public Long getSobaId() {
		return sobaId;
	}

	public void setSobaId(Long sobaId) {
		this.sobaId = sobaId;
	}

	public Long getStatusSobeId() {
		return statusSobeId;
	}

	public void setStatusSobeId(Long statusSobeId) {
		this.statusSobeId = statusSobeId;
	}
    
    
}
