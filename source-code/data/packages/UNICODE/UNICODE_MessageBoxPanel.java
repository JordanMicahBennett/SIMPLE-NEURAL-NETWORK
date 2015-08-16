 package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
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
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import data.packages.UNICODE.*;

public class UNICODE_MessageBoxPanel extends JPanel
{
    //attributes
    //establish frame connection
    private JFrame messageBoxWindowFrame; //will allow repositioning of frame by user
    //establish current coordinates genrated by pressed down mouse
    private Point held_mouse_coords = null;
    //establish current coordinates geenrated by mouse dragging
    private Point dragged_mouse_coords = null;

    //establish message box dimension
    private int width = 400, height = 180;
    
    //establish maximum buttons
    private int MAXIMUM_BUTTONS = 3; 
    
	//click response threads
    private Thread thread0 = null, thread1 = null, thread2 = null, thread3 = null;
	
	//establish text field
	private JTextField textField = new JTextField ( "", 10 ), textField1 = new JTextField ( "", 10 );
    private JTextArea textArea = new JTextArea ( 16, 30 );
	
	//establish JLabel
	private JLabel label = null;
	
	//establish menu panel for this panel
	private UNICODE_MenuPanel menuPanel = null;
	
	//when a message box panel has a list of text fields
	private ArrayList <UNICODE_MessageBoxPanel> fieldList = null;
	private ArrayList <String> fieldLabelList = null;
	
