package com.cyberlink.media.video;

import java.nio.ByteBuffer;
import p104j2.C5092c;
import p120k8.InterfaceC5203a;

/* loaded from: classes.dex */
public final class ColorConverter {

    /* renamed from: a */
    public final int f5934a;

    /* renamed from: b */
    public final int f5935b;

    @InterfaceC5203a
    private long mNativeContext;

    static {
        C5092c.m19924a();
        init();
    }

    public ColorConverter(int i9, int i10) {
        setup(i9, i10);
        if (this.mNativeContext == 0) {
            throw new UnsupportedOperationException("Unsupported color conversion.");
        }
        this.f5934a = i9;
        this.f5935b = i10;
    }

    /* renamed from: b */
    public static ColorConverter m5400b(int i9, int i10) {
        return new ColorConverter(i9, i10);
    }

    private static native void init();

    private native void nConvert(ByteBuffer byteBuffer, long j9, long j10, long j11, long j12, long j13, long j14, ByteBuffer byteBuffer2, long j15, long j16, long j17, long j18, long j19, long j20);

    private native void setup(int i9, int i10);

    /* renamed from: a */
    public void m5401a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, long j9, long j10) {
        long j11 = j9 - 1;
        long j12 = j10 - 1;
        nConvert(byteBuffer, j9, j10, 0L, 0L, j11, j12, byteBuffer2, j9, j10, 0L, 0L, j11, j12);
    }

    public void finalize() throws Throwable {
        try {
            release();
        } finally {
            super.finalize();
        }
    }

    public native void release();
}
