package ftn.uns.ac.rs.model;

import java.util.List;

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
 *         &lt;element name="idRezervacija" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element ref="{http://www.w3.org/2001/XMLSchema}RezervisaneSobeDTO"/&gt;
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
    "idRezervacija",
    "rezervisaneSobeDTO"
})
@XmlRootElement(name = "RezervisaneSobeDTO")
public class UpdateRezervisaneSobeDTO {

    protected Long idRezervacija;
    @XmlElement(required = true)
    protected List<RezervisaneSobeDTO> rezervisaneSobeDTO;

    /**
     * Gets the value of the id property.
     * 
     */
    public Long getIdRezervacija() {
        return idRezervacija;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setIdRezervacija(Long value) {
        this.idRezervacija = value;
    }

    /**
     * Gets the value of the statusRezervacije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public List<RezervisaneSobeDTO> getRezervisaneSobeDTO() {
        return rezervisaneSobeDTO;
    }

    /**
     * Sets the value of the statusRezervacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRezervisaneSobeDTO(List<RezervisaneSobeDTO> value) {
        this.rezervisaneSobeDTO = value;
    }
}
