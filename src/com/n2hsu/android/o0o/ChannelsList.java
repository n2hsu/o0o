package com.n2hsu.android.o0o;

import com.n2hsu.android.o0o.util.SQLiteOpera;
import com.n2hsu.android.o0o.util.Tool;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ChannelsList extends Activity {

	private Context context = ChannelsList.this;
	private SQLiteDatabase sd = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.addchannels);
		
		sd = new SQLiteOpera(context, Tool.DBNAME, null, Tool.DB_VERSION).getWritableDatabase();
		
		ListView category = (ListView) findViewById(R.id.category);
		
		Button bt = (Button) findViewById(R.id.button);
		
		category.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categ));
		
		bt.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				ContentValues cv = new ContentValues();
				
				cv.put(Tool.TB_TITLE, "World - Google News");
				cv.put(Tool.TB_IMAGEURL, "http://www.gstatic.com/news/img/logo/en_us/news.gif");
				cv.put(Tool.TB_IMAGELOCAL, "");
				cv.put(Tool.TB_DESCRIPTION, "Google News");
				cv.put(Tool.TB_LINK, "http://news.google.com/news?ned=us&hl=en&topic=w");
				cv.put(Tool.TB_LANGUAGE, "en");
				cv.put(Tool.TB_TTL, "");
				cv.put(Tool.TB_GENERATOR, "NFE/1.0");
				cv.put(Tool.TB_COPYRIGHT, "&copy;2012 Google");
				cv.put(Tool.TB_PUBDATE, "Tue, 17 Jan 2012 13:27:04 GMT");
				cv.put(Tool.TB_WEBMASTER, "news-feedback@google.com");
				cv.put(Tool.TB_LASTBUILDDATE, "Tue, 17 Jan 2012 13:27:04 GMT");
				cv.put(Tool.TB_XMLURL, "http://news.google.com/news?ned=us&topic=w&output=rss");
				
				sd.insert(Tool.T_TABLE, null, cv);
			}
		});
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		sd.close();
	}

	private String[] categ = { "Search", "Featured", "Recommended", "News",
			"Business", "Design", "Entertainment", "LifeStyle",
			"Science & Tech", "Sport", "Other", "Google Reader", "Curators",
			"My Library" };

}
