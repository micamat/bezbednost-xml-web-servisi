//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.07 at 07:50:23 PM CEST 
//


package ftn.uns.ac.rs.SearchMicroservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="slika" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://app.microservice.search/Model}TipSobe"/>
 *         &lt;element ref="{http://app.microservice.search/Model}Smestaj"/>
 *         &lt;element ref="{http://app.microservice.search/Model}Usluga" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "naziv",
    "opis",
    "slika",
    "tipSobe",
    "smestaj",
    "usluga"
})
@XmlRootElement(name = "Soba")
@Entity
public class Soba {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @XmlElement(required = true)
    protected String naziv;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(required = true)
    protected String slika;
    @XmlElement(name = "TipSobe", required = true)
    @ManyToOne(optional = false)
    protected TipSobe tipSobe;
    @XmlElement(name = "Smestaj", required = true)
    @ManyToOne(optional = false)
    protected Smestaj smestaj;
    //@XmlElement(name = "Usluga", required = true)
    //@ManyToMany
    /*@JoinTable(
            name = "sobne_usluge",
            joinColumns = @JoinColumn(),
            inverseJoinColumns = @JoinColumn())*/
    @OneToMany(mappedBy = "soba")
    @JsonIgnore
    protected List<SobneUsluge> sobneUsluge;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the slika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlika() {
        return slika;
    }

    /**
     * Sets the value of the slika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlika(String value) {
        this.slika = value;
    }

    /**
     * Gets the value of the tipSobe property.
     * 
     * @return
     *     possible object is
     *     {@link TipSobe }
     *     
     */
    public TipSobe getTipSobe() {
        return tipSobe;
    }

    /**
     * Sets the value of the tipSobe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipSobe }
     *     
     */
    public void setTipSobe(TipSobe value) {
        this.tipSobe = value;
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

	public List<SobneUsluge> getSobneUsluge() {
		return sobneUsluge;
	}

	@Override
	public String toString() {
		return "Soba [id=" + id + ", naziv=" + naziv + ", opis=" + opis + ", slika=" + slika + ", tipSobe=" + tipSobe
				+ ", smestaj=" + smestaj + ", sobneUsluge=" + sobneUsluge + "]";
	}

    /**
     * Gets the value of the usluga property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usluga property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsluga().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Usluga }
     * 
     * 
     */
    /*public List<Usluga> getUsluga() {
        if (usluga == null) {
            usluga = new ArrayList<Usluga>();
        }
        return this.usluga;
    }*/
	
}
