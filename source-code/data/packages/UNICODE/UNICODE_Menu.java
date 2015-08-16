package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Scanner;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import javax.swing.*;
import java.awt.TexturePaint;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;

public class UNICODE_Menu
{
    //attributes
        //establish menu visibility control variable
        private boolean visibility;
        //establish button list
        private ArrayList <UNICODE_Button> button_list = new ArrayList <UNICODE_Button> ( );
        //establish button list size
        private int button_cardinality;
    //establish screen dimensions
        //get screen dimensio
        private Dimension screen_dimension = Toolkit.getDefaultToolkit ( ).getScreenSize ( );  
        //establish variables
        private int screen_width = 1280, screen_height = 720;
    
 
        
        //establish layout mechanism requirements
            //establish maximum buttons allowed to be aligned on button list axis
            private int maximum_buttons;
            //establish angle incrementation value as points are generated
            private double angle_step;
            //establish button list axis radii; first for generation purpose, second for graphical representation of circle
            private double bla_radius_grep = 630;
            //establish button list coord pack orientaiton
            private int bla_x, bla_y;
        //establish coord gen stuff
            private ArrayList <UNICODE_Button> button_list_gen = new ArrayList <UNICODE_Button> ( );
            private int [ ] x_coord_gen,  y_coord_gen;
        //establish background animation requirements
            //establish animation cycle variable
            private int background_animation_cycle;

		//establish rotation requirements
            //old transformation data
            private AffineTransform old_affine_transformation;
			private boolean rotate = false;
			private double rotation_angle = Math.PI / 7;
			private int rotation_direction_index = 1;
			private int axis_width = 0;
			private int axis_height = 0;
			private int axis_x = 0;
			private int axis_y = 0;
			private float rotation_cycles = 0.0f; 
			
    public UNICODE_Menu ( ArrayList <UNICODE_Button> button_array, boolean visibility_status, int total_buttons, int _screen_width, int _screen_height )
    {
        //establish button list
        button_list = button_array;   
        //establish visibility
        visibility = visibility_status;
        //establish maximum buttons 
        maximum_buttons = total_buttons;
        //establish angle step
        angle_step = 360 / maximum_buttons; 
		screen_width = _screen_width;
		screen_height = _screen_height;
    }
    
    //empty constructor to access misc functions
    public UNICODE_Menu (  )
    {
    }
    
