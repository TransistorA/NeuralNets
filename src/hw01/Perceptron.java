/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 5, 2017
* Time: 4:50:11 PM
*
* Project: csci205_hw
* Package: hw01
* File: Perceptron
* Description:
* This class represents one individual perceptron in the neural network
* ****************************************
 */
package hw01;

/**
 *
 * @author Michael Matirko and Annan Miao
 */
public class Perceptron {

    /**
     * This int[] contains all of the input values passed into the perceptron.
     */
    private int[] inputArr;

    /**
     * This array is an array of all of the weights of the perceptron's weights.
     * It corresponds to the inputs in inputArr.
    *
     */
    private float[] weightArr;

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * inputs passed to it, and the weights passed to it.
     *
     * @param inputArr - An array of ints (the input values)
     * @param weightArr - An array of floats (the weight values)
     * @author Michael Matirko
     *
     */
    public Perceptron(int[] inputArr, float[] weightArr) {
        this.inputArr = inputArr;
        this.weightArr = weightArr;
    }

    private float netFunction() {
        // This is the net sum of all of the inputs and their weights
        float netSum = 0;
        for (int i = 0; i < inputArr.length; i++) {
            netSum += inputArr[i] * weightArr[i];
        }

        return netSum;
    }

}
