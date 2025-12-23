package Tugas.UI;

import javax.swing.*;
import java.awt.*;
import Tugas.utill.validator;

public class Loginframe extends JFrame{
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public Loginframe() {
        setTitle("Login Bioskop");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== BACKGROUND PANEL =====
        BackgroundPanel bgPanel =
                new BackgroundPanel("C:\\Users\\MSi\\IdeaProjects\\gambarbagus.jpeg");
        bgPanel.setLayout(new GridBagLayout());
        setContentPane(bgPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblTitle = new JLabel("LOGIN BIOSKOP");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(Color.WHITE);

        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(Color.WHITE);

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);

        JButton btnLogin = new JButton("Login");

        // ===== LAYOUT =====
        //ukuran title login bioskop
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        bgPanel.add(lblTitle, gbc);

        //ukuran username
        gbc.gridwidth = 1;
        gbc.gridy++;
        bgPanel.add(lblUser, gbc);

        gbc.gridx = 1;
        bgPanel.add(txtUsername, gbc);

        //ukuran password
        gbc.gridx = 0;
        gbc.gridy++;
        bgPanel.add(lblPass, gbc);

        gbc.gridx = 1;
        bgPanel.add(txtPassword, gbc);

        //button login
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        bgPanel.add(btnLogin, gbc);

        // ===== AKSI LOGIN =====
        btnLogin.addActionListener(e -> login());
    }

    private void login() {
        if (!validator.required(txtUsername, "Username", this)) return;
        if (!validator.required(txtPassword, "Password", this)) return;

        // Dummy login
        if (txtUsername.getText().equals("admin")
                && String.valueOf(txtPassword.getPassword()).equals("admin")) {

            JOptionPane.showMessageDialog(this, "Login Berhasil");
            new MenuFrame().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username / Password salah");
        }
    }
}
