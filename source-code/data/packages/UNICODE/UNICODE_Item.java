package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:--: Title: Bushman Gui Kit Item
//:---------------------------------------------:
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Color;
import java.awt.Rectangle;


public class UNICODE_Item extends JPanel
{
    //attributes
        //orientation
        private int xOri, yOri;
        //Image setup
        private ImageIcon iicon;
        private Image img;
        private String imgStr;
        //dimension
        private int wDimen, hDimen; //dimensions are needed when image is dyanamic.        
        //private int imgType; //type zero = static images, type 1 = dynamic and chaning images. Dyanmic images can have frames.
        private int frmX, frmY; //given that image type is dynamic, frames are used. 
        private int frmLoopLim; //given that image type is dynamic, frames are used; limit is needed in the case of movement, where frames must repeat.
        //item usage boolean
        private boolean itemUsageBoolean;

    //constructor 1 - image type 1.
    public UNICODE_Item ( int xCoord, int yCoord, String imageStream, boolean itemUsageFlag )
    {
        xOri = xCoord;
        yOri = yCoord;
        imgStr = imageStream;
        createImage ( );
        repaint ( );
    }
     
    //constructor 2 - image type 2
    public UNICODE_Item ( int xCoord, int yCoord, int width, int height, int frameX, int frameY, int frameLoopLimit, String imageStream, boolean itemUsageFlag )
    {
        xOri = xCoord;
        yOri = yCoord;
        wDimen = width;
        hDimen = height;
        frmX = frameX;
        frmY = frameY;
        frmLoopLim = frmLoopLim;
        imgStr = imageStream;
        itemUsageBoolean = itemUsageFlag;
        createImage ( );
    }    
    
    //constructor 3 
    public UNICODE_Item ( )
    {

    }
    
    //constructor 4
    public UNICODE_Item ( int xCoord, int yCoord, int width, int height, Graphics graphics )
    {
        xOri = xCoord;
        yOri = yCoord;
        wDimen = width;
        hDimen = height;
    }       
    //constructor 4 
    public UNICODE_Item ( int xCoord, int yCoord, int width, int height )
    {
        xOri = xCoord;
        yOri = yCoord;
        wDimen = width;
        hDimen = height;
    }               
    //functions

        //get collision rectangle
        public Rectangle getColRect ( )
        {
            return new Rectangle ( getX ( ), getY ( ), getWidth ( ), getHeight ( ) );
        }
        //get collision rectangle inner (gets a slightly smaller col rect than that derived from
        //the entire dimension and orientation of an item. ( improves colision )
        public Rectangle getColRect ( int offsetFactor, int dimenFactor )
        {
            return new Rectangle ( getX ( ) + offsetFactor, getY ( ) + offsetFactor, getWidth ( )/dimenFactor, getHeight ( )/dimenFactor );
        }        
        public void showColRect ( Graphics graphics, Color rectCol )
        {
            graphics.setColor ( rectCol );
            graphics.fillRect ( getX ( ), getY ( ), getWidth ( ), getHeight ( ) );
        }    
        //corelates with get col rect inner
        public void showColRect ( Graphics graphics, Color rectCol, int offsetFactor, int dimenFactor )
        {
            graphics.setColor ( rectCol );
            graphics.fillRect ( getX ( ) + offsetFactor, getY ( ) + offsetFactor, getWidth ( )/dimenFactor, getHeight ( )/dimenFactor  );
        }      
        //get custom col rect
        public Rectangle getColRect ( int xOffsetFactor, int yOffsetFactor, int width, int height )
        {
            return new Rectangle ( getX ( ) + xOffsetFactor, getY ( ) + yOffsetFactor, width, height );
        }             
        //show custom col rect
        public void showColRect ( Graphics graphics, Color rectCol, int xOffsetFactor, int yOffsetFactor, int width, int height )
        {
            graphics.setColor ( rectCol );
            graphics.fillRect ( getX ( ) + xOffsetFactor, getY ( ) + yOffsetFactor, width, height );
        }             
        //create image
        public void createImage ( )
        {
            iicon = new ImageIcon ( imgStr );
            img = iicon.getImage ( );
            img = createImage ( img.getSource ( ) );
        }
        
        //draw string
        public void drawStr ( Graphics graphics, String string )
        {
            graphics.drawString ( string, xOri, yOri );
        }
        
