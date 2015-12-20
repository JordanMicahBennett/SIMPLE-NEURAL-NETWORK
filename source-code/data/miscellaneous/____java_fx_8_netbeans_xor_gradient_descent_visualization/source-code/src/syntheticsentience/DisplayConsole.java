/* 
    Author ~ Jordan Micah Bennett 
    A simplistic artificial neural network, that naturally computes xor-logic prediction, via stochastic gradient descent.
*/
package syntheticsentience;
 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DisplayConsole extends Application 
{
    public Parent createContent ( ) 
    { 
        //..................................................
        //neuronal supervision layer definitions
            //Essential!, consists of neural network structure. (Abtracts/hides neural network computation logic)
            //By extension, this layer entails data parsing management schemes, that compute supervision cycles, that compute data organization, for artificial neuronal processing.
            LogicalOperationDemonstrationLayer DEMONSTRATION_LAYER = new LogicalOperationDemonstrationLayer ( "data/supervision.sequence.ini", false, "2,2,1" );
        
        //..................................................
        //User Interface layer
            //neuronalSupervisionCycle graph series structure
            XYChart.Series<String, Number> neuronalSupervisionCycleGraphStructure = new XYChart.Series<> ( );

            //create a neuronalSupervisionCycleToggle Toggle for initializing our new stage
            Button neuronalSupervisionCycleToggle = new Button ( "Initialize neuronal xOR [supervision] cycle ( training )" );
            neuronalSupervisionCycleToggle.setStyle ( "-fx-font-size: 24;" );
            neuronalSupervisionCycleToggle.setDefaultButton ( true );
            neuronalSupervisionCycleToggle.setOnAction
            ( 
                ( ActionEvent t ) ->
                {
                    final Stage stage = new Stage ( );

                    //create root node of scene, i.e. group
                    Group rootGroup = new Group ( );

                    //create scene with set width, height and color
                    Scene scene = new Scene ( rootGroup, 800, 600, Color.WHITESMOKE );

                    //set scene to stage
                    stage.setScene ( scene );

                    //set title to stage
                    stage.setTitle ( "Nueronal Supervision Cycle" );

                    //center stage on screen
                    stage.centerOnScreen ( );

                    //show the stage
                    stage.show ( );

                    //establish information notation label nodes
                        //establish layout layer
                        GridPane layoutLayer = new GridPane ( );
                            layoutLayer.setVgap ( 4 );
                            layoutLayer.setHgap ( 12 );


                        //establish structures
                        final Label label_I_TopologyCardinality = new Label ( DEMONSTRATION_LAYER.NEURAL_NETWORK.getTopologyCardinalityMessage ( ) );
                        label_I_TopologyCardinality.setStyle ( "-fx-font-size: .8em;" );
                        final Label label_II_NeuronCardinality = new Label ( DEMONSTRATION_LAYER.NEURAL_NETWORK.getNeuronCardinalityMessage ( ) );
                        label_II_NeuronCardinality.setStyle ( "-fx-font-size: .8em;" );
                        final Label label_III_HyperParameterSummary = new Label ( "{ { η , α } ( " + DEMONSTRATION_LAYER.NEURAL_NETWORK.eta + "," + DEMONSTRATION_LAYER.NEURAL_NETWORK.alpha + " ) }" ); //describes eta/alpha
                        label_III_HyperParameterSummary.setStyle ( "-fx-font-size: .8em;" );
                        final Label label_IV_Iteration = new Label ( "{ .. }" ); //describes synonymous supervision cycle iteration
                        label_IV_Iteration.setStyle ( "-fx-font-size: .8em;" ); 
                        final Label label_V_CycleStatus = new Label ( " computing outcome... " ); //describes cycle status
                        label_V_CycleStatus.setStyle ( "-fx-font-size: .8em;" ); 
                        
                        //organize structures ~ establish layer constraints par 'aformentioned' nodes
                        GridPane.setConstraints ( label_V_CycleStatus, 1, 0 );
                        GridPane.setConstraints ( label_I_TopologyCardinality, 1, 0 );
                        GridPane.setConstraints ( label_II_NeuronCardinality, 1, 1 );
                        GridPane.setConstraints ( label_III_HyperParameterSummary, 1, 2 );
                        GridPane.setConstraints ( label_IV_Iteration, 1, 3 );
                        
                        
                        //populate layout layer par 'aforementioned nodes'
                        layoutLayer.getChildren ( ).addAll ( label_I_TopologyCardinality, label_II_NeuronCardinality, label_III_HyperParameterSummary, label_IV_Iteration, label_V_CycleStatus );

                        //establish graph 
                            LineChart <String, Number> neuronalSupervisionCycleChart;
                            CategoryAxis horizontalAxis;
                            NumberAxis verticalAxis;    

                            horizontalAxis = new CategoryAxis ( );
                            verticalAxis = new NumberAxis ( );
                            neuronalSupervisionCycleChart = new LineChart<> ( horizontalAxis, verticalAxis );

                            // setup chart
                            neuronalSupervisionCycleChart.setTitle ( "Neuronal Error Graph" );
                            horizontalAxis.setLabel ( "cycle iteration" );
                            verticalAxis.setLabel ( "network eror sigma" );

                            // add starting data
                            neuronalSupervisionCycleGraphStructure.setName ( "Neuronal Error Graph Structure" );
                            
                            //establish neural network ineterface response elements
                            DEMONSTRATION_LAYER.establishUserInterfaceResponseComponents ( neuronalSupervisionCycleGraphStructure, label_IV_Iteration, label_V_CycleStatus );
                            
                            //execute neural network computation
                            DEMONSTRATION_LAYER.run ( );
                            
                            neuronalSupervisionCycleChart.getData ( ).add ( neuronalSupervisionCycleGraphStructure );    

                        //organize structures ~ establish layer constraints par 'aformentioned' nodes
                        GridPane.setConstraints ( label_I_TopologyCardinality, 1, 20 );   

                        //populate layout layer par 'aforementioned nodes'
                        layoutLayer.getChildren ( ).addAll ( neuronalSupervisionCycleChart );

                    //populate this stage    
                    rootGroup.getChildren ( ).add ( layoutLayer );
                }
            );


            Button neuronalDemonstrationCycleToggle = new Button ( "Initialize neuronal xOR [demonstration] cycle ( validation )" );
            neuronalDemonstrationCycleToggle.setStyle ( "-fx-font-size: 24;" );

            neuronalDemonstrationCycleToggle.setOnAction
            ( 
                ( ActionEvent t ) ->
                {
                    if ( DEMONSTRATION_LAYER.SUPERVISION_ENQUIRY.get ( ) )
                    {
                    
                        final Stage stage = new Stage ( );

                        //create root node of scene, i.e. group
                        Group rootGroup = new Group ( );

                        //create scene with set width, height and color
                        Scene scene = new Scene ( rootGroup, 420, 240, Color.WHITESMOKE );

                        //set scene to stage
                        stage.setScene ( scene );

                        //set title to stage
                        stage.setTitle ( "Nueronal Demonstration Cycle" );

                        //center stage on screen
                        stage.centerOnScreen ( );

                        //show the stage
                        stage.show ( );

                        //establish information notation label nodes
                            //establish layout layer
                            GridPane layoutLayer = new GridPane ( );
                                layoutLayer.setVgap ( 4 );
                                layoutLayer.setHgap ( 12 );

                            //establish structures
                            final Label label_I_TopologyCardinality = new Label ( DEMONSTRATION_LAYER.NEURAL_NETWORK.getTopologyCardinalityMessage ( ) );
                            label_I_TopologyCardinality.setStyle ( "-fx-font-size: .8em;" );
                            final Label label_II_NeuronCardinality = new Label ( DEMONSTRATION_LAYER.NEURAL_NETWORK.getNeuronCardinalityMessage ( ) );
                            label_II_NeuronCardinality.setStyle ( "-fx-font-size: .8em;" );
                            final Label label_III_HyperParameterSummary = new Label ( "{ { η , α } ( " + DEMONSTRATION_LAYER.NEURAL_NETWORK.eta + "," + DEMONSTRATION_LAYER.NEURAL_NETWORK.alpha + " ) }" ); //describes eta/alpha
                            label_III_HyperParameterSummary.setStyle ( "-fx-font-size: .8em;" );
                            final Label label_IV_Outcome= new Label ( "{label_IV_Outcome}" ); //describes outcome
                            label_IV_Outcome.setStyle ( "-fx-font-size: .8em;" );   

                   
                            
                            //organize structures ~ establish layer constraints par 'aformentioned' nodes
                            GridPane.setConstraints ( label_I_TopologyCardinality, 1, 0 );
                            GridPane.setConstraints ( label_II_NeuronCardinality, 1, 1 );
                            GridPane.setConstraints ( label_III_HyperParameterSummary, 1, 2 );
                            GridPane.setConstraints ( label_IV_Outcome, 1, 3 );

                            //populate layout layer par 'aforementioned nodes'
                            layoutLayer.getChildren ( ).addAll ( label_I_TopologyCardinality, label_II_NeuronCardinality, label_III_HyperParameterSummary, label_IV_Outcome );


                        //establish radio button nodes
                            HBox radioButtonScenario = new HBox ( 18 );
                            radioButtonScenario.setAlignment ( Pos.CENTER );
                            VBox radioButtonVerticallBox = new VBox ( );
                            radioButtonVerticallBox.setSpacing ( 5 );
                            radioButtonVerticallBox.setAlignment ( Pos.CENTER_LEFT );


                            ToggleGroup radioButtonToggleGroup = new ToggleGroup ( );

                            RadioButton radioButtonI = new RadioButton ( "0, 0" );
                            radioButtonI.setToggleGroup ( radioButtonToggleGroup );
                            radioButtonI.setSelected ( true );

                            RadioButton radioButtonII = new RadioButton ( "0, 1" );
                            radioButtonII.setToggleGroup ( radioButtonToggleGroup );

                            RadioButton radioButtonIII = new RadioButton ( "1, 0" );
                            radioButtonIII.setToggleGroup ( radioButtonToggleGroup );

                            RadioButton radioButtonIV = new RadioButton ( "1, 1" );
                            radioButtonIV.setToggleGroup ( radioButtonToggleGroup );

                            //organize structures ~ establish layer constraints par 'aformentioned' nodes
                            GridPane.setConstraints ( radioButtonI, 1, 9 );
                            GridPane.setConstraints ( radioButtonII, 1, 10 );
                            GridPane.setConstraints ( radioButtonIII, 1, 11 );
                            GridPane.setConstraints ( radioButtonIV, 1, 12 );

                            //populate layout layer par 'aforementioned nodes'
                            layoutLayer.getChildren ( ).addAll ( radioButtonI, radioButtonII, radioButtonIII, radioButtonIV );


                            //create a detection operation node
                            //this node triggers progressive propagation, therein altering
                            //outcomeLabel in outcome terms.
                            Button outcomePredictionToggle = new Button ( "Compute prediction" );
                            outcomePredictionToggle.setStyle ( "-fx-font-size: 12;" );
                            outcomePredictionToggle.setDefaultButton ( true );
                            outcomePredictionToggle.setOnAction
                            ( 
                                ( ActionEvent outcomePredictionToggleActionEvent ) ->
                                {
                                    //selected radio button data
                                    String unsupervisedInput = ( ( ( RadioButton ) radioButtonToggleGroup.getSelectedToggle ( ) ).getText ( ) ).replace ( " ", "" );
                                    
                                    //compute prediction on selected data 'unsupervisedInput'
                                    DEMONSTRATION_LAYER.NEURAL_NETWORK.propagateProgressively ( DEMONSTRATION_LAYER.SUPERVISION_LAYER.getInputs ( unsupervisedInput + "::" ) ); 
                                
                                    //update outcome label, therein notifying user via computed outcome
                                    label_IV_Outcome.setText ( DEMONSTRATION_LAYER.NEURAL_NETWORK.getOutcomes ( ).toString ( ) + " on [ checkbox-selection [ " + unsupervisedInput + " ] ]" );
                                }  
                            );

                            //organize structures ~ establish layer constraints par 'aformentioned' nodes
                            GridPane.setConstraints ( outcomePredictionToggle, 1, 18 );

                            //populate layout layer par 'aforementioned nodes'
                            layoutLayer.getChildren ( ).addAll ( outcomePredictionToggle );


                        //populate this stage    
                        rootGroup.getChildren ( ).add ( layoutLayer );
                    }
                    else
                        new Utilities ( ).invokeDialog ( null, "Supervision Error! Initially, invoke supervision, via \"Initialize neuronal xOR [supervision] cycle ( training )\" toggle." );
                }
            );


           // add buttons and label to grid and set their positions
            GridPane.setConstraints ( neuronalSupervisionCycleToggle, 0, 0 );
            GridPane.setConstraints ( neuronalDemonstrationCycleToggle, 1, 0 );
            GridPane scenarioLayout = new GridPane ( );
            scenarioLayout.setVgap ( 20 );
            scenarioLayout.setHgap ( 12 );
            scenarioLayout.getChildren ( ).addAll ( neuronalSupervisionCycleToggle, neuronalDemonstrationCycleToggle );
            scenarioLayout.setAlignment ( Pos.CENTER );
            return scenarioLayout;
    }
 
    @Override
    public void start ( Stage primaryStage ) throws Exception 
    {
        primaryStage.setScene ( new Scene ( createContent ( ) ) );
        primaryStage.show ( );
    }
 
    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main ( String[] args ) 
    {
        launch ( args );
    }
}