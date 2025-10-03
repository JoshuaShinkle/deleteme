package com.cyberlink.you.fcm;

import android.content.SharedPreferences;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.ULogUtility;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import p209u2.C6383t;

/* loaded from: classes.dex */
public class UInstanceIDListenerService extends FirebaseInstanceIdService {
    @Override // com.google.firebase.iid.FirebaseInstanceIdService
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        ULogUtility.m16689y("Refreshed FCM token: " + token);
        SharedPreferences sharedPreferences = Globals.m7388i0().getSharedPreferences("U", 0);
        if (C6383t.m24512a(sharedPreferences.getString("regId", ""), token)) {
            return;
        }
        sharedPreferences.edit().putString("regId", token).putBoolean("needUpdateRegId", true).apply();
        FriendsClient.m15678o();
    }
}
