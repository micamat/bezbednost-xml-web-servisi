
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Lokacija"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}TipSmestaja"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}KategorijaSmestaja"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Soba" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "lokacija",
    "tipSmestaja",
    "kategorijaSmestaja",
    "soba"
})
@XmlRootElement(name = "Smestaj")
public class Smestaj {

    protected long id;
    @XmlElement(required = true)
    protected String naziv;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(required = true)
    protected String slika;
    @XmlElement(name = "Lokacija", required = true)
    protected Lokacija lokacija;
    @XmlElement(name = "TipSmestaja", required = true)
    protected TipSmestaja tipSmestaja;
    @XmlElement(name = "KategorijaSmestaja", required = true)
    protected KategorijaSmestaja kategorijaSmestaja;
    @XmlElement(name = "Soba")
    protected List<Soba> soba;

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
     * Gets the value of the lokacija property.
     * 
     * @return
     *     possible object is
     *     {@link Lokacija }
     *     
     */
    public Lokacija getLokacija() {
        return lokacija;
    }

    /**
     * Sets the value of the lokacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link Lokacija }
     *     
     */
    public void setLokacija(Lokacija value) {
        this.lokacija = value;
    }

    /**
     * Gets the value of the tipSmestaja property.
     * 
     * @return
     *     possible object is
     *     {@link TipSmestaja }
     *     
     */
    public TipSmestaja getTipSmestaja() {
        return tipSmestaja;
    }

    /**
     * Sets the value of the tipSmestaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipSmestaja }
     *     
     */
    public void setTipSmestaja(TipSmestaja value) {
        this.tipSmestaja = value;
    }

    /**
     * Gets the value of the kategorijaSmestaja property.
     * 
     * @return
     *     possible object is
     *     {@link KategorijaSmestaja }
     *     
     */
    public KategorijaSmestaja getKategorijaSmestaja() {
        return kategorijaSmestaja;
    }

    /**
     * Sets the value of the kategorijaSmestaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link KategorijaSmestaja }
     *     
     */
    public void setKategorijaSmestaja(KategorijaSmestaja value) {
        this.kategorijaSmestaja = value;
    }

    /**
     * Gets the value of the soba property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soba property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoba().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Soba }
     * 
     * 
     */
    public List<Soba> getSoba() {
        if (soba == null) {
            soba = new ArrayList<Soba>();
        }
        return this.soba;
    }

}
