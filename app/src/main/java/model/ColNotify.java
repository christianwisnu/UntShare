package model;

import android.os.Parcel;
import android.os.Parcelable;

public class ColNotify implements Parcelable {

	private String Nama, UserImage, NotifyDesc, UploadImage, EntryDate, Latitude, Longitude, UploadDate, Description;
	private int Id, IdUser, MyId, Type, IdUpload;

	public ColNotify() {
		// TODO Auto-generated constructor stub
	}

	public ColNotify(String nama, String userImage, String notifyDesc, String uploadImage, String entryDate, String latitude, String longitude, String uploadDate, String description, int id, int idUser, int myId, int type, int idUpload) {
		Nama = nama;
		UserImage = userImage;
		NotifyDesc = notifyDesc;
		UploadImage = uploadImage;
		EntryDate = entryDate;
		Latitude = latitude;
		Longitude = longitude;
		UploadDate = uploadDate;
		Description = description;
		Id = id;
		IdUser = idUser;
		MyId = myId;
		Type = type;
		IdUpload = idUpload;
	}

	protected ColNotify(Parcel in) {
		Nama = in.readString();
		UserImage = in.readString();
		NotifyDesc = in.readString();
		UploadImage = in.readString();
		EntryDate = in.readString();
		Latitude = in.readString();
		Longitude = in.readString();
		UploadDate = in.readString();
		Description = in.readString();
		Id = in.readInt();
		IdUser = in.readInt();
		MyId = in.readInt();
		Type = in.readInt();
		IdUpload = in.readInt();
	}

	public static final Creator<ColNotify> CREATOR = new Creator<ColNotify>() {
		@Override
		public ColNotify createFromParcel(Parcel in) {
			return new ColNotify(in);
		}

		@Override
		public ColNotify[] newArray(int size) {
			return new ColNotify[size];
		}
	};

	public String getNama() {
		return Nama;
	}

	public void setNama(String nama) {
		Nama = nama;
	}

	public String getUserImage() {
		return UserImage;
	}

	public void setUserImage(String userImage) {
		UserImage = userImage;
	}

	public String getNotifyDesc() {
		return NotifyDesc;
	}

	public void setNotifyDesc(String notifyDesc) {
		NotifyDesc = notifyDesc;
	}

	public String getUploadImage() {
		return UploadImage;
	}

	public void setUploadImage(String uploadImage) {
		UploadImage = uploadImage;
	}

	public String getEntryDate() {
		return EntryDate;
	}

	public void setEntryDate(String entryDate) {
		EntryDate = entryDate;
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

	public String getUploadDate() {
		return UploadDate;
	}

	public void setUploadDate(String uploadDate) {
		UploadDate = uploadDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int idUser) {
		IdUser = idUser;
	}

	public int getMyId() {
		return MyId;
	}

	public void setMyId(int myId) {
		MyId = myId;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public int getIdUpload() {
		return IdUpload;
	}

	public void setIdUpload(int idUpload) {
		IdUpload = idUpload;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(Nama);
		dest.writeString(UserImage);
		dest.writeString(NotifyDesc);
		dest.writeString(UploadImage);
		dest.writeString(EntryDate);
		dest.writeString(Latitude);
		dest.writeString(Longitude);
		dest.writeString(UploadDate);
		dest.writeString(Description);
		dest.writeInt(Id);
		dest.writeInt(IdUser);
		dest.writeInt(MyId);
		dest.writeInt(Type);
		dest.writeInt(IdUpload);
	}
}