package com.n2hsu.android.o0o.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.os.Environment;

public class Tool {

	public final static String 	T_TABLE = "channel";
	public final static String	DBNAME = "o0o";
	public final static int 	DB_VERSION = 1;

	public final static String CHANNEL_FOLDER = "channel";
	public final static String CONTENT_FOLDER = "content";

	public final static String EXTRANAME_IMG = ".jpg";

	public final static int EY_GRID_ROW_NUM = 4;
	public final static int EY_GRID_COLUMN_NUM = 3;
	
	public final static char SPACE = ' ';
	public final static char SGQUOTE = '\'';
	public final static char EQUALQ = '=';
	
	public final static String TB_TITLE = "title";
	public final static String TB_IMAGEURL = "imageurl";
	public final static String TB_IMAGELOCAL = "imagelocal";
	public final static String TB_DESCRIPTION = "description";
	public final static String TB_LINK = "link";
	public final static String TB_LANGUAGE = "language";
	public final static String TB_TTL = "ttl";
	public final static String TB_GENERATOR = "generator";
	public final static String TB_COPYRIGHT = "copyright";
	public final static String TB_PUBDATE = "pubdate";
	public final static String TB_WEBMASTER = "webmaster";
	public final static String TB_LASTBUILDDATE = "lastbuilddate";
	public final static String TB_XMLURL ="xmlurl";
	
	public final static int LENGTH_CHANNEL_IMAGE = 1024*100;

	public static boolean checkExtStg() {

		String StgState = Environment.getExternalStorageState();

		if (StgState.equals(Environment.MEDIA_UNMOUNTED)) {
			return false;
		} else if (StgState.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			return false;
		}
		return true;
	}
	
	// 过滤特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		if (str == null) {
			return null;
		}
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
