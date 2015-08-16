//Author:Jordan Micah Bennett
package data.packages.UNICODE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class UNICODE_ClockElement extends JPanel
{
    //attributes
    private UNICODE_BinaryClockStructure binaryClockStructure = null;
    
    //constructor
    public UNICODE_ClockElement ( int state, int decimalValue, Color enabledStateColour, Color disabledStateColour, Color clockElementBackgroundColour, int x, int y, int span )
    {
        this.binaryClockStructure = new UNICODE_BinaryClockStructure ( state, decimalValue, enabledStateColour, disabledStateColour, clockElementBackgroundColour, x, y, span );
        setBackground ( clockElementBackgroundColour );
    }
    
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        setSize ( new Dimension ( binaryClockStructure.getSpan ( ), binaryClockStructure.getSpan ( ) ) );
        
        binaryClockStructure.draw ( graphics2d );
    }
    
    public void setState ( int value ) 
    {
        binaryClockStructure.setState ( value );
    }
    public int getState ( )
    {
        return binaryClockStructure.getState ( );
    }
    public int getDecimalValue ( )
    {
        return binaryClockStructure.getDecimalValue ( );
    }
}