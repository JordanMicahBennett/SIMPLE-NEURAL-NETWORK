package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
public class UNICODE_OpacityController
{
    //attributes
    private float opacity_level;

    //constructor
    public UNICODE_OpacityController ( )
    {
        opacity_level = 0.5f;
    }
    
    
    //methods
        //accessors
        public float getOpacLevel ( )
        {
            return opacity_level;
        }
        //mutators
        public void setOpacLevel ( float value )
        {
            opacity_level = value;
        }    
        public void incOpacLevel ( )
        {
            if ( getOpacLevel ( ) < 0.98f )
                opacity_level += 0.03f;
            else
                opacity_level = 0.15f;
        } 
        public void decOpacLevel ( )
        {
            if ( getOpacLevel ( ) >= 0.15f )
                opacity_level -= 0.03f;
            else
                opacity_level = 1.0f;
        }      
        
    //misc methods
    public void updateOpacity ( String direction )
    {
        if ( direction.equals ( "previous" ) )
            decOpacLevel ( );
        else if ( direction.equals ( "next" ) )
            incOpacLevel ( );
    }
    
    
    public float [ ] percentageRepresentation ( )
    {
        float [ ] array = new float [ 2 ];
        //float total_proportion = 1 - getOpacLevel ( );
       
        
        
        array [ 0 ] = ( 1 - getOpacLevel ( ) ) * 100;
        array [ 1 ] = getOpacLevel ( ) * 100;
        return array;
    }
}
