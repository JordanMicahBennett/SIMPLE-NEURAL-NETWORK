package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:--: Title: Bushman Gui Kit Gui Screen
//:---------------------------------------------:
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class UNICODE_GuiScreen extends UNICODE_Item
{
    //attributes
    int currentMenuItem;
    int screenMultiplier;
    
    //constructor 1
    public UNICODE_GuiScreen ( int xCoord, int yCoord, int width, int height, int frameX, int frameY, int frameLoopLimit, String imageStream, boolean itemUsageFlag, int scrMult  )
    {
        super ( xCoord, yCoord, width, height, frameX, frameY, frameLoopLimit, imageStream, itemUsageFlag );
        screenMultiplier = scrMult;
        currentMenuItem = -1;
    }    
   
    //methods
    //methods
        //accessors
            //get screen multiplier
            public int getScreenMultiplier ( )
            {
                return screenMultiplier;
            }
         
            //get current menu item
            public int getCurrentMenuItem ( )
            {
                return currentMenuItem;
            }
            
        //static mutators
            //set current menu item
            public void setCurrentMenuItem ( int value )
            {
                currentMenuItem = value;
            }   
       
            
        //dynamic mutators
            //increment current menu item
            public void incCurrentMenuItem ( )
            {
                currentMenuItem ++;
            }      
            //decrement current menu item
            public void decCurrentMenuItem ( )
            {
                currentMenuItem --;
            }                         
            
            
        //screen update function supports only playing of one (the same) audio file per screen update
        public void updateScreen ( String audioName, String direction, UNICODE_AudioPlayer ff_ap )
        {
            ff_ap.playAudio ( audioName );
            if ( direction.equals ( "next" ) )
                incFX ( );
            else if ( direction.equals ( "previous" ) )
                decFX ( );            
        }
        
        //screen update function supports playing of unique audio files (different) per screen update
        public void updateScreen ( String [ ] audioPack, String direction, UNICODE_AudioPlayer ff_ap )
        {
            if ( getCurrentMenuItem ( ) < ( screenMultiplier - 1 ) )
                ff_ap.playAudio ( ( String ) audioPack [ getCurrentMenuItem ( ) ] ); //do a little tyep casting/string wrapping...:)
 
            if ( direction.equals ( "next" ) )
                incFX ( );
            else if ( direction.equals ( "previous" ) )
                decFX ( );            

        }       
        
        //makes audio and returns audio pack for use in updateStcreen function type 2.
        public String [ ] makeAudioPack ( String list, int howManyScreens )
        {
            String AudioArrayPack [ ] = new String [ howManyScreens ];
            Scanner listScanner = new Scanner ( list );
            for ( int audios = 0; audios < howManyScreens; audios ++ )
                AudioArrayPack [ audios ] = listScanner.next ( );
            return AudioArrayPack;
        }
}
