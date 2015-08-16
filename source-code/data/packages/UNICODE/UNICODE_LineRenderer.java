package data.packages.UNICODE;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;

public class UNICODE_LineRenderer
{
    //constructor
    public UNICODE_LineRenderer ( )
    {
    }
    
    //methods
    public void drawDottedLine ( Graphics graphics, int xCoordA, int yCoordA, int xCoordB, int yCoordB, double dotLength, double spatialDistance, Color lineColour, Rectangle contextFrame )
    {
        graphics.setColor ( lineColour );
        
        double lineLength = Math.sqrt ( ( xCoordB - xCoordA ) * ( xCoordB - xCoordA ) + ( yCoordB - yCoordA ) * ( yCoordB - yCoordA ) );
        double yIncrementation = ( yCoordB - yCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
        double xIncrementationSpatialDistance = ( xCoordB - xCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
        double yIncrementationSpatialDistance = ( yCoordB - yCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );

		int counter = 0;
		
        for ( double i = 0; i < lineLength - dotLength; i += dotLength + spatialDistance )
        {
			int xCoord = ( int ) ( xCoordA + xIncrementationSpatialDistance * counter );
			int yCoord = ( int ) ( yCoordA + yIncrementationSpatialDistance * counter );

			if ( contextFrame.contains ( xCoord, yCoord ) )
			{
				graphics.fillOval ( xCoord, yCoord, ( int ) dotLength, ( int ) dotLength );  
				counter ++;  
			}	
        }
    }
    
    public void drawDashedLine ( Graphics graphics, int xCoordA, int yCoordA, int xCoordB, int yCoordB, double dotLength, double spatialDistance, Color lineColour )
    {
        graphics.setColor ( lineColour );
        
        if ( ( xCoordA == xCoordB ) && ( yCoordA == yCoordB ) )
        {
            graphics.drawLine ( xCoordA, yCoordA, xCoordB, yCoordB );
            return;
        }
        
        double lineLength = Math.sqrt ( ( xCoordB - xCoordA ) * ( xCoordB - xCoordA ) + ( yCoordB - yCoordA ) * ( yCoordB - yCoordA ) );
        double yIncrementation = ( yCoordB - yCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
        double xIncrementationSpatialDistance = ( xCoordB - xCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
        double yIncrementationSpatialDistance = ( yCoordB - yCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
        double xIncrementationDash = ( xCoordB - xCoordA ) / ( lineLength / ( dotLength ) );
        double yIncrementationDash = ( yCoordB - yCoordA ) / ( lineLength / ( dotLength ) );
        int counter = 0;
        
        for ( double i = 0; i < lineLength - dotLength; i += dotLength + spatialDistance )
        {
            int _xCoordA = ( int ) ( xCoordA + xIncrementationSpatialDistance * counter );
            int _yCoordA = ( int ) ( yCoordA + yIncrementationSpatialDistance * counter );
            int _xCoordB = ( int ) ( xCoordA + xIncrementationSpatialDistance * counter + xIncrementationDash );
            int _yCoordB = ( int ) ( yCoordA + yIncrementationSpatialDistance * counter + yIncrementationDash );
            graphics.drawLine ( _xCoordA, _yCoordA, _xCoordB, _yCoordB );
            counter++;
        }
        
        if ( ( dotLength + spatialDistance ) * counter <= lineLength )
            graphics.drawLine ( ( int ) ( xCoordA + xIncrementationSpatialDistance * counter ), ( int ) ( yCoordA + yIncrementationSpatialDistance * counter ), xCoordB, yCoordB );
    }
}
