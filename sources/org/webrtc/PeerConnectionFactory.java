package org.webrtc;

import java.util.List;
import org.webrtc.EglBase;
import org.webrtc.PeerConnection;
import org.webrtc.VideoCapturer;

/* loaded from: classes3.dex */
public class PeerConnectionFactory {
    private static final String TAG = "PeerConnectionFactory";
    private static volatile boolean nativeLibLoaded;
    private static Thread networkThread;
    private static Thread signalingThread;
    private static Thread workerThread;
    private EglBase localEglbase;
    private final long nativeFactory;
    private EglBase remoteEglbase;

    public static class Options {
        static final int ADAPTER_TYPE_CELLULAR = 4;
        static final int ADAPTER_TYPE_ETHERNET = 1;
        static final int ADAPTER_TYPE_LOOPBACK = 16;
        static final int ADAPTER_TYPE_UNKNOWN = 0;
        static final int ADAPTER_TYPE_VPN = 8;
        static final int ADAPTER_TYPE_WIFI = 2;
        public boolean disableEncryption;
        public boolean disableNetworkMonitor;
        public int networkIgnoreMask;
    }

    static {
        try {
            System.loadLibrary("jingle_peerconnection_so");
            nativeLibLoaded = true;
        } catch (UnsatisfiedLinkError unused) {
            nativeLibLoaded = false;
        }
    }

    @Deprecated
    public PeerConnectionFactory() {
        this(null);
    }

    public static String fieldTrialsFindFullName(String str) {
        return nativeLibLoaded ? nativeFieldTrialsFindFullName(str) : "";
    }

    public static native boolean initializeAndroidGlobals(Object obj, boolean z8, boolean z9, boolean z10);

    public static native void initializeFieldTrials(String str);

    public static native void initializeInternalTracer();

    private static native long nativeCreateAudioSource(long j9, MediaConstraints mediaConstraints);

    private static native long nativeCreateAudioTrack(long j9, String str, long j10);

    private static native long nativeCreateLocalMediaStream(long j9, String str);

    private static native long nativeCreateObserver(PeerConnection.Observer observer);

    private static native long nativeCreatePeerConnection(long j9, PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, long j10);

    private static native long nativeCreatePeerConnectionFactory(Options options);

    private static native long nativeCreateVideoSource(long j9, EglBase.Context context, boolean z8);

    private static native long nativeCreateVideoTrack(long j9, String str, long j10);

    private static native String nativeFieldTrialsFindFullName(String str);

    private static native void nativeFreeFactory(long j9);

    private static native void nativeInitializeVideoCapturer(long j9, VideoCapturer videoCapturer, long j10, VideoCapturer.CapturerObserver capturerObserver);

    private static native void nativeSetVideoHwAccelerationOptions(long j9, Object obj, Object obj2);

    private static native boolean nativeStartAecDump(long j9, int i9, int i10);

    private static native void nativeStopAecDump(long j9);

    private static native void nativeThreadsCallbacks(long j9);

    private static void onNetworkThreadReady() {
        networkThread = Thread.currentThread();
        Logging.m23185d(TAG, "onNetworkThreadReady");
    }

    private static void onSignalingThreadReady() {
        signalingThread = Thread.currentThread();
        Logging.m23185d(TAG, "onSignalingThreadReady");
    }

    private static void onWorkerThreadReady() {
        workerThread = Thread.currentThread();
        Logging.m23185d(TAG, "onWorkerThreadReady");
    }

    private static void printStackTrace(Thread thread, String str) {
        if (thread != null) {
            StackTraceElement[] stackTrace = thread.getStackTrace();
            if (stackTrace.length > 0) {
                Logging.m23185d(TAG, str + " stacks trace:");
                for (StackTraceElement stackTraceElement : stackTrace) {
                    Logging.m23185d(TAG, stackTraceElement.toString());
                }
            }
        }
    }

    public static void printStackTraces() {
        printStackTrace(networkThread, "Network thread");
        printStackTrace(workerThread, "Worker thread");
        printStackTrace(signalingThread, "Signaling thread");
    }

