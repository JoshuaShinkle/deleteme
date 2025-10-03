package p209u2;

import android.os.Handler;
import android.util.Log;

/* renamed from: u2.o */
/* loaded from: classes.dex */
public abstract class AbstractC6378o<Result, Error, Progress> extends AbstractC6381r<Result, Error> {

    /* renamed from: c */
    public static final String f21539c = "o";

    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public abstract void m24498l(Progress progress);

    /* renamed from: n */
    public final Void m24499n() {
        return m24500o(null);
    }

    /* renamed from: o */
    public final Void m24500o(final Progress progress) {
        try {
            Handler handler = this.f21545a;
            if (handler == null) {
                m24498l(progress);
            } else {
                handler.post(new Runnable() { // from class: u2.n
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21537b.m24498l(progress);
                    }
                });
            }
            return null;
        } catch (Exception e9) {
            Log.e(f21539c, "invoke onProgress failed", e9);
            return null;
        }
    }
}
