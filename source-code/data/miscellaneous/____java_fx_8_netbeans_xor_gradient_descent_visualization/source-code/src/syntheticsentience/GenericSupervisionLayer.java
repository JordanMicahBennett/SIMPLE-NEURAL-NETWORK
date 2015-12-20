/* 
    Author ~ Jordan Micah Bennett 
    A simplistic artificial neural network, that naturally computes xor-logic prediction, via stochastic gradient descent.
*/
package syntheticsentience;

import java.util.ArrayList;


public abstract class GenericSupervisionLayer
{
    //establish features
    public String fileName;
    public ArrayList <String> data;
    public Utilities utilities;
    public NormalizationLayer normalizationLayer;
    public ArrayList <Double> INPUTS;
    public ArrayList <Double> EXPECTED_OUTCOMES;
    public boolean consoleDisplayQuery; //determines whether System.out.println calls are executed
    
    //define constructor
    public GenericSupervisionLayer ( String fileName, boolean consoleDisplayQuery )
    {
        this.fileName = fileName;
        this.consoleDisplayQuery = consoleDisplayQuery;
        utilities = new Utilities ( );
        normalizationLayer = new NormalizationLayer ( );
        data = getData ( );
    }
    
    
    //define methods
        //define accessors
        public ArrayList <String> getData ( )
        {
            return utilities.getFileContents ( fileName );
        }
        public abstract ArrayList <Double> getInputs ( String line );
        public abstract ArrayList <Double> getExpectedOutcomes ( String line );
}