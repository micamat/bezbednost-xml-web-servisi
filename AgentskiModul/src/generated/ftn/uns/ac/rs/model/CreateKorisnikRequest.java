
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}KorisnikDTO" minOccurs="0"/&gt;
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
    "korisnikDTO"
})
@XmlRootElement(name = "CreateKorisnikRequest")
public class CreateKorisnikRequest {

    @XmlElement(name = "KorisnikDTO")
    protected KorisnikDTO korisnikDTO;

    /**
     * Gets the value of the korisnikDTO property.
     * 
     * @return
     *     possible object is
     *     {@link KorisnikDTO }
     *     
     */
    public KorisnikDTO getKorisnikDTO() {
        return korisnikDTO;
    }

    /**
     * Sets the value of the korisnikDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link KorisnikDTO }
     *     
     */
    public void setKorisnikDTO(KorisnikDTO value) {
        this.korisnikDTO = value;
    }

}
