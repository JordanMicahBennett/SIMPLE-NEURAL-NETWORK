package data.packages.UNICODE;
//Author(s): Jordan Micah Bennett
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Point;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.util.Scanner;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JEditorPane;

public class UNICODE_MethodPopupPanel extends JPanel
{
    //attributes
    //establish frame connection
    private JFrame methodPopupWindowFrame; //will allow repositioning of frame by user
    //establish current coordinates genrated by pressed down mouse
    private Point held_mouse_coords = null;
    //establish current coordinates geenrated by mouse dragging
    private Point dragged_mouse_coords = null;
    //establish data content
    private ArrayList dataContentArr = new ArrayList ( );
    private String dataContentStr = "";
    //establish data combo box
    private JComboBox dataComboBox = null;
    //establish currently selected item
    private String currentlySelectedItem = "";
	//establish editor panel
	private JEditorPane editorPane = null;
    
    public UNICODE_MethodPopupPanel ( ArrayList _dataContent, Color backgroundColour, JFrame _methodPopupWindowFrame )
    {
        //establish window frame
        methodPopupWindowFrame = _methodPopupWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish method box size
        //setPreferredSize ( new Dimension ( 200, 80 ) );
        
        //establish method box data content
        dataContentArr = _dataContent;
        
        //pass data from array list to data structure accepted by JComboBox
        String [ ] acceptableDataContent = new String [ dataContentArr.size ( ) ];
        
        for ( int i = 0; i < dataContentArr.size ( ); i ++ )
            acceptableDataContent [ i ] = ( String ) dataContentArr.get ( i );
        
        //establish method box combo box
        dataComboBox = new JComboBox ( acceptableDataContent );
        
		//add llistener to jcombo box
		dataComboBox.addItemListener ( new itemListening ( ) );
		
        //now add the populated JComboBox to method box panel
        add ( dataComboBox );
        
        ///add mouse listener
        addMouseMotionListener ( new mouseListening ( ) );
        addMouseListener ( new mouseListening ( ) );
		
		//add key listener
		addKeyListener ( new keyListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
    
    public UNICODE_MethodPopupPanel ( JEditorPane _editorPane, String _dataContent, Color backgroundColour, JFrame _methodPopupWindowFrame )
    {
        //establish window frame
        methodPopupWindowFrame = _methodPopupWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish method box size
        setPreferredSize ( new Dimension ( 200, 80 ) );
        
        //establish method box data content
        dataContentStr = _dataContent;
        
        //pass data from array list to data structure accepted by JComboBox
        Scanner scanner = new Scanner ( dataContentStr );
        ArrayList dataList = new ArrayList ( );
        
        while ( scanner.hasNext ( ) )
            dataList.add ( scanner.nextLine ( ) );
            
        String [ ] acceptableDataContent = new String [ dataList.size ( ) ];
 
        for ( int i = 0; i < dataList.size ( ); i ++ )
            acceptableDataContent [ i ] = ( String ) dataList.get ( i );
			
		//establish editor pane
		editorPane = _editorPane;

        //establish method box combo box
        dataComboBox = new JComboBox ( acceptableDataContent );
		
		//add llistener to jcombo box
		dataComboBox.addItemListener ( new itemListening ( ) );
        
        //now add the populated JComboBox to method box panel
        add ( dataComboBox );

        ///add mouse listener
        addMouseMotionListener ( new mouseListening ( ) );
        addMouseListener ( new mouseListening ( ) );
		
		//add key listener
		addKeyListener ( new keyListening ( ) );
        
        //set focus
        setFocusable ( true );
    }

    
    //utils
    public void showMethodPanel ( int x, int y )
    {
        setLocation ( x, y );
        this.setVisible ( true );
    }
    public void hideMethodPanel ( )
    {
        this.setVisible ( false );
    } 
    
    
    private class mouseListening implements MouseMotionListener, MouseListener
    {
        public void mousePressed ( MouseEvent mEvent )
        {
            //establish mouse pressed coords
            held_mouse_coords = mEvent.getPoint ( );
        }
        public void mouseReleased ( MouseEvent mEvent )
        {
        }
        public void mouseEntered ( MouseEvent mEvent )
        {
        }
        public void mouseExited ( MouseEvent mEvent )
        {
        }
        public void mouseDragged ( MouseEvent mEvent )
        {
            //establish dragged mouse coordinates
            dragged_mouse_coords = mEvent.getLocationOnScreen ( );    
            //methodPopupWindowFrame.setLocation ( ( int ) ( dragged_mouse_coords.getX ( ) - held_mouse_coords.getX ( ) ), ( int ) ( dragged_mouse_coords.getY ( ) - held_mouse_coords.getY ( ) ) );
        }
        public void mouseClicked ( MouseEvent mEvent )
        {
        }
        public void mouseMoved ( MouseEvent mEvent )
        {
        }
    }
	
	private class keyListening implements KeyListener 
	{
		public void keyPressed ( KeyEvent keyEvent )
		{
			switch ( keyEvent.getKeyCode ( ) )
			{
				case KeyEvent.VK_ESCAPE:
				{
					System.exit ( 0 );
				}
				break;
			}
			repaint ( );
		}
		public void keyTyped ( KeyEvent keyEvent )
		{
		}
		public void keyReleased ( KeyEvent keyEvent )
		{
		}
	}
	
	private class itemListening implements ItemListener
	{
		public void itemStateChanged ( ItemEvent itemEvent ) 
		{
			int state = itemEvent.getStateChange ( );
			if ( state == itemEvent.SELECTED )
			{
				try
				{
					editorPane.getDocument ( ).insertString ( editorPane.getDocument ( ).getLength ( ), ( String ) dataComboBox.getSelectedItem ( ), null );
			    }
				catch ( Exception error ) { } 
					
			}
		}
	}
}
