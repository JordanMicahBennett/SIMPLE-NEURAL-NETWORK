/* 
    Author ~ Jordan Micah Bennett 
    A simplistic artificial neural network, that naturally computes xor-logic prediction, via stochastic gradient descent.
*/
package syntheticsentience;

import java.util.ArrayList;

public class NeuralNetworkTopology extends ArrayList <Integer>
{
    //define features
    private String values = "";
   
    //define constructor
    public NeuralNetworkTopology ( String values )
    {
        this.values = values;
        generateTopology ( );
    }
    
    
    //define methods
        //define accessors
        
        //define mutators
        public void generateTopology ( )
        {
            String [ ] segmentedValues = values.split ( "," );
            
            //{sVI-segmented values iterator}
            for ( int sVI = 0; sVI < segmentedValues.length; sVI ++ )
                add ( Integer.parseInt ( segmentedValues [ sVI ] ) );
        }
}