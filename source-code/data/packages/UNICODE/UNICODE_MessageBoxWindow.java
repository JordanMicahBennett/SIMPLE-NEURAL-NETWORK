package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JFrame;
import java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JRootPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class UNICODE_MessageBoxWindow extends JFrame
{
    //attributes
    private UNICODE_MessageBoxPanel panel = null;
	//we need to ensure that the thread may be set after a declaration of a message box window.
	//eg.
	//1.Pop up a window
	//2.Get text from window and pass to a function that needs it
	//3.Encode that same function above in a thread after the declaration of the window
	//4.set thread attribute
	private Thread thread0 = null, thread1 = null, thread2 = null, thread3 = null;
	

    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, Thread _thread1, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
		thread1 = _thread1;
		
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, thread1, backgroundColour, buttonOutlineColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, String labelText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, labelText, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, Thread _thread1, Thread _thread2, String labelText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
		thread1 = _thread1;
		thread2 = _thread2;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, thread1, thread2, labelText, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, Thread _thread1, String labelText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
		thread1 = _thread1;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, thread1, labelText, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }  
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, String labelText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( labelText, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    } 
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, String labelText, String delimiter, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( labelText, delimiter, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    } 
	    
	public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread thread, String labelText, String delimiter, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread, labelText, delimiter, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    } 	

    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, int customWidth, int customHeight, boolean roundedEnquiry )
    {
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, customWidth, customHeight, this );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) );
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), customHeight ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, customWidth, customHeight, 12, 12 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
		
		//ensure that tthis window is always on top
		setAlwaysOnTop ( true );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, String labelText, int customWidth, int customHeight, boolean roundedEnquiry )
    {
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, labelText, customWidth, customHeight, this );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) );
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), customHeight ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, customWidth, customHeight, 12, 12 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
		
		//ensure that tthis window is always on top
		setAlwaysOnTop ( true );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, int signatureDifferentiator, Thread _thread0, Thread _thread1, String labelText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
		thread1 = _thread1;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( signatureDifferentiator, thread0, thread1, labelText, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }  
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread thread0, Thread thread1, String labelText, JPanel itemPanel, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, thread1, labelText, itemPanel, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    } 

    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, Color fieldLabelForegroundColour, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue, int maxFields, Color textfieldBackgroundColour, Color textfieldForegroundColour, int fieldWidth, int fieldHeight, boolean roundedEnquiry, float alphaLevel, int boxWidthGrowth, ArrayList <String> fieldLabelList )
    {
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( _thread0,  labelText,  backgroundColour,  buttonOutlineColour, labelBackgroundColour,  labelForegroundColour, fieldLabelForegroundColour, this,  buttonTextureDir,  buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue,  maxFields,  textfieldBackgroundColour,  textfieldForegroundColour,  fieldWidth,  fieldHeight, boxWidthGrowth, fieldLabelList );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    } 
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, int signatureDifferentiator, Thread _thread0, String labelText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( signatureDifferentiator, thread0, labelText, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, Thread _thread1, Thread _thread2, Thread _thread3, String labelText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
		thread1 = _thread1;
		thread2 = _thread2;
		thread3 = _thread3;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, thread1, thread2, thread3, labelText, backgroundColour, buttonOutlineColour, labelBackgroundColour, labelForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, String prebakedFieldText, String prebakedAreaText, Thread _thread0, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textAreaBackgroundColour, Color textAreaForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( prebakedFieldText, prebakedAreaText, thread0, backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, textAreaBackgroundColour, textAreaForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, String prebakedFieldText, String prebakedField1Text, String prebakedAreaText, Thread _thread0, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textfield1BackgroundColour, Color textfield1ForegroundColour, Color textAreaBackgroundColour, Color textAreaForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( prebakedFieldText, prebakedField1Text, prebakedAreaText, thread0, backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, textfield1BackgroundColour, textfield1ForegroundColour, textAreaBackgroundColour, textAreaForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, String prebakedFieldText, String prebakedField1Text, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textfield1BackgroundColour, Color textfield1ForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, prebakedFieldText, prebakedField1Text, backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, textfield1BackgroundColour, textfield1ForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	
    public UNICODE_MessageBoxWindow ( boolean visibilityEnquiry, Thread _thread0, String prebakedFieldText, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textfield1BackgroundColour, Color textfield1ForegroundColour, boolean roundedEnquiry, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcWidth, int arcHeight, int axisRadiusDisplacement, int lastButtonChopValue )
    {
		//establish thread attributes wrt user passed params, so that they be initially nullable, and settable later.
		thread0 = _thread0;
	
        //establish method panel
        panel = new UNICODE_MessageBoxPanel ( thread0, prebakedFieldText, backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, textfield1BackgroundColour, textfield1ForegroundColour, this, buttonTextureDir, buttonShapeType, buttonWidth, buttonHeight, arcWidth, arcHeight, axisRadiusDisplacement, lastButtonChopValue );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( visibilityEnquiry );
    }
	//accessors
		//panel methods 
		public UNICODE_MessageBoxPanel getPanel ( )
		{
			return panel;
		}
	//mutators
		//set threads
		public void setThread0 ( Thread value )
		{
			panel.setThread0 ( value );
		}
		//set threads
		public void setThread1 ( Thread value )
		{
			panel.setThread1 ( value );
		}
		//set threads
		public void setThread2 ( Thread value )
		{
			panel.setThread1 ( value );
		}
		public void setLabelText ( String value )
		{
			panel.setLabelText ( value );
			panel.repaint ( );
		}
}
