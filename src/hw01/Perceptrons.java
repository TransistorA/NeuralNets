/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 8, 2017
* Time: 4:04:44 AM
*
* Project: csci205_hw
* Package: hw01
* File: ANN_complex
* Description:
*
* ****************************************
 */
package hw01;

import java.util.ArrayList;

/**
 *
 * @author am049
 */
public class Perceptrons {

    private int numIn;
    private ArrayList<Double> inputs = new ArrayList<>();
    private ArrayList<Double> outputs = new ArrayList<>();
    private int numGroup;
    private ArrayList<Perceptron> plist = new ArrayList<>();
    private double sumweight; // the weight that connects the output node to the node next to output node

    public Perceptrons(ArrayList inputs, int numIn, double sumweight) {
        this.numIn = numIn;
        this.inputs = inputs;
        this.sumweight = sumweight;
        buildLayer();
        generateOutputs();
    }

    public void buildLayer() {
        this.numGroup = this.inputs.size() / this.numIn;

        for (int i = 0; i < this.numGroup; i++) {
            ArrayList newInputs = new ArrayList(inputs.subList(i * numIn,
                                                               i * numIn + numIn));
            Perceptron e = new Perceptron(newInputs, this.sumweight);
            this.plist.add(e);
        }
    }

    public void generateOutputs() {
        for (Perceptron e : this.plist) {
            outputs.add(e.getOutput());
        }
    }

    public void print(ArrayList a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public ArrayList getOutputs() {
        return this.outputs;
    }

    public void update(ArrayList<Double> deltas) {
        for (int i = 0; i < this.plist.size(); i++) {
            Perceptron e = this.plist.get(i);
            e.setDelta(deltas.get(i));
            e.updateWeights();
            this.plist.set(i, e);
        }
    }

}
