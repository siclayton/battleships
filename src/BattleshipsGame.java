public class BattleshipsGame {

    private BattleshipsGame(BattleshipsGameBuilder builder) {
        if ((double) (builder.numShips * builder.shipSize) / (builder.gridHeight * builder.gridWidth) > 0.25) {
            throw new IllegalArgumentException("At least 75% of the grid must be water! This ensures the game isn't too easy");
        }

        //Assigning the values of these attributes from the builder
        //The number of ships that will be placed on the board
        int numShips = builder.numShips;
        //The size of the ships that will be placed on the board
        int shipSize = builder.shipSize;
        //The height of the grid that the game will be played on
        int gridHeight = builder.gridHeight;
        //The width of the grid that the game will be played on
        int gridWidth = builder.gridWidth;

        //Instantiating the object attributes
        //Objects needed to create the game
        BoardMatrix board = new BoardMatrix(numShips, shipSize, gridHeight, gridWidth);
        BattleshipsGrid grid = new BattleshipsGrid(board, gridHeight, gridWidth);
        new BattleshipsFrame(grid, gridHeight, gridWidth);
    }

    //Builder class
    public static class BattleshipsGameBuilder {
        //All the int attributes are optional
        private int numShips;
        private int shipSize;

        private int gridHeight;
        private int gridWidth;

        public BattleshipsGameBuilder() {
            //This constructor assigns the default values to the variables
            numShips = 3;
            shipSize = 3;

            gridHeight = 7;
            gridWidth = 7;
        }
        public BattleshipsGameBuilder NumShips(int numShips) {
            this.numShips = numShips;
            return this;
        }
        public BattleshipsGameBuilder ShipSize(int shipSize) {
            this.shipSize = shipSize;
            return this;
        }
        public BattleshipsGameBuilder GridHeight(int gridHeight) {
            this.gridHeight = gridHeight;
            return this;
        }
        public BattleshipsGameBuilder GridWidth(int gridWidth) {
            this.gridWidth = gridWidth;
            return this;
        }
        public BattleshipsGame build() {
            return new BattleshipsGame(this);
        }
    }
}