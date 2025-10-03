package p209u2;

import android.content.Context;
import android.provider.Settings;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* renamed from: u2.u */
/* loaded from: classes.dex */
public final class C6384u {
    /* renamed from: a */
    public static boolean m24521a(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), "always_finish_activities") != 0;
        } catch (Settings.SettingNotFoundException unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public static void m24522b(String str, AbstractC6378o<Void, Exception, String> abstractC6378o) throws Throwable {
        System.currentTimeMillis();
        String line = null;
        try {
            try {
                Process processExec = Runtime.getRuntime().exec(str);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                while (true) {
                    try {
                        line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        abstractC6378o.m24500o(line + "\n");
                    } catch (Exception e9) {
                        e = e9;
                        line = bufferedReader;
                        abstractC6378o.m24508f(e);
                        C6370g.m24480b(line);
                    } catch (Throwable th) {
                        th = th;
                        line = bufferedReader;
                        C6370g.m24480b(line);
                        throw th;
                    }
                }
                processExec.waitFor();
                abstractC6378o.m24505c();
                C6370g.m24480b(bufferedReader);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e10) {
            e = e10;
        }
    }
}
