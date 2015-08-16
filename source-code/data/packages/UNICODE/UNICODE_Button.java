package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.AffineTransform;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Rectangle;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Point;
import java.awt.TexturePaint;
import java.util.Scanner;

public class UNICODE_Button
{
    //attibutes
        //establish requirements for all shapes
            //establish polygon - user specified container
            private Polygon polygon;
            //establish shape - all
            private Shape shape, outline;
            //establish orientation ( for all button types )
            private int xCoord, yCoord;
            //establish dimension ( for all button types )
            private int dimension_width, dimension_height;
            //establish shape type
            private String type;

            
        //establish requirements for primitive shapes ( primitive to this class )
            //establish archive of primitive shape names
            private String [ ] shape_archive = 
                                                {
                                                    "r", // rectangle
                                                    "e", // ellipse
                                                    "rr", //round rectangle 
                                                    "rsr", //right slanting rectangle
                                                    "lsr" //left slanting rectangle
                                                };
            //establish arc dimensions ( for ellipse - shaped buttons )
            private int arc_height, arc_length;                
            //establish standard polygon : slanted rectangle
                //establish string to control direction of slanting
                private String slant_direction;
                //establish lean factor ( how much the rectangle will lean )
                private int slant_factor;
                //establish points 
                    //define x points
                    int [ ] x_points_sr = new int [ 4 ];
                    //define y points
                    int [ ] y_points_sr = new int [ 4 ];
                //establish the shape, pasing the points array as params, and the number of corners as last param
                private Polygon slanted_rectangle;
                
            //establish standard polygon : triangle
                //establish points 
                    //establish x points
                    int [ ] x_points_t = new int [ 3 ];
                    //establish y points
                    int [ ] y_points_t = new int [ 3 ];
                    //establish sides
                    int sidea_t, sideb_t, sidec_t;
                    
                //establish the shape, pasing the points array as params, and the number of corners as last param
                private Polygon triangle;
                
        //mouse listener mechanism requirments 
        //in texture_pack, 0:default, 1:clicked, 2:hovered over
        private String [ ] texture_pack = new String [ 3 ];
        private int texture_index; 
		private String texture_directory = null;
 
        //establish resizing requirements
            //establish scale
            private double scale = 1.2, original_scale = 1.2;
            //establish scale limits
            private double scale_maximum = 1.2, scale_minimum = 0.1;
            //resize boolean controller
            private boolean resize = false;
            //old transformation data
            private AffineTransform old_affine_transformation;
            //establish scale change rate
            private double scale_change_rate = 0.1;
            //establish scale rectangle requirement
                //establish positional rectangle to guide scailing
                private Rectangle scale_rectangle;
                //establish central scale coordinate values
                private double scale_center_x, scale_center_y;
                //establish scale translation coordinates
                private double scale_translation_x, scale_translation_y;
                //establish button axix alginment refinement variables
                private double pane_value_divisor = 60, center_scale_multiple_divisor = 5;
       //establish visibility 
       private boolean visibility = false;
       //estabablish texture paint bounds
       private Rectangle texture_shape_bounds = null;
	   
	   //texture paint creator 
	   private UNICODE_Structure_TexturePaint tpc = null;
       
    //access misc methods
    public UNICODE_Button ( )
    {
    }

    //ellispe2d or regular rectangle - shaped button constructor
    public UNICODE_Button ( String shape_type, int _xCoord, int _yCoord, int _dimension_width, int _dimension_height, String [ ] _texture_pack, int _texture_index, boolean _visibility, double growth, String _texture_directory )
    {
        //initialise orientation
        xCoord = _xCoord;
        yCoord = _yCoord;
        
        //initialise dimension
        dimension_width = _dimension_width;
        dimension_height = _dimension_height;
        
        //initialise visibility;
        visibility = _visibility;
		
		//initialise texture directory
		texture_directory = _texture_directory;
        
        //initilaise shape type
        type = shape_type;
        
        //establish texture stuff
            //establish archive
            texture_pack = _texture_pack;
            //establish index
            texture_index = _texture_index;
        
        //initialise shape ( ellipse2d or regular rectangle? )
        if ( type.equals ( shape_archive [ 1 ] ) )
        {
            shape = new Ellipse2D.Double ( getX ( ), getY ( ), getWidth ( ), getHeight ( ) ); 
            outline = new Ellipse2D.Double ( getX ( ) - ( growth / 2 ), getY ( ) - ( growth / 2 ), getWidth ( ) + growth, getHeight ( ) + growth );     
        }
           
        else if ( type.equals ( shape_archive [ 0 ] ) )
        {
            shape = new Rectangle.Double ( getX ( ), getY ( ), getWidth ( ), getHeight ( ) );   
            outline = new Rectangle.Double ( getX ( ), getY ( ), getWidth ( ) + growth, getHeight ( ) + growth ); 
        }
    }
    
