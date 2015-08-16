package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Dictionary;
import javax.swing.Timer;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import data.packages.UNICODE.*;

public class UNICODE_LunosClock extends JPanel
{
    //attributes
        //clock element requirements
            //clock element structures
            private ArrayList <UNICODE_LunosClockElementStructure> clockElementHourStructuresDigit0 = new ArrayList <UNICODE_LunosClockElementStructure> ( ), clockElementHourStructuresDigit1 = new ArrayList <UNICODE_LunosClockElementStructure> ( );
            private ArrayList <UNICODE_LunosClockElementStructure> clockElementMinuteStructuresDigit0 = new ArrayList <UNICODE_LunosClockElementStructure> ( ), clockElementMinuteStructuresDigit1 = new ArrayList <UNICODE_LunosClockElementStructure> ( );
            private ArrayList <UNICODE_LunosClockElementStructure> clockElementSecondStructuresDigit0 = new ArrayList <UNICODE_LunosClockElementStructure> ( ), clockElementSecondStructuresDigit1 = new ArrayList <UNICODE_LunosClockElementStructure> ( );
            
            //decimal values of elements
            private int [ ] decimalValueList = { 1, 2, 4, 8 };
    
    private ArrayList <UNICODE_LunosClockElementStructure> segmentIndicatorStructures = new ArrayList <UNICODE_LunosClockElementStructure> ( );
    
    private Timer lunosClockInternalTimer = null, lunosClockIndicatorTimer = null;
    private int lunosClockInternalDelay = 0, lunosClockIndicatorDelay = 0;
    private int segmentIndicatorDisplayIndex = -1, minimumSegmentIndicatorDisplayIndex = -1, maximumSegmentIndicatorDisplayIndex =2;
    
    //consists of a dictionary of digit value keys, versus integer array of usable integers which will be used to enable relevant states as time expires
    private Dictionary <Integer, int [ ]> lunosClockDigitalOutputVersusRequiredIndicesDictionary = null;
	
	//establish visual delay requirements
	public Timer visualDelayTimer = new Timer ( 1, new visualDelayListening ( ) );
	private double visualDelayCount = 0, visualDelayLimit = 0;
    
    
    //lineThickness
    private Stroke lineThickness = null, defaultLineThickness = null;
	private JFrame lunosClockFrame = null;
    
