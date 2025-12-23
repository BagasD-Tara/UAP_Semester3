package Tugas.Model;

public class Penonton {
    private String nama;
    private String judulFilm;
    private String jadwal;

    public Penonton(String nama, String judulFilm, String jadwal) {
        this.nama = nama;
        this.judulFilm = judulFilm;
        this.jadwal = jadwal;
    }

    public String getNama() {
        return nama;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJudulFilm(String judulFilm) {
        this.judulFilm = judulFilm;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }
}
