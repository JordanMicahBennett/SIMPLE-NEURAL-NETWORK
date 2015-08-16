package data.packages.UNICODE;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.geom.AffineTransform;

public class UNICODE_GlowingLine extends JPanel
{
    //attributes
        //depths
        private int lineThickness = 0, glowThickness = 0;
        //colours
            //line colour
            private Color lineColour = null;
            //glow colour
            private int glowColourR, glowColourG, glowColourB;
        //mutlipliers
        private int glowQualityMultiplier = 0; //the higher this value is the less refined the glow begines.
        
        //prebaked brightness levels
        private int brightness = 0; //the main literal
        public int DIM = 20, BRIGHT = 50, VERY_BRIGHT = 70, BLINDING = 100, RIDICULOUS = 120;
		
		//orientation
		private int x = 0, y = 0, x2 = 0, y2 = 0;
    
    //constructor
    public UNICODE_GlowingLine ( int _lineThickness, int _glowThickness, int _glowQualityMultiplier, int _brightness, Color _lineColour, int _glowColourR, int _glowColourG, int _glowColourB ) 
    {
        //depths   
        lineThickness = _lineThickness;
        glowThickness = _glowThickness; //number of passes taken to draw strokes.
        
        //colours
            //line colour
            lineColour = _lineColour;
            //glow colour
            glowColourR = _glowColourR;
            glowColourG = _glowColourG;
            glowColourB = _glowColourB;
        
        //visuals
        brightness = _brightness;
    
        //multipliers
        glowQualityMultiplier = _glowQualityMultiplier;
        
        setBackground ( Color.black );
    }
    
    //pc
    public void draw ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        

        for ( int glowIncremtationCycles = 0; glowIncremtationCycles < glowThickness; glowIncremtationCycles ++ ) 
        {
           int alphaTransparency = brightness, alphaTransparencyDegredationRate = glowIncremtationCycles * glowQualityMultiplier;
           
           alphaTransparency += alphaTransparencyDegredationRate;
           
           graphics2d.setColor ( new Color ( glowColourR, glowColourG, glowColourB, alphaTransparency ) );
           
           graphics2d.setStroke ( new BasicStroke ( lineThickness + alphaTransparencyDegredationRate, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND ) );
           
           graphics2d.draw ( new Line2D.Double ( x, y, x2, y2 ) );
        }
        
        graphics2d.setColor ( lineColour );
        graphics2d.setStroke ( new BasicStroke ( lineThickness ) );
        graphics2d.draw ( new Line2D.Double ( x, y, x2, y2 ) );
    }
	
    
    //mutators
    public void setBrightness ( int value )
    {
        brightness = value;
    }
	public void setColour ( Color value )
	{
		lineColour = value;
	}
	public void setGlowColour ( int r, int g, int b )
	{
		glowColourR = r;
		glowColourG = g;
		glowColourB = b;
	}
	public void setOrientation ( int _x, int _y, int _x2, int _y2 )
	{
		x = _x;
		y = _y;
		x2 = _x2;
		y2 = _y2;
	}
}
