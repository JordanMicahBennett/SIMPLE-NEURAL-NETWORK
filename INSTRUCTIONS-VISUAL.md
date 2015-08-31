Author : Jordan Micah Bennett

Aim : Artificial neuronal instruction par vision detection.


![Alt text](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/data/images/captures/0.png?raw=true "default page")
============================================



????
=======
Synthetic sentience initializes via 'visual [digit] detection cycle'. 

Cycles simply indicate neuronal instruction types. 

i.[logical operation [xor] detection cycle] - xor detection

ii.[visual [digit] detection cycle] - vision detection

NOTE: Synthetic-sentience encompasses colorimetric-space [relative luminance](https://en.wikipedia.org/wiki/Relative_luminance) based pixel extraction. See [source-code/data/packages/UNICODE/UNICODE_ConveniencePack/](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/data/packages/UNICODE/UNICODE_ConveniencePack.java)getPixels ( String fileName )

		
		
SEQUENCE
=======
UNPACK (See 'UNPACKING') -> [source-code/UNICODE_DisplayConsole](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/UNICODE_DisplayConsole.java).void ( ) -> DETECT (See 'DETECTING')
 
		
		
		
UNPACKING
=======
Unpack [source-code/data/images/pattern.zip](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/data/images/patterns.zip) into the directory of images/patterns/

Ensure that the 5000 32x32 pattern images are in [source-code/data/images/](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/tree/master/source-code/data/images)patterns/

NOTE: Whence extraction has completed, you shall see source-code/data/images/patterns/test-1.bmp through to directory/images/patterns/test-5000.bmp....


DETECTING
=======
i.Freely illustrate a digit by brushing such amidst vacant area betwixt button dock and console output.

ii.Clear unwanted illustration via 'clear' toggle.

iii.Toggle 'detect'.

iv.See "RECALLING", "QUANTIZING" & "INSTRUCTING"

NOTE: Instruction cycle converges on variations of unpacked image data ([source-code/data/config/](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/tree/master/source-code/data/config)(small|medium|large|very large).ini), such that network instruction is occur-able in partitions. [See [source-code/data/config/readme-config-details.md](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/data/config/readme-config-details.md)]

Select instruction partition via zeroeth parameter in initialization of SUPERVISION_LAYER variable, via [source-code/VisualDemonstrationLayer.java](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/VisualDemonstrationLayer.java)/run ( ) method.





RECALLING
=======
Recalling utilizes [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/NeuralNetwork.java)/recall ( ) to remember neural network data. (learnt gradients/outcomes/weights/delta weights memorization-recollection)




QUANTIZING
=======
Quantizing utilizes [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/NeuralNetwork.java)/quantize ( ) to store neural network data. (learnt gradients/outcomes/weights/delta weights memorization-storage)


NOTE: Par 'quantize' toggle, synthetic-sentience's physical package size shall increase, on an order of selected training configuration vector.




INSTRUCTING
=======
Instruction utilizes [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/NeuralNetwork.java)/propagateProgressively ( ) and [source-code/NeuralNetwork](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/NeuralNetwork.java)/propagateRegressively ( ) to generate instruction cycle, via 'instruct' toggle. 

Enhanced outcomes are occur-able via sequence: [source-code/UNICODE_DisplayConsole](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/UNICODE_DisplayConsole.java).void ( ) -> QUANTIZE -> EXIT -> [source-code/UNICODE_DisplayConsole](https://github.com/JordanMicahBennett/SYNTHETIC-SENTIENCE/blob/master/source-code/UNICODE_DisplayConsole.java).void ( ) -> RECALL -> INSTRUCT -> DETECT. 



MODES
=======
Brush styles exist bi-dimensionally; there are but two brush styles:

i.Continuous brush style (non-dotted, enabled via dragging mouse)

ii.Non-continuous brush style (dotted, enabled via clicking)

NOTE: Toggle mode switch via 'mode' toggle.
Brush is resizeable on mouse wheel roll.




PERFORMANCE
=======
[Machine specifications: [ CPU : dual core 3.0 gigahertz ], [ RAM : 4 gigabytes ], [ ELAPSED TIME : 40 minutes ] ]

...

Now, each digit in outcome vector [ -1.0 ... -1.0 ], (Seen in the concluding line of on-screen console display) directly relates in the neural mechanism's outcome layer. (...one vector index per neuron)


Par exemplification:

A.Outcome: [ 1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0 ] on [ proximally-freely-drawn-selection ]

Strongly indicates perhaps; one (1) is detected, based on freely drawn non-supervised input.


B.Outcome: [ -1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-0.923 ] on [ proximally-freely-drawn-selection ] 

Weakly indicates perhaps; nine (9) is detected, based on freely drawn non-supervised input.











