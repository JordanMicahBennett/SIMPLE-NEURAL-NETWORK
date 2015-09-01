package data.packages.UNICODE; 
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:---------------------------------------------:


//Author: Jordan Micah Bennett
//Aim: Very simple paint program.
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Dimension;

public class UNICODE_PaintPanel extends JPanel
{
    //attributes
        //Mouse position tracking variables
        private Point heldMouseCords = null, draggedMouseCords = null;
        
        //Paint object geometry
            //Actual geometry that represents collection of physical strokes
            private ArrayList <Ellipse2D> strokes = new ArrayList <Ellipse2D> ( );
            //Stroke properties 
                //dimension
                private int defaultStrokeSpan = 0, strokeSpan = 0, defaultStrokeSpanDisplacement = 0, strokeSpanDisplacement = 0, minimumStrokeSpan = 0, maximumStrokeSpan = 0;
                //colour
                private Color strokeColour = null;
				private Color backgroundColour = null, dataSetBackgroundColour = null; 
                //saving
                    //output file directory {for image saving) and extension
                    private String outputDirectory = "", extension = "";
					private boolean enableImageResizingOnSaveQuery = false;
					private Dimension resizeDimension = null;
					private int frameHeight = 0;
				//mode
				public boolean CONTINUOUS_MODE = false; //non-dotted/dotted brush.
				//undo/redo mechanism
				private ArrayList <Ellipse2D> proximalStrokes = new ArrayList <Ellipse2D> ( ); //recorded strokes
				//utility
				private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );


    public UNICODE_PaintPanel ( int strokeSpan, int minimumStrokeSpan, int maximumStrokeSpan, Color strokeColour, Color backgroundColour, Color dataSetBackgroundColour, int frameHeight )
    {
        //stroke colour
        this.strokeColour = strokeColour;
		this.backgroundColour = backgroundColour;
		
        this.dataSetBackgroundColour = dataSetBackgroundColour;
		
		//span
		this.strokeSpan = strokeSpan;
		this.defaultStrokeSpan = strokeSpan;
		this.minimumStrokeSpan = minimumStrokeSpan;
		this.maximumStrokeSpan = maximumStrokeSpan;
		this.strokeSpanDisplacement = 1; //displace stroke by 1 per stroke pass
		this.defaultStrokeSpanDisplacement = strokeSpanDisplacement;
		this.frameHeight = frameHeight;
		

        //set background colour
        setBackground ( backgroundColour );
        
        //add mouse listeners
        addMouseMotionListener ( new mouseListening ( ) ) ;
        addMouseListener ( new mouseListening ( ) ) ;
		addMouseWheelListener ( new mouseWheelListening ( ) );
    }
    
    
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        //set stroke colour
        graphics2d.setColor ( strokeColour );
            
        //draw strokes
        for ( Ellipse2D stroke : strokes )
            graphics2d.fill ( stroke );
    }
    
    //clear panel
    public void clear ( )
    {
        strokes.clear ( );
    }
	
	//undo
	public void undo ( )
	{
		if ( ( strokes.size ( ) - 1 ) >= 0 ) //for minimum stroke cardinality = 0
		{
			proximalStrokes.add ( strokes.get ( strokes.size ( ) - 1 ) );
			strokes.remove ( strokes.size ( ) - 1 );
		}
		
		repaint ( );
	}
	
	//redo
	public void redo ( )
	{
		if ( proximalStrokes.size ( ) <= strokes.size ( ) ) //for proximal stroke cardinality relents strokes size
		{
			strokes.add ( ( strokes.size ( ) - 1 ), proximalStrokes.get ( proximalStrokes.size ( ) - 1 ) );
			proximalStrokes.remove ( proximalStrokes.size ( ) - 1 );
		}
		else
			proximalStrokes.clear ( );
		
		repaint ( );
	}
	
    //save image
    public void save ( )
    {
        java.awt.Dimension size = getSize ( );
		
        setBackground ( dataSetBackgroundColour );
		
        java.awt.image.BufferedImage image = new java.awt.image.BufferedImage ( size.width, size.height, java.awt.image.BufferedImage.TYPE_INT_RGB );
        Graphics2D graphics2d = image.createGraphics ( );
		
		
        paint ( graphics2d );
     
		//per pixel editing ( SYNTHETIC_SENTIENCE )
		for ( int y = 0; y < image.getHeight ( ); y ++ )
			for ( int x = 0; x < image.getWidth ( ); x ++ )
			{
				java.awt.Color imageColor = new java.awt.Color ( image.getRGB ( x, y ) );
				
				//compute luminance, and alter pixels appropriately, such that black pixels (painted) are replaced with white (detected).
				double luminance = conveniencePack.getArraySum ( new double [ ] { ( ( ( int ) ( 0.2126 * image.getRGB ( x, y ) ) ) >> 16 ) & 0xFF, ( ( ( int ) ( 0.7152 * image.getRGB ( x, y ) ) ) >> 8 ) & 0xFF, ( ( int ) ( 0.0722 * image.getRGB ( x, y ) ) ) & 0xFF } );

				
				image.setRGB ( x, y, ( luminance > 500 ) ? -1 : 1 ); //{<500} encodes as black relenting painted pixels. (lighter). I otherwise return 0, as I am uninterested via that pixel ( not detected )
				
				 //colorimetric-space relative luminance based pixel generation. ( SYNTHETIC_SENTIENCE )
			}
				
        try
        {
            javax.imageio.ImageIO.write ( image, extension, new java.io.File ( outputDirectory ) );
        }
        catch ( Exception e )
        {
        }
		
		setBackground ( backgroundColour );
		
		if ( enableImageResizingOnSaveQuery )
			conveniencePack.enableImageResizing ( outputDirectory, resizeDimension );
    }
    
    
    //mouse listener
    private class mouseListening implements MouseMotionListener, MouseListener
    {
        public void mouseEntered ( MouseEvent mouseEvent )
        {
        }
        public void mouseExited ( MouseEvent mouseEvent )
        {
        }
        public void mousePressed ( MouseEvent mouseEvent )
        {
            //establish mouse pressed coordinates
            heldMouseCords = mouseEvent.getPoint ( );
			
            if ( CONTINUOUS_MODE )
            {
                strokes.add ( new Ellipse2D.Double ( heldMouseCords.getX ( ), heldMouseCords.getY ( ), strokeSpan, strokeSpan ) );
                repaint ( );
            }
        }
        public void mouseDragged ( MouseEvent mouseEvent )
        {         
            if ( !CONTINUOUS_MODE )
            {
				//establish dragged mouse coordinates
				draggedMouseCords = mouseEvent.getLocationOnScreen ( );    
				int draggedMouseCoordX = ( int ) ( draggedMouseCords.getX ( ) - heldMouseCords.getX ( ) ), draggedMouseCoordY = ( int ) ( draggedMouseCords.getY ( ) - heldMouseCords.getY ( ) );

				//generate new stroke
				strokes.add ( new Ellipse2D.Double ( draggedMouseCords.getX ( ) - ( strokeSpan ) / strokeSpanDisplacement , draggedMouseCords.getY ( ) - ( strokeSpan * strokeSpan / ( frameHeight / 100 ) ) / strokeSpanDisplacement, strokeSpan, strokeSpan ) );
				
				//refresh panel to reflect newly appended strokes.
				repaint ( );
			}
        }
        public void mouseReleased ( MouseEvent mouseEvent )
        {
            save ( );
        }
        public void mouseMoved ( MouseEvent mouseEvent )
        {
        }
        public void mouseClicked ( MouseEvent mouseEvent )
        {
        }
    }
	
    private class mouseWheelListening implements MouseWheelListener
    {
        public void mouseWheelMoved ( MouseWheelEvent mouseWheelEvent ) 
        {
            int direction = mouseWheelEvent.getWheelRotation ( );
            //scroll up ( flatten finger on mouse wheel )
            if ( direction < 0 )
            {
                if ( strokeSpan <= maximumStrokeSpan )
                {
                    int oldStroke = strokeSpan;
                    strokeSpan ++;
                    strokeSpanDisplacement += strokeSpan - oldStroke;
                    //System.out.println ( "[up] oldStroke / " + oldStroke + "\\\\strokeSpanDisplacement / " + strokeSpanDisplacement  );
                }
            }
            //scroll down ( un-flatten finger on mouse wheel )
            else 
            {
                if ( strokeSpan >= minimumStrokeSpan )
                {
                    int oldStroke = strokeSpan;
                    strokeSpan --;
                    strokeSpanDisplacement += strokeSpan - oldStroke;
                    //System.out.println ( "[down] oldStroke / " + oldStroke + "\\\\strokeSpanDisplacement / " + strokeSpanDisplacement  );
                }
            }
            
            if ( strokeSpan >= maximumStrokeSpan )
            {
                strokeSpanDisplacement = defaultStrokeSpanDisplacement;
                strokeSpan = defaultStrokeSpan;
            }
            else if ( strokeSpan <= minimumStrokeSpan )
            {
                strokeSpanDisplacement = defaultStrokeSpanDisplacement;
                strokeSpan = defaultStrokeSpan;
            }
        }
    }
	
   
    public void setup ( String outputDirectory, String extension, boolean enableImageResizingOnSaveQuery, Dimension resizeDimension )
    {
        this.outputDirectory = outputDirectory;
        this.extension = extension;
		this.enableImageResizingOnSaveQuery = enableImageResizingOnSaveQuery;
		this.resizeDimension = resizeDimension;
    }
}