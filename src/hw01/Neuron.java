/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 7, 2017
* Time: 8:24:42 PM
*
* Project: csci205_hw
* Package: hw01
* File: NeuralNet_complex
* Description:
*
* ****************************************
 */
package hw01;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author am049
 */
public class Neuron {

    private ArrayList<Double> inputs = new ArrayList<>();
    private double output;
    private ArrayList<Double> weights = new ArrayList<>();
    private double bias;
    private int numIn;
    private int numOut;
    private double delta;
    private static double factor = 0.2;
    private double sumweight; // for error calculation

    /**
     * This is the maximum error allowed by the perceptron learning rule When
     * it's zero, the program can sometimes take a while to converge (due to the
     * way floats are represented) so this uses a very small number to
     * effectively do the same. This one works for several inputs and one output
     * within one group
     *
     * @param inputs
     * @param delta
     */
    public Neuron(ArrayList inputs, double sumweight) {
        //this.inputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1,
        //                                          0, 1, 1));
        //this.outputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1));
        this.inputs = inputs;
        this.numOut = 1;
        this.numIn = this.inputs.size();
        this.sumweight = sumweight;

        Random r = new Random();
        for (int i = 0; i < numIn; i++) {
            this.weights.add(
                    -2.4 / this.numIn + r.nextDouble() * 4.8 / this.numIn);
        }
        this.bias = -2.4 / this.numIn + r.nextDouble() * 4.8 / this.numIn;
        generateOutput();
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getDelta() {
        return this.delta;
    }

    public void print(ArrayList a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public void generateOutput() {
        if (inputs.size() != this.weights.size()) {
            throw new IllegalArgumentException("size error again");
        }
        double sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i) * this.weights.get(i);
        }
        sum += this.bias;
        this.output = f(sum);

        this.delta = this.sumweight * this.output * (1 - this.output);

    }

    public double f(double net) {
        return 1 / (1 + Math.exp(-net));
    }

    public void updateWeights() {
        for (int i = 0; i < inputs.size(); i++) {
            this.weights.set(i,
                             weights.get(i) + factor * this.delta * inputs.get(
                             i));
            this.bias -= factor * this.delta;
        }
    }

    public ArrayList getWeights() {
        return this.weights;
    }

    public double getBias() {
        return this.bias;
    }

    public double getOutput() {
        return this.output;
    }

}