    //constructor
    public UNICODE_LunosClock ( int lunosClockIndicatorDelay, int segmentPanelWidth, int segmentPanelHeight, int mostSignifigantElementSpan, Color enabledColour, Color disabledColour, Color ignoreColour, Color outlineColour, Color enabledColourIndicator, Color disabledColourIndicator, Color ignoreColourindicator, Color outlineColourIndicator, Color backgroundColour, int xExternalDisplacement, int yExternalDisplacement, int xExternalDisplacement2, int yExternalDisplacement2, int visualDelayLimit, JFrame lunosClockFrame )
    {
		//establish frame
		this.lunosClockFrame = lunosClockFrame;
		lunosClockFrame.setVisible ( false );
		
        setBackground ( backgroundColour );   
        
        //setup clock indicator delay
        this.lunosClockIndicatorDelay = lunosClockIndicatorDelay;
        
        //setup line thickness
        lineThickness = new BasicStroke ( 4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float [ ] { 3, 1 }, 0 );
        defaultLineThickness = new BasicStroke ( 1 );

        //generate segement digit 0
        generateSegmentDigit ( this.clockElementHourStructuresDigit0, segmentPanelWidth, segmentPanelHeight, mostSignifigantElementSpan, mostSignifigantElementSpan/8, xExternalDisplacement, yExternalDisplacement, 30, 40, 10, enabledColour, disabledColour, ignoreColour, outlineColour ); 
        generateSegmentDigit ( this.clockElementHourStructuresDigit1, segmentPanelWidth/2, segmentPanelHeight/2, mostSignifigantElementSpan/2, 8, 240 + xExternalDisplacement2, segmentPanelHeight/3 + yExternalDisplacement2, 20, 30, 10, enabledColour, disabledColour, ignoreColour, outlineColour );     
        
        generateSegmentDigit ( this.clockElementMinuteStructuresDigit0, segmentPanelWidth, segmentPanelHeight, mostSignifigantElementSpan, mostSignifigantElementSpan/8, xExternalDisplacement, yExternalDisplacement, 30, 40, 10, enabledColour, disabledColour, ignoreColour, outlineColour ); 
        generateSegmentDigit ( this.clockElementMinuteStructuresDigit1, segmentPanelWidth/2, segmentPanelHeight/2, mostSignifigantElementSpan/2, 8, 240 + xExternalDisplacement2, segmentPanelHeight/3 + yExternalDisplacement2, 20, 30, 10, enabledColour, disabledColour, ignoreColour, outlineColour );    
        
        generateSegmentDigit ( this.clockElementSecondStructuresDigit0, segmentPanelWidth, segmentPanelHeight, mostSignifigantElementSpan, mostSignifigantElementSpan/8, xExternalDisplacement, yExternalDisplacement, 30, 40, 10, enabledColour, disabledColour, ignoreColour, outlineColour ); 
        generateSegmentDigit ( this.clockElementSecondStructuresDigit1, segmentPanelWidth/2, segmentPanelHeight/2, mostSignifigantElementSpan/2, 8, 240 + xExternalDisplacement2, segmentPanelHeight/3 + yExternalDisplacement2, 20, 30, 10, enabledColour, disabledColour, ignoreColour, outlineColour );    

        //generate segment indicators
            int segementIndicatorSpan = mostSignifigantElementSpan / 8, yDisplacementIndicatorSegment = 0, yDisplacementRateIndicatorSegment = 20;
    
            for ( int i = 0; i < 3; i ++ )
            {
                segmentIndicatorStructures.add ( new UNICODE_LunosClockElementStructure ( 0, 8, enabledColourIndicator, disabledColourIndicator, ignoreColourindicator, outlineColourIndicator, ( int ) ( ( clockElementHourStructuresDigit0.get ( clockElementHourStructuresDigit0.size ( ) - 1 ).getBody ( ).getX ( ) + clockElementHourStructuresDigit0.get ( clockElementHourStructuresDigit0.size ( ) - 1 ).getBody ( ).getWidth ( ) ) + segementIndicatorSpan ), ( int ) ( ( clockElementHourStructuresDigit0.get ( clockElementHourStructuresDigit0.size ( ) - 1 ).getBody ( ).getY ( ) + ( clockElementHourStructuresDigit0.get ( clockElementHourStructuresDigit0.size ( ) - 1 ).getBody ( ).getHeight ( ) / 2 + 20 ) ) - yDisplacementIndicatorSegment ), segementIndicatorSpan ) );
                
                yDisplacementIndicatorSegment += yDisplacementRateIndicatorSegment;
            }
        
        //setup dictionar
        lunosClockDigitalOutputVersusRequiredIndicesDictionary = getLoadedDictionary ( );
        
        //establish timer
        lunosClockInternalTimer = new Timer ( lunosClockInternalDelay, new lunosClockInternalActionListener ( ) ); 
        lunosClockIndicatorTimer = new Timer ( lunosClockIndicatorDelay, new lunosClockIndicatorActionListener ( ) );
        
        //start the timer!
        lunosClockInternalTimer.start ( );
        lunosClockIndicatorTimer.start ( );
        
        //establish visual display components
		this.visualDelayLimit = visualDelayLimit;
		visualDelayTimer.start ( );
		
		//add mouse listener
        addMouseListener ( new mouseListenening ( ) );
    }
    
