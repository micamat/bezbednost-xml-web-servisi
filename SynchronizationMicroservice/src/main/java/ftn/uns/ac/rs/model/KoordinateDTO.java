//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 09:50:54 AM CEST 
//


package ftn.uns.ac.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;all>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="duzina" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sirina" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
@XmlRootElement(name = "KoordinateDTO")
public class KoordinateDTO {

    protected long id;
    protected float duzina;
    protected float sirina;

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
     * Gets the value of the duzina property.
     * 
     */
    public float getDuzina() {
        return duzina;
    }

    /**
     * Sets the value of the duzina property.
     * 
     */
    public void setDuzina(float value) {
        this.duzina = value;
    }

    /**
     * Gets the value of the sirina property.
     * 
     */
    public float getSirina() {
        return sirina;
    }

    /**
     * Sets the value of the sirina property.
     * 
     */
    public void setSirina(float value) {
        this.sirina = value;
    }

}
