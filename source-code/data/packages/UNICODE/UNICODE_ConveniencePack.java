package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
//import javax.media.Time;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class UNICODE_ConveniencePack
{
	//attributes
    public UNICODE_ConveniencePack ( )
    {
    }
	
    //misc
		public void hideCursor ( JPanel context )
		{
			ImageIcon imageIcon = new ImageIcon ( "null" );
			Image image = imageIcon.getImage ( );
			image = context.createImage ( image.getSource ( ) );
			
			Cursor blankCursor = Toolkit.getDefaultToolkit ( ).createCustomCursor ( image, new Point ( 0, 0 ), "blank cursor" );
			
			context.setCursor ( blankCursor );
		}
		
		public void restoreCursor ( JPanel context )
		{
			context.setCursor ( Cursor.getDefaultCursor ( ) );
		}
		// public String getFileExtensionFunction ( String completeFileStream )
		// {
			// return completeFileStream.lastIndexOf ( '.' ) > Math.max ( completeFileStream.lastIndexOf ( '/' ), completeFileStream.lastIndexOf ( '\\' ) ) ? completeFileStream.substring (  completeFileStream.lastIndexOf ( '.' ) + 1 ) : completeFileStream.substring (  Math.max ( completeFileStream.lastIndexOf ( '/' ), completeFileStream.lastIndexOf ( '\\' ) ) + 1 );
		// }
		// public String getFileExtension ( String completeFileStream )
		// {
			// return new File ( completeFileStream ).isFile ( ) ? getFileExtensionFunction ( completeFileStream ) : "I"; //'I' will indicate that the stream exists as a File or Folder.
		// }
		
		public String getFileExtensionFunction ( String completeFileStream )
		{
			return completeFileStream.split ( "\\." ) [ completeFileStream.split ( "\\." ).length - 1 ];
		}
		public String getFileExtension ( String completeFileStream, String absenceTag )
		{
			return new File ( completeFileStream ).isFile ( ) ? getFileExtensionFunction ( completeFileStream ) : absenceTag; //'I' will indicate that the stream exists as a File or Folder.
		}
		public String getFileExtension ( String completeFileStream )
		{
			return new File ( completeFileStream ).isFile ( ) ? getFileExtensionFunction ( completeFileStream ) : "I"; //'I' will indicate that the stream exists as a File or Folder.
		}
		//use regex to achieve file name; split the name component of the file interms of periods ("\\."), then return the initial component that is the name only, 
		//OTHERWISE return the full name, it is a folder.
		public String getFileNameWithoutExtension ( String completeFileStream )
		{
			return new File ( completeFileStream ).isFile ( ) ? new File ( completeFileStream ).getName ( ).split ( "\\." ) [ 0 ] : new File ( completeFileStream ).getName ( );
		}
        public String [ ] getDelimitedArray ( String data, String skip_data, String delimiter, int num_elements )
        {
            String [ ] string = new String [ num_elements ];
            //if i tell the function to perform exept when data is type  skip data, it
            //will do so without attempting to segment the aformentioned data type
                //establish data we want to delimit as delimited data
                Scanner scanner = new Scanner ( data ).useDelimiter ( delimiter );

                for ( int elements = 0; elements < num_elements; elements ++ )
                {
                    if ( !data.equals ( skip_data ) )
                        string [ elements ] = scanner.next ( );
                    else
                        string [ elements ] = skip_data;
                }
             
            return string;
        } 
        
        //get whether a substring of a string exists within a bigger string.
        public boolean stringSubsetEnquiry ( String full_string, String sub_string, int sub_portion_start, int sub_portion_end )
        {
            return full_string.indexOf ( sub_string.substring ( sub_portion_start, sub_portion_end ) ) != -1;
        }
		public String [ ] grownArray ( String [ ] array, String newElement )
		{
			String [ ] grownArray = new String [ array.length + 1 ];
			
			//pump old data into new array
			for ( int i = 0; i < array.length; i ++ )
				grownArray [ i ] = array [ i ]; 
	  
			//pump new data into new array
			grownArray [ grownArray.length - 1 ] = newElement;
			
			//redefine original array wrt grown array
			return grownArray;
		}
        //get whether a substring of a string exists within a bigger string.
        public boolean stringSubsetEnquiry ( String full_string, String sub_string )
        {
            return full_string.indexOf ( sub_string ) != -1;
        }
        
        //make array
        public String [ ] makeArray ( String list, String delimiter )
        {
            String [ ] array = list.split ( delimiter );
            return array;
        }
        
        public File makeFile ( String stream )
        {
            return new File ( stream );
        }
		public void makeDirectory ( String fileStream )
		{
			new File ( fileStream ).mkdir ( );
		}	
		public void makePhysicalFile ( String fileStream )
		{
			try
			{
				PrintWriter pw = new PrintWriter ( new FileWriter ( fileStream ) );
				pw.write ( "" );
				pw.close ( );
			}
			catch ( Exception error ) { };
		}
        public long getByteFileSize ( String fileStream )
        {
            long returnValue = 0;
            boolean sizeExtractionFailureEnquiry = false;
            
            File file = new File ( fileStream );
            
            if ( file.isFile ( ) )
                returnValue += file.length ( );

            else
            {
                try
                {
                    File [ ] innerFiles = new File ( fileStream ).listFiles ( );
                    
                    for ( File innerFile : innerFiles )
                        if ( innerFile.isFile ( ) )
                            returnValue += innerFile.length ( );
                        else
                            if ( !sizeExtractionFailureEnquiry )
                                returnValue += getByteFileSize ( innerFile.toString ( ) );  
                }
                catch ( Exception error )
                {
                    sizeExtractionFailureEnquiry = true;
                }
            }
            
            return returnValue;
        }
		
        public long getKiloByteFileSize ( String fileStream )
        {
            long returnValue = 0;
            boolean sizeExtractionFailureEnquiry = false;
            
            File file = new File ( fileStream );
            
            if ( file.isFile ( ) )
                returnValue += file.length ( ) / 1024;

            else
            {
                try
                {
                    File [ ] innerFiles = new File ( fileStream ).listFiles ( );
                    
                    for ( File innerFile : innerFiles )
                        if ( innerFile.isFile ( ) )
                            returnValue += innerFile.length ( ) / 1024;
                        else
                            if ( !sizeExtractionFailureEnquiry )
                                returnValue += getKiloByteFileSize ( innerFile.toString ( ) );  
                }
                catch ( Exception error )
                {
                    sizeExtractionFailureEnquiry = true;
                }
            }
            
            return returnValue;
        }
		
        public long getMegaByteFileSize ( String fileStream )
        {
            long returnValue = 0;
            boolean sizeExtractionFailureEnquiry = false;
            
            File file = new File ( fileStream );
            
            if ( file.isFile ( ) )
                returnValue += ( file.length ( ) / 1024 ) / 1024;

            else
            {
                try
                {
                    File [ ] innerFiles = new File ( fileStream ).listFiles ( );
                    
                    for ( File innerFile : innerFiles )
                        if ( innerFile.isFile ( ) )
                            returnValue += ( innerFile.length ( ) / 1024 ) / 1024;
                        else
                            if ( !sizeExtractionFailureEnquiry )
                                returnValue += getKiloByteFileSize ( innerFile.toString ( ) );  
                }
                catch ( Exception error )
                {
                    sizeExtractionFailureEnquiry = true;
                }
            }
            
            return returnValue;
        }
		public long getByteFileSizeAsRatioOfScreenWidth ( String fileStream, Dimension screenDimension, int screenDivisor ) 
		{
			return getByteFileSize ( fileStream ) % ( ( int ) ( screenDimension.getSize ( ).getWidth ( ) ) / screenDivisor );
		}
		public long getKiloByteFileSizeAsRatioOfScreenWidth ( String fileStream, Dimension screenDimension, int screenDivisor ) 
		{
			return getKiloByteFileSize ( fileStream ) % ( ( int ) ( screenDimension.getSize ( ).getWidth ( ) ) / screenDivisor );
		}
		public long getMegaByteFileSizeAsRatioOfScreenWidth ( String fileStream, Dimension screenDimension, int screenDivisor ) 
		{
			return getMegaByteFileSize ( fileStream ) % ( ( int ) ( screenDimension.getSize ( ).getWidth ( ) ) / screenDivisor );
		}
        public ArrayList getFileStreams ( String fileStream )
        {
            ArrayList <String> returnValue = new ArrayList <String> ( );
            boolean sizeExtractionFailureEnquiry = false;
            
            File file = new File ( fileStream );
            
            if ( file.isFile ( ) )
                returnValue.add ( file.toString ( ) );

            else
            {
                try
                {
                    File [ ] innerFiles = new File ( fileStream ).listFiles ( );
                    
                    for ( File innerFile : innerFiles )
                        if ( innerFile.isFile ( ) )
                            returnValue.add ( innerFile.toString ( ) );
                        else
                            if ( !sizeExtractionFailureEnquiry )
                                returnValue.add ( "" + getFileStreams ( innerFile.toString ( ) ) ); 
                }
                catch ( Exception error )
                {
                    sizeExtractionFailureEnquiry = true;
                }
            }
            
            return returnValue;
        }
		public void makePhysicalFile ( String fileStream, String content )
		{
			try
			{
				PrintWriter pw = new PrintWriter ( new FileWriter ( fileStream ) );
				pw.write ( content );
				pw.close ( );
			}
			catch ( Exception error ) { };
		}
		public void deleteFile ( File file )
		{
			file.delete ( );
		}	
		
    //method to convert string to url
    public URL makeUrl ( String path )
    {
        URL url = null;
        File file = new File ( path );
        try
        {
            url = file.toURL ( );
        }
        catch ( Exception error )
        {
        }
        return url;
    }	

	public void displayMessage ( JFrame frame, String message, String title, String iconType )
	{
		if ( ( iconType == "i" ) || ( iconType == "I" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.INFORMATION_MESSAGE );
		if ( ( iconType == "w" ) || ( iconType == "W" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.WARNING_MESSAGE );
		if ( ( iconType == "e" ) || ( iconType == "E" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.ERROR_MESSAGE );
		if ( ( iconType == "q" ) || ( iconType == "Q" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.QUESTION_MESSAGE );
	}
	
	//tests if filename is of type specified as target
	public boolean getFileTypeEnquiry ( String filename, String enquiryTarget )
	{
		return stringSubsetEnquiry ( filename, enquiryTarget );
	}
	
	//checks if file exists wrt file location supplied.
	//if not it creates this file physically (not in memory)
	public boolean getFileExistenceEnquiry ( String fileStream )
	{
		boolean flag = false;
		File file = new File ( fileStream );
		if ( file.exists ( ) )
			flag = true;
		else
			flag = false;
		return flag;
	}
	
	//make timer data -- array containing minutes aat index 0, and seconds at index 1 
	public int [ ] makeTimerData ( int elapsedTime )
	{
		int [ ] TimerData = new int [ 2 ];
		int rawSeconds = elapsedTime/1000;
		int refinedSeconds = rawSeconds % 60;
		int minutes = rawSeconds / 60;
		TimerData [ 0 ] = minutes;
		TimerData [ 1 ] = refinedSeconds;
		return TimerData;
	}
	
	//convert minutes to milliseconds
	public int getMilliseconds ( int minutes )
	{
		int seconds = minutes * 60;
		int milliseconds = seconds * 1000;
		return milliseconds;
	}
	
	public int getMillisecondsFromSeconds ( int seconds )
	{
		int milliseconds = seconds * 1000;
		return milliseconds;
	}
	
	//make proper time from milliseconds
	//used to display time remaining from user profile in (user freindly form) 
	public String [ ] getTimeFromMilliseconds ( int milliseconds )
	{
		//get seconds
		int rawSeconds = milliseconds / 1000;
		int refinedSeconds = rawSeconds % 60;
		//get minutes
		int minutes = rawSeconds/60;
		
		String finalSeconds = "";
		
		if ( refinedSeconds < 10 )
			finalSeconds = "0" + refinedSeconds;
			
		else
			finalSeconds = "" + refinedSeconds;   
			
		String value [ ] = { "" + minutes, finalSeconds, "" + refinedSeconds };
		return value;
	} 
	
	//make proper time from milliseconds
	//used to display time remaining from user profile in (user freindly form) 
	public String getTime ( int milliseconds )
	{
		//get seconds
		int rawSeconds = milliseconds / 1000;
		int refinedSeconds = rawSeconds % 60;
		//get minutes
		int minutes = rawSeconds/60;
		
		String finalSeconds = "";
		
		if ( refinedSeconds < 10 )
			finalSeconds = "0" + refinedSeconds;
			
		else
			finalSeconds = "" + refinedSeconds;   
			
		String value = minutes + " : " + finalSeconds;
		return value;
	} 
	
	
	//get secs from millisecs
	public int getSeconds ( double milliseconds )
	{
		return ( int ) ( milliseconds / milliseconds );
	}
	
    public Font getCustomFont ( String inputStream )
    {
        Font font = null;
        try
        {
            font = Font.createFont ( Font.TRUETYPE_FONT, new File ( inputStream ) );
        }
        catch ( Exception error ) { }
        return font;
    }
	
    public void createCustomCursor ( String cursorImageStream, JPanel destinationPanel )
    {
        //create image
		ImageIcon imageIcon = new ImageIcon ( cursorImageStream );
		Image image = imageIcon.getImage ( );
		image = destinationPanel.createImage ( image.getSource ( ) );
		
        //initalise "hot spot"
        Point point = new Point ( 0, 0 );
		
		//create toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit ( );
		
        //initialise cursor
        Cursor cursor = toolkit.createCustomCursor ( image, point, "Cursor" );                       
    }        

	public String getUpperCaseFirstLetterOfWord ( String word )
	{
        String firstLetter = "" + word.charAt ( 0 );
        String finalWord = firstLetter.toUpperCase ( ) + word.substring ( 1, word.length ( ) );
		return finalWord;
	}

	
	public String getJavaKeyWord ( String input, String commentSymbol, String delimiter )
    {
		String value = "";
	
		//establish java component list
		UNICODE_Structure_JavaComponentList javaComponentList = new UNICODE_Structure_JavaComponentList ( );

		javaComponentList = getJavaComponentStructureList ( commentSymbol, delimiter );
		
		for ( int i = 0; i < javaComponentList.size ( ); i ++ )
		{
			String componentName = ( String )  javaComponentList.get ( i ).getComponentName ( );
			String componentStream = ( String )  javaComponentList.get ( i ).getComponentStream ( );
			if ( input.equals ( componentName ) )
				value = componentStream + "." + componentName;
		}
		
        return value;
    }	
	
	public UNICODE_Structure_JavaComponentList getJavaComponentStructureList ( String commentSymbol, String delimiter )
	{
		UNICODE_Structure_JavaComponentList value = new UNICODE_Structure_JavaComponentList ( );
		try
		{
			Scanner scanner = new Scanner ( new File ( "data/config/COMPONENT LIST.ini" ) );
			while ( scanner.hasNext ( ) )
			{
				String line = scanner.nextLine ( );
				if ( !line.startsWith ( commentSymbol ) )
				{
					String name = getDelimitedArray ( line, "", delimiter, 2 ) [ 0 ];
					String stream = getDelimitedArray ( line, "", delimiter, 2 ) [ 1 ];
					value.addComponent ( name, stream );
				}
			}
		}
		catch ( Exception error ) { }
		return value;
	}
	
	
	public boolean getJavaStandardComponentEnquiry ( String input, String commentSymbol, String delimiter )
	{
		boolean flag = false;
		//establish java component list
		UNICODE_Structure_JavaComponentList javaComponentList = new UNICODE_Structure_JavaComponentList ( );
		
		javaComponentList = getJavaComponentStructureList ( commentSymbol, delimiter );
		
		for ( int i = 0; i < javaComponentList.size ( ); i ++ )
		{
			String componentName = ( String )  javaComponentList.get ( i ).getComponentName ( );
			if ( stringSubsetEnquiry ( componentName, input ) )
				flag = true;
		}
		return flag;
	}

	
	public String getUNICODEKeyWord ( String input )
	{
		String value = "";
		ArrayList valueList = new ArrayList ( );
		
		if ( stringSubsetEnquiry ( input, "UNICODE" ) )
		{
			valueList.add ( "data.packages.UNICODE." );
			valueList.add ( input );
		}
		
		for ( int i = 0; i < valueList.size ( ); i ++ )
			value += ( String ) valueList.get ( i );
		
		return getNonDuplicatedBsgkString ( value );
	}

	
	public String getNonDuplicatedBsgkString ( String input )
	{
		String value = "";
		value = input.replace ( "data.packages.UNICODE.data.packages.UNICODE.", "data.packages.UNICODE." );
		return value;
	}
	
	//burrowed function
	public String getNonDupilatedCharacters ( String duplicateTarget )
	{	
		StringBuilder noDupes = new StringBuilder ( );
		for ( int i = 0; i < duplicateTarget.length ( ); i++ ) 
		{
			String si = duplicateTarget.substring ( i, i + 1 );
			if ( noDupes.indexOf ( si ) == -1 )
			{
				noDupes.append ( si );
			}
		}
		return noDupes.toString ( );
	}


	public boolean getIntegerIncrementEnquiry ( int integer, int max )
	{
		boolean flag = false;
		int previous = integer;
		int next = integer + 1;
		if ( next < max )
		{
			if ( next > previous )
				flag = true;
			else
				flag = false;
		}
		return flag;
	}
	
	public boolean getIntegerDecrementEnquiry ( int integer, int min )
	{
		boolean flag = false;
		int next = integer;
		int previous = integer - 1;
		if ( previous > min )
		{
			if ( previous < next )
				flag = true;
			else
				flag = false;
		}
		return flag;
	}
	
	public int getDisplayWidthFromString ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String ttfName, String string )
	{
		//establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
		FontMetrics label_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, ttfName, string ) );
		//establish rectangle, from which font dimension will be derived.
		Rectangle2D label_dimension = label_metrics.getStringBounds ( string, graphics );
		
		return ( int ) label_dimension.getWidth ( );
	}
	
	public int getDisplayHeightFromString ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String ttfName, String string )
	{
		//establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
		FontMetrics label_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, ttfName, string ) );
		//establish rectangle, from which font dimension will be derived.
		Rectangle2D label_dimension = label_metrics.getStringBounds ( string, graphics );
		
		return ( int ) label_dimension.getHeight ( );
	}
	
	public double getDisplayWidthFromString ( String input, int fontSize )
	{
		Font font = new Font ( "Times New Roman", Font.PLAIN, fontSize );
		FontMetrics fontMetrics = new FontMetrics ( font ) { };
		return fontMetrics.getStringBounds ( input, null ).getWidth ( );
	}
	
	public double getDisplayHeightFromString ( String input, int fontSize )
	{
		Font font = new Font ( "Times New Roman", Font.PLAIN, fontSize );
		FontMetrics fontMetrics = new FontMetrics ( font ) { };
		return fontMetrics.getStringBounds ( input, null ).getHeight ( );
	}
	
	public UNICODE_CustomFont getUNICODEFont ( String stream )
	{
		return new UNICODE_CustomFont ( stream );
	}
	
    public int getLargestInteger ( ArrayList numList )
    {
        java.util.Collections.sort ( numList );
        return ( int ) numList.get ( numList.size ( ) - 1 );
    }
	
    public void printFile ( String content, String stream )
    {
        try
        {
            PrintWriter pw = new PrintWriter ( new FileWriter ( stream ) );
            pw.print ( content );
            pw.close ( );
        }
        catch ( Exception e ) { }
    }
	
    public void printSerializable ( Object input, String destination )
    {
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream ( destination );
			ObjectOutputStream objectOutputStream = new ObjectOutputStream ( fileOutputStream );
			objectOutputStream.writeObject ( input );
		}
		catch ( Exception error )
		{
		}
    }
    public Object loadSerializable ( String origin )
    {
		Object returnValue = null;
		try
		{
			FileInputStream fileInputStream = new FileInputStream ( origin );
			ObjectInputStream objectInputStream = new ObjectInputStream ( fileInputStream );
			returnValue = objectInputStream.readObject ( );
		}
		catch ( Exception error )
		{
		}
		return returnValue;
    }
	public ArrayList generateArrayListContentFromFile ( String fileStream )
	{
		ArrayList value = new ArrayList ( );
        try
        {
            Scanner scanner = new Scanner ( new File ( fileStream ) );
            while ( scanner.hasNext ( ) )
				value.add ( scanner.nextLine ( ) );
			scanner.close ( );
        }
        catch ( Exception e ) { }
		return value;
	}
	
   public ArrayList <Double> getPixels ( String fileStream ) 
   {
        ArrayList <Double> returnValue = new ArrayList <Double> ( );
        try 
        {
            File input = new File ( fileStream );
            BufferedImage image = ImageIO.read ( input );
            
            for ( int i = 0; i < image.getHeight ( ); i ++ )
            {
                for ( int j = 0; j < image.getWidth ( ); j ++ )
                {
                    Color colour = new Color ( image.getRGB ( j, i ) );
                    returnValue.add ( getArraySum ( new double [ ] { ( 0.2126 * colour.getRed ( ) ), ( 0.7152 * colour.getGreen ( ) ), ( 0.0722 * colour.getBlue ( ) ) } ) ); //colorimetric-space relative luminance based pixel extraction. ( SYNTHETIC_SENTIENCE )
                }
            }
        } 
        catch ( Exception e ) 
        {
        }
        
        return returnValue;
   }
		
    public String getFileContent ( String fileStream )
    {
        String value = "";
        try
        {
            Scanner scanner = new Scanner ( new File ( fileStream ) );
            while ( scanner.hasNext ( ) )
                value += scanner.nextLine ( ) + "\n";
        }
        catch ( Exception error ) { }
        return value;
    }	
	public String getFileContent ( String fileStream, String contentDelimiter )
	{
		String value = "";
		try
		{
			Scanner scanner = new Scanner ( new File ( fileStream ) );
			while ( scanner.hasNext ( ) )
				value += scanner.nextLine ( ) + contentDelimiter;
		}
		catch ( Exception error ) { }
		return value;
	}	
	public ArrayList <String> getFileContents ( String fileStream )
	{
		ArrayList <String> value = new ArrayList <String> ( );
		try
		{
			Scanner scanner = new Scanner ( new File ( fileStream ) );
			while ( scanner.hasNext ( ) )
				value.add ( scanner.nextLine ( ) );
		}
		catch ( Exception error ) { }
		return value;
	}	
	
    public String getMatchingValue ( String input, String searchTag, String searchTag2, String delimiter )
    {
        String value = "";
        ArrayList valueList = new ArrayList ( );
        
        //divide input into segments
        try
        {
            Scanner scanner = new Scanner ( input ).useDelimiter ( delimiter );
            
            while ( scanner.hasNext ( ) )
                valueList.add ( scanner.next ( ) );
            scanner.close ( );
        }
        catch ( Exception error ) { }
        
        
        //loop through the segment collection checking against the search tag
        for ( int i = 0; i < valueList.size ( ); i ++ )
            if ( stringSubsetEnquiry ( ( String ) valueList.get ( i ), searchTag ) )
                value = ( String ) valueList.get ( i );
			else if ( stringSubsetEnquiry ( ( String ) valueList.get ( i ), searchTag2 ) )
				value = ( String ) valueList.get ( i );
        
        return value;
		
		//proud of myself wrote this once, worked, well, as usual...
    }
	
	
	public void enableFullScreenMode ( JFrame applicationFrame )
	{
		java.awt.GraphicsEnvironment graphicsEnvironment = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment ( );
		java.awt.GraphicsDevice [ ] graphicsDevices = graphicsEnvironment.getScreenDevices ( );
		try
		{
			graphicsDevices [ 0 ].setFullScreenWindow ( applicationFrame );
		} 
		finally 
		{
			graphicsDevices [ 0 ].setFullScreenWindow ( applicationFrame );
		}
	}
	
    public void shutDownComputer ( )
    {
        String shutdownCmd = "shutdown -s";
        try
        {
            Process child = Runtime.getRuntime ( ).exec ( shutdownCmd );
        }
        catch ( Exception error ) { }
    }
	
	
    public void executeCommand ( String command )
    {
        try
        {
            Process child = Runtime.getRuntime ( ).exec ( command );
        }
        catch ( Exception error ) { }
    }
	
	public int getDirectoryCardinality ( String directory )
	{
		return new File ( directory ).list ( ).length;
	}
	
	//burrowed
	public void saveScreen ( String fileName )
	{
		try
		{
			Robot robot = new Robot();
	 
			BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "PNG", new File(fileName));
		}
		catch ( Exception error )
		{
		}
	}
	

    public BufferedImage getScreenImage ( Rectangle buffer ) 
    {
		BufferedImage returnValue = null;
		
        Robot robot = null;
        try 
        {
            robot = new Robot ( );
        } 
        catch ( Exception error ) 
        {
        }
		
        returnValue = robot.createScreenCapture ( buffer );
		
        return returnValue;
    }

    public void darkenImage ( BufferedImage image ) 
    {
        int width = image.getWidth ( );
        int height= image.getHeight ( );

        for ( int y=0; y < height; y++ ) 
        {
            for ( int x=0; x < width; x++ ) 
            {
                int color = image.getRGB ( x,y );
                color = color >> 1;
                color = color & 0x7F7F7F;
                image.setRGB ( x, y, color );
            }
        }
    }
	
	public InputStream getInputStreamFromInput ( String scannerLine )
	{
		return new ByteArrayInputStream ( scannerLine.getBytes ( ) );
	}
	
    
    public int getArraySum ( int [ ] value )
    {
        int returnValue = 0;
        
        for ( int i = 0; i < value.length; i ++ )
            returnValue += value [ i ];
        
        return returnValue;
    }
	
    public double getArraySum ( double [ ] value )
    {
        double returnValue = 0;
        
        for ( int i = 0; i < value.length; i ++ )
            returnValue += value [ i ];
        
        return returnValue;
    }
	
	public String getUrlData ( String urlStream, String errorMessage )
	{
		String returnValue = "";
		
		try
		{
			URL url = new URL ( urlStream );
			BufferedReader in = new BufferedReader ( new InputStreamReader ( url.openStream ( ) ) );
			String inputLine;
			
			while ( ( inputLine = in.readLine ( ) ) != null ) 
				returnValue += inputLine + "\n";
		}
		catch ( Exception error )
		{
			if ( ( !errorMessage.equals ( "" ) ) || ( errorMessage != null ) )
				returnValue += errorMessage;
			else
				returnValue += "uni-code file error!";
		}
			
		return returnValue;
	}
	
	public String getRegexComponents ( String patternString, String inputString, String replacementControllerString )
	{
		String returnValue = "";
		
        Pattern pattern = Pattern.compile ( patternString );
        
        Matcher javacPathGenerationLineMatcher = pattern.matcher ( inputString );
        if ( javacPathGenerationLineMatcher.find ( ) )
            returnValue = javacPathGenerationLineMatcher.replaceAll ( replacementControllerString );
			
		return returnValue;
	}
	
	public String getRegexComponents ( String patternString, String inputString, String replacementControllerString, String skipIdentifierTag )
	{
		String returnValue = "";
		
        Pattern pattern = Pattern.compile ( patternString );
        
        Matcher javacPathGenerationLineMatcher = pattern.matcher ( inputString );
        if ( javacPathGenerationLineMatcher.find ( ) )
			if ( !stringSubsetEnquiry ( inputString, skipIdentifierTag ) )
				returnValue = javacPathGenerationLineMatcher.replaceAll ( replacementControllerString );
			
		return returnValue;
	}
	
	public boolean getPatternMatchEnquiry ( String patternString, String inputString )
	{
		boolean returnValue = false;
		
        Pattern pattern = Pattern.compile ( patternString );
        
        Matcher javacPathGenerationLineMatcher = pattern.matcher ( inputString );
		
        if ( javacPathGenerationLineMatcher.find ( ) )
            returnValue = true;
		else
			returnValue = false;
			
		return returnValue;
	}
	



	//COLOR_BLENDER
	//from stack overflow, via "MadProgrammer"
	//http://stackoverflow.com/questions/13223065/color-fading-algorithm
		public Color getBlendedColour(float[] fractions, Color[] colors, float progress) 
		{
			Color color = null;
			if (fractions != null) 
			{
				if (colors != null) 
				{
					if (fractions.length == colors.length) 
					{
						int[] indicies = getFractionIndicies(fractions, progress);

						float[] range = new float[]{fractions[indicies[0]], fractions[indicies[1]]};
						Color[] colorRange = new Color[]{colors[indicies[0]], colors[indicies[1]]};

						float max = range[1] - range[0];
						float value = progress - range[0];
						float weight = value / max;

						color = blend(colorRange[0], colorRange[1], 1f - weight);
					} 
					else 
					{
						throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
					}
				}
				else 
				{
					throw new IllegalArgumentException("Colours can't be null");
				}
			} 
			else 
			{
				throw new IllegalArgumentException("Fractions can't be null");
			}
			return color;
		}
		
		public int[] getFractionIndicies(float[] fractions, float progress)
		{
			int[] range = new int[2];

			int startPoint = 0;
			while (startPoint < fractions.length && fractions[startPoint] <= progress) 
			{
				startPoint++;
			}

			if (startPoint >= fractions.length)
			{
				startPoint = fractions.length - 1;
			}

			range[0] = startPoint - 1;
			range[1] = startPoint;

			return range;
		}

		public Color blend(Color color1, Color color2, double ratio) 
		{
			float r = (float) ratio;
			float ir = (float) 1.0 - r;

			float rgb1[] = new float[3];
			float rgb2[] = new float[3];

			color1.getColorComponents(rgb1);
			color2.getColorComponents(rgb2);

			float red = rgb1[0] * r + rgb2[0] * ir;
			float green = rgb1[1] * r + rgb2[1] * ir;
			float blue = rgb1[2] * r + rgb2[2] * ir;

			if (red < 0)
			{
				red = 0;
			} 
			else if (red > 255)
			{
				red = 255;
			}
			if (green < 0) 
			{
				green = 0;
			}
			else if (green > 255) 
			{
				green = 255;
			}
			if (blue < 0)
			{
				blue = 0;
			}
			else if (blue > 255)
			{
				blue = 255;
			}

			Color color = null;
			try 
			{
				color = new Color(red, green, blue);
			} 
			catch (IllegalArgumentException exp)
			{
				NumberFormat nf = NumberFormat.getNumberInstance();
				System.out.println(nf.format(red) + "; " + nf.format(green) + "; " + nf.format(blue));
				exp.printStackTrace();
			}
			return color;
		}
		
    public void enableImageResizing ( String outputDirectory, Dimension resizeDimension )
    {
        // Get the BufferedImage object by reading the image
        // from the given input stream
        java.awt.image.BufferedImage bim = null;
        
        try
        {
           bim = javax.imageio.ImageIO.read ( new java.io.FileInputStream ( outputDirectory ) );
        }
        catch ( Exception error )
        {
        }
       
        // I am using fast scaling
        java.awt.Image resizedImg = bim.getScaledInstance ( ( int ) resizeDimension.getWidth ( ), ( int ) resizeDimension.getHeight ( ), java.awt.Image.SCALE_FAST );
       
        // Create a BufferedImage object of w,h width and height
        // and of the bim type
        java.awt.image.BufferedImage rBimg = new java.awt.image.BufferedImage ( ( int ) resizeDimension.getWidth ( ), ( int ) resizeDimension.getHeight ( ), bim.getType ( ) );
       
        // Create Graphics object
        Graphics2D g = rBimg.createGraphics ( );
       
        // Draw the resizedImg from 0,0 with no ImageObserver
        g.drawImage ( resizedImg, 0, 0, null );
       
        // Dispose the Graphics object, we no longer need it
        g.dispose ( );
       
        // Now, what? Just write to another file
       
        // The first argument is the resized image object
        // The second argument is the image file type, So i got the
        // extension of the output file and passed it
        // The next argument is the FileOutputStream to where the resized
        // image is to be written.
        
        try
        {
           javax.imageio.ImageIO.write ( rBimg, outputDirectory.substring ( outputDirectory.indexOf ( "." ) + 1 ), new java.io.FileOutputStream ( outputDirectory ) );
        }
        catch ( Exception error )
        {
        }
    }
}
