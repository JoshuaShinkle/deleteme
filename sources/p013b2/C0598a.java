package p013b2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.webrtc.ThreadUtils;
import p023c2.C0717a;

/* renamed from: b2.a */
/* loaded from: classes.dex */
public class C0598a implements SensorEventListener {

    /* renamed from: b */
    public final Runnable f3140b;

    /* renamed from: c */
    public final SensorManager f3141c;

    /* renamed from: a */
    public final ThreadUtils.ThreadChecker f3139a = new ThreadUtils.ThreadChecker();

    /* renamed from: d */
    public Sensor f3142d = null;

    /* renamed from: e */
    public boolean f3143e = false;

    /* renamed from: f */
    public final float f3144f = 5.0f;

    public C0598a(Context context, Runnable runnable) {
        Log.d("RTCProximitySensor", "RTCProximitySensor" + C0717a.m3547b());
        this.f3140b = runnable;
        this.f3141c = (SensorManager) context.getSystemService("sensor");
    }

    /* renamed from: a */
    public static C0598a m3291a(Context context, Runnable runnable) {
        return new C0598a(context, runnable);
    }

    /* renamed from: b */
    public final boolean m3292b() {
        if (this.f3142d != null) {
            return true;
        }
        Sensor defaultSensor = this.f3141c.getDefaultSensor(8);
        this.f3142d = defaultSensor;
        if (defaultSensor == null) {
            return false;
        }
        m3294d();
        return true;
    }

    /* renamed from: c */
    public final boolean m3293c() {
        String str = Build.MANUFACTURER;
        if (str.equals("Xiaomi") && Build.MODEL.equals("Mi 4i")) {
            return true;
        }
        return str.equals("LENOVO") && Build.MODEL.equals("Lenovo A788t");
    }

    /* renamed from: d */
    public final void m3294d() {
        if (this.f3142d == null) {
            return;
        }
        Log.d("RTCProximitySensor", "Proximity sensor: name=" + this.f3142d.getName() + ", vendor: " + this.f3142d.getVendor() + ", power: " + this.f3142d.getPower() + ", resolution: " + this.f3142d.getResolution() + ", max range: " + this.f3142d.getMaximumRange() + ", min delay: " + this.f3142d.getMinDelay() + ", type: " + this.f3142d.getStringType() + ", max delay: " + this.f3142d.getMaxDelay() + ", reporting mode: " + this.f3142d.getReportingMode() + ", isWakeUpSensor: " + this.f3142d.isWakeUpSensor());
    }

    /* renamed from: e */
    public boolean m3295e() {
        this.f3139a.checkIsOnValidThread();
        return this.f3143e;
    }

    /* renamed from: f */
    public boolean m3296f() {
        this.f3139a.checkIsOnValidThread();
        Log.d("RTCProximitySensor", TtmlNode.START + C0717a.m3547b());
        if (!m3292b()) {
            return false;
        }
        this.f3141c.registerListener(this, this.f3142d, 3);
        return true;
    }

    /* renamed from: g */
    public void m3297g() {
        this.f3139a.checkIsOnValidThread();
        Log.d("RTCProximitySensor", "stop" + C0717a.m3547b());
        Sensor sensor = this.f3142d;
        if (sensor == null) {
            return;
        }
        this.f3141c.unregisterListener(this, sensor);
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i9) {
        this.f3139a.checkIsOnValidThread();
        C0717a.m3546a(sensor.getType() == 8);
        if (i9 == 0) {
            Log.e("RTCProximitySensor", "The values returned by this sensor cannot be trusted");
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        this.f3139a.checkIsOnValidThread();
        C0717a.m3546a(sensorEvent.sensor.getType() == 8);
        if (sensorEvent.values[0] < (m3293c() ? 5.0f : this.f3142d.getMaximumRange())) {
            Log.d("RTCProximitySensor", "Proximity sensor => NEAR state");
            this.f3143e = true;
        } else {
            Log.d("RTCProximitySensor", "Proximity sensor => FAR state");
            this.f3143e = false;
        }
        Runnable runnable = this.f3140b;
        if (runnable != null) {
            runnable.run();
        }
        Log.d("RTCProximitySensor", "onSensorChanged" + C0717a.m3547b() + ": accuracy=" + sensorEvent.accuracy + ", timestamp=" + sensorEvent.timestamp + ", distance=" + sensorEvent.values[0]);
    }
}
