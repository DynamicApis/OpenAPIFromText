package com.dynamicapis.opensource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

// @JsonInclude(JsonInclude.Include.NON_NULL)
// @JsonPropertyOrder({
//     "securityDefinition"
// })
public class SecurityDefinitions implements Serializable
{

    @JsonProperty("securityDefinition")
    private SecurityDefinition securityDefinitions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7161787010071360299L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SecurityDefinitions() {
    }


    @JsonProperty("securityDefinitions")
    public SecurityDefinition getSecurityDefinitions() {
        return securityDefinitions;
    }

    @JsonProperty("securityDefinitions")
    public void setSecurityDefinitions(SecurityDefinition securityDefinitions) {
        this.securityDefinitions = securityDefinitions;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("securityDefinitions", securityDefinitions).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(additionalProperties).append(securityDefinitions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SecurityDefinitions) == false) {
            return false;
        }
        SecurityDefinitions rhs = ((SecurityDefinitions) other);
        return new EqualsBuilder().append(additionalProperties, rhs.additionalProperties).append(securityDefinitions, rhs.securityDefinitions).isEquals();
    }

}
