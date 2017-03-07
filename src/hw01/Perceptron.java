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
import java.util.Random;

/**
 *
 * @author Michael Matirko and Annan Miao
 */
public class Perceptron {

    /**
     * This array is an array of all of the weights of the perceptron's weights.
     * It corresponds to the inputs in inputArr.
     *
     */
    private ArrayList<Float> weightArr;

    private float value;

    private float ACTIVATION_CONST;

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * value passed to it, and random weights to populate the weight list
     *
     * @author Michael Matirko
     *
     */
    public Perceptron(int numweights) {
        Random randnumObj = new Random();

        for (int i = 0; i < numweights; i++) {
            // Set the weight to a random float between -.5 and .5
            weightArr.set(i, randnumObj.nextFloat() - (float) 0.5);
        }

    }

    /**
     * Gets the weight array from the perceptron
     *
     * @return
     */
    public ArrayList<Float> getWeightArr() {
        return weightArr;
    }

    /**
     * Sets the weight for the perceptron with the specified position in the
     * weight ArrayList
     *
     * @param position
     * @param weight
     * @author Michael Matirko
     */
    public void setWeight(int position, float weight) {
        this.weightArr.set(position, weight);
    }

    /**
     * Gets the value of this perceptron
     *
     * @return A float corresponding to the value of the perceptron
     * @author Michael Matirko
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets the value of the perceptron
     *
     * @return Nothing
     * @param value - the value to set the perceptron's value to
     * @author Michael Matirko
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Tells whether this perceptron is "activated" or not
     *
     * @param net
     * @return Either 1 or zero, depending on whether the perceptron is
     * activated
     * @author Michael Matirko
     */
    public int activation(float net) {
        if (net - this.ACTIVATION_CONST > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Returns a visual representation of a Perceptron object (primarily for
     * debugging purposes, but also cool to look at)
     *
     * @return A string representing a Perceptron
     */
    @Override
    public String toString() {
        return "Perceptron{" + "weightArr = " + weightArr + ", value=" + value + '}';
    }

}
