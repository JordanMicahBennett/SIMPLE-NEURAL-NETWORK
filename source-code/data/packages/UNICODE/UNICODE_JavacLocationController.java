package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

public class UNICODE_JavacLocationController
{
    //attributes
    private String javacLocation = null;

    public UNICODE_JavacLocationController ( String _javacLocation )
    {
        javacLocation = _javacLocation;
    }
    
    //methods
        //accessors
        public String getJavacLocation ( )
        {
            return javacLocation;
        }
        //mutators
        public void setJavacLocation ( String value )
        {
            javacLocation = value;
        }

}
