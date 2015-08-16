package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.Color;

public class UNICODE_CoreCurrentGlowColourController
{
    //attributes
    private String value = null;
	private UNICODE_StringToColourConverter  stringToColourConverter = null;

    public UNICODE_CoreCurrentGlowColourController ( Color _value )
    {
		stringToColourConverter = new UNICODE_StringToColourConverter ( );
        value = stringToColourConverter.getRGBString ( _value, 0 );
    }
    
    //methods
        //accessors
        public String getValue ( )
        {
            return value;
        }
        //mutators
        public void setValue ( Color value )
        {
            this.value = stringToColourConverter.getRGBString ( value, 0 );
        }

}
