package model;

import android.os.Parcel;
import android.os.Parcelable;

public class ColUpload implements Parcelable {
	
	private String Image;
	private String TglInput;
	private String UserName;
	private String UserImage;
	private String Desciption;
	private String Latitude;
	private String Longitude;
	private String kodeKain;
	private String kodeWilayah;
	private String judul;
	private String namaKain;
	private String namaWil;
	private int IdUser,IdUpload,IdInput,StatusLike;
	public ColUpload(){};

	public ColUpload(String image, String tglInput, String userName,
			String userImage, String desciption, String latitude,
			String longitude, int idUser, int idUpload, int idInput,
			int statusLike, String kodekain, String kodewil, String judulku, String namakain, String namawil) {
		super();
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
		kodeKain = kodekain;
		kodeWilayah = kodewil;
		judul = judulku;
		namaKain=namakain;
		namaWil=namawil;
	}

	protected ColUpload(Parcel in) {
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
		kodeKain = in.readString();
		kodeWilayah = in.readString();
		judul = in.readString();
		namaKain = in.readString();
		namaWil = in.readString();
	}

	public static final Creator<ColUpload> CREATOR = new Creator<ColUpload>() {
		@Override
		public ColUpload createFromParcel(Parcel in) {
			return new ColUpload(in);
		}

		@Override
		public ColUpload[] newArray(int size) {
			return new ColUpload[size];
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

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
		dest.writeString(kodeKain);
		dest.writeString(kodeWilayah);
		dest.writeString(judul);
	}
}
