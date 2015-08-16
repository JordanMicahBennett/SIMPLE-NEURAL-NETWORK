//Author(s): Jordan Micah Bennett
package data.packages.UNICODE;
import java.awt.geom.AffineTransform;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import javax.swing.Timer;

public class UNICODE_RotationManager
{ 
	//attributes
	//establish spining animation requirements
		//old transformation data
		private AffineTransform oldAffineTransformation;
		private boolean RotationEnquiry = false;
		private double rotationAngle = Math.PI / 7;
		private int rotationDirectionIndex = 1;
	
	//constructor
	public UNICODE_RotationManager ( )
	{
	}
	
	//methods
		public boolean getRotationEnquiry ( )
		{
			return RotationEnquiry;
		}
		public AffineTransform getOldAffineTransform ( )
		{
			return oldAffineTransformation;
		}
		public int getRotationDirectionIndex ( )
		{
			return rotationDirectionIndex;
		}
		public double getRotationAngle ( )
		{
			return rotationAngle;
		}
		//establish old affine transform ( used in draw function )
		public void saveOldTransform ( Graphics2D graphics2d )
		{
			oldAffineTransformation = graphics2d.getTransform ( );
		}
		//restore old transform so as to not affect other un-selected buttons or 
		//graphics 2d component or object currently on screem
		//only selected items must be scaled, this ensures this.
		public void restoreOldTransform ( Graphics2D graphics2d )
		{
			graphics2d.setTransform ( getOldAffineTransform ( ) );
		}
		public void incRotationAngle ( double value )
		{
			rotationAngle += value;
		}
		public void decRotationAngle ( double value )
		{
			rotationAngle -= value;
		}
		public void setRotationAngle ( double value )
		{
			rotationAngle = value;
		}		
		public void setRotationDirection ( String direction )
		{
			if ( direction.equals ( "clockwise" ) )
				rotationDirectionIndex = 1;
			else if ( direction.equals ( "anti-clockwise" ) )
				rotationDirectionIndex = -1;
		}
		public void setRotationEnquiry ( boolean value )
		{
			RotationEnquiry = value;
		}

		//other
		public void toggleTransformation ( Graphics2D graphics2d, int bodyX, int bodyWidth, int bodyY, int bodyHeight )
		{
			//apply rotation weh rotation bool is set true
			graphics2d.transform ( AffineTransform.getRotateInstance ( getRotationDirectionIndex ( ) * getRotationAngle ( ), bodyX + bodyWidth / 2, bodyY + bodyHeight / 2 ) );
		}
		
		public void toggleAction ( float rotationRate, String rotationDirection )
		{
			incRotationAngle ( rotationRate );
			setRotationDirection ( rotationDirection );
			setRotationEnquiry ( true );
		}
}