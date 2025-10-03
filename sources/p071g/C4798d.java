package p071g;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import p010b.C0568i;

/* renamed from: g.d */
/* loaded from: classes.dex */
public class C4798d extends ContextWrapper {

    /* renamed from: a */
    public int f16654a;

    /* renamed from: b */
    public Resources.Theme f16655b;

    /* renamed from: c */
    public LayoutInflater f16656c;

    /* renamed from: d */
    public Configuration f16657d;

    /* renamed from: e */
    public Resources f16658e;

    public C4798d() {
        super(null);
    }

    /* renamed from: a */
    public void m19043a(Configuration configuration) {
        if (this.f16658e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f16657d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f16657d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    /* renamed from: b */
    public final Resources m19044b() {
        if (this.f16658e == null) {
            Configuration configuration = this.f16657d;
            if (configuration == null) {
                this.f16658e = super.getResources();
            } else {
                this.f16658e = createConfigurationContext(configuration).getResources();
            }
        }
        return this.f16658e;
    }

    /* renamed from: c */
    public int m19045c() {
        return this.f16654a;
    }

    /* renamed from: d */
    public final void m19046d() {
        boolean z8 = this.f16655b == null;
        if (z8) {
            this.f16655b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f16655b.setTo(theme);
            }
        }
        m19047e(this.f16655b, this.f16654a, z8);
    }

    /* renamed from: e */
    public void m19047e(Resources.Theme theme, int i9, boolean z8) {
        theme.applyStyle(i9, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return m19044b();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f16656c == null) {
            this.f16656c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f16656c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f16655b;
        if (theme != null) {
            return theme;
        }
        if (this.f16654a == 0) {
            this.f16654a = C0568i.Theme_AppCompat_Light;
        }
        m19046d();
        return this.f16655b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i9) {
        if (this.f16654a != i9) {
            this.f16654a = i9;
            m19046d();
        }
    }

    public C4798d(Context context, int i9) {
        super(context);
        this.f16654a = i9;
    }

    public C4798d(Context context, Resources.Theme theme) {
        super(context);
        this.f16655b = theme;
    }
}
