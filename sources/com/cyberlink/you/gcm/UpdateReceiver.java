package com.cyberlink.you.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.cyberlink.you.Globals;
import com.cyberlink.you.friends.FriendsClient;
import com.cyberlink.you.utility.ULogUtility;
import org.apache.commons.lang3.StringUtils;
import p095i3.C5048a;

/* loaded from: classes.dex */
public class UpdateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            String action = intent.getAction();
            ULogUtility.m16689y("onReceive: " + action);
            if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                String packageName = Globals.m7388i0().getPackageName();
                if (schemeSpecificPart == null || schemeSpecificPart.isEmpty() || packageName == null || packageName.isEmpty()) {
                    return;
                }
                ULogUtility.m16689y("PackageName: " + schemeSpecificPart + StringUtils.SPACE + packageName);
                if (packageName.equals(schemeSpecificPart)) {
                    FriendsClient.m15678o();
                    C5048a.m19721a(context);
                }
            }
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }
}
