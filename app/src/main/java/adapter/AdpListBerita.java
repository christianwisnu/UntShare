package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import controll.Link;
import controll.Utils;
import model.ColBerita;
import untshare.progsby.com.untshare.R;

public class AdpListBerita extends ArrayAdapter<ColBerita> {

	private List<ColBerita> columnslist;
	private LayoutInflater vi;
	private int Resource;
	private int lastPosition = -1;
	private ViewHolder holder;
	private Activity parent;
	private Context context;
	private ListView lsvchoose;
	private String GetUpload	="getListBerita.php?IdUser=";
	private static final int SEND_UPLOAD = 201;

	public AdpListBerita(Context context, int resource, List<ColBerita> objects) {
		super(context, resource,  objects);
		this.context = context;
		vi	=	(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource		= resource;
		columnslist		= objects;
	}

	@Override
	public View getView (final int position, View convertView, final ViewGroup parent){
		View v	=	convertView;
		if (v == null){
			holder	=	new ViewHolder();
			v= vi.inflate(Resource, null);
			holder.ImgGmbar		= 	 (ImageView)v.findViewById(R.id.ImgColUserUploadBerita);
			holder.TvDate		=	 (TextView)v.findViewById(R.id.TvColUserUploadDate);
			holder.TvJudul		=	 (TextView)v.findViewById(R.id.TvColJudulUpload);
			//holder.Id					= 0;
			//holder.IdUser				= 0;
			v.setTag(holder);
		}else{
			holder 	= (ViewHolder)v.getTag();
		}

		String url 		= Link.FileImage+columnslist.get(position).getGambar();
		Utils.CurrentTime(columnslist.get(position).getTglTerbit(), holder.TvDate);
		holder.TvJudul.setText(columnslist.get(position).getJudul());
		String hari = Utils.getHari(columnslist.get(position).getTglTerbit());
		holder.TvDate.setText(hari+", "+columnslist.get(position).getTglTerbit()+" WIB");
		if (holder.ImgGmbar.getTag()==null||!holder.ImgGmbar.getTag().equals(url)){
			Utils.GetImage(url, holder.ImgGmbar, getContext());
			holder.ImgGmbar.setTag(url);
		}
		return v;
	}

	static class ViewHolder{
		private ImageView ImgGmbar;
		private TextView TvJudul;
		private TextView TvDate;
	}
}



