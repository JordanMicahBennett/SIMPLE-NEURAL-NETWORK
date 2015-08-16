package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class UNICODE_FileSelection
{
    //attributes
    private JFileChooser selector = null;
    private String directory = null;
    private boolean selection_enquiry = false;
    private File [ ] multiple_selection_data = null;
     
    //constructor
    public UNICODE_FileSelection ( String initial_directory )
    {
        selector = new JFileChooser ( initial_directory );
    }
    
    //methods
        //accessors
            //get selected file
            public String getDirectory ( )
            {
                return getSelector ( ).getSelectedFile ( ).getAbsolutePath ( );
            }
			public String getSelectedFile ( )
			{
				return getSelector ( ).getSelectedFile ( ).getName ( );
			}
            public JFileChooser getSelector ( )
            {
                return selector;
            }
            public boolean getSelectionEnquiry ( )
            {
                return selection_enquiry;
            }
            public File [ ] getMultipleSelectionData ( )
            {
                return multiple_selection_data;
            }
            public void setMultipleSelectionData ( File [ ] value )
            {
                multiple_selection_data = value;
            }
        //mutators
            public void setSelectionEnquiry ( boolean value )
            {
                selection_enquiry = value;
            }
    //misc
    public void initDirectorySearch ( )
    {
        //set file selection mode
        getSelector ( ).setFileSelectionMode ( JFileChooser.DIRECTORIES_ONLY );
        selector.setMultiSelectionEnabled ( false );
        getSelector ( ).showSaveDialog ( null );
    }
	
    //misc
    public void initFileSearch ( JFrame application_frame )
    {
        //set file selection mode
        selector.setMultiSelectionEnabled ( true );
        getSelector ( ).showOpenDialog ( application_frame );
        //get files
        setMultipleSelectionData ( getSelector ( ).getSelectedFiles ( ) );
    }
}
