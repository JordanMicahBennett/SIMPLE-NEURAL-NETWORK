package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
//Author: Jordan Micah Bennett

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;


public abstract class UNICODE_SplashPanel extends JPanel
{
    //attributes
    private Timer timer = null;
    private int cycleIndex = 0;
    private ImageIcon imageIcon = null;
    private Image image = null;
    private int splashScreenDuration = 0;
    private Thread nextThread = null;
	private Thread currentThread = null;
	private UNICODE_SplashPanel nextSplashPanel = null;
	
    //constructor
    public UNICODE_SplashPanel ( int _splashScreenDuration, String splashScreenImageDirectory, Thread _nextThread, UNICODE_SplashPanel _nextSplashPanel )
    {
		//initialise next splash panel
		nextSplashPanel = _nextSplashPanel;
	
        //initialise splash screen duration'
        splashScreenDuration = _splashScreenDuration;
        
        //intialise main program thread
        nextThread = _nextThread;
        
        //initialise timer with respect to programmer supplied splash screen duration amount, and action listener which will induce cycle index incrementation.
        timer = new Timer ( 1, new actionListener ( ) );
        
        //initialise splash image
        imageIcon = new ImageIcon ( splashScreenImageDirectory );
        image = imageIcon.getImage ( );
        image = createImage ( image.getSource ( ) );
    }
    
    
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
		Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        //draw the splash screen centrally on panel
        if ( cycleIndex < splashScreenDuration )
		{
            graphics.drawImage ( image, 0, 0, this );
			
			//draw more
			drawMore ( graphics2d );
		}
    }
	
	public abstract void drawMore ( Graphics2D graphics2d );
	
	public void commence ( )
	{
		timer.start ( );
	}
	
	public void end ( )
	{
		timer.stop ( );
		cycleIndex = 0;
	}
	
	
	public void setCurrentThread ( Thread value )
	{
		currentThread = value;
	}
    
    //action listener for cycle steps
    private class actionListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent actionEvent )
        {
			if ( cycleIndex < splashScreenDuration )
				cycleIndex ++;
			else
			{
				end ( );
				if ( nextSplashPanel != null )
					nextSplashPanel.commence ( );
				nextThread.start ( );
			}
            repaint ( );
        }
    }
}
