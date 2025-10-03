package com.cyberlink.you.feedback;

import android.webkit.MimeTypeMap;
import com.cyberlink.you.feedback.C3028a;
import com.google.common.primitives.UnsignedBytes;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import p209u2.C6370g;

/* renamed from: com.cyberlink.you.feedback.e */
/* loaded from: classes.dex */
public class C3032e {

    /* renamed from: com.cyberlink.you.feedback.e$a */
    public static class a {

        /* renamed from: a */
        public final String f13492a;

        /* renamed from: b */
        public final long f13493b;

        /* renamed from: c */
        public final String f13494c;

        /* renamed from: d */
        public final String f13495d;

        /* renamed from: e */
        public final byte[] f13496e;

        /* renamed from: f */
        public final C3028a.c f13497f;

        /* renamed from: g */
        public final String f13498g;

        public a(String str, long j9, String str2, String str3, byte[] bArr, C3028a.c cVar, String str4) {
            this.f13492a = str;
            this.f13493b = j9;
            this.f13494c = str2;
            this.f13495d = str3;
            this.f13496e = bArr;
            this.f13497f = cVar;
            this.f13498g = str4;
        }
    }

    /* JADX WARN: Not initialized variable reg: 12, insn: 0x00a3: MOVE (r0 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY]), block:B:40:0x00a3 */
    /* renamed from: a */
    public static a m15508a(String str, C3028a.c cVar) throws Throwable {
        FileInputStream fileInputStream;
        Object obj;
        boolean z8;
        Object obj2 = null;
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        try {
            try {
                fileInputStream = new FileInputStream(file);
                int i9 = 0;
                do {
                    try {
                        int i10 = fileInputStream.read(bArr, i9, length - i9);
                        z8 = i10 == -1;
                        i9 += i10;
                        if (i9 >= length) {
                            break;
                        }
                    } catch (Exception e9) {
                        e = e9;
                        e.printStackTrace();
                        C6370g.m24480b(fileInputStream);
                        return null;
                    }
                } while (!z8);
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(bArr);
                byte[] bArrDigest = messageDigest.digest();
                StringBuilder sb = new StringBuilder(bArrDigest.length);
                for (byte b9 : bArrDigest) {
                    sb.append(Integer.toString((b9 & UnsignedBytes.MAX_VALUE) + 256, 16).substring(1));
                }
                if (cVar != null) {
                    cVar.f13472a = file.length();
                    cVar.f13473b = sb.toString();
                }
                String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
                if (mimeTypeFromExtension == null) {
                    mimeTypeFromExtension = "";
                }
                a aVar = new a(file.getName(), file.length(), mimeTypeFromExtension, sb.toString(), bArr, cVar, str);
                C6370g.m24480b(fileInputStream);
                return aVar;
            } catch (Throwable th) {
                th = th;
                obj2 = obj;
                C6370g.m24480b(obj2);
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            C6370g.m24480b(obj2);
            throw th;
        }
    }
}
