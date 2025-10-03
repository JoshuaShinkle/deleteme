package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.app.DialogInterfaceC0120b;
import androidx.appcompat.view.menu.InterfaceC0146p;
import p010b.C0560a;
import p010b.C0566g;
import p010b.C0569j;
import p020c.C0694a;
import p042d0.C4647u;
import p071g.C4798d;

/* loaded from: classes.dex */
public class AppCompatSpinner extends Spinner {

    /* renamed from: j */
    public static final int[] f802j = {R.attr.spinnerMode};

    /* renamed from: b */
    public final C0223d f803b;

    /* renamed from: c */
    public final Context f804c;

    /* renamed from: d */
    public AbstractViewOnTouchListenerC0218a0 f805d;

    /* renamed from: e */
    public SpinnerAdapter f806e;

    /* renamed from: f */
    public final boolean f807f;

    /* renamed from: g */
    public InterfaceC0181f f808g;

    /* renamed from: h */
    public int f809h;

    /* renamed from: i */
    public final Rect f810i;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0175a();

        /* renamed from: b */
        public boolean f811b;

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$SavedState$a */
        public class C0175a implements Parcelable.Creator<SavedState> {
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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeByte(this.f811b ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f811b = parcel.readByte() != 0;
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$a */
    public class C0176a extends AbstractViewOnTouchListenerC0218a0 {

        /* renamed from: k */
        public final /* synthetic */ C0180e f812k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0176a(View view, C0180e c0180e) {
            super(view);
            this.f812k = c0180e;
        }

        @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
        /* renamed from: b */
        public InterfaceC0146p mo458b() {
            return this.f812k;
        }

        @Override // androidx.appcompat.widget.AbstractViewOnTouchListenerC0218a0
        @SuppressLint({"SyntheticAccessor"})
        /* renamed from: c */
        public boolean mo459c() {
            if (AppCompatSpinner.this.getInternalPopup().mo668a()) {
                return true;
            }
            AppCompatSpinner.this.m665b();
            return true;
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$b */
    public class ViewTreeObserverOnGlobalLayoutListenerC0177b implements ViewTreeObserver.OnGlobalLayoutListener {
        public ViewTreeObserverOnGlobalLayoutListenerC0177b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!AppCompatSpinner.this.getInternalPopup().mo668a()) {
                AppCompatSpinner.this.m665b();
            }
            ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$c */
    public class DialogInterfaceOnClickListenerC0178c implements InterfaceC0181f, DialogInterface.OnClickListener {

        /* renamed from: b */
        public DialogInterfaceC0120b f815b;

        /* renamed from: c */
        public ListAdapter f816c;

        /* renamed from: d */
        public CharSequence f817d;

        public DialogInterfaceOnClickListenerC0178c() {
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: a */
        public boolean mo668a() {
            DialogInterfaceC0120b dialogInterfaceC0120b = this.f815b;
            if (dialogInterfaceC0120b != null) {
                return dialogInterfaceC0120b.isShowing();
            }
            return false;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: b */
        public int mo669b() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: d */
        public void mo670d(int i9) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        public void dismiss() {
            DialogInterfaceC0120b dialogInterfaceC0120b = this.f815b;
            if (dialogInterfaceC0120b != null) {
                dialogInterfaceC0120b.dismiss();
                this.f815b = null;
            }
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: e */
        public CharSequence mo671e() {
            return this.f817d;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: g */
        public Drawable mo672g() {
            return null;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: i */
        public void mo673i(CharSequence charSequence) {
            this.f817d = charSequence;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: j */
        public void mo674j(int i9) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: k */
        public void mo675k(int i9) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: l */
        public void mo676l(int i9, int i10) {
            if (this.f816c == null) {
                return;
            }
            DialogInterfaceC0120b.a aVar = new DialogInterfaceC0120b.a(AppCompatSpinner.this.getPopupContext());
            CharSequence charSequence = this.f817d;
            if (charSequence != null) {
                aVar.m397h(charSequence);
            }
            DialogInterfaceC0120b dialogInterfaceC0120bM390a = aVar.m396g(this.f816c, AppCompatSpinner.this.getSelectedItemPosition(), this).m390a();
            this.f815b = dialogInterfaceC0120bM390a;
            ListView listViewM389a = dialogInterfaceC0120bM390a.m389a();
            listViewM389a.setTextDirection(i9);
            listViewM389a.setTextAlignment(i10);
            this.f815b.show();
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: m */
        public int mo677m() {
            return 0;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: n */
        public void mo678n(ListAdapter listAdapter) {
            this.f816c = listAdapter;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i9) {
            AppCompatSpinner.this.setSelection(i9);
            if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                AppCompatSpinner.this.performItemClick(null, i9, this.f816c.getItemId(i9));
            }
            dismiss();
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        public void setBackgroundDrawable(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$d */
    public static class C0179d implements ListAdapter, SpinnerAdapter {

        /* renamed from: b */
        public SpinnerAdapter f819b;

        /* renamed from: c */
        public ListAdapter f820c;

        public C0179d(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f819b = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f820c = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof InterfaceC0242m0) {
                    InterfaceC0242m0 interfaceC0242m0 = (InterfaceC0242m0) spinnerAdapter;
                    if (interfaceC0242m0.getDropDownViewTheme() == null) {
                        interfaceC0242m0.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f820c;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f819b;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i9, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f819b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i9, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i9) {
            SpinnerAdapter spinnerAdapter = this.f819b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i9);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i9) {
            SpinnerAdapter spinnerAdapter = this.f819b;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i9);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i9) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i9, View view, ViewGroup viewGroup) {
            return getDropDownView(i9, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f819b;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i9) {
            ListAdapter listAdapter = this.f820c;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i9);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f819b;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f819b;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$e */
    public class C0180e extends ListPopupWindow implements InterfaceC0181f {

        /* renamed from: J */
        public CharSequence f821J;

        /* renamed from: K */
        public ListAdapter f822K;

        /* renamed from: L */
        public final Rect f823L;

        /* renamed from: M */
        public int f824M;

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$e$a */
        public class a implements AdapterView.OnItemClickListener {

            /* renamed from: b */
            public final /* synthetic */ AppCompatSpinner f826b;

            public a(AppCompatSpinner appCompatSpinner) {
                this.f826b = appCompatSpinner;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
                AppCompatSpinner.this.setSelection(i9);
                if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                    C0180e c0180e = C0180e.this;
                    AppCompatSpinner.this.performItemClick(view, i9, c0180e.f822K.getItemId(i9));
                }
                C0180e.this.dismiss();
            }
        }

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$e$b */
        public class b implements ViewTreeObserver.OnGlobalLayoutListener {
            public b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                C0180e c0180e = C0180e.this;
                if (!c0180e.m682O(AppCompatSpinner.this)) {
                    C0180e.this.dismiss();
                } else {
                    C0180e.this.m680M();
                    C0180e.super.show();
                }
            }
        }

        /* renamed from: androidx.appcompat.widget.AppCompatSpinner$e$c */
        public class c implements PopupWindow.OnDismissListener {

            /* renamed from: b */
            public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f829b;

            public c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f829b = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f829b);
                }
            }
        }

        public C0180e(Context context, AttributeSet attributeSet, int i9) {
            super(context, attributeSet, i9);
            this.f823L = new Rect();
            m712x(AppCompatSpinner.this);
            m690D(true);
            m695I(0);
            m692F(new a(AppCompatSpinner.this));
        }

        /* renamed from: M */
        public void m680M() {
            int i9;
            Drawable drawableM700g = m700g();
            if (drawableM700g != null) {
                drawableM700g.getPadding(AppCompatSpinner.this.f810i);
                i9 = C0258u0.m1068b(AppCompatSpinner.this) ? AppCompatSpinner.this.f810i.right : -AppCompatSpinner.this.f810i.left;
            } else {
                Rect rect = AppCompatSpinner.this.f810i;
                rect.right = 0;
                rect.left = 0;
                i9 = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
            int i10 = appCompatSpinner.f809h;
            if (i10 == -2) {
                int iM664a = appCompatSpinner.m664a((SpinnerAdapter) this.f822K, m700g());
                int i11 = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels;
                Rect rect2 = AppCompatSpinner.this.f810i;
                int i12 = (i11 - rect2.left) - rect2.right;
                if (iM664a > i12) {
                    iM664a = i12;
                }
                m714z(Math.max(iM664a, (width - paddingLeft) - paddingRight));
            } else if (i10 == -1) {
                m714z((width - paddingLeft) - paddingRight);
            } else {
                m714z(i10);
            }
            m699d(C0258u0.m1068b(AppCompatSpinner.this) ? i9 + (((width - paddingRight) - m708t()) - m681N()) : i9 + paddingLeft + m681N());
        }

        /* renamed from: N */
        public int m681N() {
            return this.f824M;
        }

        /* renamed from: O */
        public boolean m682O(View view) {
            return C4647u.m18512H(view) && view.getGlobalVisibleRect(this.f823L);
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: e */
        public CharSequence mo671e() {
            return this.f821J;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: i */
        public void mo673i(CharSequence charSequence) {
            this.f821J = charSequence;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: k */
        public void mo675k(int i9) {
            this.f824M = i9;
        }

        @Override // androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: l */
        public void mo676l(int i9, int i10) {
            ViewTreeObserver viewTreeObserver;
            boolean zMo488a = mo488a();
            m680M();
            m689C(2);
            super.show();
            ListView listViewMo492h = mo492h();
            listViewMo492h.setChoiceMode(1);
            listViewMo492h.setTextDirection(i9);
            listViewMo492h.setTextAlignment(i10);
            m696J(AppCompatSpinner.this.getSelectedItemPosition());
            if (zMo488a || (viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            m691E(new c(bVar));
        }

        @Override // androidx.appcompat.widget.ListPopupWindow, androidx.appcompat.widget.AppCompatSpinner.InterfaceC0181f
        /* renamed from: n */
        public void mo678n(ListAdapter listAdapter) {
            super.mo678n(listAdapter);
            this.f822K = listAdapter;
        }
    }

    /* renamed from: androidx.appcompat.widget.AppCompatSpinner$f */
    public interface InterfaceC0181f {
        /* renamed from: a */
        boolean mo668a();

        /* renamed from: b */
        int mo669b();

        /* renamed from: d */
        void mo670d(int i9);

        void dismiss();

        /* renamed from: e */
        CharSequence mo671e();

        /* renamed from: g */
        Drawable mo672g();

        /* renamed from: i */
        void mo673i(CharSequence charSequence);

        /* renamed from: j */
        void mo674j(int i9);

        /* renamed from: k */
        void mo675k(int i9);

        /* renamed from: l */
        void mo676l(int i9, int i10);

        /* renamed from: m */
        int mo677m();

        /* renamed from: n */
        void mo678n(ListAdapter listAdapter);

        void setBackgroundDrawable(Drawable drawable);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.spinnerStyle);
    }

    /* renamed from: a */
    public int m664a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i9 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i9) {
                view = null;
                i9 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        drawable.getPadding(this.f810i);
        Rect rect = this.f810i;
        return iMax2 + rect.left + rect.right;
    }

    /* renamed from: b */
    public void m665b() {
        this.f808g.mo676l(getTextDirection(), getTextAlignment());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0223d c0223d = this.f803b;
        if (c0223d != null) {
            c0223d.m799b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        InterfaceC0181f interfaceC0181f = this.f808g;
        return interfaceC0181f != null ? interfaceC0181f.mo669b() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        InterfaceC0181f interfaceC0181f = this.f808g;
        return interfaceC0181f != null ? interfaceC0181f.mo677m() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f808g != null ? this.f809h : super.getDropDownWidth();
    }

    public final InterfaceC0181f getInternalPopup() {
        return this.f808g;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        InterfaceC0181f interfaceC0181f = this.f808g;
        return interfaceC0181f != null ? interfaceC0181f.mo672g() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f804c;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        InterfaceC0181f interfaceC0181f = this.f808g;
        return interfaceC0181f != null ? interfaceC0181f.mo671e() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0223d c0223d = this.f803b;
        if (c0223d != null) {
            return c0223d.m800c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0223d c0223d = this.f803b;
        if (c0223d != null) {
            return c0223d.m801d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        InterfaceC0181f interfaceC0181f = this.f808g;
        if (interfaceC0181f == null || !interfaceC0181f.mo668a()) {
            return;
        }
        this.f808g.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i9, int i10) {
        super.onMeasure(i9, i10);
        if (this.f808g == null || View.MeasureSpec.getMode(i9) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), m664a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i9)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!savedState.f811b || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC0177b());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        InterfaceC0181f interfaceC0181f = this.f808g;
        savedState.f811b = interfaceC0181f != null && interfaceC0181f.mo668a();
        return savedState;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractViewOnTouchListenerC0218a0 abstractViewOnTouchListenerC0218a0 = this.f805d;
        if (abstractViewOnTouchListenerC0218a0 == null || !abstractViewOnTouchListenerC0218a0.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        InterfaceC0181f interfaceC0181f = this.f808g;
        if (interfaceC0181f == null) {
            return super.performClick();
        }
        if (interfaceC0181f.mo668a()) {
            return true;
        }
        m665b();
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0223d c0223d = this.f803b;
        if (c0223d != null) {
            c0223d.m803f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i9) {
        super.setBackgroundResource(i9);
        C0223d c0223d = this.f803b;
        if (c0223d != null) {
            c0223d.m804g(i9);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i9) {
        InterfaceC0181f interfaceC0181f = this.f808g;
        if (interfaceC0181f == null) {
            super.setDropDownHorizontalOffset(i9);
        } else {
            interfaceC0181f.mo675k(i9);
            this.f808g.mo670d(i9);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i9) {
        InterfaceC0181f interfaceC0181f = this.f808g;
        if (interfaceC0181f != null) {
            interfaceC0181f.mo674j(i9);
        } else {
            super.setDropDownVerticalOffset(i9);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i9) {
        if (this.f808g != null) {
            this.f809h = i9;
        } else {
            super.setDropDownWidth(i9);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        InterfaceC0181f interfaceC0181f = this.f808g;
        if (interfaceC0181f != null) {
            interfaceC0181f.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i9) {
        setPopupBackgroundDrawable(C0694a.m3458b(getPopupContext(), i9));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        InterfaceC0181f interfaceC0181f = this.f808g;
        if (interfaceC0181f != null) {
            interfaceC0181f.mo673i(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0223d c0223d = this.f803b;
        if (c0223d != null) {
            c0223d.m806i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0223d c0223d = this.f803b;
        if (c0223d != null) {
            c0223d.m807j(mode);
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i9) {
        this(context, attributeSet, i9, -1);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f807f) {
            this.f806e = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.f808g != null) {
            Context context = this.f804c;
            if (context == null) {
                context = getContext();
            }
            this.f808g.mo678n(new C0179d(spinnerAdapter, context.getTheme()));
        }
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i9, int i10) {
        this(context, attributeSet, i9, i10, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d9  */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v7, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.view.View, androidx.appcompat.widget.AppCompatSpinner] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i9, int i10, Resources.Theme theme) throws Throwable {
        TypedArray typedArrayObtainStyledAttributes;
        CharSequence[] charSequenceArrM1021q;
        SpinnerAdapter spinnerAdapter;
        super(context, attributeSet, i9);
        this.f810i = new Rect();
        C0240l0.m934a(this, getContext());
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, C0569j.Spinner, i9, 0);
        this.f803b = new C0223d(this);
        if (theme != null) {
            this.f804c = new C4798d(context, theme);
        } else {
            int iM1018n = c0250q0M1004v.m1018n(C0569j.Spinner_popupTheme, 0);
            if (iM1018n != 0) {
                this.f804c = new C4798d(context, iM1018n);
            } else {
                this.f804c = context;
            }
        }
        ?? r11 = -1;
        TypedArray typedArray = null;
        try {
            if (i10 == -1) {
                try {
                    typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f802j, i9, 0);
                    try {
                        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(0);
                        r11 = typedArrayObtainStyledAttributes;
                        if (zHasValue) {
                            i10 = typedArrayObtainStyledAttributes.getInt(0, 0);
                            r11 = typedArrayObtainStyledAttributes;
                        }
                    } catch (Exception e9) {
                        e = e9;
                        Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e);
                        r11 = typedArrayObtainStyledAttributes;
                        if (typedArrayObtainStyledAttributes != null) {
                            r11.recycle();
                        }
                        if (i10 != 0) {
                        }
                        charSequenceArrM1021q = c0250q0M1004v.m1021q(C0569j.Spinner_android_entries);
                        if (charSequenceArrM1021q != null) {
                        }
                        c0250q0M1004v.m1024w();
                        this.f807f = true;
                        spinnerAdapter = this.f806e;
                        if (spinnerAdapter != null) {
                        }
                        this.f803b.m802e(attributeSet, i9);
                    }
                } catch (Exception e10) {
                    e = e10;
                    typedArrayObtainStyledAttributes = null;
                } catch (Throwable th) {
                    th = th;
                    if (typedArray != null) {
                        typedArray.recycle();
                    }
                    throw th;
                }
                r11.recycle();
            }
            if (i10 != 0) {
                DialogInterfaceOnClickListenerC0178c dialogInterfaceOnClickListenerC0178c = new DialogInterfaceOnClickListenerC0178c();
                this.f808g = dialogInterfaceOnClickListenerC0178c;
                dialogInterfaceOnClickListenerC0178c.mo673i(c0250q0M1004v.m1019o(C0569j.Spinner_android_prompt));
            } else if (i10 == 1) {
                C0180e c0180e = new C0180e(this.f804c, attributeSet, i9);
                C0250q0 c0250q0M1004v2 = C0250q0.m1004v(this.f804c, attributeSet, C0569j.Spinner, i9, 0);
                this.f809h = c0250q0M1004v2.m1017m(C0569j.Spinner_android_dropDownWidth, -2);
                c0180e.setBackgroundDrawable(c0250q0M1004v2.m1011g(C0569j.Spinner_android_popupBackground));
                c0180e.mo673i(c0250q0M1004v.m1019o(C0569j.Spinner_android_prompt));
                c0250q0M1004v2.m1024w();
                this.f808g = c0180e;
                this.f805d = new C0176a(this, c0180e);
            }
            charSequenceArrM1021q = c0250q0M1004v.m1021q(C0569j.Spinner_android_entries);
            if (charSequenceArrM1021q != null) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner_item, charSequenceArrM1021q);
                arrayAdapter.setDropDownViewResource(C0566g.support_simple_spinner_dropdown_item);
                setAdapter(arrayAdapter);
            }
            c0250q0M1004v.m1024w();
            this.f807f = true;
            spinnerAdapter = this.f806e;
            if (spinnerAdapter != null) {
                setAdapter(spinnerAdapter);
                this.f806e = null;
            }
            this.f803b.m802e(attributeSet, i9);
        } catch (Throwable th2) {
            th = th2;
            typedArray = r11;
        }
    }
}
