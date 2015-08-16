//Author: Jordan Micah Bennett
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.packages.UNICODE.UNICODE_ConveniencePack;
import java.util.ArrayList;

public class LogicalOperationDemonstrationLayer extends GenericDemonstrationLayer //autoencoder
{
    //define constructor
    public LogicalOperationDemonstrationLayer ( boolean consoleDisplayQuery )
    {
        super ( consoleDisplayQuery );
    }
    
    //define methods
        //define accessors
        //define mutators
        public void run ( ) //this must be binded with updateOutcomeDocument. {TO DO}
        {
            SUPERVISION_LAYER = new LogicalOperationSupervisionLayer ( "data/config/logical operation/xor.ini", consoleDisplayQuery ); //supervision data for training
            NEURAL_NETWORK = new NeuralNetwork ( "2,2,1", consoleDisplayQuery, SUPERVISION_LAYER.getData ( ).size ( ) ); //2x2x1 nodal configuration
    
            //{sLII - supervision layer data iterator}
            for ( int sLDI = 0; sLDI < SUPERVISION_LAYER.getData ( ).size ( ); sLDI ++ )
            {  
                NEURAL_NETWORK.propagateProgressively ( SUPERVISION_LAYER.getInputs ( SUPERVISION_LAYER.getData ( ).get ( sLDI ) ) );
                NEURAL_NETWORK.propagateRegressively ( SUPERVISION_LAYER.getExpectedOutcomes ( SUPERVISION_LAYER.getData ( ).get ( sLDI ) ) );
            }
        }
}
