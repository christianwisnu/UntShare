package untshare.progsby.com.untshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import controll.Link;
import controll.Utils;
import session.SessionManager;

public class Detail extends FragmentActivity{

	private TextView tvJudul, tvPengarang, tvJenisBerita, tvTglTerbit, tvIsiBerita;
	private ImageView imgback, imgBerita;
	private String idBerita, judul, gambar, isiBerita, tglTerbit, tipeBerita, penulis;
	private Button btnKomen;
	private RelativeLayout kolomJudul;
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
	private SessionManager session;
	private static Object sid="idUser";
	private  static Object simage ="image";
	private static Object snama ="username";
	private static Object statusUser ="status";
	private static Object sNIK ="nik";
	private static Object sNamaLengkap ="nama";
	private static Object sTelp ="telp";
	private static Object sEmail ="email";
	private static Object sAlamat ="alamat";
	private static Object sJurusan ="jurusan";
	private static Object sNamaJurusan ="namaJurusan";
	private static Object sFakultas ="fakultas";
	private static Object sNamaFakultas ="namaFakultas";
	private HashMap<String, Object> user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		Bundle i = getIntent().getExtras();
		session =new SessionManager(getApplicationContext());
		session.checkLogin();
		try {
			user = session.getUserDetails();
			sid		= user.get(SessionManager.sid);
			snama	= user.get(SessionManager.sUserName);
			simage	= user.get(SessionManager.simage);
			statusUser = user.get(SessionManager.statusAdmin);
			sNIK = user.get(SessionManager.sNIK);
			sNamaLengkap = user.get(SessionManager.sNamaLengkap);
			sTelp = user.get(SessionManager.sTelp);
			sEmail = user.get(SessionManager.sEmail);
			sAlamat = user.get(SessionManager.sAlamat);
			sJurusan = user.get(SessionManager.sJurusan);
			sNamaJurusan = user.get(SessionManager.sNamaJurusan);
			sFakultas = user.get(SessionManager.sFakultas);
			sNamaFakultas = user.get(SessionManager.sNamaFakultas);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(i!=null){
			idBerita	= i.getString("idBerita");
			judul		= i.getString("judul");
			gambar		= i.getString("gambar");
			isiBerita	= i.getString("isiBerita");
			tglTerbit	= i.getString("tglTerbit");
			tipeBerita	= i.getString("jenisBerita");
			penulis		= i.getString("penulis");
		}

		imgback			= (ImageView)findViewById(R.id.ImgDetailBack);
		imgBerita		= (ImageView)findViewById(R.id.ImgDetailGambarBerita);
		tvJudul			= (TextView)findViewById(R.id.txtDetailJudul);
		tvPengarang		= (TextView)findViewById(R.id.txtDetailPengarangBerita);
		tvJenisBerita	= (TextView)findViewById(R.id.txtDetailJenisBerita);
		tvTglTerbit		= (TextView)findViewById(R.id.txtDetailTglTerbit);
		tvIsiBerita		= (TextView)findViewById(R.id.txtDetailIsiBerita);
		btnKomen		= (Button)findViewById(R.id.btnDetailKomentar);
		kolomJudul		= (RelativeLayout)findViewById(R.id.kolomJudulDetail);

		Utils.GetImage(Link.FileImage+gambar, imgBerita, Detail.this);
		tvJudul.setText(judul);
		tvPengarang.setText(penulis+" -");
		tvJenisBerita.setText(tipeBerita);
		String hari = Utils.getHari(tglTerbit);
		tvTglTerbit.setText(hari+", "+tglTerbit+" WIB");
		tvIsiBerita.setText(Html.fromHtml(isiBerita));

		if(tipeBerita.equals(jurusan)){
			kolomJudul.setBackgroundResource(R.drawable.cardblue);
		}else if(tipeBerita.equals(fakultas)){
			kolomJudul.setBackgroundResource(R.drawable.cardblue);
		}else if(tipeBerita.equals(politik)){
			kolomJudul.setBackgroundResource(R.drawable.cardfinance);
		}else if(tipeBerita.equals(OR)){
			kolomJudul.setBackgroundResource(R.drawable.cardsport);
		}else if(tipeBerita.equals(spkBl)){
			kolomJudul.setBackgroundResource(R.drawable.cardfootball);
		}else if(tipeBerita.equals(food)){
			kolomJudul.setBackgroundResource(R.drawable.cardfood);
		}else if(tipeBerita.equals(hukum)){
			kolomJudul.setBackgroundResource(R.drawable.cardfinance);
		}else if(tipeBerita.equals(kesehatan)){
			kolomJudul.setBackgroundResource(R.drawable.cardhealth);
		}else if(tipeBerita.equals(lifestyle)){
			kolomJudul.setBackgroundResource(R.drawable.cardlifestyle);
		}else if(tipeBerita.equals(otomotif)){
			kolomJudul.setBackgroundResource(R.drawable.cardoto);
		}else if(tipeBerita.equals(traveling)){
			kolomJudul.setBackgroundResource(R.drawable.cardtravel);
		}else if(tipeBerita.equals(fotografi)){
			kolomJudul.setBackgroundResource(R.drawable.cardfoto);
		}else if(tipeBerita.equals(teknologi)){
			kolomJudul.setBackgroundResource(R.drawable.cardteknologi);
		}else if(tipeBerita.equals(finance)){
			kolomJudul.setBackgroundResource(R.drawable.cardfinance);
		}

		imgback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		btnKomen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i  = new Intent(getWindow().getContext(), Comment.class);
				i.putExtra("idBerita", idBerita);
				i.putExtra("idUser", String.valueOf(sid));
				startActivity(i);
			}
		});
	}
}


	  




