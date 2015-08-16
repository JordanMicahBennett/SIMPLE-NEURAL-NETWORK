//Author: Jordan Micah Bennett
import javax.swing.JFrame;
import java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Rectangle2D;
import java.awt.Toolkit;
import java.awt.Dimension;
import data.packages.UNICODE.*;

public class UNICODE_DisplayConsole
{

    //ESTABLISH FRAME
    static JFrame frame = new JFrame ( );

    //establish main program gui panel stuff
        //establish convenience pack
        static UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
        //establish duration pack for all splash panels 
        static int [ ] splashPanelDurationCollection = { 100, 100 };
        //establish screen dimensions
        static Dimension screenDimension = Toolkit.getDefaultToolkit ( ).getScreenSize ( );
        //establish gui panel: takes frame,
        static UNICODE_GuiPanel mainProgramPanel = new UNICODE_GuiPanel ( frame, ( int ) screenDimension.getWidth ( ), ( int ) screenDimension.getHeight ( ) );

        
    //establish splash screen panel stuff
        static GENERIC_SPLASH_PANEL splashPanelI0 = null, splashPanelI1 = null;
        
    public static void main ( String [ ] args ) 
    {
        //main program stuff
        Thread mainPogramThread = new Thread
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    //establish Jframe stuff
                        //remove splash panel
                        frame.remove ( splashPanelI1 ); //splashPanel has been defined at this point of run time. Now we can remove splash panel from the frame, and add the main program panel instead.
                        //add main program panel
                        frame.add ( mainProgramPanel );
                        //correctly display all panel components by passing panel to set content pane
                        frame.setContentPane ( mainProgramPanel ); /*translucency requirement*/
                        //set application window dimension
                        frame.setSize ( ( int ) screenDimension.getWidth ( ), ( int ) screenDimension.getHeight ( ) );
                        //center application on screen buffer based on user's screen size
                        frame.setLocationRelativeTo ( null );
                        //enable full screen mode
                        //conveniencePack.enableFullScreenMode ( frame );
                        //set opacity
                        frame.setOpacity ( 1f );
                }
            }
        );

        
        //splash screen stuff
            splashPanelI1 = new GENERIC_SPLASH_PANEL ( splashPanelDurationCollection [ 1 ], "data/images/splash1.png", mainPogramThread, null ); 
            Thread splashPanelI1Thread = new Thread
            (
                new Runnable ( )
                {
                    public void run ( )
                    {  
                        //establish Jframe stuff      
                            //clear previous splash panel from frame.
                            frame.remove ( splashPanelI0 ); //splashPanel has been defined at this point of run time. Now we can remove splash panel from the frame, and add the main program panel instead.
                            //add gui panel
                            frame.add ( splashPanelI1 );
                            //corectly display all panel components by passing panel to set content pane
                            frame.setContentPane ( splashPanelI1 ); /*translucency requirement*/
                            //set application window dimension
                            frame.setSize ( new Dimension ( 500, 220 ) );
                            //center application on screen buffer based on user's screen size
                            frame.setLocationRelativeTo ( null );
                            //show the frame
                            frame.setVisible ( true );
                    }
                }
            );
            //ENGINE LOGO
            splashPanelI0 = new GENERIC_SPLASH_PANEL ( splashPanelDurationCollection [ 0 ], "data/images/splash0.png", splashPanelI1Thread, splashPanelI1 ); 
            Thread splashPanelI0Thread = new Thread
            (
                new Runnable ( )
                {
                    public void run ( )
                    {
                        //establish Jframe stuff
                            //establish look and feel
                            frame.setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
                            //remove frame from window
                            frame.setUndecorated ( true );
                            //add gui panel
                            frame.add ( splashPanelI0 );
                            //corectly display all panel components by passing panel to set content pane
                            frame.setContentPane ( splashPanelI0 ); /*translucency requirement*/
                            //set application window dimension
                            frame.setSize ( new Dimension ( 500, 220 ) );
                            //center application on screen buffer based on user's screen size
                            frame.setLocationRelativeTo ( null );
                            //show the frame
                            frame.setVisible ( true );
                    }
                }
            );
            splashPanelI0.commence ( );
            splashPanelI0Thread.start ( );
    }

}