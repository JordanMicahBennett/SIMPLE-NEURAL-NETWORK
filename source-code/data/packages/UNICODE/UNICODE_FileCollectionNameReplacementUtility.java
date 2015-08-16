package data.packages.UNICODE;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UNICODE_FileCollectionNameReplacementUtility
{
    public static void main ( String identifierTag, String identifierFormat, String source, String replacementTag, String replacementSource, boolean internalReplacementMode, boolean externalReplacementMode, boolean combinedReplacementMode )
    {
        
        //~establish conv pack~
        UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
     
        
        //~get file names based on source~
        File fileCollectionDirectory = new File ( source );
        String [ ] fileCollectionNames = fileCollectionDirectory.list ( );
        
        
        //~derive file names of identifierFormat~
        ArrayList <String> fileCollectionNamesOfIdentifierFormat = new ArrayList <String> ( );
        String fileCollectionNamesOfIdentifierFormatStringAccumilator = "", fileCollectionNamesOfIdentifierFormatStringAccumilatorDelimiter = "::";
        int cardinalityOfNamesOfIdentifierFormat = 0;
        
        for ( int i = 0; i < fileCollectionNames.length; i ++ )
            if ( fileCollectionNames [ i ].indexOf ( identifierFormat ) != -1 ) //if file name indicates supplied file format
            {
                fileCollectionNamesOfIdentifierFormatStringAccumilator += fileCollectionNames [ i ] + fileCollectionNamesOfIdentifierFormatStringAccumilatorDelimiter;
                cardinalityOfNamesOfIdentifierFormat ++;
            }
            
        for ( int i = 0; i < cardinalityOfNamesOfIdentifierFormat; i ++ )
            fileCollectionNamesOfIdentifierFormat.add ( conveniencePack.getDelimitedArray ( fileCollectionNamesOfIdentifierFormatStringAccumilator, "", fileCollectionNamesOfIdentifierFormatStringAccumilatorDelimiter, cardinalityOfNamesOfIdentifierFormat ) [ i ] );
            

            
        //~read data wrt extracted file names of identifierFormat~
        ArrayList <String> contentCollection = new ArrayList <String> ( );
        for ( int i = 0; i < fileCollectionNamesOfIdentifierFormat.size ( ); i ++ )
        {
            String content = "";
            try
            {
                Scanner scanner = new Scanner ( new File ( source + fileCollectionNamesOfIdentifierFormat.get ( i ) ) );
                
                while ( scanner.hasNext ( ) )
                {
                    content += scanner.nextLine ( ) + "\n";
                    contentCollection.add ( i, content );
                }
                scanner.close ( );
            }
            catch ( Exception error )
            {
            }
        }
        
        //~create new files wrt new names~
        if ( !new File ( replacementSource ).exists ( ) )
            conveniencePack.makeDirectory ( replacementSource ); //if the directory doesn't exist, create it. 
            
        for ( int i = 0; i < fileCollectionNamesOfIdentifierFormat.size ( ); i ++ )
            if ( externalReplacementMode )
                conveniencePack.makePhysicalFile ( replacementSource + fileCollectionNamesOfIdentifierFormat.get ( i ).replace ( identifierTag, replacementTag ), contentCollection.get ( i ) );
            else if ( internalReplacementMode )
                conveniencePack.makePhysicalFile ( replacementSource + fileCollectionNamesOfIdentifierFormat.get ( i ), contentCollection.get ( i ).replace ( identifierTag, replacementTag ) );
            else if ( combinedReplacementMode )    
                conveniencePack.makePhysicalFile ( replacementSource + fileCollectionNamesOfIdentifierFormat.get ( i ).replace ( identifierTag, replacementTag ), contentCollection.get ( i ).replace ( identifierTag, replacementTag ) );
                
        System.out.println ( "RENAME CYCLE COMPLETE!" );
    }
}
