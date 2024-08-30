import java.util.Random;

public class BoardMatrix {
    private final boolean[][] matrix; //A boolean array representing the position of ships on the board. True represents a ship, false represents water

    private final int numShips; //The number of ships that will be placed on the board
    private final int shipSize; //The size of the ships that will be placed on the board

    private final int height; //The height of the grid that the game will be played on
    private final int width; //The width of the grid that the game will be played on

    public BoardMatrix(int numShips, int shipSize, int height, int width) {
        this.numShips = numShips;
        this.shipSize = shipSize;
        this.height = height;
        this.width = width;

        matrix = new boolean[height][width];

        placeShips();

        System.out.println(this);
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

        int currentPosition = (orientation == ORIENTATION.HORIZONTAL ? col : row) - (shipSize - 1);
        int lastPosition = (orientation == ORIENTATION.HORIZONTAL ? col : row) + shipSize;
        int gridSize = (orientation == ORIENTATION.HORIZONTAL ? width : height);

        for (int i = 0; currentPosition < lastPosition; i++, currentPosition++) {
            if (currentPosition < 0 || currentPosition >= gridSize) {
                possiblePositions[i] = true;
            } else {
                int currentRow = orientation == ORIENTATION.HORIZONTAL ? row : currentPosition;
                int currentCol = orientation == ORIENTATION.HORIZONTAL ? currentPosition : col;
                possiblePositions[i] = matrix[currentRow][currentCol];
            }
        }
        return possiblePositions;
    }
    public boolean hasSpaceForShip(boolean[] positions) {
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
        int shipSpaceOffset = findSpaceForShip(positions);

        if (shipSpaceOffset == -1) {
            return;
        }

        if (orientation == ORIENTATION.HORIZONTAL) {
            col = (col - (shipSize - 1)) + shipSpaceOffset;

            for (int i = 0; i < shipSize; i++, col++) {
                matrix[row][col] = true;
            }
        } else {
            row = (row - (shipSize - 1)) + shipSpaceOffset;

            for (int i = 0; i < shipSize; i++, row++) {
                matrix[row][col] = true;
            }
        }
    }
    private int findSpaceForShip (boolean[] positions) {
        for (int i = 0; i <= positions.length - shipSize; i++) {
            boolean consecutiveFalses = true;

            for (int j = 0; j < shipSize; j++) {
                if (positions[i + j]) { // Check if the current element contains a ship, breaking if it does
                    consecutiveFalses = false;
                    break;
                }
            }
            if (consecutiveFalses) {
                return i; // Return true if there are 'shipSize' spaces to put the ship
            }
        }
        return -1;
    }
    @Override
    public String toString() {
        /*
            Print the matrix in a more easily readable format
            X's represent positions that contain a ship
            O's represent positions that contain water
        */

        StringBuilder output = new StringBuilder();
        boolean first = true;
        for (boolean[] array : matrix) {
            if (first)
                first = false;
            else
                output.append("\n");

            for (boolean value : array) {
                if (value)
                    output.append("X");
                else
                    output.append("O");
            }
        }
        return output.toString();
    }
}