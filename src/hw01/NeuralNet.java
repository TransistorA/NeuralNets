/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 5, 2017
* Time: 4:44:11 PM
*
* Project: csci205_hw
* Package: hw01
* File: NeuralNet
* Description:
* This is the core Neural Net package that represents a neural net
* for CSCI205.
* ****************************************
 */
package hw01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Michael Matirko and Annan Miao
 */
public class NeuralNet {

    /**
     * This is the maximum error allowed by the perceptron learning rule When
     * it's zero, the program can sometimes take a while to converge (due to the
     * way floats are represented) so this uses a very small number to
     * effectively do the same.
     */
    private static double EPSILON = .5E-4;

    /**
     * This is the ArrayList of Layers used by the Neural Net. They are all
     * individual entities and have their own weights, values, etc...
     */
    private ArrayList<Layer> LayerList = new ArrayList<>();

    /**
     * Construct the neural net - right now, it's a list of layers that all have
     * a certain number of outputs.
     *
     * @param numInputs
     * @param numOutputs
     */
    public NeuralNet(int numInputs, int numOutputs) {
        //Add two new Layers to the layer list

        //INPUT LAYER
        LayerList.add(new Layer());
        for (int i = 0; i < numInputs; i++) {
            LayerList.get(0).addPer(new Perceptron(numOutputs));
        }

        //OUTPUT LAYER
        LayerList.add(new Layer());

    }

    /**
     * Updates the perceptrons according to the CSV file that was input that
     *
     *
     * specifies the new rules to use. It compares the current values and their
     * errors to the actual values, and uses the perceptron learning rule in
     * order to change the output values of the perceptron.
     *
     * @param fileName - The name of the file containing the update data
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void update(String fileName) throws FileNotFoundException, IOException {
        ArrayList<Integer> inputs = new ArrayList<>();
        ArrayList<Integer> targetOutput = new ArrayList<>();

        //ssError for each set of inputs
        ArrayList<Float> ssError = new ArrayList<>();

        BufferedReader br = null;
        String newLine = "";
        File file = new File(fileName);
        br = new BufferedReader(new FileReader(file));

        while ((newLine = br.readLine()) != null) {

            String[] numbers = newLine.split(",");
            // Get the target outputs (the outputs that we are
            // actually hoping that the neural network will give us
            targetOutput.add(Integer.parseInt(numbers[numbers.length - 1]));
            for (int i = 0; i < numbers.length - 1; i++) {
                inputs.add(Integer.parseInt(numbers[i]));
            }
        }

        int sserror = 0;
        int inputSize = this.LayerList.get(0).getPerList().size();
        int numIter = (int) Math.pow(2, inputSize);

        do {
            for (int iter = 0; iter < numIter; iter++) {
                //Initialize the ArrayList of ssErrors to be zero for everything
                ssError.set(iter) = (float) 0.0;
            }
        } while (sserror > EPSILON);
    }

    /**
     * This function is a basic step function representing the error for that
     * iteration of the process.
     *
     * @param sum
     * @return
     */
    public static int errorFunc(float sum) {
        if (sum >= 0) {
            return 1;
        }
        return 0;
    }
}
