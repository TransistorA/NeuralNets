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

import static hw01.NeuralNetClient.z;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michael Matirko and Annan Miao
 */
public class NeuralNet {

    public void update(String fileName) throws FileNotFoundException, IOException {
        ArrayList<Integer> inputs = new ArrayList<>();
        ArrayList<Integer> outputs = new ArrayList<>();
        ArrayList<Float> weights = new ArrayList<>();

        BufferedReader br = null;
        String newLine = "";
        br = new BufferedReader(new FileReader(fileName));

        while ((newLine = br.readLine()) != null) {

            String[] numbers = newLine.split(",");

            outputs.add(Integer.parseInt(numbers[numbers.length - 1]));
            for (int i = 0; i < numbers.length - 1; i++) {
                inputs.add(Integer.parseInt(numbers[i]));
            }
        }
        //System.out.println(inputs.size() / outputs.size() + 1);
        Random randnumObj = new Random();
        for (int i = 0; i < inputs.size() / outputs.size() + 1; i++) {
            weights.add(randnumObj.nextFloat() - (float) 0.5);
            //randnumObj.nextFloat() - (float) 0.5
        }

        float result = 0;
        float error = 0;
        for (int j = 0; j < outputs.size() - 1; j++) {
            int inputIndex = j * weights.size();
            do {
                for (int i = 0; i < weights.size() - 1; i++) {
                    if (error > 0) {
                        weights.set(i + 1,
                                    weights.get(i + 1) + (float) 0.3 * error);
                    }
                    if (error < 0) {
                        weights.set(i + 1,
                                    weights.get(i + 1) - (float) 0.3 * error);
                    }
                    result += inputs.get(inputIndex + i) * weights.get(
                            i + 1);
                }
                result += weights.get(0);
                error = outputs.get(j) - z(result);  // target - net
            } while (error != 0);

        }

        for (int i = 0; i < weights.size(); i++) {
            System.out.println(weights.get(i));
        }
    }

}
