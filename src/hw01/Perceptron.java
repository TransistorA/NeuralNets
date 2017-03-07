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

import java.util.ArrayList;

/**
 *
 * @author Michael Matirko and Annan Miao
 */
public class Perceptron {

    /**
     * This int[] contains all of the input values passed into the perceptron.
     */
    private ArrayList<Integer> inputArr;

    /**
     * This array is an array of all of the weights of the perceptron's weights.
     * It corresponds to the inputs in inputArr.
     *
     */
    private ArrayList<Float> weightArr;

    /**
     * This is the target value for that particular perceptron
     */
    private int output; //target
    private float bias;
    private int numInput;
    private int error;

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * inputs passed to it, and random weights (as this constructor does not
     * take a weight list as an argument.)
     *
     * @param inputArr - An array of integers (the input values)
     * @param output
     * @author Michael Matirko
     *
     */
    public Perceptron(ArrayList<Integer> inputArr, int output,
                      ArrayList<Float> weightArr) {
        this.inputArr = inputArr;
        this.output = output;
        this.numInput = inputArr.size();

        // If no weightArray is passed in, just assume that it
        // is supposed to be initialized to random values
    }

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * inputs passed to it, and the weights passed to it.
     *
     * @param inputArr - An array of ints (the input values)
     * @param weightArr - An array of floats (the weight values)
     * @author Michael Matirko
     *
     */
    public Perceptron(ArrayList<Integer> inputArr, ArrayList<Float> weightArr) {
        this.inputArr = inputArr;
        this.weightArr = weightArr;
    }

    /**
     * This function takes no arguments, and calculates the net function for
     * that particular perceptron (as a float). The net function is the sum of
     * each input with its weight.
     *
     * @return A float corresponding to the net function's value
     * @author Michael Matirko
     */
    public int Error() {
        // This is the net sum of all of the inputs and their weights
        /*if (this.inputArr.size() != this.weightArr.size()) {
            throw new IllegalArgumentException(
                    "Size Error" + this.inputArr.size() + " " + this.weightArr.size());
        }*/

        float netSum = 0;
        for (int i = 0; i < inputArr.size(); i++) {
            netSum += inputArr.get(i) * weightArr.get(i);
        }

        netSum += bias;
        this.error = output - step(netSum);

        return error;
    }

    public static int step(float num) {
        if (num >= 1) {
            return 1;
        }
        return 0;
    }

}