    //round rectangle2d - shaped button constructor
    public UNICODE_Button ( int _xCoord, int _yCoord, int _dimension_width, int _dimension_height, int arc_altitude, int arc_span, String [ ] _texture_pack, int _texture_index, boolean _visibility, double growth, String _texture_directory )
    {
        //initialise orientation
        xCoord = _xCoord;
        yCoord = _yCoord;        
        
		//initialise visibility;
        visibility = _visibility;
		
        //initialise dimension
        dimension_width = _dimension_width;
        dimension_height = _dimension_height;
        
        //initialise arc dimensions
        arc_height = arc_altitude;
        arc_length = arc_span; 
		
		//initialise texture directory
		texture_directory = _texture_directory;
        
        //establish texture stuff
            //establish archive
            texture_pack = _texture_pack;
            //establish index
            texture_index = _texture_index;
        
        //initialise shape ( round rectangle only )
        shape = new RoundRectangle2D.Double ( getX ( ), getY ( ), getWidth ( ) + growth, getHeight ( ) + growth, getArcdimension_height( ), getArcLength ( ) );
		outline = new RoundRectangle2D.Double ( getX ( ), getY ( ), getWidth ( ) + growth, getHeight ( ) + growth, getArcdimension_height( ), getArcLength ( ) );
    }

    //slanted rectangle - shaped button constructor
    public UNICODE_Button ( String shape_type, int slant_amount, int _xCoord, int _yCoord, int _dimension_width, int _dimension_height, String [ ] _texture_pack, int _texture_index, boolean _visibility, double growth, String _texture_directory )
    {
        //initialise orientation
        xCoord = _xCoord;
        yCoord = _yCoord;
        
        //initialise dimension
        dimension_width = _dimension_width;
        dimension_height = _dimension_height;
        
        //initialise visibility;
        visibility = _visibility;
		
		//initialise texture directory
		texture_directory = _texture_directory;
        
        //initilaise shape type
        type = shape_type;
        
        //intialise slant 
        slant_factor = slant_amount;

        //establish texture stuff
            //establish archive
            texture_pack = _texture_pack;
            //establish index
            texture_index = _texture_index;
            
        //manage how the rectangle slants ; assigning a proper lean factor
        if ( shape_type.equals ( shape_archive [ 4 ] ) ) //left slanted rectangle
            slant_factor = - ( slant_factor );
        else if ( shape_type.equals ( shape_archive [ 3 ] ) ) //right slanted rectangle
            slant_factor = slant_factor;     
                
        //degfine points
            //establish x points
            x_points_sr [ 0 ] = ( /*topLeftX*/ getX ( ) + slant_factor );
            x_points_sr [ 1 ] = ( /*topRightX*/ getX ( ) + slant_factor + getWidth ( ) );
            x_points_sr [ 2 ] = ( /*bottomRightX*/ getX ( ) + getWidth ( ) );
            x_points_sr [ 3 ] = ( /*bottomLeftX*/ getX ( ) );

            //establish y points
            y_points_sr [ 0 ] = ( /*topLeftY*/ getY ( ) );
            y_points_sr [ 1 ] = ( /*topRightY*/ getY ( ) );
            y_points_sr [ 2 ] = ( /*bottomRightY*/ getY ( ) + getHeight ( ) );
            y_points_sr [ 3 ] = ( /*bottomLeftY*/ getY ( ) + getHeight ( ) );
            
        //initialise slanted rectangle
        slanted_rectangle = new Polygon ( x_points_sr, y_points_sr, 4 );
            
        //define shape by slanted rectangle polygon
        shape = slanted_rectangle;  
    }
    
