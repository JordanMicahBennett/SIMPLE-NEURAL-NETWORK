//Author: Jordan Micah Bennett
import java.util.ArrayList;

public class NormalizationLayer 
{
    //establish features
    
    
    //define constructor
    public NormalizationLayer ( )
    {
    }
    
    
    //define methods
        //define accessors
            //suggestion: the upper bound saturation point's (max's) variability increases as such expands from input value. The converse is true for minimum. 
            //a deviation of +/-100 aptly applies for digit sample space. !!FOR start = 0!!
            public double getNormalizedRawOutcome ( double min, double max, double value )
            {
                return getNormalizedRawOutcome ( min, max, value, 0, 1 ); //bounds outcome in range 0,1
            } 
  
            
            //min should not exceed max, start should not exceed end !!FOR start, end = user specified!!
            public double getNormalizedRawOutcome ( double min, double max, double value, double start, double end ) 
            {
                return value >= max ? end : value <= min ? start : ( value - min ) * ( end - start ) / ( max - min ) + start; //for max - min is some index i0, end - start is another i1, value - min * i1 / i0 is another i2 where outcome yields i2 + input start. 
            }
        
            //refines normalized outcome in terms of deviation in suggesstion above.
            //this function pre-bakes a suitable maximum saturation in synergy with deviation.
            public double getNormalizedOutcome ( double min, double value, double deviation ) 
            {
                return getNormalizedRawOutcome ( min, ( Math.round ( value ) + deviation ), value ); 
            }
            
            //refines normalized outcome in terms of deviation in suggesstion above.
            //this function pre-bakes a suitable maximum saturation in synergy with deviation.  !!FOR start, end = user specified!!
            public double getNormalizedOutcome ( double min, double value, double deviation, double start, double end ) 
            {
                return getNormalizedRawOutcome ( min, ( Math.round ( value ) + deviation ), value, start, end ); 
            }
            
            //returns outcome abound 2 decimal places
            public double getRoundedOutcome ( double value )
            {
                return Double.parseDouble ( new java.text.DecimalFormat ( "##.00" ).format ( value ) );
            }
            
            //?????????? I simply derive the most desirable (highest) pixels based on boundary. [Synthetic sentience utilizes David Miller's noisy data set, whose digits ensue white]
            public double getRichlyFilteredLuminanceOutcome ( double value, int boundary )
            {
                return value >= boundary ? 1 : -1;
            }
            public double getPoorlyFilteredLuminanceOutcome ( double value, int boundary )
            {
                return value <= boundary ? 1 : -1;
            }
        //define mutators
}