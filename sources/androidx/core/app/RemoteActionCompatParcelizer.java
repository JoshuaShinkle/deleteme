package androidx.core.app;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel versionedParcel) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        remoteActionCompat.f1769a = (IconCompat) versionedParcel.m3162v(remoteActionCompat.f1769a, 1);
        remoteActionCompat.f1770b = versionedParcel.m3152l(remoteActionCompat.f1770b, 2);
        remoteActionCompat.f1771c = versionedParcel.m3152l(remoteActionCompat.f1771c, 3);
        remoteActionCompat.f1772d = (PendingIntent) versionedParcel.m3158r(remoteActionCompat.f1772d, 4);
        remoteActionCompat.f1773e = versionedParcel.m3148h(remoteActionCompat.f1773e, 5);
        remoteActionCompat.f1774f = versionedParcel.m3148h(remoteActionCompat.f1774f, 6);
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, VersionedParcel versionedParcel) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        versionedParcel.m3164x(false, false);
        versionedParcel.m3139M(remoteActionCompat.f1769a, 1);
        versionedParcel.m3130D(remoteActionCompat.f1770b, 2);
        versionedParcel.m3130D(remoteActionCompat.f1771c, 3);
        versionedParcel.m3134H(remoteActionCompat.f1772d, 4);
        versionedParcel.m3166z(remoteActionCompat.f1773e, 5);
        versionedParcel.m3166z(remoteActionCompat.f1774f, 6);
    }
}
