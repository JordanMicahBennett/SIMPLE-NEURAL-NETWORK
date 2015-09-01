//Author: Jordan Micah Bennett
import java.util.ArrayList;

import data.packages.UNICODE.UNICODE_ConveniencePack;

public class NeuralNetwork
{
    //establish features
    private CorticalColumns corticalColumns;
    private NeuralNetworkTopology neuralNetworkTopology;
    private String topologyDescription;
    private double netError; //net error E
    private double proximalMeanError, proximallyProximalMeanError, proximalMeanSmoothingFactor; 
    private double gradientError; //gradient error
    private boolean consoleDisplayQuery; //determines whether System.out.println calls are executed
    public double eta, alpha;
    public Variance variance;
    
    //establish constructor
    public NeuralNetwork ( String topologyDescription, boolean consoleDisplayQuery, double proximalMeanSmoothingFactor )
    {
        //define features
            //define console enquiry
            this.consoleDisplayQuery = consoleDisplayQuery;
        
            //define topology string
            this.topologyDescription = topologyDescription;
            
            //define proximalMeanSmoothingFactor -> data training pass cardinality
            this.proximalMeanSmoothingFactor = proximalMeanSmoothingFactor;
            
            //define overall learning rate (gradient vanishes for eta > 0.2, whilst gradientError & netError implodes for eta > 0.001) Regularization: normalize ( proximalMeanError ), normalize ( gradientError ), normalize ( exponent norm on the order of input vector size, rather than square. ). Regularized signals regress, imposing non-hyperparameter implosion/explosion/vanishing bounding.
            eta = 0.2; 
            
            //define momentum
            alpha = 0.5;
            
            //define variance
            variance = Variance.TANGENTIALLY_HYPERBOLIC; //Variance.LINEARLY_RECTIFIED | Variance.TANGENTIALLY_HYPERBOLIC
            
            //define proximalMeanError
            proximalMeanError = 1.0;
            
            //define gradient error
            gradientError = 0.0;
            
            //define proximallyProximalMeanError
            proximallyProximalMeanError = 1.0;
        
            //define neural network topology
            neuralNetworkTopology = new NeuralNetworkTopology ( topologyDescription );
            
            //define corticalColumns & neurons
                //define corticalColumns:
                corticalColumns = new CorticalColumns ( );
                
                //{cCI-cortical columns iterator}
                for ( int cCI = 0; cCI < neuralNetworkTopology.size ( ); cCI ++ ) 
                {
                    corticalColumns.add ( new CorticalColumn ( ) );
                    //define neuron interconnectivity input weights maxima
                    int sequentiallyHierarchicallyHorizontalInputWeightCardinality = neuralNetworkTopology.size ( ) == 0 ? 0 : ( ( cCI + 1 ) < neuralNetworkTopology.size ( ) ) ? neuralNetworkTopology.get ( cCI + 1 ) : 0;
                    int priorlyHierarchicallyHorizontalInputWeightCardinality = neuralNetworkTopology.size ( ) == 0 ? 0 : ( ( cCI - 1 ) > 0 ) ? neuralNetworkTopology.get ( cCI - 1 ) : 0;
                
                    //define neurons
                    //{cCNI-cortical columns neurons iterator}
                    for ( int cCNI = 0; cCNI <= neuralNetworkTopology.get ( cCI ); cCNI ++ ) //...for ( <= ) enables bias/threshold generation
                        corticalColumns.get ( cCI ).add ( new Neuron ( sequentiallyHierarchicallyHorizontalInputWeightCardinality, priorlyHierarchicallyHorizontalInputWeightCardinality, cCNI, eta, alpha, variance ) );
                    //{cCNI-cortical columns neurons iterator}
                    for ( int cCNI = 0; cCNI <= neuralNetworkTopology.get ( cCI ); cCNI ++ ) //...for ( <= ) enables bias/threshold generation
                    {
                        corticalColumns.get ( cCI ).get ( corticalColumns.get ( cCI ).size ( ) - 1 ).setOutcome ( 1.0 ); //set all biased neurons' outcomes to 1.0
                        
                        if ( consoleDisplayQuery )
                            System.out.println ( "neuron via cortical column ( " + cCI + " ) generated with identifier index ( " + cCNI + " ) with outcome ( " + corticalColumns.get ( cCI ).get ( cCNI ).getOutcome ( ) + " )." );
                    }
                }
                
            //display neuron total
            if ( consoleDisplayQuery )
                System.out.println ( "generated ( " + topologyDescription.replace ( ",", " x " ) + " ) neurons. " );
    }
    
    
    //define methods
        //define accessors
        public CorticalColumns getCorticalColumns ( )
        {
            return corticalColumns;
        }
        
