package org.webrtc;

/* loaded from: classes3.dex */
public class MediaStreamTrack {
    final long nativeTrack;

    public enum MediaType {
        MEDIA_TYPE_AUDIO,
        MEDIA_TYPE_VIDEO
    }

    public enum State {
        LIVE,
        ENDED
    }

    public MediaStreamTrack(long j9) {
        this.nativeTrack = j9;
    }

    private static native void free(long j9);

    private static native boolean nativeEnabled(long j9);

    private static native String nativeId(long j9);

    private static native String nativeKind(long j9);

    private static native boolean nativeSetEnabled(long j9, boolean z8);

    private static native State nativeState(long j9);

    public void dispose() {
        free(this.nativeTrack);
    }

    public boolean enabled() {
        return nativeEnabled(this.nativeTrack);
    }

    /* renamed from: id */
    public String m23191id() {
        return nativeId(this.nativeTrack);
    }

    public String kind() {
        return nativeKind(this.nativeTrack);
    }

    public boolean setEnabled(boolean z8) {
        return nativeSetEnabled(this.nativeTrack, z8);
    }

    public State state() {
        return nativeState(this.nativeTrack);
    }
}
