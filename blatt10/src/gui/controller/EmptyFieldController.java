package gui.controller;

import gui.model.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmptyFieldController extends MouseAdapter {

    private Spielfeld model;
    private JPanel panel;
    private int x;
    private int y;

    public EmptyFieldController(Spielfeld model, JPanel panel, int x, int y) {
        this.model = model;
        this.panel = panel;
        this.x = x;
        this.y = y;
    }


    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                ((CardLayout)panel.getLayout()).show(panel, "Field");
                model.uncover(x, y);
                break;
            case MouseEvent.BUTTON3:
                model.addMarker();
                ((CardLayout)panel.getLayout()).show(panel, "MineButton");
                break;
        }

    }

}
