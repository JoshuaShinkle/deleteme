package com.perfectcorp.ycl;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.perfectcorp.utility.C4507b;
import com.perfectcorp.utility.C4508c;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.lang.reflect.Field;
import p047d5.C4677a;
import p047d5.C4682f;
import p057e5.AbstractC4758c;
import p077g5.C4959a;
import p167p5.C6103a;

@SuppressLint({"Registered"})
/* loaded from: classes2.dex */
public class BaseActivity extends FragmentActivity {

    /* renamed from: i */
    public static long f15965i;

    /* renamed from: j */
    public static int f15966j;

    /* renamed from: k */
    public static long f15967k;

    /* renamed from: b */
    public final C4512b f15968b = new C4512b(this);

    /* renamed from: c */
    public final C4513c f15969c = new C4513c(this);

    /* renamed from: d */
    public Runnable f15970d = null;

    /* renamed from: e */
    public boolean f15971e = true;

    /* renamed from: f */
    public boolean f15972f = false;

    /* renamed from: g */
    public Bundle f15973g = null;

    /* renamed from: h */
    public boolean f15974h = false;

    /* renamed from: com.perfectcorp.ycl.BaseActivity$a */
    public class RunnableC4511a implements Runnable {
        public RunnableC4511a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BaseActivity.this.isFinishing()) {
                return;
            }
            BaseActivity.this.f15969c.m18142a();
        }
    }

    /* renamed from: com.perfectcorp.ycl.BaseActivity$b */
    public static class C4512b extends BroadcastReceiver {

        /* renamed from: a */
        public BaseActivity f15976a;

        public C4512b(BaseActivity baseActivity) {
            this.f15976a = baseActivity;
        }

        /* renamed from: a */
        public void m18140a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.f15976a.registerReceiver(this, intentFilter);
        }

        /* renamed from: b */
        public void m18141b() {
            this.f15976a.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                this.f15976a.mo13677O0();
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                this.f15976a.m18135P0();
            }
        }
    }

    /* renamed from: com.perfectcorp.ycl.BaseActivity$c */
    public static class C4513c {

        /* renamed from: a */
        public final FragmentActivity f15977a;

        /* renamed from: b */
        public final C6103a f15978b = new C6103a();

        public C4513c(FragmentActivity fragmentActivity) {
            this.f15977a = (FragmentActivity) C4959a.m19234a(fragmentActivity);
        }

        /* renamed from: a */
        public boolean m18142a() {
            if (this.f15977a.isFinishing()) {
                return true;
            }
            if (C4677a.m18682F(this.f15977a)) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (BaseActivity.f15967k == 0 || jCurrentTimeMillis - BaseActivity.f15967k > 3000) {
                    long unused = BaseActivity.f15967k = jCurrentTimeMillis;
                    C4677a.m18696W(this.f15977a.getString(C4682f.bc_back_message), 1);
                    return true;
                }
            }
            this.f15977a.onBackPressed();
            return true;
        }
    }

    /* renamed from: L0 */
    public Runnable m18134L0() {
        RunnableC4511a runnableC4511a = new RunnableC4511a();
        this.f15970d = runnableC4511a;
        return runnableC4511a;
    }

    /* renamed from: M0 */
    public void mo13673M0() {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(findViewById(R.id.content).getWindowToken(), 0);
    }

    /* renamed from: N0 */
    public boolean mo13675N0() {
        return this.f15969c.m18142a();
    }

    /* renamed from: O0 */
    public void mo13677O0() {
    }

    /* renamed from: P0 */
    public void m18135P0() {
    }

    /* renamed from: Q0 */
    public void m18136Q0() {
        if (this.f15972f) {
            return;
        }
        this.f15972f = true;
        if (f15966j == 0) {
            f15965i = System.currentTimeMillis();
        }
        f15966j++;
    }

    /* renamed from: R0 */
    public void m18137R0() {
        if (this.f15972f) {
            this.f15972f = false;
            int i9 = f15966j - 1;
            f15966j = i9;
            if (i9 == 0) {
                NetworkManager.dumpUriHistory();
            }
        }
    }

    /* renamed from: S0 */
    public final void m18138S0(Bundle bundle) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        Class<?> superclass = getClass();
        do {
            for (Field field : superclass.getDeclaredFields()) {
                Class<?> type = field.getType();
                if (Fragment.class.isAssignableFrom(type)) {
                    try {
                        field.setAccessible(true);
                        Fragment fragmentMo1850g = getSupportFragmentManager().mo1850g(bundle, type.getName());
                        if (fragmentMo1850g != null) {
                            C4507b.m18101b("fieldType = ", type.getName());
                            field.set(this, fragmentMo1850g);
                        }
                    } catch (IllegalAccessException e9) {
                        e9.printStackTrace();
                    } catch (IllegalArgumentException e10) {
                        e10.printStackTrace();
                    } catch (IllegalStateException e11) {
                        e11.printStackTrace();
                    }
                }
            }
            superclass = superclass.getSuperclass();
        } while (BaseActivity.class.isAssignableFrom(superclass));
    }

    /* renamed from: T0 */
    public final void m18139T0(Bundle bundle) throws SecurityException {
        Class<?> superclass = getClass();
        do {
            for (Field field : superclass.getDeclaredFields()) {
                Class<?> type = field.getType();
                if (Fragment.class.isAssignableFrom(type)) {
                    try {
                        field.setAccessible(true);
                        Fragment fragment = (Fragment) field.get(this);
                        if (fragment != null && fragment.isAdded() && !fragment.isRemoving()) {
                            getSupportFragmentManager().mo1855l(bundle, type.getName(), fragment);
                        }
                    } catch (IllegalAccessException e9) {
                        e9.printStackTrace();
                    } catch (IllegalArgumentException e10) {
                        e10.printStackTrace();
                    } catch (IllegalStateException e11) {
                        e11.printStackTrace();
                    }
                }
            }
            superclass = superclass.getSuperclass();
        } while (BaseActivity.class.isAssignableFrom(superclass));
    }

    @Override // android.app.Activity
    public void finish() {
        C4677a.m18710n().m18725S(null);
        super.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i9, int i10, Intent intent) {
        super.onActivityResult(i9, i10, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        C4677a.m18710n().m18725S(null);
        mo13673M0();
        super.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        C4507b.m18101b(new Object[0]);
        super.onCreate(bundle);
        C4677a.f16367d = this;
        Runnable runnableM18134L0 = m18134L0();
        if (runnableM18134L0 != null) {
            C4677a.m18710n().m18724J(runnableM18134L0);
        }
        if (bundle != null) {
            m18138S0(bundle);
        }
        this.f15968b.m18140a();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() throws SecurityException {
        C4507b.m18108i(getClass().getSimpleName());
        if (this.f15970d != null) {
            C4677a.m18710n().m18726X(this.f15970d);
            this.f15970d = null;
        }
        for (Field field : getClass().getDeclaredFields()) {
            if (AbstractC4758c.a.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    if (AbstractC4758c.m18889a((AbstractC4758c.a) field.get(this))) {
                        C4507b.m18102c(getClass().getSimpleName(), ".", field.getName(), " is registered but not removed when Activity.onDestroy!!!");
                    }
                } catch (Exception e9) {
                    C4507b.m18102c(e9);
                }
            }
        }
        if (C4677a.f16367d == this) {
            C4677a.f16367d = null;
        }
        this.f15968b.m18141b();
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i9, KeyEvent keyEvent) {
        if (i9 != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i9, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i9, KeyEvent keyEvent) {
        return (i9 == 4 && getFragmentManager().getBackStackEntryCount() == 0 && keyEvent.isTracking() && !keyEvent.isCanceled()) ? mo13675N0() : super.onKeyUp(i9, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.f15974h = true;
        C4507b.m18108i(getClass().getSimpleName());
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    @TargetApi(23)
    public void onRequestPermissionsResult(int i9, String[] strArr, int[] iArr) {
        C4508c.m18116c(this, i9, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        C4507b.m18108i("savedInstanceState: ", bundle.toString());
        super.onRestoreInstanceState(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        this.f15974h = false;
        C4507b.m18108i(getClass().getSimpleName());
        C4677a.f16367d = this;
        super.onResume();
        C4677a.m18710n().m18725S(getClass());
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) throws SecurityException {
        C4507b.m18108i("outState before super: ", bundle.toString());
        bundle.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(bundle);
        m18139T0(bundle);
        C4507b.m18108i("outState after super: ", bundle.toString());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        C4507b.m18108i(getClass().getSimpleName());
        super.onStart();
        if (this.f15971e) {
            m18136Q0();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        C4507b.m18108i(getClass().getSimpleName());
        super.onStop();
        m18137R0();
    }
}
