package com.cyberlink.media;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.cyberlink.media.CLMediaCodec;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import p104j2.C5090a;
import p104j2.C5092c;
import p104j2.C5093d;
import p120k8.InterfaceC5203a;

@TargetApi(16)
/* loaded from: classes.dex */
final class CLMediaCodecExtra implements CLMediaCodec.InterfaceC1211d, CLMediaCodec.InterfaceC1212e {

    /* renamed from: a */
    public static final Set<String> f5876a = Collections.unmodifiableSet(new HashSet(Arrays.asList(MimeTypes.AUDIO_AC3, MimeTypes.AUDIO_E_AC3, "audio/dts", MimeTypes.AUDIO_MPEG_L2, MimeTypes.VIDEO_MPEG2, MimeTypes.VIDEO_MP4V, MimeTypes.VIDEO_H264, MimeTypes.AUDIO_MPEG, "video/rv10", "video/rv20", "video/rv30", "video/rv40", "audio/cook", "audio/ra-144", "audio/ra-288", "audio/ralf", MimeTypes.AUDIO_ALAC, "audio/x-monkeys-audio")));

    @InterfaceC5203a
    private long mNativeContext;

    static {
        C5092c.m19924a();
        init();
    }

    /* renamed from: e */
    public static CLMediaCodecExtra m5333e(String str) {
        String strM19923a = C5090a.m19923a(str);
        if (!m5334f(strM19923a)) {
            throw new IllegalStateException("Cannot decode " + strM19923a);
        }
        CLMediaCodecExtra cLMediaCodecExtra = new CLMediaCodecExtra();
        cLMediaCodecExtra.setup(strM19923a, true);
        if (cLMediaCodecExtra.mNativeContext != 0) {
            return cLMediaCodecExtra;
        }
        throw new IllegalStateException("Failed to initialize the codec.");
    }

    /* renamed from: f */
    public static boolean m5334f(String str) {
        if (!m5335g(str)) {
            return false;
        }
        if (C5093d.m19937h(str)) {
            return true;
        }
        throw new InsufficientLicenseException(str);
    }

    /* renamed from: g */
    public static boolean m5335g(String str) {
        return f5876a.contains(str);
    }

    private native ByteBuffer[] getBuffers(boolean z8);

    private static native void init();

    private native void nConfigure(MediaFormat mediaFormat, int i9);

    private native void nReleaseOutputBuffer(int i9);

    private native void setup(String str, boolean z8);

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    /* renamed from: a */
    public ByteBuffer[] mo5325a() {
        return getBuffers(false);
    }

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    /* renamed from: b */
    public void mo5326b(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i9) {
        if (!m5334f(C5090a.m19923a(mediaFormat.getString("mime")))) {
            throw new UnsupportedOperationException();
        }
        nConfigure(mediaFormat, i9);
    }

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    /* renamed from: c */
    public ByteBuffer[] mo5327c() {
        return getBuffers(true);
    }

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    /* renamed from: d */
    public void mo5328d(int i9, boolean z8) {
        if (i9 < 0) {
            throw new IllegalArgumentException("index must be >= 0");
        }
        if (z8) {
            throw new UnsupportedOperationException("Video rendering is unsupported.");
        }
        nReleaseOutputBuffer(i9);
    }

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1210c
    public native int dequeueInputBuffer(long j9);

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    public native int dequeueOutputBuffer(MediaCodec.BufferInfo bufferInfo, long j9);

    public void finalize() throws Throwable {
        try {
            release();
        } finally {
            super.finalize();
        }
    }

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1212e
    public native String getName();

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    public native MediaFormat getOutputFormat();

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1210c
    public native void queueInputBuffer(int i9, int i10, int i11, long j9, int i12);

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    public native void release();

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    public native void start();

    @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
    public native void stop();
}
