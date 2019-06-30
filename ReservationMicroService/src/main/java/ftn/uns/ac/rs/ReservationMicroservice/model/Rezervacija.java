//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 03:51:10 PM CEST 
//


package ftn.uns.ac.rs.ReservationMicroservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="datumOd" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="datumDo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="brojSoba" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="cena" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Smestaj"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Poruka" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Korisnik"/&gt;
 &lt;element ref="{http://rs.ac.uns.ftn/Model}RezervisaneSobe" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "datumOd",
    "datumDo",
    "brojSoba",
    "cena",
    "smestaj",
    "poruka",
    "korisnik",
    "rezervisaneSobe"
})
@XmlRootElement(name = "Rezervacija")
@Entity
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date datumOd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date datumDo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "int")
    protected int brojSoba;
    protected float cena;
    @XmlElement(name = "Smestaj", required = true)
    @ManyToOne(optional = false)
    protected Smestaj smestaj;
    @XmlElement(name = "Poruka", required = true)
    @OneToMany(mappedBy = "rezervacija")
    protected List<Poruka> poruka;
    @XmlElement(name = "Korisnik", required = true)
    @ManyToOne(optional = true)
    @JoinColumn(nullable = true)
    protected Korisnik korisnik;
    @XmlElement(name = "RezervisaneSobe", required = true)
    @OneToMany(mappedBy = "rezervacija")
    protected List<RezervisaneSobe> rezervisaneSobe;

    /**
     * Gets the value of the id property.
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the datumOd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDatumOd() {
        return datumOd;
    }

    /**
     * Sets the value of the datumOd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumOd(Date value) {
        this.datumOd = value;
    }

    /**
     * Gets the value of the datumDo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDatumDo() {
        return datumDo;
    }

    /**
     * Sets the value of the datumDo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumDo(Date value) {
        this.datumDo = value;
    }

    /**
     * Gets the value of the cena property.
     * 
     */
    public float getCena() {
        return cena;
    }

    /**
     * Sets the value of the cena property.
     * 
     */
    public void setCena(float value) {
        this.cena = value;
    }

    /**
     * Gets the value of the smestaj property.
     * 
     * @return
     *     possible object is
     *     {@link Smestaj }
     *     
     */
    public Smestaj getSmestaj() {
        return smestaj;
    }

    /**
     * Sets the value of the smestaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Smestaj }
     *     
     */
    public void setSmestaj(Smestaj value) {
        this.smestaj = value;
    }

    /**
     * Gets the value of the poruka property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the poruka property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPoruka().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Poruka }
     * 
     * 
     */
    public List<Poruka> getPoruka() {
        if (poruka == null) {
            poruka = new ArrayList<Poruka>();
        }
        return this.poruka;
    }

    /**
     * Gets the value of the korisnik property.
     * 
     * @return
     *     possible object is
     *     {@link Korisnik }
     *     
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Sets the value of the korisnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link Korisnik }
     *     
     */
    public void setKorisnik(Korisnik value) {
        this.korisnik = value;
    }

	public List<RezervisaneSobe> getRezervisaneSobe() {
		return rezervisaneSobe;
	}

	public void setRezervisaneSobe(List<RezervisaneSobe> rezervisaneSobe) {
		this.rezervisaneSobe = rezervisaneSobe;
	}

	public void setPoruka(List<Poruka> poruka) {
		this.poruka = poruka;
	}

	public int getBrojSoba() {
		return brojSoba;
	}

	public void setBrojSoba(int brojSoba) {
		this.brojSoba = brojSoba;
	}
	

    
}
