//Author(s): Jordan Micah Bennett
package data.packages.UNICODE;
import java.util.Random;
import java.io.File;

public class UNICODE_RandomMusicSelector
{
    //attributes
    private String musicStream = "";

    
    public UNICODE_RandomMusicSelector ( String _musicStream )
    {
        musicStream = _musicStream;
    }
    
    //methods
    public String getRandomSong ( )
    {
        String value = "";
        
        Random randomizer = new Random ( );
        
        String [ ] musicList = new File ( musicStream ).list ( ); //get list of music files in directory
		
		int randomSongIndex = randomizer.nextInt ( musicList.length );
        
        value = musicStream + musicList [ randomSongIndex ]; //select random song name, and create a usable directory
        
        return value;
    }
}
