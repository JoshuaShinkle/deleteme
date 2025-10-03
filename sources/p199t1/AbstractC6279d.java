package p199t1;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import p208u1.InterfaceC6362b;

/* renamed from: t1.d */
/* loaded from: classes.dex */
public abstract class AbstractC6279d<Z> extends AbstractC6284i<ImageView, Z> implements InterfaceC6362b.a {

    /* renamed from: j */
    public Animatable f21179j;

    public AbstractC6279d(ImageView imageView) {
        super(imageView);
    }

    @Override // p199t1.AbstractC6276a, p199t1.InterfaceC6283h
    /* renamed from: e */
    public void mo7182e(Drawable drawable) {
        super.mo7182e(drawable);
        m24053p(null);
        m24052n(drawable);
    }

    @Override // p199t1.InterfaceC6283h
    /* renamed from: f */
    public void mo7183f(Z z8, InterfaceC6362b<? super Z> interfaceC6362b) {
        if (interfaceC6362b == null || !interfaceC6362b.mo24445a(z8, this)) {
            m24053p(z8);
        } else {
            m24051m(z8);
        }
    }

    @Override // p199t1.AbstractC6284i, p199t1.AbstractC6276a, p199t1.InterfaceC6283h
    /* renamed from: g */
    public void mo23900g(Drawable drawable) {
        super.mo23900g(drawable);
        m24053p(null);
        m24052n(drawable);
    }

    @Override // p199t1.AbstractC6284i, p199t1.AbstractC6276a, p199t1.InterfaceC6283h
    /* renamed from: i */
    public void mo23902i(Drawable drawable) {
        super.mo23902i(drawable);
        Animatable animatable = this.f21179j;
        if (animatable != null) {
            animatable.stop();
        }
        m24053p(null);
        m24052n(drawable);
    }

    /* renamed from: m */
    public final void m24051m(Z z8) {
        if (!(z8 instanceof Animatable)) {
            this.f21179j = null;
            return;
        }
        Animatable animatable = (Animatable) z8;
        this.f21179j = animatable;
        animatable.start();
    }

    /* renamed from: n */
    public void m24052n(Drawable drawable) {
        ((ImageView) this.f21184c).setImageDrawable(drawable);
    }

    /* renamed from: o */
    public abstract void mo24048o(Z z8);

    @Override // p199t1.AbstractC6276a, p163p1.InterfaceC5878i
    public void onStart() {
        Animatable animatable = this.f21179j;
        if (animatable != null) {
            animatable.start();
        }
    }

    @Override // p199t1.AbstractC6276a, p163p1.InterfaceC5878i
    public void onStop() {
        Animatable animatable = this.f21179j;
        if (animatable != null) {
            animatable.stop();
        }
    }

    /* renamed from: p */
    public final void m24053p(Z z8) {
        mo24048o(z8);
        m24051m(z8);
    }
}
