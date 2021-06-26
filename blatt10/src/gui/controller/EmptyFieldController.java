package gui.controller;

import gui.model.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmptyFieldController extends MouseAdapter {

    private Spielfeld model;
    private CardLayout layout;
    private JPanel panel;
    private int x;
    private int y;

    public EmptyFieldController(Spielfeld model, CardLayout layout, JPanel panel, int x, int y) {
        this.model = model;
        this.layout = layout;
        this.panel = panel;
        this.x = x;
        this.y = y;
    }


    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                layout.show(panel, "Field");
                model.uncover(x, y);
                break;
            case MouseEvent.BUTTON3:
                model.addMarker();
                layout.show(panel, "MineButton");
                break;
        }

    }

}
