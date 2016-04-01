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
    private int filterBoundary = 0; //See NormalizationLayer ( ).getRichlyFilteredLuminanceOutcome ( ) . Test via data/miscellaneous/___unused/_test.java. A lower boundary requires higher space-time complexity, while capturing a higher range of entropic? pixel values. ( Abundant entropy is sub-optimal ).
    
    //define constructor
    public VisualSupervisionLayer ( String fileName, boolean consoleDisplayQuery, int filterBoundary )
    {
        super ( fileName, consoleDisplayQuery );
        this.filterBoundary = filterBoundary;
    }
    
    
    //define methods
        //define accessors
        public ArrayList <Double> getInputs ( String line )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );
            
            String [ ] _line = line.split ( "::" );

            //{pI = pixels iterator}
            for ( int pI = 0; pI < conveniencePack.getPixels ( _line [ 0 ] ).size ( ); pI ++ )
                returnValue.add ( normalizationLayer.getHorizon ( Double.parseDouble ( conveniencePack.getPixels ( _line [ 0 ] ).get ( pI ).toString ( ) ), filterBoundary ) );

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