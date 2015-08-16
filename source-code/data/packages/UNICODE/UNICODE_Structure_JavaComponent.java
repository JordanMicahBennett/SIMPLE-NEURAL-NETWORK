package data.packages.UNICODE; //Author(s): Jordan Micah Bennett  

public class UNICODE_Structure_JavaComponent
{
    //attributes
    private String componentName = null, componentStream = null;

    //constructor
    public UNICODE_Structure_JavaComponent ( String _componentName, String _componentStream )
    {
        //establish component name
        componentName = _componentName;
        
        //establish component stream
        componentStream = _componentStream;
    }
    
    //accessors
    public String getComponentName ( )
    {
        return componentName;
    }
    public String getComponentStream ( )
    {
        return componentStream;
    }
    
    //mutators
    public void setComponentName ( String value )
    {
         componentName = value;
    }
    public void setComponentStream ( String value )
    {
         componentStream = value;
    }
}
