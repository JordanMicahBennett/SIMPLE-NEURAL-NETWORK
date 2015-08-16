package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.io.File;
import java.util.Date;
import jxl.*;


public class UNICODE_ExcelReader
{
    public UNICODE_ExcelReader ( )
    {
    }
    
    public String getDataString ( String filename, int current_sheet, int num_rows, int num_columns )
    {
        //establish workbook
        Workbook workbook = null;
        
        //open workbook
        try
        {
            workbook = Workbook.getWorkbook ( new File ( "data/excel/" + filename ) );      
        }
        catch ( Exception error )
        {
        }
        
        //establish workbook sheet
        Sheet sheet = workbook.getSheet ( current_sheet );
       
        //establish means to store data in jxcel data structure
        Cell row_jxcel_data [ ] [ ] = new Cell [ num_columns ] [ num_rows ];
        
        //establish means to pass this jxcel data into java data structure
        String row_java_data [ ] [ ] = new String [ num_columns ] [ num_rows ];
        
        //establish string which will be used to concatenate 2 dimensional data from  row_java_data, in the loop which
        //gathers data from excel table.
        String row_string = "";

        //pump data from jxcel data structure to java data structure
        for ( int rows = 0; rows < num_rows; rows ++ )
            for ( int cols = 0; cols < num_columns; cols ++ )
            {
                //pump row cell data into row cell array variable
                row_jxcel_data [ cols ] [ rows ] = sheet.getCell ( cols, rows );
                
                //pump row data cell array into 1d string array
                row_java_data [ cols ] [ rows ] = row_jxcel_data [ cols ] [ rows ].getContents ( );
                
                //equate row data to a string
                row_string += row_java_data [ cols ] [ rows ] + " ";   
            }
        //return data, so it can be used
        return row_string;
    }
	
	public String [ ] [ ] getDataArray ( String filename, int current_sheet, int num_rows, int num_columns )
	{
		String [ ] [ ] returnValue = null;
		
       //establish workbook
        Workbook workbook = null;
        
        //open workbook
        try
        {
            workbook = Workbook.getWorkbook ( new File ( "data/excel/" + filename ) );      
        }
        catch ( Exception error )
        {
        }
        
        //establish workbook sheet
        Sheet sheet = workbook.getSheet ( current_sheet );
       
        //establish means to store data in jxcel data structure
        Cell row_jxcel_data [ ] [ ] = new Cell [ num_columns ] [ num_rows ];
        
        //establish means to pass this jxcel data into java data structure
        String row_java_data [ ] [ ] = new String [ num_columns ] [ num_rows ];
        
        //pump data from jxcel data structure to java data structure
        for ( int rows = 0; rows < num_rows; rows ++ )
            for ( int cols = 0; cols < num_columns; cols ++ )
            {
				//pump row cell data into row cell array variable
				row_jxcel_data [ cols ] [ rows ] = sheet.getCell ( cols, rows );
				
				//pump row data cell array into 1d string array
				row_java_data [ cols ] [ rows ] = row_jxcel_data [ cols ] [ rows ].getContents ( );
            }
		
		returnValue = row_java_data;
		
		return returnValue;
	}
    
}
