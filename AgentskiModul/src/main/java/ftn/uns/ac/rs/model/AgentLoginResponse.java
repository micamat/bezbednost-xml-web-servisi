package ftn.uns.ac.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "token"
})
@XmlRootElement(name = "CreateCenovnikResponse")
public class AgentLoginResponse {

    protected String token;

    /**
     * Gets the value of the id property.
     * 
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setToken(String value) {
        this.token = value;
    }


}
