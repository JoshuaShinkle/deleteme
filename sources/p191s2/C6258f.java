package p191s2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.SecureRandom;
import java.util.UUID;

/* renamed from: s2.f */
/* loaded from: classes.dex */
public final class C6258f {

    /* renamed from: a */
    public static final File f21099a = C6259g.m23981c(".uno-id");

    /* renamed from: b */
    public static String f21100b = "";

    /* renamed from: s2.f$a */
    public static class a implements d {
        @Override // p191s2.C6258f.d
        /* renamed from: a */
        public void mo23976a(String str) throws IOException {
            if (str.equals(get())) {
                return;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(C6258f.f21099a), "US-ASCII");
            outputStreamWriter.write(str);
            outputStreamWriter.close();
        }

        @Override // p191s2.C6258f.d
        public String get() {
            try {
                if (C6258f.f21099a.exists() && C6258f.f21099a.length() == 73) {
                    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(C6258f.f21099a), "US-ASCII");
                    char[] cArr = new char[73];
                    inputStreamReader.read(cArr);
                    inputStreamReader.close();
                    return new String(cArr);
                }
                return "";
            } catch (Throwable unused) {
                C6258f.f21099a.delete();
                return "";
            }
        }
    }

    /* renamed from: s2.f$b */
    public static class b {

        /* renamed from: a */
        public static final SecureRandom f21101a = new SecureRandom();
    }

    /* renamed from: s2.f$c */
    public static class c implements d {

        /* renamed from: a */
        public final Context f21102a;

        public c(Context context) {
            this.f21102a = context;
        }

        @Override // p191s2.C6258f.d
        /* renamed from: a */
        public void mo23976a(String str) {
            SharedPreferences.Editor editorEdit = m23978b().edit();
            editorEdit.putString("ID", str);
            editorEdit.apply();
        }

        /* renamed from: b */
        public SharedPreferences m23978b() {
            return this.f21102a.getSharedPreferences("UNO_ID", 0);
        }

        @Override // p191s2.C6258f.d
        public String get() {
            return m23978b().getString("ID", "");
        }
    }

    /* renamed from: s2.f$d */
    public interface d {
        /* renamed from: a */
        void mo23976a(String str);

        String get();
    }

    /* renamed from: b */
    public static String m23970b(Context context) {
        d[] dVarArr = {new c(context), new a()};
        String strM23974f = m23974f(dVarArr);
        if (strM23974f.isEmpty()) {
            strM23974f = m23973e();
        }
        m23975g(dVarArr, strM23974f);
        return strM23974f;
    }

    /* renamed from: c */
    public static byte[] m23971c() {
        byte[] bArr = new byte[32];
        try {
            FileInputStream fileInputStream = new FileInputStream("/dev/urandom");
            fileInputStream.read(bArr);
            fileInputStream.close();
        } catch (Throwable unused) {
            b.f21101a.nextBytes(bArr);
        }
        return bArr;
    }

    /* renamed from: d */
    public static boolean m23972d(String str) {
        return str != null && str.length() == 73 && str.charAt(36) == '-';
    }

    /* renamed from: e */
    public static String m23973e() {
        return UUID.nameUUIDFromBytes(m23971c()).toString().toUpperCase() + "-" + UUID.randomUUID().toString().toUpperCase();
    }

    /* renamed from: f */
    public static String m23974f(d[] dVarArr) {
        String str;
        for (d dVar : dVarArr) {
            try {
                str = dVar.get();
            } catch (Throwable unused) {
            }
            if (m23972d(str)) {
                return str;
            }
        }
        return "";
    }

    /* renamed from: g */
    public static void m23975g(d[] dVarArr, String str) {
        for (d dVar : dVarArr) {
            try {
                dVar.mo23976a(str);
            } catch (Throwable th) {
                Log.d("UNOUniqueID", "", th);
            }
        }
    }
}
