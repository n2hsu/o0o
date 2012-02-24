package com.n2hsu.android.o0o;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpConnection;

import com.n2hsu.android.o0o.rss.RSSChannel;
import com.n2hsu.android.o0o.util.SQLiteOpera;
import com.n2hsu.android.o0o.util.Tool;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class O0OActivity extends Activity {

	private String[] RSSURL = { "http://news.google.com/news?ned=us&topic=h&output=rss" };

	private Context context = O0OActivity.this;

	private List<RSSChannel> channel = new ArrayList<RSSChannel>();

	private File channelPath = null;
	private File contentPath = null;

	private SQLiteOpera so = null;
	private SQLiteDatabase sd = null;

	private ViewFlipper imageWall = null;
	private ImageButton setting, sync, share, search;
	private GridView gridlines = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initConst();
		
		initView();
		
		initSQLite();

//		DB2RSSChannel();

		Network2File_Image();
		
//		GridViewChannel();
		
	}
	
	void initConst(){
		
		channelPath = this.getExternalFilesDir(Tool.CHANNEL_FOLDER);
		contentPath = this.getExternalFilesDir(Tool.CONTENT_FOLDER);
	}

	void initView(){
		
		gridlines = (GridView) findViewById(R.id.gridlines);
	}
	
	void initSQLite() {

		so = new SQLiteOpera(context, Tool.DBNAME, null, Tool.DB_VERSION);
		sd = so.getWritableDatabase();

		String sql = "select count(*) from " + Tool.T_TABLE;

		Cursor c = sd.rawQuery(sql, null);
		c.moveToNext();
		if (c.getInt(0) == 0) {

			c.close();
			sd.close();
			so.close();

			Intent it = new Intent(O0OActivity.this, ChannelsList.class);
			////无法完成跳转
			startActivity(it);
		}
	}

	void DB2RSSChannel() {

		String[] columns = { Tool.TB_TITLE, Tool.TB_IMAGEURL,
				Tool.TB_IMAGELOCAL, Tool.TB_DESCRIPTION, Tool.TB_LINK,
				Tool.TB_LANGUAGE, Tool.TB_TTL, Tool.TB_GENERATOR,
				Tool.TB_COPYRIGHT, Tool.TB_PUBDATE, Tool.TB_WEBMASTER,
				Tool.TB_LASTBUILDDATE, Tool.TB_XMLURL };

		Cursor c = sd
				.query(Tool.T_TABLE, columns, null, null, null, null, null);

		c.moveToNext();

		while (!c.isAfterLast()) {

			RSSChannel rssChannel = new RSSChannel();

			rssChannel.setTitle(c.getString(0));
			rssChannel.setImageUrl(c.getString(1));
			rssChannel.setImageLocal(c.getString(2));
			rssChannel.setDescription(c.getString(3));
			rssChannel.setLink(c.getString(4));
			rssChannel.setLanguage(c.getString(5));
			rssChannel.setTtl(c.getString(6));
			rssChannel.setGenerator(c.getString(7));
			rssChannel.setCopyright(c.getString(8));
			rssChannel.setPubDate(c.getString(9));
			rssChannel.setWebMaster(c.getString(10));
			rssChannel.setLastBuildDate(c.getString(11));
			try {
				rssChannel.setXmlURL(new URL(c.getString(12)));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			System.out.println(rssChannel.getCopyright());

			channel.add(rssChannel);

			c.moveToNext();
		}
		
		c.close();
	}

	void Network2File_Image() {

		Thread imageThread = new Thread() {

			byte[] buf = new byte[Tool.LENGTH_CHANNEL_IMAGE];
			FileOutputStream oStream = null;
			@Override
			public void run() {
				super.run();
				for (int x = 0; x < channel.size(); x++) {

					try {
						URL imgUrl = new URL(channel.get(x).getImageUrl());
						HttpURLConnection httpConn = (HttpURLConnection) imgUrl
								.openConnection();

						InputStream iStream = httpConn.getInputStream();
						int lgth = iStream.read(buf);
						
						if(Tool.checkExtStg()){
							
							File imgFile = new File(channelPath ,channel.get(x).getTitle());
							oStream = new FileOutputStream(imgFile);
							oStream.write(buf, 0, lgth);
							oStream.close();
							
							channel.get(x).setImageLocal(imgFile.getPath());
							ContentValues cv = new ContentValues();
							cv.put(Tool.TB_IMAGELOCAL, imgFile.getPath());
							
							sd.update(Tool.T_TABLE, cv, Tool.SPACE+Tool.TB_TITLE+Tool.EQUALQ+Tool.SGQUOTE+channel.get(x).getTitle()+Tool.SGQUOTE,null);
							
						}else{
							Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
						}
						
						iStream.close();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		imageThread.start();
		
	}

	void GridViewChannel(){
		
		ArrayList<HashMap<String,Object>> gvItems = new ArrayList<HashMap<String,Object>>();
		for(int x = 0; x < channel.size(); x ++){
			
			HashMap<String,Object> hash = new HashMap<String,Object>();
			
			Bitmap b = BitmapFactory.decodeFile(channel.get(x).getImageLocal());
			hash.put("image",b);
			hash.put("title", channel.get(x).getTitle());
			
			gvItems.add(hash);
		}
		GridViewAdapter gvAdapter = new GridViewAdapter(context, gvItems);
		gridlines.setAdapter(gvAdapter);
	}

	void createIcon() {
	}

	void createPopWindows() {
	}

	OnClickListener onClick = new OnClickListener() {

		public void onClick(View v) {

			if (v == sync) {
			} else if (v == share) {

				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_SUBJECT, "Title");
				intent.putExtra(Intent.EXTRA_TEXT, "content");
				startActivity(Intent.createChooser(intent, "Share "
						+ getTitle()));
			}
		}
	};
}