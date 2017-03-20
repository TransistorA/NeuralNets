/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw02;

/**
 *
 * @author user
 */
public class InputLayer extends HiddenLayer {

    public InputLayer(int numPerceptron, double[] inputs) {
        super(numPerceptron, inputs);
        setOutputs();
    }

    public void setOutputs() {
        if (this.numPerceptron != this.inputs.length) {
            throw new IllegalArgumentException(
                    "number of inputs should equal number of perceptrons in input layer.");
        }
        for (int i = 0; i < this.numPerceptron; i++) {
            this.perlist[i].setOutput(this.inputs[i]);
        }
    }

    public void update() {
        throw new IllegalArgumentException("Input layer cannot update weights.");

    }
}
