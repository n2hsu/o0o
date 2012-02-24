package com.n2hsu.android.o0o.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpera extends SQLiteOpenHelper {

	public SQLiteOpera(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public synchronized void close() {
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE ["
				+ Tool.T_TABLE
				+ "] ("
				+ "[idchannel] INTEGER DEFAULT '''0''' PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ "[title] VARCHAR(50)  NOT NULL,"
				+ "[imageurl] VARCHAR(200)  NULL,"
				+ "[imagelocal] VARCHAR(200)  NOT NULL,"
				+ "[description] VARCHAR(150)  NULL,"
				+ "[link] vaRCHAR(50) DEFAULT 'null' NOT NULL,"
				+ "[language] VARCHAR(10) DEFAULT 'en' NULL,"
				+ "[ttl] vaRCHAR(10) DEFAULT 'null' NULL,"
				+ "[generator] VARCHAR(20) DEFAULT 'null' NULL,"
				+ "[copyright] VARCHAR(50)  NULL,"
				+ "[pubdate] VARCHAR(100)  NULL,"
				+ "[webmaster] VARCHAR(30)  NULL,"
				+ "[lastbuilddate] VARCHAR(30)  NULL,"
				+ "[xmlurl] VARCHAR(50)  NOT NULL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
