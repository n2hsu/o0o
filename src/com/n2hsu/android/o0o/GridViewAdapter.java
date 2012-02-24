package com.n2hsu.android.o0o;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	private LayoutInflater layoutInft;
	private ArrayList<HashMap<String, Object>> gvItem;

	public GridViewAdapter(Context context,
			ArrayList<HashMap<String, Object>> gridViewItem) {
		this.layoutInft = LayoutInflater.from(context);
		this.gvItem = gridViewItem;
	}

	public int getCount() {
		return gvItem.size();
	}

	public Object getItem(int position) {
		return gvItem.get(position);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View gridView, ViewGroup parent) {

		if (gridView == null) {

			gridView = (LinearLayout) layoutInft.inflate(R.layout.girdview,
					null);

			ImageView iv = (ImageView) gridView.findViewById(R.id.iv);
			TextView tv = (TextView) gridView.findViewById(R.id.tv);
			iv.setImageBitmap((Bitmap)gvItem.get(position).get("image"));
			tv.setText((String)gvItem.get(position).get("title"));
		}

		return gridView;
	}

}
