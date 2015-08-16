package data.packages.UNICODE;
//:---------------------------------------------:
//:--: Author: Jordan Micah Bennett
//:--: Title: Complex Table Class
//:---------------------------------------------:
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.util.Scanner;
import java.awt.AlphaComposite;
import javax.swing.JViewport;
//texture painting
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.awt.TexturePaint;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import javax.swing.table.JTableHeader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.RoundRectangle2D;
import java.awt.Dimension ;
import javax.swing.JPanel;
import java.awt.print.PageFormat;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;

public class UNICODE_Structure_ComplexTable 
{
    //attributes
    private String [ ] column_labels;
    private String [ ] [ ] table_data;
    private JTable table;
    private DefaultTableModel table_model;
    private JScrollPane scroll_pane;
    private int item_count = - 1;
    private int deletion_index;
    private int currently_selected_row_index = - 1, currently_selected_column_index = - 1;
    private Color bgDefaultColour, bgHighlightedColour;
    private Color fgDefaultColour, fgHighlightedColour;
    private String index_content = "";
    private JPanel destination_panel; //needed to repaint panel on which a bushman complex table will be placed

    //instructions for use
    //[[[[i]]]].Describe Colour scheme (See. B.)
    //[[[[1]]]]. describe table columns ( Class.describeTableColumns )
    //[[[[2]]]]. initiate table ( Class.setupTable )
      //[[[[A]]]].To add row
      //use accessor to acces table model, and add row that way. eg. [] Class.getTableModel ( ).addRow ( new Object [ ] { } ) []
      //[[[[B]]]].To set default table row bg and fg, and highlighted row bg and fg ( background and font colour )
      //use Class.establishTableColourScheme ( bgDefaultColour, fgDefaultColour, bgHighlightedColour, fgHighlightedColour )
 
    
    //constructor
    public UNICODE_Structure_ComplexTable ( JPanel dest_pane )
    {
        destination_panel = dest_pane;
    }
    
    //methods
        //accesors        
        public int getItemCount( )
        {
            return item_count;
        }
        public int getDeletionIndex ( )
        {
            return deletion_index;
        }
        public JTable getTable ( )
        {
            return table;
        }
        public DefaultTableModel getTableModel ( )
        {
            return table_model;
        }
        public int getCurrentRowSelection ( )
        {
            return currently_selected_row_index;
        }
        public int getCurrentColumnSelection ( )
        {
            return currently_selected_column_index;
        }        
        public String getDataAtCurrentSelection ( )
        {
            return index_content;
        }
        public String [ ] getColumnLabels ( )
        {
            return column_labels;
        }
        
        public String [ ] [ ] getTableData ( ) 
        {
            return table_data;
        }
        public JScrollPane getScrollPane ( )
        {
            return scroll_pane;
        }
        public boolean getVisibility ( )
        {
            return scroll_pane.isShowing ( );
        }
        //mutators
        public void incrementItemCount ( )
        {
            item_count ++;
        }
        public void decrementItemCount ( )
        {
            item_count --;
        }     
      
        public void setCurrentRowSelection ( int value )
        {
            currently_selected_row_index = value;
        }
        public void setCurrentColumnSelection ( int value )
        {
            currently_selected_column_index = value;
        }         
        public void setVisible ( boolean value )
        {
            //set the table's container true/false -- visible/invisible
            scroll_pane.setVisible ( value );
        }
        public void setIndexContent ( String value )
        {
            index_content = value;
        }
        
        public void establishColourScheme ( Color bgDefault, Color fgDefault, Color bgHighlighted, Color fgHighlighted )
        {
            bgDefaultColour = bgDefault;
            fgDefaultColour = fgDefault;
            bgHighlightedColour = bgHighlighted;
            fgHighlightedColour = fgHighlighted;
        }
        
