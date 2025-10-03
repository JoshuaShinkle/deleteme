package org.webrtc;

/* loaded from: classes3.dex */
public class DtmfSender {
    final long nativeDtmfSender;

    public DtmfSender(long j9) {
        this.nativeDtmfSender = j9;
    }

    private static native void free(long j9);

    private static native boolean nativeCanInsertDtmf(long j9);

    private static native int nativeDuration(long j9);

    private static native boolean nativeInsertDtmf(long j9, String str, int i9, int i10);

    private static native int nativeInterToneGap(long j9);

    private static native String nativeTones(long j9);

    public boolean canInsertDtmf() {
        return nativeCanInsertDtmf(this.nativeDtmfSender);
    }

    public void dispose() {
        free(this.nativeDtmfSender);
    }

    public int duration() {
        return nativeDuration(this.nativeDtmfSender);
    }

    public boolean insertDtmf(String str, int i9, int i10) {
        return nativeInsertDtmf(this.nativeDtmfSender, str, i9, i10);
    }

    public int interToneGap() {
        return nativeInterToneGap(this.nativeDtmfSender);
    }

    public String tones() {
        return nativeTones(this.nativeDtmfSender);
    }
}
