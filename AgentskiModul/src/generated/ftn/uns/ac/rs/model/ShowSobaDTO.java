
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
 *         &lt;element name="nazivTipSobe" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="nazivSmestaj" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "nazivTipSobe",
    "nazivSmestaj"
})
@XmlRootElement(name = "ShowSobaDTO")
public class ShowSobaDTO {

    protected long id;
    @XmlElement(required = true)
    protected String naziv;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(required = true)
    protected String slika;
    protected long nazivTipSobe;
    protected long nazivSmestaj;

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
     * Gets the value of the nazivTipSobe property.
     * 
     */
    public long getNazivTipSobe() {
        return nazivTipSobe;
    }

    /**
     * Sets the value of the nazivTipSobe property.
     * 
     */
    public void setNazivTipSobe(long value) {
        this.nazivTipSobe = value;
    }

    /**
     * Gets the value of the nazivSmestaj property.
     * 
     */
    public long getNazivSmestaj() {
        return nazivSmestaj;
    }

    /**
     * Sets the value of the nazivSmestaj property.
     * 
     */
    public void setNazivSmestaj(long value) {
        this.nazivSmestaj = value;
    }

}
