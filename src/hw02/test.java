/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author user
 */
public class test {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        double[] a = {0, 0};
        double[] b = {1};
        double[] c = {0, 1};
        double[] d = {0};
        double[] a1 = {1, 0};
        double[] b1 = {0};
        double[] c1 = {1, 1};
        double[] d1 = {1};

        NeuralNet n = new NeuralNet(c, d, 3);

        ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(
                "ANN.ser"));

        file.writeObject(n);
        file.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "ANN.ser"));

        NeuralNet net = (NeuralNet) ois.readObject();

        net.update(a, b);
        /*int count = 0;
        boolean perfect = n.getSSE(c, d) < 0.02 && n.getSSE(a, b) < 0.02;
        while (count < 100000) {
            while (n.getSSE(c, d) > 0.02) {
                if (perfect) {
                    System.out.println("perfect");
                    break;
                }
                n.update(c, d);
                count += 1;
            }
            while (n.getSSE(a, b) > 0.02) {
                if (perfect) {
                    System.out.println("perfect");
                    break;
                }
                n.update(a, b);
                count += 1;
            }

        }
        System.out.println(count);
        System.out.println(Arrays.toString(n.getOutput(c, d)));
        System.out.println(n.getSSE(c, d));*/
    }

}
