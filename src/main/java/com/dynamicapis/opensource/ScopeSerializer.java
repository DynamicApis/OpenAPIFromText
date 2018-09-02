package com.dynamicapis.opensource;
import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
public class ScopeSerializer extends StdSerializer<SwaggerApi.SwaggerSecurityDefinition.Scope> {
    public ScopeSerializer(){
        this(null);
    }
    public ScopeSerializer(Class<SwaggerApi.SwaggerSecurityDefinition.Scope> t){
        super(t); 
    }
    
    @Override
    public void serialize(SwaggerApi.SwaggerSecurityDefinition.Scope value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonGenerationException {
    
        /*
        
        reading material
        
        https://www.concretepage.com/jackson-api/jackson-custom-serializer
        https://stackoverflow.com/questions/45132821/jackson-serialize-without-properties
        
        */
        jgen.writeStartObject();
        jgen.writeFieldName(value.getName());
        jgen.writeString("description");
        jgen.writeEndObject();
               
    }
}