        public String getNeuronCardinalityMessage ( )
        {
            return "generated ( " + topologyDescription.replace ( ",", " x " ) + " ) neurons..";
        }
        
        public String getTopologyCardinalityMessage ( )
        {
            return "generated nueral network topology of ( " + neuralNetworkTopology.size ( ) + " ) tiers..";
        }
        
        public void recall ( String inputStream )
        {
            corticalColumns = ( CorticalColumns ) new UNICODE_ConveniencePack ( ).loadSerializable ( inputStream );
        }

        public ArrayList <Double> getOutcomes ( )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );
            
            //{oCCNI-outcome cortical columns neurons iterator}
            for ( int oCCNI = 0; oCCNI < corticalColumns.get ( corticalColumns.size ( ) - 1 ).size ( ) - 1; oCCNI ++ )
                returnValue.add ( new NormalizationLayer ( ).getRoundedOutcome ( corticalColumns.get ( corticalColumns.size ( ) - 1 ).get ( oCCNI ).getOutcome ( ) ) );
                
            return returnValue;
        }
        
        //define mutators
        public void quantize ( String outputStream ) 
        {
            new UNICODE_ConveniencePack ( ).printSerializable ( getCorticalColumns ( ), outputStream );
        } 
        
        public void propagateProgressively ( ArrayList <Double> values ) //...for values = inputs
        {
            //bind all input values in the initial/input cortical column
            //{cCNI-cortical columns neurons iterator}
            for ( int cCNI = 0; cCNI < corticalColumns.get ( 0 ).size ( ) - 1; cCNI ++ ) //...values.size and corticalColumns.get ( 0 ).size ( ) are interchangeable
                corticalColumns.get ( 0 ).get ( cCNI ).setOutcome ( values.get ( cCNI ) );
                 
            //bind all input values differentiably in all cortical columns ( hidden..outcome ) but the first
            //{cCI-cortical columns iterator}
            for ( int cCI = 1; cCI < neuralNetworkTopology.size ( ); cCI ++ ) 
            {
                CorticalColumn priorCorticalColumn = corticalColumns.get ( cCI - 1 );
                for ( int cCNI = 0; cCNI < corticalColumns.get ( cCI ).size ( ) - 1; cCNI ++ )
                    corticalColumns.get ( cCI ).get ( cCNI ).propagateProgressively ( priorCorticalColumn );
            }
        }
        
        public void propagateRegressively ( ArrayList <Double> values ) //...for values = expected outcomes
        {
            //propagateRegressively-> compute E = netError, G ( n ) = hidden..outcomeGradients, and update = update all neuron weights
            
            //establish outcome cortical column [ derive outcome neuron @ valid index [structure].size ( ) - 1 ]
            CorticalColumn outcomeCorticalColumn = corticalColumns.get ( corticalColumns.size ( ) - 1 );
            
            //compute net error = sqrt ( 1 \ n ( sigma { ( e ( n ) - i ( n ) ) ^ 2 } ) ) for i = inputs and e = expected outcome
            //{oCCNI-outcome cortical columns neurons iterator}
            //Exploit quadratic phenomenon convex nature, thereafter generating globally converging parabola process, herein generating cost a priore 
            double sigma = 0.0;
            for ( int oCCNI = 0; oCCNI < outcomeCorticalColumn.size ( ) - 1; oCCNI ++ ) //...for [structure].size ( ) - 1 separately implies bias neuron exclusion. Bias neuron exclusion occurs globally {with the exception of hidden gradient computation} This enables threshold neuron constant-ness.
                sigma += Math.pow ( ( values.get ( oCCNI ) - outcomeCorticalColumn.get ( oCCNI ).getOutcome ( ) ), values.size ( ) ); //Absent threshold constantness aligned network control, the network surjectively non-generalizes, as priorly computed sigmas of calculations effectively generate linearly surjective, non varying outcomes. A non-differentiable network is futile. Such differentiability yields variabilities of expression of pattern detection.
                
            netError = new NormalizationLayer ( ).getNormalizedOutcome ( -1, Math.sqrt ( sigma / outcomeCorticalColumn.size ( ) - 1 ), 1, -1, 1 );
            
            //compute proximalMeanError
            proximallyProximalMeanError = proximalMeanError;
            proximalMeanError = new NormalizationLayer ( ).getNormalizedOutcome ( -1, ( proximalMeanError * proximalMeanSmoothingFactor + netError ) / ( proximalMeanSmoothingFactor + 1.0 ), 1, -1, 1 );
            
            //compute G(0) = outcome gradients
            for ( int oCCNI = 0; oCCNI < outcomeCorticalColumn.size ( ) - 1; oCCNI ++ )
                outcomeCorticalColumn.get ( oCCNI ).computeOutcomeGradient ( values.get ( oCCNI ) );
               
            //compute G(1) = hidden gradients
            //{cCI-cortical columns iterator}
            for ( int cCI = corticalColumns.size ( ) - 2; cCI > 0; cCI -- )
            {
                CorticalColumn hiddenCorticalColumn = corticalColumns.get ( cCI );
                CorticalColumn subsequentCorticalColumn = corticalColumns.get ( cCI + 1 );
                
                //{hCCNI-hidden cortical columns neurons iterator} 
                for ( int hCCNI = 0; hCCNI < hiddenCorticalColumn.size ( ) - 1; hCCNI ++ )  //...for ( < [structure].size ( ) ) Maintains neuron bias exclusion. (inclusive amidst nested instance -> Neuron/distributed weight sigma) This enables threshold neuron constant-ness.
                    hiddenCorticalColumn.get ( hCCNI ).computeHiddenGradient ( subsequentCorticalColumn ); //Absent threshold constantness aligned network control, the network surjectively non-generalizes, as priorly computed sigmas of calculations effectively generate linearly surjective, non varying outcomes. A non-differentiable network is futile. Such differentiability yields variabilities of expression of pattern detection. 
            }

            //compute U = update weights
            //{cCI-cortical columns iterator}
            for ( int cCI = corticalColumns.size ( ) - 1; cCI > 0; cCI -- )
            {
                CorticalColumn synonymousCorticalColumn = corticalColumns.get ( cCI ); //current 
                CorticalColumn priorCorticalColumn = corticalColumns.get ( cCI - 1 ); 
                
                //{sCCNI-synonymous cortical columns neurons iterator}
                for ( int sCCNI = 0; sCCNI < synonymousCorticalColumn.size ( ) - 1; sCCNI ++ )
                    synonymousCorticalColumn.get ( sCCNI ).update ( priorCorticalColumn );
            }
            //enableEtaMutation 
            enableEtaMutation ( );
        }
        
        //numerical gradient estimation/checking. 
        //numerical checking enables polynomial time gradient estimation. [recall, eta effects as a [Neuron] gradient's multiplier]
        public void enableEtaMutation ( )
        {
            double thresholdCeiling = .001, thresholdFloor = .01, decrementalFactor = .999, incrementalFactor = 1.005; //Here, I have utilized David Miller's eta mutator components initialization
            
            gradientError = new NormalizationLayer ( ).getNormalizedOutcome ( -1, ( proximalMeanError - proximallyProximalMeanError ) / proximalMeanError, 1, -1, 1 );
            
            eta = thresholdCeiling > 0 && thresholdFloor > 0 && incrementalFactor >= 1 && decrementalFactor >= 0 && decrementalFactor <= 1 ? gradientError > thresholdCeiling ? eta * decrementalFactor : gradientError < - thresholdFloor ? eta * incrementalFactor : eta : eta;
        } 
}
