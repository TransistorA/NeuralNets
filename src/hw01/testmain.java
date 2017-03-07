/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 6, 2017
* Time: 2:28:47 PM
*
* Project: csci205_hw
* Package: hw01
* File: testmain
* Description:
*
* ****************************************
 */
package hw01;

import java.io.IOException;

/**
 *
 * @author am049
 */
public class testmain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        NeuralNet a = new NeuralNet("or.csv");
        a.run();
    }

}
