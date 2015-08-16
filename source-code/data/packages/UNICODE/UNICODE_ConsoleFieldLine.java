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

public class UNICODE_ConsoleFieldLine
{
    //attributes
        //orientation
            //establish default orientation
            private int xOrientation, yOrientation;
            //establish centered coordinates
            int xCenterOrientation = 0, yCenterOrientation = 0;
        //establish textfield array list to hold increasing input that user enters
        private ArrayList textFieldContent, decrypted_textFieldContent;
        //establish variable to keep track of indices of each character that user enters
        private int character_index;
        //boolean variable to control whether a text field is showing or not
        private boolean visibilityEnquiry;
        //establish label
        private String label;
        //focus requirements
            //establish boolean variable to control whether focus is currently on that text field.
            private boolean focus;
            //geometry requirements
                //rectangle that will be passed to the shape below
                private Rectangle field_rectangle;
                //shape used to detect if mouse is currently clicked this text field
                private Shape field_shape;
                //establish description and label dimensions 
                private int text_description_width, text_label_width, text_description_height, text_label_height;
    //establish screen dimensions
        //get screen dimensio
        private Dimension screen_dimension = Toolkit.getDefaultToolkit ( ).getScreenSize ( );  
    
    //establish boolean variable to control encryption
    private boolean encrypted;    
    
    //establish pointer showing
    private boolean pointerDisplayEnquiry;
	
	private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
	
	private int paddingBetweenLabelAndDescription = 0;
	
    //constructor 1 : with text
    public UNICODE_ConsoleFieldLine ( int xOrientation, int yOrientation, String label, boolean visibilityEnquiry, boolean pointerDisplayEnquiry, int paddingBetweenLabelAndDescription )
    {
        //establish orientation
        this.xOrientation = xOrientation;
        this.yOrientation = yOrientation;
	
        
        //establish label
        this.label = label;
        
        //initialise visibility
        this.visibilityEnquiry = visibilityEnquiry;
        
        //initialise this
        this.pointerDisplayEnquiry = pointerDisplayEnquiry;
        
        //initialise focus boolean
        this.focus = true;
        
		//establish the space bewteen the label and descriptor
		this.paddingBetweenLabelAndDescription = paddingBetweenLabelAndDescription;
		
        //initialise text field string
        textFieldContent = new ArrayList ( );
		
        //initialise character index
        character_index = 0;
    }

    //for access of convenience functions
    public UNICODE_ConsoleFieldLine ( )
    {
    }
    
    //methods
        //accessors
		public int getPaddingBetweenLabelAndDescription ( )
		{
			return paddingBetweenLabelAndDescription;
		}
        public String getText ( )
        {
            String string = null;
            
			if ( textFieldContent.size ( ) > 0 )
			{
				for ( int characters = 0; characters < textFieldContent.size ( ); characters ++ )
					string += ( String ) textFieldContent.get ( characters );
			}
			else
				string = "";
			
            return string.replace ( "null", "" );
        }
        public int getSize ( )
        {
            return textFieldContent.size ( );
        }
        //since there alre allways user specified charcters in a bushman field at the start,
        //user must spscify how many starting characters they have, so as to determine if the text field
        //is empy by that standard
        public boolean isEmpty ( int num_starting_characters )
        {
            boolean flag = false;
    
            if ( getSize ( ) <= num_starting_characters )
                flag = true;
            else
                flag = false;
                
            return flag;
        }
        public String getLabel ( )
        {
            return label;
        }
        public int getX ( )
        {
            return xOrientation;
        }
        public int getY ( )
        {
            return yOrientation;
        }
        public int getCenteredX ( )
        {
            return xCenterOrientation;
        }
        public int getCenteredY ( )
        {
            return yCenterOrientation;
        }
        public ArrayList getField ( )
        {
            return textFieldContent;
        }
        public boolean getFocus ( )
        {
            return focus;
        }
        public int getCharIndex ( )
        {
            return character_index;
        }
        public int getLength ( )
        {
            return textFieldContent.size ( );
        }
        public boolean getVisibility ( )
        {
            return visibilityEnquiry;
        }
        public int getTextDescriptionWidth ( )
        {
            return text_description_width;
        }
        public int getTextDescriptionHeight ( )
        {
            return text_description_height;
        }
        public int getTextLabelWidth ( )
        {
            return text_label_width;
        }
        public int getTextLabelHeight ( )
        {
            return text_label_height;
        }        
        //mutators
            //static
            public void setText ( String description, boolean appendModeEnquiry )
            {  
				if ( !description.equals ( "" ) )
				{
					if ( appendModeEnquiry )
					{
					
						//equate description to array list
							//establish scanner to scan string
							Scanner scanner = new Scanner ( description );
							
							
							//pump array list content
							for ( int characters = 0; characters < description.length ( ); characters ++ )
							{
								getField ( ).add ( scanner.next ( ) );
								incCharIndex ( );
							} 
					}
					else
						getField ( ).add ( description );
				}
            }
			
