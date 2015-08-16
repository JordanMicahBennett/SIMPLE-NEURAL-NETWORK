package data.packages.UNICODE; //Author(s): Jordan Micah Bennett 
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UNICODE_LayerAnimationPackage 
{
    //attributes
    
    //layer's screen pack
    private UNICODE_GuiScreen_DynamicScreen layerPackage = null;
    
    //layers Timer clock
    private Timer layerAnimationTimer = null;
    
    //layer's cycle limit  and animations step delay
    private int numAnimationCycles = 0, animStepDelay = 0, currentAnimIndex = 0;

    //establish audio player
    private UNICODE_AudioPlayer audioPlayer = new UNICODE_AudioPlayer ( );
    
    //establish panel
    private JPanel panel = null;
    
    //establsih clip final stop off point - this is when the animation reaches climax, and is ready to set frame to a particular point and set panel.
    private int clipClimaxPoint = 0;
    

    public UNICODE_LayerAnimationPackage ( int x, int y, int width, int height, int frameX, int frameY, int frameLoopLimit, int _numAnimationCycles, int _animStepDelay, int _clipClimaxPoint, String imageStream, boolean itemUsageFlag, int screenMultiplier, boolean enable_hover_mode, boolean enable_sLeftGlide, boolean enable_sRightGlide, JPanel _panel )
    {
        //initialize layer screen package
        layerPackage = new UNICODE_GuiScreen_DynamicScreen ( x, y, width, height, frameX, frameY, frameLoopLimit, imageStream, itemUsageFlag, screenMultiplier, enable_hover_mode, enable_sLeftGlide, enable_sRightGlide );
        //ensure that frame loop limit is max screens
        layerPackage.setFLL ( width * screenMultiplier );
        //animation cycles
        numAnimationCycles = _numAnimationCycles;
        //animation step delay
        animStepDelay = _animStepDelay;
        //establish clip stop off point 
        clipClimaxPoint = _clipClimaxPoint;
        //establish animation timer
        layerAnimationTimer = new Timer ( animStepDelay, getLayerAnimationActionListener ( ) );
        //establish panel 
        panel = _panel;
    }
    
    //accesors
    public int getNumAnimationCycles ( )
    {
        return numAnimationCycles;
    }
    
    public int getAnimStepDelay ( )
    {
        return animStepDelay;
    }
    
    public void setCurrentMenuItem ( int startIndex )
    {
        layerPackage.setCurrentMenuItem ( startIndex );
    }
    
    public void drawPackage ( Graphics graphics )
    {
        layerPackage.drawDynamic ( graphics, panel );
    }
    
    //utils
    public void startAnimation ( )
    {
        layerAnimationTimer.start ( );
    }
    
    public void stopAnimation ( )
    {
        layerAnimationTimer.stop ( );
    }   
    
    public UNICODE_GuiScreen_DynamicScreen getLayerPackage ( )
    {
        return layerPackage;
    }
    
    
    public ActionListener getLayerAnimationActionListener ( )
    {
        return new ActionListener ( )
        {
            public void actionPerformed ( ActionEvent aEvent )
            {
                if ( currentAnimIndex < getNumAnimationCycles ( ) )
                {
                    currentAnimIndex ++;
                    if ( layerPackage.getFX ( ) < layerPackage.getFLL ( ) )
                        layerPackage.incFX ( );//manageScreenUpdate ( "next", "", audioPlayer );  
                    else
                    {
                        if ( currentAnimIndex >= clipClimaxPoint )
                        {
                            currentAnimIndex = 0;
                            layerPackage.setFX ( 1280 * 2 );
                            layerAnimationTimer.stop ( );
                        }
                    }
                }
                panel.repaint ( );
            }
        };
    }
}
