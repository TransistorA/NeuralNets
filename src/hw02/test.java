/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw02;

import java.util.Arrays;

/**
 *
 * @author user
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[] a = {0, 0};
        double[] b = {0};
        double[] c = {1, 0};
        double[] d = {1};
        NeuralNet n = new NeuralNet(c, d, 3);

        int count = 0;
        while (count < 1000 && n.getSSE(c, d) > 0.01) {
            n.update(c, d);
            count += 1;
        }
        System.out.println(count);
        System.out.println(Arrays.toString(n.getOutput(c, d)));

        /*int count = 0;
        while (count < 10000) {
            while (n.getSSE(c, d) > 0.25) {
                n.update(c, d);
            }
            count += 1;

            if (n.getSSE(c, d) < 0.25 && n.getSSE(a, b) < 0.25) {
                System.out.println("haha1");
                System.out.println(n.getSSE(c, d) + "  " + n.getSSE(a, b));
                break;
            }

            while (n.getSSE(a, b) > 0.25) {
                n.update(a, b);
            }
            count += 1;

            if (n.getSSE(c, d) < 0.25 && n.getSSE(a, b) < 0.25) {
                System.out.println("haha1");
                System.out.println(n.getSSE(c, d) + "  " + n.getSSE(a, b));
                break;
            }
        }
        if (count >= 1000) {
            System.out.println("xixi");
        }

        System.out.println(Arrays.toString(n.getOutput(c, d)));
        System.out.println(n.getSSE(c, d));*/
    }

}
