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
public class OutputLayer {

    private int numIn;
    private int numOut;
    //private int numHiddenLayer;
    //private int numNeoronHidden;
    private ArrayList<Double> inputs = new ArrayList<>();
    private ArrayList<Double> outputs = new ArrayList<>();
    private ArrayList<Double> targetOutputs = new ArrayList<>();
    private ArrayList<ArrayList<Double>> arrayListofOutputs = new ArrayList<>();
    private ArrayList<OutputPerceptrons> arrayPerceptrons = new ArrayList<>();
    private ArrayList<Double> sumweights;

    //private int numGroup;
    public OutputLayer(int numIn, int numOut, ArrayList inputs,
                       ArrayList targetOutputs) {
        this.numIn = numIn;
        this.numOut = numOut;
        this.inputs = inputs;
        this.targetOutputs = targetOutputs;
        generateNextInput();
    }

    public void generateNextInput() {
        //ArrayList<ArrayList<Double>> b = new ArrayList<>();
        for (int i = 0; i < numOut; i++) {
            OutputPerceptrons a = new OutputPerceptrons(this.inputs,
                                                        this.targetOutputs,
                                                        this.numIn);
            sumweights = a.getWeights();
            this.arrayPerceptrons.add(a);
            this.arrayListofOutputs.add(a.getOutputs());
        }
        ArrayList<Double> nextInputs = new ArrayList<>();
        for (int i = 0; i < arrayListofOutputs.get(0).size(); i++) {
            for (int j = 0; j < arrayListofOutputs.size(); j++) {
                nextInputs.add(arrayListofOutputs.get(j).get(i));
            }
        }
        this.outputs = nextInputs;
    }

    public void print(ArrayList a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public ArrayList getOutputs() {
        return this.outputs;
    }

    public void update() {
        for (OutputPerceptrons e : this.arrayPerceptrons) {
            e.update();
        }
    }

    public ArrayList getSumWeights() {
        return this.sumweights;
    }

}
