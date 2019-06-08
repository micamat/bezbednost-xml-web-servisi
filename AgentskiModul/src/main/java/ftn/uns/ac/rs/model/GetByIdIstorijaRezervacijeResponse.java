
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}IstorijaRezervacijeDTO" minOccurs="0"/&gt;
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
    "istorijaRezervacijeDTO"
})
@XmlRootElement(name = "GetByIdIstorijaRezervacijeResponse")
public class GetByIdIstorijaRezervacijeResponse {

    @XmlElement(name = "IstorijaRezervacijeDTO")
    protected IstorijaRezervacijeDTO istorijaRezervacijeDTO;

    /**
     * Gets the value of the istorijaRezervacijeDTO property.
     * 
     * @return
     *     possible object is
     *     {@link IstorijaRezervacijeDTO }
     *     
     */
    public IstorijaRezervacijeDTO getIstorijaRezervacijeDTO() {
        return istorijaRezervacijeDTO;
    }

    /**
     * Sets the value of the istorijaRezervacijeDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link IstorijaRezervacijeDTO }
     *     
     */
    public void setIstorijaRezervacijeDTO(IstorijaRezervacijeDTO value) {
        this.istorijaRezervacijeDTO = value;
    }

}
