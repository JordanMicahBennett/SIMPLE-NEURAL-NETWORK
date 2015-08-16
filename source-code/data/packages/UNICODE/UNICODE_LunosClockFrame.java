package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;

public class UNICODE_LunosClockFrame extends JFrame
{
    //attribtues
    private UNICODE_LunosClock defaultPanel = null;
    private UNICODE_LunosClock panel = null;
    
    //sets up most components, leaving you to choose colour scheme only, in addition to max delay between segment updates, and ofcourse frame location.
    public UNICODE_LunosClockFrame ( int lunosClockIndicatorDelay, int span, Color enabledColour, Color disabledColour, Color ignoreColour, Color outlineColour, Color enabledColourIndicator, Color disabledColourIndicator, Color ignoreColourIndicator, Color outlineColourIndicator,Color backgroundColour, int frameX, int frameY, int visualDelayLimit )
    {
        panel = new UNICODE_LunosClock ( lunosClockIndicatorDelay, 180, 380, span, enabledColour, disabledColour, ignoreColour, outlineColour, enabledColourIndicator, disabledColourIndicator, ignoreColourIndicator, outlineColourIndicator, backgroundColour, 40, -120, -128, -120, visualDelayLimit, this );
        
        //establish Jframe stuff
            //establish look and feel
            setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
            //remove frame from window
            setUndecorated ( true );
            //add gui panel
            add ( panel );
            //corectly display all panel components by passing panel to set content pane
            setContentPane ( panel ); /*translucency requirement*/
    		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
    		getRootPane ( ).setWindowDecorationStyle ( 0 );
            //set application window dimension
            setSize ( new Dimension ( 280, 160 ) );
            //positions frame
            setLocation ( frameX, frameY );
            //set frame shape
            setShape ( new RoundRectangle2D.Double ( 0, 0, 280, 160, 20, 20 ) );  
            //set opacity
            setOpacity ( 0.8f );
            //show the frame
            setVisible ( true );
            setAlwaysOnTop ( true );
    }
    
    //allows maximum customization
    public UNICODE_LunosClockFrame ( int lunosClockIndicatorDelay, int segmentPanelWidth, int segmentPanelHeight, int mostSignifigantElementSpan, Color enabledColour, Color disabledColour, Color ignoreColour, Color outlineColour, Color enabledColourIndicator, Color disabledColourIndicator, Color ignoreColourIndicator, Color outlineColourIndicator,Color backgroundColour, int xExternalDisplacement, int yExternalDisplacement, int xExternalDisplacement2, int yExternalDisplacement2, int frameX, int frameY, int visualDelayLimit )
    {
        panel = new UNICODE_LunosClock ( lunosClockIndicatorDelay, segmentPanelWidth, segmentPanelHeight, mostSignifigantElementSpan, enabledColour, disabledColour, ignoreColour, outlineColour, enabledColourIndicator, disabledColourIndicator, ignoreColourIndicator, outlineColourIndicator, backgroundColour, xExternalDisplacement, yExternalDisplacement, xExternalDisplacement2, yExternalDisplacement2, visualDelayLimit, this );
        
        
        //establish Jframe stuff
            //establish look and feel
            setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
            //remove frame from window
            setUndecorated ( true );
            //add gui panel
            add ( panel );
            //corectly display all panel components by passing panel to set content pane
            setContentPane ( panel ); /*translucency requirement*/
    		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
    		getRootPane ( ).setWindowDecorationStyle ( 0 );
            //set application window dimension
            setSize ( new Dimension ( 280, 160 ) );
            //positions frame
            setLocation ( frameX, frameY );
            //set frame shape
            setShape ( new RoundRectangle2D.Double ( 0, 0, 280, 160, 20, 20 ) );  
            //set opacity
            setOpacity ( 0.8f );
            //show the frame
            setVisible ( true );
            setAlwaysOnTop ( true );
    }
    
    //SAME AS ABOVE, BUT AUTOMATICALLY POSITIONS CLOCK CENTRAL SCREEN BUFFER,
        //sets up most components, leaving you to choose colour scheme only, in addition to max delay between segment updates, and ofcourse frame location.
        public UNICODE_LunosClockFrame ( int lunosClockIndicatorDelay, int span, Color enabledColour, Color disabledColour, Color ignoreColour, Color outlineColour, Color enabledColourIndicator, Color disabledColourIndicator, Color ignoreColourIndicator, Color outlineColourIndicator, Color backgroundColour, int visualDelayLimit )
        {
            panel = new UNICODE_LunosClock ( lunosClockIndicatorDelay, 180, 380, span, enabledColour, disabledColour, ignoreColour, outlineColour, enabledColourIndicator, disabledColourIndicator, ignoreColourIndicator, outlineColourIndicator, backgroundColour, 40, -120, -128, -120, visualDelayLimit, this );
            
            //establish Jframe stuff
                //establish look and feel
                setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
                //remove frame from window
                setUndecorated ( true );
                //add gui panel
                add ( panel );
                //corectly display all panel components by passing panel to set content pane
                setContentPane ( panel ); /*translucency requirement*/
        		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
        		getRootPane ( ).setWindowDecorationStyle ( 0 );
                //set application window dimension
                setSize ( new Dimension ( 280, 160 ) );
                //center application on screen buffer based on user's screen size
                setLocationRelativeTo ( null );
                //set frame shape
                setShape ( new RoundRectangle2D.Double ( 0, 0, 280, 160, 20, 20 ) );  
                //set opacity
                setOpacity ( 0.8f );
                //show the frame
                setVisible ( true );
                setAlwaysOnTop ( true );
        }
        
        //allows maximum customization
        public UNICODE_LunosClockFrame ( int lunosClockIndicatorDelay, int segmentPanelWidth, int segmentPanelHeight, int mostSignifigantElementSpan, Color enabledColour, Color disabledColour, Color ignoreColour, Color outlineColour, Color enabledColourIndicator, Color disabledColourIndicator, Color ignoreColourIndicator, Color outlineColourIndicator, Color backgroundColour, int xExternalDisplacement, int yExternalDisplacement, int xExternalDisplacement2, int yExternalDisplacement2, int visualDelayLimit )
        {
            panel = new UNICODE_LunosClock ( lunosClockIndicatorDelay, segmentPanelWidth, segmentPanelHeight, mostSignifigantElementSpan, enabledColour, disabledColour, ignoreColour, outlineColour, enabledColourIndicator, disabledColourIndicator, ignoreColourIndicator, outlineColourIndicator, backgroundColour, xExternalDisplacement, yExternalDisplacement, xExternalDisplacement2, yExternalDisplacement2, visualDelayLimit, this );
            
            
            //establish Jframe stuff
                //establish look and feel
                setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
                //remove frame from window
                setUndecorated ( true );
                //add gui panel
                add ( panel );
                //corectly display all panel components by passing panel to set content pane
                setContentPane ( panel ); /*translucency requirement*/
        		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
        		getRootPane ( ).setWindowDecorationStyle ( 0 ); 
                //set application window dimension
                setSize ( new Dimension ( 280, 160 ) );
                //center application on screen buffer based on user's screen size
                setLocationRelativeTo ( null );
                //set frame shape
                setShape ( new RoundRectangle2D.Double ( 0, 0, 280, 160, 20, 20 ) );  
                //set opacity
                setOpacity ( 0.8f );
                //show the frame
                setVisible ( true );
                setAlwaysOnTop ( true );
        }
}