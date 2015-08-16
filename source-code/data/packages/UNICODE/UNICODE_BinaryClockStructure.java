//Author:Jordan Micah Bennett
package data.packages.UNICODE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class UNICODE_BinaryClockStructure
{
	//attributes
	private Ellipse2D body = null;
	private int state = 0;
	private int decimalValue = 0;
	private Color enabledStateColour = null, disabledStateColour = null, currentColour = null, targetBackgroundColour = null;
	
	//constructor
	public UNICODE_BinaryClockStructure ( int state, int decimalValue, Color enabledStateColour, Color disabledStateColour, Color targetBackgroundColour, int x, int y, int span )
	{
		this.state = state;
		this.decimalValue = decimalValue;
		this.enabledStateColour = enabledStateColour;
		this.disabledStateColour = disabledStateColour;
		this.targetBackgroundColour = targetBackgroundColour;
		body = new Ellipse2D.Double ( x, y, span, span );
	}
	
	//methods
		//accessors
		public int getState ( )
		{
			return state;
		}
		public int getDecimalValue ( )
		{
			return decimalValue;
		}
		public int getSpan ( )
		{
		    return ( int )  body.getWidth ( );
		}
		public Ellipse2D getBody ( )
		{
			return body;
		}
		public Color getEnabledStateColour ( )
		{
		    return enabledStateColour;
		}
		public Color getDisabledStateColour ( )
		{
		    return disabledStateColour;
		}	 
		
		//mutators
		public void setState ( int value )
		{
			state = value;
		}
		
	
		//others
		public void determineCurrentColour ( Graphics2D graphics2d )
		{
			switch ( state )
			{
			    case -1: currentColour = targetBackgroundColour; break; //idle/Ignored
				case 1: currentColour = enabledStateColour; break;
				case 0: currentColour = disabledStateColour; break;
			}
			graphics2d.setColor ( currentColour );
		}
		
		public void draw ( Graphics2D graphics2d )
		{
			determineCurrentColour ( graphics2d );
			graphics2d.fill ( body );
		}
}