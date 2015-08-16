package data.packages.UNICODE;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class UNICODE_ClassNameBox extends JPanel
{
    //attributes
    private JLabel label;

    //constuctor
    public UNICODE_ClassNameBox ( JLabel label, Color colour, int width, int height )
    {
        //add label
        add ( label );
        
        //establish dimension
        setPreferredSize ( new Dimension ( width, height ) );
        
        //establish colour
        setBackground ( colour );
    }
}
