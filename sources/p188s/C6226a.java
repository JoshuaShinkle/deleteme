package p188s;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import p197t.C6273a;

/* renamed from: s.a */
/* loaded from: classes.dex */
public class C6226a extends C6273a {

    /* renamed from: s.a$a */
    public interface a {
    }

    /* renamed from: s.a$b */
    public interface b {
        void validateRequestPermissionsRequestCode(int i9);
    }

    /* renamed from: i */
    public static void m23783i(Activity activity) {
        activity.finishAffinity();
    }

    /* renamed from: j */
    public static void m23784j(Activity activity) {
        activity.finishAfterTransition();
    }

    /* renamed from: k */
    public static a m23785k() {
        return null;
    }

    /* renamed from: l */
    public static void m23786l(Activity activity) {
        activity.postponeEnterTransition();
    }

    /* renamed from: m */
    public static void m23787m(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (C6227b.m23803i(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: n */
    public static void m23788n(Activity activity, String[] strArr, int i9) {
        if (activity instanceof b) {
            ((b) activity).validateRequestPermissionsRequestCode(i9);
        }
        activity.requestPermissions(strArr, i9);
    }

    /* renamed from: o */
    public static void m23789o(Activity activity, AbstractC6241p abstractC6241p) {
        activity.setEnterSharedElementCallback(null);
    }

    /* renamed from: p */
    public static void m23790p(Activity activity, AbstractC6241p abstractC6241p) {
        activity.setExitSharedElementCallback(null);
    }

    /* renamed from: q */
    public static boolean m23791q(Activity activity, String str) {
        return activity.shouldShowRequestPermissionRationale(str);
    }

    /* renamed from: r */
    public static void m23792r(Activity activity, Intent intent, int i9, Bundle bundle) {
        activity.startActivityForResult(intent, i9, bundle);
    }

    /* renamed from: s */
    public static void m23793s(Activity activity, IntentSender intentSender, int i9, Intent intent, int i10, int i11, int i12, Bundle bundle) throws IntentSender.SendIntentException {
        activity.startIntentSenderForResult(intentSender, i9, intent, i10, i11, i12, bundle);
    }

    /* renamed from: t */
    public static void m23794t(Activity activity) {
        activity.startPostponedEnterTransition();
    }
}
