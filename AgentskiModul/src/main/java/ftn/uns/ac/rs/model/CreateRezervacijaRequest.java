//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 03:51:10 PM CEST 
//


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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}RezervacijaDTO"/&gt;
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
    "rezervacijaDTO"
})
@XmlRootElement(name = "CreateRezervacijaRequest")
public class CreateRezervacijaRequest {

    @XmlElement(name = "RezervacijaDTO", required = true)
    protected RezervacijaDTO rezervacijaDTO;

    /**
     * Gets the value of the rezervacijaDTO property.
     * 
     * @return
     *     possible object is
     *     {@link RezervacijaDTO }
     *     
     */
    public RezervacijaDTO getRezervacijaDTO() {
        return rezervacijaDTO;
    }

    /**
     * Sets the value of the rezervacijaDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link RezervacijaDTO }
     *     
     */
    public void setRezervacijaDTO(RezervacijaDTO value) {
        this.rezervacijaDTO = value;
    }

}
