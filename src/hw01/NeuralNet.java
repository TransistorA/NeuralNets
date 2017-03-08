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
     * This is our learning constant, given to be .3
     */
    public float ALPHA = .3f;

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
    public NeuralNet(int numInputs, int numHidden, int numHiddenPercep,
                     int numOutputs) {
        //Add two new Layers to the layer list

        // INPUT LAYER
        LayerList.add(new Layer(0, this));

        // MIDDLE LAYERS
        for (int i = 0; i < numHidden; i++) {
            LayerList.add(new Layer(i + 1, this));
        }

        // OUTPUT LAYER
        LayerList.add(new Layer(numHidden + 1, this));

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
        ArrayList<ArrayList<Integer>> arglist = readFile(fileName);

        ArrayList<Integer> inputs = arglist.get(0);
        ArrayList<Integer> targetOutput = arglist.get(1);

        for (int i = 0; i < inputs.size()) {
            for (int j = 0; ) {
                inputpairlist.
            }
        }

        float sserror = 0.0f;

        do {
            for (ArrayList<Integer> inpts : inputpairlist) {
                sserror = 0.0f; //Init sserror

                // Feed in our training values (into the input perceptrons)
                Layer inputlayer = this.LayerList.get(0);
                ArrayList<Perceptron> inps = inputla                                        yer.getPerList();
                for (int i = 0; i < inps.size(); i++) {
                    int inputvalue = inputs.get(i);
                    inps.get(i).setValue(inputvalue);
                }

                // Calculate SSE for the last layer (the output layer)
                int outputpos = this.LayerList.size() - 1;
                Layer outputlayer = this.LayerList.get(outputpos);
                ArrayList<Perceptron> outps = outputlayer.getPerList();
                // Individual error = target val - actual val
                for (int i = 0; i < outps.size(); i++) {
                    float pErr = targetOutput.get(i) - outps.get(i).getValue();
                    sserror += Math.pow(pErr, 2) / 2;
                }

                float delta // Reinitialize all of the neurons in the net
                        // So that we aren't stuck with old data
                for (int i = 0; i < this.LayerList.size(); i++) {
                    Layer layer = this.LayerList.get(i);
                    for (int j = 0; j < layer.getPerList().size(); j++) {
                        layer.getPerList().get(j).clean();
                    }
                }

            }
        } while (sserror > EPSILON);
    }

    /**
     * Returns the ArrayList of classification values for the Neural Net
     *
     * @param inputvals
     * @return An arraylist of the return floats
     */
    public ArrayList<Float> classify(ArrayList<Integer> inputvals) {
        Layer input = this.LayerList.get(0);

        ArrayList<Float> outputvals = null;

        // Set up the input layers
        for (int i = 0; i < input.getPerList().size(); i++) {
            input.getPerList().get(i).setValue(inputvals.get(i));
        }

        // Set everything else to null so that the values are actually
        // What they're supposed to be
        for (int i = 1; i < this.LayerList.size(); i++) {
            for (int j = 0; i < this.LayerList.get(i).getPerList().size(); i++) {
                Perceptron p = this.LayerList.get(i).getPerList().get(j);
                p.clean();
            }
        }

        // Read from the output layer
        int lastElem = this.LayerList.size() - 1;
        int numPerceptrons = this.LayerList.get(lastElem).getPerList().size();

        for (int s = 0; s < numPerceptrons; s++) {
            float pval = this.LayerList.get(lastElem).getPerList().get(s).getValue();
            outputvals.add(pval);
        }

        return outputvals;
    }

    /**
     * This function is a basic step function representing the error for that
     * iteration of the process.
     *
     * Honestly this should probably be removed at some point
     *
     * @param sum
     * @return
     */
    private int errorFunc(float sum) {
        if (sum >= 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Gets the layer at (index) in the layer list
     *
     * @param index
     * @return A layer - the one that resides at the given index
     * @author Michael Matirko
     */
    public Layer getLayer(int index) {
        return this.LayerList.get(index);
    }

    public ArrayList<ArrayList<Integer>> readFile(String filename) throws FileNotFoundException, IOException {
        ArrayList<Integer> inputs = new ArrayList<>();
        ArrayList<Integer> targetOutput = new ArrayList<>();

        //ssError for each set of inputs
        ArrayList<Float> ssError = new ArrayList<>();

        BufferedReader br = null;
        String newLine = "";
        File file = new File(filename);
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

        ArrayList<ArrayList<Integer>> returnlist = new ArrayList<>();
        returnlist.add(inputs);
        returnlist.add(targetOutput);

        return returnlist;
    }
}
