/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 6, 2017
* Time: 9:28:41 PM
*
* Project: csci205_hw
* Package: hw02
* File: Layer
* Description:
* This class represents a Layer in the ANN
* A layer has its own Perceptrons with their own weights and values
* ****************************************
 */
package hw02;
import java.util.ArrayList;
/**
 *
 * @author mjrm001
 */
public class Layer implements java.io.Serializable {
    /**
     * This is the list of perceptrons in this layer
     */
    private ArrayList<Perceptron> perList = new ArrayList<>();
    /**
     * This is the previous layer's index
     */
    private int index;
    /**
     * This is the NeuralNet that this layer belongs to
     */
    private NeuralNet neuralnet;
    public Layer(int index, int numPerceptrons, NeuralNet neuralnet) {
        //Set the previous layer's index
        this.index = index;
        this.neuralnet = neuralnet;
        for (int i = 0; i < numPerceptrons; i++) {
            this.perList.add(new Perceptron(this));
        }
    }
    /**
     * Returns the previous layer (provided that this layer isn't an input
     * layer)
     *
     * @return The previous layer, or null if there is no previous layer
     * @author Michael Matirko
     */
    public Layer getPrevLayer() {
        if (this.index == 0) {
            return null;
        }
        else {
            return this.neuralnet.getLayer(this.index - 1);
        }
    }
    /**
     * Returns the next layer (provided that this layer isn't an output layer)
     *
     * @return The previous layer, or null if there is no next layer
     * @author Michael Matirko
     */
    public Layer getNextLayer() {
        // Make sure this isn't an output layer
        if (this.index == this.neuralnet.getLayerList().size() - 1) {
            return null;
        }
        else {
            return this.neuralnet.getLayer(this.index + 1);
        }
    }
    /**
     * Gets the perceptron list for the layer
     *
     * @return An ArrayList of perceptrons
     * @author Michael Matirko
     */
    public ArrayList<Perceptron> getPerList() {
        return this.perList;
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
     * Returns the neural net to which this layer belongs
     *
     * @return A neural net
     * @author Michael Matirko
     */
    public NeuralNet getNeuralNet() {
        return this.neuralnet;
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
    public void setValuesForLayer(ArrayList<Float> newValues) {
        for (int i = 0; i < this.perList.size(); i++) {
            Perceptron p = this.perList.get(i);
            p.setValue(newValues.get(i));
        }
    }
    @Override
    public String toString() {
        return "Layer{ " + " perList = " + perList + '}';
    }
    /**
     * Returns the index of the current layer
     *
     * @return The index - an int
     * @author Michael Matirko
     */
    public int getIndex() {
        return index;
    }
}
