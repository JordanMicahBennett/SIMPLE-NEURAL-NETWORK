package data.packages.UNICODE;
//package data.packages.UNICODE;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Color;

public class UNICODE_WIZARD_BODY 
{
    //attributes
    private Ellipse2D wizardBody = null;
    private double x, y, width, height;
    private Color wizardNodeColour = null;

    
    //constructor
    public UNICODE_WIZARD_BODY ( double _x, double _y, double _width, double _height, Color _wizardNodeColour )
    {
        //establish orientation
        x = _x;
        y = _y;
        
        //establish dimension
		width = _width;
		height = _height;
        
        //establish UNICODE_WIZARD_BODY
        wizardBody = new Ellipse2D.Double ( x, y, width, height );
        
        //node colour
        wizardNodeColour = _wizardNodeColour;
    }
    
    
    //accessors
    public Ellipse2D getBody ( )
    {
        return wizardBody;
    }
    
    
    public void draw ( Graphics2D graphics2d )
    {
        //draw wizard
        graphics2d.setColor ( wizardNodeColour );
        graphics2d.fill ( wizardBody ); 
    }
}
