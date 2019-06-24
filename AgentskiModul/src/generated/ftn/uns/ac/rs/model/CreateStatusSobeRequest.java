
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}StatusSobe"/&gt;
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
    "statusSobe"
})
@XmlRootElement(name = "CreateStatusSobeRequest")
public class CreateStatusSobeRequest {

    @XmlElement(name = "StatusSobe", required = true)
    protected StatusSobe statusSobe;

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
