package p078g6;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;
import p068f6.InterfaceC4789a;

/* renamed from: g6.h */
/* loaded from: classes2.dex */
public class C4967h implements InterfaceC4789a {

    /* renamed from: a */
    public final Uri f17060a = Uri.parse("content://com.android.badge/badge");

    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return Collections.singletonList("com.zui.launcher");
    }

    @Override // p068f6.InterfaceC4789a
    @TargetApi(11)
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) {
        Bundle bundle = new Bundle();
        bundle.putInt("app_badge_count", i9);
        context.getContentResolver().call(this.f17060a, "setAppBadgeCount", (String) null, bundle);
    }
}
