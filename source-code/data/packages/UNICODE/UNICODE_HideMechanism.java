package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.TrayIcon;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Date;
import java.awt.Shape;


public class UNICODE_HideMechanism
{
    //attributes
        //establish application tray icon appearance
        private TrayIcon tray_icon = null;
        //establish system tray
        private SystemTray system_tray = null;
        //establish popup menu
        private PopupMenu popup_menu = null;
        //establish menu item
        private MenuItem menu_item = null;
        //tray properties 
        private String tray_icon_name, tray_icon_label, menu_item_label;
        private Image tray_icon_image;
        private ActionListener menu_listener, tray_listener;		
		private String tray_icon_directory = null;
		
        
        //disclose menu
        private boolean running_disclose_session = false;
            //disclose button pack!! **April 30, 2012** --updated from single button at minimization
            private UNICODE_Button [ ] disclose_buttons = null;
            //max hide session menus 
            private int MAXIMUM_MENUS = 1;
            //max buttons
            private int MAXIMUM_BUTTONS = 11;
            //establish button coord generators for all menus
            private UNICODE_Menu c_gen [ ] = new UNICODE_Menu [ MAXIMUM_MENUS ];
            //disclose button propterties
            private String disclose_buttons_directory = null;
            private int disclose_buttons_width = 0, disclose_buttons_height = 0;
            private int resized_frame_width, resized_frame_height;
			
        
        //establish runtime
        private Runtime runtime = null;
        private double used_memory = 0.0, free_memory = 0.0, total_memory = 0.0;
        
        //establish panel surface **NEW**
        private Shape surface = null;
        private Ellipse2D surface_body = null;
        
        //establish boolean flag to tag when mouse encounters hider surface
        private boolean surface_contact = false;
        
        //establish convenience pack
        private UNICODE_ConveniencePack convenience_access = new UNICODE_ConveniencePack ( );

    
        public UNICODE_HideMechanism ( String _tray_icon_name, String _tray_icon_label, String _menu_item_label, ActionListener _menu_listener, ActionListener _tray_listener, String _tray_icon_directory, int _resized_frame_width, int _resized_frame_height, int _MAXIMUM_BUTTONS, boolean _surface_contact )
        {
            //establish surface contact
            surface_contact = _surface_contact;
            //establish button max
            MAXIMUM_BUTTONS = _MAXIMUM_BUTTONS;
            //initialise buttons
            disclose_buttons = new UNICODE_Button [ MAXIMUM_BUTTONS ];
            //establish properties
            tray_icon_name = _tray_icon_name;
            tray_icon_label = _tray_icon_label;
            menu_item_label = _menu_item_label;
            menu_listener = _menu_listener;
            tray_listener = _tray_listener;
            tray_icon_directory = _tray_icon_directory;
            //establishr esized frame dimensions
            resized_frame_width = _resized_frame_width;
            resized_frame_height = _resized_frame_height;
            //establish moemry stuff
                //initialize runtime
                runtime = Runtime.getRuntime ( );
                //initialize used meomory
                used_memory = runtime.totalMemory ( ) - runtime.freeMemory ( );
                //initialize total memory
                total_memory = runtime.totalMemory ( );
                //initialize free memory
                free_memory = runtime.freeMemory ( );
        }
        
        //convenience constructor
        public UNICODE_HideMechanism ( )
        {
        }
        
        //methods
            //accessors
            public double [ ] getMemoryData ( )
            {
                double [ ] data = null;
                data [ 0 ] = total_memory;
                data [ 1 ] = free_memory;
                data [ 2 ] = used_memory;
                return data;
            }
            
