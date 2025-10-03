package androidx.lifecycle;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: androidx.lifecycle.o */
/* loaded from: classes.dex */
public abstract class AbstractC0394o {

    /* renamed from: a */
    public final Map<String, Object> f2231a = new HashMap();

    /* renamed from: b */
    public volatile boolean f2232b = false;

    /* renamed from: b */
    public static void m2111b(Object obj) throws IOException {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e9) {
                throw new RuntimeException(e9);
            }
        }
    }

    /* renamed from: a */
    public final void m2112a() {
        this.f2232b = true;
        Map<String, Object> map = this.f2231a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f2231a.values().iterator();
                while (it.hasNext()) {
                    m2111b(it.next());
                }
            }
        }
        mo1970c();
    }

    /* renamed from: c */
    public void mo1970c() {
    }
}
