package p116k4;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.cyberlink.you.Globals;

/* renamed from: k4.v0 */
/* loaded from: classes.dex */
public class C5187v0 {

    /* renamed from: a */
    public static final Handler f17763a = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    public static Toast f17764b = null;

    /* renamed from: b */
    public static /* synthetic */ void m20266b(String str, int i9) {
        Toast toast = f17764b;
        if (toast != null) {
            toast.cancel();
        }
        Toast toastMakeText = Toast.makeText(Globals.m7372O(), str, i9);
        f17764b = toastMakeText;
        toastMakeText.show();
    }

    /* renamed from: c */
    public static void m20267c(int i9) {
        m20272h(i9);
    }

    /* renamed from: d */
    public static void m20268d(String str) {
        m20273i(str);
    }

    /* renamed from: e */
    public static void m20269e(final String str, final int i9) {
        Handler handler = f17763a;
        handler.removeCallbacksAndMessages(null);
        handler.post(new Runnable() { // from class: k4.u0
            @Override // java.lang.Runnable
            public final void run() {
                C5187v0.m20266b(str, i9);
            }
        });
    }

    /* renamed from: f */
    public static void m20270f(int i9) {
        m20271g(Globals.m7375Z0(i9));
    }

    /* renamed from: g */
    public static void m20271g(String str) {
        m20269e(str, 1);
    }

    /* renamed from: h */
    public static void m20272h(int i9) {
        m20273i(Globals.m7375Z0(i9));
    }

    /* renamed from: i */
    public static void m20273i(String str) {
        m20269e(str, 0);
    }
}
