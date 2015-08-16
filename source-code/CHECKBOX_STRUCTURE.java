//Author: Jordan Micah Bennett

import data.packages.UNICODE.*;
import javax.swing.JPanel;

public class CHECKBOX_STRUCTURE extends UNICODE_Structure_CheckboxList
{
    public CHECKBOX_STRUCTURE ( int current_index, int max_boxxes, JPanel dest_pane, int _layout_type, int scrollUpRate, int scrollDownRate  ) 
    {
		super ( current_index, max_boxxes, dest_pane, _layout_type, scrollUpRate, scrollDownRate );
	}
}
