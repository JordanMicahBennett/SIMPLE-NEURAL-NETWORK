package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//NOTE This is not an actual compilet. This just utilizes standard javac utility.

public class UNICODE_Compiler
{
    //1.gets from a file, data representing a tempate of what a java executing batch file should contain.
    public ArrayList getGenericCompilationFileData ( String fileStream )
    {
        ArrayList value = new ArrayList ( );
        try
        {
            Scanner scanner = new Scanner ( new File ( fileStream ) );
            while ( scanner.hasNext ( ) )
                value.add ( scanner.nextLine ( ) );
            scanner.close ( );
        }
        catch ( Exception error ) { }
        return value;
    }

    
    //2.generates data, that will be used to create batch file to be executed, based on a generic template.
    //this template contains 3 important lines; for path setting, class generation, and java file execution.
    //the final line is arbitrary, and holds the screen.
    public ArrayList getSpecificCompilationFileData ( ArrayList genericCompilationFileData, String javacDirectory, String javaFileStream, String javaFileName, String batchFileName, int cmdWidth, int cmdHeight, String backgroundColour, String highlightColour )
    {
        ArrayList value = new ArrayList ( );
		

		value.add ( "color " + backgroundColour ); //setup colour scheme
		value.add ( "mode con lines=" + cmdWidth + " cols=" + cmdHeight );

        //SETUP javacPathGenerationLine DATA
                                                                   //set        path=directory 
        Pattern javacPathGenerationLinePattern = Pattern.compile ( "(.*)(\\s)(.*)(\\p{Punct})(.*)" );
        String javacPathGenerationLineInput = ( String ) genericCompilationFileData.get ( 0 );
        
        Matcher javacPathGenerationLineMatcher = javacPathGenerationLinePattern.matcher ( javacPathGenerationLineInput );
        if ( javacPathGenerationLineMatcher.find ( ) )
            value.add ( javacPathGenerationLineMatcher.replaceAll ( "$1$2$3$4" ) + ( char ) 34 + javacDirectory.replace ( ( char ) 47, ( char ) 92 ) + ( char ) 34 ); 
            //jNote: the ( char ) 34 means the '"' symbol.
            //I needed to do this, since directories with space would not execute unless embeded with quotes. 
            
        //SETUP javacClassGenerationLine DATA
                                                                  //javac          fileName.java 
        Pattern javacClassGenerationLinePattern = Pattern.compile ( "(.*)(\\s)(.*)(\\p{Punct})(.*)" );
        String javacClassGenerationLineInput = ( String ) genericCompilationFileData.get ( 1 );
        
        Matcher javacClassGenerationLineMatcher = javacClassGenerationLinePattern.matcher ( javacClassGenerationLineInput );
        if ( javacClassGenerationLineMatcher.find ( ) )
            value.add ( javacClassGenerationLineMatcher.replaceAll ( "$1$2" ) + ( char ) 34 + ( javaFileStream + javaFileName ).replace ( ( char ) 47, ( char ) 92 ) + javacClassGenerationLineMatcher.replaceAll ( "$4$5" ) + ( char ) 34 );
            
            
            
        //ADD COMMAND TO COPY CLASS TO CURRENT DIRECTORY    
        //SETUP javaClassRegistrationLine DATA
                                                                    //copy     src      dest
        Pattern javaClassRegistrationLinePattern = Pattern.compile ( "(.*)(\\s)(.*)(\\s)(.*)" );
        String javaClassRegistrationLineInput = ( String ) genericCompilationFileData.get ( 2 );
        
        Matcher javaClassRegistrationLineMatcher = javaClassRegistrationLinePattern.matcher ( javaClassRegistrationLineInput );
        if ( javaClassRegistrationLineMatcher.find ( ) ) //.replace ( char47,char92 ) replaces forward slashes with backward slashes. copy function in windows is compatible only with char 92 backward slash.
            value.add ( javaClassRegistrationLineMatcher.replaceAll ( "$1$2" ) + ( char ) 34 + javaFileStream.replace ( ( char ) 47, ( char ) 92 ) + javaFileName + ".class" + ( char ) 34 + javaClassRegistrationLineMatcher.replaceAll ( "$4" ) + ( char ) 34 + javaFileName + ".class" + ( char ) 34 ); 

		value.add ( "cls" ); //CLEAR ABOVE SEGEMENT, ON LY REVEALING RESULTS AFTER RUNNING java file.
		
        //SETUP javaFileExecutionLine DATA
                                                                 //java   fileName
        Pattern javaFileExecutionLinePattern = Pattern.compile ( "(.*)(\\s)(.*)" );
        String javaFileExecutionLineInput = ( String ) genericCompilationFileData.get ( 3 );
        
		value.add ( "@ECHO OFF" ); //HIDE COMMAND LINE EXECUTIONS
        Matcher javaFileExecutionLineMatcher = javaFileExecutionLinePattern.matcher ( javaFileExecutionLineInput );
        if ( javaFileExecutionLineMatcher.find ( ) )
		{
			UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
			String JavacOrJreVersion = conveniencePack.getMatchingValue ( javacDirectory.replace ( ( char ) 92, ( char ) 47 ), "jdk", "jre", "" +  ( char ) 47 );
			value.add ( "title jpencil compiler 1.0.0.0" );
			value.add ( "echo [javac OR jre version : " + JavacOrJreVersion + "]" );
			value.add ( "ECHO." ); //print line
            value.add ( javaFileExecutionLineMatcher.replaceAll ( "$1$2" ) + ( char ) 34 + javaFileName + ( char ) 34 ); 
			value.add ( "ECHO." ); //print line
		}
            //Lol, bushman discovers that the run command, does not require a file stream.
            //I use I intuitively sensed this, hence the creation of two separate input variables dealin with the name of the 
            //target file to be executed.
			

        //delete the compilation class file
        value.add ( "del " + ( char ) 34 + javaFileName + ".class" + ( char ) 34 );
		//delete the compilation batch file
        //value.add ( "del " + ( char ) 34 + batchFileName + ".bat" + ( char ) 34 );
        
        //show system pause
		value.add ( "ECHO." ); //print line
        value.add ( "pause" ); //will pause the screen after compilation is complete
		value.add ( "exit" ); //exit when key is toggled.
		
		//establish colour scheme function.
		value.add ( ":displayScheme" );
		value.add ( "%Windir%/System32/WindowsPowerShell/v1.0/Powershell.exe write-host -foregroundcolor " + highlightColour + " %1".replace ( ( char ) 47, ( char ) 92 ) );
        return value;
    }
    
