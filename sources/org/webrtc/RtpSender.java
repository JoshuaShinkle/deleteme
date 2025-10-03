package org.webrtc;

/* loaded from: classes3.dex */
public class RtpSender {
    private MediaStreamTrack cachedTrack;
    private final DtmfSender dtmfSender;
    final long nativeRtpSender;
    private boolean ownsTrack = true;

    public RtpSender(long j9) {
        this.nativeRtpSender = j9;
        long jNativeGetTrack = nativeGetTrack(j9);
        this.cachedTrack = jNativeGetTrack != 0 ? new MediaStreamTrack(jNativeGetTrack) : null;
        long jNativeGetDtmfSender = nativeGetDtmfSender(j9);
        this.dtmfSender = jNativeGetDtmfSender != 0 ? new DtmfSender(jNativeGetDtmfSender) : null;
    }

    private static native void free(long j9);

    private static native long nativeGetDtmfSender(long j9);

    private static native RtpParameters nativeGetParameters(long j9);

    private static native long nativeGetTrack(long j9);

    private static native String nativeId(long j9);

    private static native boolean nativeSetParameters(long j9, RtpParameters rtpParameters);

    private static native boolean nativeSetTrack(long j9, long j10);

    public void dispose() {
        DtmfSender dtmfSender = this.dtmfSender;
        if (dtmfSender != null) {
            dtmfSender.dispose();
        }
        MediaStreamTrack mediaStreamTrack = this.cachedTrack;
        if (mediaStreamTrack != null && this.ownsTrack) {
            mediaStreamTrack.dispose();
        }
        free(this.nativeRtpSender);
    }

    public DtmfSender dtmf() {
        return this.dtmfSender;
    }

    public RtpParameters getParameters() {
        return nativeGetParameters(this.nativeRtpSender);
    }

    /* renamed from: id */
    public String m23193id() {
        return nativeId(this.nativeRtpSender);
    }

    public boolean setParameters(RtpParameters rtpParameters) {
        return nativeSetParameters(this.nativeRtpSender, rtpParameters);
    }

    public boolean setTrack(MediaStreamTrack mediaStreamTrack, boolean z8) {
        if (!nativeSetTrack(this.nativeRtpSender, mediaStreamTrack == null ? 0L : mediaStreamTrack.nativeTrack)) {
            return false;
        }
        MediaStreamTrack mediaStreamTrack2 = this.cachedTrack;
        if (mediaStreamTrack2 != null && this.ownsTrack) {
            mediaStreamTrack2.dispose();
        }
        this.cachedTrack = mediaStreamTrack;
        this.ownsTrack = z8;
        return true;
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
