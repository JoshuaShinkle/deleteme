package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import p020c.C0694a;
import p206u.C6348b;

/* renamed from: androidx.appcompat.widget.q0 */
/* loaded from: classes.dex */
public class C0250q0 {

    /* renamed from: a */
    public final Context f1192a;

    /* renamed from: b */
    public final TypedArray f1193b;

    /* renamed from: c */
    public TypedValue f1194c;

    public C0250q0(Context context, TypedArray typedArray) {
        this.f1192a = context;
        this.f1193b = typedArray;
    }

    /* renamed from: t */
    public static C0250q0 m1002t(Context context, int i9, int[] iArr) {
        return new C0250q0(context, context.obtainStyledAttributes(i9, iArr));
    }

    /* renamed from: u */
    public static C0250q0 m1003u(Context context, AttributeSet attributeSet, int[] iArr) {
        return new C0250q0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    /* renamed from: v */
    public static C0250q0 m1004v(Context context, AttributeSet attributeSet, int[] iArr, int i9, int i10) {
        return new C0250q0(context, context.obtainStyledAttributes(attributeSet, iArr, i9, i10));
    }

    /* renamed from: a */
    public boolean m1005a(int i9, boolean z8) {
        return this.f1193b.getBoolean(i9, z8);
    }

    /* renamed from: b */
    public int m1006b(int i9, int i10) {
        return this.f1193b.getColor(i9, i10);
    }

    /* renamed from: c */
    public ColorStateList m1007c(int i9) {
        int resourceId;
        ColorStateList colorStateListM3457a;
        return (!this.f1193b.hasValue(i9) || (resourceId = this.f1193b.getResourceId(i9, 0)) == 0 || (colorStateListM3457a = C0694a.m3457a(this.f1192a, resourceId)) == null) ? this.f1193b.getColorStateList(i9) : colorStateListM3457a;
    }

    /* renamed from: d */
    public float m1008d(int i9, float f9) {
        return this.f1193b.getDimension(i9, f9);
    }

    /* renamed from: e */
    public int m1009e(int i9, int i10) {
        return this.f1193b.getDimensionPixelOffset(i9, i10);
    }

    /* renamed from: f */
    public int m1010f(int i9, int i10) {
        return this.f1193b.getDimensionPixelSize(i9, i10);
    }

    /* renamed from: g */
    public Drawable m1011g(int i9) {
        int resourceId;
        return (!this.f1193b.hasValue(i9) || (resourceId = this.f1193b.getResourceId(i9, 0)) == 0) ? this.f1193b.getDrawable(i9) : C0694a.m3458b(this.f1192a, resourceId);
    }

    /* renamed from: h */
    public Drawable m1012h(int i9) {
        int resourceId;
        if (!this.f1193b.hasValue(i9) || (resourceId = this.f1193b.getResourceId(i9, 0)) == 0) {
            return null;
        }
        return C0227f.m819b().m824d(this.f1192a, resourceId, true);
    }

    /* renamed from: i */
    public float m1013i(int i9, float f9) {
        return this.f1193b.getFloat(i9, f9);
    }

    /* renamed from: j */
    public Typeface m1014j(int i9, int i10, C6348b.a aVar) {
        int resourceId = this.f1193b.getResourceId(i9, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f1194c == null) {
            this.f1194c = new TypedValue();
        }
        return C6348b.m24365c(this.f1192a, resourceId, this.f1194c, i10, aVar);
    }

    /* renamed from: k */
    public int m1015k(int i9, int i10) {
        return this.f1193b.getInt(i9, i10);
    }

    /* renamed from: l */
    public int m1016l(int i9, int i10) {
        return this.f1193b.getInteger(i9, i10);
    }

    /* renamed from: m */
    public int m1017m(int i9, int i10) {
        return this.f1193b.getLayoutDimension(i9, i10);
    }

    /* renamed from: n */
    public int m1018n(int i9, int i10) {
        return this.f1193b.getResourceId(i9, i10);
    }

    /* renamed from: o */
    public String m1019o(int i9) {
        return this.f1193b.getString(i9);
    }

    /* renamed from: p */
    public CharSequence m1020p(int i9) {
        return this.f1193b.getText(i9);
    }

    /* renamed from: q */
    public CharSequence[] m1021q(int i9) {
        return this.f1193b.getTextArray(i9);
    }

    /* renamed from: r */
    public TypedArray m1022r() {
        return this.f1193b;
    }

    /* renamed from: s */
    public boolean m1023s(int i9) {
        return this.f1193b.hasValue(i9);
    }

    /* renamed from: w */
    public void m1024w() {
        this.f1193b.recycle();
    }
}
