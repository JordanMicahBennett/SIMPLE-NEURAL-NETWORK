package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

public class UNICODE_BufferDimensionController
{
    //attributes
    private String bufferDimensionString = null;

    public UNICODE_BufferDimensionController ( )
    {
        bufferDimensionString = "";
    }
    
    //methods
        //accessors
        public String getBufferDimensionString ( )
        {
            return bufferDimensionString;
        }
        //mutators
        public void setBufferDimensionString ( String value )
        {
            bufferDimensionString = value;
        }

}
