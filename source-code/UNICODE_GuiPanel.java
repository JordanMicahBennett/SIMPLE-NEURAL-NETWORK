//Author: Jordan Micah Bennett
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.Dimension;
import java.util.ArrayList;

import data.packages.UNICODE.*;

public class UNICODE_GuiPanel extends JPanel
{
    //global general variables
        //establish frame connection
        public JFrame applicationFrame; //will allow repositioning of frame by user
     
        //establish button docks
        private int applicationWidth, applicationHeight;
            //button dimension
            public int BUTTON_SPAN = 84;
            //main menu button dock 
            public GENERIC_MENU_BUTTON_DOCK menuButtonDock;
            public int MAXIMUM_BUTTONS = 10; //MMBD = MAIN MENU BUTTON DOCK
         
        //establish demonstration layer
        public GenericDemonstrationLayer demonstrationLayer;
        
        private UNICODE_AnimatedBubble animatedBubble;
        private Color animatedBubbleColour;
        public UNICODE_ConsoleField consoleField;
        
        public int UX_MODE = -1;
    
    //SPECIFIC COMPONENTS [0] -- UX_MODE = 0 = Visual Digit Detection Components
        //paint panel on which detection digits are drawn
        public UNICODE_PaintPanel PAINT_PANEL;
    
  
    //SPECIFIC COMPONENTS [1] -- UX_MODE = 1 = Logical Operation Computation Components
        //checkbox menu
            //maximum
            private int maxCheckboxes = 4;
            //labels
            private ArrayList checkboxLabels = new ArrayList ( );
            //platform
            public CHECKBOX_STRUCTURE checkboxPlatform;
        
        
    public UNICODE_GuiPanel ( JFrame UNICODE_frame, int applicationWidth, int applicationHeight )
    {
        //define application frame
        applicationFrame = UNICODE_frame;
        this.applicationWidth = applicationWidth;
        this.applicationHeight = applicationHeight;

        //define bg colour
        setBackground ( Color.white ); //so black buttons outline is visible
        
        
        ////////////////////////////////////////////////////
        //define UX_MODE 0[visual digit detection], 1[xor logical operation computer]
        UX_MODE = Integer.parseInt ( new UNICODE_ConveniencePack ( ).getFileContent ( "data/config/mode.txt" ).replace ( "\n", "" ).replace ( " ", "" )  ) == 0 ? 0 : 1;
        

        
        ////////////////////////////////////////////////////
        //SPECIFIC COMPONENTS [0] -- UX_MODE = 0 = Visual Digit Detection Components
        //define demonstration layer
        if ( UX_MODE == 0 )
        {
            demonstrationLayer = new VisualDemonstrationLayer ( false, 100 );
            
            //UNICODE_MenuPanel ( ArrayList <Object> _customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, Color bgColour, Color buttonOutlineColour, String _buttonShapeType,  int arcHeight, int arcDepth, int lastButtonChopValue )
            menuButtonDock = new VISUAL_MENU_BUTTON_DOCK ( generatedCustomComponentList ( ), true, 120, 80, MAXIMUM_BUTTONS, 60, 2220, "clockwise", "horizontal", "data/images/main menu/visual/", BUTTON_SPAN, BUTTON_SPAN, Color.white, Color.white, "rr", 0, 0, BUTTON_SPAN/3 );       

            PAINT_PANEL = new UNICODE_PaintPanel ( 40, 1, 90, Color.black, Color.white, new Color ( 17, 34, 51 ), applicationHeight );
            PAINT_PANEL.setup ( "data/images/temporary/_synonymous.bmp", "bmp", true, new Dimension ( 32, 32 ) );
            
            add ( PAINT_PANEL );
        }
        
        
        ////////////////////////////////////////////////////
        //SPECIFIC COMPONENTS [1] -- UX_MODE = 1 = Logical Operation Computation Components
        else
        {
            demonstrationLayer = new LogicalOperationDemonstrationLayer ( false );
            
            //UNICODE_MenuPanel ( ArrayList <Object> _customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, Color bgColour, Color buttonOutlineColour, String _buttonShapeType,  int arcHeight, int arcDepth, int lastButtonChopValue )
            menuButtonDock = new LOGICAL_OPERATION_MENU_BUTTON_DOCK ( generatedCustomComponentList ( ), true, 120, 80, MAXIMUM_BUTTONS, 60, 2220, "clockwise", "horizontal", "data/images/main menu/logical operation/", BUTTON_SPAN, BUTTON_SPAN, Color.white, Color.white, "rr", 0, 0, BUTTON_SPAN/3 );       
     
            
            //////////////////////////////////////////////
            //CHECBOX platform
            //////////////////////////////////////////////   
                checkboxPlatform = new CHECKBOX_STRUCTURE ( 0, maxCheckboxes, this, 1, 10, 10 );
                //generate some checbox labels
                checkboxLabels.add ( "0,0" );
                checkboxLabels.add ( "0,1" );
                checkboxLabels.add ( "1,0" );
                checkboxLabels.add ( "1,1" );


                //add some boxes
                for ( int boxes = 0; boxes < maxCheckboxes; boxes ++ )
                    checkboxPlatform.addBox ( "data/images/checkbox menu/", ( String ) checkboxLabels.get ( boxes ), false, "default.png", "hovered.png", "selected.png", "hovered selection.png" );
                //tell checkbox platforms to setup checboxes wrt to this panel, and establish panel wrt to 
                //this background's colour.
                checkboxPlatform.setup ( getBackground ( ), Color.black );   
        }
        
         
        ////////////////////////////////////////////////////
        //GLOBAL GENERAL COMPONENTS    
        animatedBubble = new UNICODE_AnimatedBubble ( applicationWidth / 3 - ( 74 / 2 * 2 + ( 74 - 22 ) ), 280 / 2 - ( 74 / 3 + 4 ), 74, 74, this ); //for 74 = bubble span
        animatedBubbleColour = new Color ( 0, 0, 0 );
        
        consoleField = new UNICODE_ConsoleField ( applicationWidth / 3, 190, 14, 80, "outcome:", ".", false, false, false, 4, "metro.ttf", Color.black );
        String consoleFieldTitle = UX_MODE == 1 ? "logical operation [xor] detection cycle" : "visual [digit] detection cycle", initializationSelection = UX_MODE == 1 ? "source-code/data/config/logical operation/xor.ini" : "source-code/data/config/visual/small.ini";
        consoleField.addLine ( " . outcome : ", consoleFieldTitle );
        consoleField.addLine ( " . outcome : ", "demonstration layer initialized..." );
        consoleField.addLine ( " . outcome : ", "neurons successfully constructed and supervised..." );
        consoleField.addLine ( "", "" );
        demonstrationLayer.run ( );
        consoleField.addLine ( " . outcome : ", demonstrationLayer.NEURAL_NETWORK.getTopologyCardinalityMessage ( ) );
        consoleField.addLine ( " . outcome : ", demonstrationLayer.NEURAL_NETWORK.getNeuronCardinalityMessage ( ) );
        consoleField.addLine ( "", "" );
        consoleField.addLine ( " . outcome : ", "{ { \u03B7 , \u03B1 } ( " + demonstrationLayer.NEURAL_NETWORK.eta + "," + demonstrationLayer.NEURAL_NETWORK.alpha + " ) }" );
        consoleField.addLine ( " . outcome : ", "" + demonstrationLayer.NEURAL_NETWORK.getOutcomes ( ) + " on [ " + initializationSelection + " ]" );
        consoleField.addLine ( " . outcome : ", "e, " + demonstrationLayer.NEURAL_NETWORK.netError );
        
        add ( menuButtonDock );
    }
    
    //add custom components which don't exist in UNICODE_MenuPanel by default,
    //where button click response is concerned.
    public ArrayList <Object> generatedCustomComponentList ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
        value.add ( this );
        
        return value;
    }

    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
    
        //define dock dimensions   
        menuButtonDock.setBounds ( ( applicationWidth / 2 - ( BUTTON_SPAN * MAXIMUM_BUTTONS ) / 2 ), applicationHeight - 120, applicationWidth, applicationHeight );   
        
        //render animated bubble
        animatedBubble.draw ( graphics2d, animatedBubbleColour );
        
        //render console field
        consoleField.draw ( graphics, graphics2d );
        
        //paint bounds
        if ( UX_MODE == 0 )
            PAINT_PANEL.setBounds ( 0, ( applicationHeight / 3 ), applicationWidth, applicationHeight / 2 );
        else
            //establish checkboxPlatform dimensions   
            checkboxPlatform.PLATFORM.setBounds ( applicationWidth / 2 - checkboxPlatform.PLATFORM.getWidth ( ) / 2, 300, checkboxPlatform.PLATFORM.getWidth ( ), checkboxPlatform.PLATFORM.getHeight ( ) ); 
    }
}

