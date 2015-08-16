//Author: Jordan Micah Bennett
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Color;
import java.util.ArrayList;


import data.packages.UNICODE.*;


public class VISUAL_MENU_BUTTON_DOCK extends GENERIC_MENU_BUTTON_DOCK
{    
    //constructor
    public VISUAL_MENU_BUTTON_DOCK ( ArrayList <Object> customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, Color bgColour, Color buttonOutlineColour, String buttonShapeType, int arcHeight, int arcDepth, int lastButtonChopValue )
    {
        super ( customComponentList, menuVisibility, _xCoord, _yCoord, _MAXIMUM_BUTTONS, BUTTON_PROXIMITY, AXIS_PROXIMITY, axisDirection, axisLayoutType, buttonListDirectory, buttonWidth, buttonHeight, bgColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );
    }
    
    //abstract method
    public void mouseClickedExtendedDefinition ( MouseEvent mEvent )
    { 
        //first button -- detect DIGIT AFTER DIGIT PAINTING
        if ( getMenu ( ).getButtonList ( ).get ( 0 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.demonstrationLayer.NEURAL_NETWORK.propagateProgressively ( guiPanel.demonstrationLayer.SUPERVISION_LAYER.getInputs ( "data/images/temporary/_synonymous.bmp::......" ) );
            guiPanel.consoleField.updateLine ( 8, guiPanel.demonstrationLayer.NEURAL_NETWORK.getOutcomes ( ).toString ( ) + " on [ proximally-freely-drawn-selection ]" );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }
        
        //second button - clear DIGIT PAINTING
        if ( getMenu ( ).getButtonList ( ).get ( 1 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            guiPanel.PAINT_PANEL.clear ( );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }
        
        //third button - redo proximal change digit painting
        if ( getMenu ( ).getButtonList ( ).get ( 2 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            guiPanel.PAINT_PANEL.redo ( );
        }    
        
        
        //fourth button - undo proximal change digit painting
        if ( getMenu ( ).getButtonList ( ).get ( 3 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            guiPanel.PAINT_PANEL.undo ( );
        }    
        

        //fifth button - dotted/non-dotted brush style
        if ( getMenu ( ).getButtonList ( ).get ( 4 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.PAINT_PANEL.CONTINUOUS_MODE = guiPanel.PAINT_PANEL.CONTINUOUS_MODE ? false : true; //switch modes
            
            if ( !guiPanel.PAINT_PANEL.CONTINUOUS_MODE )
                new UNICODE_MessageBoxWindow ( true, "Continuous brush style / non-dotted, enabled via dragging mouse   ", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 );    
            else
                new UNICODE_MessageBoxWindow ( true, "Non-continuous brush style / dotted, enabled via clicking", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 );    
                
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }     
        
        //sixth button - quantize/store neural network data ( cortical columns )
        if ( getMenu ( ).getButtonList ( ).get ( 5 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.demonstrationLayer.NEURAL_NETWORK.quantize ( "data/config/auto.encodement.visual.data" );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }     
        
        //seventh button - recall neural network data ( cortical columns ) 
        if ( getMenu ( ).getButtonList ( ).get ( 6 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.demonstrationLayer.NEURAL_NETWORK.recall ( "data/config/auto.encodement.visual.data" );
            
            if ( ! ( new UNICODE_ConveniencePack ( ).loadSerializable ( "data/config/auto.encodement.visual.data" ) == null ) )
                guiPanel.consoleField.updateLine ( 8, guiPanel.demonstrationLayer.NEURAL_NETWORK.getOutcomes ( ).toString ( ) + " on [ recall ]" );
            else
                new UNICODE_MessageBoxWindow ( true, "No quantization encountered!. Quantize via 'quantize' toggle.", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 );

            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }   
        
        //eigth button - toggle instruction cycle
        if ( getMenu ( ).getButtonList ( ).get ( 7 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.demonstrationLayer.run ( );
            
            guiPanel.consoleField.updateLine ( 8, guiPanel.demonstrationLayer.NEURAL_NETWORK.getOutcomes ( ).toString ( ) + " on [ instruction-cycle ]" );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }
        
        //ninth button - toggle logical operation mode, for xor detection
        if ( getMenu ( ).getButtonList ( ).get ( 8 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            new UNICODE_ConveniencePack ( ).printFile ( "1", "data/config/mode.txt" );
            
            new UNICODE_MessageBoxWindow ( true, "Logical operation computation mode enabled. Restart program to utilize.", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }        
        //exit button
        if ( getMenu ( ).getButtonList ( ).get ( LAST_BUTTON ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            audioPlayer.playAudio ( "" + getAudioByAlias ( "t" ) ); 
            System.exit ( 0 );
        }
        
        repaint ( );
    }

    public void mouseMovedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseEnteredExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseExitedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseDraggedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseReleasedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mousePressedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseWheelRolledExtendedDefinition ( MouseWheelEvent mwEvent )
    {
    }


    
    //extra rendering
    public void drawMore ( )
    {
    }
}  