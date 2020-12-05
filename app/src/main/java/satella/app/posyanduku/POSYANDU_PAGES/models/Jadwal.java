package satella.app.posyanduku.POSYANDU_PAGES.models;

public class Jadwal {
    private String bulan;
    private String tanggal;

    public Jadwal() {

    }

    public Jadwal(String bulan, String tanggal) {
        this.bulan = bulan;
        this.tanggal = tanggal;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public void getBulan(String key) {
    }
}
