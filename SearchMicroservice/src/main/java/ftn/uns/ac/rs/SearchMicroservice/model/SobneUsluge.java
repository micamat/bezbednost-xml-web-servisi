package ftn.uns.ac.rs.SearchMicroservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SobneUsluge {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
	@ManyToOne(optional = false)
	protected Soba soba;
	@ManyToOne(optional = false)
	protected Usluga usluga;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Soba getSoba() {
		return soba;
	}
	public void setSoba(Soba soba) {
		this.soba = soba;
	}
	public Usluga getUsluga() {
		return usluga;
	}
	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}
	@Override
	public String toString() {
		return "SobneUsluge [id=" + id + ", soba=" + soba + ", usluga=" + usluga + "]";
	}

}
