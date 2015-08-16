package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:--: Title: Bushman Gui Kit audio player!
//:---------------------------------------------:
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;

public class UNICODE_AudioPlayer 
{
    //attributes
    private float minimumVolume = -80.0f, maximumVolume = 6.0f, volume = maximumVolume;
    private int volBarWidth = 360;
    //constructor 1
    public UNICODE_AudioPlayer ( )
    {
    }    
    
  
    //methods
        //plays audio from default audio file location
        public void playAudio ( String fileName )
        {
            try
            {
                AudioInputStream ais = AudioSystem.getAudioInputStream ( new File ( "data/audios/" + fileName ) );
                Clip clip = AudioSystem.getClip ( );
                clip.open ( ais );
                FloatControl fc = ( FloatControl ) clip.getControl ( FloatControl.Type.MASTER_GAIN );
                fc.setValue ( getVolume ( ) );
                clip.start ( );
            }
            catch ( Exception e )
            {
            }
        }
        
    //methods
        //plays file based on customize stream
        public void playAudioCS ( String fileStream )
        {
            try
            {
                AudioInputStream ais = AudioSystem.getAudioInputStream ( new File ( fileStream ) );
                Clip clip = AudioSystem.getClip ( );
                clip.open ( ais );
                FloatControl fc = ( FloatControl ) clip.getControl ( FloatControl.Type.MASTER_GAIN );
                fc.setValue ( getVolume ( ) );         
                clip.start ( );
            }
            catch ( Exception e )
            {
            }
        }   
        //stops file based on customize stream
        public void stopAudioCS ( String fileStream )
        {
            try
            {
                AudioInputStream ais = AudioSystem.getAudioInputStream ( new File ( fileStream ) );
                Clip clip = AudioSystem.getClip ( );
                clip.open ( ais );
                FloatControl fc = ( FloatControl ) clip.getControl ( FloatControl.Type.MASTER_GAIN );
                fc.setValue ( getVolume ( ) );           
                clip.stop ( );
            }
            catch ( Exception e )
            {
            }
        }   
        
        //get volume
        public float getVolume ( )
        {
            return volume;
        }
        
        
        //set volume
        public void setVolume ( float value ) 
        {
            volume = value;
        }
        //set min volume
        public void setMinVolume ( float value ) 
        {
            minimumVolume = value;
        }
        //set max volume
        public void setMaxVolume ( float value ) 
        {
            maximumVolume = value;
        }        
        //inc volume
        public void incVolume ( float value ) 
        {
            volume += value;
        }
        
        //dec volume
        public void decVolume ( float value ) 
        {
            volume -= value;
        }            
        
        //get minimum volume
        public float getMinVolume ( )
        {
            return minimumVolume;
        }
        
        //get maximum volume
        public float getMaxVolume ( )
        {
            return maximumVolume;
        }  
        
}
