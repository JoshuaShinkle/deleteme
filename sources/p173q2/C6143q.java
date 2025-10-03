package p173q2;

import android.app.Activity;
import android.content.Context;
import com.cyberlink.you.BaseActivity;
import com.cyberlink.you.BaseFragmentActivity;

/* renamed from: q2.q */
/* loaded from: classes.dex */
public final class C6143q {
    /* renamed from: a */
    public static boolean m23608a(Context context) {
        if (context instanceof BaseActivity) {
            return ((BaseActivity) context).m7364e();
        }
        if (context instanceof BaseFragmentActivity) {
            return ((BaseFragmentActivity) context).m7367J0();
        }
        if (context instanceof Activity) {
            return ((Activity) context).isFinishing();
        }
        return false;
    }
}
