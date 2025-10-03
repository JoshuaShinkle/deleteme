package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.gms.actions.SearchIntents;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import net.sqlcipher.database.SQLiteDatabase;
import p010b.C0560a;
import p010b.C0563d;
import p010b.C0565f;
import p010b.C0566g;
import p010b.C0567h;
import p010b.C0569j;
import p042d0.C4647u;
import p062f0.AbstractC4774a;
import p071g.InterfaceC4797c;

/* loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements InterfaceC4797c {

    /* renamed from: W */
    public static final C0206n f892W;

    /* renamed from: A */
    public AbstractC4774a f893A;

    /* renamed from: B */
    public boolean f894B;

    /* renamed from: C */
    public CharSequence f895C;

    /* renamed from: D */
    public boolean f896D;

    /* renamed from: E */
    public boolean f897E;

    /* renamed from: F */
    public int f898F;

    /* renamed from: G */
    public boolean f899G;

    /* renamed from: H */
    public CharSequence f900H;

    /* renamed from: I */
    public CharSequence f901I;

    /* renamed from: J */
    public boolean f902J;

    /* renamed from: K */
    public int f903K;

    /* renamed from: L */
    public SearchableInfo f904L;

    /* renamed from: M */
    public Bundle f905M;

    /* renamed from: N */
    public final Runnable f906N;

    /* renamed from: O */
    public Runnable f907O;

    /* renamed from: P */
    public final WeakHashMap<String, Drawable.ConstantState> f908P;

    /* renamed from: Q */
    public final View.OnClickListener f909Q;

    /* renamed from: R */
    public View.OnKeyListener f910R;

    /* renamed from: S */
    public final TextView.OnEditorActionListener f911S;

    /* renamed from: T */
    public final AdapterView.OnItemClickListener f912T;

    /* renamed from: U */
    public final AdapterView.OnItemSelectedListener f913U;

    /* renamed from: V */
    public TextWatcher f914V;

    /* renamed from: b */
    public final SearchAutoComplete f915b;

    /* renamed from: c */
    public final View f916c;

    /* renamed from: d */
    public final View f917d;

    /* renamed from: e */
    public final View f918e;

    /* renamed from: f */
    public final ImageView f919f;

    /* renamed from: g */
    public final ImageView f920g;

    /* renamed from: h */
    public final ImageView f921h;

    /* renamed from: i */
    public final ImageView f922i;

    /* renamed from: j */
    public final View f923j;

    /* renamed from: k */
    public C0207o f924k;

    /* renamed from: l */
    public Rect f925l;

    /* renamed from: m */
    public Rect f926m;

    /* renamed from: n */
    public int[] f927n;

    /* renamed from: o */
    public int[] f928o;

    /* renamed from: p */
    public final ImageView f929p;

    /* renamed from: q */
    public final Drawable f930q;

    /* renamed from: r */
    public final int f931r;

    /* renamed from: s */
    public final int f932s;

    /* renamed from: t */
    public final Intent f933t;

    /* renamed from: u */
    public final Intent f934u;

    /* renamed from: v */
    public final CharSequence f935v;

    /* renamed from: w */
    public View.OnFocusChangeListener f936w;

    /* renamed from: x */
    public View.OnClickListener f937x;

    /* renamed from: y */
    public boolean f938y;

    /* renamed from: z */
    public boolean f939z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0191a();

        /* renamed from: b */
        public boolean f940b;

        /* renamed from: androidx.appcompat.widget.SearchView$SavedState$a */
        public class C0191a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i9) {
                return new SavedState[i9];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f940b + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i9) {
            super.writeToParcel(parcel, i9);
            parcel.writeValue(Boolean.valueOf(this.f940b));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f940b = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {

        /* renamed from: e */
        public int f941e;

        /* renamed from: f */
        public SearchView f942f;

        /* renamed from: g */
        public boolean f943g;

        /* renamed from: h */
        public final Runnable f944h;

        /* renamed from: androidx.appcompat.widget.SearchView$SearchAutoComplete$a */
        public class RunnableC0192a implements Runnable {
            public RunnableC0192a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.m757c();
            }
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, C0560a.autoCompleteTextViewStyle);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i9 = configuration.screenWidthDp;
            int i10 = configuration.screenHeightDp;
            if (i9 >= 960 && i10 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i9 >= 600) {
                return PsExtractor.AUDIO_STREAM;
            }
            if (i9 < 640 || i10 < 480) {
                return 160;
            }
            return PsExtractor.AUDIO_STREAM;
        }

        /* renamed from: a */
        public void m755a() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (Build.VERSION.SDK_INT < 29) {
                SearchView.f892W.m761c(this);
                return;
            }
            setInputMethodMode(1);
            if (enoughToFilter()) {
                showDropDown();
            }
        }

        /* renamed from: b */
        public boolean m756b() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        /* renamed from: c */
        public void m757c() {
            if (this.f943g) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f943g = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f941e <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f943g) {
                removeCallbacks(this.f944h);
                post(this.f944h);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        public void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onFocusChanged(boolean z8, int i9, Rect rect) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            super.onFocusChanged(z8, i9, rect);
            this.f942f.m751z();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i9, KeyEvent keyEvent) {
            if (i9 == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f942f.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i9, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z8) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            super.onWindowFocusChanged(z8);
            if (z8 && this.f942f.hasFocus() && getVisibility() == 0) {
                this.f943g = true;
                if (SearchView.m715m(getContext())) {
                    m755a();
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        public void replaceText(CharSequence charSequence) {
        }

        public void setImeVisibility(boolean z8) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z8) {
                this.f943g = false;
                removeCallbacks(this.f944h);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f943g = true;
                    return;
                }
                this.f943g = false;
                removeCallbacks(this.f944h);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        public void setSearchView(SearchView searchView) {
            this.f942f = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i9) {
            super.setThreshold(i9);
            this.f941e = i9;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i9) {
            super(context, attributeSet, i9);
            this.f944h = new RunnableC0192a();
            this.f941e = getThreshold();
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$a */
    public class C0193a implements TextWatcher {
        public C0193a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i9, int i10, int i11) {
            SearchView.this.m750y(charSequence);
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$b */
    public class RunnableC0194b implements Runnable {
        public RunnableC0194b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.m721F();
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$c */
    public class RunnableC0195c implements Runnable {
        public RunnableC0195c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC4774a abstractC4774a = SearchView.this.f893A;
            if (abstractC4774a instanceof ViewOnClickListenerC0238k0) {
                abstractC4774a.mo916b(null);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$d */
    public class ViewOnFocusChangeListenerC0196d implements View.OnFocusChangeListener {
        public ViewOnFocusChangeListenerC0196d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z8) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.f936w;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z8);
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$e */
    public class ViewOnLayoutChangeListenerC0197e implements View.OnLayoutChangeListener {
        public ViewOnLayoutChangeListenerC0197e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
            SearchView.this.m728b();
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$f */
    public class ViewOnClickListenerC0198f implements View.OnClickListener {
        public ViewOnClickListenerC0198f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            SearchView searchView = SearchView.this;
            if (view == searchView.f919f) {
                searchView.m747v();
                return;
            }
            if (view == searchView.f921h) {
                searchView.m743r();
                return;
            }
            if (view == searchView.f920g) {
                searchView.m748w();
            } else if (view == searchView.f922i) {
                searchView.m716A();
            } else if (view == searchView.f915b) {
                searchView.m734h();
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$g */
    public class ViewOnKeyListenerC0199g implements View.OnKeyListener {
        public ViewOnKeyListenerC0199g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i9, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.f904L == null) {
                return false;
            }
            if (searchView.f915b.isPopupShowing() && SearchView.this.f915b.getListSelection() != -1) {
                return SearchView.this.m749x(view, i9, keyEvent);
            }
            if (SearchView.this.f915b.m756b() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i9 != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.m741p(0, null, searchView2.f915b.getText().toString());
            return true;
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$h */
    public class C0200h implements TextView.OnEditorActionListener {
        public C0200h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i9, KeyEvent keyEvent) {
            SearchView.this.m748w();
            return true;
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$i */
    public class C0201i implements AdapterView.OnItemClickListener {
        public C0201i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i9, long j9) {
            SearchView.this.m744s(i9, 0, null);
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$j */
    public class C0202j implements AdapterView.OnItemSelectedListener {
        public C0202j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i9, long j9) {
            SearchView.this.m745t(i9);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$k */
    public interface InterfaceC0203k {
    }

    /* renamed from: androidx.appcompat.widget.SearchView$l */
    public interface InterfaceC0204l {
    }

    /* renamed from: androidx.appcompat.widget.SearchView$m */
    public interface InterfaceC0205m {
    }

    /* renamed from: androidx.appcompat.widget.SearchView$n */
    public static class C0206n {

        /* renamed from: a */
        public Method f956a;

        /* renamed from: b */
        public Method f957b;

        /* renamed from: c */
        public Method f958c;

        @SuppressLint({"DiscouragedPrivateApi", "SoonBlockedPrivateApi"})
        public C0206n() throws NoSuchMethodException, SecurityException {
            this.f956a = null;
            this.f957b = null;
            this.f958c = null;
            m758d();
            try {
                Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f956a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f957b = declaredMethod2;
                declaredMethod2.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f958c = method;
                method.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        /* renamed from: d */
        public static void m758d() {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
            }
        }

        /* renamed from: a */
        public void m759a(AutoCompleteTextView autoCompleteTextView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            m758d();
            Method method = this.f957b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        /* renamed from: b */
        public void m760b(AutoCompleteTextView autoCompleteTextView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            m758d();
            Method method = this.f956a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        /* renamed from: c */
        public void m761c(AutoCompleteTextView autoCompleteTextView) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            m758d();
            Method method = this.f958c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }
    }

    /* renamed from: androidx.appcompat.widget.SearchView$o */
    public static class C0207o extends TouchDelegate {

        /* renamed from: a */
        public final View f959a;

        /* renamed from: b */
        public final Rect f960b;

        /* renamed from: c */
        public final Rect f961c;

        /* renamed from: d */
        public final Rect f962d;

        /* renamed from: e */
        public final int f963e;

        /* renamed from: f */
        public boolean f964f;

        public C0207o(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f963e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f960b = new Rect();
            this.f962d = new Rect();
            this.f961c = new Rect();
            m762a(rect, rect2);
            this.f959a = view;
        }

        /* renamed from: a */
        public void m762a(Rect rect, Rect rect2) {
            this.f960b.set(rect);
            this.f962d.set(rect);
            Rect rect3 = this.f962d;
            int i9 = this.f963e;
            rect3.inset(-i9, -i9);
            this.f961c.set(rect2);
        }

        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z8;
            boolean z9;
            int x8 = (int) motionEvent.getX();
            int y8 = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z10 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z9 = this.f964f;
                    if (z9 && !this.f962d.contains(x8, y8)) {
                        z10 = z9;
                        z8 = false;
                    }
                } else {
                    if (action == 3) {
                        z9 = this.f964f;
                        this.f964f = false;
                    }
                    z8 = true;
                    z10 = false;
                }
                z10 = z9;
                z8 = true;
            } else if (this.f960b.contains(x8, y8)) {
                this.f964f = true;
                z8 = true;
            } else {
                z8 = true;
                z10 = false;
            }
            if (!z10) {
                return false;
            }
            if (!z8 || this.f961c.contains(x8, y8)) {
                Rect rect = this.f961c;
                motionEvent.setLocation(x8 - rect.left, y8 - rect.top);
            } else {
                motionEvent.setLocation(this.f959a.getWidth() / 2, this.f959a.getHeight() / 2);
            }
            return this.f959a.dispatchTouchEvent(motionEvent);
        }
    }

    static {
        f892W = Build.VERSION.SDK_INT < 29 ? new C0206n() : null;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(C0563d.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(C0563d.abc_search_view_preferred_width);
    }

    /* renamed from: m */
    public static boolean m715m(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    private void setQuery(CharSequence charSequence) {
        this.f915b.setText(charSequence);
        this.f915b.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    /* renamed from: A */
    public void m716A() {
        SearchableInfo searchableInfo = this.f904L;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(m732f(this.f933t, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(m731e(this.f934u, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    /* renamed from: B */
    public final void m717B() {
        post(this.f906N);
    }

    /* renamed from: C */
    public final void m718C(int i9) {
        Editable text = this.f915b.getText();
        Cursor cursorMo19006d = this.f893A.mo19006d();
        if (cursorMo19006d == null) {
            return;
        }
        if (!cursorMo19006d.moveToPosition(i9)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceConvertToString = this.f893A.convertToString(cursorMo19006d);
        if (charSequenceConvertToString != null) {
            setQuery(charSequenceConvertToString);
        } else {
            setQuery(text);
        }
    }

    /* renamed from: D */
    public void m719D(CharSequence charSequence, boolean z8) {
        this.f915b.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.f915b;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.f901I = charSequence;
        }
        if (!z8 || TextUtils.isEmpty(charSequence)) {
            return;
        }
        m748w();
    }

    /* renamed from: E */
    public final void m720E() {
        boolean z8 = true;
        boolean z9 = !TextUtils.isEmpty(this.f915b.getText());
        if (!z9 && (!this.f938y || this.f902J)) {
            z8 = false;
        }
        this.f921h.setVisibility(z8 ? 0 : 8);
        Drawable drawable = this.f921h.getDrawable();
        if (drawable != null) {
            drawable.setState(z9 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    /* renamed from: F */
    public void m721F() {
        int[] iArr = this.f915b.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.f917d.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.f918e.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    /* renamed from: G */
    public final void m722G() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.f915b;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(m736j(queryHint));
    }

    /* renamed from: H */
    public final void m723H() {
        this.f915b.setThreshold(this.f904L.getSuggestThreshold());
        this.f915b.setImeOptions(this.f904L.getImeOptions());
        int inputType = this.f904L.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.f904L.getSuggestAuthority() != null) {
                inputType = inputType | C3322C.DEFAULT_BUFFER_SEGMENT_SIZE | 524288;
            }
        }
        this.f915b.setInputType(inputType);
        AbstractC4774a abstractC4774a = this.f893A;
        if (abstractC4774a != null) {
            abstractC4774a.mo916b(null);
        }
        if (this.f904L.getSuggestAuthority() != null) {
            ViewOnClickListenerC0238k0 viewOnClickListenerC0238k0 = new ViewOnClickListenerC0238k0(getContext(), this, this.f904L, this.f908P);
            this.f893A = viewOnClickListenerC0238k0;
            this.f915b.setAdapter(viewOnClickListenerC0238k0);
            ((ViewOnClickListenerC0238k0) this.f893A).m931y(this.f896D ? 2 : 1);
        }
    }

    /* renamed from: I */
    public final void m724I() {
        this.f918e.setVisibility((m739n() && (this.f920g.getVisibility() == 0 || this.f922i.getVisibility() == 0)) ? 0 : 8);
    }

    /* renamed from: J */
    public final void m725J(boolean z8) {
        this.f920g.setVisibility((this.f894B && m739n() && hasFocus() && (z8 || !this.f899G)) ? 0 : 8);
    }

    /* renamed from: K */
    public final void m726K(boolean z8) {
        this.f939z = z8;
        int i9 = z8 ? 0 : 8;
        boolean z9 = !TextUtils.isEmpty(this.f915b.getText());
        this.f919f.setVisibility(i9);
        m725J(z9);
        this.f916c.setVisibility(z8 ? 8 : 0);
        this.f929p.setVisibility((this.f929p.getDrawable() == null || this.f938y) ? 8 : 0);
        m720E();
        m727L(!z9);
        m724I();
    }

    /* renamed from: L */
    public final void m727L(boolean z8) {
        int i9 = 8;
        if (this.f899G && !m738l() && z8) {
            this.f920g.setVisibility(8);
            i9 = 0;
        }
        this.f922i.setVisibility(i9);
    }

    /* renamed from: b */
    public void m728b() {
        if (this.f923j.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.f917d.getPaddingLeft();
            Rect rect = new Rect();
            boolean zM1068b = C0258u0.m1068b(this);
            int dimensionPixelSize = this.f938y ? resources.getDimensionPixelSize(C0563d.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(C0563d.abc_dropdownitem_text_padding_left) : 0;
            this.f915b.getDropDownBackground().getPadding(rect);
            this.f915b.setDropDownHorizontalOffset(zM1068b ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.f915b.setDropDownWidth((((this.f923j.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    /* renamed from: c */
    public final Intent m729c(String str, Uri uri, String str2, String str3, int i9, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.f901I);
        if (str3 != null) {
            intent.putExtra(SearchIntents.EXTRA_QUERY, str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.f905M;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i9 != 0) {
            intent.putExtra("action_key", i9);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.f904L.getSearchActivity());
        return intent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.f897E = true;
        super.clearFocus();
        this.f915b.clearFocus();
        this.f915b.setImeVisibility(false);
        this.f897E = false;
    }

    /* renamed from: d */
    public final Intent m730d(Cursor cursor, int i9, String str) {
        int position;
        String strM911p;
        try {
            String strM911p2 = ViewOnClickListenerC0238k0.m911p(cursor, "suggest_intent_action");
            if (strM911p2 == null) {
                strM911p2 = this.f904L.getSuggestIntentAction();
            }
            if (strM911p2 == null) {
                strM911p2 = "android.intent.action.SEARCH";
            }
            String str2 = strM911p2;
            String strM911p3 = ViewOnClickListenerC0238k0.m911p(cursor, "suggest_intent_data");
            if (strM911p3 == null) {
                strM911p3 = this.f904L.getSuggestIntentData();
            }
            if (strM911p3 != null && (strM911p = ViewOnClickListenerC0238k0.m911p(cursor, "suggest_intent_data_id")) != null) {
                strM911p3 = strM911p3 + "/" + Uri.encode(strM911p);
            }
            return m729c(str2, strM911p3 == null ? null : Uri.parse(strM911p3), ViewOnClickListenerC0238k0.m911p(cursor, "suggest_intent_extra_data"), ViewOnClickListenerC0238k0.m911p(cursor, "suggest_intent_query"), i9, str);
        } catch (RuntimeException e9) {
            try {
                position = cursor.getPosition();
            } catch (RuntimeException unused) {
                position = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e9);
            return null;
        }
    }

    /* renamed from: e */
    public final Intent m731e(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f905M;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    /* renamed from: f */
    public final Intent m732f(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    /* renamed from: g */
    public final void m733g() {
        this.f915b.dismissDropDown();
    }

    public int getImeOptions() {
        return this.f915b.getImeOptions();
    }

    public int getInputType() {
        return this.f915b.getInputType();
    }

    public int getMaxWidth() {
        return this.f898F;
    }

    public CharSequence getQuery() {
        return this.f915b.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.f895C;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.f904L;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.f935v : getContext().getText(this.f904L.getHintId());
    }

    public int getSuggestionCommitIconResId() {
        return this.f932s;
    }

    public int getSuggestionRowLayout() {
        return this.f931r;
    }

    public AbstractC4774a getSuggestionsAdapter() {
        return this.f893A;
    }

    /* renamed from: h */
    public void m734h() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= 29) {
            this.f915b.refreshAutoCompleteResults();
            return;
        }
        C0206n c0206n = f892W;
        c0206n.m760b(this.f915b);
        c0206n.m759a(this.f915b);
    }

    /* renamed from: i */
    public final void m735i(View view, Rect rect) {
        view.getLocationInWindow(this.f927n);
        getLocationInWindow(this.f928o);
        int[] iArr = this.f927n;
        int i9 = iArr[1];
        int[] iArr2 = this.f928o;
        int i10 = i9 - iArr2[1];
        int i11 = iArr[0] - iArr2[0];
        rect.set(i11, i10, view.getWidth() + i11, view.getHeight() + i10);
    }

    /* renamed from: j */
    public final CharSequence m736j(CharSequence charSequence) {
        if (!this.f938y || this.f930q == null) {
            return charSequence;
        }
        int textSize = (int) (this.f915b.getTextSize() * 1.25d);
        this.f930q.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.f930q), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    /* renamed from: k */
    public final boolean m737k() {
        SearchableInfo searchableInfo = this.f904L;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = this.f904L.getVoiceSearchLaunchWebSearch() ? this.f933t : this.f904L.getVoiceSearchLaunchRecognizer() ? this.f934u : null;
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, C3322C.DEFAULT_BUFFER_SEGMENT_SIZE) == null) ? false : true;
    }

    /* renamed from: l */
    public boolean m738l() {
        return this.f939z;
    }

    /* renamed from: n */
    public final boolean m739n() {
        return (this.f894B || this.f899G) && !m738l();
    }

    /* renamed from: o */
    public final void m740o(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e9) {
            Log.e("SearchView", "Failed launch activity: " + intent, e9);
        }
    }

    @Override // p071g.InterfaceC4797c
    public void onActionViewCollapsed() {
        m719D("", false);
        clearFocus();
        m726K(true);
        this.f915b.setImeOptions(this.f903K);
        this.f902J = false;
    }

    @Override // p071g.InterfaceC4797c
    public void onActionViewExpanded() {
        if (this.f902J) {
            return;
        }
        this.f902J = true;
        int imeOptions = this.f915b.getImeOptions();
        this.f903K = imeOptions;
        this.f915b.setImeOptions(imeOptions | 33554432);
        this.f915b.setText("");
        setIconified(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.f906N);
        post(this.f907O);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z8, int i9, int i10, int i11, int i12) {
        super.onLayout(z8, i9, i10, i11, i12);
        if (z8) {
            m735i(this.f915b, this.f925l);
            Rect rect = this.f926m;
            Rect rect2 = this.f925l;
            rect.set(rect2.left, 0, rect2.right, i12 - i10);
            C0207o c0207o = this.f924k;
            if (c0207o != null) {
                c0207o.m762a(this.f926m, this.f925l);
                return;
            }
            C0207o c0207o2 = new C0207o(this.f926m, this.f925l, this.f915b);
            this.f924k = c0207o2;
            setTouchDelegate(c0207o2);
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i9, int i10) {
        int i11;
        if (m738l()) {
            super.onMeasure(i9, i10);
            return;
        }
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        if (mode == Integer.MIN_VALUE) {
            int i12 = this.f898F;
            size = i12 > 0 ? Math.min(i12, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.f898F;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i11 = this.f898F) > 0) {
            size = Math.min(i11, size);
        }
        int mode2 = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i10);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        m726K(savedState.f940b);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f940b = m738l();
        return savedState;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z8) {
        super.onWindowFocusChanged(z8);
        m717B();
    }

    /* renamed from: p */
    public void m741p(int i9, String str, String str2) {
        getContext().startActivity(m729c("android.intent.action.SEARCH", null, null, str2, i9, str));
    }

    /* renamed from: q */
    public final boolean m742q(int i9, int i10, String str) {
        Cursor cursorMo19006d = this.f893A.mo19006d();
        if (cursorMo19006d == null || !cursorMo19006d.moveToPosition(i9)) {
            return false;
        }
        m740o(m730d(cursorMo19006d, i10, str));
        return true;
    }

    /* renamed from: r */
    public void m743r() {
        if (!TextUtils.isEmpty(this.f915b.getText())) {
            this.f915b.setText("");
            this.f915b.requestFocus();
            this.f915b.setImeVisibility(true);
        } else if (this.f938y) {
            clearFocus();
            m726K(true);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i9, Rect rect) {
        if (this.f897E || !isFocusable()) {
            return false;
        }
        if (m738l()) {
            return super.requestFocus(i9, rect);
        }
        boolean zRequestFocus = this.f915b.requestFocus(i9, rect);
        if (zRequestFocus) {
            m726K(false);
        }
        return zRequestFocus;
    }

    /* renamed from: s */
    public boolean m744s(int i9, int i10, String str) {
        m742q(i9, 0, null);
        this.f915b.setImeVisibility(false);
        m733g();
        return true;
    }

    public void setAppSearchData(Bundle bundle) {
        this.f905M = bundle;
    }

    public void setIconified(boolean z8) {
        if (z8) {
            m743r();
        } else {
            m747v();
        }
    }

    public void setIconifiedByDefault(boolean z8) {
        if (this.f938y == z8) {
            return;
        }
        this.f938y = z8;
        m726K(z8);
        m722G();
    }

    public void setImeOptions(int i9) {
        this.f915b.setImeOptions(i9);
    }

    public void setInputType(int i9) {
        this.f915b.setInputType(i9);
    }

    public void setMaxWidth(int i9) {
        this.f898F = i9;
        requestLayout();
    }

    public void setOnCloseListener(InterfaceC0203k interfaceC0203k) {
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.f936w = onFocusChangeListener;
    }

    public void setOnQueryTextListener(InterfaceC0204l interfaceC0204l) {
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.f937x = onClickListener;
    }

    public void setOnSuggestionListener(InterfaceC0205m interfaceC0205m) {
    }

    public void setQueryHint(CharSequence charSequence) {
        this.f895C = charSequence;
        m722G();
    }

    public void setQueryRefinementEnabled(boolean z8) {
        this.f896D = z8;
        AbstractC4774a abstractC4774a = this.f893A;
        if (abstractC4774a instanceof ViewOnClickListenerC0238k0) {
            ((ViewOnClickListenerC0238k0) abstractC4774a).m931y(z8 ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.f904L = searchableInfo;
        if (searchableInfo != null) {
            m723H();
            m722G();
        }
        boolean zM737k = m737k();
        this.f899G = zM737k;
        if (zM737k) {
            this.f915b.setPrivateImeOptions("nm");
        }
        m726K(m738l());
    }

    public void setSubmitButtonEnabled(boolean z8) {
        this.f894B = z8;
        m726K(m738l());
    }

    public void setSuggestionsAdapter(AbstractC4774a abstractC4774a) {
        this.f893A = abstractC4774a;
        this.f915b.setAdapter(abstractC4774a);
    }

    /* renamed from: t */
    public boolean m745t(int i9) {
        m718C(i9);
        return true;
    }

    /* renamed from: u */
    public void m746u(CharSequence charSequence) {
        setQuery(charSequence);
    }

    /* renamed from: v */
    public void m747v() {
        m726K(false);
        this.f915b.requestFocus();
        this.f915b.setImeVisibility(true);
        View.OnClickListener onClickListener = this.f937x;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    /* renamed from: w */
    public void m748w() {
        Editable text = this.f915b.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        if (this.f904L != null) {
            m741p(0, null, text.toString());
        }
        this.f915b.setImeVisibility(false);
        m733g();
    }

    /* renamed from: x */
    public boolean m749x(View view, int i9, KeyEvent keyEvent) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.f904L != null && this.f893A != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i9 == 66 || i9 == 84 || i9 == 61) {
                return m744s(this.f915b.getListSelection(), 0, null);
            }
            if (i9 == 21 || i9 == 22) {
                this.f915b.setSelection(i9 == 21 ? 0 : this.f915b.length());
                this.f915b.setListSelection(0);
                this.f915b.clearListSelection();
                this.f915b.m755a();
                return true;
            }
            if (i9 == 19) {
                this.f915b.getListSelection();
                return false;
            }
        }
        return false;
    }

    /* renamed from: y */
    public void m750y(CharSequence charSequence) {
        Editable text = this.f915b.getText();
        this.f901I = text;
        boolean z8 = !TextUtils.isEmpty(text);
        m725J(z8);
        m727L(!z8);
        m720E();
        m724I();
        this.f900H = charSequence.toString();
    }

    /* renamed from: z */
    public void m751z() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        m726K(m738l());
        m717B();
        if (this.f915b.hasFocus()) {
            m734h();
        }
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0560a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i9) {
        super(context, attributeSet, i9);
        this.f925l = new Rect();
        this.f926m = new Rect();
        this.f927n = new int[2];
        this.f928o = new int[2];
        this.f906N = new RunnableC0194b();
        this.f907O = new RunnableC0195c();
        this.f908P = new WeakHashMap<>();
        ViewOnClickListenerC0198f viewOnClickListenerC0198f = new ViewOnClickListenerC0198f();
        this.f909Q = viewOnClickListenerC0198f;
        this.f910R = new ViewOnKeyListenerC0199g();
        C0200h c0200h = new C0200h();
        this.f911S = c0200h;
        C0201i c0201i = new C0201i();
        this.f912T = c0201i;
        C0202j c0202j = new C0202j();
        this.f913U = c0202j;
        this.f914V = new C0193a();
        C0250q0 c0250q0M1004v = C0250q0.m1004v(context, attributeSet, C0569j.SearchView, i9, 0);
        LayoutInflater.from(context).inflate(c0250q0M1004v.m1018n(C0569j.SearchView_layout, C0566g.abc_search_view), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(C0565f.search_src_text);
        this.f915b = searchAutoComplete;
        searchAutoComplete.setSearchView(this);
        this.f916c = findViewById(C0565f.search_edit_frame);
        View viewFindViewById = findViewById(C0565f.search_plate);
        this.f917d = viewFindViewById;
        View viewFindViewById2 = findViewById(C0565f.submit_area);
        this.f918e = viewFindViewById2;
        ImageView imageView = (ImageView) findViewById(C0565f.search_button);
        this.f919f = imageView;
        ImageView imageView2 = (ImageView) findViewById(C0565f.search_go_btn);
        this.f920g = imageView2;
        ImageView imageView3 = (ImageView) findViewById(C0565f.search_close_btn);
        this.f921h = imageView3;
        ImageView imageView4 = (ImageView) findViewById(C0565f.search_voice_btn);
        this.f922i = imageView4;
        ImageView imageView5 = (ImageView) findViewById(C0565f.search_mag_icon);
        this.f929p = imageView5;
        C4647u.m18534b0(viewFindViewById, c0250q0M1004v.m1011g(C0569j.SearchView_queryBackground));
        C4647u.m18534b0(viewFindViewById2, c0250q0M1004v.m1011g(C0569j.SearchView_submitBackground));
        int i10 = C0569j.SearchView_searchIcon;
        imageView.setImageDrawable(c0250q0M1004v.m1011g(i10));
        imageView2.setImageDrawable(c0250q0M1004v.m1011g(C0569j.SearchView_goIcon));
        imageView3.setImageDrawable(c0250q0M1004v.m1011g(C0569j.SearchView_closeIcon));
        imageView4.setImageDrawable(c0250q0M1004v.m1011g(C0569j.SearchView_voiceIcon));
        imageView5.setImageDrawable(c0250q0M1004v.m1011g(i10));
        this.f930q = c0250q0M1004v.m1011g(C0569j.SearchView_searchHintIcon);
        C0254s0.m1061a(imageView, getResources().getString(C0567h.abc_searchview_description_search));
        this.f931r = c0250q0M1004v.m1018n(C0569j.SearchView_suggestionRowLayout, C0566g.abc_search_dropdown_item_icons_2line);
        this.f932s = c0250q0M1004v.m1018n(C0569j.SearchView_commitIcon, 0);
        imageView.setOnClickListener(viewOnClickListenerC0198f);
        imageView3.setOnClickListener(viewOnClickListenerC0198f);
        imageView2.setOnClickListener(viewOnClickListenerC0198f);
        imageView4.setOnClickListener(viewOnClickListenerC0198f);
        searchAutoComplete.setOnClickListener(viewOnClickListenerC0198f);
        searchAutoComplete.addTextChangedListener(this.f914V);
        searchAutoComplete.setOnEditorActionListener(c0200h);
        searchAutoComplete.setOnItemClickListener(c0201i);
        searchAutoComplete.setOnItemSelectedListener(c0202j);
        searchAutoComplete.setOnKeyListener(this.f910R);
        searchAutoComplete.setOnFocusChangeListener(new ViewOnFocusChangeListenerC0196d());
        setIconifiedByDefault(c0250q0M1004v.m1005a(C0569j.SearchView_iconifiedByDefault, true));
        int iM1010f = c0250q0M1004v.m1010f(C0569j.SearchView_android_maxWidth, -1);
        if (iM1010f != -1) {
            setMaxWidth(iM1010f);
        }
        this.f935v = c0250q0M1004v.m1020p(C0569j.SearchView_defaultQueryHint);
        this.f895C = c0250q0M1004v.m1020p(C0569j.SearchView_queryHint);
        int iM1015k = c0250q0M1004v.m1015k(C0569j.SearchView_android_imeOptions, -1);
        if (iM1015k != -1) {
            setImeOptions(iM1015k);
        }
        int iM1015k2 = c0250q0M1004v.m1015k(C0569j.SearchView_android_inputType, -1);
        if (iM1015k2 != -1) {
            setInputType(iM1015k2);
        }
        setFocusable(c0250q0M1004v.m1005a(C0569j.SearchView_android_focusable, true));
        c0250q0M1004v.m1024w();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.f933t = intent;
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.f934u = intent2;
        intent2.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        View viewFindViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.f923j = viewFindViewById3;
        if (viewFindViewById3 != null) {
            viewFindViewById3.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC0197e());
        }
        m726K(this.f938y);
        m722G();
    }
}
