package data.packages.UNICODE; 
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:---------------------------------------------:
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;

public class UNICODE_BlankFrame
{
	//establish frame property variable requirements
		//establish frame UNICODE_BlankPanel variable requirement
		private UNICODE_BlankPanel panel = null;
		//establish frame opacity variable requirement
		private float opacity = 0.0f;
		//establish frame rounded enquiry variable requirement
		private boolean roundedEnquiry = false;
		//establish frame dimension variable requirements
		private int width = 0, height = 0;
		//establish frame orientation variable requirements
		private int xStart = 0, yStart = 0;
		//establish JFrame
		private JFrame frame = null;
		
		//visibility controller
		private boolean visibilityEnquiry = false;
		
	//constructor
	public UNICODE_BlankFrame ( UNICODE_BlankPanel panel, float opacity, int xStart, int yStart, int width, int bufferHeight, boolean roundedEnquiry )
	{
		this.opacity = opacity;
		this.xStart = xStart;
		this.yStart = yStart;
		this.width = width;
		this.height = bufferHeight - 40;
		this.roundedEnquiry = roundedEnquiry;
		
		//establish JFrame
		frame = new JFrame ( );
		
        //establish method panel
        this.panel = panel;
		
        //establish Jframe stuff
        frame.setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        frame.setUndecorated ( true );
        
        //add gui panel
        frame.add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        frame.setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		frame.setLayout ( new BoxLayout ( frame.getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		frame.getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        frame.setSize ( new Dimension ( width, height ) );
        
        //establish location
        frame.setLocation ( xStart, yStart );
        
        //set frame shape
        if ( roundedEnquiry )
            frame.setShape ( new RoundRectangle2D.Double ( 0, 0, width, this.height, 30, 30 ) );
            
        //set opacity
        frame.setOpacity ( opacity );
        
        //show the frame
        frame.setVisible ( true );
		
		//set always on top
		frame.setAlwaysOnTop ( true );
	}
	
	public void hide ( )
	{
		frame.setVisible ( false );
	}
	
	public void show ( )
	{
		frame.setVisible ( true );
	}	
	
	public void toggleVisiblilty ( )
	{
		if ( !visibilityEnquiry )
		{
			show ( );
			visibilityEnquiry = true;
		}
		else if ( visibilityEnquiry )
		{
			hide ( );
			visibilityEnquiry = false;
		}
	}
	
	public void changeColour ( Color value )
	{
		panel.repaint ( );
		frame.repaint ( );
		frame.setAlwaysOnTop ( true );
		panel.setBackground ( value );
	}
	
	public void changePaint ( Color value0, Color value1 )
	{
		panel.repaint ( );
		frame.repaint ( );
		frame.setAlwaysOnTop ( true );
		panel.setPaint ( value0, value1 );
	}
}