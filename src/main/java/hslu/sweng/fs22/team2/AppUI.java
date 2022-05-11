package hslu.sweng.fs22.team2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppUI extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppUI.class.getResource("MainPage.fxml"));
        Scene MainPage = new Scene(fxmlLoader.load(), 800, 800);


        //Set up window settings
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.getIcons().add(new Image(AppUI.class.getResourceAsStream("icon.png")));


        primaryStage.setTitle("Cinema Booking System");
        primaryStage.setScene(MainPage);
        primaryStage.show();
    }
}
