/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 5, 2017
* Time: 4:44:11 PM
*
* Project: csci205_hw
* Package: hw02
* File: NeuralNet
* Description:
* This is the core Neural Net package that represents a neural net
* for CSCI205.
* ****************************************
 */
package hw02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Michael Matirko and Annan Miao
 */
public class NeuralNet implements java.io.Serializable {

    /**
     * This is the maximum error allowed by the perceptron learning rule When
     * it's zero, the program can sometimes take a while to converge (due to the
     * way floats are represented) so this uses a very small number to
     * effectively do the same.
     */
    private static double EPSILON = .05;

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
     * This is the number of inputs to our neural net
     */
    private int numInputs;

    /**
     * This is the training data output to a string
     */
    private String trainingData;

    /**
     * The file name for the logfile
     */
    private final String LOGFILE_NAME = "ANNTrainingLog.csv";

    /**
     * Construct the neural net - right now, it's a list of layers that all have
     * a certain number of outputs.
     *
     * @param numInputs
     * @param numOutputs
     * @author Annan Miao
     */
    public NeuralNet(int numInputs, int numHidden, int numHiddenPercep,
                     int numOutputs) {

        this.numInputs = numInputs;

        // Create the logfile leader for this Net
        createLogHeader();

        // INPUT LAYER
        LayerList.add(new Layer(0, numInputs, this));

        // MIDDLE LAYERS
        for (int i = 0; i < numHidden; i++) {
            LayerList.add(new Layer(i + 1, numHiddenPercep, this));
        }

        // OUTPUT LAYER
        LayerList.add(new Layer(numHidden + 1, numOutputs, this));

    }

    /**
     * Updates the perceptrons according to the CSV file that was input that
     * specifies the new rules to use. It compares the current values and their
     * errors to the actual values, and uses the perceptron learning rule in
     * order to change the output values of the perceptron.
     *
     * @param fileName - The name of the file containing the update data
     * @throws FileNotFoundException
     * @throws IOException
     * @author Equal parts Michael Matirko and Annan Miao
     */
    public void update(String fileName) throws FileNotFoundException, IOException {
        ArrayList<ArrayList<Integer>> arglist = readFile(fileName);

        ArrayList<Integer> inputs = arglist.get(0);
        ArrayList<Integer> targetOutput = arglist.get(1);

        // This is an arraylist of all possible input pairs:
        // ex. if our input values from the truth table are
        // 00011011, this splits it up in to
        // Arraylist(AL(0,0), AL(0,1), AL(1,0), AL(1,1)).
        ArrayList<ArrayList<Integer>> inputpairlist = new ArrayList<>();
        String ttinputs = inputs.toString();
        ttinputs = ttinputs.replace("[", "");
        ttinputs = ttinputs.replace("]", "");
        ttinputs = ttinputs.replace(",", "");
        ttinputs = ttinputs.replace(" ", "");
        for (int i = 0; i < ttinputs.length(); i++) {
            if (i % numInputs == 0) {
                ArrayList<Integer> pair = new ArrayList<>();
                for (int j = 0; j < this.numInputs; j++) {
                    pair.add(j);
                }
                inputpairlist.add(pair);
            }
        }

        // Pick a big value to init sserror
        float sserror = 10000;
        System.out.println(inputpairlist);
        System.out.println(targetOutput);
        int epoch = 0;

        do {
            for (ArrayList<Integer> inpts : inputpairlist) {
                sserror = 0.0f; //Re-init sserror

                // Feed in our training values (into the input perceptrons)
                Layer inputlayer = this.LayerList.get(0);
                ArrayList<Perceptron> inps = inputlayer.getPerList();
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
                    // Z is our current output perceptron
                    Perceptron z = outps.get(i);
                    float pErr = targetOutput.get(i) - z.getValue();
                    sserror += Math.pow(pErr, 2) / 2;

                    //Calculate w_jk(i+1)
                    // I THINK THIS IS THE PROBLEM HERE
                    for (int x = 0; x < z.getWeightArr().size(); x++) {
                        // First, get the perceptron j that k is connected to
                        Perceptron j = z.getLayer().getPrevLayer().getPerList().get(
                                x);
                        float curweight = z.getWeightArr().get(x);
                        // Calculate sigma for the output perceptron
                        float deltaout = z.getValue() * (1 - z.getValue()) * pErr;
                        //Calculate w_jk for the next iteration
                        float w_jk = curweight + ALPHA * j.getValue() * deltaout;
                        //Set the new weight
                        //???????????????????????????????????
                        z.setWeight(x, w_jk);
                    }

                }

                //Iterate through the hidden (middle) layers (and the input layer)
                //
                int index_last_hidden = this.LayerList.size() - 2;
                for (int l = index_last_hidden; l >= 0; l--) {
                    // Iterate through the perceptrons in the middle layers
                    Layer hdnlayer = this.LayerList.get(l);
                    ArrayList<Perceptron> hdnpercep = hdnlayer.getPerList();

                    for (int perindex = 0; perindex < hdnpercep.size(); perindex++) {
                        Perceptron pc = hdnpercep.get(perindex);
                        Layer nextlayer = hdnlayer.getNextLayer();
                        // Calculate delta_j (little delta)
                        float sum = 0;
                        // Iterate through all the perceptron in the next layer
                        // In order to calculate the sum of previous errors/weights
                        for (int s = 0; s < nextlayer.getPerList().size(); s++) {
                            Perceptron nextp = nextlayer.getPerList().get(s);
                            // Get the weight of the next perceptron
                            Float weight = nextp.getWeightArr().get(perindex);
                            // Add this weight to the total sum
                            sum += weight * nextp.getError();

                            // Finally, add everything together to calculate del_j
                            // and set it
                            float del_j = pc.getValue() * (1 - pc.getValue()) * sum;
                            System.out.println("SET DEL_J EQUAL TO " + del_j);
                            pc.setError(del_j);
                        }

                    }

                }

                // Iterate through all of the non output layers
                for (int iter = 0; iter < this.LayerList.size() - 2; iter++) {
                    Layer curlayer = this.LayerList.get(iter);
                    ArrayList<Perceptron> curplist = curlayer.getPerList();
                    for (int p = 0; p < curplist.size(); p++) {
                        Perceptron per = curplist.get(p);
                        float del_w = ALPHA * per.getValue() * per.getError();
                        // HERE IS THE PROBLEM
                        System.out.println(
                                "DEL W IS " + del_w + " \n" + "PER.getError is " + per.getError() + " \nper.getval is " + per.getValue());
                        for (int w = 0; w < per.getWeightArr().size(); w++) {
                            //Set the weight to be delta w + current weight
                            float curweight = per.getWeightArr().get(w);
                            per.setWeight(w, del_w + curweight);
                        }
                    }
                }

                float delta = 0.0f; // Reinitialize all of the neurons in the net
                // So that we aren't stuck with old data
                for (int i = 0; i < this.LayerList.size(); i++) {
                    Layer layer = this.LayerList.get(i);
                    for (int j = 0; j < layer.getPerList().size(); j++) {
                        layer.getPerList().get(j).clean();
                    }
                }

            }

            //Increment epoch
            epoch += 1;

            // Update the log file for the current iteration
            this.trainingData += "------\n Epoch " + epoch + " \n";
            this.trainingData += this.toString();

        } while (sserror > EPSILON);

