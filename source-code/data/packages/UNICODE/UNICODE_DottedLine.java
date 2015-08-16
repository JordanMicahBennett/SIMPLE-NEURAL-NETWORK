package data.packages.UNICODE;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class UNICODE_DottedLine
{
    //attributes
        //body features
            //bodies attribute
            private int xCoordA = 0, yCoordA = 0, xCoordB = 0, yCoordB = 0;
            private double dotLength = 0, spatialDistance = 0;
            //actual bodies
            private ArrayList <Ellipse2D> dotCollection = null;
       //line animation requirements
            private Timer animationTimer = null;
            private int maximumDots = 0;
            private int currentDotAnimationIndex = -1;
            private JPanel destinationPanel = null;
            private ArrayList <Double> animationSpanList = null, reversedAnimationSpanList = null;
            private int animationDelay;
            private boolean animationSpanCompletionEnquiry = false;
        //camera requirements
        private Rectangle contextFrame = null;
        
    //constructor
    public UNICODE_DottedLine ( int xCoordA, int yCoordA, int xCoordB, int yCoordB, double dotLength, double spatialDistance, int animationDelay, JPanel destinationPanel, Rectangle contextFrame )
    {
            //establish attributes
            this.xCoordA = xCoordA;
            this.yCoordA = yCoordA;
            this.xCoordB = xCoordB;
            this.yCoordB = yCoordB;
            this.dotLength = dotLength;
            this.spatialDistance = spatialDistance;
            this.destinationPanel = destinationPanel;
            this.animationDelay = animationDelay;
            //establish dot bodies
            dotCollection = new ArrayList <Ellipse2D> ( );
            //generate dotted line
            generateDottedLine ( );
            //establish animation timer
            animationTimer = new Timer ( animationDelay, new animationListener ( ) );
            //establish animation span list 
            this.animationSpanList = getAnimationSpanList ( );
            this.reversedAnimationSpanList = getReversedAnimationSpanList ( animationSpanList );
            this.contextFrame = contextFrame;
    }
    
    //methods
        //accessors
        public int getCurrentDotAnimationIndex ( )
        {
            return currentDotAnimationIndex;
        }
        
        public int getPreviousDotAnimationIndex ( )
        {
            int returnValue = 0;
            
            if ( getCurrentDotAnimationIndex ( ) - 1 >= 0 )
                returnValue = getCurrentDotAnimationIndex ( ) - 1;
            
            return returnValue;
        }
        
        public ArrayList <Double> getAnimationSpanList ( )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );
            
            double spanIncrementationRate = ( double ) dotLength / ( double ) maximumDots, spanComponent = 0.0;
            
            for ( int i = 0; i < maximumDots; i ++ )
            {
                returnValue.add ( 0 + spanComponent );
                spanComponent += spanIncrementationRate;
            }
            
            return returnValue; 
        }
        
        public ArrayList <Double> getReversedAnimationSpanList (  ArrayList <Double> defaultAnimationSpanList )
        {
            ArrayList <Double> returnValue = new ArrayList <Double> ( );
            
            for ( int i = defaultAnimationSpanList.size ( ) - 1; i >= 0; i -- )
                returnValue.add ( defaultAnimationSpanList.get ( i ) );
            
            return returnValue; 
        }   
        
        public Timer getAnimationTimer ( )
        {
            return animationTimer;
        }
        public boolean getAnimationSpanCompletionEnquiry ( )
        {
            return animationSpanCompletionEnquiry;
        }
        
        //mutators
        public void toggleDotAnimationIndex ( )
        {
            if ( getAnimationSpanCompletionEnquiry ( ) == false )
                currentDotAnimationIndex ++;
        }
        public void toggleDotReversedAnimationIndex ( )
        {
            if ( getAnimationSpanCompletionEnquiry ( ) == true )
                currentDotAnimationIndex --;
        }
        public void startAnimation ( )
        {
            animationTimer.start ( );
        }
        public void stopAnimation ( )
        {
            animationTimer.stop ( );
        }
        public void setAnimationSpanCompletionEnquiry ( boolean value )
        {
            animationSpanCompletionEnquiry = value;
        }
        
        //other
        public void generateDottedLine ( )
        {
            double lineLength = Math.sqrt ( ( xCoordB - xCoordA ) * ( xCoordB - xCoordA ) + ( yCoordB - yCoordA ) * ( yCoordB - yCoordA ) );
            double yIncrementation = ( yCoordB - yCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
            double xIncrementationSpatialDistance = ( xCoordB - xCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
            double yIncrementationSpatialDistance = ( yCoordB - yCoordA ) / ( lineLength / ( dotLength + spatialDistance ) );
            
            int counter = 0;
            
            for ( double i = 0; i < lineLength - dotLength; i += dotLength + spatialDistance )
            {
                //limit computation and object cardinality with respect to xCoord limit supplied. (xCoordB)
                int xCoord = ( int ) ( xCoordA + xIncrementationSpatialDistance * counter );
                int yCoord = ( int ) ( yCoordA + yIncrementationSpatialDistance * counter );
                
                if ( contextFrame.contains ( xCoord, yCoord ) )
                {
                    dotCollection.add ( new Ellipse2D.Double ( xCoord, yCoord, ( int ) dotLength, ( int ) dotLength ) );
                
                    counter ++;
                }
            }
            
            //establish maximum dots
            maximumDots = dotCollection.size ( );
        }
        
        public void draw ( Graphics2D graphics2d, Color lineColour )
        {
            //draw bodies
            graphics2d.setColor ( lineColour );
            
            for ( int i = 0; i < dotCollection.size ( ); i ++ )
                graphics2d.fill ( dotCollection.get ( i ) );
        }
        
        public void enableAnimationStepFunction ( ArrayList <Double> animSpanCollection )
        {
            toggleDotAnimationIndex ( );
            
            if ( getCurrentDotAnimationIndex ( ) < ( maximumDots - 1 ) )
            {
                double xCoordinate = dotCollection.get ( getCurrentDotAnimationIndex ( ) ).getX ( );
                double yCoordinate = dotCollection.get ( getCurrentDotAnimationIndex ( ) ).getY ( ); 
                
                double span = animSpanCollection.get (  getCurrentDotAnimationIndex ( ) );
               
                Ellipse2D replacementCurrentDot = new Ellipse2D.Double ( xCoordinate, yCoordinate, span, span );
                dotCollection.set ( getCurrentDotAnimationIndex ( ), replacementCurrentDot );
            }
        }
        
        public void enableReversednimationStepFunction ( ArrayList <Double> animSpanCollection )
        {
            toggleDotReversedAnimationIndex ( );
            
            if ( getCurrentDotAnimationIndex ( ) > 0 )
            {
                double xCoordinate = dotCollection.get ( getCurrentDotAnimationIndex ( ) ).getX ( );
                double yCoordinate = dotCollection.get ( getCurrentDotAnimationIndex ( ) ).getY ( ); 
                
                double span = animSpanCollection.get (  getCurrentDotAnimationIndex ( ) );
               
                Ellipse2D replacementCurrentDot = new Ellipse2D.Double ( xCoordinate, yCoordinate, span, span );
                dotCollection.set ( getCurrentDotAnimationIndex ( ), replacementCurrentDot );
            }
        }
        public void doAnimationStep ( )
        {
            if ( ( getCurrentDotAnimationIndex ( ) < ( maximumDots - 1 ) ) && ( getAnimationSpanCompletionEnquiry ( ) == false ) )
            {
                enableAnimationStepFunction ( animationSpanList );
                
                if ( getCurrentDotAnimationIndex ( ) >= ( maximumDots - 1 ) )
                    setAnimationSpanCompletionEnquiry ( true );
            }
            else 
            {
                enableReversednimationStepFunction ( reversedAnimationSpanList );
                
                if ( getCurrentDotAnimationIndex ( ) <= 0 )
                    setAnimationSpanCompletionEnquiry ( false );
            }
            
            destinationPanel.repaint ( );
        }
        
        private class animationListener implements ActionListener
        {
            public void actionPerformed ( ActionEvent actionEvent )
            {
                doAnimationStep ( );
            }
        }
}