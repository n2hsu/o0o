package com.n2hsu.android.o0o;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.n2hsu.android.o0o.rss.RSSChannel;
import com.n2hsu.android.o0o.rss.RSSItem;
import com.n2hsu.android.o0o.util.Tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

public class GetContentAsyncTask extends
		AsyncTask<List<RSSChannel>, Void, Void> {

	// private Handler handler;
	private File channelPath, contentPath;

	public GetContentAsyncTask(Handler hd, File cnPath, File ctPath) {

		// this.handler = hd;
		this.channelPath = cnPath;
		this.contentPath = ctPath;
	}

	@Override
	protected Void doInBackground(List<RSSChannel>... channel) {

		for (int x = 0; x < channel[0].size(); x++) {

			AnalyzeXML(channel[0].get(x));
			writeFile_RSSChannel(channelPath, channel[0].get(x));
			writeFile_RSSItem(contentPath, channel[0].get(x));
		}

		// Message msg = new Message();
		// msg.what = 0x001;
		// this.handler.sendMessage(msg);
		return null;

	}

	private void AnalyzeXML(RSSChannel channel) {

		RSSItem item = null;

		try {
			HttpURLConnection conn = (HttpURLConnection) channel.getXmlURL()
					.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(reader);

			int eventType = xpp.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {

				if (eventType == XmlPullParser.START_TAG) {
					if (xpp.getName().equals("item")) {
						item = new RSSItem();
						while (true) {
							eventType = xpp.next();
							if (eventType == XmlPullParser.START_TAG) {

								if (xpp.getName().equals("title")) {
									item.setTitle(xpp.nextText());
									// Log.i("title", item.getTitle());
								} else if (xpp.getName().equals("link")) {
									item.setLink(xpp.nextText());
									// Log.i("link", item.getLink());
								} else if (xpp.getName().equals("description")) {
									item.setDescription(xpp.nextText());
								} else if (xpp.getName().equals("pubDate")) {
									item.setPubDate(xpp.nextText());
									// Log.i("pubDate", item.getPubDate());
								}
							} else if (eventType == XmlPullParser.END_TAG) {
								if (xpp.getName().equals("item")) {
									channel.getItem().add(item);
									break;
								}
							}
						}
					} else if (xpp.getName().equals("title")
							&& xpp.getDepth() == 3) {
						channel.setTitle(xpp.nextText());
					} else if (xpp.getName().equals("url")
							&& xpp.getDepth() == 4) {
						channel.setImageUrl(xpp.nextText());
						Log.i("Img", channel.getImageUrl());
					} else if (xpp.getName().equals("description")
							&& xpp.getDepth() == 3) {
						channel.setDescription(xpp.nextText());
					} else if (xpp.getName().equals("copyright")
							&& xpp.getDepth() == 3) {
						channel.setCopyright(xpp.nextText());
					} else if (xpp.getName().equals("generator")
							&& xpp.getDepth() == 3) {
						channel.setGenerator(xpp.nextText());

					} else if (xpp.getName().equals("language")
							&& xpp.getDepth() == 3) {
						channel.setLanguage(xpp.nextText());

					} else if (xpp.getName().equals("lastBuildDate")
							&& xpp.getDepth() == 3) {
						channel.setLastBuildDate(xpp.nextText());
					} else if (xpp.getName().equals("link")
							&& xpp.getDepth() == 3) {
						channel.setLink(xpp.nextText());
					} else if (xpp.getName().equals("lastBuildDate")
							&& xpp.getDepth() == 3) {
						channel.setLastBuildDate(xpp.nextText());
					} else if (xpp.getName().equals("ttl")
							&& xpp.getDepth() == 3) {
						channel.setTtl(xpp.nextText());
					} else if (xpp.getName().equals("webMaster")
							&& xpp.getDepth() == 3) {
						channel.setWebMaster(xpp.nextText());
					}
				}
				eventType = xpp.next();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
	}

	void writeFile_RSSChannel(File channelPath, RSSChannel channel) {

		File channelInfo = null, channelImage = null;
		FileWriter fw = null;

		try {
			channelInfo = new File(channelPath, Tool.StringFilter(channel
					.getTitle()));
			channelImage = new File(channelPath, Tool.StringFilter(channel
					.getTitle())+ ".jpg");

			fw = new FileWriter(channelInfo);
			fw.write(channel.getTitle());
			fw.close();

			if (channel.getImageUrl() != null) {

				HttpURLConnection conn = (HttpURLConnection) new URL(
						channel.getImageUrl()).openConnection();
				conn.setDoInput(true);
				conn.connect();

				Bitmap image = BitmapFactory
						.decodeStream(conn.getInputStream());

				image.compress(Bitmap.CompressFormat.JPEG, 100,
						new FileOutputStream(channelImage));
			}else{
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeFile_RSSItem(File contentPath, RSSChannel channel) {

		FileWriter fw = null;
		File file = null;
		try {

			file = new File(contentPath, Tool.StringFilter(channel.getTitle()));

			fw = new FileWriter(file);

			for (int x = 0; x < channel.getItem().size(); x++) {

				fw.append(channel.getItem().get(x).getTitle() + "\n"
						+ channel.getItem().get(x).getCategory() + "\n"
						+ channel.getItem().get(x).getLink() + "\n"
						+ channel.getItem().get(x).getDescription() + "\n"
						+ channel.getItem().get(x).getPubDate() + "\n");

			}
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
