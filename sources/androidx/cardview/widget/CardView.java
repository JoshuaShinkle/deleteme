package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import p122l.C5258a;
import p122l.C5259b;
import p122l.C5260c;
import p122l.C5261d;

/* loaded from: classes.dex */
public class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = {R.attr.colorBackground};
    private static final InterfaceC0270c IMPL;
    private final InterfaceC0269b mCardViewDelegate;
    private boolean mCompatPadding;
    final Rect mContentPadding;
    private boolean mPreventCornerOverlap;
    final Rect mShadowBounds;
    int mUserSetMinHeight;
    int mUserSetMinWidth;

    /* renamed from: androidx.cardview.widget.CardView$a */
    public class C0267a implements InterfaceC0269b {

        /* renamed from: a */
        public Drawable f1249a;

        public C0267a() {
        }

        @Override // androidx.cardview.widget.InterfaceC0269b
        /* renamed from: a */
        public void mo1099a(Drawable drawable) {
            this.f1249a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // androidx.cardview.widget.InterfaceC0269b
        /* renamed from: b */
        public boolean mo1100b() {
            return CardView.this.getPreventCornerOverlap();
        }

        @Override // androidx.cardview.widget.InterfaceC0269b
        /* renamed from: c */
        public boolean mo1101c() {
            return CardView.this.getUseCompatPadding();
        }

        @Override // androidx.cardview.widget.InterfaceC0269b
        /* renamed from: d */
        public Drawable mo1102d() {
            return this.f1249a;
        }

        @Override // androidx.cardview.widget.InterfaceC0269b
        /* renamed from: e */
        public View mo1103e() {
            return CardView.this;
        }

        @Override // androidx.cardview.widget.InterfaceC0269b
        public void setShadowPadding(int i9, int i10, int i11, int i12) {
            CardView.this.mShadowBounds.set(i9, i10, i11, i12);
            CardView cardView = CardView.this;
            Rect rect = cardView.mContentPadding;
            CardView.super.setPadding(i9 + rect.left, i10 + rect.top, i11 + rect.right, i12 + rect.bottom);
        }
    }

    static {
        C0268a c0268a = new C0268a();
        IMPL = c0268a;
        c0268a.mo1113j();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return IMPL.mo1111h(this.mCardViewDelegate);
    }

    public float getCardElevation() {
        return IMPL.mo1106c(this.mCardViewDelegate);
    }

    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    public float getMaxCardElevation() {
        return IMPL.mo1110g(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public float getRadius() {
        return IMPL.mo1107d(this.mCardViewDelegate);
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        if (IMPL instanceof C0268a) {
            super.onMeasure(i9, i10);
            return;
        }
        int mode = View.MeasureSpec.getMode(i9);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i9 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.mo1115l(this.mCardViewDelegate)), View.MeasureSpec.getSize(i9)), mode);
        }
        int mode2 = View.MeasureSpec.getMode(i10);
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            i10 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.mo1114k(this.mCardViewDelegate)), View.MeasureSpec.getSize(i10)), mode2);
        }
        super.onMeasure(i9, i10);
    }

    public void setCardBackgroundColor(int i9) {
        IMPL.mo1117n(this.mCardViewDelegate, ColorStateList.valueOf(i9));
    }

    public void setCardElevation(float f9) {
        IMPL.mo1109f(this.mCardViewDelegate, f9);
    }

    public void setContentPadding(int i9, int i10, int i11, int i12) {
        this.mContentPadding.set(i9, i10, i11, i12);
        IMPL.mo1112i(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float f9) {
        IMPL.mo1118o(this.mCardViewDelegate, f9);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i9) {
        this.mUserSetMinHeight = i9;
        super.setMinimumHeight(i9);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i9) {
        this.mUserSetMinWidth = i9;
        super.setMinimumWidth(i9);
    }

    @Override // android.view.View
    public void setPadding(int i9, int i10, int i11, int i12) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i9, int i10, int i11, int i12) {
    }

    public void setPreventCornerOverlap(boolean z8) {
        if (z8 != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = z8;
            IMPL.mo1116m(this.mCardViewDelegate);
        }
    }

    public void setRadius(float f9) {
        IMPL.mo1105b(this.mCardViewDelegate, f9);
    }

    public void setUseCompatPadding(boolean z8) {
        if (this.mCompatPadding != z8) {
            this.mCompatPadding = z8;
            IMPL.mo1108e(this.mCardViewDelegate);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C5258a.cardViewStyle);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        IMPL.mo1117n(this.mCardViewDelegate, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i9) throws Resources.NotFoundException {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i9);
        Rect rect = new Rect();
        this.mContentPadding = rect;
        this.mShadowBounds = new Rect();
        C0267a c0267a = new C0267a();
        this.mCardViewDelegate = c0267a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5261d.CardView, i9, C5260c.CardView);
        int i10 = C5261d.CardView_cardBackgroundColor;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(i10);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(C5259b.cardview_light_background);
            } else {
                color = getResources().getColor(C5259b.cardview_dark_background);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = colorStateListValueOf;
        float dimension = typedArrayObtainStyledAttributes.getDimension(C5261d.CardView_cardCornerRadius, BitmapDescriptorFactory.HUE_RED);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(C5261d.CardView_cardElevation, BitmapDescriptorFactory.HUE_RED);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(C5261d.CardView_cardMaxElevation, BitmapDescriptorFactory.HUE_RED);
        this.mCompatPadding = typedArrayObtainStyledAttributes.getBoolean(C5261d.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = typedArrayObtainStyledAttributes.getBoolean(C5261d.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(C5261d.CardView_contentPadding, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(C5261d.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(C5261d.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(C5261d.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(C5261d.CardView_contentPaddingBottom, dimensionPixelSize);
        float f9 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.mUserSetMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(C5261d.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(C5261d.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        IMPL.mo1104a(c0267a, context, colorStateList, dimension, dimension2, f9);
    }
}
