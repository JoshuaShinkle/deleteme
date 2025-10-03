package p088h6;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;

/* renamed from: h6.a */
/* loaded from: classes2.dex */
public class C5027a {
    /* renamed from: a */
    public static boolean m19603a(Context context, Intent intent) {
        List<ResolveInfo> listQueryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        return listQueryBroadcastReceivers != null && listQueryBroadcastReceivers.size() > 0;
    }
}
