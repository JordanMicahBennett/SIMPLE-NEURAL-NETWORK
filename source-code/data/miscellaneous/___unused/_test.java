//Author: Jordan Micah Bennett

import data.packages.UNICODE.*;
import java.util.ArrayList;
//test to determine appropriate pixels for extraction.
//TO DO: Automate process vis a vis neuronal-like feature extraction.
public class _test
{
    public static void main ( String arguments [ ] )
    {
        //ArrayList <Double> pixels = new UNICODE_ConveniencePack ( ).getPixels ( "data/images/miscellaneous/___unused/_test.png" );
        ArrayList <Double> pixels = new UNICODE_ConveniencePack ( ).getPixels ( "data/images/patterns/test-31.bmp" );        
        
        String outcome = "";
        int inputVectorSpan = 32, filterBoundary = 100;
        
        for ( int p = 0; p < pixels.size ( ); p ++ )
        {
            outcome += new NormalizationLayer ( ).getRichlyFilteredLuminanceOutcome ( Double.parseDouble ( new java.text.DecimalFormat ( "##" ).format ( pixels.get ( p ) ) ), filterBoundary ) + ( ( p % inputVectorSpan == 0 ) ? "\n" : ",  " );
        }
            
        System.out.println ( outcome );
    }
}