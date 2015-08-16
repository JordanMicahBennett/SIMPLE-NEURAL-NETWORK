package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

public class UNICODE_DescriptionViewController
{
    //attributes
    private String value = null;

    public UNICODE_DescriptionViewController ( String _value )
    {
        value = _value;
    }
    
    //methods
        //accessors
        public String getValue ( )
        {
            return value;
        }
        //mutators
        public void setValue ( String _value )
        {
            value = _value;
        }

}