            public int getMaxButtons ( )
            {
                return MAXIMUM_BUTTONS;
            }
            public boolean getSurfaceContactEnquiry ( )
            {
                return surface_contact;
            }
            public UNICODE_Button [ ] getDiscloseButtons ( )
            {
                return disclose_buttons;
            }
            public int getResizedFrameWidth ( )
            {
                return resized_frame_width;
            }
            public int getResizedFrameHeight ( )
            {
                return resized_frame_height;
            }
            public boolean getDiscloseRunSessionEnquiry ( ) //tell whether a disclose session has been toggled. a disclose session is toggled when programmer begins to hide frame.
            {
                return running_disclose_session;
            }
			public String getTrayIconDirectory ( )
			{
				return tray_icon_directory;
			}
			public String getDiscloseButtonsDirectory ( )
			{
				return disclose_buttons_directory;
			}			
            public void setDiscloseRunSessionEnquiry ( boolean value )
            {
                running_disclose_session = value;
            }
			public void setTrayIconDirectory ( String value )
			{
				tray_icon_directory = value;
			}
			public void setHideButtonDirectory ( String value )
			{
				disclose_buttons_directory = value;
			}			
            //mutators
                //dynamic
                public void setDiscloseButtonVisibility ( boolean value )
                {
                    setDiscloseRunSessionEnquiry ( false );
                }
                public void setSurfaceContactEnquiry ( boolean value )
                {
                    surface_contact = value;
                }
            //misc
            public void showDiscloseButtons ( Graphics2D graphics2d, Color outline_colour, JPanel pane )
            {
                //define hider surface eclipse
                surface_body = new Ellipse2D.Double ( pane.getX ( ), pane.getY ( ), pane.getWidth ( ), pane.getHeight ( ) );
                //define surface shape
                surface = surface_body;
                if ( getDiscloseRunSessionEnquiry ( ) )
                { 
                    for ( int b = 0; b < MAXIMUM_BUTTONS; b ++ )
                    {
                        disclose_buttons [ b ].setVisibility ( true );
                        disclose_buttons [ b ].draw ( graphics2d, outline_colour, pane );
                    }
                }
            }
            
            public void killDiscloseSession ( )
            {
                setDiscloseRunSessionEnquiry ( false );
            }
            
            public void establishComponents ( UNICODE_Button null_button )
            {
                //define menus
                menu_item = new MenuItem ( menu_item_label );
                popup_menu = new PopupMenu ( );
                popup_menu.add ( menu_item );
                
                //define tray icon image
                tray_icon_image = Toolkit.getDefaultToolkit ( ).getImage ( getTrayIconDirectory ( ) + tray_icon_name );
                
                //define tray icon
                tray_icon = new TrayIcon ( tray_icon_image, tray_icon_label, popup_menu );
                
                //add listeners
                menu_item.addActionListener ( menu_listener );
                tray_icon.addActionListener ( tray_listener );
                
                //define system tray
                system_tray = SystemTray.getSystemTray ( );
                
                //add icon to tray
                try
                {
                    system_tray.add ( tray_icon );
                }
                catch ( Exception error )
                {
                }
            }

            
            public void createDiscloseMenu ( UNICODE_Menu null_menu, UNICODE_Button null_button, int button_proximity, int axis_radius, String _disclose_buttons_directory, int _disclose_buttons_width, int _disclose_buttons_height, ArrayList [ ] imgs )
            {    
                //set disclose enquiry true
                setDiscloseRunSessionEnquiry ( true );
                //establish coord gen c_gen [ ]
                c_gen = null_menu.defineGridAxisCoordinateGenerationArray ( MAXIMUM_MENUS, "" + MAXIMUM_BUTTONS, false, button_proximity, axis_radius, ( resized_frame_width ) - ( axis_radius / 2 ), ( resized_frame_height ) + ( axis_radius / 2 ), "anti-clockwise", "circular" );    
                //establish property labels via user specified data
                disclose_buttons_width = _disclose_buttons_width;
                disclose_buttons_height = _disclose_buttons_height;
                disclose_buttons_directory = _disclose_buttons_directory;
               
                //create disclose button instance
                for ( int buttons = 0; buttons < MAXIMUM_BUTTONS; buttons ++ )
                {
                    //if we reach the last button..make it's dimension smaller ( exit startegy button )
                    if ( buttons == ( MAXIMUM_BUTTONS - 1 ) )
                    {
                        disclose_buttons_width = disclose_buttons_width/2;
                        disclose_buttons_height = disclose_buttons_height/2;
                    }
                    else
                    {
                        disclose_buttons_width = disclose_buttons_width;
                        disclose_buttons_height = disclose_buttons_height;  
                    }
                    disclose_buttons [ buttons ] = new UNICODE_Button ( "e", c_gen [ 0 ].getXGen ( ) [ buttons ], c_gen [ 0 ].getYGen ( ) [ buttons ], disclose_buttons_width, disclose_buttons_height, null_button.cCTL ( null_button.cTL ( "" + imgs [ buttons ].get ( 0 ) + " " + imgs [ buttons ].get ( 1 ) + " " + imgs [ buttons ].get ( 2 ) ) ), 0, false, 0, getDiscloseButtonsDirectory ( ) );    
                }
            }

            
            //breaks up long stream of button layer image names into
            //array list containing each line, further adding to each index of
            //such array lists individual image names per index
            //BASSICALLY IT DOES THIS: 
            //A.takes a long stream like this:"a.png:b.png:c.png,0.png:1.png:2.png,i.png:ii.png:iii.png,A.png:B.png:C.png"
            //Ai.so wrt above, the max buttons it accounts for is 4(delim=","), while max components is 3, (delim=":") 
            //B.breaks it down to array lists like this:
            //arrayList [ 0 ].add ( delimArray ( "a.png:b.png:c.png", "", "," 4 ) );
            //arrayList [ 1 ].add ( delimArray ( "0.png:1.png:2.png" "", "," 4 ) );
            //arrayList [ 2 ].add ( delimArray ( "i.png:ii.png:iii.png" "", "," 4 ) );;
            //arrayList [ 3 ].add ( delimArray ( "A.png:B.png:C.png" "", "," 4 ) );
            //C.then further breaks up each line and adds it to the indices of each array list index,
            //taking the line as a param in delimiting that same line...like so:
            //arrayList [ 0 ].add ( delimArray ( delimArray ( delimArray ( "A.png:B.png:C.png" "", "," 4 ), "", 3 ) ) );...etc
            public ArrayList [ ] makeDiscloseButtonPackStream ( String stream_list, String stream_delimiter, int num_components, String stream_component_delimiter )
            {
                ArrayList data [ ] = new ArrayList [ MAXIMUM_BUTTONS ];
                
                //initiliase data
                for ( int buttons = 0; buttons < MAXIMUM_BUTTONS; buttons ++ )
                    data [ buttons ] = new ArrayList ( );

                //now fill those newly added array lists with each line of stream delimited data
                for ( int buttons = 0; buttons < MAXIMUM_BUTTONS; buttons ++ ) //for each button that we need to add data to,
                    for ( int datum = 0; datum < num_components; datum ++ )
                        data [ buttons ].add ( convenience_access.getDelimitedArray ( convenience_access.getDelimitedArray ( stream_list, "", stream_delimiter, MAXIMUM_BUTTONS ) [ buttons ], "", stream_component_delimiter, num_components ) [ datum ] );

                return data;
            }
    
