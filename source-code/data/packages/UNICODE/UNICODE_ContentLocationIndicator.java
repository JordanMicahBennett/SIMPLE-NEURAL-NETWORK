package data.packages.UNICODE;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;

public class UNICODE_ContentLocationIndicator
{
    //attributes
        //establish bodies
        private Ellipse2D minimalisticIndicator = null;
        private Ellipse2D maximalIndicator = null;
        //establish body dimensions
        private double minimumWidth = 0, maximumWidth = 0;
		//establish orientation
		private int xIndicatorCoordA = 0, xIndicatorCoordB = 0, yIndicatorCoordA = 0, yIndicatorCoordB = 0;
        //establish spatial separator representation
        private int indicatorSeparationSpace = 0;
        //establish visual
        private Color indicatorColour = null;
		//screen 
		private Dimension screenDimension = null;
        
		//orientation maintainance requirments
		//the content location indicator will be blitted on the bui field, which moves.
		//we want its orientation to remain constant, such that it stays centered on buffer. So we introduce some stablilizing functions, via these variables.
		private double contentScrollRate = 0;
		private double scrollRightwardsAdjusterA, scrollRightwardsAdjusterB;
		private double scrollLeftwardsAdjusterA, scrollLeftwardsAdjusterB;

	
    //constructor
    public UNICODE_ContentLocationIndicator ( Color _indicatorColour, int _indicatorSeparationSpace, double _minimumWidth, double _maximumWidth, Dimension _screenDimension, double _contentScrollRate )
    {
        //establish colour
        indicatorColour = _indicatorColour;
        
        //establish indicatorSeparationSpace
        indicatorSeparationSpace = _indicatorSeparationSpace;
		
		//establish scroll rate
		contentScrollRate = _contentScrollRate;
        
        //establish dimensions
        minimumWidth = _minimumWidth;
        maximumWidth = _maximumWidth;
		
		//establish screen dimension
		screenDimension = _screenDimension;
        
        //using screen dimension, and indicatorSeparationSpace, compute the coordinates or the indicators.
        //such that they fit bottom the screen, and centered.
            //get screen dimensions
            int screenWidth = ( int ) screenDimension.getWidth ( );
            int screenHeight = ( int ) screenDimension.getHeight ( );
            //compute coordinates'
            yIndicatorCoordA = ( int ) ( screenHeight - ( maximumWidth + ( maximumWidth / 2 ) ) ); //just above bottom the buffer. Y cord is viable for both indicators.
			yIndicatorCoordB = ( int ) ( screenHeight - ( maximumWidth + ( maximumWidth / 2 ) ) ); //just above bottom the buffer. Y cord is viable for both indicators.
            xIndicatorCoordA = ( int ) ( ( screenWidth / 2 ) - ( maximumWidth / 2 ) ); //x coordinate for leftmost minimalistic indicator
            xIndicatorCoordB = ( int ) ( indicatorSeparationSpace + xIndicatorCoordA ); //x coordinate for rightmost maximal indicator
   
        //establish bodies appropriately
        minimalisticIndicator = new Ellipse2D.Double ( xIndicatorCoordA, yIndicatorCoordA, maximumWidth, maximumWidth );
        maximalIndicator = new Ellipse2D.Double ( xIndicatorCoordB, yIndicatorCoordB, minimumWidth, minimumWidth );
    }
    
    //methods
	
	//accessors
	public double getMaxWidth ( )
	{
		return maximumWidth;
	}
	
