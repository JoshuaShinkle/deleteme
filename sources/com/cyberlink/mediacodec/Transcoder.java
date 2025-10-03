package com.cyberlink.mediacodec;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import com.cyberlink.media.C1215b;
import com.cyberlink.media.C1217d;
import com.cyberlink.media.CLMediaCodec;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.util.GmsVersion;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Locale;

/* loaded from: classes.dex */
public class Transcoder extends Thread {

    /* renamed from: J */
    public static final String f6063J = "Transcoder";

    /* renamed from: e */
    public Context f6076e;

    /* renamed from: b */
    public boolean f6073b = false;

    /* renamed from: c */
    public String f6074c = null;

    /* renamed from: d */
    public Uri f6075d = null;

    /* renamed from: f */
    public C1215b f6077f = null;

    /* renamed from: g */
    public CLMediaCodec f6078g = null;

    /* renamed from: h */
    public boolean f6079h = false;

    /* renamed from: i */
    public boolean f6080i = true;

    /* renamed from: j */
    public CLMediaCodec f6081j = null;

    /* renamed from: k */
    public String f6082k = null;

    /* renamed from: l */
    public File f6083l = null;

    /* renamed from: m */
    public MediaMuxer f6084m = null;

    /* renamed from: n */
    public MediaCodec f6085n = null;

    /* renamed from: o */
    public MediaCodec f6086o = null;

    /* renamed from: p */
    public long f6087p = -1;

    /* renamed from: q */
    public long f6088q = -1;

    /* renamed from: r */
    public long f6089r = -1;

    /* renamed from: s */
    public long f6090s = -1;

    /* renamed from: t */
    public float f6091t = 30.0f;

    /* renamed from: u */
    public int f6092u = 48000;

    /* renamed from: v */
    public int f6093v = 16;

    /* renamed from: w */
    public int f6094w = 2;

    /* renamed from: x */
    public int f6095x = -1;

    /* renamed from: y */
    public int f6096y = -1;

    /* renamed from: z */
    public int f6097z = 30;

    /* renamed from: A */
    public AssetFileDescriptor f6064A = null;

    /* renamed from: B */
    public boolean f6065B = false;

    /* renamed from: C */
    public int f6066C = -1;

    /* renamed from: D */
    public TRANSCODER_STATUS f6067D = TRANSCODER_STATUS.STATUS_PENDING;

    /* renamed from: E */
    public String f6068E = "Pending.";

    /* renamed from: F */
    public long f6069F = -1;

    /* renamed from: G */
    public boolean f6070G = false;

    /* renamed from: H */
    public Handler f6071H = null;

    /* renamed from: I */
    public InterfaceC1243a f6072I = null;

    public enum TRANSCODER_STATUS {
        STATUS_SUCCESS,
        STATUS_PENDING,
        STATUS_TRANSCODING,
        STATUS_ERROR_UNSUPPORTED_SDK_VERSION,
        STATUS_ERROR_INVALID_INPUT,
        STATUS_ERROR_CREATING_EXTRACTOR,
        STATUS_ERROR_SETTING_EXTRACTOR_SOURCE,
        STATUS_ERROR_RELEASING_EXTRACTOR,
        STATUS_ERROR_CREATING_MUXER,
        STATUS_ERROR_CONFIGURING_MUXER,
        STATUS_ERROR_RELEASING_MUXER,
        STATUS_ERROR_CREATING_VIDEO_DECODER,
        STATUS_ERROR_CONFIGURING_VIDEO_DECODER,
        STATUS_ERROR_FORMAT_CHANGED_VIDEO_DECODER,
        STATUS_ERROR_RELEASING_VIDEO_DECODER,
        STATUS_ERROR_CREATING_AUDIO_DECODER,
        STATUS_ERROR_CONFIGURING_AUDIO_DECODER,
        STATUS_ERROR_FORMAT_CHANGED_AUDIO_DECODER,
        STATUS_ERROR_RELEASING_AUDIO_DECODER,
        STATUS_ERROR_CREATING_VIDEO_ENCODER,
        STATUS_ERROR_CONFIGURING_VIDEO_ENCODER,
        STATUS_ERROR_FORMAT_CHANGED_VIDEO_ENCODER,
        STATUS_ERROR_RELEASING_VIDEO_ENCODER,
        STATUS_ERROR_CREATING_AUDIO_ENCODER,
        STATUS_ERROR_CONFIGURING_AUDIO_ENCODER,
        STATUS_ERROR_FORMAT_CHANGED_AUDIO_ENCODER,
        STATUS_ERROR_RELEASING_AUDIO_ENCODER,
        STATUS_ERROR_INSUFFICIENT_LICENSE,
        STATUS_ERROR_STORAGE_FULL,
        STATUS_ERROR_RUNTIME_EXCEPTION,
        STATUS_ERROR_UNKNOWN
    }

    /* renamed from: com.cyberlink.mediacodec.Transcoder$a */
    public interface InterfaceC1243a {
        /* renamed from: a */
        void mo5528a(int i9);

        /* renamed from: b */
        void mo5529b(Transcoder transcoder);
    }

    /* renamed from: a */
    public static boolean m5502a(int i9, int i10) {
        return (i9 & i10) == i10;
    }

    /* renamed from: b */
    public static void m5503b(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byteBuffer2.rewind();
        byteBuffer.put(byteBuffer2);
        byteBuffer2.rewind();
        byteBuffer.flip();
    }

    /* renamed from: c */
    public static void m5504c(String str, Object... objArr) {
    }

    /* renamed from: d */
    public static void m5505d(String str, Object... objArr) {
    }

    /* renamed from: e */
    public static void m5506e(String str, Object... objArr) {
        Log.e(f6063J, String.format(Locale.US, str, objArr));
    }

    /* renamed from: f */
    public static void m5507f(String str, Object... objArr) {
    }

    /* renamed from: g */
    public static void m5508g(String str, Object... objArr) {
        Log.d(f6063J, String.format(Locale.US, str, objArr));
    }

    /* renamed from: h */
    public static void m5509h(String str, Object... objArr) {
    }

    /* renamed from: i */
    public static void m5510i(String str, Object... objArr) {
    }

    /* renamed from: j */
    public static void m5511j(String str, Object... objArr) {
    }

    /* renamed from: k */
    public static void m5512k(String str, Object... objArr) {
    }

    /* renamed from: l */
    public static int m5513l(int i9, int i10) {
        return i9 <= 22050 ? 64000 : 128000;
    }

    /* renamed from: m */
    public static int m5514m(int i9, int i10, int i11) {
        if (i10 <= 480) {
            if (i11 <= 30) {
                return 1500000;
            }
            if (i11 <= 60) {
                return 4000000;
            }
            return GmsVersion.VERSION_SAGA;
        }
        if (i10 <= 720) {
            return i11 <= 30 ? GmsVersion.VERSION_LONGHORN : i11 <= 60 ? 9000000 : 16000000;
        }
        if (i11 <= 30) {
            return 12000000;
        }
        return i11 <= 60 ? 20000000 : 34000000;
    }

