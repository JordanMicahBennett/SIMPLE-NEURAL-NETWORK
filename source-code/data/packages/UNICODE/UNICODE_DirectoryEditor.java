package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import javax.swing.Timer;
import java.awt.Desktop;

public class UNICODE_DirectoryEditor 
{
    //attributes
        //directory editing
            //single dir
            private File old_directory = null, new_directory = null;
            private boolean operationEnquiry = false; //tags boolean to file duplication operation
			
    //constructor
    public UNICODE_DirectoryEditor ( )
    {
    }
    
    //methods
        //accessors
        public boolean getOperationEnquiry ( )
        {
            return operationEnquiry;
        }
        //mutators
        public void setOperationEnquiry ( boolean value )
        {
            operationEnquiry = value;
        }
        
        //misc
            //function to change a single specified directory name with new user specified directory name
            public void changeDirName ( String old_dir_name, String new_dir_name )
            {
                //establish old directory
                File old_directory = new File ( old_dir_name );
                //establish new directory
                File new_directory = new File ( new_dir_name );
                //rename old directory wrt new directory
                old_directory.renameTo ( new_directory );
            }
            
            //function to duplicate files.
            //Thanks to mkYong : http://www.mkyong.com/java/how-to-copy-directory-in-java/
            public void folderDuplicationFunction ( File source_directory, File destination_directory, Timer load_timer ) throws IOException
            {
                //if the source entered is a valid file
                if ( source_directory.isDirectory ( ) )
                {
                    //establish destination directory - create if non-existent!
                    if ( !destination_directory.exists ( ) )
                        destination_directory.mkdir ( );
                        
                    //next, get the list of files in source directory
                    String [ ] source_files = source_directory.list ( );
        
                    //now generate that same list of source file name directories
                    //for the new file directory
                    for ( String source_file : source_files )
                    {
                        File source_dir_gen = new File ( source_directory, source_file );
                        File destination_dir_gen = new File ( destination_directory, source_file );
                        //recursively generate source names, so new destination names can be created per source
                        folderDuplicationFunction ( source_dir_gen, destination_dir_gen, load_timer );
                    }
                }
                //above I duplicated directories inclusive of file names, NOT the actual files.
                //this is what this else block is for.
                else
                {
                    InputStream source_is = new FileInputStream ( source_directory );
                    OutputStream destination_os = new FileOutputStream ( destination_directory );
                    byte [ ] buffer = new byte [ 1024 ];
                    int length = 0;
                    while ( ( length = source_is.read ( buffer ) ) > 0 )
                    {
                        destination_os.write ( buffer, 0, length );
                    }
                    source_is.close ( );
                    destination_os.close ( );
                    load_timer.stop ( );
                    setOperationEnquiry ( false );
                }
            }
            
            
            public void duplicateFolder ( String source_name, String destination_name, Timer load_timer )
            {
                //establish directories
                File source_directory = new File ( source_name );
                File destination_directory = new File ( destination_name );
                load_timer.start ( );
                setOperationEnquiry ( true );
                try
                {
                    folderDuplicationFunction ( source_directory, destination_directory, load_timer );
                }
                catch ( Exception error )
                {
                }
            }
            
            
            //delete by file directory
            public boolean removeFolder ( File directory )
            {
                if ( directory.isDirectory ( ) )
                {
                    String [ ] sub_components = directory.list ( );
                    for ( String sub_comp : sub_components )
                    {
                        boolean deletion_success = removeFolder ( new File ( directory, sub_comp ) );
                        
                        if ( !deletion_success )
                            return false;
                    }
                }
                return directory.delete ( );
            }   
			
			//open directory based on supplied location
			public void openFolder ( String fileStream ) 
			{
				File directory = new File ( fileStream );
				Desktop desktop = Desktop.getDesktop ( );
				
				try { desktop.open ( directory ); } catch ( Exception error ) { };
			}
}
