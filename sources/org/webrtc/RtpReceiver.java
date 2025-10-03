package org.webrtc;

import org.webrtc.MediaStreamTrack;

/* loaded from: classes3.dex */
public class RtpReceiver {
    private MediaStreamTrack cachedTrack;
    private long nativeObserver;
    final long nativeRtpReceiver;

    public interface Observer {
        void onFirstPacketReceived(MediaStreamTrack.MediaType mediaType);
    }

    public RtpReceiver(long j9) {
        this.nativeRtpReceiver = j9;
        this.cachedTrack = new MediaStreamTrack(nativeGetTrack(j9));
    }

    private static native void free(long j9);

    private static native RtpParameters nativeGetParameters(long j9);

    private static native long nativeGetTrack(long j9);

    private static native String nativeId(long j9);

    private static native long nativeSetObserver(long j9, Observer observer);

    private static native boolean nativeSetParameters(long j9, RtpParameters rtpParameters);

    private static native long nativeUnsetObserver(long j9, long j10);

    public void SetObserver(Observer observer) {
        long j9 = this.nativeObserver;
        if (j9 != 0) {
            nativeUnsetObserver(this.nativeRtpReceiver, j9);
        }
        this.nativeObserver = nativeSetObserver(this.nativeRtpReceiver, observer);
    }

    public void dispose() {
        this.cachedTrack.dispose();
        long j9 = this.nativeObserver;
        if (j9 != 0) {
            nativeUnsetObserver(this.nativeRtpReceiver, j9);
            this.nativeObserver = 0L;
        }
        free(this.nativeRtpReceiver);
    }

    public RtpParameters getParameters() {
        return nativeGetParameters(this.nativeRtpReceiver);
    }

    /* renamed from: id */
    public String m23192id() {
        return nativeId(this.nativeRtpReceiver);
    }

    public boolean setParameters(RtpParameters rtpParameters) {
        return nativeSetParameters(this.nativeRtpReceiver, rtpParameters);
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
