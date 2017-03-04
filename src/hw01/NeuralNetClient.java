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

import java.util.Scanner;

/**
 *
 * @author Annan Miao and Michael Matirko
 */
public class NeuralNetClient {

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
    public static void main(String[] args) {
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
        Scanner inputsc = new Scanner(System.in);
        int selection = inputsc.nextInt();

        // Call the correct method depending on the user's choice
        if (selection == 1) {
            createNewANN();
        }
        else if (selection == 2) {
            loadExistingConfig();
        }

    }

    /**
     * This method is called when the user selects the option matching "create a
     * new ANN." It creates a new Neural Net that can be fed input by the user.
     * It takes input from the user corresponding to the number of inputs that
     * the neural net should take in, and the number of outputs that should be
     * in the network.
     *
     * @author Michael Matirko
     */
    public static void createNewANN() {
        System.out.println("Create ANN");
    }

    /**
     * This method prompts the user to input the location of an existing ANN
     * config file (in the form of a CSV file).
     *
     * @author
     */
    private static void loadExistingConfig() {

    }

}
