package p074g2;

import android.util.Log;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/* renamed from: g2.a */
/* loaded from: classes.dex */
public class C4831a {

    /* renamed from: a */
    public static final String f16818a = "a";

    /* renamed from: b */
    public static boolean f16819b = false;

    /* renamed from: g2.a$a */
    public class a implements OnCompleteListener<Void> {

        /* renamed from: a */
        public final /* synthetic */ FirebaseRemoteConfig f16820a;

        /* renamed from: b */
        public final /* synthetic */ long f16821b;

        public a(FirebaseRemoteConfig firebaseRemoteConfig, long j9) {
            this.f16820a = firebaseRemoteConfig;
            this.f16821b = j9;
        }

        @Override // com.google.android.gms.tasks.OnCompleteListener
        public void onComplete(Task<Void> task) {
            Log.i(C4831a.f16818a, "RemoteConfig fetch completed, isSuccessful: " + task.isSuccessful());
            if (task.isSuccessful()) {
                this.f16820a.activateFetched();
            } else {
                Exception exception = task.getException();
                if (exception != null) {
                    ULogUtility.m16670f(C4831a.f16818a, "fetch fail reason:" + exception.getMessage());
                }
            }
            Log.d(C4831a.f16818a, "remoteConfig.fetch cost " + ((System.currentTimeMillis() - this.f16821b) / 1000.0f) + " seconds");
        }
    }

    /* renamed from: b */
    public static long m19168b(String str) {
        return FirebaseRemoteConfig.getInstance().getLong(str);
    }

    /* renamed from: c */
    public static String m19169c(String str) {
        return FirebaseRemoteConfig.getInstance().getString(str);
    }

    /* renamed from: d */
    public static void m19170d(int i9, boolean z8) {
        if (f16819b) {
            Log.i(f16818a, "RemoteConfig have been initialized.");
            return;
        }
        f16819b = true;
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettingsBuild = new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(z8).build();
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.setConfigSettings(firebaseRemoteConfigSettingsBuild);
        firebaseRemoteConfig.setDefaults(i9);
        firebaseRemoteConfig.fetch(0).addOnCompleteListener(new a(firebaseRemoteConfig, System.currentTimeMillis()));
    }
}
