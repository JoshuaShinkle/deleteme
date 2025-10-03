package androidx.appcompat.app;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.C0135e;
import androidx.appcompat.view.menu.C0137g;
import androidx.appcompat.view.menu.InterfaceC0143m;
import androidx.appcompat.view.menu.InterfaceC0144n;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.C0227f;
import androidx.appcompat.widget.C0250q0;
import androidx.appcompat.widget.C0256t0;
import androidx.appcompat.widget.C0258u0;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.InterfaceC0249q;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.widget.C0333h;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.Lifecycle;
import com.google.android.exoplayer2.extractor.p037ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import p010b.C0560a;
import p010b.C0562c;
import p010b.C0565f;
import p010b.C0566g;
import p010b.C0568i;
import p010b.C0569j;
import p020c.C0694a;
import p042d0.C4620d0;
import p042d0.C4623f;
import p042d0.C4624f0;
import p042d0.C4625g;
import p042d0.C4628h0;
import p042d0.C4647u;
import p042d0.InterfaceC4643q;
import p071g.AbstractC4796b;
import p071g.C4798d;
import p071g.C4799e;
import p071g.C4800f;
import p071g.C4801g;
import p071g.WindowCallbackC4803i;
import p132m.C5308g;
import p188s.C6226a;
import p188s.C6230e;
import p197t.C6273a;
import p206u.C6348b;

/* loaded from: classes.dex */
public class AppCompatDelegateImpl extends AbstractC0122d implements C0137g.a, LayoutInflater.Factory2 {

    /* renamed from: b0 */
    public static final C5308g<String, Integer> f280b0 = new C5308g<>();

    /* renamed from: c0 */
    public static final boolean f281c0 = false;

    /* renamed from: d0 */
    public static final int[] f282d0 = {R.attr.windowBackground};

    /* renamed from: e0 */
    public static final boolean f283e0 = !"robolectric".equals(Build.FINGERPRINT);

    /* renamed from: f0 */
    public static final boolean f284f0 = true;

    /* renamed from: A */
    public boolean f285A;

    /* renamed from: B */
    public boolean f286B;

    /* renamed from: C */
    public boolean f287C;

    /* renamed from: D */
    public boolean f288D;

    /* renamed from: E */
    public boolean f289E;

    /* renamed from: F */
    public boolean f290F;

    /* renamed from: G */
    public boolean f291G;

    /* renamed from: H */
    public PanelFeatureState[] f292H;

    /* renamed from: I */
    public PanelFeatureState f293I;

    /* renamed from: J */
    public boolean f294J;

    /* renamed from: K */
    public boolean f295K;

    /* renamed from: L */
    public boolean f296L;

    /* renamed from: M */
    public boolean f297M;

    /* renamed from: N */
    public boolean f298N;

    /* renamed from: O */
    public int f299O;

    /* renamed from: P */
    public int f300P;

    /* renamed from: Q */
    public boolean f301Q;

    /* renamed from: R */
    public boolean f302R;

    /* renamed from: S */
    public AbstractC0111j f303S;

    /* renamed from: T */
    public AbstractC0111j f304T;

    /* renamed from: U */
    public boolean f305U;

    /* renamed from: V */
    public int f306V;

    /* renamed from: W */
    public final Runnable f307W;

    /* renamed from: X */
    public boolean f308X;

    /* renamed from: Y */
    public Rect f309Y;

    /* renamed from: Z */
    public Rect f310Z;

    /* renamed from: a0 */
    public C0125g f311a0;

    /* renamed from: e */
    public final Object f312e;

    /* renamed from: f */
    public final Context f313f;

    /* renamed from: g */
    public Window f314g;

    /* renamed from: h */
    public C0109h f315h;

    /* renamed from: i */
    public final InterfaceC0121c f316i;

    /* renamed from: j */
    public AbstractC0119a f317j;

    /* renamed from: k */
    public MenuInflater f318k;

    /* renamed from: l */
    public CharSequence f319l;

    /* renamed from: m */
    public InterfaceC0249q f320m;

    /* renamed from: n */
    public C0107f f321n;

    /* renamed from: o */
    public C0118q f322o;

    /* renamed from: p */
    public AbstractC4796b f323p;

    /* renamed from: q */
    public ActionBarContextView f324q;

    /* renamed from: r */
    public PopupWindow f325r;

    /* renamed from: s */
    public Runnable f326s;

    /* renamed from: t */
    public C4620d0 f327t;

    /* renamed from: u */
    public boolean f328u;

    /* renamed from: v */
    public boolean f329v;

    /* renamed from: w */
    public ViewGroup f330w;

    /* renamed from: x */
    public TextView f331x;

    /* renamed from: y */
    public View f332y;

    /* renamed from: z */
    public boolean f333z;

    public static final class PanelFeatureState {

        /* renamed from: a */
        public int f334a;

        /* renamed from: b */
        public int f335b;

        /* renamed from: c */
        public int f336c;

        /* renamed from: d */
        public int f337d;

        /* renamed from: e */
        public int f338e;

        /* renamed from: f */
        public int f339f;

        /* renamed from: g */
        public ViewGroup f340g;

        /* renamed from: h */
        public View f341h;

        /* renamed from: i */
        public View f342i;

        /* renamed from: j */
        public C0137g f343j;

        /* renamed from: k */
        public C0135e f344k;

        /* renamed from: l */
        public Context f345l;

        /* renamed from: m */
        public boolean f346m;

        /* renamed from: n */
        public boolean f347n;

        /* renamed from: o */
        public boolean f348o;

        /* renamed from: p */
        public boolean f349p;

        /* renamed from: q */
        public boolean f350q = false;

        /* renamed from: r */
        public boolean f351r;

        /* renamed from: s */
        public Bundle f352s;

        @SuppressLint({"BanParcelableUsage"})
        public static class SavedState implements Parcelable {
            public static final Parcelable.Creator<SavedState> CREATOR = new C0101a();

            /* renamed from: b */
            public int f353b;

            /* renamed from: c */
            public boolean f354c;

            /* renamed from: d */
            public Bundle f355d;

            /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$PanelFeatureState$SavedState$a */
            public class C0101a implements Parcelable.ClassLoaderCreator<SavedState> {
                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel) {
                    return SavedState.m345a(parcel, null);
                }

                @Override // android.os.Parcelable.ClassLoaderCreator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.m345a(parcel, classLoader);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public SavedState[] newArray(int i9) {
                    return new SavedState[i9];
                }
            }

