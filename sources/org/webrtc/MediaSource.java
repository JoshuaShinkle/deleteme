package org.webrtc;

/* loaded from: classes3.dex */
public class MediaSource {
    final long nativeSource;

    public enum State {
        INITIALIZING,
        LIVE,
        ENDED,
        MUTED
    }

    public MediaSource(long j9) {
        this.nativeSource = j9;
    }

    private static native void free(long j9);

    private static native State nativeState(long j9);

    public void dispose() {
        free(this.nativeSource);
    }

    public State state() {
        return nativeState(this.nativeSource);
    }
}
