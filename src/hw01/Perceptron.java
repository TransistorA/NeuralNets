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
     * This is the value of the neuron.
     */
    private float value;

    /**
     * This is the activation constant (currently unused)
     */
    private float ACTIVATION_CONST;

    /**
     * This is the layer that the Perceptron resides in
     */
    private Layer layer;

    /**
     * Has the neuron been initialized or not? I.e, have we done any back
     * propagation yet?
     */
    private boolean status;

    /**
     * The constructor for the perceptron initializes each perceptron with the
     * value passed to it, and random weights to populate the weight list
     *
     * @author Michael Matirko
     * @param layer
     *
     */
    public Perceptron(Layer layer) {
        Random randnumObj = new Random();

        int numweights = layer.getPerList().size();

        for (int i = 0; i < numweights; i++) {
            // Set the weight to a random float between -2.4/m and 2.4/m
            weightArr.set(i,
                          (float) (-2.4f * randnumObj.nextDouble() / (float) this.layer.getNeuralNet().getNumInputs()));
        }

    }

    /**
     * Gets the weight array from the perceptron
     *
     * @return The array of weights
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
     * @author Annan Miao
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
        if (this.status) {
            // If we have assigned it a value
            return value;
        }
        else {
            // Otherwise, get a value from the other layers
            this.status = true;
            return this.value = activation(net());
        }
    }

    /**
     * This calculates the net function for the perceptron. It does it based on
     * the previous perceptrons in the previous layers that lead to it.
     *
     * @return A float with the net value
     */
    public float net() {
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
     * @return A float - (approximately) 1 or zero, depending on whether the
     * perceptron is activated based on the sigmoid function
     * @param the net - a float
     * @author Annan Miao
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
     * Wipes the value of the neuron (sets the value back to false)
     *
     * @author Michael Matirko
     */
    public void clean() {
        this.status = false;
    }

    /**
     * Returns a visual representation of a Perceptron object (primarily for
     * debugging purposes, but also cool to look at)
     *
     * @return A string representing a Perceptron
     * @author Basically netbeans (this is just generated code), Michael Matirko
     */
    @Override
    public String toString() {
        return "Perceptron{" + "weightArr = " + weightArr + ", value=" + value + '}';
    }

}
