/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw03;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Main extends Application {

    private Model theModel;
    private View theView;
    private Controller theCtrl;

    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new Model();
        this.theView = new View();
        this.theCtrl = new Controller(theModel, theView);
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(this.theView.getRoot(), 700, 600);

        primaryStage.setTitle("ANN Visualization");

        primaryStage.setScene(scene);

        primaryStage.sizeToScene();

        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
