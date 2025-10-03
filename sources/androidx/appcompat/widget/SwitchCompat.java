package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import androidx.core.widget.C0337l;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.reflect.InvocationTargetException;
import p010b.C0569j;
import p020c.C0694a;
import p042d0.C4647u;
import p061f.C4773a;
import p224w.C6494a;

/* loaded from: classes.dex */
public class SwitchCompat extends CompoundButton {

    /* renamed from: O */
    public static final Property<SwitchCompat, Float> f965O = new C0208a(Float.class, "thumbPos");

    /* renamed from: P */
    public static final int[] f966P = {R.attr.state_checked};

    /* renamed from: A */
    public int f967A;

    /* renamed from: B */
    public int f968B;

    /* renamed from: C */
    public int f969C;

    /* renamed from: D */
    public int f970D;

    /* renamed from: E */
    public int f971E;

    /* renamed from: F */
    public int f972F;

    /* renamed from: G */
    public final TextPaint f973G;

    /* renamed from: H */
    public ColorStateList f974H;

    /* renamed from: I */
    public Layout f975I;

    /* renamed from: J */
    public Layout f976J;

    /* renamed from: K */
    public TransformationMethod f977K;

    /* renamed from: L */
    public ObjectAnimator f978L;

    /* renamed from: M */
    public final C0241m f979M;

    /* renamed from: N */
    public final Rect f980N;

    /* renamed from: b */
    public Drawable f981b;

    /* renamed from: c */
    public ColorStateList f982c;

    /* renamed from: d */
    public PorterDuff.Mode f983d;

    /* renamed from: e */
    public boolean f984e;

    /* renamed from: f */
    public boolean f985f;

    /* renamed from: g */
    public Drawable f986g;

    /* renamed from: h */
    public ColorStateList f987h;

    /* renamed from: i */
    public PorterDuff.Mode f988i;

    /* renamed from: j */
    public boolean f989j;

    /* renamed from: k */
    public boolean f990k;

    /* renamed from: l */
    public int f991l;

    /* renamed from: m */
    public int f992m;

    /* renamed from: n */
    public int f993n;

    /* renamed from: o */
    public boolean f994o;

    /* renamed from: p */
    public CharSequence f995p;

    /* renamed from: q */
    public CharSequence f996q;

    /* renamed from: r */
    public boolean f997r;

    /* renamed from: s */
    public int f998s;

    /* renamed from: t */
    public int f999t;

    /* renamed from: u */
    public float f1000u;

    /* renamed from: v */
    public float f1001v;

    /* renamed from: w */
    public VelocityTracker f1002w;

    /* renamed from: x */
    public int f1003x;

    /* renamed from: y */
    public float f1004y;

    /* renamed from: z */
    public int f1005z;

