package org.webrtc;

/* loaded from: classes3.dex */
class Histogram {
    private final long handle;

    private Histogram(long j9) {
        this.handle = j9;
    }

    public static Histogram createCounts(String str, int i9, int i10, int i11) {
        return new Histogram(nativeCreateCounts(str, i9, i10, i11));
    }

    public static Histogram createEnumeration(String str, int i9) {
        return new Histogram(nativeCreateEnumeration(str, i9));
    }

    private static native void nativeAddSample(long j9, int i9);

    private static native long nativeCreateCounts(String str, int i9, int i10, int i11);

    private static native long nativeCreateEnumeration(String str, int i9);

    public void addSample(int i9) {
        nativeAddSample(this.handle, i9);
    }
}
