package com.cyberlink.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build;
import com.cyberlink.media.C1215b;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import p064f2.C4778a;
import p104j2.C5092c;
import p120k8.InterfaceC5203a;

@TargetApi(16)
/* loaded from: classes.dex */
final class CLMediaExtractorExtra implements C1215b.b {

    @InterfaceC5203a
    private long mNativeContext;

    static {
        C5092c.m19924a();
        init();
    }

    public CLMediaExtractorExtra() {
        setup();
    }

    /* renamed from: f */
    public static boolean m5336f(String str) {
        if (C5092c.m19926c()) {
            return "ts".equals(str) || "m2ts".equals(str) || "mpg".equals(str) || "mpeg".equals(str) || "m2p".equals(str) || "ps".equals(str) || "mov".equals(str) || "qt".equals(str) || "rm".equals(str) || "rmvb".equals(str) || "ra".equals(str) || "ram".equals(str) || "flv".equals(str) || "m4a".equals(str) || "ape".equals(str);
        }
        return false;
    }

    /* renamed from: g */
    public static boolean m5337g(C1217d c1217d) {
        return !(c1217d.f5884a == null && c1217d.f5886c == null) && C4778a.m19010b(c1217d.m5366a("CL-Content-Type"), c1217d.m5368d()) && C4778a.m19011c(c1217d.m5366a("CL-PNCODE"), c1217d.m5368d());
    }

    /* renamed from: h */
    public static boolean m5338h(String str) {
        return "mkv".equals(str) ? m5339i("video/x-matroska") : "avi".equals(str) ? m5339i("video/avi") : m5336f(str);
    }

    /* renamed from: i */
    public static boolean m5339i(String str) {
        if ("text/3gpp-tt".equals(str) || "video/x-matroska".equals(str)) {
            return true;
        }
        if (!"video/avi".equals(str)) {
            return false;
        }
        if (C5092c.m19926c()) {
            return true;
        }
        String str2 = Build.DEVICE;
        if ("LIFETAB_E10310".equals(str2)) {
            return false;
        }
        String str3 = Build.MODEL;
        return ("LIFETAB_E10310".equals(str3) || "LIFETAB_E10312".equals(str2) || "LIFETAB_E10312".equals(str3) || "LIFETAB_E7310".equals(str2) || "LIFETAB_E7310".equals(str3) || "LIFETAB_E7312".equals(str2) || "LIFETAB_E7312".equals(str3)) ? false : true;
    }

    private static final native void init();

    private native Map<String, Object> nGetTrackFormat(int i9);

    private native void setDataSource(String str, String[] strArr, String[] strArr2);

    private native void setDataSourceFd(FileDescriptor fileDescriptor, long j9, long j10);

    private final native void setup();

    @Override // com.cyberlink.media.C1215b.a
    /* renamed from: a */
    public void mo5340a(String str, Map<String, String> map) {
        String[] strArr;
        String[] strArr2;
        if (map != null) {
            strArr = new String[map.size()];
            strArr2 = new String[map.size()];
            int i9 = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                strArr[i9] = entry.getKey();
                strArr2[i9] = entry.getValue();
                i9++;
            }
        } else {
            strArr = null;
            strArr2 = null;
        }
        setDataSource(str, strArr, strArr2);
    }

    @Override // com.cyberlink.media.C1215b.b
    public native boolean advance();

    @Override // com.cyberlink.media.C1215b.b
    /* renamed from: b */
    public MediaFormat mo5341b(int i9) {
        MediaFormat mediaFormat = new MediaFormat();
        for (Map.Entry<String, Object> entry : nGetTrackFormat(i9).entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                mediaFormat.setInteger(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                mediaFormat.setLong(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                mediaFormat.setFloat(key, ((Float) value).floatValue());
            } else if (value instanceof String) {
                mediaFormat.setString(key, (String) value);
            } else if (value instanceof ByteBuffer) {
                mediaFormat.setByteBuffer(key, (ByteBuffer) value);
            }
        }
        return CLMediaFormat.m5346a(mediaFormat);
    }

    @Override // com.cyberlink.media.C1215b.a
    /* renamed from: c */
    public void mo5342c(FileDescriptor fileDescriptor, long j9, long j10) {
        setDataSourceFd(fileDescriptor, j9, j10);
    }

    @Override // com.cyberlink.media.C1215b.a
    /* renamed from: d */
    public void mo5343d(String str) {
        setDataSource(str, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    @Override // com.cyberlink.media.C1215b.a
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo5344e(Context context, Uri uri, Map<String, String> map) throws IOException {
        String scheme = uri.getScheme();
        if (scheme == null || scheme.equals("file")) {
            mo5340a(uri.getPath(), map);
            return;
        }
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            AssetFileDescriptor assetFileDescriptorOpenAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
            if (assetFileDescriptorOpenAssetFileDescriptor == null) {
                if (assetFileDescriptorOpenAssetFileDescriptor != null) {
                    assetFileDescriptorOpenAssetFileDescriptor.close();
                }
            } else {
                if (assetFileDescriptorOpenAssetFileDescriptor.getDeclaredLength() < 0) {
                    m5345j(assetFileDescriptorOpenAssetFileDescriptor.getFileDescriptor());
                } else {
                    mo5342c(assetFileDescriptorOpenAssetFileDescriptor.getFileDescriptor(), assetFileDescriptorOpenAssetFileDescriptor.getStartOffset(), assetFileDescriptorOpenAssetFileDescriptor.getDeclaredLength());
                }
                assetFileDescriptorOpenAssetFileDescriptor.close();
            }
        } catch (IOException unused) {
            if (0 != 0) {
                assetFileDescriptor.close();
            }
            mo5340a(uri.toString(), map);
        } catch (SecurityException unused2) {
            if (0 != 0) {
            }
            mo5340a(uri.toString(), map);
        } catch (Throwable th) {
            if (0 != 0) {
                assetFileDescriptor.close();
            }
            throw th;
        }
    }

    public void finalize() throws Throwable {
        try {
            release();
        } finally {
            super.finalize();
        }
    }

    @Override // com.cyberlink.media.C1215b.b
    public native int getSampleFlags();

    @Override // com.cyberlink.media.C1215b.b
    public native long getSampleTime();

    @Override // com.cyberlink.media.C1215b.b
    public native int getSampleTrackIndex();

    @Override // com.cyberlink.media.C1215b.b
    public native int getTrackCount();

    /* renamed from: j */
    public void m5345j(FileDescriptor fileDescriptor) {
        mo5342c(fileDescriptor, 0L, 576460752303423487L);
    }

    @Override // com.cyberlink.media.C1215b.b
    public native int readSampleData(ByteBuffer byteBuffer, int i9);

    @Override // com.cyberlink.media.C1215b.b
    public native void release();

    @Override // com.cyberlink.media.C1215b.b
    public native void selectTrack(int i9);
}
