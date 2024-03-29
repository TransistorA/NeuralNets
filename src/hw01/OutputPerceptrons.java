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
public class OutputPerceptrons {

    private int numIn;
    private ArrayList<Double> inputs = new ArrayList<>();
    private ArrayList<Double> outputs = new ArrayList<>();
    private ArrayList<Double> targetOutputs = new ArrayList<>();
    private int numGroup;
    private ArrayList<OutputPerceptron> plist = new ArrayList<>();
    private ArrayList<Double> weights;

    public OutputPerceptrons(ArrayList inputs, ArrayList targetOutputs,
                             int numIn) {
        this.numIn = numIn;
        this.inputs = inputs;
        this.targetOutputs = targetOutputs;
        buildLayer();
        generateOutputs();
    }

    public void buildLayer() {
        this.numGroup = this.inputs.size() / this.numIn;

        for (int i = 0; i < this.numGroup; i++) {
            ArrayList newInputs = new ArrayList(inputs.subList(i * numIn,
                                                               i * numIn + numIn));
            OutputPerceptron e = new OutputPerceptron(newInputs,
                                                      this.targetOutputs.get(i));
            this.plist.add(e);
            this.weights = e.getWeights();
        }
    }

    public void generateOutputs() {
        for (OutputPerceptron e : this.plist) {
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

    public ArrayList getWeights() {
        return this.weights;
    }

    public void update() {
        for (OutputPerceptron e : this.plist) {
            e.updateWeights();
        }
    }

}
