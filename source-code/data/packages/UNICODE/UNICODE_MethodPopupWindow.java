package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JFrame;
import java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;

public class UNICODE_MethodPopupWindow extends JFrame
{
	//establish editor panel
	private JEditorPane editorPane = null;

    //FOUR SIGNATURES; TYPE ONE ACCEPTS AN ARRAY LIST FOR DATA, TYPE TWO ACCEPTS A STRING OF DATA; AND BOTH TYPES' FRAME LOCATIONS CAN EITHER BE CENTERED
    //OR CUSTOMIZED HENCE 4 SIGNATURES
    public UNICODE_MethodPopupWindow ( ArrayList methodContent, int x, int y, int width, int height, Color backgroundColour, boolean roundedEnquiry )
    {
        //establish method panel
        UNICODE_MethodPopupPanel panel = new UNICODE_MethodPopupPanel ( methodContent, backgroundColour, this );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        //remove frame from window
        setUndecorated ( true );
        //add gui panel
        add ( panel );
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		//i didn't know zero was valid as a psrameter till this god forsaken day.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
		
        //set application window dimension
        setSize ( new Dimension ( width, height ) );
        setLocation ( x, y );
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, width, height, 30, 30 ) );
        //set opacity
        setOpacity ( 0.7f );
        //show the frame
        setVisible ( true );
    }
    
    public UNICODE_MethodPopupWindow ( JEditorPane _editorPane, String methodContent, int x, int y, int width, int height, Color backgroundColour, boolean roundedEnquiry )
    {
		//establish editor pane
		editorPane = _editorPane;
	
        //establish method panel
        UNICODE_MethodPopupPanel panel = new UNICODE_MethodPopupPanel ( editorPane, methodContent, backgroundColour, this );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        //remove frame from window
        setUndecorated ( true );
        //add gui panel
        add ( panel );
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		//i didn't know zero was valid as a psrameter till this god forsaken day.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        //set application window dimension
        setSize ( new Dimension ( width, height ) );
        setLocation ( x, y );
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, width, height, 30, 30 ) );
        //set opacity
        setOpacity ( 0.7f );
        //show the frame
        setVisible ( true );
    }
    public UNICODE_MethodPopupWindow ( ArrayList methodContent, int width, int height, Color backgroundColour, boolean roundedEnquiry )
    {
        //establish method panel
        UNICODE_MethodPopupPanel panel = new UNICODE_MethodPopupPanel ( methodContent, backgroundColour, this );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        //remove frame from window
        setUndecorated ( true );
        //add gui panel
        add ( panel );
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		//i didn't know zero was valid as a psrameter till this god forsaken day.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        //set application window dimension
        setSize ( new Dimension ( width, height ) );
        //center application on screen buffer based on user's screen size
        setLocationRelativeTo ( null );
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, width, height, 30, 30 ) );
        //set opacity
        setOpacity ( 0.7f );
        //show the frame
        setVisible ( true );
    }
    
    public UNICODE_MethodPopupWindow ( JEditorPane _editorPane, String methodContent, int width, int height, Color backgroundColour, boolean roundedEnquiry )
    {
		//establish editor pane
		editorPane = _editorPane;
		
        //establish method panel
        UNICODE_MethodPopupPanel panel = new UNICODE_MethodPopupPanel ( editorPane, methodContent, backgroundColour, this );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        //remove frame from window
        setUndecorated ( true );
        //add gui panel
        add ( panel );
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		//i didn't know zero was valid as a psrameter till this god forsaken day.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        //set application window dimension
        setSize ( new Dimension ( width, height ) );
        //center application on screen buffer based on user's screen size
        setLocationRelativeTo ( null );
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, width, height, 30, 30 ) );
        //set opacity
        setOpacity ( 0.7f );
        //show the frame
        setVisible ( true );
    }
}
