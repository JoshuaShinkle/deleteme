package com.google.android.gms.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

@Deprecated
/* loaded from: classes2.dex */
public class InstanceIDListenerService extends zze {
    public static void zzd(Context context, zzak zzakVar) {
        zzakVar.zzz();
        Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra("CMD", "RST");
        intent.setClassName(context, "com.google.android.gms.gcm.GcmReceiver");
        context.sendBroadcast(intent);
    }

    @Override // com.google.android.gms.iid.zze
    public void handleIntent(Intent intent) {
        Bundle bundle;
        if ("com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("subtype");
            if (stringExtra != null) {
                bundle = new Bundle();
                bundle.putString("subtype", stringExtra);
            } else {
                bundle = null;
            }
            InstanceID instanceID = InstanceID.getInstance(this, bundle);
            String stringExtra2 = intent.getStringExtra("CMD");
            if (Log.isLoggable("InstanceID", 3)) {
                StringBuilder sb = new StringBuilder(String.valueOf(stringExtra).length() + 34 + String.valueOf(stringExtra2).length());
                sb.append("Service command. subtype:");
                sb.append(stringExtra);
                sb.append(" command:");
                sb.append(stringExtra2);
                Log.d("InstanceID", sb.toString());
            }
            if ("RST".equals(stringExtra2)) {
                instanceID.zzo();
                onTokenRefresh();
                return;
            }
            if ("RST_FULL".equals(stringExtra2)) {
                if (InstanceID.zzp().isEmpty()) {
                    return;
                }
                InstanceID.zzp().zzz();
                onTokenRefresh();
                return;
            }
            if ("SYNC".equals(stringExtra2)) {
                zzak zzakVarZzp = InstanceID.zzp();
                String strValueOf = String.valueOf(stringExtra);
                zzakVarZzp.zzi("|T|".length() != 0 ? strValueOf.concat("|T|") : new String(strValueOf));
                String strValueOf2 = String.valueOf(stringExtra);
                zzakVarZzp.zzi("|T-timestamp|".length() != 0 ? strValueOf2.concat("|T-timestamp|") : new String(strValueOf2));
                onTokenRefresh();
            }
        }
    }

    public void onTokenRefresh() {
    }
}
