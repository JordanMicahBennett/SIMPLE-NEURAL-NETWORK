package data.packages.UNICODE; //Author(s): Jordan Micah Bennett

import java.io.File;
import java.util.Date;

public class UNICODE_GlowIntensityAndSpanValueGenerator
{
    //attributes
	private float spanPercentile = 0.0f, maximumIntensityPercentile = 0.0f, intensityLevel = 0.0f;
	private String ageReflectionAnswer = null; //dictates whether values for span and intensity will reflect file age.

    //constructor
    public UNICODE_GlowIntensityAndSpanValueGenerator ( String ageReflectionAnswer, float spanPercentile, float intensityLevel, float maximumIntensityPercentile )
    {
		this.ageReflectionAnswer = ageReflectionAnswer;
		this.spanPercentile = spanPercentile;
		this.intensityLevel = intensityLevel;
		this.maximumIntensityPercentile = maximumIntensityPercentile;
    }
    
    //methods
    public float getSpanValue ( File targetFile, double objectWidth )
    {
		float returnValue = 0.0f;
		
		long fileAge = targetFile.lastModified ( );
		int fileYear = new Date ( fileAge ).getYear ( );
		
		if ( ageReflectionAnswer.equals ( "yes" ) )
			returnValue = ( float ) ( fileAge * fileYear % ( ( spanPercentile / 100 ) * objectWidth ) );
		else if ( ageReflectionAnswer.equals ( "no" ) )
			returnValue = ( float ) ( spanPercentile / 100 ) * ( float ) objectWidth;
		
		return returnValue;
    }
    public float getIntensityValue ( File targetFile )
    {
		float returnValue = 0.0f;
		
		long fileAge =  targetFile.lastModified ( );
		int fileYear = new Date ( fileAge ).getYear ( );
		
		if ( ageReflectionAnswer.equals ( "yes" ) )
			returnValue = ( float ) fileAge % fileYear % maximumIntensityPercentile;
		else if ( ageReflectionAnswer.equals ( "no" ) )
			returnValue = intensityLevel;
			
		return returnValue;
    }
}