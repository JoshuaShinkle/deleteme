package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import p068f6.InterfaceC4789a;
import p088h6.C5027a;

/* loaded from: classes2.dex */
public class DefaultBadger implements InterfaceC4789a {
    @Override // p068f6.InterfaceC4789a
    /* renamed from: a */
    public List<String> mo19020a() {
        return new ArrayList(0);
    }

    @Override // p068f6.InterfaceC4789a
    /* renamed from: b */
    public void mo19021b(Context context, ComponentName componentName, int i9) throws ShortcutBadgeException {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i9);
        intent.putExtra("badge_count_package_name", componentName.getPackageName());
        intent.putExtra("badge_count_class_name", componentName.getClassName());
        if (C5027a.m19603a(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
    }

    /* renamed from: c */
    public boolean m20985c(Context context) {
        return C5027a.m19603a(context, new Intent("android.intent.action.BADGE_COUNT_UPDATE"));
    }
}
