//Author:Jordan Micah Bennett
package data.packages.UNICODE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import java.util.Hashtable;
import java.util.Dictionary;
import java.awt.Dimension;
import data.packages.UNICODE.*;

public class UNICODE_BinaryClock extends JPanel
{               
    //attributes
    private JPanel clockBaseContainer = null;
    
    private JPanel segmentsContainer = new JPanel ( );
    
    //clock element components
    private UNICODE_ClockElement [ ] clockElementHourSegmentsFirstColumn = new UNICODE_ClockElement [ 4 ];
    private UNICODE_ClockElement [ ] clockElementHourSegmentsSecondColumn = new UNICODE_ClockElement [ 4 ];
    private JPanel [ ] segmentContainersA = new JPanel [ 2 ];
    private JPanel segmentContainersAContainer = new JPanel ( );
    
    private UNICODE_ClockElement [ ] clockElementMinuteSegmentsFirstColumn = new UNICODE_ClockElement [ 4 ];
    private UNICODE_ClockElement [ ] clockElementMinuteSegmentsSecondColumn = new UNICODE_ClockElement [ 4 ];
    private JPanel [ ] segmentContainersB = new JPanel [ 2 ];
    private JPanel segmentContainersBContainer = new JPanel ( );
    
    private UNICODE_ClockElement [ ] clockElementSecondSegmentsFirstColumn = new UNICODE_ClockElement [ 4 ];
    private UNICODE_ClockElement [ ] clockElementSecondSegmentsSecondColumn = new UNICODE_ClockElement [ 4 ];
    private JPanel [ ] segmentContainersC = new JPanel [ 2 ];
    private JPanel segmentContainersCContainer = new JPanel ( );
    
    private Color clockElementBaseColour = null, clockBaseContainerColour = null;
    private Color clockElementEnabledColour = null, clockElementDisabledColour = null;
    private UNICODE_DateCreator dateCreator = new UNICODE_DateCreator ( );
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    
    private Timer binaryClockTimer = null;
    
    //consists of a dictionary of digit value keys, versus integer array of usable integers which will be used to enable relevant states as time expires
    private Dictionary <Integer, int [ ]> binaryClockDigitalOutputVersusRequiredIndicesDictionary = null;
    
    //establish alternative method to display time
    private UNICODE_CustomFont customFont = new UNICODE_CustomFont ( "data/fonts/" );
    private String defaultDateString = "";
    private Color defaultDateStringColour = null;
    private String fontName = "";
    private int yLocation = 0;
    
    //establish string to control whether binary mode or default mode may be enabled
    private String binaryModeEnquiryAnswer = "on";
    private int bufferWidth = 0;
    private float fontSize = 0;
	private int clockElementSize = 0;
	private int [ ] decimalValues = new int [ 4 ];
	
	//establish directory display component
	private UNICODE_MessageBoxWindow binaryClockDisplayComponent = null;
	
	//establish file manager
	private Dimension bufferDimension = null;	
	
	//establish visual delay requirements
	public Timer visualDelayTimer = new Timer ( 1, new visualDelayListening ( ) );
	private double visualDelayCount = 0, visualDelayLimit = 0;
	private int yDisplacementHelper = 0; //by how much more do we want our binary clock display component to appear yAxis wise @ startup?
	
    //constructor
    public UNICODE_BinaryClock ( Dimension bufferDimension, double visualDelayLimit, int yDisplacementHelper, Color clockElementBaseColour, Color clockBaseContainerColour, Color clockElementEnabledColour, Color clockElementDisabledColour, int tickDelay, int clockElementSize, Color defaultDateStringColour, String fontName, int bufferWidth, int yLocation, float fontSize, String binaryModeEnquiryAnswer )
    {
        //establish default time string requirments
        this.defaultDateStringColour = defaultDateStringColour;
        this.fontName = fontName;
        this.bufferWidth = bufferWidth;
        this.yLocation = yLocation;
        this.fontSize = fontSize;
        this.binaryModeEnquiryAnswer = binaryModeEnquiryAnswer;
		this.clockElementSize = clockElementSize;
		this.yDisplacementHelper = yDisplacementHelper;

		//establish visual delay requirements
		this.bufferDimension = bufferDimension;
		this.visualDelayLimit = visualDelayLimit;
        
        //setup dictionar
        binaryClockDigitalOutputVersusRequiredIndicesDictionary = getLoadedDictionary ( );
        
        //establish timer
        binaryClockTimer = new Timer ( tickDelay, new binaryClockActionListening ( ) );
        
        //establish clockElementBaseColour
        this.clockElementBaseColour = clockElementBaseColour;
        this.clockBaseContainerColour = clockBaseContainerColour;
        this.clockElementEnabledColour = clockElementEnabledColour;
        this.clockElementDisabledColour = clockElementDisabledColour;
        
        //establish clockElementBase

            
            //establish decimal value list
            decimalValues [ 0 ] = 1;
			decimalValues [ 1 ] = 2;
			decimalValues [ 2 ] = 4;
			decimalValues [ 3 ] = 8;
			
            //generate components
            generateComponents ( );
        

        addMouseListener ( new mouseListening ( ) );
		
		setBackground ( clockElementBaseColour );
    }
	
