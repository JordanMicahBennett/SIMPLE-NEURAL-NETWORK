package data.packages.UNICODE;
//package data.packages.UNICODE;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import javax.swing.Timer;
import java.awt.Point;


public class UNICODE_WIZARD_FIELD extends JPanel 
{
    //attributes
        //establish convenience pack
        private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
        //Wizard REPOSITIONING VARS
            //establish current coordinates genrated by pressed down mouse
            private Point heldMouseCoords = null;
            //establish current coordinates geenrated by mouse dragging
            private Point draggedMouseCoords = null;
    
        //image vars
        private ArrayList <UNICODE_WIZARD_CONNECTOR> wizardConnectors = new ArrayList <UNICODE_WIZARD_CONNECTOR> ( );
        private ArrayList <UNICODE_WIZARD_BODY> wizards = new ArrayList <UNICODE_WIZARD_BODY> ( );
        private ArrayList <UNICODE_WIZARD_DESCRIPTOR> wizardDescriptors = new ArrayList <UNICODE_WIZARD_DESCRIPTOR> ( );
		//establsih wizard descriptor bodies (may be different message box windows)
		private ArrayList <UNICODE_MessageBoxWindow> wizardDescriptorBodies = null;
	
        //dimension
        private int wizardWidth, wizardHeight, spatialMultiplier;
        
        //cardinality
        private int maxWizards = 10;
        
        //randomizer
        private Random randomizer = new Random ( );
        
        //coordinate gen vars
            //error solving
            private int minimumNodeFieldCardinalityBeforeChopping = 0;
       
            //lists
                //x
                private ArrayList <Integer> xCoordList = new ArrayList <Integer> ( );
				private ArrayList <Integer> locomotionBounds = new ArrayList <Integer> ( );
                private int xCoordListIncrementor = 0;
                //y
                private int yCoord = 0;

        //scroll vars
            //timer
            //private Timer scrollRightwardsTimer = new Timer ( 1, new scrollRightwardsListener ( ) ), scrollLeftwardsTimer = new Timer ( 1, new scrollLeftwardsListener ( ) );
            //scroll value vars
                //rate
                private double scrollRate;
                //adjuster
                private int scrollRightwardsAdjuster, scrollLeftwardsAdjuster;
                //mouse wheel int controller
                private int rotationDirection;
                
        
        //establish line visibility controll boolean
        private boolean lineVisibilityEnquiry = true;
        
            
        //Wizards description requirements
        private Color wizardDescriptorBodyFolderColour = null, wizardDescriptorBodyFileColour = null, wizardDescriptionColour = null;
        private boolean descriptionModeEnquiry = false;
        
        //establish description view controller
        private UNICODE_DescriptionViewController descriptionViewController = null;
        
        //colour scheme variables
            //all
            private Color connectorColour = null, connectorGlowColour = null, wizardNodeColour = null;
            
        
        //opacity level of wizard descriptors
        private float descriptorOpacityLevel = 0.0f;
        
    //connector glow properties
    private int connectorThickness = 0, connectorGlowThickness = 0, connectorGlowQualityMultiplier = 0, connectorGlowBrightness = 0;
    

    
    //indicates whether a field has been generated
    private boolean fieldGenerationEnquiry = false;
    
    //timers
    private Timer wizardDescriptorRightwardLocomotionTimer = new Timer ( 1, new wizardDescriptorRightwardLocomotionListener ( ) );
    private Timer wizardDescriptorLeftwardLocomotionTimer = new Timer ( 1, new wizardDescriptorLeftwardLocomotionListener ( ) );
    
    private int targetScrollableDescriptorIndex = 0, targetScrollableDescriptorScrollRate = 0;
	
	//screen dimension
	private Dimension screenDimension  = null;
	
