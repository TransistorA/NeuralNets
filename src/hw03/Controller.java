/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw03;

import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author user
 */
public class Controller {

    private Model model;
    private View view;
    private TimerTask task;

    public Controller(Model m, View v) {
        model = m;
        view = v;

        view.getLearn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                task = new TimerTask() {
                    public void run() {
                        view.learn();
                    }
                };
                new Timer().scheduleAtFixedRate(task, 20, 20);
            }
        });

        view.getStop().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                task.cancel();
            }
        });
        view.getUpdate().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.learn();
                //System.out.println(model.getEdges1Value(0, 0).getValue());
            }
        });
    }

}
