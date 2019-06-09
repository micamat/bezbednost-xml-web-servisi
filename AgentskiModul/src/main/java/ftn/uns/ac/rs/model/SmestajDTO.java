
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
 *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="slika" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="idLokacija" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idTipSmestaja" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idKategorijaSmestaja" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "naziv",
    "opis",
    "slika",
    "idLokacija",
    "idTipSmestaja",
    "idKategorijaSmestaja"
})
@XmlRootElement(name = "SmestajDTO")
public class SmestajDTO {

    protected Long id;
    @XmlElement(required = true)
    protected String naziv;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(required = true)
    protected String slika;
    protected long idLokacija;
    protected long idTipSmestaja;
    protected long idKategorijaSmestaja;

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
     * Gets the value of the idLokacija property.
     * 
     */
    public long getIdLokacija() {
        return idLokacija;
    }

    /**
     * Sets the value of the idLokacija property.
     * 
     */
    public void setIdLokacija(long value) {
        this.idLokacija = value;
    }

    /**
     * Gets the value of the idTipSmestaja property.
     * 
     */
    public long getIdTipSmestaja() {
        return idTipSmestaja;
    }

    /**
     * Sets the value of the idTipSmestaja property.
     * 
     */
    public void setIdTipSmestaja(long value) {
        this.idTipSmestaja = value;
    }

    /**
     * Gets the value of the idKategorijaSmestaja property.
     * 
     */
    public long getIdKategorijaSmestaja() {
        return idKategorijaSmestaja;
    }

    /**
     * Sets the value of the idKategorijaSmestaja property.
     * 
     */
    public void setIdKategorijaSmestaja(long value) {
        this.idKategorijaSmestaja = value;
    }

}