            public void triggerFrameResized ( JFrame application_frame, float opacity_level, int initial_screen_width, int initial_screen_height )
            {
                int resized_frame_x = ( initial_screen_width / 2 ) - ( resized_frame_width / 2 ), resized_frame_y =  ( initial_screen_height / 2 ) - ( resized_frame_height / 2 );
                application_frame.setBounds ( resized_frame_x, resized_frame_y, resized_frame_width, resized_frame_height );
                application_frame.setShape ( new Ellipse2D.Double ( 0, 0, resized_frame_width, resized_frame_width ) );
                application_frame.setOpacity ( opacity_level );
                application_frame.repaint ( );
            }
            public void triggerFrameUnresized ( JFrame application_frame, int initial_screen_width, int initial_screen_height )
            {
                application_frame.setBounds ( 0, 0, initial_screen_width, initial_screen_height );
                application_frame.setLocationRelativeTo ( null );
                application_frame.setShape ( new Rectangle2D.Double ( 0, 0, initial_screen_width, initial_screen_height ) );
                application_frame.repaint ( );
            }    
            
            public void triggerMemoryImprinting ( UNICODE_DateCreator date_creator, UNICODE_TextField clock_field )
            {
                try
                {
                    PrintWriter pw = new PrintWriter ( new FileWriter ( "data/logs/memory_log.ini", true ) );
                    pw.println ( ":------------------------------------------------------------------:" );
                    pw.println ( ":-- " + date_creator.getDate ( ) );
                    pw.println ( ":-- " + clock_field.getLabel ( ) );
                    pw.println ( ":-- Total Memory : " + total_memory );
                    pw.println ( ":-- Free Memory : " + free_memory );
                    pw.println ( ":-- Used Memory : " + used_memory );
                    pw.println ( ":------------------------------------------------------------------:" );
                    pw.close ( );
                }
                catch ( Exception error )
                {
                }
                
                JOptionPane.showMessageDialog ( null, "memory print saved in data/prints/memory_log.ini", "memory notice", JOptionPane.INFORMATION_MESSAGE );
            }
            //check if mouse is in the hider surface
            public boolean contains ( int mouseX, int mouseY )
            {
                return surface.contains ( mouseX, mouseY );
            }
}