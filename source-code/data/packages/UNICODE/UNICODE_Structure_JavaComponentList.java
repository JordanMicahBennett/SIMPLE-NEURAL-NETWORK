package data.packages.UNICODE; //Author(s): Jordan Micah Bennett  

import java.util.ArrayList;

public class UNICODE_Structure_JavaComponentList extends ArrayList <UNICODE_Structure_JavaComponent>
{
    //constructor
    public UNICODE_Structure_JavaComponentList ( )
    {
    }
    
    //utils
    public void addComponent ( String componentName, String componentStream )
    {
        add ( new UNICODE_Structure_JavaComponent ( componentName, componentStream ) );
    }
}
