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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}CenovnikDTO" minOccurs="0"/>
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
    "cenovnikDTO"
})
@XmlRootElement(name = "CreateCenovnikRequest")
public class CreateCenovnikRequest {

    @XmlElement(name = "CenovnikDTO")
    protected CenovnikDTO cenovnikDTO;

    /**
     * Gets the value of the cenovnikDTO property.
     * 
     * @return
     *     possible object is
     *     {@link CenovnikDTO }
     *     
     */
    public CenovnikDTO getCenovnikDTO() {
        return cenovnikDTO;
    }

    /**
     * Sets the value of the cenovnikDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CenovnikDTO }
     *     
     */
    public void setCenovnikDTO(CenovnikDTO value) {
        this.cenovnikDTO = value;
    }

}
