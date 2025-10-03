package com.cyberlink.clrtc.p031ve;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.view.Surface;
import com.cyberlink.meeting.page.p032m.MeetingActivity;
import com.cyberlink.p030U.R;
import com.cyberlink.you.Globals;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ScreenCaptureService extends Service {

    /* renamed from: e */
    public static ScreenCaptureService f5647e;

    /* renamed from: f */
    public static final List<AbstractC1145a> f5648f = new ArrayList();

    /* renamed from: b */
    public VirtualDisplay f5649b;

    /* renamed from: c */
    public MediaProjection f5650c;

    /* renamed from: d */
    public MediaProjectionManager f5651d;

    /* renamed from: com.cyberlink.clrtc.ve.ScreenCaptureService$a */
    public static abstract class AbstractC1145a {

        /* renamed from: a */
        public final String f5652a;

        public AbstractC1145a(String str) {
            this.f5652a = str;
        }

        /* renamed from: b */
        public abstract void mo5177b();
    }

    /* renamed from: a */
    public static void m5166a(AbstractC1145a abstractC1145a) {
        f5648f.add(abstractC1145a);
    }

    /* renamed from: b */
    public static void m5167b(int i9, int i10, SurfaceTexture surfaceTexture) {
        ScreenCaptureService screenCaptureService = f5647e;
        if (screenCaptureService != null) {
            screenCaptureService.m5171c(i9, i10, surfaceTexture);
        }
    }

    /* renamed from: e */
    public static boolean m5168e() {
        ScreenCaptureService screenCaptureService = f5647e;
        return screenCaptureService != null && screenCaptureService.m5173f();
    }

    /* renamed from: g */
    public static void m5169g(Intent intent, int i9, int i10, MediaProjection.Callback callback, SurfaceTexture surfaceTexture, Handler handler) {
        ScreenCaptureService screenCaptureService = f5647e;
        if (screenCaptureService != null) {
            screenCaptureService.m5174h(intent, i9, i10, callback, surfaceTexture, handler);
        }
    }

    /* renamed from: i */
    public static void m5170i(MediaProjection.Callback callback) {
        ScreenCaptureService screenCaptureService = f5647e;
        if (screenCaptureService != null) {
            screenCaptureService.m5175j(callback);
        }
    }

    /* renamed from: c */
    public final void m5171c(int i9, int i10, SurfaceTexture surfaceTexture) {
        VirtualDisplay virtualDisplay = this.f5649b;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            m5172d(i9, i10, surfaceTexture);
        }
    }

    /* renamed from: d */
    public final void m5172d(int i9, int i10, SurfaceTexture surfaceTexture) {
        surfaceTexture.setDefaultBufferSize(i9, i10);
        this.f5649b = this.f5650c.createVirtualDisplay("CLRTC_ScreenCapture", i9, i10, 400, 3, new Surface(surfaceTexture), null, null);
    }

    /* renamed from: f */
    public boolean m5173f() {
        return this.f5649b != null;
    }

    /* renamed from: h */
    public final void m5174h(Intent intent, int i9, int i10, MediaProjection.Callback callback, SurfaceTexture surfaceTexture, Handler handler) {
        MediaProjection mediaProjection = this.f5651d.getMediaProjection(-1, intent);
        this.f5650c = mediaProjection;
        mediaProjection.registerCallback(callback, handler);
        m5172d(i9, i10, surfaceTexture);
    }

    /* renamed from: j */
    public final void m5175j(MediaProjection.Callback callback) {
        VirtualDisplay virtualDisplay = this.f5649b;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            this.f5649b = null;
        }
        MediaProjection mediaProjection = this.f5650c;
        if (mediaProjection != null) {
            mediaProjection.unregisterCallback(callback);
            this.f5650c.stop();
            this.f5650c = null;
        }
        stopForeground(true);
        stopSelf();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        int i9 = Build.VERSION.SDK_INT;
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("c.c.c.ve.ScreenCaptureService", "Desktop Sharing Channel", 3));
        startForeground(1, new Notification.Builder(this, "c.c.c.ve.ScreenCaptureService").setContentTitle(getText(R.string.u_app_name)).setColor(Globals.m7388i0().m7423F0()).setContentText(getText(R.string.clm_meeting_in_sharing)).setSmallIcon(R.drawable.ic_stat_name_s).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MeetingActivity.class), i9 >= 31 ? 201326592 : 0)).build());
        this.f5651d = (MediaProjectionManager) getSystemService("media_projection");
        synchronized (ScreenCaptureService.class) {
            f5647e = this;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        synchronized (ScreenCaptureService.class) {
            if (f5647e == this) {
                f5647e = null;
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i9, int i10) {
        String stringExtra = intent.getStringExtra("extra.token");
        Iterator<AbstractC1145a> it = f5648f.iterator();
        while (it.hasNext()) {
            AbstractC1145a next = it.next();
            if (next.f5652a.equals(stringExtra)) {
                next.mo5177b();
                it.remove();
            }
        }
        return 2;
    }
}
