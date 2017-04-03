/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw02;

import java.util.Random;

/**
 *
 * @author user
 */
public class Perceptron implements java.io.Serializable {

    private int numInput;
    private double[] weights, inputs, weightsdelta;
    private double output;
    private double learningFactor = 0.2;
    private double momentum = 0.5;
    private double bias;
    private double delta;
    private double biasdelta;

    public Perceptron(double[] inputs) {
        this.numInput = inputs.length;
        this.weights = new double[numInput];
        this.weightsdelta = new double[numInput];
        this.inputs = inputs;

        Random r = new Random();
        for (int i = 0; i < numInput; i++) {
            // Set the weight to a random float between -2.4/m and 2.4/m
            this.weights[i] = -2.4 / numInput + 4.8 * r.nextDouble() / numInput;
        }
        this.bias = -2.4 / numInput + 4.8 * r.nextDouble() / numInput;

        for (int i = 0; i < numInput; i++) {
            this.weightsdelta[i] = 0;
        }

        generateOutput();
    }

    public Perceptron(double[] inputs, double[] weights, double bias) {
        this.numInput = inputs.length;
        this.weights = new double[numInput];
        this.inputs = inputs;
        this.weights = weights;
        this.weightsdelta = new double[numInput];
        this.bias = bias;
        for (int i = 0; i < numInput; i++) {
            this.weightsdelta[i] = 0;
        }

        generateOutput();
    }

    public void generateOutput() {
        double sum = 0;
        for (int i = 0; i < this.numInput; i++) {
            sum += this.inputs[i] * this.weights[i];
        }
        sum += this.bias;
        this.output = activate(sum);
    }

    /**
     *
     * @param error Error is desired output - output for output layer, sum
     * weight * output delta for hidden layer, and none for input layer
     */
    /*public void getDelta(double error) {
        this.delta = this.output * (1 - this.output) * error;
    }*/
    private double activate(double num) {
        return 1.0 / (1.0 + Math.exp(-num));
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getOutput() {
        return this.output;
    }

    public void update(double error) {
        this.delta = this.output * (1 - this.output) * error;
        for (int i = 0; i < this.numInput; i++) {
            this.weights[i] += this.momentum * this.weightsdelta[i] + this.learningFactor * this.inputs[i] * this.delta;
            this.weightsdelta[i] = this.learningFactor * this.inputs[i] * this.delta;
        }
        this.bias += this.momentum * biasdelta + this.learningFactor * this.delta;
        this.biasdelta = this.learningFactor * this.delta;
        generateOutput();
    }

    public double[] getWeights() {
        return this.weights;
    }

    public double getDelta() {
        return this.delta;
    }

    public double getBias() {
        return this.bias;
    }

}
