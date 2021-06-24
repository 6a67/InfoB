package gui.controller;

import gui.model.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MarkedFieldController extends MouseAdapter {

    private Spielfeld model;
    private CardLayout layout;
    private JPanel panel;

    public MarkedFieldController(Spielfeld model, CardLayout layout, JPanel panel) {
        this.model = model;
        this.layout = layout;
        this.panel = panel;
    }


    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            model.removeMarker();
            layout.show(panel, "EmptyButton");
        }
    }

}
