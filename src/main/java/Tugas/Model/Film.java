// java
package Tugas.Model;

import java.io.Serializable;

public class Film implements Serializable {
    private static final long serialVersionUID = 1L;

    private String judul;
    private String studio;
    private String jadwal;
    private String harga;

    public Film(String judul, String studio, String jadwal, String harga) {
        this.judul = judul;
        this.studio = studio;
        this.jadwal = jadwal;
        this.harga = harga;
    }

    public String getJudul() {
        return judul;
    }

    public String getStudio() {
        return studio;
    }

    public String getJadwal() {
        return jadwal;
    }

    public String getHarga() {
        return harga;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
