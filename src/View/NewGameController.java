package View;

import Model.MyModel;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.annotation.Resource;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.ResourceBundle;

public class NewGameController implements Observer,Initializable, IView {
    boolean lock = true;
    ObservableList listGenerate = FXCollections.observableArrayList();
    ObservableList listSolution = FXCollections.observableArrayList();

    private boolean finish = false;

    private MyViewModel viewModel;
    @FXML
    private ComboBox<String>comboSolve;
    @FXML
    private ChoiceBox<String> choiceSolver;
    @FXML
    private TextField screen;
    @FXML
    public TextField txtfld_rowsNum;
    @FXML
    public TextField txtfld_columnsNum;
    @FXML
    public MazeDisplayer mazeDisplayer;
    @FXML
    private ComboBox<String>combo;
/* the code from the lecture*/
    public void setViewModel(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void displayMaze(int[][] maze) {
        mazeDisplayer.setMaze(maze);
        int characterPositionRow = viewModel.getCharacterPositionRow();
        int characterPositionColumn = viewModel.getCharacterPositionColumn();
        int endPositionRow = viewModel.getEndPositionRow();
        int endPositionCol = viewModel.getEndPositionColumn();
        mazeDisplayer.setCharacterPosition(characterPositionRow, characterPositionColumn, endPositionRow,endPositionCol);
       // this.characterPositionRow.set(characterPositionRow + "");
        //this.characterPositionColumn.set(characterPositionColumn + "");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            displayMaze(viewModel.getMaze());
        }
    }

    public void setResizeEvent(Scene scene) {
        long width = 0;
        long height = 0;
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                System.out.println("Width: " + newSceneWidth);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);
            }
        });
    }


    @FXML
    private void generate(ActionEvent event) {
        lock = false;
        screen.setText("");
        String maze = combo.getValue();

        try {
            InputStream in = new FileInputStream("resources/config.properties");
            Throwable var1 = null;

            SimpleMazeGenerator var3;
            Properties prop = new Properties();
            prop.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream("resources/config.properties");

            if (maze != null) {
                if (maze == "Empty Maze") {
                    prop.setProperty("Generate", "Empty");
                    screen.setText("Empty Maze");
                    System.out.println("Empty Maze");

                }
                if (maze == "Simple Maze") {
                    prop.setProperty("Generate", "Simple");
                    screen.setText("Simple Maze");
                    System.out.println("Simple Maze");
                }
                if (maze == "Complex Maze") {
                    prop.setProperty("Generate", "Complex");
                    screen.setText("Complex Maze");
                    System.out.println("Complex Maze");
                }
                prop.store(out,null);
                out.close();
                int heigth = Integer.valueOf(txtfld_rowsNum.getText());
                int width = Integer.valueOf(txtfld_columnsNum.getText());
                viewModel.generateMaze(width, heigth);
                displayMaze(viewModel.getMaze());
                finish = false;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @FXML
    private void solve(ActionEvent event){

        if(lock == false){

            try {
                InputStream in = new FileInputStream("resources/config.properties");
                Throwable var1 = null;

                SimpleMazeGenerator var3;
                Properties prop = new Properties();
                prop.load(in);
                in.close();

            String massege = comboSolve.getValue();
            if(massege !=null){
                if(massege == "BFS"){
                    prop.setProperty("Solve","BFS");
                    screen.setText("BFS is selected");
                    System.out.println("BFS");
                }

                if(massege == "DFS"){
                    prop.setProperty("Solve","DFS");
                    screen.setText("DFS is selected");
                    System.out.println("DFS");

                }if(massege =="Best First Search"){
                    prop.setProperty("Solve","Best First Search");
                    screen.setText("Best first search is selected");
                    System.out.println("Best first search");
                }
                viewModel.solveMaze();
                //displayMaze(viewModel.getsolution());
            }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }/////
        else{
            screen.setText("you can't do it");
            System.out.println("you can't do it");
        }
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
        MenuItem menu = ((MenuItem) event.getSource());
        while (menu.getParentPopup() == null) {
            menu = menu.getParentMenu();
        }

        Stage window = (Stage) menu.getParentPopup().getOwnerWindow();
        window.setScene(scene);

        NewGameController view = fxmlLoader.getController();
//        view.setResizeEvent(scene);
        view.setViewModel(viewModel);
        viewModel.addObserver(view);

        finish = false;

        window.show();
    }


    @FXML
    public void about(ActionEvent event) {
        Pane pane = new HBox(15);
        javafx.scene.image.Image im = new Image("snoop.jpg");
        ImageView imv = new ImageView(im);
//    pane.getChildren().add(imv);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "ido & inon");
        alert.setGraphic(imv);
        alert.showAndWait();
    }

    public void KeyPressed(KeyEvent keyEvent) {
        if(!finish) {
            viewModel.moveCharacter(keyEvent.getCode());
            keyEvent.consume();
        }
        if(viewModel.getCharacterPositionRow() == viewModel.getEndPositionRow() && viewModel.getCharacterPositionColumn() == viewModel.getEndPositionColumn()){
            finish = true;
            screen.setText("Congratulations!");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    private void loadData() {
        listGenerate.removeAll(listGenerate);
        listSolution.removeAll(listSolution);
        String name_G1 = "Empty Maze";
        String name_G2 = "Simple Maze";
        String name_G3 = "Complex Maze";
        listGenerate.addAll(name_G1, name_G2, name_G3);
        String name_S1 = "BFS";
        String name_S2 = "DFS";
        String name_S3 = "Best First Search";
        listSolution.addAll(name_S1, name_S2, name_S3);
        combo.getItems().addAll(listGenerate);
        comboSolve.getItems().addAll(listSolution);
    }





}
