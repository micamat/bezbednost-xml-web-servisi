package ftn.uns.ac.rs.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;all&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}Long"/&gt;
 *         &lt;element name="naslov" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tekst" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="statusKomentara" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="datumKreiranja" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="nazivSmestaj" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="korisnickoImeKorisnik" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "ShowKomentarDTO")
public class ShowKomentarDTO {

    protected Long id;
    @XmlElement(required = true)
    protected String naslov;
    @XmlElement(required = true)
    protected String tekst;
    @XmlElement(required = true)
    protected String statusKomentara;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date datumKreiranja;
    protected String nazivSmestaj;
    protected String korisnickoImeKorisnik;

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
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the tekst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTekst() {
        return tekst;
    }

    /**
     * Sets the value of the tekst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTekst(String value) {
        this.tekst = value;
    }

    /**
     * Gets the value of the statusKomentara property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusKomentara() {
        return statusKomentara;
    }

    /**
     * Sets the value of the statusKomentara property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusKomentara(String value) {
        this.statusKomentara = value;
    }

    /**
     * Gets the value of the datumKreiranja property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    /**
     * Sets the value of the datumKreiranja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDatumKreiranja(Date value) {
        this.datumKreiranja = value;
    }

    /**
     * Gets the value of the idSmestaj property.
     * 
     */
    public String getNazivSmestaj() {
        return nazivSmestaj;
    }

    /**
     * Sets the value of the idSmestaj property.
     * 
     */
    public void setNazivSmestaj(String value) {
        this.nazivSmestaj = value;
    }

    /**
     * Gets the value of the idKorisnik property.
     * 
     */
    public String getKorisnickoImeKorisnik() {
        return korisnickoImeKorisnik;
    }

    /**
     * Sets the value of the idKorisnik property.
     * 
     */
    public void setKorisnickoImeKorisnik(String value) {
        this.korisnickoImeKorisnik = value;
    }

}

