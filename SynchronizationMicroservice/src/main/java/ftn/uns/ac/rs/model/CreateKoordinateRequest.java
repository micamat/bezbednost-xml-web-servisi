//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 09:50:54 AM CEST 
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
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}KoordinateDTO" minOccurs="0"/>
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
    "koordinateDTO"
})
@XmlRootElement(name = "CreateKoordinateRequest")
public class CreateKoordinateRequest {

    @XmlElement(name = "KoordinateDTO")
    protected KoordinateDTO koordinateDTO;

    /**
     * Gets the value of the koordinateDTO property.
     * 
     * @return
     *     possible object is
     *     {@link KoordinateDTO }
     *     
     */
    public KoordinateDTO getKoordinateDTO() {
        return koordinateDTO;
    }

    /**
     * Sets the value of the koordinateDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link KoordinateDTO }
     *     
     */
    public void setKoordinateDTO(KoordinateDTO value) {
        this.koordinateDTO = value;
    }

}
