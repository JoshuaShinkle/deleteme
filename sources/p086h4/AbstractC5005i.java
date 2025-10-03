package p086h4;

import android.app.Activity;
import p189s0.AbstractC6243a;

/* renamed from: h4.i */
/* loaded from: classes.dex */
public abstract class AbstractC5005i {

    /* renamed from: a */
    public Activity f17248a;

    /* renamed from: b */
    public C4998c0 f17249b;

    public AbstractC5005i(Activity activity, C4998c0 c4998c0) {
        this.f17248a = activity;
        this.f17249b = c4998c0;
    }

    /* renamed from: a */
    public abstract AbstractC6243a mo19399a();

    /* renamed from: b */
    public abstract void mo19400b();

    /* renamed from: c */
    public void m19449c() {
        AbstractC6243a abstractC6243aMo19399a = mo19399a();
        if (abstractC6243aMo19399a == null) {
            return;
        }
        this.f17249b.f17224e.setAdapter(abstractC6243aMo19399a);
        mo19400b();
        mo19401d();
    }

    /* renamed from: d */
    public abstract void mo19401d();

    /* renamed from: e */
    public abstract void mo19402e(int i9);
}
