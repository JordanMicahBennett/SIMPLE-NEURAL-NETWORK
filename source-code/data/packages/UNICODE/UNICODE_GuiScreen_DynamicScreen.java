package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
//:------------------------------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:--: Title: Bushman Gui Kit Gui Screen : Dynamic Screening (never scrolls)
//:------------------------------------------------------------------:
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class UNICODE_GuiScreen_DynamicScreen extends UNICODE_GuiScreen
{    
    //establish hover mode boolean
    boolean hover_mode_bool;
    //establish glide scroll booleans
    boolean sGlide_right_bool, sGlide_left_bool;
    
    //constructor 
    public UNICODE_GuiScreen_DynamicScreen ( int xCoord, int yCoord, int width, int height, int frameX, int frameY, int frameLoopLimit, String imageStream, boolean itemUsageFlag, int screenMultiplier, boolean enable_hover_mode, boolean enable_sLeftGlide, boolean enable_sRightGlide )
    {
        super ( xCoord, yCoord, width, height, frameX, frameY, frameLoopLimit, imageStream, itemUsageFlag, screenMultiplier );
        hover_mode_bool = enable_hover_mode;
        sGlide_right_bool = enable_sRightGlide;
        sGlide_left_bool = enable_sLeftGlide;
    }    
   
    //methods
            //manage screen updates
            public void manageScreenUpdate ( String direction, String audioName, UNICODE_AudioPlayer ff_ap )
            {
                
                //NB. the multiplier is also and indicator as to how many items are in a menu!!!
                if ( direction.equals ( "next" ) ) //if user has toggle button intended for going to next item...
                    if ( getVisibility ( ) )
                    {
                        //setFLL ( getWidth ( ) * getScreenMultiplier ( ) ); //then set frame loop limit, wrt multiplier, which will controll how many screens are looped through/
                        updateScreen ( audioName, "next", ff_ap );  

                        //if current menu item has surpassed upper bounds ( max menu item ),
                        //be sure to set frame as such, and set current menu item index to min ( always 1 )
                        if ( getCurrentMenuItem ( ) >= getScreenMultiplier ( ) ) 
                        {
                           setFX ( 0 ); //set frame x corod to first item
                           setCurrentMenuItem ( 1 );     
                        }
                        
                        //else if current menu item is still increasable, that is less than
                        //its max menu items ( varys: based on no. of screens per menu )
                        else if ( getCurrentMenuItem ( ) <= getScreenMultiplier ( ) ) 
                        {
                            incCurrentMenuItem ( );
                        }   
                    }
            
                if ( direction.equals ( "previous" ) ) //if user has toggle button intended for going to next item...
                    if ( getVisibility ( ) )
                    {
                        //setFLL ( getWidth ( ) * getScreenMultiplier ( ) ); //then set frame loop limit, wrt multiplier, which will controll how many screens are looped through/
                        
                        updateScreen ( audioName, "previous", ff_ap );  
                        
                        //if current menu item has underpassed lower bounds ( min menu item = 1 ),
                        //be sure to set frame as such, and set current menu item index to max 
                        //( varys: based on no. of screens per menu )
                        if ( getCurrentMenuItem ( ) <= 1 ) 
                        {
                           setFX ( getFLL ( ) - getWidth ( ) ); //set frame x coord to last item
                           setCurrentMenuItem ( getScreenMultiplier ( ) );     
                        }
                        
                        //else if menu items is still decreasable, decrease it
                        else if ( getCurrentMenuItem ( ) > 1 ) 
                        {
                            decCurrentMenuItem ( );
                        }                          
                    }             
            }
 
            //manage screen updates
            public void manageScreenUpdate ( String direction, String [ ] audioNames, UNICODE_AudioPlayer ff_ap )
            {
                
                //NB. the multiplier is also and indicator as to how many items are in a menu!!!
                if ( direction.equals ( "next" ) ) //if user has toggle button intended for going to next item...
                    if ( getVisibility ( ) )
                    {
                        setFLL ( getWidth ( ) * getScreenMultiplier ( ) ); //then set frame loop limit, wrt multiplier, which will controll how many screens are looped through/
                        
                        if ( getFX ( ) >= ( getFLL ( ) - getWidth ( ) ) ) //then if frame x coord has reached frame loop limit, set frame to zero, (user is scrolling uo)
                           setFX ( ( - getWidth ( ) ) ); //set frame x corod to - item width
                           
                        if ( getFX ( ) < getFLL ( ) ) //or if frame x coord is less than frame loop lim, then user may update screen
                        {
                            updateScreen ( audioNames, "next", ff_ap );  
                            if ( getCurrentMenuItem ( ) >= getScreenMultiplier ( ) )
                                setCurrentMenuItem ( 0 );                            
                            incCurrentMenuItem ( );
                        }
                    }
                       
                if ( direction.equals ( "previous" ) ) //if user has toggle button intended for going to next item...
                    if ( getVisibility ( ) )
                    {
                        setFLL ( getWidth ( ) * getScreenMultiplier ( ) ); //then set frame loop limit, wrt multiplier, which will controll how many screens are looped through/
                        
                        if ( getFX ( ) <= 0 ) //then if frame x coord has reached frame x minimum
                           setFX ( getFLL ( ) ); //set frame x corod to last item
                           
                        if ( getFX ( ) > 0 ) //or if frame x coord is valid and still greater than minimum, 
                        {
                            updateScreen ( audioNames, "previous", ff_ap );  
                            if ( getCurrentMenuItem ( ) <= 0 )
                                setCurrentMenuItem ( getScreenMultiplier ( ) );                            
                            decCurrentMenuItem ( );
                        }
                    }             
            }
            
            //manage screen updates ( for a volume adjuster )
            public void manageScreenUpdateV ( String direction, String audioName )
            {
                //NB. the multiplier is also and indicator as to how many items are in a menu!!!
                
                if ( direction.equals ( "next" ) ) //if user has toggle button inteded for going to next item...
                    if ( getVisibility ( ) )
                        setFX ( getWidth ( ) );
           
                if ( direction.equals ( "previous" ) )
                    if ( getVisibility ( ) )
                        setFX ( 0 );                
            }
            
            //accessors
                //get hover mode
                public boolean getHvBool ( )
                {
                    return hover_mode_bool;
                }
                //get glide scroll right boolean
                public boolean getGSRBool ( )
                {
                    return sGlide_right_bool;
                }
                //get glide scroll left boolean
                public boolean getGSLBool ( )
                {
                    return sGlide_left_bool;
                }
           //mutators
                //set hover mode 
                public void setHvBool ( boolean value )
                {
                    hover_mode_bool = value;
                }
                //set glide scroll right boolean
                public void setGSRBool ( boolean value )
                {
                    sGlide_right_bool = value;
                }
                //set glide scroll left boolean
                public void setGSLBool ( boolean value )
                {
                    sGlide_left_bool = value;
                }
}
