package compiler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Init extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditorUI.fxml"));
        Parent root = (Parent)loader.load();
        Controller controller = (Controller)loader.getController();
        primaryStage.setTitle("Compilador");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }

}