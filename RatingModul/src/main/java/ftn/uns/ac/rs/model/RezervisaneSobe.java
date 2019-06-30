
package ftn.uns.ac.rs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RezervisaneSobe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
	
    protected String statusRezervacije;
    
    protected float cena;

    @ManyToOne(optional = false)
    protected Rezervacija rezervacija;

    @ManyToOne(optional = false)
    protected Soba soba;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatusRezervacije() {
		return statusRezervacije;
	}

	public void setStatusRezervacije(String statusRezervacije) {
		this.statusRezervacije = statusRezervacije;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}

}
