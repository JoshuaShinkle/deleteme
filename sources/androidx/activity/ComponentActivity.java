package androidx.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.C0386g;
import androidx.lifecycle.C0396q;
import androidx.lifecycle.FragmentC0393n;
import androidx.lifecycle.InterfaceC0383d;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.InterfaceC0397r;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.C0493a;
import androidx.savedstate.InterfaceC0494b;
import androidx.savedstate.SavedStateRegistry;

/* loaded from: classes.dex */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements InterfaceC0397r, InterfaceC0494b, InterfaceC0096c {
    private int mContentLayoutId;
    private final C0386g mLifecycleRegistry;
    private final OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final C0493a mSavedStateRegistryController;
    private C0396q mViewModelStore;

    /* renamed from: androidx.activity.ComponentActivity$a */
    public class RunnableC0091a implements Runnable {
        public RunnableC0091a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ComponentActivity.super.onBackPressed();
        }
    }

    /* renamed from: androidx.activity.ComponentActivity$b */
    public static final class C0092b {

        /* renamed from: a */
        public Object f159a;

        /* renamed from: b */
        public C0396q f160b;
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new C0386g(this);
        this.mSavedStateRegistryController = C0493a.m2968a(this);
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new RunnableC0091a());
        if (getLifecycle() == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
        }
        getLifecycle().mo2047a(new InterfaceC0383d() { // from class: androidx.activity.ComponentActivity.2
            @Override // androidx.lifecycle.InterfaceC0383d
            /* renamed from: c */
            public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_STOP) {
                    Window window = ComponentActivity.this.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        getLifecycle().mo2047a(new InterfaceC0383d() { // from class: androidx.activity.ComponentActivity.3
            @Override // androidx.lifecycle.InterfaceC0383d
            /* renamed from: c */
            public void mo209c(InterfaceC0385f interfaceC0385f, Lifecycle.Event event) {
                if (event != Lifecycle.Event.ON_DESTROY || ComponentActivity.this.isChangingConfigurations()) {
                    return;
                }
                ComponentActivity.this.getViewModelStore().m2116a();
            }
        });
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        C0092b c0092b = (C0092b) getLastNonConfigurationInstance();
        if (c0092b != null) {
            return c0092b.f159a;
        }
        return null;
    }

    @Override // androidx.core.app.ComponentActivity, androidx.lifecycle.InterfaceC0385f
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // androidx.activity.InterfaceC0096c
    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // androidx.savedstate.InterfaceC0494b
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.m2969b();
    }

    @Override // androidx.lifecycle.InterfaceC0397r
    public C0396q getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.mViewModelStore == null) {
            C0092b c0092b = (C0092b) getLastNonConfigurationInstance();
            if (c0092b != null) {
                this.mViewModelStore = c0092b.f160b;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new C0396q();
            }
        }
        return this.mViewModelStore;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.m213c();
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.m2970c(bundle);
        FragmentC0393n.m2105e(this);
        int i9 = this.mContentLayoutId;
        if (i9 != 0) {
            setContentView(i9);
        }
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        C0092b c0092b;
        Object objOnRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        C0396q c0396q = this.mViewModelStore;
        if (c0396q == null && (c0092b = (C0092b) getLastNonConfigurationInstance()) != null) {
            c0396q = c0092b.f160b;
        }
        if (c0396q == null && objOnRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        C0092b c0092b2 = new C0092b();
        c0092b2.f159a = objOnRetainCustomNonConfigurationInstance;
        c0092b2.f160b = c0396q;
        return c0092b2;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        Lifecycle lifecycle = getLifecycle();
        if (lifecycle instanceof C0386g) {
            ((C0386g) lifecycle).m2094p(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.m2971d(bundle);
    }

    public ComponentActivity(int i9) {
        this();
        this.mContentLayoutId = i9;
    }
}
