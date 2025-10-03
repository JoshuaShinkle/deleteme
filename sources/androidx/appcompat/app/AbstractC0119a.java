package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import p010b.C0569j;
import p071g.AbstractC4796b;

/* renamed from: androidx.appcompat.app.a */
/* loaded from: classes.dex */
public abstract class AbstractC0119a {

    /* renamed from: androidx.appcompat.app.a$b */
    public interface b {
        void onMenuVisibilityChanged(boolean z8);
    }

    @Deprecated
    /* renamed from: androidx.appcompat.app.a$c */
    public static abstract class c {
        /* renamed from: a */
        public abstract CharSequence m383a();

        /* renamed from: b */
        public abstract View m384b();

        /* renamed from: c */
        public abstract Drawable m385c();

        /* renamed from: d */
        public abstract CharSequence m386d();

        /* renamed from: e */
        public abstract void m387e();
    }

    /* renamed from: f */
    public boolean m368f() {
        return false;
    }

    /* renamed from: g */
    public abstract boolean mo369g();

    /* renamed from: h */
    public abstract void mo370h(boolean z8);

    /* renamed from: i */
    public abstract int mo371i();

    /* renamed from: j */
    public abstract Context mo372j();

    /* renamed from: k */
    public boolean m373k() {
        return false;
    }

    /* renamed from: l */
    public abstract void mo374l(Configuration configuration);

    /* renamed from: m */
    public void m375m() {
    }

    /* renamed from: n */
    public abstract boolean mo376n(int i9, KeyEvent keyEvent);

    /* renamed from: o */
    public boolean m377o(KeyEvent keyEvent) {
        return false;
    }

    /* renamed from: p */
    public boolean m378p() {
        return false;
    }

    /* renamed from: q */
    public abstract void mo379q(boolean z8);

    /* renamed from: r */
    public abstract void mo380r(boolean z8);

    /* renamed from: s */
    public abstract void mo381s(CharSequence charSequence);

    /* renamed from: t */
    public abstract AbstractC4796b mo382t(AbstractC4796b.a aVar);

    /* renamed from: androidx.appcompat.app.a$a */
    public static class a extends ViewGroup.MarginLayoutParams {

        /* renamed from: a */
        public int f376a;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f376a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.ActionBarLayout);
            this.f376a = typedArrayObtainStyledAttributes.getInt(C0569j.ActionBarLayout_android_layout_gravity, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(int i9, int i10) {
            super(i9, i10);
            this.f376a = 8388627;
        }

        public a(a aVar) {
            super((ViewGroup.MarginLayoutParams) aVar);
            this.f376a = 0;
            this.f376a = aVar.f376a;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f376a = 0;
        }
    }
}