    //methods
    public void generateSegmentDigit ( ArrayList <UNICODE_LunosClockElementStructure> clockElementHourStructuresDigit0, int segmentPanelWidth, int segmentPanelHeight, int mostSignifigantElementSpan, int leastSignifigantElementSpan, int xExternalDisplacement, int yExternalDisplacement, int displacementRate0, int displacementRate1, int spanJump, Color enabledColour, Color disabledColour, Color ignoreColour, Color outlineColour )
    {
        //generate most significant clock element
        UNICODE_LunosClockElementStructure mostSignificantElement = new UNICODE_LunosClockElementStructure ( 0, decimalValueList [ 3 ], enabledColour, disabledColour, ignoreColour, outlineColour, ( ( int ) ( segmentPanelWidth / 2 - mostSignifigantElementSpan / 2 ) + xExternalDisplacement ),  ( int ) ( ( segmentPanelHeight / 2 - mostSignifigantElementSpan / 2 ) + yExternalDisplacement ), mostSignifigantElementSpan );
               
        //generate segment components
        int leastSignificantElementSpan = leastSignifigantElementSpan, yDisplacementLeastSignificant = 0, yDisplacementRateLeastSignificant = 0;
        for ( int i = 0; i < 3; i ++ )
        {
            //control spacing between leftmost elements
            switch ( i )
            {
                case 0 : yDisplacementRateLeastSignificant = displacementRate0; break;
                default: yDisplacementRateLeastSignificant = displacementRate1; break;
            }    
            
            clockElementHourStructuresDigit0.add ( new UNICODE_LunosClockElementStructure ( 0, decimalValueList [ i ], enabledColour, disabledColour, ignoreColour, outlineColour,  ( int ) ( mostSignificantElement.getBody ( ).getX ( ) - leastSignificantElementSpan / 2 ), ( int ) ( ( mostSignificantElement.getBody ( ).getY ( ) + ( mostSignificantElement.getBody ( ).getHeight ( ) / 2 + 30 ) ) - yDisplacementLeastSignificant ), leastSignificantElementSpan ) );
            
            leastSignificantElementSpan += spanJump;
            yDisplacementLeastSignificant += yDisplacementRateLeastSignificant;
        }
        
        //add most significant clock element to list
        clockElementHourStructuresDigit0.add ( mostSignificantElement );
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
    public void refreshColumn ( ArrayList <UNICODE_LunosClockElementStructure> clockElementStructureCollection )
    {
        for ( int i = 0; i < clockElementStructureCollection.size ( ); i ++ )
            if ( clockElementStructureCollection.get ( i ).getState ( ) != -1 )
                clockElementStructureCollection.get ( i ).setState ( 0 );
    }
    
    
    public void evaluateColumn ( int digitValue, ArrayList <UNICODE_LunosClockElementStructure> clockElementStructureCollection )
    {
        if ( digitValue != 0 )
        {
            int [ ] targetIndices = lunosClockDigitalOutputVersusRequiredIndicesDictionary.get ( digitValue );
    
            int targetIndicesTraversor = 0;
            int cycleIndex = 0;
            boolean cycleIndexEquivalenceEnquiry = ( cycleIndex == targetIndices [ targetIndicesTraversor ] );
            int cycleIndexStep = 0;
            
            //while the entire length of the collection has not yet been evaluated
            while ( cycleIndex < clockElementStructureCollection.size ( ) )
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
                        //then perform the actual re-evaluation.
                        //re-evaluation is another way of saying I"m detemerming whether a match has been found, based on updated cycle index.
                        cycleIndexEquivalenceEnquiry = ( ( cycleIndexStep ) == targetIndices [ targetIndicesTraversor ] );
                    
                    if ( cycleIndexEquivalenceEnquiry )
                    {
                        clockElementStructureCollection.get ( targetIndices [ targetIndicesTraversor ] ).setState ( 1 );//set active wrt targetIndicesTraversor
                           
                        if ( targetIndicesTraversor == targetIndices.length - 1 ) //is the last targetIndex yet reached?
                            cycleIndex = clockElementStructureCollection.size ( ); //if so end loop.
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
                    clockElementStructureCollection.get ( targetIndices [ targetIndicesTraversor ] ).setState ( 1 );//set active wrt targetIndicesTraversor
                    
                    if ( targetIndicesTraversor == targetIndices.length - 1 ) //is the last targetIndex yet reached?
                        cycleIndex = clockElementStructureCollection.size ( ); //if so end loop.
                    else
                    {
                        targetIndicesTraversor ++; //update targetIndicesTraversor to evaluate next targetIndex under next cycle
                        cycleIndexEquivalenceEnquiry = false;
                    }
                }
            }
        }
    }
	
    
    //paint component
    public void paintComponent ( Graphics graphics ) 
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        graphics2d.setStroke ( lineThickness );
        
        switch ( segmentIndicatorDisplayIndex )
        {
            case 2: 
            {
                refreshColumn ( segmentIndicatorStructures );
                segmentIndicatorStructures.get ( 2 ).setState ( 1 );
                drawSegment ( graphics2d, clockElementHourStructuresDigit0, clockElementHourStructuresDigit1 ); 
            }
            break;
            case 1: 
            {
                refreshColumn ( segmentIndicatorStructures );
                segmentIndicatorStructures.get ( 1 ).setState ( 1 );
                drawSegment ( graphics2d, clockElementMinuteStructuresDigit0, clockElementMinuteStructuresDigit1 ); 
            }
            break;
            case 0: 
            {
                refreshColumn ( segmentIndicatorStructures );
                segmentIndicatorStructures.get ( 0 ).setState ( 1 );
                drawSegment ( graphics2d, clockElementSecondStructuresDigit0, clockElementSecondStructuresDigit1 ); 
            }
            break;
            default:
            {
                refreshColumn ( segmentIndicatorStructures );
                segmentIndicatorStructures.get ( 2 ).setState ( 1 );
                drawSegment ( graphics2d, clockElementHourStructuresDigit0, clockElementHourStructuresDigit1 ); 
            }
            break;
        }
                
        //dtaw segment indicators    
        for ( int i = 0; i < segmentIndicatorStructures.size ( ); i ++ )
            segmentIndicatorStructures.get ( i ).draw ( graphics2d );
            
