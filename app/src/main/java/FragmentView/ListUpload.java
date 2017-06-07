package FragmentView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.ColBerita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.AdpListBerita;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;

import controll.AppController;
import controll.Link;
import untshare.progsby.com.untshare.Detail;
import untshare.progsby.com.untshare.R;

public class ListUpload extends Fragment {	
	private View vupload;
	private AdpListBerita adapter;
	private ListView lsvupload;
	private ArrayList<ColBerita>columnlist= new ArrayList<ColBerita>();
	private TextView tvstatus;
	private  ProgressBar prbstatus;
	private String getJurusan="getBeritaJurusan.php?IdJurusan=";
	private String getFakultas="getBeritaFakultas.php?IdFakultas=";
	private String getExternal="getBeritaExternal.php?IdKategori=";
	private static final int SEND_UPLOAD = 201;
	private String tipeBerita, idBerita;
	private String jurusan="Berita Jurusan";
	private String fakultas="Berita Fakultas";
	private String politik="Politik";
	private String OR ="Olah Raga";
	private String spkBl="Sepak Bola";
	private String food ="Food";
	private String hukum="Hukum";
	private String kesehatan="Kesehatan";
	private String lifestyle="Lifestyle";
	private String otomotif="Otomotif";
	private String traveling="Traveling";
	private String fotografi="Fotografi";
	private String teknologi="Teknologi";
	private String finance="Finance";
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
    	
    	Bundle bundle	 = this.getArguments();
    	if (bundle!=null){
    		tipeBerita	= bundle.getString("tipeBerita");
			idBerita	= bundle.getString("idBerita");
		}

    	vupload	= inflater.inflate(R.layout.list_upload,container,false);
    	lsvupload	= (ListView)vupload.findViewById(R.id.LsvUpload);
    	tvstatus	= (TextView)vupload.findViewById(R.id.TvStatusUpload);
    	prbstatus	= (ProgressBar)vupload.findViewById(R.id.PrbStatusUpload);
    	
