
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}CenovnikDTO"/&gt;
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
    "cenovnikDTO"
})
@XmlRootElement(name = "CreateCenovnikRequest")
public class CreateCenovnikRequest {

    @XmlElement(name = "CenovnikDTO", required = true)
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
