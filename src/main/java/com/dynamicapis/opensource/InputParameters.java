package com.dynamicapis.opensource;

public class InputParameters {
    
    private Boolean useCodeComments;
    private String inputDirectory;
    private String outputFile;
    
    public InputParameters(){
        this.useCodeComments = false;
        this.outputFile = "openapidoc.json";
        this.inputDirectory = ".";
    }
    
    public void setUseCodeComments(Boolean useCodeComments){
        this.useCodeComments = useCodeComments;
    }
    public void setInputDirectory(String inputDirectory){
        this.inputDirectory = inputDirectory;
    }
    public void setOutputFile(String outputFile){
        this.outputFile = outputFile;
    }
    public Boolean getUseCodeComments(){
        return this.useCodeComments;
    }
    public String getInputDirectory(){
        return this.inputDirectory;
    }
    public String getOutputFile(){
        return this.outputFile;
    }
}

