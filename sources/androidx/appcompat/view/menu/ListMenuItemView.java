package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.widget.C0250q0;
import p010b.C0560a;
import p010b.C0565f;
import p010b.C0566g;
import p010b.C0569j;
import p042d0.C4647u;

/* loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements InterfaceC0144n.a, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: b */
    public C0139i f457b;

    /* renamed from: c */
    public ImageView f458c;

    /* renamed from: d */
    public RadioButton f459d;

    /* renamed from: e */
    public TextView f460e;

    /* renamed from: f */
    public CheckBox f461f;

    /* renamed from: g */
    public TextView f462g;

    /* renamed from: h */
    public ImageView f463h;

    /* renamed from: i */
    public ImageView f464i;

    /* renamed from: j */
    public LinearLayout f465j;

    /* renamed from: k */
    public Drawable f466k;

    /* renamed from: l */
    public int f467l;

    /* renamed from: m */
    public Context f468m;

    /* renamed from: n */
    public boolean f469n;

    /* renamed from: o */
    public Drawable f470o;

    /* renamed from: p */
    public boolean f471p;

    /* renamed from: q */
    public LayoutInflater f472q;

    /* renamed from: r */
    public boolean f473r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.listMenuViewStyle);
    }

    private LayoutInflater getInflater() {
        if (this.f472q == null) {
            this.f472q = LayoutInflater.from(getContext());
        }
        return this.f472q;
    }

    private void setSubMenuArrowVisible(boolean z8) {
        ImageView imageView = this.f463h;
        if (imageView != null) {
            imageView.setVisibility(z8 ? 0 : 8);
        }
    }

    /* renamed from: a */
    public final void m462a(View view) {
        m463b(view, -1);
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f464i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f464i.getLayoutParams();
        rect.top += this.f464i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    /* renamed from: b */
    public final void m463b(View view, int i9) {
        LinearLayout linearLayout = this.f465j;
        if (linearLayout != null) {
            linearLayout.addView(view, i9);
        } else {
            addView(view, i9);
        }
    }

    /* renamed from: c */
    public final void m464c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(C0566g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.f461f = checkBox;
        m462a(checkBox);
    }

    /* renamed from: d */
    public final void m465d() {
        ImageView imageView = (ImageView) getInflater().inflate(C0566g.abc_list_menu_item_icon, (ViewGroup) this, false);
        this.f458c = imageView;
        m463b(imageView, 0);
    }

    /* renamed from: e */
    public final void m466e() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(C0566g.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.f459d = radioButton;
        m462a(radioButton);
    }

    /* renamed from: f */
    public void m467f(boolean z8, char c9) {
        int i9 = (z8 && this.f457b.m522A()) ? 0 : 8;
        if (i9 == 0) {
            this.f462g.setText(this.f457b.m528h());
        }
        if (this.f462g.getVisibility() != i9) {
            this.f462g.setVisibility(i9);
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public C0139i getItemData() {
        return this.f457b;
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public void initialize(C0139i c0139i, int i9) {
        this.f457b = c0139i;
        setVisibility(c0139i.isVisible() ? 0 : 8);
        setTitle(c0139i.m529i(this));
        setCheckable(c0139i.isCheckable());
        m467f(c0139i.m522A(), c0139i.m527g());
        setIcon(c0139i.getIcon());
        setEnabled(c0139i.isEnabled());
        setSubMenuArrowVisible(c0139i.hasSubMenu());
        setContentDescription(c0139i.getContentDescription());
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        C4647u.m18534b0(this, this.f466k);
        TextView textView = (TextView) findViewById(C0565f.title);
        this.f460e = textView;
        int i9 = this.f467l;
        if (i9 != -1) {
            textView.setTextAppearance(this.f468m, i9);
        }
        this.f462g = (TextView) findViewById(C0565f.shortcut);
        ImageView imageView = (ImageView) findViewById(C0565f.submenuarrow);
        this.f463h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f470o);
        }
        this.f464i = (ImageView) findViewById(C0565f.group_divider);
        this.f465j = (LinearLayout) findViewById(C0565f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i9, int i10) {
        if (this.f458c != null && this.f469n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f458c.getLayoutParams();
            int i11 = layoutParams.height;
            if (i11 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i11;
            }
        }
        super.onMeasure(i9, i10);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0144n.a
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean z8) {
        CompoundButton compoundButton;
        View view;
        if (!z8 && this.f459d == null && this.f461f == null) {
            return;
        }
        if (this.f457b.m533m()) {
            if (this.f459d == null) {
                m466e();
            }
            compoundButton = this.f459d;
            view = this.f461f;
        } else {
            if (this.f461f == null) {
                m464c();
            }
            compoundButton = this.f461f;
            view = this.f459d;
        }
        if (z8) {
            compoundButton.setChecked(this.f457b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f461f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f459d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z8) {
        CompoundButton compoundButton;
        if (this.f457b.m533m()) {
            if (this.f459d == null) {
                m466e();
            }
            compoundButton = this.f459d;
        } else {
            if (this.f461f == null) {
                m464c();
            }
            compoundButton = this.f461f;
        }
        compoundButton.setChecked(z8);
    }

    public void setForceShowIcon(boolean z8) {
        this.f473r = z8;
        this.f469n = z8;
    }

    public void setGroupDividerEnabled(boolean z8) {
        ImageView imageView = this.f464i;
        if (imageView != null) {
            imageView.setVisibility((this.f471p || !z8) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z8 = this.f457b.m546z() || this.f473r;
        if (z8 || this.f469n) {
            ImageView imageView = this.f458c;
            if (imageView == null && drawable == null && !this.f469n) {
                return;
            }
            if (imageView == null) {
                m465d();
            }
            if (drawable == null && !this.f469n) {
                this.f458c.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f458c;
            if (!z8) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f458c.getVisibility() != 0) {
                this.f458c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f460e.getVisibility() != 8) {
                this.f460e.setVisibility(8);
            }
        } else {
            this.f460e.setText(charSequence);
            if (this.f460e.getVisibility() != 0) {
                this.f460e.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet);
        C0250q0 c0250q0M1004v = C0250q0.m1004v(getContext(), attributeSet, C0569j.MenuView, i9, 0);
        this.f466k = c0250q0M1004v.m1011g(C0569j.MenuView_android_itemBackground);
        this.f467l = c0250q0M1004v.m1018n(C0569j.MenuView_android_itemTextAppearance, -1);
        this.f469n = c0250q0M1004v.m1005a(C0569j.MenuView_preserveIconSpacing, false);
        this.f468m = context;
        this.f470o = c0250q0M1004v.m1011g(C0569j.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, C0560a.dropDownListViewStyle, 0);
        this.f471p = typedArrayObtainStyledAttributes.hasValue(0);
        c0250q0M1004v.m1024w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
