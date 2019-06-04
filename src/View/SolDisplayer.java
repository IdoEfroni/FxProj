package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class SolDisplayer extends Canvas {
    private int[][] maze;
    private ArrayList<int[]> arrsol;

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public String getImageFileNamePath() {
        return ImageFileNamePath.get();
    }

    public StringProperty imageFileNamePathProperty() {
        return ImageFileNamePath;
    }

    public void setImageFileNamePath(String imageFileNamePath) {
        this.ImageFileNamePath.set(imageFileNamePath);
    }

    public void setSol(ArrayList<int[]> arr) {
        this.arrsol = arr;
        presentSol();
    }

    public void cleansol() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
    }

    public void presentSol() {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.length;
            double cellWidth = canvasWidth / maze[0].length;
            Image wallImage = new Image("brick.jpg");
            Image characterImage = new Image("mario.png");
            Image path = new Image("path.png");
            Image endImg = new Image("peach.jpg");
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());
            for (int i = 0; i < arrsol.size(); i++) {
                int x = arrsol.get(i)[0];
                int y = arrsol.get(i)[1];
                Image coin = new Image("coin.png");
                gc.drawImage(coin, y * cellHeight, x * cellWidth, cellHeight, cellWidth);

            }
        }
    }

    private StringProperty ImageFileNamePath = new SimpleStringProperty();


}
