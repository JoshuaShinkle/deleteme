package p095i3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import com.cyberlink.you.alarm.AlarmReceiver;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.ULogUtility;
import com.google.android.gms.auth.api.credentials.CredentialsApi;

/* renamed from: i3.a */
/* loaded from: classes.dex */
public final class C5048a {
    /* renamed from: a */
    public static boolean m19721a(Context context) {
        AlarmManager alarmManager;
        Intent intent = new Intent(context, (Class<?>) AlarmReceiver.class);
        int i9 = Build.VERSION.SDK_INT;
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, i9 >= 31 ? 201326592 : 0);
        if (broadcast == null || (alarmManager = (AlarmManager) context.getSystemService("alarm")) == null) {
            return false;
        }
        FriendsClient.m15653U();
        if (i9 >= 33) {
            ULogUtility.m16690z("Is idle mode: " + ((PowerManager) context.getSystemService("power")).isDeviceIdleMode());
            alarmManager.setAndAllowWhileIdle(0, System.currentTimeMillis() + ((long) (FriendsClient.m15653U() * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)), broadcast);
            return true;
        }
        ULogUtility.m16690z("Is idle mode: " + ((PowerManager) context.getSystemService("power")).isDeviceIdleMode());
        alarmManager.setExactAndAllowWhileIdle(0, System.currentTimeMillis() + ((long) (FriendsClient.m15653U() * CredentialsApi.ACTIVITY_RESULT_ADD_ACCOUNT)), broadcast);
        return true;
    }
}
