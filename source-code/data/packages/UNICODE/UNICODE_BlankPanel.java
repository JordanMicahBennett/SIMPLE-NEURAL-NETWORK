package data.packages.UNICODE; 
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:---------------------------------------------:
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class UNICODE_BlankPanel extends JPanel 
{
	//attributes
	private Color colour = null;
	private int buttonCardinality = 0;
	private UNICODE_FadePaint faintPaint;
	private UNICODE_FadePaintDirections paintDirection;
	private Color colouri, colourii;
	private int targetWidth, targetHeight;
	private double leftColourSwingPercentage;
	
	
	//constructor
		//use regular colouring
		public UNICODE_BlankPanel ( Color colour )
		{
			this.colour = colour;
			setBackground ( colour );
		}
		//use unicode fade paint
		public UNICODE_BlankPanel ( UNICODE_FadePaintDirections paintDirection, Color colouri, Color colourii, int targetWidth, int targetHeight, double leftColourSwingPercentage )
		{
			this.paintDirection = paintDirection;
			this.colouri = colouri;
			this.colourii = colourii;
			this.leftColourSwingPercentage = leftColourSwingPercentage;
			faintPaint = new UNICODE_FadePaint ( paintDirection, colouri, colourii, targetWidth, targetHeight, leftColourSwingPercentage );
		}
		

	public abstract void drawMore ( Graphics graphics, Graphics2D graphics2d );
	{
	}
	
	public void setPaint ( Color value0, Color value1 )
	{
		faintPaint.changeColour ( value0, value1 );
	}
	
	//paint component
	public void paintComponent ( Graphics graphics )
	{
		super.paintComponent ( graphics );
		Graphics2D graphics2d = ( Graphics2D ) graphics;

		faintPaint.draw ( graphics2d );
		
		drawMore ( graphics, graphics2d );
	}
}