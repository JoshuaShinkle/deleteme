package com.google.android.gms.gcm;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.iid.InstanceID;
import java.io.IOException;
import java.util.regex.Pattern;

@Deprecated
/* loaded from: classes2.dex */
public class GcmPubSub {
    private static GcmPubSub zzo;
    private static final Pattern zzq = Pattern.compile("/topics/[a-zA-Z0-9-_.~%]{1,900}");
    private InstanceID zzp;

    private GcmPubSub(Context context) {
        this.zzp = InstanceID.getInstance(context);
    }

    @Deprecated
    public static synchronized GcmPubSub getInstance(Context context) {
        if (zzo == null) {
            GoogleCloudMessaging.zze(context);
            zzo = new GcmPubSub(context);
        }
        return zzo;
    }

    @Deprecated
    public void subscribe(String str, String str2, Bundle bundle) throws IOException {
        if (str == null || str.isEmpty()) {
            String strValueOf = String.valueOf(str);
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Invalid appInstanceToken: ".concat(strValueOf) : new String("Invalid appInstanceToken: "));
        }
        if (str2 == null || !zzq.matcher(str2).matches()) {
            String strValueOf2 = String.valueOf(str2);
            throw new IllegalArgumentException(strValueOf2.length() != 0 ? "Invalid topic name: ".concat(strValueOf2) : new String("Invalid topic name: "));
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("gcm.topic", str2);
        this.zzp.getToken(str, str2, bundle);
    }

    @Deprecated
    public void unsubscribe(String str, String str2) throws IOException {
        Bundle bundle = new Bundle();
        bundle.putString("gcm.topic", str2);
        this.zzp.zzd(str, str2, bundle);
    }
}
