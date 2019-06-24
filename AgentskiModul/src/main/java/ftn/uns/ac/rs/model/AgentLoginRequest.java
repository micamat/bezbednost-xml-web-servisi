package ftn.uns.ac.rs.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "agentLoginDTO"
})
@XmlRootElement(name = "CreateCenovnikRequest")
public class AgentLoginRequest {

    @XmlElement(name = "AgentLoginDTO", required = true)
    protected AgentLoginDTO agentLoginDTO;

    /**
     * Gets the value of the cenovnikDTO property.
     * 
     * @return
     *     possible object is
     *     {@link CenovnikDTO }
     *     
     */
    public AgentLoginDTO getAgentLoginDTO() {
        return agentLoginDTO;
    }

    /**
     * Sets the value of the cenovnikDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link CenovnikDTO }
     *     
     */
    public void setAgentLoginDTO(AgentLoginDTO value) {
        this.agentLoginDTO = value;
    }
}