        //add row to table by index
        public void addRow ( String data_string, int index )
        {
			//establish column cardinality
			int columnCardinality = column_labels.length;
		
            //establish column component array
            String [ ] column_comp_array = new String [ columnCardinality ];
            
            //increment item count
            incrementItemCount ( );
            
            //scan the data string
            Scanner string_scanner = new Scanner ( data_string );
            
            //turn string into individual column components
            for ( int column_components = 0; column_components < columnCardinality; column_components ++ )
                column_comp_array [ column_components ] = string_scanner.next ( );
            
            
			//generate object array
			String objectArray [ ] [ ] = new String [ columnCardinality/*column cardinality*/ ] [ 1 /*row cardinality*/ ];
			
			for ( int columns = 0; columns < columnCardinality; columns ++ )
				objectArray [ columns ] [ 0 ] = column_comp_array [ columns ];
			
            
            //insert data into table via table model
			getTableModel ( ).insertRow ( index, objectArray );
		}
        //add row to table at end of table
        public void addRow ( String data_string )
        {
			//establish column cardinality
			int columnCardinality = column_labels.length;
		
            //establish column component array
            String [ ] column_comp_array = new String [ columnCardinality ];
            
            //increment item count
            incrementItemCount ( );
            
            //scan the data string
            Scanner string_scanner = new Scanner ( data_string );
            
            //turn string into individual column components
            for ( int column_components = 0; column_components < columnCardinality; column_components ++ )
                column_comp_array [ column_components ] = string_scanner.next ( );
            
            
			//generate object array
			String objectArray [ ] [ ] = new String [ columnCardinality/*column cardinality*/ ] [ 1 /*row cardinality*/ ];
			
			for ( int columns = 0; columns < columnCardinality; columns ++ )
				objectArray [ columns ] [ 0 ] = column_comp_array [ columns ];
			
            
            //insert data into table via table model
			getTableModel ( ).addRow ( objectArray );
		}   
		
        //add rows to table
        public void addRows ( String [ ] [ ] data, int rowCardinality, int initialRow )
        {
			//establish column cardinality
			int columnCardinality = column_labels.length;
	
            
			//populate with dummy data @ non-dummy information length
			for ( int rows = 0; rows < rowCardinality; rows ++ )
			{
				getTableModel ( ).addRow ( new Object [ ] { "" } );		
				incrementItemCount ( );		
			}
			
			//alter/populate with non-dummy information 
			for ( int columns = 0; columns < columnCardinality; columns ++ )
				for ( int rows = initialRow; rows < rowCardinality; rows ++ )
					getTableModel ( ).setValueAt ( data [ columns ] [ rows ], rows, columns );	
        } 
     
        //add image @ location
        public void addImage ( Image image, int columnIndex, int rowIndex )
        {
            //insert data into table via table model
			getTableModel ( ).setValueAt ( image, rowIndex, columnIndex );
        }       
		
		//add image icon @ location
        public void addImageIcon ( ImageIcon imageIcon, int columnIndex, int rowIndex )
        {
            //insert data into table via table model
			getTableModel ( ).setValueAt ( imageIcon, rowIndex, columnIndex );
        }       
		
        //remove row
        public void removeRow ( int index )
        {
            getTableModel ( ).removeRow ( index );
            decrementItemCount ( );
        }
        
        //remove row - removes last row
        public void removeRow ( )
        {
            getTableModel ( ).removeRow ( getItemCount ( ) );
            decrementItemCount ( );
        }
  
        //creators
            //column description
            public void describeColumns ( String [ ] column_description_array )
            {
                column_labels = column_description_array;
            }
            //column description creation
            public String [ ] makeColumnDescription ( String header_string, int how_many_headers )
            {
                String [ ] desc_array = new String [ how_many_headers ];
                Scanner header_string_scan = new Scanner ( header_string );
                
                for ( int h = 0; h < how_many_headers; h ++ )
                    desc_array [ h ] = header_string_scan.next ( );
                
                return desc_array;
            }
        
