package p066f4;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/* renamed from: f4.a */
/* loaded from: classes.dex */
public class C4787a extends BitmapDrawable {

    /* renamed from: a */
    public int f16636a;

    /* renamed from: b */
    public int f16637b;

    /* renamed from: c */
    public boolean f16638c;

    /* renamed from: a */
    public final synchronized void m19014a() {
        if (this.f16636a <= 0 && this.f16637b <= 0 && this.f16638c && m19015b()) {
            getBitmap().recycle();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized boolean m19015b() {
        boolean z8;
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            z8 = !bitmap.isRecycled();
        }
        return z8;
    }

    /* renamed from: c */
    public void m19016c(boolean z8) {
        synchronized (this) {
            if (z8) {
                this.f16637b++;
                this.f16638c = true;
            } else {
                this.f16637b--;
            }
        }
        m19014a();
    }
}
