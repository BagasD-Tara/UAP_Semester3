package Tugas.utill;

import javax.swing.*;
import java.awt.*;

public class validator {
    public static boolean required(JTextField field, String fieldName, Component parent) {
        if (field.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    parent,
                    fieldName + " belum terisi",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            field.requestFocus();
            return false;
        }
        return true;
    }

    // Validasi JPasswordField
    public static boolean required(JPasswordField field, String fieldName, Component parent) {
        if (field.getPassword().length == 0) {
            JOptionPane.showMessageDialog(
                    parent,
                    fieldName + " belum terisi",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            field.requestFocus();
            return false;
        }
        return true;
    }
}
