package satella.app.posyanduku.POSYANDU_PAGES.models;

public class Timbang {
    private String nama;
    private String tgl_timbang;
    private String tinggi_badan;
    private String berat_badan;
    private String linkar_kepala;

    public Timbang() {

    }

    public Timbang(String nama, String tgl_timbang, String tinggi_badan, String berat_badan, String linkar_kepala) {
        this.nama = nama;
        this.tgl_timbang = tgl_timbang;
        this.tinggi_badan = tinggi_badan;
        this.berat_badan = berat_badan;
        this.linkar_kepala = linkar_kepala;
    }

    public static void getName(String key) {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_timbang() {
        return tgl_timbang;
    }

    public void setTgl_timbang(String tgl_timbang) {
        this.tgl_timbang = tgl_timbang;
    }

    public String getTinggi_timbang() {
        return tinggi_badan;
    }

    public void setTinggi_timbang(String tinggi_timbang) {
        this.tinggi_badan = tinggi_timbang;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getLinkar_badan() {
        return linkar_kepala;
    }

    public void setLinkar_badan(String linkar_badan) {
        this.linkar_kepala = linkar_badan;
    }

    @Override
    public String toString() {
        return " " + nama +
                " " + tgl_timbang +
                " " + tinggi_badan +
                " " + berat_badan +
                " " + linkar_kepala;
    }
}
