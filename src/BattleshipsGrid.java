import javax.swing.*;

public class BattleshipsGrid {
    JButton[][] grid;
    int height;
    int width;
    BoardMatrix boardMatrix;

    public BattleshipsGrid(BoardMatrix boardMatrix, int height, int width) {
        this.boardMatrix = boardMatrix;
        this.height = height;
        this.width = width;

        grid = new JButton[height][width];

        populateGrid();

        System.out.println(this);
    }
    private void populateGrid() {
        boolean[][] matrix = boardMatrix.getMatrix();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix[y][x])
                    grid[y][x] = new BattleshipTile();
                else
                    grid[y][x] = new JButton();
            }
        }
    }

    public JButton[][] getGrid() {
        return grid;
    }
}
