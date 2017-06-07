package untshare.progsby.com.untshare;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controll.CheckValidation;
import model.ColComment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.AdpCommenet;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.request.StringRequest;

import controll.AppController;
import controll.Link;

public class Comment extends Activity{
	private AdpCommenet adapter;
	private ListView lsvcomment;
	private ArrayList<ColComment>columnlist= new ArrayList<ColComment>();
	private TextView tvstatus;
	private ProgressBar prbstatus;
	private String GetCoomment		="getComment.php?idBerita=";
	private String InsertComment	="insertComment.php";
	private ImageView imgback,imgsend;
	private EditText ecomment;
	private String idBerita, idUser;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		Bundle bundle	 = getIntent().getExtras();
		if (bundle!=null){
			idBerita	= bundle.getString("idBerita");
			idUser		= bundle.getString("idUser");
		}

		lsvcomment		= (ListView)findViewById(R.id.LsvComment);
		tvstatus		= (TextView)findViewById(R.id.TvCommentStatus);
		prbstatus		= (ProgressBar)findViewById(R.id.PbrCommentStatus);
		imgback			= (ImageView)findViewById(R.id.ImgBackCooment);
		imgsend			= (ImageView)findViewById(R.id.ImgSendComment);
		ecomment		= (EditText)findViewById(R.id.EComment);
		adapter = new AdpCommenet(Comment.this, R.layout.col_comment, columnlist);
		lsvcomment.setAdapter(adapter);

		Cache cache = AppController.getInstance().getRequestQueue().getCache();
		Cache.Entry entry = cache.get(Link.FilePHP+GetCoomment+idBerita);
		if (entry != null) {
			//fetch the data from the cache
			try {
				String data = new String(entry.data, "UTF-8");
				parseJsonFeed(new JSONObject(data));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (JSONException jsonException) {
				jsonException.printStackTrace();
			}
		} else {
			GetDataComment(Link.FilePHP+GetCoomment+idBerita);
		}

		imgback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		imgsend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(validation()){
					ecomment.setEnabled(false);
					imgsend.setEnabled(false);
					SendComment(Link.FilePHP + InsertComment);
				}
			}
		});

		ecomment.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.length()>0){
					imgsend.setImageResource(R.mipmap.ic_action_send_yellow_light);
				}else{
					imgsend.setImageResource(R.mipmap.ic_action_send_holo_light);
				}
				//CheckValidation.hasText(ecomment, getResources().getString(R.string.ValComment));
			}
		});
	}

	@Override
	protected void onResume(){
		super.onResume();
		GetDataComment(Link.FilePHP + GetCoomment + idBerita);
	}

	private void GetDataComment(String Url){
		tvstatus.setVisibility(View.GONE);
		prbstatus.setVisibility(View.VISIBLE);
		JsonObjectRequest jsonget = new JsonObjectRequest(Method.GET, Url, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						try {
							int sucses= response.getInt("success");
							Log.i("Status", String.valueOf(sucses));
							if (sucses==1){
								tvstatus.setVisibility(View.GONE);
								prbstatus.setVisibility(View.GONE);
								adapter.clear();
								JSONArray JsonArray = response.getJSONArray("Comment");
								for (int i = 0; i < JsonArray.length(); i++) {
									JSONObject object = JsonArray.getJSONObject(i);
									ColComment colums 	= new ColComment();
									colums.setIdComent(object.getInt("id_komentar"));
									colums.setIdUser(object.getInt("id_user"));
									colums.setIdBerita(object.getInt("id_berita"));
									colums.setIsiComent(object.getString("t_komentar"));
									colums.setTglKomentar(object.getString("dt_tglkomentar"));
									colums.setStatusComent(object.getString("vc_statuskomentar"));
									colums.setUserName(object.getString("c_username"));
									colums.setFoto(object.getString("vc_foto"));
									columnlist.add(colums);
								}
							}else{
								tvstatus.setVisibility(View.VISIBLE);
								tvstatus.setText("Tidak Ada Data");
								prbstatus.setVisibility(View.GONE);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						adapter.notifyDataSetChanged();
						lsvcomment.invalidate();
					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				if (error instanceof TimeoutError || error instanceof NoConnectionError) {
					tvstatus.setVisibility(View.VISIBLE);
					tvstatus.setText("Check Koneksi Internet Anda");
					prbstatus.setVisibility(View.GONE);
				} else if (error instanceof AuthFailureError) {
					//TODO
					tvstatus.setVisibility(View.VISIBLE);
					tvstatus.setText("AuthFailureError");
					prbstatus.setVisibility(View.GONE);
				} else if (error instanceof ServerError) {
					//TODO
					tvstatus.setVisibility(View.VISIBLE);
					tvstatus.setText("Check ServerError");
					prbstatus.setVisibility(View.GONE);
				} else if (error instanceof NetworkError) {
					//TODO
					tvstatus.setVisibility(View.VISIBLE);
					tvstatus.setText("Check NetworkError");
					prbstatus.setVisibility(View.GONE);
				} else if (error instanceof ParseError) {
					//TODO
					tvstatus.setVisibility(View.VISIBLE);
					tvstatus.setText("Check ParseError");
					prbstatus.setVisibility(View.GONE);
				}
			}
		}){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String,String> params = new HashMap<String, String>();
				params.put("Content-Type","application/json");
				return params;
			}

		};
		AppController.getInstance().getRequestQueue().getCache().invalidate(Url, true);
		AppController.getInstance().addToRequestQueue(jsonget);
	}

	private void parseJsonFeed(JSONObject response) {
		try {
			JSONArray JsonArray = response.getJSONArray("Comment");
			for (int i = 0; i < JsonArray.length(); i++) {
				JSONObject object = JsonArray.getJSONObject(i);
				ColComment colums 	= new ColComment();
				colums.setIdComent(object.getInt("id_komentar"));
				colums.setIdUser(object.getInt("id_user"));
				colums.setIdBerita(object.getInt("id_berita"));
				colums.setIsiComent(object.getString("t_komentar"));
				colums.setTglKomentar(object.getString("dt_tglkomentar"));
				colums.setStatusComent(object.getString("vc_statuskomentar"));
				colums.setUserName(object.getString("c_username"));
				colums.setFoto(object.getString("vc_foto"));
				columnlist.add(colums);
			}
			adapter.notifyDataSetChanged();
			lsvcomment.invalidate();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void SendComment(String save){
		StringRequest register = new StringRequest(Method.POST, save,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
						VolleyLog.d("Respone", response.toString());

						try {
							JSONObject jsonrespon = new JSONObject(response);
							int Sucsess = jsonrespon.getInt("success");
							Log.i("Suceses", String.valueOf(Sucsess));
							if (Sucsess == 1 ){
								ecomment.setEnabled(true);
								imgsend.setEnabled(true);
								ecomment.setText("");
								GetDataComment(Link.FilePHP+GetCoomment+idBerita);
							}else{
								Toast.makeText(Comment.this,
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

			}
		}){
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("idBerita", idBerita);
				params.put("idUser", idUser);
				params.put("comment", ecomment.getText().toString());
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

	private boolean validation(){
		boolean val =true;
		if(!CheckValidation.hasText(ecomment, getResources().getString(R.string.ValComment)))val= false;
		return val;
	}
}

