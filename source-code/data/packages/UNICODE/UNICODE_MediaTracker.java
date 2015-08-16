package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.util.Scanner;
import javax.swing.Timer;

public class UNICODE_MediaTracker 
{
    //attributes
    private String loadElipse;
    private int loadElipseCharCount;
    
    //constructor 1
    public UNICODE_MediaTracker ( )
    {
        loadElipse = "";
        loadElipseCharCount = -1;
    }    
    
    //get load elipse
    public String getLoadElipse ( )
    {
        return loadElipse;
    }
    //get load elipse
    public int getloadElipseCharCount ( )
    {
        return loadElipseCharCount;
    }    
    //set load elipse
    public void incLoadElipse ( String [ ] loadContent )
    {
        loadElipseCharCount ++;
        
        if ( loadElipseCharCount < loadContent.length )
            loadElipse += loadContent [ loadElipseCharCount ];
        else
        {
            loadElipse = "";
            loadElipseCharCount = -1;
        }
    }
    
}
