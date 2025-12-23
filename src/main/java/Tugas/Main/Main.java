package Tugas.Main;

import Tugas.UI.Loginframe;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Loginframe().setVisible(true);
        });
    }
}
