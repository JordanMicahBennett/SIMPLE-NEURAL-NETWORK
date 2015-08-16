package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.io.File;
import java.util.Date;
import jxl.*;
import jxl.write.*;

public class UNICODE_ExcelWriter
{
    //attributes
        //jxcel requirements
            //establish workbook
            private WritableWorkbook workbook = null;    
            //establish workbook sheet
            private WritableSheet sheet = null;
        //java requirements
            //row/column cardinality
            private int max_rows = 0;
            private int max_columns = 0;
     
    public UNICODE_ExcelWriter ( )
    {
    }
    
    //methods

        //mutators
            //dynamic
            public void outputData ( String filename, int current_sheet, int _max_rows, int _max_columns, String [ ] [ ] input_data )
            {
                //establish num rows and cols
                max_rows = _max_rows;
                max_columns = _max_columns;
                //open workbook
                try
                {
                    //define work book wrt user speficied file
                    workbook = Workbook.createWorkbook ( new File ( "data/excel/" + filename ) ); 
                    //define workbook sheet
                    sheet = workbook.createSheet ( "Schedule Output Table", current_sheet );

                    //pump data from java data structure to cells of table.
                    for ( int rows = 0; rows < max_rows; rows ++ )
                        for ( int cols = 0; cols < max_columns; cols ++ )
                        {
                            Label labels = new Label ( cols, rows, input_data [ rows ] [ cols ]  );
                            //pump label datas into the cells.
                            sheet.addCell ( labels );
                        }
                    workbook.write ( );
                    workbook.close ( );
                }
                catch ( Exception error )
                {
                }
            }    
}
