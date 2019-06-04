package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CharacterDisplayer extends Canvas {
    private int characterPositionRow = 1;
    private int characterPositionColumn = 1;
    private int endPositionRow = 1;
    private int endPositionColumn = 1;
    private int[][] maze;


    public void setCharacterPosition(int row, int column, int endR, int endC) {
        characterPositionRow = row;
        characterPositionColumn = column;
        endPositionRow = endR;
        endPositionColumn = endC;
        displayCharcter();
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public void displayCharcter() {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.length;
            double cellWidth = canvasWidth / maze[0].length;
            Image characterImage = new Image("mario.png");
            Image endImg = new Image("peach.jpg");
            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, getWidth(), getHeight());
            gc.drawImage(endImg, endPositionColumn * cellHeight, endPositionRow * cellWidth, cellHeight, cellWidth);
            gc.drawImage(characterImage, characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
        }


    }
    public int getCharacterPositionRow(){
        return characterPositionRow;
    }
    public int getCharacterPositionColumn () {
        return characterPositionColumn;
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public StringProperty imageFileNameCharacterProperty() {
        return ImageFileNameCharacter;
    }

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }

    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();

}

