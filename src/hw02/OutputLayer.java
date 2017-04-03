/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw02;

/**
 *
 * @author user
 */
public class OutputLayer extends HiddenLayer {

    private double[] desiredOutputs;
    private double sse;

    public OutputLayer(int numPerceptron, double[] inputs,
                       double[] desiredOutputs) {
        super(numPerceptron, inputs);
        this.desiredOutputs = desiredOutputs;
        calculateError();
    }

    public OutputLayer(int numPerceptron, double[] inputs, double[][] weights,
                       double[] bias, double[] desiredOutputs) {
        super(numPerceptron, inputs, weights, bias);
        this.desiredOutputs = desiredOutputs;
        calculateError();
    }

    public void calculateError() {
        if (this.numPerceptron != this.desiredOutputs.length) {
            throw new IllegalArgumentException("size error.");
        }
        this.sse = 0;
        for (int i = 0; i < this.numPerceptron; i++) {
            this.sse += Math.pow(this.desiredOutputs[i] - this.outputs[i], 2);
        }
    }

    public double getSSE() {
        return this.sse;
    }

    public void update() {

        for (int i = 0; i < this.numPerceptron; i++) {
            Perceptron a = this.perlist[i];
            a.update(this.desiredOutputs[i] - this.outputs[i]);
            this.perlist[i] = a;
            this.bias[i] = a.getBias();
            this.weights[i] = a.getWeights();
        }
        generateOutputs();
        calculateError();

    }

}
