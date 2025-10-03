package p088h6;

import android.database.Cursor;
import java.io.Closeable;
import java.io.IOException;

/* renamed from: h6.b */
/* loaded from: classes2.dex */
public class C5028b {
    /* renamed from: a */
    public static void m19604a(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    /* renamed from: b */
    public static void m19605b(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
