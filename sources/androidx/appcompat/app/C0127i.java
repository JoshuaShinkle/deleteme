package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Calendar;
import org.apache.commons.lang3.time.DateUtils;
import p197t.C6274b;

/* renamed from: androidx.appcompat.app.i */
/* loaded from: classes.dex */
public class C0127i {

    /* renamed from: d */
    public static C0127i f392d;

    /* renamed from: a */
    public final Context f393a;

    /* renamed from: b */
    public final LocationManager f394b;

    /* renamed from: c */
    public final a f395c = new a();

    /* renamed from: androidx.appcompat.app.i$a */
    public static class a {

        /* renamed from: a */
        public boolean f396a;

        /* renamed from: b */
        public long f397b;

        /* renamed from: c */
        public long f398c;

        /* renamed from: d */
        public long f399d;

        /* renamed from: e */
        public long f400e;

        /* renamed from: f */
        public long f401f;
    }

    public C0127i(Context context, LocationManager locationManager) {
        this.f393a = context;
        this.f394b = locationManager;
    }

    /* renamed from: a */
    public static C0127i m408a(Context context) {
        if (f392d == null) {
            Context applicationContext = context.getApplicationContext();
            f392d = new C0127i(applicationContext, (LocationManager) applicationContext.getSystemService(FirebaseAnalytics.Param.LOCATION));
        }
        return f392d;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: b */
    public final Location m409b() {
        Location locationM410c = C6274b.m24031b(this.f393a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? m410c("network") : null;
        Location locationM410c2 = C6274b.m24031b(this.f393a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? m410c("gps") : null;
        return (locationM410c2 == null || locationM410c == null) ? locationM410c2 != null ? locationM410c2 : locationM410c : locationM410c2.getTime() > locationM410c.getTime() ? locationM410c2 : locationM410c;
    }

    /* renamed from: c */
    public final Location m410c(String str) {
        try {
            if (this.f394b.isProviderEnabled(str)) {
                return this.f394b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e9) {
            Log.d("TwilightManager", "Failed to get last known location", e9);
            return null;
        }
    }

    /* renamed from: d */
    public boolean m411d() {
        a aVar = this.f395c;
        if (m412e()) {
            return aVar.f396a;
        }
        Location locationM409b = m409b();
        if (locationM409b != null) {
            m413f(locationM409b);
            return aVar.f396a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i9 = Calendar.getInstance().get(11);
        return i9 < 6 || i9 >= 22;
    }

    /* renamed from: e */
    public final boolean m412e() {
        return this.f395c.f401f > System.currentTimeMillis();
    }

    /* renamed from: f */
    public final void m413f(Location location) {
        long j9;
        a aVar = this.f395c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        C0126h c0126hM406b = C0126h.m406b();
        c0126hM406b.m407a(jCurrentTimeMillis - DateUtils.MILLIS_PER_DAY, location.getLatitude(), location.getLongitude());
        long j10 = c0126hM406b.f389a;
        c0126hM406b.m407a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z8 = c0126hM406b.f391c == 1;
        long j11 = c0126hM406b.f390b;
        long j12 = c0126hM406b.f389a;
        c0126hM406b.m407a(DateUtils.MILLIS_PER_DAY + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j13 = c0126hM406b.f390b;
        if (j11 == -1 || j12 == -1) {
            j9 = jCurrentTimeMillis + 43200000;
        } else {
            j9 = (jCurrentTimeMillis > j12 ? 0 + j13 : jCurrentTimeMillis > j11 ? 0 + j12 : 0 + j11) + 60000;
        }
        aVar.f396a = z8;
        aVar.f397b = j10;
        aVar.f398c = j11;
        aVar.f399d = j12;
        aVar.f400e = j13;
        aVar.f401f = j9;
    }
}
