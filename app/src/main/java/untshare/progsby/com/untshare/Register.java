package untshare.progsby.com.untshare;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import controll.AsyncInvokeURLTask;
import model.ColUser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import session.SessionManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import controll.AppController;
import controll.Link;
import controll.Utils;


public class Register extends Fragment{

	public static ArrayList<HashMap<String, Object>> listJurusan = new ArrayList<HashMap<String, Object>>();
	public static String[] ARRJURUSAN=null;
	private String FileRegister = "registerUser.php";
	//private String FileRegisterLogin = "registerUser.php";
	private String encodedString,slasid, hasilSpinJurusan, kodeJurusan;
	private EditText eNama, eEmail, ePassword, eusername, eNik, eTelp, eAlamat;
	private ProgressDialog dialog;
	private Button bSingup;
	private ImageView ImgProfi;
	private String TAG = SignUp.class.getName();
	private static int RESULT_LOAD_IMG = 1;
	private ArrayList<ColUser>columnlist = new ArrayList<ColUser>();
	private SessionManager session;
	private String simage="none.jpg";
    private Spinner spjurusan;
	private TextInputLayout inputLayoutName, inputLayoutNik, inputLayoutTelp, inputLayoutEmail, inputLayoutAdress,
			inputLayoutUserName, inputLayoutPasw;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
    	session		= new SessionManager(getActivity());
    	View vwregister	= inflater.inflate(R.layout.register,container,false);

		inputLayoutName = (TextInputLayout)vwregister.findViewById(R.id.input_layout_nama);
		inputLayoutNik = (TextInputLayout)vwregister.findViewById(R.id.input_layout_nik);
		inputLayoutTelp = (TextInputLayout)vwregister.findViewById(R.id.input_layout_telp);
		inputLayoutEmail = (TextInputLayout)vwregister.findViewById(R.id.input_layout_email);
		inputLayoutAdress = (TextInputLayout)vwregister.findViewById(R.id.input_layout_adress);
		inputLayoutUserName = (TextInputLayout)vwregister.findViewById(R.id.input_layout_username);
		inputLayoutPasw = (TextInputLayout)vwregister.findViewById(R.id.input_layout_pasw);

		eNama		= (EditText)vwregister.findViewById(R.id.input_nama);
		eNik		= (EditText)vwregister.findViewById(R.id.input_nik);
		eTelp		= (EditText)vwregister.findViewById(R.id.input_telp);
		eEmail		= (EditText)vwregister.findViewById(R.id.input_email);
		eAlamat		= (EditText)vwregister.findViewById(R.id.input_adress);
		eusername	= (EditText)vwregister.findViewById(R.id.input_username);
		ePassword	= (EditText)vwregister.findViewById(R.id.input_pasw);
        spjurusan = (Spinner) vwregister.findViewById(R.id.spJurusan);

		bSingup		= (Button)vwregister.findViewById(R.id.bRegisterSave);
		ImgProfi	= (ImageView)vwregister.findViewById(R.id.ImgRegisterProfile);

        getDataJurusan(spjurusan);
        Animation animFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        animFadeIn.setDuration(1000);
        animFadeIn.setStartOffset(1000);
        animFadeIn.setFillAfter(true);

        bSingup.setAnimation(animFadeIn);

		eNama.addTextChangedListener(new MyTextWatcher(eNama));
        ePassword.addTextChangedListener(new MyTextWatcher(ePassword));
        eusername.addTextChangedListener(new MyTextWatcher(eusername));

