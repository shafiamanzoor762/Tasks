import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Q16_3 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = new TrafficLightPane(300, 300);

        primaryStage.setScene(new Scene(pane));
        primaryStage.setTitle("Traffic Light Simulation");
        primaryStage.show();
    }

    private class TrafficLightPane extends Pane {

        double width;
        double height;

        private TrafficLightPane(double paneWidth, double paneHeight) {

            width = paneWidth;
            height = paneHeight;
            setPrefWidth(width);
            setPrefHeight(height);

            Rectangle rectangle = new Rectangle(width / 3, height / 6, width / 3, height / 1.5);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(Color.BLACK);
            getChildren().add(rectangle);

            String[] lightColors = {"Red", "Yellow", "Green"};

            ToggleGroup toggleGroup = new ToggleGroup();

            Circle[] circles = new Circle[3];
            Color[] colors = new Color[]{Color.RED, Color.YELLOW, Color.GREEN};
            double[] circleY = new double[]{0.3, 0.5, 0.7};
            RadioButton[] radioButtons = new RadioButton[circles.length];

            for (int i = 0; i < circles.length; i++) {
                circles[i] = new Circle(width / 2, height * circleY[i], width * 0.09);
                circles[i].setFill(Color.TRANSPARENT);
                circles[i].setStroke(Color.BLACK);
                radioButtons[i] = new RadioButton(lightColors[i]);

                final int index = i;
                radioButtons[i].setOnAction(e-> {
                    resetLights(circles);
                    circles[index].setFill(colors[index]);
                });
                radioButtons[i].setToggleGroup(toggleGroup);
            }

            HBox radioButtonPane = new HBox(10);
            radioButtonPane.getChildren().addAll(radioButtons);
            radioButtonPane.setAlignment(Pos.CENTER);
            radioButtonPane.setLayoutX(width * 0.2);
            radioButtonPane.setLayoutY(height - 30);

            getChildren().addAll(circles);
            getChildren().addAll(radioButtonPane);
        }

        private void resetLights(Shape[] shapes) {
            for (Shape shape : shapes) {
                shape.setFill(Color.TRANSPARENT);
            }
        }
    }
}
