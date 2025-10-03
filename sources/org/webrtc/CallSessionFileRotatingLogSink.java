package org.webrtc;

import org.webrtc.Logging;

/* loaded from: classes3.dex */
public class CallSessionFileRotatingLogSink {
    private long nativeSink;

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public CallSessionFileRotatingLogSink(String str, int i9, Logging.Severity severity) {
        this.nativeSink = nativeAddSink(str, i9, severity.ordinal());
    }

    public static byte[] getLogData(String str) {
        return nativeGetLogData(str);
    }

    private static native long nativeAddSink(String str, int i9, int i10);

    private static native void nativeDeleteSink(long j9);

    private static native byte[] nativeGetLogData(String str);

    public void dispose() {
        long j9 = this.nativeSink;
        if (j9 != 0) {
            nativeDeleteSink(j9);
            this.nativeSink = 0L;
        }
    }
}
