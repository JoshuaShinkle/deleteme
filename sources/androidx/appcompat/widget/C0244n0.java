package androidx.appcompat.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: androidx.appcompat.widget.n0 */
/* loaded from: classes.dex */
public class C0244n0 extends ContextWrapper {

    /* renamed from: c */
    public static final Object f1183c = new Object();

    /* renamed from: d */
    public static ArrayList<WeakReference<C0244n0>> f1184d;

    /* renamed from: a */
    public final Resources f1185a;

    /* renamed from: b */
    public final Resources.Theme f1186b;

    public C0244n0(Context context) {
        super(context);
        if (!C0256t0.m1064b()) {
            this.f1185a = new C0248p0(this, context.getResources());
            this.f1186b = null;
            return;
        }
        C0256t0 c0256t0 = new C0256t0(this, context.getResources());
        this.f1185a = c0256t0;
        Resources.Theme themeNewTheme = c0256t0.newTheme();
        this.f1186b = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    /* renamed from: a */
    public static boolean m997a(Context context) {
        return ((context instanceof C0244n0) || (context.getResources() instanceof C0248p0) || (context.getResources() instanceof C0256t0) || !C0256t0.m1064b()) ? false : true;
    }

    /* renamed from: b */
    public static Context m998b(Context context) {
        if (!m997a(context)) {
            return context;
        }
        synchronized (f1183c) {
            ArrayList<WeakReference<C0244n0>> arrayList = f1184d;
            if (arrayList == null) {
                f1184d = new ArrayList<>();
            } else {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    WeakReference<C0244n0> weakReference = f1184d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f1184d.remove(size);
                    }
                }
                for (int size2 = f1184d.size() - 1; size2 >= 0; size2--) {
                    WeakReference<C0244n0> weakReference2 = f1184d.get(size2);
                    C0244n0 c0244n0 = weakReference2 != null ? weakReference2.get() : null;
                    if (c0244n0 != null && c0244n0.getBaseContext() == context) {
                        return c0244n0;
                    }
                }
            }
            C0244n0 c0244n02 = new C0244n0(context);
            f1184d.add(new WeakReference<>(c0244n02));
            return c0244n02;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f1185a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f1185a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1186b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i9) {
        Resources.Theme theme = this.f1186b;
        if (theme == null) {
            super.setTheme(i9);
        } else {
            theme.applyStyle(i9, true);
        }
    }
}
