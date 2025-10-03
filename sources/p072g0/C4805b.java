package p072g0;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/* renamed from: g0.b */
/* loaded from: classes.dex */
public class C4805b {
    /* renamed from: a */
    public static void m19074a(AutoCloseable autoCloseable) throws Exception {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e9) {
                throw e9;
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: b */
    public static long m19075b(Context context, Uri uri) {
        return m19076c(context, uri, "last_modified", 0L);
    }

    /* renamed from: c */
    public static long m19076c(Context context, Uri uri, String str, long j9) throws Exception {
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
            return (!cursorQuery.moveToFirst() || cursorQuery.isNull(0)) ? j9 : cursorQuery.getLong(0);
        } catch (Exception e9) {
            Log.w("DocumentFile", "Failed query: " + e9);
            return j9;
        } finally {
            m19074a(cursorQuery);
        }
    }
}