	public void resetActiveColour ( Color value )
	{
		clockElementEnabledColour = value;
	}
	
	
	public void cleanSurfaces ( )
	{
		//refresh inner-surface objects
		for ( UNICODE_ClockElement element : clockElementHourSegmentsFirstColumn )
			element.removeAll ( );
		for ( UNICODE_ClockElement element : clockElementHourSegmentsSecondColumn )
			element.removeAll ( );
			
		for ( UNICODE_ClockElement element : clockElementMinuteSegmentsFirstColumn )
			element.removeAll ( );
		for ( UNICODE_ClockElement element : clockElementMinuteSegmentsSecondColumn )
			element.removeAll ( );
			
		for ( UNICODE_ClockElement element : clockElementSecondSegmentsFirstColumn )
			element.removeAll ( );
		for ( UNICODE_ClockElement element : clockElementSecondSegmentsSecondColumn )
			element.removeAll ( );	
	
	
		//refresh segment containers
		for ( JPanel panel : segmentContainersA )
			panel.removeAll ( );
		for ( JPanel panel : segmentContainersB )
			panel.removeAll ( );
		for ( JPanel panel : segmentContainersC )
			panel.removeAll ( );
			
		//refresh main container
		segmentsContainer.removeAll ( );
	
		//refresh frame context
		binaryClockDisplayComponent.remove ( segmentsContainer );
	}
	
	public void refreshSurfaces ( )
	{
		//refresh inner-surface objects
		for ( UNICODE_ClockElement element : clockElementHourSegmentsFirstColumn )
			element.repaint ( );
		for ( UNICODE_ClockElement element : clockElementHourSegmentsSecondColumn )
			element.repaint ( );
			
		for ( UNICODE_ClockElement element : clockElementMinuteSegmentsFirstColumn )
			element.repaint ( );
		for ( UNICODE_ClockElement element : clockElementMinuteSegmentsSecondColumn )
			element.repaint ( );
			
		for ( UNICODE_ClockElement element : clockElementSecondSegmentsFirstColumn )
			element.repaint ( );
		for ( UNICODE_ClockElement element : clockElementSecondSegmentsSecondColumn )
			element.repaint ( );	
	
	
		//refresh segment containers
		for ( JPanel panel : segmentContainersA )
			panel.repaint ( );
		for ( JPanel panel : segmentContainersB )
			panel.repaint ( );
		for ( JPanel panel : segmentContainersC )
			panel.repaint ( );
			
		//refresh main container
		segmentsContainer.repaint ( );
	
		//refresh frame context
		binaryClockDisplayComponent.repaint ( );
	}
	
	public void resetColours ( Color value )
	{
		//reset colour
		cleanSurfaces ( );
		resetActiveColour ( value );
        generateComponents ( );
		refreshSurfaces ( );
	}
	