        //reset stroke
        graphics2d.setStroke ( defaultLineThickness );
    }
    
    public void drawSegment ( Graphics2D graphics2d, ArrayList <UNICODE_LunosClockElementStructure> clockElementStructuresDigit0, ArrayList <UNICODE_LunosClockElementStructure> clockElementStructuresDigit1 )
    {
        //digit 0
            //draw the most significant clock element
            clockElementStructuresDigit0.get ( clockElementStructuresDigit0.size ( ) - 1 ).draw ( graphics2d );
            //draw all clock elements
            for ( int i = 0; i < clockElementStructuresDigit0.size ( ) - 1; i ++ )
                clockElementStructuresDigit0.get ( i ).draw ( graphics2d );
        //digit 1
            //draw the most significant clock element
            clockElementStructuresDigit1.get ( clockElementStructuresDigit1.size ( ) - 1 ).draw ( graphics2d );
            //draw all clock elements
            for ( int i = 0; i < clockElementStructuresDigit1.size ( ) - 1; i ++ )
                clockElementStructuresDigit1.get ( i ).draw ( graphics2d );
    }
    
    
    private class lunosClockInternalActionListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent actionEvent )
        {
            refreshColumn ( clockElementHourStructuresDigit0 );
            refreshColumn ( clockElementHourStructuresDigit1 );
            refreshColumn ( clockElementMinuteStructuresDigit0 );
            refreshColumn ( clockElementMinuteStructuresDigit1 );    
            refreshColumn ( clockElementSecondStructuresDigit0 );
            refreshColumn ( clockElementSecondStructuresDigit1 );
            
            evaluateColumn ( new UNICODE_DateCreator ( ).getHoursDigits ( ) [ 0 ], clockElementHourStructuresDigit0 );
            evaluateColumn ( new UNICODE_DateCreator ( ).getHoursDigits ( ) [ 1 ], clockElementHourStructuresDigit1 );
            evaluateColumn ( new UNICODE_DateCreator ( ).getMinutesDigits ( ) [ 0 ], clockElementMinuteStructuresDigit0 );
            evaluateColumn ( new UNICODE_DateCreator ( ).getMinutesDigits ( ) [ 1 ], clockElementMinuteStructuresDigit1 );
            evaluateColumn ( new UNICODE_DateCreator ( ).getSecondsDigits ( ) [ 0 ], clockElementSecondStructuresDigit0 );
            evaluateColumn ( new UNICODE_DateCreator ( ).getSecondsDigits ( ) [ 1 ], clockElementSecondStructuresDigit1 );
            
            repaint ( );
        }
    }
    
    public void toggleSegmentIndicatorDisplayIndex ( )
    {
        if ( segmentIndicatorDisplayIndex > minimumSegmentIndicatorDisplayIndex )
            segmentIndicatorDisplayIndex --;
        else
            segmentIndicatorDisplayIndex = maximumSegmentIndicatorDisplayIndex;
    }
    
    private class lunosClockIndicatorActionListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent actionEvent )
        {
            toggleSegmentIndicatorDisplayIndex ( );
        }
    }
    
    private class mouseListenening implements MouseListener
    {
        public void mouseClicked ( MouseEvent mouseEvent )
        {
            if ( segmentIndicatorStructures.get ( 0 ).getBody ( ).contains ( mouseEvent.getX ( ), mouseEvent.getY ( ) ) )
            {
                refreshColumn ( segmentIndicatorStructures );
                lunosClockIndicatorTimer.stop ( );
                lunosClockIndicatorTimer.start ( );
                segmentIndicatorStructures.get ( 0 ).setState ( 1 );
            }
            else if ( segmentIndicatorStructures.get ( 1 ).getBody ( ).contains ( mouseEvent.getX ( ), mouseEvent.getY ( ) ) )
            {
                refreshColumn ( segmentIndicatorStructures );
                lunosClockIndicatorTimer.stop ( );
                lunosClockIndicatorTimer.start ( );
                segmentIndicatorStructures.get ( 1 ).setState ( 1 );
            }
            else if ( segmentIndicatorStructures.get ( 2 ).getBody ( ).contains ( mouseEvent.getX ( ), mouseEvent.getY ( ) ) )
            {
                refreshColumn ( segmentIndicatorStructures );
                lunosClockIndicatorTimer.stop ( );
                lunosClockIndicatorTimer.start ( );
                segmentIndicatorStructures.get ( 2 ).setState ( 1 );
            }
            repaint ( );
        }
        public void mouseReleased ( MouseEvent mouseEvent )
        {
        }
        public void mouseDragged ( MouseEvent mouseEvent )
        {
        }
        public void mouseMoved ( MouseEvent mouseEvent )
        {
        }
        public void mousePressed ( MouseEvent mouseEvent )
        {
        }
        public void mouseExited ( MouseEvent mouseEvent )
        {
        }
        public void mouseEntered ( MouseEvent mouseEvent )
        {
        } 
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
				lunosClockFrame.setVisible ( true );
				visualDelayTimer.stop ( );
			}
		}
	}
}