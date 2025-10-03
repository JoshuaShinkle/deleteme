package com.cyberlink.clrtc;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import androidx.annotation.Keep;
import com.cyberlink.clrtc.p031ve.C1156a;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglBase;
import org.webrtc.MediaSource;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.ThreadUtils;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoSource;
import p023c2.C0718b;

/* loaded from: classes.dex */
abstract class CLRTCVideoCapturer {

    /* renamed from: h */
    public static final String f4836h = "CLRTCVideoCapturer";

    /* renamed from: a */
    public final Handler f4837a;

    /* renamed from: b */
    public final Logger f4838b;

    /* renamed from: c */
    public PeerConnectionFactory f4839c;

    /* renamed from: d */
    public VideoCapturer f4840d;

    /* renamed from: e */
    public VideoSource f4841e;

    /* renamed from: f */
    public long f4842f;

    /* renamed from: g */
    public VideoRenderer f4843g = null;

    @Keep
    public static class CLRTCVideoCapturerObserver implements CapturerObserver {
        private static final String TAG = "CLRTCVideoCapturerObserver";
        private final VideoCapturer capturer;
        private final Handler handler = new Handler();
        private final Logger logger;
        private final VideoSource source;

        public CLRTCVideoCapturerObserver(VideoSource videoSource, VideoCapturer videoCapturer, Logger logger) {
            this.source = videoSource;
            this.capturer = videoCapturer;
            this.logger = logger;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$addSink$0(long j9, boolean z8, boolean z9, boolean z10, int i9, boolean z11, int i10) {
            Logger logger = this.logger;
            String str = TAG;
            logger.m4467t(str, " > addSink run: " + j9);
            CLRTCVideoCapturer.nativeVideoSourceAddSink(C0718b.m3549a(MediaSource.class, this.source, "nativeSource"), j9, z8, z9, z10, i9, z11, i10);
            this.logger.m4467t(str, " > addSink out: " + j9);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$removeSink$1(long j9) {
            Logger logger = this.logger;
            String str = TAG;
            logger.m4467t(str, " > removeSink run: " + j9);
            CLRTCVideoCapturer.nativeVideoSourceRemoveSink(C0718b.m3549a(MediaSource.class, this.source, "nativeSource"), j9);
            this.logger.m4467t(str, " > removeSink out: " + j9);
        }

        @Override // com.cyberlink.clrtc.CLRTCVideoCapturer.CapturerObserver
        @Keep
        public void addSink(final long j9, final boolean z8, final boolean z9, final boolean z10, final int i9, final boolean z11, final int i10) {
            this.logger.m4467t(TAG, "[in] addSink: " + j9);
            ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new Runnable() { // from class: com.cyberlink.clrtc.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5199b.lambda$addSink$0(j9, z8, z9, z10, i9, z11, i10);
                }
            });
        }

        @Override // com.cyberlink.clrtc.CLRTCVideoCapturer.CapturerObserver
        @Keep
        public boolean applyRotation() {
            this.logger.m4460i(TAG, "applyRotation [TODO]");
            return false;
        }

        @Override // com.cyberlink.clrtc.CLRTCVideoCapturer.CapturerObserver
        @Keep
        public boolean changePrefer(int i9, int i10, int i11) {
            Logger logger = this.logger;
            String str = TAG;
            logger.m4467t(str, "[in] changePrefer: " + i9 + "x" + i10 + ", " + i11 + "fps");
            try {
                try {
                    if (C1156a.m5193g()) {
                        this.source.adaptOutputFormat(i9 - (i9 % 16), i10 - (i10 % 16), i11);
                    } else {
                        this.source.adaptOutputFormat(i9, i10, i11);
                    }
                    this.logger.m4467t(str, "[out] changePrefer");
                    return true;
                } catch (Exception e9) {
                    Logger logger2 = this.logger;
                    String str2 = TAG;
                    logger2.m4459h(str2, "[err] changePrefer but failed: " + e9);
                    this.logger.m4467t(str2, "[out] changePrefer");
                    return false;
                }
            } catch (Throwable th) {
                this.logger.m4467t(TAG, "[out] changePrefer");
                throw th;
            }
        }

