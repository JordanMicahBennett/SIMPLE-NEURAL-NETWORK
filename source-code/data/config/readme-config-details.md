VISUAL 
=========



(small|large|medium|very large).ini format -> input::expected outcome:

You may configure zeroeth parameter of SUPERVISON_LAYER's initialization in [source-code/VisualDemonstrationLayer](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/VisualDemonstrationLayer.java) to stream any one of the four following supervision files ( ...small.ini by default ):




	"small.ini" - small supervision data { O ( T = time complexity ) of supervision time is the smallest }

	"medium.ini" - medium supervision data { O ( T = time complexity ) of supervision time is niether very fast, nor very slow, some effectiveness }

	"large.ini" - large supervision data { O ( T = time complexity ) of supervision time is the almost the slowest, performs more effectively than medium, albeit slower }

	"very large.ini" - very large supervision data { O ( T = time complexity ) of supervision time is the slowest, albeit most effective }
	
	
	
	
	
(small|large|medium|very large).ini contains configuration pairs of input data/expected outcome:

[data/images/patterns/test-1.bmp::-1 -1 -1 -1 -1 1 -1 -1 -1 -1]




Via aforementioned, "data/images/patterns/test-1.bmp" represents input data component origin,

while "-1 -1 -1 -1 -1 1 -1 -1 -1 -1" represents expected outcome array, where 1/-1 @ particular position represents activation/non-activation par digit.

	
	

	
	
	
LOGICAL OPERATION
=========




(xor|...).ini format -> input::expected outcome:




You may configure zeroeth parameter of SUPERVISON_LAYER's initialization in [source-code/LogicalOperationDemonstrationLayer](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/LogicalOperationDemonstrationLayer.java) to stream additional supervision files ( in the same dimension as facing sample - "xor.ini" ):
	

	
	
	
xor.ini contains configuration pairs of input data/expected outcome:

[1,0::1]




Via aforementioned, "1,0" represents input data component,

while "1" represents expected outcome value, where outcome height represents activation/non-activation par input.