package p218v2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.cyberlink.you.utility.Permission.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import p127l4.C5287b;
import p127l4.InterfaceC5286a;
import p127l4.InterfaceC5288c;
import p236x2.C6566a;

/* renamed from: v2.c */
/* loaded from: classes.dex */
public final class C6455c implements InterfaceC5286a {

    /* renamed from: b */
    public final Activity f21725b;

    /* renamed from: e */
    public InterfaceC5288c f21728e;

    /* renamed from: f */
    public Map<String, Boolean> f21729f;

    /* renamed from: d */
    public boolean f21727d = false;

    /* renamed from: c */
    public final Handler f21726c = new Handler(Looper.getMainLooper());

    public C6455c(Activity activity) {
        this.f21725b = activity;
    }

    @Override // p127l4.InterfaceC5286a
    @TargetApi(23)
    /* renamed from: M */
    public void mo7361M(Permission permission, InterfaceC5288c interfaceC5288c) {
        this.f21728e = interfaceC5288c;
        HashMap map = new HashMap();
        this.f21729f = map;
        map.put(permission.mo16657b(), Boolean.valueOf(this.f21725b.shouldShowRequestPermissionRationale(permission.mo16657b())));
        this.f21725b.requestPermissions(new String[]{permission.mo16657b()}, permission.mo16656a());
    }

    /* renamed from: a */
    public Handler m24703a() {
        return this.f21726c;
    }

    @Override // p127l4.InterfaceC5286a
    @TargetApi(23)
    /* renamed from: a0 */
    public void mo7362a0(Permission[] permissionArr, InterfaceC5288c interfaceC5288c) {
        this.f21728e = interfaceC5288c;
        ArrayList arrayList = new ArrayList();
        for (Permission permission : permissionArr) {
            if (this.f21725b.checkSelfPermission(permission.mo16657b()) != 0) {
                arrayList.add(permission.mo16657b());
            }
        }
        this.f21729f = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            this.f21729f.put(str, Boolean.valueOf(this.f21725b.shouldShowRequestPermissionRationale(str)));
        }
        this.f21725b.requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), C5287b.m20578a(permissionArr));
    }

    /* renamed from: b */
    public boolean m24704b() {
        return this.f21725b.isFinishing() || m24706d();
    }

    /* renamed from: c */
    public final boolean m24705c(int[] iArr) {
        boolean z8 = iArr.length > 0;
        for (int i9 : iArr) {
            z8 &= i9 == 0;
        }
        return z8;
    }

    /* renamed from: d */
    public boolean m24706d() {
        return this.f21727d;
    }

    /* renamed from: e */
    public void m24707e(Bundle bundle) {
    }

    /* renamed from: f */
    public void m24708f() {
        this.f21727d = true;
    }

    /* renamed from: g */
    public void m24709g() {
    }

    @TargetApi(23)
    /* renamed from: h */
    public void m24710h(int i9, String[] strArr, int[] iArr) {
        InterfaceC5288c interfaceC5288c = this.f21728e;
        if (interfaceC5288c == null) {
            return;
        }
        if (strArr.length <= 0) {
            interfaceC5288c.mo6907a(false);
            return;
        }
        if (m24705c(iArr)) {
            this.f21728e.mo6908b();
            return;
        }
        boolean z8 = true;
        for (int i10 = 0; i10 < Math.min(strArr.length, iArr.length); i10++) {
            if (iArr[i10] == -1) {
                z8 &= !this.f21725b.shouldShowRequestPermissionRationale(strArr[i10]);
            }
        }
        this.f21728e.mo6907a(z8);
    }

    /* renamed from: i */
    public void m24711i() {
    }

    /* renamed from: j */
    public void m24712j() {
        C6566a.m25166y(this.f21725b);
    }

    /* renamed from: k */
    public void m24713k() {
        C6566a.m25165x(this.f21725b);
    }
}
