package hslu.sweng.fs22.team2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppUI extends Application {

    Stage primaryStage;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage window) throws IOException {
        primaryStage = window;
        FXMLLoader fxmlLoader = new FXMLLoader(AppUI.class.getResource("MainPage.fxml"));
        Scene MainPage = new Scene(fxmlLoader.load(), 800, 800);


        //Set up window settings
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(AppUI.class.getResourceAsStream("icon.png"))));


        primaryStage.setTitle("Cinema Booking System");
        primaryStage.setScene(MainPage);
        primaryStage.show();
    }
}
