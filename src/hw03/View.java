/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw03;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TimerTask;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import neuralnet.ANN;
import static neuralnet.ANN.computeOutputError;
import neuralnet.data.LabeledInstances;

/**
 *
 * @author user
 */
public class View {

    private TimerTask task;
    private Group root;
    private Rectangle[] Layer1, Layer2, Layer3;
    private SimpleDoubleProperty[][] Edges1, Edges2;
    private SimpleDoubleProperty Error;
    private SimpleIntegerProperty Epoch;
    private Button update, learn, stop, build;
    private Text errorText, epochText;
    private TextField in, hidden, out, filename, learningrate, momentum;
    private ANN theANN;
    private int numInputs, numHidden, numOutputs;
    private int epoch = 0;
    private double error = Double.NaN;
    private LabeledInstances trainData; // = new LabeledInstances( "./src/ExampleXOR.csv", true, 2);
    private ArrayList<ArrayList<Double>> output;

    public View() {

        initialize();

        // create the buttons to learn continuously, update by one epoch and stop
        learn = new Button("Learn");
        stop = new Button("Stop");
        update = new Button("Update(By one epoch)");

        learn.setLayoutX(50);
        learn.setLayoutY(100);
        stop.setLayoutX(150);
        stop.setLayoutY(100);
        update.setLayoutX(250);
        update.setLayoutY(100);

        root.getChildren().addAll(learn, stop, update);
    }

    private void updateLinePosition(Line line, Rectangle r1, Rectangle r2) {
        line.setStartX(r1.getX() + r1.getWidth() / 2);
        line.setStartY(r1.getY() + r1.getHeight() / 2);
        line.setEndX(r2.getX() + r2.getWidth() / 2);
        line.setEndY(r2.getY() + r2.getHeight() / 2);
    }

    public void learn() {
        epoch += 1;
        theANN.learn(trainData, true, 1);
        output = theANN.classifyInstances(trainData);
        error = computeOutputError(trainData, output);
        Error.set(error);
        Epoch.set(epoch);
        for (int i = 0; i < numHidden; i++) {
            for (int j = 0; j < numInputs; j++) {
                Edges1[i][j].set(theANN.getEdges(0).getWeightOf(i, j));
            }
        }
        for (int i = 0; i < numOutputs; i++) {
            for (int j = 0; j < numHidden; j++) {
                Edges2[i][j].set(theANN.getEdges(1).getWeightOf(i, j));
            }
        }
    }

    /**
     *
     * @param i the index of the neoron of the output layer
     * @param j the index of the neoron of the input layer
     * @return the value of the weight between outputIndex and inputIndex
     */
    public DoubleProperty getEdges1Value(int i, int j) {
        //return new SimpleDoubleProperty(theANN.getEdges(0).getWeightOf(i, j));
        return Edges1[i][j];
    }

    public DoubleProperty getEdges2Value(int i, int j) {
        //return new SimpleDoubleProperty(theANN.getEdges(1).getWeightOf(i, j));
        return Edges2[i][j];
    }

    public IntegerProperty getEpochProperty() {
        return Epoch;
    }

    public DoubleProperty getErrorProperty() {
        return Error;
    }

    public Group getRoot() {
        return root;
    }

    public Button getLearn() {
        return learn;
    }

    public Button getStop() {
        return stop;
    }

    public Button getUpdate() {
        return update;
    }

    /**
     * Create the rectangles to represent neurons. Use 3 list of rectangles to
     * represent 3 layers of neurons
     */
    public void createNeurons() {
        // Create the rectangles to represent neurons
        for (int i = 0; i < numInputs; i++) {
            Rectangle r1 = new Rectangle(50, 30, Color.BLUE);
            r1.setX(100);
            r1.setY(150 + 100 * i);
            Layer1[i] = r1;
            root.getChildren().add(r1);
        }

        for (int i = 0; i < numHidden; i++) {
            Rectangle r1 = new Rectangle(50, 30, Color.GREEN);
            r1.setX(300);
            r1.setY(150 + 100 * i);
            Layer2[i] = r1;
            root.getChildren().add(r1);
        }
        for (int i = 0; i < numOutputs; i++) {
            Rectangle r1 = new Rectangle(50, 30, Color.GREY);
            r1.setX(500);
            r1.setY(150 + 100 * i);
            Layer3[i] = r1;
            root.getChildren().add(r1);
        }
    }

