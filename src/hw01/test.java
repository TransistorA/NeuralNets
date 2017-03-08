/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Michael Matirko, Annan Miao
* Date: Mar 8, 2017
* Time: 3:46:21 AM
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

/**
 *
 * @author am049
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ANN_complex a = new ANN_complex(2, 2, 1, new ArrayList<>(Arrays.asList(
                                        0.0, 0.0, 0.0, 1.0, 1.0,
                                        0.0, 1.0, 1.0)), new ArrayList<>(
                                        Arrays.asList(0.0, 1.0,
                                                      1.0, 0.0)), 0.1);
        a.createInputLayer();
        //a.print(a.getOutput());
        a.calculateSSE();
        System.out.println(a.getSSE());
    }

}
