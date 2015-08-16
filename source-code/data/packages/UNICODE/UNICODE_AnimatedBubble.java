//Author(s): Jordan Micah Bennett
package data.packages.UNICODE;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.AlphaComposite;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.geom.AffineTransform;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class UNICODE_AnimatedBubble
{
	//attributes
	private Ellipse2D animatedBubble = null;
	private UNICODE_ConveniencePack conveniencePack = null;
	
	
	//properties
	private int horizontalSpan = 0, verticalSpan = 0;
	
	//field scrolling requriements
	private int xCoordinate = 0, yCoordinate = 0;
	
	//establish state enquiry
	private boolean animatedBubbleEnableEnquiry = true;
	
		
	//establish line style
	private Stroke descriptorEmbracerLineStyle = null;
	
	//establish spining animation requirements
	private UNICODE_RotationManager rotationManager = null;
	private Timer bubbleAnimationTimer = null;
	
	//destination panel
	private JPanel destinationPanel = null;
			
	//constructor
	public UNICODE_AnimatedBubble ( int xCoordinate, int yCoordinate, int horizontalSpan, int verticalSpan, JPanel destinationPanel )
	{
		//establish destination panel
		this.destinationPanel = destinationPanel;
		
		//establish dimension
		this.horizontalSpan = horizontalSpan;
		this.verticalSpan = verticalSpan;
		
		//establish orientation
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		this.rotationManager = new UNICODE_RotationManager ( );
		
		this.rotationManager.setRotationEnquiry ( true );
		
		conveniencePack = new UNICODE_ConveniencePack ( );
		animatedBubble = new Ellipse2D.Double ( xCoordinate, yCoordinate, horizontalSpan, verticalSpan );
		
		this.descriptorEmbracerLineStyle = new BasicStroke ( 2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float [ ] { 2, 4 }, 0 );
		
		//embracer anim requirements
		this.bubbleAnimationTimer = new Timer ( 10, new bubbleAnimationListener ( ) );	
		
		this.bubbleAnimationTimer.start ( );
	}
	
	//methods
		//accessors
		public boolean getAnimatedBubbleEnableEnquiry ( )
		{
			return animatedBubbleEnableEnquiry;
		}
		
		
		
		//mutators
		public void setAnimatedBubbleEnableEnquiry ( boolean value )
		{
			animatedBubbleEnableEnquiry = value;
		}

		//other
		public void draw ( Graphics2D graphics2d, Color colour )
		{
			//save old transformation
			rotationManager.saveOldTransform ( graphics2d );
			
			if ( getAnimatedBubbleEnableEnquiry ( ) )
			{
				//apply rotation weh rotation bool is set true
				if ( rotationManager.getRotationEnquiry ( ) )
				{
					rotationManager.toggleTransformation ( graphics2d, ( int ) animatedBubble.getX ( ), ( int ) animatedBubble.getWidth ( ), ( int ) animatedBubble.getY ( ), ( int ) animatedBubble.getHeight ( ) );
					
					graphics2d.setColor ( colour );
					graphics2d.setStroke ( descriptorEmbracerLineStyle );
					graphics2d.draw ( animatedBubble );
				}	
		
				
				/*
				//DRAW PARAM STAMPS - TESTING
				//the following two lines:
				//allows for the generation of translucent effects on the fly.
				//Attained via: http://www.java-gaming.org/index.php/topic,1546.
				System.setProperty ( "sun.java2d.translaccel", "true" );
				System.setProperty ( "sun.java2d.ddforcevram", "true" ); 
				
				graphics2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.8f ) ); //set alpha composite of descriptor 
				
				for ( Ellipse2D animatedStamp : animatedStamps )
					graphics2d.fill ( animatedStamp );
				*/
			}
			//restore old affine transform
			rotationManager.restoreOldTransform ( graphics2d );
		}
		
		
		
	private class bubbleAnimationListener implements ActionListener 
	{
		public void actionPerformed ( ActionEvent actionEvent )
		{
			rotationManager.toggleAction ( 0.0224f, "anti-clockwise" );
			destinationPanel.repaint ( );
		}
	}
}