***************************************
*****README FOR NEURAL NET HW PT 1*****
*****Michael Matirko & Annan Miao******
Bucknell CSCI 205: Professor Brian King
           Due: 3/8/2016
		   
-> Names: Michael Matirko, Annan Miao 

-> Primary Resources: The most important resource that we
used when completing this project was the PDF given with the
specifications for what was expected for HW1. We also used the
two videos that were posted - the second one was much more useful 
than the first one (because we found that it was just much of the
content from the PDF.) The second video, on the other hand, was
very helpful when trying to understand all of the math that was 
used for the back propagation of the errors. We had initially sought
other resources in the hopes of finding something that would give
a simple overview of the math, but quickly found that the simplest 
resource that we could find (anywhere) was the second video that
was posted by Professor Dancy. Additionally, we spoke with Ryan 
Pasculano (who completed this project last semester) and asked 
him about the validity of some of our ideas ("is it a good idea
to structure this like this? is it good to have a separate class for
this? etc...") He also helped clarify the concept of a perceptron
as we were both kind of unclear as to exactly what a perceptron was.
We searched for an implementation of the update (or train) funciton
on the internet, but neither of us found anything we were particularly
ok with using, so we went back to looking at the second video on 
Moodle for ideas.

-> Configuration files:
When running the program, we had intended that the user would run
NeuralNetClient. This client implements a public interface for the 
entire Neural Net, and accomplishes tasks for the user while
using the methods provided by the Neural Net class. We had hoped to 
use serialization in order to take all of the objects used by 
one instance of the neural net, package them up, and save them, but we
didn't get a chance to look into that (or whether it was possible or not).

-> Things we struggled with:
As mentioned above, we initially struggled with the concept of 
what exactly a perceptron was. Based on the image provided on the 
very first page of the PDF, we someone managed to come up with the idea
that a single perceptron had a list of inputs (x_0...x_m), a list of weights,
etc... We eventually realized that we needed to change this idea of a perceptron
(and we changed the resulting code for the perceptron to reflect this). Everything
else was relatively straightforward - until we got to attempting to make the
update function. This was the function intended to update the weight values
for all of the neurons based on the previous errors, and a constant. Conceptually,
we had issues with exactly how to take the ideas given to us in the second 
video, and translate them into code. For version two, we realize that we 
must find a way to successfully implement this function.

