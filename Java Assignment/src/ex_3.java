import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ex_3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        double width = 500;
        double height = 500;
        BallPane ballPane = new BallPane(width / 2, height / 2, Math.min(width, height) / 15);

        Button btUp = new Button("Up");
        btUp.setOnAction(e -> ballPane.moveUp());

        Button btDown = new Button("Down");
        btDown.setOnAction(e -> ballPane.moveDown());

        Button btLeft = new Button("Left");
        btLeft.setOnAction(e -> ballPane.moveLeft());

        Button btRight = new Button("Right");
        btRight.setOnAction(e -> ballPane.moveRight());

        HBox buttons = new HBox(btUp, btDown, btLeft, btRight);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10, 10, 10, 10));

        BorderPane pane = new BorderPane();
        pane.setCenter(ballPane);
        pane.setBottom(buttons);

        Scene scene = new Scene(pane, width, height);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Move the Ball");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class BallPane extends Pane {

    private Circle ball;

    public BallPane(double centerX, double centerY, double radius) {
        ball = new Circle(centerX, centerY, radius);
        getChildren().add(ball);
    }

    public void moveUp() {
        if (ball.getCenterY() - ball.getRadius() - 10 >= 0) {
            ball.setCenterY(ball.getCenterY() - 10);
        }
    }

    public void moveDown() {
        if (ball.getCenterY() + ball.getRadius() + 10 <= getHeight()) {
            ball.setCenterY(ball.getCenterY() + 10);
        }
    }

    public void moveLeft() {
        if (ball.getCenterX() - ball.getRadius() - 10 >= 0) {
            ball.setCenterX(ball.getCenterX() - 10);
        }
    }

    public void moveRight() {
        if (ball.getCenterX() + ball.getRadius() + 10 <= getWidth()) {
            ball.setCenterX(ball.getCenterX() + 10);
        }
    }
    public static void main(String[] args) {
      Application.launch(args);
  }
}

