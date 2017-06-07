package adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import model.ColComment;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import controll.Link;
import controll.Utils;
import untshare.progsby.com.untshare.R;

public class AdpCommenet extends ArrayAdapter<ColComment> {
	private List<ColComment> columnslist; //diambil Dari JColum Tempat Mendaftar String Data
	private LayoutInflater vi;
	private int Resource;
	private ViewHolder holder;
	private Context context;
	private ListView lsvchoose;

	private static final int SEND_UPLOAD = 201;
	public AdpCommenet(Context context, int resource, List<ColComment> objects) {
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
			holder.ImgUser				= 	 (ImageView)v.findViewById(R.id.ImgColCommentUser);
			holder.TvUser				= 	 (TextView)v.findViewById(R.id.TvColCommentNameUser);
			holder.TvDate				=	 (TextView)v.findViewById(R.id.TvColCommentTime);
			holder.TvDescription		=	 (TextView)v.findViewById(R.id.TvColCommentMessage);
			//holder.IdUpload				= 0;
			//holder.IdUser				= 0;
			v.setTag(holder);

		}else{
			holder 	= (ViewHolder)v.getTag();

		}

		String url 		= Link.FileImage+columnslist.get(position).getFoto();
		/*String hari = Utils.getHari(columnslist.get(position).getTglTerbit());
		holder.TvDate.setText(hari+", "+columnslist.get(position).getTglTerbit()+" WIB");*/
		Utils.CurrentTime(columnslist.get(position).getTglKomentar(), holder.TvDate);
		holder.TvDescription.setText(columnslist.get(position).getIsiComent());
		holder.TvUser.setText(columnslist.get(position).getUserName());

		if (holder.ImgUser.getTag()==null||!holder.ImgUser.getTag().equals(url)){
			Utils.GetCycleImage(url, holder.ImgUser, getContext());
			holder.ImgUser.setTag(url);
		}
		return v;
	}

	static class ViewHolder{
		private ImageView ImgUser;
		private TextView TvDescription;
		private TextView TvUser;
		private TextView TvDate;
	}
}



