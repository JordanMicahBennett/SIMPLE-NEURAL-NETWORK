/* 
    Author ~ Jordan Micah Bennett 
    A simplistic artificial neural network, that naturally computes xor-logic prediction, via stochastic gradient descent.
*/
package syntheticsentience;

//Activation/Variance enumerations
public enum Variance { TANGENTIALLY_HYPERBOLIC /*Hyperbolic Tangent Function*/, LINEARLY_RECTIFIED /* Rectified Linear Unit ( Softplus-Approximation )*/ }