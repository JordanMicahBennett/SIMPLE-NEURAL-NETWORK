package data.packages.UNICODE; //Author(s): Jordan Micah Bennett //Author(s): Jordan Micah Bennett
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JOptionPane;

public class UNICODE_ConsoleField
{
	//attributes
	private int currentLineIndex = 0;
	private ArrayList <UNICODE_ConsoleFieldLine> consoleFieldLines = new ArrayList <UNICODE_ConsoleFieldLine> ( );
	
	//global line attribs all subsequent console lines will inherit these attributes, and or a variation.
	private int xStart = 0, yStart = 0, lineSpacingAmount = 0, paddingBetweenLabelAndDescription = 0;
	private String label, pointerSymbol;
	private boolean pointerDisplayEnquiry = false;
	private boolean lineNumberDisplayEnquiry = false;
	private boolean appendModeEnquiry = false;
	
	//establish consoleInputField requirement custom font
	private UNICODE_CustomFont customFont = new UNICODE_CustomFont ( "data/fonts/" );
	
	private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
	
	private Thread additionalUpdatingThread = null;
	private int additionalUpdatingThreadKeyCode = 0;
	
	private String fontName = "";
	private Color fontColour = null;
	private int fontSize = 0;
	private int scrollModeIndex = 0;
		
	public UNICODE_ConsoleField ( int xStart, int yStart, int fontSize, int lineSpacingAmount, String label, String pointerSymbol, boolean pointerDisplayEnquiry, boolean lineNumberDisplayEnquiry, boolean appendModeEnquiry, int paddingBetweenLabelAndDescription, String fontName, Color fontColour )
	{
		this.fontName = fontName;
		this.xStart = xStart;
		this.yStart = yStart;
		this.lineSpacingAmount = lineSpacingAmount;
		this.fontSize = fontSize;
		this.fontColour = fontColour;
		this.label = label;
		this.pointerSymbol = pointerSymbol;
		this.pointerDisplayEnquiry = pointerDisplayEnquiry;
		this.paddingBetweenLabelAndDescription = paddingBetweenLabelAndDescription;
		this.pointerDisplayEnquiry = pointerDisplayEnquiry;
		this.lineNumberDisplayEnquiry = lineNumberDisplayEnquiry;
		this.appendModeEnquiry = appendModeEnquiry;
	}
	
	
	//methods
		//accessors
		public int getCurrentLineIndex ( )
		{
			return currentLineIndex;
		}
		public int getXStart ( )
		{
			return xStart;
		}
		public int getYStart ( )
		{
			return yStart;
		}
		public int getLineSpacingAmount ( )
		{
			return lineSpacingAmount;
		}
		
		public int getScrollModeIndex ( )
		{
			return scrollModeIndex;
		}

		public String getLabel ( )
		{
			return label;
		}
		public boolean pointerDisplayEnquiry ( )
		{
			return pointerDisplayEnquiry;
		}	
		public String getPointerSymbol ( )
		{
			String returnValue = "";
			
			if ( pointerDisplayEnquiry )
				returnValue = pointerSymbol;
			else
				returnValue = "";
				
			return pointerSymbol;
		}
		public UNICODE_CustomFont getCustomFont ( )
		{
			return customFont;
		}
		public int getPaddingBetweenLabelAndDescription ( )
		{
			return paddingBetweenLabelAndDescription;
		}
		public Thread getAdditionalUpdatingThread ( )
		{
			return additionalUpdatingThread;
		}
		
		public int getAdditionalUpdatingThreadKeyCode ( )
		{
			return additionalUpdatingThreadKeyCode;
		}
		public int getLineNumber ( )
		{
			return consoleFieldLines.size ( );
		}
		//mutators
		public void incCurrentLineIndex ( )
		{
			currentLineIndex ++;
		}
		public void incLineSpacingAmount ( )
		{
			lineSpacingAmount += conveniencePack.getDisplayHeightFromString ( consoleFieldLines.get ( consoleFieldLines.size ( ) - 1 ).getText ( ), fontSize );
		}	
		public void decCurrentLineIndex ( )
		{
			currentLineIndex --;
		}
		public void setCurrentLineIndex ( int value )
		{
			currentLineIndex = value;
		}
		public void setAdditionalUpdatingThread ( Thread value )
		{
			additionalUpdatingThread = value;
		}
		public void setAdditionalUpdatingThreadKeyCode ( int value )
		{
			additionalUpdatingThreadKeyCode = value;
		}
		public void toggleScrollModeIndex ( )
		{
			if ( scrollModeIndex == 0 )
				scrollModeIndex = 1;
			else if ( scrollModeIndex == 1 )
				scrollModeIndex = 0;
		}
		
		
		public void allowUpdating ( KeyEvent kEvent )
		{
			//allow all console fields to be updated, given that they are enabled
			consoleFieldLines.get ( getCurrentLineIndex ( ) ).allowUpdating ( kEvent );
			
			//disable previous line, then create new one.
			if ( kEvent.getKeyCode ( ) == 40 ) 
			{
				consoleFieldLines.get ( getCurrentLineIndex ( ) ).setFocus ( false );
				addLine ( );
			}
			
			//customized updating thread
			if ( kEvent.getKeyCode ( ) == additionalUpdatingThreadKeyCode ) 
				additionalUpdatingThread.start ( );
		}
		
		
		public void draw ( Graphics graphics, Graphics2D graphics2d )
		{
			for ( int i = 0; i < consoleFieldLines.size ( ); i ++ )
				consoleFieldLines.get ( i ).draw ( graphics, graphics2d, getCustomFont ( ), consoleFieldLines.get ( i ).getX ( ), consoleFieldLines.get ( i ).getY ( ), fontSize, "simple.ttf", fontColour );

			//draw pointer
			int pointerX = getXStart ( ) +
			( int ) conveniencePack.getDisplayWidthFromString ( consoleFieldLines.get ( getCurrentLineIndex ( ) ).getText ( ) , fontSize ) + 
			( int ) conveniencePack.getDisplayWidthFromString ( consoleFieldLines.get ( getCurrentLineIndex ( ) ).getLabel ( ), fontSize );
			
			if ( pointerDisplayEnquiry )
			{
				graphics.setColor ( fontColour );
				getCustomFont ( ).write ( graphics, getPointerSymbol ( ), pointerX, consoleFieldLines.get ( getCurrentLineIndex ( ) ).getY ( ) + 4, fontSize, fontName );
			}
		}
		
