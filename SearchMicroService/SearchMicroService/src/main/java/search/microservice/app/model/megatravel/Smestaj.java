//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.30 at 03:00:16 PM CEST 
//


package search.microservice.app.model.megatravel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="slika" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}Lokacija"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}TipSmestaja"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}KategorijaSmestaja"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}Soba" maxOccurs="unbounded"/>
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
    "lokacija",
    "tipSmestaja",
    "kategorijaSmestaja",
    "soba"
})
@XmlRootElement(name = "Smestaj")
@Entity
public class Smestaj {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @XmlElement(required = true)
    @Column(nullable = false)
    protected String naziv;
    @XmlElement(required = true)
    @Column(nullable = false)
    protected String opis;
    @XmlElement(required = true)
    @Column(nullable = false)
    protected String slika;
    @XmlElement(name = "Lokacija", required = true)
    @OneToOne(optional = false)
    protected Lokacija lokacija;
    @XmlElement(name = "TipSmestaja", required = true)
    @ManyToOne(optional = false)
    protected TipSmestaja tipSmestaja;
    @XmlElement(name = "KategorijaSmestaja", required = true)
    @ManyToOne(optional = false)
    protected KategorijaSmestaja kategorijaSmestaja;
    @XmlElement(name = "Soba", required = true)
    @OneToMany(mappedBy = "smestaj")
    protected List<Soba> soba;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
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