    /**
     * Create the textfields for initialization. Take input data to build the
     * ANN for visualization.
     */
    public void initialize() {
        root = new Group();

        // create the textfields
        in = new TextField("2");
        hidden = new TextField("4");
        out = new TextField("1");
        filename = new TextField("./src/ExampleXOR.csv");
        learningrate = new TextField("0.3");
        momentum = new TextField("0.7");
        in.setLayoutY(10);
        hidden.setLayoutY(10);
        hidden.setLayoutX(200);
        out.setLayoutY(40);
        filename.setLayoutY(40);
        filename.setLayoutX(200);
        learningrate.setLayoutY(70);
        momentum.setLayoutY(70);
        momentum.setLayoutX(200);

        root.getChildren().addAll(in, hidden, out, filename, learningrate,
                                  momentum);

        // build the ANN using the initial conditions
        build = new Button("Build");
        build.setLayoutX(400);
        build.setLayoutY(25);
        build.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                numInputs = Integer.parseInt(in.getText());
                numHidden = Integer.parseInt(hidden.getText());
                numOutputs = Integer.parseInt(out.getText());

                Layer1 = new Rectangle[numInputs];
                Layer2 = new Rectangle[numHidden];
                Layer3 = new Rectangle[numOutputs];

                theANN = new ANN(Integer.parseInt(in.getText()),
                                 Integer.parseInt(
                                         hidden.getText()), Integer.parseInt(
                                 out.getText()));
                theANN.setMomentum(Double.parseDouble(momentum.getText()));
                theANN.setLearningRate(
                        Double.parseDouble(learningrate.getText()));
                try {
                    trainData = new LabeledInstances(filename.getText(), true, 2);
                } catch (FileNotFoundException ex) {
                    System.exit(0);
                }
                Edges1 = new SimpleDoubleProperty[numHidden][numInputs];
                Edges2 = new SimpleDoubleProperty[numOutputs][numHidden];
                theANN.learn(trainData, true, 1);
                output = theANN.classifyInstances(trainData);
                error = computeOutputError(trainData, output);
                Error = new SimpleDoubleProperty(error);
                Epoch = new SimpleIntegerProperty(0);

                for (int i = 0; i < numHidden; i++) {
                    for (int j = 0; j < numInputs; j++) {
                        Edges1[i][j] = new SimpleDoubleProperty(
                                theANN.getEdges(0).getWeightOf(i, j));
                    }
                }
                for (int i = 0; i < numOutputs; i++) {
                    for (int j = 0; j < numHidden; j++) {
                        Edges2[i][j] = new SimpleDoubleProperty(
                                theANN.getEdges(1).getWeightOf(i, j));
                    }
                }

                createNeurons();

                // create the lines to represent the edges between neurons and the texts to declare the weights
                for (int i = 0; i < numHidden; i++) {
                    for (int j = 0; j < numInputs; j++) {
                        Line l1 = new Line();
                        l1.setStrokeWidth(2);
                        updateLinePosition(l1, Layer2[i], Layer1[j]);

                        Text t1 = new Text(
                                Layer1[j].getX() + (Layer2[i].getX() - Layer1[j].getX()) / 2,
                                Layer1[j].getY() + (Layer2[i].getY() - Layer1[j].getY()) / 2 + 15,
                                "" + getEdges1Value(i, j).getValue());
                        t1.textProperty().bind(Bindings.convert(Edges1[i][j]));
                        //t1.textProperty().bind(Bindings.convert( model.getEdges1Value(i, j)));
                        root.getChildren().addAll(l1, t1);
                    }
                }
                for (int i = 0; i < numOutputs; i++) {
                    for (int j = 0; j < numHidden; j++) {
                        Line l2 = new Line();
                        l2.setStrokeWidth(2);
                        updateLinePosition(l2, Layer3[i], Layer2[j]);

                        Text t2 = new Text(
                                Layer2[j].getX() + (Layer3[i].getX() - Layer2[j].getX()) / 2,
                                Layer2[j].getY() + (Layer3[i].getY() - Layer2[j].getY()) / 2 + 15,
                                "" + getEdges2Value(i, j).getValue());
                        t2.textProperty().bind(Bindings.convert(
                                getEdges2Value(i, j)));
                        root.getChildren().addAll(l2, t2);
                    }
                }

                // create the label of average SSE
                Text errorDeclare = new Text(400, 70, "The average SSE: ");
                errorText = new Text(530, 70,
                                     "" + getErrorProperty().getValue());
                errorText.textProperty().bind(Bindings.convert(
                        getErrorProperty()));

                Text epochDeclare = new Text(400, 90, "The current epoch: ");
                epochText = new Text(530, 90, "" + getEpochProperty().getValue());
                epochText.textProperty().bind(Bindings.convert(
                        getEpochProperty()));

                root.getChildren().addAll(errorDeclare, errorText, epochDeclare,
                                          epochText);

            }
        });
        root.getChildren().add(build);
    }

    /*@Override
    public void start(Stage primaryStage) throws InterruptedException, FileNotFoundException {
        //m = new Model();
        Button btn = new Button();
        btn.setText("Update");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                task = new TimerTask() {
                    public void run() {
                        m.update();
                    }
                };
                new Timer().scheduleAtFixedRate(task, 20, 20);
            }
        });

        Button btn2 = new Button();
        btn2.setText("Stop");
        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                task.cancel();
            }
        });

        VBox root = new VBox();

        Text t = new Text(0, 10, "" + m.getValue());
        t.textProperty().bind(Bindings.convert(m.valueProperty()));
        root.getChildren().addAll(t, btn, btn2);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
 /*public static void main(String[] args) {
        launch(args);
    }*/

 /*public void update() {
        double a = m.getValue();
        m.setValue(a + 1);
    }*/
}
