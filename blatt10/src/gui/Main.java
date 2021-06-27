package gui;

import gui.model.Spielfeld;
import gui.view.SweepView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int x = 9;
        int y = 9;
        int bombs = 10;


        if(args.length != 0 && args.length != 3) {
            System.out.println("The program must be started with the width of the playing field, the height of the playing field and the number of bombs");
            return;
        }

        if(args.length != 0) {
            try {
                x = Integer.parseInt(args[0]);
                y = Integer.parseInt(args[1]);
                bombs = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("The input values are not valid integers");
                return;
            }
        }


        if(x * y <= bombs) {
            System.out.println("There must not be more bombs than fields");
            return;
        }

        JFrame frame = new JFrame("Sweep");
        frame.setResizable(false);
        Spielfeld model = new Spielfeld(x, y, bombs);
        SweepView sweepView = new SweepView(model);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(sweepView);
        frame.pack();
        frame.setVisible(true);
    }

}