    	adapter		= new AdpListBerita(getActivity(), R.layout.colupload, columnlist);
    	lsvupload.setAdapter(adapter);
    	Cache cache = AppController.getInstance().getRequestQueue().getCache();
		Cache.Entry entry = null;
		if(tipeBerita.equals(jurusan)){
			entry = cache.get(Link.FilePHP+getJurusan+idBerita);
		}else if(tipeBerita.equals(fakultas)){
			entry = cache.get(Link.FilePHP+getFakultas+idBerita);
		}else{
			entry = cache.get(Link.FilePHP+getExternal+idBerita);
		}

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
			if(tipeBerita.equals(jurusan)){
				GetDataUpload(Link.FilePHP+getJurusan+idBerita);
			}else if(tipeBerita.equals(fakultas)){
				GetDataUpload(Link.FilePHP+getFakultas+idBerita);
			}else{
				GetDataUpload(Link.FilePHP+getExternal+idBerita);
			}
		}
    	
    	lsvupload.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> Parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), String.valueOf(columnlist.get(position).getIdUser()), Toast.LENGTH_LONG).show();
				Intent i = new Intent(getActivity(), Detail.class);
				i.putExtra("idBerita", String.valueOf(columnlist.get(position).getIdBerita()));
				i.putExtra("judul", columnlist.get(position).getJudul());
				i.putExtra("gambar", columnlist.get(position).getGambar());
				i.putExtra("isiBerita", columnlist.get(position).getIsiBerita());
				i.putExtra("tglTerbit", columnlist.get(position).getTglTerbit());
				i.putExtra("penulis", columnlist.get(position).getPenulis());
				/*i.putExtra("idJurusan", columnlist.get(position).getIdJurusan());
				i.putExtra("namaJurusan", columnlist.get(position).getNamaJurusan());
				i.putExtra("idKategori", columnlist.get(position).getIdKategori());
				i.putExtra("namaKategori", columnlist.get(position).getNamaKategori());*/
				if(tipeBerita.equals(jurusan)){
					i.putExtra("jenisBerita", jurusan);
				}else if(tipeBerita.equals(fakultas)){
					i.putExtra("jenisBerita", fakultas);
				}else if(tipeBerita.equals(politik)){
					i.putExtra("jenisBerita", politik);
				}else if(tipeBerita.equals(OR)){
					i.putExtra("jenisBerita", OR);
				}else if(tipeBerita.equals(spkBl)){
					i.putExtra("jenisBerita", spkBl);
				}else if(tipeBerita.equals(food)){
					i.putExtra("jenisBerita", food);
				}else if(tipeBerita.equals(hukum)){
					i.putExtra("jenisBerita", hukum);
				}else if(tipeBerita.equals(kesehatan)){
					i.putExtra("jenisBerita", kesehatan);
				}else if(tipeBerita.equals(lifestyle)){
					i.putExtra("jenisBerita", lifestyle);
				}else if(tipeBerita.equals(otomotif)){
					i.putExtra("jenisBerita", otomotif);
				}else if(tipeBerita.equals(traveling)){
					i.putExtra("jenisBerita", traveling);
				}else if(tipeBerita.equals(fotografi)){
					i.putExtra("jenisBerita", fotografi);
				}else if(tipeBerita.equals(teknologi)){
					i.putExtra("jenisBerita", teknologi);
				}else if(tipeBerita.equals(finance)){
					i.putExtra("jenisBerita", finance);
				}
				getActivity().startActivity(i);
			}
		});
    	return vupload;
    }

	private void GetDataUpload(String Url){
		tvstatus.setVisibility(View.GONE);
		prbstatus.setVisibility(View.VISIBLE);
		JsonObjectRequest jsonget = new JsonObjectRequest(Request.Method.GET, Url, null,
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
								JSONArray JsonArray = response.getJSONArray("uploade");
								for (int i = 0; i < JsonArray.length(); i++) {
									JSONObject object = JsonArray.getJSONObject(i);
									ColBerita colums 	= new ColBerita();
									colums.setIdBerita(object.getInt("i_idberita"));
									colums.setJudul(object.getString("vc_judul"));
									colums.setGambar(object.getString("vc_gambar"));
									colums.setIsiBerita(object.getString("t_isi"));
									colums.setTglTerbit(object.getString("dt_tglterbit"));
									colums.setTglKadaluarsa(object.getString("dt_tglkadaluarsa"));
									colums.setStatusBerita(object.getString("vc_statusberita"));
									colums.setIdJurusan(object.getInt("i_idjurusan"));
									colums.setIdKategori(object.getInt("i_idkategori"));
									colums.setPenulis(object.getString("vc_user"));
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
						lsvupload.invalidate();
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
			JSONArray JsonArray = response.getJSONArray("uploade");
			for (int i = 0; i < JsonArray.length(); i++) {
				JSONObject object = JsonArray.getJSONObject(i);
				ColBerita colums 	= new ColBerita();
				colums.setIdBerita(object.getInt("i_idberita"));
				colums.setJudul(object.getString("vc_judul"));
				colums.setGambar(object.getString("vc_gambar"));
				colums.setIsiBerita(object.getString("t_isi"));
				colums.setTglTerbit(object.getString("dt_tglterbit"));
				colums.setTglKadaluarsa(object.getString("dt_tglkadaluarsa"));
				colums.setStatusBerita(object.getString("vc_statusberita"));
				colums.setIdJurusan(object.getInt("i_idjurusan"));
				colums.setNamaJurusan(object.getString("vc_namajurusan"));
				colums.setIdKategori(object.getInt("i_idkategori"));
				colums.setNamaKategori(object.getString("vc_namakategori"));
				colums.setPenulis(object.getString("vc_user"));
				columnlist.add(colums);
			}
			adapter.notifyDataSetChanged();
			lsvupload.invalidate();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
    	
	@Override
	public void onResume(){
		super.onResume();
		if(tipeBerita.equals(jurusan)){
			GetDataUpload(Link.FilePHP+getJurusan+idBerita);
		}else if(tipeBerita.equals(fakultas)){
			GetDataUpload(Link.FilePHP+getFakultas+idBerita);
		}else{
			GetDataUpload(Link.FilePHP+getExternal+idBerita);
		}
	}
}