    public static native void shutdownInternalTracer();

    public static native boolean startInternalTracingCapture(String str);

    public static native void stopInternalTracingCapture();

    public AudioSource createAudioSource(MediaConstraints mediaConstraints) {
        return new AudioSource(nativeCreateAudioSource(this.nativeFactory, mediaConstraints));
    }

    public AudioTrack createAudioTrack(String str, AudioSource audioSource) {
        return new AudioTrack(nativeCreateAudioTrack(this.nativeFactory, str, audioSource.nativeSource));
    }

    public MediaStream createLocalMediaStream(String str) {
        return new MediaStream(nativeCreateLocalMediaStream(this.nativeFactory, str));
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        long jNativeCreateObserver = nativeCreateObserver(observer);
        if (jNativeCreateObserver == 0) {
            return null;
        }
        long jNativeCreatePeerConnection = nativeCreatePeerConnection(this.nativeFactory, rTCConfiguration, mediaConstraints, jNativeCreateObserver);
        if (jNativeCreatePeerConnection == 0) {
            return null;
        }
        return new PeerConnection(jNativeCreatePeerConnection, jNativeCreateObserver);
    }

    public VideoSource createVideoSource(VideoCapturer videoCapturer) {
        EglBase eglBase = this.localEglbase;
        long jNativeCreateVideoSource = nativeCreateVideoSource(this.nativeFactory, eglBase == null ? null : eglBase.getEglBaseContext(), videoCapturer.isScreencast());
        nativeInitializeVideoCapturer(this.nativeFactory, videoCapturer, jNativeCreateVideoSource, new VideoCapturer.AndroidVideoTrackSourceObserver(jNativeCreateVideoSource));
        return new VideoSource(jNativeCreateVideoSource);
    }

    public VideoTrack createVideoTrack(String str, VideoSource videoSource) {
        return new VideoTrack(nativeCreateVideoTrack(this.nativeFactory, str, videoSource.nativeSource));
    }

    public void dispose() {
        nativeFreeFactory(this.nativeFactory);
        networkThread = null;
        workerThread = null;
        signalingThread = null;
        EglBase eglBase = this.localEglbase;
        if (eglBase != null) {
            eglBase.release();
        }
        EglBase eglBase2 = this.remoteEglbase;
        if (eglBase2 != null) {
            eglBase2.release();
        }
    }

    @Deprecated
    public native void nativeSetOptions(long j9, Options options);

    @Deprecated
    public void setOptions(Options options) {
        nativeSetOptions(this.nativeFactory, options);
    }

    public void setVideoHwAccelerationOptions(EglBase.Context context, EglBase.Context context2) {
        if (this.localEglbase != null) {
            Logging.m23189w(TAG, "Egl context already set.");
            this.localEglbase.release();
        }
        if (this.remoteEglbase != null) {
            Logging.m23189w(TAG, "Egl context already set.");
            this.remoteEglbase.release();
        }
        this.localEglbase = EglBase.create(context);
        this.remoteEglbase = EglBase.create(context2);
        nativeSetVideoHwAccelerationOptions(this.nativeFactory, this.localEglbase.getEglBaseContext(), this.remoteEglbase.getEglBaseContext());
    }

    public boolean startAecDump(int i9, int i10) {
        return nativeStartAecDump(this.nativeFactory, i9, i10);
    }

    public void stopAecDump() {
        nativeStopAecDump(this.nativeFactory);
    }

    public void threadsCallbacks() {
        nativeThreadsCallbacks(this.nativeFactory);
    }

    public PeerConnectionFactory(Options options) {
        long jNativeCreatePeerConnectionFactory = nativeCreatePeerConnectionFactory(options);
        this.nativeFactory = jNativeCreatePeerConnectionFactory;
        if (jNativeCreatePeerConnectionFactory == 0) {
            throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
        }
    }

    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> list, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(list), mediaConstraints, observer);
    }
}