        @Override // com.cyberlink.clrtc.CLRTCVideoCapturer.CapturerObserver
        @Keep
        public boolean isScreencast() {
            return this.capturer.isScreencast();
        }

        @Override // com.cyberlink.clrtc.CLRTCVideoCapturer.CapturerObserver
        @Keep
        public void removeSink(final long j9) {
            this.logger.m4467t(TAG, "[in] removeSink: " + j9);
            this.handler.post(new Runnable() { // from class: com.cyberlink.clrtc.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5178b.lambda$removeSink$1(j9);
                }
            });
        }

        @Override // com.cyberlink.clrtc.CLRTCVideoCapturer.CapturerObserver
        @Keep
        public boolean startCapture(int i9, int i10, int i11, int i12, int i13, int i14) {
            Logger logger = this.logger;
            String str = TAG;
            logger.m4467t(str, "[in] startCapture: " + i9 + "x" + i10 + ", " + i11 + "fps [" + i12 + "x" + i13 + ", " + i14 + "fps]");
            try {
                try {
                    if (C1156a.m5193g()) {
                        this.source.adaptOutputFormat(i12 - (i12 % 16), i13 - (i13 % 16), i14);
                    } else {
                        this.source.adaptOutputFormat(i12, i13, i14);
                    }
                    this.capturer.startCapture(i9, i10, Math.max(Math.min(i11, i14), 1));
                    this.logger.m4467t(str, "[out] startCapture");
                    return true;
                } catch (Exception e9) {
                    Logger logger2 = this.logger;
                    String str2 = TAG;
                    logger2.m4459h(str2, "[err] startCapture: " + e9);
                    this.logger.m4467t(str2, "[out] startCapture");
                    return false;
                }
            } catch (Throwable th) {
                this.logger.m4467t(TAG, "[out] startCapture");
                throw th;
            }
        }