		bSingup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog = new ProgressDialog(getActivity());
				dialog.setCancelable(true);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setMessage("Register ...");
				Register(Link.FilePHP+FileRegister);
			}
		});
		
		ImgProfi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent galleryIntent = new Intent(Intent.ACTION_PICK,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
			}
		});
		return vwregister;
	}

	private class MyTextWatcher implements TextWatcher {

		private View view;

		private MyTextWatcher(View view) {
			this.view = view;
		}

		public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		}

		public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		}

		public void afterTextChanged(Editable editable) {
			switch (view.getId()) {
				case R.id.input_nama:
					validateName();
					break;
				case R.id.input_username:
					validateUserName();
					break;
				case R.id.input_pasw:
					validatePasw();
					break;
			}
		}
	}

	private boolean validateName() {
		if (eNama.getText().toString().trim().isEmpty()) {
			inputLayoutName.setError(getString(R.string.err_msg_name));
			requestFocus(eNama);
			return false;
		} else {
			inputLayoutName.setErrorEnabled(false);
		}
		return true;
	}

	private boolean validateUserName() {
		if (eusername.getText().toString().trim().isEmpty()) {
			inputLayoutUserName.setError(getString(R.string.err_msg_user_name));
			requestFocus(eusername);
			return false;
		} else {
			inputLayoutUserName.setErrorEnabled(false);
		}
		return true;
	}

	private boolean validatePasw() {
		if (ePassword.getText().toString().trim().isEmpty()) {
			inputLayoutPasw.setError(getString(R.string.err_msg_pasw));
			requestFocus(ePassword);
			return false;
		} else {
			inputLayoutPasw.setErrorEnabled(false);
		}
		return true;
	}

	private void requestFocus(View view) {
		if (view.requestFocus()) {
			getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		}
	}

	public void getDataJurusan(final Spinner jurusan) {
		try{
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					1);
			nameValuePairs.add(new BasicNameValuePair("act", "queryspinner"));
			AsyncInvokeURLTask task = new AsyncInvokeURLTask(nameValuePairs,
					new AsyncInvokeURLTask.OnPostExecuteListener() {

						@Override
						public void onPostExecute(String result) {
							// TODO Auto-generated method stub
							Log.d("TAG", "getdataspinner:" + result);
							setDataSpinner(result, jurusan);
						}
					});

			task.applicationContext =this.getActivity();
			task.mNoteItWebUrl = "/getJurusan.php";
			task.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDataSpinner(String result, Spinner jurusan){
		try{
			JSONObject json = new JSONObject(result);
			if(json.getString("errorcode").equals("0")){
				//Toast.makeText(ListDataServerActivity.this, json.getString("errormsg"), Toast.LENGTH_SHORT).show();
			}
			listJurusan = new ArrayList<HashMap<String, Object>>();
			JSONArray  datameja= json.getJSONArray("datakain");
			for (int i=0; i<datameja.length(); i++){
				JSONObject jsonobj =datameja.getJSONObject(i);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("i_idjurusan", jsonobj.getInt("i_idjurusan"));
				map.put("vc_namajurusan", jsonobj.getString("vc_namajurusan"));
				listJurusan.add(map);
			}
			ARRJURUSAN = new String[listJurusan.size()];
			for (int i = 0; i < listJurusan.size(); i++) {
				ARRJURUSAN[i]=listJurusan.get(i).get("i_idjurusan")+"--"+listJurusan.get(i).get("vc_namajurusan");
			}
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
		}

		ArrayAdapter<String> aaJurusan = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, ARRJURUSAN);
		aaJurusan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jurusan.setAdapter(aaJurusan);

		jurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				// TODO Auto-generated method stub
				hasilSpinJurusan = parent.getItemAtPosition(pos).toString();
				String[] parts = hasilSpinJurusan.split("--");
				String kode = parts[0];
				kodeJurusan=kode;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void Register(String save){		
		dialog.show();
		StringRequest register = new StringRequest(Method.POST, save, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				VolleyLog.d("Respone", response.toString());
				try {
					JSONObject jsonrespon = new JSONObject(response);
					int Sucsess = jsonrespon.getInt("success");
					slasid		=String.valueOf(Sucsess);
					Log.i("Suceses", String.valueOf(Sucsess));
					if (Sucsess > 0 ){
						//RegisterLogin(Link.FilePHP+FileRegisterLogin);
                        session.createLoginSession(columnlist.get(0).getIdUser(), columnlist.get(0).getUserName(),
                                columnlist.get(0).getStatus(), columnlist.get(0).getNik(), columnlist.get(0).getNamaLengkap(),
                                columnlist.get(0).getTelp(), columnlist.get(0).getEmail(), columnlist.get(0).getAlamat(),
                                columnlist.get(0).getJurusan(), columnlist.get(0).getNamaJurusan(), columnlist.get(0).getFoto(),
                                columnlist.get(0).getFakultas(), columnlist.get(0).getNamaFakultas());
                        Intent i = new Intent(getActivity(), Main2Activity.class);
                        startActivityForResult(i, 5000);
                        getActivity().overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
                        getActivity().finish();
						Toast.makeText(getActivity(),
								"Sukses", Toast.LENGTH_LONG)
								.show();
					}else{
						Toast.makeText(getActivity(),
								"Gagal Coba Lagi", Toast.LENGTH_LONG)
								.show();
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, error.toString());
			}
		}){
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("userName", eusername.getText().toString().trim());
				params.put("pasw", ePassword.getText().toString().trim());
				params.put("status", "USER");
				params.put("nik", eNik.getText().toString());
				params.put("nama", eNama.getText().toString());
				params.put("telp", eTelp.getText().toString()==null?"":eTelp.getText().toString());
				params.put("email", eEmail.getText().toString()==null?"":eEmail.getText().toString());
				params.put("alamat", eAlamat.getText().toString()==null?"":eEmail.getText().toString());
				params.put("foto", simage);
				params.put("jurusan", kodeJurusan);
				return params;
			}
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String,String> params = new HashMap<String, String>();
				params.put("Content-Type","application/x-www-form-urlencoded");
				return params;
			}
		};
		AppController.getInstance().addToRequestQueue(register);
	}
	
	/*private void RegisterLogin(String save){
		StringRequest register = new StringRequest(Method.POST, save, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				VolleyLog.d("Respone", response.toString());
				try {
					JSONObject jsonrespon = new JSONObject(response);
					int Sucsess = jsonrespon.getInt("success");
					Log.i("Suceses", String.valueOf(Sucsess));
					if (Sucsess ==1 ){
						// "" = imei. diganti
						session.createLoginSession(String.valueOf(slasid), eNama.getText().toString(), simage, "", "User");
						Intent i = new Intent(getActivity(), MainMenu.class);
						startActivityForResult(i, 5000);
						getActivity().overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
						getActivity().finish();
					}else{
						Toast.makeText(getActivity(),
								"Gagal Coba Lagi", Toast.LENGTH_SHORT)
								.show();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				VolleyLog.d(TAG, error.toString());
			}
		}){
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("Id_User", slasid);
				params.put("User_Name", eusername.getText().toString().trim());
				params.put("Passwordd", ePassword.getText().toString().trim());
				return params;
			}
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String,String> params = new HashMap<String, String>();
				params.put("Content-Type","application/x-www-form-urlencoded");
				return params;
			}
		};
		AppController.getInstance().addToRequestQueue(register);
	}*/

	// When Image is selected from Gallery
    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMG && resultCode == getActivity().RESULT_OK && null != data) {
        	simage	= getNameImage().toString()+".jpg";
        	Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();            
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Utils.GetCycleImage("file:///"+picturePath, ImgProfi, getActivity());
            String fileNameSegments[] = picturePath.split("/");
            Bitmap myImg = BitmapFactory.decodeFile(picturePath);            
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // Must compress the Image to reduce image size to make upload easy
            myImg.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] byte_arr = stream.toByteArray();
            // Encode Image to String
            encodedString = Base64.encodeToString(byte_arr, 0);           
            uploadImage(Link.FileUpload,simage);
        }
    }

    public void uploadImage(String url,final String ImageName) {
		StringRequest stringRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				try {
					Log.e("RESPONSE", response);
				} catch (Exception e) {
					Log.d("JSON Exception", e.toString());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.d("ERROR", "Error [" + error + "]");
				Toast.makeText(getActivity(),
						"Cannot connect to server", Toast.LENGTH_LONG)
						.show();
			}
		}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("image", encodedString);
				params.put("filename", ImageName);
				return params;
			}
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String,String> params = new HashMap<String, String>();
				params.put("Content-Type","application/x-www-form-urlencoded");
				return params;
			}
		};
		AppController.getInstance().addToRequestQueue(stringRequest);
	}

	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String getNameImage() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd-HHmmss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
}