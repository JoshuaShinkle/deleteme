package p047d5;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;
import com.perfectcorp.utility.C4507b;
import com.perfectcorp.utility.PromisedTask;
import com.perfectcorp.ycl.BaseActivity;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import p156o5.AbstractC5467a;

/* renamed from: d5.a */
/* loaded from: classes2.dex */
public class C4677a extends ContextWrapper {

    /* renamed from: g */
    public static Context f16370g;

    /* renamed from: h */
    public static boolean f16371h;

    /* renamed from: i */
    public static String f16372i;

    /* renamed from: j */
    public static C4677a f16373j;

    /* renamed from: o */
    public static String f16378o;

    /* renamed from: q */
    public static String f16380q;

    /* renamed from: r */
    public static String f16381r;

    /* renamed from: s */
    public static String f16382s;

    /* renamed from: t */
    public static String f16383t;

    /* renamed from: u */
    public static int f16384u;

    /* renamed from: v */
    public static int f16385v;

    /* renamed from: a */
    public String f16390a;

    /* renamed from: b */
    public File f16391b;

    /* renamed from: c */
    public static final String f16366c = File.separator;

    /* renamed from: d */
    public static BaseActivity f16367d = null;

    /* renamed from: e */
    public static boolean f16368e = false;

    /* renamed from: f */
    public static String f16369f = NetworkManager.BC_SERVER_MODE.PRODUCTION;

    /* renamed from: k */
    public static final List<Runnable> f16374k = Collections.synchronizedList(new LinkedList());

    /* renamed from: l */
    public static String f16375l = "Webinars";

    /* renamed from: m */
    public static String f16376m = "2.0";

    /* renamed from: n */
    public static String f16377n = "for Android";

    /* renamed from: p */
    public static String f16379p = null;

    /* renamed from: w */
    public static String f16386w = "1.0";

    /* renamed from: x */
    public static String f16387x = "gcm";

    /* renamed from: y */
    public static String f16388y = "Mozilla/5.0";

    /* renamed from: z */
    public static boolean f16389z = false;

    /* renamed from: A */
    public static float f16361A = 3.0f;

    /* renamed from: B */
    public static String f16362B = NetworkManager.BC_SERVER_MODE.PRODUCTION;

    /* renamed from: C */
    public static PromisedTask<?, ?, Boolean> f16363C = null;

    /* renamed from: D */
    public static Toast f16364D = null;

    /* renamed from: E */
    public static final Handler f16365E = new Handler(Looper.getMainLooper());

    /* renamed from: d5.a$a */
    public class a extends PromisedTask<Void, Void, Void> {
        public a() {
        }

