package p125l2;

import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/* renamed from: l2.c */
/* loaded from: classes.dex */
public final class C5279c {

    /* renamed from: a */
    public static final String f17883a;

    static {
        if (new File("/sys/devices/platform/omap").exists()) {
            f17883a = "omap";
        } else if (new File("/sys/devices/platform/tegra-nvmap").exists() || new File("/sys/devices/platform/tegra-rtc").exists()) {
            f17883a = "tegra";
        } else {
            f17883a = "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0036, code lost:
    
        r5 = r0.substring(r2 + 1).trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0040, code lost:
    
        r1.close();
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m20557a(String str) throws IOException {
        String strTrim;
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo"), Charset.forName("UTF-8")));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            bufferedReader2.close();
                            break;
                        }
                        int iIndexOf = line.indexOf(58);
                        if (iIndexOf > 0 && str.equalsIgnoreCase(line.substring(0, iIndexOf).trim())) {
                            break;
                        }
                    } catch (Throwable unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader == null) {
                            return "";
                        }
                        bufferedReader.close();
                        return "";
                    }
                }
                return strTrim;
            } catch (Throwable unused2) {
            }
        } catch (IOException unused3) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m20558b() {
        return m20557a("Hardware");
    }

    /* renamed from: c */
    public static boolean m20559c(String str) {
        return Build.HARDWARE.equalsIgnoreCase(str) || m20558b().equalsIgnoreCase(str);
    }
}