    /* renamed from: androidx.appcompat.widget.SwitchCompat$a */
    public class C0208a extends Property<SwitchCompat, Float> {
        public C0208a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(SwitchCompat switchCompat) {
            return Float.valueOf(switchCompat.f1004y);
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(SwitchCompat switchCompat, Float f9) {
            switchCompat.setThumbPosition(f9.floatValue());
        }
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f982c = null;
        this.f983d = null;
        this.f984e = false;
        this.f985f = false;
        this.f987h = null;
        this.f988i = null;
        this.f989j = false;
        this.f990k = false;
        this.f1002w = VelocityTracker.obtain();
        this.f980N = new Rect();
        C0240l0.m934a(this, getContext());
        TextPaint textPaint = new TextPaint(1);
        this.f973G = textPaint;
        textPaint.density = getResources().getDisplayMetrics().density;
        int[] iArr = C0569j.SwitchCompat;
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, iArr, i9, 0);
        C4647u.m18528X(this, context, iArr, attributeSet, c0250q0M1004v.m1022r(), i9, 0);
        Drawable drawableM1011g = c0250q0M1004v.m1011g(C0569j.SwitchCompat_android_thumb);
        this.f981b = drawableM1011g;
        if (drawableM1011g != null) {
            drawableM1011g.setCallback(this);
        }
        Drawable drawableM1011g2 = c0250q0M1004v.m1011g(C0569j.SwitchCompat_track);
        this.f986g = drawableM1011g2;
        if (drawableM1011g2 != null) {
            drawableM1011g2.setCallback(this);
        }
        this.f995p = c0250q0M1004v.m1020p(C0569j.SwitchCompat_android_textOn);
        this.f996q = c0250q0M1004v.m1020p(C0569j.SwitchCompat_android_textOff);
        this.f997r = c0250q0M1004v.m1005a(C0569j.SwitchCompat_showText, true);
        this.f991l = c0250q0M1004v.m1010f(C0569j.SwitchCompat_thumbTextPadding, 0);
        this.f992m = c0250q0M1004v.m1010f(C0569j.SwitchCompat_switchMinWidth, 0);
        this.f993n = c0250q0M1004v.m1010f(C0569j.SwitchCompat_switchPadding, 0);
        this.f994o = c0250q0M1004v.m1005a(C0569j.SwitchCompat_splitTrack, false);
        ColorStateList colorStateListM1007c = c0250q0M1004v.m1007c(C0569j.SwitchCompat_thumbTint);
        if (colorStateListM1007c != null) {
            this.f982c = colorStateListM1007c;
            this.f984e = true;
        }
        PorterDuff.Mode modeM1076d = C0262x.m1076d(c0250q0M1004v.m1015k(C0569j.SwitchCompat_thumbTintMode, -1), null);
        if (this.f983d != modeM1076d) {
            this.f983d = modeM1076d;
            this.f985f = true;
        }
        if (this.f984e || this.f985f) {
            m765b();
        }
        ColorStateList colorStateListM1007c2 = c0250q0M1004v.m1007c(C0569j.SwitchCompat_trackTint);
        if (colorStateListM1007c2 != null) {
            this.f987h = colorStateListM1007c2;
            this.f989j = true;
        }
        PorterDuff.Mode modeM1076d2 = C0262x.m1076d(c0250q0M1004v.m1015k(C0569j.SwitchCompat_trackTintMode, -1), null);
        if (this.f988i != modeM1076d2) {
            this.f988i = modeM1076d2;
            this.f990k = true;
        }
        if (this.f989j || this.f990k) {
            m766c();
        }
        int iM1018n = c0250q0M1004v.m1018n(C0569j.SwitchCompat_switchTextAppearance, 0);
        if (iM1018n != 0) {
            m771i(context, iM1018n);
        }
        C0241m c0241m = new C0241m(this);
        this.f979M = c0241m;
        c0241m.m954m(attributeSet, i9);
        c0250q0M1004v.m1024w();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f999t = viewConfiguration.getScaledTouchSlop();
        this.f1003x = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    /* renamed from: f */
    public static float m763f(float f9, float f10, float f11) {
        return f9 < f10 ? f10 : f9 > f11 ? f11 : f9;
    }

    private boolean getTargetCheckedState() {
        return this.f1004y > 0.5f;
    }

    private int getThumbOffset() {
        return (int) (((C0258u0.m1068b(this) ? 1.0f - this.f1004y : this.f1004y) * getThumbScrollRange()) + 0.5f);
    }

    private int getThumbScrollRange() {
        Drawable drawable = this.f986g;
        if (drawable == null) {
            return 0;
        }
        Rect rect = this.f980N;
        drawable.getPadding(rect);
        Drawable drawable2 = this.f981b;
        Rect rectM1075c = drawable2 != null ? C0262x.m1075c(drawable2) : C0262x.f1222c;
        return ((((this.f1005z - this.f968B) - rect.left) - rect.right) - rectM1075c.left) - rectM1075c.right;
    }