        //miscallaneus
        public void setup ( int rowHeight, final String table_bg_directory, final String table_bg_image, String alignment, String tableThumbStream, String tableTrackStream, Color tableBackgroundColour )
        {
         //table
            //make the table model ( with image icon and updatability support )
            table_model = new DefaultTableModel ( table_data, column_labels )
            {
                public Class getColumnClass ( int column_index )
                {
                    Object object = getValueAt ( item_count, column_index );
                    if ( object == null )
                        return Object.class;
                    else
                        return object.getClass ( );
                }
            };
            //make the table
            table = new JTable ( table_model )
            {

                //allows table data to be inserted, while governing how cells rect when selected
                //and unselected. also assigns deletion_index to the current row selected...to allow proper deletion
                public Component prepareRenderer ( TableCellRenderer tc_renderer, int row_index, int col_index )
                {
                    Component component = super.prepareRenderer ( tc_renderer, row_index, col_index );
                    component.setBackground ( bgDefaultColour );
                    component.setForeground ( fgDefaultColour );
                    
                    if ( isCellSelected ( row_index, col_index ) )
                    {
                        component.setBackground ( bgHighlightedColour );
                        component.setForeground ( fgHighlightedColour );  
                        deletion_index = row_index;
                        setCurrentRowSelection ( row_index );
                        setCurrentColumnSelection ( col_index );
                    }
                    else if ( !isCellSelected ( row_index, col_index ) )
                    {
                        component.setBackground ( bgDefaultColour);
                        component.setForeground ( fgDefaultColour );  
                        deletion_index = -10;
                        //currently_selected_row_index = -10;
                    }                
 
                    return component;
                }
                
                //disallows table data editing
                public boolean isCellEditable ( int row_index, int col_index )
                {
                    return false;
                }
            };
            
            
            //make the scroll pane
            scroll_pane = new JScrollPane ( table )
            {
                //establish texture paint
                    //establish paint bounds
                    Rectangle paint_bounds = new Rectangle ( table.getX ( ), table.getY ( ), table.getWidth ( ), table.getHeight ( ) );
                    UNICODE_Structure_TexturePaint tex_creator = new UNICODE_Structure_TexturePaint ( table_bg_directory, table_bg_image );
                    TexturePaint texture = tex_creator.makeTexturePaint ( );
                
                //override the jviewport component of the scroll pane
                @Override
                protected JViewport createViewport ( )
                {
                    return new JViewport ( )
                    {
                        @Override
                        public void paintComponent ( Graphics graphics )
                        {
                            Graphics2D graphics2d = ( Graphics2D ) graphics;
                            graphics2d.setPaint ( texture );
                            graphics2d.fillRect ( 0, 0, getWidth ( ), getHeight ( ) );
                            super.paintComponent ( graphics );
                        }
                    };
                }
            };
			
			//customized scrollbar
			JScrollBar scrollBar = scroll_pane.getVerticalScrollBar ( );
			scrollBar.setPreferredSize ( new Dimension ( 12, Integer.MAX_VALUE ) );
			scrollBar.setUI ( new UNICODE_MetalScrollBarUI ( tableThumbStream, tableTrackStream ) );
            
 
            //adjust the table
            table.setRowHeight ( rowHeight );        
            table.setFillsViewportHeight ( true );
            //align cells
            align_cells ( alignment );
            //adjust opacities
            adjustOpacities ( table, scroll_pane );
            
            //set alginment of scroll pane
            scroll_pane.setAlignmentX ( Component.CENTER_ALIGNMENT );
            
			//set border style
			getScrollPane ( ).setBorder ( BorderFactory.createLineBorder ( tableBackgroundColour ) );
			getTable ( ).getTableHeader ( ).setBackground ( tableBackgroundColour );
 	
			
            //add scroll pane to destination panel
            destination_panel.add ( scroll_pane );
        }
        
