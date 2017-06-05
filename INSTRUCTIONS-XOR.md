Author : Jordan Micah Bennett

Aim : Artificial neuronal instruction par xor detection.


![Alt text](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/data/images/captures/1.png?raw=true "default page")
============================================



????
=======
Synthetic sentience initializes via '[xor] detection cycle'. 

Cycles simply indicate neuronal instruction types. 

i.[logical operation [xor] detection cycle] - xor detection

ii.[visual [digit] detection cycle] - vision detection

		
		
SEQUENCE
=======
[source-code/UNICODE_DisplayConsole](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/UNICODE_DisplayConsole.java).void ( ) -> DETECT (See 'DETECTING')
 


DETECTING
=======
i.Select any xor configuration [0,0 | 0,1 | 1,0 | 1,1] via checkbox list.

ii.Toggle 'detect'.

iii.See "RECALLING", "QUANTIZING" & "INSTRUCTING"

NOTE: Instruction cycle converges on [source-code/data/config/logical operation/xor.ini](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/data/config/logical%20operation/xor.ini). [See [source-code/data/config/readme-config-details.md](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/data/config/readme-config-details.md)]

Select customized instruction cycle origin via zeroeth parameter in initialization of SUPERVISION_LAYER variable, via [source-code/LogicalOperationDemonstrationLayer.java](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/LogicalOperationDemonstrationLayer.java)/run ( ) method.

RECALLING
=======
Recalling utilizes [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/NeuralNetwork.java)/recall ( ) to remember neural network data. (learnt gradients/outcomes/weights/delta weights memorization-recollection)



QUANTIZING
=======
Quantizing utilizes [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/NeuralNetwork.java)/quantize ( ) to store neural network data. (learnt gradients/outcomes/weights/delta weights memorization-storage)


NOTE: Par 'quantize' toggle, SIMPLE-NEURAL-NETWORK's physical package size shall increase, on an order of selected training configuration vector.



INSTRUCTING
=======
Instruction utilizes [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/NeuralNetwork.java)/propagateProgressively ( ) and [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/NeuralNetwork.java)/propagateRegressively ( ) to generate instruction cycle, via 'instruct' toggle. 

Enhanced outcomes are occur-able via sequence: [source-code/UNICODE_DisplayConsole](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/UNICODE_DisplayConsole.java).void ( ) -> QUANTIZE -> EXIT -> [source-code/UNICODE_DisplayConsole](https://github.com/JordanMicahBennett/SIMPLE-NEURAL-NETWORK/blob/master/source-code/UNICODE_DisplayConsole.java).void ( ) -> RECALL -> INSTRUCT -> DETECT. 






PERFORMANCE
=======
[Machine specifications: [ CPU : dual core 3.0 gigahertz ], [ RAM : 4 gigabytes ], [ ELAPSED TIME : 1 minute ] ]

...


Now, the digit outcome vector [ -1.0 ], (Seen in the concluding line of on-screen console display) directly relates in the neural mechanism's outcome layer. (...one vector index per neuron)


Par exemplification:

A.Outcome: [ 0.82 ] on [ checkbox-selection [ 0,1 ] ]

Strongly indicates perhaps; one (1) is detected, based on checkbox-selection non-supervised input.


B.Outcome: [ 0.26 ] on [ checkbox-selection [ 0,0 ] ]

Strongly indicates perhaps; zero (0) is detected, based on checkbox-selection non-supervised input.
