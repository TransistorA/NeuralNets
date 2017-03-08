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

    /**
     * This is the value of the neuron. It is a float so that when it's not set,
     * it's equal to null. (instead of 0)
     */
    private Float value;

    private float ACTIVATION_CONST;

    /**
     * This is the layer that the Perceptron resides in
     */
    private Layer layer;

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * value passed to it, and random weights to populate the weight list
     *
     * @author Michael Matirko
     *
     */
    public Perceptron(Layer layer) {
        Random randnumObj = new Random();

        int numweights = layer.getPerList().size();

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
    public Float getValue() {
        if (value != null) {
            return value;
        }
        else {
            return this.value = activation(net());
        }
    }

    public Float net() {
        ArrayList<Perceptron> prevList = this.layer.getPrevLayer().getPerList();
        float net = 0.0f;

        for (int i = 0; i < prevList.size(); i++) {
            Perceptron p = prevList.get(i);
            net += p.getValue() * this.weightArr.get(i);
        }

        return net;
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
     * activated based on the sigmoid function
     * @author Michael Matirko
     */
    public float activation(float net) {
        float fnet = 1 / (1 + (float) Math.pow(Math.E, -1 * net));

        /*
    *    if (net - this.ACTIVATION_CONST > 0) {
    *        return 1.0f;
    *    }
    *    else {
    *        return 0.0f;
    *    }
         */
        return fnet;

    }

    /**
     * Wipes the value of the neuron (sets the value back to null)
     */
    public void clean() {
        this.value = null;
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