        public void adjustOpacities ( JTable table, JScrollPane scroll_pane )
        {
            //adjust opacities  
                //scroll pane (via viewport) and table
                Color colour = new Color ( 0, 0, 0, 0 );
                table.setOpaque ( false );
                table.setBackground ( colour );
                scroll_pane.getViewport ( ).setOpaque ( false );
                scroll_pane.getViewport ( ).setBackground ( colour ); 
//                 
//                 //adjust this panel
//                 scroll_pane.setOpaque ( false );
//                 this.setBackground ( new Color ( 0, 0, 0, 0 ) );
//                 
                //table header
                JTableHeader header = table.getTableHeader ( );
                header.setBackground ( new Color ( 255, 255, 255, 0 ) );
                header.setFocusable ( false );
        }

       
        //cel data centering    
        public void align_cells ( String alignment )
        {
            if ( alignment.equals ( "left" ) )
            {
                //left align strings
                TableCellRenderer tcr_str = table.getDefaultRenderer ( String.class );
                DefaultTableCellRenderer dtcr_str = ( DefaultTableCellRenderer ) tcr_str;
                dtcr_str.setHorizontalAlignment ( SwingConstants.LEFT );
                //left align floats
                TableCellRenderer tcr_float = table.getDefaultRenderer ( Float.class );
                DefaultTableCellRenderer dtcr_float = ( DefaultTableCellRenderer ) tcr_float;
                dtcr_float.setHorizontalAlignment ( SwingConstants.LEFT );
                //left align integers
                TableCellRenderer tcr_int = table.getDefaultRenderer ( Integer.class );
                DefaultTableCellRenderer dtcr_int = ( DefaultTableCellRenderer ) tcr_int;
                dtcr_int.setHorizontalAlignment ( SwingConstants.LEFT );
                //left align doubles
                TableCellRenderer tcr_double = table.getDefaultRenderer ( Double.class );
                DefaultTableCellRenderer dtcr_double = ( DefaultTableCellRenderer ) tcr_double;
                dtcr_double.setHorizontalAlignment ( SwingConstants.LEFT );
                //left align image icons
                TableCellRenderer tcr_img_icon = table.getDefaultRenderer ( ImageIcon.class );
                DefaultTableCellRenderer dtcr_img_icon = ( DefaultTableCellRenderer ) tcr_img_icon;
                dtcr_img_icon.setHorizontalAlignment ( SwingConstants.LEFT );
                //left align images
                TableCellRenderer tcr_img = table.getDefaultRenderer ( Image.class );
                DefaultTableCellRenderer dtcr_img = ( DefaultTableCellRenderer ) tcr_img;
                dtcr_img.setHorizontalAlignment ( SwingConstants.LEFT );
            }
            if ( alignment.equals ( "center" ) )
            {
                //center strings
                TableCellRenderer tcr_str = table.getDefaultRenderer ( String.class );
                DefaultTableCellRenderer dtcr_str = ( DefaultTableCellRenderer ) tcr_str;
                dtcr_str.setHorizontalAlignment ( SwingConstants.CENTER );
                //center floats
                TableCellRenderer tcr_float = table.getDefaultRenderer ( Float.class );
                DefaultTableCellRenderer dtcr_float = ( DefaultTableCellRenderer ) tcr_float;
                dtcr_float.setHorizontalAlignment ( SwingConstants.CENTER );
                //center integers
                TableCellRenderer tcr_int = table.getDefaultRenderer ( Integer.class );
                DefaultTableCellRenderer dtcr_int = ( DefaultTableCellRenderer ) tcr_int;
                dtcr_int.setHorizontalAlignment ( SwingConstants.CENTER );
                //center doubles
                TableCellRenderer tcr_double = table.getDefaultRenderer ( Double.class );
                DefaultTableCellRenderer dtcr_double = ( DefaultTableCellRenderer ) tcr_double;
                dtcr_double.setHorizontalAlignment ( SwingConstants.CENTER );
                //center image icons
                TableCellRenderer tcr_img_icon = table.getDefaultRenderer ( ImageIcon.class );
                DefaultTableCellRenderer dtcr_img_icon = ( DefaultTableCellRenderer ) tcr_img_icon;
                dtcr_img_icon.setHorizontalAlignment ( SwingConstants.CENTER );
                //center images
                TableCellRenderer tcr_img = table.getDefaultRenderer ( Image.class );
                DefaultTableCellRenderer dtcr_img = ( DefaultTableCellRenderer ) tcr_img;
                dtcr_img.setHorizontalAlignment ( SwingConstants.CENTER );
            }
            if ( alignment.equals ( "right" ) )
            {
                //right align strings
                TableCellRenderer tcr_str = table.getDefaultRenderer ( String.class );
                DefaultTableCellRenderer dtcr_str = ( DefaultTableCellRenderer ) tcr_str;
                dtcr_str.setHorizontalAlignment ( SwingConstants.RIGHT );
                //right align floats
                TableCellRenderer tcr_float = table.getDefaultRenderer ( Float.class );
                DefaultTableCellRenderer dtcr_float = ( DefaultTableCellRenderer ) tcr_float;
                dtcr_float.setHorizontalAlignment ( SwingConstants.RIGHT );
                //right align integers
                TableCellRenderer tcr_int = table.getDefaultRenderer ( Integer.class );
                DefaultTableCellRenderer dtcr_int = ( DefaultTableCellRenderer ) tcr_int;
                dtcr_int.setHorizontalAlignment ( SwingConstants.RIGHT );
                //right align doubles
                TableCellRenderer tcr_double = table.getDefaultRenderer ( Double.class );
                DefaultTableCellRenderer dtcr_double = ( DefaultTableCellRenderer ) tcr_double;
                dtcr_double.setHorizontalAlignment ( SwingConstants.RIGHT );
                //right align image icons
                TableCellRenderer tcr_img_icon = table.getDefaultRenderer ( ImageIcon.class );
                DefaultTableCellRenderer dtcr_img_icon = ( DefaultTableCellRenderer ) tcr_img_icon;
                dtcr_img_icon.setHorizontalAlignment ( SwingConstants.RIGHT );
                //right align images
                TableCellRenderer tcr_img = table.getDefaultRenderer ( Image.class );
                DefaultTableCellRenderer dtcr_img = ( DefaultTableCellRenderer ) tcr_img;
                dtcr_img.setHorizontalAlignment ( SwingConstants.RIGHT );
            }
        }
        
        
        public void clean ( )
        {
            table_model.getDataVector ( ).removeAllElements ( );
            table_model.fireTableDataChanged ( );
            item_count = -1;
            deletion_index = -10;
        }
}
