import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.StringReader;
import java.io.BufferedReader;

public class Processor {
    
    private static final String NEWLINE = "\n";

    private InputParameters inputParameters;
    
    public Processor(InputParameters inputParameters){
        this.inputParameters = inputParameters;
    }
    
    public void Generate() throws RuntimeException, FileNotFoundException, IOException {
        
        List<String> fileList = this.obtainListOfFilesFromDirectory();        
        
        StringBuilder sb = new StringBuilder();
        
        for (String filePathAsString : fileList) {
            this.obtainRawInput(sb,filePathAsString);
		}
		
		System.out.println(sb.toString());
            
        return;
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
            if(line.toLowerCase().contains("[swaggersecuritydefinition(") ||
                line.toLowerCase().contains("[swaggerroute(") ||
                    line.toLowerCase().contains("[swaggeroperation(") ||
                        line.toLowerCase().contains("[swaggerinput(") ||
                            line.toLowerCase().contains("[swaggerresponse(") ||
                                line.toLowerCase().contains("[swaggerresponseheader(") ||
                                    line.toLowerCase().contains("[swaggerobject(") ||
                                        line.toLowerCase().contains("[swaggerobjectproperty(")){
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