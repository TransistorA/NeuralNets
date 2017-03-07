/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 4, 2017
* Time: 1:07:11 PM
*
* Project: csci205_hw
* Package: hw01
* File: NeuralNetClient
* Description:
* This file is the primary interface that a user will
* use in order to interact with the Neural Net. (ANN).
* ****************************************
 */
package hw01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Annan Miao and Michael Matirko
 */
public class NeuralNetClient_new {

    private static ArrayList<Double> weights = null;
    private static double bias;
    private static boolean endloop = false;

    /**
     * This method is the main method for the Neural Net Client. It calls all of
     * the required methods in order to allow the user to use the ANN in the
     * ways specified in the HW document. It also asks the user if they want to
     * create a new ANN, or read in a config file, and then calls the required
     * methods from there.
     *
     * @param args the command line arguments
     * @author Michael Matirko
     */
    public static void main(String[] args) throws IOException {

        System.out.println("\n");
        System.out.println(
                "*************************************************");
        System.out.println(
                "        Welcome to the Neural Net Client         ");
        System.out.println(
                " An ANN implementation by A. Miao and M. Matirko ");
        System.out.println(
                "*************************************************");
        System.out.println(
                "     Select one of the following options:   \n   ");
        System.out.println(
                " 1) Create a new ANN                             ");
        System.out.println(
                " 2) Read from an existing ANN config             ");
        System.out.println(
                " 3) End the program             ");

        while (endloop == false) {
            // Get a selection from the user
            System.out.print("\n Selection: ");
            Scanner inputsc = new Scanner(System.in);
            int selection = inputsc.nextInt();

            // Call the correct method depending on the user's choice
            if (selection == 1) {
                createNewANN();
            }
            else if (selection == 2) {
                loadExistingConfig();
            }

            else if (selection == 3) {
                System.out.println("Goodbye!");
                endloop = true;
            }
        }

    }

    /**
     * This method is called when the user selects the option matching "create a
     * new ANN." It creates a new Neural Net that can be fed input by the user.
     * It takes input from the user corresponding to the number of inputs that
     * the neural net should take in, and the number of outputs that should be
     * in the network.
     *
     * @author Annan Miao
     */
    public static void createNewANN() throws FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Create ANN\nEnter the file name: ");
        String fileName = in.next();
        System.out.print(
                "Do you want to save the correct weights into a file? (y/n) ");
        String yn = in.next();

        NeuralNet_new a = new NeuralNet_new(fileName);
        a.generateCorrectWeights();
        weights = a.getWeights();
        bias = a.getBias();

        if (yn.equals("n")) {
            System.out.println("OK don't create file.");
        }
        else if (yn.equals("y")) {
            System.out.print("Enter the save file name: ");
            String saveFile = in.next();
            PrintWriter out = new PrintWriter(saveFile, "UTF-8");
            out.print(a.getBias());
            out.print(",");
            for (int i = 0; i < a.getWeights().size(); i++) {
                out.print(a.getWeights().get(i));
                out.print(",");
            }
            out.close();
            System.out.println("File created!");
        }

    }

    /**
     * This method prompts the user to input the location of an existing ANN
     * config file (in the form of a CSV file).
     *
     * @author
     */
    private static void loadExistingConfig() throws FileNotFoundException, IOException {
        if (weights == null) {
            System.out.println(
                    "Train a data file first to get the weights for prediction.");
        }
        else {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter the file name that contains input values: ");
            String fileName = in.next();
            System.out.print("Enter the file that you want to save outputs in: ");
            String fileName2 = in.next();

            String newLine;
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            ArrayList<Integer> inputs = new ArrayList<>();

            while ((newLine = br.readLine()) != null) {

                String[] numbers = newLine.split(",");
                for (int i = 0; i < numbers.length; i++) {
                    inputs.add(Integer.parseInt(numbers[i]));
                }
            }

            NeuralNet_new a = new NeuralNet_new(inputs, weights, bias);
            a.generateOutputs();
            System.out.println("The outputs are: ");
            a.print(a.getOutputs());

            PrintWriter out = new PrintWriter(fileName2, "UTF-8");
            for (int i = 0; i < a.getOutputs().size(); i++) {
                out.println(a.getOutputs().get(i));
            }
            out.close();
        }
    }
}
