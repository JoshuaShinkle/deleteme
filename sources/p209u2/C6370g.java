package p209u2;

import com.google.android.gms.appinvite.PreviewActivity;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

/* renamed from: u2.g */
/* loaded from: classes.dex */
public class C6370g {
    /* renamed from: a */
    public static void m24479a(Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        closeable.close();
    }

    /* renamed from: b */
    public static void m24480b(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof Closeable) {
                m24479a((Closeable) obj);
            } else {
                obj.getClass().getMethod(PreviewActivity.ON_CLICK_LISTENER_CLOSE, new Class[0]).invoke(obj, new Object[0]);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: c */
    public static long m24481c(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j9 = 0;
        while (true) {
            int i9 = inputStream.read(bArr);
            if (i9 == -1) {
                return j9;
            }
            outputStream.write(bArr, 0, i9);
            j9 += i9;
        }
    }
}
