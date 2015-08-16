package data.packages.UNICODE;

import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.awt.Point;
import java.awt.Shape;

public class UNICODE_FileExtensionIndicatorElementCollectionGenerator
{
    //attributes
    private ArrayList <Ellipse2D> fileTypeIndicatorElements = null;
    private ArrayList <Point> coordinateCollection = null;
    private UNICODE_CoordinateCollectionGenerator coordinateCollectionGenerator = null;
    
    //establish vars that are changed in this class.
    private int childBodyCardinality = 0;
    private int childBodyWidth = 0, childBodyHeight = 0;
    //constructor
    public UNICODE_FileExtensionIndicatorElementCollectionGenerator ( int childBodyWidth, int childBodyHeight, int childBodyCardinality, double parentBodyX, double parentBodyY, double parentBodyWidth, double parentBodyHeight, double distanceFromCenterDisplacer )
    {
        this.childBodyWidth = childBodyWidth;
        this.childBodyHeight = childBodyHeight;
        this.childBodyCardinality = childBodyCardinality;

        coordinateCollectionGenerator = new UNICODE_CoordinateCollectionGenerator ( childBodyWidth, childBodyHeight,  childBodyCardinality, parentBodyX, parentBodyY, parentBodyWidth, parentBodyHeight, distanceFromCenterDisplacer );
        coordinateCollection = coordinateCollectionGenerator.getCoordinateCollection ( );
        
        fileTypeIndicatorElements = new ArrayList <Ellipse2D> ( );

        
        generateIndicatorElementCollection ( );
    }
    
    //methods
        //accessors
        public ArrayList <Ellipse2D> getIndicatorElementCollection ( )
        {
            return fileTypeIndicatorElements;
        }

        public void generateIndicatorElementCollection ( )
        {
            for ( int i = 0; i < childBodyCardinality; i ++ )
                fileTypeIndicatorElements.add ( new Ellipse2D.Double ( coordinateCollection.get ( i ).getX ( ), coordinateCollection.get ( i ).getY ( ), childBodyWidth, childBodyHeight ) );    
        }
}