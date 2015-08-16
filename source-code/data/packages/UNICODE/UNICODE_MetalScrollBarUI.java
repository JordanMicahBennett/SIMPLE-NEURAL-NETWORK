package data.packages.UNICODE;
//Author: Jordan Micah Bennett
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Color;


public class UNICODE_MetalScrollBarUI extends MetalScrollBarUI 
{
    private Image imageThumb, imageTrack;
    private JButton illusoryToggle = new JButton ( ) //Used to remove unwanted up down arrow toggles.
                                                        {
                                                            @Override
                                                            public Dimension getPreferredSize ( )
                                                            {
                                                                return new Dimension ( 0, 0 );
                                                            }
                                                        };
    
    UNICODE_MetalScrollBarUI ( String thumbStream, String trackStream )
    {
        try 
        {
            imageThumb = ImageIO.read ( new File ( thumbStream ) );
            imageTrack = ImageIO.read ( new File ( trackStream ) );
        } 
        catch ( IOException e )
        {
        }
    }



    @Override
    protected void paintThumb ( Graphics graphics, JComponent c, Rectangle thumbBounds ) 
    {        
        graphics.translate ( thumbBounds.x, thumbBounds.y );
        graphics.setColor ( Color.red ); 
        graphics.drawRect ( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );
        AffineTransform transform = AffineTransform.getScaleInstance ( ( double ) thumbBounds.width / imageThumb.getWidth ( null ), ( double ) thumbBounds.height / imageThumb.getHeight ( null ) );
        ( ( Graphics2D ) graphics ).drawImage ( imageThumb, transform, null );
        graphics.translate ( -thumbBounds.x, -thumbBounds.y );
    }
    
    @Override
    protected void paintTrack ( Graphics graphics, JComponent c, Rectangle trackBounds ) 
    {        
        graphics.translate ( trackBounds.x, trackBounds.y );
        
        ( ( Graphics2D ) graphics ).drawImage ( imageTrack,AffineTransform.getScaleInstance ( 1, ( double ) trackBounds.height / imageTrack.getHeight ( null ) ), null );
        graphics.translate ( -trackBounds.x, -trackBounds.y );
    }
    
    @Override
    protected JButton createDecreaseButton ( int orientation ) 
    {
        return illusoryToggle;
    }
    
    @Override
    protected JButton createIncreaseButton ( int orientation )  
    {
        return illusoryToggle;
    }
}