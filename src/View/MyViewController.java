package View;

import java.awt.*;
import java.awt.Button;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.Random;
import java.util.WeakHashMap;

import Model.IModel;
import Model.MyModel;
import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MyViewController implements IView {

    @FXML
    public void print() {
        System.out.printf("wtf");
    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void about(ActionEvent event) {
        Pane pane = new HBox(15);
        Image im = new Image("snoop.jpg");
        ImageView imv = new ImageView(im);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ido & inon");
        alert.setGraphic(imv);
        alert.showAndWait();
    }

    @FXML
    public void New(ActionEvent event) throws IOException {

        MyModel model = new MyModel();
        //model.startServers();
        MyViewModel viewModel = new MyViewModel(model);
        model.addObserver(viewModel);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newGame.fxml"));

        Parent tableParent = fxmlLoader.load();
        Scene scene = new Scene(tableParent, 800, 700);
        scene.getStylesheets().add(getClass().getResource("GenerateStyle.css").toExternalForm());

        String ssound = "file:///C:/Users/Public/FxProj/resources/maritheme.mp3";
        Media sound = new Media(ssound);
        MediaPlayer a =new MediaPlayer(sound);
        a.setOnEndOfMedia(new Runnable() {
            public void run() {
                a.seek(Duration.ZERO);
            }
        });
        a.play();


        Stage window = null;
        if (event.getTarget().toString().substring(0, event.getTarget().toString().indexOf('@')).equals("MenuItem")) {
            MenuItem menu = ((MenuItem) event.getSource());
            while (menu.getParentPopup() == null) {
                menu = menu.getParentMenu();
            }

            window = (Stage) menu.getParentPopup().getOwnerWindow();
        } else {
            //Button button = ((Button)event.getSource());
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        //Stage window = new Stage();

        window.setScene(scene);

        NewGameController view = fxmlLoader.getController();
//        view.setResizeEvent(scene);
        view.setViewModel(viewModel);
        viewModel.addObserver(view);

        window.show();
    }

    @Override
    public void displayMaze(int[][] maze) {
    }
}


