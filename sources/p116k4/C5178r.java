package p116k4;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import androidx.fragment.app.Fragment;
import java.util.Iterator;

/* renamed from: k4.r */
/* loaded from: classes.dex */
public final class C5178r {

    /* renamed from: a */
    public static final String f17744a = "r";

    /* renamed from: a */
    public static Intent m20232a() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        return intent;
    }

    /* renamed from: b */
    public static Intent m20233b() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        return intent;
    }

    /* renamed from: c */
    public static Intent m20234c() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("video/*");
        return intent;
    }

    /* renamed from: d */
    public static Intent m20235d(Context context, String str, String str2, String... strArr) {
        Intent intent = new Intent();
        intent.setAction(str);
        if (str2 != null) {
            intent.setType(str2);
        }
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, 0).iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo next = it.next();
            for (String str3 : strArr) {
                if (next.activityInfo.packageName.contains(str3)) {
                    ActivityInfo activityInfo = next.activityInfo;
                    intent.setClassName(activityInfo.packageName, activityInfo.name);
                    break loop0;
                }
            }
        }
        return intent;
    }

    /* renamed from: e */
    public static Intent m20236e(String str, String str2) {
        return m20237f(str, str2, null);
    }

    /* renamed from: f */
    public static Intent m20237f(String str, String str2, String[] strArr) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        String strConcat = "mailto:";
        if (strArr == null || strArr.length <= 0) {
            intent.putExtra("android.intent.extra.SUBJECT", str);
            intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str2, 0).toString());
            intent.setData(Uri.parse("mailto:"));
        } else {
            for (String str3 : strArr) {
                strConcat = strConcat.concat(str3 + ",");
            }
            intent.setData(Uri.parse(strConcat + "?subject=" + str + "&body=" + Html.fromHtml(str2).toString()));
        }
        return intent;
    }

    /* renamed from: g */
    public static boolean m20238g(Activity activity, Intent intent, int i9) {
        try {
            activity.startActivityForResult(intent, i9);
            return true;
        } catch (ActivityNotFoundException e9) {
            Log.w(f17744a, "No Activity to handle such Intent: ", e9);
            return false;
        }
    }

    /* renamed from: h */
    public static boolean m20239h(Fragment fragment, Intent intent, int i9) {
        try {
            fragment.startActivityForResult(intent, i9);
            return true;
        } catch (ActivityNotFoundException e9) {
            Log.w(f17744a, "No Activity to handle such Intent: ", e9);
            return false;
        }
    }

    /* renamed from: i */
    public static boolean m20240i(Activity activity, int i9, Uri uri, Uri uri2, int i10) {
        Intent intentM20235d = m20235d(activity.getApplicationContext(), "com.android.camera.action.CROP", "image/*", "gallery", "google");
        if (intentM20235d.getComponent() == null) {
            return false;
        }
        intentM20235d.setDataAndType(uri, "image/*");
        intentM20235d.setFlags(1);
        intentM20235d.putExtra("crop", "true");
        intentM20235d.putExtra("aspectX", 1);
        intentM20235d.putExtra("aspectY", 1);
        intentM20235d.putExtra("outputX", i10);
        intentM20235d.putExtra("outputY", i10);
        intentM20235d.putExtra("return-data", false);
        intentM20235d.putExtra("output", uri2);
        return m20238g(activity, intentM20235d, i9);
    }

    /* renamed from: j */
    public static boolean m20241j(Activity activity, int i9) {
        return m20238g(activity, m20232a(), i9);
    }

    /* renamed from: k */
    public static boolean m20242k(Fragment fragment, int i9) {
        return m20239h(fragment, m20232a(), i9);
    }

    /* renamed from: l */
    public static boolean m20243l(Activity activity, int i9) {
        return m20238g(activity, m20233b(), i9);
    }

    /* renamed from: m */
    public static boolean m20244m(Activity activity, int i9) {
        return m20238g(activity, m20234c(), i9);
    }

    /* renamed from: n */
    public static boolean m20245n(Fragment fragment, int i9) {
        return m20239h(fragment, m20234c(), i9);
    }
}
