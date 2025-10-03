package p216v0;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/* renamed from: v0.c */
/* loaded from: classes.dex */
public final class C6448c {

    /* renamed from: a */
    public static final Charset f21714a = Charset.forName("US-ASCII");

    /* renamed from: b */
    public static final Charset f21715b = Charset.forName("UTF-8");

    /* renamed from: a */
    public static void m24691a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e9) {
                throw e9;
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: b */
    public static void m24692b(File file) throws IOException {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            throw new IOException("not a readable directory: " + file);
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                m24692b(file2);
            }
            if (!file2.delete()) {
                throw new IOException("failed to delete file: " + file2);
            }
        }
    }
}
