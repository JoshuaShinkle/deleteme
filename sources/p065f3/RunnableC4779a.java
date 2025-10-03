package p065f3;

import android.content.Context;
import android.content.SharedPreferences;
import com.cyberlink.you.Globals;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import p209u2.C6383t;

/* renamed from: f3.a */
/* loaded from: classes.dex */
public class RunnableC4779a implements Runnable {
    /* renamed from: a */
    public static void m19012a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("U", 0);
        if (C6383t.m24512a(sharedPreferences.getString("regId", ""), str)) {
            return;
        }
        ULogUtility.m16689y("Got FCM token: " + str);
        sharedPreferences.edit().putString("regId", str).putBoolean("needUpdateRegId", true).apply();
    }

    @Override // java.lang.Runnable
    public void run() {
        String strM7493U0 = Globals.m7388i0().m7493U0();
        if (!C6383t.m24517f(strM7493U0)) {
            ULogUtility.m16689y("Cached FCM token: " + strM7493U0);
            return;
        }
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Globals.m7372O()) != 0) {
            return;
        }
        String token = FirebaseInstanceId.getInstance().getToken();
        if (C6383t.m24517f(token)) {
            return;
        }
        m19012a(Globals.m7372O(), token);
    }
}