    //3.Generates batch file based on compilation specifics.
    public void generateBatch ( ArrayList specificCompilationFileData, String fileStream )
    {
        try
        {
            PrintWriter printWriter = new PrintWriter ( fileStream );
            
            for ( int i = 0; i < specificCompilationFileData.size ( ); i ++ )
                printWriter.println ( specificCompilationFileData.get ( i ) );
            printWriter.close ( );
        }
        catch ( Exception error ) { }
    }
    
    
    //4.executes batch commands.
    //this is used to execute the batch file enerated above.
    public void executeBatch ( String command )
    {
        try
        {  
            Runtime runtime = Runtime.getRuntime ( );
            Process process = runtime.exec ( command );
            process.waitFor ( );
        }
        catch ( Throwable throwable ) { throwable.printStackTrace ( ); };
    }

    //5.Compile and run java class
    public void compileAndRun ( String compileTemplateStream, String javacStream, String spaceSymbol, String javaFileLocation, String javaFileName, String batchFileName, int cmdWidth, int cmdHeight, String backgroundColour, String highlightColour ) 
    {
        //1.establish generic compilation file data
        ArrayList genericCompilationFileData = getGenericCompilationFileData ( compileTemplateStream );
        
        
        //2.establish specific compilation file data, javac directory, and a java file.
        ArrayList specificCompilationFileData = getSpecificCompilationFileData ( genericCompilationFileData, javacStream.replace ( spaceSymbol, " " ), javaFileLocation, javaFileName, batchFileName, cmdWidth, cmdHeight, backgroundColour, highlightColour );
        
        //3.generate batch file with specific compilation data
        generateBatch ( specificCompilationFileData, batchFileName + ".bat" );
        
        //4.execute batch file generate above.
        executeBatch ( "cmd /c start " + batchFileName + ".bat" );
    }
}
