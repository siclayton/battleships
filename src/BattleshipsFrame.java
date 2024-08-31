import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipsFrame extends JFrame implements ActionListener {
    private final JFrame frame;
    private JPanel centrePanel;
    private JLabel guessCounter;

    BattleshipsGrid battleshipsGrid;

    int gridHeight;
    int gridWidth;

    int guesses;

    public BattleshipsFrame(BattleshipsGrid battleshipsGrid, int gridHeight, int gridWidth) {
        this.battleshipsGrid = battleshipsGrid;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;

        guesses = 0;

        frame = new JFrame("Battleships");
        setUpFrame();
    }
    private void setUpFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        guessCounter = new JLabel("Guesses = 0");
        northPanel.add(guessCounter);
        frame.add(northPanel, BorderLayout.NORTH);

        centrePanel = new JPanel(new GridLayout(gridHeight, gridWidth));
        frame.add(centrePanel, BorderLayout.CENTER);
        addTilesToCentrePanel();

        frame.setVisible(true);
    }
    private void addTilesToCentrePanel() {
        JButton[][] grid = battleshipsGrid.getGrid();

        for (JButton[] array : grid) {
            for (JButton button : array) {
                button.addActionListener(this);
                centrePanel.add(button);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton pressed = (JButton) actionEvent.getSource();

        if (pressed instanceof BattleshipTile b) {
            if (!b.isSunk()) {
                guesses++;
                b.setBackground(Color.GREEN);
            }
        } else {
            guesses++;
            pressed.setBackground(Color.RED);
        }

        guessCounter.setText("Guesses = " + guesses);
    }
}
