import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Q16_15 extends Application {

    Label labelGrapes = new Label("Grapes", new ImageView("file:///F:/eclipse_Tasks/Ch_15_Exercises/image/grapes.gif"));

    @Override
    public void start(Stage primaryStage) throws Exception {

        labelGrapes.setPadding(new Insets(5));
        ComboBox<String> comboBoxContentDisplay = new ComboBox<>();
        String[] options = {"TOP", "BOTTOM", "LEFT", "RIGHT"};
        comboBoxContentDisplay.getItems().addAll(options);
        comboBoxContentDisplay.setValue(options[2]);
        comboBoxContentDisplay.setOnAction(e-> {
            updateDisplay(comboBoxContentDisplay.getValue());
            primaryStage.sizeToScene();
        });
        Label labelContentDisplay = new Label("Content Display:", comboBoxContentDisplay);
        labelContentDisplay.setContentDisplay(ContentDisplay.RIGHT);

        TextField textFieldGraphicTextGap = new TextField();
        textFieldGraphicTextGap.setPrefColumnCount(4);
        textFieldGraphicTextGap.setText(labelGrapes.getGraphicTextGap() + "");
        textFieldGraphicTextGap.setOnAction(e-> {
            labelGrapes.setGraphicTextGap(
                    Double.parseDouble(textFieldGraphicTextGap.getText()));
            primaryStage.sizeToScene();
        });

        Label labelGraphicTextGap = new Label("Graphic Text Gap:", textFieldGraphicTextGap);
        labelGraphicTextGap.setContentDisplay(ContentDisplay.RIGHT);
        HBox topPane = new HBox(labelContentDisplay, labelGraphicTextGap);
        topPane.setSpacing(5);
        topPane.setPadding(new Insets(5));

        StackPane stackPane = new StackPane(labelGrapes);

        BorderPane borderPane = new BorderPane(stackPane);
        borderPane.setTop(topPane);
        primaryStage.setScene(new Scene(borderPane));
        primaryStage.setTitle("Graphic Content Display");
        primaryStage.show();
    }

    public void updateDisplay(String direction) {
        switch (direction) {
            case "TOP": labelGrapes.setContentDisplay(ContentDisplay.TOP);break;
            case "BOTTOM": labelGrapes.setContentDisplay(ContentDisplay.BOTTOM);break;
            case "RIGHT": labelGrapes.setContentDisplay(ContentDisplay.RIGHT);break;
            case "LEFT": labelGrapes.setContentDisplay(ContentDisplay.LEFT);break;
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
