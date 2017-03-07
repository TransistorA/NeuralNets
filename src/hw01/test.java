/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 6, 2017
* Time: 11:10:19 AM
*
* Project: csci205_hw
* Package: hw01
* File: test
* Description:
*
* ****************************************
 */
package hw01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author am049
 */
public class test {

    private ArrayList<Integer> inputs;
    private ArrayList<Integer> outputs;
    private ArrayList<Double> weights;
    private double bias;
    private boolean correctWeights = true;
    private int numIn;
    private int numOut;

    public test() {
        this.numIn = 2;
        this.numOut = 1;
        this.inputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1,
                                                    0, 1, 1));
        this.outputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1));
        this.weights = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < numIn; i++) {
            this.weights.add(r.nextDouble() - 0.5);
        }
        this.bias = r.nextDouble() - 0.5;
    }

    public void run() {

        if (inputs.size() / numIn != outputs.size() / numOut) {
            throw new IllegalArgumentException("Size Error.");
        }

        this.correctWeights = correctWeights();
        while (this.correctWeights == false) {
            updateWeights();
            this.correctWeights = correctWeights();
        }

        System.out.println("The bias is \n" + this.bias + "\n");
        System.out.println("The weights are: ");
        print(weights);

    }

    public void print(ArrayList a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public int calculateError(ArrayList<Integer> inputs, int output) {

        if (inputs.size() != weights.size()) {
            throw new IllegalArgumentException("haha size error");
        }

        double netSum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            netSum += inputs.get(i) * this.weights.get(i);
        }

        netSum += this.bias;
        int error = output - step(netSum);

        return error;
    }

    public int step(double num) {
        if (num >= 0) {
            return 1;
        }
        return 0;
    }

    public boolean correctWeights() {
        int error = 0;
        for (int i = 0; i < inputs.size() / numIn; i++) {

            ArrayList newInputs = new ArrayList(inputs.subList(i * numIn,
                                                               i * numIn + numIn));

            error = calculateError(newInputs, outputs.get(i));
            if (error != 0) {
                return false;
            }
        }
        return true;
    }

    public void updateWeights() {
        int error = 0;
        for (int i = 0; i < inputs.size() / numIn; i++) {

            ArrayList newInputs = new ArrayList(inputs.subList(i * numIn,
                                                               i * numIn + numIn));

            error = calculateError(newInputs, outputs.get(i));
            for (int j = 0; j < weights.size(); j++) {
                this.weights.set(j,
                                 weights.get(j) + 0.3 * error * (int) newInputs.get(
                                 j));
                this.bias += 0.3 * error;
            }
        }

    }

}
