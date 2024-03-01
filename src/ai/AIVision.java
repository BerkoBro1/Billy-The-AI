package ai;

import javax.swing.*;
import java.awt.*;

public class AIVision extends JPanel {

    public JLabel[][] tiles;

    public AIVision() {

        setLayout(new GridLayout(15, 11));

        tiles = new JLabel[15][11];
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 11; j++) {
                tiles[i][j] = new JLabel();
                tiles[i][j].setOpaque(true);
                add(tiles[i][j]);
            }
        }
    }

    public void updateImage(double[] data) {
        int i = 0;
        for(double d : data) {
            if (d == 0) tiles[i / 11][i % 11].setBackground(Color.WHITE);
            else tiles[i / 11][i % 11].setBackground(Color.BLACK);
            i++;
        }
    }

}
