package data.packages.UNICODE;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class UNICODE_DateCreator
{ 
	//declare time variable
	private Date date = new Date ( ); //moth, day time
	private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );

	public UNICODE_DateCreator ( )
	{
	}

	//method to return day, year, and month
	public String getDate ( ) //..this does not need to be in a loop, so use as separate function.
	{
		String day = "";
		String month = "";
		
		//determine day
		switch ( date.getDay ( ) )
		{
			case 0 : day = "Sunday"; break; case 1 : day = "Monday"; break;  case 2 : day = "Tuesday"; break; case 3 : day = "Wednesday"; break; case 4 : day = "Thursday"; break; case 5 : day = "Friday"; break;  case 6 : day = "Saturday"; break; 
		}

		//determine month
		switch ( date.getMonth ( ) )
		{
			case 0 : month = "January"; break; case 1 : month = "February"; break; case 2 : month = "March"; break; case 3 : month = "April"; break; case  4: month = "May"; break; case 5 : month = "June"; break; case 6 : month = "July"; break; case 7 : month = "August"; break; case 8 : month = "September"; break; case 9 : month = "October"; break; case 10 : month = "November"; break; case 11 : month = "December"; break;
		}

		//year
		Date date_year = new Date ( );
		DateFormat year = new SimpleDateFormat ( "yyyy" );

		//display derived date
		return "" + day + " " + month + " " + date.getDate ( ) + " , " + year.format ( date_year );
	}

	public String getTime ( )
	{
		return date.getHours ( ) + ":" + date.getMinutes ( ) + ":" + date.getSeconds ( );
	}
	
	public int getHours ( )
	{
		return date.getHours ( );
	}
	public int getMinutes ( )
	{
		return date.getMinutes ( );
	}
	public int getSeconds ( )
	{
		return date.getSeconds ( );
	}
	
    public int [ ] getHoursDigits ( )
    {
        int [ ] returnValue = new int [ 2 ];
        
        String hours = "" + getHours ( );
        
        try
        {
            returnValue [ 0 ] = Integer.parseInt ( conveniencePack.getDelimitedArray ( hours, "", "", 2 ) [ 0 ] );
            returnValue [ 1 ] = Integer.parseInt ( conveniencePack.getDelimitedArray ( hours, "", "", 2 ) [ 1 ] );
        }
        catch ( Exception error )
        {
            returnValue [ 0 ] = 0;
            returnValue [ 1 ] = Integer.parseInt ( hours );
        }
        
        return returnValue;
    }
    
    public int [ ] getMinutesDigits ( )
    {
        int [ ] returnValue = new int [ 2 ];
        
        String minutes = "" + getMinutes ( );
        
        try
        {
            returnValue [ 0 ] = Integer.parseInt ( conveniencePack.getDelimitedArray ( minutes, "", "", 2 ) [ 0 ] );
            returnValue [ 1 ] = Integer.parseInt ( conveniencePack.getDelimitedArray ( minutes, "", "", 2 ) [ 1 ] );
        }
        catch ( Exception error )
        {
            returnValue [ 0 ] = 0;
            returnValue [ 1 ] = Integer.parseInt ( minutes );
        }
        
        return returnValue;
    }
    
    public int [ ] getSecondsDigits ( )
    {
        int [ ] returnValue = new int [ 2 ];
        
        String seconds = "" + getSeconds ( );
        
        try
        {
            returnValue [ 0 ] = Integer.parseInt ( conveniencePack.getDelimitedArray ( seconds, "", "", 2 ) [ 0 ] );
            returnValue [ 1 ] = Integer.parseInt ( conveniencePack.getDelimitedArray ( seconds, "", "", 2 ) [ 1 ] );
        }
        catch ( Exception error )
        {
            returnValue [ 0 ] = 0;
            returnValue [ 1 ] = Integer.parseInt ( seconds );
        }
        
        return returnValue;
    }
}