    //constructor
    public UNICODE_WIZARD_FIELD ( int _wizardWidth, int _wizardHeight, int _spatialMultiplier, double _scrollRate, Dimension _screenDimension, UNICODE_DescriptionViewController _descriptionViewController, Color _connectorColour, Color _connectorGlowColour, Color _wizardNodeColour, float _descriptorOpacityLevel, int _connectorThickness, int _connectorGlowThickness, int _connectorGlowQualityMultiplier, int _connectorGlowBrightness, int _maxWizards, ArrayList <UNICODE_MessageBoxWindow> _wizardDescriptorBodies )
    {
        
        //establsih wizard descriptor bodies (may be different message box windows)
        wizardDescriptorBodies = _wizardDescriptorBodies;
    
        //establish colour scheme vars
            //all
            connectorColour = _connectorColour;
            connectorGlowColour = _connectorGlowColour;
            wizardNodeColour = _wizardNodeColour;
            //establish glow properties
            connectorThickness = _connectorThickness; 
            connectorGlowThickness = _connectorGlowThickness;
            connectorGlowQualityMultiplier = _connectorGlowQualityMultiplier;
            connectorGlowBrightness = _connectorGlowBrightness;
        
        //opacity level of wizard descriptors
        descriptorOpacityLevel = _descriptorOpacityLevel;
        
		//establish screen dimension
		screenDimension = _screenDimension;
		
		//establish locomotion bounds.
		locomotionBounds = getComputedLocomotionBounds ( screenDimension, wizardDescriptorBodies );
		
        //compute minimumNodeFieldCardinalityBeforeChopping
        minimumNodeFieldCardinalityBeforeChopping = ( ( int ) screenDimension.getWidth ( ) / 100 ) + 2;
        
        //establish description view controller
        descriptionViewController = _descriptionViewController;

        //establish wizard dimension
        wizardWidth = _wizardWidth;
        wizardHeight = _wizardHeight;
        
        //establish spatial multiplier ( will directly affect the proximity latency between wizards ) 
        spatialMultiplier = _spatialMultiplier;
        
        //scroll rate
        scrollRate = _scrollRate;
        
        //estabish max wizards
        maxWizards = _maxWizards;
        
        //set focus to this panel
        setFocusable ( true );
    }
    

        
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
       
