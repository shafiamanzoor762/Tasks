
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ex_29 extends Application {

    @Override
    public void start(Stage primary) {

        CarPane carPane = new CarPane();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10), e-> carPane.moveCar(1)));
        timeline.setCycleCount(carPane.getOneCycleDuration());
        timeline.play();
        timeline.setOnFinished(e-> {
            carPane.reset();
            timeline.play();
        });

        Button pauseBtn = new Button("PAUSE");
        pauseBtn.setOnAction(e-> timeline.pause());

        Button resumeBtn = new Button("RESUME");
        resumeBtn.setOnAction(e-> timeline.play());

        Button increaseBtn = new Button("INCREASE");
        increaseBtn.setOnAction(e-> {
            timeline.setRate(timeline.getCurrentRate() + 1);
        });

        Button decreaseBtn = new Button("DECREASE");
        decreaseBtn.setOnAction(e-> {
            if (timeline.getCurrentRate() <= 0) return;

            timeline.setRate(timeline.getCurrentRate() - 1);
        });
        
        
        Button redrawButton = new Button("REDRAW CAR");
        redrawButton.setOnAction(e -> {
            carPane.redrawCar();
        });

        HBox hButtons = new HBox(pauseBtn, resumeBtn, increaseBtn, decreaseBtn,redrawButton);
        hButtons.setSpacing(10);
        hButtons.setAlignment(Pos.CENTER);
        hButtons.setPadding(new Insets(5, 5, 5, 5));


        BorderPane borderPane = new BorderPane(carPane, null, null, hButtons, null);
        borderPane.setOnKeyPressed(e-> {
            switch (e.getCode()) {
                case UP: increaseBtn.fire(); break;
                case DOWN: decreaseBtn.fire(); break;
            }
        });
        primary.setScene(new Scene(borderPane));
        primary.setTitle("Moving Car");
        primary.show();

    }

    public static void main(String[] args) {
        Application.launch(args);

    }

    private class CarPane extends Pane {

        private double width;
        private double height;

        private double leftTireX;
        private double leftTireY;
        
        private double tireRadius;

        Circle[] tire = new Circle[2];
        Polyline cover = new Polyline();
        ObservableList<Double> points;
        Rectangle base;

        private CarPane(){

            width = 600;
            height = 200;
            leftTireX = width * 0.2;
            leftTireY = height * 0.9;
            tireRadius = height * 0.1;

            setMinWidth(width);
            setMinHeight(height);

            setMaxWidth(width);
            setMaxHeight(height);

            reset();
        }

        public void redrawCar() {
            double newBaseX = Math.random() * (width - 200);
            double newBaseY = Math.random() * (height - 100);

            getChildren().clear();
            leftTireX = newBaseX;
            leftTireY = newBaseY;
            reset();
        }
        
		public void reset() {

            if (points != null)
                points.clear();
            getChildren().clear();
            drawCar();
            moveCar(tireRadius * 13 * -1);
        }

        public void drawCar() {
            for (int i = 0; i < tire.length; i++) {
                tire[i] = new Circle(leftTireX + (i * 4 * tireRadius), leftTireY, tireRadius);
                tire[i].setStroke(Color.BLACK);
                tire[i].setFill(Color.TRANSPARENT);

            }

            double baseX = tire[0].getCenterX() - tire[0].getRadius() * 3;
            double baseY = tire[0].getCenterY() - tire[0].getRadius() * 3;
            base = new Rectangle(baseX, baseY, tireRadius * 10, tireRadius * 2);
            base.setFill(Color.TRANSPARENT);
            base.setStroke(Color.BLACK);

            double startX = base.getX() + tireRadius * 2;
            double startY = base.getY();
            double currentX = startX;
            double currentY = startY;

            points = cover.getPoints();

            double distance = tireRadius * 2;
            points.addAll(currentX, currentY);

            currentX += distance;
            currentY -= distance;
            points.addAll(currentX, currentY);

            currentX += distance;
            points.addAll(currentX,currentY);

            currentX += distance;
            currentY += distance;
            points.addAll(currentX,currentY);

            points.addAll(startX, startY);
            cover.setFill(Color.BLUE);

            getChildren().addAll(tire);
            getChildren().add(base);
            getChildren().add(cover);
        }

        private void moveCar(double distance) {

            for (Circle c : tire) {
                c.setCenterX(c.getCenterX() + distance);
            }

            base.setX(base.getX() + distance);

            for (int i = 0; i < points.size(); i += 2) {
                points.set(i, points.get(i) + distance);
            }

        }

        public int getOneCycleDuration(){
            return (int)width;
        }
    }
}