    //triangle - shaped button constructor
    public UNICODE_Button ( int _xCoord, int _yCoord, int _sidea_t, int _sideb_t, int _sidec_t, String [ ] _texture_pack, int _texture_index, boolean _visibility, double growth, String _texture_directory )
    {
        //initialise orientation
        xCoord = _xCoord;
        yCoord = _yCoord;
        
        //initialise dimension
        sidea_t = _sidea_t;
        sideb_t = _sideb_t;
        sidec_t = _sidec_t;
        
        //initialise visibility;
        visibility = _visibility;
		
		//initialise texture directory
		texture_directory = _texture_directory;
        
        //establish texture stuff
            //establish archive
            texture_pack = _texture_pack;
            //establish index
            texture_index = _texture_index;
            
        //establish points
            //define x points
            x_points_t [ 0 ] = ( /*top*/ getX ( ) );
            x_points_t [ 1 ] = ( /*bottomLeftX*/ getX ( ) - getSideC_T ( ) );
            x_points_t [ 2 ] = ( /*bottomRightX*/ getX ( ) + getSideC_T ( ) );

            //establish y points
            y_points_t [ 0 ] = ( /*topY*/ getY ( ) );
            y_points_t [ 1 ] = ( /*bottomRightY*/ getY ( ) + getSideA_T ( ) );
            y_points_t [ 2 ] = ( /*bottomRightY*/ getY ( ) + getSideB_T ( ) );
            
        //initialise triangle polygon
        triangle = new Polygon ( x_points_t, y_points_t, 3 );
            
        //define shape by slanted rectangle polygon
        shape = triangle;  
    } 
    
    
    //methods
        //accessors
		public String getTextureDirectory ( )
		{
			return texture_directory;
		}
        public Shape getShape ( )
        {
            return shape;
        }
        public Shape getOutline ( )
        {
            return outline;
        }
        public int getX ( )
        {
            return xCoord;
        }
        public int getY ( )
        {
            return yCoord;
        }
        public int getWidth ( )
        {
            return dimension_width;
        }
        public int getHeight( )
        {
            return dimension_height;
        }
        public boolean getVisibility ( )
        {
            return visibility;
        }
        public int getSideA_T ( )
        {
            return sidea_t;
        }
        public int getSideB_T ( )
        {
            return sideb_t;
        }
        public int getSideC_T ( )
        {
            return sidec_t;
        }
        public double getScale ( )
        {
            return scale;
        }
        public double getOriginalScale ( )
        {
            return original_scale;
        }
        public double getScaleMaximum ( ) 
        {
            return scale_maximum;
        }
        public double getScaleMinimum ( ) 
        {
            return scale_minimum;
        }
        public double getScaleChangeRate ( )
        {
            return scale_change_rate;
        }
        public double getTweakOneDivisor ( )
        {
            return pane_value_divisor;
        }
        public double getTweakTwoDivisor ( )
        {
            return center_scale_multiple_divisor;
        }
        public Rectangle getTextureShapeBounds ( )
        {
            return texture_shape_bounds;
        }

        public AffineTransform getOldAffineTransform ( )
        {
            return old_affine_transformation;
        }
        public int getArcdimension_height( )
        {
            return arc_height;
        }
        public int getArcLength ( )
        {
            return arc_length;
        }      
        public String [ ] getTexturePack ( )
        {
            return texture_pack;
        }
        public int getTextureIndex ( )
        {
            return texture_index;
        }
        public boolean isResized ( )
        {
            return resize;
        }
        //mutators
            //dynamic
            public void incTextureIndex ( )
            {
                texture_index ++;
            }
            public void decTextureIndex ( )
            {
                texture_index --;
            }       
            public void increaseScale ( )
            {
                scale += scale_change_rate;
            }     
            public void decreaseScale ( )
            {
                scale -= scale_change_rate;
            }     
            public void incTweakOneDivisor ( double value )
            {
                pane_value_divisor += value;
            }
            public void incTweakTwoDivisor ( double value )
            {
                center_scale_multiple_divisor += value;
            }
            public void decTweakOneDivisor ( double value )
            {
                pane_value_divisor -= value;
            }
            public void decTweakTwoDivisor ( double value )
            {
                center_scale_multiple_divisor -= value;
            }           
            //static
			public void setTextureDirectory ( String value )
			{
				texture_directory = value;
			}
            public void setVisibility ( boolean value )
            {
                visibility = value;
            }
            public void setTexturePack ( String [ ] value )
            {
                texture_pack = value;
            }
            public void setTextureIndex ( int value )
            {
                texture_index = value;
            }
            public void setScaleMaximum ( double value ) 
            {
                scale_maximum = value;
            }
            public void setScaleMinimum ( double value ) 
            {
                scale_minimum = value;
            }
            public void setOriginalScale ( double value )
            {
                original_scale = value;
            }
            public void setResized ( boolean value )
            {
                resize = value;
            }
            public void setScaleChangeRate ( double value )
            {
                scale_change_rate = value;
            }     
            public void setScale ( double value )
            {
                scale = value;
            }
            public void resetScale ( )
            {
                scale = original_scale;
            }
            public void setTweakOneDivisor ( double value )
            {
                pane_value_divisor = value;
            }
            public void setTweakTwoDivisor ( double value )
            {
                center_scale_multiple_divisor = value;
            }
           
       
        
            
        //miscallanues
            //establish old affine transform ( used in draw function )
            public void establishOldAffineTransform ( Graphics2D graphics2d )
            {
                old_affine_transformation = graphics2d.getTransform ( );
            }
            
