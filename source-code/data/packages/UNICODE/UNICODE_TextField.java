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

public class UNICODE_TextField
{
    //attributes
        //orientation
            //establish default orientation
            private int xOrientation, yOrientation;
            //establish centered coordinates
            int xCenterOrientation = 0, yCenterOrientation = 0;
        //establish textfield array list to hold increasing input that user enters
        private ArrayList text_field_content, decrypted_text_field_content;
        //establish variable to keep track of indices of each character that user enters
        private int character_index;
        //boolean variable to control whether a text field is showing or not
        private boolean visibility;
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
        //establish variables
        private int screen_width = ( int ) screen_dimension.getWidth ( ), screen_height = ( int ) screen_dimension.getHeight ( );
    
    //establish boolean variable to control encryption
    private boolean encrypted;    
    
    //establish pointer showing
    private boolean show_pointer;
    
    //constructor 1 : with text
    public UNICODE_TextField ( int xCoord, int yCoord, String _label, String description, int desc_length, boolean isVisible, boolean encryption_question, boolean _show_pointer )
    {
        //establish orientation
        xOrientation = xCoord;
        yOrientation = yCoord;
        
        //establish label
        label = _label;
        
        //initialise visibility
        visibility = isVisible;
        
        //initialise this
        show_pointer = _show_pointer;
        
        //initialise focus boolean
        focus = false;
        
        //establish encryption variable
        encrypted = encryption_question;
        
        //initialise text field string
        text_field_content = new ArrayList ( );
        decrypted_text_field_content = new ArrayList ( ); //represents un asteriskized value of user input. this can be user to compare datas like passwords.
        
        //initialise character index
        character_index = 0;
        
        //equate description to array list
            //establish scanner to scan string
            Scanner scannern = new Scanner ( description );
            Scanner scannerd = new Scanner ( description );
            //pump array list content
            if ( encrypted )
            {
                for ( int characters = 0; characters < desc_length; characters ++ )
                {
                    incTextField ( characters, "" + scannern.next ( ) );
                    incDecryptedTextField ( characters, "" + scannerd.next ( ) );
                    incCharIndex ( );
                }
            }
            else if ( !encrypted )
                for ( int characters = 0; characters < desc_length; characters ++ )
                {
                    incTextField ( characters, "" + scannern.next ( ) );
                    incCharIndex ( );
                }        
    }

    //for access of convenience functions
    public UNICODE_TextField ( )
    {
    }
    
