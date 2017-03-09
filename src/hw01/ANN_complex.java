/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 8, 2017
* Time: 6:03:17 AM
*
* Project: csci205_hw
* Package: hw01
* File: AMM_complex
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
public class ANN_complex {

    private int numIn;
    private int numOut;
    private int numHiddenLayer;
    private int numNeoronHidden;
    private ArrayList<Double> inputs = new ArrayList<>();
    private ArrayList<Double> outputs = new ArrayList<>();
    private ArrayList<Double> targetOutputs = new ArrayList<>();
    private double sse;
    private double minsse;
    private NeuronLayer a;
    private OutputLayer b;

    private int numGroup;

    public ANN_complex(int numIn, int numNeoronHidden, int numOut,
                       ArrayList inputs, ArrayList targetOutputs, double minsse) {
        this.numIn = numIn;
        this.numNeoronHidden = numNeoronHidden;
        this.numOut = numOut;
        this.inputs = inputs;
        this.targetOutputs = targetOutputs;
        this.minsse = minsse;
        createLayers();
    }

    public void createLayers() {
        ArrayList<Double> emptyWeights = new ArrayList<>();
        for (int i = 0; i < this.numNeoronHidden; i++) {
            emptyWeights.add(0.0);
        }
        a = new NeuronLayer(this.numIn, this.numNeoronHidden, this.inputs,
                            emptyWeights);

        b = new OutputLayer(this.numNeoronHidden, this.numOut,
                            a.getOutputs(), this.targetOutputs);
        a.setSumWeight(b.getSumWeights());
        this.outputs = b.getOutputs();
    }

    public ArrayList getOutput() {
        return this.outputs;
    }

    public void print(ArrayList a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public void calculateSSE() {
        double sse = 0;
        if (this.outputs.size() != this.targetOutputs.size()) {
            throw new IllegalArgumentException("size error");
        }
        for (int i = 0; i < this.outputs.size(); i++) {
            sse += Math.pow(this.targetOutputs.get(i) - this.outputs.get(i), 2) / 2 / numIn;
        }
        this.sse = sse;
    }

    public double getSSE() {
        return this.sse;
    }

    public void updateWeights() {
        b.update();
        a.update(b.getSumWeights());
    }

}
