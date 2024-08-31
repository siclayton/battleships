public class BattleshipsGame {
    private final int numShips; //The number of ships that will be placed on the board
    private final int shipSize; //The size of the ships that will be placed on the board

    private final int gridHeight; //The height of the grid that the game will be played on
    private final int gridWidth; //The width of the grid that the game will be played on

    //Objects needed to create the game
    private final BoardMatrix board;
    private final BattleshipsGrid grid;
//    private final BattleshipsFrame frame;

    private BattleshipsGame(BattleshipsGameBuilder builder) {
        if ((double) (builder.numShips * builder.shipSize) / (builder.gridHeight * builder.gridWidth) > 0.25) {
            throw new IllegalArgumentException("At least 75% of the grid must be water! This ensures the game isn't too easy");
        }

        //Assigning the values of these attributes from the builder
        this.numShips = builder.numShips;
        this.shipSize = builder.shipSize;
        this.gridHeight = builder.gridHeight;
        this.gridWidth = builder.gridWidth;

        //Instantiating the object attributes
        this.board = new BoardMatrix(numShips, shipSize, gridHeight, gridWidth);
        this.grid = new BattleshipsGrid(board, gridHeight, gridWidth);
//        this.frame = new BattleshipsFrame();
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