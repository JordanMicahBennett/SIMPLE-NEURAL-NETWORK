package data.packages.UNICODE; 
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class UNICODE_DonutShape extends JPanel 
{
    //attributes    
    private Shape donutShape = null; 
    
    public UNICODE_DonutShape ( double outerBodyX, double outerBodyY, double outerBodyWidth, double outerBodyHeight, double innerBodyWidth, double innerBodyHeight )
    {
        //establish outer Ellipse
        Area outerBody = new Area ( new Ellipse2D.Double ( outerBodyX, outerBodyY, outerBodyWidth, outerBodyHeight ) );
        //establish inner Ellipse
        Area innerBody = new Area ( new Ellipse2D.Double ( outerBodyWidth / 2 - innerBodyWidth / 2, outerBodyHeight / 2 - innerBodyHeight / 2, innerBodyWidth, innerBodyHeight ) );
        //mutate the outer body, subtracting the inner body
        outerBody.subtract ( innerBody );
        //establish this shape
        donutShape = outerBody;
    }
    
    public Shape getBody ( )
    {
        return donutShape;
    }
}