    /* renamed from: n */
    public static void m5515n(String str, boolean z8) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        objArr[1] = z8 ? "encoding" : "decoding";
        m5508g("Codecs that support %s %s:", objArr);
        int codecCount = MediaCodecList.getCodecCount();
        for (int i9 = 0; i9 < codecCount; i9++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i9);
            if (codecInfoAt.isEncoder() == z8) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (String str2 : supportedTypes) {
                    if (str2.equalsIgnoreCase(str)) {
                        m5508g("  Codec %s, supported %d types:", codecInfoAt.getName(), Integer.valueOf(supportedTypes.length));
                        for (int i10 = 0; i10 < supportedTypes.length; i10++) {
                            m5508g("    Type %s : %s", Integer.valueOf(i10), supportedTypes[i10]);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0030 A[PHI: r7
      0x0030: PHI (r7v1 double) = (r7v0 double), (r7v0 double), (r7v3 double) binds: [B:11:0x0020, B:13:0x0026, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    /* renamed from: o */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public double m5516o() {
        double d9;
        double d10;
        long j9 = this.f6087p;
        double d11 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (j9 >= 0) {
            long j10 = this.f6089r;
            if (j10 >= 0) {
                d9 = j10 / j9;
                if (d9 > 1.0d) {
                    d9 = 1.0d;
                }
            } else {
                d9 = 0.0d;
            }
        }
        long j11 = this.f6088q;
        if (j11 >= 0) {
            long j12 = this.f6090s;
            if (j12 >= 0) {
                d11 = j12 / j11;
                d10 = d11 <= 1.0d ? d11 : 1.0d;
            }
        }
        return (j9 < 0 || j11 < 0) ? j9 >= 0 ? d9 : d10 : d10 < d9 ? d10 : d9;
    }

    /* renamed from: p */
    public TRANSCODER_STATUS m5517p() {
        return this.f6067D;
    }

    /* renamed from: q */
    public boolean m5518q() {
        return this.f6065B;
    }

    /* renamed from: r */
    public final void m5519r() {
        this.f6087p = -1L;
        this.f6088q = -1L;
        this.f6089r = -1L;
        this.f6090s = -1L;
        this.f6065B = false;
        this.f6066C = -1;
        this.f6067D = TRANSCODER_STATUS.STATUS_PENDING;
        this.f6068E = "Transcoding...";
        this.f6069F = -1L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:1022:0x177a, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1023:0x177b, code lost:
    
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1024:0x1792, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1025:0x1795, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1026:0x1796, code lost:
    
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1027:0x17af, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1028:0x17b0, code lost:
    
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r36 = r15;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r63 = r5;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1029:0x17ce, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1030:0x17cf, code lost:
    
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r36 = r15;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1031:0x17ec, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1032:0x17ed, code lost:
    
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r63 = r5;
        r6 = true;
        r36 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1033:0x180b, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1034:0x180c, code lost:
    
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r63 = r5;
        r36 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1035:0x1829, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1036:0x182a, code lost:
    
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1037:0x1840, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1038:0x1841, code lost:
    
        r72 = r8;
        r52 = r9;
        r5 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1041:0x186e, code lost:
    
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1048:0x1895, code lost:
    
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1084:0x1a03, code lost:
    
        r86.f6067D = ((com.cyberlink.mediacodec.Transcoder.MyRuntimeException) r5).m5527a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1086:0x1a0f, code lost:
    
        if ((r5 instanceof com.cyberlink.media.InsufficientLicenseException) != false) goto L1087;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1087:0x1a11, code lost:
    
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6100C;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1089:0x1a18, code lost:
    
        if ((r5 instanceof java.lang.RuntimeException) != false) goto L1090;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1090:0x1a1a, code lost:
    
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6102E;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1091:0x1a1f, code lost:
    
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6103F;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1095:0x1a40, code lost:
    
        r3 = r86.f6068E + r3 + r86.f6069F + r4;
        r86.f6068E = r3;
        m5508g(r3, new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:1097:0x1a63, code lost:
    
        r63.m5544f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1099:0x1a68, code lost:
    
        r72.m5559e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1101:0x1a6d, code lost:
    
        r52.m5571k();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1104:0x1a74, code lost:
    
        r3.m5362l();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1106:0x1a78, code lost:
    
        m5506e(r2, new java.lang.Object[0]);
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6112i;
        r86.f6068E = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1107:0x1a84, code lost:
    
        r86.f6077f = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1110:0x1a8b, code lost:
    
        if (r37 != false) goto L1323;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1111:0x1a8d, code lost:
    
        r2.m5321v();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1112:0x1a90, code lost:
    
        r86.f6078g.m5318q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1114:0x1a96, code lost:
    
        m5506e(r7, new java.lang.Object[0]);
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6119p;
        r86.f6068E = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1115:0x1aa2, code lost:
    
        r86.f6078g = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1118:0x1aa9, code lost:
    
        if (r46 != false) goto L1271;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1119:0x1aab, code lost:
    
        r2.m5321v();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1120:0x1aae, code lost:
    
        r86.f6081j.m5318q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1122:0x1ab4, code lost:
    
        m5506e(r8, new java.lang.Object[0]);
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6123t;
        r86.f6068E = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1123:0x1ac0, code lost:
    
        r86.f6081j = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1126:0x1ac7, code lost:
    
        if (r36 != false) goto L1289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1127:0x1ac9, code lost:
    
        r2.stop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1128:0x1acc, code lost:
    
        r86.f6085n.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1130:0x1ad2, code lost:
    
        m5506e(r9, new java.lang.Object[0]);
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6127x;
        r86.f6068E = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1131:0x1ade, code lost:
    
        r86.f6085n = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1134:0x1ae5, code lost:
    
        if (r54 != false) goto L1216;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1135:0x1ae7, code lost:
    
        r2.stop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1136:0x1aea, code lost:
    
        r86.f6086o.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1138:0x1af0, code lost:
    
        m5506e(r10, new java.lang.Object[0]);
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6099B;
        r86.f6068E = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1139:0x1afc, code lost:
    
        r86.f6086o = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1142:0x1b03, code lost:
    
        if (r59 != false) goto L1246;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1143:0x1b05, code lost:
    
        r2.stop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1144:0x1b08, code lost:
    
        r86.f6084m.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:1146:0x1b0e, code lost:
    
        m5506e(r11, new java.lang.Object[0]);
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6115l;
        r86.f6068E = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1147:0x1b1a, code lost:
    
        r86.f6084m = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1424:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x0481, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "Cannot create videoDecoder!", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6116m);
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0633, code lost:
    
        r31 = r11;
        r29 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:309:0x0637, code lost:
    
        r2 = r86.f6078g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x0639, code lost:
    
        if (r2 != null) goto L320;
     */
    /* JADX WARN: Code restructure failed: missing block: B:312:0x063d, code lost:
    
        if (r86.f6081j == null) goto L314;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x0649, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "Not a valid video clip", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6109f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x064e, code lost:
    
        if (r2 == null) goto L344;
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0650, code lost:
    
        r2.m5320u();
     */
    /* JADX WARN: Code restructure failed: missing block: B:322:0x0653, code lost:
    
        r2 = r86.f6078g.m5313l();
        r5 = new java.lang.Object[2];
        r5[0] = java.lang.Integer.valueOf(r2.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0667, code lost:
    
        if (r2.length <= 0) goto L327;
     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x0669, code lost:
    
        r4 = r2[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x066b, code lost:
    
        if (r4 == null) goto L327;
     */
    /* JADX WARN: Code restructure failed: missing block: B:326:0x066d, code lost:
    
        r4 = r4.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x0672, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:328:0x0673, code lost:
    
        r5[1] = java.lang.Integer.valueOf(r4);
        m5511j("videoDecoder InputBuffer size %d, capacity %d", r5);
        r3 = r86.f6078g.m5315n();
        r6 = new java.lang.Object[2];
        r6[0] = java.lang.Integer.valueOf(r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x0691, code lost:
    
        if (r3.length <= 0) goto L333;
     */
    /* JADX WARN: Code restructure failed: missing block: B:330:0x0693, code lost:
    
        r5 = r3[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x0695, code lost:
    
        if (r5 == null) goto L333;
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x0697, code lost:
    
        r5 = r5.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x069c, code lost:
    
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x069d, code lost:
    
        r6[1] = java.lang.Integer.valueOf(r5);
        m5511j("videoDecoder OutputBuffer size %d, capacity %d", r6);
        r4 = new android.media.MediaCodec.BufferInfo();
        r5 = new com.cyberlink.mediacodec.C1247d("VideoDecodedFrame");
        r5.m5553i(r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x06c3, code lost:
    
        if (r86.f6078g.m5314m().contains("SEC") == false) goto L337;
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x06c5, code lost:
    
        r86.f6079h = true;
        m5508g("videoDecoder, drop abnormal video decoded frame for SEC codec", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x06d1, code lost:
    
        r86.f6079h = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:338:0x06d4, code lost:
    
        r6 = new com.cyberlink.mediacodec.C1247d("VideoSample");
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x06db, code lost:
    
        r7 = true;
        r11 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x06de, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:341:0x06df, code lost:
    
        r5 = r0;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r6 = true;
        r36 = false;
        r37 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x06fd, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x06fe, code lost:
    
        r5 = r0;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r36 = false;
        r37 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x071b, code lost:
    
        r2 = null;
        r3 = null;
        r4 = null;
        r5 = null;
        r6 = null;
        r7 = false;
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x0722, code lost:
    
        r14 = r86.f6085n;
     */
    /* JADX WARN: Code restructure failed: missing block: B:346:0x0724, code lost:
    
        if (r14 == null) goto L370;
     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x0726, code lost:
    
        r14.start();
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x0729, code lost:
    
        r14 = r86.f6085n.getInputBuffers();
        r32 = r3;
        r33 = r11;
        r11 = new java.lang.Object[2];
        r11[0] = java.lang.Integer.valueOf(r14.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:0x0742, code lost:
    
        if (r14.length <= 0) goto L353;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0744, code lost:
    
        r3 = r14[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x0746, code lost:
    
        if (r3 == null) goto L353;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x0748, code lost:
    
        r3 = r3.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x074d, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x074e, code lost:
    
        r11[1] = java.lang.Integer.valueOf(r3);
        m5512k("videoEncoder InputBuffer size %d, capacity %d", r11);
        r3 = r86.f6085n.getOutputBuffers();
        r15 = new java.lang.Object[2];
        r15[0] = java.lang.Integer.valueOf(r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x076d, code lost:
    
        if (r3.length <= 0) goto L359;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x076f, code lost:
    
        r14 = r3[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:357:0x0771, code lost:
    
        if (r14 == null) goto L359;
     */
    /* JADX WARN: Code restructure failed: missing block: B:358:0x0773, code lost:
    
        r14 = r14.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x0778, code lost:
    
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x0779, code lost:
    
        r15[1] = java.lang.Integer.valueOf(r14);
        m5512k("videoEncoder OutputBuffer size %d, capacity %d", r15);
        r11 = new android.media.MediaCodec.BufferInfo();
        r14 = new com.cyberlink.mediacodec.C1247d("VideoEncodedFrame");
        r14.m5553i(r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x0794, code lost:
    
        r35 = r3;
        r15 = true;
        r34 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:362:0x079b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:363:0x079c, code lost:
    
        r5 = r0;
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r6 = true;
        r36 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x07ba, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x07bb, code lost:
    
        r5 = r0;
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r36 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:366:0x07d8, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x07d9, code lost:
    
        r5 = r0;
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r6 = true;
        r36 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:368:0x07f7, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:369:0x07f8, code lost:
    
        r5 = r0;
        r37 = r7;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r36 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:370:0x0815, code lost:
    
        r32 = r3;
        r33 = r11;
        r11 = null;
        r14 = null;
        r15 = false;
        r34 = true;
        r35 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x0820, code lost:
    
        r3 = r86.f6081j;
     */
    /* JADX WARN: Code restructure failed: missing block: B:372:0x0822, code lost:
    
        if (r3 == null) goto L410;
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x0824, code lost:
    
        r3.m5320u();
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x0827, code lost:
    
        r3 = r86.f6081j.m5313l();
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x082d, code lost:
    
        r36 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x0831, code lost:
    
        r37 = r7;
        r38 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x0836, code lost:
    
        r14 = new java.lang.Object[2];
        r14[0] = java.lang.Integer.valueOf(r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:379:0x0842, code lost:
    
        if (r3.length <= 0) goto L383;
     */
    /* JADX WARN: Code restructure failed: missing block: B:380:0x0844, code lost:
    
        r7 = r3[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:381:0x0846, code lost:
    
        if (r7 == null) goto L383;
     */
    /* JADX WARN: Code restructure failed: missing block: B:382:0x0848, code lost:
    
        r7 = r7.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x084d, code lost:
    
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:384:0x084e, code lost:
    
        r14[1] = java.lang.Integer.valueOf(r7);
        m5504c("audioDecoder InputBuffer size %d, capacity %d", r14);
        r7 = r86.f6081j.m5315n();
        r39 = r3;
        r3 = new java.lang.Object[2];
        r3[0] = java.lang.Integer.valueOf(r7.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:0x0870, code lost:
    
        if (r7.length <= 0) goto L389;
     */
    /* JADX WARN: Code restructure failed: missing block: B:386:0x0872, code lost:
    
        r15 = r7[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:387:0x0874, code lost:
    
        if (r15 == null) goto L389;
     */
    /* JADX WARN: Code restructure failed: missing block: B:388:0x0876, code lost:
    
        r15 = r15.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:389:0x087b, code lost:
    
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:390:0x087c, code lost:
    
        r3[1] = java.lang.Integer.valueOf(r15);
        m5504c("audioDecoder OutputBuffer size %d, capacity %d", r3);
        r3 = new android.media.MediaCodec.BufferInfo();
        r14 = new com.cyberlink.mediacodec.C1245b("AudioDecodedFrame");
        r14.m5553i(r7.length);
        r15 = new com.cyberlink.mediacodec.C1247d("AudioSample");
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x08a0, code lost:
    
        r3 = r3;
        r40 = false;
        r46 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:392:0x08a8, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x08aa, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x08b4, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x08b5, code lost:
    
        r37 = r7;
        r36 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x08b9, code lost:
    
        r5 = r0;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r6 = true;
        r46 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x08d5, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x08d6, code lost:
    
        r37 = r7;
        r36 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x08da, code lost:
    
        r5 = r0;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r46 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:406:0x08f5, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x08f6, code lost:
    
        r37 = r7;
        r36 = r15;
        r5 = r0;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:408:0x0914, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x0915, code lost:
    
        r37 = r7;
        r36 = r15;
        r5 = r0;
        r72 = r8;
        r52 = r9;
        r63 = r10;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x0932, code lost:
    
        r37 = r7;
        r38 = r14;
        r36 = r15;
        r3 = null;
        r7 = null;
        r14 = null;
        r15 = null;
        r39 = null;
        r40 = true;
        r46 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x0942, code lost:
    
        r42 = r33;
        r56 = r34;
        r55 = r40;
        r34 = null;
        r40 = 0;
        r43 = false;
        r44 = 0;
        r45 = 0;
        r47 = 0;
        r49 = 0;
        r51 = null;
        r52 = null;
        r53 = null;
        r54 = false;
        r57 = true;
        r58 = false;
        r59 = false;
        r60 = false;
        r61 = 0;
        r62 = -1;
        r33 = r7;
        r7 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x0971, code lost:
    
        if (isInterrupted() != false) goto L1401;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x0973, code lost:
    
        r63 = r10;
        r64 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x0977, code lost:
    
        if (r43 != false) goto L509;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x0979, code lost:
    
        r10 = r86.f6077f.m5358g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x0981, code lost:
    
        r73 = r7;
        r72 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x0985, code lost:
    
        r7 = r86.f6077f.m5357f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x098a, code lost:
    
        if (r10 != (-1)) goto L449;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x098e, code lost:
    
        if (r86.f6078g == null) goto L431;
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x0994, code lost:
    
        if (r6.m5550f() == false) goto L427;
     */
    /* JADX WARN: Code restructure failed: missing block: B:424:0x0996, code lost:
    
        r66 = r86.f6078g.m5311i(1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x099e, code lost:
    
        if (r66 <= 0) goto L427;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x09a0, code lost:
    
        r86.f6078g.m5317p(r66, 0, 0, 0, 4);
        m5508g("Send EOS to VideoDecoder", new java.lang.Object[0]);
        r7 = true;
        r60 = true;
        r61 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x09c1, code lost:
    
        if (r6.m5549d() == false) goto L430;
     */
    /* JADX WARN: Code restructure failed: missing block: B:429:0x09c3, code lost:
    
        r7 = new com.cyberlink.mediacodec.InterfaceC1244a.c(r2[0]);
        r7.f6138b = 0;
        r7.f6139c = -1;
        r6.mo5537a(r7);
        r6.m5552h();
        m5508g("Queue EOS to videoSampleQueue", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x09e0, code lost:
    
        r7 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:431:0x09e2, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x09e5, code lost:
    
        if (r86.f6081j == null) goto L443;
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x09eb, code lost:
    
        if (r15.m5550f() == false) goto L439;
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x09ed, code lost:
    
        r66 = r86.f6081j.m5311i(1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:437:0x09f5, code lost:
    
        if (r66 <= 0) goto L439;
     */
    /* JADX WARN: Code restructure failed: missing block: B:438:0x09f7, code lost:
    
        r86.f6081j.m5317p(r66, 0, 0, 0, 4);
        m5508g("Send EOS to AudioDecoder", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x0a13, code lost:
    
        if (r15.m5549d() == false) goto L442;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0a15, code lost:
    
        r8 = new com.cyberlink.mediacodec.InterfaceC1244a.c(r39[0]);
        r8.f6138b = 0;
        r8.f6139c = -1;
        r15.mo5537a(r8);
        r15.m5552h();
        m5508g("Queue EOS to audioSampleQueue", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:442:0x0a32, code lost:
    
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x0a34, code lost:
    
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:444:0x0a35, code lost:
    
        if (r7 == false) goto L447;
     */
    /* JADX WARN: Code restructure failed: missing block: B:445:0x0a37, code lost:
    
        if (r8 == false) goto L447;
     */
    /* JADX WARN: Code restructure failed: missing block: B:446:0x0a39, code lost:
    
        m5508g("Extractor, EOS", new java.lang.Object[0]);
        r43 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:447:0x0a44, code lost:
    
        m5507f("Extractor, wainting for EOS delivery", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:448:0x0a4c, code lost:
    
        r75 = r13;
        r74 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:449:0x0a52, code lost:
    
        if (r10 != r13) goto L474;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x0a58, code lost:
    
        if (r6.m5550f() == false) goto L463;
     */
    /* JADX WARN: Code restructure failed: missing block: B:452:0x0a5a, code lost:
    
        r11 = r13;
        r74 = r14;
        r66 = r86.f6078g.m5311i(1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x0a65, code lost:
    
        if (r66 < 0) goto L464;
     */
    /* JADX WARN: Code restructure failed: missing block: B:456:0x0a6e, code lost:
    
        m5502a(r86.f6077f.m5356e(), 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:457:0x0a71, code lost:
    
        r68 = r86.f6077f.m5361k(r2[r66], 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:458:0x0a7a, code lost:
    
        if (r68 <= 0) goto L460;
     */
    /* JADX WARN: Code restructure failed: missing block: B:459:0x0a7c, code lost:
    
        r86.f6078g.m5317p(r66, 0, r68, r7, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:460:0x0a89, code lost:
    
        r86.f6077f.m5355a();
        r75 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:461:0x0a92, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:462:0x0a93, code lost:
    
        r5 = r0;
        r52 = r9;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:463:0x0a99, code lost:
    
        r11 = r13;
        r74 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:465:0x0aa0, code lost:
    
        if (r6.m5549d() == false) goto L470;
     */
    /* JADX WARN: Code restructure failed: missing block: B:466:0x0aa2, code lost:
    
        r10 = new com.cyberlink.mediacodec.InterfaceC1244a.c(r2[0]);
        r75 = r11;
        r10.f6138b = r86.f6077f.m5361k(r10.f6137a, 0);
        r10.f6139c = r7;
        r10.f6140d = r86.f6077f.m5356e();
        r6.mo5537a(r10);
        r13 = new java.lang.Object[2];
        r13[0] = java.lang.Long.valueOf(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:468:0x0ad8, code lost:
    
        r13[1] = java.lang.Integer.valueOf(r6.m5554j());
     */
    /* JADX WARN: Code restructure failed: missing block: B:469:0x0ada, code lost:
    
        m5507f("Video sample %d to Queue, VideoSampleQueue size %d", r13);
        r86.f6077f.m5355a();
        r7 = r43;
        r10 = r45;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:470:0x0ae9, code lost:
    
        r75 = r11;
        r7 = r44 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:471:0x0aee, code lost:
    
        if (r7 <= 5) goto L473;
     */
    /* JADX WARN: Code restructure failed: missing block: B:472:0x0af0, code lost:
    
        r6.m5553i(r6.m5548c() + 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:473:0x0af9, code lost:
    
        r8 = r7;
        r7 = r43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:474:0x0afe, code lost:
    
        r75 = r13;
        r74 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:475:0x0b02, code lost:
    
        if (r10 != r12) goto L499;
     */
    /* JADX WARN: Code restructure failed: missing block: B:477:0x0b08, code lost:
    
        if (r15.m5550f() == false) goto L489;
     */
    /* JADX WARN: Code restructure failed: missing block: B:478:0x0b0a, code lost:
    
        r66 = r86.f6081j.m5311i(1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:479:0x0b12, code lost:
    
        if (r66 < 0) goto L489;
     */
    /* JADX WARN: Code restructure failed: missing block: B:482:0x0b1b, code lost:
    
        m5502a(r86.f6077f.m5356e(), 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:483:0x0b1e, code lost:
    
        r68 = r86.f6077f.m5361k(r39[r66], 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:484:0x0b27, code lost:
    
        if (r68 <= 0) goto L486;
     */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x0b29, code lost:
    
        r86.f6081j.m5317p(r66, 0, r68, r7, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:486:0x0b36, code lost:
    
        r86.f6077f.m5355a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:487:0x0b3d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:488:0x0b3e, code lost:
    
        r5 = r0;
        r52 = r9;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x0b48, code lost:
    
        if (r15.m5549d() == false) goto L495;
     */
    /* JADX WARN: Code restructure failed: missing block: B:491:0x0b4a, code lost:
    
        r10 = new com.cyberlink.mediacodec.InterfaceC1244a.c(r39[0]);
        r10.f6138b = r86.f6077f.m5361k(r10.f6137a, 0);
        r10.f6139c = r7;
        r10.f6140d = r86.f6077f.m5356e();
        r15.mo5537a(r10);
        r13 = new java.lang.Object[2];
        r13[0] = java.lang.Long.valueOf(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:493:0x0b7e, code lost:
    
        r13[1] = java.lang.Integer.valueOf(r15.m5554j());
     */
    /* JADX WARN: Code restructure failed: missing block: B:494:0x0b80, code lost:
    
        m5507f("Audio sample %d to Queue, AudioSampleQueue size %d", r13);
        r86.f6077f.m5355a();
        r7 = r43;
        r8 = r44;
        r10 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:495:0x0b8f, code lost:
    
        r7 = r45 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x0b92, code lost:
    
        if (r7 <= 5) goto L498;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x0b94, code lost:
    
        r15.m5553i(r15.m5548c() + 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x0b9d, code lost:
    
        r10 = r7;
        r7 = r43;
        r8 = r44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x0ba3, code lost:
    
        r14 = new java.lang.Object[2];
        r14[0] = java.lang.Integer.valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:501:0x0bb4, code lost:
    
        r14[1] = java.lang.Long.valueOf(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:502:0x0bb6, code lost:
    
        m5509h("Extractor, Unknown track %d, sample time %d", r14);
        r86.f6077f.m5355a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:503:0x0bbf, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:504:0x0bc0, code lost:
    
        r72 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x0bc2, code lost:
    
        r5 = r0;
        r52 = r9;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x0bd7, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x0bd8, code lost:
    
        r72 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:508:0x0bda, code lost:
    
        r5 = r0;
        r52 = r9;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x0bef, code lost:
    
        r73 = r7;
        r72 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:510:0x0bf5, code lost:
    
        r7 = r43;
        r8 = r44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:511:0x0bf9, code lost:
    
        r10 = r45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:512:0x0bfb, code lost:
    
        if (r42 != false) goto L585;
     */
    /* JADX WARN: Code restructure failed: missing block: B:514:0x0c01, code lost:
    
        if (r6.mo5538e() == false) goto L1411;
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x0c03, code lost:
    
        r66 = r12;
        r14 = r86.f6078g.m5311i(1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:516:0x0c0d, code lost:
    
        if (r14 < 0) goto L1412;
     */
    /* JADX WARN: Code restructure failed: missing block: B:517:0x0c0f, code lost:
    
        r11 = (com.cyberlink.mediacodec.InterfaceC1244a.c) r6.m5551g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:518:0x0c1d, code lost:
    
        if (r11.f6139c < 0) goto L525;
     */
    /* JADX WARN: Code restructure failed: missing block: B:521:0x0c22, code lost:
    
        m5502a(r11.f6140d, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:522:0x0c25, code lost:
    
        m5503b(r2[r14], r11.f6137a);
        r86.f6078g.m5317p(r14, 0, r11.f6138b, r11.f6139c, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:523:0x0c44, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:524:0x0c45, code lost:
    
        r5 = r0;
        r52 = r9;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:525:0x0c4b, code lost:
    
        m5508g("Video sample EOS", new java.lang.Object[0]);
        r86.f6078g.m5317p(r14, 0, 0, 0, 4);
        r60 = true;
        r61 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:526:0x0c68, code lost:
    
        r12 = r66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:527:0x0c6b, code lost:
    
        m5509h("VideoDecoder, dequeueInputBuffer invalid index " + r14, new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:528:0x0c87, code lost:
    
        r66 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:529:0x0c89, code lost:
    
        r11 = r86.f6078g.m5312j(r4, 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:530:0x0c92, code lost:
    
        if (r11 == (-3)) goto L571;
     */
    /* JADX WARN: Code restructure failed: missing block: B:532:0x0c95, code lost:
    
        if (r11 == (-2)) goto L563;
     */
    /* JADX WARN: Code restructure failed: missing block: B:534:0x0c98, code lost:
    
        if (r11 == (-1)) goto L556;
     */
    /* JADX WARN: Code restructure failed: missing block: B:535:0x0c9a, code lost:
    
        if (r60 == false) goto L537;
     */
    /* JADX WARN: Code restructure failed: missing block: B:536:0x0c9c, code lost:
    
        r61 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:538:0x0ca2, code lost:
    
        if (r5.m5549d() == false) goto L555;
     */
    /* JADX WARN: Code restructure failed: missing block: B:539:0x0ca4, code lost:
    
        r13 = r4.presentationTimeUs;
        r40 = r13 - r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:540:0x0caa, code lost:
    
        r68 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:541:0x0cad, code lost:
    
        m5502a(r4.flags, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:542:0x0cb0, code lost:
    
        r2 = m5502a(r4.flags, 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:543:0x0cbb, code lost:
    
        if (r40 >= 0) goto L550;
     */
    /* JADX WARN: Code restructure failed: missing block: B:545:0x0cbf, code lost:
    
        if (r86.f6079h != false) goto L547;
     */
    /* JADX WARN: Code restructure failed: missing block: B:547:0x0cc2, code lost:
    
        if (r2 != false) goto L549;
     */
    /* JADX WARN: Code restructure failed: missing block: B:548:0x0cc4, code lost:
    
        r69 = r6;
        r86.f6078g.m5319r(r11, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:549:0x0ccd, code lost:
    
        r69 = r6;
        r6 = new com.cyberlink.mediacodec.InterfaceC1244a.d(r11, r4, r86.f6091t);
        r6.f6131b = 0;
        r6.f6132c = 0;
        r5.mo5537a(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:550:0x0ce1, code lost:
    
        r69 = r6;
        r5.mo5537a(new com.cyberlink.mediacodec.InterfaceC1244a.d(r11, r4, r86.f6091t));
     */
    /* JADX WARN: Code restructure failed: missing block: B:551:0x0ced, code lost:
    
        if (r2 == false) goto L587;
     */
    /* JADX WARN: Code restructure failed: missing block: B:552:0x0cef, code lost:
    
        m5508g("VideoDecoder EOS", new java.lang.Object[0]);
        r5.m5552h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:553:0x0cfb, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:554:0x0cfc, code lost:
    
        r5 = r0;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:555:0x0cff, code lost:
    
        r68 = r2;
        r69 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:556:0x0d05, code lost:
    
        r68 = r2;
        r69 = r6;
        m5509h("videoDecoder, dequeueOutputBuffer timed out!", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:557:0x0d11, code lost:
    
        if (r60 == false) goto L586;
     */
    /* JADX WARN: Code restructure failed: missing block: B:558:0x0d13, code lost:
    
        r2 = r61 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:559:0x0d17, code lost:
    
        if (r2 <= 20) goto L562;
     */
    /* JADX WARN: Code restructure failed: missing block: B:560:0x0d19, code lost:
    
        m5508g("VideoDecoder EOS by waiting count", new java.lang.Object[0]);
        r6 = new com.cyberlink.mediacodec.InterfaceC1244a.d(-1, r4, r86.f6091t);
        r6.f6131b = 0;
        r6.f6132c = 0;
        r6.f6134e = 4;
        r5.mo5537a(r6);
        r5.m5552h();
        r61 = r2;
        r13 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:561:0x0d3c, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:562:0x0d3f, code lost:
    
        r61 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:563:0x0d43, code lost:
    
        r68 = r2;
        r69 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:564:0x0d49, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:565:0x0d4a, code lost:
    
        r11 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:566:0x0d4c, code lost:
    
        r11[0] = r86.f6078g.m5316o().toString();
        m5511j("VideoDecoder, New output format %s", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:567:0x0d5d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:568:0x0d5e, code lost:
    
        r5 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:569:0x0d5f, code lost:
    
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:570:0x0d61, code lost:
    
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:571:0x0d73, code lost:
    
        r68 = r2;
        r69 = r6;
        r2 = r86.f6078g.m5315n();
        r12 = new java.lang.Object[2];
        r12[0] = java.lang.Integer.valueOf(r2.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:572:0x0d8b, code lost:
    
        if (r2.length <= 0) goto L576;
     */
    /* JADX WARN: Code restructure failed: missing block: B:573:0x0d8d, code lost:
    
        r11 = r2[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:574:0x0d8f, code lost:
    
        if (r11 == null) goto L576;
     */
    /* JADX WARN: Code restructure failed: missing block: B:575:0x0d91, code lost:
    
        r11 = r11.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:576:0x0d96, code lost:
    
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:579:0x0d9c, code lost:
    
        r12[1] = java.lang.Integer.valueOf(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:580:0x0d9e, code lost:
    
        m5511j("videoDecoder OutputBuffer size changed %d, capacity %d", r12);
        r5.m5553i(r2.length);
        r32 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:581:0x0da8, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:583:0x0dab, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:585:0x0dae, code lost:
    
        r68 = r2;
        r69 = r6;
        r66 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:586:0x0db4, code lost:
    
        r13 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:587:0x0db6, code lost:
    
        r2 = r42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:588:0x0db8, code lost:
    
        if (r55 != false) goto L679;
     */
    /* JADX WARN: Code restructure failed: missing block: B:590:0x0dbe, code lost:
    
        if (r15.mo5538e() == false) goto L1415;
     */
    /* JADX WARN: Code restructure failed: missing block: B:591:0x0dc0, code lost:
    
        r6 = r86.f6081j.m5311i(1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:592:0x0dc8, code lost:
    
        if (r6 < 0) goto L1416;
     */
    /* JADX WARN: Code restructure failed: missing block: B:593:0x0dca, code lost:
    
        r11 = (com.cyberlink.mediacodec.InterfaceC1244a.c) r15.m5551g();
        r70 = r7;
        r71 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:594:0x0dda, code lost:
    
        if (r11.f6139c < 0) goto L599;
     */
    /* JADX WARN: Code restructure failed: missing block: B:597:0x0ddf, code lost:
    
        m5502a(r11.f6140d, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:598:0x0de2, code lost:
    
        m5503b(r39[r6], r11.f6137a);
        r86.f6081j.m5317p(r6, 0, r11.f6138b, r11.f6139c, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:599:0x0dff, code lost:
    
        m5508g("Audio sample EOS", new java.lang.Object[0]);
        r86.f6081j.m5317p(r6, 0, 0, 0, 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:600:0x0e18, code lost:
    
        r7 = r70;
        r8 = r71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:601:0x0e1d, code lost:
    
        r70 = r7;
        r71 = r8;
        m5509h("AudioDecoder, dequeueInputBuffer invalid index " + r6, new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:602:0x0e39, code lost:
    
        r70 = r7;
        r71 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:603:0x0e3d, code lost:
    
        r6 = r86.f6081j.m5312j(r3, 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:604:0x0e46, code lost:
    
        if (r6 == (-3)) goto L666;
     */
    /* JADX WARN: Code restructure failed: missing block: B:606:0x0e49, code lost:
    
        if (r6 == (-2)) goto L621;
     */
    /* JADX WARN: Code restructure failed: missing block: B:608:0x0e4c, code lost:
    
        if (r6 == (-1)) goto L620;
     */
    /* JADX WARN: Code restructure failed: missing block: B:610:0x0e52, code lost:
    
        if (r74.m5549d() == false) goto L619;
     */
    /* JADX WARN: Code restructure failed: missing block: B:611:0x0e54, code lost:
    
        r7 = r3.presentationTimeUs;
     */
    /* JADX WARN: Code restructure failed: missing block: B:613:0x0e59, code lost:
    
        m5502a(r3.flags, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:614:0x0e5c, code lost:
    
        r7 = m5502a(r3.flags, 4);
        r77 = r2;
        r2 = r74;
        r2.mo5537a(new com.cyberlink.mediacodec.InterfaceC1244a.a(r6, r3, r86.f6092u, r86.f6093v, r86.f6094w));
     */
    /* JADX WARN: Code restructure failed: missing block: B:615:0x0e81, code lost:
    
        if (r7 == false) goto L681;
     */
    /* JADX WARN: Code restructure failed: missing block: B:616:0x0e83, code lost:
    
        m5508g("AudioDecoder, EOS", new java.lang.Object[0]);
        r2.m5552h();
        r8 = r52;
        r11 = r53;
        r55 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:617:0x0e96, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:618:0x0e97, code lost:
    
        r5 = r0;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:619:0x0e9b, code lost:
    
        r77 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:620:0x0e9f, code lost:
    
        r77 = r2;
        r2 = r74;
        m5509h("AudioDecoder, dequeueOutputBuffer timed out!", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:621:0x0ead, code lost:
    
        r77 = r2;
        r2 = r74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:623:0x0eb4, code lost:
    
        r8 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:624:0x0eb6, code lost:
    
        r8[0] = r86.f6081j.m5316o().toString();
        m5508g("AudioDecoder, New output format %s", r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:625:0x0ec6, code lost:
    
        if (r54 != false) goto L1402;
     */
    /* JADX WARN: Code restructure failed: missing block: B:626:0x0ec8, code lost:
    
        r6 = r86.f6081j.m5316o();
        r86.f6092u = r6.getInteger("sample-rate");
        r86.f6094w = r6.getInteger("channel-count");
        r6 = android.media.MediaCodec.createEncoderByType(com.google.android.exoplayer2.util.MimeTypes.AUDIO_AAC);
        r86.f6086o = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:628:0x0ee9, code lost:
    
        r11 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:629:0x0eeb, code lost:
    
        r11[0] = r6.getName();
        m5508g("Using audioEncoder: %s", r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:630:0x0ef7, code lost:
    
        if (r86.f6086o == null) goto L1403;
     */
    /* JADX WARN: Code restructure failed: missing block: B:631:0x0ef9, code lost:
    
        r6 = android.media.MediaFormat.createAudioFormat(com.google.android.exoplayer2.util.MimeTypes.AUDIO_AAC, r86.f6092u, r86.f6094w);
        r7 = m5513l(r86.f6092u, r86.f6094w);
        r6.setInteger("aac-profile", 2);
        r6.setInteger("bitrate", r7);
        r8 = r33[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:632:0x0f19, code lost:
    
        if (r8 == null) goto L634;
     */
    /* JADX WARN: Code restructure failed: missing block: B:633:0x0f1b, code lost:
    
        r6.setInteger("max-input-size", r8.capacity());
     */
    /* JADX WARN: Code restructure failed: missing block: B:636:0x0f27, code lost:
    
        m5508g("config AudioEncoder with format %s", r6.toString());
        r86.f6086o.configure(r6, (android.view.Surface) null, (android.media.MediaCrypto) null, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:637:0x0f3a, code lost:
    
        r86.f6086o.start();
     */
    /* JADX WARN: Code restructure failed: missing block: B:638:0x0f3f, code lost:
    
        r6 = r86.f6086o.getInputBuffers();
        r11 = new java.lang.Object[2];
        r11[0] = java.lang.Integer.valueOf(r6.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:639:0x0f53, code lost:
    
        if (r6.length <= 0) goto L643;
     */
    /* JADX WARN: Code restructure failed: missing block: B:640:0x0f55, code lost:
    
        r8 = r6[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:641:0x0f57, code lost:
    
        if (r8 == null) goto L643;
     */
    /* JADX WARN: Code restructure failed: missing block: B:642:0x0f59, code lost:
    
        r8 = r8.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:643:0x0f5e, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:644:0x0f5f, code lost:
    
        r11[1] = java.lang.Integer.valueOf(r8);
        m5505d("audioEncoder InputBuffer size %d, capacity %d", r11);
        r7 = r86.f6086o.getOutputBuffers();
        r12 = new java.lang.Object[2];
        r12[0] = java.lang.Integer.valueOf(r7.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:645:0x0f7e, code lost:
    
        if (r7.length <= 0) goto L649;
     */
    /* JADX WARN: Code restructure failed: missing block: B:646:0x0f80, code lost:
    
        r11 = r7[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:647:0x0f82, code lost:
    
        if (r11 == null) goto L649;
     */
    /* JADX WARN: Code restructure failed: missing block: B:648:0x0f84, code lost:
    
        r11 = r11.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:649:0x0f89, code lost:
    
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:650:0x0f8a, code lost:
    
        r12[1] = java.lang.Integer.valueOf(r11);
        m5505d("audioEncoder OutputBuffer size %d, capacity %d", r12);
        r8 = new android.media.MediaCodec.BufferInfo();
        r11 = new com.cyberlink.mediacodec.C1247d("AudioEncodedFrame");
        r11.m5553i(r7.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:651:0x0fa5, code lost:
    
        r34 = r6;
        r51 = r7;
        r54 = true;
        r57 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:652:0x0faf, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:653:0x0fb0, code lost:
    
        r5 = r0;
        r52 = r9;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r6 = true;
        r54 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:654:0x0fc8, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:655:0x0fc9, code lost:
    
        r5 = r0;
        r52 = r9;
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
        r54 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:656:0x0fe0, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:659:0x0feb, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "AudioEncoder, failed to configure!", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6129z, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:661:0x0ff5, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "Can't create audioEncoder!", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6128y);
     */
    /* JADX WARN: Code restructure failed: missing block: B:663:0x0fff, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "AudioDecoder, format changed twice", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6122s);
     */
    /* JADX WARN: Code restructure failed: missing block: B:664:0x1000, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:665:0x1001, code lost:
    
        r5 = r0;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:666:0x1005, code lost:
    
        r77 = r2;
        r2 = r74;
        r6 = r86.f6081j.m5315n();
        r11 = new java.lang.Object[2];
        r11[0] = java.lang.Integer.valueOf(r6.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:667:0x101d, code lost:
    
        if (r6.length <= 0) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:668:0x101f, code lost:
    
        r8 = r6[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:669:0x1021, code lost:
    
        if (r8 == null) goto L671;
     */
    /* JADX WARN: Code restructure failed: missing block: B:670:0x1023, code lost:
    
        r8 = r8.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:671:0x1028, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:674:0x102e, code lost:
    
        r11[1] = java.lang.Integer.valueOf(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:675:0x1030, code lost:
    
        m5504c("AudioDecoder, OutputBuffer size changed %d, capacity %d", r11);
        r2.m5553i(r6.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:676:0x1037, code lost:
    
        r33 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:677:0x103a, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:678:0x103b, code lost:
    
        r5 = r0;
        r52 = r9;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:679:0x1041, code lost:
    
        r77 = r2;
        r70 = r7;
        r71 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:680:0x1047, code lost:
    
        r2 = r74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:681:0x1049, code lost:
    
        r8 = r52;
        r11 = r53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:682:0x104d, code lost:
    
        if (r56 != false) goto L772;
     */
    /* JADX WARN: Code restructure failed: missing block: B:684:0x1053, code lost:
    
        if (r5.mo5538e() == false) goto L714;
     */
    /* JADX WARN: Code restructure failed: missing block: B:685:0x1055, code lost:
    
        r6 = (com.cyberlink.mediacodec.InterfaceC1244a.d) r5.m5551g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:686:0x105d, code lost:
    
        r40 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:687:0x1060, code lost:
    
        r3 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:688:0x1062, code lost:
    
        r41 = r4;
        r42 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:689:0x1066, code lost:
    
        r3[0] = java.lang.Long.valueOf(r6.f6135f);
        m5512k("Video frame (to Encoder) %d", r3);
        r3 = r86.f6080i;
     */
    /* JADX WARN: Code restructure failed: missing block: B:690:0x1074, code lost:
    
        if (r3 == false) goto L694;
     */
    /* JADX WARN: Code restructure failed: missing block: B:692:0x1078, code lost:
    
        if (r6.f6132c == 0) goto L694;
     */
    /* JADX WARN: Code restructure failed: missing block: B:693:0x107a, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:694:0x107c, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:695:0x107d, code lost:
    
        if (r3 != false) goto L699;
     */
    /* JADX WARN: Code restructure failed: missing block: B:697:0x1081, code lost:
    
        if (r6.f6132c == 0) goto L699;
     */
    /* JADX WARN: Code restructure failed: missing block: B:698:0x1083, code lost:
    
        r9.m5570j(r32[r6.f6130a]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:699:0x108a, code lost:
    
        r3 = r6.f6130a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:700:0x108c, code lost:
    
        if (r3 < 0) goto L707;
     */
    /* JADX WARN: Code restructure failed: missing block: B:701:0x108e, code lost:
    
        r86.f6078g.m5319r(r3, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:702:0x1093, code lost:
    
        if (r4 == false) goto L704;
     */
    /* JADX WARN: Code restructure failed: missing block: B:703:0x1095, code lost:
    
        r72.m5556a();
        r72.m5557c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:705:0x109d, code lost:
    
        r5 = r63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:706:0x10a3, code lost:
    
        r5.m5545g(r6.f6135f * 1000);
        r5.m5546h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:707:0x10aa, code lost:
    
        r5 = r63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:709:0x10b3, code lost:
    
        if (m5502a(r6.f6134e, 4) == false) goto L715;
     */
    /* JADX WARN: Code restructure failed: missing block: B:710:0x10b5, code lost:
    
        r86.f6085n.signalEndOfInputStream();
     */
    /* JADX WARN: Code restructure failed: missing block: B:711:0x10bb, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:712:0x10bc, code lost:
    
        r52 = r9;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:713:0x10c1, code lost:
    
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:714:0x10d3, code lost:
    
        r40 = r3;
        r41 = r4;
        r42 = r5;
        r5 = r63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:715:0x10db, code lost:
    
        r3 = r86.f6085n.dequeueOutputBuffer(r64, 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:716:0x10e6, code lost:
    
        if (r3 == (-3)) goto L757;
     */
    /* JADX WARN: Code restructure failed: missing block: B:718:0x10e9, code lost:
    
        if (r3 == (-2)) goto L738;
     */
    /* JADX WARN: Code restructure failed: missing block: B:720:0x10ec, code lost:
    
        if (r3 == (-1)) goto L737;
     */
    /* JADX WARN: Code restructure failed: missing block: B:722:0x10f2, code lost:
    
        if (r38.m5549d() == false) goto L735;
     */
    /* JADX WARN: Code restructure failed: missing block: B:723:0x10f4, code lost:
    
        r6 = r64.presentationTimeUs;
        r43 = r6 - r47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:724:0x10fa, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:725:0x10fd, code lost:
    
        m5502a(r64.flags, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:726:0x1100, code lost:
    
        r6 = m5502a(r64.flags, 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:727:0x110b, code lost:
    
        if (r43 < 0) goto L729;
     */
    /* JADX WARN: Code restructure failed: missing block: B:728:0x110d, code lost:
    
        r12 = r38;
        r12.mo5537a(new com.cyberlink.mediacodec.InterfaceC1244a.d(r3, r64));
        r64 = r64;
        r47 = r6;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:729:0x111d, code lost:
    
        r12 = r38;
        r64 = r64;
        r4 = 0;
        r86.f6085n.releaseOutputBuffer(r3, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:730:0x1127, code lost:
    
        if (r6 == false) goto L732;
     */
    /* JADX WARN: Code restructure failed: missing block: B:731:0x1129, code lost:
    
        m5508g("VideoEncoder, EOS", new java.lang.Object[r4]);
        r12.m5552h();
        r38 = r10;
        r7 = r73;
        r56 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:732:0x113b, code lost:
    
        r38 = r10;
        r7 = r73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:733:0x1141, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:735:0x1144, code lost:
    
        r64 = r64;
        r12 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:736:0x1148, code lost:
    
        r38 = r10;
        r3 = r73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:737:0x114e, code lost:
    
        r64 = r64;
        r12 = r38;
        m5509h("videoEncoder, dequeueOutputBuffer timed out!", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:738:0x115b, code lost:
    
        r64 = r64;
        r12 = r38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:740:0x1162, code lost:
    
        r6 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:741:0x1164, code lost:
    
        r6[0] = r86.f6085n.getOutputFormat().toString();
        m5508g("VideoEncoder, New output format %s", r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:742:0x1177, code lost:
    
        if ((-1) != r73) goto L1404;
     */
    /* JADX WARN: Code restructure failed: missing block: B:743:0x1179, code lost:
    
        r3 = r86.f6085n.getOutputFormat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:744:0x117f, code lost:
    
        r7 = r86.f6084m.addTrack(r3);
        r6 = r31;
        r86.f6084m.setOrientationHint(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:745:0x118c, code lost:
    
        r31 = r6;
        r38 = r10;
        r10 = new java.lang.Object[2];
        r10[0] = java.lang.Integer.valueOf(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:747:0x11a2, code lost:
    
        r10[1] = r3.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:748:0x11a4, code lost:
    
        m5508g("Muxer, Video track %d, format %s", r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:749:0x11a9, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:751:0x11b4, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "Muxer, failed to add video track!", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6114k, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:753:0x11be, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "VideoEncoder, format changed twice", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6126w);
     */
    /* JADX WARN: Code restructure failed: missing block: B:754:0x11bf, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:755:0x11c0, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:756:0x11c1, code lost:
    
        r63 = r5;
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:757:0x11c7, code lost:
    
        r64 = r64;
        r12 = r38;
        r3 = r73;
        r38 = r10;
        r4 = r86.f6085n.getOutputBuffers();
        r10 = new java.lang.Object[2];
        r10[0] = java.lang.Integer.valueOf(r4.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:758:0x11e4, code lost:
    
        if (r4.length <= 0) goto L762;
     */
    /* JADX WARN: Code restructure failed: missing block: B:759:0x11e6, code lost:
    
        r7 = r4[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:760:0x11e8, code lost:
    
        if (r7 == null) goto L762;
     */
    /* JADX WARN: Code restructure failed: missing block: B:761:0x11ea, code lost:
    
        r7 = r7.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:762:0x11ef, code lost:
    
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:763:0x11f0, code lost:
    
        r10[1] = java.lang.Integer.valueOf(r7);
        m5512k("videoEncoder OutputBuffer size changed %d, capacity %d", r10);
        r12.m5553i(r4.length);
        r7 = r3;
        r35 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:764:0x1203, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:766:0x1206, code lost:
    
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:767:0x1208, code lost:
    
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:768:0x121a, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:770:0x121d, code lost:
    
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:771:0x121f, code lost:
    
        r11 = "Muxer, failed to stop/release muxer";
        r10 = "AudioEncoder, failed to stop/release AudioEnocder";
        r9 = r23;
        r8 = "AudioDecoder, failed to stop/release AudioDecoder";
        r7 = "VideoDecoder, failed to stop/release VideoDecoder";
        r2 = "Extractor, failed to stop/release Extractor";
        r4 = " ms.";
        r3 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:772:0x1231, code lost:
    
        r40 = r3;
        r41 = r4;
        r42 = r5;
        r12 = r38;
        r5 = r63;
        r3 = r73;
        r38 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:773:0x123f, code lost:
    
        r7 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:774:0x1240, code lost:
    
        if (r57 != false) goto L851;
     */
    /* JADX WARN: Code restructure failed: missing block: B:776:0x1246, code lost:
    
        if (r2.mo5538e() == false) goto L1419;
     */
    /* JADX WARN: Code restructure failed: missing block: B:777:0x1248, code lost:
    
        r43 = r13;
        r3 = r86.f6086o.dequeueInputBuffer(1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:778:0x1252, code lost:
    
        if (r3 < 0) goto L1420;
     */
    /* JADX WARN: Code restructure failed: missing block: B:779:0x1254, code lost:
    
        r4 = (com.cyberlink.mediacodec.InterfaceC1244a.a) r2.m5551g();
        r10 = new java.lang.Object[3];
        r10[0] = java.lang.Long.valueOf(r4.f6135f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:780:0x126e, code lost:
    
        r14 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:781:0x126f, code lost:
    
        r10[1] = java.lang.Integer.valueOf(r4.f6133d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:782:0x1271, code lost:
    
        r10[2] = java.lang.Integer.valueOf(r4.f6132c);
        m5505d("Audio frame (to Encoder) %d, offset %d, size %d", r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:783:0x127f, code lost:
    
        if (r4.f6130a < 0) goto L788;
     */
    /* JADX WARN: Code restructure failed: missing block: B:784:0x1281, code lost:
    
        r34[r3].clear();
        r33[r4.f6130a].position(r4.f6133d);
        r33[r4.f6130a].limit(r4.f6133d + r4.f6132c);
        r34[r3].put(r33[r4.f6130a]);
        r45 = r15;
        r74 = r2;
        r86.f6086o.queueInputBuffer(r3, r4.f6133d, r4.f6132c, r4.f6135f, r4.f6134e);
     */
    /* JADX WARN: Code restructure failed: missing block: B:786:0x12c6, code lost:
    
        r86.f6081j.m5319r(r4.f6130a, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:788:0x12ca, code lost:
    
        r74 = r2;
        r45 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:789:0x12ce, code lost:
    
        r13 = r43;
        r15 = r45;
        r2 = r74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:790:0x12d6, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:791:0x12d7, code lost:
    
        r63 = r5;
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:792:0x12db, code lost:
    
        r6 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:793:0x12de, code lost:
    
        r74 = r2;
        r45 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:794:0x12e2, code lost:
    
        m5509h("AudioEncoder, dequeueInputBuffer invalid index " + r3, new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:795:0x12fa, code lost:
    
        r74 = r2;
        r43 = r13;
        r45 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:796:0x1300, code lost:
    
        r2 = r86.f6086o.dequeueOutputBuffer(r8, 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:797:0x1309, code lost:
    
        if (r2 == (-3)) goto L835;
     */
    /* JADX WARN: Code restructure failed: missing block: B:799:0x130c, code lost:
    
        if (r2 == (-2)) goto L817;
     */
    /* JADX WARN: Code restructure failed: missing block: B:801:0x130f, code lost:
    
        if (r2 == (-1)) goto L816;
     */
    /* JADX WARN: Code restructure failed: missing block: B:803:0x1315, code lost:
    
        if (r11.m5549d() == false) goto L813;
     */
    /* JADX WARN: Code restructure failed: missing block: B:804:0x1317, code lost:
    
        r3 = r8.presentationTimeUs;
        r13 = r3 - r49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:806:0x131e, code lost:
    
        m5502a(r8.flags, 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:807:0x1321, code lost:
    
        r6 = m5502a(r8.flags, 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:808:0x132c, code lost:
    
        if (r13 < 0) goto L810;
     */
    /* JADX WARN: Code restructure failed: missing block: B:809:0x132e, code lost:
    
        r11.mo5537a(new com.cyberlink.mediacodec.InterfaceC1244a.a(r2, r8));
        r49 = r3;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:810:0x133a, code lost:
    
        r4 = 0;
        r86.f6086o.releaseOutputBuffer(r2, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:811:0x1340, code lost:
    
        if (r6 == false) goto L813;
     */
    /* JADX WARN: Code restructure failed: missing block: B:812:0x1342, code lost:
    
        m5508g("AudioEncoder, EOS", new java.lang.Object[r4]);
        r11.m5552h();
        r2 = r62;
        r57 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:813:0x1352, code lost:
    
        r2 = r62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:814:0x1356, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:815:0x1357, code lost:
    
        r63 = r5;
        r52 = r9;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:816:0x135e, code lost:
    
        m5509h("AudioEncoder, dequeueOutputBuffer timed out!", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:819:0x136a, code lost:
    
        r4 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:820:0x136c, code lost:
    
        r4[0] = r86.f6086o.getOutputFormat().toString();
        m5508g("AudioEncoder, New output format %s", r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:821:0x137f, code lost:
    
        if ((-1) != r62) goto L1405;
     */
    /* JADX WARN: Code restructure failed: missing block: B:822:0x1381, code lost:
    
        r2 = r86.f6086o.getOutputFormat();
     */
    /* JADX WARN: Code restructure failed: missing block: B:823:0x1387, code lost:
    
        r3 = r86.f6084m.addTrack(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:824:0x138d, code lost:
    
        r10 = new java.lang.Object[2];
        r10[0] = java.lang.Integer.valueOf(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:826:0x139e, code lost:
    
        r10[1] = r2.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:827:0x13a0, code lost:
    
        m5508g("Muxer, Audio track %d, format %s", r10);
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:828:0x13a6, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:830:0x13b1, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "Muxer, failed to add audio track!", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6114k, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:832:0x13bb, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "AudioEncoder, format changed twice", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6098A);
     */
    /* JADX WARN: Code restructure failed: missing block: B:833:0x13bc, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:834:0x13bd, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:835:0x13c0, code lost:
    
        r2 = r62;
        r3 = r86.f6086o.getOutputBuffers();
        r10 = new java.lang.Object[2];
        r10[0] = java.lang.Integer.valueOf(r3.length);
     */
    /* JADX WARN: Code restructure failed: missing block: B:836:0x13d6, code lost:
    
        if (r3.length <= 0) goto L840;
     */
    /* JADX WARN: Code restructure failed: missing block: B:837:0x13d8, code lost:
    
        r6 = r3[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:838:0x13da, code lost:
    
        if (r6 == null) goto L840;
     */
    /* JADX WARN: Code restructure failed: missing block: B:839:0x13dc, code lost:
    
        r6 = r6.capacity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:840:0x13e1, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:843:0x13e7, code lost:
    
        r10[1] = java.lang.Integer.valueOf(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:844:0x13e9, code lost:
    
        m5505d("audioEncoder OutputBuffer size changed %d, capacity %d", r10);
        r11.m5553i(r3.length);
        r51 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:845:0x13f3, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:846:0x13f4, code lost:
    
        r63 = r5;
        r52 = r9;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:847:0x13fb, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:848:0x13fc, code lost:
    
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:849:0x1400, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:850:0x1401, code lost:
    
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:851:0x1405, code lost:
    
        r74 = r2;
        r43 = r13;
        r45 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:852:0x140d, code lost:
    
        if (r58 != false) goto L952;
     */
    /* JADX WARN: Code restructure failed: missing block: B:853:0x140f, code lost:
    
        r6 = r66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:854:0x1411, code lost:
    
        if (r59 != false) goto L870;
     */
    /* JADX WARN: Code restructure failed: missing block: B:856:0x1414, code lost:
    
        if ((-1) == r6) goto L860;
     */
    /* JADX WARN: Code restructure failed: missing block: B:857:0x1416, code lost:
    
        if ((-1) == r2) goto L859;
     */
    /* JADX WARN: Code restructure failed: missing block: B:859:0x1419, code lost:
    
        r13 = r75;
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:860:0x141d, code lost:
    
        r13 = r75;
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:861:0x1420, code lost:
    
        if ((-1) == r13) goto L865;
     */
    /* JADX WARN: Code restructure failed: missing block: B:862:0x1422, code lost:
    
        if ((-1) == r7) goto L864;
     */
    /* JADX WARN: Code restructure failed: missing block: B:864:0x1425, code lost:
    
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:865:0x1427, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:866:0x1428, code lost:
    
        if (r4 == false) goto L871;
     */
    /* JADX WARN: Code restructure failed: missing block: B:867:0x142a, code lost:
    
        if (r10 == false) goto L871;
     */
    /* JADX WARN: Code restructure failed: missing block: B:868:0x142c, code lost:
    
        m5510i("Muxer, start", new java.lang.Object[0]);
        r86.f6084m.start();
     */
    /* JADX WARN: Code restructure failed: missing block: B:869:0x1439, code lost:
    
        r59 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:870:0x143c, code lost:
    
        r13 = r75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:871:0x143f, code lost:
    
        if (r59 == false) goto L942;
     */
    /* JADX WARN: Code restructure failed: missing block: B:872:0x1441, code lost:
    
        if (r11 == null) goto L898;
     */
    /* JADX WARN: Code restructure failed: missing block: B:874:0x1447, code lost:
    
        if (r11.mo5538e() == false) goto L898;
     */
    /* JADX WARN: Code restructure failed: missing block: B:875:0x1449, code lost:
    
        r4 = (com.cyberlink.mediacodec.InterfaceC1244a.b) r11.m5551g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:876:0x144f, code lost:
    
        if (r4 == null) goto L898;
     */
    /* JADX WARN: Code restructure failed: missing block: B:877:0x1451, code lost:
    
        r10 = r51[r4.f6130a];
     */
    /* JADX WARN: Code restructure failed: missing block: B:878:0x1455, code lost:
    
        if (r10 != null) goto L882;
     */
    /* JADX WARN: Code restructure failed: missing block: B:879:0x1457, code lost:
    
        m5509h("Muxer, audioEncoderOutputBuffer[" + r4.f6130a + "] was null", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:880:0x1475, code lost:
    
        r66 = r6;
        r53 = r8;
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:881:0x147b, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:882:0x147e, code lost:
    
        r14 = new android.media.MediaCodec.BufferInfo();
     */
    /* JADX WARN: Code restructure failed: missing block: B:883:0x1487, code lost:
    
        r53 = r8;
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:884:0x148b, code lost:
    
        r66 = r6;
        r14.set(r4.f6133d, r4.f6132c, r4.f6131b, r4.f6134e);
     */
    /* JADX WARN: Code restructure failed: missing block: B:885:0x14a5, code lost:
    
        if (m5502a(r14.flags, 2) == false) goto L887;
     */
    /* JADX WARN: Code restructure failed: missing block: B:886:0x14a7, code lost:
    
        m5509h("Muxer, ignoring BUFFER_FLAG_CODEC_CONFIG from AudioEncodedFrame " + r4.f6131b, new java.lang.Object[0]);
        r14.size = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:888:0x14c4, code lost:
    
        if (r14.size == 0) goto L881;
     */
    /* JADX WARN: Code restructure failed: missing block: B:889:0x14c6, code lost:
    
        r10.position(r14.offset);
        r10.limit(r14.offset + r14.size);
        r86.f6084m.writeSampleData(r2, r10, r14);
        r8 = new java.lang.Object[2];
        r8[0] = java.lang.Integer.valueOf(r14.size);
     */
    /* JADX WARN: Code restructure failed: missing block: B:891:0x14ed, code lost:
    
        r8[1] = java.lang.Long.valueOf(r14.presentationTimeUs);
     */
    /* JADX WARN: Code restructure failed: missing block: B:892:0x14ef, code lost:
    
        m5510i("Muxer, write %d bytes of audio data, for time %d", r8);
        r86.f6089r = r14.presentationTimeUs;
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:893:0x14f7, code lost:
    
        r86.f6086o.releaseOutputBuffer(r4.f6130a, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:894:0x1500, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:895:0x1501, code lost:
    
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:896:0x1505, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:897:0x1506, code lost:
    
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:898:0x150a, code lost:
    
        r66 = r6;
        r53 = r8;
        r52 = r9;
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:899:0x1511, code lost:
    
        if (r12 == null) goto L929;
     */
    /* JADX WARN: Code restructure failed: missing block: B:901:0x1517, code lost:
    
        if (r12.mo5538e() == false) goto L929;
     */
    /* JADX WARN: Code restructure failed: missing block: B:902:0x1519, code lost:
    
        r4 = (com.cyberlink.mediacodec.InterfaceC1244a.b) r12.m5551g();
     */
    /* JADX WARN: Code restructure failed: missing block: B:903:0x151f, code lost:
    
        if (r4 == null) goto L929;
     */
    /* JADX WARN: Code restructure failed: missing block: B:904:0x1521, code lost:
    
        r6 = r4.f6130a;
        r8 = r35[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:905:0x1525, code lost:
    
        if (r8 != null) goto L912;
     */
    /* JADX WARN: Code restructure failed: missing block: B:908:0x152a, code lost:
    
        r10 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:909:0x152c, code lost:
    
        r10[0] = java.lang.Integer.valueOf(r6);
        m5509h("Muxer, videoEncoderOutputBuffer[%d] was null", r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:910:0x1539, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:911:0x153a, code lost:
    
        r63 = r5;
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:912:0x153f, code lost:
    
        r3 = new android.media.MediaCodec.BufferInfo();
        r3.set(r4.f6133d, r4.f6132c, r4.f6131b, r4.f6134e);
     */
    /* JADX WARN: Code restructure failed: missing block: B:913:0x1560, code lost:
    
        if (m5502a(r3.flags, 2) == false) goto L918;
     */
    /* JADX WARN: Code restructure failed: missing block: B:916:0x1565, code lost:
    
        r10 = new java.lang.Object[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:917:0x1567, code lost:
    
        r10[0] = java.lang.Long.valueOf(r4.f6131b);
        m5509h("Muxer, ignoring BUFFER_FLAG_CODEC_CONFIG from VideoEncodedFrame %d", r10);
        r3.size = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:919:0x1577, code lost:
    
        if (r3.size == 0) goto L926;
     */
    /* JADX WARN: Code restructure failed: missing block: B:920:0x1579, code lost:
    
        r8.position(r3.offset);
        r8.limit(r3.offset + r3.size);
        r86.f6084m.writeSampleData(r7, r8, r3);
        r9 = new java.lang.Object[2];
        r9[0] = java.lang.Integer.valueOf(r3.size);
     */
    /* JADX WARN: Code restructure failed: missing block: B:921:0x159f, code lost:
    
        r14 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:922:0x15a0, code lost:
    
        r9[1] = java.lang.Long.valueOf(r3.presentationTimeUs);
     */
    /* JADX WARN: Code restructure failed: missing block: B:923:0x15a2, code lost:
    
        m5510i("Muxer, write %d bytes of video data, for time %d", r9);
        r86.f6090s = r3.presentationTimeUs;
     */
    /* JADX WARN: Code restructure failed: missing block: B:924:0x15aa, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:925:0x15ab, code lost:
    
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:927:0x15b0, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:928:0x15b1, code lost:
    
        r86.f6085n.releaseOutputBuffer(r4.f6130a, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:930:0x15bb, code lost:
    
        if (r3 == false) goto L943;
     */
    /* JADX WARN: Code restructure failed: missing block: B:932:0x15bf, code lost:
    
        if (r86.f6072I == null) goto L943;
     */
    /* JADX WARN: Code restructure failed: missing block: B:933:0x15c1, code lost:
    
        r3 = (int) (m5516o() * 100.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:934:0x15cb, code lost:
    
        if (r86.f6066C >= r3) goto L943;
     */
    /* JADX WARN: Code restructure failed: missing block: B:935:0x15cd, code lost:
    
        r86.f6066C = r3;
        r86.f6072I.mo5528a(r3);
        r3 = r86.f6083l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:936:0x15d6, code lost:
    
        if (r3 == null) goto L943;
     */
    /* JADX WARN: Code restructure failed: missing block: B:938:0x15e0, code lost:
    
        if (r3.getUsableSpace() <= 0) goto L1406;
     */
    /* JADX WARN: Code restructure failed: missing block: B:941:0x15ec, code lost:
    
        throw new com.cyberlink.mediacodec.Transcoder.MyRuntimeException(r86, "Device ran out of storage space.", com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6101D);
     */
    /* JADX WARN: Code restructure failed: missing block: B:942:0x15ed, code lost:
    
        r66 = r6;
        r53 = r8;
        r52 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:943:0x15f4, code lost:
    
        if (r56 == false) goto L953;
     */
    /* JADX WARN: Code restructure failed: missing block: B:944:0x15f6, code lost:
    
        if (r12 == null) goto L947;
     */
    /* JADX WARN: Code restructure failed: missing block: B:946:0x15fc, code lost:
    
        if (r12.m5550f() == false) goto L953;
     */
    /* JADX WARN: Code restructure failed: missing block: B:947:0x15fe, code lost:
    
        if (r57 == false) goto L953;
     */
    /* JADX WARN: Code restructure failed: missing block: B:948:0x1600, code lost:
    
        if (r11 == null) goto L951;
     */
    /* JADX WARN: Code restructure failed: missing block: B:950:0x1606, code lost:
    
        if (r11.m5550f() == false) goto L953;
     */
    /* JADX WARN: Code restructure failed: missing block: B:951:0x1608, code lost:
    
        m5508g("Muxer EOS", new java.lang.Object[0]);
        r58 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:952:0x1613, code lost:
    
        r53 = r8;
        r52 = r9;
        r13 = r75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:953:0x161a, code lost:
    
        if (r58 == false) goto L1408;
     */
    /* JADX WARN: Code restructure failed: missing block: B:954:0x161c, code lost:
    
        if (r57 == false) goto L1409;
     */
    /* JADX WARN: Code restructure failed: missing block: B:955:0x161e, code lost:
    
        if (r56 == false) goto L1410;
     */
    /* JADX WARN: Code restructure failed: missing block: B:956:0x1620, code lost:
    
        m5508g("Overall EOS", new java.lang.Object[0]);
        r86.f6090s = r86.f6088q;
        r86.f6089r = r86.f6087p;
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6105b;
        r86.f6068E = "Transcoded successfully.";
     */
    /* JADX WARN: Code restructure failed: missing block: B:958:0x1639, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:959:0x163a, code lost:
    
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:960:0x163e, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:961:0x163f, code lost:
    
        r63 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:962:0x1643, code lost:
    
        r62 = r2;
        r10 = r5;
        r3 = r40;
        r4 = r41;
        r5 = r42;
        r40 = r43;
        r15 = r45;
        r9 = r52;
        r52 = r53;
        r2 = r68;
        r6 = r69;
        r43 = r70;
        r44 = r71;
        r8 = r72;
        r14 = r74;
        r42 = r77;
        r53 = r11;
        r45 = r38;
        r11 = r64;
        r38 = r12;
        r12 = r66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:963:0x166e, code lost:
    
        r72 = r8;
        r52 = r9;
        r5 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:964:0x1673, code lost:
    
        r86.f6065B = true;
        r86.f6069F = java.lang.System.currentTimeMillis() - r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:965:0x1682, code lost:
    
        if (com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6105b != r86.f6067D) goto L967;
     */
    /* JADX WARN: Code restructure failed: missing block: B:966:0x1684, code lost:
    
        r2 = r86.f6068E + r28 + r86.f6069F + " ms.";
        r86.f6068E = r2;
        m5508g(r2, new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:967:0x16a9, code lost:
    
        if (r5 == null) goto L969;
     */
    /* JADX WARN: Code restructure failed: missing block: B:968:0x16ab, code lost:
    
        r5.m5544f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:969:0x16ae, code lost:
    
        if (r72 == null) goto L971;
     */
    /* JADX WARN: Code restructure failed: missing block: B:970:0x16b0, code lost:
    
        r72.m5559e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:971:0x16b3, code lost:
    
        if (r52 == null) goto L973;
     */
    /* JADX WARN: Code restructure failed: missing block: B:972:0x16b5, code lost:
    
        r52.m5571k();
     */
    /* JADX WARN: Code restructure failed: missing block: B:973:0x16b8, code lost:
    
        r2 = r86.f6077f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:974:0x16ba, code lost:
    
        if (r2 == null) goto L979;
     */
    /* JADX WARN: Code restructure failed: missing block: B:975:0x16bc, code lost:
    
        r2.m5362l();
     */
    /* JADX WARN: Code restructure failed: missing block: B:977:0x16c0, code lost:
    
        m5506e("Extractor, failed to stop/release Extractor", new java.lang.Object[0]);
        r86.f6067D = com.cyberlink.mediacodec.Transcoder.TRANSCODER_STATUS.f6112i;
        r86.f6068E = "Extractor, failed to stop/release Extractor";
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1005:0x1735  */
    /* JADX WARN: Removed duplicated region for block: B:1013:0x1755  */
    /* JADX WARN: Removed duplicated region for block: B:1056:0x18d6 A[Catch: all -> 0x19b3, Exception -> 0x19b5, TryCatch #111 {Exception -> 0x19b5, all -> 0x19b3, blocks: (B:1068:0x199a, B:1069:0x19b2, B:1056:0x18d6, B:1057:0x18ef, B:1054:0x18b6, B:1055:0x18d5, B:1058:0x18f0, B:1059:0x191f, B:1060:0x1920, B:1061:0x194f, B:1062:0x1950, B:1063:0x197f), top: B:1379:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:1062:0x1950 A[Catch: all -> 0x19b3, Exception -> 0x19b5, TryCatch #111 {Exception -> 0x19b5, all -> 0x19b3, blocks: (B:1068:0x199a, B:1069:0x19b2, B:1056:0x18d6, B:1057:0x18ef, B:1054:0x18b6, B:1055:0x18d5, B:1058:0x18f0, B:1059:0x191f, B:1060:0x1920, B:1061:0x194f, B:1062:0x1950, B:1063:0x197f), top: B:1379:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:1084:0x1a03 A[Catch: all -> 0x1b24, TryCatch #2 {all -> 0x1b24, blocks: (B:1082:0x19fc, B:1084:0x1a03, B:1092:0x1a23, B:1085:0x1a0d, B:1087:0x1a11, B:1088:0x1a16, B:1090:0x1a1a, B:1091:0x1a1f), top: B:1220:0x19fc }] */
    /* JADX WARN: Removed duplicated region for block: B:1085:0x1a0d A[Catch: all -> 0x1b24, TryCatch #2 {all -> 0x1b24, blocks: (B:1082:0x19fc, B:1084:0x1a03, B:1092:0x1a23, B:1085:0x1a0d, B:1087:0x1a11, B:1088:0x1a16, B:1090:0x1a1a, B:1091:0x1a1f), top: B:1220:0x19fc }] */
    /* JADX WARN: Removed duplicated region for block: B:1095:0x1a40  */
    /* JADX WARN: Removed duplicated region for block: B:1097:0x1a63  */
    /* JADX WARN: Removed duplicated region for block: B:1099:0x1a68  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x025d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1101:0x1a6d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x025f A[Catch: all -> 0x064a, Exception -> 0x064c, TRY_ENTER, TryCatch #24 {all -> 0x064a, blocks: (B:78:0x0194, B:80:0x019c, B:82:0x01cb, B:88:0x0209, B:90:0x020f, B:92:0x021c, B:95:0x0228, B:134:0x02cf, B:137:0x02dc, B:139:0x02ea, B:141:0x02ef, B:144:0x02ff, B:148:0x0338, B:251:0x04f2, B:252:0x04fb, B:143:0x02f3, B:253:0x04fc, B:254:0x0505, B:110:0x025f, B:112:0x0268, B:120:0x0291, B:122:0x0299, B:125:0x02a2, B:127:0x02af, B:129:0x02b7, B:132:0x02c2, B:133:0x02cc, B:113:0x026e, B:115:0x0272, B:116:0x0276, B:118:0x027a, B:101:0x023c, B:104:0x0252, B:84:0x01e8, B:86:0x01ee, B:257:0x0511, B:259:0x051f, B:262:0x052e, B:265:0x0549, B:270:0x055c, B:278:0x057e, B:279:0x0587, B:276:0x0574, B:277:0x057d, B:286:0x05ac, B:289:0x05bb, B:311:0x063b, B:314:0x0640, B:315:0x0649, B:321:0x0650), top: B:1252:0x0194 }] */
    /* JADX WARN: Removed duplicated region for block: B:1110:0x1a8b  */
    /* JADX WARN: Removed duplicated region for block: B:1118:0x1aa9  */
    /* JADX WARN: Removed duplicated region for block: B:1126:0x1ac7  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0268 A[Catch: all -> 0x064a, Exception -> 0x064c, TryCatch #24 {all -> 0x064a, blocks: (B:78:0x0194, B:80:0x019c, B:82:0x01cb, B:88:0x0209, B:90:0x020f, B:92:0x021c, B:95:0x0228, B:134:0x02cf, B:137:0x02dc, B:139:0x02ea, B:141:0x02ef, B:144:0x02ff, B:148:0x0338, B:251:0x04f2, B:252:0x04fb, B:143:0x02f3, B:253:0x04fc, B:254:0x0505, B:110:0x025f, B:112:0x0268, B:120:0x0291, B:122:0x0299, B:125:0x02a2, B:127:0x02af, B:129:0x02b7, B:132:0x02c2, B:133:0x02cc, B:113:0x026e, B:115:0x0272, B:116:0x0276, B:118:0x027a, B:101:0x023c, B:104:0x0252, B:84:0x01e8, B:86:0x01ee, B:257:0x0511, B:259:0x051f, B:262:0x052e, B:265:0x0549, B:270:0x055c, B:278:0x057e, B:279:0x0587, B:276:0x0574, B:277:0x057d, B:286:0x05ac, B:289:0x05bb, B:311:0x063b, B:314:0x0640, B:315:0x0649, B:321:0x0650), top: B:1252:0x0194 }] */
    /* JADX WARN: Removed duplicated region for block: B:1134:0x1ae5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x026e A[Catch: all -> 0x064a, Exception -> 0x064c, TryCatch #24 {all -> 0x064a, blocks: (B:78:0x0194, B:80:0x019c, B:82:0x01cb, B:88:0x0209, B:90:0x020f, B:92:0x021c, B:95:0x0228, B:134:0x02cf, B:137:0x02dc, B:139:0x02ea, B:141:0x02ef, B:144:0x02ff, B:148:0x0338, B:251:0x04f2, B:252:0x04fb, B:143:0x02f3, B:253:0x04fc, B:254:0x0505, B:110:0x025f, B:112:0x0268, B:120:0x0291, B:122:0x0299, B:125:0x02a2, B:127:0x02af, B:129:0x02b7, B:132:0x02c2, B:133:0x02cc, B:113:0x026e, B:115:0x0272, B:116:0x0276, B:118:0x027a, B:101:0x023c, B:104:0x0252, B:84:0x01e8, B:86:0x01ee, B:257:0x0511, B:259:0x051f, B:262:0x052e, B:265:0x0549, B:270:0x055c, B:278:0x057e, B:279:0x0587, B:276:0x0574, B:277:0x057d, B:286:0x05ac, B:289:0x05bb, B:311:0x063b, B:314:0x0640, B:315:0x0649, B:321:0x0650), top: B:1252:0x0194 }] */
    /* JADX WARN: Removed duplicated region for block: B:1142:0x1b03  */
    /* JADX WARN: Removed duplicated region for block: B:1151:0x1b23 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:1157:0x1b37  */
    /* JADX WARN: Removed duplicated region for block: B:1159:0x1b5a  */
    /* JADX WARN: Removed duplicated region for block: B:1161:0x1b5f  */
    /* JADX WARN: Removed duplicated region for block: B:1163:0x1b64  */
    /* JADX WARN: Removed duplicated region for block: B:1172:0x1b82  */
    /* JADX WARN: Removed duplicated region for block: B:1180:0x1ba0  */
    /* JADX WARN: Removed duplicated region for block: B:1188:0x1bbe  */
    /* JADX WARN: Removed duplicated region for block: B:1196:0x1bdc  */
    /* JADX WARN: Removed duplicated region for block: B:1204:0x1bfa  */
    /* JADX WARN: Removed duplicated region for block: B:1212:0x1c18  */
    /* JADX WARN: Removed duplicated region for block: B:1281:0x0141 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1303:0x1b6b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1305:0x1a74 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1337:0x020f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1396:0x04fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02ea A[Catch: all -> 0x064a, Exception -> 0x064c, TryCatch #24 {all -> 0x064a, blocks: (B:78:0x0194, B:80:0x019c, B:82:0x01cb, B:88:0x0209, B:90:0x020f, B:92:0x021c, B:95:0x0228, B:134:0x02cf, B:137:0x02dc, B:139:0x02ea, B:141:0x02ef, B:144:0x02ff, B:148:0x0338, B:251:0x04f2, B:252:0x04fb, B:143:0x02f3, B:253:0x04fc, B:254:0x0505, B:110:0x025f, B:112:0x0268, B:120:0x0291, B:122:0x0299, B:125:0x02a2, B:127:0x02af, B:129:0x02b7, B:132:0x02c2, B:133:0x02cc, B:113:0x026e, B:115:0x0272, B:116:0x0276, B:118:0x027a, B:101:0x023c, B:104:0x0252, B:84:0x01e8, B:86:0x01ee, B:257:0x0511, B:259:0x051f, B:262:0x052e, B:265:0x0549, B:270:0x055c, B:278:0x057e, B:279:0x0587, B:276:0x0574, B:277:0x057d, B:286:0x05ac, B:289:0x05bb, B:311:0x063b, B:314:0x0640, B:315:0x0649, B:321:0x0650), top: B:1252:0x0194 }] */
    /* JADX WARN: Removed duplicated region for block: B:1424:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1425:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007d A[Catch: all -> 0x19b7, Exception -> 0x19db, TRY_ENTER, TRY_LEAVE, TryCatch #119 {Exception -> 0x19db, all -> 0x19b7, blocks: (B:3:0x0029, B:16:0x007d), top: B:1363:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0135 A[Catch: all -> 0x1980, Exception -> 0x198d, TryCatch #48 {all -> 0x1980, blocks: (B:68:0x013d, B:70:0x0141, B:71:0x014b, B:62:0x0131, B:64:0x0135, B:66:0x0139), top: B:1283:0x0131 }] */
    /* JADX WARN: Removed duplicated region for block: B:989:0x16f5  */
    /* JADX WARN: Removed duplicated region for block: B:997:0x1715  */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() throws Throwable {
        String str;
        long j9;
        Exception exc;
        boolean z8;
        boolean z9;
        boolean z10;
        C1249f c1249f;
        boolean z11;
        boolean z12;
        C1246c c1246c;
        C1248e c1248e;
        Throwable th;
        boolean z13;
        C1215b c1215b;
        CLMediaCodec cLMediaCodec;
        CLMediaCodec cLMediaCodec2;
        MediaCodec mediaCodec;
        MediaCodec mediaCodec2;
        MediaMuxer mediaMuxer;
        InterfaceC1243a interfaceC1243a;
        InterfaceC1243a interfaceC1243a2;
        C1215b c1215b2;
        CLMediaCodec cLMediaCodec3;
        CLMediaCodec cLMediaCodec4;
        MediaCodec mediaCodec3;
        MediaCodec mediaCodec4;
        MediaMuxer mediaMuxer2;
        String str2;
        String str3;
        Exception exc2;
        CLMediaCodec cLMediaCodec5;
        MediaCodec mediaCodec5;
        MediaCodec mediaCodec6;
        MediaMuxer mediaMuxer3;
        String str4;
        String str5;
        boolean z14;
        boolean z15;
        float f9;
        boolean z16;
        Uri uri;
        String strExtractMetadata;
        String strExtractMetadata2;
        int integer;
        C1248e c1248e2;
        C1248e c1248e3;
        C1249f c1249f2;
        Surface surfaceM5558d;
        Surface surfaceM5558d2;
        C1217d c1217dM5377g;
        String str6 = "rotation";
        String str7 = " ms.";
        String str8 = " Spent ";
        String str9 = "rotation-degrees";
        String str10 = "frame-rate";
        String str11 = "Muxer, failed to stop/release muxer";
        String str12 = "VideoEncoder, failed to stop/release VideoEnocder";
        String str13 = "AudioDecoder, failed to stop/release AudioDecoder";
        m5519r();
        this.f6067D = TRANSCODER_STATUS.STATUS_TRANSCODING;
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            str = this.f6074c;
        } catch (Exception e9) {
            e = e9;
            str = "VideoEncoder, failed to stop/release VideoEnocder";
            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
            str6 = "Extractor, failed to stop/release Extractor";
            j9 = jCurrentTimeMillis;
            str13 = "Muxer, failed to stop/release muxer";
            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
        } catch (Throwable th2) {
            th = th2;
            str = "VideoEncoder, failed to stop/release VideoEnocder";
            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
            str6 = "Extractor, failed to stop/release Extractor";
            j9 = jCurrentTimeMillis;
            str13 = "Muxer, failed to stop/release muxer";
            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
        }
        try {
            if (str == null) {
                try {
                    try {
                        if (this.f6075d == null) {
                            if (this.f6064A != null) {
                                if (this.f6082k != null) {
                                    try {
                                    } catch (Exception e10) {
                                        e = e10;
                                        str2 = ")";
                                    }
                                    try {
                                        try {
                                            if (this.f6064A != null) {
                                                str2 = ")";
                                                try {
                                                    m5508g("Input: AssetFileDescriptor", new Object[0]);
                                                    c1217dM5377g = new C1217d.b(this.f6064A.getFileDescriptor()).m5381k(this.f6064A.getStartOffset()).m5380j(this.f6064A.getLength()).m5377g();
                                                    str3 = "VideoEncoder, failed to stop/release VideoEnocder";
                                                } catch (Exception e11) {
                                                    exc2 = e11;
                                                    str3 = "VideoEncoder, failed to stop/release VideoEnocder";
                                                    try {
                                                        try {
                                                            if (this.f6075d != null) {
                                                            }
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                            str6 = "Extractor, failed to stop/release Extractor";
                                                            j9 = jCurrentTimeMillis;
                                                            str13 = "Muxer, failed to stop/release muxer";
                                                            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                            str = str3;
                                                            str8 = " ms.";
                                                            str7 = " Spent ";
                                                            th = th;
                                                            z13 = true;
                                                            z8 = false;
                                                            z9 = false;
                                                            z10 = false;
                                                            c1249f = null;
                                                            z11 = false;
                                                            z12 = false;
                                                            c1246c = null;
                                                            c1248e = null;
                                                            this.f6065B = z13;
                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                            }
                                                            if (c1246c != null) {
                                                            }
                                                            if (c1248e != null) {
                                                            }
                                                            if (c1249f != null) {
                                                            }
                                                            c1215b = this.f6077f;
                                                            if (c1215b != null) {
                                                            }
                                                            cLMediaCodec = this.f6078g;
                                                            if (cLMediaCodec != null) {
                                                            }
                                                            cLMediaCodec2 = this.f6081j;
                                                            if (cLMediaCodec2 != null) {
                                                            }
                                                            mediaCodec = this.f6085n;
                                                            if (mediaCodec != null) {
                                                            }
                                                            mediaCodec2 = this.f6086o;
                                                            if (mediaCodec2 != null) {
                                                            }
                                                            mediaMuxer = this.f6084m;
                                                            if (mediaMuxer != null) {
                                                            }
                                                            interfaceC1243a = this.f6072I;
                                                            if (interfaceC1243a == null) {
                                                            }
                                                        }
                                                    } catch (Exception e12) {
                                                        e = e12;
                                                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                        str6 = "Extractor, failed to stop/release Extractor";
                                                        j9 = jCurrentTimeMillis;
                                                        str13 = "Muxer, failed to stop/release muxer";
                                                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                        str = str3;
                                                        str8 = " ms.";
                                                        str7 = " Spent ";
                                                        exc = e;
                                                        z8 = false;
                                                        z9 = false;
                                                        z10 = false;
                                                        c1249f = null;
                                                        z11 = false;
                                                        z12 = false;
                                                        c1246c = null;
                                                        c1248e = null;
                                                        try {
                                                            exc.printStackTrace();
                                                            if (!(exc instanceof MyRuntimeException)) {
                                                            }
                                                            this.f6068E = exc.getMessage();
                                                            this.f6090s = 0L;
                                                            this.f6089r = 0L;
                                                            this.f6065B = true;
                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                            }
                                                            if (c1246c != null) {
                                                            }
                                                            if (c1248e != null) {
                                                            }
                                                            if (c1249f != null) {
                                                            }
                                                            c1215b2 = this.f6077f;
                                                            if (c1215b2 != null) {
                                                            }
                                                            cLMediaCodec3 = this.f6078g;
                                                            if (cLMediaCodec3 != null) {
                                                            }
                                                            cLMediaCodec4 = this.f6081j;
                                                            if (cLMediaCodec4 != null) {
                                                            }
                                                            mediaCodec3 = this.f6085n;
                                                            if (mediaCodec3 != null) {
                                                            }
                                                            mediaCodec4 = this.f6086o;
                                                            if (mediaCodec4 != null) {
                                                            }
                                                            mediaMuxer2 = this.f6084m;
                                                            if (mediaMuxer2 != null) {
                                                            }
                                                            interfaceC1243a2 = this.f6072I;
                                                            if (interfaceC1243a2 == null) {
                                                            }
                                                            interfaceC1243a2.mo5529b(this);
                                                        } catch (Throwable th4) {
                                                            th = th4;
                                                            z13 = true;
                                                            this.f6065B = z13;
                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                            }
                                                            if (c1246c != null) {
                                                            }
                                                            if (c1248e != null) {
                                                            }
                                                            if (c1249f != null) {
                                                            }
                                                            c1215b = this.f6077f;
                                                            if (c1215b != null) {
                                                            }
                                                            cLMediaCodec = this.f6078g;
                                                            if (cLMediaCodec != null) {
                                                            }
                                                            cLMediaCodec2 = this.f6081j;
                                                            if (cLMediaCodec2 != null) {
                                                            }
                                                            mediaCodec = this.f6085n;
                                                            if (mediaCodec != null) {
                                                            }
                                                            mediaCodec2 = this.f6086o;
                                                            if (mediaCodec2 != null) {
                                                            }
                                                            mediaMuxer = this.f6084m;
                                                            if (mediaMuxer != null) {
                                                            }
                                                            interfaceC1243a = this.f6072I;
                                                            if (interfaceC1243a == null) {
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                str2 = ")";
                                                try {
                                                    Uri uri2 = this.f6075d;
                                                    if (uri2 != null) {
                                                        str3 = "VideoEncoder, failed to stop/release VideoEnocder";
                                                        try {
                                                            Object[] objArr = new Object[1];
                                                            objArr[0] = uri2;
                                                            m5508g("Input: %s", objArr);
                                                            c1217dM5377g = new C1217d.b(this.f6076e, this.f6075d).m5377g();
                                                        } catch (Throwable th5) {
                                                            th = th5;
                                                            z13 = true;
                                                            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                            str6 = "Extractor, failed to stop/release Extractor";
                                                            j9 = jCurrentTimeMillis;
                                                            str13 = "Muxer, failed to stop/release muxer";
                                                            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                            str = str3;
                                                            z8 = false;
                                                            z9 = false;
                                                            z10 = false;
                                                            c1249f = null;
                                                            z11 = false;
                                                            z12 = false;
                                                            c1246c = null;
                                                            c1248e = null;
                                                            str8 = " ms.";
                                                            str7 = " Spent ";
                                                            this.f6065B = z13;
                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                            }
                                                            if (c1246c != null) {
                                                            }
                                                            if (c1248e != null) {
                                                            }
                                                            if (c1249f != null) {
                                                            }
                                                            c1215b = this.f6077f;
                                                            if (c1215b != null) {
                                                            }
                                                            cLMediaCodec = this.f6078g;
                                                            if (cLMediaCodec != null) {
                                                            }
                                                            cLMediaCodec2 = this.f6081j;
                                                            if (cLMediaCodec2 != null) {
                                                            }
                                                            mediaCodec = this.f6085n;
                                                            if (mediaCodec != null) {
                                                            }
                                                            mediaCodec2 = this.f6086o;
                                                            if (mediaCodec2 != null) {
                                                            }
                                                            mediaMuxer = this.f6084m;
                                                            if (mediaMuxer != null) {
                                                            }
                                                            interfaceC1243a = this.f6072I;
                                                            if (interfaceC1243a == null) {
                                                            }
                                                        }
                                                    } else {
                                                        str3 = "VideoEncoder, failed to stop/release VideoEnocder";
                                                        if (str != null) {
                                                            try {
                                                                Object[] objArr2 = new Object[1];
                                                                objArr2[0] = str;
                                                                m5508g("Input: %s", objArr2);
                                                                c1217dM5377g = new C1217d.b(this.f6074c).m5377g();
                                                            } catch (Throwable th6) {
                                                                th = th6;
                                                                z13 = true;
                                                                str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                str6 = "Extractor, failed to stop/release Extractor";
                                                                j9 = jCurrentTimeMillis;
                                                                str13 = "Muxer, failed to stop/release muxer";
                                                                str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                str = str3;
                                                                z8 = false;
                                                                z9 = false;
                                                                z10 = false;
                                                                c1249f = null;
                                                                z11 = false;
                                                                z12 = false;
                                                                c1246c = null;
                                                                c1248e = null;
                                                                str8 = " ms.";
                                                                str7 = " Spent ";
                                                                this.f6065B = z13;
                                                                this.f6069F = System.currentTimeMillis() - j9;
                                                                if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                }
                                                                if (c1246c != null) {
                                                                }
                                                                if (c1248e != null) {
                                                                }
                                                                if (c1249f != null) {
                                                                }
                                                                c1215b = this.f6077f;
                                                                if (c1215b != null) {
                                                                }
                                                                cLMediaCodec = this.f6078g;
                                                                if (cLMediaCodec != null) {
                                                                }
                                                                cLMediaCodec2 = this.f6081j;
                                                                if (cLMediaCodec2 != null) {
                                                                }
                                                                mediaCodec = this.f6085n;
                                                                if (mediaCodec != null) {
                                                                }
                                                                mediaCodec2 = this.f6086o;
                                                                if (mediaCodec2 != null) {
                                                                }
                                                                mediaMuxer = this.f6084m;
                                                                if (mediaMuxer != null) {
                                                                }
                                                                interfaceC1243a = this.f6072I;
                                                                if (interfaceC1243a == null) {
                                                                }
                                                            }
                                                        } else {
                                                            c1217dM5377g = null;
                                                        }
                                                    }
                                                } catch (Exception e13) {
                                                    e = e13;
                                                    str3 = "VideoEncoder, failed to stop/release VideoEnocder";
                                                    exc2 = e;
                                                    if (this.f6075d != null) {
                                                    }
                                                }
                                            }
                                            this.f6077f = C1215b.m5350b(c1217dM5377g);
                                        } catch (Exception e14) {
                                            e = e14;
                                            exc2 = e;
                                            if (this.f6075d != null) {
                                                throw new MyRuntimeException("Extractor, failed to set data source! (input uri: " + this.f6075d + str2, TRANSCODER_STATUS.STATUS_ERROR_SETTING_EXTRACTOR_SOURCE, exc2);
                                            }
                                            if (this.f6074c != null) {
                                                throw new MyRuntimeException("Extractor, failed to set data source! (input file: " + this.f6074c + str2, TRANSCODER_STATUS.STATUS_ERROR_SETTING_EXTRACTOR_SOURCE, exc2);
                                            }
                                            if (this.f6064A != null) {
                                                throw new MyRuntimeException("Extractor, failed to set data source! (input mAssetFileDescriptor: " + this.f6064A + str2, TRANSCODER_STATUS.STATUS_ERROR_SETTING_EXTRACTOR_SOURCE, exc2);
                                            }
                                            if (this.f6077f != null) {
                                            }
                                        }
                                        if (this.f6077f != null) {
                                            throw new MyRuntimeException("Cannnot create extractor!", TRANSCODER_STATUS.STATUS_ERROR_CREATING_EXTRACTOR);
                                        }
                                        try {
                                            this.f6084m = new MediaMuxer(this.f6082k, 0);
                                            this.f6083l = new File(this.f6082k).getParentFile();
                                            m5508g("Extractor, track count %d", Integer.valueOf(this.f6077f.m5359h()));
                                            int i9 = 0;
                                            C1248e c1248e4 = null;
                                            C1249f c1249f3 = null;
                                            C1246c c1246c2 = null;
                                            int integer2 = 0;
                                            int i10 = -1;
                                            int i11 = -1;
                                            while (true) {
                                                try {
                                                    String str14 = str8;
                                                    if (i9 >= this.f6077f.m5359h()) {
                                                        break;
                                                    }
                                                    try {
                                                        MediaFormat mediaFormatM5360i = this.f6077f.m5360i(i9);
                                                        String string = mediaFormatM5360i.getString("mime");
                                                        j9 = jCurrentTimeMillis;
                                                        try {
                                                            try {
                                                                if (string.startsWith("video/")) {
                                                                    int i12 = integer2;
                                                                    m5508g("Extractor, Video track %d, format: %s", Integer.valueOf(i9), mediaFormatM5360i.toString());
                                                                    m5515n(string, false);
                                                                    this.f6077f.m5363m(i9);
                                                                    this.f6088q = mediaFormatM5360i.getLong("durationUs");
                                                                    if (mediaFormatM5360i.containsKey(str9)) {
                                                                        integer2 = mediaFormatM5360i.getInteger(str9);
                                                                        mediaFormatM5360i.setInteger(str9, 0);
                                                                        m5508g("Video orientation (by %s): %d", str9, Integer.valueOf(integer2));
                                                                    } else if (mediaFormatM5360i.containsKey(str6)) {
                                                                        integer2 = mediaFormatM5360i.getInteger(str6);
                                                                        m5508g("Video orientation (by %s): %d", str6, Integer.valueOf(integer2));
                                                                    } else {
                                                                        integer2 = i12;
                                                                        z14 = true;
                                                                        if (mediaFormatM5360i.containsKey("frame-rate")) {
                                                                            str4 = str6;
                                                                            str5 = str9;
                                                                        } else {
                                                                            try {
                                                                                f9 = mediaFormatM5360i.getFloat("frame-rate");
                                                                                this.f6091t = f9;
                                                                                str4 = str6;
                                                                                str5 = str9;
                                                                            } catch (Exception unused) {
                                                                                str4 = str6;
                                                                                str5 = str9;
                                                                            }
                                                                            try {
                                                                                Object[] objArr3 = new Object[2];
                                                                                objArr3[0] = "frame-rate";
                                                                                Float fValueOf = Float.valueOf(f9);
                                                                                z16 = true;
                                                                                try {
                                                                                    objArr3[1] = fValueOf;
                                                                                    m5508g("Video framerate (by %s, int): %f", objArr3);
                                                                                } catch (Throwable th7) {
                                                                                    th = th7;
                                                                                    c1248e = c1248e4;
                                                                                    c1249f = c1249f3;
                                                                                    c1246c = c1246c2;
                                                                                    z13 = z16;
                                                                                    str13 = "Muxer, failed to stop/release muxer";
                                                                                    str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                    str = str3;
                                                                                    str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                    str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                    str6 = "Extractor, failed to stop/release Extractor";
                                                                                    str8 = " ms.";
                                                                                    str7 = str14;
                                                                                    z8 = false;
                                                                                    z9 = false;
                                                                                    z10 = false;
                                                                                    z11 = false;
                                                                                    z12 = false;
                                                                                    this.f6065B = z13;
                                                                                    this.f6069F = System.currentTimeMillis() - j9;
                                                                                    if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                    }
                                                                                    if (c1246c != null) {
                                                                                    }
                                                                                    if (c1248e != null) {
                                                                                    }
                                                                                    if (c1249f != null) {
                                                                                    }
                                                                                    c1215b = this.f6077f;
                                                                                    if (c1215b != null) {
                                                                                    }
                                                                                    cLMediaCodec = this.f6078g;
                                                                                    if (cLMediaCodec != null) {
                                                                                    }
                                                                                    cLMediaCodec2 = this.f6081j;
                                                                                    if (cLMediaCodec2 != null) {
                                                                                    }
                                                                                    mediaCodec = this.f6085n;
                                                                                    if (mediaCodec != null) {
                                                                                    }
                                                                                    mediaCodec2 = this.f6086o;
                                                                                    if (mediaCodec2 != null) {
                                                                                    }
                                                                                    mediaMuxer = this.f6084m;
                                                                                    if (mediaMuxer != null) {
                                                                                    }
                                                                                    interfaceC1243a = this.f6072I;
                                                                                    if (interfaceC1243a == null) {
                                                                                    }
                                                                                }
                                                                            } catch (Exception unused2) {
                                                                                try {
                                                                                    float integer3 = mediaFormatM5360i.getInteger("frame-rate");
                                                                                    this.f6091t = integer3;
                                                                                    Object[] objArr4 = new Object[2];
                                                                                    objArr4[0] = "frame-rate";
                                                                                    objArr4[1] = Float.valueOf(integer3);
                                                                                    m5508g("Video framerate (by %s, float): %f", objArr4);
                                                                                    z15 = false;
                                                                                } catch (Exception unused3) {
                                                                                }
                                                                                if (!z14) {
                                                                                }
                                                                                this.f6065B = z13;
                                                                                this.f6069F = System.currentTimeMillis() - j9;
                                                                                if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                }
                                                                                if (c1246c != null) {
                                                                                }
                                                                                if (c1248e != null) {
                                                                                }
                                                                                if (c1249f != null) {
                                                                                }
                                                                                c1215b = this.f6077f;
                                                                                if (c1215b != null) {
                                                                                }
                                                                                cLMediaCodec = this.f6078g;
                                                                                if (cLMediaCodec != null) {
                                                                                }
                                                                                cLMediaCodec2 = this.f6081j;
                                                                                if (cLMediaCodec2 != null) {
                                                                                }
                                                                                mediaCodec = this.f6085n;
                                                                                if (mediaCodec != null) {
                                                                                }
                                                                                mediaCodec2 = this.f6086o;
                                                                                if (mediaCodec2 != null) {
                                                                                }
                                                                                mediaMuxer = this.f6084m;
                                                                                if (mediaMuxer != null) {
                                                                                }
                                                                                interfaceC1243a = this.f6072I;
                                                                                if (interfaceC1243a == null) {
                                                                                }
                                                                            }
                                                                            z15 = false;
                                                                            if (!z14 || z15) {
                                                                                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                                                                                uri = this.f6075d;
                                                                                if (uri == null) {
                                                                                    mediaMetadataRetriever.setDataSource(this.f6076e, uri);
                                                                                } else {
                                                                                    String str15 = this.f6074c;
                                                                                    if (str15 != null) {
                                                                                        mediaMetadataRetriever.setDataSource(str15);
                                                                                    } else {
                                                                                        AssetFileDescriptor assetFileDescriptor = this.f6064A;
                                                                                        if (assetFileDescriptor != null) {
                                                                                            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), this.f6064A.getStartOffset(), this.f6064A.getLength());
                                                                                        }
                                                                                    }
                                                                                }
                                                                                if (z14 && (strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(24)) != null) {
                                                                                    integer2 = Integer.parseInt(strExtractMetadata2);
                                                                                    Object[] objArr5 = new Object[1];
                                                                                    objArr5[0] = Integer.valueOf(integer2);
                                                                                    m5508g("Video orientation (by MediaMetadataRetriever) %d", objArr5);
                                                                                }
                                                                                if (z15 && (strExtractMetadata = mediaMetadataRetriever.extractMetadata(25)) != null) {
                                                                                    float f10 = Float.parseFloat(strExtractMetadata);
                                                                                    this.f6091t = f10;
                                                                                    Object[] objArr6 = new Object[1];
                                                                                    objArr6[0] = Float.valueOf(f10);
                                                                                    m5508g("Video framerate (by MediaMetadataRetriever) %f", objArr6);
                                                                                }
                                                                                mediaMetadataRetriever.release();
                                                                            }
                                                                            MediaCodec mediaCodecCreateEncoderByType = MediaCodec.createEncoderByType(MimeTypes.VIDEO_H264);
                                                                            this.f6085n = mediaCodecCreateEncoderByType;
                                                                            try {
                                                                                Object[] objArr7 = new Object[1];
                                                                                objArr7[0] = mediaCodecCreateEncoderByType.getName();
                                                                                m5508g("Using videoEncoder: %s", objArr7);
                                                                                if (this.f6085n != null) {
                                                                                    throw new MyRuntimeException("Cannot create videoEncoder!", TRANSCODER_STATUS.STATUS_ERROR_CREATING_VIDEO_ENCODER);
                                                                                }
                                                                                int integer4 = this.f6095x;
                                                                                if (-1 == integer4 || -1 == (integer = this.f6096y)) {
                                                                                    integer4 = mediaFormatM5360i.getInteger("width");
                                                                                    integer = mediaFormatM5360i.getInteger("height");
                                                                                }
                                                                                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MimeTypes.VIDEO_H264, integer4, integer);
                                                                                mediaFormatCreateVideoFormat.setInteger("bitrate", m5514m(integer4, integer, this.f6097z));
                                                                                mediaFormatCreateVideoFormat.setInteger("frame-rate", this.f6097z);
                                                                                mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
                                                                                mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
                                                                                try {
                                                                                    m5508g("config VideoEncoder with format: %s", mediaFormatCreateVideoFormat.toString());
                                                                                    this.f6085n.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
                                                                                    C1246c c1246c3 = new C1246c(this.f6085n.createInputSurface());
                                                                                    try {
                                                                                        try {
                                                                                            c1246c3.m5543e();
                                                                                            CLMediaCodec cLMediaCodecM5304g = CLMediaCodec.m5304g(string, false);
                                                                                            this.f6078g = cLMediaCodecM5304g;
                                                                                            z16 = true;
                                                                                            try {
                                                                                                Object[] objArr8 = new Object[1];
                                                                                                objArr8[0] = cLMediaCodecM5304g.m5314m();
                                                                                                m5508g("Using videoDecoder: %s", objArr8);
                                                                                                if (this.f6078g != null) {
                                                                                                    if (this.f6080i) {
                                                                                                        c1248e2 = new C1248e();
                                                                                                        try {
                                                                                                            try {
                                                                                                                surfaceM5558d2 = c1248e2.m5558d();
                                                                                                            } catch (Exception e15) {
                                                                                                                e = e15;
                                                                                                                c1246c = c1246c3;
                                                                                                                c1248e = c1248e2;
                                                                                                                c1249f = c1249f3;
                                                                                                                str13 = "Muxer, failed to stop/release muxer";
                                                                                                                str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                                str = str3;
                                                                                                                str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                                str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                                str6 = "Extractor, failed to stop/release Extractor";
                                                                                                                str8 = " ms.";
                                                                                                                str7 = str14;
                                                                                                                z8 = false;
                                                                                                                z9 = false;
                                                                                                                z10 = false;
                                                                                                                z11 = false;
                                                                                                                z12 = false;
                                                                                                                exc = e;
                                                                                                                exc.printStackTrace();
                                                                                                                if (!(exc instanceof MyRuntimeException)) {
                                                                                                                }
                                                                                                                this.f6068E = exc.getMessage();
                                                                                                                this.f6090s = 0L;
                                                                                                                this.f6089r = 0L;
                                                                                                                this.f6065B = true;
                                                                                                                this.f6069F = System.currentTimeMillis() - j9;
                                                                                                                if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                                }
                                                                                                                if (c1246c != null) {
                                                                                                                }
                                                                                                                if (c1248e != null) {
                                                                                                                }
                                                                                                                if (c1249f != null) {
                                                                                                                }
                                                                                                                c1215b2 = this.f6077f;
                                                                                                                if (c1215b2 != null) {
                                                                                                                }
                                                                                                                cLMediaCodec3 = this.f6078g;
                                                                                                                if (cLMediaCodec3 != null) {
                                                                                                                }
                                                                                                                cLMediaCodec4 = this.f6081j;
                                                                                                                if (cLMediaCodec4 != null) {
                                                                                                                }
                                                                                                                mediaCodec3 = this.f6085n;
                                                                                                                if (mediaCodec3 != null) {
                                                                                                                }
                                                                                                                mediaCodec4 = this.f6086o;
                                                                                                                if (mediaCodec4 != null) {
                                                                                                                }
                                                                                                                mediaMuxer2 = this.f6084m;
                                                                                                                if (mediaMuxer2 != null) {
                                                                                                                }
                                                                                                                interfaceC1243a2 = this.f6072I;
                                                                                                                if (interfaceC1243a2 == null) {
                                                                                                                }
                                                                                                                interfaceC1243a2.mo5529b(this);
                                                                                                            }
                                                                                                        } catch (Throwable th8) {
                                                                                                            th = th8;
                                                                                                            c1246c = c1246c3;
                                                                                                            c1248e = c1248e2;
                                                                                                            c1249f = c1249f3;
                                                                                                            str13 = "Muxer, failed to stop/release muxer";
                                                                                                            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                            str = str3;
                                                                                                            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                            str6 = "Extractor, failed to stop/release Extractor";
                                                                                                            str8 = " ms.";
                                                                                                            str7 = str14;
                                                                                                            z13 = true;
                                                                                                            z8 = false;
                                                                                                            z9 = false;
                                                                                                            z10 = false;
                                                                                                            z11 = false;
                                                                                                            z12 = false;
                                                                                                            th = th;
                                                                                                            this.f6065B = z13;
                                                                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                            }
                                                                                                            if (c1246c != null) {
                                                                                                            }
                                                                                                            if (c1248e != null) {
                                                                                                            }
                                                                                                            if (c1249f != null) {
                                                                                                            }
                                                                                                            c1215b = this.f6077f;
                                                                                                            if (c1215b != null) {
                                                                                                            }
                                                                                                            cLMediaCodec = this.f6078g;
                                                                                                            if (cLMediaCodec != null) {
                                                                                                            }
                                                                                                            cLMediaCodec2 = this.f6081j;
                                                                                                            if (cLMediaCodec2 != null) {
                                                                                                            }
                                                                                                            mediaCodec = this.f6085n;
                                                                                                            if (mediaCodec != null) {
                                                                                                            }
                                                                                                            mediaCodec2 = this.f6086o;
                                                                                                            if (mediaCodec2 != null) {
                                                                                                            }
                                                                                                            mediaMuxer = this.f6084m;
                                                                                                            if (mediaMuxer != null) {
                                                                                                            }
                                                                                                            interfaceC1243a = this.f6072I;
                                                                                                            if (interfaceC1243a == null) {
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        c1248e2 = null;
                                                                                                        surfaceM5558d2 = null;
                                                                                                    }
                                                                                                    try {
                                                                                                        Object[] objArr9 = new Object[2];
                                                                                                        objArr9[0] = mediaFormatM5360i.toString();
                                                                                                        try {
                                                                                                            objArr9[1] = Boolean.valueOf(surfaceM5558d2 != null);
                                                                                                            m5508g("config VideoDecoder with format %s, using surface %b", objArr9);
                                                                                                            this.f6078g.m5310f(mediaFormatM5360i, surfaceM5558d2, null, 0);
                                                                                                        } catch (Throwable th9) {
                                                                                                            th = th9;
                                                                                                            c1246c = c1246c3;
                                                                                                            c1248e = c1248e2;
                                                                                                            c1249f = c1249f3;
                                                                                                            z13 = true;
                                                                                                            str13 = "Muxer, failed to stop/release muxer";
                                                                                                            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                            str = str3;
                                                                                                            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                            str6 = "Extractor, failed to stop/release Extractor";
                                                                                                            str8 = " ms.";
                                                                                                            str7 = str14;
                                                                                                            z8 = false;
                                                                                                            z9 = false;
                                                                                                            z10 = false;
                                                                                                            z11 = false;
                                                                                                            z12 = false;
                                                                                                            th = th;
                                                                                                            this.f6065B = z13;
                                                                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                            }
                                                                                                            if (c1246c != null) {
                                                                                                            }
                                                                                                            if (c1248e != null) {
                                                                                                            }
                                                                                                            if (c1249f != null) {
                                                                                                            }
                                                                                                            c1215b = this.f6077f;
                                                                                                            if (c1215b != null) {
                                                                                                            }
                                                                                                            cLMediaCodec = this.f6078g;
                                                                                                            if (cLMediaCodec != null) {
                                                                                                            }
                                                                                                            cLMediaCodec2 = this.f6081j;
                                                                                                            if (cLMediaCodec2 != null) {
                                                                                                            }
                                                                                                            mediaCodec = this.f6085n;
                                                                                                            if (mediaCodec != null) {
                                                                                                            }
                                                                                                            mediaCodec2 = this.f6086o;
                                                                                                            if (mediaCodec2 != null) {
                                                                                                            }
                                                                                                            mediaMuxer = this.f6084m;
                                                                                                            if (mediaMuxer != null) {
                                                                                                            }
                                                                                                            interfaceC1243a = this.f6072I;
                                                                                                            if (interfaceC1243a == null) {
                                                                                                            }
                                                                                                        }
                                                                                                    } catch (Exception e16) {
                                                                                                        if (!this.f6073b) {
                                                                                                            throw new MyRuntimeException("VideoDecoder, failed to configure!", TRANSCODER_STATUS.STATUS_ERROR_CONFIGURING_VIDEO_DECODER, e16);
                                                                                                        }
                                                                                                        m5508g("VideoDecoder, failed to configure!", new Object[0]);
                                                                                                        this.f6078g = null;
                                                                                                        c1248e2 = null;
                                                                                                    }
                                                                                                } else {
                                                                                                    c1248e2 = c1248e4;
                                                                                                }
                                                                                                if (this.f6078g == null && this.f6073b) {
                                                                                                    try {
                                                                                                        CLMediaCodec cLMediaCodecM5304g2 = CLMediaCodec.m5304g(string, true);
                                                                                                        this.f6078g = cLMediaCodecM5304g2;
                                                                                                        Object[] objArr10 = new Object[1];
                                                                                                        objArr10[0] = cLMediaCodecM5304g2.m5314m();
                                                                                                        m5508g("Using Extra videoDecoder: %s", objArr10);
                                                                                                        boolean zM5309e = this.f6078g.m5309e();
                                                                                                        this.f6080i = zM5309e;
                                                                                                        if (zM5309e) {
                                                                                                            c1248e3 = new C1248e();
                                                                                                            try {
                                                                                                                try {
                                                                                                                    surfaceM5558d = c1248e3.m5558d();
                                                                                                                } catch (Exception e17) {
                                                                                                                    exc = e17;
                                                                                                                    c1246c = c1246c3;
                                                                                                                    c1248e = c1248e3;
                                                                                                                    c1249f = c1249f3;
                                                                                                                    str13 = "Muxer, failed to stop/release muxer";
                                                                                                                    str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                                    str = str3;
                                                                                                                    str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                                    str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                                    str6 = "Extractor, failed to stop/release Extractor";
                                                                                                                    str8 = " ms.";
                                                                                                                    str7 = str14;
                                                                                                                    z8 = false;
                                                                                                                    z9 = false;
                                                                                                                    z10 = false;
                                                                                                                    z11 = false;
                                                                                                                    z12 = false;
                                                                                                                    exc.printStackTrace();
                                                                                                                    if (!(exc instanceof MyRuntimeException)) {
                                                                                                                    }
                                                                                                                    this.f6068E = exc.getMessage();
                                                                                                                    this.f6090s = 0L;
                                                                                                                    this.f6089r = 0L;
                                                                                                                    this.f6065B = true;
                                                                                                                    this.f6069F = System.currentTimeMillis() - j9;
                                                                                                                    if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                                    }
                                                                                                                    if (c1246c != null) {
                                                                                                                    }
                                                                                                                    if (c1248e != null) {
                                                                                                                    }
                                                                                                                    if (c1249f != null) {
                                                                                                                    }
                                                                                                                    c1215b2 = this.f6077f;
                                                                                                                    if (c1215b2 != null) {
                                                                                                                    }
                                                                                                                    cLMediaCodec3 = this.f6078g;
                                                                                                                    if (cLMediaCodec3 != null) {
                                                                                                                    }
                                                                                                                    cLMediaCodec4 = this.f6081j;
                                                                                                                    if (cLMediaCodec4 != null) {
                                                                                                                    }
                                                                                                                    mediaCodec3 = this.f6085n;
                                                                                                                    if (mediaCodec3 != null) {
                                                                                                                    }
                                                                                                                    mediaCodec4 = this.f6086o;
                                                                                                                    if (mediaCodec4 != null) {
                                                                                                                    }
                                                                                                                    mediaMuxer2 = this.f6084m;
                                                                                                                    if (mediaMuxer2 != null) {
                                                                                                                    }
                                                                                                                    interfaceC1243a2 = this.f6072I;
                                                                                                                    if (interfaceC1243a2 == null) {
                                                                                                                    }
                                                                                                                    interfaceC1243a2.mo5529b(this);
                                                                                                                }
                                                                                                            } catch (Throwable th10) {
                                                                                                                th = th10;
                                                                                                                c1246c = c1246c3;
                                                                                                                c1248e = c1248e3;
                                                                                                                c1249f = c1249f3;
                                                                                                                str13 = "Muxer, failed to stop/release muxer";
                                                                                                                str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                                str = str3;
                                                                                                                str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                                str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                                str6 = "Extractor, failed to stop/release Extractor";
                                                                                                                str8 = " ms.";
                                                                                                                str7 = str14;
                                                                                                                z13 = true;
                                                                                                                z8 = false;
                                                                                                                z9 = false;
                                                                                                                z10 = false;
                                                                                                                z11 = false;
                                                                                                                z12 = false;
                                                                                                                this.f6065B = z13;
                                                                                                                this.f6069F = System.currentTimeMillis() - j9;
                                                                                                                if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                                }
                                                                                                                if (c1246c != null) {
                                                                                                                }
                                                                                                                if (c1248e != null) {
                                                                                                                }
                                                                                                                if (c1249f != null) {
                                                                                                                }
                                                                                                                c1215b = this.f6077f;
                                                                                                                if (c1215b != null) {
                                                                                                                }
                                                                                                                cLMediaCodec = this.f6078g;
                                                                                                                if (cLMediaCodec != null) {
                                                                                                                }
                                                                                                                cLMediaCodec2 = this.f6081j;
                                                                                                                if (cLMediaCodec2 != null) {
                                                                                                                }
                                                                                                                mediaCodec = this.f6085n;
                                                                                                                if (mediaCodec != null) {
                                                                                                                }
                                                                                                                mediaCodec2 = this.f6086o;
                                                                                                                if (mediaCodec2 != null) {
                                                                                                                }
                                                                                                                mediaMuxer = this.f6084m;
                                                                                                                if (mediaMuxer != null) {
                                                                                                                }
                                                                                                                interfaceC1243a = this.f6072I;
                                                                                                                if (interfaceC1243a == null) {
                                                                                                                }
                                                                                                            }
                                                                                                        } else {
                                                                                                            c1248e3 = null;
                                                                                                            surfaceM5558d = null;
                                                                                                        }
                                                                                                    } catch (Throwable th11) {
                                                                                                        th = th11;
                                                                                                        c1246c = c1246c3;
                                                                                                        c1248e = c1248e2;
                                                                                                        z13 = true;
                                                                                                        c1249f = c1249f3;
                                                                                                        str13 = "Muxer, failed to stop/release muxer";
                                                                                                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                        str = str3;
                                                                                                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                        str6 = "Extractor, failed to stop/release Extractor";
                                                                                                        str8 = " ms.";
                                                                                                        str7 = str14;
                                                                                                        z8 = false;
                                                                                                        z9 = false;
                                                                                                        z10 = false;
                                                                                                        z11 = false;
                                                                                                        z12 = false;
                                                                                                        th = th;
                                                                                                        this.f6065B = z13;
                                                                                                        this.f6069F = System.currentTimeMillis() - j9;
                                                                                                        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                        }
                                                                                                        if (c1246c != null) {
                                                                                                        }
                                                                                                        if (c1248e != null) {
                                                                                                        }
                                                                                                        if (c1249f != null) {
                                                                                                        }
                                                                                                        c1215b = this.f6077f;
                                                                                                        if (c1215b != null) {
                                                                                                        }
                                                                                                        cLMediaCodec = this.f6078g;
                                                                                                        if (cLMediaCodec != null) {
                                                                                                        }
                                                                                                        cLMediaCodec2 = this.f6081j;
                                                                                                        if (cLMediaCodec2 != null) {
                                                                                                        }
                                                                                                        mediaCodec = this.f6085n;
                                                                                                        if (mediaCodec != null) {
                                                                                                        }
                                                                                                        mediaCodec2 = this.f6086o;
                                                                                                        if (mediaCodec2 != null) {
                                                                                                        }
                                                                                                        mediaMuxer = this.f6084m;
                                                                                                        if (mediaMuxer != null) {
                                                                                                        }
                                                                                                        interfaceC1243a = this.f6072I;
                                                                                                        if (interfaceC1243a == null) {
                                                                                                        }
                                                                                                    }
                                                                                                    try {
                                                                                                        Object[] objArr11 = new Object[2];
                                                                                                        objArr11[0] = mediaFormatM5360i.toString();
                                                                                                        z16 = true;
                                                                                                        try {
                                                                                                            objArr11[1] = Boolean.valueOf(surfaceM5558d != null);
                                                                                                            m5508g("config Extra VideoDecoder with format %s, using surface %b", objArr11);
                                                                                                            this.f6078g.m5310f(mediaFormatM5360i, surfaceM5558d, null, 0);
                                                                                                        } catch (Throwable th12) {
                                                                                                            th = th12;
                                                                                                            c1246c = c1246c3;
                                                                                                            c1248e = c1248e3;
                                                                                                            c1249f = c1249f3;
                                                                                                            z13 = z16;
                                                                                                            str13 = "Muxer, failed to stop/release muxer";
                                                                                                            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                            str = str3;
                                                                                                            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                            str6 = "Extractor, failed to stop/release Extractor";
                                                                                                            str8 = " ms.";
                                                                                                            str7 = str14;
                                                                                                            z8 = false;
                                                                                                            z9 = false;
                                                                                                            z10 = false;
                                                                                                            z11 = false;
                                                                                                            z12 = false;
                                                                                                            this.f6065B = z13;
                                                                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                            }
                                                                                                            if (c1246c != null) {
                                                                                                            }
                                                                                                            if (c1248e != null) {
                                                                                                            }
                                                                                                            if (c1249f != null) {
                                                                                                            }
                                                                                                            c1215b = this.f6077f;
                                                                                                            if (c1215b != null) {
                                                                                                            }
                                                                                                            cLMediaCodec = this.f6078g;
                                                                                                            if (cLMediaCodec != null) {
                                                                                                            }
                                                                                                            cLMediaCodec2 = this.f6081j;
                                                                                                            if (cLMediaCodec2 != null) {
                                                                                                            }
                                                                                                            mediaCodec = this.f6085n;
                                                                                                            if (mediaCodec != null) {
                                                                                                            }
                                                                                                            mediaCodec2 = this.f6086o;
                                                                                                            if (mediaCodec2 != null) {
                                                                                                            }
                                                                                                            mediaMuxer = this.f6084m;
                                                                                                            if (mediaMuxer != null) {
                                                                                                            }
                                                                                                            interfaceC1243a = this.f6072I;
                                                                                                            if (interfaceC1243a == null) {
                                                                                                            }
                                                                                                        }
                                                                                                    } catch (Exception e18) {
                                                                                                        throw new MyRuntimeException("VideoDecoder (extra), failed to configure!", TRANSCODER_STATUS.STATUS_ERROR_CONFIGURING_VIDEO_DECODER, e18);
                                                                                                    }
                                                                                                } else {
                                                                                                    c1248e3 = c1248e2;
                                                                                                }
                                                                                                CLMediaCodec cLMediaCodec6 = this.f6078g;
                                                                                                if (cLMediaCodec6 == null || this.f6080i) {
                                                                                                    c1249f2 = c1249f3;
                                                                                                } else {
                                                                                                    MediaFormat mediaFormatM5316o = cLMediaCodec6.m5316o();
                                                                                                    try {
                                                                                                        Object[] objArr12 = new Object[1];
                                                                                                        objArr12[0] = mediaFormatM5316o.toString();
                                                                                                        m5508g("VideoDecoder output format %s", objArr12);
                                                                                                        c1249f2 = new C1249f(mediaFormatM5316o.getInteger("color-format"), mediaFormatM5316o.getInteger("width"), mediaFormatM5316o.getInteger("height"));
                                                                                                    } catch (Throwable th13) {
                                                                                                        th = th13;
                                                                                                        c1246c = c1246c3;
                                                                                                        c1248e = c1248e3;
                                                                                                        z13 = true;
                                                                                                        c1249f = c1249f3;
                                                                                                        str13 = "Muxer, failed to stop/release muxer";
                                                                                                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                                        str = str3;
                                                                                                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                                        str6 = "Extractor, failed to stop/release Extractor";
                                                                                                        str8 = " ms.";
                                                                                                        str7 = str14;
                                                                                                        z8 = false;
                                                                                                        z9 = false;
                                                                                                        z10 = false;
                                                                                                        z11 = false;
                                                                                                        z12 = false;
                                                                                                        this.f6065B = z13;
                                                                                                        this.f6069F = System.currentTimeMillis() - j9;
                                                                                                        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                                        }
                                                                                                        if (c1246c != null) {
                                                                                                        }
                                                                                                        if (c1248e != null) {
                                                                                                        }
                                                                                                        if (c1249f != null) {
                                                                                                        }
                                                                                                        c1215b = this.f6077f;
                                                                                                        if (c1215b != null) {
                                                                                                        }
                                                                                                        cLMediaCodec = this.f6078g;
                                                                                                        if (cLMediaCodec != null) {
                                                                                                        }
                                                                                                        cLMediaCodec2 = this.f6081j;
                                                                                                        if (cLMediaCodec2 != null) {
                                                                                                        }
                                                                                                        mediaCodec = this.f6085n;
                                                                                                        if (mediaCodec != null) {
                                                                                                        }
                                                                                                        mediaCodec2 = this.f6086o;
                                                                                                        if (mediaCodec2 != null) {
                                                                                                        }
                                                                                                        mediaMuxer = this.f6084m;
                                                                                                        if (mediaMuxer != null) {
                                                                                                        }
                                                                                                        interfaceC1243a = this.f6072I;
                                                                                                        if (interfaceC1243a == null) {
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            } catch (Throwable th14) {
                                                                                                th = th14;
                                                                                                c1246c = c1246c3;
                                                                                                c1248e = c1248e4;
                                                                                            }
                                                                                        } catch (Exception e19) {
                                                                                            exc = e19;
                                                                                            c1246c = c1246c3;
                                                                                            c1248e = c1248e4;
                                                                                        }
                                                                                    } catch (Throwable th15) {
                                                                                        th = th15;
                                                                                        c1246c = c1246c3;
                                                                                        c1248e = c1248e4;
                                                                                    }
                                                                                    try {
                                                                                        if (this.f6078g == null) {
                                                                                            break;
                                                                                        }
                                                                                        c1246c2 = c1246c3;
                                                                                        c1248e4 = c1248e3;
                                                                                        c1249f3 = c1249f2;
                                                                                        i11 = i9;
                                                                                        i9++;
                                                                                        str8 = str14;
                                                                                        jCurrentTimeMillis = j9;
                                                                                        str6 = str4;
                                                                                        str9 = str5;
                                                                                    } catch (Exception e20) {
                                                                                        e = e20;
                                                                                        c1246c = c1246c3;
                                                                                        c1248e = c1248e3;
                                                                                        c1249f = c1249f2;
                                                                                        str13 = "Muxer, failed to stop/release muxer";
                                                                                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                        str = str3;
                                                                                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                        str6 = "Extractor, failed to stop/release Extractor";
                                                                                        str8 = " ms.";
                                                                                        str7 = str14;
                                                                                        z8 = false;
                                                                                        z9 = false;
                                                                                        z10 = false;
                                                                                        z11 = false;
                                                                                        z12 = false;
                                                                                        exc = e;
                                                                                        exc.printStackTrace();
                                                                                        if (!(exc instanceof MyRuntimeException)) {
                                                                                        }
                                                                                        this.f6068E = exc.getMessage();
                                                                                        this.f6090s = 0L;
                                                                                        this.f6089r = 0L;
                                                                                        this.f6065B = true;
                                                                                        this.f6069F = System.currentTimeMillis() - j9;
                                                                                        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                        }
                                                                                        if (c1246c != null) {
                                                                                        }
                                                                                        if (c1248e != null) {
                                                                                        }
                                                                                        if (c1249f != null) {
                                                                                        }
                                                                                        c1215b2 = this.f6077f;
                                                                                        if (c1215b2 != null) {
                                                                                        }
                                                                                        cLMediaCodec3 = this.f6078g;
                                                                                        if (cLMediaCodec3 != null) {
                                                                                        }
                                                                                        cLMediaCodec4 = this.f6081j;
                                                                                        if (cLMediaCodec4 != null) {
                                                                                        }
                                                                                        mediaCodec3 = this.f6085n;
                                                                                        if (mediaCodec3 != null) {
                                                                                        }
                                                                                        mediaCodec4 = this.f6086o;
                                                                                        if (mediaCodec4 != null) {
                                                                                        }
                                                                                        mediaMuxer2 = this.f6084m;
                                                                                        if (mediaMuxer2 != null) {
                                                                                        }
                                                                                        interfaceC1243a2 = this.f6072I;
                                                                                        if (interfaceC1243a2 == null) {
                                                                                        }
                                                                                        interfaceC1243a2.mo5529b(this);
                                                                                    } catch (Throwable th16) {
                                                                                        th = th16;
                                                                                        c1246c = c1246c3;
                                                                                        c1248e = c1248e3;
                                                                                        c1249f = c1249f2;
                                                                                        str13 = "Muxer, failed to stop/release muxer";
                                                                                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                        str = str3;
                                                                                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                        str6 = "Extractor, failed to stop/release Extractor";
                                                                                        str8 = " ms.";
                                                                                        str7 = str14;
                                                                                        z13 = true;
                                                                                        z8 = false;
                                                                                        z9 = false;
                                                                                        z10 = false;
                                                                                        z11 = false;
                                                                                        z12 = false;
                                                                                        th = th;
                                                                                        this.f6065B = z13;
                                                                                        this.f6069F = System.currentTimeMillis() - j9;
                                                                                        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                        }
                                                                                        if (c1246c != null) {
                                                                                        }
                                                                                        if (c1248e != null) {
                                                                                        }
                                                                                        if (c1249f != null) {
                                                                                        }
                                                                                        c1215b = this.f6077f;
                                                                                        if (c1215b != null) {
                                                                                        }
                                                                                        cLMediaCodec = this.f6078g;
                                                                                        if (cLMediaCodec != null) {
                                                                                        }
                                                                                        cLMediaCodec2 = this.f6081j;
                                                                                        if (cLMediaCodec2 != null) {
                                                                                        }
                                                                                        mediaCodec = this.f6085n;
                                                                                        if (mediaCodec != null) {
                                                                                        }
                                                                                        mediaCodec2 = this.f6086o;
                                                                                        if (mediaCodec2 != null) {
                                                                                        }
                                                                                        mediaMuxer = this.f6084m;
                                                                                        if (mediaMuxer != null) {
                                                                                        }
                                                                                        interfaceC1243a = this.f6072I;
                                                                                        if (interfaceC1243a == null) {
                                                                                        }
                                                                                    }
                                                                                } catch (Exception e21) {
                                                                                    throw new MyRuntimeException("VideoEncoder, failed to configure!", TRANSCODER_STATUS.STATUS_ERROR_CONFIGURING_VIDEO_ENCODER, e21);
                                                                                }
                                                                            } catch (Throwable th17) {
                                                                                th = th17;
                                                                                c1248e = c1248e4;
                                                                                c1249f = c1249f3;
                                                                                c1246c = c1246c2;
                                                                                z13 = true;
                                                                            }
                                                                        }
                                                                        z15 = true;
                                                                        if (!z14) {
                                                                            MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                                                                            uri = this.f6075d;
                                                                            if (uri == null) {
                                                                            }
                                                                            if (z14) {
                                                                                integer2 = Integer.parseInt(strExtractMetadata2);
                                                                                Object[] objArr52 = new Object[1];
                                                                                objArr52[0] = Integer.valueOf(integer2);
                                                                                m5508g("Video orientation (by MediaMetadataRetriever) %d", objArr52);
                                                                            }
                                                                            if (z15) {
                                                                                float f102 = Float.parseFloat(strExtractMetadata);
                                                                                this.f6091t = f102;
                                                                                Object[] objArr62 = new Object[1];
                                                                                objArr62[0] = Float.valueOf(f102);
                                                                                m5508g("Video framerate (by MediaMetadataRetriever) %f", objArr62);
                                                                            }
                                                                            mediaMetadataRetriever2.release();
                                                                            MediaCodec mediaCodecCreateEncoderByType2 = MediaCodec.createEncoderByType(MimeTypes.VIDEO_H264);
                                                                            this.f6085n = mediaCodecCreateEncoderByType2;
                                                                            Object[] objArr72 = new Object[1];
                                                                            objArr72[0] = mediaCodecCreateEncoderByType2.getName();
                                                                            m5508g("Using videoEncoder: %s", objArr72);
                                                                            if (this.f6085n != null) {
                                                                            }
                                                                        }
                                                                    }
                                                                    z14 = false;
                                                                    if (mediaFormatM5360i.containsKey("frame-rate")) {
                                                                    }
                                                                    z15 = true;
                                                                    if (!z14) {
                                                                    }
                                                                } else {
                                                                    str4 = str6;
                                                                    str5 = str9;
                                                                    int i13 = integer2;
                                                                    if (string.startsWith("audio/")) {
                                                                        Object[] objArr13 = new Object[2];
                                                                        objArr13[0] = Integer.valueOf(i9);
                                                                        try {
                                                                            objArr13[1] = mediaFormatM5360i;
                                                                            m5508g("Extractor, Audio track %d, format %s", objArr13);
                                                                            this.f6077f.m5363m(i9);
                                                                            this.f6087p = mediaFormatM5360i.getLong("durationUs");
                                                                            try {
                                                                                CLMediaCodec cLMediaCodecM5304g3 = CLMediaCodec.m5304g(string, true);
                                                                                this.f6081j = cLMediaCodecM5304g3;
                                                                                Object[] objArr14 = new Object[1];
                                                                                objArr14[0] = cLMediaCodecM5304g3.m5314m();
                                                                                m5508g("Using audioDecoder: %s", objArr14);
                                                                                if (this.f6081j == null) {
                                                                                    throw new MyRuntimeException("Cannot create audioDecoder!", TRANSCODER_STATUS.STATUS_ERROR_CREATING_AUDIO_DECODER);
                                                                                }
                                                                                try {
                                                                                    try {
                                                                                        Object[] objArr15 = new Object[1];
                                                                                        objArr15[0] = mediaFormatM5360i.toString();
                                                                                        m5508g("config AudioDecoder with format: %s", objArr15);
                                                                                        this.f6081j.m5310f(mediaFormatM5360i, null, null, 0);
                                                                                        i10 = i9;
                                                                                    } catch (Throwable th18) {
                                                                                        th = th18;
                                                                                        z13 = true;
                                                                                        c1248e = c1248e4;
                                                                                        c1249f = c1249f3;
                                                                                        c1246c = c1246c2;
                                                                                        str13 = "Muxer, failed to stop/release muxer";
                                                                                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                                        str = str3;
                                                                                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                                        str6 = "Extractor, failed to stop/release Extractor";
                                                                                        str8 = " ms.";
                                                                                        str7 = str14;
                                                                                        z8 = false;
                                                                                        z9 = false;
                                                                                        z10 = false;
                                                                                        z11 = false;
                                                                                        z12 = false;
                                                                                        this.f6065B = z13;
                                                                                        this.f6069F = System.currentTimeMillis() - j9;
                                                                                        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                                        }
                                                                                        if (c1246c != null) {
                                                                                        }
                                                                                        if (c1248e != null) {
                                                                                        }
                                                                                        if (c1249f != null) {
                                                                                        }
                                                                                        c1215b = this.f6077f;
                                                                                        if (c1215b != null) {
                                                                                        }
                                                                                        cLMediaCodec = this.f6078g;
                                                                                        if (cLMediaCodec != null) {
                                                                                        }
                                                                                        cLMediaCodec2 = this.f6081j;
                                                                                        if (cLMediaCodec2 != null) {
                                                                                        }
                                                                                        mediaCodec = this.f6085n;
                                                                                        if (mediaCodec != null) {
                                                                                        }
                                                                                        mediaCodec2 = this.f6086o;
                                                                                        if (mediaCodec2 != null) {
                                                                                        }
                                                                                        mediaMuxer = this.f6084m;
                                                                                        if (mediaMuxer != null) {
                                                                                        }
                                                                                        interfaceC1243a = this.f6072I;
                                                                                        if (interfaceC1243a == null) {
                                                                                        }
                                                                                    }
                                                                                } catch (Exception e22) {
                                                                                    throw new MyRuntimeException("AudioDecoder, failed to configure!", TRANSCODER_STATUS.STATUS_ERROR_CONFIGURING_AUDIO_DECODER, e22);
                                                                                }
                                                                            } catch (Throwable th19) {
                                                                                th = th19;
                                                                                z13 = true;
                                                                            }
                                                                        } catch (Throwable th20) {
                                                                            th = th20;
                                                                            c1248e = c1248e4;
                                                                            c1249f = c1249f3;
                                                                            c1246c = c1246c2;
                                                                            z13 = true;
                                                                        }
                                                                    } else {
                                                                        Object[] objArr16 = new Object[2];
                                                                        objArr16[0] = Integer.valueOf(i9);
                                                                        try {
                                                                            objArr16[1] = string;
                                                                            m5509h("Extractor, Unknown track %d: %s", objArr16);
                                                                        } catch (Throwable th21) {
                                                                            th = th21;
                                                                            z13 = true;
                                                                            c1248e = c1248e4;
                                                                            c1249f = c1249f3;
                                                                            c1246c = c1246c2;
                                                                            str13 = "Muxer, failed to stop/release muxer";
                                                                            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                            str = str3;
                                                                            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                            str6 = "Extractor, failed to stop/release Extractor";
                                                                            str8 = " ms.";
                                                                            str7 = str14;
                                                                            z8 = false;
                                                                            z9 = false;
                                                                            z10 = false;
                                                                            z11 = false;
                                                                            z12 = false;
                                                                            th = th;
                                                                            this.f6065B = z13;
                                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                            }
                                                                            if (c1246c != null) {
                                                                            }
                                                                            if (c1248e != null) {
                                                                            }
                                                                            if (c1249f != null) {
                                                                            }
                                                                            c1215b = this.f6077f;
                                                                            if (c1215b != null) {
                                                                            }
                                                                            cLMediaCodec = this.f6078g;
                                                                            if (cLMediaCodec != null) {
                                                                            }
                                                                            cLMediaCodec2 = this.f6081j;
                                                                            if (cLMediaCodec2 != null) {
                                                                            }
                                                                            mediaCodec = this.f6085n;
                                                                            if (mediaCodec != null) {
                                                                            }
                                                                            mediaCodec2 = this.f6086o;
                                                                            if (mediaCodec2 != null) {
                                                                            }
                                                                            mediaMuxer = this.f6084m;
                                                                            if (mediaMuxer != null) {
                                                                            }
                                                                            interfaceC1243a = this.f6072I;
                                                                            if (interfaceC1243a == null) {
                                                                            }
                                                                        }
                                                                    }
                                                                    integer2 = i13;
                                                                    i9++;
                                                                    str8 = str14;
                                                                    jCurrentTimeMillis = j9;
                                                                    str6 = str4;
                                                                    str9 = str5;
                                                                }
                                                            } catch (Throwable th22) {
                                                                th = th22;
                                                                th = th;
                                                                c1248e = c1248e4;
                                                                c1249f = c1249f3;
                                                                c1246c = c1246c2;
                                                                str13 = "Muxer, failed to stop/release muxer";
                                                                str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                                str = str3;
                                                                str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                                str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                                str6 = "Extractor, failed to stop/release Extractor";
                                                                str8 = " ms.";
                                                                str7 = str14;
                                                                z13 = true;
                                                                z8 = false;
                                                                z9 = false;
                                                                z10 = false;
                                                                z11 = false;
                                                                z12 = false;
                                                                this.f6065B = z13;
                                                                this.f6069F = System.currentTimeMillis() - j9;
                                                                if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                                }
                                                                if (c1246c != null) {
                                                                }
                                                                if (c1248e != null) {
                                                                }
                                                                if (c1249f != null) {
                                                                }
                                                                c1215b = this.f6077f;
                                                                if (c1215b != null) {
                                                                }
                                                                cLMediaCodec = this.f6078g;
                                                                if (cLMediaCodec != null) {
                                                                }
                                                                cLMediaCodec2 = this.f6081j;
                                                                if (cLMediaCodec2 != null) {
                                                                }
                                                                mediaCodec = this.f6085n;
                                                                if (mediaCodec != null) {
                                                                }
                                                                mediaCodec2 = this.f6086o;
                                                                if (mediaCodec2 != null) {
                                                                }
                                                                mediaMuxer = this.f6084m;
                                                                if (mediaMuxer != null) {
                                                                }
                                                                interfaceC1243a = this.f6072I;
                                                                if (interfaceC1243a == null) {
                                                                }
                                                            }
                                                        } catch (Exception e23) {
                                                            e = e23;
                                                            exc = e;
                                                            c1248e = c1248e4;
                                                            c1249f = c1249f3;
                                                            c1246c = c1246c2;
                                                            str13 = "Muxer, failed to stop/release muxer";
                                                            str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                            str = str3;
                                                            str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                            str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                            str6 = "Extractor, failed to stop/release Extractor";
                                                            str8 = " ms.";
                                                            str7 = str14;
                                                            z8 = false;
                                                            z9 = false;
                                                            z10 = false;
                                                            z11 = false;
                                                            z12 = false;
                                                            exc.printStackTrace();
                                                            if (!(exc instanceof MyRuntimeException)) {
                                                            }
                                                            this.f6068E = exc.getMessage();
                                                            this.f6090s = 0L;
                                                            this.f6089r = 0L;
                                                            this.f6065B = true;
                                                            this.f6069F = System.currentTimeMillis() - j9;
                                                            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                                            }
                                                            if (c1246c != null) {
                                                            }
                                                            if (c1248e != null) {
                                                            }
                                                            if (c1249f != null) {
                                                            }
                                                            c1215b2 = this.f6077f;
                                                            if (c1215b2 != null) {
                                                            }
                                                            cLMediaCodec3 = this.f6078g;
                                                            if (cLMediaCodec3 != null) {
                                                            }
                                                            cLMediaCodec4 = this.f6081j;
                                                            if (cLMediaCodec4 != null) {
                                                            }
                                                            mediaCodec3 = this.f6085n;
                                                            if (mediaCodec3 != null) {
                                                            }
                                                            mediaCodec4 = this.f6086o;
                                                            if (mediaCodec4 != null) {
                                                            }
                                                            mediaMuxer2 = this.f6084m;
                                                            if (mediaMuxer2 != null) {
                                                            }
                                                            interfaceC1243a2 = this.f6072I;
                                                            if (interfaceC1243a2 == null) {
                                                            }
                                                            interfaceC1243a2.mo5529b(this);
                                                        }
                                                    } catch (Exception e24) {
                                                        e = e24;
                                                        j9 = jCurrentTimeMillis;
                                                    } catch (Throwable th23) {
                                                        th = th23;
                                                        j9 = jCurrentTimeMillis;
                                                    }
                                                } catch (Exception e25) {
                                                    e = e25;
                                                    str7 = str8;
                                                    c1248e = c1248e4;
                                                    c1249f = c1249f3;
                                                    C1246c c1246c4 = c1246c2;
                                                    j9 = jCurrentTimeMillis;
                                                    str13 = "Muxer, failed to stop/release muxer";
                                                    str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                    str = str3;
                                                    str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                    str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                    str6 = "Extractor, failed to stop/release Extractor";
                                                    str8 = " ms.";
                                                } catch (Throwable th24) {
                                                    th = th24;
                                                    str7 = str8;
                                                    c1248e = c1248e4;
                                                    c1249f = c1249f3;
                                                    C1246c c1246c5 = c1246c2;
                                                    j9 = jCurrentTimeMillis;
                                                    str13 = "Muxer, failed to stop/release muxer";
                                                    str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                                    str = str3;
                                                    str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                                    str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                                    str6 = "Extractor, failed to stop/release Extractor";
                                                    str8 = " ms.";
                                                }
                                            }
                                        } catch (Exception e26) {
                                            throw new MyRuntimeException("Cannnot create muxer! (output file: " + this.f6082k + str2, TRANSCODER_STATUS.STATUS_ERROR_CREATING_MUXER, e26);
                                        }
                                    } catch (Throwable th25) {
                                        th = th25;
                                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                                        str6 = "Extractor, failed to stop/release Extractor";
                                        j9 = jCurrentTimeMillis;
                                        str13 = "Muxer, failed to stop/release muxer";
                                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                                        str = str3;
                                        z13 = true;
                                        z8 = false;
                                        z9 = false;
                                        z10 = false;
                                        c1249f = null;
                                        z11 = false;
                                        z12 = false;
                                        c1246c = null;
                                        c1248e = null;
                                        str8 = " ms.";
                                        str7 = " Spent ";
                                        this.f6065B = z13;
                                        this.f6069F = System.currentTimeMillis() - j9;
                                        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                                        }
                                        if (c1246c != null) {
                                        }
                                        if (c1248e != null) {
                                        }
                                        if (c1249f != null) {
                                        }
                                        c1215b = this.f6077f;
                                        if (c1215b != null) {
                                        }
                                        cLMediaCodec = this.f6078g;
                                        if (cLMediaCodec != null) {
                                        }
                                        cLMediaCodec2 = this.f6081j;
                                        if (cLMediaCodec2 != null) {
                                        }
                                        mediaCodec = this.f6085n;
                                        if (mediaCodec != null) {
                                        }
                                        mediaCodec2 = this.f6086o;
                                        if (mediaCodec2 != null) {
                                        }
                                        mediaMuxer = this.f6084m;
                                        if (mediaMuxer != null) {
                                        }
                                        interfaceC1243a = this.f6072I;
                                        if (interfaceC1243a == null) {
                                        }
                                    }
                                }
                            }
                            throw new MyRuntimeException("Input/Output files are not set!", TRANSCODER_STATUS.STATUS_ERROR_INVALID_INPUT);
                        }
                    } catch (Throwable th26) {
                        th = th26;
                        str = "VideoEncoder, failed to stop/release VideoEnocder";
                        str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                        str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                        str6 = "Extractor, failed to stop/release Extractor";
                        j9 = jCurrentTimeMillis;
                        str13 = "Muxer, failed to stop/release muxer";
                        str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                        z13 = true;
                        z8 = false;
                        z9 = false;
                        z10 = false;
                        c1249f = null;
                        z11 = false;
                        z12 = false;
                        c1246c = null;
                        c1248e = null;
                        str8 = " ms.";
                        str7 = " Spent ";
                        this.f6065B = z13;
                        this.f6069F = System.currentTimeMillis() - j9;
                        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                        }
                        if (c1246c != null) {
                        }
                        if (c1248e != null) {
                        }
                        if (c1249f != null) {
                        }
                        c1215b = this.f6077f;
                        if (c1215b != null) {
                        }
                        cLMediaCodec = this.f6078g;
                        if (cLMediaCodec != null) {
                        }
                        cLMediaCodec2 = this.f6081j;
                        if (cLMediaCodec2 != null) {
                        }
                        mediaCodec = this.f6085n;
                        if (mediaCodec != null) {
                        }
                        mediaCodec2 = this.f6086o;
                        if (mediaCodec2 != null) {
                        }
                        mediaMuxer = this.f6084m;
                        if (mediaMuxer != null) {
                        }
                        interfaceC1243a = this.f6072I;
                        if (interfaceC1243a == null) {
                        }
                    }
                } catch (Exception e27) {
                    exc = e27;
                    str = "VideoEncoder, failed to stop/release VideoEnocder";
                    str11 = "AudioDecoder, failed to stop/release AudioDecoder";
                    str10 = "VideoDecoder, failed to stop/release VideoDecoder";
                    str6 = "Extractor, failed to stop/release Extractor";
                    j9 = jCurrentTimeMillis;
                    str13 = "Muxer, failed to stop/release muxer";
                    str12 = "AudioEncoder, failed to stop/release AudioEnocder";
                    z8 = false;
                    z9 = false;
                    z10 = false;
                    c1249f = null;
                    z11 = false;
                    z12 = false;
                    c1246c = null;
                    c1248e = null;
                    str8 = " ms.";
                    str7 = " Spent ";
                    exc.printStackTrace();
                    if (!(exc instanceof MyRuntimeException)) {
                    }
                    this.f6068E = exc.getMessage();
                    this.f6090s = 0L;
                    this.f6089r = 0L;
                    this.f6065B = true;
                    this.f6069F = System.currentTimeMillis() - j9;
                    if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
                    }
                    if (c1246c != null) {
                    }
                    if (c1248e != null) {
                    }
                    if (c1249f != null) {
                    }
                    c1215b2 = this.f6077f;
                    if (c1215b2 != null) {
                    }
                    cLMediaCodec3 = this.f6078g;
                    if (cLMediaCodec3 != null) {
                    }
                    cLMediaCodec4 = this.f6081j;
                    if (cLMediaCodec4 != null) {
                    }
                    mediaCodec3 = this.f6085n;
                    if (mediaCodec3 != null) {
                    }
                    mediaCodec4 = this.f6086o;
                    if (mediaCodec4 != null) {
                    }
                    mediaMuxer2 = this.f6084m;
                    if (mediaMuxer2 != null) {
                    }
                    interfaceC1243a2 = this.f6072I;
                    if (interfaceC1243a2 == null) {
                    }
                    interfaceC1243a2.mo5529b(this);
                }
            }
        } catch (Exception e28) {
            e = e28;
            exc = e;
            z8 = false;
            z9 = false;
            z10 = false;
            c1249f = null;
            z11 = false;
            z12 = false;
            c1246c = null;
            c1248e = null;
            exc.printStackTrace();
            if (!(exc instanceof MyRuntimeException)) {
            }
            this.f6068E = exc.getMessage();
            this.f6090s = 0L;
            this.f6089r = 0L;
            this.f6065B = true;
            this.f6069F = System.currentTimeMillis() - j9;
            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
            }
            if (c1246c != null) {
            }
            if (c1248e != null) {
            }
            if (c1249f != null) {
            }
            c1215b2 = this.f6077f;
            if (c1215b2 != null) {
            }
            cLMediaCodec3 = this.f6078g;
            if (cLMediaCodec3 != null) {
            }
            cLMediaCodec4 = this.f6081j;
            if (cLMediaCodec4 != null) {
            }
            mediaCodec3 = this.f6085n;
            if (mediaCodec3 != null) {
            }
            mediaCodec4 = this.f6086o;
            if (mediaCodec4 != null) {
            }
            mediaMuxer2 = this.f6084m;
            if (mediaMuxer2 != null) {
            }
            interfaceC1243a2 = this.f6072I;
            if (interfaceC1243a2 == null) {
            }
            interfaceC1243a2.mo5529b(this);
        } catch (Throwable th27) {
            th = th27;
            th = th;
            z13 = true;
            z8 = false;
            z9 = false;
            z10 = false;
            c1249f = null;
            z11 = false;
            z12 = false;
            c1246c = null;
            c1248e = null;
            this.f6065B = z13;
            this.f6069F = System.currentTimeMillis() - j9;
            if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
            }
            if (c1246c != null) {
            }
            if (c1248e != null) {
            }
            if (c1249f != null) {
            }
            c1215b = this.f6077f;
            if (c1215b != null) {
            }
            cLMediaCodec = this.f6078g;
            if (cLMediaCodec != null) {
            }
            cLMediaCodec2 = this.f6081j;
            if (cLMediaCodec2 != null) {
            }
            mediaCodec = this.f6085n;
            if (mediaCodec != null) {
            }
            mediaCodec2 = this.f6086o;
            if (mediaCodec2 != null) {
            }
            mediaMuxer = this.f6084m;
            if (mediaMuxer != null) {
            }
            interfaceC1243a = this.f6072I;
            if (interfaceC1243a == null) {
            }
        }
        this.f6065B = z13;
        this.f6069F = System.currentTimeMillis() - j9;
        if (TRANSCODER_STATUS.STATUS_SUCCESS == this.f6067D) {
            String str16 = this.f6068E + str7 + this.f6069F + str8;
            this.f6068E = str16;
            m5508g(str16, new Object[0]);
        }
        if (c1246c != null) {
            c1246c.m5544f();
        }
        if (c1248e != null) {
            c1248e.m5559e();
        }
        if (c1249f != null) {
            c1249f.m5571k();
        }
        c1215b = this.f6077f;
        if (c1215b != null) {
            try {
                c1215b.m5362l();
            } catch (Exception unused4) {
                m5506e(str6, new Object[0]);
                this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_EXTRACTOR;
                this.f6068E = str6;
            }
            this.f6077f = null;
        }
        cLMediaCodec = this.f6078g;
        if (cLMediaCodec != null) {
            if (z9) {
                try {
                    cLMediaCodec.m5321v();
                } catch (Exception unused5) {
                    m5506e(str10, new Object[0]);
                    this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_VIDEO_DECODER;
                    this.f6068E = str10;
                }
            }
            this.f6078g.m5318q();
            this.f6078g = null;
        }
        cLMediaCodec2 = this.f6081j;
        if (cLMediaCodec2 != null) {
            if (z10) {
                try {
                    cLMediaCodec2.m5321v();
                } catch (Exception unused6) {
                    m5506e(str11, new Object[0]);
                    this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_AUDIO_DECODER;
                    this.f6068E = str11;
                }
            }
            this.f6081j.m5318q();
            this.f6081j = null;
        }
        mediaCodec = this.f6085n;
        if (mediaCodec != null) {
            if (z8) {
                try {
                    mediaCodec.stop();
                } catch (Exception unused7) {
                    m5506e(str, new Object[0]);
                    this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_VIDEO_ENCODER;
                    this.f6068E = str;
                }
            }
            this.f6085n.release();
            this.f6085n = null;
        }
        mediaCodec2 = this.f6086o;
        if (mediaCodec2 != null) {
            if (z11) {
                try {
                    mediaCodec2.stop();
                } catch (Exception unused8) {
                    m5506e(str12, new Object[0]);
                    this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_AUDIO_ENCODER;
                    this.f6068E = str12;
                }
            }
            this.f6086o.release();
            this.f6086o = null;
        }
        mediaMuxer = this.f6084m;
        if (mediaMuxer != null) {
            if (z12) {
                try {
                    mediaMuxer.stop();
                } catch (Exception unused9) {
                    m5506e(str13, new Object[0]);
                    this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_MUXER;
                    this.f6068E = str13;
                }
            }
            this.f6084m.release();
            this.f6084m = null;
        }
        interfaceC1243a = this.f6072I;
        if (interfaceC1243a == null) {
            throw th;
        }
        interfaceC1243a.mo5529b(this);
        throw th;
        this.f6077f = null;
        CLMediaCodec cLMediaCodec7 = this.f6078g;
        if (cLMediaCodec7 != null) {
            if (z9) {
                try {
                    cLMediaCodec7.m5321v();
                } catch (Exception unused10) {
                    m5506e("VideoDecoder, failed to stop/release VideoDecoder", new Object[0]);
                    this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_VIDEO_DECODER;
                    this.f6068E = "VideoDecoder, failed to stop/release VideoDecoder";
                    this.f6078g = null;
                    cLMediaCodec5 = this.f6081j;
                    if (cLMediaCodec5 != null) {
                        if (z10) {
                            try {
                                cLMediaCodec5.m5321v();
                            } catch (Exception unused11) {
                                m5506e("AudioDecoder, failed to stop/release AudioDecoder", new Object[0]);
                                this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_AUDIO_DECODER;
                                this.f6068E = "AudioDecoder, failed to stop/release AudioDecoder";
                                this.f6081j = null;
                                mediaCodec5 = this.f6085n;
                                if (mediaCodec5 != null) {
                                }
                                mediaCodec6 = this.f6086o;
                                if (mediaCodec6 != null) {
                                }
                                mediaMuxer3 = this.f6084m;
                                if (mediaMuxer3 != null) {
                                }
                                interfaceC1243a2 = this.f6072I;
                                if (interfaceC1243a2 == null) {
                                }
                                interfaceC1243a2.mo5529b(this);
                            }
                        }
                        this.f6081j.m5318q();
                        this.f6081j = null;
                    }
                    mediaCodec5 = this.f6085n;
                    if (mediaCodec5 != null) {
                        if (z8) {
                            try {
                                mediaCodec5.stop();
                            } catch (Exception unused12) {
                                String str17 = str3;
                                m5506e(str17, new Object[0]);
                                this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_VIDEO_ENCODER;
                                this.f6068E = str17;
                                this.f6085n = null;
                                mediaCodec6 = this.f6086o;
                                if (mediaCodec6 != null) {
                                }
                                mediaMuxer3 = this.f6084m;
                                if (mediaMuxer3 != null) {
                                }
                                interfaceC1243a2 = this.f6072I;
                                if (interfaceC1243a2 == null) {
                                }
                                interfaceC1243a2.mo5529b(this);
                            }
                        }
                        this.f6085n.release();
                        this.f6085n = null;
                    }
                    mediaCodec6 = this.f6086o;
                    if (mediaCodec6 != null) {
                        if (z11) {
                            try {
                                mediaCodec6.stop();
                            } catch (Exception unused13) {
                                m5506e("AudioEncoder, failed to stop/release AudioEnocder", new Object[0]);
                                this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_AUDIO_ENCODER;
                                this.f6068E = "AudioEncoder, failed to stop/release AudioEnocder";
                                this.f6086o = null;
                                mediaMuxer3 = this.f6084m;
                                if (mediaMuxer3 != null) {
                                }
                                interfaceC1243a2 = this.f6072I;
                                if (interfaceC1243a2 == null) {
                                }
                                interfaceC1243a2.mo5529b(this);
                            }
                        }
                        this.f6086o.release();
                        this.f6086o = null;
                    }
                    mediaMuxer3 = this.f6084m;
                    if (mediaMuxer3 != null) {
                        if (z12) {
                            try {
                                mediaMuxer3.stop();
                            } catch (Exception unused14) {
                                m5506e("Muxer, failed to stop/release muxer", new Object[0]);
                                this.f6067D = TRANSCODER_STATUS.STATUS_ERROR_RELEASING_MUXER;
                                this.f6068E = "Muxer, failed to stop/release muxer";
                                this.f6084m = null;
                                interfaceC1243a2 = this.f6072I;
                                if (interfaceC1243a2 == null) {
                                }
                                interfaceC1243a2.mo5529b(this);
                            }
                        }
                        this.f6084m.release();
                        this.f6084m = null;
                    }
                    interfaceC1243a2 = this.f6072I;
                    if (interfaceC1243a2 == null) {
                        return;
                    }
                    interfaceC1243a2.mo5529b(this);
                }
            }
            this.f6078g.m5318q();
            this.f6078g = null;
        }
        cLMediaCodec5 = this.f6081j;
        if (cLMediaCodec5 != null) {
        }
        mediaCodec5 = this.f6085n;
        if (mediaCodec5 != null) {
        }
        mediaCodec6 = this.f6086o;
        if (mediaCodec6 != null) {
        }
        mediaMuxer3 = this.f6084m;
        if (mediaMuxer3 != null) {
        }
        interfaceC1243a2 = this.f6072I;
        if (interfaceC1243a2 == null) {
        }
        interfaceC1243a2.mo5529b(this);
    }

    /* renamed from: s */
    public void m5520s(Context context) {
        this.f6076e = context;
    }

    /* renamed from: t */
    public void m5521t(AssetFileDescriptor assetFileDescriptor) {
        this.f6064A = assetFileDescriptor;
    }

    /* renamed from: u */
    public void m5522u(String str) {
        this.f6082k = str;
    }

    /* renamed from: v */
    public void m5523v(String str) {
        this.f6074c = str;
    }

    /* renamed from: w */
    public void m5524w(Uri uri) {
        this.f6075d = uri;
    }

    /* renamed from: x */
    public void m5525x(int i9, int i10) {
        this.f6095x = i9;
        this.f6096y = i10;
    }

    /* renamed from: y */
    public void m5526y(InterfaceC1243a interfaceC1243a) {
        this.f6072I = interfaceC1243a;
    }

    public class MyRuntimeException extends RuntimeException {
        private static final long serialVersionUID = -7937496186704722188L;
        private TRANSCODER_STATUS mStatus;

        public MyRuntimeException(String str, TRANSCODER_STATUS transcoder_status) {
            super(str);
            Transcoder.m5506e(str, new Object[0]);
            this.mStatus = transcoder_status;
        }

        /* renamed from: a */
        public TRANSCODER_STATUS m5527a() {
            return this.mStatus;
        }

        public MyRuntimeException(String str, TRANSCODER_STATUS transcoder_status, Exception exc) {
            super(str + " {" + exc.getMessage() + "}");
            Transcoder.m5506e(str, new Object[0]);
            Transcoder.m5506e("Extra information: %s", exc.getMessage());
            this.mStatus = transcoder_status;
        }
    }
}
