package androidx.appcompat.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.C0256t0;
import androidx.fragment.app.FragmentActivity;
import p071g.AbstractC4796b;
import p188s.C6226a;
import p188s.C6230e;
import p188s.C6242q;

/* loaded from: classes.dex */
public class AppCompatActivity extends FragmentActivity implements InterfaceC0121c, C6242q.a {

    /* renamed from: b */
    public AbstractC0122d f278b;

    /* renamed from: c */
    public Resources f279c;

    @Override // p188s.C6242q.a
    /* renamed from: C */
    public Intent mo248C() {
        return C6230e.m23808a(this);
    }

    /* renamed from: I0 */
    public AbstractC0122d m249I0() {
        if (this.f278b == null) {
            this.f278b = AbstractC0122d.m399e(this, this);
        }
        return this.f278b;
    }

    /* renamed from: J0 */
    public AbstractC0119a m250J0() {
        return m249I0().mo311k();
    }

    /* renamed from: K0 */
    public void m251K0(C6242q c6242q) {
        c6242q.m23882b(this);
    }

    /* renamed from: L0 */
    public void m252L0(int i9) {
    }

    /* renamed from: M0 */
    public void m253M0(C6242q c6242q) {
    }

    @Deprecated
    /* renamed from: N0 */
    public void m254N0() {
    }

    /* renamed from: O0 */
    public boolean m255O0() {
        Intent intentMo248C = mo248C();
        if (intentMo248C == null) {
            return false;
        }
        if (!m258R0(intentMo248C)) {
            m257Q0(intentMo248C);
            return true;
        }
        C6242q c6242qM23880d = C6242q.m23880d(this);
        m251K0(c6242qM23880d);
        m253M0(c6242qM23880d);
        c6242qM23880d.m23884e();
        try {
            C6226a.m23783i(this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    /* renamed from: P0 */
    public final boolean m256P0(KeyEvent keyEvent) {
        return false;
    }

    /* renamed from: Q0 */
    public void m257Q0(Intent intent) {
        C6230e.m23812e(this, intent);
    }

    /* renamed from: R0 */
    public boolean m258R0(Intent intent) {
        return C6230e.m23813f(this, intent);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        m249I0().mo297b(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(m249I0().mo300d(context));
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        AbstractC0119a abstractC0119aM250J0 = m250J0();
        if (getWindow().hasFeature(0)) {
            if (abstractC0119aM250J0 == null || !abstractC0119aM250J0.m368f()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        AbstractC0119a abstractC0119aM250J0 = m250J0();
        if (keyCode == 82 && abstractC0119aM250J0 != null && abstractC0119aM250J0.m377o(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i9) {
        return (T) m249I0().mo304g(i9);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return m249I0().mo309j();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.f279c == null && C0256t0.m1064b()) {
            this.f279c = new C0256t0(this, super.getResources());
        }
        Resources resources = this.f279c;
        return resources == null ? super.getResources() : resources;
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        m249I0().mo315m();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f279c != null) {
            this.f279c.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        m249I0().mo317n(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        m254N0();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        AbstractC0122d abstractC0122dM249I0 = m249I0();
        abstractC0122dM249I0.mo313l();
        abstractC0122dM249I0.mo319o(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m249I0().mo321p();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (m256P0(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i9, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i9, MenuItem menuItem) {
        if (super.onMenuItemSelected(i9, menuItem)) {
            return true;
        }
        AbstractC0119a abstractC0119aM250J0 = m250J0();
        if (menuItem.getItemId() != 16908332 || abstractC0119aM250J0 == null || (abstractC0119aM250J0.mo371i() & 4) == 0) {
            return false;
        }
        return m255O0();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i9, Menu menu) {
        return super.onMenuOpened(i9, menu);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i9, Menu menu) {
        super.onPanelClosed(i9, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        m249I0().mo323q(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        m249I0().mo325r();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        m249I0().mo327s(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        m249I0().mo329t();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        m249I0().mo331u();
    }

    @Override // androidx.appcompat.app.InterfaceC0121c
    public void onSupportActionModeFinished(AbstractC4796b abstractC4796b) {
    }

    @Override // androidx.appcompat.app.InterfaceC0121c
    public void onSupportActionModeStarted(AbstractC4796b abstractC4796b) {
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i9) {
        super.onTitleChanged(charSequence, i9);
        m249I0().mo264C(charSequence);
    }

    @Override // androidx.appcompat.app.InterfaceC0121c
    public AbstractC4796b onWindowStartingSupportActionMode(AbstractC4796b.a aVar) {
        return null;
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        AbstractC0119a abstractC0119aM250J0 = m250J0();
        if (getWindow().hasFeature(0)) {
            if (abstractC0119aM250J0 == null || !abstractC0119aM250J0.m378p()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i9) {
        m249I0().mo337y(i9);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i9) {
        super.setTheme(i9);
        m249I0().mo262B(i9);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        m249I0().mo315m();
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        m249I0().mo339z(view);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        m249I0().mo260A(view, layoutParams);
    }
}
