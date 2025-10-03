package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p161p.C5867b;

/* loaded from: classes.dex */
public class Constraints extends ViewGroup {

    /* renamed from: b */
    public C0300a f1624b;

    public Constraints(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        m1413c(attributeSet);
        super.setVisibility(8);
    }

    @Override // android.view.ViewGroup
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C0299a generateDefaultLayoutParams() {
        return new C0299a(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public C0299a generateLayoutParams(AttributeSet attributeSet) {
        return new C0299a(getContext(), attributeSet);
    }

    /* renamed from: c */
    public final void m1413c(AttributeSet attributeSet) {
        Log.v("Constraints", " ################# init");
    }

    public C0300a getConstraintSet() {
        if (this.f1624b == null) {
            this.f1624b = new C0300a();
        }
        this.f1624b.m1419b(this);
        return this.f1624b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.C0298a(layoutParams);
    }

    /* renamed from: androidx.constraintlayout.widget.Constraints$a */
    public static class C0299a extends ConstraintLayout.C0298a {

        /* renamed from: n0 */
        public float f1625n0;

        /* renamed from: o0 */
        public boolean f1626o0;

        /* renamed from: p0 */
        public float f1627p0;

        /* renamed from: q0 */
        public float f1628q0;

        /* renamed from: r0 */
        public float f1629r0;

        /* renamed from: s0 */
        public float f1630s0;

        /* renamed from: t0 */
        public float f1631t0;

        /* renamed from: u0 */
        public float f1632u0;

        /* renamed from: v0 */
        public float f1633v0;

        /* renamed from: w0 */
        public float f1634w0;

        /* renamed from: x0 */
        public float f1635x0;

        /* renamed from: y0 */
        public float f1636y0;

        /* renamed from: z0 */
        public float f1637z0;

        public C0299a(int i9, int i10) {
            super(i9, i10);
            this.f1625n0 = 1.0f;
            this.f1626o0 = false;
            this.f1627p0 = BitmapDescriptorFactory.HUE_RED;
            this.f1628q0 = BitmapDescriptorFactory.HUE_RED;
            this.f1629r0 = BitmapDescriptorFactory.HUE_RED;
            this.f1630s0 = BitmapDescriptorFactory.HUE_RED;
            this.f1631t0 = 1.0f;
            this.f1632u0 = 1.0f;
            this.f1633v0 = BitmapDescriptorFactory.HUE_RED;
            this.f1634w0 = BitmapDescriptorFactory.HUE_RED;
            this.f1635x0 = BitmapDescriptorFactory.HUE_RED;
            this.f1636y0 = BitmapDescriptorFactory.HUE_RED;
            this.f1637z0 = BitmapDescriptorFactory.HUE_RED;
        }

        public C0299a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1625n0 = 1.0f;
            this.f1626o0 = false;
            this.f1627p0 = BitmapDescriptorFactory.HUE_RED;
            this.f1628q0 = BitmapDescriptorFactory.HUE_RED;
            this.f1629r0 = BitmapDescriptorFactory.HUE_RED;
            this.f1630s0 = BitmapDescriptorFactory.HUE_RED;
            this.f1631t0 = 1.0f;
            this.f1632u0 = 1.0f;
            this.f1633v0 = BitmapDescriptorFactory.HUE_RED;
            this.f1634w0 = BitmapDescriptorFactory.HUE_RED;
            this.f1635x0 = BitmapDescriptorFactory.HUE_RED;
            this.f1636y0 = BitmapDescriptorFactory.HUE_RED;
            this.f1637z0 = BitmapDescriptorFactory.HUE_RED;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5867b.ConstraintSet);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i9 = 0; i9 < indexCount; i9++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i9);
                if (index == C5867b.ConstraintSet_android_alpha) {
                    this.f1625n0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1625n0);
                } else if (index == C5867b.ConstraintSet_android_elevation) {
                    this.f1627p0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1627p0);
                    this.f1626o0 = true;
                } else if (index == C5867b.ConstraintSet_android_rotationX) {
                    this.f1629r0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1629r0);
                } else if (index == C5867b.ConstraintSet_android_rotationY) {
                    this.f1630s0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1630s0);
                } else if (index == C5867b.ConstraintSet_android_rotation) {
                    this.f1628q0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1628q0);
                } else if (index == C5867b.ConstraintSet_android_scaleX) {
                    this.f1631t0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1631t0);
                } else if (index == C5867b.ConstraintSet_android_scaleY) {
                    this.f1632u0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1632u0);
                } else if (index == C5867b.ConstraintSet_android_transformPivotX) {
                    this.f1633v0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1633v0);
                } else if (index == C5867b.ConstraintSet_android_transformPivotY) {
                    this.f1634w0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1634w0);
                } else if (index == C5867b.ConstraintSet_android_translationX) {
                    this.f1635x0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1635x0);
                } else if (index == C5867b.ConstraintSet_android_translationY) {
                    this.f1636y0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1636y0);
                } else if (index == C5867b.ConstraintSet_android_translationZ) {
                    this.f1635x0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1637z0);
                }
            }
        }
    }
}