            /* renamed from: a */
            public static SavedState m345a(Parcel parcel, ClassLoader classLoader) {
                SavedState savedState = new SavedState();
                savedState.f353b = parcel.readInt();
                boolean z8 = parcel.readInt() == 1;
                savedState.f354c = z8;
                if (z8) {
                    savedState.f355d = parcel.readBundle(classLoader);
                }
                return savedState;
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i9) {
                parcel.writeInt(this.f353b);
                parcel.writeInt(this.f354c ? 1 : 0);
                if (this.f354c) {
                    parcel.writeBundle(this.f355d);
                }
            }
        }

        public PanelFeatureState(int i9) {
            this.f334a = i9;
        }

        /* renamed from: a */
        public InterfaceC0144n m341a(InterfaceC0143m.a aVar) {
            if (this.f343j == null) {
                return null;
            }
            if (this.f344k == null) {
                C0135e c0135e = new C0135e(this.f345l, C0566g.abc_list_menu_item_layout);
                this.f344k = c0135e;
                c0135e.setCallback(aVar);
                this.f343j.addMenuPresenter(this.f344k);
            }
            return this.f344k.m510b(this.f340g);
        }

        /* renamed from: b */
        public boolean m342b() {
            if (this.f341h == null) {
                return false;
            }
            return this.f342i != null || this.f344k.m509a().getCount() > 0;
        }

        /* renamed from: c */
        public void m343c(C0137g c0137g) {
            C0135e c0135e;
            C0137g c0137g2 = this.f343j;
            if (c0137g == c0137g2) {
                return;
            }
            if (c0137g2 != null) {
                c0137g2.removeMenuPresenter(this.f344k);
            }
            this.f343j = c0137g;
            if (c0137g == null || (c0135e = this.f344k) == null) {
                return;
            }
            c0137g.addMenuPresenter(c0135e);
        }

        /* renamed from: d */
        public void m344d(Context context) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme themeNewTheme = context.getResources().newTheme();
            themeNewTheme.setTo(context.getTheme());
            themeNewTheme.resolveAttribute(C0560a.actionBarPopupTheme, typedValue, true);
            int i9 = typedValue.resourceId;
            if (i9 != 0) {
                themeNewTheme.applyStyle(i9, true);
            }
            themeNewTheme.resolveAttribute(C0560a.panelMenuListTheme, typedValue, true);
            int i10 = typedValue.resourceId;
            if (i10 != 0) {
                themeNewTheme.applyStyle(i10, true);
            } else {
                themeNewTheme.applyStyle(C0568i.Theme_AppCompat_CompactMenu, true);
            }
            C4798d c4798d = new C4798d(context, 0);
            c4798d.getTheme().setTo(themeNewTheme);
            this.f345l = c4798d;
            TypedArray typedArrayObtainStyledAttributes = c4798d.obtainStyledAttributes(C0569j.AppCompatTheme);
            this.f335b = typedArrayObtainStyledAttributes.getResourceId(C0569j.AppCompatTheme_panelBackground, 0);
            this.f339f = typedArrayObtainStyledAttributes.getResourceId(C0569j.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$a */
    public class RunnableC0102a implements Runnable {
        public RunnableC0102a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl.f306V & 1) != 0) {
                appCompatDelegateImpl.m289S(0);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if ((appCompatDelegateImpl2.f306V & 4096) != 0) {
                appCompatDelegateImpl2.m289S(108);
            }
            AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl3.f305U = false;
            appCompatDelegateImpl3.f306V = 0;
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$b */
    public class C0103b implements InterfaceC4643q {
        public C0103b() {
        }

        @Override // p042d0.InterfaceC4643q
        public C4628h0 onApplyWindowInsets(View view, C4628h0 c4628h0) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            int iM18439g = c4628h0.m18439g();
            int iM279J0 = AppCompatDelegateImpl.this.m279J0(c4628h0, null);
            if (iM18439g != iM279J0) {
                c4628h0 = c4628h0.m18444m(c4628h0.m18437e(), iM279J0, c4628h0.m18438f(), c4628h0.m18436d());
            }
            return C4647u.m18520P(view, c4628h0);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$c */
    public class C0104c implements ContentFrameLayout.InterfaceC0182a {
        public C0104c() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.InterfaceC0182a
        /* renamed from: a */
        public void mo349a() {
        }

        @Override // androidx.appcompat.widget.ContentFrameLayout.InterfaceC0182a
        public void onDetachedFromWindow() {
            AppCompatDelegateImpl.this.m287Q();
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$d */
    public class RunnableC0105d implements Runnable {

        /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$d$a */
        public class a extends C4624f0 {
            public a() {
            }

            @Override // p042d0.InterfaceC4622e0
            /* renamed from: b */
            public void mo350b(View view) {
                AppCompatDelegateImpl.this.f324q.setAlpha(1.0f);
                AppCompatDelegateImpl.this.f327t.m18413f(null);
                AppCompatDelegateImpl.this.f327t = null;
            }

            @Override // p042d0.C4624f0, p042d0.InterfaceC4622e0
            /* renamed from: c */
            public void mo351c(View view) {
                AppCompatDelegateImpl.this.f324q.setVisibility(0);
            }
        }

        public RunnableC0105d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            appCompatDelegateImpl.f325r.showAtLocation(appCompatDelegateImpl.f324q, 55, 0, 0);
            AppCompatDelegateImpl.this.m290T();
            if (!AppCompatDelegateImpl.this.m263B0()) {
                AppCompatDelegateImpl.this.f324q.setAlpha(1.0f);
                AppCompatDelegateImpl.this.f324q.setVisibility(0);
            } else {
                AppCompatDelegateImpl.this.f324q.setAlpha(BitmapDescriptorFactory.HUE_RED);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.f327t = C4647u.m18533b(appCompatDelegateImpl2.f324q).m18408a(1.0f);
                AppCompatDelegateImpl.this.f327t.m18413f(new a());
            }
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$e */
    public class C0106e extends C4624f0 {
        public C0106e() {
        }

        @Override // p042d0.InterfaceC4622e0
        /* renamed from: b */
        public void mo350b(View view) {
            AppCompatDelegateImpl.this.f324q.setAlpha(1.0f);
            AppCompatDelegateImpl.this.f327t.m18413f(null);
            AppCompatDelegateImpl.this.f327t = null;
        }

        @Override // p042d0.C4624f0, p042d0.InterfaceC4622e0
        /* renamed from: c */
        public void mo351c(View view) {
            AppCompatDelegateImpl.this.f324q.setVisibility(0);
            AppCompatDelegateImpl.this.f324q.sendAccessibilityEvent(32);
            if (AppCompatDelegateImpl.this.f324q.getParent() instanceof View) {
                C4647u.m18527W((View) AppCompatDelegateImpl.this.f324q.getParent());
            }
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$f */
    public final class C0107f implements InterfaceC0143m.a {
        public C0107f() {
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        /* renamed from: a */
        public boolean mo352a(C0137g c0137g) {
            Window.Callback callbackM301d0 = AppCompatDelegateImpl.this.m301d0();
            if (callbackM301d0 == null) {
                return true;
            }
            callbackM301d0.onMenuOpened(108, c0137g);
            return true;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        public void onCloseMenu(C0137g c0137g, boolean z8) {
            AppCompatDelegateImpl.this.m278J(c0137g);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$g */
    public class C0108g implements AbstractC4796b.a {

        /* renamed from: a */
        public AbstractC4796b.a f363a;

        /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$g$a */
        public class a extends C4624f0 {
            public a() {
            }

            @Override // p042d0.InterfaceC4622e0
            /* renamed from: b */
            public void mo350b(View view) {
                AppCompatDelegateImpl.this.f324q.setVisibility(8);
                AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
                PopupWindow popupWindow = appCompatDelegateImpl.f325r;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (appCompatDelegateImpl.f324q.getParent() instanceof View) {
                    C4647u.m18527W((View) AppCompatDelegateImpl.this.f324q.getParent());
                }
                AppCompatDelegateImpl.this.f324q.removeAllViews();
                AppCompatDelegateImpl.this.f327t.m18413f(null);
                AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl2.f327t = null;
                C4647u.m18527W(appCompatDelegateImpl2.f330w);
            }
        }

        public C0108g(AbstractC4796b.a aVar) {
            this.f363a = aVar;
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: a */
        public boolean mo353a(AbstractC4796b abstractC4796b, MenuItem menuItem) {
            return this.f363a.mo353a(abstractC4796b, menuItem);
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: b */
        public void mo354b(AbstractC4796b abstractC4796b) {
            this.f363a.mo354b(abstractC4796b);
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl.f325r != null) {
                appCompatDelegateImpl.f314g.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.f326s);
            }
            AppCompatDelegateImpl appCompatDelegateImpl2 = AppCompatDelegateImpl.this;
            if (appCompatDelegateImpl2.f324q != null) {
                appCompatDelegateImpl2.m290T();
                AppCompatDelegateImpl appCompatDelegateImpl3 = AppCompatDelegateImpl.this;
                appCompatDelegateImpl3.f327t = C4647u.m18533b(appCompatDelegateImpl3.f324q).m18408a(BitmapDescriptorFactory.HUE_RED);
                AppCompatDelegateImpl.this.f327t.m18413f(new a());
            }
            AppCompatDelegateImpl appCompatDelegateImpl4 = AppCompatDelegateImpl.this;
            InterfaceC0121c interfaceC0121c = appCompatDelegateImpl4.f316i;
            if (interfaceC0121c != null) {
                interfaceC0121c.onSupportActionModeFinished(appCompatDelegateImpl4.f323p);
            }
            AppCompatDelegateImpl appCompatDelegateImpl5 = AppCompatDelegateImpl.this;
            appCompatDelegateImpl5.f323p = null;
            C4647u.m18527W(appCompatDelegateImpl5.f330w);
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: c */
        public boolean mo355c(AbstractC4796b abstractC4796b, Menu menu) {
            C4647u.m18527W(AppCompatDelegateImpl.this.f330w);
            return this.f363a.mo355c(abstractC4796b, menu);
        }

        @Override // p071g.AbstractC4796b.a
        /* renamed from: d */
        public boolean mo356d(AbstractC4796b abstractC4796b, Menu menu) {
            return this.f363a.mo356d(abstractC4796b, menu);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$h */
    public class C0109h extends WindowCallbackC4803i {
        public C0109h(Window.Callback callback) {
            super(callback);
        }

        /* renamed from: b */
        public final ActionMode m357b(ActionMode.Callback callback) {
            C4800f.a aVar = new C4800f.a(AppCompatDelegateImpl.this.f313f, callback);
            AbstractC4796b abstractC4796bM267D0 = AppCompatDelegateImpl.this.m267D0(aVar);
            if (abstractC4796bM267D0 != null) {
                return aVar.m19048e(abstractC4796bM267D0);
            }
            return null;
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.m288R(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return super.dispatchKeyShortcutEvent(keyEvent) || AppCompatDelegateImpl.this.m322p0(keyEvent.getKeyCode(), keyEvent);
        }

        @Override // android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public boolean onCreatePanelMenu(int i9, Menu menu) {
            if (i9 != 0 || (menu instanceof C0137g)) {
                return super.onCreatePanelMenu(i9, menu);
            }
            return false;
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public boolean onMenuOpened(int i9, Menu menu) {
            super.onMenuOpened(i9, menu);
            AppCompatDelegateImpl.this.m328s0(i9);
            return true;
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public void onPanelClosed(int i9, Menu menu) {
            super.onPanelClosed(i9, menu);
            AppCompatDelegateImpl.this.m330t0(i9);
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public boolean onPreparePanel(int i9, View view, Menu menu) {
            C0137g c0137g = menu instanceof C0137g ? (C0137g) menu : null;
            if (i9 == 0 && c0137g == null) {
                return false;
            }
            if (c0137g != null) {
                c0137g.setOverrideVisibleItems(true);
            }
            boolean zOnPreparePanel = super.onPreparePanel(i9, view, menu);
            if (c0137g != null) {
                c0137g.setOverrideVisibleItems(false);
            }
            return zOnPreparePanel;
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i9) {
            C0137g c0137g;
            PanelFeatureState panelFeatureStateM298b0 = AppCompatDelegateImpl.this.m298b0(0, true);
            if (panelFeatureStateM298b0 == null || (c0137g = panelFeatureStateM298b0.f343j) == null) {
                super.onProvideKeyboardShortcuts(list, menu, i9);
            } else {
                super.onProvideKeyboardShortcuts(list, c0137g, i9);
            }
        }

        @Override // android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        @Override // p071g.WindowCallbackC4803i, android.view.Window.Callback
        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i9) {
            return (AppCompatDelegateImpl.this.m312k0() && i9 == 0) ? m357b(callback) : super.onWindowStartingActionMode(callback, i9);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$i */
    public class C0110i extends AbstractC0111j {

        /* renamed from: c */
        public final PowerManager f367c;

        public C0110i(Context context) {
            super();
            this.f367c = (PowerManager) context.getApplicationContext().getSystemService("power");
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AbstractC0111j
        /* renamed from: b */
        public IntentFilter mo358b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AbstractC0111j
        /* renamed from: c */
        public int mo359c() {
            return this.f367c.isPowerSaveMode() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AbstractC0111j
        /* renamed from: d */
        public void mo360d() {
            AppCompatDelegateImpl.this.m266D();
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$j */
    public abstract class AbstractC0111j {

        /* renamed from: a */
        public BroadcastReceiver f369a;

        /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$j$a */
        public class a extends BroadcastReceiver {
            public a() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                AbstractC0111j.this.mo360d();
            }
        }

        public AbstractC0111j() {
        }

        /* renamed from: a */
        public void m361a() {
            BroadcastReceiver broadcastReceiver = this.f369a;
            if (broadcastReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.f313f.unregisterReceiver(broadcastReceiver);
                } catch (IllegalArgumentException unused) {
                }
                this.f369a = null;
            }
        }

        /* renamed from: b */
        public abstract IntentFilter mo358b();

        /* renamed from: c */
        public abstract int mo359c();

        /* renamed from: d */
        public abstract void mo360d();

        /* renamed from: e */
        public void m362e() {
            m361a();
            IntentFilter intentFilterMo358b = mo358b();
            if (intentFilterMo358b == null || intentFilterMo358b.countActions() == 0) {
                return;
            }
            if (this.f369a == null) {
                this.f369a = new a();
            }
            AppCompatDelegateImpl.this.f313f.registerReceiver(this.f369a, intentFilterMo358b);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$k */
    public class C0112k extends AbstractC0111j {

        /* renamed from: c */
        public final C0127i f372c;

        public C0112k(C0127i c0127i) {
            super();
            this.f372c = c0127i;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AbstractC0111j
        /* renamed from: b */
        public IntentFilter mo358b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.TIME_TICK");
            return intentFilter;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AbstractC0111j
        /* renamed from: c */
        public int mo359c() {
            return this.f372c.m411d() ? 2 : 1;
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.AbstractC0111j
        /* renamed from: d */
        public void mo360d() {
            AppCompatDelegateImpl.this.m266D();
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$l */
    public static class C0113l {
        /* renamed from: a */
        public static void m363a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i9 = configuration.densityDpi;
            int i10 = configuration2.densityDpi;
            if (i9 != i10) {
                configuration3.densityDpi = i10;
            }
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$m */
    public static class C0114m {
        /* renamed from: a */
        public static void m364a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            LocaleList locales = configuration.getLocales();
            LocaleList locales2 = configuration2.getLocales();
            if (locales.equals(locales2)) {
                return;
            }
            configuration3.setLocales(locales2);
            configuration3.locale = configuration2.locale;
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$n */
    public static class C0115n {
        /* renamed from: a */
        public static void m365a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
            int i9 = configuration.colorMode & 3;
            int i10 = configuration2.colorMode;
            if (i9 != (i10 & 3)) {
                configuration3.colorMode |= i10 & 3;
            }
            int i11 = configuration.colorMode & 12;
            int i12 = configuration2.colorMode;
            if (i11 != (i12 & 12)) {
                configuration3.colorMode |= i12 & 12;
            }
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$o */
    public static class C0116o {
        /* renamed from: a */
        public static void m366a(ContextThemeWrapper contextThemeWrapper, Configuration configuration) {
            contextThemeWrapper.applyOverrideConfiguration(configuration);
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$p */
    public class C0117p extends ContentFrameLayout {
        public C0117p(Context context) {
            super(context);
        }

        /* renamed from: b */
        public final boolean m367b(int i9, int i10) {
            return i9 < -5 || i10 < -5 || i9 > getWidth() + 5 || i10 > getHeight() + 5;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return AppCompatDelegateImpl.this.m288R(keyEvent) || super.dispatchKeyEvent(keyEvent);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0 || !m367b((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return super.onInterceptTouchEvent(motionEvent);
            }
            AppCompatDelegateImpl.this.m282L(0);
            return true;
        }

        @Override // android.view.View
        public void setBackgroundResource(int i9) {
            setBackgroundDrawable(C0694a.m3458b(getContext(), i9));
        }
    }

    /* renamed from: androidx.appcompat.app.AppCompatDelegateImpl$q */
    public final class C0118q implements InterfaceC0143m.a {
        public C0118q() {
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        /* renamed from: a */
        public boolean mo352a(C0137g c0137g) {
            Window.Callback callbackM301d0;
            if (c0137g != c0137g.getRootMenu()) {
                return true;
            }
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (!appCompatDelegateImpl.f286B || (callbackM301d0 = appCompatDelegateImpl.m301d0()) == null || AppCompatDelegateImpl.this.f298N) {
                return true;
            }
            callbackM301d0.onMenuOpened(108, c0137g);
            return true;
        }

        @Override // androidx.appcompat.view.menu.InterfaceC0143m.a
        public void onCloseMenu(C0137g c0137g, boolean z8) {
            C0137g rootMenu = c0137g.getRootMenu();
            boolean z9 = rootMenu != c0137g;
            AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
            if (z9) {
                c0137g = rootMenu;
            }
            PanelFeatureState panelFeatureStateM293W = appCompatDelegateImpl.m293W(c0137g);
            if (panelFeatureStateM293W != null) {
                if (!z9) {
                    AppCompatDelegateImpl.this.m283M(panelFeatureStateM293W, z8);
                } else {
                    AppCompatDelegateImpl.this.m276I(panelFeatureStateM293W.f334a, panelFeatureStateM293W, rootMenu);
                    AppCompatDelegateImpl.this.m283M(panelFeatureStateM293W, true);
                }
            }
        }
    }

    public AppCompatDelegateImpl(Activity activity, InterfaceC0121c interfaceC0121c) {
        this(activity, null, interfaceC0121c, activity);
    }

    /* renamed from: X */
    public static Configuration m259X(Configuration configuration, Configuration configuration2) {
        Configuration configuration3 = new Configuration();
        configuration3.fontScale = BitmapDescriptorFactory.HUE_RED;
        if (configuration2 != null && configuration.diff(configuration2) != 0) {
            float f9 = configuration.fontScale;
            float f10 = configuration2.fontScale;
            if (f9 != f10) {
                configuration3.fontScale = f10;
            }
            int i9 = configuration.mcc;
            int i10 = configuration2.mcc;
            if (i9 != i10) {
                configuration3.mcc = i10;
            }
            int i11 = configuration.mnc;
            int i12 = configuration2.mnc;
            if (i11 != i12) {
                configuration3.mnc = i12;
            }
            C0114m.m364a(configuration, configuration2, configuration3);
            int i13 = configuration.touchscreen;
            int i14 = configuration2.touchscreen;
            if (i13 != i14) {
                configuration3.touchscreen = i14;
            }
            int i15 = configuration.keyboard;
            int i16 = configuration2.keyboard;
            if (i15 != i16) {
                configuration3.keyboard = i16;
            }
            int i17 = configuration.keyboardHidden;
            int i18 = configuration2.keyboardHidden;
            if (i17 != i18) {
                configuration3.keyboardHidden = i18;
            }
            int i19 = configuration.navigation;
            int i20 = configuration2.navigation;
            if (i19 != i20) {
                configuration3.navigation = i20;
            }
            int i21 = configuration.navigationHidden;
            int i22 = configuration2.navigationHidden;
            if (i21 != i22) {
                configuration3.navigationHidden = i22;
            }
            int i23 = configuration.orientation;
            int i24 = configuration2.orientation;
            if (i23 != i24) {
                configuration3.orientation = i24;
            }
            int i25 = configuration.screenLayout & 15;
            int i26 = configuration2.screenLayout;
            if (i25 != (i26 & 15)) {
                configuration3.screenLayout |= i26 & 15;
            }
            int i27 = configuration.screenLayout & PsExtractor.AUDIO_STREAM;
            int i28 = configuration2.screenLayout;
            if (i27 != (i28 & PsExtractor.AUDIO_STREAM)) {
                configuration3.screenLayout |= i28 & PsExtractor.AUDIO_STREAM;
            }
            int i29 = configuration.screenLayout & 48;
            int i30 = configuration2.screenLayout;
            if (i29 != (i30 & 48)) {
                configuration3.screenLayout |= i30 & 48;
            }
            int i31 = configuration.screenLayout & 768;
            int i32 = configuration2.screenLayout;
            if (i31 != (i32 & 768)) {
                configuration3.screenLayout |= i32 & 768;
            }
            C0115n.m365a(configuration, configuration2, configuration3);
            int i33 = configuration.uiMode & 15;
            int i34 = configuration2.uiMode;
            if (i33 != (i34 & 15)) {
                configuration3.uiMode |= i34 & 15;
            }
            int i35 = configuration.uiMode & 48;
            int i36 = configuration2.uiMode;
            if (i35 != (i36 & 48)) {
                configuration3.uiMode |= i36 & 48;
            }
            int i37 = configuration.screenWidthDp;
            int i38 = configuration2.screenWidthDp;
            if (i37 != i38) {
                configuration3.screenWidthDp = i38;
            }
            int i39 = configuration.screenHeightDp;
            int i40 = configuration2.screenHeightDp;
            if (i39 != i40) {
                configuration3.screenHeightDp = i40;
            }
            int i41 = configuration.smallestScreenWidthDp;
            int i42 = configuration2.smallestScreenWidthDp;
            if (i41 != i42) {
                configuration3.smallestScreenWidthDp = i42;
            }
            C0113l.m363a(configuration, configuration2, configuration3);
        }
        return configuration3;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: A */
    public void mo260A(View view, ViewGroup.LayoutParams layoutParams) {
        m291U();
        ViewGroup viewGroup = (ViewGroup) this.f330w.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f315h.m19071a().onContentChanged();
    }

    /* renamed from: A0 */
    public final int m261A0(int i9) {
        if (i9 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            return 108;
        }
        if (i9 != 9) {
            return i9;
        }
        Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
        return 109;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: B */
    public void mo262B(int i9) {
        this.f300P = i9;
    }

    /* renamed from: B0 */
    public final boolean m263B0() {
        ViewGroup viewGroup;
        return this.f329v && (viewGroup = this.f330w) != null && C4647u.m18513I(viewGroup);
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: C */
    public final void mo264C(CharSequence charSequence) {
        this.f319l = charSequence;
        InterfaceC0249q interfaceC0249q = this.f320m;
        if (interfaceC0249q != null) {
            interfaceC0249q.setWindowTitle(charSequence);
            return;
        }
        if (m334w0() != null) {
            m334w0().mo381s(charSequence);
            return;
        }
        TextView textView = this.f331x;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* renamed from: C0 */
    public final boolean m265C0(ViewParent viewParent) {
        if (viewParent == null) {
            return false;
        }
        View decorView = this.f314g.getDecorView();
        while (viewParent != null) {
            if (viewParent == decorView || !(viewParent instanceof View) || C4647u.m18512H((View) viewParent)) {
                return false;
            }
            viewParent = viewParent.getParent();
        }
        return true;
    }

    /* renamed from: D */
    public boolean m266D() {
        return m268E(true);
    }

    /* renamed from: D0 */
    public AbstractC4796b m267D0(AbstractC4796b.a aVar) {
        InterfaceC0121c interfaceC0121c;
        if (aVar == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        AbstractC4796b abstractC4796b = this.f323p;
        if (abstractC4796b != null) {
            abstractC4796b.mo438a();
        }
        C0108g c0108g = new C0108g(aVar);
        AbstractC0119a abstractC0119aMo311k = mo311k();
        if (abstractC0119aMo311k != null) {
            AbstractC4796b abstractC4796bMo382t = abstractC0119aMo311k.mo382t(c0108g);
            this.f323p = abstractC4796bMo382t;
            if (abstractC4796bMo382t != null && (interfaceC0121c = this.f316i) != null) {
                interfaceC0121c.onSupportActionModeStarted(abstractC4796bMo382t);
            }
        }
        if (this.f323p == null) {
            this.f323p = m269E0(c0108g);
        }
        return this.f323p;
    }

    /* renamed from: E */
    public final boolean m268E(boolean z8) throws PackageManager.NameNotFoundException {
        if (this.f298N) {
            return false;
        }
        int iM274H = m274H();
        boolean zM275H0 = m275H0(m314l0(this.f313f, iM274H), z8);
        if (iM274H == 0) {
            m296a0(this.f313f).m362e();
        } else {
            AbstractC0111j abstractC0111j = this.f303S;
            if (abstractC0111j != null) {
                abstractC0111j.m361a();
            }
        }
        if (iM274H == 3) {
            m295Z(this.f313f).m362e();
        } else {
            AbstractC0111j abstractC0111j2 = this.f304T;
            if (abstractC0111j2 != null) {
                abstractC0111j2.m361a();
            }
        }
        return zM275H0;
    }

    /* renamed from: E0 */
    public AbstractC4796b m269E0(AbstractC4796b.a aVar) {
        AbstractC4796b abstractC4796bOnWindowStartingSupportActionMode;
        Context c4798d;
        InterfaceC0121c interfaceC0121c;
        m290T();
        AbstractC4796b abstractC4796b = this.f323p;
        if (abstractC4796b != null) {
            abstractC4796b.mo438a();
        }
        if (!(aVar instanceof C0108g)) {
            aVar = new C0108g(aVar);
        }
        InterfaceC0121c interfaceC0121c2 = this.f316i;
        if (interfaceC0121c2 == null || this.f298N) {
            abstractC4796bOnWindowStartingSupportActionMode = null;
        } else {
            try {
                abstractC4796bOnWindowStartingSupportActionMode = interfaceC0121c2.onWindowStartingSupportActionMode(aVar);
            } catch (AbstractMethodError unused) {
            }
        }
        if (abstractC4796bOnWindowStartingSupportActionMode != null) {
            this.f323p = abstractC4796bOnWindowStartingSupportActionMode;
        } else {
            if (this.f324q == null) {
                if (this.f289E) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = this.f313f.getTheme();
                    theme.resolveAttribute(C0560a.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = this.f313f.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        c4798d = new C4798d(this.f313f, 0);
                        c4798d.getTheme().setTo(themeNewTheme);
                    } else {
                        c4798d = this.f313f;
                    }
                    this.f324q = new ActionBarContextView(c4798d);
                    PopupWindow popupWindow = new PopupWindow(c4798d, (AttributeSet) null, C0560a.actionModePopupWindowStyle);
                    this.f325r = popupWindow;
                    C0333h.m1601b(popupWindow, 2);
                    this.f325r.setContentView(this.f324q);
                    this.f325r.setWidth(-1);
                    c4798d.getTheme().resolveAttribute(C0560a.actionBarSize, typedValue, true);
                    this.f324q.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, c4798d.getResources().getDisplayMetrics()));
                    this.f325r.setHeight(-2);
                    this.f326s = new RunnableC0105d();
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) this.f330w.findViewById(C0565f.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(m294Y()));
                        this.f324q = (ActionBarContextView) viewStubCompat.m781a();
                    }
                }
            }
            if (this.f324q != null) {
                m290T();
                this.f324q.m584k();
                C4799e c4799e = new C4799e(this.f324q.getContext(), this.f324q, aVar, this.f325r == null);
                if (aVar.mo356d(c4799e, c4799e.mo440c())) {
                    c4799e.mo444i();
                    this.f324q.m581h(c4799e);
                    this.f323p = c4799e;
                    if (m263B0()) {
                        this.f324q.setAlpha(BitmapDescriptorFactory.HUE_RED);
                        C4620d0 c4620d0M18408a = C4647u.m18533b(this.f324q).m18408a(1.0f);
                        this.f327t = c4620d0M18408a;
                        c4620d0M18408a.m18413f(new C0106e());
                    } else {
                        this.f324q.setAlpha(1.0f);
                        this.f324q.setVisibility(0);
                        this.f324q.sendAccessibilityEvent(32);
                        if (this.f324q.getParent() instanceof View) {
                            C4647u.m18527W((View) this.f324q.getParent());
                        }
                    }
                    if (this.f325r != null) {
                        this.f314g.getDecorView().post(this.f326s);
                    }
                } else {
                    this.f323p = null;
                }
            }
        }
        AbstractC4796b abstractC4796b2 = this.f323p;
        if (abstractC4796b2 != null && (interfaceC0121c = this.f316i) != null) {
            interfaceC0121c.onSupportActionModeStarted(abstractC4796b2);
        }
        return this.f323p;
    }

    /* renamed from: F */
    public final void m270F() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.f330w.findViewById(R.id.content);
        View decorView = this.f314g.getDecorView();
        contentFrameLayout.m686a(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray typedArrayObtainStyledAttributes = this.f313f.obtainStyledAttributes(C0569j.AppCompatTheme);
        typedArrayObtainStyledAttributes.getValue(C0569j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.getMinWidthMajor());
        typedArrayObtainStyledAttributes.getValue(C0569j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.getMinWidthMinor());
        int i9 = C0569j.AppCompatTheme_windowFixedWidthMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            typedArrayObtainStyledAttributes.getValue(i9, contentFrameLayout.getFixedWidthMajor());
        }
        int i10 = C0569j.AppCompatTheme_windowFixedWidthMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            typedArrayObtainStyledAttributes.getValue(i10, contentFrameLayout.getFixedWidthMinor());
        }
        int i11 = C0569j.AppCompatTheme_windowFixedHeightMajor;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            typedArrayObtainStyledAttributes.getValue(i11, contentFrameLayout.getFixedHeightMajor());
        }
        int i12 = C0569j.AppCompatTheme_windowFixedHeightMinor;
        if (typedArrayObtainStyledAttributes.hasValue(i12)) {
            typedArrayObtainStyledAttributes.getValue(i12, contentFrameLayout.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    /* renamed from: F0 */
    public final void m271F0() {
        if (this.f329v) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    /* renamed from: G */
    public final void m272G(Window window) {
        if (this.f314g != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof C0109h) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        C0109h c0109h = new C0109h(callback);
        this.f315h = c0109h;
        window.setCallback(c0109h);
        C0250q0 c0250q0M1003u = C0250q0.m1003u(this.f313f, null, f282d0);
        Drawable drawableM1012h = c0250q0M1003u.m1012h(0);
        if (drawableM1012h != null) {
            window.setBackgroundDrawable(drawableM1012h);
        }
        c0250q0M1003u.m1024w();
        this.f314g = window;
    }

    /* renamed from: G0 */
    public final AppCompatActivity m273G0() {
        for (Context baseContext = this.f313f; baseContext != null; baseContext = ((ContextWrapper) baseContext).getBaseContext()) {
            if (baseContext instanceof AppCompatActivity) {
                return (AppCompatActivity) baseContext;
            }
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    /* renamed from: H */
    public final int m274H() {
        int i9 = this.f299O;
        return i9 != -100 ? i9 : AbstractC0122d.m401h();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /* renamed from: H0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m275H0(int i9, boolean z8) throws PackageManager.NameNotFoundException {
        boolean z9;
        Configuration configurationM284N = m284N(this.f313f, i9, null);
        boolean zM310j0 = m310j0();
        int i10 = this.f313f.getResources().getConfiguration().uiMode & 48;
        int i11 = configurationM284N.uiMode & 48;
        boolean z10 = true;
        if (i10 != i11 && z8 && !zM310j0 && this.f295K && (f283e0 || this.f296L)) {
            Object obj = this.f312e;
            if ((obj instanceof Activity) && !((Activity) obj).isChild()) {
                C6226a.m23787m((Activity) this.f312e);
                z9 = true;
            }
        } else {
            z9 = false;
        }
        if (z9 || i10 == i11) {
            z10 = z9;
        } else {
            m277I0(i11, zM310j0, null);
        }
        if (z10) {
            Object obj2 = this.f312e;
            if (obj2 instanceof AppCompatActivity) {
                ((AppCompatActivity) obj2).m252L0(i9);
            }
        }
        return z10;
    }

    /* renamed from: I */
    public void m276I(int i9, PanelFeatureState panelFeatureState, Menu menu) {
        if (menu == null) {
            if (panelFeatureState == null && i9 >= 0) {
                PanelFeatureState[] panelFeatureStateArr = this.f292H;
                if (i9 < panelFeatureStateArr.length) {
                    panelFeatureState = panelFeatureStateArr[i9];
                }
            }
            if (panelFeatureState != null) {
                menu = panelFeatureState.f343j;
            }
        }
        if ((panelFeatureState == null || panelFeatureState.f348o) && !this.f298N) {
            this.f315h.m19071a().onPanelClosed(i9, menu);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: I0 */
    public final void m277I0(int i9, boolean z8, Configuration configuration) {
        Resources resources = this.f313f.getResources();
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        if (configuration != null) {
            configuration2.updateFrom(configuration);
        }
        configuration2.uiMode = i9 | (resources.getConfiguration().uiMode & (-49));
        resources.updateConfiguration(configuration2, null);
        int i10 = this.f300P;
        if (i10 != 0) {
            this.f313f.setTheme(i10);
            this.f313f.getTheme().applyStyle(this.f300P, true);
        }
        if (z8) {
            Object obj = this.f312e;
            if (obj instanceof Activity) {
                Activity activity = (Activity) obj;
                if (activity instanceof InterfaceC0385f) {
                    if (((InterfaceC0385f) activity).getLifecycle().mo2048b().m2050a(Lifecycle.State.STARTED)) {
                        activity.onConfigurationChanged(configuration2);
                    }
                } else if (this.f297M) {
                    activity.onConfigurationChanged(configuration2);
                }
            }
        }
    }

    /* renamed from: J */
    public void m278J(C0137g c0137g) {
        if (this.f291G) {
            return;
        }
        this.f291G = true;
        this.f320m.mo594i();
        Window.Callback callbackM301d0 = m301d0();
        if (callbackM301d0 != null && !this.f298N) {
            callbackM301d0.onPanelClosed(108, c0137g);
        }
        this.f291G = false;
    }

    /* renamed from: J0 */
    public final int m279J0(C4628h0 c4628h0, Rect rect) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        boolean z8;
        boolean z9;
        int iM18439g = c4628h0 != null ? c4628h0.m18439g() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.f324q;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z8 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f324q.getLayoutParams();
            if (this.f324q.isShown()) {
                if (this.f309Y == null) {
                    this.f309Y = new Rect();
                    this.f310Z = new Rect();
                }
                Rect rect2 = this.f309Y;
                Rect rect3 = this.f310Z;
                if (c4628h0 == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(c4628h0.m18437e(), c4628h0.m18439g(), c4628h0.m18438f(), c4628h0.m18436d());
                }
                C0258u0.m1067a(this.f330w, rect2, rect3);
                int i9 = rect2.top;
                int i10 = rect2.left;
                int i11 = rect2.right;
                C4628h0 c4628h0M18573y = C4647u.m18573y(this.f330w);
                int iM18437e = c4628h0M18573y == null ? 0 : c4628h0M18573y.m18437e();
                int iM18438f = c4628h0M18573y == null ? 0 : c4628h0M18573y.m18438f();
                if (marginLayoutParams.topMargin == i9 && marginLayoutParams.leftMargin == i10 && marginLayoutParams.rightMargin == i11) {
                    z9 = false;
                } else {
                    marginLayoutParams.topMargin = i9;
                    marginLayoutParams.leftMargin = i10;
                    marginLayoutParams.rightMargin = i11;
                    z9 = true;
                }
                if (i9 <= 0 || this.f332y != null) {
                    View view = this.f332y;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i12 = marginLayoutParams2.height;
                        int i13 = marginLayoutParams.topMargin;
                        if (i12 != i13 || marginLayoutParams2.leftMargin != iM18437e || marginLayoutParams2.rightMargin != iM18438f) {
                            marginLayoutParams2.height = i13;
                            marginLayoutParams2.leftMargin = iM18437e;
                            marginLayoutParams2.rightMargin = iM18438f;
                            this.f332y.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(this.f313f);
                    this.f332y = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = iM18437e;
                    layoutParams.rightMargin = iM18438f;
                    this.f330w.addView(this.f332y, -1, layoutParams);
                }
                View view3 = this.f332y;
                z = view3 != null;
                if (z && view3.getVisibility() != 0) {
                    m281K0(this.f332y);
                }
                if (!this.f288D && z) {
                    iM18439g = 0;
                }
                z8 = z;
                z = z9;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z8 = false;
            } else {
                z8 = false;
                z = false;
            }
            if (z) {
                this.f324q.setLayoutParams(marginLayoutParams);
            }
        }
        View view4 = this.f332y;
        if (view4 != null) {
            view4.setVisibility(z8 ? 0 : 8);
        }
        return iM18439g;
    }

    /* renamed from: K */
    public final void m280K() {
        AbstractC0111j abstractC0111j = this.f303S;
        if (abstractC0111j != null) {
            abstractC0111j.m361a();
        }
        AbstractC0111j abstractC0111j2 = this.f304T;
        if (abstractC0111j2 != null) {
            abstractC0111j2.m361a();
        }
    }

    /* renamed from: K0 */
    public final void m281K0(View view) {
        view.setBackgroundColor((C4647u.m18505A(view) & UserMetadata.MAX_INTERNAL_KEY_SIZE) != 0 ? C6273a.m24024c(this.f313f, C0562c.abc_decor_view_status_guard_light) : C6273a.m24024c(this.f313f, C0562c.abc_decor_view_status_guard));
    }

    /* renamed from: L */
    public void m282L(int i9) {
        m283M(m298b0(i9, true), true);
    }

    /* renamed from: M */
    public void m283M(PanelFeatureState panelFeatureState, boolean z8) {
        ViewGroup viewGroup;
        InterfaceC0249q interfaceC0249q;
        if (z8 && panelFeatureState.f334a == 0 && (interfaceC0249q = this.f320m) != null && interfaceC0249q.mo587b()) {
            m278J(panelFeatureState.f343j);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f313f.getSystemService("window");
        if (windowManager != null && panelFeatureState.f348o && (viewGroup = panelFeatureState.f340g) != null) {
            windowManager.removeView(viewGroup);
            if (z8) {
                m276I(panelFeatureState.f334a, panelFeatureState, null);
            }
        }
        panelFeatureState.f346m = false;
        panelFeatureState.f347n = false;
        panelFeatureState.f348o = false;
        panelFeatureState.f341h = null;
        panelFeatureState.f350q = true;
        if (this.f293I == panelFeatureState) {
            this.f293I = null;
        }
    }

    /* renamed from: N */
    public final Configuration m284N(Context context, int i9, Configuration configuration) {
        int i10 = i9 != 1 ? i9 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = BitmapDescriptorFactory.HUE_RED;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i10 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    /* renamed from: O */
    public final ViewGroup m285O() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        ViewGroup viewGroup;
        TypedArray typedArrayObtainStyledAttributes = this.f313f.obtainStyledAttributes(C0569j.AppCompatTheme);
        int i9 = C0569j.AppCompatTheme_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i9)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(C0569j.AppCompatTheme_windowNoTitle, false)) {
            mo335x(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(i9, false)) {
            mo335x(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(C0569j.AppCompatTheme_windowActionBarOverlay, false)) {
            mo335x(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(C0569j.AppCompatTheme_windowActionModeOverlay, false)) {
            mo335x(10);
        }
        this.f289E = typedArrayObtainStyledAttributes.getBoolean(C0569j.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        m292V();
        this.f314g.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f313f);
        if (this.f290F) {
            viewGroup = this.f288D ? (ViewGroup) layoutInflaterFrom.inflate(C0566g.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(C0566g.abc_screen_simple, (ViewGroup) null);
        } else if (this.f289E) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(C0566g.abc_dialog_title_material, (ViewGroup) null);
            this.f287C = false;
            this.f286B = false;
        } else if (this.f286B) {
            TypedValue typedValue = new TypedValue();
            this.f313f.getTheme().resolveAttribute(C0560a.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new C4798d(this.f313f, typedValue.resourceId) : this.f313f).inflate(C0566g.abc_screen_toolbar, (ViewGroup) null);
            InterfaceC0249q interfaceC0249q = (InterfaceC0249q) viewGroup.findViewById(C0565f.decor_content_parent);
            this.f320m = interfaceC0249q;
            interfaceC0249q.setWindowCallback(m301d0());
            if (this.f287C) {
                this.f320m.mo593h(109);
            }
            if (this.f333z) {
                this.f320m.mo593h(2);
            }
            if (this.f285A) {
                this.f320m.mo593h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.f286B + ", windowActionBarOverlay: " + this.f287C + ", android:windowIsFloating: " + this.f289E + ", windowActionModeOverlay: " + this.f288D + ", windowNoTitle: " + this.f290F + " }");
        }
        C4647u.m18554l0(viewGroup, new C0103b());
        if (this.f320m == null) {
            this.f331x = (TextView) viewGroup.findViewById(C0565f.title);
        }
        C0258u0.m1069c(viewGroup);
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(C0565f.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f314g.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f314g.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new C0104c());
        return viewGroup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: P */
    public View m286P(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zM265C0 = false;
        if (this.f311a0 == null) {
            String string = this.f313f.obtainStyledAttributes(C0569j.AppCompatTheme).getString(C0569j.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.f311a0 = new C0125g();
            } else {
                try {
                    this.f311a0 = (C0125g) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.f311a0 = new C0125g();
                }
            }
        }
        boolean z8 = f281c0;
        if (z8) {
            if (!(attributeSet instanceof XmlPullParser)) {
                zM265C0 = m265C0((ViewParent) view);
            } else if (((XmlPullParser) attributeSet).getDepth() > 1) {
                zM265C0 = true;
            }
        }
        return this.f311a0.createView(view, str, context, attributeSet, zM265C0, z8, true, C0256t0.m1064b());
    }

    /* renamed from: Q */
    public void m287Q() {
        C0137g c0137g;
        InterfaceC0249q interfaceC0249q = this.f320m;
        if (interfaceC0249q != null) {
            interfaceC0249q.mo594i();
        }
        if (this.f325r != null) {
            this.f314g.getDecorView().removeCallbacks(this.f326s);
            if (this.f325r.isShowing()) {
                try {
                    this.f325r.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f325r = null;
        }
        m290T();
        PanelFeatureState panelFeatureStateM298b0 = m298b0(0, false);
        if (panelFeatureStateM298b0 == null || (c0137g = panelFeatureStateM298b0.f343j) == null) {
            return;
        }
        c0137g.close();
    }

    /* renamed from: R */
    public boolean m288R(KeyEvent keyEvent) {
        View decorView;
        Object obj = this.f312e;
        if (((obj instanceof C4623f.a) || (obj instanceof DialogC0123e)) && (decorView = this.f314g.getDecorView()) != null && C4623f.m18424d(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f315h.m19071a().dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        return keyEvent.getAction() == 0 ? m318n0(keyCode, keyEvent) : m324q0(keyCode, keyEvent);
    }

    /* renamed from: S */
    public void m289S(int i9) {
        PanelFeatureState panelFeatureStateM298b0;
        PanelFeatureState panelFeatureStateM298b02 = m298b0(i9, true);
        if (panelFeatureStateM298b02.f343j != null) {
            Bundle bundle = new Bundle();
            panelFeatureStateM298b02.f343j.saveActionViewStates(bundle);
            if (bundle.size() > 0) {
                panelFeatureStateM298b02.f352s = bundle;
            }
            panelFeatureStateM298b02.f343j.stopDispatchingItemsChanged();
            panelFeatureStateM298b02.f343j.clear();
        }
        panelFeatureStateM298b02.f351r = true;
        panelFeatureStateM298b02.f350q = true;
        if ((i9 != 108 && i9 != 0) || this.f320m == null || (panelFeatureStateM298b0 = m298b0(0, false)) == null) {
            return;
        }
        panelFeatureStateM298b0.f346m = false;
        m338y0(panelFeatureStateM298b0, null);
    }

    /* renamed from: T */
    public void m290T() {
        C4620d0 c4620d0 = this.f327t;
        if (c4620d0 != null) {
            c4620d0.m18409b();
        }
    }

    /* renamed from: U */
    public final void m291U() {
        if (this.f329v) {
            return;
        }
        this.f330w = m285O();
        CharSequence charSequenceM299c0 = m299c0();
        if (!TextUtils.isEmpty(charSequenceM299c0)) {
            InterfaceC0249q interfaceC0249q = this.f320m;
            if (interfaceC0249q != null) {
                interfaceC0249q.setWindowTitle(charSequenceM299c0);
            } else if (m334w0() != null) {
                m334w0().mo381s(charSequenceM299c0);
            } else {
                TextView textView = this.f331x;
                if (textView != null) {
                    textView.setText(charSequenceM299c0);
                }
            }
        }
        m270F();
        m332u0(this.f330w);
        this.f329v = true;
        PanelFeatureState panelFeatureStateM298b0 = m298b0(0, false);
        if (this.f298N) {
            return;
        }
        if (panelFeatureStateM298b0 == null || panelFeatureStateM298b0.f343j == null) {
            m308i0(108);
        }
    }

    /* renamed from: V */
    public final void m292V() {
        if (this.f314g == null) {
            Object obj = this.f312e;
            if (obj instanceof Activity) {
                m272G(((Activity) obj).getWindow());
            }
        }
        if (this.f314g == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    /* renamed from: W */
    public PanelFeatureState m293W(Menu menu) {
        PanelFeatureState[] panelFeatureStateArr = this.f292H;
        int length = panelFeatureStateArr != null ? panelFeatureStateArr.length : 0;
        for (int i9 = 0; i9 < length; i9++) {
            PanelFeatureState panelFeatureState = panelFeatureStateArr[i9];
            if (panelFeatureState != null && panelFeatureState.f343j == menu) {
                return panelFeatureState;
            }
        }
        return null;
    }

    /* renamed from: Y */
    public final Context m294Y() {
        AbstractC0119a abstractC0119aMo311k = mo311k();
        Context contextMo372j = abstractC0119aMo311k != null ? abstractC0119aMo311k.mo372j() : null;
        return contextMo372j == null ? this.f313f : contextMo372j;
    }

    /* renamed from: Z */
    public final AbstractC0111j m295Z(Context context) {
        if (this.f304T == null) {
            this.f304T = new C0110i(context);
        }
        return this.f304T;
    }

    /* renamed from: a0 */
    public final AbstractC0111j m296a0(Context context) {
        if (this.f303S == null) {
            this.f303S = new C0112k(C0127i.m408a(context));
        }
        return this.f303S;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: b */
    public void mo297b(View view, ViewGroup.LayoutParams layoutParams) {
        m291U();
        ((ViewGroup) this.f330w.findViewById(R.id.content)).addView(view, layoutParams);
        this.f315h.m19071a().onContentChanged();
    }

    /* renamed from: b0 */
    public PanelFeatureState m298b0(int i9, boolean z8) {
        PanelFeatureState[] panelFeatureStateArr = this.f292H;
        if (panelFeatureStateArr == null || panelFeatureStateArr.length <= i9) {
            PanelFeatureState[] panelFeatureStateArr2 = new PanelFeatureState[i9 + 1];
            if (panelFeatureStateArr != null) {
                System.arraycopy(panelFeatureStateArr, 0, panelFeatureStateArr2, 0, panelFeatureStateArr.length);
            }
            this.f292H = panelFeatureStateArr2;
            panelFeatureStateArr = panelFeatureStateArr2;
        }
        PanelFeatureState panelFeatureState = panelFeatureStateArr[i9];
        if (panelFeatureState != null) {
            return panelFeatureState;
        }
        PanelFeatureState panelFeatureState2 = new PanelFeatureState(i9);
        panelFeatureStateArr[i9] = panelFeatureState2;
        return panelFeatureState2;
    }

    /* renamed from: c0 */
    public final CharSequence m299c0() {
        Object obj = this.f312e;
        return obj instanceof Activity ? ((Activity) obj).getTitle() : this.f319l;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: d */
    public Context mo300d(Context context) {
        this.f295K = true;
        int iM314l0 = m314l0(context, m274H());
        if (f284f0 && (context instanceof ContextThemeWrapper)) {
            try {
                C0116o.m366a((ContextThemeWrapper) context, m284N(context, iM314l0, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof C4798d) {
            try {
                ((C4798d) context).m19043a(m284N(context, iM314l0, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!f283e0) {
            return super.mo300d(context);
        }
        try {
            Configuration configuration = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration2 = context.getResources().getConfiguration();
            Configuration configurationM284N = m284N(context, iM314l0, configuration.equals(configuration2) ? null : m259X(configuration, configuration2));
            C4798d c4798d = new C4798d(context, C0568i.Theme_AppCompat_Empty);
            c4798d.m19043a(configurationM284N);
            boolean z8 = false;
            try {
                z8 = context.getTheme() != null;
            } catch (NullPointerException unused3) {
            }
            if (z8) {
                C6348b.b.m24369a(c4798d.getTheme());
            }
            return super.mo300d(c4798d);
        } catch (PackageManager.NameNotFoundException e9) {
            throw new RuntimeException("Application failed to obtain resources from itself", e9);
        }
    }

    /* renamed from: d0 */
    public final Window.Callback m301d0() {
        return this.f314g.getCallback();
    }

    /* renamed from: e0 */
    public final void m302e0() {
        m291U();
        if (this.f286B && this.f317j == null) {
            Object obj = this.f312e;
            if (obj instanceof Activity) {
                this.f317j = new C0128j((Activity) this.f312e, this.f287C);
            } else if (obj instanceof Dialog) {
                this.f317j = new C0128j((Dialog) this.f312e);
            }
            AbstractC0119a abstractC0119a = this.f317j;
            if (abstractC0119a != null) {
                abstractC0119a.mo379q(this.f308X);
            }
        }
    }

    /* renamed from: f0 */
    public final boolean m303f0(PanelFeatureState panelFeatureState) {
        View view = panelFeatureState.f342i;
        if (view != null) {
            panelFeatureState.f341h = view;
            return true;
        }
        if (panelFeatureState.f343j == null) {
            return false;
        }
        if (this.f322o == null) {
            this.f322o = new C0118q();
        }
        View view2 = (View) panelFeatureState.m341a(this.f322o);
        panelFeatureState.f341h = view2;
        return view2 != null;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: g */
    public <T extends View> T mo304g(int i9) {
        m291U();
        return (T) this.f314g.findViewById(i9);
    }

    /* renamed from: g0 */
    public final boolean m305g0(PanelFeatureState panelFeatureState) {
        panelFeatureState.m344d(m294Y());
        panelFeatureState.f340g = new C0117p(panelFeatureState.f345l);
        panelFeatureState.f336c = 81;
        return true;
    }

    /* renamed from: h0 */
    public final boolean m306h0(PanelFeatureState panelFeatureState) {
        Resources.Theme themeNewTheme;
        Context context = this.f313f;
        int i9 = panelFeatureState.f334a;
        if ((i9 == 0 || i9 == 108) && this.f320m != null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = context.getTheme();
            theme.resolveAttribute(C0560a.actionBarTheme, typedValue, true);
            if (typedValue.resourceId != 0) {
                themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(theme);
                themeNewTheme.applyStyle(typedValue.resourceId, true);
                themeNewTheme.resolveAttribute(C0560a.actionBarWidgetTheme, typedValue, true);
            } else {
                theme.resolveAttribute(C0560a.actionBarWidgetTheme, typedValue, true);
                themeNewTheme = null;
            }
            if (typedValue.resourceId != 0) {
                if (themeNewTheme == null) {
                    themeNewTheme = context.getResources().newTheme();
                    themeNewTheme.setTo(theme);
                }
                themeNewTheme.applyStyle(typedValue.resourceId, true);
            }
            if (themeNewTheme != null) {
                C4798d c4798d = new C4798d(context, 0);
                c4798d.getTheme().setTo(themeNewTheme);
                context = c4798d;
            }
        }
        C0137g c0137g = new C0137g(context);
        c0137g.setCallback(this);
        panelFeatureState.m343c(c0137g);
        return true;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: i */
    public int mo307i() {
        return this.f299O;
    }

    /* renamed from: i0 */
    public final void m308i0(int i9) {
        this.f306V = (1 << i9) | this.f306V;
        if (this.f305U) {
            return;
        }
        C4647u.m18525U(this.f314g.getDecorView(), this.f307W);
        this.f305U = true;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: j */
    public MenuInflater mo309j() {
        if (this.f318k == null) {
            m302e0();
            AbstractC0119a abstractC0119a = this.f317j;
            this.f318k = new C4801g(abstractC0119a != null ? abstractC0119a.mo372j() : this.f313f);
        }
        return this.f318k;
    }

    /* renamed from: j0 */
    public final boolean m310j0() throws PackageManager.NameNotFoundException {
        if (!this.f302R && (this.f312e instanceof Activity)) {
            PackageManager packageManager = this.f313f.getPackageManager();
            if (packageManager == null) {
                return false;
            }
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(this.f313f, this.f312e.getClass()), Build.VERSION.SDK_INT >= 29 ? 269221888 : 786432);
                this.f301Q = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
            } catch (PackageManager.NameNotFoundException e9) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e9);
                this.f301Q = false;
            }
        }
        this.f302R = true;
        return this.f301Q;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: k */
    public AbstractC0119a mo311k() {
        m302e0();
        return this.f317j;
    }

    /* renamed from: k0 */
    public boolean m312k0() {
        return this.f328u;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: l */
    public void mo313l() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f313f);
        if (layoutInflaterFrom.getFactory() == null) {
            C4625g.m18427a(layoutInflaterFrom, this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof AppCompatDelegateImpl) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    /* renamed from: l0 */
    public int m314l0(Context context, int i9) {
        if (i9 == -100) {
            return -1;
        }
        if (i9 != -1) {
            if (i9 == 0) {
                if (((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() == 0) {
                    return -1;
                }
                return m296a0(context).mo359c();
            }
            if (i9 != 1 && i9 != 2) {
                if (i9 == 3) {
                    return m295Z(context).mo359c();
                }
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
        return i9;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: m */
    public void mo315m() {
        AbstractC0119a abstractC0119aMo311k = mo311k();
        if (abstractC0119aMo311k == null || !abstractC0119aMo311k.m373k()) {
            m308i0(0);
        }
    }

    /* renamed from: m0 */
    public boolean m316m0() {
        AbstractC4796b abstractC4796b = this.f323p;
        if (abstractC4796b != null) {
            abstractC4796b.mo438a();
            return true;
        }
        AbstractC0119a abstractC0119aMo311k = mo311k();
        return abstractC0119aMo311k != null && abstractC0119aMo311k.mo369g();
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: n */
    public void mo317n(Configuration configuration) throws PackageManager.NameNotFoundException {
        AbstractC0119a abstractC0119aMo311k;
        if (this.f286B && this.f329v && (abstractC0119aMo311k = mo311k()) != null) {
            abstractC0119aMo311k.mo374l(configuration);
        }
        C0227f.m819b().m826g(this.f313f);
        m268E(false);
    }

    /* renamed from: n0 */
    public boolean m318n0(int i9, KeyEvent keyEvent) {
        if (i9 == 4) {
            this.f294J = (keyEvent.getFlags() & 128) != 0;
        } else if (i9 == 82) {
            m320o0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: o */
    public void mo319o(Bundle bundle) throws PackageManager.NameNotFoundException {
        String strM23810c;
        this.f295K = true;
        m268E(false);
        m292V();
        Object obj = this.f312e;
        if (obj instanceof Activity) {
            try {
                strM23810c = C6230e.m23810c((Activity) obj);
            } catch (IllegalArgumentException unused) {
                strM23810c = null;
            }
            if (strM23810c != null) {
                AbstractC0119a abstractC0119aM334w0 = m334w0();
                if (abstractC0119aM334w0 == null) {
                    this.f308X = true;
                } else {
                    abstractC0119aM334w0.mo379q(true);
                }
            }
            AbstractC0122d.m398a(this);
        }
        this.f296L = true;
    }

    /* renamed from: o0 */
    public final boolean m320o0(int i9, KeyEvent keyEvent) {
        if (keyEvent.getRepeatCount() != 0) {
            return false;
        }
        PanelFeatureState panelFeatureStateM298b0 = m298b0(i9, true);
        if (panelFeatureStateM298b0.f348o) {
            return false;
        }
        return m338y0(panelFeatureStateM298b0, keyEvent);
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return m286P(view, str, context, attributeSet);
    }

    @Override // androidx.appcompat.view.menu.C0137g.a
    public boolean onMenuItemSelected(C0137g c0137g, MenuItem menuItem) {
        PanelFeatureState panelFeatureStateM293W;
        Window.Callback callbackM301d0 = m301d0();
        if (callbackM301d0 == null || this.f298N || (panelFeatureStateM293W = m293W(c0137g.getRootMenu())) == null) {
            return false;
        }
        return callbackM301d0.onMenuItemSelected(panelFeatureStateM293W.f334a, menuItem);
    }

    @Override // androidx.appcompat.view.menu.C0137g.a
    public void onMenuModeChange(C0137g c0137g) {
        m340z0(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: p */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void mo321p() {
        if (this.f312e instanceof Activity) {
            AbstractC0122d.m402v(this);
        }
        if (this.f305U) {
            this.f314g.getDecorView().removeCallbacks(this.f307W);
        }
        this.f297M = false;
        this.f298N = true;
        if (this.f299O != -100) {
            Object obj = this.f312e;
            if ((obj instanceof Activity) && ((Activity) obj).isChangingConfigurations()) {
                f280b0.put(this.f312e.getClass().getName(), Integer.valueOf(this.f299O));
            } else {
                f280b0.remove(this.f312e.getClass().getName());
            }
        }
        AbstractC0119a abstractC0119a = this.f317j;
        if (abstractC0119a != null) {
            abstractC0119a.m375m();
        }
        m280K();
    }

    /* renamed from: p0 */
    public boolean m322p0(int i9, KeyEvent keyEvent) {
        AbstractC0119a abstractC0119aMo311k = mo311k();
        if (abstractC0119aMo311k != null && abstractC0119aMo311k.mo376n(i9, keyEvent)) {
            return true;
        }
        PanelFeatureState panelFeatureState = this.f293I;
        if (panelFeatureState != null && m336x0(panelFeatureState, keyEvent.getKeyCode(), keyEvent, 1)) {
            PanelFeatureState panelFeatureState2 = this.f293I;
            if (panelFeatureState2 != null) {
                panelFeatureState2.f347n = true;
            }
            return true;
        }
        if (this.f293I == null) {
            PanelFeatureState panelFeatureStateM298b0 = m298b0(0, true);
            m338y0(panelFeatureStateM298b0, keyEvent);
            boolean zM336x0 = m336x0(panelFeatureStateM298b0, keyEvent.getKeyCode(), keyEvent, 1);
            panelFeatureStateM298b0.f346m = false;
            if (zM336x0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: q */
    public void mo323q(Bundle bundle) {
        m291U();
    }

    /* renamed from: q0 */
    public boolean m324q0(int i9, KeyEvent keyEvent) {
        if (i9 == 4) {
            boolean z8 = this.f294J;
            this.f294J = false;
            PanelFeatureState panelFeatureStateM298b0 = m298b0(0, false);
            if (panelFeatureStateM298b0 != null && panelFeatureStateM298b0.f348o) {
                if (!z8) {
                    m283M(panelFeatureStateM298b0, true);
                }
                return true;
            }
            if (m316m0()) {
                return true;
            }
        } else if (i9 == 82) {
            m326r0(0, keyEvent);
            return true;
        }
        return false;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: r */
    public void mo325r() {
        AbstractC0119a abstractC0119aMo311k = mo311k();
        if (abstractC0119aMo311k != null) {
            abstractC0119aMo311k.mo380r(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /* renamed from: r0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean m326r0(int i9, KeyEvent keyEvent) {
        boolean zM338y0;
        InterfaceC0249q interfaceC0249q;
        if (this.f323p != null) {
            return false;
        }
        boolean zMo591f = true;
        PanelFeatureState panelFeatureStateM298b0 = m298b0(i9, true);
        if (i9 != 0 || (interfaceC0249q = this.f320m) == null || !interfaceC0249q.mo589d() || ViewConfiguration.get(this.f313f).hasPermanentMenuKey()) {
            boolean z8 = panelFeatureStateM298b0.f348o;
            if (z8 || panelFeatureStateM298b0.f347n) {
                m283M(panelFeatureStateM298b0, true);
                zMo591f = z8;
            } else if (panelFeatureStateM298b0.f346m) {
                if (panelFeatureStateM298b0.f351r) {
                    panelFeatureStateM298b0.f346m = false;
                    zM338y0 = m338y0(panelFeatureStateM298b0, keyEvent);
                } else {
                    zM338y0 = true;
                }
                if (zM338y0) {
                    m333v0(panelFeatureStateM298b0, keyEvent);
                }
            } else {
                zMo591f = false;
            }
        } else if (this.f320m.mo587b()) {
            zMo591f = this.f320m.mo591f();
        } else if (!this.f298N && m338y0(panelFeatureStateM298b0, keyEvent)) {
            zMo591f = this.f320m.mo592g();
        }
        if (zMo591f) {
            AudioManager audioManager = (AudioManager) this.f313f.getApplicationContext().getSystemService(MimeTypes.BASE_TYPE_AUDIO);
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
            } else {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
        return zMo591f;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: s */
    public void mo327s(Bundle bundle) {
    }

    /* renamed from: s0 */
    public void m328s0(int i9) {
        AbstractC0119a abstractC0119aMo311k;
        if (i9 != 108 || (abstractC0119aMo311k = mo311k()) == null) {
            return;
        }
        abstractC0119aMo311k.mo370h(true);
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: t */
    public void mo329t() {
        this.f297M = true;
        m266D();
    }

    /* renamed from: t0 */
    public void m330t0(int i9) {
        if (i9 == 108) {
            AbstractC0119a abstractC0119aMo311k = mo311k();
            if (abstractC0119aMo311k != null) {
                abstractC0119aMo311k.mo370h(false);
                return;
            }
            return;
        }
        if (i9 == 0) {
            PanelFeatureState panelFeatureStateM298b0 = m298b0(i9, true);
            if (panelFeatureStateM298b0.f348o) {
                m283M(panelFeatureStateM298b0, false);
            }
        }
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: u */
    public void mo331u() {
        this.f297M = false;
        AbstractC0119a abstractC0119aMo311k = mo311k();
        if (abstractC0119aMo311k != null) {
            abstractC0119aMo311k.mo380r(false);
        }
    }

    /* renamed from: u0 */
    public void m332u0(ViewGroup viewGroup) {
    }

    /* renamed from: v0 */
    public final void m333v0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        int i9;
        ViewGroup.LayoutParams layoutParams;
        if (panelFeatureState.f348o || this.f298N) {
            return;
        }
        if (panelFeatureState.f334a == 0) {
            if ((this.f313f.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackM301d0 = m301d0();
        if (callbackM301d0 != null && !callbackM301d0.onMenuOpened(panelFeatureState.f334a, panelFeatureState.f343j)) {
            m283M(panelFeatureState, true);
            return;
        }
        WindowManager windowManager = (WindowManager) this.f313f.getSystemService("window");
        if (windowManager != null && m338y0(panelFeatureState, keyEvent)) {
            ViewGroup viewGroup = panelFeatureState.f340g;
            if (viewGroup != null && !panelFeatureState.f350q) {
                View view = panelFeatureState.f342i;
                if (view != null && (layoutParams = view.getLayoutParams()) != null && layoutParams.width == -1) {
                    i9 = -1;
                }
                panelFeatureState.f347n = false;
                WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams(i9, -2, panelFeatureState.f337d, panelFeatureState.f338e, CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE, 8519680, -3);
                layoutParams2.gravity = panelFeatureState.f336c;
                layoutParams2.windowAnimations = panelFeatureState.f339f;
                windowManager.addView(panelFeatureState.f340g, layoutParams2);
                panelFeatureState.f348o = true;
            }
            if (viewGroup == null) {
                if (!m305g0(panelFeatureState) || panelFeatureState.f340g == null) {
                    return;
                }
            } else if (panelFeatureState.f350q && viewGroup.getChildCount() > 0) {
                panelFeatureState.f340g.removeAllViews();
            }
            if (!m303f0(panelFeatureState) || !panelFeatureState.m342b()) {
                panelFeatureState.f350q = true;
                return;
            }
            ViewGroup.LayoutParams layoutParams3 = panelFeatureState.f341h.getLayoutParams();
            if (layoutParams3 == null) {
                layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
            }
            panelFeatureState.f340g.setBackgroundResource(panelFeatureState.f335b);
            ViewParent parent = panelFeatureState.f341h.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(panelFeatureState.f341h);
            }
            panelFeatureState.f340g.addView(panelFeatureState.f341h, layoutParams3);
            if (!panelFeatureState.f341h.hasFocus()) {
                panelFeatureState.f341h.requestFocus();
            }
            i9 = -2;
            panelFeatureState.f347n = false;
            WindowManager.LayoutParams layoutParams22 = new WindowManager.LayoutParams(i9, -2, panelFeatureState.f337d, panelFeatureState.f338e, CredentialsApi.ACTIVITY_RESULT_NO_HINTS_AVAILABLE, 8519680, -3);
            layoutParams22.gravity = panelFeatureState.f336c;
            layoutParams22.windowAnimations = panelFeatureState.f339f;
            windowManager.addView(panelFeatureState.f340g, layoutParams22);
            panelFeatureState.f348o = true;
        }
    }

    /* renamed from: w0 */
    public final AbstractC0119a m334w0() {
        return this.f317j;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: x */
    public boolean mo335x(int i9) {
        int iM261A0 = m261A0(i9);
        if (this.f290F && iM261A0 == 108) {
            return false;
        }
        if (this.f286B && iM261A0 == 1) {
            this.f286B = false;
        }
        if (iM261A0 == 1) {
            m271F0();
            this.f290F = true;
            return true;
        }
        if (iM261A0 == 2) {
            m271F0();
            this.f333z = true;
            return true;
        }
        if (iM261A0 == 5) {
            m271F0();
            this.f285A = true;
            return true;
        }
        if (iM261A0 == 10) {
            m271F0();
            this.f288D = true;
            return true;
        }
        if (iM261A0 == 108) {
            m271F0();
            this.f286B = true;
            return true;
        }
        if (iM261A0 != 109) {
            return this.f314g.requestFeature(iM261A0);
        }
        m271F0();
        this.f287C = true;
        return true;
    }

    /* renamed from: x0 */
    public final boolean m336x0(PanelFeatureState panelFeatureState, int i9, KeyEvent keyEvent, int i10) {
        C0137g c0137g;
        boolean zPerformShortcut = false;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((panelFeatureState.f346m || m338y0(panelFeatureState, keyEvent)) && (c0137g = panelFeatureState.f343j) != null) {
            zPerformShortcut = c0137g.performShortcut(i9, keyEvent, i10);
        }
        if (zPerformShortcut && (i10 & 1) == 0 && this.f320m == null) {
            m283M(panelFeatureState, true);
        }
        return zPerformShortcut;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: y */
    public void mo337y(int i9) {
        m291U();
        ViewGroup viewGroup = (ViewGroup) this.f330w.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f313f).inflate(i9, viewGroup);
        this.f315h.m19071a().onContentChanged();
    }

    /* renamed from: y0 */
    public final boolean m338y0(PanelFeatureState panelFeatureState, KeyEvent keyEvent) {
        InterfaceC0249q interfaceC0249q;
        InterfaceC0249q interfaceC0249q2;
        InterfaceC0249q interfaceC0249q3;
        if (this.f298N) {
            return false;
        }
        if (panelFeatureState.f346m) {
            return true;
        }
        PanelFeatureState panelFeatureState2 = this.f293I;
        if (panelFeatureState2 != null && panelFeatureState2 != panelFeatureState) {
            m283M(panelFeatureState2, false);
        }
        Window.Callback callbackM301d0 = m301d0();
        if (callbackM301d0 != null) {
            panelFeatureState.f342i = callbackM301d0.onCreatePanelView(panelFeatureState.f334a);
        }
        int i9 = panelFeatureState.f334a;
        boolean z8 = i9 == 0 || i9 == 108;
        if (z8 && (interfaceC0249q3 = this.f320m) != null) {
            interfaceC0249q3.mo588c();
        }
        if (panelFeatureState.f342i == null) {
            if (z8) {
                m334w0();
            }
            C0137g c0137g = panelFeatureState.f343j;
            if (c0137g == null || panelFeatureState.f351r) {
                if (c0137g == null && (!m306h0(panelFeatureState) || panelFeatureState.f343j == null)) {
                    return false;
                }
                if (z8 && this.f320m != null) {
                    if (this.f321n == null) {
                        this.f321n = new C0107f();
                    }
                    this.f320m.mo586a(panelFeatureState.f343j, this.f321n);
                }
                panelFeatureState.f343j.stopDispatchingItemsChanged();
                if (!callbackM301d0.onCreatePanelMenu(panelFeatureState.f334a, panelFeatureState.f343j)) {
                    panelFeatureState.m343c(null);
                    if (z8 && (interfaceC0249q = this.f320m) != null) {
                        interfaceC0249q.mo586a(null, this.f321n);
                    }
                    return false;
                }
                panelFeatureState.f351r = false;
            }
            panelFeatureState.f343j.stopDispatchingItemsChanged();
            Bundle bundle = panelFeatureState.f352s;
            if (bundle != null) {
                panelFeatureState.f343j.restoreActionViewStates(bundle);
                panelFeatureState.f352s = null;
            }
            if (!callbackM301d0.onPreparePanel(0, panelFeatureState.f342i, panelFeatureState.f343j)) {
                if (z8 && (interfaceC0249q2 = this.f320m) != null) {
                    interfaceC0249q2.mo586a(null, this.f321n);
                }
                panelFeatureState.f343j.startDispatchingItemsChanged();
                return false;
            }
            boolean z9 = KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1;
            panelFeatureState.f349p = z9;
            panelFeatureState.f343j.setQwertyMode(z9);
            panelFeatureState.f343j.startDispatchingItemsChanged();
        }
        panelFeatureState.f346m = true;
        panelFeatureState.f347n = false;
        this.f293I = panelFeatureState;
        return true;
    }

    @Override // androidx.appcompat.app.AbstractC0122d
    /* renamed from: z */
    public void mo339z(View view) {
        m291U();
        ViewGroup viewGroup = (ViewGroup) this.f330w.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f315h.m19071a().onContentChanged();
    }

    /* renamed from: z0 */
    public final void m340z0(boolean z8) {
        InterfaceC0249q interfaceC0249q = this.f320m;
        if (interfaceC0249q == null || !interfaceC0249q.mo589d() || (ViewConfiguration.get(this.f313f).hasPermanentMenuKey() && !this.f320m.mo590e())) {
            PanelFeatureState panelFeatureStateM298b0 = m298b0(0, true);
            panelFeatureStateM298b0.f350q = true;
            m283M(panelFeatureStateM298b0, false);
            m333v0(panelFeatureStateM298b0, null);
            return;
        }
        Window.Callback callbackM301d0 = m301d0();
        if (this.f320m.mo587b() && z8) {
            this.f320m.mo591f();
            if (this.f298N) {
                return;
            }
            callbackM301d0.onPanelClosed(108, m298b0(0, true).f343j);
            return;
        }
        if (callbackM301d0 == null || this.f298N) {
            return;
        }
        if (this.f305U && (this.f306V & 1) != 0) {
            this.f314g.getDecorView().removeCallbacks(this.f307W);
            this.f307W.run();
        }
        PanelFeatureState panelFeatureStateM298b02 = m298b0(0, true);
        C0137g c0137g = panelFeatureStateM298b02.f343j;
        if (c0137g == null || panelFeatureStateM298b02.f351r || !callbackM301d0.onPreparePanel(0, panelFeatureStateM298b02.f342i, c0137g)) {
            return;
        }
        callbackM301d0.onMenuOpened(108, panelFeatureStateM298b02.f343j);
        this.f320m.mo592g();
    }

    public AppCompatDelegateImpl(Dialog dialog, InterfaceC0121c interfaceC0121c) {
        this(dialog.getContext(), dialog.getWindow(), interfaceC0121c, dialog);
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public AppCompatDelegateImpl(Context context, Window window, InterfaceC0121c interfaceC0121c, Object obj) {
        C5308g<String, Integer> c5308g;
        Integer num;
        AppCompatActivity appCompatActivityM273G0;
        this.f327t = null;
        this.f328u = true;
        this.f299O = -100;
        this.f307W = new RunnableC0102a();
        this.f313f = context;
        this.f316i = interfaceC0121c;
        this.f312e = obj;
        if (this.f299O == -100 && (obj instanceof Dialog) && (appCompatActivityM273G0 = m273G0()) != null) {
            this.f299O = appCompatActivityM273G0.m249I0().mo307i();
        }
        if (this.f299O == -100 && (num = (c5308g = f280b0).get(obj.getClass().getName())) != null) {
            this.f299O = num.intValue();
            c5308g.remove(obj.getClass().getName());
        }
        if (window != null) {
            m272G(window);
        }
        C0227f.m821h();
    }
}
