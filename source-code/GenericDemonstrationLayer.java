//Author: Jordan Micah Bennett
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import data.packages.UNICODE.UNICODE_ConveniencePack;
import java.util.ArrayList;

public abstract class GenericDemonstrationLayer //autoencoder
{
    //establish features
    public boolean consoleDisplayQuery; //determines whether System.out.println calls are executed
    public GenericSupervisionLayer SUPERVISION_LAYER;
    public NeuralNetwork NEURAL_NETWORK;
    
    //define constructor
    public GenericDemonstrationLayer ( boolean consoleDisplayQuery )
    {
        this.consoleDisplayQuery = consoleDisplayQuery;
    }
    
    //define methods
        //define accessors
        //define mutators
        public abstract void run ( ); //this must be binded with updateOutcomeDocument. {TO DO}
}
