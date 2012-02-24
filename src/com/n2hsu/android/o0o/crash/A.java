//package com.n2hsu.android.o0o.crash;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import android.content.ComponentName;
//import android.content.Intent;
//import android.content.Intent.ShortcutIconResource;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Handler;
//import android.os.Message;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.n2hsu.android.o0o.O0OActivity;
//import com.n2hsu.android.o0o.R;
//
//public class A {
//
//	Intent shortcut = new Intent(
//			"com.android.launcher.action.INSTALL_SHORTCUT");
//
//	ComponentName comp = new ComponentName(this.getPackageName(), "."
//			+ this.getLocalClassName());
//	ShortcutIconResource icon = Intent.ShortcutIconResource.fromContext(
//			O0OActivity.this, R.drawable.ic_launcher);
//
//	shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
//			getString(R.string.app_name));
//	shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(
//			Intent.ACTION_MAIN).setComponent(comp));
//	shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
//	shortcut.putExtra("duplicate", true);
//
//	sendBroadcast(shortcut);
//
//	Toast.makeText(O0OActivity.this, "", Toast.LENGTH_SHORT).show();
//	
//	
//	
//	void getImages() {
//
//		for (int x = 0; x < channel.get(0).getItem().size(); x++) {
//
//			final ImageView iv = new ImageView(context);
//			imageWall.addView(iv);
//
//			final Handler handler = new Handler() {
//
//				@Override
//				public void handleMessage(Message msg) {
//					super.handleMessage(msg);
//
//					if (msg.what == 0) {
//						Bitmap b = (Bitmap) msg.obj;
//						iv.setImageBitmap(b);
//					}
//				}
//			};
//
//			Thread thread = new Thread() {
//
//				@Override
//				public void run() {
//					super.run();
//					String url = "http://ww4.sinaimg.cn/bmiddle/612edf3ajw1dow531dfpnj.jpg";
//
//					try {
//						HttpURLConnection conn = (HttpURLConnection) new URL(
//								url).openConnection();
//						conn.setDoInput(true);
//						conn.connect();
//						InputStream is = conn.getInputStream();
//						Bitmap bitmap = BitmapFactory.decodeStream(is);
//						is.close();
//						Message msg = new Message();
//						msg.what = 0;
//						msg.obj = bitmap;
//
//						handler.sendMessage(msg);
//					} catch (MalformedURLException e) {
//						e.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			};
//			thread.start();
//		}
//	}
//}
