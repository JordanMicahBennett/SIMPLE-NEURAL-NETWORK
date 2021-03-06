//Author: Jordan Micah Bennett
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.packages.UNICODE.UNICODE_ConveniencePack;
import java.util.ArrayList;

public class VisualDemonstrationLayer extends GenericDemonstrationLayer //autoencoder
{
    //define features
    public int FILTER_BOUNDARY = 0;

    //define constructor
    public VisualDemonstrationLayer ( boolean consoleDisplayQuery, int FILTER_BOUNDARY )
    {
        super ( consoleDisplayQuery );
        this.FILTER_BOUNDARY = FILTER_BOUNDARY;
    }
    
    //define methods
        //define accessors
        //define mutators
     
        public void run ( ) //this must be binded with updateOutcomeDocument. {TO DO}
        {
            SUPERVISION_LAYER = new VisualSupervisionLayer ( "data/config/visual/medium.ini", consoleDisplayQuery, FILTER_BOUNDARY ); //supervision data for training
            NEURAL_NETWORK = new NeuralNetwork ( "1024,1024,10", consoleDisplayQuery, SUPERVISION_LAYER.getData ( ).size ( ) ); //1024x1024x10 nodal configuration
            
            //{sLIDI - supervision layer data iterator}
            for ( int sLDI = 0; sLDI < SUPERVISION_LAYER.getData ( ).size ( ); sLDI ++ )
            {  
                NEURAL_NETWORK.propagateProgressively ( SUPERVISION_LAYER.getInputs ( SUPERVISION_LAYER.getData ( ).get ( sLDI ) ) );
                NEURAL_NETWORK.propagateRegressively ( SUPERVISION_LAYER.getExpectedOutcomes ( SUPERVISION_LAYER.getData ( ).get ( sLDI ) ) );
            }
        }
}
