package com.perfectcorp.utility;

import com.google.android.gms.appinvite.PreviewActivity;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.perfectcorp.utility.a */
/* loaded from: classes2.dex */
public class C4506a {
    /* renamed from: a */
    public static void m18098a(Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        closeable.close();
    }

    /* renamed from: b */
    public static void m18099b(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof Closeable) {
                m18098a((Closeable) obj);
            } else {
                obj.getClass().getMethod(PreviewActivity.ON_CLICK_LISTENER_CLOSE, new Class[0]).invoke(obj, new Object[0]);
            }
        } catch (Exception unused) {
        }
    }
}
