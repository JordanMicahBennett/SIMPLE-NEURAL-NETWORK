package data.packages.UNICODE;
import java.util.ArrayList;
import java.awt.Point;

public class UNICODE_CoordinateCollectionGenerator
{
    //attributes
    private ArrayList <Point> coordinateCollection = null;
    private double angleStep = 0.0;
    private double parentBodyWidth, parentBodyHeight, parentBodyWidthBeforeDisplacement, parentBodyHeightBeforeDisplacement, parentBodyX, parentBodyY, spacing;
    private int childBodyWidth, childBodyHeight;
    private int childBodyCardinality;
    private double distanceFromCenterDisplacer = 0;
    //constructor
    public UNICODE_CoordinateCollectionGenerator ( int childBodyWidth, int childBodyHeight, int childBodyCardinality, double parentBodyX, double parentBodyY, double parentBodyWidth, double parentBodyHeight, double distanceFromCenterDisplacer )
    {
        coordinateCollection = new ArrayList <Point> ( );
        
        this.spacing = !(childBodyCardinality % 2 == 0) ? childBodyCardinality * 100 : ( childBodyCardinality + 1 ) * 100;
        this.childBodyCardinality = childBodyCardinality;
        this.childBodyWidth = childBodyWidth;
        this.childBodyHeight = childBodyHeight;
        this.distanceFromCenterDisplacer = distanceFromCenterDisplacer; //allows user to alter default distance from center.
        angleStep = 360 / childBodyCardinality;
        
        this.parentBodyWidthBeforeDisplacement = parentBodyWidth;
        this.parentBodyHeightBeforeDisplacement = parentBodyHeight;
        
        this.parentBodyWidth = parentBodyWidthBeforeDisplacement + distanceFromCenterDisplacer;
        this.parentBodyHeight = parentBodyHeightBeforeDisplacement + distanceFromCenterDisplacer;
        
        this.parentBodyX = parentBodyX;
        this.parentBodyY = parentBodyY;
        
        
        generateCoordinateCollection ( );
    }
    
    //methods
        //accessors
        public ArrayList <Point> getCoordinateCollection ( )
        {
            return coordinateCollection;
        }
        
        //mutators
        public void generateCoordinateCollection ( )
        {
            double alpha = ( Math.PI * 2 / childBodyCardinality ) + spacing;
             
            for ( int i = 0; i < childBodyCardinality; i ++ )
            {
                double theta = alpha * i;
                double x = ( parentBodyX - parentBodyWidthBeforeDisplacement / 2 + Math.cos ( theta ) * parentBodyWidth / 2 ) + ( parentBodyWidth / 2 );
                double y = ( parentBodyY - parentBodyHeightBeforeDisplacement / 2 + Math.sin ( theta ) * parentBodyHeight / 2 ) + ( parentBodyHeight / 2 );
                coordinateCollection.add ( new Point ( ( int ) ( x ), ( int ) ( y ) ) );
            }
        }
}