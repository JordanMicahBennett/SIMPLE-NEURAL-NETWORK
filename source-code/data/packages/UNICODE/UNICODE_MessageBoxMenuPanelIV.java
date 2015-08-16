package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import data.packages.UNICODE.*;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

public class UNICODE_MessageBoxMenuPanelIV extends UNICODE_MenuPanel
{
    //constructor
    public UNICODE_MessageBoxMenuPanelIV ( ArrayList <Object> customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, Color bgColour, Color buttonOutlineColour, String buttonShapeType, int arcHeight, int arcDepth, int lastButtonChopValue )
    {
        super ( customComponentList, menuVisibility, _xCoord, _yCoord, _MAXIMUM_BUTTONS, BUTTON_PROXIMITY, AXIS_PROXIMITY, axisDirection, axisLayoutType, buttonListDirectory, buttonWidth, buttonHeight, bgColour, buttonOutlineColour, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );
    }
    
    public void mouseClickedExtendedDefinition ( MouseEvent mEvent )
    {
        if ( getMenu ( ).getButtonList ( ).get ( 0 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
			audioPlayer.playAudio ( getAudioByAlias ( "e" ) );
			Thread thread = ( Thread ) getCustomComponentList ( ).get ( 0 );
			thread.start ( );
		}
        if ( getMenu ( ).getButtonList ( ).get ( 1 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
			audioPlayer.playAudio ( getAudioByAlias ( "e" ) );
			Thread thread = ( Thread ) getCustomComponentList ( ).get ( 1 );
			thread.start ( );
		}
        if ( getMenu ( ).getButtonList ( ).get ( 2 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
			audioPlayer.playAudio ( getAudioByAlias ( "e" ) );
			Thread thread = ( Thread ) getCustomComponentList ( ).get ( 2 );
			thread.start ( );
		}
        if ( getMenu ( ).getButtonList ( ).get ( 3 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
			audioPlayer.playAudio ( getAudioByAlias ( "t" ) );
			UNICODE_MessageBoxPanel messageBoxPanel = ( UNICODE_MessageBoxPanel ) getCustomComponentList ( ).get ( 3 );
			JFrame frame = ( JFrame ) getCustomComponentList ( ).get ( 4 );
			messageBoxPanel.setVisible ( false );
			frame.setVisible ( false );
		}
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
	
	public void drawMore ( )
	{
	}
}