        @Override // com.perfectcorp.utility.PromisedTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void r32) {
            SharedPreferences sharedPreferencesM18705i = C4677a.m18705i();
            if (C4677a.m18681E()) {
                String unused = C4677a.f16369f = NetworkManager.BC_SERVER_MODE.DEMO1;
                return null;
            }
            String unused2 = C4677a.f16369f = sharedPreferencesM18705i.getString("BC_Server_Mode", NetworkManager.BC_SERVER_MODE.PRODUCTION);
            if (C4677a.f16369f != null) {
                return null;
            }
            String unused3 = C4677a.f16369f = NetworkManager.BC_SERVER_MODE.PRODUCTION;
            return null;
        }
    }

    /* renamed from: d5.a$b */
    public class b implements Runnable {

        /* renamed from: b */
        public final /* synthetic */ CharSequence f16393b;

        /* renamed from: c */
        public final /* synthetic */ int f16394c;

        public b(CharSequence charSequence, int i9) {
            this.f16393b = charSequence;
            this.f16394c = i9;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C4677a.f16364D != null) {
                C4677a.f16364D.cancel();
            }
            Toast unused = C4677a.f16364D = Toast.makeText(C4677a.m18710n(), this.f16393b, this.f16394c);
            C4677a.f16364D.show();
        }
    }

    /* renamed from: d5.a$c */
    public class c extends PromisedTask<String, Void, Void> {
        @Override // com.perfectcorp.utility.PromisedTask
        public Void doInBackground(String str) {
            SharedPreferences.Editor editorEdit = C4677a.m18710n().getApplicationContext().getSharedPreferences("BeautyCircleResumeActivityFile", 0).edit();
            editorEdit.putString("BeautyCircleResumeActivity", str);
            editorEdit.apply();
            return null;
        }
    }

    /* renamed from: d5.a$d */
    public class d extends PromisedTask<Void, Void, Void> {

        /* renamed from: a */
        public final /* synthetic */ String f16395a;

        public d(String str) {
            this.f16395a = str;
        }

        @Override // com.perfectcorp.utility.PromisedTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void r42) {
            C4677a.m18705i().edit().putString("VERSION_UPGRADE_HISTORY", this.f16395a).putLong("UPGRADE_DIALOG_TIMES", 0L).putLong("UPGRADE_DIALOG_TIMESTAMP", 0L).apply();
            return null;
        }
    }

    public C4677a(Context context) {
        super(context);
        this.f16390a = null;
        this.f16391b = null;
    }

    /* renamed from: A */
    public static String m18677A() {
        return f16377n;
    }

    /* renamed from: B */
    public static boolean m18678B() {
        C4507b.f15929a = AbstractC5467a.m21110b("DEVELOPER_MODE_STATUS", false);
        C4507b.f15930b = AbstractC5467a.m21110b("DEVELOPER_WEP_REQUEST_TOAST", false);
        C4507b.f15931c = AbstractC5467a.m21110b("DEVELOPER_ALLOW_CHARGE", false);
        C4507b.f15932d = AbstractC5467a.m21110b("DEVELOPER_ALLOW_CREATE_LIVE", false);
        m18710n();
        if (m18680D()) {
            C4507b.f15929a = true;
        }
        return C4507b.f15929a;
    }

    /* renamed from: C */
    public static boolean m18679C() {
        return new File(Environment.getExternalStorageDirectory() + File.separator + "webinar_debug").exists();
    }

    /* renamed from: D */
    public static boolean m18680D() {
        return f16368e;
    }

    /* renamed from: E */
    public static boolean m18681E() {
        return f16371h;
    }

    /* renamed from: F */
    public static boolean m18682F(Activity activity) throws SecurityException {
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) activity.getSystemService("activity")).getRunningTasks(10);
            if (runningTasks.get(0).numActivities == 1) {
                return runningTasks.get(0).topActivity.getClassName().equals(activity.getClass().getName());
            }
            return false;
        } catch (Exception e9) {
            e9.printStackTrace();
            return false;
        }
    }

    /* renamed from: H */
    public static boolean m18683H(Runnable runnable) {
        return f16365E.post(runnable);
    }

    /* renamed from: I */
    public static boolean m18684I(Runnable runnable, long j9) {
        return f16365E.postDelayed(runnable, j9);
    }

    /* renamed from: K */
    public static void m18685K(Runnable runnable) {
        f16365E.removeCallbacks(runnable);
    }

    /* renamed from: L */
    public static void m18686L(String str) {
        m18705i().edit().putString("DisplayName", str).apply();
    }

    /* renamed from: M */
    public static void m18687M(String str) {
        m18705i().edit().putString("DoInitUrl", str).apply();
    }

    /* renamed from: N */
    public static void m18688N(Map<String, String> map) {
        NetworkManager.setServerInitParameter(map);
    }

    /* renamed from: O */
    public static void m18689O(boolean z8) {
        f16371h = z8;
    }

    /* renamed from: P */
    public static void m18690P(String str) {
        f16372i = str;
    }

    /* renamed from: Q */
    public static PromisedTask<?, ?, Void> m18691Q(String str) {
        return new c().execute(str);
    }

    /* renamed from: R */
    public static void m18692R(String str) {
        new d(str).execute(null);
    }

    /* renamed from: T */
    public static void m18693T(String str) {
        m18694U(str, 1);
    }

    /* renamed from: U */
    public static void m18694U(String str, int i9) {
        if (C4507b.f15929a || m18679C()) {
            m18696W("[DEBUG] " + str, i9);
        }
    }

    /* renamed from: V */
    public static void m18695V(CharSequence charSequence) {
        m18696W(charSequence, 0);
    }

    /* renamed from: W */
    public static void m18696W(CharSequence charSequence, int i9) {
        BaseActivity baseActivity = f16367d;
        if (baseActivity != null) {
            baseActivity.runOnUiThread(new b(charSequence, i9));
        }
    }

    /* renamed from: e */
    public static void m18701e(String str) {
        if (str == null) {
            return;
        }
        String strM18722z = m18722z();
        if (strM18722z.isEmpty()) {
            m18692R(str);
            return;
        }
        int iLastIndexOf = strM18722z.lastIndexOf(";");
        if (strM18722z.substring(iLastIndexOf <= 0 ? 0 : iLastIndexOf + 1, strM18722z.length()).equalsIgnoreCase(str)) {
            return;
        }
        m18692R(strM18722z + ";" + str);
    }

    /* renamed from: f */
    public static int m18702f(int i9) {
        return (int) m18710n().getResources().getDimension(i9);
    }

    /* renamed from: g */
    public static String m18703g() {
        return f16386w;
    }

    /* renamed from: h */
    public static String m18704h() {
        return f16387x;
    }

    /* renamed from: i */
    public static SharedPreferences m18705i() {
        return m18710n().getApplicationContext().getSharedPreferences("BeautyCircle", 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* renamed from: j */
    public static String m18706j() {
        int i9 = 1;
        try {
            String str = m18710n().getApplicationContext().getPackageManager().getPackageInfo(m18710n().getApplicationContext().getPackageName(), 0).versionName;
            f16378o = str;
            String[] strArrSplit = str.split("\\.", 4);
            i9 = strArrSplit.length >= 3 ? String.format("%s.%s.%s", strArrSplit[0], strArrSplit[1], strArrSplit[2]) : f16378o;
            return i9;
        } catch (PackageManager.NameNotFoundException e9) {
            Object[] objArr = new Object[i9];
            objArr[0] = "Could not get versionName name: " + e9;
            C4507b.m18102c(objArr);
            return "";
        }
    }

    /* renamed from: k */
    public static String m18707k() {
        return f16378o;
    }

    /* renamed from: l */
    public static String m18708l() {
        return m18705i().getString("DisplayName", "");
    }

    /* renamed from: m */
    public static String m18709m() {
        return m18705i().getString("DoInitUrl", "");
    }

    /* renamed from: n */
    public static C4677a m18710n() {
        return m18711o(f16367d);
    }

    /* renamed from: o */
    public static C4677a m18711o(Context context) {
        if (f16373j == null) {
            if (context == null) {
                context = f16370g;
            }
            C4677a c4677a = new C4677a(context.getApplicationContext());
            C4507b.f15938j = context.getApplicationContext();
            c4677a.m18723G();
        }
        return f16373j;
    }

    /* renamed from: p */
    public static String m18712p() {
        return f16380q;
    }

    /* renamed from: q */
    public static boolean m18713q(String str, boolean z8) {
        return m18705i().getBoolean(str, z8);
    }

    /* renamed from: r */
    public static String m18714r() {
        return f16372i;
    }

    /* renamed from: s */
    public static String m18715s() {
        return f16383t;
    }

    /* renamed from: t */
    public static String m18716t() {
        return m18710n().getResources().getString(C4682f.REVISION);
    }

    /* renamed from: u */
    public static String m18717u() {
        return f16369f;
    }

    /* renamed from: v */
    public static String m18718v() {
        if (f16379p == null) {
            f16379p = Settings.Secure.getString(m18710n().getContentResolver(), "android_id");
        }
        return f16379p;
    }

    /* renamed from: w */
    public static String m18719w() {
        return f16388y;
    }

    /* renamed from: x */
    public static String m18720x() {
        return f16381r;
    }

    /* renamed from: y */
    public static String m18721y() {
        return f16376m;
    }

    /* renamed from: z */
    public static String m18722z() {
        return m18705i().getString("VERSION_UPGRADE_HISTORY", "");
    }

    /* renamed from: G */
    public void m18723G() {
        f16373j = this;
        m18701e(m18706j());
        new a().execute(null);
        f16380q = Build.MODEL;
        f16381r = Build.MANUFACTURER;
        f16382s = Build.CPU_ABI;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i9 = displayMetrics.widthPixels;
        f16384u = i9;
        int i10 = displayMetrics.heightPixels;
        f16385v = i10;
        if (i9 > 0 && i10 > 0) {
            f16383t = String.valueOf(f16385v) + "x" + String.valueOf(f16384u);
        }
        f16361A = displayMetrics.density;
        f16368e = (getApplicationInfo().flags & 2) != 0;
        m18678B();
        C4507b.m18101b("Application startup. Build version :" + m18707k());
    }

    /* renamed from: J */
    public void m18724J(Runnable runnable) {
        f16374k.add(0, runnable);
    }

    /* renamed from: S */
    public void m18725S(Class<?> cls) {
        if (cls == null) {
            this.f16390a = "";
        } else {
            this.f16390a = cls.getSimpleName();
        }
        m18691Q(this.f16390a);
    }

    /* renamed from: X */
    public void m18726X(Runnable runnable) {
        f16374k.remove(runnable);
    }
}
