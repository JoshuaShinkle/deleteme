package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.C0386g;
import androidx.lifecycle.FragmentC0393n;
import androidx.lifecycle.InterfaceC0385f;
import androidx.lifecycle.Lifecycle;
import p042d0.C4623f;
import p132m.C5308g;

/* loaded from: classes.dex */
public class ComponentActivity extends Activity implements InterfaceC0385f, C4623f.a {
    private C5308g<Class<? extends C0312a>, C0312a> mExtraDataMap = new C5308g<>();
    private C0386g mLifecycleRegistry = new C0386g(this);

    @Deprecated
    /* renamed from: androidx.core.app.ComponentActivity$a */
    public static class C0312a {
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C4623f.m18424d(decorView, keyEvent)) {
            return C4623f.m18425e(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !C4623f.m18424d(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Deprecated
    public <T extends C0312a> T getExtraData(Class<T> cls) {
        return (T) this.mExtraDataMap.get(cls);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // android.app.Activity
    @SuppressLint({"RestrictedApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentC0393n.m2105e(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.mLifecycleRegistry.m2090k(Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public void putExtraData(C0312a c0312a) {
        this.mExtraDataMap.put(c0312a.getClass(), c0312a);
    }

    @Override // p042d0.C4623f.a
    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}
