package p209u2;

import android.os.Handler;
import android.util.Log;

/* renamed from: u2.r */
/* loaded from: classes.dex */
public abstract class AbstractC6381r<Result, Error> {

    /* renamed from: b */
    public static final String f21544b = "r";

    /* renamed from: a */
    public final Handler f21545a;

    public AbstractC6381r() {
        this(null);
    }

    /* renamed from: c */
    public final Void m24505c() {
        return m24506d(null);
    }

    /* renamed from: d */
    public final Void m24506d(final Result result) {
        try {
            Handler handler = this.f21545a;
            if (handler == null) {
                m24503g(result);
            } else {
                handler.post(new Runnable() { // from class: u2.p
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21540b.m24503g(result);
                    }
                });
            }
            return null;
        } catch (Exception e9) {
            Log.e(f21544b, "invoke onComplete failed", e9);
            return null;
        }
    }

    /* renamed from: e */
    public final Void m24507e() {
        return m24508f(null);
    }

    /* renamed from: f */
    public final Void m24508f(final Error error) {
        try {
            Handler handler = this.f21545a;
            if (handler == null) {
                m24504h(error);
            } else {
                handler.post(new Runnable() { // from class: u2.q
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f21542b.m24504h(error);
                    }
                });
            }
            return null;
        } catch (Exception e9) {
            Log.e(f21544b, "invoke onError failed", e9);
            return null;
        }
    }

    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public abstract void m24503g(Result result);

    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public void m24504h(Error error) {
    }

    public AbstractC6381r(Handler handler) {
        this.f21545a = handler;
    }
}
