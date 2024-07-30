
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ex_15 extends Application {

    Pane drawingPane = new Pane();

    @Override
    public void start(Stage primaryStage) {

        double canvasWidth = 500;
        double canvasHeight = 500;

        drawingPane.setOnMouseClicked(e-> {
            double clickX = e.getX();
            double clickY = e.getY();

            if (e.getButton() == MouseButton.PRIMARY) {
                Circle drawnCircle = drawCircle(clickX, clickY);
                drawingPane.getChildren().add(drawnCircle);
            } else if (e.getButton() == MouseButton.SECONDARY) {
                removeCircle(clickX, clickY);
            }
        });

        Scene scene = new Scene(drawingPane, canvasWidth, canvasHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Circle Drawer");
        primaryStage.show();
    }

    private Circle drawCircle(double x, double y) {
        Circle circle = new Circle(x, y, 10, Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    private void removeCircle(double x, double y) {
        ObservableList<Node> nodeList = drawingPane.getChildren();
        for (int i = nodeList.size() - 1; i >= 0; i--) {
            Node node = nodeList.get(i);
            if (node instanceof Circle && node.contains(x, y)) {
                drawingPane.getChildren().remove(node);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
