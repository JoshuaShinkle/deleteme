package org.webrtc;

/* loaded from: classes3.dex */
public class VideoSource extends MediaSource {
    public VideoSource(long j9) {
        super(j9);
    }

    private static native void nativeAdaptOutputFormat(long j9, int i9, int i10, int i11);

    public void adaptOutputFormat(int i9, int i10, int i11) {
        nativeAdaptOutputFormat(this.nativeSource, i9, i10, i11);
    }
}