        //draw entity -- static image
        public void drawStatic ( Graphics graphics, JPanel FoodFrenzy_GP )
        {
            //draw image statically, that is grab all horizontal and vertical bits at once
            graphics.drawImage ( getImg ( ), xOri, yOri, FoodFrenzy_GP );
        }
        
        //draw entity -- "dynamic" image
        public void drawDynamic ( Graphics graphics, JPanel FoodFrenzy_GP )
        {
            //draw image dynamically, grabbing horizontal and vertical bi
            graphics.drawImage ( getImg ( ), xOri, yOri, xOri + wDimen, yOri + hDimen, frmX, frmY, frmX + wDimen, frmY + hDimen, FoodFrenzy_GP );
        }     
      
        
    //methods
        //accessors     
            
            //ORIENTATION
            public int getX ( )
            {
                return xOri;
            }     
            public int getY ( )
            {
                return yOri;
            }              
            //DIMENSION
            public int getWidth ( )
            {
                return wDimen;
            }     
            public int getHeight ( )
            {
                return hDimen;
            }                  
            //FRAME
            public int getFX ( )
            {
                return frmX;
            }     
            public int getFY ( )
            {
                return frmY;
            }        
            public int getFLL ( )
            {
                return frmLoopLim;
            }
            //ITEM USAGE
            public boolean getVisibility ( )
            {
                return itemUsageBoolean;
            }            
            //IMAGE
            public Image getImg ( )
            {
                return img;
            }
            
        //static mutators ( value setters )
            //ORIENTATION
            public void setX ( int value )
            {
                xOri = value;
            }     
            //ORIENTATION
            public void setY ( int value )
            {
                yOri = value;
            }                 
            public void getY ( int value )
            {
                yOri = value;
            }              
            //DIMENSION
            public void getWidth ( int value )
            {
                wDimen = value;
            }     
            public void getHeight ( int value )
            {
                hDimen = value;
            }                  
            //FRAME
            public void setFX ( int value )
            {
                frmX = value;
            }     
            public void setFY ( int value )
            {
                frmY = value;
            }                  
            //ITEM USAGE
            public void setVisible ( boolean value )
            {
                itemUsageBoolean = value;
            }    
            //FRAME LOOP LIMIT
            public void setFLL ( int value )
            {
                frmLoopLim = value;
            }                   
        //dynamic mutators ( value incrementors )
            //ORIENTATION
            public void incX ( int value )
            {
                xOri += value;
            }     
            public void incY ( int value )
            {
                yOri += value;
            }                    
            //FRAME
            public void incFX ( )
            {
                frmX += getWidth ( );
            }     
            public void incFY ( )
            {
                frmX += getHeight ( );
            }                  
        //dynamic mutators ( value decrementors )
            //ORIENTATION
            public void decX ( int value )
            {
                xOri -= value;
            }     
            public void decY ( int value )
            {
                yOri -= value;
            }                    
            //FRAME
            public void decFX ( )
            {
                frmX -= getWidth ( );
            }     
            public void decFY ( )
            {
                frmX -= getHeight ( );
            }  

      //misc functions
            public void animate ( int amount, int frameY, int frameMin, int frameMax, String direction, boolean allowFrameUpdate )
            {
                setFY ( frameY );
                    
                if ( direction.equals ( "east" ) )
                    incX ( amount );
                if ( direction.equals ( "west" ) )
                    decX ( amount );
                if ( direction.equals ( "north" ) )
                    decY ( amount );
                if ( direction.equals ( "south" ) )
                    incY ( amount );
                if ( direction.equals ( "south east" ) )
                {
                    incX ( amount );
                    incY ( amount );
                }
                if ( direction.equals ( "north east" ) )
                {
                    incX ( amount );
                    decY ( amount );
                }
                if ( direction.equals ( "south west" ) )
                {
                    decX ( amount );
                    incY ( amount );
                }
                if ( direction.equals ( "north west" ) )
                {
                    decX ( amount );
                    decY ( amount );
                }                
                
                if ( allowFrameUpdate )
                {
                    if ( ( getFX ( ) > frameMin ) && ( getFX ( ) <= frameMax ) )
                        setFX ( frameMin );
                    else
                        incFX ( );
                }
            }
}
