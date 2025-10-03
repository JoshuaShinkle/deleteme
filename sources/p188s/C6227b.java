package p188s;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* renamed from: s.b */
/* loaded from: classes.dex */
public final class C6227b {

    /* renamed from: a */
    public static final Class<?> f20953a;

    /* renamed from: b */
    public static final Field f20954b;

    /* renamed from: c */
    public static final Field f20955c;

    /* renamed from: d */
    public static final Method f20956d;

    /* renamed from: e */
    public static final Method f20957e;

    /* renamed from: f */
    public static final Method f20958f;

    /* renamed from: g */
    public static final Handler f20959g = new Handler(Looper.getMainLooper());

    /* renamed from: s.b$a */
    public class a implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ d f20960b;

        /* renamed from: c */
        public final /* synthetic */ Object f20961c;

        public a(d dVar, Object obj) {
            this.f20960b = dVar;
            this.f20961c = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f20960b.f20966b = this.f20961c;
        }
    }

    /* renamed from: s.b$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ Application f20962b;

        /* renamed from: c */
        public final /* synthetic */ d f20963c;

        public b(Application application, d dVar) {
            this.f20962b = application;
            this.f20963c = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f20962b.unregisterActivityLifecycleCallbacks(this.f20963c);
        }
    }

    /* renamed from: s.b$c */
    public class c implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ Object f20964b;

        /* renamed from: c */
        public final /* synthetic */ Object f20965c;

        public c(Object obj, Object obj2) {
            this.f20964b = obj;
            this.f20965c = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Method method = C6227b.f20956d;
                if (method != null) {
                    method.invoke(this.f20964b, this.f20965c, Boolean.FALSE, "AppCompat recreation");
                } else {
                    C6227b.f20957e.invoke(this.f20964b, this.f20965c, Boolean.FALSE);
                }
            } catch (RuntimeException e9) {
                if (e9.getClass() == RuntimeException.class && e9.getMessage() != null && e9.getMessage().startsWith("Unable to stop")) {
                    throw e9;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
            }
        }
    }

    /* renamed from: s.b$d */
    public static final class d implements Application.ActivityLifecycleCallbacks {

        /* renamed from: b */
        public Object f20966b;

        /* renamed from: c */
        public Activity f20967c;

        /* renamed from: d */
        public boolean f20968d = false;

        /* renamed from: e */
        public boolean f20969e = false;

        /* renamed from: f */
        public boolean f20970f = false;

        public d(Activity activity) {
            this.f20967c = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f20967c == activity) {
                this.f20967c = null;
                this.f20969e = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f20969e || this.f20970f || this.f20968d || !C6227b.m23802h(this.f20966b, activity)) {
                return;
            }
            this.f20970f = true;
            this.f20966b = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f20967c == activity) {
                this.f20968d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static {
        Class<?> clsM23795a = m23795a();
        f20953a = clsM23795a;
        f20954b = m23796b();
        f20955c = m23800f();
        f20956d = m23798d(clsM23795a);
        f20957e = m23797c(clsM23795a);
        f20958f = m23799e(clsM23795a);
    }

    /* renamed from: a */
    public static Class<?> m23795a() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static Field m23796b() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: c */
    public static Method m23797c(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: d */
    public static Method m23798d(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: e */
    public static Method m23799e(Class<?> cls) {
        if (m23801g() && cls != null) {
            try {
                Class<?> cls2 = Boolean.TYPE;
                Method declaredMethod = cls.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, cls2, Configuration.class, Configuration.class, cls2, cls2);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    /* renamed from: f */
    public static Field m23800f() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: g */
    public static boolean m23801g() {
        int i9 = Build.VERSION.SDK_INT;
        return i9 == 26 || i9 == 27;
    }

    /* renamed from: h */
    public static boolean m23802h(Object obj, Activity activity) {
        try {
            Object obj2 = f20955c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            f20959g.postAtFrontOfQueue(new c(f20954b.get(activity), obj2));
            return true;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }

    /* renamed from: i */
    public static boolean m23803i(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        }
        if (m23801g() && f20958f == null) {
            return false;
        }
        if (f20957e == null && f20956d == null) {
            return false;
        }
        try {
            Object obj2 = f20955c.get(activity);
            if (obj2 == null || (obj = f20954b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            Handler handler = f20959g;
            handler.post(new a(dVar, obj2));
            try {
                if (m23801g()) {
                    Method method = f20958f;
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new b(application, dVar));
                return true;
            } catch (Throwable th) {
                f20959g.post(new b(application, dVar));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
