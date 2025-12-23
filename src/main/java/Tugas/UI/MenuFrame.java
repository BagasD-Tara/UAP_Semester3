package Tugas.UI;

import Tugas.Model.Film;
import Tugas.Model.Penonton;
import Tugas.utill.validator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MenuFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // ===== DATA =====
    private ArrayList<Film> listFilm = new ArrayList<>();
    private ArrayList<Penonton> listPenonton = new ArrayList<>();

    // ===== TABLE MODEL =====
    private DefaultTableModel filmModel;
    private DefaultTableModel penontonModel;

    // ===== FORM FILM =====
    private JTextField txtJudul, txtStudio, txtHarga;
    private JComboBox<String> cbJadwal;

    // ===== FORM PENONTON =====
    private JTextField txtNamaPenonton;
    private JComboBox<String> cbFilmPenonton, cbJadwalPenonton;

    // ===== UPDATE FILM =====
    private JComboBox<String> cbUpdateFilm;
    private JTextField txtUpdateJadwal;
    private JTextField txtUpdateHarga;

    // ===== DELETE COMBOS (made class-level so they can be refreshed) =====
    private JComboBox<String> cbDeleteFilm;
    private JComboBox<String> cbDeletePenonton;

    public MenuFrame() {
        setTitle("Menu Pemesanan Tiket Bioskop");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(panelMenuUtama(), "MENU");
        mainPanel.add(panelPilihKursi(), "KURSI");
        mainPanel.add(panelSukses(), "SUKSES");

        add(mainPanel);
        setVisible(true);
    }


    // ================= PANEL =================


    private JPanel panelMenuUtama() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Data Film", panelDataFilm());
        tabbedPane.addTab("Data Penonton", panelDataPenonton());
        tabbedPane.addTab("Tambah Film", panelTambahFilm());
        tabbedPane.addTab("Update Film", panelUpdateFilm());
        tabbedPane.addTab("Delete Film", panelDeleteFilm());
        tabbedPane.addTab("Tambah Penonton", panelTambahPenonton());
        tabbedPane.addTab("Hapus Penonton", panelHapusPenonton());

        JButton btnPesan = new JButton("Pesan Tiket");
        btnPesan.addActionListener(e ->
                cardLayout.show(mainPanel, "KURSI")
        );

        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(btnPesan, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel panelPilihKursi() {
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Pilih Kursi Anda"));

        JButton[] kursi = new JButton[12];

        for (int i = 0; i < kursi.length; i++) {
            int nomor = i + 1;
            kursi[i] = new JButton("Kursi " + nomor);

            kursi[i].addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "Kursi " + nomor + " dipilih");
                cardLayout.show(mainPanel, "SUKSES");
            });

            panel.add(kursi[i]);
        }

        return panel;
    }

    private JPanel panelSukses() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel lbl = new JLabel(
                "🎉 SUKSES! KURSI BERHASIL DIPESAN 🎉",
                SwingConstants.CENTER
        );
        lbl.setFont(new Font("Arial", Font.BOLD, 22));

        JButton btnKembali = new JButton("Kembali ke Menu");
        btnKembali.addActionListener(e ->
                cardLayout.show(mainPanel, "MENU")
        );

        panel.add(lbl, BorderLayout.CENTER);
        panel.add(btnKembali, BorderLayout.SOUTH);

        return panel;
    }


    private JPanel panelDataFilm() {
        JPanel panel = new JPanel(new BorderLayout());
        filmModel = new DefaultTableModel(new String[]{"Judul", "Studio", "Jadwal", "Harga"}, 0);
        JTable table = new JTable(filmModel);
        panel.add(new JScrollPane(table));
        return panel;
    }


    private JPanel panelDataPenonton() {
        JPanel panel = new JPanel(new BorderLayout());
        penontonModel = new DefaultTableModel(new String[]{"Nama", "Film", "Jadwal"}, 0);
        JTable table = new JTable(penontonModel);
        panel.add(new JScrollPane(table));
        return panel;
    }


    private JPanel panelTambahFilm() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Tambah Film"));


        txtJudul = new JTextField();
        txtStudio = new JTextField();
        txtHarga = new JTextField();
        cbJadwal = new JComboBox<>(new String[]{"10:00", "13:00", "16:00", "19:00"});
        JButton btnTambah = new JButton("Tambah Film");


        panel.add(new JLabel("Judul Film")); panel.add(txtJudul);
        panel.add(new JLabel("Studio")); panel.add(txtStudio);
        panel.add(new JLabel("Jadwal")); panel.add(cbJadwal);
        panel.add(new JLabel("Harga")); panel.add(txtHarga);
        panel.add(new JLabel()); panel.add(btnTambah);


        btnTambah.addActionListener(e -> tambahFilm());
        return panel;
    }

    private JPanel panelUpdateFilm() {

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Update Film"));

        cbUpdateFilm = new JComboBox<>();
        txtUpdateJadwal = new JTextField();
        txtUpdateHarga = new JTextField();

        JButton btnUpdate = new JButton("Update Film");

        panel.add(new JLabel("Pilih Film"));
        panel.add(cbUpdateFilm);

        panel.add(new JLabel("Jadwal Baru"));
        panel.add(txtUpdateJadwal);

        panel.add(new JLabel("Harga Baru"));
        panel.add(txtUpdateHarga);

        panel.add(new JLabel());
        panel.add(btnUpdate);

        // isi combo saat panel dibuat
        refreshComboFilm(cbUpdateFilm);

        // saat film dipilih → isi otomatis
        cbUpdateFilm.addActionListener(e -> loadSelectedFilm());

        btnUpdate.addActionListener(e -> updateFilm());

        return panel;
    }

    private JPanel panelDeleteFilm() {
        JPanel panel = new JPanel(new FlowLayout());
        cbDeleteFilm = new JComboBox<>();
        JButton btnHapus = new JButton("Hapus Film");


        panel.add(new JLabel("Pilih Film"));
        panel.add(cbDeleteFilm);
        panel.add(btnHapus);


        btnHapus.addActionListener(e -> {
            int index = cbDeleteFilm.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Pilih film terlebih dahulu");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin menghapus film ini?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // remove film
                listFilm.remove(index);
                //sekalian remove penonton yang nonton film itu
                listPenonton.removeIf(p -> p.getJudulFilm().equals(cbDeleteFilm.getItemAt(index)));
                refreshFilmTable();
                refreshComboFilm();
                refreshPenontonTable();
                refreshComboPenonton();
                JOptionPane.showMessageDialog(this, "Film berhasil dihapus");
            }
        });
        refreshComboFilm(cbDeleteFilm);
        return panel;
    }

    private JPanel panelTambahPenonton() {

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Tambah Penonton"));

        txtNamaPenonton = new JTextField();
        cbFilmPenonton = new JComboBox<>();
        cbJadwalPenonton = new JComboBox<>(new String[]{
                "10:00", "13:00", "16:00", "19:00"
        });

        JButton btnTambah = new JButton("Tambah Penonton");

        panel.add(new JLabel("Nama Penonton"));
        panel.add(txtNamaPenonton);

        panel.add(new JLabel("Pilih Film"));
        panel.add(cbFilmPenonton);

        panel.add(new JLabel("Pilih Jadwal"));
        panel.add(cbJadwalPenonton);

        panel.add(new JLabel());
        panel.add(btnTambah);

        btnTambah.addActionListener(e -> tambahPenonton());

        refreshComboFilm(cbFilmPenonton);

        return panel;
    }

    private JPanel panelHapusPenonton() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Hapus Penonton"));

        cbDeletePenonton = new JComboBox<>();
        JButton btnHapus = new JButton("Hapus Penonton");

        panel.add(new JLabel("Pilih Penonton"));
        panel.add(cbDeletePenonton);
        panel.add(btnHapus);

        // isi combo saat panel dibuat
        refreshComboPenonton(cbDeletePenonton);

        btnHapus.addActionListener(e -> {
            int index = cbDeletePenonton.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Pilih penonton terlebih dahulu");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Yakin ingin menghapus penonton ini?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                listPenonton.remove(index);
                refreshPenontonTable();
                refreshComboPenonton();
                JOptionPane.showMessageDialog(this, "Penonton berhasil dihapus");
            }
        });

        return panel;
    }


    // ================= LOGIC =================


    private void tambahFilm() {
        if (!validator.required(txtJudul, "Judul Film", this)) return;
        if (!validator.required(txtStudio, "Studio", this)) return;
        if (!validator.required(txtHarga, "Harga", this)) return;


        Film film = new Film(txtJudul.getText(), txtStudio.getText(), cbJadwal.getSelectedItem().toString(), txtHarga.getText());
        listFilm.add(film);
        refreshFilmTable();
        refreshComboFilm();
    }

    private void updateFilm() {

        int index = cbUpdateFilm.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Pilih film terlebih dahulu");
            return;
        }

        if (!validator.required(txtUpdateJadwal, "Jadwal", this)) return;
        if (!validator.required(txtUpdateHarga, "Harga", this)) return;

        Film film = listFilm.get(index);

        film.setJadwal(txtUpdateJadwal.getText());
        film.setHarga(txtUpdateHarga.getText());

        refreshFilmTable();
        refreshComboFilm(cbUpdateFilm);

        JOptionPane.showMessageDialog(this, "Data film berhasil diperbarui");
    }

    private void loadDummyFilm() {
        listFilm.add(new Film("Interstellar", "Studio 1", "13:00", "50000"));
        listFilm.add(new Film("Avengers", "Studio 2", "16:00", "55000"));
        refreshFilmTable();
    }


    private void loadDummyPenonton() {
        listPenonton.add(new Penonton("Budi", "Interstellar", "13:00"));
        listPenonton.add(new Penonton("Siti", "Avengers", "16:00"));
        refreshPenontonTable();
    }

    private void loadSelectedFilm() {
        int index = cbUpdateFilm.getSelectedIndex();
        if (index < 0) return;

        Film film = listFilm.get(index);

        txtUpdateJadwal.setText(film.getJadwal());
        txtUpdateHarga.setText(film.getHarga());
    }

    private void refreshFilmTable() {
        filmModel.setRowCount(0);
        for (Film f : listFilm) {
            filmModel.addRow(new Object[]{f.getJudul(), f.getStudio(), f.getJadwal(), f.getHarga()});
        }
    }


    private void refreshPenontonTable() {
        penontonModel.setRowCount(0);
        for (Penonton p : listPenonton) {
            penontonModel.addRow(new Object[]{p.getNama(), p.getJudulFilm(), p.getJadwal()});
        }
    }


    private void refreshComboFilm() {
        if (cbUpdateFilm != null) {
            refreshComboFilm(cbUpdateFilm);
        }
        if (cbFilmPenonton != null) {
            refreshComboFilm(cbFilmPenonton);
        }
        if (cbDeleteFilm != null) {
            refreshComboFilm(cbDeleteFilm);
        }
    }
    private void refreshComboFilm(JComboBox<String> combo) {
        combo.removeAllItems();
        for (Film f : listFilm) {
            combo.addItem(f.getJudul());
        }
        if (combo.getItemCount() > 0) combo.setSelectedIndex(0);
    }

    private void tambahPenonton() {

        if (!validator.required(txtNamaPenonton, "Nama Penonton", this)) return;

        if (cbFilmPenonton.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Belum ada film tersedia");
            return;
        }

        Penonton penonton = new Penonton(
                txtNamaPenonton.getText(),
                cbFilmPenonton.getSelectedItem().toString(),
                cbJadwalPenonton.getSelectedItem().toString()
        );

        listPenonton.add(penonton);

        penontonModel.addRow(new Object[]{
                penonton.getNama(),
                penonton.getJudulFilm(),
                penonton.getJadwal()
        });

        txtNamaPenonton.setText("");
        cbJadwalPenonton.setSelectedIndex(0);

        refreshComboPenonton();

        JOptionPane.showMessageDialog(this, "Penonton berhasil ditambahkan");
    }

    private void refreshComboPenonton(JComboBox<String> combo) {
        combo.removeAllItems();
        for (Penonton p : listPenonton) {
            combo.addItem(p.getNama() + " - " + p.getJudulFilm());
        }
    }

    private void refreshComboPenonton() {
        if (cbDeletePenonton != null) {
            refreshComboPenonton(cbDeletePenonton);
        }
    }

}
