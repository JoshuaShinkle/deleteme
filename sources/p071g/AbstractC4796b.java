package p071g;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* renamed from: g.b */
/* loaded from: classes.dex */
public abstract class AbstractC4796b {

    /* renamed from: b */
    public Object f16652b;

    /* renamed from: c */
    public boolean f16653c;

    /* renamed from: g.b$a */
    public interface a {
        /* renamed from: a */
        boolean mo353a(AbstractC4796b abstractC4796b, MenuItem menuItem);

        /* renamed from: b */
        void mo354b(AbstractC4796b abstractC4796b);

        /* renamed from: c */
        boolean mo355c(AbstractC4796b abstractC4796b, Menu menu);

        /* renamed from: d */
        boolean mo356d(AbstractC4796b abstractC4796b, Menu menu);
    }

    /* renamed from: a */
    public abstract void mo438a();

    /* renamed from: b */
    public abstract View mo439b();

    /* renamed from: c */
    public abstract Menu mo440c();

    /* renamed from: d */
    public abstract MenuInflater mo441d();

    /* renamed from: e */
    public abstract CharSequence mo442e();

    /* renamed from: f */
    public Object m19040f() {
        return this.f16652b;
    }

    /* renamed from: g */
    public abstract CharSequence mo443g();

    /* renamed from: h */
    public boolean m19041h() {
        return this.f16653c;
    }

    /* renamed from: i */
    public abstract void mo444i();

    /* renamed from: j */
    public abstract boolean mo445j();

    /* renamed from: k */
    public abstract void mo446k(View view);

    /* renamed from: l */
    public abstract void mo447l(int i9);

    /* renamed from: m */
    public abstract void mo448m(CharSequence charSequence);

    /* renamed from: n */
    public void m19042n(Object obj) {
        this.f16652b = obj;
    }

    /* renamed from: o */
    public abstract void mo449o(int i9);

    /* renamed from: p */
    public abstract void mo450p(CharSequence charSequence);

    /* renamed from: q */
    public void mo451q(boolean z8) {
        this.f16653c = z8;
    }
}
