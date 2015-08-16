package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.Color;
import java.util.Scanner;

public class UNICODE_StringToColourConverter
{
    public UNICODE_StringToColourConverter ( )
    {
    }
    
    
    //converts a string with r g b integer components into 
    //a java colour
    public Color getColourFromString ( String colour_string )
    {
        //establish the colour that will be returned to this function
        Color colour = null;
        
        //establish Scanner to scan colour string
        Scanner scanner = new Scanner ( colour_string );
        colour = new Color ( Integer.parseInt ( scanner.next ( ) ), Integer.parseInt ( scanner.next ( ) ), Integer.parseInt ( scanner.next ( ) ) );
        
        //return colour
        return colour;
    }
    
    public String getRGBString ( Color colour )
    {
        //establish the string that will be returned to this function
        String string = null;
        
        //pump the colour integer components into the string
        string = colour.getRed ( ) + " " + colour.getGreen ( ) + " " + colour.getBlue ( );
        
        //return rgb string
        return string;
    }
	
    public String getRGBString ( Color colour, int signatureOne )
    {
        //establish the string that will be returned to this function
        String string = null;
        
        //pump the colour integer components into the string
        string = colour.getRed ( ) + "," + colour.getGreen ( ) + "," + colour.getBlue ( );
        
        //return rgb string
        return string;
    }
}
