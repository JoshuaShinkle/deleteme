package p127l4;

import android.app.Activity;
import com.cyberlink.you.utility.Permission.Permission;

/* renamed from: l4.b */
/* loaded from: classes.dex */
public class C5287b {

    /* renamed from: a */
    public static final boolean f17913a = false;

    /* renamed from: a */
    public static int m20578a(Permission[] permissionArr) {
        int iMo16656a = 0;
        for (Permission permission : permissionArr) {
            iMo16656a |= permission.mo16656a();
        }
        return iMo16656a;
    }

    /* renamed from: b */
    public static boolean m20579b(Permission permission, Activity activity) {
        return m20581d(activity, permission);
    }

    /* renamed from: c */
    public static boolean m20580c(Permission[] permissionArr, Activity activity) {
        if (f17913a) {
            return true;
        }
        for (Permission permission : permissionArr) {
            if (!m20581d(activity, permission)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    public static boolean m20581d(Activity activity, Permission permission) {
        return f17913a || activity.checkSelfPermission(permission.mo16657b()) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: e */
    public static void m20582e(Permission permission, InterfaceC5288c interfaceC5288c, Activity activity) {
        if (activity instanceof InterfaceC5286a) {
            ((InterfaceC5286a) activity).mo7361M(permission, interfaceC5288c);
            return;
        }
        throw new IllegalArgumentException(activity.getClass().getName() + " did not implement PermissionAsker");
    }

    /* renamed from: f */
    public static void m20583f(Permission permission, InterfaceC5288c interfaceC5288c, Activity activity) {
        if (!m20579b(permission, activity)) {
            m20582e(permission, interfaceC5288c, activity);
        } else if (interfaceC5288c != null) {
            interfaceC5288c.mo6908b();
        }
    }

    /* renamed from: g */
    public static void m20584g(Permission[] permissionArr, InterfaceC5288c interfaceC5288c, Activity activity) {
        if (!m20580c(permissionArr, activity)) {
            m20585h(permissionArr, interfaceC5288c, activity);
        } else if (interfaceC5288c != null) {
            interfaceC5288c.mo6908b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: h */
    public static void m20585h(Permission[] permissionArr, InterfaceC5288c interfaceC5288c, Activity activity) {
        if (activity instanceof InterfaceC5286a) {
            ((InterfaceC5286a) activity).mo7362a0(permissionArr, interfaceC5288c);
            return;
        }
        throw new IllegalArgumentException(activity.getClass().getName() + " did not implement PermissionAsker");
    }
}
