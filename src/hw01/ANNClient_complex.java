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

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author am049
 */
public class ANNClient_complex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ANN_complex a = new ANN_complex(2, 4, 1, new ArrayList<>(Arrays.asList(
                                        0.0, 0.0, 0.0, 1.0, 1.0,
                                        0.0, 1.0, 1.0)), new ArrayList<>(
                                        Arrays.asList(0.0, 1.0,
                                                      1.0, 0.0)), 0.1);
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the min SSE for the sample input: ");
        double minsse = in.nextDouble();
        a.calculateSSE();
        double sse = a.getSSE();

        for (int count = 0; count < 100; count++) {
            a.updateWeights();
            sse = a.getSSE();
            if (sse <= minsse) {
                System.out.println("The output within minsse is: ");
                a.print(a.getOutput());
                exit(0);
            }
        }
        System.out.println(
                "Required minsse too low! Cannot get required output after 100 loops. Try again.");

    }

}
