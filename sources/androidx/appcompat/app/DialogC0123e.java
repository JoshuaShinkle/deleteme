package androidx.appcompat.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import p010b.C0560a;
import p042d0.C4623f;
import p071g.AbstractC4796b;

/* renamed from: androidx.appcompat.app.e */
/* loaded from: classes.dex */
public class DialogC0123e extends Dialog implements InterfaceC0121c {
    private AbstractC0122d mDelegate;
    private final C4623f.a mKeyDispatcher;

    /* renamed from: androidx.appcompat.app.e$a */
    public class a implements C4623f.a {
        public a() {
        }

        @Override // p042d0.C4623f.a
        public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
            return DialogC0123e.this.superDispatchKeyEvent(keyEvent);
        }
    }

    public DialogC0123e(Context context) {
        this(context, 0);
    }

    private static int getThemeResId(Context context, int i9) {
        if (i9 != 0) {
            return i9;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0560a.dialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().mo297b(view, layoutParams);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        getDelegate().mo321p();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return C4623f.m18425e(this.mKeyDispatcher, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i9) {
        return (T) getDelegate().mo304g(i9);
    }

    public AbstractC0122d getDelegate() {
        if (this.mDelegate == null) {
            this.mDelegate = AbstractC0122d.m400f(this, this);
        }
        return this.mDelegate;
    }

    public AbstractC0119a getSupportActionBar() {
        return getDelegate().mo311k();
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        getDelegate().mo315m();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        getDelegate().mo313l();
        super.onCreate(bundle);
        getDelegate().mo319o(bundle);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        getDelegate().mo331u();
    }

    @Override // androidx.appcompat.app.InterfaceC0121c
    public void onSupportActionModeFinished(AbstractC4796b abstractC4796b) {
    }

    @Override // androidx.appcompat.app.InterfaceC0121c
    public void onSupportActionModeStarted(AbstractC4796b abstractC4796b) {
    }

    @Override // androidx.appcompat.app.InterfaceC0121c
    public AbstractC4796b onWindowStartingSupportActionMode(AbstractC4796b.a aVar) {
        return null;
    }

    @Override // android.app.Dialog
    public void setContentView(int i9) {
        getDelegate().mo337y(i9);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        getDelegate().mo264C(charSequence);
    }

    boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean supportRequestWindowFeature(int i9) {
        return getDelegate().mo335x(i9);
    }

    public DialogC0123e(Context context, int i9) {
        super(context, getThemeResId(context, i9));
        this.mKeyDispatcher = new a();
        AbstractC0122d delegate = getDelegate();
        delegate.mo262B(getThemeResId(context, i9));
        delegate.mo319o(null);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        getDelegate().mo339z(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().mo260A(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i9) {
        super.setTitle(i9);
        getDelegate().mo264C(getContext().getString(i9));
    }

    public DialogC0123e(Context context, boolean z8, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z8, onCancelListener);
        this.mKeyDispatcher = new a();
    }
}
