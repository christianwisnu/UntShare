package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rizky on 1/3/2016.
 */
public class ColUploaPopuler implements Parcelable {
    private String Image,TglInput,UserName,UserImage,Desciption,Latitude,Longitude;
    private int IdUser,IdUpload,IdInput,StatusLike,Likee,Commentt;
    private String judul;
    private String namaKain;
    private String namaWil;
    private String kodeKain;
    private String kodeWilayah;

    public  ColUploaPopuler(){};

    public ColUploaPopuler(String image, String tglInput, String userName, String userImage, String desciption, String latitude,
                           String longitude, int idUser, int idUpload, int idInput, int statusLike, int likee, int commentt
            , String kodekain, String kodewil, String judulku, String namakain, String namawil) {
        Image = image;
        TglInput = tglInput;
        UserName = userName;
        UserImage = userImage;
        Desciption = desciption;
        Latitude = latitude;
        Longitude = longitude;
        IdUser = idUser;
        IdUpload = idUpload;
        IdInput = idInput;
        StatusLike = statusLike;
        Likee = likee;
        Commentt = commentt;
        kodeKain = kodekain;
        kodeWilayah = kodewil;
        judul = judulku;
        namaKain=namakain;
        namaWil=namawil;
    }

    protected ColUploaPopuler(Parcel in) {
        Image = in.readString();
        TglInput = in.readString();
        UserName = in.readString();
        UserImage = in.readString();
        Desciption = in.readString();
        Latitude = in.readString();
        Longitude = in.readString();
        IdUser = in.readInt();
        IdUpload = in.readInt();
        IdInput = in.readInt();
        StatusLike = in.readInt();
        Likee = in.readInt();
        Commentt = in.readInt();
        kodeKain = in.readString();
        kodeWilayah = in.readString();
        judul = in.readString();
        namaKain = in.readString();
        namaWil = in.readString();
    }

    public static final Creator<ColUploaPopuler> CREATOR = new Creator<ColUploaPopuler>() {
        @Override
        public ColUploaPopuler createFromParcel(Parcel in) {
            return new ColUploaPopuler(in);
        }

        @Override
        public ColUploaPopuler[] newArray(int size) {
            return new ColUploaPopuler[size];
        }
    };

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTglInput() {
        return TglInput;
    }

    public void setTglInput(String tglInput) {
        TglInput = tglInput;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getDesciption() {
        return Desciption;
    }

    public void setDesciption(String desciption) {
        Desciption = desciption;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public int getIdUpload() {
        return IdUpload;
    }

    public void setIdUpload(int idUpload) {
        IdUpload = idUpload;
    }

    public int getIdInput() {
        return IdInput;
    }

    public void setIdInput(int idInput) {
        IdInput = idInput;
    }

    public int getStatusLike() {
        return StatusLike;
    }

    public void setStatusLike(int statusLike) {
        StatusLike = statusLike;
    }

    public int getLikee() {
        return Likee;
    }

    public void setLikee(int likee) {
        Likee = likee;
    }

    public int getCommentt() {
        return Commentt;
    }

    public void setCommentt(int commentt) {
        Commentt = commentt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Image);
        dest.writeString(TglInput);
        dest.writeString(UserName);
        dest.writeString(UserImage);
        dest.writeString(Desciption);
        dest.writeString(Latitude);
        dest.writeString(Longitude);
        dest.writeInt(IdUser);
        dest.writeInt(IdUpload);
        dest.writeInt(IdInput);
        dest.writeInt(StatusLike);
        dest.writeInt(Likee);
        dest.writeInt(Commentt);
        dest.writeString(kodeKain);
        dest.writeString(kodeWilayah);
        dest.writeString(judul);
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNamaKain() {
        return namaKain;
    }

    public void setNamaKain(String namaKain) {
        this.namaKain = namaKain;
    }

    public String getNamaWil() {
        return namaWil;
    }

    public void setNamaWil(String namaWil) {
        this.namaWil = namaWil;
    }

    public String getKodeKain() {
        return kodeKain;
    }

    public void setKodeKain(String kodeKain) {
        this.kodeKain = kodeKain;
    }

    public String getKodeWilayah() {
        return kodeWilayah;
    }

    public void setKodeWilayah(String kodeWilayah) {
        this.kodeWilayah = kodeWilayah;
    }
}
