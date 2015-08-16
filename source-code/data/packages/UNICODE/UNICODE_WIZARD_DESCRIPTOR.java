package data.packages.UNICODE;
//package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.AlphaComposite;

public class UNICODE_WIZARD_DESCRIPTOR
{
    //attributes
    private UNICODE_MessageBoxWindow wizardDescriptorBody = null;
    private double descriptorBodyWidth, descriptorBodyHeight;
    private int textWidth, textHeight;
    private UNICODE_WIZARD_BODY wizardBodyClass = null;
    
    //scrolling
    private int scrollRightwardsAdjuster = 0, scrollLeftwardsAdjuster = 0;
    
    //constructor
    public UNICODE_WIZARD_DESCRIPTOR ( UNICODE_WIZARD_BODY _wizardBodyClass, double _descriptorBodyWidth, double _descriptorBodyHeight, UNICODE_MessageBoxWindow _wizardDescriptionBody )
    {
        //establish dimension
        descriptorBodyWidth = _descriptorBodyWidth;
        descriptorBodyHeight = _descriptorBodyHeight;
        
        //establish wizard body
        wizardBodyClass = _wizardBodyClass;
        
        //establish wizard dimensions
        double wizardBodyX = wizardBodyClass.getBody ( ).getX ( );
        double wizardBodyY = wizardBodyClass.getBody ( ).getY ( );
        double wizardBodyWidth = wizardBodyClass.getBody ( ).getWidth ( );
        double wizardBodyHeight = wizardBodyClass.getBody ( ).getHeight ( );

        //establish wizard descriptor shape - formulae to center a child component on parent, given that the child was already established, and rather adopted by a parent = cX - pW/2 + cW/2
        wizardDescriptorBody = _wizardDescriptionBody;
        wizardDescriptorBody.setLocation ( ( int ) ( wizardBodyX - descriptorBodyWidth/2 + wizardBodyWidth/2 ), ( int ) ( wizardBodyY - descriptorBodyHeight/2 + wizardBodyHeight/2 ) );//, ( int ) descriptorBodyWidth, ( int ) descriptorBodyHeight );
        wizardDescriptorBody.setAlwaysOnTop ( true );
    }
    
    //accessors
    public UNICODE_MessageBoxWindow getBody ( )
    {
        return wizardDescriptorBody;
    }
    
    public void move ( String direction, int scrollRate )
    {
        if ( direction.equals ( "rightwards" ) )
        {
            scrollRightwardsAdjuster = getBody ( ).getX ( ) + ( int ) scrollRate;
                getBody ( ).setLocation ( scrollRightwardsAdjuster, getBody ( ).getY ( ) );
        }
        else if ( direction.equals ( "leftwards" ) )
        {
            scrollLeftwardsAdjuster = getBody ( ).getX ( ) - ( int ) scrollRate;
                getBody ( ).setLocation ( scrollLeftwardsAdjuster, getBody ( ).getY ( ) );
        }
    }
}
