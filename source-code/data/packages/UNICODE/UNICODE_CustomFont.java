package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:--: Title: Bushman Gui Kit Custom Font
//:---------------------------------------------:
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

public class UNICODE_CustomFont
{
    //attributes
    private Font font;
    private Color fontColour;
    private float fontSize;
    //orientation
    private int xOri, yOri;
	//directory
	private String directory = null;
        
		
    //constructor 
    public UNICODE_CustomFont (  String _directory )
    {
		directory = _directory;
        fontSize = 15f;
    }    
   
    //methods
        //accessors
            //ORIENTATION
            public int getX ( )
            {
                return xOri;
            }     
            public int getY ( )
            {
                return yOri;
            }     
            //get font colour
            public Color getColour ( )
            {
                return fontColour;
            }
			public String getDirectory ( )
			{
				return directory;
			}
            //get font 
            public Font getFont ( Graphics graphics, String fileName, String string )
            {
                Font thisFont = null;
                try
                {
                     thisFont = Font.createFont ( Font.TRUETYPE_FONT, new File ( getDirectory ( ) + fileName ) ).deriveFont ( fontSize );
                }
                catch ( Exception error )
                {
                }
                //graphics.setFont ( thisFont );
                return thisFont;
            }
            //get font size
            public float getFontSize ( )
            {
                return fontSize;
            }    
			
        //mutators
            //ORIENTATION
            public void setX ( int value )
            {
                xOri = value;
            }     
            //ORIENTATION
            public void setY ( int value )
            {
                yOri = value;
            }
			public void setDirectory ( String value )
			{
				directory = value;
			}
            //screen update function
            public void write ( Graphics graphics, String string, int x, int y, float fontSize, String fileName )
            {
                setX ( x );
                setY ( y );
                try
                {
                    font = Font.createFont ( Font.TRUETYPE_FONT, new File ( getDirectory ( ) + fileName ) ).deriveFont ( fontSize );
                    
                }
                catch ( Exception e )
                {
                }
                
                graphics.setColor ( fontColour );
                graphics.setFont ( font );
                drawStr ( graphics, string );
            }
            //screen update function
            //produces background font under intial font
            public void write ( Graphics graphics, String string, int x, int y, float fontSize, String fileName, Color echoColour, float echoSize  )
            {
                //draw echoing font
                setX ( x + 30 );
                setY ( y );                
                try
                {
                    font = Font.createFont ( Font.TRUETYPE_FONT, new File ( getDirectory ( ) + fileName ) ).deriveFont ( echoSize );
                    
                }
                catch ( Exception e )
                {
                }
                
                graphics.setColor ( echoColour );
                graphics.setFont ( font );
                drawStr ( graphics, string );    
                
                //draw intial font above eahcoing font
                setX ( x );
                setY ( y - 30 );                
                try
                {
                    font = Font.createFont ( Font.TRUETYPE_FONT, new File ( getDirectory ( ) + fileName ) ).deriveFont ( fontSize );
                    
                }
                catch ( Exception e )
                {
                }
                
                graphics.setColor ( fontColour );
                graphics.setFont ( font );
                drawStr ( graphics, string );                               
            }            
            //change font colour
            public void setFontColour ( Color value )
            {
                fontColour = value;
            }
            //change font size
            public void setFontSize ( float value )
            {
                fontSize = value;
            }          
            
            //misc
                //draw string
                public void drawStr ( Graphics graphics, String string )
                {
                    graphics.drawString ( string, xOri, yOri );
                }
}
