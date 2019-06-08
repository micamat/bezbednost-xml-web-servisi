
package ftn.uns.ac.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="cena" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Korisnik"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Soba"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Rezervacija"/&gt;
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
    "cena",
    "korisnik",
    "soba",
    "rezervacija"
})
@XmlRootElement(name = "RezervacijaPoSobi")
public class RezervacijaPoSobi {

    protected long id;
    protected float cena;
    @XmlElement(name = "Korisnik", required = true)
    protected Korisnik korisnik;
    @XmlElement(name = "Soba", required = true)
    protected Soba soba;
    @XmlElement(name = "Rezervacija", required = true)
    protected Rezervacija rezervacija;

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

    /**
     * Gets the value of the soba property.
     * 
     * @return
     *     possible object is
     *     {@link Soba }
     *     
     */
    public Soba getSoba() {
        return soba;
    }

    /**
     * Sets the value of the soba property.
     * 
     * @param value
     *     allowed object is
     *     {@link Soba }
     *     
     */
    public void setSoba(Soba value) {
        this.soba = value;
    }

    /**
     * Gets the value of the rezervacija property.
     * 
     * @return
     *     possible object is
     *     {@link Rezervacija }
     *     
     */
    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    /**
     * Sets the value of the rezervacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rezervacija }
     *     
     */
    public void setRezervacija(Rezervacija value) {
        this.rezervacija = value;
    }

}
