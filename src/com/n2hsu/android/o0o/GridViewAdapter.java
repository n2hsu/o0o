package com.n2hsu.android.o0o;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter{

	private LayoutInflater layoutInft;
	private ArrayList<HashMap<String,String>> gvItem;
	
	public  GridViewAdapter(Context context,ArrayList<HashMap<String,String>> gridViewItem){
		this.layoutInft = LayoutInflater.from(context);
		this.gvItem = gridViewItem ;
	}
	@Override
	public int getCount() {
		return gvItem.size();
	}
	@Override
	public Object getItem(int position) {
		return gvItem.get(position);
	}
	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View gridView, ViewGroup parent) {
		
		
		if(gridView == null){
			
			gridView = (RelativeLayout) layoutInft.inflate(R.layout.girdview, null);
			
			ImageView iv = (ImageView) gridView.findViewById(R.id.iv);
			TextView tv = (TextView) gridView.findViewById(R.id.tv);
			
//			Bitmap img = BitmapFactory.decodeFile(gvItem.get(position).get("image"));
//			
//			iv.setImageBitmap(img);
			tv.setText(gvItem.get(position).get("title"));
			
			iv.setImageResource(R.drawable.ic_launcher);
		}
		
		return gridView;
	}

}
