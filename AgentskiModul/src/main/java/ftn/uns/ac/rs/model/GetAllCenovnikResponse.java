
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}CenovnikDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlRootElement(name = "GetAllCenovnikResponse")
public class GetAllCenovnikResponse {

    @XmlElement(name = "CenovnikDTO")
    protected List<CenovnikDTO> cenovnikDTO;

    /**
     * Gets the value of the cenovnikDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cenovnikDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCenovnikDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CenovnikDTO }
     * 
     * 
     */
    public List<CenovnikDTO> getCenovnikDTO() {
        if (cenovnikDTO == null) {
            cenovnikDTO = new ArrayList<CenovnikDTO>();
        }
        return this.cenovnikDTO;
    }

}
