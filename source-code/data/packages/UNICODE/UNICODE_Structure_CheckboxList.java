package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class UNICODE_Structure_CheckboxList 
{
    //attributes
    private String label;
	public JPanel PLATFORM;
    private JPanel destination_pane;
    public int CHECKBOX_INDEX;
    public ArrayList <JCheckBox> CHECKBOXES = new ArrayList <JCheckBox> ( );
    private int maximum_checkboxes;
    private int layout_type;
    //changing boundaries.
    private int scrolling_up_attempts, scrolling_down_attempts;
    //establish screen dimensions
        //get screen dimensio
        private Dimension screen_dimension = Toolkit.getDefaultToolkit ( ).getScreenSize ( );  
        //establish variables
        private int screen_width = ( int ) screen_dimension.getWidth ( ), screen_height = ( int ) screen_dimension.getHeight ( );
		
        //establish timers that scroll checkbox platform up and down
        private Timer scrollUpTimer = new Timer ( 1, new scrollUpListening ( ) );
        private Timer scrollDownTimer = new Timer ( 1, new scrollDownListening ( ) );
		private int scrollUpRate, scrollDownRate;
        
    //constructor
    public UNICODE_Structure_CheckboxList ( int current_index, int max_boxxes, JPanel dest_pane, int _layout_type, int scrollUpRate, int scrollDownRate  ) 
    {
        //initialsie max boxxes
        maximum_checkboxes = max_boxxes;
        //init maximum
        CHECKBOX_INDEX = current_index;
        //establish destination panel
        destination_pane = dest_pane;
        //establish layout type
        layout_type = _layout_type;
		//establish platform
		PLATFORM = new JPanel ( );
		//establish scroll rates
		this.scrollUpRate = scrollUpRate;
		this.scrollDownRate = scrollDownRate;
    } 
    
    
    //constructor - convenience accessor
    public UNICODE_Structure_CheckboxList ( ) 
    {
        
    } 
    
    //methods
        //accessors
        public ArrayList <JCheckBox> getCheckboxes ( )
        {
            return CHECKBOXES;
        }
        public int getCheckboxIndex ( )
        {
            return CHECKBOX_INDEX;
        }
        public boolean getVisibility ( )
        {
            return PLATFORM.isVisible ( );
        }
        public JPanel getPane ( )
        {
            return PLATFORM;
        }
        public int getX ( )
        {
            return PLATFORM.getX ( );
        }
        public int getY ( )
        {
            return PLATFORM.getY ( );
        }
        public int getWidth ( )
        {
            return PLATFORM.getWidth ( );
        }
        public int getHeight ( )
        {
            return PLATFORM.getHeight ( );
        }
        public int getScrollUpAttempts ( )
        {
            return scrolling_up_attempts;
        }
        public int getScrollDownAttempts ( )
        {
            return scrolling_down_attempts;
        }
        public int getMaximumcheckboxes ( )
        {
            return maximum_checkboxes;
        }
        //mutators
            //dynamic -- add Box from defaultly directorized images
		
            public void addBox ( String directory, String label, boolean selected, String icon_name, String rollover_icon_name, String selected_icon_name, String rollover_selected_icon_name )
            {
                //initialise tha newly added box
                CHECKBOXES.add ( makeCheckBox ( directory, label, selected, icon_name, rollover_icon_name, selected_icon_name, rollover_selected_icon_name ) );
                //update check box index
                incCheckBoxIndex ( 1 ); 
            }
        public JCheckBox makeCheckBox ( String directory, String label, boolean selected, String icon_name, String rollover_icon_name, String selected_icon_name, String rollover_selected_icon_name )
        {
            return new UNICODE_Structure_Checkbox ( directory, label, selected, icon_name, rollover_icon_name, selected_icon_name, rollover_selected_icon_name );
        }
			
            public void incCheckBoxIndex ( int value )
            {
                CHECKBOX_INDEX += value;
            }
            public void decCheckBoxIndex( int value )
            {
                CHECKBOX_INDEX -= value;
            }       
            public void incMaximum ( int value )
            {
                maximum_checkboxes += value;
            }
            public void decMaximum ( int value )
            {
                maximum_checkboxes -= value;
            }      
            public void setScrollUpAttempts ( int value )
            {
                scrolling_up_attempts = value;
            }
            public void setScrollDownAttempts ( int value )
            {
                scrolling_down_attempts = value;
            }
            public void incScrollUpAttempts ( int value )
            {
                scrolling_up_attempts += value;
            }
            public void incScrollDownAttempts ( int value )
            {
                scrolling_down_attempts += value;
            }
            public void deScrollUpAttempts ( int value )
            {
                scrolling_up_attempts -= value;
            }
            public void decScrollDownAttempts ( int value )
            {
                scrolling_down_attempts -= value;
            }
            //static
            public void resetBounds ( int x_change, int y_change, int w_change, int h_change )
            {
                PLATFORM.setBounds ( getX ( ) + x_change, getY ( ) + y_change, getWidth ( ) + w_change, getHeight ( ) + h_change );
            }
			public void setVisibility ( boolean value )
			{
				PLATFORM.setVisible ( value );
			}
			public void toggleVisibility ( )
			{
				if ( PLATFORM.isVisible ( ) )
					PLATFORM.setVisible ( false );
				else
					PLATFORM.setVisible ( true );
			}
            public void setMaximumcheckboxes ( int value )
            {
                maximum_checkboxes = value;
            }

    //misc

        public void colourcheckboxes ( Color background_colour, Color foreground_colour )
        {
            for ( int boxxes = 0; boxxes < getMaximumcheckboxes ( ); boxxes ++ )
            {
                CHECKBOXES.get ( boxxes ).setBackground ( background_colour );
                CHECKBOXES.get ( boxxes ).setForeground ( foreground_colour );
            }
        }
        //repaints the checkboxes, the panel on which it is placed, and the destination panel.
        public void repaint ( )
        {
            for ( int boxxes = 0; boxxes < CHECKBOXES.size ( ); boxxes ++ )
            {
                CHECKBOXES.get ( boxxes ).repaint ( );
            }
            getPane ( ).repaint ( );
            destination_pane.repaint ( );
        }
        
        public void setup ( Color background_colour, Color foreground_colour )
        {
            //initilize checkbox containers
                //intialise panel
                PLATFORM = new JPanel ( );
				PLATFORM.setBackground ( background_colour );
				
            for ( int boxxes = 0; boxxes < getMaximumcheckboxes ( ); boxxes ++ )
            {
                PLATFORM.add ( CHECKBOXES.get ( boxxes ) );
                CHECKBOXES.get ( boxxes ).setBackground ( background_colour );
                CHECKBOXES.get ( boxxes ).setForeground ( foreground_colour );
            }
            PLATFORM.setLayout ( new BoxLayout ( PLATFORM, layout_type ) );
            //add the list pane to a scrollable pane
            //list_scroll_pane.add ( PLATFORM );
            //add scroll pane to destination panel
            destination_pane.add ( PLATFORM );
        }
        
        //scroll up
        public void _scrollDown ( )
        {
            //compute number of screens worth of data
            int num_screens_worth_data = getHeight ( ) / screen_height;   
			
            int bottom_most_piece = 5;
            //scrolling down is only neccessary if there more than one page worth of data
            if ( num_screens_worth_data > 1 )
            {
                //compute y scroll up limit
                int y_coord_limit = - ( ( getHeight ( ) / ( num_screens_worth_data / 2 ) ) + ( ( PLATFORM.getHeight ( ) + bottom_most_piece ) * ( ( int ) Math.pow ( num_screens_worth_data, 2 ) ) ) );
                //test to see if y coord is still greater than the last portion of checkbox list
                if ( getY ( ) >= y_coord_limit )
                    resetBounds ( 0, -scrollDownRate, 0, 0 );   
            }   
			else if ( num_screens_worth_data == 1 )
			{
                resetBounds ( 0, -scrollDownRate/2, 0, 0 ); 
			}
        }
        public void _scrollUp ( )
        {
            if ( getY ( ) <= 0 )
                resetBounds ( 0, scrollUpRate, 0, 0 );      
        }
		public void scrollDown ( )
		{
			incScrollDownAttempts ( 1 );
			scrollUpTimer.stop ( );
			
			if ( getScrollDownAttempts ( ) <= 1 )
				scrollDownTimer.start ( );
			else
			{
				setScrollDownAttempts ( 0 );
				scrollDownTimer.stop ( );
			}
		}
		
		public void scrollUp ( )
		{   
			incScrollUpAttempts ( 1 );
			scrollDownTimer.stop ( );
			
			if ( getScrollUpAttempts ( ) <= 1 )
				scrollUpTimer.start ( );
			else
			{
				setScrollUpAttempts ( 0 );
				scrollUpTimer.stop ( );
			}
		}
		
		//checbox scrolling action listener
		private class scrollUpListening implements ActionListener 
		{
			public void actionPerformed ( ActionEvent aEvent )
			{
				_scrollUp ( );
				repaint ( );
			}
		}
		private class scrollDownListening implements ActionListener 
		{
			public void actionPerformed ( ActionEvent aEvent )
			{
				_scrollDown ( );
				repaint ( );
			}
		}
}
