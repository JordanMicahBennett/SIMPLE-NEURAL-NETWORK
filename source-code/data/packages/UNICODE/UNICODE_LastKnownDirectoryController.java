package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

public class UNICODE_LastKnownDirectoryController
{
    //attributes
    private String lastKnownDirectory = null;

    public UNICODE_LastKnownDirectoryController ( String _lastKnownDirectory )
    {
        lastKnownDirectory = _lastKnownDirectory;
    }
    
    //methods
        //accessors
        public String getLastKnownDirectory ( )
        {
            return lastKnownDirectory;
        }
        //mutators
        public void setLastKnownDirectory ( String value )
        {
            lastKnownDirectory = value;
        }

}
