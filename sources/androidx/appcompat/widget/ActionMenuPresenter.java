package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.AbstractC0132b;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.C0139i;
import androidx.appcompat.view.menu.C0142l;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.view.menu.InterfaceC0146p;
import androidx.appcompat.view.menu.SubMenuC0148r;
import androidx.appcompat.widget.ActionMenuView;
import java.util.ArrayList;
import p010b.C0560a;
import p010b.C0566g;
import p042d0.AbstractC4615b;
import p071g.C4795a;
import p224w.C6494a;

/* loaded from: classes.dex */
public class ActionMenuPresenter extends AbstractC0132b implements AbstractC4615b.a {

    /* renamed from: A */
    public C0157a f706A;

    /* renamed from: B */
    public RunnableC0159c f707B;

    /* renamed from: C */
    public C0158b f708C;

    /* renamed from: D */
    public final C0162f f709D;

    /* renamed from: E */
    public int f710E;

    /* renamed from: l */
    public C0160d f711l;

    /* renamed from: m */
    public Drawable f712m;

    /* renamed from: n */
    public boolean f713n;

    /* renamed from: o */
    public boolean f714o;

    /* renamed from: p */
    public boolean f715p;

    /* renamed from: q */
    public int f716q;

    /* renamed from: r */
    public int f717r;

    /* renamed from: s */
    public int f718s;

    /* renamed from: t */
    public boolean f719t;

    /* renamed from: u */
    public boolean f720u;

    /* renamed from: v */
    public boolean f721v;

    /* renamed from: w */
    public boolean f722w;

    /* renamed from: x */
    public int f723x;

    /* renamed from: y */
    public final SparseBooleanArray f724y;

    /* renamed from: z */
    public C0161e f725z;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0156a();

        /* renamed from: b */
        public int f726b;

