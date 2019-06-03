package Model;

import algorithms.search.AState;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public interface IModel {
    void generateMaze(int width, int height);

    void moveCharacter(KeyCode movement);

    int[][] getMaze();

    int getCharacterPositionRow();

    int getCharacterPositionColumn();
    void solveMaze();
    ArrayList<int[]> getSolution();
}

