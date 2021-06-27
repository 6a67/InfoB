package gui.controller;

import gui.model.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MarkedFieldController extends MouseAdapter {

    private Spielfeld model;
    private JPanel panel;

    public MarkedFieldController(Spielfeld model, JPanel panel) {
        this.model = model;
        this.panel = panel;
    }


    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            model.removeMarker();
            ((CardLayout)panel.getLayout()).show(panel, "EmptyButton");
        }
    }

}
