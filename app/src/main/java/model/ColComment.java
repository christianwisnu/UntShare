package model;

import android.os.Parcel;
import android.os.Parcelable;

public class ColComment implements Parcelable{

	private String isiComent;
	private String tglKomentar;
	private String statusComent;
	private String userName;
	private String foto;
	private Integer idBerita;
	private Integer idUser;
	private Integer idComent;

	public ColComment() {}

	/*public ColComment(String isiComent, String image, String name,
					  String imageUpload, String time, int idComment, int idUser,
					  int idUpload, int myId) {
		super();
		Comment = comment;
		Image = image;
		Name = name;
		ImageUpload = imageUpload;
		Time = time;
		IdComment = idComment;
		IdUser = idUser;
		IdUpload = idUpload;
		MyId = myId;
	}*/

	protected ColComment(Parcel in) {
		isiComent	= in.readString();
		tglKomentar = in.readString();
		statusComent= in.readString();
		userName 	= in.readString();
		foto 		= in.readString();
		idBerita 	= in.readInt();
		idUser 		= in.readInt();
		idComent 	= in.readInt();
	}

	public static final Creator<ColComment> CREATOR = new Creator<ColComment>() {
		@Override
		public ColComment createFromParcel(Parcel in) {
			return new ColComment(in);
		}

		@Override
		public ColComment[] newArray(int size) {
			return new ColComment[size];
		}
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(isiComent);
		dest.writeString(tglKomentar);
		dest.writeString(statusComent);
		dest.writeString(userName);
		dest.writeString(foto);
		dest.writeInt(idBerita);
		dest.writeInt(idUser);
		dest.writeInt(idUser);
	}

	public String getIsiComent() {
		return isiComent;
	}

	public void setIsiComent(String isiComent) {
		this.isiComent = isiComent;
	}

	public String getTglKomentar() {
		return tglKomentar;
	}

	public void setTglKomentar(String tglKomentar) {
		this.tglKomentar = tglKomentar;
	}

	public String getStatusComent() {
		return statusComent;
	}

	public void setStatusComent(String statusComent) {
		this.statusComent = statusComent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getIdBerita() {
		return idBerita;
	}

	public void setIdBerita(Integer idBerita) {
		this.idBerita = idBerita;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdComent() {
		return idComent;
	}

	public void setIdComent(Integer idComent) {
		this.idComent = idComent;
	}
}
