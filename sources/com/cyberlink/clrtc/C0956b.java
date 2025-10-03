package com.cyberlink.clrtc;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import com.cyberlink.clrtc.p031ve.C1159d;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglBase;
import org.webrtc.VideoCapturer;

/* renamed from: com.cyberlink.clrtc.b */
/* loaded from: classes.dex */
public class C0956b extends CLRTCVideoCapturer {

    /* renamed from: i */
    public static final String f5078i = "b";

    /* renamed from: com.cyberlink.clrtc.b$a */
    public class a extends MediaProjection.Callback {
        public a() {
        }

        @Override // android.media.projection.MediaProjection.Callback
        public void onStop() {
            C0956b.this.f4838b.m4458g(C0956b.f5078i, "onStop");
        }
    }

    public C0956b(Context context, EglBase.Context context2, Logger logger) {
        super(logger, context, context2);
    }

    @Override // com.cyberlink.clrtc.CLRTCVideoCapturer
    /* renamed from: i */
    public VideoCapturer mo4437i(Context context, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        return new C1159d(context, new a());
    }

    /* renamed from: v */
    public void m4998v(Intent intent) {
        VideoCapturer videoCapturer = this.f4840d;
        if (videoCapturer instanceof C1159d) {
            ((C1159d) videoCapturer).m5215n(intent);
        }
    }

    /* renamed from: w */
    public void m4999w(int i9, int i10) {
        this.f4840d.changeCaptureFormat(i9, i10, 24);
    }
}
