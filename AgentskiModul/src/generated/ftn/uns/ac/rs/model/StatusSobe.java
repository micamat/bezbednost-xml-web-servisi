
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}Sifarnik"/&gt;
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
    "sifarnik"
})
@XmlRootElement(name = "StatusSobe")
public class StatusSobe {

    @XmlElement(name = "Sifarnik", required = true)
    protected Sifarnik sifarnik;

    /**
     * Gets the value of the sifarnik property.
     * 
     * @return
     *     possible object is
     *     {@link Sifarnik }
     *     
     */
    public Sifarnik getSifarnik() {
        return sifarnik;
    }

    /**
     * Sets the value of the sifarnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sifarnik }
     *     
     */
    public void setSifarnik(Sifarnik value) {
        this.sifarnik = value;
    }

}
