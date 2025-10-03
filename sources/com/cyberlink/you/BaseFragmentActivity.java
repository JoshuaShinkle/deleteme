package com.cyberlink.you;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.FragmentActivity;
import com.cyberlink.you.utility.Permission.Permission;
import p127l4.InterfaceC5286a;
import p127l4.InterfaceC5288c;
import p218v2.C6455c;

/* loaded from: classes.dex */
public abstract class BaseFragmentActivity extends FragmentActivity implements InterfaceC5286a {

    /* renamed from: b */
    public final C6455c f7281b = new C6455c(this);

    /* renamed from: I0 */
    public Handler m7366I0() {
        return this.f7281b.m24703a();
    }

    /* renamed from: J0 */
    public boolean m7367J0() {
        return this.f7281b.m24704b();
    }

    @Override // p127l4.InterfaceC5286a
    @TargetApi(23)
    /* renamed from: M */
    public void mo7361M(Permission permission, InterfaceC5288c interfaceC5288c) {
        this.f7281b.mo7361M(permission, interfaceC5288c);
    }

    @Override // p127l4.InterfaceC5286a
    @TargetApi(23)
    /* renamed from: a0 */
    public void mo7362a0(Permission[] permissionArr, InterfaceC5288c interfaceC5288c) {
        this.f7281b.mo7362a0(permissionArr, interfaceC5288c);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7281b.m24707e(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f7281b.m24708f();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f7281b.m24709g();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    @TargetApi(23)
    public void onRequestPermissionsResult(int i9, String[] strArr, int[] iArr) {
        this.f7281b.m24710h(i9, strArr, iArr);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f7281b.m24711i();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.f7281b.m24712j();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.f7281b.m24713k();
    }
}
