
package ftn.uns.ac.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="datumOd" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="datumDo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="nazivSoba" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nazivStatusSobe" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "ShowZauzeceDTO")
public class ShowZauzeceDTO {

    protected long id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datumOd;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datumDo;
    @XmlElement(required = true)
    protected String nazivSoba;
    @XmlElement(required = true)
    protected String nazivStatusSobe;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the datumOd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumOd() {
        return datumOd;
    }

    /**
     * Sets the value of the datumOd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumOd(XMLGregorianCalendar value) {
        this.datumOd = value;
    }

    /**
     * Gets the value of the datumDo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumDo() {
        return datumDo;
    }

    /**
     * Sets the value of the datumDo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumDo(XMLGregorianCalendar value) {
        this.datumDo = value;
    }

    /**
     * Gets the value of the nazivSoba property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivSoba() {
        return nazivSoba;
    }

    /**
     * Sets the value of the nazivSoba property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivSoba(String value) {
        this.nazivSoba = value;
    }

    /**
     * Gets the value of the nazivStatusSobe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivStatusSobe() {
        return nazivStatusSobe;
    }

    /**
     * Sets the value of the nazivStatusSobe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivStatusSobe(String value) {
        this.nazivStatusSobe = value;
    }

}
