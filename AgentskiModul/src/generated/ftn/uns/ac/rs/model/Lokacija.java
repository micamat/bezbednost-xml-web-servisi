
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
 *       &lt;all&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="drzava" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="grad" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ulica" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="broj" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="duzina" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="sirina" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
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
@XmlRootElement(name = "Lokacija")
public class Lokacija {

    protected long id;
    @XmlElement(required = true)
    protected String drzava;
    @XmlElement(required = true)
    protected String grad;
    @XmlElement(required = true)
    protected String ulica;
    @XmlElement(required = true)
    protected String broj;
    protected float duzina;
    protected float sirina;

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
     * Gets the value of the drzava property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrzava() {
        return drzava;
    }

    /**
     * Sets the value of the drzava property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrzava(String value) {
        this.drzava = value;
    }

    /**
     * Gets the value of the grad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrad() {
        return grad;
    }

    /**
     * Sets the value of the grad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrad(String value) {
        this.grad = value;
    }

    /**
     * Gets the value of the ulica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUlica() {
        return ulica;
    }

    /**
     * Sets the value of the ulica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUlica(String value) {
        this.ulica = value;
    }

    /**
     * Gets the value of the broj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroj(String value) {
        this.broj = value;
    }

    /**
     * Gets the value of the duzina property.
     * 
     */
    public float getDuzina() {
        return duzina;
    }

    /**
     * Sets the value of the duzina property.
     * 
     */
    public void setDuzina(float value) {
        this.duzina = value;
    }

    /**
     * Gets the value of the sirina property.
     * 
     */
    public float getSirina() {
        return sirina;
    }

    /**
     * Sets the value of the sirina property.
     * 
     */
    public void setSirina(float value) {
        this.sirina = value;
    }

}
