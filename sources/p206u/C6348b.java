package p206u;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;
import p021c0.C0702h;
import p206u.C6347a;
import p215v.C6430d;

/* renamed from: u.b */
/* loaded from: classes.dex */
public final class C6348b {

    /* renamed from: u.b$a */
    public static abstract class a {

        /* renamed from: u.b$a$a, reason: collision with other inner class name */
        public class RunnableC6885a implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ Typeface f21416b;

            public RunnableC6885a(Typeface typeface) {
                this.f21416b = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.onFontRetrieved(this.f21416b);
            }
        }

        /* renamed from: u.b$a$b */
        public class b implements Runnable {

            /* renamed from: b */
            public final /* synthetic */ int f21418b;

            public b(int i9) {
                this.f21418b = i9;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.onFontRetrievalFailed(this.f21418b);
            }
        }

        public final void callbackFailAsync(int i9, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new b(i9));
        }

        public final void callbackSuccessAsync(Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new RunnableC6885a(typeface));
        }

        public abstract void onFontRetrievalFailed(int i9);

        public abstract void onFontRetrieved(Typeface typeface);
    }

    /* renamed from: u.b$b */
    public static final class b {

        /* renamed from: u.b$b$a */
        public static class a {

            /* renamed from: a */
            public static final Object f21420a = new Object();

            /* renamed from: b */
            public static Method f21421b;

            /* renamed from: c */
            public static boolean f21422c;

            /* JADX WARN: Removed duplicated region for block: B:30:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* renamed from: a */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public static void m24370a(Resources.Theme theme) {
                Method method;
                synchronized (f21420a) {
                    if (f21422c) {
                        method = f21421b;
                        if (method != null) {
                        }
                    } else {
                        try {
                            Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                            f21421b = declaredMethod;
                            declaredMethod.setAccessible(true);
                        } catch (NoSuchMethodException e9) {
                            Log.i("ResourcesCompat", "Failed to retrieve rebase() method", e9);
                        }
                        f21422c = true;
                        method = f21421b;
                        if (method != null) {
                            try {
                                method.invoke(theme, new Object[0]);
                            } catch (IllegalAccessException | InvocationTargetException e10) {
                                Log.i("ResourcesCompat", "Failed to invoke rebase() method via reflection", e10);
                                f21421b = null;
                            }
                        }
                    }
                }
            }
        }

        /* renamed from: u.b$b$b, reason: collision with other inner class name */
        public static class C6886b {
            /* renamed from: a */
            public static void m24371a(Resources.Theme theme) {
                theme.rebase();
            }
        }

        /* renamed from: a */
        public static void m24369a(Resources.Theme theme) {
            if (Build.VERSION.SDK_INT >= 29) {
                C6886b.m24371a(theme);
            } else {
                a.m24370a(theme);
            }
        }
    }

    /* renamed from: a */
    public static Drawable m24363a(Resources resources, int i9, Resources.Theme theme) {
        return resources.getDrawable(i9, theme);
    }

    /* renamed from: b */
    public static Typeface m24364b(Context context, int i9) {
        if (context.isRestricted()) {
            return null;
        }
        return m24367e(context, i9, new TypedValue(), 0, null, null, false);
    }

    /* renamed from: c */
    public static Typeface m24365c(Context context, int i9, TypedValue typedValue, int i10, a aVar) {
        if (context.isRestricted()) {
            return null;
        }
        return m24367e(context, i9, typedValue, i10, aVar, null, true);
    }

    /* renamed from: d */
    public static void m24366d(Context context, int i9, a aVar, Handler handler) throws Resources.NotFoundException {
        C0702h.m3468b(aVar);
        if (context.isRestricted()) {
            aVar.callbackFailAsync(-4, handler);
        } else {
            m24367e(context, i9, new TypedValue(), 0, aVar, handler, false);
        }
    }

    /* renamed from: e */
    public static Typeface m24367e(Context context, int i9, TypedValue typedValue, int i10, a aVar, Handler handler, boolean z8) throws Resources.NotFoundException {
        Resources resources = context.getResources();
        resources.getValue(i9, typedValue, true);
        Typeface typefaceM24368f = m24368f(context, resources, typedValue, i9, i10, aVar, handler, z8);
        if (typefaceM24368f != null || aVar != null) {
            return typefaceM24368f;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i9) + " could not be retrieved.");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a3  */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Typeface m24368f(Context context, Resources resources, TypedValue typedValue, int i9, int i10, a aVar, Handler handler, boolean z8) {
        CharSequence charSequence = typedValue.string;
        if (charSequence == null) {
            throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(i9) + "\" (" + Integer.toHexString(i9) + ") is not a Font: " + typedValue);
        }
        String string = charSequence.toString();
        if (!string.startsWith("res/")) {
            if (aVar != null) {
                aVar.callbackFailAsync(-3, handler);
            }
            return null;
        }
        Typeface typefaceM24599f = C6430d.m24599f(resources, i9, i10);
        if (typefaceM24599f != null) {
            if (aVar != null) {
                aVar.callbackSuccessAsync(typefaceM24599f, handler);
            }
            return typefaceM24599f;
        }
        try {
            if (!string.toLowerCase().endsWith(".xml")) {
                Typeface typefaceM24597d = C6430d.m24597d(context, resources, i9, string, i10);
                if (aVar != null) {
                    if (typefaceM24597d != null) {
                        aVar.callbackSuccessAsync(typefaceM24597d, handler);
                    } else {
                        aVar.callbackFailAsync(-3, handler);
                    }
                }
                return typefaceM24597d;
            }
            C6347a.a aVarM24346b = C6347a.m24346b(resources.getXml(i9), resources);
            if (aVarM24346b != null) {
                return C6430d.m24596c(context, aVarM24346b, resources, i9, i10, aVar, handler, z8);
            }
            Log.e("ResourcesCompat", "Failed to find font-family tag");
            if (aVar != null) {
                aVar.callbackFailAsync(-3, handler);
            }
            return null;
        } catch (IOException e9) {
            Log.e("ResourcesCompat", "Failed to read xml resource " + string, e9);
            if (aVar != null) {
                aVar.callbackFailAsync(-3, handler);
            }
            return null;
        } catch (XmlPullParserException e10) {
            Log.e("ResourcesCompat", "Failed to parse xml resource " + string, e10);
            if (aVar != null) {
            }
            return null;
        }
    }
}
