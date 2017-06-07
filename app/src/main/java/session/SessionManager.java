package session;

import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import untshare.progsby.com.untshare.SignUp;


public class SessionManager {
	// Shared Preferences
	private SharedPreferences pref;

	// Editor for Shared preferences
	private Editor editor;

	// Context
	private Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "news.xml";

	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";

	public static final String sid ="idUser";
	public static final String simage ="image";
	public static final String sUserName ="username";
	public static final String statusAdmin ="status";
	public static final String sNIK ="nik";
	public static final String sNamaLengkap ="nama";
	public static final String sTelp ="telp";
	public static final String sEmail ="email";
	public static final String sAlamat ="alamat";
	public static final String sJurusan ="jurusan";
	public static final String sNamaJurusan ="namaJurusan";
	public static final String sFakultas ="fakultas";
	public static final String sNamaFakultas ="namaFakultas";
	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	/**
	 * Create login session
	 * */
	public void createLoginSession(Integer id, String userName, String status, String nik, String namaLgkp,
					String tlp, String email, String alamat, Integer idJurusan, String nmJurusan, String foto,
					String fakultas, String nmFakultas){
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);
		editor.putInt(sid, id);
		editor.putString(sUserName, userName);
		editor.putString(statusAdmin, status);
		editor.putString(simage, foto);
		editor.putString(sNIK, nik);
		editor.putString(sNamaLengkap, namaLgkp);
		editor.putString(sTelp, tlp);
		editor.putString(sEmail, email);
		editor.putString(sAlamat, alamat);
		editor.putInt(sJurusan, idJurusan);
		editor.putString(sNamaJurusan, nmJurusan);
		editor.putString(sFakultas, fakultas);
		editor.putString(sNamaFakultas, nmFakultas);
		editor.commit();
	}

	/**
	 * Check login method wil check user login status
	 * If false it will redirect user to login page
	 * Else won't do anything
	 * */
	public void checkLogin(){
		// Check login status
		if(!this.isLoggedIn()){
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, SignUp.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
		}
	}

	/**
	 * Get stored session data
	 * */
	public HashMap<String, Object> getUserDetails(){
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put(sid, pref.getInt(sid, 0));
		user.put(sUserName, pref.getString(sUserName, null));
		user.put(statusAdmin, pref.getString(statusAdmin, null));
		user.put(simage, pref.getString(simage, null));
		user.put(sNIK, pref.getString(sNIK, null));
		user.put(sNamaLengkap, pref.getString(sNamaLengkap, null));
		user.put(sTelp, pref.getString(sTelp, null));
		user.put(sEmail, pref.getString(sEmail, null));
		user.put(sAlamat, pref.getString(sAlamat, null));
		user.put(sJurusan, pref.getInt(sJurusan, 0));
		user.put(sNamaJurusan, pref.getString(sNamaJurusan, null));
		user.put(sFakultas, pref.getString(sFakultas, null));
		user.put(sNamaFakultas, pref.getString(sNamaFakultas, null));
		return user;
	}

	/**
	 * Clear session details
	 * */
	public void logoutUser(){
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();

		// After logout redirect user to Loing Activity
		Intent i = new Intent(_context, SignUp.class);
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Staring Login Activity
		_context.startActivity(i);
	}

	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}

}
