
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
 *         &lt;element name="idSoba" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idRezervacija" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "idSoba",
    "idRezervacija"
})
@XmlRootElement(name = "RezervacijaPoSobiDTO")
public class RezervacijaPoSobiDTO {

    protected long id;
    protected float cena;
    @XmlElement(name = "Korisnik", required = true)
    protected Korisnik korisnik;
    protected long idSoba;
    protected long idRezervacija;

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
     * Gets the value of the idSoba property.
     * 
     */
    public long getIdSoba() {
        return idSoba;
    }

    /**
     * Sets the value of the idSoba property.
     * 
     */
    public void setIdSoba(long value) {
        this.idSoba = value;
    }

    /**
     * Gets the value of the idRezervacija property.
     * 
     */
    public long getIdRezervacija() {
        return idRezervacija;
    }

    /**
     * Sets the value of the idRezervacija property.
     * 
     */
    public void setIdRezervacija(long value) {
        this.idRezervacija = value;
    }

}
