package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.Color;
import java.util.Random; 

public class UNICODE_RandomColourFactory
{
	//attributes
	private Random randomizer = new Random ( );

	//constructor
	public UNICODE_RandomColourFactory ( )
	{
	}
	
	public Color getRandomColour ( int lowerLimit, int upperLimit )
	{
		Color returnValue = null;
			
			
		int redValue = randomizer.nextInt ( upperLimit ), greenValue = randomizer.nextInt ( upperLimit ), blueValue = randomizer.nextInt ( upperLimit );
		
		returnValue = new Color ( redValue, greenValue, blueValue );
		
		return returnValue;
	}
	
	public Color getRandomColour ( )
	{
		int upperLimit = 255, lowerLimit = 0;
		
		Color returnValue = null;
		
		int redValue = randomizer.nextInt ( upperLimit ), greenValue = randomizer.nextInt ( upperLimit ), blueValue = randomizer.nextInt ( upperLimit );
		
		returnValue = new Color ( redValue, greenValue, blueValue );
		
		return returnValue;
	}
}