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
public class NeuralNet {

    private double[] inputs;
    private double[] desiredOutputs;
    private int numInputs;
    private int numOutputs;
    private int numPerHidden;
    private double[] outputs;
    private double sse;
    private double maxsse = 0.01;
    private HiddenLayer hidden;
    private InputLayer in;
    private OutputLayer out;

    public NeuralNet(double[] inputs, double[] desiredOutputs, int numPerHidden) {
        this.inputs = inputs;
        this.desiredOutputs = desiredOutputs;
        this.numInputs = inputs.length;
        this.numOutputs = desiredOutputs.length;
        this.numPerHidden = numPerHidden;

        InputLayer in = new InputLayer(this.numInputs, this.inputs);
        HiddenLayer hidden = new HiddenLayer(this.numPerHidden, in.getOutputs());
        OutputLayer out = new OutputLayer(this.numOutputs, hidden.getOutputs(),
                                          this.desiredOutputs);
        this.in = in;
        this.hidden = hidden;
        this.out = out;

        generateOutputs();
    }

    public void update(double[] inputs, double[] desiredOutputs) {
        this.inputs = inputs;
        this.desiredOutputs = desiredOutputs;
        this.in = new InputLayer(this.numInputs, this.inputs);

        this.out.update();
        this.hidden.update(this.out.getPerlist());
        this.out = new OutputLayer(this.numOutputs, this.hidden.getOutputs(),
                                   this.out.getWeights(),
                                   this.out.getBias(), this.desiredOutputs);
        this.hidden = new HiddenLayer(this.numPerHidden, in.getOutputs(),
                                      this.hidden.getWeights(),
                                      this.hidden.getBias());
        this.sse = this.out.getSSE();

        generateOutputs();
    }

    public void generateOutputs() {
        this.outputs = this.out.getOutputs();
    }

    public double getSSE(double[] inputs, double[] desiredOutputs) {
        InputLayer in = new InputLayer(this.numInputs, inputs);
        HiddenLayer hidden = new HiddenLayer(this.numPerHidden, in.getOutputs(),
                                             this.hidden.getWeights(),
                                             this.hidden.getBias());
        OutputLayer out = new OutputLayer(this.numOutputs,
                                          hidden.getOutputs(),
                                          this.out.getWeights(),
                                          this.out.getBias(),
                                          desiredOutputs);
        return out.getSSE();
    }

    public double[] getOutput(double[] inputs, double[] desiredOutputs) {
        InputLayer in = new InputLayer(this.numInputs, inputs);
        HiddenLayer hidden = new HiddenLayer(this.numPerHidden, in.getOutputs(),
                                             this.hidden.getWeights(),
                                             this.hidden.getBias());
        OutputLayer out = new OutputLayer(this.numOutputs,
                                          hidden.getOutputs(),
                                          this.out.getWeights(),
                                          this.out.getBias(),
                                          desiredOutputs);
        return out.getOutputs();
    }

}
