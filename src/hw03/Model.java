/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw03;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author user
 */
public class Model {

    private SimpleDoubleProperty value;

    public ObservableValueBase getColor() {
        if (value.get() < 0) {
            return new ObservableValueBase<Paint>() {
                @Override
                public Paint getValue() {
                    return Color.RED;
                }
            };
        }
        else if (value.get() >= 0) {
            return new ObservableValueBase<Paint>() {
                @Override
                public Paint getValue() {
                    return Color.BLUE;
                }
            };
        }
        return null;
    }
}
