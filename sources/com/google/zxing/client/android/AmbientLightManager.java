package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.camera.FrontLightMode;

/* loaded from: classes2.dex */
final class AmbientLightManager implements SensorEventListener {
    private static final float BRIGHT_ENOUGH_LUX = 450.0f;
    private static final float TOO_DARK_LUX = 45.0f;
    private CameraManager cameraManager;
    private final Context context;
    private Sensor lightSensor;

    public AmbientLightManager(Context context) {
        this.context = context;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i9) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f9 = sensorEvent.values[0];
        CameraManager cameraManager = this.cameraManager;
        if (cameraManager != null) {
            if (f9 <= TOO_DARK_LUX) {
                cameraManager.setTorch(true);
            } else if (f9 >= BRIGHT_ENOUGH_LUX) {
                cameraManager.setTorch(false);
            }
        }
    }

    public void start(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
        if (FrontLightMode.readPref(PreferenceManager.getDefaultSharedPreferences(this.context)) == FrontLightMode.AUTO) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(5);
            this.lightSensor = defaultSensor;
            if (defaultSensor != null) {
                sensorManager.registerListener(this, defaultSensor, 3);
            }
        }
    }

    public void stop() {
        if (this.lightSensor != null) {
            ((SensorManager) this.context.getSystemService("sensor")).unregisterListener(this);
            this.cameraManager = null;
            this.lightSensor = null;
        }
    }
}
