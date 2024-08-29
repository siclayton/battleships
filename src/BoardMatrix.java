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
        Random rand = new Random();

        for (int i = 0; i < numShips; i++) {
            ORIENTATION orientation = rand.nextInt(2) == 0 ? ORIENTATION.VERTICAL : ORIENTATION.HORIZONTAL;
            int row = rand.nextInt(width);
            int col = rand.nextInt(height);
            boolean[] possiblePositions = findPossiblePositions(row, col, orientation);
        }
    }
    private boolean[] findPossiblePositions(int row, int col, ORIENTATION orientation) {
        boolean[] possiblePositions = new boolean[shipSize * 2 - 1];

        if (orientation == ORIENTATION.VERTICAL) {
            for (int i = row - (shipSize -1); i < row + shipSize; i++) {
                possiblePositions[i] = matrix[col][i];
            }
        } else {
            for (int i = col - (shipSize -1); i < col + shipSize; i++) {
                possiblePositions[i] = matrix[i][row];
            }
        }
        return possiblePositions;
    }
}
