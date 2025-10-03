package com.cyberlink.media;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import p104j2.C5093d;

@TargetApi(16)
/* loaded from: classes.dex */
public final class CLMediaCodec {

    /* renamed from: i */
    public static final Set<CLMediaCodec> f5842i = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));

    /* renamed from: a */
    public InterfaceC1211d f5843a;

    /* renamed from: b */
    public final String f5844b;

    /* renamed from: c */
    public InterfaceC1210c f5845c;

    /* renamed from: d */
    public int f5846d;

    /* renamed from: e */
    public boolean f5847e;

    /* renamed from: f */
    public volatile boolean f5848f;

    /* renamed from: g */
    public ByteBuffer[] f5849g;

    /* renamed from: h */
    public ByteBuffer[] f5850h;

    public enum Vendor {
        CYBERLINK,
        QUALCOMM,
        NVIDIA,
        SAMSUNG,
        INTEL,
        MEDIATEK,
        ROCKCHIP,
        TI,
        MARVELL,
        UNKNOWN;

        /* renamed from: a */
        public static Vendor m5322a(String str) {
            return (str.startsWith("OMX.CL.") || str.startsWith("CL.")) ? CYBERLINK : str.startsWith("OMX.qcom.") ? QUALCOMM : str.startsWith("OMX.Nvidia.") ? NVIDIA : (str.startsWith("OMX.SEC.") || str.startsWith("OMX.Exynos.")) ? SAMSUNG : str.startsWith("OMX.Intel.") ? INTEL : str.startsWith("OMX.MTK.") ? MEDIATEK : str.startsWith("OMX.rk.") ? ROCKCHIP : str.startsWith("OMX.TI.") ? TI : str.startsWith("OMX.MARVELL.") ? MARVELL : UNKNOWN;
        }
    }

    /* renamed from: com.cyberlink.media.CLMediaCodec$a */
    public class C1208a implements InterfaceC1210c {

        /* renamed from: a */
        public ByteBuffer[] f5862a;

        /* renamed from: b */
        public final Queue<Integer> f5863b;

        /* renamed from: c */
        public final Queue<C1209b> f5864c;

        public C1208a(int i9) {
            this.f5862a = CLMediaCodec.this.f5843a.mo5327c();
            CLMediaCodec.this.f5849g = new ByteBuffer[i9];
            System.arraycopy(this.f5862a, 0, CLMediaCodec.this.f5849g, 0, this.f5862a.length);
            int length = i9 - this.f5862a.length;
            this.f5863b = new ArrayDeque(length);
            this.f5864c = new ArrayDeque(length);
            int iCapacity = this.f5862a[0].capacity();
            for (int length2 = this.f5862a.length; length2 < i9; length2++) {
                CLMediaCodec.this.f5849g[length2] = ByteBuffer.allocateDirect(iCapacity).order(ByteOrder.nativeOrder());
                this.f5863b.add(Integer.valueOf(length2));
            }
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1210c
        public int dequeueInputBuffer(long j9) {
            if (j9 < 0 && !this.f5863b.isEmpty()) {
                j9 = 0;
            }
            long jM5306k = CLMediaCodec.m5306k(j9);
            if (m5324f(jM5306k) > 0) {
                return m5323e();
            }
            if (j9 > 0) {
                long jNanoTime = System.nanoTime();
                if (jNanoTime > jM5306k) {
                    return m5323e();
                }
                j9 = (jM5306k - jNanoTime) / 1000;
            }
            int iDequeueInputBuffer = CLMediaCodec.this.f5843a.dequeueInputBuffer(j9);
            return iDequeueInputBuffer >= 0 ? iDequeueInputBuffer : m5323e();
        }

        /* renamed from: e */
        public final int m5323e() {
            Integer numPoll = this.f5863b.poll();
            if (numPoll != null) {
                return numPoll.intValue();
            }
            return -1;
        }

        /* renamed from: f */
        public int m5324f(long j9) {
            long j10;
            while (!this.f5864c.isEmpty()) {
                C1209b c1209bElement = this.f5864c.element();
                if (c1209bElement.f5866a < this.f5862a.length) {
                    CLMediaCodec.this.f5843a.queueInputBuffer(c1209bElement.f5866a, c1209bElement.f5867b, c1209bElement.f5868c, c1209bElement.f5869d, c1209bElement.f5870e);
                } else {
                    if (j9 > 0) {
                        long jNanoTime = System.nanoTime();
                        if (jNanoTime > j9) {
                            break;
                        }
                        j10 = (j9 - jNanoTime) / 1000;
                    } else {
                        j10 = j9;
                    }
                    int iDequeueInputBuffer = CLMediaCodec.this.f5843a.dequeueInputBuffer(j10);
                    if (iDequeueInputBuffer < 0) {
                        break;
                    }
                    ByteBuffer byteBuffer = this.f5862a[iDequeueInputBuffer];
                    CLMediaCodec.m5307s(byteBuffer, c1209bElement.f5867b, c1209bElement.f5868c);
                    byteBuffer.put(CLMediaCodec.this.f5849g[c1209bElement.f5866a]);
                    byteBuffer.position(c1209bElement.f5867b);
                    CLMediaCodec.this.f5843a.queueInputBuffer(iDequeueInputBuffer, c1209bElement.f5867b, c1209bElement.f5868c, c1209bElement.f5869d, c1209bElement.f5870e);
                    this.f5863b.add(Integer.valueOf(c1209bElement.f5866a));
                }
                this.f5864c.remove();
            }
            return this.f5864c.size();
        }

        @Override // com.cyberlink.media.CLMediaCodec.InterfaceC1210c
        public void queueInputBuffer(int i9, int i10, int i11, long j9, int i12) {
            this.f5864c.add(new C1209b(i9, i10, i11, j9, i12));
            m5324f(0L);
        }
    }

    /* renamed from: com.cyberlink.media.CLMediaCodec$b */
    public static class C1209b {

        /* renamed from: a */
        public final int f5866a;

        /* renamed from: b */
        public final int f5867b;

        /* renamed from: c */
        public final int f5868c;

        /* renamed from: d */
        public final long f5869d;

        /* renamed from: e */
        public final int f5870e;

        public C1209b(int i9, int i10, int i11, long j9, int i12) {
            this.f5866a = i9;
            this.f5867b = i10;
            this.f5868c = i11;
            this.f5869d = j9;
            this.f5870e = i12;
        }
    }

    /* renamed from: com.cyberlink.media.CLMediaCodec$c */
    public interface InterfaceC1210c {
        int dequeueInputBuffer(long j9);

        void queueInputBuffer(int i9, int i10, int i11, long j9, int i12);
    }

    @TargetApi(16)
    /* renamed from: com.cyberlink.media.CLMediaCodec$d */
    public interface InterfaceC1211d extends InterfaceC1210c {
        /* renamed from: a */
        ByteBuffer[] mo5325a();

        /* renamed from: b */
        void mo5326b(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i9);

        /* renamed from: c */
        ByteBuffer[] mo5327c();

        /* renamed from: d */
        void mo5328d(int i9, boolean z8);

        int dequeueOutputBuffer(MediaCodec.BufferInfo bufferInfo, long j9);

        MediaFormat getOutputFormat();

        void release();

        void start();

        void stop();
    }

    /* renamed from: com.cyberlink.media.CLMediaCodec$e */
    public interface InterfaceC1212e {
        String getName();
    }

    /* renamed from: com.cyberlink.media.CLMediaCodec$f */
    public static class C1213f {

        /* renamed from: a */
        public static final Map<String, List<MediaCodecInfo>> f5871a;

        /* renamed from: b */
        public static final Set<String> f5872b;

        /* renamed from: c */
        public static final Map<String, List<MediaCodecInfo>> f5873c;

        /* renamed from: d */
        public static final Set<String> f5874d;

        /* renamed from: e */
        public static final Vendor f5875e;

        static {
            HashMap map = new HashMap();
            HashSet hashSet = new HashSet();
            HashMap map2 = new HashMap();
            HashSet hashSet2 = new HashSet();
            Vendor vendorM5322a = Vendor.UNKNOWN;
            for (int i9 = 0; i9 < MediaCodecList.getCodecCount(); i9++) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i9);
                String name = codecInfoAt.getName();
                if (vendorM5322a == Vendor.UNKNOWN) {
                    vendorM5322a = Vendor.m5322a(name);
                }
                if (codecInfoAt.isEncoder()) {
                    m5332d(map2, codecInfoAt);
                    hashSet2.add(name);
                } else {
                    m5332d(map, codecInfoAt);
                    hashSet.add(name);
                }
            }
            f5871a = Collections.unmodifiableMap(map);
            f5872b = Collections.unmodifiableSet(hashSet);
            f5873c = Collections.unmodifiableMap(map2);
            f5874d = Collections.unmodifiableSet(hashSet2);
            f5875e = vendorM5322a;
        }

        /* renamed from: a */
        public static boolean m5329a(String str) {
            return f5871a.containsKey(str);
        }

        /* renamed from: b */
        public static List<MediaCodecInfo> m5330b(String str, Map<String, List<MediaCodecInfo>> map) {
            List<MediaCodecInfo> list = map.get(str);
            return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        }

        /* renamed from: c */
        public static List<MediaCodecInfo> m5331c(String str) {
            return m5330b(str, f5871a);
        }

        /* renamed from: d */
        public static void m5332d(Map<String, List<MediaCodecInfo>> map, MediaCodecInfo mediaCodecInfo) {
            for (String str : mediaCodecInfo.getSupportedTypes()) {
                List<MediaCodecInfo> arrayList = map.get(str);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    map.put(str, arrayList);
                }
                arrayList.add(mediaCodecInfo);
            }
        }
    }

    public CLMediaCodec(InterfaceC1211d interfaceC1211d) {
        this(interfaceC1211d, null);
    }

    /* renamed from: g */
    public static CLMediaCodec m5304g(String str, boolean z8) {
        return new CLMediaCodec(m5305h(str, z8));
    }

    /* renamed from: h */
    public static InterfaceC1211d m5305h(String str, boolean z8) {
        Log.v("CLMediaCodec", "createDecoderByType " + str);
        if (z8) {
            if (!C5093d.m19937h(str) && !C5093d.m19940k(str) && C1213f.m5329a(str)) {
                try {
                    return AbstractC1214a.m5348e(MediaCodec.createDecoderByType(str), false);
                } catch (Throwable unused) {
                }
            }
            try {
                return CLMediaCodecExtra.m5333e(str);
            } catch (IllegalStateException unused2) {
            }
        }
        if (C1213f.m5329a(str)) {
            return AbstractC1214a.m5348e(MediaCodec.createDecoderByType(str), false);
        }
        throw new IllegalStateException();
    }

    /* renamed from: k */
    public static long m5306k(long j9) {
        if (j9 <= 0) {
            return j9;
        }
        return (j9 * 1000) + System.nanoTime();
    }

    /* renamed from: s */
    public static void m5307s(ByteBuffer byteBuffer, int i9, int i10) {
        if (byteBuffer != null) {
            byteBuffer.limit(i10 + i9).position(i9);
        }
    }

    /* renamed from: t */
    public static void m5308t(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        m5307s(byteBuffer, bufferInfo.offset, bufferInfo.size);
    }

    /* renamed from: e */
    public boolean m5309e() {
        return this.f5843a instanceof AbstractC1214a;
    }

    /* renamed from: f */
    public void m5310f(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i9) {
        this.f5843a.mo5326b(mediaFormat, surface, mediaCrypto, i9);
    }

    /* renamed from: i */
    public int m5311i(long j9) {
        int iDequeueInputBuffer = this.f5845c.dequeueInputBuffer(j9);
        if (iDequeueInputBuffer >= 0) {
            this.f5849g[iDequeueInputBuffer].clear();
            this.f5848f = true;
        }
        return iDequeueInputBuffer;
    }

    /* renamed from: j */
    public int m5312j(MediaCodec.BufferInfo bufferInfo, long j9) {
        int iDequeueOutputBuffer = this.f5843a.dequeueOutputBuffer(bufferInfo, j9);
        if (iDequeueOutputBuffer >= 0) {
            m5308t(this.f5850h[iDequeueOutputBuffer], bufferInfo);
            this.f5848f = true;
        } else if (iDequeueOutputBuffer == -3) {
            this.f5850h = this.f5843a.mo5325a();
        }
        return iDequeueOutputBuffer;
    }

    /* renamed from: l */
    public ByteBuffer[] m5313l() {
        return this.f5849g;
    }

    /* renamed from: m */
    public String m5314m() {
        InterfaceC1211d interfaceC1211d = this.f5843a;
        if (interfaceC1211d instanceof InterfaceC1212e) {
            return ((InterfaceC1212e) interfaceC1211d).getName();
        }
        String str = this.f5844b;
        if (str != null) {
            return str;
        }
        throw new UnsupportedOperationException();
    }

    /* renamed from: n */
    public ByteBuffer[] m5315n() {
        return this.f5850h;
    }

    /* renamed from: o */
    public MediaFormat m5316o() {
        return this.f5843a.getOutputFormat();
    }

    /* renamed from: p */
    public void m5317p(int i9, int i10, int i11, long j9, int i12) {
        m5307s(this.f5849g[i9], i10, i11);
        this.f5845c.queueInputBuffer(i9, i10, i11, j9, i12);
    }

    /* renamed from: q */
    public void m5318q() {
        InterfaceC1211d interfaceC1211d = this.f5843a;
        if (interfaceC1211d != null) {
            interfaceC1211d.release();
            this.f5843a = null;
        }
    }

    /* renamed from: r */
    public void m5319r(int i9, boolean z8) {
        this.f5843a.mo5328d(i9, z8);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CLMediaCodec [");
        sb.append(this.f5843a);
        sb.append(", name=");
        InterfaceC1211d interfaceC1211d = this.f5843a;
        sb.append(interfaceC1211d instanceof InterfaceC1212e ? ((InterfaceC1212e) interfaceC1211d).getName() : this.f5844b);
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: u */
    public void m5320u() {
        this.f5843a.start();
        ByteBuffer[] byteBufferArrMo5327c = this.f5843a.mo5327c();
        this.f5849g = byteBufferArrMo5327c;
        this.f5845c = (this.f5847e || byteBufferArrMo5327c.length >= this.f5846d) ? this.f5843a : new C1208a(this.f5846d);
        this.f5850h = this.f5843a.mo5325a();
    }

    /* renamed from: v */
    public void m5321v() {
        this.f5848f = false;
        this.f5847e = false;
        this.f5845c = null;
        this.f5849g = null;
        this.f5850h = null;
        this.f5843a.stop();
    }

    public CLMediaCodec(InterfaceC1211d interfaceC1211d, String str) {
        if (interfaceC1211d == null) {
            throw new IllegalStateException("codec implementation is null.");
        }
        this.f5843a = interfaceC1211d;
        this.f5844b = str;
    }
}
