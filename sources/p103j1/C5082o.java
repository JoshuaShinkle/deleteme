package p103j1;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import java.io.File;

/* renamed from: j1.o */
/* loaded from: classes.dex */
public final class C5082o {

    /* renamed from: c */
    public static final File f17515c = new File("/proc/self/fd");

    /* renamed from: d */
    public static volatile C5082o f17516d;

    /* renamed from: a */
    public volatile int f17517a;

    /* renamed from: b */
    public volatile boolean f17518b = true;

    /* renamed from: a */
    public static C5082o m19884a() {
        if (f17516d == null) {
            synchronized (C5082o.class) {
                if (f17516d == null) {
                    f17516d = new C5082o();
                }
            }
        }
        return f17516d;
    }

    /* renamed from: b */
    public final synchronized boolean m19885b() {
        boolean z8 = true;
        int i9 = this.f17517a + 1;
        this.f17517a = i9;
        if (i9 >= 50) {
            this.f17517a = 0;
            int length = f17515c.list().length;
            if (length >= 700) {
                z8 = false;
            }
            this.f17518b = z8;
            if (!this.f17518b && Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit 700");
            }
        }
        return this.f17518b;
    }

    @TargetApi(26)
    /* renamed from: c */
    public boolean m19886c(int i9, int i10, BitmapFactory.Options options, DecodeFormat decodeFormat, boolean z8, boolean z9) {
        if (!z8 || z9) {
            return false;
        }
        boolean z10 = i9 >= 128 && i10 >= 128 && m19885b();
        if (z10) {
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
            options.inMutable = false;
        }
        return z10;
    }
}
