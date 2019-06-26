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
 *         &lt;element name="token" type="xs:string"/&gt;
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
    "token"
})
@XmlRootElement(name = "ValidateTokenRequest")
public class ValidateTokenRequest {

    @XmlElement(name = "token", required = true)
    protected String token;

    /**
     * Gets the value of the agentDTO property.
     * 
     * @return
     *     possible object is
     *     {@link AgentDTO }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the agentDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgentDTO }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

}

