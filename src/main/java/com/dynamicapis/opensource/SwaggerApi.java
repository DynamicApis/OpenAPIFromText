package com.dynamicapis.opensource;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
public class SwaggerApi {
    
    private SwaggerSecurityDefinition[] securityDefinitions;
    private String route;
    
    public SwaggerSecurityDefinition[] getSecurityDefinitions(){
        return this.securityDefinitions;
    }
    
    public void setSecurityDefinitions(SwaggerSecurityDefinition[] definitions){
        this.securityDefinitions = definitions;
    }
    
    public String getRoute(){
        return this.route;
    }
    
    public void setRoute(String route){
        this.route = route;
    }
    
    public class SwaggerSecurityDefinition {
        
        private String type;
        private String flow;
        private String authorizationUrl;
        private Scope[] scopes;
        
        public String getType(){
            return this.type;
        }
        
        public void setType(String type){
            this.type = type;
        }
        
        public String getFlow(){
            return this.flow;
        }
        
        public void setFlow(String flow){
            this.flow = flow;
        }
        
        public String getAuthorizationUrl(){
            return this.authorizationUrl;
        }
        
        public void setAuthorizationUrl(String authorizationUrl){
            this.authorizationUrl = authorizationUrl;    
        }
        
        public Scope[] getScopes(){
            return this.scopes;
        }
        
        public void setScopes(Scope[] scopes){
            this.scopes = scopes;
        }
        
        // @Override
        // public String toString() {
        //     return String.format("{ \"{0}\", \"{1}\" }", this.name, this.description );
        // }
        
        @JsonSerialize(using=ScopeSerializer.class)
        public class Scope implements Serializable {
            
            private static final long serialVersionUID = 2926346626990185211L;
            
            private String name;
            private String description;
            
            public String getName(){
                return this.name;
            }
            public void setName(String name){
                this.name = name;
            }
            
            public String getDescription(){
                return this.description;
            }
            public void setDescription(String description){
                this.description = description;
            }
            
            // @Override
            // public String toString() {
            //     return String.format("{ \"{0}\", \"{1}\" }", this.name, this.description );
            // }
            
        }
        
    }
    
}

