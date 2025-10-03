package p015b4;

import android.content.Context;
import android.content.res.Resources;
import com.cyberlink.p030U.R;
import java.util.Objects;
import p201t3.C6288b;

/* renamed from: b4.k */
/* loaded from: classes.dex */
public class C0681k {

    /* renamed from: b4.k$a */
    public interface a<T> {
        void onError(String str);

        void onSuccess(T t8);
    }

    /* renamed from: b4.k$b */
    public static class b implements C6288b.h {

        /* renamed from: a */
        public a f3291a;

        public b(a aVar) {
            this.f3291a = aVar;
        }

        @Override // p201t3.C6288b.h
        public void onError(String str) {
            this.f3291a.onError(C0681k.m3398d(str));
        }
    }

    /* renamed from: b */
    public static void m3396b(String str, a<String> aVar) {
        Objects.requireNonNull(aVar);
        C0672b.m3380b(str, new C0677g(aVar), new b(aVar)).m24088p();
    }

    /* renamed from: c */
    public static void m3397c(String str, a<String> aVar) {
        Objects.requireNonNull(aVar);
        C0672b.m3381c(str, new C0677g(aVar), new b(aVar)).m24088p();
    }

    /* renamed from: d */
    public static String m3398d(String str) {
        if (str.contains("The email is invalid") || str.contains("Bad Request")) {
            return "400";
        }
        if (str.contains("The account is not found")) {
            return "404";
        }
        if (str.contains("The password is invalid") || str.contains("Password field is required")) {
            return "401";
        }
        if (str.contains("Duplicated with an existing email") || str.contains("Account already exists")) {
            return "400.1";
        }
        if (str.contains("The verification code is invalid")) {
            return "400.2";
        }
        if (str.contains("The account token is invalid")) {
            return "400.3";
        }
        if (str.contains("The device is different than requested one")) {
            return "400.6";
        }
        if (str.contains("Too Many Requests")) {
            return "429";
        }
        if (!str.contains("Server connection fail")) {
            if (str.contains("Account not activated")) {
                return "403";
            }
            if (str.contains("Incorrect email or password")) {
                return "401";
            }
            if (str.contains("The account binding is invalid")) {
                return "400.7";
            }
            if (!str.contains("Display Name field is required")) {
                return str.contains("The email has been locked for being bound or registered") ? "423" : "error_unknown";
            }
        }
        return "500";
    }

    /* renamed from: e */
    public static String m3399e(Context context, String str) {
        Resources resources;
        resources = context.getResources();
        str.hashCode();
        switch (str) {
            case "400":
                return resources.getString(R.string.error_login_invalid_email);
            case "401":
                return resources.getString(R.string.error_login_wrong_password);
            case "404":
                return resources.getString(R.string.error_login_wrong_account);
            case "429":
                return resources.getString(R.string.error_login_too_many_requests);
            case "500":
                return resources.getString(R.string.error_server_response);
            case "400.1":
                return resources.getString(R.string.error_login_duplicate_email);
            case "400.2":
                return resources.getString(R.string.error_login_invalid_code);
            case "400.3":
                return resources.getString(R.string.error_server_response);
            case "400.6":
                return resources.getString(R.string.error_login_different_device);
            case "400.7":
                return resources.getString(R.string.bind_cyberlink_account_invalid);
            default:
                return resources.getString(R.string.feedback_unknown_error);
        }
    }

    /* renamed from: f */
    public static /* synthetic */ void m3400f(a aVar, C0671a c0671a) {
        aVar.onSuccess(c0671a.m3378b());
    }

    /* renamed from: g */
    public static void m3401g(String str, String str2, final a<C0684n> aVar) {
        Objects.requireNonNull(aVar);
        C0672b.m3382d(str, str2, new C6288b.d() { // from class: b4.i
            @Override // p201t3.C6288b.d
            public final void onComplete(Object obj) {
                aVar.onSuccess((C0684n) obj);
            }
        }, new b(aVar)).m24088p();
    }

    /* renamed from: h */
    public static void m3402h(String str, String str2, a<String> aVar) {
        Objects.requireNonNull(aVar);
        C0672b.m3383e(str, str2, new C0677g(aVar), new b(aVar)).m24088p();
    }

    /* renamed from: i */
    public static void m3403i(String str, a<String> aVar) {
        Objects.requireNonNull(aVar);
        C0672b.m3379a(str, new C0677g(aVar), new b(aVar)).m24088p();
    }

    /* renamed from: j */
    public static void m3404j(String str, String str2, a<C0683m> aVar) {
        Objects.requireNonNull(aVar);
        C0672b.m3384f(str, str2, new C0678h(aVar), new b(aVar)).m24088p();
    }

    /* renamed from: k */
    public static void m3405k(String str, a<C0683m> aVar) {
        Objects.requireNonNull(aVar);
        C0672b.m3385g(str, new C0678h(aVar), new b(aVar)).m24088p();
    }

    /* renamed from: l */
    public static void m3406l(String str, String str2, String str3, boolean z8, String str4, String str5, String str6, final a<String> aVar) {
        C0672b.m3386h(str, str2, str3, z8, str4, str5, str6, new C6288b.d() { // from class: b4.j
            @Override // p201t3.C6288b.d
            public final void onComplete(Object obj) {
                C0681k.m3400f(aVar, (C0671a) obj);
            }
        }, new b(aVar)).m24088p();
    }
}
