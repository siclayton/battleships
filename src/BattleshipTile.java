import javax.swing.*;

public class BattleshipTile extends JButton {
    private final boolean isSunk;

    public BattleshipTile() {
        isSunk = false;
    }
    public boolean isSunk() {
        return isSunk;
    }
}