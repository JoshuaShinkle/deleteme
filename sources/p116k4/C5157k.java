package p116k4;

import java.util.Date;
import java.util.Locale;

/* renamed from: k4.k */
/* loaded from: classes.dex */
public class C5157k {
    /* renamed from: a */
    public static Date m20085a(String str) {
        if (C5170o0.m20170e(str)) {
            return null;
        }
        try {
            return new Date(Long.parseLong(str));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m20086b(long j9) {
        long j10 = j9 / 60;
        String str = String.format(Locale.getDefault(), "%02d", Long.valueOf(j10 / 60));
        String str2 = String.format(Locale.getDefault(), "%02d", Long.valueOf(j10 % 60));
        String str3 = String.format(Locale.getDefault(), "%02d", Long.valueOf(j9 % 60));
        if (str.equals("00")) {
            return str2 + ":" + str3;
        }
        return str + ":" + str2 + ":" + str3;
    }
}