        @Override // com.cyberlink.clrtc.CLRTCVideoCapturer.CapturerObserver
        @Keep
        public boolean stopCapture() {
            Logger logger = this.logger;
            String str = TAG;
            logger.m4467t(str, "[in] stopCapture");
            try {
                try {
                    this.capturer.stopCapture();
                    this.logger.m4467t(str, "[out] stopCapture");
                    return true;
                } catch (Exception e9) {
                    Logger logger2 = this.logger;
                    String str2 = TAG;
                    logger2.m4459h(str2, "[err] stopCapture: " + e9);
                    this.logger.m4467t(str2, "[out] stopCapture");
                    return false;
                }
            } catch (Throwable th) {
                this.logger.m4467t(TAG, "[out] stopCapture");
                throw th;
            }
        }
    }

    @Keep
    public interface CapturerObserver {
        void addSink(long j9, boolean z8, boolean z9, boolean z10, int i9, boolean z11, int i10);

        boolean applyRotation();

        boolean changePrefer(int i9, int i10, int i11);

        boolean isScreencast();

        void removeSink(long j9);

        boolean startCapture(int i9, int i10, int i11, int i12, int i13, int i14);

        boolean stopCapture();
    }

    /* renamed from: com.cyberlink.clrtc.CLRTCVideoCapturer$a */
    public class C0943a implements CameraVideoCapturer.CameraEventsHandler {
        public C0943a() {
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraClosed() {
            CLRTCVideoCapturer.this.f4838b.m4458g(CLRTCVideoCapturer.f4836h, "camera closed");
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraDisconnected() {
            CLRTCVideoCapturer.this.f4838b.m4459h(CLRTCVideoCapturer.f4836h, "camera disconnected");
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraError(String str) {
            CLRTCVideoCapturer.this.f4838b.m4459h(CLRTCVideoCapturer.f4836h, "camera error reason: " + str);
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraFreezed(String str) {
            CLRTCVideoCapturer.this.f4838b.m4459h(CLRTCVideoCapturer.f4836h, "camera freezed reason: " + str);
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onCameraOpening(String str) {
            CLRTCVideoCapturer.this.f4838b.m4458g(CLRTCVideoCapturer.f4836h, "camera opening");
        }

        @Override // org.webrtc.CameraVideoCapturer.CameraEventsHandler
        public void onFirstFrameAvailable() {
            CLRTCVideoCapturer.this.f4838b.m4458g(CLRTCVideoCapturer.f4836h, "first frame available");
        }
    }

    static {
        System.loadLibrary("c++_shared");
        System.loadLibrary("DO");
    }

    public CLRTCVideoCapturer(Logger logger, Context context, EglBase.Context context2) {
        HandlerThread handlerThread = new HandlerThread("CaptureSourceThread");
        handlerThread.start();
        this.f4837a = new Handler(handlerThread.getLooper());
        this.f4838b = logger;
        m4439k(context, context2);
    }

    private static native void freeWrappedVideoCapturer(long j9);

    /* renamed from: h */
    public static PeerConnectionFactory m4432h(Context context, EglBase.Context context2) {
        if (!PeerConnectionFactory.initializeAndroidGlobals(context, true, true, true)) {
            Log.e(f4836h, "Failed to initializeAndroidGlobals");
        }
        PeerConnectionFactory peerConnectionFactory = new PeerConnectionFactory(null);
        peerConnectionFactory.setVideoHwAccelerationOptions(context2, context2);
        return peerConnectionFactory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public /* synthetic */ void m4433l(Context context, EglBase.Context context2, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler) {
        this.f4839c = m4432h(context, context2);
        VideoCapturer videoCapturerMo4437i = mo4437i(context, cameraEventsHandler);
        this.f4840d = videoCapturerMo4437i;
        this.f4841e = this.f4839c.createVideoSource(videoCapturerMo4437i);
        this.f4842f = nativeWrapVideoCapturer(new CLRTCVideoCapturerObserver(this.f4841e, this.f4840d, this.f4838b));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public /* synthetic */ void m4434m() {
        Logger logger = this.f4838b;
        String str = f4836h;
        logger.m4467t(str, " > removeRenderer run");
        nativeVideoSourceRemoveSink(C0718b.m3549a(MediaSource.class, this.f4841e, "nativeSource"), C0718b.m3549a(VideoRenderer.class, this.f4843g, "nativeVideoRenderer"));
        this.f4843g = null;
        this.f4838b.m4467t(str, " > removeRenderer done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n */
    public /* synthetic */ void m4435n(VideoRenderer videoRenderer) throws NoSuchFieldException, SecurityException {
        Logger logger = this.f4838b;
        String str = f4836h;
        logger.m4467t(str, " > setRenderer run");
        this.f4843g = videoRenderer;
        long jM3549a = C0718b.m3549a(MediaSource.class, this.f4841e, "nativeSource");
        long jM3549a2 = C0718b.m3549a(VideoRenderer.class, videoRenderer, "nativeVideoRenderer");
        this.f4838b.m4467t(str, " > setRenderer: " + jM3549a2);
        nativeVideoSourceAddSink(jM3549a, jM3549a2, false, false, false, 0, false, 0);
        this.f4838b.m4467t(str, " > setRenderer done");
    }

    private static native boolean nativeAddSupportedCaptureFormat(long j9, int i9, int i10, int i11, int i12);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeVideoSourceAddSink(long j9, long j10, boolean z8, boolean z9, boolean z10, int i9, boolean z11, int i10);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeVideoSourceRemoveSink(long j9, long j10);

    private static native long nativeWrapVideoCapturer(CapturerObserver capturerObserver);

    public void finalize() throws Throwable {
        this.f4837a.getLooper().quit();
        super.finalize();
    }

    /* renamed from: g */
    public final void m4436g() {
        nativeAddSupportedCaptureFormat(this.f4842f, 640, 360, 30, 17);
        nativeAddSupportedCaptureFormat(this.f4842f, 1280, 720, 30, 17);
    }

    /* renamed from: i */
    public abstract VideoCapturer mo4437i(Context context, CameraVideoCapturer.CameraEventsHandler cameraEventsHandler);

    /* renamed from: j */
    public void m4438j() {
        this.f4838b.m4467t(f4836h, "dispose");
        m4440o();
        freeWrappedVideoCapturer(this.f4842f);
        VideoCapturer videoCapturer = this.f4840d;
        if (videoCapturer != null) {
            videoCapturer.dispose();
            this.f4840d = null;
        }
        PeerConnectionFactory peerConnectionFactory = this.f4839c;
        if (peerConnectionFactory != null) {
            peerConnectionFactory.dispose();
            this.f4839c = null;
        }
    }

    /* renamed from: k */
    public final void m4439k(final Context context, final EglBase.Context context2) {
        final C0943a c0943a = new C0943a();
        ThreadUtils.invokeAtFrontUninterruptibly(this.f4837a, new Runnable() { // from class: com.cyberlink.clrtc.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f5134b.m4433l(context, context2, c0943a);
            }
        });
        m4436g();
    }

    /* renamed from: o */
    public void m4440o() {
        if (this.f4843g == null) {
            return;
        }
        this.f4838b.m4467t(f4836h, "removeRenderer");
        ThreadUtils.invokeAtFrontUninterruptibly(this.f4837a, new Runnable() { // from class: com.cyberlink.clrtc.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f5161b.m4434m();
            }
        });
    }

    /* renamed from: p */
    public void m4441p(int i9, int i10, int i11) {
        this.f4840d.resetFormat(i9, i10, i11);
    }

    /* renamed from: q */
    public void m4442q() {
        this.f4838b.m4467t(f4836h, "resumeCapturer");
        this.f4840d.resumeCapture();
    }

    /* renamed from: r */
    public void m4443r(final VideoRenderer videoRenderer) {
        this.f4838b.m4467t(f4836h, "setRenderer");
        ThreadUtils.invokeAtFrontUninterruptibly(this.f4837a, new Runnable() { // from class: com.cyberlink.clrtc.c
            @Override // java.lang.Runnable
            public final void run() throws NoSuchFieldException, SecurityException {
                this.f5113b.m4435n(videoRenderer);
            }
        });
    }

    /* renamed from: s */
    public void m4444s() {
        Logger logger = this.f4838b;
        String str = f4836h;
        logger.m4467t(str, "stopCapture");
        try {
            if (this.f4840d != null) {
                try {
                    this.f4838b.m4467t(str, " > stopCapture in");
                    this.f4840d.stopCapture();
                    this.f4838b.m4467t(str, " > stopCapture out");
                } catch (InterruptedException e9) {
                    Logger logger2 = this.f4838b;
                    String str2 = f4836h;
                    logger2.m4467t(str2, " > stopCapture error: " + e9);
                    this.f4838b.m4467t(str2, " > stopCapture out");
                }
            }
        } catch (Throwable th) {
            this.f4838b.m4467t(f4836h, " > stopCapture out");
            throw th;
        }
    }

    /* renamed from: t */
    public void m4445t(CameraVideoCapturer.CameraSwitchHandler cameraSwitchHandler) {
        this.f4838b.m4467t(f4836h, "switchCamera");
        VideoCapturer videoCapturer = this.f4840d;
        if (videoCapturer instanceof CameraVideoCapturer) {
            ((CameraVideoCapturer) videoCapturer).switchCamera(cameraSwitchHandler);
        }
    }
}