            public void resetText ( String description, int new_char_index )
            {
                textFieldContent.clear ( );
                textFieldContent.add ( description );
                setCharIndex ( new_char_index );
            }
            public void setLabel ( String value )
            {
                label = value;
            }
            public void setX ( int value )
            {
                xOrientation = value;
            }
            public void setY ( int value )
            {
                yOrientation = value;
            }
            public void setCenteredX ( int value )
            {
                xCenterOrientation = value;
            }
            public void setCenteredY ( int value )
            {
                yCenterOrientation = value;
            }
            public void setFocus ( boolean value )
            {
                focus = value;
            }
            public void setVisible ( boolean value )
            {
                visibilityEnquiry = value;
            }
            public void setCharIndex ( int value )
            {
                character_index = value;
            }
            public void setTextDescriptionWidth ( int value )
            {
                text_description_width = value;
            }
            public void setTextDescriptionHeight ( int value )
            {
                text_description_height = value;
            }
            public void setTextLabelWidth ( int value )
            {
                text_label_width = value;
            }
            public void setTextLabelHeight ( int value )
            {
                text_label_height = value;
            }        
            //dynamic
            public void incTextField ( String value )
            {
                textFieldContent.add ( value );
            }

            public void incTextField ( int index, String value )
            {
                textFieldContent.add ( index, value );
            }
            public void decTextField ( )
            {
                textFieldContent.remove ( getCharIndex ( ) );
            }   
            public void decTextField ( int index )
            {
                textFieldContent.remove ( index );
            }   
            public void incCharIndex ( )
            {
                character_index ++;
            }
            public void incCharIndex ( int value )
            {
                character_index += value;
            }
            public void decCharIndex ( )
            {
                character_index --;
            }
        //misc
            //"grow" or "shrink" a bushman text field based on which key is toggled ( pressed )
            public void allowUpdating ( KeyEvent kEvent )
            {
                if ( getFocus ( ) && getVisibility ( ) )
                {
					//test to see if user has toggled keys that don't have legitimate character output.
					//then append to text with key characters except those that are isolated by the above condition
					if ( ( kEvent.getKeyCode ( ) != 13 ) && ( kEvent.getKeyCode ( ) != 27 ) && ( kEvent.getKeyCode ( ) != 8 ) && ( kEvent.getKeyCode ( ) != 20 ) && ( kEvent.getKeyCode ( ) != 16 ) && ( kEvent.getKeyCode ( ) != 17 ) && ( kEvent.getKeyCode ( ) != 18 )&& ( kEvent.getKeyCode ( ) != 524 )&& ( kEvent.getKeyCode ( ) != 155 )&& ( kEvent.getKeyCode ( ) != 127 )&& ( kEvent.getKeyCode ( ) != 35 )&& ( kEvent.getKeyCode ( ) != 34 )&& ( kEvent.getKeyCode ( ) != 33 )&& ( kEvent.getKeyCode ( ) != 12 )&& ( kEvent.getKeyCode ( ) != 36 )&& ( kEvent.getKeyCode ( ) != 144 )&& ( kEvent.getKeyCode ( ) != 145 )&& ( kEvent.getKeyCode ( ) != 19 ) && ( kEvent.getKeyCode ( ) != 37 ) && ( kEvent.getKeyCode ( ) != 38 ) && ( kEvent.getKeyCode ( ) != 39 ) && ( kEvent.getKeyCode ( ) != 40 ) && ( kEvent.getKeyCode ( ) != 112 ) && ( kEvent.getKeyCode ( ) != 113 ) && ( kEvent.getKeyCode ( ) != 114 ) && ( kEvent.getKeyCode ( ) != 115 ) && ( kEvent.getKeyCode ( ) != 116 ) && ( kEvent.getKeyCode ( ) != 117 ) && ( kEvent.getKeyCode ( ) != 118 ) && ( kEvent.getKeyCode ( ) != 119 ) && ( kEvent.getKeyCode ( ) != 120 ) && ( kEvent.getKeyCode ( ) != 121 ) && ( kEvent.getKeyCode ( ) != 122 ) && ( kEvent.getKeyCode ( ) != 123 ) )
					{
						incTextField ( getCharIndex ( ), "" + kEvent.getKeyChar ( ) );	
						incCharIndex ( );
					}
					//if backspace key is toggled...
					if ( kEvent.getKeyCode ( ) == 8 ) 
						if ( getCharIndex ( ) > 0 ) //if there is something to delete
						{
							decCharIndex ( ); //establish deletion index
							decTextField ( );
						}
					//reposition character index when left and right arrow keys are toggled!!!
						//left arrow key
						if ( kEvent.getKeyCode ( ) == 37 ) 
							//if there is still a letter to go behind
							if ( getLength ( ) >= 1 ) 
								decCharIndex ( );
								
						//right arrow key
						if ( kEvent.getKeyCode ( ) == 39 ) 
							//if there is still a letter to go infront of
							if ( getCharIndex ( ) <= getLength ( ) ) 
								incCharIndex ( ); //fast forward character index one place
								
					// //if delete key is toggled, then delete rightwards
					// if ( kEvent.getKeyCode ( ) == 46 ) 
					// {
						// incCharIndex ( ); //establish deletion index
						// decTextField ( );
					// }
				}
            }
            //draw text field 
            public void draw ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, int startX, int startY, int fontSize, String fontName, Color fontColour )
            {     
                if ( getVisibility ( ) )
                {
					//establish colour
                    graphics.setColor ( fontColour );
					
                    //draw label
                    font.write ( graphics, getLabel ( ), startX, startY, fontSize, fontName );
                    
                    //draw description
					int descriptionX = getPaddingBetweenLabelAndDescription ( ) + startX + ( int ) conveniencePack.getDisplayWidthFromString ( getLabel ( ), fontSize );
					font.write ( graphics, getText ( ), descriptionX, startY, fontSize, fontName );					
                }   
            }
            
            //clean array list
            public void clean ( int starting_index )
            {
				for ( int elements = starting_index; elements <= getField ( ).size ( ); elements ++ )
				{  
					decCharIndex ( );
					decTextField ( elements );
				}
            }
}
