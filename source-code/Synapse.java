//Author: Jordan Micah Bennett
import java.io.Serializable;

public class Synapse implements Serializable //The hierarchy's entirety, [Synapse -> CorticalColumns] requires serialization to be viably storable/retrievable.
{
    //establish features
    private double weight;
    private double deltaWeight;
    
    //define constructor
    public Synapse ( )
    {
    }
    
    
    //define methods
        //define accessors
        public double getWeight ( )
        {
            return weight;
        }
        public double getDeltaWeight ( )
        {
            return deltaWeight;
        }
        //define mutators
        public void setWeight ( double value )
        {
            weight = value;
        }
        public void setDeltaWeight ( double value )
        {
            deltaWeight = value;
        }
}