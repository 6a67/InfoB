package gui.view;

import gui.controller.EmptyFieldController;
import gui.controller.MarkedFieldController;
import gui.model.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class SweepView extends JPanel implements Observer {

    private Spielfeld model;
    private JLabel bombsLabel;
    private JPanel minefield;
    private JPanel[][] fieldGrid;
    private JLabel[][] values;


    public SweepView(Spielfeld model) {
        this.model = model;
        this.model.addObserver(this);
        int x = model.getWidth();
        int y = model.getHeight();
        this.setLayout(new BorderLayout());
        this.bombsLabel = new JLabel("Bombs: " + model.getMarkers() + "/" + model.getBombs());
        this.bombsLabel.setFont(bombsLabel.getFont().deriveFont(16.0f));
        this.add(bombsLabel);

        this.minefield = new JPanel();
        GridLayout grid = new GridLayout(x, y, 0, 0);
        this.minefield.setLayout(grid);
        this.add(minefield, BorderLayout.SOUTH);

        fieldGrid = new JPanel[x][y];
        values = new JLabel[x][y];

        for(int j = 0; j < y; j++) {
            for(int i = 0; i < x; i++) {
                fieldGrid[i][j] = new JPanel();
                fieldGrid[i][j].setLayout(new CardLayout());

                values[i][j] = new JLabel(String.valueOf(model.getField(i, j)), SwingConstants.CENTER);
                values[i][j].setFont(values[i][j].getFont().deriveFont(20.0f));
                fieldGrid[i][j].add(values[i][j], "Field");

                JButton jb1 = new JButton();
                jb1.setPreferredSize(new Dimension(40,40));
                jb1.addMouseListener(new EmptyFieldController(model, fieldGrid[i][j], i, j));
                fieldGrid[i][j].add(jb1, "EmptyButton");

                JButton jb2 = new JButton("!");
                jb2.setPreferredSize(new Dimension(40,40));
                jb2.addMouseListener(new MarkedFieldController(model, fieldGrid[i][j]));
                jb2.setVisible(true);
                fieldGrid[i][j].add(jb2, "MineButton");

                ((CardLayout) fieldGrid[i][j].getLayout()).show(fieldGrid[i][j], "EmptyButton");
                minefield.add(fieldGrid[i][j]);
            }
        }


    }

    @Override
    public void update(Observable observable, Object o) {
        // Refreshes the field
        bombsLabel.setText("Bombs: " + model.getMarkers() + "/" + model.getBombs());
        for(int j = 0; j < ((GridLayout)minefield.getLayout()).getColumns(); j++) {
            for(int i = 0; i < ((GridLayout)minefield.getLayout()).getRows(); i++) {
                if(!model.isCovered(i, j)) {
                    ((CardLayout) fieldGrid[i][j].getLayout()).show(fieldGrid[i][j], "Field");
                }
            }
        }

        if(model.isOver()) {
            if(model.isWon()) {
                // Popup dialog if the game is won
                if(JOptionPane.showConfirmDialog(null, "You won. Do you want to generate a new game?", "Game ended", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    model.generateGame();
                    for(int i = 0; i < fieldGrid.length; i++) {
                        for(int j = 0; j < fieldGrid[0].length; j++) {
                            ((CardLayout) fieldGrid[i][j].getLayout()).show(fieldGrid[i][j], "EmptyButton");
                            values[i][j].setText(String.valueOf(model.getField(i, j)));
                        }
                    }
                } else {
                    System.exit(0);
                }

            } else {
                // Popup dialog if the game is lost
                if(JOptionPane.showConfirmDialog(null, "You lost. Do you want to restart the game?", "Game ended", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    model.restart();
                    for(int i = 0; i < fieldGrid.length; i++) {
                        for(int j = 0; j < fieldGrid[0].length; j++) {
                            ((CardLayout) fieldGrid[i][j].getLayout()).show(fieldGrid[i][j], "EmptyButton");
                        }
                    }
                } else {
                    System.exit(0);
                }

            }

        }

    }
}
