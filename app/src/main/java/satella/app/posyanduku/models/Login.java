package satella.app.posyanduku.models;

public class Login {
    private String id_posyandu;
    private String nama_posyandu;
    private String ketua;
    private String noTelp;
    private String rw;
    private String kelurahan;
    private String kecamatan;
    private String kota;
    private String provinsi;

    public Login() {

    }

    public Login(String id_posyandu, String nama_posyandu, String ketua, String noTelp, String rw, String kelurahan, String kecamatan, String kota, String provinsi) {
        this.id_posyandu = id_posyandu;
        this.nama_posyandu = nama_posyandu;
        this.ketua = ketua;
        this.noTelp = noTelp;
        this.rw = rw;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
        this.kota = kota;
        this.provinsi = provinsi;
    }

    public String getId_posyandu() {
        return id_posyandu;
    }

    public void setId_posyandu(String id_posyandu) {
        this.id_posyandu = id_posyandu;
    }

    public String getNama_posyandu() {
        return nama_posyandu;
    }

    public void setNama_posyandu(String nama_posyandu) {
        this.nama_posyandu = nama_posyandu;
    }

    public String getKetua() {
        return ketua;
    }

    public void setKetua(String ketua) {
        this.ketua = ketua;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    @Override
    public String toString() {
        return " " + id_posyandu +
                " " + nama_posyandu +
                " " + ketua +
                " " + noTelp +
                " " + rw +
                " " + kelurahan +
                " " + kecamatan +
                " " + kota +
                " " + provinsi;
    }
}
