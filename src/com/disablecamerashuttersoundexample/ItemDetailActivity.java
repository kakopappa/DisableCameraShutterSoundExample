package com.disablecamerashuttersoundexample;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;

 
public class ItemDetailActivity extends Activity {
	private static final String TAG = "ItemDetailActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		
		int cameraId = 0;
		
		Camera camera = Camera.open(cameraId);
		
		disableShutterSound(camera, cameraId);
	}
	
	private void disableShutterSound(Camera camera, int cameraId) {

		try {
			if (hasField(Camera.CameraInfo.class, "canDisableShutterSound")) {
				Field canDisableField = null;
				Camera.CameraInfo info = new Camera.CameraInfo();
				Camera.getCameraInfo(cameraId, info);
				
				canDisableField = Camera.CameraInfo.class.getDeclaredField("canDisableShutterSound");
				canDisableField.setAccessible(true);
				Boolean canDisableShutterSound = (Boolean) canDisableField.get(info);

				if (canDisableShutterSound) {
					Method method = Camera.class.getMethod("enableShutterSound", boolean.class);

					if (method != null) {
						method.setAccessible(true);
						method.invoke(camera, false);
					}
				}
			}
		} catch (Exception e) {
			Log.e(TAG, "disableShutterSound # failed!", e);
		}
	}
	
	private boolean hasField(Class<?> klass, String fieldName) {
		try {
			klass.getDeclaredField(fieldName);
			return true;
		} catch (NoSuchFieldException e) {
			return false;
		}
	}
}
