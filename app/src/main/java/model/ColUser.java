package model;

import android.os.Parcel;
import android.os.Parcelable;

public class ColUser implements  Parcelable {

	private Integer idUser;
	private String userName;
	private String status;
	private String nik;
	private String namaLengkap;
	private String telp;
	private String email;
	private String alamat;
	private Integer jurusan;
	private String namaJurusan;
	private String foto;
	private String fakultas;
	private String namaFakultas;

	public ColUser() {
	};

	protected ColUser(Parcel in) {
		idUser = in.readInt();
		userName = in.readString();
		status = in.readString();
		nik = in.readString();
		namaLengkap = in.readString();
		telp = in.readString();
		email = in.readString();
		alamat = in.readString();
		jurusan = in.readInt();
		namaJurusan = in.readString();
		foto = in.readString();
		fakultas = in.readString();
		namaFakultas = in.readString();
	}

	public static final Creator<ColUser> CREATOR = new Creator<ColUser>() {
		@Override
		public ColUser createFromParcel(Parcel in) {
			return new ColUser(in);
		}

		@Override
		public ColUser[] newArray(int size) {
			return new ColUser[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(idUser);
		dest.writeString(userName);
		dest.writeString(status);
		dest.writeString(nik);
		dest.writeString(namaLengkap);
		dest.writeString(telp);
		dest.writeString(email);
		dest.writeString(alamat);
		dest.writeInt(jurusan);
		dest.writeString(namaJurusan);
		dest.writeString(fakultas);
		dest.writeString(foto);
		dest.writeString(namaFakultas);
	}

	public Integer getIdUser() {return idUser;}
	public String getUserName() {return userName;}
	public String getStatus() {return status;}
	public String getNik() {return nik;}
	public String getNamaLengkap() {return namaLengkap;}
	public String getTelp() {return telp;}
	public String getEmail() {return email;}
	public String getAlamat() {return alamat;}
	public Integer getJurusan() {return jurusan;}
	public String getNamaJurusan() {return namaJurusan;}
	public String getFoto() {return foto;}
	public String getFakultas() {return fakultas;}
	public String getNamaFakultas() {return namaFakultas;}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}
	public void setTelp(String telp) {
		this.telp = telp;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public void setJurusan(Integer jurusan) {
		this.jurusan = jurusan;
	}
	public void setNamaJurusan(String namaJurusan) {
		this.namaJurusan = namaJurusan;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public void setFakultas(String fakultas) {
		this.fakultas = fakultas;
	}
	public void setNamaFakultas(String namaFakultas) {
		this.namaFakultas = namaFakultas;
	}
}