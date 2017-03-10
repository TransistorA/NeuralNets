/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 4, 2017
* Time: 1:07:11 PM
*
* Project: csci205_hw
* Package: hw02
* File: NeuralNetClient
* Description:
* This file is the primary interface that a user will
* use in order to interact with the Neural Net. (ANN).
* ****************************************
 */
package hw02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Annan Miao and Michael Matirko
 */
public class NeuralNetClient {

    static NeuralNet net;

    static int numInputs;

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
        System.out.println("*************************************************");
        System.out.println("        Welcome to the Neural Net Client         ");
        System.out.println(" An ANN implementation by A. Miao and M. Matirko ");
        System.out.println("*************************************************");
        System.out.println("     Select one of the following options:   \n   ");
        System.out.println(" 1) Create a new ANN                             ");
        System.out.println(" 2) Read from an existing ANN config             ");
        System.out.print("\n Selection: ");

        // Get a selection from the user
        int selection = getUserSelection();

        // Call the correct method depending on the user's choice
        if (selection == 1) {
            createNewANN();
        }
        else if (selection == 2) {
            loadConfiguration();
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

        System.out.println("******************Create ANN******************");

        System.out.print("How many inputs should we have? ");
        NeuralNetClient.numInputs = in.nextInt();

        System.out.print("\nHow many hidden layers should we have? ");
        int numHidden = in.nextInt();

        System.out.print(
                "\nHow many perceptrons should we have per hidden layer? ");
        int numHiddenPer = in.nextInt();

        System.out.print("\nHow many outputs should we have? ");
        int numOutputs = in.nextInt();

        net = new NeuralNet(numInputs, numHidden, numHiddenPer, numOutputs);

        System.out.println("     Select one of the following options:   \n   ");
        System.out.println(" 1) Training Mode for ANN \n                     ");
        System.out.println(" 2) Classification Mode for ANN \n               ");

        // Get a selection from the user
        int selection = getUserSelection();

        // Call the correct method depending on the user's choice
        if (selection == 1) {
            trainingMode();

        }
        else if (selection == 2) {
            classificationMode();
        }

    }

    private static void trainingMode() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a CSV file with training data: ");
        String filename = in.next();

        net.update(filename);

        System.out.println("**************Neural Net Trained****************");
        System.out.println("     Select one of the following options:   \n   ");
        System.out.println(" 1) Training Mode for ANN \n                     ");
        System.out.println(" 2) Classification Mode for ANN \n               ");
        System.out.println(" 3) Save Configuration for ANN \n                ");

        // Get a selection from the user
        int selection = getUserSelection();

        // Call the correct method depending on the user's choice
        if (selection == 1) {
            trainingMode();

        }
        else if (selection == 2) {
            classificationMode();
        }
        else if (selection == 3) {
            saveConfiguration();
        }

    }

    private static void classificationMode() throws FileNotFoundException, IOException {
        System.out.println(net);
        ArrayList<Integer> targetOutput = new ArrayList<>();

        BufferedReader br = null;
        File file = new File("data.csv");
        br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        ArrayList<ArrayList<Integer>> inputpairlist = new ArrayList<>();

        while (line != null) {
            ArrayList<Integer> inputs = new ArrayList<>();
            String[] numbers = line.split(",");
            // Iterate over the line
            for (int i = 0; i < numInputs; i++) {
                inputs.add(Integer.parseInt(numbers[i]));
            }
            line = br.readLine();
            inputpairlist.add(inputs);
        }

        System.out.println(inputpairlist);
        for (int i = 0; i < inputpairlist.size(); i++) {
            System.out.println("Trying input pair " + inputpairlist.get(i));
            System.out.println(net.classify(inputpairlist.get(i)));
        }

    }

    /**
     * Saves the configuration of the neural net to a file using serialization.
     *
     * We had to use TutorialsPoint in order to find code that would allow us to
     * easily serialize our neural net. The information used from the site is
     * the information needed to create the file output stream, and use
     * writeObject() in order to actually serialize the object.
     *
     * @throws java.io.IOException
     * @see
     * <a href = "https://www.tutorialspoint.com/java/java_serialization.htm">
     * https://www.tutorialspoint.com/java/java_serialization.htm <\a>
     *
     * @author Michael Matirko
     */
    public static void saveConfiguration() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter a name for the configuration file to save: ");
        String fname = in.next();

        FileOutputStream fos = new FileOutputStream(fname);

        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(net);
        out.close();
        fos.close();
        System.out.println(" Configuration for the net saved in " + fname + " !");
    }

    /**
     * Loads the configuration of the neural net from a file using
     * (de)serialization.
     *
     * We had to use TutorialsPoint in order to find code that would allow us to
     * easily serialize our neural net. The information used from the site is
     * the information needed to create the file output stream, and use
     * writeObject() in order to actually serialize the object.
     *
     * @throws java.io.IOException
     * @see
     * <a href = "https://www.tutorialspoint.com/java/java_serialization.htm">
     * https://www.tutorialspoint.com/java/java_serialization.htm <\a>
     *
     * @author Michael Matirko
     */
    public static void loadConfiguration() throws IOException {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print(
                    "\nEnter a name for the configuration file to load: ");
            String fname = in.next();

            FileInputStream fis = new FileInputStream(fname);

            ObjectInputStream objstream = new ObjectInputStream(fis);
            net = (NeuralNet) objstream.readObject();
            objstream.close();
            fis.close();
            System.out.println(
                    " Loaded configuration data from " + fname + " !");
        } catch (ClassNotFoundException ex) {
            System.out.println(
                    "Generated a ClassNotFound Exception. Maybe not a config file?");
        }
    }

    /**
     * We use the scanner for int inputs quite a bit, so here is a method that
     * creates a scanner and takes in an int (and returns that int.)
     *
     * @return An integer
     * @author Michael Matirko
     */
    public static int getUserSelection() {
        // Get a selection from the user
        Scanner inputsc = new Scanner(System.in);
        int selection = inputsc.nextInt();

        return selection;
    }

}