        /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$SavedState$a */
        public class C0156a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            parcel.writeInt(this.f726b);
        }

        public SavedState(Parcel parcel) {
            this.f726b = parcel.readInt();
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$a */
    public class C0157a extends C0142l {
        public C0157a(Context context, SubMenuC0148r subMenuC0148r, View view) {
            super(context, subMenuC0148r, view, false, C0560a.actionOverflowMenuStyle);
            if (!((C0139i) subMenuC0148r.getItem()).m532l()) {
                View view2 = ActionMenuPresenter.this.f711l;
                m567f(view2 == null ? (View) ActionMenuPresenter.this.f502j : view2);
            }
            m571j(ActionMenuPresenter.this.f709D);
        }

        @Override // androidx.appcompat.view.menu.C0142l
        /* renamed from: e */
        public void mo566e() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.f706A = null;
            actionMenuPresenter.f710E = 0;
            super.mo566e();
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$b */
    public class C0158b extends ActionMenuItemView.AbstractC0130b {
        public C0158b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.AbstractC0130b
        /* renamed from: a */
        public InterfaceC0146p mo460a() {
            C0157a c0157a = ActionMenuPresenter.this.f706A;
            if (c0157a != null) {
                return c0157a.m564c();
            }
            return null;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$c */
    public class RunnableC0159c implements Runnable {

        /* renamed from: b */
        public C0161e f729b;

        public RunnableC0159c(C0161e c0161e) {
            this.f729b = c0161e;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ActionMenuPresenter.this.f496d != null) {
                ActionMenuPresenter.this.f496d.changeMenuMode();
            }
            View view = (View) ActionMenuPresenter.this.f502j;
            if (view != null && view.getWindowToken() != null && this.f729b.m574m()) {
                ActionMenuPresenter.this.f725z = this.f729b;
            }
            ActionMenuPresenter.this.f707B = null;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$d */
    public class C0160d extends AppCompatImageView implements ActionMenuView.InterfaceC0163a {

        /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$d$a */
        public class a extends AbstractViewOnTouchListenerC0218a0 {

            /* renamed from: k */
            public final /* synthetic */ ActionMenuPresenter f732k;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
                this.f732k = actionMenuPresenter;
            }

            @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
            /* renamed from: b */
            public InterfaceC0146p mo458b() {
                C0161e c0161e = ActionMenuPresenter.this.f725z;
                if (c0161e == null) {
                    return null;
                }
                return c0161e.m564c();
            }

            @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
            /* renamed from: c */
            public boolean mo459c() {
                ActionMenuPresenter.this.m619D();
                return true;
            }

            @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
            /* renamed from: d */
            public boolean mo632d() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.f707B != null) {
                    return false;
                }
                actionMenuPresenter.m624u();
                return true;
            }
        }

        public C0160d(Context context) {
            super(context, null, C0560a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            C0254s0.m1061a(this, getContentDescription());
            setOnTouchListener(new a(this, ActionMenuPresenter.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.InterfaceC0163a
        /* renamed from: a */
        public boolean mo453a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.InterfaceC0163a
        /* renamed from: b */
        public boolean mo454b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.m619D();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i9, int i10, int i11, int i12) {
            boolean frame = super.setFrame(i9, i10, i11, i12);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                C6494a.m24843f(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$e */
    public class C0161e extends C0142l {
        public C0161e(Context context, C0137g c0137g, View view, boolean z8) {
            super(context, c0137g, view, z8, C0560a.actionOverflowMenuStyle);
            m569h(8388613);
            m571j(ActionMenuPresenter.this.f709D);
        }

        @Override // androidx.appcompat.view.menu.C0142l
        /* renamed from: e */
        public void mo566e() {
            if (ActionMenuPresenter.this.f496d != null) {
                ActionMenuPresenter.this.f496d.close();
            }
            ActionMenuPresenter.this.f725z = null;
            super.mo566e();
        }
    }

    /* renamed from: androidx.appcompat.widget.ActionMenuPresenter$f */
    public class C0162f implements InterfaceC0143m.a {
        public C0162f() {
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        /* renamed from: a */
        public boolean mo352a(C0137g c0137g) {
            if (c0137g == ActionMenuPresenter.this.f496d) {
                return false;
            }
            ActionMenuPresenter.this.f710E = ((SubMenuC0148r) c0137g).getItem().getItemId();
            InterfaceC0143m.a aVarM478f = ActionMenuPresenter.this.m478f();
            if (aVarM478f != null) {
                return aVarM478f.mo352a(c0137g);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        public void onCloseMenu(C0137g c0137g, boolean z8) {
            if (c0137g instanceof SubMenuC0148r) {
                c0137g.getRootMenu().close(false);
            }
            InterfaceC0143m.a aVarM478f = ActionMenuPresenter.this.m478f();
            if (aVarM478f != null) {
                aVarM478f.onCloseMenu(c0137g, z8);
            }
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, C0566g.abc_action_menu_layout, C0566g.abc_action_menu_item_layout);
        this.f724y = new SparseBooleanArray();
        this.f709D = new C0162f();
    }

    /* renamed from: A */
    public void m616A(ActionMenuView actionMenuView) {
        this.f502j = actionMenuView;
        actionMenuView.initialize(this.f496d);
    }

    /* renamed from: B */
    public void m617B(Drawable drawable) {
        C0160d c0160d = this.f711l;
        if (c0160d != null) {
            c0160d.setImageDrawable(drawable);
        } else {
            this.f713n = true;
            this.f712m = drawable;
        }
    }

    /* renamed from: C */
    public void m618C(boolean z8) {
        this.f714o = z8;
        this.f715p = true;
    }

    /* renamed from: D */
    public boolean m619D() {
        C0137g c0137g;
        if (!this.f714o || m627x() || (c0137g = this.f496d) == null || this.f502j == null || this.f707B != null || c0137g.getNonActionItems().isEmpty()) {
            return false;
        }
        RunnableC0159c runnableC0159c = new RunnableC0159c(new C0161e(this.f495c, this.f496d, this.f711l, true));
        this.f707B = runnableC0159c;
        ((View) this.f502j).post(runnableC0159c);
        return true;
    }

    @Override // p042d0.AbstractC4615b.a
    /* renamed from: a */
    public void mo620a(boolean z8) {
        if (z8) {
            super.onSubMenuSelected(null);
            return;
        }
        C0137g c0137g = this.f496d;
        if (c0137g != null) {
            c0137g.close(false);
        }
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b
    /* renamed from: c */
    public void mo475c(C0139i c0139i, InterfaceC0144n.a aVar) {
        aVar.initialize(c0139i, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.f502j);
        if (this.f708C == null) {
            this.f708C = new C0158b();
        }
        actionMenuItemView.setPopupCallback(this.f708C);
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b
    /* renamed from: e */
    public boolean mo477e(ViewGroup viewGroup, int i9) {
        if (viewGroup.getChildAt(i9) == this.f711l) {
            return false;
        }
        return super.mo477e(viewGroup, i9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public boolean flagActionItems() {
        ArrayList<C0139i> visibleItems;
        int size;
        int i9;
        int iM633l;
        boolean z8;
        int i10;
        ActionMenuPresenter actionMenuPresenter = this;
        C0137g c0137g = actionMenuPresenter.f496d;
        View view = null;
        ?? r32 = 0;
        if (c0137g != null) {
            visibleItems = c0137g.getVisibleItems();
            size = visibleItems.size();
        } else {
            visibleItems = null;
            size = 0;
        }
        int i11 = actionMenuPresenter.f718s;
        int i12 = actionMenuPresenter.f717r;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.f502j;
        boolean z9 = false;
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < size; i15++) {
            C0139i c0139i = visibleItems.get(i15);
            if (c0139i.m535o()) {
                i13++;
            } else if (c0139i.m534n()) {
                i14++;
            } else {
                z9 = true;
            }
            if (actionMenuPresenter.f722w && c0139i.isActionViewExpanded()) {
                i11 = 0;
            }
        }
        if (actionMenuPresenter.f714o && (z9 || i14 + i13 > i11)) {
            i11--;
        }
        int i16 = i11 - i13;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.f724y;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.f720u) {
            int i17 = actionMenuPresenter.f723x;
            iM633l = i12 / i17;
            i9 = i17 + ((i12 % i17) / iM633l);
        } else {
            i9 = 0;
            iM633l = 0;
        }
        int i18 = 0;
        int i19 = 0;
        while (i18 < size) {
            C0139i c0139i2 = visibleItems.get(i18);
            if (c0139i2.m535o()) {
                View viewMo479g = actionMenuPresenter.mo479g(c0139i2, view, viewGroup);
                if (actionMenuPresenter.f720u) {
                    iM633l -= ActionMenuView.m633l(viewMo479g, i9, iM633l, iMakeMeasureSpec, r32);
                } else {
                    viewMo479g.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewMo479g.getMeasuredWidth();
                i12 -= measuredWidth;
                if (i19 == 0) {
                    i19 = measuredWidth;
                }
                int groupId = c0139i2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                c0139i2.m541u(true);
                z8 = r32;
                i10 = size;
            } else if (c0139i2.m534n()) {
                int groupId2 = c0139i2.getGroupId();
                boolean z10 = sparseBooleanArray.get(groupId2);
                boolean z11 = (i16 > 0 || z10) && i12 > 0 && (!actionMenuPresenter.f720u || iM633l > 0);
                boolean z12 = z11;
                i10 = size;
                if (z11) {
                    View viewMo479g2 = actionMenuPresenter.mo479g(c0139i2, null, viewGroup);
                    if (actionMenuPresenter.f720u) {
                        int iM633l2 = ActionMenuView.m633l(viewMo479g2, i9, iM633l, iMakeMeasureSpec, 0);
                        iM633l -= iM633l2;
                        if (iM633l2 == 0) {
                            z12 = false;
                        }
                    } else {
                        viewMo479g2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    }
                    boolean z13 = z12;
                    int measuredWidth2 = viewMo479g2.getMeasuredWidth();
                    i12 -= measuredWidth2;
                    if (i19 == 0) {
                        i19 = measuredWidth2;
                    }
                    z11 = z13 & (!actionMenuPresenter.f720u ? i12 + i19 <= 0 : i12 < 0);
                }
                if (z11 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z10) {
                    sparseBooleanArray.put(groupId2, false);
                    for (int i20 = 0; i20 < i18; i20++) {
                        C0139i c0139i3 = visibleItems.get(i20);
                        if (c0139i3.getGroupId() == groupId2) {
                            if (c0139i3.m532l()) {
                                i16++;
                            }
                            c0139i3.m541u(false);
                        }
                    }
                }
                if (z11) {
                    i16--;
                }
                c0139i2.m541u(z11);
                z8 = false;
            } else {
                z8 = r32;
                i10 = size;
                c0139i2.m541u(z8);
            }
            i18++;
            r32 = z8;
            size = i10;
            view = null;
            actionMenuPresenter = this;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b
    /* renamed from: g */
    public View mo479g(C0139i c0139i, View view, ViewGroup viewGroup) {
        View actionView = c0139i.getActionView();
        if (actionView == null || c0139i.m530j()) {
            actionView = super.mo479g(c0139i, view, viewGroup);
        }
        actionView.setVisibility(c0139i.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b
    /* renamed from: h */
    public InterfaceC0144n mo480h(ViewGroup viewGroup) {
        InterfaceC0144n interfaceC0144n = this.f502j;
        InterfaceC0144n interfaceC0144nMo480h = super.mo480h(viewGroup);
        if (interfaceC0144n != interfaceC0144nMo480h) {
            ((ActionMenuView) interfaceC0144nMo480h).setPresenter(this);
        }
        return interfaceC0144nMo480h;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b, androidx.appcompat.view.menu.InterfaceC0143m
    public void initForMenu(Context context, C0137g c0137g) {
        super.initForMenu(context, c0137g);
        Resources resources = context.getResources();
        C4795a c4795aM19032b = C4795a.m19032b(context);
        if (!this.f715p) {
            this.f714o = c4795aM19032b.m19039h();
        }
        if (!this.f721v) {
            this.f716q = c4795aM19032b.m19034c();
        }
        if (!this.f719t) {
            this.f718s = c4795aM19032b.m19035d();
        }
        int measuredWidth = this.f716q;
        if (this.f714o) {
            if (this.f711l == null) {
                C0160d c0160d = new C0160d(this.f494b);
                this.f711l = c0160d;
                if (this.f713n) {
                    c0160d.setImageDrawable(this.f712m);
                    this.f712m = null;
                    this.f713n = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f711l.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f711l.getMeasuredWidth();
        } else {
            this.f711l = null;
        }
        this.f717r = measuredWidth;
        this.f723x = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b
    /* renamed from: j */
    public boolean mo482j(int i9, C0139i c0139i) {
        return c0139i.m532l();
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b, androidx.appcompat.view.menu.InterfaceC0143m
    public void onCloseMenu(C0137g c0137g, boolean z8) {
        m621r();
        super.onCloseMenu(c0137g, z8);
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i9;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof SavedState) && (i9 = ((SavedState) parcelable).f726b) > 0 && (menuItemFindItem = this.f496d.findItem(i9)) != null) {
            onSubMenuSelected((SubMenuC0148r) menuItemFindItem.getSubMenu());
        }
    }

    @Override // androidx.appcompat.view.menu.InterfaceC0143m
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.f726b = this.f710E;
        return savedState;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b, androidx.appcompat.view.menu.InterfaceC0143m
    public boolean onSubMenuSelected(SubMenuC0148r subMenuC0148r) {
        boolean z8 = false;
        if (!subMenuC0148r.hasVisibleItems()) {
            return false;
        }
        SubMenuC0148r subMenuC0148r2 = subMenuC0148r;
        while (subMenuC0148r2.getParentMenu() != this.f496d) {
            subMenuC0148r2 = (SubMenuC0148r) subMenuC0148r2.getParentMenu();
        }
        View viewM622s = m622s(subMenuC0148r2.getItem());
        if (viewM622s == null) {
            return false;
        }
        this.f710E = subMenuC0148r.getItem().getItemId();
        int size = subMenuC0148r.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size) {
                break;
            }
            MenuItem item = subMenuC0148r.getItem(i9);
            if (item.isVisible() && item.getIcon() != null) {
                z8 = true;
                break;
            }
            i9++;
        }
        C0157a c0157a = new C0157a(this.f495c, subMenuC0148r, viewM622s);
        this.f706A = c0157a;
        c0157a.m568g(z8);
        this.f706A.m572k();
        super.onSubMenuSelected(subMenuC0148r);
        return true;
    }

    /* renamed from: r */
    public boolean m621r() {
        return m624u() | m625v();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: s */
    public final View m622s(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.f502j;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = viewGroup.getChildAt(i9);
            if ((childAt instanceof InterfaceC0144n.a) && ((InterfaceC0144n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: t */
    public Drawable m623t() {
        C0160d c0160d = this.f711l;
        if (c0160d != null) {
            return c0160d.getDrawable();
        }
        if (this.f713n) {
            return this.f712m;
        }
        return null;
    }

    /* renamed from: u */
    public boolean m624u() {
        Object obj;
        RunnableC0159c runnableC0159c = this.f707B;
        if (runnableC0159c != null && (obj = this.f502j) != null) {
            ((View) obj).removeCallbacks(runnableC0159c);
            this.f707B = null;
            return true;
        }
        C0161e c0161e = this.f725z;
        if (c0161e == null) {
            return false;
        }
        c0161e.m563b();
        return true;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0132b, androidx.appcompat.view.menu.InterfaceC0143m
    public void updateMenuView(boolean z8) {
        super.updateMenuView(z8);
        ((View) this.f502j).requestLayout();
        C0137g c0137g = this.f496d;
        boolean z9 = false;
        if (c0137g != null) {
            ArrayList<C0139i> actionItems = c0137g.getActionItems();
            int size = actionItems.size();
            for (int i9 = 0; i9 < size; i9++) {
                AbstractC4615b abstractC4615bMo469b = actionItems.get(i9).mo469b();
                if (abstractC4615bMo469b != null) {
                    abstractC4615bMo469b.m18394i(this);
                }
            }
        }
        C0137g c0137g2 = this.f496d;
        ArrayList<C0139i> nonActionItems = c0137g2 != null ? c0137g2.getNonActionItems() : null;
        if (this.f714o && nonActionItems != null) {
            int size2 = nonActionItems.size();
            if (size2 == 1) {
                z9 = !nonActionItems.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z9 = true;
            }
        }
        if (z9) {
            if (this.f711l == null) {
                this.f711l = new C0160d(this.f494b);
            }
            ViewGroup viewGroup = (ViewGroup) this.f711l.getParent();
            if (viewGroup != this.f502j) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.f711l);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f502j;
                actionMenuView.addView(this.f711l, actionMenuView.m638f());
            }
        } else {
            C0160d c0160d = this.f711l;
            if (c0160d != null) {
                Object parent = c0160d.getParent();
                Object obj = this.f502j;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f711l);
                }
            }
        }
        ((ActionMenuView) this.f502j).setOverflowReserved(this.f714o);
    }

    /* renamed from: v */
    public boolean m625v() {
        C0157a c0157a = this.f706A;
        if (c0157a == null) {
            return false;
        }
        c0157a.m563b();
        return true;
    }

    /* renamed from: w */
    public boolean m626w() {
        return this.f707B != null || m627x();
    }

    /* renamed from: x */
    public boolean m627x() {
        C0161e c0161e = this.f725z;
        return c0161e != null && c0161e.m565d();
    }

    /* renamed from: y */
    public void m628y(Configuration configuration) {
        if (!this.f719t) {
            this.f718s = C4795a.m19032b(this.f495c).m19035d();
        }
        C0137g c0137g = this.f496d;
        if (c0137g != null) {
            c0137g.onItemsChanged(true);
        }
    }

    /* renamed from: z */
    public void m629z(boolean z8) {
        this.f722w = z8;
    }
}
