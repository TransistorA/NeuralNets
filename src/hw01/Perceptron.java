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

import java.util.Random;

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
     * This is the target value for that particular perceptron
     */
    private int target;
    private int[] outputArr;

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * inputs passed to it, and random weights (as this constructor does not
     * take a weight list as an argument.)
     *
     * @param inputArr - An array of integers (the input values)
     * @param outputArr - An output array that will contain the outputs
     * @param target - the integer target value
     * @author Michael Matirko
     *
     */
    public Perceptron(int[] inputArr, int[] outputArr, int target) {
        this(inputArr, new float[1], outputArr, target);

        // If no weightArray is passed in, just assume that it
        // is supposed to be initialized to random values
        float[] weightArr = new float[inputArr.length];

        Random randnumObj = new Random();

        for (int i = 0; i < inputArr.length; i++) {
            // Set the weight to a random float between -.5 and .5
            weightArr[i] = randnumObj.nextFloat() - (float) 0.5;
        }

    }

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * inputs passed to it, and the weights passed to it.
     *
     * @param inputArr - An array of ints (the input values)
     * @param weightArr - An array of floats (the weight values)
     * @param outputArr - An output array that will contain the outputs
     * @param target - the integer target value
     * @author Michael Matirko
     *
     */
    public Perceptron(int[] inputArr, float[] weightArr, int[] outputArr,
                      int target) {
        this.inputArr = inputArr;
        this.weightArr = weightArr;
        this.outputArr = outputArr;
        this.target = target;
    }

    private float netFunction() {
        // This is the net sum of all of the inputs and their weights
        if (this.inputArr.length != this.outputArr.length) {
            throw new IllegalArgumentException("");
        }

        float netSum = 0;
        for (int i = 0; i < inputArr.length; i++) {
            netSum += inputArr[i] * weightArr[i];
        }

        return netSum;
    }

}
