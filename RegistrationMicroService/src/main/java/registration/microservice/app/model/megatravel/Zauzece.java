//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.30 at 03:00:16 PM CEST 
//


package registration.microservice.app.model.megatravel;

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
 *         &lt;element name="datumOd" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="datumDo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}Soba"/>
 *         &lt;element ref="{http://model.app.microservice.search/MegaTravel}StatusSobe"/>
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
@XmlRootElement(name = "zauzece")
@Entity
public class Zauzece {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @Column(nullable = false)
    protected Date datumOd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @Column(nullable = false)
    protected Date datumDo;
    @XmlElement(name = "Soba", required = true)
    @ManyToOne(optional = false)
    protected Soba soba;
    @XmlElement(name = "StatusSobe", required = true)
    @ManyToOne(optional = false)
    protected StatusSobe statusSobe;

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
     * Gets the value of the statusSobe property.
     * 
     * @return
     *     possible object is
     *     {@link StatusSobe }
     *     
     */
    public StatusSobe getStatusSobe() {
        return statusSobe;
    }

    /**
     * Sets the value of the statusSobe property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusSobe }
     *     
     */
    public void setStatusSobe(StatusSobe value) {
        this.statusSobe = value;
    }

}
