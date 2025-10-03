package com.cyberlink.clrtc;

import android.content.Context;
import android.util.Log;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglBase;
import org.webrtc.VideoCapturer;

/* renamed from: com.cyberlink.clrtc.a */
/* loaded from: classes.dex */
public class C0947a extends CLRTCVideoCapturer {

    /* renamed from: i */
    public static final String f5056i = "a";

    public C0947a(Context context, EglBase.Context context2, Logger logger) {
        super(logger, context, context2);
    }

    /* renamed from: u */
    public static VideoCapturer m4995u(CameraEnumerator cameraEnumerator, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        CameraVideoCapturer cameraVideoCapturerCreateCapturer;
        CameraVideoCapturer cameraVideoCapturerCreateCapturer2;
        String[] deviceNames = cameraEnumerator.getDeviceNames();
        for (String str : deviceNames) {
            if (cameraEnumerator.isFrontFacing(str) && (cameraVideoCapturerCreateCapturer2 = cameraEnumerator.createCapturer(str, cameraEventsHandler)) != null) {
                return cameraVideoCapturerCreateCapturer2;
            }
        }
        for (String str2 : deviceNames) {
            if (!cameraEnumerator.isFrontFacing(str2) && (cameraVideoCapturerCreateCapturer = cameraEnumerator.createCapturer(str2, cameraEventsHandler)) != null) {
                return cameraVideoCapturerCreateCapturer;
            }
        }
        return null;
    }

    /* renamed from: v */
    public static boolean m4996v(Context context) {
        return Camera2Enumerator.isSupported(context);
    }

    @Override // com.cyberlink.clrtc.CLRTCVideoCapturer
    /* renamed from: i */
    public VideoCapturer mo4437i(Context context, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        VideoCapturer videoCapturerM4995u = m4996v(context) ? m4995u(new Camera2Enumerator(context), cameraEventsHandler) : m4995u(new Camera1Enumerator(false), cameraEventsHandler);
        if (videoCapturerM4995u != null) {
            return videoCapturerM4995u;
        }
        Log.w(f5056i, "Failed to open camera");
        return null;
    }
}
