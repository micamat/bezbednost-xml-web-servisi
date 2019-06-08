
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}RezervacijaPoSobiDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "rezervacijaPoSobiDTO"
})
@XmlRootElement(name = "GetAllRezervacijaPoSobiResponse")
public class GetAllRezervacijaPoSobiResponse {

    @XmlElement(name = "RezervacijaPoSobiDTO")
    protected List<RezervacijaPoSobiDTO> rezervacijaPoSobiDTO;

    /**
     * Gets the value of the rezervacijaPoSobiDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rezervacijaPoSobiDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRezervacijaPoSobiDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RezervacijaPoSobiDTO }
     * 
     * 
     */
    public List<RezervacijaPoSobiDTO> getRezervacijaPoSobiDTO() {
        if (rezervacijaPoSobiDTO == null) {
            rezervacijaPoSobiDTO = new ArrayList<RezervacijaPoSobiDTO>();
        }
        return this.rezervacijaPoSobiDTO;
    }

}
