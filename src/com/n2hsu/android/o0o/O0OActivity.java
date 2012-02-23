package com.n2hsu.android.o0o;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.n2hsu.android.o0o.rss.RSSChannel;
import com.n2hsu.android.o0o.util.SQLiteOpera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class O0OActivity extends Activity {

	private String CHANNEL_FOLDER = "channel", CONTENT_FOLDER = "content";
	private String EXTRANAME_IMG = ".jpg";
	private String DBNAME = "o0o";
	private int EY_GRID_ROW_NUM = 4, EY_GRID_COLUMN_NUM = 3;
	private String[] RSSURL = { "http://news.google.com/news?ned=us&topic=h&output=rss", };
	private ImageButton setting, sync, share, search;
	private Context context = O0OActivity.this;
	private List<RSSChannel> channel = new ArrayList<RSSChannel>();
	private ViewFlipper imageWall;

	private File channelPath, contentPath;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SQLiteOpera so = new SQLiteOpera(context, DBNAME, null, 1);
		
		SQLiteDatabase sd = so.getWritableDatabase();
		so.onCreate(sd);
		
	}

	boolean checkExtStg() {

		String StgState = Environment.getExternalStorageState();

		if (StgState.equals(Environment.MEDIA_UNMOUNTED)) {
			Toast.makeText(O0OActivity.this, "", Toast.LENGTH_SHORT).show();
			return false;
		} else if (StgState.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			Toast.makeText(O0OActivity.this, "", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	void createIcon() {
	}

	void createPopWindows() {
	}

	OnClickListener onClick = new OnClickListener() {

		@Override
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