package com.dynamicapis.opensource;

import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Processor {
    
    private static final String NEWLINE = "\n";
    

    private InputParameters inputParameters;
    private String uuid;
    private String rawFile;
    private String tokenizedFile;
    
    public Processor(InputParameters inputParameters){
        this.inputParameters = inputParameters;
        this.uuid = "test";//UUID.randomUUID().toString();
        this.rawFile = "./"+this.uuid+"-raw.txt";
        this.tokenizedFile = "./"+this.uuid+"-raw-tokenized.txt";
    }
    
    // private void GenerateSample() throws RuntimeException, FileNotFoundException, IOException {
        
    //     List<SwaggerApi> apis = new ArrayList<SwaggerApi>();
        
    //     SwaggerApi api = new SwaggerApi();
    //     api.setRoute("/api/v1/something");
        
    //     List<SwaggerApi.SwaggerSecurityDefinition> securityDefinitions = new ArrayList<SwaggerApi.SwaggerSecurityDefinition>();
        
    //     SwaggerApi.SwaggerSecurityDefinition securityDefinition = api.new SwaggerSecurityDefinition();
    //     securityDefinition.setType("oauth");
        
        
    //     List<SwaggerApi.SwaggerSecurityDefinition.Scope> scopes = new ArrayList<SwaggerApi.SwaggerSecurityDefinition.Scope>();
    //     SwaggerApi.SwaggerSecurityDefinition.Scope scope = securityDefinition.new Scope();
    //     scope.setName("readaccess");
    //     scope.setDescription("sets the read access");
        
    //     scopes.add(scope);
        
    //     SwaggerApi.SwaggerSecurityDefinition.Scope[] swaggerApiSecDefScopesArr = new SwaggerApi.SwaggerSecurityDefinition.Scope[scopes.size()];
    //     swaggerApiSecDefScopesArr = scopes.toArray(swaggerApiSecDefScopesArr);
        
    //     securityDefinition.setScopes(swaggerApiSecDefScopesArr);
        
    //     securityDefinitions.add(securityDefinition);
        
        
    //     SwaggerApi.SwaggerSecurityDefinition[] swaggerApiSecDefArr = new SwaggerApi.SwaggerSecurityDefinition[securityDefinitions.size()];
    //     swaggerApiSecDefArr = securityDefinitions.toArray(swaggerApiSecDefArr);
        
        
    //     api.setSecurityDefinitions(swaggerApiSecDefArr);
        
    //     apis.add(api);
        
    //     SwaggerApi[] swaggerApiArr = new SwaggerApi[apis.size()];
    //     swaggerApiArr = apis.toArray(swaggerApiArr);
        
    //     this.WriteObjectToFile(inputParameters.getOutputFile(),swaggerApiArr);
        
    //     return;
    // }
    
    // public void GetSample() throws IOException {
        
    //     File outputFile = new File("./test.txt");
    //     FileWriter outputWriter = new FileWriter(outputFile, false);
        
        
    //     Swagger swagger = new Swagger();
    //     swagger.setSwagger("2.0");
    //     swagger.setBasePath("https://www.dynamicapis.com");
        
        
    //     // //String type, Scopes scopes, String flow, String authorizationUrl, String tokenUrl, String in, String name
    //     // SecurityDefinition definition = new SecurityDefinition("oauth2", null,"implicit","https://www.dynamicapis.com",null,null,null);
       
    //     // SecurityDefinition[] swaggerSecDefArr = new SecurityDefinition[1];
    //     // swaggerSecDefArr[0] = definition;
        
    //     //definitions.setSecurityDefinitions(swaggerSecDefArr);
        
    //     swagger.setSecurityDefinitions(new SecurityDefinitions());
        
    //     System.out.println(swagger.toString());
        
    //     com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //     String objectAsJsonString = objectMapper.writeValueAsString(swagger);
    //     outputWriter.write(objectAsJsonString);
    //     outputWriter.close();
        
    // }
    
    public void Generate() throws RuntimeException, FileNotFoundException, IOException {
        
        //first
        this.GatherAllApplicableComments();
        
        //new input file to work with is the this.rawFile
        this.TokenizeRawData();
        
        
        //this.GenerateSample();
        
        
//         List<String> fileList = this.obtainListOfFilesFromDirectory();        
        
//         StringBuilder sb = new StringBuilder();
        
//         for (String filePathAsString : fileList) {
//             this.obtainRawInput(sb,filePathAsString);
// 		}
		
// 		//System.out.println(sb.toString());
// 		this.WriteToFile(inputParameters.getOutputFile(), sb);
            
//         return;
    }
    
    private void TokenizeRawData() throws FileNotFoundException, IOException{
        Map<String,Object> tokenizedMap = new TreeMap<String,Object>();
        
        String sourcecode = this.readEntireFile(this.rawFile);
                
        BufferedReader bufReader = new BufferedReader(new StringReader(sourcecode));
        String line = null;
        while( (line=bufReader.readLine()) != null )
        {
            
            String mapKey = null;
            
            //get the key
            String regexString = "(?!\\[)(.*)(?=\\()";
            Pattern pattern = Pattern.compile(regexString);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                if(matcher.start()==1){
                    mapKey = matcher.group(0);
                }
            }
            
            if(mapKey != null){
                System.out.println("Will work on "+ mapKey);
                
                Map<String,String> objectMap = this.TokenProperties(line);
                
                tokenizedMap.put(mapKey,objectMap);
            }
            
        }
        
        
        
        this.WriteToFile(this.tokenizedFile, tokenizedMap.toString());
        
    }
    
    private Map<String,String> TokenProperties(String properties){
        Map<String,String> objectMap = new HashMap<String,String>();
        
        String regexString = "(?<=\\()(.*)(?=\\))";
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(properties);
        while (matcher.find()) {
          
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group(1));
            
            //split by comma but not the comma within the value
            String[] tokens = matcher.group(1).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            for(String token : tokens){
                
                //now split on equals
                String[] splitKvP = token.split("=(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1);
                
                String key = splitKvP[0].trim();
                String value = splitKvP[1].trim();
                
                objectMap.put(key,value);    
                
            }
            
        }
        
        return objectMap;
    }
    
    private void GatherAllApplicableComments() throws FileNotFoundException, IOException {
        List<String> fileList = this.obtainListOfFilesFromDirectory();
        StringBuilder sb = new StringBuilder();
                for (String filePathAsString : fileList) {
            this.obtainRawInput(sb,filePathAsString);
		}
		this.WriteToFile(this.rawFile, sb);
    }
    
    // private void WriteObjectToFile(String outputFileAbsolutePath, SwaggerApi[] apis) throws IOException{
    //     File outputFile = new File(outputFileAbsolutePath);
    //     FileWriter outputWriter = new FileWriter(outputFile, false); // true to append
    //                                                          // false to overwrite.
        
    //     String objectAsJsonString = "";
        
    //     com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //     objectAsJsonString = objectMapper.writeValueAsString(apis);
        
    //     outputWriter.write(objectAsJsonString);
    //     outputWriter.close();
    // }
    
    private void WriteToFile(String outputFileAbsolutePath, StringBuilder content) throws IOException{
        File outputFile = new File(outputFileAbsolutePath);
        FileWriter outputWriter = new FileWriter(outputFile, false); // true to append
                                                             // false to overwrite.
        outputWriter.write(content.toString());
        
        // String objectAsJsonString = "";
        
        // com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        // objectAsJsonString = objectMapper.writeValueAsString(this.inputParameters);
        
        // outputWriter.write(objectAsJsonString);
        outputWriter.close();
    }
    
    private void WriteToFile(String outputFileAbsolutePath, String outputToWrite) throws IOException{
        File outputFile = new File(outputFileAbsolutePath);
        FileWriter outputWriter = new FileWriter(outputFile, false); // true to append
                                                        // false to overwrite.
        outputWriter.write(outputToWrite);
        outputWriter.close();
    }
    
    private List<String> obtainListOfFilesFromDirectory() throws IOException {
        
        String directoryPath = this.inputParameters.getInputDirectory();
        
        List<String> list = new ArrayList<String>();
        
        Files.walk(Paths.get(directoryPath)).filter(Files::isRegularFile).forEach((Path path)->list.add(path.toAbsolutePath().toString()));
                
        return list;
    }
    

    private void obtainRawInput(StringBuilder sb, String fullyQualifiedFilePath) throws FileNotFoundException, IOException{
        
        String sourcecode = this.readEntireFile(fullyQualifiedFilePath);
                
        BufferedReader bufReader = new BufferedReader(new StringReader(sourcecode));
        String line = null;
        while( (line=bufReader.readLine()) != null )
        {
            //TODO: Replace this with REGEX
            //Phase 1 must be on same line
            if((line.toLowerCase().contains("[swaggersecuritydefinition(") ||
                line.toLowerCase().contains("[swaggerroute(") ||
                    line.toLowerCase().contains("[swaggeroperation(") ||
                        line.toLowerCase().contains("[swaggerinput(") ||
                            line.toLowerCase().contains("[swaggerresponse(") ||
                                line.toLowerCase().contains("[swaggerresponseheader(") ||
                                    line.toLowerCase().contains("[swaggerobject(") ||
                                        line.toLowerCase().contains("[swaggerobjectproperty(")) && line.toLowerCase().contains(")]")){
                sb.append(line);
                sb.append(NEWLINE);
            }
        }
        
    }    
    
    
    private String readEntireFile(String fullyQualifiedFilePath) throws FileNotFoundException {
        
        String entireFileText = new Scanner(new File(fullyQualifiedFilePath))
            .useDelimiter("\\A").next();
                
        return entireFileText;
        
    }
    
}