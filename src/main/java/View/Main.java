package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ido & inon");
        //String ssound = "/FxProj/resources/sound.mp3";
        //Media sound = new Media(ssound);
        //MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.play();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        scene = new Scene(root, 800, 700);
        scene.getStylesheets().add(getClass().getResource("MainStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

