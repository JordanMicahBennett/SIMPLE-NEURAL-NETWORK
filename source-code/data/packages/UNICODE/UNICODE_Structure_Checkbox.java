package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;


public class UNICODE_Structure_Checkbox extends JCheckBox
{
    //attributes
	private String directory = null;
    
    
    //constructor 1 - default "data/images/ directory.
    public UNICODE_Structure_Checkbox ( String _directory, String label, boolean selected, String icon_name, String rollover_icon_name, String selected_icon_name, String rollover_selected_icon_name )
    {
		directory = _directory;
        setVisuals ( label, selected, icon_name, rollover_icon_name, selected_icon_name, rollover_selected_icon_name );	
    }
	
	//methods
		//accessors
		public String getDirectory ( )
		{
			return directory;
		}
		//mutators
		public void setDirectory ( String value )
		{
			directory = value;
		}  
    
    //set icon images
    protected void setVisuals ( String label, boolean selected, String icon_name, String rollover_icon_name, String selected_icon_name, String rollover_selected_icon_name )
    {
        try
        {
            ImageIcon iicon = new ImageIcon ( getDirectory ( ) + icon_name );
            ImageIcon riicon = new ImageIcon ( getDirectory ( ) + rollover_icon_name );
            ImageIcon siicon = new ImageIcon ( getDirectory ( ) + selected_icon_name );
            ImageIcon rsiicon = new ImageIcon ( getDirectory ( ) + rollover_selected_icon_name );
            //establish visual appearance 
            setIcon ( iicon );
            setRolloverIcon ( riicon );
            setSelectedIcon ( siicon );
            setRolloverSelectedIcon ( rsiicon );
            //set label
            setLabel ( label );
            //establish whetehr checkbox is selected
            setSelected ( selected );
        }
        catch ( Exception error )
        {
        }
    }
	
	//set label
}
