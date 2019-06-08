
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
 *         &lt;element ref="{http://rs.ac.uns.ftn/Model}TipSmestajaDTO" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "tipSmestajaDTO"
})
@XmlRootElement(name = "GetAllTipSmestajaResponse")
public class GetAllTipSmestajaResponse {

    @XmlElement(name = "TipSmestajaDTO")
    protected List<TipSmestajaDTO> tipSmestajaDTO;

    /**
     * Gets the value of the tipSmestajaDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tipSmestajaDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipSmestajaDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipSmestajaDTO }
     * 
     * 
     */
    public List<TipSmestajaDTO> getTipSmestajaDTO() {
        if (tipSmestajaDTO == null) {
            tipSmestajaDTO = new ArrayList<TipSmestajaDTO>();
        }
        return this.tipSmestajaDTO;
    }

}
