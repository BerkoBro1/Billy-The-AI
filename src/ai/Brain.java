package ai;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Brain {

    //test inputs
    double[][] X;

    public NeuralNetwork nn;

    BufferedReader br;


    //Outputs:
    // 0 : Up-Right
    // 1 : Right
    double[][] Y;

    private double[] visibleTiles;
    private AIVision aiVision;

    public Brain() throws IOException {

        int dataLines = 20;

        br = new BufferedReader(new FileReader("src/ai/data.txt"));
        X = new double[dataLines][165];
        Y = new double[dataLines][1];
        double[][] inputArray = new double[dataLines][166];
        String line;
        int i = 0;
        while((line = br.readLine()) != null) {
            int j = 0;
            for (String s : line.split(" ")) {
                inputArray[i][j] = Double.parseDouble(s);
                j++;
            }
            for(j = 0; j < 165; j++) {
                X[i][j] = inputArray[i][j];
            }
            Y[i][0] = inputArray[i][165];
            i++;
        }

        nn = new NeuralNetwork(165, 50, 1);
        nn.fit(X, Y, 500);

        JFrame frame = new JFrame("AI Vision");
        aiVision = new AIVision();
        frame.add(aiVision);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(220, 300);
    }

    public void fixVisibleTiles(int[][] data) {

        //15 tall 11 far

        visibleTiles = new double[15*11];

        System.out.println(data[0].length/2);
        double[][] cutData = new double[15][11];
        int xLeft = 10;
        int yTop = 0;
        int ic = 0;
        int jc = 0;
        for(int i = yTop; i < data.length-2; i++) {
            for(int j = xLeft; j < 21; j++) {
                cutData[ic][jc] = data[i][j] != 0 ? 1 : 0;
                jc++;
            }
            jc = 0;
            ic++;
        }

        int i = 0;
        for(double[] j : cutData) {
            for(double k : j) {
                visibleTiles[i] = k;
                i++;
            }
        }


    }

    public double getOutput() {
        aiVision.updateImage(visibleTiles);
        return nn.predict(visibleTiles).get(0);
    }

    public void printVision() {
        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < 11; j++) {
                System.out.print(visibleTiles[i*j] + " ");
            }
            System.out.println(); //optional - remove if collecting data
        }
    }

}