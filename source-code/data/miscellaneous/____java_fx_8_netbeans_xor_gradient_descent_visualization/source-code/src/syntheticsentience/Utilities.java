/* 
    Author ~ Jordan Micah Bennett 
    A simplistic artificial neural network, that naturally computes xor-logic prediction, via stochastic gradient descent.
*/
package syntheticsentience;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utilities
{
	//attributes
    public Utilities ( )
    {
    }
    
    public Object loadSerializable ( String origin )
    {
		Object returnValue = null;
		try
		{
			FileInputStream fileInputStream = new FileInputStream ( origin );
			ObjectInputStream objectInputStream = new ObjectInputStream ( fileInputStream );
			returnValue = objectInputStream.readObject ( );
		}
		catch ( Exception error )
		{
		}
		return returnValue;
    }
    public void printSerializable ( Object input, String destination )
    {
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream ( destination );
			ObjectOutputStream objectOutputStream = new ObjectOutputStream ( fileOutputStream );
			objectOutputStream.writeObject ( input );
		}
		catch ( Exception error )
		{
		}
    }
    public ArrayList <String> getFileContents ( String fileStream )
    {
            ArrayList <String> value = new ArrayList <String> ( );
            try
            {
                    Scanner scanner = new Scanner ( new File ( fileStream ) );
                    while ( scanner.hasNext ( ) )
                            value.add ( scanner.nextLine ( ) );
            }
            catch ( Exception error ) { }
            return value;
    }
    public void invokeDialog ( Stage stage, String contentText )
    {
        Alert alert = new Alert ( Alert.AlertType.INFORMATION, "" );
                                 alert.initModality ( Modality.APPLICATION_MODAL );
                                 alert.initOwner ( stage );
                                 alert.getDialogPane ( ).setContentText ( contentText );
                                 alert.getDialogPane ( ).setHeaderText ( null );
                                 alert.showAndWait ( )
                                     .filter ( response -> response == ButtonType.OK )
                                     .ifPresent ( response -> System.out.println ( "The alert was approved" ) );
    }
}
