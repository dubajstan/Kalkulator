package Model.Main;

import GUI.KalkulatorGUI;

import javax.swing.*;

public class Kalkulator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KalkulatorGUI().setVisible(true);
            }
        });
    }

}
