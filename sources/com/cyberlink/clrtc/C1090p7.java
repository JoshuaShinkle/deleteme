package com.cyberlink.clrtc;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;
import com.cyberlink.clrtc.rtc.RTCAudioManager;
import com.google.android.exoplayer2.util.MimeTypes;

/* renamed from: com.cyberlink.clrtc.p7 */
/* loaded from: classes.dex */
public class C1090p7 extends ContentObserver {

    /* renamed from: a */
    public final Context f5466a;

    /* renamed from: b */
    public final AudioManager f5467b;

    /* renamed from: c */
    public final a f5468c;

    /* renamed from: d */
    public RTCAudioManager.AudioDevice f5469d;

    /* renamed from: e */
    public int f5470e;

    /* renamed from: com.cyberlink.clrtc.p7$a */
    public interface a {
        /* renamed from: a */
        void mo4927a(int i9);
    }

    public C1090p7(Context context, Handler handler, a aVar) {
        super(handler);
        this.f5469d = RTCAudioManager.AudioDevice.NONE;
        this.f5470e = 0;
        this.f5466a = context;
        this.f5467b = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.f5468c = aVar;
        context.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this);
    }

    /* renamed from: a */
    public void m5047a(RTCAudioManager.AudioDevice audioDevice) {
        if (this.f5469d != audioDevice) {
            this.f5469d = audioDevice;
            onChange(true);
        }
    }

    /* renamed from: b */
    public void m5048b() {
        this.f5466a.getContentResolver().unregisterContentObserver(this);
    }

    /* renamed from: c */
    public final int m5049c() {
        int streamMaxVolume;
        int streamVolume;
        if (RTCAudioManager.AudioDevice.BLUETOOTH == this.f5469d) {
            streamMaxVolume = this.f5467b.getStreamMaxVolume(6);
            streamVolume = this.f5467b.getStreamVolume(6);
        } else {
            streamMaxVolume = this.f5467b.getStreamMaxVolume(0);
            streamVolume = this.f5467b.getStreamVolume(0);
        }
        return Math.round((Math.min(streamVolume, r0) * 100.0f) / Math.max(streamMaxVolume, 1));
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z8) {
        int iM5049c;
        if (this.f5469d == RTCAudioManager.AudioDevice.NONE || (iM5049c = m5049c()) == this.f5470e) {
            return;
        }
        this.f5470e = iM5049c;
        this.f5468c.mo4927a(iM5049c);
    }
}
