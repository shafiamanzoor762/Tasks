import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Q16_17 extends Application {

    ScrollBar[] colorScrollBars = new ScrollBar[4];
    Color mainColor = Color.RED;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Text text = new Text("Show Colors");

        Label[] labels = new Label[4];
        String[] stringLabels = {"Red", "Green", "Blue", "Opacity"};

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(5));

        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < colorScrollBars.length; i++) {
            colorScrollBars[i] = new ScrollBar();
            colorScrollBars[i].setMin(0);
            if (!stringLabels[i].equals("Opacity")) {
                colorScrollBars[i].setMax(255);
                colorScrollBars[i].setValue(255);
            } else {
                colorScrollBars[i].setMax(1);
                colorScrollBars[i].setValue(1);
            }
            labels[i] = new Label(stringLabels[i]);
            colorScrollBars[i].valueProperty()
                    .addListener((obser, old, newV) -> text.setFill(getSelectedColor()));

            gridPane.add(labels[i], 0, i);
            gridPane.add(colorScrollBars[i], 1, i);

        }
        StackPane stackPane = new StackPane(text);
        stackPane.setPrefSize(300, 200);
        BorderPane borderPane = new BorderPane(stackPane);
        borderPane.setBottom(gridPane);

        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("Color Changer");
        primaryStage.show();
    }

    private Color getSelectedColor() {

    	double[] rgb = new double[4];
        for (int i = 0; i < rgb.length; i++) {
            rgb[i] = colorScrollBars[i].getValue();
        }
        return Color.rgb((int) rgb[0], (int) rgb[1], (int) rgb[2], rgb[3]);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
