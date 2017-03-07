/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 6, 2017
* Time: 9:28:41 PM
*
* Project: csci205_hw
* Package: hw01
* File: Layer
* Description:
* This class represents a Layer in the ANN
* A layer has its own Perceptrons with their own weights and values
* ****************************************
 */
package hw01;

import java.util.ArrayList;

/**
 *
 * @author mjrm001
 */
public class Layer {

    /**
     * This is the list of perceptrons in this layer
     */
    private ArrayList<Perceptron> perList;

    public Layer() {

    }

    /**
     * Gets the perceptron list for the layer
     *
     * @return An ArrayList of perceptrons
     * @author Michael Matirko
     */
    public ArrayList<Perceptron> getPerList() {
        return perList;
    }

    /**
     * Sets the perceptron list for the layer
     *
     * @param ArrayList<Perceptron> perList
     * @author Michael Matirko
     */
    public void setPerList(ArrayList<Perceptron> perList) {
        this.perList = perList;
    }

    /**
     * Sets a specific perceptron in the layer
     *
     * @param index
     * @param Perceptron perc
     * @author Michael Matirko
     */
    public void setPer(int index, Perceptron perc) {
        this.perList.set(index, perc);
    }

    /**
     * Adds a perceptron to the layer
     *
     * @param Perceptron perc
     * @author Michael Matirko
     */
    public void addPer(Perceptron perc) {
        this.perList.add(perc);
    }

    /**
     * This calculates the net value of that perceptron's weight (currently each
     * perceptron has one weight)
     *
     * @return
     */
    public float net() {
        // Calculate net function for the layer
        float net = (float) 0.0;

        for (Perceptron p : this.perList) {
            for (float weight : p.getWeightArr()) {
                net += p.getValue() * weight;
            }
        }

        return net;
    }

    /**
     * Updates all of the neurons in this layer with new values in the form of
     * an ArrayList of floats.
     *
     * @param newValues
     * @return
     */
    public float setValuesForLayer(ArrayList<Float> newValues) {
        for (int i = 0; i < this.perList.size(); i++) {
            Perceptron p = this.perList.get(i);
            p.setValue(newValues.get(i));
        }
    }

    @Override
    public String toString() {
        return "Layer{ " + " perList = " + perList + '}';
    }

}
