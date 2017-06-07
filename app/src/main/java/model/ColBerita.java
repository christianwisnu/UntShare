package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by christian on 18/05/17.
 */

public class ColBerita implements Parcelable {

    private Integer idBerita;
    private String judul;
    private String gambar;
    private String isiBerita;
    private String tglTerbit;
    private String tglKadaluarsa;
    private String statusBerita;
    private Integer idJurusan;
    private String namaJurusan;
    private Integer idKategori;
    private String namaKategori;
    private String penulis;

    public ColBerita(){}

    public Integer getIdBerita() {
        return idBerita;
    }

    public void setIdBerita(Integer idBerita) {
        this.idBerita = idBerita;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    public String getTglTerbit() {
        return tglTerbit;
    }

    public void setTglTerbit(String tglTerbit) {
        this.tglTerbit = tglTerbit;
    }

    public String getTglKadaluarsa() {
        return tglKadaluarsa;
    }

    public void setTglKadaluarsa(String tglKadaluarsa) {
        this.tglKadaluarsa = tglKadaluarsa;
    }

    public String getStatusBerita() {
        return statusBerita;
    }

    public void setStatusBerita(String statusBerita) {
        this.statusBerita = statusBerita;
    }

    public Integer getIdJurusan() {
        return idJurusan;
    }

    public void setIdJurusan(Integer idJurusan) {
        this.idJurusan = idJurusan;
    }

    public String getNamaJurusan() {
        return namaJurusan;
    }

    public void setNamaJurusan(String namaJurusan) {
        this.namaJurusan = namaJurusan;
    }

    public Integer getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Integer idKategori) {
        this.idKategori = idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    protected ColBerita(Parcel in) {
        idBerita = in.readInt();
        judul = in.readString();
        gambar = in.readString();
        isiBerita = in.readString();
        tglTerbit = in.readString();
        tglKadaluarsa = in.readString();
        statusBerita = in.readString();
        idJurusan = in.readInt();
        idKategori = in.readInt();
        namaJurusan = in.readString();
        namaKategori = in.readString();
    }

    public static final Creator<ColBerita> CREATOR = new Creator<ColBerita>() {
        @Override
        public ColBerita createFromParcel(Parcel in) {
            return new ColBerita(in);
        }

        @Override
        public ColBerita[] newArray(int size) {
            return new ColBerita[size];
        }
    };

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idBerita);
        dest.writeString(judul);
        dest.writeString(gambar);
        dest.writeString(isiBerita);
        dest.writeString(tglTerbit);
        dest.writeString(tglKadaluarsa);
        dest.writeString(statusBerita);
        dest.writeInt(idJurusan);
        dest.writeInt(idKategori);
        dest.writeString(namaJurusan);
        dest.writeString(namaKategori);
    }
}
