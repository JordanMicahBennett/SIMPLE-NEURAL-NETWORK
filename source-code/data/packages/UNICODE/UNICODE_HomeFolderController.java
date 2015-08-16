package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

public class UNICODE_HomeFolderController
{
    //attributes
    private String value = null;

    public UNICODE_HomeFolderController ( String _value )
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
