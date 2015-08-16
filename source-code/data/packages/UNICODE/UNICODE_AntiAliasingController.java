package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.RenderingHints;
import java.awt.Graphics2D;

public class UNICODE_AntiAliasingController
{
    //attributes
    private RenderingHints rendering_hints = null;
    private String rendering_answer = null;

    public UNICODE_AntiAliasingController ( String _rendering_answer )
    {
        rendering_answer = _rendering_answer;
    }
    
    //methods
        //accessors
        public String getRenderingAnswer ( )
        {
            return rendering_answer;
        }
        //mutators
        public void setRenderingAnswer ( String value )
        {
            rendering_answer = value;
        }
    
    //misc
        //setup anti aliasing
        public void setupAntiAliasing ( Graphics2D graphics2d )
        {
            //establish hint
            rendering_hints = graphics2d.getRenderingHints ( );
            if ( getRenderingAnswer ( ).equals ( "on" ) )
            {
                //setup hint
                rendering_hints.put ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
                //setup graphics 2d context wrt hints
                graphics2d.setRenderingHints ( rendering_hints );
            }
            else if ( getRenderingAnswer ( ).equals ( "off" ) )
            {
                //setup hint
                rendering_hints.put ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF );
                //setup graphics 2d context wrt hints
                graphics2d.setRenderingHints ( rendering_hints ); 
            }
        }
}
