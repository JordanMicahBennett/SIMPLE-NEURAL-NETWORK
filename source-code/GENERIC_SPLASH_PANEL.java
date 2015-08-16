//Author: Jordan Micah Bennett
import java.awt.Color;
import java.awt.Graphics2D;

import data.packages.UNICODE.*;

public class GENERIC_SPLASH_PANEL extends UNICODE_SplashPanel
{
    //attributes
    
    
    //constructor
    public GENERIC_SPLASH_PANEL ( int _splashScreenDuration, String splashScreenImageDirectory, Thread _nextThread, UNICODE_SplashPanel _nextSplashPanel )
    {
        super ( _splashScreenDuration, splashScreenImageDirectory, _nextThread, _nextSplashPanel );
    }
    
    
    //extra draw method
    public void drawMore ( Graphics2D graphics2d )
    {
        
    }
}