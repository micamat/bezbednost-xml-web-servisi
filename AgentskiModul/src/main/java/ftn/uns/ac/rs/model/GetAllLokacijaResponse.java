
package ftn.uns.ac.rs.model;

import java.util.ArrayList;
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}LokacijaDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "lokacijaDTO"
})
@XmlRootElement(name = "GetAllLokacijaResponse")
public class GetAllLokacijaResponse {

    @XmlElement(name = "LokacijaDTO")
    protected List<LokacijaDTO> lokacijaDTO;

    /**
     * Gets the value of the lokacijaDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lokacijaDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLokacijaDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LokacijaDTO }
     * 
     * 
     */
    public List<LokacijaDTO> getLokacijaDTO() {
        if (lokacijaDTO == null) {
            lokacijaDTO = new ArrayList<LokacijaDTO>();
        }
        return this.lokacijaDTO;
    }

}
