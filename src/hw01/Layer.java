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
public class Layer {

    private int numIn;
    private int numOut;
    //private int numHiddenLayer;
    //private int numNeoronHidden;
    private ArrayList<Double> inputs = new ArrayList<>();
    private ArrayList<Double> outputs = new ArrayList<>();
    //private int numGroup;

    public Layer(int numIn, int numOut, ArrayList inputs) {
        this.numIn = numIn;
        this.numOut = numOut;
        this.inputs = inputs;
        generateNextInput();
    }

    public void generateNextInput() {
        ArrayList<ArrayList<Double>> b = new ArrayList<>();
        for (int i = 0; i < numOut; i++) {
            Perceptrons a = new Perceptrons(this.inputs, this.numIn);
            b.add(a.getOutputs());
        }
        ArrayList<Double> nextInputs = new ArrayList<>();
        for (int i = 0; i < b.get(0).size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                nextInputs.add(b.get(j).get(i));
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

}