    //methods
        //accessors
        public String getText ( int start_index )
        {
            String string = null;
            
            for ( int characters = start_index; characters < text_field_content.size ( ); characters ++ )
                string += ( String ) text_field_content.get ( characters );

            return string.replace ( "null", "" );
        }
        public int getSize ( )
        {
            return text_field_content.size ( );
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
        public String getDecryptedText ( int start_index )
        {
            String string = null;
            
            for ( int characters = start_index; characters < decrypted_text_field_content.size ( ); characters ++ )
                string += ( String ) decrypted_text_field_content.get ( characters );

            return string.replace ( "null", "" );
        }
        public boolean getEncryption ( )
        {
            return encrypted;
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
            return text_field_content;
        }
        public ArrayList getDecryptedField ( )
        {
            return decrypted_text_field_content;
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
            return text_field_content.size ( );
        }
        public boolean getVisibility ( )
        {
            return visibility;
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
            public void setText ( String description, int desc_length )
            {  
                //equate description to array list
                    //establish scanner to scan string
                    Scanner scanner = new Scanner ( description );
                    //pump array list content
                    for ( int characters = 0; characters < desc_length; characters ++ )
                    {
                        incTextField ( characters, "" + scanner.next ( ) );
                        incCharIndex ( );
                    } 
            }
            public void resetText ( String description, int new_char_index )
            {
                text_field_content.clear ( );
                text_field_content.add ( description );
                setCharIndex ( new_char_index );
            }
            public void setLabel ( String value )
            {
                label = value;
            }
            public void setEncryption ( boolean value )
            {
                encrypted = value;
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
                visibility = value;
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
                text_field_content.add ( value );
            }

            public void incTextField ( int index, String value )
            {
                text_field_content.add ( index, value );
            }
            public void decTextField ( )
            {
                text_field_content.remove ( getCharIndex ( ) );
            }   
            public void decTextField ( int index )
            {
                text_field_content.remove ( index );
            }   
            public void incDecryptedTextField ( String value )
            {
                decrypted_text_field_content.add ( value );
            }
            public void incDecryptedTextField ( int index, String value )
            {
                decrypted_text_field_content.add ( index, value );
            }
            public void decDecryptedTextField ( )
            {
                decrypted_text_field_content.remove ( getCharIndex ( ) );
            }   
            public void decDecryptedTextField ( int index )
            {
                decrypted_text_field_content.remove ( index );
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
            public void allowUpdating ( KeyEvent kEvent, int max_elements, String encryption_symbol )
            {
                if ( getFocus ( ) && getVisibility ( ) )
                {
                    if ( getCharIndex ( ) < max_elements ) //let programmer controll the maximum amount of characters taht may be entered.
                    {
                        //test to see if user has toggled keys that don't have legitimate character output.
                        //then append to text with key characters except those that are isolated by the above condition
                        if ( ( kEvent.getKeyCode ( ) != 13 ) && ( kEvent.getKeyCode ( ) != 27 ) && ( kEvent.getKeyCode ( ) != 8 ) && ( kEvent.getKeyCode ( ) != 20 ) && ( kEvent.getKeyCode ( ) != 16 ) && ( kEvent.getKeyCode ( ) != 17 ) && ( kEvent.getKeyCode ( ) != 18 )&& ( kEvent.getKeyCode ( ) != 524 )&& ( kEvent.getKeyCode ( ) != 155 )&& ( kEvent.getKeyCode ( ) != 127 )&& ( kEvent.getKeyCode ( ) != 35 )&& ( kEvent.getKeyCode ( ) != 34 )&& ( kEvent.getKeyCode ( ) != 33 )&& ( kEvent.getKeyCode ( ) != 12 )&& ( kEvent.getKeyCode ( ) != 36 )&& ( kEvent.getKeyCode ( ) != 144 )&& ( kEvent.getKeyCode ( ) != 145 )&& ( kEvent.getKeyCode ( ) != 19 ) && ( kEvent.getKeyCode ( ) != 37 ) && ( kEvent.getKeyCode ( ) != 38 ) && ( kEvent.getKeyCode ( ) != 39 ) && ( kEvent.getKeyCode ( ) != 40 ) && ( kEvent.getKeyCode ( ) != 112 ) && ( kEvent.getKeyCode ( ) != 113 ) && ( kEvent.getKeyCode ( ) != 114 ) && ( kEvent.getKeyCode ( ) != 115 ) && ( kEvent.getKeyCode ( ) != 116 ) && ( kEvent.getKeyCode ( ) != 117 ) && ( kEvent.getKeyCode ( ) != 118 ) && ( kEvent.getKeyCode ( ) != 119 ) && ( kEvent.getKeyCode ( ) != 120 ) && ( kEvent.getKeyCode ( ) != 121 ) && ( kEvent.getKeyCode ( ) != 122 ) && ( kEvent.getKeyCode ( ) != 123 ) )
                        {
                            if ( getEncryption ( ) )
                            {
                                incDecryptedTextField ( getCharIndex ( ), "" + kEvent.getKeyChar ( ) );
                                incTextField ( getCharIndex ( ), encryption_symbol );
                            }
                            else
                                incTextField ( getCharIndex ( ), "" + kEvent.getKeyChar ( ) );
                            incCharIndex ( );
                        }
                        //if backspace key is toggled...
                        if ( kEvent.getKeyCode ( ) == 8 ) 
                            if ( getCharIndex ( ) >= 0 ) //if there is something to delete
                            {
                                decCharIndex ( ); //establish deletion index
                                if ( getEncryption ( ) ) //if encryption ws enabled, this implies that text the decrypted text field was filled...so enabled deletion of such,
                                    decDecryptedTextField ( );
                                    
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
                        
                    }
                }
            }
            //draw text field - last param determines whether text will be centered on screen or not
            //yes || YES || y || Y = centered ignore jump_x, jump_y params, passing 0
            //no || NO || n || N = custom coordinates, use jump_x, jump_y coordinates, to differentiate centerd text
            public void draw ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String label_font_name, String text_field_font_name, float text_field_size, Color text_field_colour, float label_size, Color label_colour, String center_answer, int jump_x, int jump_y, float pointer_size, String pointer, int pointer_distance, Color pointer_colour )
            {       
                //we want to get label size, so we can start of appending and setting text field text away from the label.
                    //establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
                    FontMetrics label_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, label_font_name, getLabel ( ) ) );
                    //establish rectangle, from which font dimension will be derived.
                    Rectangle2D label_dimension = label_metrics.getStringBounds ( getLabel ( ), graphics );
    
                    //establish label's width 
                    setTextLabelWidth ( ( int ) label_dimension.getWidth ( ) );
                    //for the purpose of mouse click receiver, we also need height.
                    setTextLabelHeight ( ( int ) label_dimension.getHeight ( ) );

                    
                //we also want to get field description size, for the purpose of the click receiver
                //I just happened to initalise here...where graphics and graphics2d exists.
                    //establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
                    FontMetrics description_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, label_font_name, getText ( 0 ) ) );
                    //establish rectangle, from which font dimension will be derived.
                    Rectangle2D description_dimension = label_metrics.getStringBounds ( getText ( 0 ), graphics );    
                    //establish description's width 
                    setTextDescriptionWidth ( ( int ) description_dimension.getWidth ( ) );
                    //establish description's height
                    setTextDescriptionHeight ( ( int ) description_dimension.getHeight ( ) );
      
               
                //establish variables to center text.
                    //label
                    int center_x_label = ( int ) ( ( screen_width - label_dimension.getWidth ( ) ) / 2 ) - label_metrics.stringWidth ( getLabel ( ) ) * 4;
                    int center_y_label = ( int ) ( ( ( screen_height - label_dimension.getHeight ( ) ) / 2 + label_metrics.getAscent ( ) ) );
                    //description
                    int center_x_description = ( int ) ( ( screen_width - description_dimension.getWidth ( ) ) / 2 ) - description_metrics.stringWidth ( getText ( 0 ) ) * 4;
                    int center_y_description = ( int ) ( ( ( screen_height - description_dimension.getHeight ( ) ) / 2 + description_metrics.getAscent ( ) ) );
                    //average the above central locations, and establish this as new center
                    int center_x = ( center_x_label + center_x_description ) / 2;
                    int center_y = ( center_y_label + center_y_description ) / 2;
                    
                    
                //determine whether or not the user wants to center text
                if ( center_answer.equals ( "yes" ) || center_answer.equals ( "y" ) || center_answer.equals ( "YES" ) || center_answer.equals ( "Y" ) )
                {
                    setCenteredX ( center_x );
                    setCenteredY ( center_y );
                }
                else if ( center_answer.equals ( "no" ) || center_answer.equals ( "n" ) || center_answer.equals ( "NO" ) || center_answer.equals ( "N" ) )
                {
                    setCenteredX ( getX ( ) );
                    setCenteredY ( getY ( ) );
                }
                
                
                if ( getVisibility ( ) )
                {
                    //draw label
                    graphics.setColor ( label_colour );
                    font.write ( graphics, "" + getLabel ( ), getCenteredX ( ) + jump_x, getCenteredY ( ) + jump_y, label_size, label_font_name );
                    
                    //draw description
                    graphics.setColor ( text_field_colour );
                    font.write ( graphics, "" + getText ( 0 ), ( getCenteredX ( ) + ( text_label_width * 2 ) ) + jump_x, getCenteredY ( ) + jump_y, text_field_size, text_field_font_name );
                    
                    //draw pointer
                    //determine pointer location
                    int width_summation_of_preceding_characters = 0;
                    for ( int characters = 0; characters < getCharIndex ( ); characters ++ )
                        width_summation_of_preceding_characters += ( description_metrics.charWidth ( characters ) * 4 );
                        
                    int pointer_x = ( int ) ( width_summation_of_preceding_characters - ( description_metrics.charWidth ( getCharIndex ( ) ) ) );// - ( description_metrics.charWidth ( getCharIndex ( ) ) / 2 );
                    graphics.setColor ( pointer_colour );    
                    
                    if ( show_pointer )
                        font.write ( graphics, pointer,  ( getCenteredX ( ) + ( text_label_width * 2 ) ) + jump_x + pointer_x, getCenteredY ( ) + jump_y + pointer_distance, pointer_size, text_field_font_name );
                }   
            }
            
            //draw with respect to a specified buffer limit, instead of screen limit.
            //draw text field - last param determines whether text will be centered on screen or not
            //yes || YES || y || Y = centered ignore jump_x, jump_y params, passing 0
            //no || NO || n || N = custom coordinates, use jump_x, jump_y coordinates, to differentiate centerd text
            public void draw ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String label_font_name, String text_field_font_name, float text_field_size, Color text_field_colour, float label_size, Color label_colour, String center_answer, int jump_x, int jump_y, float pointer_size, String pointer, int pointer_distance, Color pointer_colour, int specific_buffer_width, int specific_buffer_height )
            {       
                //we want to get label size, so we can start of appending and setting text field text away from the label.
                    //establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
                    FontMetrics label_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, label_font_name, getLabel ( ) ) );
                    //establish rectangle, from which font dimension will be derived.
                    Rectangle2D label_dimension = label_metrics.getStringBounds ( getLabel ( ), graphics );
    
                    //establish label's width 
                    setTextLabelWidth ( ( int ) label_dimension.getWidth ( ) );
                    //for the purpose of mouse click receiver, we also need height.
                    setTextLabelHeight ( ( int ) label_dimension.getHeight ( ) );

                    
                //we also want to get field description size, for the purpose of the click receiver
                //I just happened to initalise here...where graphics and graphics2d exists.
                    //establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
                    FontMetrics description_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, label_font_name, getText ( 0 ) ) );
                    //establish rectangle, from which font dimension will be derived.
                    Rectangle2D description_dimension = label_metrics.getStringBounds ( getText ( 0 ), graphics );    
                    //establish description's width 
                    setTextDescriptionWidth ( ( int ) description_dimension.getWidth ( ) );
                    //establish description's height
                    setTextDescriptionHeight ( ( int ) description_dimension.getHeight ( ) );
      
               
                //establish variables to center text.
                    //label
                    int center_x_label = ( int ) ( ( specific_buffer_width - label_dimension.getWidth ( ) ) / 2 ) - label_metrics.stringWidth ( getLabel ( ) ) * 4;
                    int center_y_label = ( int ) ( ( ( specific_buffer_height - label_dimension.getHeight ( ) ) / 2 + label_metrics.getAscent ( ) ) );
                    //description
                    int center_x_description = ( int ) ( ( specific_buffer_width - description_dimension.getWidth ( ) ) / 2 ) - description_metrics.stringWidth ( getText ( 0 ) ) * 4;
                    int center_y_description = ( int ) ( ( ( specific_buffer_height - description_dimension.getHeight ( ) ) / 2 + description_metrics.getAscent ( ) ) );
                    //average the above central locations, and establish this as new center
                    int center_x = ( center_x_label + center_x_description ) / 2;
                    int center_y = ( center_y_label + center_y_description ) / 2;
                    
                    
                //determine whether or not the user wants to center text
                if ( center_answer.equals ( "yes" ) || center_answer.equals ( "y" ) || center_answer.equals ( "YES" ) || center_answer.equals ( "Y" ) )
                {
                    setCenteredX ( center_x );
                    setCenteredY ( center_y );
                }
                else if ( center_answer.equals ( "no" ) || center_answer.equals ( "n" ) || center_answer.equals ( "NO" ) || center_answer.equals ( "N" ) )
                {
                    setCenteredX ( getX ( ) );
                    setCenteredY ( getY ( ) );
                }
                
                
                if ( getVisibility ( ) )
                {
                    //draw label
                    graphics.setColor ( label_colour );
                    font.write ( graphics, "" + getLabel ( ), getCenteredX ( ) + jump_x, getCenteredY ( ) + jump_y, label_size, label_font_name );
                    
                    //draw description
                    graphics.setColor ( text_field_colour );
                    font.write ( graphics, "" + getText ( 0 ), ( getCenteredX ( ) + ( text_label_width * 2 ) ) + jump_x, getCenteredY ( ) + jump_y, text_field_size, text_field_font_name );
                    
                    //draw pointer
                    //determine pointer location
                    int width_summation_of_preceding_characters = 0;
                    for ( int characters = 0; characters < getCharIndex ( ); characters ++ )
                        width_summation_of_preceding_characters += ( description_metrics.charWidth ( characters ) * 4 );
                        
                    int pointer_x = ( int ) ( width_summation_of_preceding_characters - ( description_metrics.charWidth ( getCharIndex ( ) ) ) );// - ( description_metrics.charWidth ( getCharIndex ( ) ) / 2 );
                    graphics.setColor ( pointer_colour );   
                    if ( show_pointer )
                        font.write ( graphics, pointer,  ( getCenteredX ( ) + ( text_label_width * 2 ) ) + jump_x + pointer_x, getCenteredY ( ) + jump_y + pointer_distance, pointer_size, text_field_font_name );
                }   
            }
            //activate mouse lisetening - passed to a mouse listener's click function
            //if the font is centered, resulting positioning is different;
            //such that rectangles are created differently, and jump_x, and jump_y is used to diffrentiate centered string collections.
            public void activateClickReceiver ( int mouseX, int mouseY, String center_answer, int jump_x, int jump_y )
            {
                if ( getVisibility ( ) )
                {
                    //the rectangle that is passed to the shape must take into account the
                    //entire text field area, that is label + label width * 2 + description width
                    int field_rect_width = getTextLabelWidth ( ) * 2;
                    int field_rect_height = 0; //initialise  height
                    
                    //NOTE!!!!!!!!!
                    //the height is the max height of the bigger of the fonts in a bushman text field.
                    //if the label is larger, then the height is the label
                    //if the description is larger, then the height is the label
                    //this is done so an accurate rectangle for detecting mouse clicks on a text area can be created.
                    
                    //determine max height.
                    if ( getTextDescriptionHeight ( ) > getTextLabelHeight ( ) )
                        field_rect_height = getTextDescriptionHeight ( );
                    else
                        field_rect_height = getTextLabelHeight ( );
                        
                    //determine how the rectangle should be created based center_answer
                    if ( center_answer.equals ( "yes" ) || center_answer.equals ( "y" ) || center_answer.equals ( "YES" ) || center_answer.equals ( "Y" ) )
                        field_rectangle = new Rectangle ( getCenteredX ( ) + jump_x, getCenteredY ( ) + jump_y, field_rect_width, field_rect_height );
                    else if ( center_answer.equals ( "no" ) || center_answer.equals ( "n" ) || center_answer.equals ( "NO" ) || center_answer.equals ( "N" ) )
                        field_rectangle = new Rectangle ( getX ( ), getY ( ), field_rect_width, field_rect_height );   
                        
                    field_shape = field_rectangle;
  
                    if ( field_shape.contains ( mouseX, mouseY ) )
                        setFocus ( true ); //if mouse is clicked, while existent within this shape, activate focus
                    else
                        setFocus ( false ); //else disable focus 
                }
            }
            
            //clean array list
            public void clean ( int starting_index )
            {
                if ( getEncryption ( ) )
                    for ( int elements = starting_index; elements < getDecryptedField ( ).size ( ); elements ++ )
                    {
                        decCharIndex ( );
                        decDecryptedTextField ( elements );
                        decTextField ( elements );
                    }
                else
                    for ( int elements = starting_index; elements <= getField ( ).size ( ); elements ++ )
                    {  
                        decCharIndex ( );
                        decTextField ( elements );
                    }
            }
            
            
            //determines whether user inputs match
            public boolean getMismatchEnquiry ( String specified_string )
            {
                boolean flag = false;
                //if encryption is true, then this implies that the decrypted value
                //is being checked against, instead of default description value.
                if ( getEncryption ( ) )
                {
                    if ( !getDecryptedText ( 1 ).equals ( specified_string ) ) //if mismatch occurs for encrypted fields
                        flag = false; //then enquiry result is false for encrypted fields                       
                    else
                        flag = true; //else enquiry result is true for encrypted fields
                }
                else //encryption is disabled, which implies that the normal bushman field text is being compared against
                {
                    if ( !getText ( 1 ).equals ( specified_string ) ) //if mismatch occurs for un-encrypted fields
                        flag = false; //then enquiry result is false for un-encrypted fields                      
                    else
                        flag = true; //else enquiry result is true for un-encrypted fields
                }
                return flag;
            }
            
            //generates programmer specified response
            public void generateMismatchResponse ( UNICODE_AudioPlayer audio_player, String sound, String response, String response_title )
            {
                audio_player.playAudio ( sound );
                JOptionPane.showMessageDialog ( null, response, response_title, JOptionPane.WARNING_MESSAGE );       
            }
}
