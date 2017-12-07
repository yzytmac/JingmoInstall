package com.example.jngmoinstall;

import java.io.File;
import java.lang.reflect.Method;

import android.R.integer;
import android.app.Activity;
import android.content.pm.IPackageInstallObserver;
import android.content.pm.IPackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private EditText editText;
	public static final int INSTALL_REPLACE_EXISTING = 0x00000002;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.ed);
		Button bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+editText.getText().toString().trim();
		runInstall(path);

	}
	
	private void runInstall(String path) {
		// 静默安装
				IPackageManager mPm;
				try {
					Class<?> forName = Class.forName("android.os.ServiceManager");
					Method method = forName.getMethod("getService", String.class);
					IBinder iBinder = (IBinder) method.invoke(null, "package");
					Log.e("yzy", "iBinder------------:");
					mPm = IPackageManager.Stub.asInterface(iBinder);
					File apkFile = new File(path);
					mPm.installPackage(Uri.fromFile(apkFile), new MyObserver(), INSTALL_REPLACE_EXISTING, apkFile.getPath());
					Toast.makeText(this, "安装成功", 0).show();
				} catch (Exception e) {
					Log.e("yzy", "Exception:"+e.toString());
					Toast.makeText(this, "安装失败", 0).show();
				}
	}
	
	private class MyObserver extends IPackageInstallObserver.Stub{

		@Override
		public void packageInstalled(String packageName, int returnCode)
				throws RemoteException {
			Log.e("yzy", "returnCode:"+returnCode);
			
		}
	}

}
