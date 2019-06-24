
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}ZauzeceDTO"/&gt;
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
    "zauzeceDTO"
})
@XmlRootElement(name = "CreateZauzeceRequest")
public class CreateZauzeceRequest {

    @XmlElement(name = "ZauzeceDTO", required = true)
    protected ZauzeceDTO zauzeceDTO;

    /**
     * Gets the value of the zauzeceDTO property.
     * 
     * @return
     *     possible object is
     *     {@link ZauzeceDTO }
     *     
     */
    public ZauzeceDTO getZauzeceDTO() {
        return zauzeceDTO;
    }

    /**
     * Sets the value of the zauzeceDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZauzeceDTO }
     *     
     */
    public void setZauzeceDTO(ZauzeceDTO value) {
        this.zauzeceDTO = value;
    }

}