        //Store the results in the log file.
        LogEnd();
    }

    /**
     * Returns the ArrayList of classification values for the Neural Net. You
     * have to pass in an arraylist of n input values.
     *
     * @param inputvals
     * @return An arraylist of the return float(s)
     * @author Michael Matirko
     */
    public ArrayList<Float> classify(ArrayList<Integer> inputvals) {
        Layer input = this.LayerList.get(0);

        ArrayList<Float> outputvals = new ArrayList<>();

        // Set up the input layers
        for (int i = 0; i < input.getPerList().size(); i++) {
            input.getPerList().get(i).setValue(inputvals.get(i));
        }
        System.out.println(this);
        // Set everything else to null so that the values are actually
        // What they're supposed to be
        for (int i = 1; i < this.LayerList.size(); i++) {
            for (int j = 0; i < this.LayerList.get(i).getPerList().size(); i++) {
                Perceptron p = this.LayerList.get(i).getPerList().get(j);
                p.clean();
                System.out.println("Cleaned " + p);
            }
        }

        // Read from the output layer
        int lastElem = this.LayerList.size() - 1;
        int numPerceptrons = this.LayerList.get(lastElem).getPerList().size() - 1;
        System.out.println(
                "output elem is: " + lastElem + " and it has percep: " + this.LayerList.get(
                        lastElem).getPerList());

        for (int s = 0; s <= numPerceptrons; s++) {
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
     * @author Annan Miao
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

    /**
     * Returns numinputs
     *
     * @return The number of inputs, an integer
     * @author Netbeans generated, Michael Matirko
     */
    public int getNumInputs() {
        return this.numInputs;
    }

    public ArrayList<Layer> getLayerList() {
        return LayerList;
    }

    @Override
    public String toString() {
        String returnstr = "";
        returnstr += "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n";
        for (int i = 0; i < this.LayerList.size(); i++) {
            String perlist = this.getLayer(i).getPerList().toString();
            returnstr += perlist + '\n';
        }
        returnstr += "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n";
        return returnstr;
    }

    /**
     * Creates the header for the log file. Information regarding formatting
     * date objects used from TutorialsPoint
     *
     * @see <a href https://www.tutorialspoint.com/java/java_date_time.htm>
     * https://www.tutorialspoint.com/java/java_date_time.htm </a>
     *
     * @author Michael Matirko
     *
     */
    public void createLogHeader() {
        Date startdate = new Date();

        this.trainingData += "ANN - Neural Net, " + String.format("$T $D",
                                                                  startdate);
    }

    /**
     * Creates the end for the log file and writes it to a file. Information
     * regarding formatting date objects used from TutorialsPoint
     *
     * @see <a href https://www.tutorialspoint.com/java/java_date_time.htm>
     * https://www.tutorialspoint.com/java/java_date_time.htm </a>
     *
     * @author Michael Matirko
     *
     */
    public void LogEnd() throws IOException {
        Date enddate = new Date();
        this.trainingData += "--------------------\nTrainingEnded\n";
        this.trainingData += String.format("$T $D", enddate);

        //Write the log data to the output file
        BufferedWriter br = new BufferedWriter(new FileWriter(LOGFILE_NAME));
        br.write(this.trainingData);
        br.close();

    }

}
