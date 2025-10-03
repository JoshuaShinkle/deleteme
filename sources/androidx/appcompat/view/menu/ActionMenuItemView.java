package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.C0254s0;
import p010b.C0569j;

/* loaded from: classes.dex */
public class ActionMenuItemView extends AppCompatTextView implements InterfaceC0144n.a, View.OnClickListener, ActionMenuView.InterfaceC0163a {

    /* renamed from: f */
    public C0139i f442f;

    /* renamed from: g */
    public CharSequence f443g;

    /* renamed from: h */
    public Drawable f444h;

    /* renamed from: i */
    public C0137g.b f445i;

    /* renamed from: j */
    public AbstractViewOnTouchListenerC0218a0 f446j;

    /* renamed from: k */
    public AbstractC0130b f447k;

    /* renamed from: l */
    public boolean f448l;

    /* renamed from: m */
    public boolean f449m;

    /* renamed from: n */
    public int f450n;

    /* renamed from: o */
    public int f451o;

    /* renamed from: p */
    public int f452p;

    /* renamed from: androidx.appcompat.view.menu.ActionMenuItemView$a */
    public class C0129a extends AbstractViewOnTouchListenerC0218a0 {
        public C0129a() {
            super(ActionMenuItemView.this);
        }

        @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
        /* renamed from: b */
        public InterfaceC0146p mo458b() {
            AbstractC0130b abstractC0130b = ActionMenuItemView.this.f447k;
            if (abstractC0130b != null) {
                return abstractC0130b.mo460a();
            }
            return null;
        }

        @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
        /* renamed from: c */
        public boolean mo459c() {
            InterfaceC0146p interfaceC0146pMo458b;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            C0137g.b bVar = actionMenuItemView.f445i;
            return bVar != null && bVar.mo461a(actionMenuItemView.f442f) && (interfaceC0146pMo458b = mo458b()) != null && interfaceC0146pMo458b.mo488a();
        }
    }

    /* renamed from: androidx.appcompat.view.menu.ActionMenuItemView$b */
    public static abstract class AbstractC0130b {
        /* renamed from: a */
        public abstract InterfaceC0146p mo460a();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // androidx.appcompat.widget.ActionMenuView.InterfaceC0163a
    /* renamed from: a */
    public boolean mo453a() {
        return m455d();
    }

    @Override // androidx.appcompat.widget.ActionMenuView.InterfaceC0163a
    /* renamed from: b */
    public boolean mo454b() {
        return m455d() && this.f442f.getIcon() == null;
    }

    /* renamed from: d */
    public boolean m455d() {
        return !TextUtils.isEmpty(getText());
    }

    /* renamed from: e */
    public final boolean m456e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i9 = configuration.screenWidthDp;
        return i9 >= 480 || (i9 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    /* renamed from: f */
    public final void m457f() {
        boolean z8 = true;
        boolean z9 = !TextUtils.isEmpty(this.f443g);
        if (this.f444h != null && (!this.f442f.m523B() || (!this.f448l && !this.f449m))) {
            z8 = false;
        }
        boolean z10 = z9 & z8;
        setText(z10 ? this.f443g : null);
        CharSequence contentDescription = this.f442f.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            setContentDescription(z10 ? null : this.f442f.getTitle());
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.f442f.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            C0254s0.m1061a(this, z10 ? null : this.f442f.getTitle());
        } else {
            C0254s0.m1061a(this, tooltipText);
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public C0139i getItemData() {
        return this.f442f;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public void initialize(C0139i c0139i, int i9) {
        this.f442f = c0139i;
        setIcon(c0139i.getIcon());
        setTitle(c0139i.m529i(this));
        setId(c0139i.getItemId());
        setVisibility(c0139i.isVisible() ? 0 : 8);
        setEnabled(c0139i.isEnabled());
        if (c0139i.hasSubMenu() && this.f446j == null) {
            this.f446j = new C0129a();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        C0137g.b bVar = this.f445i;
        if (bVar != null) {
            bVar.mo461a(this.f442f);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f448l = m456e();
        m457f();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i9, int i10) {
        int i11;
        boolean zM455d = m455d();
        if (zM455d && (i11 = this.f451o) >= 0) {
            super.setPadding(i11, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i9, i10);
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        int measuredWidth = getMeasuredWidth();
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, this.f450n) : this.f450n;
        if (mode != 1073741824 && this.f450n > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i10);
        }
        if (zM455d || this.f444h == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f444h.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractViewOnTouchListenerC0218a0 abstractViewOnTouchListenerC0218a0;
        if (this.f442f.hasSubMenu() && (abstractViewOnTouchListenerC0218a0 = this.f446j) != null && abstractViewOnTouchListenerC0218a0.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public boolean prefersCondensedTitle() {
        return true;
    }

    public void setCheckable(boolean z8) {
    }

    public void setChecked(boolean z8) {
    }

    public void setExpandedFormat(boolean z8) {
        if (this.f449m != z8) {
            this.f449m = z8;
            C0139i c0139i = this.f442f;
            if (c0139i != null) {
                c0139i.m524c();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f444h = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i9 = this.f452p;
            if (intrinsicWidth > i9) {
                intrinsicHeight = (int) (intrinsicHeight * (i9 / intrinsicWidth));
                intrinsicWidth = i9;
            }
            if (intrinsicHeight > i9) {
                intrinsicWidth = (int) (intrinsicWidth * (i9 / intrinsicHeight));
            } else {
                i9 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i9);
        }
        setCompoundDrawables(drawable, null, null, null);
        m457f();
    }

    public void setItemInvoker(C0137g.b bVar) {
        this.f445i = bVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i9, int i10, int i11, int i12) {
        this.f451o = i9;
        super.setPadding(i9, i10, i11, i12);
    }

    public void setPopupCallback(AbstractC0130b abstractC0130b) {
        this.f447k = abstractC0130b;
    }

    public void setTitle(CharSequence charSequence) {
        this.f443g = charSequence;
        m457f();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        Resources resources = context.getResources();
        this.f448l = m456e();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0569j.ActionMenuItemView, i9, 0);
        this.f450n = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0569j.ActionMenuItemView_android_minWidth, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f452p = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f451o = -1;
        setSaveEnabled(false);
    }
}