	public void generateComponents ( )
	{
            //subsidiary bases
                //SEGMENT ZERO - HOURS
                    //ROW 0
                    for ( int i = 0; i < 4; i ++ )
                        clockElementHourSegmentsFirstColumn [ i ] = new UNICODE_ClockElement ( getHoursCreationState ( i ), decimalValues [ i ], this.clockElementEnabledColour, this.clockElementDisabledColour, this.clockElementBaseColour, 0, 0, clockElementSize );
                        
                    //ROW 1
                     for ( int i = 0; i < 4; i ++ )
                        clockElementHourSegmentsSecondColumn [ i ] = new UNICODE_ClockElement ( 0, decimalValues [ i ], this.clockElementEnabledColour, this.clockElementDisabledColour, this.clockElementBaseColour, 0, 0, clockElementSize );
                        
                    //add to appropriate segment
                    for ( int i = 0; i < 2; i ++ )
                        segmentContainersA [ i ] = new JPanel ( );
                        
                    for ( int i = 3; i > -1; i -- )
                    {
                        segmentContainersA [ 0 ].add ( clockElementHourSegmentsFirstColumn [ i ] );
                        segmentContainersA [ 1 ].add ( clockElementHourSegmentsSecondColumn [ i ] );
                    }
                    
                    for ( int i = 0; i < 2; i ++ )
                    {
                        segmentContainersA [ i ].setLayout ( new BoxLayout ( segmentContainersA [ i ], BoxLayout.Y_AXIS ) );
                        segmentContainersA [ i ].setBackground ( clockBaseContainerColour );   
                    }
                    
                    for ( int i = 0; i < 2; i ++ )    
                        segmentContainersAContainer.add ( segmentContainersA [ i ] );
                        
                    segmentContainersAContainer.setLayout ( new BoxLayout ( segmentContainersAContainer, BoxLayout.X_AXIS  ) );
                    segmentContainersAContainer.setBackground ( clockBaseContainerColour );    
                    
                    
                //SEGMENT ONE - MINUTES
                    //ROW 0
                    for ( int i = 0; i < 4; i ++ )
                        clockElementMinuteSegmentsFirstColumn [ i ] = new UNICODE_ClockElement ( getOtherCreationState ( i ), decimalValues [ i ], this.clockElementEnabledColour, this.clockElementDisabledColour, this.clockElementBaseColour, 0, 0, clockElementSize );
                        
                    //ROW 1
                     for ( int i = 0; i < 4; i ++ )
                        clockElementMinuteSegmentsSecondColumn [ i ] = new UNICODE_ClockElement ( 0, decimalValues [ i ], this.clockElementEnabledColour, this.clockElementDisabledColour, this.clockElementBaseColour, 0, 0, clockElementSize );
                        
                        
                   //add to appropriate segment
                    for ( int i = 0; i < 2; i ++ )
                        segmentContainersB [ i ] = new JPanel ( );
                        
                    for ( int i = 3; i > -1; i -- )
                    {
                        segmentContainersB [ 0 ].add ( clockElementMinuteSegmentsFirstColumn [ i ] );
                        segmentContainersB [ 1 ].add ( clockElementMinuteSegmentsSecondColumn [ i ] );
                    }
                    
                    for ( int i = 0; i < 2; i ++ )
                    {
                        segmentContainersB [ i ].setLayout ( new BoxLayout ( segmentContainersB [ i ], BoxLayout.Y_AXIS ) );
                        segmentContainersB [ i ].setBackground ( clockBaseContainerColour );   
                    }
                    
                    for ( int i = 0; i < 2; i ++ )    
                        segmentContainersBContainer.add ( segmentContainersB [ i ] );
                        
                    segmentContainersBContainer.setLayout ( new BoxLayout ( segmentContainersBContainer, BoxLayout.X_AXIS  ) );
                    segmentContainersBContainer.setBackground ( clockBaseContainerColour );
                    
                //SEGMENT TWO - SECONDS
                    //ROW 0
                    for ( int i = 0; i < 4; i ++ )
                        clockElementSecondSegmentsFirstColumn [ i ] = new UNICODE_ClockElement ( getOtherCreationState ( i ), decimalValues [ i ], this.clockElementEnabledColour, this.clockElementDisabledColour, this.clockElementBaseColour, 0, 0, clockElementSize );
                        
                    //ROW 1
                    for ( int i = 0; i < 4; i ++ )
                        clockElementSecondSegmentsSecondColumn [ i ] = new UNICODE_ClockElement ( 0, decimalValues [ i ], this.clockElementEnabledColour, this.clockElementDisabledColour, this.clockElementBaseColour, 0, 0, clockElementSize );
                        
                    //add to appropriate segment
                    for ( int i = 0; i < 2; i ++ )
                        segmentContainersC [ i ] = new JPanel ( );
                        
                    for ( int i = 3; i > -1; i -- )
                    {
                        segmentContainersC [ 0 ].add ( clockElementSecondSegmentsFirstColumn [ i ] );
                        segmentContainersC [ 1 ].add ( clockElementSecondSegmentsSecondColumn [ i ] );
                    }
                    
                    for ( int i = 0; i < 2; i ++ )
                    {
                        segmentContainersC [ i ].setLayout ( new BoxLayout ( segmentContainersC [ i ], BoxLayout.Y_AXIS ) );
                        segmentContainersC [ i ].setBackground ( clockBaseContainerColour );   
                    }
                    
                    for ( int i = 0; i < 2; i ++ )    
                        segmentContainersCContainer.add ( segmentContainersC [ i ] );
                        
                    segmentContainersCContainer.setLayout ( new BoxLayout ( segmentContainersCContainer, BoxLayout.X_AXIS  ) );
                    segmentContainersCContainer.setBackground ( clockBaseContainerColour );
                    
            //add subsidiary bases to container
            segmentsContainer.add ( segmentContainersAContainer );
            segmentsContainer.add ( segmentContainersBContainer );
            segmentsContainer.add ( segmentContainersCContainer );
            
            //establish clockBaseContainer layout
            segmentsContainer.setLayout ( new BoxLayout ( segmentsContainer, BoxLayout.X_AXIS ) );
            segmentsContainer.setBackground ( clockBaseContainerColour );
            
        //add clock base container to this panel
        add ( segmentsContainer ); 
            
        //initialise binaryClockDisplayComponent
		segmentsContainer.setBounds ( 0, 0, 64, 42 );
        binaryClockDisplayComponent = new UNICODE_MessageBoxWindow ( true, 0.7f, clockBaseContainerColour, Color.black, Color.black, Color.black, "~", 80, 42, false );
		binaryClockDisplayComponent.setVisible ( false );//hide on startup
		visualDelayTimer.start ( );
		binaryClockTimer.start ( );
		
		//add segments container to binaryClockDisplayComponent
		binaryClockDisplayComponent.add ( segmentsContainer );	
	}
    

    
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        if ( binaryModeEnquiryAnswer.equals ( "on" ) )
        {
            segmentsContainer.setVisible ( true );
        }
        else if ( binaryModeEnquiryAnswer.equals ( "off" ) )
        {
            segmentsContainer.setVisible ( false );
            graphics2d.setColor ( defaultDateStringColour );
            customFont.write ( graphics, defaultDateString, bufferWidth / 2 - ( int ) conveniencePack.getDisplayWidthFromString ( defaultDateString, ( int ) fontSize ) / 2, yLocation, fontSize, fontName );
        }
    }
	public void show ( )
	{
		binaryClockDisplayComponent.setVisible ( true );
		binaryClockDisplayComponent.setAlwaysOnTop ( true );
		//show the binaryClockDisplayComponent
		int binaryClockdisplayComponentSurfaceVaccum = 24; //the panel's surface is greater than the segments being rendered. We need to consider this value to achive true central x coordinate.
		binaryClockDisplayComponent.setBounds ( ( ( ( int ) bufferDimension.getWidth ( ) / 2 ) - binaryClockDisplayComponent.getWidth ( ) / 2 ) + binaryClockdisplayComponentSurfaceVaccum, binaryClockDisplayComponent.getHeight ( ) + yDisplacementHelper, binaryClockDisplayComponent.getWidth ( ), binaryClockDisplayComponent.getHeight ( ) );
		binaryClockDisplayComponent.repaint ( );
	}
	
	//visual delay shit
	private class visualDelayListening implements ActionListener
	{
		public void actionPerformed ( ActionEvent actionEvent )
		{
			if ( visualDelayCount < visualDelayLimit )
				visualDelayCount ++;
			else
			{
				show ( );
				visualDelayTimer.stop ( );
			}
		}
	}
	
    private class mouseListening implements MouseListener
    {
        public void mouseClicked ( MouseEvent mouseEvent )
        {
            toggleModes ( );
        }
        public void mouseDragged ( MouseEvent mouseEvent )
        {
        }
        public void mouseEntered ( MouseEvent mouseEvent )
        {
        }
        public void mouseExited ( MouseEvent mouseEvent )
        {
        }
        public void mouseMoved ( MouseEvent mouseEvent )
        {
        }
        public void mousePressed ( MouseEvent mouseEvent )
        {
        }
        public void mouseReleased ( MouseEvent mouseEvent )
        {
        }
    }
    
    public String getElementsFromIntegerArray ( int [ ] integerArray )
    {
        String returnValue = "";
        
        String delimiter = "";
        
        for ( int i = 0; i < integerArray.length; i ++ )
        {
            if ( i == integerArray.length - 1 )
                delimiter = "";
            else
                delimiter = ",";
                
            returnValue += integerArray [ i ] + delimiter;
        }
            
        return returnValue;
    }
    
    public int getHoursCreationState ( int iterationIndex )
    {
        int returnValue = 0;
        
        switch ( iterationIndex )
        {
            case 2: returnValue = -1; break;
            case 3: returnValue = -1; break;
            default: returnValue = 0; break;
        }
        
        return returnValue;
    }
    
    public int getOtherCreationState ( int iterationIndex )
    {
        int returnValue = 0;
        
        switch ( iterationIndex )
        {
            case 3: returnValue = -1; break;
            default: returnValue = 0; break;
        }
        
        return returnValue;
    }
    
    public String getBinaryModeEnquiryAnswer ( )
    {
        return binaryModeEnquiryAnswer;
    }
    
    public void setBinaryModeEnquiryAnswer ( String value )
    {
        binaryModeEnquiryAnswer = value;
    }
    
    public void toggleModes ( )
    {
        if ( getBinaryModeEnquiryAnswer ( ).equals ( "on" ) )
            setBinaryModeEnquiryAnswer ( "off" );

        else if ( getBinaryModeEnquiryAnswer ( ).equals ( "off" ) )
            setBinaryModeEnquiryAnswer ( "on" );
    }
    
    
    public Dictionary <Integer, int [ ]> getLoadedDictionary ( )
    {
        Dictionary <Integer, int [ ]> returnValue = new Hashtable <Integer, int [ ]> ( );
        
        returnValue.put ( 0, new int [ ] { } );
        returnValue.put ( 1, new int [ ] { 0 } );
        returnValue.put ( 2, new int [ ] { 1 } );
        returnValue.put ( 3, new int [ ] { 0, 1 } );
        returnValue.put ( 4, new int [ ] { 2 } );
        returnValue.put ( 5, new int [ ] { 0, 2 } );
        returnValue.put ( 6, new int [ ] { 1, 2 } );
        returnValue.put ( 7, new int [ ] { 0, 1, 2 } );
        returnValue.put ( 8, new int [ ] { 3 } );
        returnValue.put ( 9, new int [ ] { 0, 3 } );
        
        return returnValue;
    }
    
    //conveniencePack
    public void refreshColumn ( UNICODE_ClockElement [ ] clockElementCollection )
    {
        for ( int i = 0; i < clockElementCollection.length; i ++ )
            if ( clockElementCollection [ i ].getState ( ) != -1 )
                clockElementCollection [ i ].setState ( 0 );
    }
    
    public String evaluateColumn ( int digitValue, UNICODE_ClockElement [ ] clockElementCollection )
    {
        String returnValue = "";
        
        if ( digitValue != 0 )
        {
            int [ ] targetIndices = binaryClockDigitalOutputVersusRequiredIndicesDictionary.get ( digitValue );
    
            int targetIndicesTraversor = 0;
            int cycleIndex = 0;
            boolean cycleIndexEquivalenceEnquiry = ( cycleIndex == targetIndices [ targetIndicesTraversor ] );
            int cycleIndexStep = 0;
            
            //while the entire length of the collection has not yet been evaluated
            while ( cycleIndex < clockElementCollection.length )
            {
                if ( !cycleIndexEquivalenceEnquiry )
                {
                    //re-evaluate cycleIndexEquivalenceEnquiry, however increment cycleIndex by 1 to proceed cycling through clockElementCollection length
                        //first determine how the cycleIndex will be incremented
                        switch ( digitValue )
                        {
                            case 6: cycleIndexStep = cycleIndex + 1; break;
                            case 7: cycleIndexStep = cycleIndex + 1; break;
                            default: cycleIndexStep = cycleIndex += 1; break;
                        }
                        //then peform the actual re-evaluation.
                        //re-evaluation is another way of syaing I"m detemerming whether a match has been found, based on updated cycle index.
                        cycleIndexEquivalenceEnquiry = ( ( cycleIndexStep ) == targetIndices [ targetIndicesTraversor ] );
                    
                    if ( cycleIndexEquivalenceEnquiry )
                    {
                        clockElementCollection [ targetIndices [ targetIndicesTraversor ] ].setState ( 1 );//set active wrt targetIndicesTraversor
                           
                        if ( targetIndicesTraversor == targetIndices.length - 1 ) //is the last targetIndex yet reached?
                            cycleIndex = clockElementCollection.length; //if so end loop.
                        else
                        {
                            cycleIndex ++;
                            targetIndicesTraversor ++; //update targetIndicesTraversor to evaluate next targetIndex under next cycle
                            cycleIndexEquivalenceEnquiry = false; //reset cycleIndexEquivalenceEnquiry, so as to allow re-evaluation wrt to updated cycleIndex.
                        }
                    }
                }
                else if ( cycleIndexEquivalenceEnquiry )
                {
                    clockElementCollection [ targetIndices [ targetIndicesTraversor ] ].setState ( 1 );//set active wrt targetIndicesTraversor
                    
                    if ( targetIndicesTraversor == targetIndices.length - 1 ) //is the last targetIndex yet reached?
                        cycleIndex = clockElementCollection.length; //if so end loop.
                    else
                    {
                        targetIndicesTraversor ++; //update targetIndicesTraversor to evaluate next targetIndex under next cycle
                        cycleIndexEquivalenceEnquiry = false;
                    }
                }
            }
        }
        
        return returnValue;
    }
    
    private class binaryClockActionListening implements ActionListener
    {
        public void actionPerformed ( ActionEvent actionEvent )
        {
            if ( binaryModeEnquiryAnswer.equals ( "off" ) )
            //update default date string
                defaultDateString = new UNICODE_DateCreator ( ).getHoursDigits ( ) [ 0 ] + new UNICODE_DateCreator ( ).getHoursDigits ( ) [ 1 ] + ":" + new UNICODE_DateCreator ( ).getMinutesDigits ( ) [ 0 ] + new UNICODE_DateCreator ( ).getMinutesDigits ( ) [ 1 ] + ":" +new UNICODE_DateCreator ( ).getSecondsDigits ( ) [ 0 ] + new UNICODE_DateCreator ( ).getSecondsDigits ( ) [ 1 ];
            else if ( binaryModeEnquiryAnswer.equals ( "on" ) )
            {
            //refresh columns
                //refresh hours segment 
                    //refresh first column
                    refreshColumn ( clockElementHourSegmentsFirstColumn );
                    //refresh second column
                    refreshColumn ( clockElementHourSegmentsSecondColumn );
                //refresh minutes segment 
                    //refresh first column
                    refreshColumn ( clockElementMinuteSegmentsFirstColumn );
                    //refresh second column
                    refreshColumn ( clockElementMinuteSegmentsSecondColumn );
                //refresh seconds segment 
                    //refresh first column
                    refreshColumn ( clockElementSecondSegmentsFirstColumn );
                    //refresh second column
                    refreshColumn ( clockElementSecondSegmentsSecondColumn );      
            
            //evaluate all columns wrt to java date/time via system time.
                //evaluate hours segment 
                    //evaluate first column
                    evaluateColumn ( new UNICODE_DateCreator ( ).getHoursDigits ( ) [ 0 ], clockElementHourSegmentsFirstColumn );
                    //evaluate second column
                    evaluateColumn ( new UNICODE_DateCreator ( ).getHoursDigits ( ) [ 1 ], clockElementHourSegmentsSecondColumn );
                //evaluate minutes segment 
                    //evaluate first column
                    evaluateColumn ( new UNICODE_DateCreator ( ).getMinutesDigits ( ) [ 0 ], clockElementMinuteSegmentsFirstColumn );
                    //evaluate second column
                    evaluateColumn ( new UNICODE_DateCreator ( ).getMinutesDigits ( ) [ 1 ], clockElementMinuteSegmentsSecondColumn );
                //evaluate seconds segment 
                    //evaluate first column
                    evaluateColumn ( new UNICODE_DateCreator ( ).getSecondsDigits ( ) [ 0 ], clockElementSecondSegmentsFirstColumn );
                    //evaluate second column
                    evaluateColumn ( new UNICODE_DateCreator ( ).getSecondsDigits ( ) [ 1 ], clockElementSecondSegmentsSecondColumn );
            }
			binaryClockDisplayComponent.repaint ( );
            repaint ( );
        }
    }
}