//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.30 at 03:00:16 PM CEST 
//


package ftn.uns.ac.rs.ReservationMicroservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
 *       &lt;all>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}ID"/>
 *         &lt;element name="cena" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datumOd" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="datumDo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}Smestaj"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}TipSobe"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Cenovnik")
@Entity
public class Cenovnik {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @XmlElement(required = true)
    @Column(nullable = false)
    protected String cena;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @Column(nullable = false)
    protected Date datumOd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @Column(nullable = false)
    protected Date datumDo;
    @XmlElement(name = "Smestaj", required = true)
    @ManyToOne(optional = false)
    protected Smestaj smestaj;
    @XmlElement(name = "TipSobe", required = true)
    @ManyToOne(optional = false)
    protected TipSobe tipSobe;

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
     * Gets the value of the cena property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCena() {
        return cena;
    }

    /**
     * Sets the value of the cena property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCena(String value) {
        this.cena = value;
    }

    /**
     * Gets the value of the datumOd property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
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
     *     {@link Date }
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
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setDatumDo(Date value) {
        this.datumDo = value;
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

}
