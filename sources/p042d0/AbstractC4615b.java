package p042d0;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: d0.b */
/* loaded from: classes.dex */
public abstract class AbstractC4615b {

    /* renamed from: a */
    public final Context f16228a;

    /* renamed from: b */
    public a f16229b;

    /* renamed from: c */
    public b f16230c;

    /* renamed from: d0.b$a */
    public interface a {
        /* renamed from: a */
        void mo620a(boolean z8);
    }

    /* renamed from: d0.b$b */
    public interface b {
        void onActionProviderVisibilityChanged(boolean z8);
    }

    public AbstractC4615b(Context context) {
        this.f16228a = context;
    }

    /* renamed from: a */
    public boolean mo548a() {
        return false;
    }

    /* renamed from: b */
    public boolean mo552b() {
        return true;
    }

    /* renamed from: c */
    public abstract View mo549c();

    /* renamed from: d */
    public View mo553d(MenuItem menuItem) {
        return mo549c();
    }

    /* renamed from: e */
    public boolean mo550e() {
        return false;
    }

    /* renamed from: f */
    public void mo551f(SubMenu subMenu) {
    }

    /* renamed from: g */
    public boolean mo554g() {
        return false;
    }

    /* renamed from: h */
    public void m18393h() {
        this.f16230c = null;
        this.f16229b = null;
    }

    /* renamed from: i */
    public void m18394i(a aVar) {
        this.f16229b = aVar;
    }

    /* renamed from: j */
    public void mo555j(b bVar) {
        if (this.f16230c != null && bVar != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f16230c = bVar;
    }

    /* renamed from: k */
    public void m18395k(boolean z8) {
        a aVar = this.f16229b;
        if (aVar != null) {
            aVar.mo620a(z8);
        }
    }
}
