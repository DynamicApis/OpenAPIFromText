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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "scopes",
    "flow",
    "authorizationUrl",
    "tokenUrl",
    "in",
    "name"
})
public class SecurityDefinition implements Serializable
{

    @JsonProperty("type")
    private String type;
    @JsonProperty("scopes")
    private Scopes scopes;
    @JsonProperty("flow")
    private String flow;
    @JsonProperty("authorizationUrl")
    private String authorizationUrl;
    @JsonProperty("tokenUrl")
    private String tokenUrl;
    @JsonProperty("in")
    private String in;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 623685347394327272L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SecurityDefinition() {
    }

    /**
     * 
     * @param scopes
     * @param authorizationUrl
     * @param flow
     * @param type
     * @param tokenUrl
     */
    public SecurityDefinition(String type, Scopes scopes, String flow, String authorizationUrl, String tokenUrl, String in, String name) {
        super();
        this.type = type;
        this.scopes = scopes;
        this.flow = flow;
        this.authorizationUrl = authorizationUrl;
        this.tokenUrl = tokenUrl;
        this.in = in;
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("scopes")
    public Scopes getScopes() {
        return scopes;
    }

    @JsonProperty("scopes")
    public void setScopes(Scopes scopes) {
        this.scopes = scopes;
    }

    @JsonProperty("flow")
    public String getFlow() {
        return flow;
    }

    @JsonProperty("flow")
    public void setFlow(String flow) {
        this.flow = flow;
    }
    
    @JsonProperty("in")
    public String getIn() {
        return in;
    }
    @JsonProperty("in")
    public void setIn(String in) {
        this.in = in;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }



    @JsonProperty("authorizationUrl")
    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    @JsonProperty("authorizationUrl")
    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    @JsonProperty("tokenUrl")
    public String getTokenUrl() {
        return tokenUrl;
    }

    @JsonProperty("tokenUrl")
    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    /*@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

    // @Override
    // public String toString() {
    //     return new ToStringBuilder(this).append("type", type).append("scopes", scopes).append("flow", flow).append("authorizationUrl", authorizationUrl).append("tokenUrl", tokenUrl).append("additionalProperties", additionalProperties).append("in", in).append("name", name).toString();
    // }

    // @Override
    // public int hashCode() {
    //     return new HashCodeBuilder().append(scopes).append(authorizationUrl).append(additionalProperties).append(flow).append(type).append(tokenUrl).append(in).append(name).toHashCode();
    // }

    // @Override
    // public boolean equals(Object other) {
    //     if (other == this) {
    //         return true;
    //     }
    //     if ((other instanceof SecurityDefinition) == false) {
    //         return false;
    //     }
    //     SecurityDefinition rhs = ((SecurityDefinition) other);
    //     return new EqualsBuilder().append(scopes, rhs.scopes).append(authorizationUrl, rhs.authorizationUrl).append(additionalProperties, rhs.additionalProperties).append(flow, rhs.flow).append(type, rhs.type).append(tokenUrl, rhs.tokenUrl).isEquals();
    // }

}
