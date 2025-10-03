package p256z4;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p006a5.C0035c;

/* renamed from: z4.f */
/* loaded from: classes2.dex */
public class C6830f {

    /* renamed from: h */
    public static C6830f f22711h;

    /* renamed from: i */
    public static final Object f22712i = new Object();

    /* renamed from: a */
    public final Context f22713a;

    /* renamed from: b */
    public final Boolean f22714b;

    /* renamed from: c */
    public final Boolean f22715c;

    /* renamed from: d */
    public final DisplayMetrics f22716d;

    /* renamed from: e */
    public final String f22717e;

    /* renamed from: f */
    public final Integer f22718f;

    /* renamed from: g */
    public final String f22719g;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public C6830f(Context context) throws NoSuchMethodException, PackageManager.NameNotFoundException, SecurityException {
        String str;
        Integer numValueOf;
        int i9;
        String string;
        Method method;
        Boolean bool;
        Boolean bool2;
        this.f22713a = context;
        PackageManager packageManager = context.getPackageManager();
        Boolean bool3 = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            str = packageInfo.versionName;
            try {
                numValueOf = Integer.valueOf(packageInfo.versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
                C0035c.m149k("MixpanelAPI.SysInfo", "System information constructed with a context that apparently doesn't exist.");
                numValueOf = null;
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                i9 = applicationInfo.labelRes;
                this.f22717e = str;
                this.f22718f = numValueOf;
                if (i9 != 0) {
                }
                this.f22719g = string;
                method = packageManager.getClass().getMethod("hasSystemFeature", String.class);
                if (method == null) {
                }
                this.f22714b = bool3;
                this.f22715c = bool2;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                this.f22716d = displayMetrics;
                ((WindowManager) this.f22713a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            str = null;
        }
        ApplicationInfo applicationInfo2 = context.getApplicationInfo();
        i9 = applicationInfo2.labelRes;
        this.f22717e = str;
        this.f22718f = numValueOf;
        if (i9 != 0) {
            CharSequence charSequence = applicationInfo2.nonLocalizedLabel;
            string = charSequence == null ? "Misc" : charSequence.toString();
        } else {
            string = context.getString(i9);
        }
        this.f22719g = string;
        try {
            method = packageManager.getClass().getMethod("hasSystemFeature", String.class);
        } catch (NoSuchMethodException unused3) {
            method = null;
        }
        if (method == null) {
            try {
                bool = (Boolean) method.invoke(packageManager, "android.hardware.nfc");
                try {
                    bool2 = (Boolean) method.invoke(packageManager, "android.hardware.telephony");
                } catch (IllegalAccessException unused4) {
                    C0035c.m149k("MixpanelAPI.SysInfo", "System version appeared to support PackageManager.hasSystemFeature, but we were unable to call it.");
                    bool2 = null;
                    bool3 = bool;
                    this.f22714b = bool3;
                    this.f22715c = bool2;
                    DisplayMetrics displayMetrics2 = new DisplayMetrics();
                    this.f22716d = displayMetrics2;
                    ((WindowManager) this.f22713a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics2);
                } catch (InvocationTargetException unused5) {
                    C0035c.m149k("MixpanelAPI.SysInfo", "System version appeared to support PackageManager.hasSystemFeature, but we were unable to call it.");
                    bool2 = null;
                    bool3 = bool;
                    this.f22714b = bool3;
                    this.f22715c = bool2;
                    DisplayMetrics displayMetrics22 = new DisplayMetrics();
                    this.f22716d = displayMetrics22;
                    ((WindowManager) this.f22713a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics22);
                }
            } catch (IllegalAccessException unused6) {
                bool = null;
            } catch (InvocationTargetException unused7) {
                bool = null;
            }
            bool3 = bool;
        } else {
            bool2 = null;
        }
        this.f22714b = bool3;
        this.f22715c = bool2;
        DisplayMetrics displayMetrics222 = new DisplayMetrics();
        this.f22716d = displayMetrics222;
        ((WindowManager) this.f22713a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics222);
    }

    /* renamed from: f */
    public static C6830f m25531f(Context context) {
        synchronized (f22712i) {
            if (f22711h == null) {
                f22711h = new C6830f(context.getApplicationContext());
            }
        }
        return f22711h;
    }

    /* renamed from: a */
    public Integer m25532a() {
        return this.f22718f;
    }

    /* renamed from: b */
    public String m25533b() {
        return this.f22717e;
    }

    /* renamed from: c */
    public String m25534c() {
        return this.f22713a.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le") ? "ble" : this.f22713a.getPackageManager().hasSystemFeature("android.hardware.bluetooth") ? "classic" : "none";
    }

    /* renamed from: d */
    public String m25535d() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f22713a.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    /* renamed from: e */
    public DisplayMetrics m25536e() {
        return this.f22716d;
    }

    /* renamed from: g */
    public boolean m25537g() {
        return this.f22714b.booleanValue();
    }

    /* renamed from: h */
    public boolean m25538h() {
        return this.f22715c.booleanValue();
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: i */
    public Boolean m25539i() {
        BluetoothAdapter defaultAdapter;
        try {
            if (this.f22713a.getPackageManager().checkPermission("android.permission.BLUETOOTH", this.f22713a.getPackageName()) != 0 || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
                return null;
            }
            return Boolean.valueOf(defaultAdapter.isEnabled());
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    @SuppressLint({"MissingPermission"})
    /* renamed from: j */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Boolean m25540j() {
        boolean z8;
        if (this.f22713a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return null;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f22713a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            z8 = activeNetworkInfo.getType() == 1 && activeNetworkInfo.isConnected();
        }
        return Boolean.valueOf(z8);
    }
}
