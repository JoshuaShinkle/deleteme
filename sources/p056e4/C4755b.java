package p056e4;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.cyberlink.you.p036ui.C3123g;
import java.util.concurrent.TimeUnit;

/* renamed from: e4.b */
/* loaded from: classes.dex */
public class C4755b {

    /* renamed from: e4.b$a */
    public static class a {
        /* renamed from: a */
        public static void m18881a(String str, String str2) {
        }

        /* renamed from: b */
        public static void m18882b(String str, String str2) {
        }
    }

    /* renamed from: a */
    public static int[] m18878a(Context context) {
        int[] iArr = {0, 0};
        if (context != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            iArr[0] = displayMetrics.widthPixels;
            iArr[1] = displayMetrics.heightPixels;
        }
        return iArr;
    }

    /* renamed from: b */
    public static long m18879b(long j9) {
        long millis = TimeUnit.SECONDS.toMillis(1L);
        long j10 = j9 % millis;
        long j11 = j9 / millis;
        return j10 > 500 ? (j11 + 1) * millis : j11 * millis;
    }

    /* renamed from: c */
    public static void m18880c(Activity activity, int i9, int i10, int i11, int i12, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        C3123g.m16382a(activity).setCancelable(false).setTitle(activity.getResources().getString(i9)).setMessage(i10).setPositiveButton(i11, onClickListener).setNegativeButton(i12, onClickListener2).show();
    }
}