    //methods
        //accessors
        public boolean getVisibility ( )
        {
            return visibility;
        }
        public ArrayList <UNICODE_Button> getButtonList ( )
        {
            return button_list;
        }
        public int getButtonListSize ( )
        {
            return ( int ) button_list.size ( );
        }
        public int getScrWidth ( )
        {
            return screen_width;
        }        
        public int getScrHeight ( )
        {
            return screen_height;
        }      
        public int getMaximumButtons ( )
        {
            return maximum_buttons;
        }
        public int [ ] getXGen ( )
        {
            return x_coord_gen;
        }
        public int [ ] getYGen ( )
        {
            return y_coord_gen;
        }            
        public Point getAxisLocation ( )
        {
            return new Point ( bla_x, bla_y );
        }
        public int getBackgroundAnimationCycle ( )
        {
            return background_animation_cycle;
        }
		public boolean isRotated ( )
		{
			return rotate;
		}
		public double getRotationAngle ( )
		{
			return rotation_angle;
		}		
		public int getRotationDirectionIndex ( )
		{
			return rotation_direction_index;
		}	
        public AffineTransform getOldAffineTransform ( )
        {
            return old_affine_transformation;
        }
		public int getAxisWidth ( )
		{
			return axis_width;
		}
		public int getAxisHeight ( )
		{
			return axis_height;
		}
 		public int getAxisX ( )
		{
			return axis_x;
		}	
		public int getAxisY ( )
		{
			return axis_y;
		}
		public float getRotationCycles ( )
		{
			return rotation_cycles;
		}       
        //mutators
            //static
            public void setScrWidth ( int value )
            {
                screen_width = value;
            }        
            public void getScrHeight ( int value )
            {
                screen_height = value;
            } 
            public void setVisibility ( boolean value )
            {
                visibility = value;
            }
            public void setButtonListSize ( int value )
            {
                button_cardinality = value;
            }
            public void setXCoordGen ( int index, int value )
            {
                x_coord_gen [ index ] = value;
            }
            public void setYCoordGen ( int index, int value )
            {
                y_coord_gen [ index ] = value;
            }
            public void setAxisLocation ( int x, int y )
            {
                bla_x = x;
                bla_y = y;
            }
            public void setBackgroundAnimationCycle ( int value )
            {
                background_animation_cycle = value;
            }
			public void setRotationCycles ( float value )
			{
				rotation_cycles = value;
			}
			public void resetRotationCycles ( )
			{
				rotation_cycles = 0;
			}
			public void setAxisWidth ( int value )
			{
				axis_width = value;
			}
			public void setAxisHeight ( int value )
			{
				axis_height = value;
			}
			public void setAxisX ( int value )
			{
				axis_x = value;
			}	
			public void setAxisY ( int value )
			{
				axis_y = value;
			}
            //dynamic
            public void incButtonListSize ( )
            {
                button_cardinality ++;
            }            
            public void decButtonListSize ( )
            {
                button_cardinality --;
            }     
            public void incBackgroundAnimationCycle ( )
            {
                background_animation_cycle ++;
            }
            public void incBackgroundAnimationCycle ( int value )
            {
                background_animation_cycle += value;
            }           
            public void decBackgroundAnimationCycle ( )
            {
                background_animation_cycle --;
            }   
            public void decBackgroundAnimationCycle ( int value )
            {
                background_animation_cycle -= value;
            }    
			
