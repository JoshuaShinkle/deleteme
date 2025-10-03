package p116k4;

import android.annotation.SuppressLint;
import android.util.Base64;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p227w2.C6518a;

@SuppressLint({"GetInstance"})
/* renamed from: k4.a */
/* loaded from: classes.dex */
public class C5127a {
    /* renamed from: a */
    public static String m19997a(String str) {
        if (C5170o0.m20170e(str)) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpecM20000d = m20000d();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(m19999c());
            byte[] bArrDecode = Base64.decode(str, 0);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpecM20000d, ivParameterSpec);
            return new String(cipher.doFinal(bArrDecode), "UTF-8");
        } catch (Exception e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static String m19998b(String str) {
        if (C5170o0.m20170e(str)) {
            return "";
        }
        try {
            SecretKeySpec secretKeySpecM20000d = m20000d();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(m19999c());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpecM20000d, ivParameterSpec);
            return Base64.encodeToString(cipher.doFinal(str.getBytes("UTF8")), 0);
        } catch (Exception e9) {
            e9.printStackTrace();
            return "";
        }
    }

    /* renamed from: c */
    public static byte[] m19999c() {
        String strConcat = "";
        for (String str : C6518a.f21935g) {
            strConcat = strConcat.concat(str);
        }
        byte[] bArr = new byte[16];
        Arrays.fill(bArr, (byte) 0);
        byte[] bytes = strConcat.getBytes();
        System.arraycopy(bytes, 0, bArr, 0, bytes.length < 16 ? bytes.length : 16);
        return bArr;
    }

    /* renamed from: d */
    public static SecretKeySpec m20000d() {
        String strConcat = "";
        for (String str : C6518a.f21935g) {
            strConcat = strConcat.concat(str);
        }
        byte[] bArr = new byte[32];
        Arrays.fill(bArr, (byte) 0);
        byte[] bytes = strConcat.getBytes();
        System.arraycopy(bytes, 0, bArr, 0, bytes.length < 32 ? bytes.length : 32);
        return new SecretKeySpec(bArr, "AES");
    }
}
