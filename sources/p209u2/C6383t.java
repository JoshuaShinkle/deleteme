package p209u2;

import android.text.TextUtils;
import android.util.Log;
import com.google.common.primitives.UnsignedBytes;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;

/* renamed from: u2.t */
/* loaded from: classes.dex */
public class C6383t {

    /* renamed from: a */
    public static final String f21549a = "t";

    /* renamed from: b */
    public static final char[] f21550b = "0123456789ABCDEF".toCharArray();

    /* renamed from: c */
    public static Random f21551c = new Random();

    /* renamed from: d */
    public static char[] f21552d = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: a */
    public static boolean m24512a(CharSequence charSequence, CharSequence charSequence2) {
        return TextUtils.equals(charSequence, charSequence2);
    }

    /* renamed from: b */
    public static boolean m24513b(CharSequence charSequence, CharSequence charSequence2) {
        int length;
        if (charSequence == charSequence2) {
            return true;
        }
        if (charSequence == null || charSequence2 == null || (length = charSequence.length()) != charSequence2.length()) {
            return false;
        }
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            return ((String) charSequence).equalsIgnoreCase((String) charSequence2);
        }
        for (int i9 = 0; i9 < length; i9++) {
            if (Character.toLowerCase(charSequence.charAt(i9)) != Character.toLowerCase(charSequence2.charAt(i9))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: c */
    public static String m24514c(long j9) {
        return j9 < 1024 ? String.format(Locale.US, "%dB", Long.valueOf(j9)) : j9 < 1048576 ? String.format(Locale.US, "%.2fKB", Float.valueOf(j9 / 1024.0f)) : String.format(Locale.US, "%.2fMB", Float.valueOf(j9 / 1048576.0f));
    }

    /* renamed from: d */
    public static byte[] m24515d(String str, String str2) {
        try {
            return m24516e(str, str2.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e9) {
            Log.e(f21549a, "", e9);
            return null;
        }
    }

    /* renamed from: e */
    public static byte[] m24516e(String str, byte[] bArr) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e9) {
            Log.e(f21549a, "", e9);
            return null;
        }
    }

    /* renamed from: f */
    public static boolean m24517f(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence);
    }

    /* renamed from: g */
    public static String m24518g(int i9) {
        if (i9 < 1) {
            return null;
        }
        char[] cArr = new char[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            cArr[i10] = f21552d[f21551c.nextInt(71)];
        }
        return new String(cArr);
    }

    /* renamed from: h */
    public static String m24519h(byte[] bArr) {
        return m24520i(m24516e("SHA-256", bArr));
    }

    /* renamed from: i */
    public static String m24520i(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[bArr.length * 2];
        int i9 = 0;
        for (byte b9 : bArr) {
            int i10 = b9 & UnsignedBytes.MAX_VALUE;
            int i11 = i9 + 1;
            char[] cArr2 = f21550b;
            cArr[i9] = cArr2[i10 >>> 4];
            i9 = i11 + 1;
            cArr[i11] = cArr2[i10 & 15];
        }
        return new String(cArr);
    }
}
