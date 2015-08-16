package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.io.File;
import java.io.PrintWriter;

public class UNICODE_ExecutableInvocation
{
    //attributes
    private Runtime runtime = null;
    
    //constructor
    public UNICODE_ExecutableInvocation ( )
    {
        //initialise runtime
        runtime = Runtime.getRuntime ( );
    }
    
    public void invokeExecutable ( String executable_directory, String executable_name )
    {
        try
        {
            Runtime.getRuntime ( ).exec ( executable_directory + executable_name );
        }
        catch ( Exception error )
        {
        }
    }
    
    public void invokeBatch ( String executable_directory, String batch_name )
    {
        try
        {
            //Runtime.getRuntime ( ).exec ( "cmd /c start " + executable_directory + batch_name );
            Runtime.getRuntime ( ).exec ( new String [ ] { "cmd.exe", "/c", executable_directory + batch_name } );
        }
        catch ( Exception error )
        {
        }
    }
    
    public void createBatchFromExecutable ( String executable_directory, String application_name, String executable_name )
    {
        try
        {
            PrintWriter pw = new PrintWriter ( new File ( executable_directory + executable_name ) );
            pw.write ( "start " + application_name );
            pw.close ( );
        }
        catch ( Exception error )
        {
        }
    }
    
    
}
