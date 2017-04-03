/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw02;

import java.util.Arrays;

/**
 *
 * @author user
 */
public class HiddenLayer implements java.io.Serializable {

    Perceptron[] perlist;
    double[] inputs;
    double[] outputs;
    int numPerceptron;
    double[][] weights;
    double[] bias;

    public HiddenLayer(int numPerceptron, double[] inputs) {
        this.perlist = new Perceptron[numPerceptron];
        this.inputs = inputs;
        this.outputs = new double[numPerceptron];
        this.numPerceptron = numPerceptron;
        this.weights = new double[numPerceptron][];
        this.bias = new double[numPerceptron];

        for (int i = 0; i < this.numPerceptron; i++) {
            Perceptron a = new Perceptron(inputs);
            perlist[i] = a;
        }

        for (int i = 0; i < this.numPerceptron; i++) {
            this.weights[i] = this.perlist[i].getWeights();
            this.bias[i] = this.perlist[i].getBias();
        }

        generateOutputs();
    }

    public HiddenLayer(int numPerceptron, double[] inputs, double[][] weights,
                       double[] bias) {
        this.perlist = new Perceptron[numPerceptron];
        this.inputs = inputs;
        this.outputs = new double[numPerceptron];
        this.numPerceptron = numPerceptron;
        this.weights = weights;
        this.bias = bias;

        for (int i = 0; i < this.numPerceptron; i++) {
            Perceptron a = new Perceptron(inputs, weights[i], bias[i]);
            perlist[i] = a;
        }
        generateOutputs();
    }

    public void generateOutputs() {
        for (int i = 0; i < this.numPerceptron; i++) {
            outputs[i] = this.perlist[i].getOutput();
        }
    }

    public double[] getOutputs() {
        return this.outputs;
    }

    public void update(Perceptron[] perlist) {

        System.out.println("hidden before weights" + Arrays.toString(
                this.getWeights()[0]));
        System.out.println(
                "hidden before bias" + Arrays.toString(this.getBias()));

        double error = 0;
        for (int a = 0; a < this.numPerceptron; a++) {
            for (int b = 0; b < perlist.length; b++) {
                error += perlist[b].getWeights()[a] * perlist[b].getDelta();
            }
        }
        for (int i = 0; i < this.numPerceptron; i++) {
            Perceptron a = this.perlist[i];
            a.update(error);
            this.perlist[i] = a;
            this.bias[i] = a.getBias();
            this.weights[i] = a.getWeights();
        }
        generateOutputs();

        System.out.println("hidden after weights" + Arrays.toString(
                this.getWeights()[0]));
        System.out.println(
                "hidden after bias" + Arrays.toString(this.getBias()));

    }

    public Perceptron[] getPerlist() {
        return this.perlist;
    }

    public double[][] getWeights() {
        return this.weights;
    }

    public double[] getBias() {
        return this.bias;
    }
}
