package p201t3;

import android.graphics.Bitmap;
import android.util.LruCache;

/* renamed from: t3.q */
/* loaded from: classes.dex */
public class C6303q {

    /* renamed from: a */
    public int f21250a;

    /* renamed from: b */
    public LruCache<String, Bitmap> f21251b = null;

    /* renamed from: t3.q$a */
    public class a extends LruCache<String, Bitmap> {
        public a(int i9) {
            super(i9);
        }

        @Override // android.util.LruCache
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    }

    public C6303q(int i9) {
        this.f21250a = i9;
        m24129c();
    }

    /* renamed from: a */
    public boolean m24127a(String str, Bitmap bitmap) {
        if (this.f21251b == null || bitmap == null || m24128b(str) != null) {
            return false;
        }
        this.f21251b.put(str, bitmap);
        return true;
    }

    /* renamed from: b */
    public Bitmap m24128b(String str) {
        LruCache<String, Bitmap> lruCache = this.f21251b;
        if (lruCache == null || str == null) {
            return null;
        }
        return lruCache.get(str);
    }

    /* renamed from: c */
    public boolean m24129c() {
        if (this.f21251b != null) {
            return false;
        }
        this.f21251b = new a(this.f21250a);
        return true;
    }
}