            //restore old transform so as to not affect other un-selected buttons or 
            //graphics 2d component or object currently on screem
            //only selected items must be scaled, this ensures this.
            public void restoreOldTransform ( Graphics2D graphics2d )
            {
                graphics2d.setTransform ( getOldAffineTransform ( ) );
            }
        
            
            //draw button
            public void draw ( Graphics2D graphics2d, Color outline_colour, JPanel pane )
            {
                if ( getVisibility ( ) )
                {
                    //establish rectangle that matches shape orientation, and some essense of
                    //shape dimension. These bounds are used in resizing process, and also
                    //passed to texture paint creator in order to center texture paint generated,
                    //on the shape drawn.
                    texture_shape_bounds = new Rectangle ( getX ( ), getY ( ), getWidth ( ), getHeight ( ) );
                    ////////////////////////////////////////////
                    //BEGIN BUSHMAN BUTTON RESIZING
                    ////////////////////////////////////////////
                    //establish resizing requirements
                            //establish scale central coordinates
                            scale_center_x = texture_shape_bounds.getBounds ( ).getCenterX ( );
                            scale_center_y = texture_shape_bounds.getBounds ( ).getCenterY ( );
                        //establish saved affine transformation
                        establishOldAffineTransform ( graphics2d );
                        
                        //establish translation coordinates
                        scale_translation_x = ( pane.getWidth ( ) / pane_value_divisor ) - ( scale_center_x * scale ) / center_scale_multiple_divisor;
                        scale_translation_y = ( pane.getHeight ( ) / pane_value_divisor ) - ( scale_center_y * scale ) / center_scale_multiple_divisor;
                        
                        //apply scaling when resize bool is set true ( when a button is hovered over by mouse )
                        if ( resize )
                        {   
                            //adjust shape location while scaling
                            graphics2d.translate ( scale_translation_x, scale_translation_y );
                            //scale while adjusting shape location
                            graphics2d.scale ( getScale ( ), getScale ( ) );
                        }
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
                    //END BUSHMAN BUTTON RESIZING - the restoreOldTransform at end of this function ensures that 
                    //only the button that is currently selected will be scalled.
                    ////////////////////////////////////////////////////////////////////////////////////////////////////
           
                    
                    ////////////////////////////////////////////
                    //BEGIN BUSHMAN BUTTON SHAPE DRAWING
                    ////////////////////////////////////////////
                        //establish texture paint creator
                        tpc = new UNICODE_Structure_TexturePaint ( getTextureDirectory ( ), getTexturePack ( ) [ getTextureIndex ( ) ] );
                        //once paint is set, this tells graphics2d component to fill any java componen
                        //with that texture paint
                        graphics2d.setPaint ( tpc.makeTexturePaint ( texture_shape_bounds ) );
                        graphics2d.fill ( getShape ( ) );
                        graphics2d.setColor ( outline_colour );
                        graphics2d.draw ( getOutline ( ) );
                    ////////////////////////////////////////////
                    //END BUSHMAN BUTTON SHAPE DRAWING
                    ////////////////////////////////////////////
                    
                    
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //RESET PAINT COMPONENT GRAPHICS2D TRANSFORM, TO PREVENT RESHAPING of non-selected buttons, or other 
                    //objects and components currently in the graphics2d context
                    ////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //reset stransformation so that other components are unaffected
                    restoreOldTransform ( graphics2d );
                }
            }
            
            //ctl: create texture layers
            //PROTOTYPE 1: define texture layers ( fixed amount of 3 for now : hovered over, clicked, default. )
            public String [ ] cTL ( String button_state_string )
            {    
                //establish local array
                String [ ] array = new String [ 3 ];  
                
                //create scanner to scan user string of image sources 
                Scanner scanner = new Scanner ( button_state_string );
                
                //fill array with these image names
                for ( int layers = 0; layers < array.length; layers ++ )
                    array [ layers ] += scanner.next ( );
                    
                return array;
            }
            
            
            //cCTL: create clean texture layers
            //removes null characters from texture layer array above
            public String [ ] cCTL ( String [ ] texturePack )
            {
                String [ ] array = new String [ texturePack.length ];
    
                for ( int layers = 0; layers < texturePack.length; layers ++ )
                    array [ layers ] = texturePack [ layers ].replace ( "null", "" );
                return array;
            }
            
            //check for collision between self and mouse
            public boolean contains ( int mouseX, int mouseY )
            {
                return getShape ( ).contains ( mouseX, mouseY );
            }
}
