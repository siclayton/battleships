import java.util.Random;

public class BoardMatrix {
    private boolean[][] matrix;

    int numShips; //The number of ships that will be placed on the board
    int shipSize; //The size of the ships that will be placed on the board

    int height; //The height of the grid that the game will be played on
    int width; //The width of the grid that the game will be played on

    public BoardMatrix(int numShips, int shipSize, int height, int width) {
        this.numShips = numShips;
        this.shipSize = shipSize;
        this.height = height;
        this.width = width;

        matrix = new boolean[height][width];

        placeShips();
    }
    private void placeShips() {

    }
}
