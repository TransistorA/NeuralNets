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
import java.util.Random;

/**
 *
 * @author am049
 */
public class ANN_simple {

    private ArrayList<Integer> inputs = new ArrayList<>();
    private ArrayList<Integer> outputs = new ArrayList<>();
    private ArrayList<Double> weights = new ArrayList<>();
    private double bias;
    private boolean correctWeights = true;
    private int numIn;
    private int numOut;
    //private String fileName;

    /**
     * This is the maximum error allowed by the perceptron learning rule When
     * it's zero, the program can sometimes take a while to converge (due to the
     * way floats are represented) so this uses a very small number to
     * effectively do the same.
     */
    private static double EPSILON = .5E-4;

    /*public NeuralNet_new(String fileName) throws FileNotFoundException, IOException {
        //this.numIn = 2;
        //this.numOut = 1;
        //this.inputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1,
        //                                          0, 1, 1));
        //this.outputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1));

        this.fileName = fileName;
        String newLine;
        File file = new File(this.fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((newLine = br.readLine()) != null) {

            String[] numbers = newLine.split(",");
            // Get the target outputs (the outputs that we are
            // actually hoping that the neural network will give us
            outputs.add(Integer.parseInt(numbers[numbers.length - 1]));
            for (int i = 0; i < numbers.length - 1; i++) {
                inputs.add(Integer.parseInt(numbers[i]));
            }
        }
        this.numOut = 1;
        this.numIn = this.inputs.size() / this.outputs.size();

        Random r = new Random();
        for (int i = 0; i < numIn; i++) {
            this.weights.add(r.nextDouble() - 0.5);
        }
        this.bias = r.nextDouble() - 0.5;

    }*/
    public ANN_simple(ArrayList inputs, ArrayList outputs) {
        //this.numIn = 2;
        //this.numOut = 1;
        //this.inputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1,
        //                                          0, 1, 1));
        //this.outputs = new ArrayList<>(Arrays.asList(0, 0, 0, 1));

        this.inputs = inputs;
        this.outputs = outputs;
        this.numOut = 1;
        this.numIn = this.inputs.size() / this.outputs.size();

        Random r = new Random();
        for (int i = 0; i < numIn; i++) {
            this.weights.add(r.nextDouble() - 0.5);
        }
        this.bias = r.nextDouble() - 0.5;

    }

    public ANN_simple(ArrayList inputs, ArrayList weights, double bias) {
        this.inputs = inputs;
        this.weights = weights;
        this.numIn = this.weights.size();
        this.bias = bias;
        this.outputs = new ArrayList<>();
    }

    public void generateOutputs() {
        for (int i = 0; i < inputs.size() / numIn; i++) {

            ArrayList newInputs = new ArrayList(inputs.subList(i * numIn,
                                                               i * numIn + numIn));
            outputs.add(calculateOutput(newInputs));
        }
    }

    public void generateCorrectWeights() {

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

    public int calculateOutput(ArrayList<Integer> inputs) {
        if (inputs.size() != this.weights.size()) {
            throw new IllegalArgumentException("size error again");
        }
        double sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i) * this.weights.get(i);
        }
        sum += this.bias;
        return step(sum);
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

    public ArrayList getWeights() {
        return this.weights;
    }

    public double getBias() {
        return this.bias;
    }

    public ArrayList getOutputs() {
        return this.outputs;
    }

}
