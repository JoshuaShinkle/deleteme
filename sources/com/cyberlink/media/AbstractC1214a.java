package com.cyberlink.media;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.cyberlink.media.CLMediaCodec;
import java.nio.ByteBuffer;

@TargetApi(16)
/* renamed from: com.cyberlink.media.a */
/* loaded from: classes.dex */
public abstract class AbstractC1214a implements CLMediaCodec.InterfaceC1211d {

    /* renamed from: com.cyberlink.media.a$b */
    public static class b extends AbstractC1214a {

        /* renamed from: a */
        public final MediaCodec f5880a;

        /* renamed from: b */
        public boolean f5881b;

        public b(MediaCodec mediaCodec, boolean z8) {
            super();
            this.f5880a = mediaCodec;
            this.f5881b = z8;
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        /* renamed from: a */
        public ByteBuffer[] mo5325a() {
            return this.f5880a.getOutputBuffers();
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        /* renamed from: b */
        public void mo5326b(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i9) {
            this.f5881b |= (i9 & 1) != 0;
            this.f5880a.configure(mediaFormat, surface, mediaCrypto, i9);
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        /* renamed from: c */
        public ByteBuffer[] mo5327c() {
            return this.f5880a.getInputBuffers();
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        /* renamed from: d */
        public void mo5328d(int i9, boolean z8) {
            this.f5880a.releaseOutputBuffer(i9, z8);
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1210c
        public int dequeueInputBuffer(long j9) {
            return this.f5880a.dequeueInputBuffer(j9);
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        public int dequeueOutputBuffer(MediaCodec.BufferInfo bufferInfo, long j9) {
            return this.f5880a.dequeueOutputBuffer(bufferInfo, j9);
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        public MediaFormat getOutputFormat() {
            return this.f5880a.getOutputFormat();
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1210c
        public void queueInputBuffer(int i9, int i10, int i11, long j9, int i12) throws MediaCodec.CryptoException {
            this.f5880a.queueInputBuffer(i9, i10, i11, j9, i12);
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        public void release() {
            this.f5880a.release();
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        public void start() {
            this.f5880a.start();
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1211d
        public void stop() {
            this.f5880a.stop();
        }
    }

    @TargetApi(18)
    /* renamed from: com.cyberlink.media.a$c */
    public static class c extends b implements CLMediaCodec.InterfaceC1212e {
        public c(MediaCodec mediaCodec, boolean z8) {
            super(mediaCodec, z8);
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1212e
        public String getName() {
            return this.f5880a.getName();
        }
    }

    @TargetApi(19)
    /* renamed from: com.cyberlink.media.a$d */
    public static class d extends c {
        public d(MediaCodec mediaCodec, boolean z8) {
            super(mediaCodec, z8);
        }
    }

    /* renamed from: e */
    public static AbstractC1214a m5348e(MediaCodec mediaCodec, boolean z8) {
        return m5349f(mediaCodec, z8, true);
    }

    /* renamed from: f */
    public static AbstractC1214a m5349f(MediaCodec mediaCodec, boolean z8, boolean z9) {
        if (mediaCodec != null) {
            return new d(mediaCodec, z8);
        }
        throw new IllegalStateException("codec is null.");
    }

    public AbstractC1214a() {
    }
}
