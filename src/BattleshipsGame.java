public class BattleshipsGame {
    private int numShips; //The number of ships that will be placed on the board
    private int shipSize; //The size of the ships that will be placed on the board

    private int gridHeight; //The height of the grid that the game will be played on
    private int gridWidth; //The width of the grid that the game will be played on

    //Objects needed to create the game
    private final BoardMatrix board;
    private final BattleshipsGrid grid;
    private final BattleshipsFrame frame;

    private BattleshipsGame(BattleshipsGameBuilder builder) {
        //Assigning the values of these attributes from the builder
        numShips = builder.numShips;
        shipSize = builder.shipSize;
        gridHeight = builder.gridHeight;
        gridWidth = builder.gridWidth;

        //Instantiating the object attributes
        board = new BoardMatrix();
        grid = new BattleshipsGrid();
        frame = new BattleshipsFrame();
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
        public BattleshipsGameBuilder setNumShips(int numShips) {
            this.numShips = numShips;
            return this;
        }
        public BattleshipsGameBuilder setShipSize(int shipSize) {
            this.shipSize = shipSize;
            return this;
        }
        public BattleshipsGameBuilder setGridHeight(int gridHeight) {
            this.gridHeight = gridHeight;
            return this;
        }
        public BattleshipsGameBuilder setGridWidth(int gridWidth) {
            this.gridWidth = gridWidth;
            return this;
        }
        public BattleshipsGame build() {
            return new BattleshipsGame(this);
        }
    }
}