/* 
    Author ~ Jordan Micah Bennett 
    A simplistic artificial neural network, that naturally computes xor-logic prediction, via stochastic gradient descent.
*/
package syntheticsentience;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javafx.animation.AnimationTimer;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class LogicalOperationDemonstrationLayer 
{
    public GenericSupervisionLayer SUPERVISION_LAYER;
    public NeuralNetwork NEURAL_NETWORK;
    public XYChart.Series<String, Number> USER_INTERFACE_GRAPH_STRUCTURE;
    public Label USER_INTERFACE_CYCLE_INDEX_LABEL, USER_INTERFACE_STATUS_LABEL;
    public final AtomicReference<Boolean> SUPERVISION_ENQUIRY;
    
    //define constructor
    public LogicalOperationDemonstrationLayer ( String fileName, boolean consoleDisplayQuery, String topologyString )
    {
        //establish neural network 'NEURAL_NETWORK' together with data parsing layer 'SUPERVISION_LAYER'
        SUPERVISION_LAYER = new LogicalOperationSupervisionLayer ( fileName, consoleDisplayQuery ); //supervision data for training ( wherein 'false' implies no log dump )
        NEURAL_NETWORK = new NeuralNetwork ( topologyString, consoleDisplayQuery, SUPERVISION_LAYER.getData ( ).size ( ) ); //2x2x1 nodal configuration ( wherein 'false' implies no log dump )  
        
        //establish user interface response component(s) 
        SUPERVISION_ENQUIRY = new AtomicReference<Boolean> ( false ); //required as inner blocks require final or atomic reference. The latter is choosen, since atomic references are mutatable.
    }
    
    
    //define methods
        //define mutators
        public void run ( )
        {
            AnimationTimer SUPERVISION_CLOCK = new AnimationTimer ( ) 
            {
                int supervisionLayerDataIterator = 0;

                @Override
                public void handle ( long l ) 
                {
                    if ( supervisionLayerDataIterator < SUPERVISION_LAYER.getData ( ).size ( ) )
                    {
                        USER_INTERFACE_GRAPH_STRUCTURE.getData ( ).add ( new XYChart.Data <String, Number> ( "" + supervisionLayerDataIterator, NEURAL_NETWORK.PURE_ERROR ) );
                        NEURAL_NETWORK.propagateProgressively ( SUPERVISION_LAYER.getInputs ( SUPERVISION_LAYER.getData ( ).get ( supervisionLayerDataIterator ) ) );
                        NEURAL_NETWORK.propagateRegressively ( SUPERVISION_LAYER.getExpectedOutcomes ( SUPERVISION_LAYER.getData ( ).get ( supervisionLayerDataIterator ) ) );
                        USER_INTERFACE_CYCLE_INDEX_LABEL.setText ( "synonymous cycle index ~> " + supervisionLayerDataIterator );
                        supervisionLayerDataIterator ++;
                    }
                    else
                    {
                       SUPERVISION_ENQUIRY.set ( true );
                       USER_INTERFACE_STATUS_LABEL.setText ( "neurons successfully constructed and supervised..." );
                    }
                }
            };

            SUPERVISION_CLOCK.start ( );  
        }  
        
        //such components transduce/communicate neural network data.
        public void establishUserInterfaceResponseComponents ( XYChart.Series<String, Number> USER_INTERFACE_GRAPH_STRUCTURE, Label USER_INTERFACE_CYCLE_INDEX_LABEL, Label USER_INTERFACE_STATUS_LABEL )
        {
            this.USER_INTERFACE_GRAPH_STRUCTURE = USER_INTERFACE_GRAPH_STRUCTURE;
            this.USER_INTERFACE_CYCLE_INDEX_LABEL = USER_INTERFACE_CYCLE_INDEX_LABEL;
            this.USER_INTERFACE_STATUS_LABEL = USER_INTERFACE_STATUS_LABEL;
        }
        
}