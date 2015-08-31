//Author: Jordan Micah Bennett
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;

import data.packages.UNICODE.UNICODE_ConveniencePack; 

public class VisualSupervisionLayer extends GenericSupervisionLayer
{
    //define feature(s)
    private NormalizationLayer normalizationLayer = new NormalizationLayer ( );
    
    //define constructor
    public VisualSupervisionLayer ( String fileName, boolean consoleDisplayQuery )
    {
        super ( fileName, consoleDisplayQuery );
    }
    
    
    //define methods
        //define accessors
        public ArrayList <Double> getInputs ( String line )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );
            
            String [ ] _line = line.split ( "::" );
            String outcome = "";
            int FILTER_BOUNDARY = 80; //See NormalizationLayer ( ).getRichlyFilteredLuminanceOutcome ( ) . Test via data/miscellaneous/___unused/_test.java. A lower boundary requires higher space-time complexity, while capturing a higher value of entropic pixel values. (High entropy is sub-optimal).
            
            //{pI = pixels iterator}
            for ( int pI = 0; pI < conveniencePack.getPixels ( _line [ 0 ] ).size ( ); pI ++ )
            {
                returnValue.add ( normalizationLayer.getRichlyFilteredLuminanceOutcome ( Double.parseDouble ( conveniencePack.getPixels ( _line [ 0 ] ).get ( pI ).toString ( ) ), FILTER_BOUNDARY ) );
            }
                  
            System.out.println ( outcome );
            
            return returnValue;
        }
        public ArrayList <Double> getExpectedOutcomes ( String line )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );

            String [ ] _line = line.split ( "::" );
            String [ ] subLine = _line [ 1 ].split ( " " );
            
            //{sI = subLine iterator}
            for ( int sI = 0; sI < subLine.length; sI ++ )
                returnValue.add ( Double.parseDouble ( subLine [ sI ] ) );
                    
            return returnValue;
        } 
}