        generateFieldComponents ( graphics2d );
    }
    
    //get panel midline
    public int getPanelMidpointY ( )
    {
        return this.getHeight ( ) / 2 - 100;
    }
    
    //get wizard list
    public ArrayList <UNICODE_WIZARD_BODY> getWizardList ( )
    {
        return wizards;
    }
    
    

    public ArrayList <UNICODE_WIZARD_DESCRIPTOR> getDescriptorList ( )
    {
        return wizardDescriptors;
    }
    
    public void setDescriptionModeEnquiry ( boolean value )
    {
        descriptionModeEnquiry = value;
    }
    
    public boolean getFieldGenerationEnquiry ( )
    {
        return fieldGenerationEnquiry;
    }
    
    public void setFieldGenerationEnquiry ( boolean value )
    {
        fieldGenerationEnquiry = value;
    }
    
    public void generateFieldComponents ( Graphics2D graphics2d )
    {
        //reset bounds according to how many nodes where found, with respect to the spatial order in which they were born.
        if ( maxWizards < minimumNodeFieldCardinalityBeforeChopping )
            setBounds ( getX ( ), getY ( ), getWidth ( ) + minimumNodeFieldCardinalityBeforeChopping, getHeight ( ) ); //AN APPROPRIATE FORMULAE FOR EXTENDING THE FRAME'S WIDTH, WITH RESPECT TO THE NEW NODES FOUND.
        else
            setBounds ( getX ( ), getY ( ), ( maxWizards * ( wizardWidth * maxWizards ) ), getHeight ( ) ); //AN APPROPRIATE FORMULAE FOR EXTENDING THE FRAME'S WIDTH, WITH RESPECT TO THE NEW NODES FOUND.
        
            
        //compute y coorrd
        yCoord = getPanelMidpointY ( );
            
        //generat x coords
        for ( int i = 0; i < maxWizards; i ++ )
        {
            if ( i == 0 )
               xCoordListIncrementor += ( this.getWidth ( ) / 2 );
            else
               xCoordListIncrementor += spatialMultiplier;
            
            
            xCoordList.add ( xCoordListIncrementor );
        }
        
        //generate wizard list
        for ( int i = 0; i < maxWizards; i ++ )
            wizards.add ( new UNICODE_WIZARD_BODY ( xCoordList.get ( i ), yCoord, wizardWidth, wizardHeight, wizardNodeColour ) );
        
        //generate wizard connectors
        for ( int i = 0; i < maxWizards; i ++ )
            if ( ( i + 1 ) < maxWizards )
                wizardConnectors.add ( new UNICODE_WIZARD_CONNECTOR ( xCoordList.get ( i ), yCoord, xCoordList.get ( i + 1 ), yCoord, wizards, "on", connectorColour, connectorGlowColour, connectorThickness, connectorGlowThickness, connectorGlowQualityMultiplier, connectorGlowBrightness ) );

    }
    
    public void generateWizardDescriptors ( )
    {
        for ( int i = 0; i < maxWizards; i ++ )
            wizardDescriptors.add ( new UNICODE_WIZARD_DESCRIPTOR ( wizards.get ( i ), 400, 200, getWizardDescriptorBodies ( ).get ( i ) ) );
    }
    
    public void shrink ( JPanel destinationPanel )
    {
        for ( int i = 0; i < maxWizards; i ++ )
        {
            wizardDescriptorBodies.get ( i ).setBounds ( 10, 10, 100, 100 );
            wizardDescriptorBodies.get ( i ).repaint ( );
        }
        destinationPanel.repaint ( );
    }
    
    
    public ArrayList <UNICODE_WIZARD_DESCRIPTOR> getWizardDescriptors ( )
    {
        return wizardDescriptors;
    }
    
    public ArrayList <UNICODE_MessageBoxWindow> getWizardDescriptorBodies ( )
    {
        return wizardDescriptorBodies;
    }
	
	public ArrayList <Integer> getComputedLocomotionBounds ( Dimension _screenDimension, ArrayList <UNICODE_MessageBoxWindow> _wizardDescriptorBodies )
	{
		ArrayList <Integer> value = new ArrayList <Integer> ( );		

		//establish screen width.
		int screenWidth = ( int ) _screenDimension.getWidth ( );
		
		for ( int i = 0; i < _wizardDescriptorBodies.size ( ); i ++ )
		{
			//establish descriptor width
			int eachBodyWidth = _wizardDescriptorBodies.get ( i ).getWidth ( );
			int xBound = ( ( screenWidth / 2 ) - ( eachBodyWidth / 2 ) );
			value.add ( xBound );
		}
		
		return value;
	}
    
    
    public ArrayList <Integer> getCoordListX ( )
    {
        return xCoordList;
    }

 
        
        public void triggerScrolling ( int _targetIndex, int _rate, String direction )
		{ 
			//specify the wizard field's tartget index
			targetScrollableDescriptorIndex = _targetIndex;
			targetScrollableDescriptorScrollRate = _rate;
			
            if ( direction.equals ( "rightwards" ) )
            {
                wizardDescriptorLeftwardLocomotionTimer.stop ( );
                wizardDescriptorRightwardLocomotionTimer.start ( );
            }
            else if ( direction.equals ( "leftwards" ) )
            {
                wizardDescriptorRightwardLocomotionTimer.stop ( );
                wizardDescriptorLeftwardLocomotionTimer.start ( );
            }
        }
        
        
        
        public void scrollDescriptorCollectionRightwards ( )
        {
            for ( int i = 0; i < maxWizards; i ++ )
			{
				if ( wizardDescriptors.get ( targetScrollableDescriptorIndex ).getBody ( ).getX ( ) >= ( locomotionBounds.get ( targetScrollableDescriptorIndex ) + 80 ) ) //if that body hasn't yet been reached
					wizardDescriptors.get ( i ).move ( "leftwards", targetScrollableDescriptorScrollRate ); //scroll all bodies rightwards
			}
        }
        
        public void scrollDescriptorCollectionLeftwards ( )
        {
            for ( int i = 0; i < maxWizards; i ++ )
			{
				if ( wizardDescriptors.get ( targetScrollableDescriptorIndex ).getBody ( ).getX ( ) <= ( locomotionBounds.get ( targetScrollableDescriptorIndex ) + 0 ) ) //if that body hasn't yet been reached
					wizardDescriptors.get ( i ).move ( "rightwards", targetScrollableDescriptorScrollRate ); //scroll all bodies rightwards
			}
        }
                
        
    private class wizardDescriptorRightwardLocomotionListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent actionEvent ) 
        {
            scrollDescriptorCollectionRightwards ( );
        }
    }
    
    private class wizardDescriptorLeftwardLocomotionListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent actionEvent ) 
        {
            scrollDescriptorCollectionLeftwards ( );
        }
    }
}
