import javax.swing.*;

public class BattleshipTile extends JButton {
    boolean isSunk;

    public BattleshipTile() {
        isSunk = false;
    }
    public boolean isSunk() {
        return isSunk;
    }
}