		//internal
			public void addLine ( )
			{
				incCurrentLineIndex ( );
				consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getLineSpacingAmount ( ), "" + getLineNumber ( ) + getLabel ( ), true, pointerDisplayEnquiry, getPaddingBetweenLabelAndDescription ( ) ) );
			}
			public void addLine ( String customLabel, String customDescription )
			{
				String label = "";
				
				if ( lineNumberDisplayEnquiry )
					label = "" + getLineNumber ( ) + customLabel;
				else
					label = customLabel;
				
				consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getLineSpacingAmount ( ), label, true, pointerDisplayEnquiry, getPaddingBetweenLabelAndDescription ( ) ) );
				consoleFieldLines.get ( consoleFieldLines.size ( ) - 1 ).setText ( customDescription, appendModeEnquiry );
				incLineSpacingAmount ( );
			}				
		//external
			public void addLine ( String customLabel, Thread renewedAdditionalUpdatingThread )
			{
				String label = "";
				
				if ( lineNumberDisplayEnquiry )
					label = "" + getLineNumber ( ) + customLabel;
				else
					label = customLabel;
			
				additionalUpdatingThread = renewedAdditionalUpdatingThread;
				consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getLineSpacingAmount ( ), label, true, pointerDisplayEnquiry, getPaddingBetweenLabelAndDescription ( ) ) );						
				incLineSpacingAmount ( );
			}
			public void addLine ( String customLabel, String customDescription, Thread renewedAdditionalUpdatingThread )
			{
				additionalUpdatingThread = renewedAdditionalUpdatingThread;
				consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getLineSpacingAmount ( ), "" + getLineNumber ( ) + customLabel, true, pointerDisplayEnquiry, getPaddingBetweenLabelAndDescription ( ) ) );
				consoleFieldLines.get ( getCurrentLineIndex ( ) ).setText ( customDescription, appendModeEnquiry );
				incLineSpacingAmount ( );
			}
			public void scrollField ( int rotationDirection, int scrollRate )
			{
				if ( scrollModeIndex == 0 )
				{
					for ( int i = 0; i < consoleFieldLines.size ( ); i ++ )
					{
						//scroll upwards
						if ( rotationDirection < 0 )
							consoleFieldLines.get ( i ).setY ( consoleFieldLines.get ( i ).getY ( ) - scrollRate );	
						//scroll downwards						
						if ( rotationDirection > 0 )
							consoleFieldLines.get ( i ).setY ( consoleFieldLines.get ( i ).getY ( ) + scrollRate );		
					}	
				}
				
				else if ( scrollModeIndex == 1 )
				{
					for ( int i = 0; i < consoleFieldLines.size ( ); i ++ )
					{
						//scroll upwards
						if ( rotationDirection < 0 )
							consoleFieldLines.get ( i ).setX ( consoleFieldLines.get ( i ).getX ( ) - scrollRate );	
						//scroll downwards						
						if ( rotationDirection > 0 )
							consoleFieldLines.get ( i ).setX ( consoleFieldLines.get ( i ).getX ( ) + scrollRate );		
					}	
				}	
			}
			
			public String getLineText ( )
			{
				return consoleFieldLines.get ( getCurrentLineIndex ( ) ).getText ( );
			}
			
			public void updateLine ( int index, String value )
			{
				consoleFieldLines.get ( index ).resetText ( value, 0 );
			}
			
			public void updateLine ( String value ) //updates last line
			{
				consoleFieldLines.get ( getCurrentLineIndex ( ) ).resetText ( value, 0 );
			}
}