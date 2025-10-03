package p245y2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;

/* renamed from: y2.g5 */
/* loaded from: classes.dex */
public class HandlerC6652g5 extends Handler {

    /* renamed from: a */
    public C6773x5 f22260a;

    /* renamed from: b */
    public boolean f22261b;

    public HandlerC6652g5() {
        super(Looper.getMainLooper());
        this.f22261b = false;
    }

    /* renamed from: a */
    public void m25245a() {
        removeMessages(1);
        this.f22261b = false;
    }

    /* renamed from: b */
    public final void m25246b() {
        C6773x5 c6773x5 = this.f22260a;
        if (c6773x5 != null) {
            c6773x5.m25321e();
        }
        this.f22261b = false;
    }

    /* renamed from: c */
    public boolean m25247c() {
        return this.f22260a.f22499j;
    }

    /* renamed from: d */
    public void m25248d() {
        removeMessages(1);
        sendMessageDelayed(obtainMessage(1), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        this.f22261b = true;
    }

    /* renamed from: e */
    public void m25249e(C6773x5 c6773x5) {
        this.f22260a = c6773x5;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1 && this.f22261b) {
            m25246b();
        }
    }
}
