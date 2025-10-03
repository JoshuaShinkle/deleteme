package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p010b.C0569j;
import p042d0.C4647u;
import p224w.C6494a;

/* renamed from: androidx.appcompat.widget.j */
/* loaded from: classes.dex */
public class C0235j extends C0233i {

    /* renamed from: d */
    public final SeekBar f1095d;

    /* renamed from: e */
    public Drawable f1096e;

    /* renamed from: f */
    public ColorStateList f1097f;

    /* renamed from: g */
    public PorterDuff.Mode f1098g;

    /* renamed from: h */
    public boolean f1099h;

    /* renamed from: i */
    public boolean f1100i;

    public C0235j(SeekBar seekBar) {
        super(seekBar);
        this.f1097f = null;
        this.f1098g = null;
        this.f1099h = false;
        this.f1100i = false;
        this.f1095d = seekBar;
    }

    @Override // androidx.appcompat.widget.C0233i
    /* renamed from: c */
    public void mo885c(AttributeSet attributeSet, int i9) {
        super.mo885c(attributeSet, i9);
        Context context = this.f1095d.getContext();
        int[] iArr = C0569j.AppCompatSeekBar;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, iArr, i9, 0);
        SeekBar seekBar = this.f1095d;
        C4647u.m18528X(seekBar, seekBar.getContext(), iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        Drawable drawableM1012h = c0250q0M1004v.m1012h(C0569j.AppCompatSeekBar_android_thumb);
        if (drawableM1012h != null) {
            this.f1095d.setThumb(drawableM1012h);
        }
        m899j(c0250q0M1004v.m1011g(C0569j.AppCompatSeekBar_tickMark));
        int i10 = C0569j.AppCompatSeekBar_tickMarkTintMode;
        if (c0250q0M1004v.m1023s(i10)) {
            this.f1098g = C0262x.m1076d(c0250q0M1004v.m1015k(i10, -1), this.f1098g);
            this.f1100i = true;
        }
        int i11 = C0569j.AppCompatSeekBar_tickMarkTint;
        if (c0250q0M1004v.m1023s(i11)) {
            this.f1097f = c0250q0M1004v.m1007c(i11);
            this.f1099h = true;
        }
        c0250q0M1004v.m1024w();
        m895f();
    }

    /* renamed from: f */
    public final void m895f() {
        Drawable drawable = this.f1096e;
        if (drawable != null) {
            if (this.f1099h || this.f1100i) {
                Drawable drawableM24849l = C6494a.m24849l(drawable.mutate());
                this.f1096e = drawableM24849l;
                if (this.f1099h) {
                    C6494a.m24846i(drawableM24849l, this.f1097f);
                }
                if (this.f1100i) {
                    C6494a.m24847j(this.f1096e, this.f1098g);
                }
                if (this.f1096e.isStateful()) {
                    this.f1096e.setState(this.f1095d.getDrawableState());
                }
            }
        }
    }

    /* renamed from: g */
    public void m896g(Canvas canvas) {
        if (this.f1096e != null) {
            int max = this.f1095d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1096e.getIntrinsicWidth();
                int intrinsicHeight = this.f1096e.getIntrinsicHeight();
                int i9 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i10 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1096e.setBounds(-i9, -i10, i9, i10);
                float width = ((this.f1095d.getWidth() - this.f1095d.getPaddingLeft()) - this.f1095d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f1095d.getPaddingLeft(), this.f1095d.getHeight() / 2);
                for (int i11 = 0; i11 <= max; i11++) {
                    this.f1096e.draw(canvas);
                    canvas.translate(width, BitmapDescriptorFactory.HUE_RED);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    /* renamed from: h */
    public void m897h() {
        Drawable drawable = this.f1096e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1095d.getDrawableState())) {
            this.f1095d.invalidateDrawable(drawable);
        }
    }

    /* renamed from: i */
    public void m898i() {
        Drawable drawable = this.f1096e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* renamed from: j */
    public void m899j(Drawable drawable) {
        Drawable drawable2 = this.f1096e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1096e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1095d);
            C6494a.m24844g(drawable, C4647u.m18567s(this.f1095d));
            if (drawable.isStateful()) {
                drawable.setState(this.f1095d.getDrawableState());
            }
            m895f();
        }
        this.f1095d.invalidate();
    }
}
