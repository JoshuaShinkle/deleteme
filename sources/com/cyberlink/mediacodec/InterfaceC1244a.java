package com.cyberlink.mediacodec;

import android.media.MediaCodec;
import com.google.android.exoplayer2.C3322C;
import java.nio.ByteBuffer;

/* renamed from: com.cyberlink.mediacodec.a */
/* loaded from: classes.dex */
public interface InterfaceC1244a {

    /* renamed from: com.cyberlink.mediacodec.a$a */
    public static class a extends b {
        public a(int i9, MediaCodec.BufferInfo bufferInfo) {
            this(i9, bufferInfo, 48000, 16, 2);
        }

        /* renamed from: c */
        public static void m5532c(String str, Object... objArr) {
        }

        @Override // com.cyberlink.mediacodec.InterfaceC1244a
        /* renamed from: b */
        public boolean mo5531b(InterfaceC1244a interfaceC1244a) {
            a aVar = (a) interfaceC1244a;
            if (aVar == null) {
                return false;
            }
            this.f6135f = aVar.f6135f + aVar.f6136g;
            m5532c("Adjust FrameTime from " + this.f6131b + " to " + this.f6135f, new Object[0]);
            return true;
        }

        /* renamed from: d */
        public void m5533d(int i9, int i10, int i11) {
            this.f6136g = (((this.f6132c / ((i10 + 7) / 8)) / i11) * C3322C.MICROS_PER_SECOND) / i9;
        }

        public a(int i9, MediaCodec.BufferInfo bufferInfo, int i10, int i11, int i12) {
            super(i9, bufferInfo);
            m5533d(i10, i11, i12);
        }
    }

    /* renamed from: com.cyberlink.mediacodec.a$b */
    public static abstract class b implements InterfaceC1244a {

        /* renamed from: a */
        public int f6130a;

        /* renamed from: b */
        public long f6131b;

        /* renamed from: c */
        public int f6132c;

        /* renamed from: d */
        public int f6133d;

        /* renamed from: e */
        public int f6134e;

        /* renamed from: f */
        public long f6135f;

        /* renamed from: g */
        public long f6136g;

        public b(int i9, MediaCodec.BufferInfo bufferInfo) {
            this.f6130a = i9;
            long j9 = bufferInfo.presentationTimeUs;
            this.f6131b = j9;
            this.f6132c = bufferInfo.size;
            this.f6133d = bufferInfo.offset;
            this.f6134e = bufferInfo.flags;
            this.f6135f = j9;
        }

        @Override // com.cyberlink.mediacodec.InterfaceC1244a
        /* renamed from: a */
        public int mo5530a(InterfaceC1244a interfaceC1244a) {
            return (int) (this.f6131b - ((b) interfaceC1244a).f6131b);
        }
    }

    /* renamed from: com.cyberlink.mediacodec.a$c */
    public static class c implements InterfaceC1244a {

        /* renamed from: a */
        public ByteBuffer f6137a;

        /* renamed from: b */
        public int f6138b;

        /* renamed from: c */
        public long f6139c;

        /* renamed from: d */
        public int f6140d;

        public c(ByteBuffer byteBuffer) {
            if (byteBuffer != null) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.capacity());
                this.f6137a = byteBufferAllocate;
                byteBufferAllocate.clear();
            } else {
                this.f6137a = null;
            }
            this.f6138b = 0;
            this.f6139c = 0L;
            this.f6140d = 0;
        }

        @Override // com.cyberlink.mediacodec.InterfaceC1244a
        /* renamed from: a */
        public int mo5530a(InterfaceC1244a interfaceC1244a) {
            return (int) (this.f6139c - ((c) interfaceC1244a).f6139c);
        }

        @Override // com.cyberlink.mediacodec.InterfaceC1244a
        /* renamed from: b */
        public boolean mo5531b(InterfaceC1244a interfaceC1244a) {
            return false;
        }
    }

    /* renamed from: a */
    int mo5530a(InterfaceC1244a interfaceC1244a);

    /* renamed from: b */
    boolean mo5531b(InterfaceC1244a interfaceC1244a);

    /* renamed from: com.cyberlink.mediacodec.a$d */
    public static class d extends b {

        /* renamed from: h */
        public long f6141h;

        public d(int i9, MediaCodec.BufferInfo bufferInfo) {
            this(i9, bufferInfo, 30.0f);
            this.f6141h = this.f6136g;
        }

        /* renamed from: c */
        public static void m5534c(String str, Object... objArr) {
        }

        @Override // com.cyberlink.mediacodec.InterfaceC1244a.b, com.cyberlink.mediacodec.InterfaceC1244a
        /* renamed from: a */
        public int mo5530a(InterfaceC1244a interfaceC1244a) {
            long j9 = this.f6131b - ((b) interfaceC1244a).f6131b;
            this.f6141h = j9;
            return (int) j9;
        }

        @Override // com.cyberlink.mediacodec.InterfaceC1244a
        /* renamed from: b */
        public boolean mo5531b(InterfaceC1244a interfaceC1244a) {
            d dVar = (d) interfaceC1244a;
            if (dVar == null) {
                return false;
            }
            long j9 = dVar.f6135f;
            long j10 = dVar.f6141h;
            this.f6135f = j9 + j10;
            this.f6141h = j10;
            m5534c("Adjust FrameTime from " + this.f6131b + " to " + this.f6135f, new Object[0]);
            return true;
        }

        /* renamed from: d */
        public void m5535d(float f9) {
            this.f6136g = (long) (1000000.0f / f9);
        }

        public d(int i9, MediaCodec.BufferInfo bufferInfo, float f9) {
            super(i9, bufferInfo);
            m5535d(f9);
        }
    }
}
