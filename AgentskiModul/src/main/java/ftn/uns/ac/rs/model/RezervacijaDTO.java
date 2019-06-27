//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 03:51:10 PM CEST 
//


package ftn.uns.ac.rs.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}Long"/&gt;
 *         &lt;element name="datumOd" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="datumDo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="cena" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="idKorisnika" type="{http://www.w3.org/2001/XMLSchema}Long"/&gt;
 *         &lt;element name="idSmestaj" type="{http://www.w3.org/2001/XMLSchema}Long"/&gt;
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}RezervisaneSobeDTO"/&gt;
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
    "datumOd",
    "datumDo",
    "cena",
    "idKorisnika",
    "idSmestaj",
    "rezervisaneSobeDTO"
})
@XmlRootElement(name = "RezervacijaDTO")
public class RezervacijaDTO {

    protected Long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date datumOd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date datumDo;
    protected float cena;
    protected Long idKorisnika;
    protected Long idSmestaj;
    @XmlElement(name = "RezervisaneSobeDTO", required = true)
    protected List<RezervisaneSobeDTO> rezervisaneSobeDTO;

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
     * Gets the value of the idKorisnika property.
     * 
     */
    public Long getIdKorisnika() {
        return idKorisnika;
    }

    /**
     * Sets the value of the idKorisnika property.
     * 
     */
    public void setIdKorisnika(Long value) {
        this.idKorisnika = value;
    }

    /**
     * Gets the value of the idSmestaj property.
     * 
     */
    public Long getIdSmestaj() {
        return idSmestaj;
    }

    /**
     * Sets the value of the idSmestaj property.
     * 
     */
    public void setIdSmestaj(Long value) {
        this.idSmestaj = value;
    }

    /**
     * Gets the value of the idSoba property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idSoba property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdSoba().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<RezervisaneSobeDTO> getRezervisaneSobeDTO() {
        if (rezervisaneSobeDTO == null) {
        	rezervisaneSobeDTO = new ArrayList<RezervisaneSobeDTO>();
        }
        return this.rezervisaneSobeDTO;
    }

}