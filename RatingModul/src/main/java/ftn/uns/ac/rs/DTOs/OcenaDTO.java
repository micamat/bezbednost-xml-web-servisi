package ftn.uns.ac.rs.DTOs;

public class OcenaDTO {
	
	protected int value;

	protected Long idRezervacije;

	protected String usernameKorisnika;
	
	public OcenaDTO() {
		super();
	}

	public OcenaDTO(int value, Long idRezervacije, String usernameKorisnika) {
		super();
		this.value = value;
		this.idRezervacije = idRezervacije;
		this.usernameKorisnika = usernameKorisnika;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Long getIdRezervacije() {
		return idRezervacije;
	}

	public void setIdRezervacije(Long idRezervacije) {
		this.idRezervacije = idRezervacije;
	}

	public String getUsernameKorisnika() {
		return usernameKorisnika;
	}

	public void setUsernameKorisnika(String usernameKorisnika) {
		this.usernameKorisnika = usernameKorisnika;
	}

}
