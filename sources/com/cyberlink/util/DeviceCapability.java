package com.cyberlink.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.cyberlink.util.DeviceCapability;
import com.cyberlink.you.utility.CLUtility;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang3.StringUtils;
import p135m2.C5314a;
import p209u2.C6369f;
import p209u2.C6370g;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class DeviceCapability {

    /* renamed from: a */
    public static final String f7224a = "DeviceCapability";

    /* renamed from: b */
    public static Future<C1399a> f7225b;

    /* renamed from: c */
    public static int f7226c;

    /* renamed from: d */
    public static String f7227d;

    /* renamed from: e */
    public static String f7228e;

    public static class DeviceCapabilityDebugException extends RuntimeException {
    }

    /* renamed from: com.cyberlink.util.DeviceCapability$a */
    public static final class C1399a {

        /* renamed from: a */
        @SerializedName("do")
        public Boolean f7229a = null;

        /* renamed from: b */
        @SerializedName("audio_16kHz")
        public Boolean f7230b = null;

        /* renamed from: c */
        @SerializedName("aecm")
        public Boolean f7231c = null;

        /* renamed from: d */
        @SerializedName("audio_alert")
        public Boolean f7232d = null;

        /* renamed from: e */
        @SerializedName("v_out")
        public Boolean f7233e = null;

        /* renamed from: f */
        @SerializedName("v_in")
        public Boolean f7234f = null;

        /* renamed from: g */
        @SerializedName("re_init_speaker")
        public Boolean f7235g = null;

        /* renamed from: b */
        public boolean m7321b() {
            return Boolean.TRUE.equals(this.f7232d);
        }

        /* renamed from: c */
        public boolean m7322c() {
            return Boolean.FALSE.equals(this.f7233e);
        }

        /* renamed from: d */
        public boolean m7323d() {
            return Boolean.FALSE.equals(this.f7229a);
        }

        /* renamed from: e */
        public boolean m7324e() {
            return Boolean.TRUE.equals(this.f7230b);
        }

        /* renamed from: f */
        public boolean m7325f() {
            return Boolean.TRUE.equals(this.f7235g);
        }

        /* renamed from: g */
        public boolean m7326g() {
            return Boolean.TRUE.equals(this.f7231c);
        }

        /* renamed from: h */
        public final boolean m7327h(C1399a c1399a) {
            boolean z8;
            Boolean bool;
            Boolean bool2;
            Boolean bool3;
            Boolean bool4;
            Boolean bool5;
            Boolean bool6;
            Boolean bool7;
            if (this.f7229a != null || (bool7 = c1399a.f7229a) == null) {
                z8 = false;
            } else {
                this.f7229a = bool7;
                z8 = true;
            }
            if (this.f7230b == null && (bool6 = c1399a.f7230b) != null) {
                this.f7230b = bool6;
                z8 = true;
            }
            if (this.f7231c == null && (bool5 = c1399a.f7231c) != null) {
                this.f7231c = bool5;
                z8 = true;
            }
            if (this.f7232d == null && (bool4 = c1399a.f7232d) != null) {
                this.f7232d = bool4;
                z8 = true;
            }
            if (this.f7233e == null && (bool3 = c1399a.f7233e) != null) {
                this.f7233e = bool3;
                z8 = true;
            }
            if (this.f7234f == null && (bool2 = c1399a.f7234f) != null) {
                this.f7234f = bool2;
                z8 = true;
            }
            if (this.f7235g != null || (bool = c1399a.f7235g) == null) {
                return z8;
            }
            this.f7235g = bool;
            return true;
        }

        /* renamed from: i */
        public boolean m7328i() {
            return Boolean.FALSE.equals(this.f7234f);
        }

        public String toString() {
            return super.toString();
        }
    }

    /* renamed from: com.cyberlink.util.DeviceCapability$b */
    public static class C1400b {

        /* renamed from: a */
        @SerializedName("device")
        public String f7236a = null;

        /* renamed from: b */
        @SerializedName("model")
        public String f7237b = null;

        /* renamed from: c */
        @SerializedName("product")
        public String f7238c = null;

        /* renamed from: d */
        @SerializedName("brand")
        public String f7239d = null;

        /* renamed from: e */
        @SerializedName("manufacturer")
        public String f7240e = null;

        /* renamed from: f */
        @SerializedName("board")
        public String f7241f = null;

        /* renamed from: g */
        @SerializedName("hardware")
        public String f7242g = null;

        /* renamed from: h */
        @SerializedName("cpuinfo")
        public String f7243h = null;

        /* renamed from: i */
        @SerializedName("capability")
        public C1399a f7244i = null;

        /* renamed from: j */
        public List<String> f7245j = null;

        /* renamed from: k */
        @SerializedName("version")
        public String f7246k = null;

        /* renamed from: k */
        public static boolean m7331k(String str, String str2) {
            if (C6383t.m24517f(str)) {
                return false;
            }
            if (!str.contains("|") && !str.contains("*")) {
                return str.equalsIgnoreCase(str2);
            }
            for (String str3 : str.split("\\|")) {
                if (!C6383t.m24517f(str3)) {
                    if (str3.endsWith("*")) {
                        Locale locale = Locale.US;
                        if (str2.toLowerCase(locale).startsWith(str3.replaceAll("\\*", "").toLowerCase(locale))) {
                            return true;
                        }
                    } else if (str3.equalsIgnoreCase(str2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* renamed from: m */
        public static boolean m7332m(String str, List<String> list) {
            if (C6383t.m24517f(str)) {
                return false;
            }
            for (String str2 : str.split("\\|")) {
                if (!C6383t.m24517f(str2)) {
                    for (String str3 : list) {
                        if (!C6383t.m24517f(str3)) {
                            Locale locale = Locale.US;
                            if (str3.toLowerCase(locale).contains(str2.toLowerCase(locale))) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        /* renamed from: c */
        public boolean m7333c() {
            return m7331k(this.f7241f, Build.BOARD);
        }

        /* renamed from: d */
        public boolean m7334d() {
            return m7331k(this.f7239d, Build.BRAND);
        }

        /* renamed from: e */
        public boolean m7335e() {
            return m7332m(this.f7243h, this.f7245j);
        }

        /* renamed from: f */
        public boolean m7336f() {
            return m7331k(this.f7236a, Build.DEVICE);
        }

        /* renamed from: g */
        public boolean m7337g() {
            return m7331k(this.f7242g, Build.HARDWARE);
        }

        /* renamed from: h */
        public boolean m7338h() {
            return m7331k(this.f7240e, Build.MANUFACTURER);
        }

        /* renamed from: i */
        public boolean m7339i() {
            return m7331k(this.f7237b, Build.MODEL);
        }

        /* renamed from: j */
        public boolean m7340j() {
            return m7331k(this.f7238c, Build.PRODUCT);
        }

        /* renamed from: l */
        public boolean m7341l(List<String> list) {
            this.f7245j = list;
            if (m7342n(this.f7236a, Build.DEVICE)) {
                return false;
            }
            int i9 = !C6383t.m24517f(this.f7236a) ? 1 : 0;
            if (m7342n(this.f7237b, Build.MODEL)) {
                return false;
            }
            if (!C6383t.m24517f(this.f7237b)) {
                i9++;
            }
            if (m7342n(this.f7241f, Build.BOARD)) {
                return false;
            }
            if (!C6383t.m24517f(this.f7241f)) {
                i9++;
            }
            if (m7342n(this.f7242g, Build.HARDWARE)) {
                return false;
            }
            if (!C6383t.m24517f(this.f7242g)) {
                i9++;
            }
            if (m7342n(this.f7238c, Build.PRODUCT)) {
                return false;
            }
            if (!C6383t.m24517f(this.f7238c)) {
                i9++;
            }
            if (m7342n(this.f7239d, Build.BRAND)) {
                return false;
            }
            if (!C6383t.m24517f(this.f7239d)) {
                i9++;
            }
            if (m7342n(this.f7240e, Build.MANUFACTURER)) {
                return false;
            }
            if (!C6383t.m24517f(this.f7240e)) {
                i9++;
            }
            if (m7343o(this.f7243h, list)) {
                return false;
            }
            if (!C6383t.m24517f(this.f7243h)) {
                i9++;
            }
            return i9 >= 2;
        }

        /* renamed from: n */
        public boolean m7342n(String str, String str2) {
            return (C6383t.m24517f(str) || m7331k(str, str2)) ? false : true;
        }

        /* renamed from: o */
        public boolean m7343o(String str, List<String> list) {
            return (C6383t.m24517f(str) || m7332m(str, list)) ? false : true;
        }

        public String toString() {
            String str = "";
            if (m7336f()) {
                str = "\n > device:" + this.f7236a;
            }
            if (m7339i()) {
                str = str + "\n > model:" + this.f7237b;
            }
            if (m7333c()) {
                str = str + "\n > board:" + this.f7241f;
            }
            if (m7337g()) {
                str = str + "\n > hardware:" + this.f7242g;
            }
            if (m7340j()) {
                str = str + "\n > product:" + this.f7238c;
            }
            if (m7334d()) {
                str = str + "\n > brand:" + this.f7239d;
            }
            if (m7338h()) {
                str = str + "\n > manufacturer:" + this.f7240e;
            }
            if (!m7335e()) {
                return str;
            }
            return str + "\n > cpuinfo:" + this.f7243h;
        }
    }

    /* renamed from: c */
    public static boolean m7309c(BufferedReader bufferedReader) {
        try {
            final C1400b[] c1400bArr = (C1400b[]) new GsonBuilder().create().fromJson((Reader) bufferedReader, C1400b[].class);
            if (c1400bArr == null || c1400bArr.length <= 0) {
                return false;
            }
            C5314a.m20795e();
            f7225b = Executors.newSingleThreadExecutor().submit(new Callable() { // from class: u2.e
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return DeviceCapability.m7319m(c1400bArr);
                }
            });
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: d */
    public static void m7310d(Context context) {
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static C1399a m7311e() {
        C1399a c1399a;
        Future<C1399a> future = f7225b;
        if (future == null) {
            throw new IllegalStateException("Should initialize this class first");
        }
        try {
            c1399a = future.get();
        } catch (ExecutionException e9) {
            if (e9.getCause() instanceof DeviceCapabilityDebugException) {
                throw ((DeviceCapabilityDebugException) e9.getCause());
            }
            c1399a = null;
            if (c1399a == null) {
            }
        } catch (Exception unused) {
            c1399a = null;
            if (c1399a == null) {
            }
        }
        return c1399a == null ? new C1399a() : c1399a;
    }

    /* renamed from: f */
    public static List<String> m7312f() {
        try {
            List<String> listM24474p = C6369f.m24474p(new File("/proc/cpuinfo"));
            for (int i9 = 0; i9 < listM24474p.size(); i9++) {
                listM24474p.set(i9, listM24474p.get(i9).replaceAll(StringUtils.SPACE, ""));
            }
            return Collections.unmodifiableList(listM24474p);
        } catch (Throwable unused) {
            return Collections.emptyList();
        }
    }

    /* renamed from: g */
    public static int m7313g() {
        return f7226c;
    }

    /* renamed from: h */
    public static synchronized void m7314h(final Context context, final int i9, String str, String str2) {
        if (f7225b != null) {
            return;
        }
        f7227d = str;
        f7228e = str2;
        final long jCurrentTimeMillis = System.currentTimeMillis();
        f7225b = Executors.newSingleThreadExecutor().submit(new Callable() { // from class: u2.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeviceCapability.m7316j(context, i9, jCurrentTimeMillis);
            }
        });
        m7310d(context);
    }

    /* renamed from: j */
    public static /* synthetic */ C1399a m7316j(Context context, int i9, long j9) throws Throwable {
        C1399a c1399aM7317k = m7317k(context, i9);
        Log.i(f7224a, "Predefined: " + c1399aM7317k);
        return c1399aM7317k;
    }

    /* renamed from: k */
    public static C1399a m7317k(Context context, int i9) throws Throwable {
        C1400b[] c1400bArrM7318l = m7318l(context, i9);
        if (c1400bArrM7318l == null || c1400bArrM7318l.length == 0) {
            return null;
        }
        return m7319m(c1400bArrM7318l);
    }

    /* renamed from: l */
    public static C1400b[] m7318l(Context context, int i9) throws Throwable {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(CLUtility.m16508Z());
            String str = File.separator;
            sb.append(str);
            sb.append(f7227d);
            sb.append(str);
            sb.append(f7228e);
            File file = new File(sb.toString());
            if (file.exists()) {
                bufferedReader = new BufferedReader(new FileReader(file));
                inputStreamReader = null;
            } else {
                inputStreamReader = new InputStreamReader(context.getResources().openRawResource(i9));
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                } catch (Exception unused) {
                    bufferedReader = null;
                    C6370g.m24480b(bufferedReader);
                    C6370g.m24480b(inputStreamReader);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    C6370g.m24480b(bufferedReader2);
                    C6370g.m24480b(inputStreamReader);
                    throw th;
                }
            }
            try {
                C1400b[] c1400bArr = (C1400b[]) new GsonBuilder().create().fromJson((Reader) bufferedReader, C1400b[].class);
                C6370g.m24480b(bufferedReader);
                C6370g.m24480b(inputStreamReader);
                return c1400bArr;
            } catch (Exception unused2) {
                C6370g.m24480b(bufferedReader);
                C6370g.m24480b(inputStreamReader);
                return null;
            } catch (Throwable th2) {
                bufferedReader2 = bufferedReader;
                th = th2;
                C6370g.m24480b(bufferedReader2);
                C6370g.m24480b(inputStreamReader);
                throw th;
            }
        } catch (Exception unused3) {
            bufferedReader = null;
            inputStreamReader = null;
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
        }
    }

    /* renamed from: m */
    public static C1399a m7319m(C1400b[] c1400bArr) {
        List<String> listM7312f = m7312f();
        C1399a c1399a = null;
        for (C1400b c1400b : c1400bArr) {
            if (!C6383t.m24517f(c1400b.f7246k)) {
                f7226c = Integer.valueOf(c1400b.f7246k).intValue();
            } else if (c1400b.m7341l(listM7312f)) {
                if (c1399a == null) {
                    c1399a = c1400b.f7244i;
                } else {
                    c1399a.m7327h(c1400b.f7244i);
                }
            }
        }
        return c1399a;
    }
}