	//funcs
    public void draw ( Graphics2D graphics2d )
    {
        //establish colour visual
        graphics2d.setColor ( indicatorColour );
        
        //draw the indicators
		graphics2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.8f ) ); //set alpha composite of descriptor 	
        graphics2d.fill ( minimalisticIndicator );
        graphics2d.fill ( maximalIndicator );
		graphics2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 1.0f ) ); //reset alpha composite of descriptor, so other components are unaffected.
    }

	//method to maintain orientation of content indicators
	public void maintainOrientation ( String scrollDirection )
	{
		if ( scrollDirection.equals ( "rightwards" ) )
		{
			scrollRightwardsAdjusterA = minimalisticIndicator.getX ( ) + ( int ) contentScrollRate;
			minimalisticIndicator.setFrame ( new Rectangle ( ( int ) scrollRightwardsAdjusterA, yIndicatorCoordA, ( int ) minimalisticIndicator.getWidth ( ), ( int ) minimalisticIndicator.getHeight ( ) ) );
			
			scrollRightwardsAdjusterB = maximalIndicator.getX ( ) + ( int ) contentScrollRate;
			maximalIndicator.setFrame ( new Rectangle ( ( int ) scrollRightwardsAdjusterB, yIndicatorCoordB, ( int ) maximalIndicator.getWidth ( ), ( int ) maximalIndicator.getHeight ( ) ) );
		}
		else if ( scrollDirection.equals ( "leftwards" ) )
		{
			scrollLeftwardsAdjusterA = minimalisticIndicator.getX ( ) - ( int ) contentScrollRate;
			minimalisticIndicator.setFrame ( new Rectangle ( ( int ) scrollLeftwardsAdjusterA, yIndicatorCoordA, ( int ) minimalisticIndicator.getWidth ( ), ( int ) minimalisticIndicator.getHeight ( ) ) );
			
			scrollLeftwardsAdjusterB = maximalIndicator.getX ( ) - ( int ) contentScrollRate;
			maximalIndicator.setFrame ( new Rectangle ( ( int ) scrollLeftwardsAdjusterB, yIndicatorCoordB, ( int ) maximalIndicator.getWidth ( ), ( int ) maximalIndicator.getHeight ( ) ) );
		}
	}
	
	int scrollRightwardsDimensionAdjusterA, scrollRightwardsDimensionAdjusterB;
	int	scrollLeftwardsDimensionAdjusterA, scrollLeftwardsDimensionAdjusterB;
	
	
	//method to perform relevant animation to indicate that scrolling ensues.
	public void performAnimation ( String scrollDirection )
	{
		if ( scrollDirection.equals ( "rightwards" ) )
		{
			//minimalstic
				//maintain orientation, while mutating dimention
					//establish orientational maintainance var
					scrollRightwardsAdjusterA = minimalisticIndicator.getX ( ) + ( int ) contentScrollRate;
					//establish dimension mutation var
					if ( minimalisticIndicator.getWidth ( ) > minimumWidth )
						scrollRightwardsDimensionAdjusterA = ( int ) ( minimalisticIndicator.getWidth ( ) - 1 );
					else
						scrollRightwardsDimensionAdjusterA = ( int ) minimalisticIndicator.getWidth ( );
					//mutate minimalistic body wrt vars established above.	
					minimalisticIndicator.setFrame ( new Rectangle ( ( int ) scrollRightwardsAdjusterA, yIndicatorCoordA, scrollRightwardsDimensionAdjusterA, scrollRightwardsDimensionAdjusterA ) );
			//maximal
				//maintain orientation, while mutating dimention
					//establish orientational maintainance var
					scrollRightwardsAdjusterB = maximalIndicator.getX ( ) + ( int ) contentScrollRate;
					//establish dimension mutation var
					if ( maximalIndicator.getWidth ( ) < maximumWidth )
						scrollRightwardsDimensionAdjusterB = ( int ) ( maximalIndicator.getWidth ( ) + 1 ); 
					else
						scrollRightwardsDimensionAdjusterB = ( int ) maximalIndicator.getWidth ( );
					//mutate minimalistic body wrt vars established above.	
					maximalIndicator.setFrame ( new Rectangle ( ( int ) scrollRightwardsAdjusterB, yIndicatorCoordB, scrollRightwardsDimensionAdjusterB, scrollRightwardsDimensionAdjusterB ) );
		}
		else if ( scrollDirection.equals ( "leftwards" ) )
		{
			//minimalstic
				//maintain orientation, while mutating dimention
					//establish orientational maintainance var
					scrollLeftwardsAdjusterA = minimalisticIndicator.getX ( ) - ( int ) contentScrollRate;
					//establish dimension mutation var
					if ( minimalisticIndicator.getWidth ( ) < maximumWidth )
						scrollLeftwardsDimensionAdjusterA = ( int ) ( minimalisticIndicator.getWidth ( ) + 1 );
					else
						scrollLeftwardsDimensionAdjusterA = ( int ) minimalisticIndicator.getWidth ( );
					//mutate minimalistic body wrt vars established above.	
					minimalisticIndicator.setFrame ( new Rectangle ( ( int ) scrollLeftwardsAdjusterA, yIndicatorCoordA, scrollLeftwardsDimensionAdjusterA, scrollLeftwardsDimensionAdjusterA ) );
			//maximal
				//maintain orientation, while mutating dimention
					//establish orientational maintainance var
					scrollLeftwardsAdjusterB = maximalIndicator.getX ( ) - ( int ) contentScrollRate;
					//establish dimension mutation var
					if ( maximalIndicator.getWidth ( ) > minimumWidth )
						scrollLeftwardsDimensionAdjusterB = ( int ) ( maximalIndicator.getWidth ( ) - 1 ); 
					else
						scrollLeftwardsDimensionAdjusterB = ( int ) maximalIndicator.getWidth ( );
					//mutate minimalistic body wrt vars established above.	
					maximalIndicator.setFrame ( new Rectangle ( ( int ) scrollLeftwardsAdjusterB, yIndicatorCoordB, scrollLeftwardsDimensionAdjusterB, scrollLeftwardsDimensionAdjusterB ) );
		}
	}
	
	public void changeColour ( Color value )
	{
		indicatorColour = value;
	}
}
