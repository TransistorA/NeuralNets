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
import java.util.Random;

/**
 *
 * @author Michael Matirko and Annan Miao
 */
public class dontuseit {

    /**
     * This is the maximum error allowed by the perceptron learning rule When
     * it's zero, the program can sometimes take a while to converge (due to the
     * way floats are represented) so this uses a very small number to
     * effectively do the same.
     */
    public static double EPSILON = .5E-4;
    private String fileName;
    private ArrayList<Float> weights;
    private float bias;

    /**
     * This is the ArrayList of perceptrons used by the Neural Net. They are all
     * individual entities and have their own weights, inputs, etc...
     */
    ArrayList<Perceptron> perList = new ArrayList<Perceptron>();

    public dontuseit(String fileName) {
        this.fileName = fileName;

        //Perceptron newPer = new Perceptron();
        //this.perList.add(newPer);
    }

    /**
     * Updates the perceptrons according to the CSV file that was input that
     *
     *
     * specifies the new rules to use. It compares the current values and their
     * errors to the actual values, and uses the perceptron learning rule in
     * order to change the output values of the perceptron.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void update(int numInput) throws FileNotFoundException, IOException {
        ArrayList<Integer> inputs = new ArrayList<>();
        int output;
        ArrayList weights = new ArrayList<>();

        Random randnumObj = new Random();

        for (int i = 0; i < numInput; i++) {
            // Set the weight to a random float between -.5 and .5
            weights.add(randnumObj.nextFloat() - (float) 0.5);
        }
        this.bias = randnumObj.nextFloat();

        //ArrayList<Float> weights = new ArrayList<>();
        BufferedReader br = null;
        String newLine = "";
        File file = new File(this.fileName);
        br = new BufferedReader(new FileReader(file));

        while ((newLine = br.readLine()) != null) {

            String[] numbers = newLine.split(",");

            output = (Integer.parseInt(numbers[numbers.length - 1]));
            for (int i = 0; i < numbers.length - 1; i++) {
                inputs.add(Integer.parseInt(numbers[i]));
            }

            Perceptron p = new Perceptron(inputs, output, weights);
            int error = p.Error();
            while (error != 0) {
                if (error > 0) {
                    for (int i = 0; i < weights.size(); i++) {
                        weights.set(i,
                                    (float) weights.get(i) - (float) 0.3 * inputs.get(
                                    i) * error);
                    }
                    this.bias += 0.3;
                    p = new Perceptron(inputs, output, weights);
                    error = p.Error();
                }
                if (error < 0) {
                    for (int i = 0; i < weights.size(); i++) {
                        weights.set(i,
                                    (float) weights.get(i) - (float) 0.3 * inputs.get(
                                    i) * error);
                    }
                    this.bias -= 0.3;
                    p = new Perceptron(inputs, output, weights);
                    error = p.Error();
                }
            }

        }

        for (int i = 0; i < weights.size(); i++) {
            System.out.println(weights.get(i));
        }

        /*Random randnumObj = new Random();
        for (int i = 0; i < inputs.size() / outputs.size() + 1; i++) {
            weights.add(randnumObj.nextFloat() - (float) 0.5);
        }

        float result = 0;
        float error = 0;
        for (int j = 0; j < outputs.size(); j++) {
            int inputIndex = j * (weights.size() - 1);
            do {
                for (int i = 0; i < weights.size() - 1; i++) {
                    if (error > 0) {
                        weights.set(i + 1,
                                    weights.get(i + 1) + (float) 0.3 * inputs.get(
                                    inputIndex + i) * error);
                    }
                    if (error < 0) {
                        weights.set(i + 1,
                                    weights.get(i + 1) + (float) 0.3 * inputs.get(
                                    inputIndex + i) * error);
                    }
                    result += inputs.get(inputIndex + i) * weights.get(
                            i + 1);
                }
                result += weights.get(0);
                error = outputs.get(j) - errorFunc(result);  // target - net
            } while (error != 0);
            //while (Math.abs(error) > EPSILON);

        }

        for (int i = 0; i < weights.size(); i++) {
            System.out.println(weights.get(i));
        }*/
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
