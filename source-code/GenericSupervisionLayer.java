//Author: Jordan Micah Bennett
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;

import data.packages.UNICODE.UNICODE_ConveniencePack; 

public abstract class GenericSupervisionLayer
{
    //establish features
    public String fileName;
    public ArrayList <String> data;
    public UNICODE_ConveniencePack conveniencePack;
    public NormalizationLayer normalizationLayer;
    public ArrayList <Double> INPUTS;
    public ArrayList <Double> EXPECTED_OUTCOMES;
    public boolean consoleDisplayQuery; //determines whether System.out.println calls are executed
    
    //define constructor
    public GenericSupervisionLayer ( String fileName, boolean consoleDisplayQuery )
    {
        this.fileName = fileName;
        this.consoleDisplayQuery = consoleDisplayQuery;
        conveniencePack = new UNICODE_ConveniencePack ( );
        normalizationLayer = new NormalizationLayer ( );
        data = getData ( );
    }
    
    
    //define methods
        //define accessors
        public ArrayList <String> getData ( )
        {
            return conveniencePack.getFileContents ( fileName );
        }
        public abstract ArrayList <Double> getInputs ( String line );
        public abstract ArrayList <Double> getExpectedOutcomes ( String line );
}