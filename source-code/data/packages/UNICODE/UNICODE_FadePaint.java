package data.packages.UNICODE; 
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:---------------------------------------------:
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
//USAGE: for the last param, we supply leftColourSwingPercentFactor values from 0-100. The more the percentile, the more of the first colour "leftColour" will arrise, in the supplied direction. 
//......A value of 0 implies regular ratio distribution, half colouri, half colourii. More means more colouri, and less colourii.
//REQUIRMENTS: UNICODE_PaintDirections direction, two colours to blend, target dimension (width, height) and leftColourSwingPercentFactor.


public class UNICODE_FadePaint
{
    //attributes
        private UNICODE_FadePaintDirections direction;
        //establish paint properties
            //body
            private GradientPaint paint;
            //orientation ( direction )
            private float xiCoordinate, yiCoordinate, xiiCoordinate, yiiCoordinate;
            //colour
            private Color colouri, colourii;
            //direction controllers
            //orientation controllers
                //target surface dimension
                private int targetWidth, targetHeight;
                //ratio
                private double leftColourSwingPercentFactor;
                //paint bounds
                private Rectangle paintBounds;

    //constructor
    public UNICODE_FadePaint ( UNICODE_FadePaintDirections direction, Color colouri, Color colourii, int targetWidth, int targetHeight, double leftColourSwingPercentFactor )
    {
        //establish paint properties
            //colours
            this.colouri = colouri;
            this.colourii = colourii;
            //orientation controllers
                //target surface dimension
                this.targetWidth = targetWidth;
                this.targetHeight = targetHeight;    
                //ration
                this.leftColourSwingPercentFactor = leftColourSwingPercentFactor;       
            //orientation ( direction )
            this.direction = direction;
            determineDirection ( );
            //paint bounds
            paintBounds = new Rectangle ( 0, 0, targetWidth, targetHeight );
            //paint
            paint = new GradientPaint ( xiCoordinate, yiCoordinate, colouri, xiiCoordinate, yiiCoordinate, colourii ); 
    }
    
    //methods
    public void draw ( Graphics2D graphics2d )
    {
        graphics2d.setPaint ( paint );
        graphics2d.fill ( paintBounds );
    }
	
	public void changeColour ( Color value0, Color value1 )
	{
		paint = new GradientPaint ( xiCoordinate, yiCoordinate, value0, xiiCoordinate, yiiCoordinate, value1 ); 
	}
    
    public void determineDirection ( )
    {
            switch ( direction )
            {
                case UPWARD:
                {
                    xiCoordinate = 0;
                    yiCoordinate = ( float ) ( targetHeight - ( ( leftColourSwingPercentFactor / 100 ) * targetHeight ) ); //start is the bottom
                    xiiCoordinate = 0;
                    yiiCoordinate = 0;
                }
                break;
                case DOWNWARD:
                {
                    xiCoordinate = 0;
                    yiCoordinate = 0;
                    xiiCoordinate = 0;
                    yiiCoordinate = ( float ) ( targetHeight + ( ( leftColourSwingPercentFactor / 100 ) * targetHeight ) ); //start is the top
                }
                break;
                case RIGHTWARD:
                {
                    xiCoordinate = 0;
                    yiCoordinate = 0; 
                    xiiCoordinate = ( float ) ( targetWidth + ( ( leftColourSwingPercentFactor / 100 ) * targetWidth ) ); //start is the left
                    yiiCoordinate = 0;
                }
                break;
                case LEFTWARD:
                {
                    xiCoordinate = ( float ) ( targetWidth - ( ( leftColourSwingPercentFactor / 100 ) * targetWidth ) ); //start is the right
                    yiCoordinate = 0;
                    xiiCoordinate = 0;
                    yiiCoordinate = 0;
                }
                break;
                case UPWARDANDRIGHTWARD:
                {
                    xiCoordinate = 0;
                    yiCoordinate = ( float ) ( targetHeight - ( ( leftColourSwingPercentFactor / 100 ) * targetHeight ) ); //start is the bottom
                    xiiCoordinate = ( float ) ( targetWidth + ( ( leftColourSwingPercentFactor / 100 ) * targetWidth ) ); //start is the left
                    yiiCoordinate = 0;
                }
				break;
            }
    }
}