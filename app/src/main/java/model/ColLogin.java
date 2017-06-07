package model;

import android.os.Parcel;
import android.os.Parcelable;

public class ColLogin implements Parcelable{
	private String Id_User, User_Name, Passwordd;
	private int Id;
	
	public ColLogin(){};
	
	public ColLogin(String id_User, String user_Name, String passwordd, int id) {
		super();
		Id_User = id_User;
		User_Name = user_Name;
		Passwordd = passwordd;
		Id = id;
	}
	
	
	
	public String getId_User() {
		return Id_User;
	}



	public void setId_User(String id_User) {
		Id_User = id_User;
	}



	public String getUser_Name() {
		return User_Name;
	}



	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}



	public String getPasswordd() {
		return Passwordd;
	}



	public void setPasswordd(String passwordd) {
		Passwordd = passwordd;
	}



	public int getId() {
		return Id;
	}



	public void setId(int id) {
		Id = id;
	}



	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

}
