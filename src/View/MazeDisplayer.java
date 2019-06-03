package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Aviadjo on 3/9/2017.
 */
public class MazeDisplayer extends Canvas {

    private int[][] maze;
    private int characterPositionRow = 1;
    private int characterPositionColumn = 1;
    private int endPositionRow = 1;
    private int endPositionColumn = 1;

    public void setMaze(int[][] maze) {
        this.maze = maze;
        redraw();
    }

    public void setCharacterPosition(int row, int column, int endR, int endC) {
        characterPositionRow = row;
        characterPositionColumn = column;
        endPositionRow = endR;
        endPositionColumn = endC;
        redraw();
    }

    public int getCharacterPositionRow() {
        return characterPositionRow;
    }

    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }

    public void redraw() {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.length;
            double cellWidth = canvasWidth / maze[0].length;

            Image wallImage = new Image("file:///Users/Public/FxProj/src/View/wall.jpg");
            Image characterImage = new Image("file:///Users/Public/FxProj/src/View/hodor.jpg");
            Image path = new Image("file:///Users/Public/FxProj/resources/brick.jpg");
            Image endImg = new Image("file:///Users/yinon/IdeaProjects/FxProj/src/View/bran.jpg");

            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());

            //Draw Maze
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    if (maze[i][j] == 1) {
                        //gc.fillRect(i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                        gc.drawImage(wallImage, j * cellHeight, i * cellWidth, cellHeight, cellWidth);
                    }
                    else{
                        gc.drawImage(path, i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                    }
                }
            }

            //Draw Character
            //gc.setFill(Color.RED);
            //gc.fillOval(characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
            gc.drawImage(endImg,endPositionColumn*cellHeight,endPositionRow *cellWidth, cellHeight,cellWidth);
            gc.drawImage(characterImage, characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
        }
    }

    //region Properties
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();

    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }
    //endregion

}
