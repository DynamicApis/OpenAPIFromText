public class Processor {
    
    private InputParameters inputParameters;
    
    public Processor(InputParameters inputParameters){
        this.inputParameters = inputParameters;
    }
    
    public void Generate() throws RuntimeException {
        System.out.println("Generating.");
        return;
    }
    
}