            //restore old transform so as to not affect other un-selected buttons or 
            //graphics 2d component or object currently on screem
            //only selected items must be scaled, this ensures this.
            public void restoreOldTransform ( Graphics2D graphics2d )
            {
                graphics2d.setTransform ( getOldAffineTransform ( ) );
            }
			public void incRotationAngle ( double value )
			{
				rotation_angle += value;
			}
			public void decRotationAngle ( double value )
			{
				rotation_angle -= value;
			}
			public void setRotationAngle ( double value )
			{
				rotation_angle = value;
			}		
			public void setRotation ( boolean value )
			{
				rotate = value;
			}
			public void setRotationDirection ( String direction )
			{
				if ( direction.equals ( "clockwise" ) )
					rotation_direction_index = 1;
				else if ( direction.equals ( "anti-clockwise" ) )
					rotation_direction_index = -1;
			}
            //establish old affine transform ( used in draw function )
            public void establishOldAffineTransform ( Graphics2D graphics2d )
            {
                old_affine_transformation = graphics2d.getTransform ( );
            }
			public void incRotationCycles ( float value )
			{
				rotation_cycles += value;
			}		
			public void decRotationCycles ( float value )
			{
				rotation_cycles -= value;
			}		
        //misc
            //show menu
            public void showMenu ( Graphics2D graphics2d, Color button_outline_colour, JPanel gui_panel )
            {
				//save old transformation
				establishOldAffineTransform ( graphics2d );
				
				//setup rotational axis dimension and orientation
				setAxisX ( screen_width - getAxisWidth ( ) );
				setAxisY ( screen_height - getAxisHeight ( ) );
				
				//apply rotation weh rotation bool is set true
				if ( isRotated ( ) )
					graphics2d.transform ( AffineTransform.getRotateInstance ( getRotationDirectionIndex ( ) * getRotationAngle ( ), getAxisX ( ) + getAxisWidth ( ) / 2, getAxisY ( ) + getAxisHeight ( ) / 2 ) );
		
                if ( getVisibility ( ) )
                {
                    for ( int buttons = 0; buttons < getButtonListSize ( ); buttons ++ )
                    {
                        button_list.get ( buttons ).setVisibility ( true );
                        button_list.get ( buttons ).draw ( graphics2d, button_outline_colour, gui_panel );
                    }
                }
				
				//restore old affine transform
				restoreOldTransform ( graphics2d );
            }
            
            
            //layout buttons
            public void showUiAxis ( Graphics2D graphics2d, Color ui_axis_colour )
            {
                graphics2d.setColor ( ui_axis_colour );
                //draw graphical repreenttion of circle axis
                Shape bla_grep = new Ellipse2D.Double ( getScrWidth ( ) / 3 - getScrWidth ( ) / 12, getScrHeight ( ) / 14, bla_radius_grep, bla_radius_grep );
                graphics2d.draw ( bla_grep );
            }
            
            
            //generate layout points
            public Point [ ] generateLayoutPoints ( double axis_radius, int buffer_width, int buffer_height, String layoutType )
            {
				//establish point
				Point [ ] point = null;				
				//establish axis location ( setting getAxisLocation ( ).getX ( ) , and getAxisLocation ( ).getY ( ) )
				setAxisLocation ( buffer_width/2, buffer_height/3 );
				//establish point
				point = new Point [ getMaximumButtons ( ) ];
					
				///////////////////
				//REGULAR 
				///////////////////
				if ( layoutType.equals ( "circular" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
						
				else if ( layoutType.equals ( "left-slant" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
            
				else if ( layoutType.equals ( "right-slant" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
         
				else if ( layoutType.equals ( "vertical" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( ( ( angle_step * buttons ) * ( Math.PI / 360 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 360 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
            
				else if ( layoutType.equals ( "horizontal" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 360 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( ( ( angle_step * buttons ) * ( Math.PI / 360 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "random" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 8 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "t" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 360 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 70 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				///////////////////
				//INVERTIONS 
				///////////////////
				else if ( layoutType.equals ( "circular+vertical-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "circular+hortizontal-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) -( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "circular+vertical-flip+horizontal-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "circular+clockwise-rotation" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) - ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
																
				else if ( layoutType.equals ( "circular+anti-clockwise-rotation" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "circular+clockwise-rotation+vertical-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) - ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) -( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
								
				else if ( layoutType.equals ( "circular+clockwise-rotation+horizontal-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
   
   
				else if ( layoutType.equals ( "circular+left-rotation+vertical-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) - ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "left-slant+vertical-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
            
				else if ( layoutType.equals ( "right-slant+vertical-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
         
				else if ( layoutType.equals ( "vertical+vertical-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
            
				else if ( layoutType.equals ( "horizontal+horizontal-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.cos ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
				else if ( layoutType.equals ( "random+vertical-flip" ) )
					//generate the positions
					for ( int buttons = 0; buttons < getMaximumButtons ( ); buttons ++ )
						point [ buttons ] = new Point ( ( int ) ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 8 ) ) ) + ( int ) getAxisLocation ( ).getX ( ), ( int ) - ( axis_radius * Math.sin ( ( angle_step * buttons ) * ( Math.PI / 180 ) ) ) + ( int ) getAxisLocation( ).getY ( ) );
				
                return point;
            }
			
        
            //NOTE:
            //coordinate generation mechanism - solves the problem of setX ( ), setY ( ) .. of UNICODE_Button which is non-functional
   
                
            //grid axis coordinate generation
                //convenience function : grid axis coordinate generation array:
                //shortcut; defines user coordGen arrays in gui panel, based on max menus
                //the user just needs to create an array, in GuiPanel, then pass this function to it.
                public UNICODE_Menu [ ] defineGridAxisCoordinateGenerationArray ( int maximum_menus, String max_button_list, boolean visibility, int highest_button_number, double axis_radius, int buffer_width, int buffer_height, String direction, String layoutType )
                { 
					//establish local menu array
					UNICODE_Menu [ ] menu_array = new UNICODE_Menu [ maximum_menus ];
						
					if ( layoutType != null )
					{
						//establish max button array, so as to allocate establishment function with unique max button values
								//the array
								int max_button_value_array [ ] = new int [ maximum_menus ];
								//the scanner to extract data from string
								Scanner scanner = new Scanner ( max_button_list );
							
							//establish coordinate generation for all menus
							for ( int menus = 0; menus < maximum_menus; menus ++ )
							{
								//estabish unique max button number per menu
								max_button_value_array [ menus ] = Integer.parseInt ( scanner.next ( ) );
								
								//intialise menus
								menu_array [ menus ] = new UNICODE_Menu ( );
								
								//establish grid generation function, passing the max button value array
								//to generate grids with unique button n
								menu_array [ menus ].establishGridAxisCoordinateGenerationFunction ( max_button_value_array [ menus ], visibility, highest_button_number, axis_radius, buffer_width, buffer_height, direction, layoutType );  
							}
					}
					//return the array 
					return menu_array;
                }
                    
                
                //requirement of function above, actually generates coordinates and sets them to 
                //arrays to be used to define orientation in button creations in Gui Panel.
                //the function above creates the fake menus needed to generate these coordinates.
                public void establishGridAxisCoordinateGenerationFunction ( int max_buttons, boolean visibility_status, int highestButtonNumber, double axis_radius, int buffer_width, int buffer_height, String direction, String layoutType )
                {                
					if ( layoutType != null )
					{
						//establish null button menu
						UNICODE_Menu null_menu = new UNICODE_Menu ( button_list_gen, visibility_status, highestButtonNumber, screen_width, screen_height );
						
						//establish index start ( tells how indices recive data )
						if ( direction.equals ( "anti-clockwise" ) )
						{
							int index_start = max_buttons - 1;
					
							//establish bushman button array
							for ( int buttons = 0; buttons < max_buttons; buttons ++ )
								button_list_gen.add ( new UNICODE_Button ( ) );
								
							//initialise coord gen integer arrays
							x_coord_gen = new int [ max_buttons ];
							y_coord_gen = new int [ max_buttons ];
								
							//generate coordinates
							for ( int buttons = 0; buttons < max_buttons; buttons ++ )
							{
								//pump coordinates into coord arrays starting from highest position in array based on
								//maximum buttons
								setXCoordGen ( index_start - buttons, ( int ) null_menu.generateLayoutPoints ( axis_radius, buffer_width, buffer_height, layoutType ) [ buttons ].getX ( ) );
								setYCoordGen ( index_start - buttons, ( int ) null_menu.generateLayoutPoints ( axis_radius, buffer_width, buffer_height, layoutType ) [ buttons ].getY ( ) );
							}
						}
						else if ( direction.equals ( "clockwise" ) )
						{
							int index_start = 0;
					
							//establish bushman button array
							for ( int buttons = 0; buttons < max_buttons; buttons ++ )
								button_list_gen.add ( new UNICODE_Button ( ) );
								
							//initialise coord gen integer arrays
							x_coord_gen = new int [ max_buttons ];
							y_coord_gen = new int [ max_buttons ];
								
							//generate coordinates
							for ( int buttons = 0; buttons < max_buttons; buttons ++ )
							{
								//pump coordinates into coord arrays starting from highest position in array based on
								//maximum buttons
								setXCoordGen ( buttons, ( int ) null_menu.generateLayoutPoints ( axis_radius, buffer_width, buffer_height, layoutType ) [ buttons ].getX ( ) );
								setYCoordGen ( buttons, ( int ) null_menu.generateLayoutPoints ( axis_radius, buffer_width, buffer_height, layoutType ) [ buttons ].getY ( ) );
							}
						}
					}
                }
                
                //establish button array grid adjuster
                //used to adjust buttons such that user can adjust all but last then position of the last button
                //this is due to the structure of my menus. the last button is always smallest, therefore
                //requires different adjustment. If a user wants buttons the same way, just feed
                //the "all" orientation to the "last"
                public int [ ] createButtonGridAdjustmentPack ( int all_x, int all_y, int last_x, int last_y )
                {
                    int [ ] array = new int [ 4 ];
                    array [ 0 ] = all_x;
                    array [ 1 ] = all_y;
                    array [ 2 ] = last_x;
                    array [ 3 ] = last_y;
                    return array;
                }
                 
                //create manu label
                //all texts in create label will align centrally to screen!!!!
                //Howver theri are timers when more than one font is on screen, 
                //so we want to set their coordinates uniqely among each other,
                //so we have "jump" params that will indeed do this.
                public void createLabel ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String menu_description, float menu_label_size, String font_name )
                {
                    //establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of text dimension
                    FontMetrics font_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, font_name, menu_description ) );
                    //establish rectangle, from which font dimension will be derived.
                    Rectangle2D text_dimension = font_metrics.getStringBounds ( menu_description, graphics );
                    //establish central coordinates, based on derived string bounds 
                    int center_x = ( int ) ( ( screen_width - text_dimension.getWidth ( ) ) / 2 ) - font_metrics.stringWidth ( menu_description ) * 4;
                    int center_y = ( int ) ( ( ( screen_height - text_dimension.getHeight ( ) ) / 2 + font_metrics.getAscent ( ) ) );
                    
                    font.write ( graphics, menu_description, center_x, center_y, menu_label_size, font_name );                    
                }
				
                //same like above, but take jump coordinates to slightl y adjust coords wrt to central locations.
                public void createLabel ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String menu_description, int jump_x, int jump_y, float menu_label_size, String font_name )
                {
                    //establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of text dimension
                    FontMetrics font_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, font_name, menu_description ) );
                    //establish rectangle, from which font dimension will be derived.
                    Rectangle2D text_dimension = font_metrics.getStringBounds ( menu_description, graphics );
                    //establish central coordinates, based on derived string bounds 
                    int center_x = ( int ) ( ( ( screen_width - text_dimension.getWidth ( ) ) / 2 ) - font_metrics.stringWidth ( menu_description ) * 4 ) + jump_x;
                    int center_y = ( int ) ( ( ( ( screen_height - text_dimension.getHeight ( ) ) / 2 + font_metrics.getAscent ( ) ) ) ) + jump_y;
                    
                    font.write ( graphics, menu_description, center_x, center_y, menu_label_size, font_name );                    
                }
                
                //show menu animation
                public void showAnimation ( Graphics graphics, Graphics2D graphics2d, JPanel panel, String filename, UNICODE_Structure_TexturePaint texture_creator, int animation_height_span, double animation_y1_stretch, double animation_y2_stretch, int y_start )
                {
                    //establish panel dimension ( panel on which the animation will take place )
                    Dimension panel_dimension = panel.getSize ( );
                    
                    //establish amplitude
                    int amplitude = panel_dimension.height / animation_height_span;
                    
                    //establish animation texture paint
                    TexturePaint texture = texture_creator.makeTexturePaint ( );
                    graphics2d.setPaint ( texture );
                    
                    //generate sine wave animations
                    for ( int anim_x = 0; anim_x < panel_dimension.width; anim_x ++ )
                    {
                        int anim_y1 = ( int ) ( 1.0 + Math.sin ( ( anim_x - getBackgroundAnimationCycle ( ) ) * animation_y1_stretch ) * amplitude );
                        int anim_y2 = ( int ) ( 1.0 + Math.sin ( ( anim_x + getBackgroundAnimationCycle ( ) ) * animation_y2_stretch ) * amplitude );
                        graphics.drawLine ( anim_x, anim_y1 + y_start, anim_x, anim_y2 + y_start );
                    }
                    //NOTE y1 and y2 strecth dictates how much the animation sine waves will strecth accoss the screen.
                    //acceptable values span from y1,y2(0.05,0.07) .. to y1,y2(0.008,0.009)
                }  
				
                //show menu animation, with a user supplied buffer range. ie screen size.
                public void showAnimation ( Graphics graphics, Graphics2D graphics2d, JPanel panel, String filename, UNICODE_Structure_TexturePaint texture_creator, int animation_height_span, double animation_y1_stretch, double animation_y2_stretch, int y_start, Rectangle bufferRange )
                {
                    //establish panel dimension ( panel on which the animation will take place )
                    Dimension panel_dimension = panel.getSize ( );
                    
                    //establish amplitude
                    int amplitude = panel_dimension.height / animation_height_span;
                    
                    //establish animation texture paint
                    TexturePaint texture = texture_creator.makeTexturePaint ( bufferRange );
                    graphics2d.setPaint ( texture );
                    
                    //generate sine wave animations
                    for ( int anim_x = 0; anim_x < panel_dimension.width; anim_x ++ )
                    {
                        int anim_y1 = ( int ) ( 1.0 + Math.sin ( ( anim_x - getBackgroundAnimationCycle ( ) ) * animation_y1_stretch ) * amplitude );
                        int anim_y2 = ( int ) ( 1.0 + Math.sin ( ( anim_x + getBackgroundAnimationCycle ( ) ) * animation_y2_stretch ) * amplitude );
                        graphics.drawLine ( anim_x, anim_y1 + y_start, anim_x, anim_y2 + y_start );
                    }
                    //NOTE y1 and y2 strecth dictates how much the animation sine waves will strecth accoss the screen.
                    //acceptable values span from y1,y2(0.05,0.07) .. to y1,y2(0.008,0.009)
                }  
				//establish button visual response action listener component
				public void establishButtonVisualResponseActionListenerComponent ( boolean entityDisplayEnquiry, int activeButtonSelectionIndex, ArrayList <UNICODE_Button> buttonPack, double scaleChangeRate )
				{
					if ( entityDisplayEnquiry )
						if ( buttonPack.get ( activeButtonSelectionIndex ).getScale ( ) <= buttonPack.get ( activeButtonSelectionIndex ).getScaleMaximum ( ) )
						{
							buttonPack.get ( activeButtonSelectionIndex ).setResized ( true );
							buttonPack.get ( activeButtonSelectionIndex ).setScaleChangeRate ( scaleChangeRate );
							buttonPack.get ( activeButtonSelectionIndex ).increaseScale ( );
						}
				}
				//establish button visual response action listener component
				public void establishButtonVisualResponseActionListenerComponent2 ( boolean entityDisplayEnquiry, int activeButtonSelectionIndex, UNICODE_Button [ ] buttonPack, double scaleChangeRate )
				{
					if ( entityDisplayEnquiry )
						if ( buttonPack [ activeButtonSelectionIndex ].getScale ( ) <= buttonPack [ activeButtonSelectionIndex ].getScaleMaximum ( ) )
						{
							buttonPack [ activeButtonSelectionIndex ].setResized ( true );
							buttonPack [ activeButtonSelectionIndex ].setScaleChangeRate ( scaleChangeRate );
							buttonPack [ activeButtonSelectionIndex ].increaseScale ( );
						}
				}
				//establish button visual response timer trigger component
				public void establishButtonVisualResponseTimerTriggerComponent ( boolean entityDisplayEnquiry, int maximumButtonsInEntity, Timer buttonResizingTimer, double maximumButtonScale, int activeButtonSelectionIndex, ArrayList <UNICODE_Button> buttonPack, MouseEvent mEvent )
				{
					if ( entityDisplayEnquiry )
						for ( int buttons = 0; buttons < maximumButtonsInEntity; buttons ++ )
						{
							if ( buttonPack.get ( buttons ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
							{
								buttonResizingTimer.start ( );
								buttonPack.get ( buttons ).setScaleMaximum ( maximumButtonScale );//reset max button scale
								buttonPack.get ( buttons ).setTextureIndex ( 1 ); //switch button texture layer
								//place a break after getting button index
								//otherwise the timer will only be called when
								//the buttons has reached the end of its value in its for loop
								//instead of the value index we need to resize the appropriate button
								activeButtonSelectionIndex = buttons; break;//tell the resize timer which button to resize
							}
							else
							{
								buttonResizingTimer.stop ( ); //stop resize timer
								buttonPack.get ( buttons ).setResized ( false ); //disable resizing
								buttonPack.get ( buttons ).resetScale ( ); //reset scale to original
								buttonPack.get ( buttons ).setTextureIndex ( 0 ); //reset texture index
							}
						}
				}
				
				//establish button visual response timer trigger component for disclose/hider menus
				public void establishButtonVisualResponseTimerTriggerComponentForDiscloseMenu ( UNICODE_HideMechanism hider, Timer buttonResizingTimer, double maximumButtonScale, int activeButtonSelectionIndex, MouseEvent mEvent )
				{
					if ( hider.getDiscloseRunSessionEnquiry ( ) )
					{
						for ( int buttons = 0; buttons < hider.getMaxButtons ( ); buttons ++ )
						{
							if ( hider.getDiscloseButtons ( ) [ buttons ].contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
							{
								buttonResizingTimer.start ( );
								hider.getDiscloseButtons ( ) [ buttons ].setScaleMaximum ( maximumButtonScale );//reset max button scale
								hider.getDiscloseButtons ( ) [ buttons ].setTextureIndex ( 1 ); //switch button texture layer
								hider.setSurfaceContactEnquiry ( true );
								activeButtonSelectionIndex = buttons; break;
								//place a break after getting button index
								//otherwise the timer will only be called when
								//the buttons has reached the end of its value in its for loop
								//instead of the value index we need to resize the appropriate button
							}
							else
							{
								hider.setSurfaceContactEnquiry ( false );
								buttonResizingTimer.stop ( ); //stop resize timer
								hider.getDiscloseButtons ( ) [ buttons ].setResized ( false ); //disable resizing
								hider.getDiscloseButtons ( ) [ buttons ].resetScale ( ); //reset scale to original
								hider.getDiscloseButtons ( ) [ buttons ].setTextureIndex ( 0 ); //reset texture index       
							}
						}
					}
				}
				
	//ROTATION ANIMATION
    //the menu inner bridge param is a minimum value for inner axis, which enables that menu axis' first rotation process stops.
								//SAMPLE INPUR : menu,          1200,            824,             2.2f,          0.756f,           0.3f,                "clockwise",    timer
    public void performAxisAnimation ( UNICODE_Menu menu, int axisWidth, int axisHeight, float out_rmin, float out_rangle, float out_cycrate, String animationDirection, Timer ROTATION_ANIMATION_TIMER )
    {
        menu.incRotationCycles ( out_cycrate );
        //spin menu axis only
        if ( menu.getRotationCycles ( ) < out_rmin )  //prevent timer from incrementing menu axis once more
        {
            menu.setAxisWidth ( axisWidth );
            menu.setAxisHeight ( axisHeight );
            menu.incRotationAngle ( out_rangle );
            menu.setRotationDirection ( animationDirection );
            menu.setRotation ( true );
        }
        else
        {
            //spin inner axis only, only after menu is complete.
            menu.setRotation ( false ); //kill menu axis rotation
			ROTATION_ANIMATION_TIMER.stop ( );
			menu.resetRotationCycles ( );
        }
	}                 
		//axis animation without timer
    public void performAxisAnimation ( UNICODE_Menu menu, int axisWidth, int axisHeight, float out_rangle, float out_cycrate, String animationDirection )
    {
        menu.incRotationCycles ( out_cycrate );
        //spin menu axis only
		menu.setAxisWidth ( axisWidth );
		menu.setAxisHeight ( axisHeight );
		menu.incRotationAngle ( out_rangle );
		menu.setRotationDirection ( animationDirection );
		menu.setRotation ( true );
	}      			
}                
