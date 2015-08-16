//Author: Jordan Micah Bennett
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Color;
import java.util.ArrayList;


import data.packages.UNICODE.*;


public class LOGICAL_OPERATION_MENU_BUTTON_DOCK extends GENERIC_MENU_BUTTON_DOCK
{    
    //constructor
    public LOGICAL_OPERATION_MENU_BUTTON_DOCK ( ArrayList <Object> customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, Color bgColour, Color buttonOutlineColour, String buttonShapeType, int arcHeight, int arcDepth, int lastButtonChopValue )
    {
        super ( customComponentList, menuVisibility, _xCoord, _yCoord, _MAXIMUM_BUTTONS, BUTTON_PROXIMITY, AXIS_PROXIMITY, axisDirection, axisLayoutType, buttonListDirectory, buttonWidth, buttonHeight, bgColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );
    }
    
    //abstract method
    public void mouseClickedExtendedDefinition ( MouseEvent mEvent )
    { 
        for ( int bI = 1; bI < LAST_BUTTON - 4; bI ++ )
            if ( getMenu ( ).getButtonList ( ).get ( bI ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
            {
                audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
                new UNICODE_MessageBoxWindow ( true, "not available in 0.0.0.1", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 );    
            }
            
        //first button -- detect xor outcome based on checkbox label selection
        if ( getMenu ( ).getButtonList ( ).get ( 0 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            //derive input for facing unsupervised run, with respect to ux checkbox selection.
            String unsupervisedInput = "";
            for ( int cI = 0; cI < guiPanel.checkboxPlatform.getCheckboxes ( ).size ( ); cI ++ )
                if ( guiPanel.checkboxPlatform.getCheckboxes ( ).get ( cI ).getSelectedObjects ( ) != null )
                    unsupervisedInput += "" + guiPanel.checkboxPlatform.getCheckboxes ( ).get ( cI ).getLabel ( );
            
            //unsupervised run!
            guiPanel.demonstrationLayer.NEURAL_NETWORK.propagateProgressively ( guiPanel.demonstrationLayer.SUPERVISION_LAYER.getInputs ( unsupervisedInput.replace ( " ", "" ) + "::" ) ); 
            
            //reveal outcome in ux display
            guiPanel.consoleField.updateLine ( 8, guiPanel.demonstrationLayer.NEURAL_NETWORK.getOutcomes ( ).toString ( ) + " on [ checkbox-selection [ " + unsupervisedInput + " ] ]" );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }
        
        //sixth button - toggle instruction cycle
        if ( getMenu ( ).getButtonList ( ).get ( 5 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.demonstrationLayer.run ( );
            
            guiPanel.consoleField.updateLine ( 8, guiPanel.demonstrationLayer.NEURAL_NETWORK.getOutcomes ( ).toString ( ) + " on [ instruction-cycle ]" );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }
        
        //seventh button - quantize/store neural network data ( cortical columns )
        if ( getMenu ( ).getButtonList ( ).get ( 6 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.demonstrationLayer.NEURAL_NETWORK.quantize ( "data/config/auto.encodement.logical.operation.data" );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }     
        
        //eigth button - recall neural network data ( cortical columns ) 
        if ( getMenu ( ).getButtonList ( ).get ( 7 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            UNICODE_GuiPanel guiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 0 );
            
            guiPanel.demonstrationLayer.NEURAL_NETWORK.recall ( "data/config/auto.encodement.logical.operation.data" );
            
            if ( ! ( new UNICODE_ConveniencePack ( ).loadSerializable ( "data/config/auto.encodement.logical.operation.data" ) == null ) )
                guiPanel.consoleField.updateLine ( 8, guiPanel.demonstrationLayer.NEURAL_NETWORK.getOutcomes ( ).toString ( ) + " on [ recall ]" );
            else
                new UNICODE_MessageBoxWindow ( true, "No quantization encountered!. Quantize via 'quantize' toggle.", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 );

            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }      
        
        //ninth button - toggle visual mode, for visual digit detection
        if ( getMenu ( ).getButtonList ( ).get ( 8 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            new UNICODE_ConveniencePack ( ).printFile ( "0", "data/config/mode.txt" );
            
            new UNICODE_MessageBoxWindow ( true, "Visual digit detection mode enabled. Restart program to utilize.", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), false, "data/images/message box/okay/","rr", 64, 64, 0, 0, 0, 0 );
            
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