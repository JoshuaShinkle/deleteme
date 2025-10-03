package p191s2;

import android.os.Environment;
import java.io.File;

/* renamed from: s2.g */
/* loaded from: classes.dex */
public final class C6259g {

    /* renamed from: a */
    public static String f21103a;

    /* renamed from: a */
    public static boolean m23979a(File file) {
        boolean z8;
        if (file.isDirectory()) {
            z8 = true;
            for (File file2 : file.listFiles()) {
                z8 = z8 && m23979a(file2);
            }
        } else {
            z8 = true;
        }
        return z8 && file.delete();
    }

    /* renamed from: b */
    public static File m23980b() {
        String str = f21103a;
        return str == null ? Environment.getExternalStorageDirectory() : m23982d(str);
    }

    /* renamed from: c */
    public static File m23981c(String str) {
        try {
            return new File(m23980b(), str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: d */
    public static File m23982d(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (!file.exists() || file.isDirectory()) {
            m23979a(file);
            file.mkdirs();
            return file;
        }
        throw new AssertionError(file + " exists but is not a directory.");
    }
}