	//constructor : customizable two threaded frame.
    public UNICODE_MessageBoxPanel ( Thread _thread0, Thread _thread1, Color backgroundColour, Color buttonOutlineColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        thread1 = _thread1;
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelI ( generatedCustomComponentListI ( ), true, 100, 130, 3, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		add ( menuPanel );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable one threaded frame, that has a manipuble text field.
    public UNICODE_MessageBoxPanel ( Thread _thread0, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelII ( generatedCustomComponentListII ( ), true, 230, 20, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel fieldPanel = new JPanel ( );
		
		//setup text field colour
		textField.setBackground ( textfieldBackgroundColour );
		textField.setForeground ( textfieldForegroundColour );

		//add text field  panel to this panel, and setup
		fieldPanel.add ( textField );
		fieldPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( fieldPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	
	//constructor : customizable one threaded frame....for label, whose text may be customized.
    public UNICODE_MessageBoxPanel ( Thread _thread0, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
		
		//establish label
		label = new JLabel ( labelText );
		
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelII ( generatedCustomComponentListII ( ), true, 230, 20, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable three threaded frame....for label, whose text may be customized.
    public UNICODE_MessageBoxPanel ( Thread _thread0, Thread _thread1, Thread _thread2, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        thread1 = _thread1;	
        thread2 = _thread2;
		
		//establish label
		label = new JLabel ( labelText );
		
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width + 120, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelIV ( generatedCustomComponentListIV ( ), true, 124, 20, 4, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable all threaded threaded frame....for label, whose text may be customized.
    public UNICODE_MessageBoxPanel ( Thread _thread0, Thread _thread1, Thread _thread2, Thread _thread3, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        thread1 = _thread1;	
        thread2 = _thread2;
		thread3 = _thread3;
		
		//establish label
		label = new JLabel ( labelText );
		
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width + 120, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelXI ( generatedCustomComponentListVIII ( ), true, 124, 20, 4, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	//constructor : customizable one threaded frame....for box with two threads, and label
    public UNICODE_MessageBoxPanel ( Thread _thread0, Thread _thread1, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        thread1 = _thread1;	
		
		//establish label
		label = new JLabel ( labelText );
		
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
		
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelI ( generatedCustomComponentListI ( ), true, 124, 20, 3, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
    
	//constructor : customizable one threaded frame....one button, no threads, and label...
    public UNICODE_MessageBoxPanel ( String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {		
		//establish label
		label = new JLabel ( labelText );
		
	
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelIII ( generatedCustomComponentListIII ( ), true, 340, 20, 1, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : no threaded frame....one button, no threads, and multi-line label...
    public UNICODE_MessageBoxPanel ( String labelText, String delimiter, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {		
		//establish label
		label = new JLabel ( "" );
		
	
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
		
		//establish multiline JLabel capbility
			//establish broken labelText
			String [ ] brokenLabelText = new UNICODE_ConveniencePack ( ).makeArray ( labelText, delimiter );
			//now build a list of strings wrt brokenLabelText
			ArrayList brokenLabelTextCompilation = new ArrayList ( );	
				//add html starting tag 
				brokenLabelTextCompilation.add ( "<HTML>" );	
				//loop, brokenLabelTextCompilation, building in terms of its components
				for ( int i = 0; i < brokenLabelText.length; i ++ )
					brokenLabelTextCompilation.add ( "" + brokenLabelText [ i ] + "<BR>" );
				//add html ending tag
				brokenLabelTextCompilation.add ( "</HTML>" );	
			//now build a string of final results wrt brokenLabelTextCompilation
			String finalLabelText = "";
			for ( int i = 0; i < brokenLabelTextCompilation.size ( ); i ++ )
				finalLabelText += ( String ) brokenLabelTextCompilation.get ( i );
			//now set Label wrt to finalLabelText content
			label.setText ( finalLabelText ); 
				
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelIII ( generatedCustomComponentListIII ( ), true, 340, 20, 1, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable threaded frame....one button, no threads, and multi-line label...
    public UNICODE_MessageBoxPanel ( Thread _thread0, String labelText, String delimiter, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {		
        //establish threads
        thread0 = _thread0;
	
		//establish label
		label = new JLabel ( "" );
		
	
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
		
		//establish multiline JLabel capbility
			//establish broken labelText
			String [ ] brokenLabelText = new UNICODE_ConveniencePack ( ).makeArray ( labelText, delimiter );
			//now build a list of strings wrt brokenLabelText
			ArrayList brokenLabelTextCompilation = new ArrayList ( );	
				//add html starting tag 
				brokenLabelTextCompilation.add ( "<HTML>" );	
				//loop, brokenLabelTextCompilation, building in terms of its components
				for ( int i = 0; i < brokenLabelText.length; i ++ )
					brokenLabelTextCompilation.add ( "" + brokenLabelText [ i ] + "<BR>" );
				//add html ending tag
				brokenLabelTextCompilation.add ( "</HTML>" );	
			//now build a string of final results wrt brokenLabelTextCompilation
			String finalLabelText = "";
			for ( int i = 0; i < brokenLabelTextCompilation.size ( ); i ++ )
				finalLabelText += ( String ) brokenLabelTextCompilation.get ( i );
			//now set Label wrt to finalLabelText content
			label.setText ( finalLabelText ); 
				
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelX ( generatedCustomComponentListVII ( ), true, 340, 20, 1, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }	
	
	//constructor : customizable one threaded frame, that has a manipuble text field, but no buttons, in addition to a shortened frame.
    public UNICODE_MessageBoxPanel ( Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, int customWidth, int customHeight, JFrame _messageBoxWindowFrame )
    {
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( customWidth, customHeight ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelV ( null, false, 1, 1, 1, 1, 1, null, null, null, 1, 1, backgroundColour, buttonOutlineColour, null, 1, 1, 1 );

		//add menu panel to this
		JPanel fieldPanel = new JPanel ( );
		
		//setup text field colour
		textField.setBackground ( textfieldBackgroundColour );
		textField.setForeground ( textfieldForegroundColour );

		//add text field  panel to this panel, and setup
		fieldPanel.add ( textField );
		fieldPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( fieldPanel );
		container.add ( menuPanel );
		fieldPanel.setVisible ( true );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable one threaded frame, that has a label, but no buttons, in addition to a shortened frame.
    public UNICODE_MessageBoxPanel ( Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, String labelText, int customWidth, int customHeight, JFrame _messageBoxWindowFrame )
    {
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( customWidth, customHeight ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelV ( null, false, 1, 1, 1, 1, 1, null, null, null, 1, 1, backgroundColour, buttonOutlineColour, null, 1, 1, 1 );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );
		
		//establish label
		label = new JLabel ( labelText );
	
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );

		//add text field  panel to this panel, and setup
		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		labelPanel.setVisible ( true );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable one threaded frame, that has a manipuble text field, but no buttons, in addition to a shortened frame.
    public UNICODE_MessageBoxPanel ( Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color fieldLabelForegroundColour, int customWidth, int customHeight, JFrame _messageBoxWindowFrame, JLabel fieldLabel )
    {
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( customWidth, customHeight ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelV ( null, false, 1, 1, 1, 1, 1, null, null, null, 1, 1, backgroundColour, buttonOutlineColour, null, 1, 1, 1 );

		//add menu panel to this
		JPanel fieldPanel = new JPanel ( );
		
		//setup text field colour
		textField.setBackground ( textfieldBackgroundColour );
		textField.setForeground ( textfieldForegroundColour );
		fieldLabel.setForeground ( fieldLabelForegroundColour );

		//add text field  panel to this panel, and setup
		fieldPanel.add ( fieldLabel );
		fieldPanel.add ( textField );
		
		fieldPanel.setLayout ( new BoxLayout ( fieldPanel, BoxLayout.Y_AXIS ) );
		
		fieldPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( fieldPanel );
		container.add ( menuPanel );
		fieldPanel.setVisible ( true );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }	
	

	
	
	
	//constructor : customizable one threaded frame....for box with two threads, and label 
	//(HOWEVER, there are only two buttons on this panel in comparison to the signature which almost mirros this one.
	//A differentiating param is added last to differenentiate these. This value doesn't matter.
    public UNICODE_MessageBoxPanel ( int signatureDifferentiator, Thread _thread0, Thread _thread1, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        thread1 = _thread1;	
		
		//establish label
		label = new JLabel ( labelText );
		
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
		
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelVI ( generatedCustomComponentListI ( ), true, 230, 20, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable one threaded frame....one button, one thread, label, and a display View Panel
	//but the okay button does not quit the box
    public UNICODE_MessageBoxPanel ( Thread _thread0, Thread _thread1, String labelText, JPanel itemPanel, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {		
		//establish thread
		thread0 = _thread0;
		thread1 = _thread1;
	
		//establish label
		label = new JLabel ( labelText );
		
	
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelVII ( generatedCustomComponentListVI ( ), true, 230, 10, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, 48, 48, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( itemPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
		
	//constructor : customizable one threaded frame....one button, one thread, label, and a display View Panel
	//but the okay button does not quit the box
    public UNICODE_MessageBoxPanel ( Thread _thread0, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, Color fieldLabelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue, int maxFields, Color textfieldBackgroundColour, Color textfieldForegroundColour, int fieldWidth, int fieldHeight, int boxWidthGrowth, ArrayList <String> fieldLabelList )
    {		
		//establish thread
		thread0 = _thread0;
		
		//establish label
		label = new JLabel ( labelText );
		
		//establish list of labels for fields
			//declare
			ArrayList <JLabel> fieldLabels = new ArrayList <JLabel> ( );
			//define
			for ( int i = 0; i < maxFields; i ++ )
				fieldLabels.add ( new JLabel ( fieldLabelList.get ( i ) ) );
		
	
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
		
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width + boxWidthGrowth, height ) );
		
		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		
		labelPanel.setBackground ( labelBackgroundColour );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelVIII ( generatedCustomComponentListV ( ), true, 340 + boxWidthGrowth, -20, 1, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		JPanel container = new JPanel ( );
		
		fieldList = new ArrayList <UNICODE_MessageBoxPanel> ( );
		
		for ( int i = 0; i < maxFields; i ++ )
			fieldList.add ( new UNICODE_MessageBoxPanel ( backgroundColour, buttonOutlineColour, textfieldBackgroundColour, textfieldForegroundColour, fieldLabelForegroundColour, fieldWidth, fieldHeight, messageBoxWindowFrame, fieldLabels.get ( i ) ) );
	
		JPanel fieldListPanel = new JPanel ( );
		
		for ( int i = 0; i < maxFields; i ++ )
			fieldListPanel.add ( fieldList.get ( i ) );
			
		fieldListPanel.setLayout ( new BoxLayout ( fieldListPanel, BoxLayout.X_AXIS ) );
			
		container.add ( labelPanel );
		container.add ( fieldListPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable one threaded frame....for label, whose text may be customized. ONE BUTTON, ONE THREAD, BOX DISSAPEARS.
    public UNICODE_MessageBoxPanel ( int signatureDifferentiator, Thread _thread0, String labelText, Color backgroundColour, Color buttonOutlineColour, Color labelBackgroundColour, Color labelForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
		
		//establish label
		label = new JLabel ( labelText );
		
		//set label text colour and background
		label.setBackground ( labelBackgroundColour );
		label.setForeground ( labelForegroundColour );
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelIX ( generatedCustomComponentListVII ( ), true, 330, 20, 1, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel labelPanel = new JPanel ( );

		labelPanel.add ( label );
		labelPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( labelPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	
	//constructor : customizable one threaded frame, that has a manipuble text field and text area.
    public UNICODE_MessageBoxPanel ( String prebakedFieldText, String prebakedAreaText, Thread _thread0, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textAreaBackgroundColour, Color textAreaForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, 330 ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelII ( generatedCustomComponentListII ( ), true, 230, 20, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel fieldPanel = new JPanel ( );
		
		//setup text field
		textField.setText ( prebakedFieldText );
		textField.setBackground ( textfieldBackgroundColour );
		textField.setForeground ( textfieldForegroundColour );
		
		//setup text area
		textArea.setText ( prebakedAreaText );
		textArea.setBackground ( textAreaBackgroundColour );
		textArea.setForeground ( textAreaForegroundColour );


		//add text field and text area panel to this panel, and setup
		fieldPanel.add ( textField );
		fieldPanel.add ( textArea );
		fieldPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( fieldPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	
	//constructor : customizable one threaded frame, that has a manipuble text field and text area.
    public UNICODE_MessageBoxPanel ( String prebakedFieldText, String prebakedField1Text, String prebakedAreaText, Thread _thread0, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textfield1BackgroundColour, Color textfield1ForegroundColour, Color textAreaBackgroundColour, Color textAreaForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, 330 ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelII ( generatedCustomComponentListII ( ), true, 230, 20, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel fieldPanel = new JPanel ( );
		
		//setup text field
		textField.setText ( prebakedFieldText );
		textField.setBackground ( textfieldBackgroundColour );
		textField.setForeground ( textfieldForegroundColour );
		
		//setup text field
		textField1.setText ( prebakedField1Text );
		textField1.setBackground ( textfield1BackgroundColour );
		textField1.setForeground ( textfield1ForegroundColour );
		
		//setup text area
		textArea.setText ( prebakedAreaText );
		textArea.setBackground ( textAreaBackgroundColour );
		textArea.setForeground ( textAreaForegroundColour );


		//add text field and text area panel to this panel, and setup
		fieldPanel.add ( textField );
		fieldPanel.add ( textField1 );
		fieldPanel.add ( textArea );
		fieldPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( fieldPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }

	
	//constructor : customizable one threaded frame, that has a manipuble text field and text area.
    public UNICODE_MessageBoxPanel ( Thread _thread0, String prebakedFieldText, String prebakedField1Text, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textfield1BackgroundColour, Color textfield1ForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, 80 ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelII ( generatedCustomComponentListII ( ), true, 230, 20, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel fieldPanel = new JPanel ( );
		
		//setup text field
		textField.setText ( prebakedFieldText );
		textField.setBackground ( textfieldBackgroundColour );
		textField.setForeground ( textfieldForegroundColour );
		
		//setup text field
		textField1.setText ( prebakedField1Text );
		textField1.setBackground ( textfield1BackgroundColour );
		textField1.setForeground ( textfield1ForegroundColour );

		//add text field and text area panel to this panel, and setup
		fieldPanel.add ( textField );
		fieldPanel.add ( textField1 );
		fieldPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( fieldPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
	//constructor : customizable one threaded frame, that has a manipuble text field and text area.
    public UNICODE_MessageBoxPanel ( Thread _thread0, String prebakedFieldText, Color backgroundColour, Color buttonOutlineColour, Color textfieldBackgroundColour, Color textfieldForegroundColour, Color textfield1BackgroundColour, Color textfield1ForegroundColour, JFrame _messageBoxWindowFrame, String buttonTextureDir, String buttonShapeType, int buttonWidth, int buttonHeight, int arcHeight, int arcDepth, int axisRadiusDisplacement, int lastButtonChopValue )
    {
        //establish threads
        thread0 = _thread0;
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, 80 ) );
		
		//establish menu panel
		menuPanel = new UNICODE_MessageBoxMenuPanelII ( generatedCustomComponentListII ( ), true, 230, 20, 2, 33, 1340 + axisRadiusDisplacement, "clockwise", "horizontal", buttonTextureDir, buttonWidth, buttonHeight, backgroundColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );

		//add menu panel to this
		JPanel fieldPanel = new JPanel ( );
		
		//setup text field
		textField.setText ( prebakedFieldText );
		textField.setBackground ( textfieldBackgroundColour );
		textField.setForeground ( textfieldForegroundColour );
		
		//add text field and text area panel to this panel, and setup
		fieldPanel.add ( textField );
		fieldPanel.setBackground ( backgroundColour );
		
		JPanel container = new JPanel ( );
		container.add ( fieldPanel );
		container.add ( menuPanel );
		
		container.setBackground ( backgroundColour );
		container.setLayout ( new BoxLayout ( container, BoxLayout.Y_AXIS ) );
		
		add ( container );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
	
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        //establish anti aliasing
        //anti_alias_manager.setupAntiAliasing ( graphics2d );
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
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.
    public ArrayList <Object> generatedCustomComponentListI ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
        value.add ( thread0 );
        value.add ( thread1 );
		value.add ( this );
		value.add ( messageBoxWindowFrame );
        
        return value;
    }
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.
    public ArrayList <Object> generatedCustomComponentListII ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
        value.add ( thread0 );
		value.add ( this );
		value.add ( messageBoxWindowFrame );
        
        return value;
    }
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.//boxxes with message only, and okay option. NO THREADS NECCESSARY.
    public ArrayList <Object> generatedCustomComponentListIII ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
		value.add ( this );
		value.add ( messageBoxWindowFrame );
        
        return value;
    }
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.//boxxes with message only, and four buttons, hence three threads
    public ArrayList <Object> generatedCustomComponentListIV ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
		value.add ( thread0 );
		value.add ( thread1 );
		value.add ( thread2 );
		value.add ( this );
		value.add ( messageBoxWindowFrame );
        
        return value;
    }
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.
    public ArrayList <Object> generatedCustomComponentListV ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
        value.add ( thread0 );
		value.add ( this );
        
        return value;
    }
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.
    public ArrayList <Object> generatedCustomComponentListVI ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
        value.add ( thread0 );
		value.add ( thread1 );
		value.add ( this );
        
        return value;
    }
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.//boxxes with message only, and four buttons, hence three threads
    public ArrayList <Object> generatedCustomComponentListVII ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
		value.add ( thread0 );
		value.add ( this );
		value.add ( messageBoxWindowFrame );
        
        return value;
    }
	
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.//boxxes with message only, and four buttons, hence three threads
    public ArrayList <Object> generatedCustomComponentListVIII ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
		value.add ( thread0 );
		value.add ( thread1 );
		value.add ( thread2 );
		value.add ( this );
		value.add ( messageBoxWindowFrame );
		value.add ( thread3 );
        
        return value;
    }

	
    //mouse listener
    private class mouseListening implements MouseListener, MouseMotionListener
    { 
        public void mouseClicked ( MouseEvent mEvent )
        {
			
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
     
        public void mousePressed ( MouseEvent mEvent )
        {
            //establish mouse pressed coords
            held_mouse_coords = mEvent.getPoint ( );
			repaint ( );
        }
        
        public void mouseDragged ( MouseEvent mEvent )
        {         
            //establish dragged mouse coordinates
            dragged_mouse_coords = mEvent.getLocationOnScreen ( );    
            messageBoxWindowFrame.setLocation ( ( int ) ( dragged_mouse_coords.getX ( ) - held_mouse_coords.getX ( ) ), ( int ) ( dragged_mouse_coords.getY ( ) - held_mouse_coords.getY ( ) ) );
			repaint ( );
        }
        public void mouseMoved ( MouseEvent mEvent )
        {
		}
    }
	
	
	
	public JTextField getTextField ( )
	{
		return textField;
	}
	
	public String getTextFieldText ( )
	{
		return textField.getText ( );
	}
	
	public JTextField getTextField1 ( )
	{
		return textField1;
	}
	
	public String getTextField1Text ( )
	{
		return textField1.getText ( );
	}
	
	public JTextArea getTextArea ( )
	{
		return textArea;
	}
	
	public String getTextAreaText ( )
	{
		return textArea.getText ( );
	}

	public void setTextFieldText ( String value )
	{
		textField.setText ( value );
	}
	
	public void setTextField1Text ( String value )
	{
		textField1.setText ( value );
	}
	
	public void setTextAreaText ( String value )
	{
		textArea.setText ( value );
	}
	
	public String getLabelText ( )
	{
		return label.getText ( );
	}	
	public void setLabelText ( String value )
	{
		label.setText ( value );
		label.repaint ( );
	}	
	public ArrayList <UNICODE_MessageBoxPanel> getFieldList ( )
	{
		return fieldList;
	}
	
	//mutators
		//set threads
		public void setThread0 ( Thread value )
		{
			thread0 = value;
		}
		//set threads
		public void setThread1 ( Thread value )
		{
			thread1 = value;
		}
		//set threads
		public void setThread2 ( Thread value )
		{
			thread2 = value;
		}
}
