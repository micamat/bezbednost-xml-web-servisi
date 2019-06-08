
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}KategorijaSmestajaDTO" minOccurs="0"/&gt;
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
    "kategorijaSmestajaDTO"
})
@XmlRootElement(name = "CreateKategorijaSmestajaRequest")
public class CreateKategorijaSmestajaRequest {

    @XmlElement(name = "KategorijaSmestajaDTO")
    protected KategorijaSmestajaDTO kategorijaSmestajaDTO;

    /**
     * Gets the value of the kategorijaSmestajaDTO property.
     * 
     * @return
     *     possible object is
     *     {@link KategorijaSmestajaDTO }
     *     
     */
    public KategorijaSmestajaDTO getKategorijaSmestajaDTO() {
        return kategorijaSmestajaDTO;
    }

    /**
     * Sets the value of the kategorijaSmestajaDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link KategorijaSmestajaDTO }
     *     
     */
    public void setKategorijaSmestajaDTO(KategorijaSmestajaDTO value) {
        this.kategorijaSmestajaDTO = value;
    }

}
