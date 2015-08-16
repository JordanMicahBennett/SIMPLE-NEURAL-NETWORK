package data.packages.UNICODE;
//package data.packages.UNICODE;
import java.awt.geom.Line2D;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.Color;
public class UNICODE_WIZARD_CONNECTOR
{
    //attributes
    private UNICODE_GlowingLine wizardConnector = null;
    //runtime transformation requirements
    private AffineTransform oldAffineTransformation;
    //establish wizard shape
    private ArrayList <UNICODE_WIZARD_BODY> wizardBodies = null;
	//establish description view controler answer
	private String descriptionViewControllerAnswer = "";
	//establish colour scheme
	private Color connectorColour = null, connectorGlowColour = null;
	//glow properties
	private int connectorThickness = 0, connectorGlowThickness = 0, connectorGlowQualityMultiplier = 0, connectorGlowBrightness = 0;
	
    //constructor
    public UNICODE_WIZARD_CONNECTOR ( int x1, int y1, int x2, int y2, ArrayList <UNICODE_WIZARD_BODY> _wizardBodies, String _descriptionViewControllerAnswer, Color _connectorColour, Color _connectorGlowColour, int _connectorThickness, int _connectorGlowThickness, int _connectorGlowQualityMultiplier, int _connectorGlowBrightness )
    {
		//establish description view controller answer
		descriptionViewControllerAnswer = _descriptionViewControllerAnswer;
		
		//establish colour scheme
		connectorColour = _connectorColour;
		connectorGlowColour = _connectorGlowColour;
		
		//establish glow properties
		connectorThickness = _connectorThickness; 
		connectorGlowThickness = _connectorGlowThickness;
		connectorGlowQualityMultiplier = _connectorGlowQualityMultiplier;
		connectorGlowBrightness = _connectorGlowBrightness;
	
        //establish wizard shape
        wizardBodies = _wizardBodies;
        
        //establish line 2, 10, 2, 0,
        wizardConnector = new UNICODE_GlowingLine ( connectorThickness, connectorGlowThickness, connectorGlowQualityMultiplier, connectorGlowBrightness, connectorColour, connectorGlowColour.getRed ( ), connectorGlowColour.getGreen ( ), connectorGlowColour.getBlue ( ) );
    }
    
    public UNICODE_GlowingLine getBody ( )
    {
        return wizardConnector;
    }
    
    public void drawLine ( Graphics2D graphics2d, int wizardBodiesIndex )
    {
        //DRAW SHAPE, WHILE CONTROLLING WHICH SHAPE MAY BE RELOCATED @ RUNTIME.
            //establish saved affine transformation
            // establishOldAffineTransform ( graphics2d );
            //translate line
            // if ( wizardShapes.get ( wizardShapesIndex ).getRelocationEnquiry ( ) )
            // {
//                 if ( ( wizardShapesIndex + 1 ) < maxBuis )
//                 {
                    double x1 = wizardBodies.get ( wizardBodiesIndex ).getBody ( ).getX ( );
                    double x2 = wizardBodies.get ( wizardBodiesIndex + 1 ).getBody ( ).getX ( );
                    double y1 = wizardBodies.get ( wizardBodiesIndex ).getBody ( ).getY ( ) + wizardBodies.get ( wizardBodiesIndex ).getBody ( ).getHeight ( ) / 2;
                    double y2 = wizardBodies.get ( wizardBodiesIndex + 1 ).getBody ( ).getY ( ) + wizardBodies.get ( wizardBodiesIndex ).getBody ( ).getHeight ( ) / 2;
                    
                    wizardConnector.setOrientation ( ( int ) x1, ( int ) y1, ( int ) x2, ( int ) y2 );
					

//                 }
            // }
                
            //draw wizard connector
            wizardConnector.draw ( graphics2d );
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //RESET PAINT COMPONENT GRAPHICS2D TRANSFORM, TO PREVENT TRANSLATION of non-selected buttons, or other 
            //objects and components currently in the graphics2d context
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //reset stransformation so that other components are unaffected
            //restoreOldTransform ( graphics2d );   
    }
    
    //re-location after startup related accessors and mutators
        public AffineTransform getOldAffineTransform ( )
        {
            return oldAffineTransformation;
        }
        //establish old affine transform ( used in draw function )
        public void establishOldAffineTransform ( Graphics2D graphics2d )
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
}
