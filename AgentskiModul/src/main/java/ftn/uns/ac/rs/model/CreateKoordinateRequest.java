//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.26 at 12:44:11 AM CEST 
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Koordinate" minOccurs="0"/>
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
    "koordinate"
})
@XmlRootElement(name = "CreateKoordinateRequest")
public class CreateKoordinateRequest {

    @XmlElement(name = "Koordinate")
    protected Koordinate koordinate;

    /**
     * Gets the value of the koordinate property.
     * 
     * @return
     *     possible object is
     *     {@link Koordinate }
     *     
     */
    public Koordinate getKoordinate() {
        return koordinate;
    }

    /**
     * Sets the value of the koordinate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Koordinate }
     *     
     */
    public void setKoordinate(Koordinate value) {
        this.koordinate = value;
    }

}
