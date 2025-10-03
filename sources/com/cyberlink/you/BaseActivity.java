package com.cyberlink.you;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.cyberlink.you.utility.Permission.Permission;
import p127l4.InterfaceC5286a;
import p127l4.InterfaceC5288c;
import p218v2.C6455c;

/* loaded from: classes.dex */
public abstract class BaseActivity extends Activity implements InterfaceC5286a {

    /* renamed from: b */
    public final C6455c f7280b = new C6455c(this);

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public /* synthetic */ void m7360f(View view) {
        onBackPressed();
    }

    @Override // p127l4.InterfaceC5286a
    @TargetApi(23)
    /* renamed from: M */
    public void mo7361M(Permission permission, InterfaceC5288c interfaceC5288c) {
        this.f7280b.mo7361M(permission, interfaceC5288c);
    }

    @Override // p127l4.InterfaceC5286a
    @TargetApi(23)
    /* renamed from: a0 */
    public void mo7362a0(Permission[] permissionArr, InterfaceC5288c interfaceC5288c) {
        this.f7280b.mo7362a0(permissionArr, interfaceC5288c);
    }

    /* renamed from: d */
    public Handler m7363d() {
        return this.f7280b.m24703a();
    }

    /* renamed from: e */
    public boolean m7364e() {
        return this.f7280b.m24704b();
    }

    /* renamed from: g */
    public void m7365g(int i9) {
        findViewById(i9).setOnClickListener(new View.OnClickListener() { // from class: v2.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f21724b.m7360f(view);
            }
        });
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7280b.m24707e(bundle);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f7280b.m24708f();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.f7280b.m24709g();
    }

    @Override // android.app.Activity
    @TargetApi(23)
    public void onRequestPermissionsResult(int i9, String[] strArr, int[] iArr) {
        this.f7280b.m24710h(i9, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.f7280b.m24711i();
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.f7280b.m24712j();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.f7280b.m24713k();
    }
}
