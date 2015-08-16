 //Author(s): Jordan Micah Bennett
package data.packages.UNICODE;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Dimension;

public class UNICODE_Structure_SplitPane
{
    //attributes
    private int divider_location, split_type;
        //when user wants to establish split pane with two scroll panes
        private JScrollPane [ ] scroll_panes;
        //when user wants to establish split pane with a scroll pane, and a panel.
        private JScrollPane single_scroll_pane;
        private JPanel single_scroll_pane_partner;
        //when user wants to establish split pane with two regular panels
        private JPanel another_single_scroll_pane_partner;
    private Dimension minimum_dimension;
    private boolean expandability;
    private JSplitPane split_pane;

    //constructor - takes divider location, split type, array of scroll panes to add, minimum dimension scroll panes may shrink to, and expandibility
    //TAKES TWO SCROLL PANES
    public UNICODE_Structure_SplitPane ( int _divider_location, int _split_type, JScrollPane [ ] _scroll_panes, Dimension _minimum_dimension, boolean _expandability )
    {
        //establish variables
        divider_location = _divider_location;
        scroll_panes = _scroll_panes;
        minimum_dimension = _minimum_dimension;
        expandability = _expandability;
        split_type = _split_type;
    }
    
    //TAKES SCROLL PANE AND A SCROLL PANEL
    public UNICODE_Structure_SplitPane ( int _divider_location, int _split_type, JScrollPane _single_scroll_pane, JPanel _single_scroll_pane_partner, Dimension _minimum_dimension, boolean _expandability )
    {
        //establish variables
        divider_location = _divider_location;
        single_scroll_pane = _single_scroll_pane;
        single_scroll_pane_partner = _single_scroll_pane_partner;
        minimum_dimension = _minimum_dimension;
        expandability = _expandability;
        split_type = _split_type;
    }
    
    
    //TAKES TWO REGULAR PANES
    public UNICODE_Structure_SplitPane ( int _divider_location, int _split_type, JPanel _single_scroll_pane_partner, JPanel _another_single_scroll_pane_partner, Dimension _minimum_dimension, boolean _expandability )
    {
        //establish variables
        divider_location = _divider_location;
        single_scroll_pane_partner = _single_scroll_pane_partner;
        another_single_scroll_pane_partner = _another_single_scroll_pane_partner;
        minimum_dimension = _minimum_dimension;
        expandability = _expandability;
        split_type = _split_type;
    }
    
    //constructor - convenience functions access
    public UNICODE_Structure_SplitPane ( )
    {
    }
    
    
    //methods
        //accessors
        public JSplitPane getSplitPane ( )
        {
            return split_pane;
        }
        
        
        //misc
        public Dimension makeDim ( int x_size, int y_size )
        {
            return new Dimension ( x_size, y_size );
        }
        
        public JScrollPane [ ] makeScrolls ( JScrollPane pane0, JScrollPane pane1 )
        {
            JScrollPane array [ ] = new JScrollPane [ 2 ];
            array [ 0 ] = pane0;
            array [ 1 ] = pane1;
            return array;
        }
        
        public JScrollPane [ ] makeScrolls ( UNICODE_Structure_ComplexTable [ ] tables )
        {
            JScrollPane array [ ] = new JScrollPane [ 2 ];
            array [ 0 ] = tables [ 0 ].getScrollPane ( );
            array [ 1 ] = tables [ 1 ].getScrollPane ( );
            return array;
        }
        
        //comobo between two JScroll panes
        public void establishScrollScrollSplit ( )
        {
            //establisj split pane 
            split_pane = new JSplitPane ( split_type, scroll_panes [ 0 ], scroll_panes [ 1 ] );
            //set properties
            split_pane.setOneTouchExpandable ( expandability );
            split_pane.setDividerLocation ( divider_location );
            //establish dimension minimums 
            scroll_panes [ 0 ].setMinimumSize ( minimum_dimension );
            scroll_panes [ 1 ].setMinimumSize ( minimum_dimension );
        }
        //combo between a scroll pane and a panel
        public void establishScrollPaneSplit ( )
        {
            //establisj split pane 
            split_pane = new JSplitPane ( split_type, single_scroll_pane_partner, single_scroll_pane );
            //set properties
            split_pane.setOneTouchExpandable ( expandability );
            split_pane.setDividerLocation ( divider_location ); //center divider location
            //establish dimension minimums 
            single_scroll_pane.setMinimumSize ( minimum_dimension );
            single_scroll_pane_partner.setMinimumSize ( minimum_dimension );
        }
        //rverse combo to the above
        public void establishPaneScrollSplit ( )
        {
            //establisj split pane 
            split_pane = new JSplitPane ( split_type, single_scroll_pane, single_scroll_pane_partner );
            //set properties
            split_pane.setOneTouchExpandable ( expandability );
            split_pane.setDividerLocation ( divider_location ); //center divider location
            //establish dimension minimums 
            single_scroll_pane.setMinimumSize ( minimum_dimension );
            single_scroll_pane_partner.setMinimumSize ( minimum_dimension );
        }
        //combo between two regular panels
        public void establishPanePaneSplit ( )
        {
            //establisj split pane 
            split_pane = new JSplitPane ( split_type, single_scroll_pane_partner, another_single_scroll_pane_partner );
            //set properties
            split_pane.setOneTouchExpandable ( expandability );
            split_pane.setDividerLocation ( divider_location ); //center divider location
            //establish dimension minimums 
            single_scroll_pane_partner.setMinimumSize ( minimum_dimension );
            another_single_scroll_pane_partner.setMinimumSize ( minimum_dimension );
        }
        
        
        //misc 
            //set bounds
            public void setBounds ( int new_width, int new_height, int screen_width, int screen_height )
            {
                //we gonna reposition the schedule platform, since other 
                //components have affected regular flow of panel placement
                    //establish old table platform width
                    int resized_width = new_width;
                    int resized_height = new_height;
                    int resized_x = ( ( screen_width / 2 ) - ( resized_width / 2 ) );
                    int resized_y = ( ( screen_height / 2 ) - ( resized_height / 2 ) );
                    getSplitPane ( ).setBounds ( resized_x, resized_y, resized_width, resized_height );
            }
}
