package hslu.sweng.fs22.team2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppUI.class.getResource("MainPage.fxml"));

        //Set min window sizex
        stage.setMinHeight(600);
        stage.setMinWidth(800);


        Scene scene = new Scene(fxmlLoader.load(), 800 , 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
