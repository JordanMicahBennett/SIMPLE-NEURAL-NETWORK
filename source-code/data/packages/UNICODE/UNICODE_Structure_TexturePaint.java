package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.image.BufferedImage;
import java.awt.TexturePaint;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class UNICODE_Structure_TexturePaint
{
    //attributes
    private BufferedImage buffered_img = null;
	
	//directory
	private String texture_directory = null, texture_source = null;

    
    public UNICODE_Structure_TexturePaint ( String _texture_directory, String _texture_source )
    {
		//initialise texture directory
		texture_directory = _texture_directory;
		//initialise texture source
		texture_source = _texture_source;	
    }
	
	//methods
		//accessors
			public String getTextureDirectory ( )
			{
				return texture_directory;
			}
			public String getTextureSource ( )
			{
				return texture_source;
			}
		//mutators
			public void setTextureDirectory ( String value )
			{
				texture_directory = value;
			}
			public void setTextureSource ( String value )
			{
				texture_source = value;
			}
		//misc
			//method to create texture paint, with respect to user specified bounds
			public TexturePaint makeTexturePaint ( Rectangle rectangle )
			{
				try
				{
					buffered_img = ImageIO.read ( getClass ( ).getResourceAsStream ( getTextureDirectory ( ) + getTextureSource ( ) ) );
				}
				catch ( Exception error )
				{
				
				}
				
				return new TexturePaint ( buffered_img, rectangle );
			}
			
			//method to create texture paint; let java decide how to bound texture paint on applicable surfaces,
			//based on dimensional data extracted at runtime.
			public TexturePaint makeTexturePaint ( )
			{
				try
				{
					buffered_img = ImageIO.read ( getClass ( ).getResource ( getTextureDirectory ( ) + getTextureSource ( ) ) );
				}
				catch ( Exception error )
				{
					
				}
				
				return new TexturePaint ( buffered_img, new Rectangle ( buffered_img.getWidth ( ), buffered_img.getHeight ( ) ) );
			}  
}
