
package ftn.uns.ac.rs.model;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}TipSobe"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Smestaj"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Usluga" maxOccurs="unbounded"/&gt;
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
    "tipSobe",
    "smestaj",
    "usluga"
})
@XmlRootElement(name = "Soba")
public class Soba {

    protected long id;
    @XmlElement(required = true)
    protected String naziv;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(required = true)
    protected String slika;
    @XmlElement(name = "TipSobe", required = true)
    protected TipSobe tipSobe;
    @XmlElement(name = "Smestaj", required = true)
    protected Smestaj smestaj;
    @XmlElement(name = "Usluga", required = true)
    protected List<Usluga> usluga;

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
    public List<Usluga> getUsluga() {
        if (usluga == null) {
            usluga = new ArrayList<Usluga>();
        }
        return this.usluga;
    }

}
