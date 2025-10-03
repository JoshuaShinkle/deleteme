package p078g6;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import p068f6.InterfaceC4789a;

/* renamed from: g6.g */
/* loaded from: classes2.dex */
public class C4966g implements InterfaceC4789a {
    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return new ArrayList(0);
    }

    @Override // p068f6.InterfaceC4789a
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) {
        Bundle bundle = new Bundle();
        bundle.putInt("app_badge_count", i9);
        bundle.putString("app_badge_component_name", componentName.flattenToString());
        context.getContentResolver().call(Uri.parse("content://com.android.launcher3.cornermark.unreadbadge"), "setAppUnreadCount", (String) null, bundle);
    }
}
