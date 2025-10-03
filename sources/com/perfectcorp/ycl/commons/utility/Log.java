package com.perfectcorp.ycl.commons.utility;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p159io.IOUtils;

/* loaded from: classes2.dex */
public final class Log {

    /* renamed from: a */
    public static int f16001a = 2;

    /* renamed from: b */
    public static InterfaceC4574c f16002b = Printers.DEFAULT;

    /* renamed from: c */
    public static final List<InterfaceC4574c> f16003c = new ArrayList();

    /* renamed from: d */
    public static final Application.ActivityLifecycleCallbacks f16004d = new C4572a();

    public enum Printers implements InterfaceC4574c {
        DEFAULT { // from class: com.perfectcorp.ycl.commons.utility.Log.Printers.1
            @Override // com.perfectcorp.ycl.commons.utility.Log.InterfaceC4574c
            /* renamed from: a */
            public int mo18154a(int i9, String str, String str2) {
                try {
                    return android.util.Log.println(i9, str, str2);
                } catch (Throwable unused) {
                    System.err.println(C4573b.m18155a(i9, str, str2));
                    return 0;
                }
            }
        },
        NOP { // from class: com.perfectcorp.ycl.commons.utility.Log.Printers.2
            @Override // com.perfectcorp.ycl.commons.utility.Log.InterfaceC4574c
            /* renamed from: a */
            public int mo18154a(int i9, String str, String str2) {
                return 0;
            }
        },
        CRASHLYTICS { // from class: com.perfectcorp.ycl.commons.utility.Log.Printers.3
            @Override // com.perfectcorp.ycl.commons.utility.Log.InterfaceC4574c
            /* renamed from: a */
            public int mo18154a(int i9, String str, String str2) {
                return 0;
            }
        };

        /* synthetic */ Printers(C4572a c4572a) {
            this();
        }
    }

    /* renamed from: com.perfectcorp.ycl.commons.utility.Log$a */
    public class C4572a implements Application.ActivityLifecycleCallbacks {

        /* renamed from: b */
        public final String f16009b = "ActivityLifecycle";

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Log.m18147a("ActivityLifecycle", "onActivityCreated " + activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            Log.m18147a("ActivityLifecycle", "onActivityDestroyed " + activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            Log.m18147a("ActivityLifecycle", "onActivityPaused " + activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            Log.m18147a("ActivityLifecycle", "onActivityResumed " + activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            Log.m18147a("ActivityLifecycle", "onActivitySaveInstanceState " + activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            Log.m18147a("ActivityLifecycle", "onActivityStarted " + activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Log.m18147a("ActivityLifecycle", "onActivityStopped " + activity);
        }
    }

    /* renamed from: com.perfectcorp.ycl.commons.utility.Log$b */
    public static final class C4573b {
        /* renamed from: a */
        public static String m18155a(int i9, String str, String str2) {
            return new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US).format(new Date()) + StringUtils.SPACE + Thread.currentThread().getId() + StringUtils.SPACE + m18156b(i9) + IOUtils.DIR_SEPARATOR_UNIX + str + ": " + str2;
        }

        /* renamed from: b */
        public static char m18156b(int i9) {
            switch (i9) {
                case 2:
                    return 'V';
                case 3:
                    return 'D';
                case 4:
                    return 'I';
                case 5:
                    return 'W';
                case 6:
                    return 'E';
                case 7:
                    return 'A';
                default:
                    return '?';
            }
        }
    }

    /* renamed from: com.perfectcorp.ycl.commons.utility.Log$c */
    public interface InterfaceC4574c {
        /* renamed from: a */
        int mo18154a(int i9, String str, String str2);
    }

    /* renamed from: a */
    public static int m18147a(String str, String str2) {
        return m18152f(3, str, str2);
    }

    /* renamed from: b */
    public static int m18148b(String str, String str2, Throwable th) {
        return m18152f(3, str, str2 + '\n' + m18150d(th));
    }

    /* renamed from: c */
    public static int m18149c(String str, String str2) {
        return m18152f(6, str, str2);
    }

    /* renamed from: d */
    public static String m18150d(Throwable th) {
        return android.util.Log.getStackTraceString(th);
    }

    /* renamed from: e */
    public static int m18151e(String str, String str2) {
        return m18152f(4, str, str2);
    }

    /* renamed from: f */
    public static int m18152f(int i9, String str, String str2) {
        Iterator<InterfaceC4574c> it = f16003c.iterator();
        while (it.hasNext()) {
            it.next().mo18154a(i9, str, str2);
        }
        if (i9 < f16001a) {
            return 0;
        }
        return f16002b.mo18154a(i9, str, str2);
    }

    /* renamed from: g */
    public static int m18153g(String str, String str2) {
        return m18152f(5, str, str2);
    }
}
