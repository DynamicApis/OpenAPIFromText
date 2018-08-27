import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class openapift {
    
    private static InputParameters inputParameters;
    
    public static void main(String[] args) throws IllegalArgumentException, RuntimeException, FileNotFoundException, IOException {
        
        //expect the following args
        // "codecomments" DEFAULT true, which will be a bool but accept true, yes, 1; else it will be considered false
        // "input", which will be the directory to begin recursively checking
        // "output" DEFAULT 'openapidoc.json' in root of input directory, which will be the file to save the output to
        ParseInputParameters(args);        
        
        Processor processor = new Processor(inputParameters);
        processor.Generate();
        
        System.out.println("Program Complete.");

   }
   
   public static void ParseInputParameters(String[] args) throws IllegalArgumentException {
       
        inputParameters = new InputParameters();
        
        CommandOptions cmd = new CommandOptions(args);

        if(cmd.hasOption("-codecomments")){
            
            String codecomments = cmd.valueOf("-codecomments");
            
            Boolean useCodeComments = codecomments.equals("yes") || 
                codecomments.equals("true") || 
                    codecomments.equals("1") || 
                        codecomments.equals("y");        
                        
            inputParameters.setUseCodeComments(useCodeComments);
            
            if(!useCodeComments){
                throw new IllegalArgumentException("Code Comments only supported at this time.");
            }
            
        }
        
        if(cmd.hasOption("-input")){
            
            inputParameters.setInputDirectory(cmd.valueOf("-input"));
            
        }
        
        if(cmd.hasOption("-output")){
            
            inputParameters.setOutputFile(cmd.valueOf("-output"));
            
        }
   }
   
}