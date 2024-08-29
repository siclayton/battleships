import java.util.Random;

public class BoardMatrix {
    private final boolean[][] matrix; //A boolean array representing the position of ships on the board. True represents a ship, false represents water

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
            ORIENTATION orientation;
            int row;
            int col;
            boolean[] possiblePositions;
            //Randomly generate the position and orientation of the ship until a position with enough room around it to place a ship in the given orientation is generated
            do {
                orientation = rand.nextInt(2) == 0 ? ORIENTATION.VERTICAL : ORIENTATION.HORIZONTAL;
                row = rand.nextInt(height);
                col = rand.nextInt(width);
                possiblePositions = findPossiblePositions(row, col, orientation);
            } while (matrix[row][col] || !hasSpaceForShip(possiblePositions));

            placeShip(row, col, orientation, possiblePositions);
        }
    }
    private boolean[] findPossiblePositions(int row, int col, ORIENTATION orientation) {
        boolean[] possiblePositions = new boolean[shipSize * 2 - 1];

        if (orientation == ORIENTATION.HORIZONTAL) {
            for (int i = col - (shipSize -1); i < col + shipSize; i++) {
                if (col < 0 || col >= width) {
                    possiblePositions[i] = true;
                }
                possiblePositions[i] = matrix[row][i];
            }
        } else {
            for (int i = row - (shipSize -1); i < row + shipSize; i++) {
                if (row < 0 || row >= height) {
                    possiblePositions[i] = true;
                }
                possiblePositions[i] = matrix[i][col];
            }
        }
        return possiblePositions;
    }
    private boolean hasSpaceForShip(boolean[] positions) {
        for (int i = 0; i <= positions.length - shipSize; i++) {
            boolean consecutiveFalses = true;

            for (int j = 0; j < shipSize; j++) {
                if (positions[i + j]) { // Check if the current element contains a ship, breaking if it does
                    consecutiveFalses = false;
                    break;
                }
            }
            if (consecutiveFalses) {
                return true; // Return true if there are 'shipSize' spaces to put the ship
            }
        }
        return false;
    }
    private void placeShip(int row, int col, ORIENTATION orientation, boolean[] positions) {
        int[] shipSpaceIndexes = findSpaceForShip(positions);

        if (shipSpaceIndexes == null) {
            return;
        }

        if (orientation == ORIENTATION.HORIZONTAL) {
            col = (col - (shipSize - 1)) + shipSpaceIndexes[0];

            for (int i = 0; i < shipSize; i++, col++) {
                matrix[row][col] = true;
            }
        } else {
            row = (row - (shipSize - 1)) + shipSpaceIndexes[0];

            for (int i = 0; i < shipSize; i++, row++) {
                matrix[row][col] = true;
            }
        }
    }
    private int[] findSpaceForShip (boolean[] positions) {
        for (int i = 0; i <= positions.length - shipSize; i++) {
            boolean consecutiveFalses = true;

            for (int j = 0; j < shipSize; j++) {
                if (positions[i + j]) { // Check if the current element contains a ship, breaking if it does
                    consecutiveFalses = false;
                    break;
                }
            }
            if (consecutiveFalses) {
                return new int[] {i, i + shipSize - 1}; // Return true if there are 'shipSize' spaces to put the ship
            }
        }
        return null;
    }
}