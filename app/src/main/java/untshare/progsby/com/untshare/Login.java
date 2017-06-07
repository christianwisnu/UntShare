package untshare.progsby.com.untshare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import controll.AppController;
import controll.Link;
import model.ColUser;
import session.SessionManager;

public class Login extends Fragment{

	private String Login				="login.php";
	private EditText eNama,ePassword;
	private Button bLogin;
	private String TAG = Login.class.getName();
	private SessionManager session;
	private ArrayList<ColUser> columnlist = new ArrayList<ColUser>();
	private ProgressDialog dialog;
	private TextInputLayout inputLayoutName, inputLayoutPasw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
    	View vwlogin	= inflater.inflate(R.layout.login,container,false);
		session		= new SessionManager(getActivity());
		bLogin		= (Button)vwlogin.findViewById(R.id.bLoginLogin);
		inputLayoutName = (TextInputLayout)vwlogin.findViewById(R.id.input_layout_loginusername);
		inputLayoutPasw = (TextInputLayout)vwlogin.findViewById(R.id.input_layout_loginpasw);
		eNama		= (EditText)vwlogin.findViewById(R.id.eLoginUserName);
		ePassword	= (EditText)vwlogin.findViewById(R.id.eLoginPassword);
        Animation animFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        animFadeIn.setDuration(1000);
        animFadeIn.setStartOffset(1000);
        animFadeIn.setFillAfter(true);

        bLogin.setAnimation(animFadeIn);

		eNama.addTextChangedListener(new MyTextWatcher(eNama));
		ePassword.addTextChangedListener(new MyTextWatcher(ePassword));

		bLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog = new ProgressDialog(getActivity());
				dialog.setCancelable(true);
				dialog.setMessage("CHECK ID ANDA ...");
				SingUp(Link.FilePHP + Login);
				Log.i("LINK",Link.FilePHP + Login);
			}
		});
		return vwlogin;
		
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
				case R.id.input_layout_loginusername:
					validateUserName();
					break;
				case R.id.input_layout_loginpasw:
					validatePasw();
					break;
			}
		}
	}

	private boolean validateUserName() {
		if (eNama.getText().toString().trim().isEmpty()) {
			inputLayoutName.setError(getString(R.string.err_msg_user_name));
			requestFocus(eNama);
			return false;
		} else {
			inputLayoutName.setErrorEnabled(false);
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

	private void SingUp(String save){
		dialog.show();
		StringRequest simpan = new StringRequest(Request.Method.POST, save,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						VolleyLog.d(TAG, response.toString());

						try {
							JSONObject jsonrespon = new JSONObject(response);
							int Sucsess = jsonrespon.getInt("success");
							Log.i("Status", String.valueOf(Sucsess));
							dialog.dismiss();
							if (Sucsess==1){
								JSONArray JsonArray = jsonrespon.getJSONArray("uploade");
								for (int i = 0; i < JsonArray.length(); i++) {
									JSONObject objectt = JsonArray.getJSONObject(i);
									ColUser colums 	= new ColUser();
									colums.setIdUser(objectt.getInt("i_iduser"));
									colums.setUserName(objectt.getString("c_username"));
									colums.setStatus(objectt.getString("vc_status"));
									colums.setNik(objectt.getString("vc_nik"));
									colums.setNamaLengkap(objectt.getString("vc_nama"));
									colums.setTelp(objectt.getString("vc_telp"));
									colums.setEmail(objectt.getString("vc_email"));
									colums.setAlamat(objectt.getString("vc_alamat"));
									colums.setJurusan(objectt.getInt("i_idjurusan"));
									colums.setFoto(objectt.getString("vc_foto"));
									colums.setNamaJurusan(objectt.getString("vc_namajurusan"));
									colums.setFakultas(objectt.getString("c_kodefakultas"));
									colums.setNamaFakultas(objectt.getString("vc_namafakultas"));
									columnlist.add(colums);
								}
								//Log.i("IDUSER", String.valueOf(String.valueOf(columnlist.get(0).getId())));
								session.createLoginSession(columnlist.get(0).getIdUser(), columnlist.get(0).getUserName(),
										columnlist.get(0).getStatus(), columnlist.get(0).getNik(), columnlist.get(0).getNamaLengkap(),
										columnlist.get(0).getTelp(), columnlist.get(0).getEmail(), columnlist.get(0).getAlamat(),
										columnlist.get(0).getJurusan(), columnlist.get(0).getNamaJurusan(), columnlist.get(0).getFoto(),
										columnlist.get(0).getFakultas(), columnlist.get(0).getNamaFakultas());
								Intent i = new Intent(getActivity(), Main2Activity.class);
								startActivityForResult(i, 5000);
								getActivity().overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
								getActivity().finish();
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
				// TODO Auto-generated method stub
				VolleyLog.d(TAG, error.toString());
				dialog.dismiss();
			}
		}){
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("passwd", ePassword.getText().toString().trim());
				params.put("username",  eNama.getText().toString().trim());
				return params;
			}
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String,String> params = new HashMap<String, String>();
				params.put("Content-Type","application/x-www-form-urlencoded");
				return params;
			}
		};
		AppController.getInstance().getRequestQueue().getCache().invalidate(save, true);
		AppController.getInstance().addToRequestQueue(simpan);
	}



}