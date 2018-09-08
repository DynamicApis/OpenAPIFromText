package com.dynamicapis.opensource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "swagger",
    "info",
    "basePath",
    "schemes",
    "consumes",
    "produces",
    "security",
    "paths",
    "securityDefinitions",
    "definitions"
})
public class Swagger implements Serializable
{

    @JsonProperty("swagger")
    private String swagger;
    // @JsonProperty("info")
    // private Info info;
    @JsonProperty("basePath")
    private String basePath;
    // @JsonProperty("schemes")
    // private List<String> schemes = new ArrayList<String>();
    // @JsonProperty("consumes")
    // private List<String> consumes = new ArrayList<String>();
    // @JsonProperty("produces")
    // private List<String> produces = new ArrayList<String>();
    // @JsonProperty("security")
    // private List<Security> security = new ArrayList<Security>();
    // @JsonProperty("paths")
    // private Paths paths;
    @JsonProperty("securityDefinitions")
    private SecurityDefinitions securityDefinitions;
    // @JsonProperty("definitions")
    // private Definitions definitions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7164628938114868454L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Swagger() {
    }

    /**
     * 
     * @param basePath
     * @param paths
     * @param schemes
     * @param produces
     * @param swagger
     * @param definitions
     * @param security
     * @param consumes
     * @param securityDefinitions
     * @param info
     */
    public Swagger(String swagger, String basePath, SecurityDefinitions securityDefinitions) {
        super();
        this.swagger = swagger;
        this.basePath = basePath;
        this.securityDefinitions = securityDefinitions;
    }

    @JsonProperty("swagger")
    public String getSwagger() {
        return swagger;
    }

    @JsonProperty("swagger")
    public void setSwagger(String swagger) {
        this.swagger = swagger;
    }

    @JsonProperty("basePath")
    public String getBasePath() {
        return basePath;
    }

    @JsonProperty("basePath")
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    @JsonProperty("securityDefinitions")
    public SecurityDefinitions getSecurityDefinitions() {
        return securityDefinitions;
    }

    @JsonProperty("securityDefinitions")
    public void setSecurityDefinitions(SecurityDefinitions securityDefinitions) {
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
        return new ToStringBuilder(this).append("swagger", swagger).append("basePath", basePath).append("securityDefinitions", securityDefinitions).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(basePath).append(additionalProperties).append(swagger).append(securityDefinitions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Swagger) == false) {
            return false;
        }
        Swagger rhs = ((Swagger) other);
        return new EqualsBuilder().append(basePath, rhs.basePath).append(additionalProperties, rhs.additionalProperties).append(swagger, rhs.swagger).append(securityDefinitions, rhs.securityDefinitions).isEquals();
    }

}
