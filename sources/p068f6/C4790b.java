package p068f6;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import com.google.android.exoplayer2.C3322C;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.impl.AdwHomeBadger;
import me.leolin.shortcutbadger.impl.ApexHomeBadger;
import me.leolin.shortcutbadger.impl.DefaultBadger;
import me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import me.leolin.shortcutbadger.impl.NovaHomeBadger;
import me.leolin.shortcutbadger.impl.SonyHomeBadger;
import p078g6.C4960a;
import p078g6.C4961b;
import p078g6.C4962c;
import p078g6.C4963d;
import p078g6.C4964e;
import p078g6.C4965f;
import p078g6.C4966g;
import p078g6.C4967h;

/* renamed from: f6.b */
/* loaded from: classes2.dex */
public final class C4790b {

    /* renamed from: a */
    public static final List<Class<? extends InterfaceC4789a>> f16641a;

    /* renamed from: b */
    public static final Object f16642b;

    /* renamed from: c */
    public static InterfaceC4789a f16643c;

    /* renamed from: d */
    public static ComponentName f16644d;

    static {
        LinkedList linkedList = new LinkedList();
        f16641a = linkedList;
        f16642b = new Object();
        linkedList.add(AdwHomeBadger.class);
        linkedList.add(ApexHomeBadger.class);
        linkedList.add(NewHtcHomeBadger.class);
        linkedList.add(NovaHomeBadger.class);
        linkedList.add(SonyHomeBadger.class);
        linkedList.add(C4960a.class);
        linkedList.add(C4962c.class);
        linkedList.add(C4963d.class);
        linkedList.add(C4964e.class);
        linkedList.add(C4967h.class);
        linkedList.add(C4965f.class);
        linkedList.add(C4966g.class);
        linkedList.add(C4961b.class);
    }

    /* renamed from: a */
    public static boolean m19022a(Context context, int i9) {
        try {
            m19023b(context, i9);
            return true;
        } catch (ShortcutBadgeException e9) {
            if (!Log.isLoggable("ShortcutBadger", 3)) {
                return false;
            }
            Log.d("ShortcutBadger", "Unable to execute badge", e9);
            return false;
        }
    }

    /* renamed from: b */
    public static void m19023b(Context context, int i9) throws ShortcutBadgeException {
        if (f16643c == null && !m19024c(context)) {
            throw new ShortcutBadgeException("No default launcher available");
        }
        try {
            f16643c.mo19021b(context, f16644d, i9);
        } catch (Exception e9) {
            throw new ShortcutBadgeException("Unable to execute badge", e9);
        }
    }

    /* renamed from: c */
    public static boolean m19024c(Context context) throws IllegalAccessException, InstantiationException {
        InterfaceC4789a interfaceC4789aNewInstance;
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            Log.e("ShortcutBadger", "Unable to find launch intent for package " + context.getPackageName());
            return false;
        }
        f16644d = launchIntentForPackage.getComponent();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE);
        if (resolveInfoResolveActivity == null || resolveInfoResolveActivity.activityInfo.name.toLowerCase().contains("resolver")) {
            return false;
        }
        String str = resolveInfoResolveActivity.activityInfo.packageName;
        Iterator<Class<? extends InterfaceC4789a>> it = f16641a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            try {
                interfaceC4789aNewInstance = it.next().newInstance();
            } catch (Exception unused) {
                interfaceC4789aNewInstance = null;
            }
            if (interfaceC4789aNewInstance != null && interfaceC4789aNewInstance.mo19020a().contains(str)) {
                f16643c = interfaceC4789aNewInstance;
                break;
            }
        }
        if (f16643c != null) {
            return true;
        }
        String str2 = Build.MANUFACTURER;
        if (str2.equalsIgnoreCase("ZUK")) {
            f16643c = new C4967h();
            return true;
        }
        if (str2.equalsIgnoreCase("OPPO")) {
            f16643c = new C4963d();
            return true;
        }
        if (str2.equalsIgnoreCase("VIVO")) {
            f16643c = new C4965f();
            return true;
        }
        if (str2.equalsIgnoreCase("ZTE")) {
            f16643c = new C4966g();
            return true;
        }
        f16643c = new DefaultBadger();
        return true;
    }
}
