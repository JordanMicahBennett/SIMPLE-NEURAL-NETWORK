package data.packages.UNICODE;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.BoxLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import data.packages.UNICODE.*;

public class UNICODE_ClassDiagramBox extends JPanel
{
    //attributes
    //establish current coordinates genrated by pressed down mouse
    private Point held_mouse_coords = null;
    //establish current coordinates geenrated by mouse dragging
    private Point dragged_mouse_coords = null;
    
    //establish box class name
    private JLabel classNameLabel;
    
    private JPanel masterBox = new JPanel ( );
    private UNICODE_ClassNameBox classNameBox = null;
    private UNICODE_AttributesBox attributesBox = null;
    private UNICODE_MethodsBox methodsBox = null;
    
    //establish boxes' dimension variables
    private int sharedWidth = 0;
    private int attributesBoxHeight = 0, methodsBoxHeight = 0, classDiagramHeight = 0, combinedHeight = 0;
    
    //establish convenience pack
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    
    //establish avialiablemethods string
    private String availableMethodsString = null;
    
    //constuctor
    public UNICODE_ClassDiagramBox ( String classNameLabelText, String _availableMethodsString )
    {
        //establish label wrt to supplied text
        classNameLabel = new JLabel ( classNameLabelText );
        
        //establish dimensions
            //establish the width which will be used by all boxes
            sharedWidth = ( int ) conveniencePack.getDisplayWidthFromString ( classNameLabelText, 21 );
            //establish box heights
            classDiagramHeight = ( int ) conveniencePack.getDisplayHeightFromString ( classNameLabelText, 21 );
            attributesBoxHeight = ( int ) conveniencePack.getDisplayHeightFromString ( "attributes", 21 );
            methodsBoxHeight = ( int ) conveniencePack.getDisplayHeightFromString ( "methods", 21 );

        
        //establish combined height ( height of class diagram box, or the summation of the heights of attributes,methods, and class name boxes )
        combinedHeight = classDiagramHeight + attributesBoxHeight + methodsBoxHeight;
        
        //establish boxes
            //establish class name box
            classNameBox = new UNICODE_ClassNameBox ( classNameLabel, Color.white, sharedWidth, classDiagramHeight );
            //establish attributes box
            attributesBox = new UNICODE_AttributesBox ( "attributes", Color.lightGray, sharedWidth, attributesBoxHeight );
            //establish attributes box
            methodsBox = new UNICODE_MethodsBox ( "methods", Color.gray, sharedWidth, methodsBoxHeight );
            //establish class diagram box
            setPreferredSize ( new Dimension ( sharedWidth, combinedHeight ) );
        
        //add components
            //add class diagram box
            masterBox.add ( classNameBox );
            //add attributes box
            masterBox.add ( attributesBox );
            //add methods box
            masterBox.add ( methodsBox );
            //set layout
            masterBox.setLayout ( new BoxLayout ( masterBox, BoxLayout.Y_AXIS ) );
           
            //add master Box
            add ( masterBox );
        //establish colour
        setBackground ( Color.white );
        
        //add mouse listeners
        addMouseMotionListener ( new mouseListening ( ) );
        addMouseListener ( new mouseListening ( ) );
        
        //establish available methods string
        availableMethodsString = _availableMethodsString;
    }
    
    
    //accessors
    public boolean isDraggable ( int x, int y )
    {
        return contains ( x, y );
    }
    public String getMethodString ( )
    {
        return availableMethodsString;
    }
    
    
    //listeners
    private class mouseListening implements MouseMotionListener, MouseListener
    {
        public void mouseClicked ( MouseEvent mouseEvent )  
        { 
        }              
    
        public void mousePressed ( MouseEvent mouseEvent )  
        {      
            //establish mouse pressed coords
            held_mouse_coords = mouseEvent.getPoint ( );  
        }               
    
        public void mouseReleased ( MouseEvent mouseEvent ) 
        { 
        }
        
        public void mouseEntered ( MouseEvent mouseEvent )  
        {
        }              
    
        public void mouseExited ( MouseEvent mouseEvent )   
        {  
        }             
    
        public void mouseDragged ( MouseEvent mouseEvent )  
        {    
            //establish dragged mouse coordinates
            dragged_mouse_coords = mouseEvent.getLocationOnScreen ( );   
            
            if ( isDraggable ( mouseEvent.getX ( ), mouseEvent.getY ( ) ) )
            setLocation ( ( int ) ( dragged_mouse_coords.getX ( ) - held_mouse_coords.getX ( ) ), ( int ) ( dragged_mouse_coords.getY ( ) - held_mouse_coords.getY ( ) ) );
            
            repaint ( );
        }
    
        public void mouseMoved ( MouseEvent mouseEvent )    
        {
        }
    }
}