    /* renamed from: a */
    public final void m764a(boolean z8) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, f965O, z8 ? 1.0f : BitmapDescriptorFactory.HUE_RED);
        this.f978L = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(250L);
        this.f978L.setAutoCancel(true);
        this.f978L.start();
    }

    /* renamed from: b */
    public final void m765b() {
        Drawable drawable = this.f981b;
        if (drawable != null) {
            if (this.f984e || this.f985f) {
                Drawable drawableMutate = C6494a.m24849l(drawable).mutate();
                this.f981b = drawableMutate;
                if (this.f984e) {
                    C6494a.m24846i(drawableMutate, this.f982c);
                }
                if (this.f985f) {
                    C6494a.m24847j(this.f981b, this.f983d);
                }
                if (this.f981b.isStateful()) {
                    this.f981b.setState(getDrawableState());
                }
            }
        }
    }

    /* renamed from: c */
    public final void m766c() {
        Drawable drawable = this.f986g;
        if (drawable != null) {
            if (this.f989j || this.f990k) {
                Drawable drawableMutate = C6494a.m24849l(drawable).mutate();
                this.f986g = drawableMutate;
                if (this.f989j) {
                    C6494a.m24846i(drawableMutate, this.f987h);
                }
                if (this.f990k) {
                    C6494a.m24847j(this.f986g, this.f988i);
                }
                if (this.f986g.isStateful()) {
                    this.f986g.setState(getDrawableState());
                }
            }
        }
    }

    /* renamed from: d */
    public final void m767d() {
        ObjectAnimator objectAnimator = this.f978L;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int i9;
        int i10;
        Rect rect = this.f980N;
        int i11 = this.f969C;
        int i12 = this.f970D;
        int i13 = this.f971E;
        int i14 = this.f972F;
        int thumbOffset = getThumbOffset() + i11;
        Drawable drawable = this.f981b;
        Rect rectM1075c = drawable != null ? C0262x.m1075c(drawable) : C0262x.f1222c;
        Drawable drawable2 = this.f986g;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            int i15 = rect.left;
            thumbOffset += i15;
            if (rectM1075c != null) {
                int i16 = rectM1075c.left;
                if (i16 > i15) {
                    i11 += i16 - i15;
                }
                int i17 = rectM1075c.top;
                int i18 = rect.top;
                i9 = i17 > i18 ? (i17 - i18) + i12 : i12;
                int i19 = rectM1075c.right;
                int i20 = rect.right;
                if (i19 > i20) {
                    i13 -= i19 - i20;
                }
                int i21 = rectM1075c.bottom;
                int i22 = rect.bottom;
                if (i21 > i22) {
                    i10 = i14 - (i21 - i22);
                }
                this.f986g.setBounds(i11, i9, i13, i10);
            } else {
                i9 = i12;
            }
            i10 = i14;
            this.f986g.setBounds(i11, i9, i13, i10);
        }
        Drawable drawable3 = this.f981b;
        if (drawable3 != null) {
            drawable3.getPadding(rect);
            int i23 = thumbOffset - rect.left;
            int i24 = thumbOffset + this.f968B + rect.right;
            this.f981b.setBounds(i23, i12, i24, i14);
            Drawable background = getBackground();
            if (background != null) {
                C6494a.m24843f(background, i23, i12, i24, i14);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableHotspotChanged(float f9, float f10) {
        super.drawableHotspotChanged(f9, f10);
        Drawable drawable = this.f981b;
        if (drawable != null) {
            C6494a.m24842e(drawable, f9, f10);
        }
        Drawable drawable2 = this.f986g;
        if (drawable2 != null) {
            C6494a.m24842e(drawable2, f9, f10);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f981b;
        boolean state = false;
        if (drawable != null && drawable.isStateful()) {
            state = false | drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f986g;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    /* renamed from: e */
    public final void m768e(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setAction(3);
        super.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }

    /* renamed from: g */
    public final boolean m769g(float f9, float f10) {
        if (this.f981b == null) {
            return false;
        }
        int thumbOffset = getThumbOffset();
        this.f981b.getPadding(this.f980N);
        int i9 = this.f970D;
        int i10 = this.f999t;
        int i11 = i9 - i10;
        int i12 = (this.f969C + thumbOffset) - i10;
        int i13 = this.f968B + i12;
        Rect rect = this.f980N;
        return f9 > ((float) i12) && f9 < ((float) (((i13 + rect.left) + rect.right) + i10)) && f10 > ((float) i11) && f10 < ((float) (this.f972F + i10));
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (!C0258u0.m1068b(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.f1005z;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.f993n : compoundPaddingLeft;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (C0258u0.m1068b(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.f1005z;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.f993n : compoundPaddingRight;
    }

    public boolean getShowText() {
        return this.f997r;
    }

    public boolean getSplitTrack() {
        return this.f994o;
    }

    public int getSwitchMinWidth() {
        return this.f992m;
    }

    public int getSwitchPadding() {
        return this.f993n;
    }

    public CharSequence getTextOff() {
        return this.f996q;
    }

    public CharSequence getTextOn() {
        return this.f995p;
    }

    public Drawable getThumbDrawable() {
        return this.f981b;
    }

    public int getThumbTextPadding() {
        return this.f991l;
    }

    public ColorStateList getThumbTintList() {
        return this.f982c;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f983d;
    }

    public Drawable getTrackDrawable() {
        return this.f986g;
    }

    public ColorStateList getTrackTintList() {
        return this.f987h;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.f988i;
    }

    /* renamed from: h */
    public final Layout m770h(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.f977K;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, this);
        }
        CharSequence charSequence2 = charSequence;
        return new StaticLayout(charSequence2, this.f973G, charSequence2 != null ? (int) Math.ceil(Layout.getDesiredWidth(charSequence2, r2)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, true);
    }

    /* renamed from: i */
    public void m771i(Context context, int i9) {
        C0250q0 c0250q0M1002t = C0250q0.m1002t(context, i9, C0569j.TextAppearance);
        ColorStateList colorStateListM1007c = c0250q0M1002t.m1007c(C0569j.TextAppearance_android_textColor);
        if (colorStateListM1007c != null) {
            this.f974H = colorStateListM1007c;
        } else {
            this.f974H = getTextColors();
        }
        int iM1010f = c0250q0M1002t.m1010f(C0569j.TextAppearance_android_textSize, 0);
        if (iM1010f != 0) {
            float f9 = iM1010f;
            if (f9 != this.f973G.getTextSize()) {
                this.f973G.setTextSize(f9);
                requestLayout();
            }
        }
        m773k(c0250q0M1002t.m1015k(C0569j.TextAppearance_android_typeface, -1), c0250q0M1002t.m1015k(C0569j.TextAppearance_android_textStyle, -1));
        if (c0250q0M1002t.m1005a(C0569j.TextAppearance_textAllCaps, false)) {
            this.f977K = new C4773a(getContext());
        } else {
            this.f977K = null;
        }
        c0250q0M1002t.m1024w();
    }

    /* renamed from: j */
    public void m772j(Typeface typeface, int i9) {
        float f9 = BitmapDescriptorFactory.HUE_RED;
        if (i9 <= 0) {
            this.f973G.setFakeBoldText(false);
            this.f973G.setTextSkewX(BitmapDescriptorFactory.HUE_RED);
            setSwitchTypeface(typeface);
            return;
        }
        Typeface typefaceDefaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i9) : Typeface.create(typeface, i9);
        setSwitchTypeface(typefaceDefaultFromStyle);
        int i10 = (~(typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0)) & i9;
        this.f973G.setFakeBoldText((i10 & 1) != 0);
        TextPaint textPaint = this.f973G;
        if ((i10 & 2) != 0) {
            f9 = -0.25f;
        }
        textPaint.setTextSkewX(f9);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f981b;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f986g;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.f978L;
        if (objectAnimator == null || !objectAnimator.isStarted()) {
            return;
        }
        this.f978L.end();
        this.f978L = null;
    }

    /* renamed from: k */
    public final void m773k(int i9, int i10) {
        m772j(i9 != 1 ? i9 != 2 ? i9 != 3 ? null : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF, i10);
    }

    /* renamed from: l */
    public final void m774l(MotionEvent motionEvent) {
        this.f998s = 0;
        boolean targetCheckedState = true;
        boolean z8 = motionEvent.getAction() == 1 && isEnabled();
        boolean zIsChecked = isChecked();
        if (z8) {
            this.f1002w.computeCurrentVelocity(CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT);
            float xVelocity = this.f1002w.getXVelocity();
            if (Math.abs(xVelocity) <= this.f1003x) {
                targetCheckedState = getTargetCheckedState();
            } else if (!C0258u0.m1068b(this) ? xVelocity <= BitmapDescriptorFactory.HUE_RED : xVelocity >= BitmapDescriptorFactory.HUE_RED) {
                targetCheckedState = false;
            }
        } else {
            targetCheckedState = zIsChecked;
        }
        if (targetCheckedState != zIsChecked) {
            playSoundEffect(0);
        }
        setChecked(targetCheckedState);
        m768e(motionEvent);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i9) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i9 + 1);
        if (isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f966P);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int width;
        super.onDraw(canvas);
        Rect rect = this.f980N;
        Drawable drawable = this.f986g;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i9 = this.f970D;
        int i10 = this.f972F;
        int i11 = i9 + rect.top;
        int i12 = i10 - rect.bottom;
        Drawable drawable2 = this.f981b;
        if (drawable != null) {
            if (!this.f994o || drawable2 == null) {
                drawable.draw(canvas);
            } else {
                Rect rectM1075c = C0262x.m1075c(drawable2);
                drawable2.copyBounds(rect);
                rect.left += rectM1075c.left;
                rect.right -= rectM1075c.right;
                int iSave = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        int iSave2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = getTargetCheckedState() ? this.f975I : this.f976J;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.f974H;
            if (colorStateList != null) {
                this.f973G.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.f973G.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                width = bounds.left + bounds.right;
            } else {
                width = getWidth();
            }
            canvas.translate((width / 2) - (layout.getWidth() / 2), ((i11 + i12) / 2) - (layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(iSave2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        CharSequence charSequence = isChecked() ? this.f995p : this.f996q;
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        CharSequence text = accessibilityNodeInfo.getText();
        if (TextUtils.isEmpty(text)) {
            accessibilityNodeInfo.setText(charSequence);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        sb.append(' ');
        sb.append(charSequence);
        accessibilityNodeInfo.setText(sb);
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int iMax;
        int width;
        int paddingLeft;
        int i13;
        int paddingTop;
        int height;
        super.onLayout(z8, i9, i10, i11, i12);
        int iMax2 = 0;
        if (this.f981b != null) {
            Rect rect = this.f980N;
            Drawable drawable = this.f986g;
            if (drawable != null) {
                drawable.getPadding(rect);
            } else {
                rect.setEmpty();
            }
            Rect rectM1075c = C0262x.m1075c(this.f981b);
            iMax = Math.max(0, rectM1075c.left - rect.left);
            iMax2 = Math.max(0, rectM1075c.right - rect.right);
        } else {
            iMax = 0;
        }
        if (C0258u0.m1068b(this)) {
            paddingLeft = getPaddingLeft() + iMax;
            width = ((this.f1005z + paddingLeft) - iMax) - iMax2;
        } else {
            width = (getWidth() - getPaddingRight()) - iMax2;
            paddingLeft = (width - this.f1005z) + iMax + iMax2;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop2 = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2;
            i13 = this.f967A;
            paddingTop = paddingTop2 - (i13 / 2);
        } else {
            if (gravity == 80) {
                height = getHeight() - getPaddingBottom();
                paddingTop = height - this.f967A;
                this.f969C = paddingLeft;
                this.f970D = paddingTop;
                this.f972F = height;
                this.f971E = width;
            }
            paddingTop = getPaddingTop();
            i13 = this.f967A;
        }
        height = i13 + paddingTop;
        this.f969C = paddingLeft;
        this.f970D = paddingTop;
        this.f972F = height;
        this.f971E = width;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i9, int i10) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int intrinsicWidth;
        int intrinsicHeight;
        if (this.f997r) {
            if (this.f975I == null) {
                this.f975I = m770h(this.f995p);
            }
            if (this.f976J == null) {
                this.f976J = m770h(this.f996q);
            }
        }
        Rect rect = this.f980N;
        Drawable drawable = this.f981b;
        int intrinsicHeight2 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            intrinsicWidth = (this.f981b.getIntrinsicWidth() - rect.left) - rect.right;
            intrinsicHeight = this.f981b.getIntrinsicHeight();
        } else {
            intrinsicWidth = 0;
            intrinsicHeight = 0;
        }
        this.f968B = Math.max(this.f997r ? Math.max(this.f975I.getWidth(), this.f976J.getWidth()) + (this.f991l * 2) : 0, intrinsicWidth);
        Drawable drawable2 = this.f986g;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            intrinsicHeight2 = this.f986g.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int iMax = rect.left;
        int iMax2 = rect.right;
        Drawable drawable3 = this.f981b;
        if (drawable3 != null) {
            Rect rectM1075c = C0262x.m1075c(drawable3);
            iMax = Math.max(iMax, rectM1075c.left);
            iMax2 = Math.max(iMax2, rectM1075c.right);
        }
        int iMax3 = Math.max(this.f992m, (this.f968B * 2) + iMax + iMax2);
        int iMax4 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.f1005z = iMax3;
        this.f967A = iMax4;
        super.onMeasure(i9, i10);
        if (getMeasuredHeight() < iMax4) {
            setMeasuredDimension(getMeasuredWidthAndState(), iMax4);
        }
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence = isChecked() ? this.f995p : this.f996q;
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0089  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f1002w.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x8 = motionEvent.getX();
            float y8 = motionEvent.getY();
            if (isEnabled() && m769g(x8, y8)) {
                this.f998s = 1;
                this.f1000u = x8;
                this.f1001v = y8;
            }
        } else if (actionMasked == 1) {
            if (this.f998s == 2) {
                m774l(motionEvent);
                super.onTouchEvent(motionEvent);
                return true;
            }
            this.f998s = 0;
            this.f1002w.clear();
        } else if (actionMasked == 2) {
            int i9 = this.f998s;
            if (i9 == 1) {
                float x9 = motionEvent.getX();
                float y9 = motionEvent.getY();
                if (Math.abs(x9 - this.f1000u) > this.f999t || Math.abs(y9 - this.f1001v) > this.f999t) {
                    this.f998s = 2;
                    getParent().requestDisallowInterceptTouchEvent(true);
                    this.f1000u = x9;
                    this.f1001v = y9;
                    return true;
                }
            } else if (i9 == 2) {
                float x10 = motionEvent.getX();
                int thumbScrollRange = getThumbScrollRange();
                float f9 = x10 - this.f1000u;
                float f10 = thumbScrollRange != 0 ? f9 / thumbScrollRange : f9 > BitmapDescriptorFactory.HUE_RED ? 1.0f : -1.0f;
                if (C0258u0.m1068b(this)) {
                    f10 = -f10;
                }
                float fM763f = m763f(this.f1004y + f10, BitmapDescriptorFactory.HUE_RED, 1.0f);
                if (fM763f != this.f1004y) {
                    this.f1000u = x10;
                    setThumbPosition(fM763f);
                }
                return true;
            }
        } else if (actionMasked == 3) {
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z8) {
        super.setChecked(z8);
        boolean zIsChecked = isChecked();
        if (getWindowToken() != null && C4647u.m18513I(this)) {
            m764a(zIsChecked);
        } else {
            m767d();
            setThumbPosition(zIsChecked ? 1.0f : BitmapDescriptorFactory.HUE_RED);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(C0337l.m1622q(this, callback));
    }

    public void setShowText(boolean z8) {
        if (this.f997r != z8) {
            this.f997r = z8;
            requestLayout();
        }
    }

    public void setSplitTrack(boolean z8) {
        this.f994o = z8;
        invalidate();
    }

    public void setSwitchMinWidth(int i9) {
        this.f992m = i9;
        requestLayout();
    }

    public void setSwitchPadding(int i9) {
        this.f993n = i9;
        requestLayout();
    }

    public void setSwitchTypeface(Typeface typeface) {
        if ((this.f973G.getTypeface() == null || this.f973G.getTypeface().equals(typeface)) && (this.f973G.getTypeface() != null || typeface == null)) {
            return;
        }
        this.f973G.setTypeface(typeface);
        requestLayout();
        invalidate();
    }

    public void setTextOff(CharSequence charSequence) {
        this.f996q = charSequence;
        requestLayout();
    }

    public void setTextOn(CharSequence charSequence) {
        this.f995p = charSequence;
        requestLayout();
    }

    public void setThumbDrawable(Drawable drawable) {
        Drawable drawable2 = this.f981b;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f981b = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbPosition(float f9) {
        this.f1004y = f9;
        invalidate();
    }

    public void setThumbResource(int i9) {
        setThumbDrawable(C0694a.m3458b(getContext(), i9));
    }

    public void setThumbTextPadding(int i9) {
        this.f991l = i9;
        requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f982c = colorStateList;
        this.f984e = true;
        m765b();
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f983d = mode;
        this.f985f = true;
        m765b();
    }

    public void setTrackDrawable(Drawable drawable) {
        Drawable drawable2 = this.f986g;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f986g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setTrackResource(int i9) {
        setTrackDrawable(C0694a.m3458b(getContext(), i9));
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        this.f987h = colorStateList;
        this.f989j = true;
        m766c();
    }

    public void setTrackTintMode(PorterDuff.Mode mode) {
        this.f988i = mode;
        this.f990k = true;
        m766c();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f981b || drawable == this.f986g;
    }
}
