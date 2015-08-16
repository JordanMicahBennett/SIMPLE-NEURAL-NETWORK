package data.packages.UNICODE;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class UNICODE_MethodsBox extends JPanel
{
    //attributes
    private JLabel label;

    //constuctor
    public UNICODE_MethodsBox ( String labelText, Color colour, int width, int height )
    {
        //establish label wrt to supplied text
        label = new JLabel ( labelText );
        
        //add label
        add ( label );
        
        //establish dimension
        setPreferredSize ( new Dimension ( width, height ) );
        
        //establish colour
        setBackground ( colour );
    }
}
