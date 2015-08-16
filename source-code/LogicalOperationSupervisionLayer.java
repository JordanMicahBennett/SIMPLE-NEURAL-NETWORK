//Author: Jordan Micah Bennett
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;

import data.packages.UNICODE.UNICODE_ConveniencePack; 

public class LogicalOperationSupervisionLayer extends GenericSupervisionLayer
{
    //define constructor
    public LogicalOperationSupervisionLayer ( String fileName, boolean consoleDisplayQuery )
    {
        super ( fileName, consoleDisplayQuery );
    }
    
    
    //define methods
        //define accessors
        public ArrayList <Double> getInputs ( String line )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );
            
            String [ ] _line = line.split ( "::" );
            String [ ] _nodes = _line [ 0 ].split ( "," );
            
            returnValue.add ( Double.parseDouble ( "" + _nodes [ 0 ] ) );
            returnValue.add ( Double.parseDouble ( "" + _nodes [ 1 ] ) );
                    
            return returnValue;
        }
        public ArrayList <Double> getExpectedOutcomes ( String line )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );

            String [ ] _line = line.split ( "::" );
 
            returnValue.add ( Double.parseDouble ( _line [ 1 ] ) );
                    
            return returnValue;
        }  
}